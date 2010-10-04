/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
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
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jst.j2ee.application.internal.operations.AddComponentToEnterpriseApplicationDataModelProvider;
import org.eclipse.jst.j2ee.componentcore.EnterpriseArtifactEdit;
import org.eclipse.jst.j2ee.datamodel.properties.IEARComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.archive.ArchiveWrapper;
import org.eclipse.jst.j2ee.internal.archive.ComponentArchiveSaveAdapter;
import org.eclipse.jst.j2ee.internal.archive.EARComponentArchiveSaveAdapter;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.operation.CreateReferenceComponentsDataModelProvider;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class EARComponentImportOperation extends J2EEArtifactImportOperation {

	protected EnterpriseArtifactEdit artifactEdit = null;

	protected final int LINK_COMPONENTS_WORK = 10;
	protected final int WEB_LIB_WORK = 10;
	protected final int DISPOSE_WORK = 10;

	public EARComponentImportOperation(IDataModel model) {
		super(model);
	}

	
	@Override
	protected ComponentArchiveSaveAdapter getArchiveSaveAdapter(IVirtualComponent virtualComponent){
		return new EARComponentArchiveSaveAdapter(virtualComponent);
	}
	
	@Override
	protected int computeTotalWork() {
		int baseWork = super.computeTotalWork() + WEB_LIB_WORK + DISPOSE_WORK;
		List modelsToImport = (List) model.getProperty(IEARComponentImportDataModelProperties.HANDLED_PROJECT_MODELS_LIST);
		IDataModel importModel = null;
		for (int i = 0; i < modelsToImport.size(); i++) {
			importModel = (IDataModel) modelsToImport.get(i);
			ArchiveWrapper nestedArchive = (ArchiveWrapper) importModel.getProperty(IEARComponentImportDataModelProperties.ARCHIVE_WRAPPER);
			baseWork += LINK_COMPONENTS_WORK + PROJECT_CREATION_WORK + nestedArchive.getSize();
		}
		return baseWork;
	}

	/**
	 * Subclasses must override to performs the workbench modification steps
	 * that are to be contained within a single logical workbench change.
	 * 
	 * @param monitor
	 *            the progress monitor to use to display progress
	 */
	@Override
	protected void doExecute(IProgressMonitor monitor) throws ExecutionException {
		super.doExecute(monitor);
		ExecutionException firstNestedException = null;
		List modelsToImport = (List) model.getProperty(IEARComponentImportDataModelProperties.HANDLED_PROJECT_MODELS_LIST);
		try {
			IDataModel importModel = null;
			// make sure the wars handle importing their own web libs
			for (int i = modelsToImport.size() - 1; i > 0; i--) {
				importModel = (IDataModel) modelsToImport.get(i);
				ArchiveWrapper nestedArchive = (ArchiveWrapper) importModel.getProperty(IEARComponentImportDataModelProperties.ARCHIVE_WRAPPER);
				if (nestedArchive.getPath().toString().startsWith("WEB-INF/lib/")) { //$NON-NLS-1$
					ArchiveWrapper owningWar = nestedArchive.getParent();
					modelsToImport.remove(importModel);
					for (int j = 0; j < modelsToImport.size(); j++) {
						IDataModel warModel = (IDataModel) modelsToImport.get(j);
						if (((ArchiveWrapper) (warModel.getProperty(IEARComponentImportDataModelProperties.ARCHIVE_WRAPPER))).getUnderLyingArchive() == owningWar.getUnderLyingArchive()) {
							// TODO this is bad, but don't have access to the
							// plugin where these
							// constants are defined.
							String archivesSelected = "WARImportDataModel.WEB_LIB_ARCHIVES_SELECTED"; //$NON-NLS-1$
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
								if (((ArchiveWrapper)libModel.getProperty(IJ2EEComponentImportDataModelProperties.ARCHIVE_WRAPPER)).getUnderLyingArchive() == nestedArchive.getUnderLyingArchive()) {
									libModel.setProperty(IJ2EEComponentImportDataModelProperties.PROJECT_NAME, importModel.getProperty(IJ2EEComponentImportDataModelProperties.PROJECT_NAME));
									libModel.setProperty(IFacetProjectCreationDataModelProperties.FACET_RUNTIME, importModel.getProperty(IFacetProjectCreationDataModelProperties.FACET_RUNTIME));
								}
							}
						}
					}
				}
			}
			monitor.worked(WEB_LIB_WORK);

			List <String> ddSpecifiedURIs = ((ArchiveWrapper) model.getProperty(IEARComponentImportDataModelProperties.ARCHIVE_WRAPPER)).getDDMappedModuleURIs();
			List componentToAddAsModules = new ArrayList();
			Map componentToURIMapAsModules = new HashMap();
			List componentToAddAsComponents = new ArrayList();
			Map componentToURIMapAsComponents = new HashMap();
			Map moduleDeployPathMap = new HashMap();
			Map componentDeployPathMap = new HashMap();
			
			
			for (int i = 0; i < modelsToImport.size(); i++) {
				importModel = (IDataModel) modelsToImport.get(i);
				ArchiveWrapper nestedArchive = (ArchiveWrapper) importModel.getProperty(IEARComponentImportDataModelProperties.ARCHIVE_WRAPPER);
				importModel.setProperty(IJ2EEComponentImportDataModelProperties.CLOSE_ARCHIVE_ON_DISPOSE, Boolean.FALSE);
				IDataModel compCreationModel = importModel.getNestedModel(IJ2EEComponentImportDataModelProperties.NESTED_MODEL_J2EE_COMPONENT_CREATION);
				if (compCreationModel.isProperty(IJ2EEFacetProjectCreationDataModelProperties.MODULE_URI)){
					compCreationModel.setProperty(IJ2EEFacetProjectCreationDataModelProperties.MODULE_URI, nestedArchive.getPath().toString());
				}
				compCreationModel.setBooleanProperty(IJ2EEFacetProjectCreationDataModelProperties.ADD_TO_EAR, false);
				try {
					IStatus nestedImportStatus = importModel.getDefaultOperation().execute(new SubProgressMonitor(monitor, PROJECT_CREATION_WORK + nestedArchive.getSize()), info);
					if (!nestedImportStatus.isOK() && nestedImportStatus.getSeverity() == IStatus.ERROR) {
						throw new ExecutionException(nestedImportStatus.getMessage(), nestedImportStatus.getException());
					}
				} catch (ExecutionException e) {
					if (firstNestedException == null) {
						firstNestedException = e;
					}
					J2EEPlugin.logError(e);
				}
				IVirtualComponent component = (IVirtualComponent) importModel.getProperty(IJ2EEComponentImportDataModelProperties.COMPONENT);
				String uri = nestedArchive.getPath().toString();
				String archiveName = nestedArchive.getPath().lastSegment().toString();
				String deployPath = nestedArchive.getPath().removeLastSegments(1).makeAbsolute().toString();
				
				if(ddSpecifiedURIs.contains(uri)){
					componentToAddAsModules.add(component);
					componentToURIMapAsModules.put(component, archiveName);
					moduleDeployPathMap.put(component, deployPath);
				} else {
					componentToAddAsComponents.add(component);
					componentToURIMapAsComponents.put(component, archiveName);
					componentDeployPathMap.put(component, deployPath);
				}
			}
			
			if (componentToAddAsModules.size() > 0) {
				//this block adds the component references to the component file and the EAR DD
				//this should only be called if the EAR DD already contains the module
				//and is necessary to update the id attribute (for the dependency object)
				IDataModel addComponentsDM = DataModelFactory.createDataModel(new AddComponentToEnterpriseApplicationDataModelProvider());
				addComponentsDM.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, virtualComponent);
				addComponentsDM.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, componentToAddAsModules);
				addComponentsDM.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_TO_URI_MAP, componentToURIMapAsModules);
				addComponentsDM.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_DEPLOY_PATH_MAP, moduleDeployPathMap);
				addComponentsDM.getDefaultOperation().execute(new SubProgressMonitor(monitor, LINK_COMPONENTS_WORK/2), info);
			}
			if (componentToAddAsComponents.size() > 0){
				//this block only adds the component references to the component file
				//this should only be called when they are not already linked into the EAR DD
				IDataModel addComponentsDM = DataModelFactory.createDataModel(new CreateReferenceComponentsDataModelProvider());
				addComponentsDM.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, virtualComponent);
				addComponentsDM.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, componentToAddAsComponents);
				addComponentsDM.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_TO_URI_MAP, componentToURIMapAsComponents);
				addComponentsDM.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_DEPLOY_PATH_MAP, componentDeployPathMap);
				addComponentsDM.getDefaultOperation().execute(new SubProgressMonitor(monitor, LINK_COMPONENTS_WORK/2), info);
			}
		
		} finally {
			if (null != artifactEdit) {
				artifactEdit.dispose();
				artifactEdit = null;
			}
			resetDisposeImportModels();
			monitor.worked(DISPOSE_WORK);
		}
		if (firstNestedException != null) {
			throw firstNestedException;
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
	
}
