/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.application.internal.operations;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.datamodel.properties.IJavaUtilityJarImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.earcreation.EARCreationResourceHandler;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.osgi.util.NLS;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class CreateProjectWithExtractedJarOperation extends J2EEUtilityJarImportAssistantOperation {

//	private boolean createBinaryProject = false; 
	private String newProjectName;


	public CreateProjectWithExtractedJarOperation(File utilityJar) {
		super(NLS.bind(EARCreationResourceHandler.CreateProjectWithExtractedJarOperation_Create_project_with_extracted_conte_, utilityJar.getName()), utilityJar);
		newProjectName = getUtilityJarProjectName(utilityJar);
	}

	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		MultiStatus status = new MultiStatus(J2EEPlugin.PLUGIN_ID, 0, NLS.bind(EARCreationResourceHandler.CreateProjectWithExtractedJarOperation_Create_project_with_extracted_conte_, getUtilityJar().getName()), null);
		Archive archive = null;
		try {
			archive = CommonarchiveFactory.eINSTANCE.primOpenArchive(getUtilityJar().getAbsolutePath());

			IDataModel importModel = DataModelFactory.createDataModel(new J2EEUtilityJarImportDataModelProvider());
			// importModel.setBooleanProperty(IJavaUtilityJarImportDataModelProperties.PRESERVE_PROJECT_METADATA,
			// createBinaryProject);

			importModel.setProperty(IJavaUtilityJarImportDataModelProperties.FILE, archive);
//			importModel.setProperty(IJavaUtilityJarImportDataModelProperties.FILE_NAME, getUtilityJar().getAbsolutePath());

			// if (overrideProjectRoot && projectRoot != null && projectRoot.length() > 0)
			// importModel.getJ2eeProjectCreationDataModel().setProperty(IJavaUtilityProjectCreationDataModelProperties.PROJECT_LOCATION,
			// projectRoot);

			// importModel.getJ2eeProjectCreationDataModel().setBooleanProperty(J2EEProjectCreationDataModel.ADD_SERVER_TARGET, true);
			if (isOverwriteIfNecessary()) {
				// importModel.setBooleanProperty(IJavaUtilityJarImportDataModelProperties.OVERWRITE_HANDLER);
				IProject existingProject = getWorkspaceRoot().getProject(newProjectName);
				if (existingProject.exists()) {
					existingProject.delete(true, true, monitor);
				}
			}
			
			importModel.setProperty(IJavaUtilityJarImportDataModelProperties.PROJECT_NAME, newProjectName);
			importModel.setProperty(IJavaUtilityJarImportDataModelProperties.EAR_PROJECT_NAME, getAssociatedEARProjectName());

			status.add(importModel.getDefaultOperation().execute(new SubProgressMonitor(monitor, 1), info)); 
			
			IProject associatedEARProject = getWorkspaceRoot().getProject(getAssociatedEARProjectName());
 			IProject utilityJarProject = getWorkspaceRoot().getProject(newProjectName);
			linkArchiveToEAR(associatedEARProject, getUtilityJar().getName(), utilityJarProject, new SubProgressMonitor(monitor, 1));
			
		} catch (OpenFailureException e) {
			status.add(J2EEPlugin.createErrorStatus(0, e.getMessage(), e));
			J2EEPlugin.logError(0, e.getMessage(), e);
		} catch (InvocationTargetException e) {
			status.add(J2EEPlugin.createErrorStatus(0, e.getMessage(), e));
			J2EEPlugin.logError(0, e.getMessage(), e);
		} catch (InterruptedException e) {
			status.add(J2EEPlugin.createErrorStatus(0, e.getMessage(), e));
			J2EEPlugin.logError(0, e.getMessage(), e);
		} catch (CoreException e) {
			status.add(J2EEPlugin.createErrorStatus(0, e.getMessage(), e));
			J2EEPlugin.logError(0, e.getMessage(), e);
		} finally {
			if(archive != null)
				archive.close();
		}
		return status;
	}

	public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		return Status.CANCEL_STATUS;
	}

	public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		return Status.CANCEL_STATUS;
	}

	public void setNewProjectName(String newProjectName) {
		this.newProjectName = newProjectName;
	}



}
