/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.archive.operations;



import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jem.util.emf.workbench.JavaProjectUtilities;
import org.eclipse.jst.j2ee.application.operations.J2EEArtifactExportDataModel;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;
import org.eclipse.jst.j2ee.internal.plugin.LibCopyBuilder;
import org.eclipse.wst.common.frameworks.internal.enablement.nonui.WFTWrappedException;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;

public abstract class J2EEArtifactExportOperation extends WTPOperation {
	protected IProgressMonitor progressMonitor;
	protected IProject project;
	protected IPath destinationPath;
	protected ModuleFile moduleFile;
	protected boolean exportSource = false;
	protected boolean buildIncremental = true;

	/**
	 * Assumptions: aProject is a valid project with an J2EENatureRuntime, and the destination path
	 * is the fully qualified path where a new jar will be created, and the client code has already
	 * confirmed overwrite if the file already exists
	 */
	public J2EEArtifactExportOperation(J2EEArtifactExportDataModel model) {
		super(model);
		setProject(ResourcesPlugin.getWorkspace().getRoot().getProject(model.getStringProperty(J2EEArtifactExportDataModel.PROJECT_NAME)));
		setDestinationPath(new Path(model.getStringProperty(J2EEArtifactExportDataModel.ARCHIVE_DESTINATION)));
		setExportSource(model.getBooleanProperty(J2EEArtifactExportDataModel.EXPORT_SOURCE_FILES));
	}

	protected abstract String archiveString();

	public abstract void createModuleFile() throws SaveFailureException;

	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		try {
			setProgressMonitor(monitor);
			//defect 240999
			project.refreshLocal(IResource.DEPTH_INFINITE, null);
			if (buildIncremental && operationDataModel.getBooleanProperty(J2EEArtifactExportDataModel.RUN_BUILD)) {
				javac(project, monitor);
			}
			export();
		} catch (SaveFailureException e) {
			throw new WFTWrappedException(e, EJBArchiveOpsResourceHandler.getString("Error_exporting__UI_") + archiveString()); //$NON-NLS-1$ = "Error exporting "
		} finally {
			monitor.done();
		}
	}

	public abstract void export() throws SaveFailureException, CoreException, InvocationTargetException, InterruptedException;

	/**
	 * Insert the method's description here. Creation date: (11/08/00 5:23:49 PM)
	 * 
	 * @return com.ibm.itp.common.IPath
	 */
	public IPath getDestinationPath() {
		return destinationPath;
	}

	protected IJavaProject getJavaProject() {
		return JavaProjectUtilities.getJavaProject(getProject());
	}

	protected ModuleFile getModuleFile() {
		return moduleFile;
	}

	/**
	 * Insert the method's description here. Creation date: (10/13/00 2:12:50 PM)
	 * 
	 * @return com.ibm.itp.common.IProgressMonitor
	 */
	protected IProgressMonitor getProgressMonitor() {
		return progressMonitor;
	}

	/**
	 * Insert the method's description here. Creation date: (11/08/00 5:20:53 PM)
	 * 
	 * @return com.ibm.itp.core.api.resources.IProject
	 */
	protected IProject getProject() {
		return project;
	}

	/**
	 * Insert the method's description here. Creation date: (4/19/2001 6:02:06 PM)
	 * 
	 * @return boolean
	 */
	protected boolean isExportSource() {
		return exportSource;
	}

	/**
	 * Insert the method's description here. Creation date: (11/08/00 5:23:49 PM)
	 * 
	 * @param newDestinationPath
	 *            com.ibm.itp.common.IPath
	 */
	protected void setDestinationPath(IPath newDestinationPath) {
		destinationPath = newDestinationPath;
	}

	/**
	 * Insert the method's description here. Creation date: (4/19/2001 6:02:06 PM)
	 * 
	 * @param newExportSource
	 *            boolean
	 */
	protected void setExportSource(boolean newExportSource) {
		exportSource = newExportSource;
	}

	protected void setModuleFile(ModuleFile newModuleFile) {
		moduleFile = newModuleFile;
	}

	/**
	 * Insert the method's description here. Creation date: (10/13/00 2:12:50 PM)
	 * 
	 * @param newProgressMonitor
	 *            com.ibm.itp.common.IProgressMonitor
	 */
	protected void setProgressMonitor(IProgressMonitor newProgressMonitor) {
		progressMonitor = newProgressMonitor;
	}

	/**
	 * Insert the method's description here. Creation date: (11/08/00 5:20:53 PM)
	 * 
	 * @param newProject
	 *            com.ibm.itp.core.api.resources.IProject
	 */
	protected void setProject(IProject newProject) {
		project = newProject;
	}

	/**
	 * Indicates whether an incremental build should be invoked before export
	 * 
	 * @return Returns a boolean
	 */
	public boolean shouldBuildIncremental() {
		return buildIncremental;
	}

	/**
	 * Indicates whether an incremental build should be invoked before export
	 * 
	 * @param buildIncremental
	 *            The buildIncremental to set
	 */
	protected void setBuildIncremental(boolean buildIncremental) {
		this.buildIncremental = buildIncremental;
	}

	/**
	 * Find the specific Java command amongst the build spec of a given description
	 */
	private static ICommand getJavaCommand(IProjectDescription description) throws CoreException {
		if (description == null) {
			return null;
		}

		ICommand[] commands = description.getBuildSpec();
		for (int i = 0; i < commands.length; ++i) {
			if (commands[i].getBuilderName().equals(JavaCore.BUILDER_ID)) {
				return commands[i];
			}
		}
		return null;
	}

	private static ICommand getLibCopyBuilder(IProjectDescription description) throws CoreException {
		if (description == null) {
			return null;
		}

		ICommand[] commands = description.getBuildSpec();
		for (int i = 0; i < commands.length; ++i) {
			if (commands[i].getBuilderName().equals(LibCopyBuilder.BUILDER_ID)) {
				return commands[i];
			}
		}
		return null;

	}

	public static void javac(IProject project, IProgressMonitor monitor) throws CoreException {
		IProjectDescription description = project.getDescription();
		ICommand javaBuilder = getJavaCommand(description);
		if (javaBuilder != null) {
			project.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, JavaCore.BUILDER_ID, javaBuilder.getArguments(), monitor);
		}
		ICommand libCopyBuilder = getLibCopyBuilder(description);
		if (null != libCopyBuilder) {
			project.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, LibCopyBuilder.BUILDER_ID, libCopyBuilder.getArguments(), monitor);
		}
	}

}