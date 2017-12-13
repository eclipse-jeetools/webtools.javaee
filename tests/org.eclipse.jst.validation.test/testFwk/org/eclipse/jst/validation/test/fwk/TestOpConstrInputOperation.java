package org.eclipse.jst.validation.test.fwk;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.validation.test.BVTValidationPlugin;
import org.eclipse.jst.validation.test.fwk.validator.JDTUtility;
import org.eclipse.jst.validation.test.internal.registry.ValidatorTestcase;
import org.eclipse.jst.validation.test.setup.IBuffer;

/**
 * This class constructs the input for the TestOpConstrOperation. Because
 * TestOpConstrOperation is not invoked as part of a build, when its IResources
 * are touched, a build is invoked after each touch, and that means that the
 * IResourceDelta that's constructed has only one IResource in it instead
 * of the group of changed IResource instances.
 */
public class TestOpConstrInputOperation implements IWorkspaceRunnable {
	public static final String FWK_NOBUILD_TEST_VALIDATOR_CLASS = "org.eclipse.jst.validation.test.fwk.validator.FwkNoBuildTestValidator"; //$NON-NLS-1$
	public static final String FWK_TEST_VALIDATOR_CLASS = "org.eclipse.jst.validation.test.fwk.validator.FwkTestValidator"; //$NON-NLS-1$
	public static final String PROPERTIES_VALIDATOR_CLASS = "org.eclipse.wst.validation.sample.PropertiesValidator"; //$NON-NLS-1$
	
	private IProject _project = null;
	private IBuffer _buffer = null;
	private static ICommand _builderCommand = null;
	
	private IResourceDelta _changedDelta = null;
	private IResourceDelta _emptyDelta = null;
	private JavaHelpers[] _changedClasses = null;
	
	/**
	 * IProject must exist and be open.
	 */
	public TestOpConstrInputOperation(IBuffer buffer, IProject project) {
		setProject(project);
		setBuffer(buffer);
	}

	public IBuffer getBuffer() {
		return _buffer;
	}
	
	public void setBuffer(IBuffer b) {
		_buffer = b;
	}
		
	public IProject getProject() {
		return _project;
	}
	
	public void setProject(IProject p) {
		_project = p;
	}

