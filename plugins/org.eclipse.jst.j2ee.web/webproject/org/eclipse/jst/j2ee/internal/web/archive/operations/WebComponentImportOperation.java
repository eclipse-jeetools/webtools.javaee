/*******************************************************************************
 * Copyright (c) 2003, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.archive.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.archive.ArchiveWrapper;
import org.eclipse.jst.j2ee.internal.archive.ComponentArchiveSaveAdapter;
import org.eclipse.jst.j2ee.internal.archive.WebComponentArchiveSaveAdapter;
import org.eclipse.jst.j2ee.internal.archive.operations.J2EEArtifactImportOperation;
import org.eclipse.jst.j2ee.internal.web.plugin.WebPlugin;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.jst.j2ee.web.datamodel.properties.IWebComponentImportDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.operation.CreateReferenceComponentsDataModelProvider;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class WebComponentImportOperation extends J2EEArtifactImportOperation {
	/**
	 * @param model
	 */
	public WebComponentImportOperation(IDataModel model) {
		super(model);
	}

	protected final int LINK_COMPONENTS_WORK = 10;
	protected final int LIB_FOLDER_WORK = 2;
	
	@Override
	protected int computeTotalWork() {
		int baseWork = super.computeTotalWork() + LIB_FOLDER_WORK;
		List selectedLibs = (List) model.getProperty(IWebComponentImportDataModelProperties.WEB_LIB_ARCHIVES_SELECTED);
		List libProjects = (List) model.getProperty(IWebComponentImportDataModelProperties.WEB_LIB_MODELS);
		IDataModel importModel = null;
		ArchiveWrapper libArchive = null;
		for (int i = 0; null != libProjects && i < libProjects.size(); i++) {
			importModel = (IDataModel) libProjects.get(i);
			libArchive = (ArchiveWrapper) importModel.getProperty(IJ2EEComponentImportDataModelProperties.ARCHIVE_WRAPPER);
			if (selectedLibs.contains(libArchive)) {
				baseWork += LINK_COMPONENTS_WORK + PROJECT_CREATION_WORK + libArchive.getSize();
			}
		}
		
		return baseWork;
	}
	
	@Override
	protected void doExecute(IProgressMonitor monitor) throws ExecutionException {
		super.doExecute(monitor);
		IVirtualFolder libFolder = virtualComponent.getRootFolder().getFolder(WebArtifactEdit.WEBLIB);
		if (!libFolder.exists()) {
			try {
				libFolder.create(IResource.FORCE, new SubProgressMonitor(monitor, LIB_FOLDER_WORK));
			} catch (CoreException e) {
				WebPlugin.logError(e);
			}
		} else {
			monitor.worked(LIB_FOLDER_WORK);
		}
		try {
			importWebLibraryProjects(monitor);
		} catch (InvocationTargetException e) {
			WebPlugin.logError(e);
		} catch (InterruptedException e) {
			WebPlugin.logError(e);
		}
	}

	private void importWebLibraryProjects(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException, ExecutionException {
		List <ArchiveWrapper> selectedLibs = (List<ArchiveWrapper>) model.getProperty(IWebComponentImportDataModelProperties.WEB_LIB_ARCHIVES_SELECTED);
		List <Object> selectedLibsUnderlyingArchives = new ArrayList();
		for(ArchiveWrapper lib: selectedLibs){
			selectedLibsUnderlyingArchives.add(lib.getUnderLyingArchive());
		}
		List <IDataModel> libProjects = (List <IDataModel>) model.getProperty(IWebComponentImportDataModelProperties.WEB_LIB_MODELS);
		IDataModel importModel = null;
		IVirtualComponent nestedComponent = null;
		ArchiveWrapper libArchive = null;
		List targetComponents = new ArrayList();
		Map compToURIMap = new HashMap();
		for (int i = 0; null != libProjects && i < libProjects.size(); i++) {
			importModel = libProjects.get(i);
			libArchive = (ArchiveWrapper) importModel.getProperty(IJ2EEComponentImportDataModelProperties.ARCHIVE_WRAPPER);
			if (selectedLibsUnderlyingArchives.contains(libArchive.getUnderLyingArchive())) {
				importModel.getDefaultOperation().execute(new SubProgressMonitor(monitor, PROJECT_CREATION_WORK + libArchive.getSize()) , info);
				nestedComponent = (IVirtualComponent) importModel.getProperty(IJ2EEComponentImportDataModelProperties.COMPONENT);
				targetComponents.add(nestedComponent);
				String archiveURI = libArchive.getPath().lastSegment();
				compToURIMap.put(nestedComponent, archiveURI);
			}
		}
		/**
		 * The J2EEComponentClasspathContainr will handle adding these to the classpath, so they
		 * don't need to be added here.
		 */
		if (targetComponents.size() > 0) {
			IDataModel createRefComponentsModel = DataModelFactory.createDataModel(new CreateReferenceComponentsDataModelProvider());
			createRefComponentsModel.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, virtualComponent);
			createRefComponentsModel.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_DEPLOY_PATH, "/WEB-INF/lib/"); //$NON-NLS-1$
			createRefComponentsModel.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, targetComponents);
			createRefComponentsModel.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_TO_URI_MAP, compToURIMap);
			createRefComponentsModel.getDefaultOperation().execute(new SubProgressMonitor(monitor, LINK_COMPONENTS_WORK * targetComponents.size()), info);
		}
	}

	@Override
	protected ComponentArchiveSaveAdapter getArchiveSaveAdapter(
			IVirtualComponent virtualComponent) {
		return new WebComponentArchiveSaveAdapter(virtualComponent);
	}
}
