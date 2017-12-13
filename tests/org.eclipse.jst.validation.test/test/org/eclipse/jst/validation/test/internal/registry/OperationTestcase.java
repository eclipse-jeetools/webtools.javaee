package org.eclipse.jst.validation.test.internal.registry;

import java.util.logging.Level;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.validation.test.BVTValidationPlugin;
import org.eclipse.jst.validation.test.IOperationRunnable;
import org.eclipse.jst.validation.test.setup.IBuffer;

/**
 * Instead of testing a validator, an operation test case tests the 
 * validation framework.
 */
public class OperationTestcase implements ITestcaseMetaData {
	private String _pluginId = null; // The plugin id that has contributed this test case.
	private String _projectName = null; // the name of the project that this test case tests
	private String _inputFileName = null;
	private String _opName = null; // the name of the test case
	private IOperationRunnable _runnable = null;

	public OperationTestcase(String pluginId, String projectName, String opName, String inputFileName, IOperationRunnable runnable) {
		_pluginId = pluginId;
		_projectName = projectName;
		_inputFileName = inputFileName;
		_opName = opName;
		_runnable = runnable;
	}
	
	public String getName() {
		return _opName;
	}
	
	public boolean isVisible() {
		return true; // framework test cases are always visible on the test collector menu
	}
	
	public String getDeclaringPluginId() {
		return _pluginId;
	}
	
	public String getProjectName() {
		return _projectName;
	}
	
	public IProject getProject() {
		return TestcaseUtility.findProject(this);
	}
	
	public String getInputFileName() {
		return _inputFileName;
	}
	
	public IOperationRunnable getRunnable() {
		return _runnable;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.validation.test.internal.registry.ITestcaseMetaData#run(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.jst.validation.test.setup.IBuffer, org.eclipse.core.resources.IProject)
	 */
	public void run(IBuffer buffer, IProject project) {
		try {
			getRunnable().setBuffer(buffer);
			getRunnable().setProject(project);
			getRunnable().setName(getName());
			ResourcesPlugin.getWorkspace().run(getRunnable(), buffer.getProgressMonitor());
		}
		catch(CoreException exc) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc); //$NON-NLS-1$
			}
		}
	}

	public String toString() {
		return getName();
	}
}
