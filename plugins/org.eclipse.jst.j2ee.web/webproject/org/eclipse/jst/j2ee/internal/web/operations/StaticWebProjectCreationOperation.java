/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.web.operations;



import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResourceStatus;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.wst.common.frameworks.internal.enablement.nonui.WFTWrappedException;
import org.eclipse.wst.common.frameworks.internal.operations.IHeadlessRunnableWithProgress;

public class StaticWebProjectCreationOperation implements IHeadlessRunnableWithProgress {
	protected WebProjectInfo fProjectInfo;

	public StaticWebProjectCreationOperation(WebProjectInfo info) {
		fProjectInfo = info;
	}

	protected void completeExecute(IProgressMonitor monitor) throws CoreException {

	}


	/**
	 * create a sample cascading style sheet for the user
	 */
	protected boolean createMasterCSS(IBaseWebNature runtime) throws CoreException {
		boolean retVal = true;

		IPath fileName = runtime.getCSSFolder().getProjectRelativePath().append(IWebNatureConstants.DEFAULT_CSS_FILE_NAME);

		//Customer defect 192523; don't create the file if it already exists
		if (fileExists(fileName, runtime))
			return false;
		String contents = (new MasterCSS()).generate(null);
		runtime.createFile(fileName, contents);

		return retVal;
	}

	public static IProject createWebProject(String aProjectName) {
		// Set up WebProjectInfo
		IContainer container = J2EEPlugin.getWorkspace().getRoot().getProject(aProjectName);
		if (container.exists())
			return (IProject) container;
		WebProjectInfo projectInfo = new WebProjectInfo();
		projectInfo.setProjectName(aProjectName);

		return createWebProject(projectInfo, false, true);
	}


	public static IProject createWebProject(WebProjectInfo projectInfo, boolean isBinaryProject, boolean createDefaultFiles) {
		// Set up WebProjectInfo
		IContainer container = J2EEPlugin.getWorkspace().getRoot().getProject(projectInfo.getProjectName());
		if (container.exists())
			return (IProject) container;

		StaticWebProjectCreationOperation op = new StaticWebProjectCreationOperation(projectInfo);
		//	op.setIsBinaryProject(isBinaryProject);
		//	op.setCreateDefaultFiles(createDefaultFiles);
		try {
			op.run(new NullProgressMonitor());
		} catch (java.lang.reflect.InvocationTargetException e) {
			com.ibm.wtp.common.logger.proxy.Logger.getLogger().logError(e);
		} catch (InterruptedException ex) {
			com.ibm.wtp.common.logger.proxy.Logger.getLogger().logError(ex);
		}

		return projectInfo.getProject();
	}

	/**
	 * create the files that are specific for a web project. ie. a simple web.xml and simple style
	 * sheet
	 */
	protected void createWebProjectFiles(IBaseWebNature runtime) {

		try {
			WebProjectInfo projectInfo = fProjectInfo;

			runtime.setFeatureIds(projectInfo.getFeatureIds());

			// static web project have a "ResourceSet root" which is used to publish
			// and assemble urls to web resources.
			runtime.setContextRoot(projectInfo.getContextRoot());

		} catch (CoreException e) {
			com.ibm.wtp.common.logger.proxy.Logger.getLogger().logError(e);
		}
	}

	protected boolean fileExists(IPath path, IBaseWebNature runtime) {
		IFile existing = runtime.getProject().getFile(path);
		return existing.exists();
	}

	protected boolean fileExists(IPath path) {
		IFile existing = J2EEPlugin.getWorkspace().getRoot().getFile(path);
		return existing.exists();
	}

	/**
	 * Return the message to display in the progress monitor
	 */
	protected java.lang.String getCreationMessage() {
		return ProjectSupportResourceHandler.getString("Creating_Web_Project..._UI_"); //$NON-NLS-1$ = "Creating Web Project..."
	}

	/**
	 * Gets the new project and updates it from the template.
	 */
	protected void updateProjectFromInfo() throws CoreException {

		IBaseWebNature runtime = WebNatureRuntimeUtilities.createRuntime(fProjectInfo);
		// create the default files for the project
		createWebProjectFiles(runtime);
	}

