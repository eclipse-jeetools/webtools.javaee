/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.application.internal.operations;

import java.io.File;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jst.j2ee.datamodel.properties.IJavaUtilityJarImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.earcreation.EARCreationResourceHandler;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.osgi.util.NLS;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.IProjectCreationPropertiesNew;


public class CreateProjectWithExtractedJarOperation extends J2EEUtilityJarImportAssistantOperation {
	private String newProjectName;
	private String projectRoot;

	public CreateProjectWithExtractedJarOperation(File utilityJar, String overridingProjectRoot) {
		super(NLS.bind(EARCreationResourceHandler.CreateProjectWithExtractedJarOperation_Create_project_with_extracted_conte_, utilityJar.getName()), utilityJar);
		newProjectName = getUtilityJarProjectName(utilityJar);
		projectRoot = findUniqueLocation(overridingProjectRoot, newProjectName); 
	}

	@Override
	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		MultiStatus status = new MultiStatus(J2EEPlugin.PLUGIN_ID, 0, NLS.bind(EARCreationResourceHandler.CreateProjectWithExtractedJarOperation_Create_project_with_extracted_conte_, getUtilityJar().getName()), null);
		try {

			IDataModel importModel = DataModelFactory.createDataModel(new J2EEUtilityJarImportDataModelProvider()); 

			importModel.setProperty(IJavaUtilityJarImportDataModelProperties.FILE_NAME, getUtilityJar().getAbsolutePath());

			if (projectRoot != null && projectRoot.length() > 0) {				
				importModel.setBooleanProperty(IProjectCreationPropertiesNew.USE_DEFAULT_LOCATION, false);
				importModel.setProperty(IProjectCreationPropertiesNew.USER_DEFINED_LOCATION, projectRoot);
			}

			if (isOverwriteIfNecessary()) {
				IProject existingProject = getWorkspaceRoot().getProject(newProjectName);
				if (existingProject.exists()) {
					existingProject.delete(true, true, monitor);
				}
			}
			
			importModel.setProperty(IJavaUtilityJarImportDataModelProperties.PROJECT_NAME, newProjectName);
			importModel.setProperty(IJavaUtilityJarImportDataModelProperties.EAR_PROJECT_NAME, getAssociatedEARProjectName());

			status.add(importModel.getDefaultOperation().execute(new SubProgressMonitor(monitor, 1), info)); 
		} catch (CoreException e) {
			status.add(J2EEPlugin.createErrorStatus(0, e.getMessage(), e));
			J2EEPlugin.logError(0, e.getMessage(), e);
		} 
		return status;
	}

	@Override
	public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		return Status.CANCEL_STATUS;
	}

	@Override
	public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		return Status.CANCEL_STATUS;
	}

	public void setNewProjectName(String newProjectName) {
		this.newProjectName = newProjectName;
	}

}
