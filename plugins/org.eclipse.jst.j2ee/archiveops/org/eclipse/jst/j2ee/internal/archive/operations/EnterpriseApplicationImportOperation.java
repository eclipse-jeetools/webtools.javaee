/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.archive.operations;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.application.operations.AddArchiveToEARDataModel;
import org.eclipse.jst.j2ee.application.operations.AddModuleToEARDataModel;
import org.eclipse.jst.j2ee.application.operations.AddUtilityProjectToEARDataModel;
import org.eclipse.jst.j2ee.application.operations.AddWebModuleToEARDataModel;
import org.eclipse.jst.j2ee.application.operations.EnterpriseApplicationImportDataModel;
import org.eclipse.jst.j2ee.application.operations.EnterpriseApplicationCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.EnterpriseApplicationCreationOperation;
import org.eclipse.jst.j2ee.application.operations.J2EEArtifactImportDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleImportDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEArtifactCreationDataModelOld;
import org.eclipse.jst.j2ee.application.operations.J2EEUtilityJarImportDataModel;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.SaveStrategy;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.internal.earcreation.EAREditModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.UtilityJARMapping;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetDataModel;
import org.eclipse.wst.common.frameworks.internal.enablement.nonui.WFTWrappedException;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;

import com.ibm.wtp.common.logger.proxy.Logger;

public class EnterpriseApplicationImportOperation extends J2EEArtifactImportOperation {
	public EnterpriseApplicationImportOperation(EnterpriseApplicationImportDataModel dataModel) {
		super(dataModel);
	}

	private EnterpriseApplicationImportDataModel getEARImportDataModel() {
		return (EnterpriseApplicationImportDataModel) operationDataModel;
	}

