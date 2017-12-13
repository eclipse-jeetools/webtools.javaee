package org.eclipse.jst.validation.test.setup;

import java.io.File;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jst.validation.test.BVTValidationPlugin;
import org.eclipse.jst.validation.test.internal.util.BVTValidationUtility;
import org.eclipse.wst.validation.ValidationFramework;
import org.eclipse.wst.validation.internal.plugin.ValidationPlugin;

/**
 * Abstract class that provides some of the functionality required to create
 * a project and populate it with the contents of a file.
 */
public abstract class AImportOperation implements IImportOperation {
	private void preRun(IProgressMonitor monitor) {
		ValidationFramework.getDefault().suspendAllValidation(true);
	}
	
	private void postRun(IProgressMonitor monitor) {
		ValidationFramework.getDefault().suspendAllValidation(false);
	}
	
	protected void deleteOldProject(IProgressMonitor monitor, String projectName) {
		// delete any existing project of this name
		IProject existingProject = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		if (existingProject.exists()) {
			try {
				existingProject.delete(true, null); // null=no progress mon (don't scare users)
			}
			catch (CoreException exc) {
				BVTValidationPlugin.getPlugin().handleException(exc);
			}
		}
	}
	
	public boolean run(IProgressMonitor monitor, File file) {
		boolean imported = true;
		int executionMap = 0x0;
		IProject project = null;
		try {
			if(monitor == null) {
				monitor = new NullProgressMonitor();
			}
			
			preRun(monitor);
			
			try {
				String projectName = BVTValidationUtility.getProjectName(file);
				deleteOldProject(monitor, projectName);
				project = createNewProject(monitor, projectName, file);
				
				if((project == null) || (!project.isAccessible())) {
					executionMap |= 0x1;
					imported = false;
					String message = "Cannot import file because IProject is null or not accessible."; //$NON-NLS-1$
					monitor.subTask(message);
					ValidationPlugin.getPlugin().logMessage(IStatus.ERROR, message);
				}
				else {				
					imported = importFile(monitor, project, file);
					executionMap |= 0x2;
				}
			}
			catch (Throwable exc) {
				executionMap |= 0x4;
				imported = false;
				ValidationPlugin.getPlugin().handleException(exc);
			}
			finally {
				postRun(monitor);
			}
		}
		finally {
			if(!imported) {
				String projectName = (project == null) ? file.getName() : project.getName();
				ValidationPlugin.getPlugin().logMessage(IStatus.ERROR, 
					"AImportOperation for " + projectName + " was unsuccessful."); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		return imported;
	}
	
	protected abstract IProject createNewProject(IProgressMonitor monitor, String projectName, File inputFile);
	
	/**
	 * Return true if the file was imported successfully.
	 */
	protected abstract boolean importFile(IProgressMonitor monitor, IProject project, File file);
}
