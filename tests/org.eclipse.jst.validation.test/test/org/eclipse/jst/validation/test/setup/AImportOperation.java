package org.eclipse.jst.validation.test.setup;

import java.io.File;
import java.util.logging.Level;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jem.util.logger.LogEntry;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.validation.test.BVTValidationPlugin;
import org.eclipse.jst.validation.test.internal.util.BVTValidationUtility;
import org.eclipse.wst.validation.internal.operations.ValidatorManager;
import org.eclipse.wst.validation.plugin.ValidationPlugin;

/**
 * Abstract class that provides some of the functionality required to create
 * a project and populate it with the contents of a file.
 */
public abstract class AImportOperation implements IImportOperation {
	private void preRun(IProgressMonitor monitor) {
		ValidatorManager.getManager().suspendAllValidation(true);
	}
	
	private void postRun(IProgressMonitor monitor) {
		ValidatorManager.getManager().suspendAllValidation(false);
	}
	
	protected void deleteOldProject(IProgressMonitor monitor, String projectName) {
		// delete any existing project of this name
		IProject existingProject = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		if (existingProject.exists()) {
			try {
				existingProject.delete(true, null); // null=no progress mon (don't scare users)
			}
			catch (CoreException exc) {
				Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
				if(logger.isLoggingLevel(Level.SEVERE)) {
					logger.write(Level.SEVERE, exc);
				}
			}
		}
	}
	
	public boolean run(IProgressMonitor monitor, File file) {
		boolean imported = true;
		int executionMap = 0x0;
		IProject project = null;
		Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
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
					if(logger.isLoggingLevel(Level.SEVERE)) {
						LogEntry entry = ValidationPlugin.getLogEntry();
						entry.setExecutionMap(executionMap);
						entry.setText(message);
						logger.write(Level.SEVERE, entry);
					}

				}
				else {				
					imported = importFile(monitor, project, file);
					executionMap |= 0x2;
				}
			}
			catch (Throwable exc) {
				executionMap |= 0x4;
				imported = false;
				if(logger.isLoggingLevel(Level.SEVERE)) {
					LogEntry entry = ValidationPlugin.getLogEntry();
					entry.setTargetException(exc);
					logger.write(Level.SEVERE, entry);
				}
			}
			finally {
				postRun(monitor);
			}
		}
		finally {
			if(!imported) {
				String projectName = (project == null) ? file.getName() : project.getName();
				LogEntry entry = ValidationPlugin.getLogEntry();
				entry.setExecutionMap(executionMap);
				entry.setText("AImportOperation for " + projectName + " was unsuccessful."); //$NON-NLS-1$ //$NON-NLS-2$
				logger.write(Level.SEVERE, entry);
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
