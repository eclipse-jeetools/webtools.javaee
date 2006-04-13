/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive.operations;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.application.internal.operations.AddComponentToEnterpriseApplicationDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.IAddComponentToEnterpriseApplicationDataModelProperties;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.SaveStrategy;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.jst.j2ee.datamodel.properties.IEARComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class EARComponentImportOperation extends J2EEArtifactImportOperation {

	protected EARArtifactEdit artifactEdit = null;

	public EARComponentImportOperation(IDataModel model) {
		super(model);
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
		List modelsToImport = (List) model.getProperty(IEARComponentImportDataModelProperties.HANDLED_PROJECT_MODELS_LIST);
		try {
			IDataModel importModel = null;
			// make sure the wars handle importing their own web libs
			for (int i = modelsToImport.size() - 1; i > 0; i--) {
				importModel = (IDataModel) modelsToImport.get(i);
				Archive nestedArchive = (Archive) importModel.getProperty(IEARComponentImportDataModelProperties.FILE);
				if (nestedArchive.getURI().startsWith(ArchiveConstants.WEBAPP_LIB_URI)) {
					WARFile owningWar = (WARFile) nestedArchive.eContainer();
					modelsToImport.remove(importModel);
					for (int j = 0; j < modelsToImport.size(); j++) {
						IDataModel warModel = (IDataModel) modelsToImport.get(j);
						if (warModel.getProperty(IEARComponentImportDataModelProperties.FILE) == owningWar) {
							// TODO this is bad, but don't have access to the plugin where these
							// constants are defined.
							String archivesSelected = "WARImportDataModel.WEB_LIB_ARCHIVES_SELECTED";
							String libModels = "WARImportDataModel.WEB_LIB_MODELS"; //$NON-NLS-1$
							List warHandledArchives = (List) warModel.getProperty(archivesSelected);
							if (warHandledArchives == Collections.EMPTY_LIST) {
								warHandledArchives = new ArrayList();
								warModel.setProperty(archivesSelected, warHandledArchives);
							}
							warHandledArchives.add(nestedArchive);
							List warLibModels = (List) warModel.getProperty(libModels);
							for (int k = 0; k < warLibModels.size(); k++) {
								IDataModel libModel = (IDataModel) warLibModels.get(k);
								if (libModel.getProperty(IJ2EEComponentImportDataModelProperties.FILE) == nestedArchive) {
									libModel.setProperty(IJ2EEComponentImportDataModelProperties.PROJECT_NAME, importModel.getProperty(IJ2EEComponentImportDataModelProperties.PROJECT_NAME));
								}
							}
						}
					}
				}
			}

			List componentToAdd = new ArrayList();
			Map componentToURIMap = new HashMap();
			for (int i = 0; i < modelsToImport.size(); i++) {
				importModel = (IDataModel) modelsToImport.get(i);
				Archive nestedArchive = (Archive) importModel.getProperty(IEARComponentImportDataModelProperties.FILE);
				importModel.setProperty(IJ2EEComponentImportDataModelProperties.CLOSE_ARCHIVE_ON_DISPOSE, Boolean.FALSE);
				IDataModel compCreationModel = importModel.getNestedModel(IJ2EEComponentImportDataModelProperties.NESTED_MODEL_J2EE_COMPONENT_CREATION);
				if (compCreationModel.isProperty(IJ2EEFacetProjectCreationDataModelProperties.MODULE_URI))
					compCreationModel.setProperty(IJ2EEFacetProjectCreationDataModelProperties.MODULE_URI, nestedArchive.getURI());
				try {
					importModel.getDefaultOperation().execute(monitor, info);
				} catch (ExecutionException e) {
					Logger.getLogger().logError(e);
				}
				IVirtualComponent component = (IVirtualComponent) importModel.getProperty(IJ2EEComponentImportDataModelProperties.COMPONENT);
				componentToAdd.add(component);
				componentToURIMap.put(component, nestedArchive.getURI());
			}
			if (componentToAdd.size() > 0) {
				IDataModel addComponentsDM = DataModelFactory.createDataModel(new AddComponentToEnterpriseApplicationDataModelProvider());
				addComponentsDM.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, virtualComponent);
				addComponentsDM.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, componentToAdd);
				addComponentsDM.setProperty(IAddComponentToEnterpriseApplicationDataModelProperties.TARGET_COMPONENTS_TO_URI_MAP, componentToURIMap);
				addComponentsDM.getDefaultOperation().execute(monitor, info);
			}
			try {
				fixupClasspaths(modelsToImport, virtualComponent);
			} catch (JavaModelException e) {
				Logger.getLogger().logError(e);
			}
		} finally {
			if (null != artifactEdit) {
				artifactEdit.dispose();
				artifactEdit = null;
			}
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


	private void fixupClasspaths(List selectedModels, IVirtualComponent earComponent) throws JavaModelException {
		IDataModel importModel;
		for (int i = 0; i < selectedModels.size(); i++) {
			importModel = (IDataModel) selectedModels.get(i);
			Archive archive = (Archive) importModel.getProperty(IJ2EEComponentImportDataModelProperties.FILE);
			String[] manifestClasspath = archive.getManifest().getClassPathTokenized();
			if (manifestClasspath.length > 0) {
				if (null == artifactEdit) {
					artifactEdit = EARArtifactEdit.getEARArtifactEditForRead(earComponent.getProject());
				}
				List extraEntries = fixupClasspath(earComponent, manifestClasspath, new ArrayList(), archive, (IVirtualComponent) importModel.getProperty(IJ2EEComponentImportDataModelProperties.COMPONENT));
				addToClasspath(importModel, extraEntries);
				fixModuleReference(importModel, manifestClasspath);
			}
		}
	}

	private List fixupClasspath(IVirtualComponent earComponent, String[] manifestClasspath, List computedFiles, Archive anArchive, IVirtualComponent nestedComponent) throws JavaModelException {
		List extraEntries = new ArrayList();
		for (int j = 0; j < manifestClasspath.length; j++) {
			String manifestURI = ArchiveUtil.deriveEARRelativeURI(manifestClasspath[j], anArchive);
			if (null == manifestURI) {
				continue;
			}
			IVirtualFile vFile = earComponent.getRootFolder().getFile(manifestURI);
			if (!computedFiles.contains(vFile)) {
				computedFiles.add(vFile);
				if (vFile.exists()) {
					IFile file = vFile.getUnderlyingFile();
					extraEntries.add(JavaCore.newLibraryEntry(file.getFullPath(), file.getFullPath(), null, true));
					Archive archive = null;
					try {
						archive = (Archive) getEarFile().getFile(manifestURI);
						String[] nestedManifestClasspath = archive.getManifest().getClassPathTokenized();
						extraEntries.addAll(fixupClasspath(earComponent, nestedManifestClasspath, computedFiles, archive, nestedComponent));
					} catch (FileNotFoundException e) {
						Logger.getLogger().logError(e);
					} finally {
						if (null != archive) {
							archive.close();
						}
					}
				} else {
					IVirtualComponent comp = artifactEdit.getModuleByManifestURI(manifestURI);
					if (null != comp) {
						IProject project = comp.getProject();
						extraEntries.add(JavaCore.newProjectEntry(project.getFullPath(), true));
					} else {
						String compSearchName = manifestURI.substring(0, manifestURI.length() - 4);
						IVirtualReference vRef = earComponent.getReference(compSearchName);
						if (vRef == null) {
							IVirtualReference[] refs = earComponent.getReferences();
							String archiveName = null;
							for (int refCount = 0; vRef == null && refCount < refs.length; refCount++) {
								archiveName = refs[refCount].getArchiveName();
								if (null != archiveName && archiveName.equals(manifestURI)) {
									vRef = refs[refCount];
								}
							}
						}

						if (null != vRef && nestedComponent.getProject() != vRef.getReferencedComponent().getProject()) {
							IProject project = vRef.getReferencedComponent().getProject();
							extraEntries.add(JavaCore.newProjectEntry(project.getFullPath(), true));
						}
					}
				}
			}
		}
		return extraEntries;
	}

	protected SaveStrategy createSaveStrategy(IProject project) { // NOOP
		return null;
	}

	protected SaveStrategy createSaveStrategy(IVirtualComponent virtualComponent) {
		return new EARComponentSaveStrategyImpl(virtualComponent);
	}

	protected EARFile getEarFile() {
		return (EARFile) model.getProperty(IEARComponentImportDataModelProperties.FILE);
	}
}