	/*package*/ static void debug(final IBuffer buffer, String title, IResourceDelta delta) {
		try {
			buffer.write(title);
			delta.accept(new IResourceDeltaVisitor() {
				public boolean visit(IResourceDelta subdelta) throws CoreException {
					if (subdelta == null)
						return true;

					IResource resource = subdelta.getResource();
					buffer.write("resource is: " + resource.getFullPath()); //$NON-NLS-1$
					return true; // visit the subdelta's children
				}
			});
		}
		catch(CoreException exc) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
			}
		}
	}
	
	/**
	 * Return an IResource that is not validated by the Fwk Test Validator (this
	 * resource is used for the case where a resource is changed, and a build
	 * is invoked, but the Fwk Test validator does not validate that resource and
	 * should not be launched.)
	 */	
	public static IResource[] getEmptyResources(IProject project) {
		IResource[] changedResources = new IResource[]{
			project.getFile(".classpath"), //$NON-NLS-1$
		};
		return changedResources;
	}
	
	public static void touch(IProject project, IResource[] changedResources) {
		for(int i=0; i<changedResources.length; i++) {
			IResource res = changedResources[i];
			try {
				res.touch(null); // null IProgressMonitor
			}
			catch(CoreException exc) {
				// Oh well...dirty the next file.
				Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
				if(logger.isLoggingLevel(Level.SEVERE)) {
					logger.write(Level.SEVERE, exc);
				}
			}
		}
	}

	private static IResourceDelta getDelta(IProject project, IResource[] changedResources) {
		try {
			touch(project, changedResources);
			project.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, TestOpConstrBuilder.BUILDER_ID, _builderCommand.getArguments(), null); // null IProgressMonitor
			return TestOpConstrBuilder.singleton().getDelta();
		}
		catch(CoreException exc) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
			}
			return null;
		}
	}
	
	private static void addBuilder(IProject project, boolean doAdd) {
		if(doAdd) {
			// Add the builder to the project
			try {
				IProjectDescription description = project.getDescription();
				ICommand[] oldCommands = description.getBuildSpec();
				ICommand[] newCommands = new ICommand[oldCommands.length + 1];
				System.arraycopy(oldCommands, 0, newCommands, 0, oldCommands.length);
				_builderCommand = description.newCommand();
				_builderCommand.setBuilderName(TestOpConstrBuilder.BUILDER_ID);
				newCommands[oldCommands.length] = _builderCommand;
				description.setBuildSpec(newCommands);

				project.setDescription(description, null);
			}
			catch(CoreException exc) {
				Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
				if(logger.isLoggingLevel(Level.SEVERE)) {
					logger.write(Level.SEVERE, exc);
				}
			}
		}
		else {
			// Remove the builder
			try {
				IProjectDescription description = project.getDescription();
				ICommand[] oldCommands = description.getBuildSpec();
				ICommand[] newCommands = new ICommand[oldCommands.length - 1];
				System.arraycopy(oldCommands, 0, newCommands, 0, newCommands.length);
				description.setBuildSpec(newCommands);
				project.setDescription(description, null);
			}
			catch(CoreException exc) {
				Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
				if(logger.isLoggingLevel(Level.SEVERE)) {
					logger.write(Level.SEVERE, exc);
				}
			}
		}
	}

	public void run(IProgressMonitor monitor) {
		try {
			addBuilder(getProject(), true);
			
			// First, build the project so that the subsequent builds aren't started with a null delta.
			getProject().build(IncrementalProjectBuilder.INCREMENTAL_BUILD, TestOpConstrBuilder.BUILDER_ID, _builderCommand.getArguments(), null); // null IProgressMonitor
			
			ValidatorTestcase[] tmds = JDTUtility.getVFTests(monitor, getProject());
			if((tmds == null) || (tmds.length == 0)) {
				return;
			}

			// Then calculate the deltas
			IResource[] changedResources = getChangedResources(getProject(), tmds);
			_emptyDelta = getDelta(getProject(), getEmptyResources(getProject()));
			_changedDelta = getDelta(getProject(), changedResources);
			
			// Then restore the project's build commands back to what they were.
			addBuilder(getProject(), false);

			_changedClasses = getChangedClasses(changedResources);
		}
		catch(Throwable exc) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
			}
		}
		finally {
		}
	}
	
	public IResourceDelta getChangedDelta() {
		return _changedDelta;
	}
	
	public IResourceDelta getEmptyDelta() {
		return _emptyDelta;
	}
	
	public static IResource[] getChangedResources(IProject project, ValidatorTestcase[] tmds) {
		Set temp = new HashSet();
		for(int i=0; i<tmds.length; i++) {
			ValidatorTestcase tmd = tmds[i];
			IResource[] res = tmd.getResources(project);
			for(int j=0; j<res.length; j++) {
				temp.add(res[j]);
			}
		}
		IResource[] result = new IResource[temp.size()];
		temp.toArray(result);
		return result;
	}
	
	public JavaHelpers[] getChangedClasses(IResource[] resources) {
		if(_changedClasses == null) {
			if(resources == null) {
				return new JavaHelpers[0];
			}
			
			JavaHelpers[] result = new JavaHelpers[resources.length];
			int count = 0;
			for(int i=0; i<resources.length; i++) {
				IResource resource = resources[i];
				if((resource instanceof IFile) && (resource.getFileExtension().equals("java") || resource.getFileExtension().equals("class"))) { //$NON-NLS-1$  //$NON-NLS-2$
					JavaHelpers h = JDTUtility.getJavaHelpers((IFile)resource);
					if(h != null) {
						result[count++] = h;
					}
				}
			}
			
			if(count != resources.length) {
				JavaHelpers[] temp = new JavaHelpers[count];
				System.arraycopy(result, 0, temp, 0, count);
				return temp;
			}
			else {
				return result;
			}
		}
		return _changedClasses;
	}
}

