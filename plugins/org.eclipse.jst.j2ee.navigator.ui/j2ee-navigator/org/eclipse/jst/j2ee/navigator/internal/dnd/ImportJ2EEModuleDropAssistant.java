/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.navigator.internal.dnd;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jst.j2ee.application.internal.operations.EARComponentImportDataModelProvider;
import org.eclipse.jst.j2ee.applicationclient.internal.creation.AppClientComponentImportDataModelProvider;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBComponentImportDataModelProvider;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorComponentImportDataModelProvider;
import org.eclipse.jst.j2ee.internal.navigator.ui.Messages;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentImportDataModelProvider;
import org.eclipse.jst.j2ee.navigator.internal.plugin.J2EENavigatorPlugin;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.CopyFilesAndFoldersOperation;
import org.eclipse.ui.navigator.CommonDropAdapter;
import org.eclipse.ui.progress.IProgressService;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class ImportJ2EEModuleDropAssistant extends AddProjectToEARDropAssistant {
	
	@Override
	public boolean isSupportedType(TransferData aTransferType) { 
		return FileTransfer.getInstance().isSupportedType(aTransferType);
	}
	
	@Override
	public IStatus handleDrop(CommonDropAdapter aDropAdapter, final DropTargetEvent aDropTargetEvent, final Object aTarget) {		
		
		if(FileTransfer.getInstance().isSupportedType(aDropAdapter.getCurrentTransfer())) {
			
			final Shell shell = getShell();
		
			IProgressService service = PlatformUI.getWorkbench().getProgressService();	 
			Job importArtifactsJob = new Job(Messages.ImportJ2EEModuleDropAssistant_Importing_Java_Enterprise_Edition_artifacts) {
				@Override
				protected IStatus run(IProgressMonitor monitor) {					
					
					IProject targetEARProject = getProject(aTarget);
					if(targetEARProject != null) { 
						/* If this isn't an ear project, we can't add the newly created modules to it. */
						if(!hasEarFacet(targetEARProject))
							targetEARProject = null;
					} /* otherwise we assume the IWorkspaceRoot and just don't add the new modules an an existing ear */

					String[] names = (String[]) aDropTargetEvent.data;
					
					monitor.beginTask(Messages.ImportJ2EEModuleDropAssistant_Importing_Java_Enterprise_Edition_artifacts, names.length);
					final MultiStatus status = new MultiStatus(J2EENavigatorPlugin.PLUGIN_ID, 0, Messages.ImportJ2EEModuleDropAssistant_Importing_Java_Enterprise_Edition_artifacts, null);
					
					SubProgressMonitor submonitor = new SubProgressMonitor(monitor, 10);
					IDataModel importDataModel = null;
					boolean performSimpleJarCopy = false;
					List simpleJarsToCopyList = null;
					IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
					List createdComponents = new ArrayList();
					for(int i=0; i<names.length && !monitor.isCanceled(); i++) {
						try {
							importDataModel = null;
							performSimpleJarCopy = false;
							int separatorIndex = names[i].lastIndexOf(File.separatorChar);
							int dotIndex = names[i].lastIndexOf('.');
							if(separatorIndex > 0 && separatorIndex+1 < dotIndex) {
								String filename = names[i].substring(separatorIndex+1, dotIndex);
								if(root.getProject(filename).exists()) {
									filename = calculateValidProjectName(filename);
								}
								String extension = names[i].substring(dotIndex);
								if(IJ2EEModuleConstants.WAR_EXT.equals(extension)) {
									importDataModel = DataModelFactory.createDataModel(new WebComponentImportDataModelProvider());
								} else if(IJ2EEModuleConstants.RAR_EXT.equals(extension)) {
									importDataModel = DataModelFactory.createDataModel(new ConnectorComponentImportDataModelProvider());
								} else if(IJ2EEModuleConstants.EAR_EXT.equals(extension)) {
									importDataModel = DataModelFactory.createDataModel(new EARComponentImportDataModelProvider());
								} else if(IJ2EEModuleConstants.JAR_EXT.equals(extension)) {  									
									Archive archive = null;
									try {
										archive = CommonarchiveFactory.eINSTANCE.openArchive(names[i]);
										if(archive.isApplicationClientFile())
											importDataModel = DataModelFactory.createDataModel(new AppClientComponentImportDataModelProvider());
										else if(archive.isEJBJarFile())
											importDataModel = DataModelFactory.createDataModel(new EJBComponentImportDataModelProvider());
										else {
											performSimpleJarCopy = true; //handle Utility jars as regular jars.
											if(simpleJarsToCopyList == null){
												simpleJarsToCopyList = new ArrayList();
												simpleJarsToCopyList.add(names[i]);
											}
										}
										
									} finally {
										if(archive != null)
											archive.close();
									}
								} 
								
								if(importDataModel != null) {
									importDataModel.setStringProperty(IJ2EEComponentImportDataModelProperties.FILE_NAME, names[i]);
									importDataModel.setStringProperty(IJ2EEComponentImportDataModelProperties.PROJECT_NAME, filename);
									importDataModel.getDefaultOperation().execute(submonitor, null);
									
									createdComponents.add(importDataModel.getProperty(IJ2EEComponentImportDataModelProperties.COMPONENT));
									
								} else if(!performSimpleJarCopy){
									status.add(J2EENavigatorPlugin.createErrorStatus(0, NLS.bind(Messages.ImportJ2EEModuleDropAssistant_Could_not_recognize_extension_0_, extension), null));
								}
								
							} 
						} catch (Throwable e) {
							String msg = e.getMessage() != null ? e.getMessage() : e.toString();
							status.add(J2EENavigatorPlugin.createErrorStatus(0, msg, e));
						} 						
					}
					
					if(targetEARProject != null) {
						List createdModuleProjects = new ArrayList();
						for(int i=0; i<createdComponents.size(); i++) {
							IVirtualComponent component = (IVirtualComponent) createdComponents.get(i);							
							/* If this isn't an ear project, we can't add the newly created modules to it. */
							if(!hasEarFacet(component.getProject())) {
								createdModuleProjects.add(component.getProject());
							}
						}
						IDataModel dataModel = getAddModuleDataModel(targetEARProject, createdModuleProjects);
						IUndoableOperation dropOperation = dataModel.getDefaultOperation();
						IStatus addProjectsStatus = null;
						try {
							addProjectsStatus = dropOperation.execute(monitor, null);
							if(addProjectsStatus != null)
								status.add(addProjectsStatus);
						} catch (ExecutionException e) { 
							String msg = e.getMessage() != null ? e.getMessage() : e.toString();
							status.add(J2EENavigatorPlugin.createErrorStatus(0, msg, null));
						}
						//copy the simpleJarsOver
						if(simpleJarsToCopyList != null ){
							try{
								final String [] jarsToCopyArray = new String[simpleJarsToCopyList.size()];
								simpleJarsToCopyList.toArray(jarsToCopyArray);
								
								CopyFilesAndFoldersOperation operation = new CopyFilesAndFoldersOperation(shell);
								operation.copyFilesInCurrentThread(jarsToCopyArray, targetEARProject, monitor);
							}catch (Throwable e) {
								String msg = e.getMessage() != null ? e.getMessage() : e.toString();
								status.add(J2EENavigatorPlugin.createErrorStatus(0, msg, e));
							} 	
						}	
					}
					
					return status;
				}

			};
			
			service.showInDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), importArtifactsJob);
			importArtifactsJob.setRule(ResourcesPlugin.getWorkspace().getRoot());
			importArtifactsJob.schedule();
			return Status.OK_STATUS;
		}
		return Status.CANCEL_STATUS;
	}
	

	@Override
	public IStatus validateDrop(Object target, int operation, TransferData transferType) {
		IStatus status = Status.CANCEL_STATUS;
		if(FileTransfer.getInstance().isSupportedType(transferType)) {
			IProject project = null;
			if(target instanceof IWorkspaceRoot)		
				status = Status.OK_STATUS;
			else if( (project = getProject(target)) != null && hasEarFacet(project))
				status = Status.OK_STATUS;
		}
		return status;
	}

}
