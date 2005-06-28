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
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.SaveStrategy;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.datamodel.properties.IEARComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.common.XMLResource;
import org.eclipse.jst.j2ee.internal.earcreation.EAREditModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.UtilityJARMapping;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.operation.CreateReferenceComponentsDataModelProvider;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class EARComponentImportOperation extends J2EEArtifactImportOperation {

	private EARFile earFile;

	public EARComponentImportOperation(IDataModel dataModel) {
		super(dataModel);
		earFile = (EARFile) model.getProperty(IEARComponentImportDataModelProperties.FILE);
	}

	/**
	 * Subclasses must override to performs the workbench modification steps that are to be
	 * contained within a single logical workbench change.
	 * 
	 * @param monitor
	 *            the progress monitor to use to display progress
	 */
	protected void doExecute(IProgressMonitor monitor) throws ExecutionException {
		super.doExecute(monitor);
		// monitor.beginTask(null, earFile.getFiles().size());


		// virtualComponent =
		// createVirtualComponent(model.getNestedModel(IJ2EEComponentImportDataModelProperties.NESTED_MODEL_J2EE_COMPONENT_CREATION),
		// monitor);

		// List modelsNotToAddToEAR = (List)
		// model.getProperty(IEARComponentImportDataModelProperties.UNHANDLED_PROJECT_MODELS_LIST);
		List modelsToImport = (List) model.getProperty(IEARComponentImportDataModelProperties.HANDLED_PROJECT_MODELS_LIST);
		try {
			IDataModel importModel = null;
			List allModels = (List) model.getProperty(IEARComponentImportDataModelProperties.ALL_PROJECT_MODELS_LIST);
			List componentHandlesToAdd = new ArrayList();
			for (int i = 0; i < allModels.size(); i++) {
				importModel = (IDataModel) allModels.get(i);
				if (modelsToImport.contains(importModel)) {
					importModel.setProperty(IJ2EEComponentImportDataModelProperties.CLOSE_ARCHIVE_ON_DISPOSE, Boolean.FALSE);
					try {
						importModel.getDefaultOperation().execute(monitor, info);
					} catch (ExecutionException e) {
						Logger.getLogger().logError(e);
					}
					componentHandlesToAdd.add(((IVirtualComponent) importModel.getProperty(IJ2EEComponentImportDataModelProperties.COMPONENT)).getComponentHandle());
				}
			}
			if (componentHandlesToAdd.size() > 0) {
				IDataModel addComponentsDM = DataModelFactory.createDataModel(new CreateReferenceComponentsDataModelProvider());
				addComponentsDM.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT_HANDLE, virtualComponent.getComponentHandle());
				addComponentsDM.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_HANDLE_LIST, componentHandlesToAdd);
			}

		} finally {
			resetDisposeImportModels();
			// FileSet.printState();
		}
	}

	/**
	 * @param modelsToImport
	 */
	private void resetDisposeImportModels() {
		resetDisposeImportModels((List) model.getProperty(IEARComponentImportDataModelProperties.ALL_PROJECT_MODELS_LIST));
	}

	private void resetDisposeImportModels(List models) {
		IDataModel model;
		for (int i = 0; i < models.size(); i++) {
			model = (IDataModel) models.get(i);
			model.setProperty(IJ2EEComponentImportDataModelProperties.CLOSE_ARCHIVE_ON_DISPOSE, Boolean.TRUE);
		}
	}


	private void fixupClasspaths(List selectedModels, IProject earProject) throws JavaModelException {
		IDataModel importModel;
		for (int i = 0; i < selectedModels.size(); i++) {
			importModel = (IDataModel) selectedModels.get(i);
			Archive archive = (Archive) importModel.getProperty(IJ2EEComponentImportDataModelProperties.FILE);
			String[] manifestClasspath = archive.getManifest().getClassPathTokenized();
			if (manifestClasspath.length > 0) {
				List extraEntries = fixupClasspath(earProject, manifestClasspath, new ArrayList(), archive, ((IVirtualComponent) importModel.getProperty(IJ2EEComponentImportDataModelProperties.COMPONENT)).getProject());
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
						archive = (Archive) earFile.getFile(file.getProjectRelativePath().toString());
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

	private void releaseDeploymentDescriptor() {
		try {
			if (earFile != null && earFile.isDeploymentDescriptorSet()) {
				XMLResource res = (XMLResource) earFile.getDeploymentDescriptor().eResource();
				if (res != null)
					res.releaseFromRead();
			}
		} catch (Exception suppress) {
			org.eclipse.jem.util.logger.proxy.Logger.getLogger().logError(suppress);
		}
	}

	protected SaveStrategy createSaveStrategy(IProject project) { // NOOP
		return null;
	}

	protected SaveStrategy createSaveStrategy(IVirtualComponent virtualComponent) {
		return new EARComponentSaveStrategyImpl(virtualComponent);
	}
}