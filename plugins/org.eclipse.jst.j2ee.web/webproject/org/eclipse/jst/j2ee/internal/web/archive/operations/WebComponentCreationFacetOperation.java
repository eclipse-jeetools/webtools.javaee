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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.project.facet.FacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.project.facet.IFacetDataModelPropeties;
import org.eclipse.jst.j2ee.project.facet.IFacetProjectCreationDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.JavaFacetInstallDataModelProvider;
import org.eclipse.jst.j2ee.web.datamodel.properties.IWebComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.web.project.facet.IWebFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.web.project.facet.WebFacetInstallDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;
import org.eclipse.wst.common.project.facet.core.runtime.RuntimeManager;

public class WebComponentCreationFacetOperation extends AbstractDataModelOperation {

	public WebComponentCreationFacetOperation(IDataModel model) {
		super(model);
	}

	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		IDataModel dm = DataModelFactory.createDataModel(new FacetProjectCreationDataModelProvider());
		String projectName = model.getStringProperty(IWebComponentCreationDataModelProperties.PROJECT_NAME);
		dm.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, projectName);
		List facetDMs = new ArrayList();
		facetDMs.add(setupJavaInstallAction());
		facetDMs.add(setupWebInstallAction());
		dm.setProperty(IFacetProjectCreationDataModelProperties.FACET_DM_LIST, facetDMs);
		return dm.getDefaultOperation().execute(monitor, info);
	}

	protected IDataModel setupWebInstallAction() {
		String versionStr = model.getPropertyDescriptor(IWebComponentCreationDataModelProperties.COMPONENT_VERSION).getPropertyDescription();
		IDataModel webFacetInstallDataModel = DataModelFactory.createDataModel(new WebFacetInstallDataModelProvider());
		webFacetInstallDataModel.setProperty(IWebFacetInstallDataModelProperties.FACET_PROJECT_NAME, model.getStringProperty(IWebComponentCreationDataModelProperties.PROJECT_NAME));
		webFacetInstallDataModel.setProperty(IWebFacetInstallDataModelProperties.FACET_VERSION_STR, versionStr);
		webFacetInstallDataModel.setProperty(IWebFacetInstallDataModelProperties.CONTENT_DIR, model.getStringProperty(IWebComponentCreationDataModelProperties.WEBCONTENT_FOLDER));
		return webFacetInstallDataModel;
	}

	protected IDataModel setupJavaInstallAction() {
		IDataModel dm = DataModelFactory.createDataModel(new JavaFacetInstallDataModelProvider());
		dm.setProperty(IFacetDataModelPropeties.FACET_PROJECT_NAME, model.getStringProperty(IWebComponentCreationDataModelProperties.PROJECT_NAME));
		dm.setProperty(IWebFacetInstallDataModelProperties.FACET_VERSION_STR, "1.4");
		return dm;
	}

	protected void setRuntime(IFacetedProject facetProj) throws CoreException {
		String runtimeID = model.getStringProperty(IWebComponentCreationDataModelProperties.RUNTIME_TARGET_ID);
		try {
			IRuntime runtime = RuntimeManager.getRuntime(runtimeID);
			facetProj.setRuntime(runtime, null);
		} catch (IllegalArgumentException e) {
			Logger.getLogger().logError(e);
		}
	}

}