	/**
	 * Subclasses must override to performs the workbench modification steps that are to be
	 * contained within a single logical workbench change.
	 * 
	 * @param monitor
	 *            the progress monitor to use to display progress
	 */
	protected void doExecute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		//FileSet.printState();
		EnterpriseApplicationImportDataModel model = getEARImportDataModel();
		monitor.beginTask(null, model.getModuleFile().getFiles().size());
		boolean earImported = model.getBooleanProperty(EnterpriseApplicationImportDataModel.IMPORT_EAR_PROJECT);
		if (earImported) {
			IProject project = model.getProject();
			if (model.getBooleanProperty(J2EEArtifactImportDataModel.OVERWRITE_PROJECT) && model.getBooleanProperty(J2EEArtifactImportDataModel.DELETE_BEFORE_OVERWRITE_PROJECT)) {
				if (project.exists()) {
					project.delete(true, true, new NullProgressMonitor());
				}
			}
			if (!project.exists()) {
				createModuleProject(model.getJ2eeArtifactCreationDataModel(), monitor);
			}
			try {
				monitor.beginTask(EARArchiveOpsResourceHandler.getString("IMPORTING_EAR_FILE_UI_"), model.getEARFile().getFiles().size()); //$NON-NLS-1$
				importEARProject(monitor);
			} finally {
				releaseDeploymentDescriptor();
			}
		}
		List modelsNotToAddToEAR = model.getUnhandledProjectModels();
		List modelsToImport = model.getHandledSelectedModels();
		try {
			J2EEArtifactImportDataModel importModel = null;
			WTPOperation importOp = null;
			List allModels = model.getProjectModels();
			IProject earProject = model.getProject();
			IProject archiveProject = null;
			AddArchiveToEARDataModel addArchiveProjectToEARDataModel = null;
			boolean synchServerTarget = model.getBooleanProperty(EnterpriseApplicationImportDataModel.SYNC_SERVER_TARGETS_WITH_EAR);
			boolean moduleImported = false;
			for (int i = 0; i < allModels.size(); i++) {
				moduleImported = false;
				importModel = (J2EEArtifactImportDataModel) allModels.get(i);
				if (modelsToImport.contains(importModel)) {
					moduleImported = true;
					importOp = importModel.getDefaultOperation();
					importModel.setProperty(J2EEArtifactImportDataModel.CLOSE_ARCHIVE_ON_DISPOSE, Boolean.FALSE);
					importOp.run(monitor);
				}
				if (earProject.exists() && (earImported || moduleImported) && !modelsNotToAddToEAR.contains(importModel)) {
					archiveProject = importModel.getProject();
					if (archiveProject.exists()) {
						if (importModel instanceof J2EEModuleImportDataModel) {
							addArchiveProjectToEARDataModel = AddModuleToEARDataModel.createAddToEARDataModel(earProject.getName(), archiveProject).getAppropriateDataModel();
							addArchiveProjectToEARDataModel.setProperty(AddModuleToEARDataModel.ARCHIVE_URI, importModel.getStringProperty(J2EEArtifactImportDataModel.URI_FOR_MODULE_MAPPING));
							addArchiveProjectToEARDataModel.setBooleanProperty(AddArchiveToEARDataModel.SYNC_TARGET_RUNTIME, synchServerTarget);
							if (addArchiveProjectToEARDataModel.isProperty(AddWebModuleToEARDataModel.CONTEXT_ROOT) && importModel.isProperty(AddWebModuleToEARDataModel.CONTEXT_ROOT)) {
								addArchiveProjectToEARDataModel.setProperty(AddWebModuleToEARDataModel.CONTEXT_ROOT, importModel.getProperty(AddWebModuleToEARDataModel.CONTEXT_ROOT));
							}
							addArchiveProjectToEARDataModel.getDefaultOperation().run(monitor);
							if (synchServerTarget && importModel.isProperty("WARImportDataModel.HANDLED_ARCHIVES")) { //$NON-NLS-1$
								List weblibs = (List) importModel.getProperty("WARImportDataModel.HANDLED_ARCHIVES"); //$NON-NLS-1$
								for (int j = 0; null != weblibs && j < weblibs.size(); j++) {
									IProject webLibProject = ((J2EEUtilityJarImportDataModel) weblibs.get(j)).getProject();
									ServerTargetDataModel sModel = new ServerTargetDataModel();
									sModel.setProperty(ServerTargetDataModel.RUNTIME_TARGET_ID, model.getProperty(EnterpriseApplicationImportDataModel.SERVER_TARGET_ID));
									sModel.setProperty(ServerTargetDataModel.PROJECT_NAME, webLibProject.getName());
									sModel.getDefaultOperation().run(monitor);
								}
							}
						} else if (importModel instanceof J2EEUtilityJarImportDataModel) {
							addArchiveProjectToEARDataModel = AddUtilityProjectToEARDataModel.createAddToEARDataModel(earProject.getName(), archiveProject);
							addArchiveProjectToEARDataModel.setProperty(AddModuleToEARDataModel.ARCHIVE_URI, importModel.getStringProperty(J2EEArtifactImportDataModel.URI_FOR_MODULE_MAPPING));
							addArchiveProjectToEARDataModel.setBooleanProperty(AddArchiveToEARDataModel.SYNC_TARGET_RUNTIME, synchServerTarget);
							addArchiveProjectToEARDataModel.getDefaultOperation().run(monitor);
						}
					}
				}
			}
			if (!model.getBooleanProperty(EnterpriseApplicationImportDataModel.PRESERVE_PROJECT_METADATA) && earProject.exists()) {
				modelsToImport.removeAll(modelsNotToAddToEAR);
				fixupClasspaths(modelsToImport, earProject);
			}
		} finally {
			resetDisposeImportModels();
			//FileSet.printState();
		}
	}

	/**
	 * @param modelsToImport
	 */
	private void resetDisposeImportModels() {
		resetDisposeImportModels(getEARImportDataModel().getProjectModels());
	}

	private void resetDisposeImportModels(List models) {
		J2EEArtifactImportDataModel model;
		for (int i = 0; i < models.size(); i++) {
			model = (J2EEArtifactImportDataModel) models.get(i);
			model.setProperty(J2EEArtifactImportDataModel.CLOSE_ARCHIVE_ON_DISPOSE, Boolean.TRUE);
		}
	}


	private void fixupClasspaths(List selectedModels, IProject earProject) throws JavaModelException {
		J2EEArtifactImportDataModel importModel;
		for (int i = 0; i < selectedModels.size(); i++) {
			importModel = (J2EEArtifactImportDataModel) selectedModels.get(i);
			Archive archive = importModel.getArchiveFile();
			String[] manifestClasspath = archive.getManifest().getClassPathTokenized();
			if (manifestClasspath.length > 0) {
				List extraEntries = fixupClasspath(earProject, manifestClasspath, new ArrayList(), archive, importModel.getProject());
				addToClasspath(importModel, extraEntries);
			}
		}
	}

	private List fixupClasspath(IProject earProject, String[] manifestClasspath, List computedFiles, Archive anArchive, IProject aProject) throws JavaModelException {
		List extraEntries = new ArrayList();
		for (int j = 0; j < manifestClasspath.length; j++) {
			String manifestURI = ArchiveUtil.deriveEARRelativeURI(manifestClasspath[j], anArchive);
			if (null == manifestURI) {
				continue;
			}
			IFile file = earProject.getFile(manifestURI);
			if (!computedFiles.contains(file)) {
				computedFiles.add(file);
				if (null != file && file.exists()) {
					extraEntries.add(JavaCore.newLibraryEntry(file.getFullPath(), file.getFullPath(), null, true));
					Archive archive = null;
					try {
						archive = (Archive) getEARImportDataModel().getEARFile().getFile(file.getProjectRelativePath().toString());
						String[] nestedManifestClasspath = archive.getManifest().getClassPathTokenized();
						extraEntries.addAll(fixupClasspath(earProject, nestedManifestClasspath, computedFiles, archive, aProject));
					} catch (FileNotFoundException e) {
						Logger.getLogger().logError(e);
					} finally {
						if (null != archive) {
							archive.close();
						}
					}
				} else {
					Object key = new Object();
					EAREditModel editModel = null;
					try {
						editModel = (EAREditModel) EARNatureRuntime.getEditModelForProject(earProject, key);
						UtilityJARMapping mapping = editModel.getUtilityJARMapping(manifestURI);
						if (null != mapping) {
							IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(mapping.getProjectName());
							if (aProject != project) {
								extraEntries.add(JavaCore.newProjectEntry(project.getFullPath(), true));
							}
						} else {
							IProject project = editModel.getMappedProject(manifestURI);
							if (null != project && project.exists() && aProject != project) {
								extraEntries.add(JavaCore.newProjectEntry(project.getFullPath()));
							}
						}
					} finally {
						if (null != editModel) {
							editModel.releaseAccess(key);
						}
					}
				}
			}
		}
		return extraEntries;
	}

	protected void importEARProject(IProgressMonitor monitor) throws InvocationTargetException {
		EARFile earFile = getEARImportDataModel().getEARFile();
		try {
			EARProjectSaveStrategyImpl saveStrat = new EARProjectSaveStrategyImpl(getEARImportDataModel());
			saveStrat.setMonitor(monitor);
			earFile.save(saveStrat);
		} catch (Exception ex) {
			String errorString = EARArchiveOpsResourceHandler.getString("ERROR_IMPORTING_EAR_FILE"); //$NON-NLS-1$
			throw new WFTWrappedException(ex, errorString);
		}
	}

	private void releaseDeploymentDescriptor() {
		EARFile earFile = getEARImportDataModel().getEARFile();
		try {
			if (earFile != null && earFile.isDeploymentDescriptorSet()) {
				XMLResource res = (XMLResource) earFile.getDeploymentDescriptor().eResource();
				if (res != null)
					res.releaseFromRead();
			}
		} catch (Exception suppress) {
			com.ibm.wtp.common.logger.proxy.Logger.getLogger().logError(suppress);
		}
	}

	protected void createModuleProject(J2EEArtifactCreationDataModelOld model, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		EnterpriseApplicationCreationOperation earProjectOp = new EnterpriseApplicationCreationOperation((EnterpriseApplicationCreationDataModel) model);
		earProjectOp.run(monitor);
	}

	protected SaveStrategy createSaveStrategy(IProject project) { //NOOP
		return null;
	}
}