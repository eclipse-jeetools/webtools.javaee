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

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.j2ee.internal.earcreation.EARCreationResourceHandler;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetProjectCreationDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.UtilityProjectCreationDataModelProvider;
import org.eclipse.osgi.util.NLS;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class CreateProjectWithLinkedJarOperation extends J2EEUtilityJarImportAssistantOperation {

	private String linkedPathVariable;

	public CreateProjectWithLinkedJarOperation(File utilityJar, String linkedPathVariable) {
		super(NLS.bind(EARCreationResourceHandler.CreateProjectWithLinkedJarOperation_Creating_project_with_linked_archiv_, utilityJar.getName()), utilityJar);
		this.linkedPathVariable= linkedPathVariable;
	}

	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		MultiStatus status = new MultiStatus(J2EEPlugin.PLUGIN_ID, 0, NLS.bind(EARCreationResourceHandler.CreateProjectWithLinkedJarOperation_Creating_project_with_linked_archiv_, getUtilityJar().getName()), null);
 
		try {
			IProject project = getWorkspaceRoot().getProject(getUtilityJarProjectName(getUtilityJar()));
			
			IDataModel createUtilityProject = DataModelFactory.createDataModel(new UtilityProjectCreationDataModelProvider());
			createUtilityProject.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, getUtilityJarProjectName(getUtilityJar()));
 			
			createUtilityProject.setProperty(IJ2EEFacetProjectCreationDataModelProperties.EAR_PROJECT_NAME, getAssociatedEARProjectName());
			
			status.add(createUtilityProject.getDefaultOperation().execute(monitor, info));
			
			IProject utilityJarProject = getWorkspaceRoot().getProject(getUtilityJarProjectName(getUtilityJar()));
			
			/* Remove the default src folder */
			IJavaProject utilityJarJavaProject = JavaCore.create(utilityJarProject);
			IPackageFragmentRoot srcFolder = utilityJarJavaProject.getPackageFragmentRoot(utilityJarProject.getFolder("src"));
			if(srcFolder.exists()) {
				srcFolder.delete(IResource.FORCE, IPackageFragmentRoot.ORIGINATING_PROJECT_CLASSPATH, monitor);
			}			
			status.add(createLinkedArchive(project, getUtilityJar().getName(), getUtilityJar(), linkedPathVariable, monitor));
			
			/* Remove the /src mapping */
			IVirtualComponent utilComponent = ComponentCore.createComponent(project);
			status.add(removeRootMapping(utilComponent, "/src", monitor));
			
			/* Create the /<archive> mapping */
			status.add(createVirtualArchiveComponent(project, getUtilityJar().getName(), project.getFile(getUtilityJar().getName()), monitor));
			 
			/* Make this project a utility jar project for the EAR */
			IProject associatedEARProject = getWorkspaceRoot().getProject(getAssociatedEARProjectName());			
			status.add(linkArchiveToEAR(associatedEARProject, getUtilityJar().getName(), utilityJarProject, new SubProgressMonitor(monitor, 1)));

		} catch (Exception e) {
			status.add(J2EEPlugin.createErrorStatus(0, EARCreationResourceHandler.J2EEUtilityJarListImportOperation_UI_2, e));
		}		 
		return status; 
	}

	public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		return Status.CANCEL_STATUS;
	}

	public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		return Status.CANCEL_STATUS;
	}
	

}
