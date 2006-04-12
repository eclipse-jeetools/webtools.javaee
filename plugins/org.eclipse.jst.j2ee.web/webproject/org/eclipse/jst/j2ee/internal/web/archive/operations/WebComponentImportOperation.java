/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.archive.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.SaveStrategy;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.archive.operations.J2EEArtifactImportOperation;
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

	protected void doExecute(IProgressMonitor monitor) throws ExecutionException {
		super.doExecute(monitor);
		IVirtualFolder libFolder = virtualComponent.getRootFolder().getFolder(WebArtifactEdit.WEBLIB);
		if (!libFolder.exists()) {
			try {
				libFolder.create(IResource.FORCE, new NullProgressMonitor());
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			importWebLibraryProjects(monitor);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void importWebLibraryProjects(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException, ExecutionException {
		List selectedLibs = (List) model.getProperty(IWebComponentImportDataModelProperties.WEB_LIB_ARCHIVES_SELECTED);
		List libProjects = (List) model.getProperty(IWebComponentImportDataModelProperties.WEB_LIB_MODELS);
		IDataModel importModel = null;
		IVirtualComponent nestedComponent = null;
		Archive libArchive = null;
		List targetComponents = new ArrayList();
		List extraEntries = new ArrayList();
		for (int i = 0; null != libProjects && i < libProjects.size(); i++) {
			importModel = (IDataModel) libProjects.get(i);
			libArchive = (Archive) importModel.getProperty(IJ2EEComponentImportDataModelProperties.FILE);
			if (selectedLibs.contains(libArchive)) {
				importModel.getDefaultOperation().execute(monitor, info);
				nestedComponent = (IVirtualComponent) importModel.getProperty(IJ2EEComponentImportDataModelProperties.COMPONENT);
				targetComponents.add(nestedComponent);
				extraEntries.add(JavaCore.newProjectEntry(nestedComponent.getProject().getFullPath(), true));
			}
		}
		if (targetComponents.size() > 0) {
			IDataModel createRefComponentsModel = DataModelFactory.createDataModel(new CreateReferenceComponentsDataModelProvider());
			createRefComponentsModel.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, virtualComponent);
			createRefComponentsModel.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_DEPLOY_PATH, "/WEB-INF/lib/"); //$NON-NLS-1$
			createRefComponentsModel.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, targetComponents);
			createRefComponentsModel.getDefaultOperation().execute(monitor, info);
			try {
				addToClasspath(model, extraEntries);
			} catch (JavaModelException e) {
				Logger.getLogger().logError(e);
			}
		}
	}

	protected SaveStrategy createSaveStrategy(IVirtualComponent aVirtualComponent) {
		return new WebComponentSaveStrategyImpl(aVirtualComponent);
	}
}
