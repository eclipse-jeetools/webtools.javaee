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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.application.internal.operations.AddComponentToEnterpriseApplicationDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.IAddComponentToEnterpriseApplicationDataModelProperties;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.SaveStrategy;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.jst.j2ee.datamodel.properties.IEARComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
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
		} finally {
			if (null != artifactEdit) {
				artifactEdit.dispose();
				artifactEdit = null;
			}
			resetDisposeImportModels();
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
