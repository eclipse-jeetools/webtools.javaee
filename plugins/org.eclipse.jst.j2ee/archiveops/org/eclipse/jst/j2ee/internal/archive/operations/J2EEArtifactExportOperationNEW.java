/*******************************************************************************
 * Copyright (c) 2003, 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentExportDataModelProperties;
import org.eclipse.jst.j2ee.internal.plugin.LibCopyBuilder;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public abstract class J2EEArtifactExportOperationNEW extends AbstractDataModelOperation {

	protected IProgressMonitor progressMonitor;
	private IVirtualComponent component;
	private IPath destinationPath;
	private ModuleFile moduleFile;
	private boolean exportSource = false;

	public J2EEArtifactExportOperationNEW() {
		super();
	}

	public J2EEArtifactExportOperationNEW(IDataModel model) {
		super(model);
	}

	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		setComponent((IVirtualComponent)model.getProperty(IJ2EEComponentExportDataModelProperties.COMPONENT_NAME));
		setDestinationPath(new Path(model.getStringProperty(IJ2EEComponentExportDataModelProperties.ARCHIVE_DESTINATION)));
		setExportSource(model.getBooleanProperty(IJ2EEComponentExportDataModelProperties.EXPORT_SOURCE_FILES));
		try {
			setProgressMonitor(monitor);
			// defect 240999
			component.getProject().refreshLocal(IResource.DEPTH_INFINITE, null);
			if (model.getBooleanProperty(IJ2EEComponentExportDataModelProperties.RUN_BUILD)) {
				javac(component.getProject(), monitor);
			}
			export();
		} catch (Exception e) {
			throw new ExecutionException(EJBArchiveOpsResourceHandler.getString("Error_exporting__UI_") + archiveString(), e);
		}
		return OK_STATUS;
	}

	protected abstract void export() throws SaveFailureException, CoreException, InvocationTargetException, InterruptedException;

	protected abstract String archiveString();

	protected void setProgressMonitor(IProgressMonitor newProgressMonitor) {
		progressMonitor = newProgressMonitor;
	}

	protected IProgressMonitor getProgressMonitor() {
		return progressMonitor;
	}

	private void setComponent(IVirtualComponent newComponent) {
		component = newComponent;
	}

	protected IVirtualComponent getComponent() {
		return component;
	}

	protected IPath getDestinationPath() {
		return destinationPath;
	}

	protected void setDestinationPath(IPath newDestinationPath) {
		destinationPath = newDestinationPath;
	}

	protected boolean isExportSource() {
		return exportSource;
	}

	protected void setExportSource(boolean newExportSource) {
		exportSource = newExportSource;
	}

	protected ModuleFile getModuleFile() {
		return moduleFile;
	}

	protected void setModuleFile(ModuleFile newModuleFile) {
		moduleFile = newModuleFile;
	}

	public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		// TODO Auto-generated method stub
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

}