	/**
	 * Initiates a batch of changes, by invoking the execute() method as a workspace runnable.
	 * 
	 * @param monitor
	 *            the progress monitor to use to display progress
	 * @exception InvocationTargetException
	 *                wraps any CoreException, runtime exception or error thrown by the execute()
	 *                method
	 * @see WorkspaceModifyOperation - this class was directly copied from it
	 */
	public synchronized final void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		final InvocationTargetException[] iteHolder = new InvocationTargetException[1];
		try {
			IWorkspaceRunnable workspaceRunnable = new IWorkspaceRunnable() {
				public void run(IProgressMonitor pm) throws CoreException {
					try {
						execute(pm);
					} catch (InvocationTargetException e) {
						// Pass it outside the workspace runnable
						iteHolder[0] = e;
					} catch (InterruptedException e) {
						// Re-throw as OperationCanceledException, which will be
						// caught and re-thrown as InterruptedException below.
						throw new OperationCanceledException(e.getMessage());
					}
					// CoreException and OperationCanceledException are propagated
				}
			};
			J2EEPlugin.getWorkspace().run(workspaceRunnable, monitor);
		} catch (CoreException e) {
			if (e.getStatus().getCode() == IResourceStatus.OPERATION_FAILED)
				throw new WFTWrappedException(e.getStatus().getException(), e.getMessage());
			throw new WFTWrappedException(e);
		} catch (OperationCanceledException e) {
			throw new InterruptedException(e.getMessage());
		}
		// Re-throw the InvocationTargetException, if any occurred
		if (iteHolder[0] != null) {
			throw new WFTWrappedException(iteHolder[0].getTargetException(), iteHolder[0].getMessage());
		}
	}

	/**
	 * execute method comment.
	 */
	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {

		monitor.beginTask(getCreationMessage(), 2000);

		// get or create the project
		getNewProject(monitor);
		updateProjectFromInfo();

		try {
			completeExecute(monitor);

		} finally {
			monitor.done();
		}
	}

	/**
	 * Creates the project if one does not yet exist
	 */
	protected IProject getNewProject(IProgressMonitor monitor) throws CoreException {

		// create the new project and cache it if successful
		IPath newProjectPath = fProjectInfo.getProjectPath();
		final IProject newProjectHandle = fProjectInfo.createProjectHandle(newProjectPath);

		if (newProjectHandle.exists()) {
			fProjectInfo.setProject(newProjectHandle);
			return newProjectHandle;
		}
		createProject(newProjectHandle, new SubProgressMonitor(monitor, 1000));

		fProjectInfo.setProject(newProjectHandle);
		return newProjectHandle;
	}

	/**
	 * Creates a concrete project resource from a project handle. Returns a <code>boolean</code>
	 * indicating success.
	 * 
	 * @param projectHandle
	 *            the project handle to create a project resource with
	 * @param monitor
	 *            the progress monitor to show visual progress with
	 * @exception com.ibm.itp.core.api.resources.CoreException
	 */
	protected void createProject(IProject projectHandle, IProgressMonitor monitor) throws CoreException {

		if (monitor.isCanceled())
			throw new OperationCanceledException();

		try {
			monitor.beginTask(ProjectSupportResourceHandler.getString("Creating__UI_"), 10); //$NON-NLS-1$ = "Creating:"

			// create the project
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			if (!projectHandle.exists()) {
				IProjectDescription desc = workspace.newProjectDescription(projectHandle.getName());
				IPath locationPath = fProjectInfo.getProjectLocation();
				if (locationPath != null && Platform.getLocation().equals(locationPath)) {
					locationPath = null;
				}
				desc.setLocation(locationPath);
				projectHandle.create(desc, new SubProgressMonitor(monitor, 1));
			}
			if (!projectHandle.isOpen()) {
				projectHandle.open(new SubProgressMonitor(monitor, 1));
			}

			if (monitor.isCanceled())
				throw new OperationCanceledException();

		} finally {
			monitor.done();
		}

		if (monitor.isCanceled())
			throw new OperationCanceledException();
	}


}