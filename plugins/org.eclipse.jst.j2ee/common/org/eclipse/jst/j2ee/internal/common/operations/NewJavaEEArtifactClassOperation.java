/*******************************************************************************
 * Copyright (c) 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.common.operations;

import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.JAVA_PACKAGE;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.JAVA_PACKAGE_FRAGMENT_ROOT;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.SOURCE_FOLDER;
import static org.eclipse.wst.common.componentcore.internal.operation.IArtifactEditOperationDataModelProperties.PROJECT_NAME;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.codegen.jet.JETEmitter;
import org.eclipse.emf.codegen.jet.JETException;
import org.eclipse.jdt.core.IJavaModelMarker;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.WTPJETEmitter;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.WTPPlugin;
import org.eclipse.wst.common.frameworks.internal.enablement.nonui.WFTWrappedException;

public abstract class NewJavaEEArtifactClassOperation extends AbstractDataModelOperation {

	/**
	 * The extension name for a java class
	 */
	protected static final String DOT_JAVA = ".java"; //$NON-NLS-1$

	public NewJavaEEArtifactClassOperation(IDataModel dataModel) {
		super(dataModel);
	}

	@Override
	public IStatus execute(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		return doExecute(monitor, info);
	}
	
	/**
	 * Subclasses may extend this method to add their own actions during
	 * execution. The implementation of the execute method drives the running of
	 * the operation. This implementation will create the java source folder,
	 * create the java package, and then if using annotations, will use
	 * templates to generate an annotated web artifact java class, or if it is
	 * not annotated, the web artifact java class file will be created without
	 * the annotated tags using templates. Optionally, subclasses may extend the
	 * generateUsingTemplates or createJavaFile method rather than extend the
	 * execute method. This method will accept a null parameter.
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
	 * @see NewWebClassOperation#generateUsingTemplates(IProgressMonitor,
	 *      IPackageFragment)
	 * 
	 * @param monitor
	 * @throws CoreException
	 * @throws InterruptedException
	 * @throws InvocationTargetException
	 */
	public IStatus doExecute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		// Create source folder if it does not exist
		createJavaSourceFolder();
		// Create java package if it does not exist
		IPackageFragment pack = createJavaPackage();
		// Generate using templates
		try {
			generateUsingTemplates(monitor, pack);
		} catch (Exception e) {
			return J2EEPlugin.createStatus(IStatus.ERROR, e.getMessage(), e);
		}
		return OK_STATUS;
	}
	
	protected abstract void generateUsingTemplates(IProgressMonitor monitor,
			IPackageFragment fragment) throws WFTWrappedException,
			CoreException;

	/**
	 * This method will return the java package as specified by the new java
	 * class data model. If the package does not exist, it will create the
	 * package. This method should not return null.
	 * 
	 * @see #JAVA_PACKAGE
	 * @see IPackageFragmentRoot#createPackageFragment(java.lang.String,
	 *      boolean, org.eclipse.core.runtime.IProgressMonitor)
	 * 
	 * @return IPackageFragment the java package
	 */
	protected final IPackageFragment createJavaPackage() {
		// Retrieve the package name from the java class data model
		String packageName = model.getStringProperty(JAVA_PACKAGE);
		IPackageFragmentRoot packRoot = (IPackageFragmentRoot) model.getProperty(JAVA_PACKAGE_FRAGMENT_ROOT);
		IPackageFragment pack = packRoot.getPackageFragment(packageName);
		// Handle default package
		if (pack == null) {
			pack = packRoot.getPackageFragment(""); //$NON-NLS-1$
		}
		// Create the package fragment if it does not exist
		if (!pack.exists()) {
			String packName = pack.getElementName();
			try {
				pack = packRoot.createPackageFragment(packName, true, null);
			} catch (JavaModelException e) {
				J2EEPlugin.logError(e);
			}
		}
		// Return the package
		return pack;
	}

	/**
	 * This method is intended for internal use only. This will use the
	 * WTPJETEmitter to create an annotated java file based on the passed in
	 * bean class template model. This method does not accept null
	 * parameters. It will not return null. If annotations are not used, it will
	 * use the non annotated template to omit the annotated tags.
	 * 
	 * @see NewMessageDrivenBeanClassOperation#generateUsingTemplates(IProgressMonitor,
	 *      IPackageFragment)
	 * @see JETEmitter#generate(org.eclipse.core.runtime.IProgressMonitor,
	 *      java.lang.Object[])
	 * @see CreateMessageDrivenBeanTemplateModel
	 * 
	 * @param tempModel
	 * @param monitor
	 * @param template_file2 
	 * @return String the source for the java file
	 * @throws JETException
	 */
	protected String generateTemplateSource(WTPPlugin plugin, CreateJavaEEArtifactTemplateModel tempModel, String template_file, IProgressMonitor monitor) throws JETException {
		URL templateURL = FileLocator.find(plugin.getBundle(), new Path(template_file), null);
		cleanUpOldEmitterProject();
		WTPJETEmitter emitter = new WTPJETEmitter(templateURL.toString(), this.getClass().getClassLoader());
		emitter.setIntelligentLinkingEnabled(true);
		emitter.addVariable(J2EEPlugin.getPlugin().getPluginID(), J2EEPlugin.getPlugin().getPluginID());
		emitter.addVariable(plugin.getPluginID(), plugin.getPluginID());
		return emitter.generate(monitor, new Object[] { tempModel });
	}

	protected void cleanUpOldEmitterProject() {
		IProject project = ProjectUtilities.getProject(WTPJETEmitter.PROJECT_NAME);
		if (project == null || !project.exists())
			return;
		try {
			IMarker[] markers = project.findMarkers(IJavaModelMarker.BUILDPATH_PROBLEM_MARKER, false, IResource.DEPTH_ZERO);
			for (int i = 0, l = markers.length; i < l; i++) {
				if (((Integer) markers[i].getAttribute(IMarker.SEVERITY)).intValue() == IMarker.SEVERITY_ERROR) {
					project.delete(true, new NullProgressMonitor());
					break;
				}
			}
		} catch (Exception e) {
			J2EEPlugin.logError(e);
		}
	}
	
	/**
	 * This method will return the java source folder as specified in the java
	 * class data model. It will create the java source folder if it does not
	 * exist. This method may return null.
	 * 
	 * @see #SOURCE_FOLDER
	 * @see IFolder#create(boolean, boolean,
	 *      org.eclipse.core.runtime.IProgressMonitor)
	 * 
	 * @return IFolder the java source folder
	 */
	protected final IFolder createJavaSourceFolder() {
		// Get the source folder name from the data model
		IFolder folder = getSourceFolder();
		// If folder does not exist, create the folder with the specified path
		if (!folder.exists()) {
			try {
				folder.create(true, true, null);
			} catch (CoreException e) {
				J2EEPlugin.logError(e);
			}
		}
		// Return the source folder
		return folder;
	}

	public IProject getTargetProject() {
		String projectName = model.getStringProperty(PROJECT_NAME);
		return ProjectUtilities.getProject(projectName);
	}
	
	protected IFolder getSourceFolder() {
		String folderFullPath = model.getStringProperty(SOURCE_FOLDER);
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		return root.getFolder(new Path(folderFullPath));
	}	

}
