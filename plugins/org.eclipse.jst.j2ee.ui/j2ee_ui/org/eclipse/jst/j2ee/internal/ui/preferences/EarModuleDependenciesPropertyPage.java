/******************************************************************************
 * Copyright (c) 2009 Red Hat
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Rob Stryker - initial implementation and ongoing maintenance
 ******************************************************************************/
package org.eclipse.jst.j2ee.internal.ui.preferences;

import java.util.ArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.application.internal.operations.AddComponentToEnterpriseApplicationDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.RemoveComponentFromEnterpriseApplicationDataModelProvider;
import org.eclipse.jst.j2ee.internal.common.classpath.J2EEComponentClasspathUpdater;
import org.eclipse.jst.jee.project.facet.EarCreateDeploymentFilesDataModelProvider;
import org.eclipse.jst.jee.project.facet.ICreateDeploymentFilesDataModelProperties;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.ui.propertypage.AddModuleDependenciesPropertiesPage;
import org.eclipse.wst.common.componentcore.ui.propertypage.ModuleAssemblyRootPage;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;

public class EarModuleDependenciesPropertyPage extends
		AddModuleDependenciesPropertiesPage {
	public EarModuleDependenciesPropertyPage(IProject project,
			ModuleAssemblyRootPage page) {
		super(project, page);
	}

	protected IDataModelOperation generateEARDDOperation() {
		IDataModel model = DataModelFactory.createDataModel(new EarCreateDeploymentFilesDataModelProvider());
		model.setProperty(ICreateDeploymentFilesDataModelProperties.GENERATE_DD, rootComponent);
		model.setProperty(ICreateDeploymentFilesDataModelProperties.TARGET_PROJECT, project);
		return model.getDefaultOperation();
	}
	
	@Override
	public boolean postHandleChanges(IProgressMonitor monitor) {
		return true;
	}
	
	@Override
	protected void handleRemoved(ArrayList<IVirtualComponent> removed) {
		super.handleRemoved(removed);
		J2EEComponentClasspathUpdater.getInstance().queueUpdateEAR(rootComponent.getProject());
	}

//	protected void postAddProjects(Set moduleProjects) throws CoreException {
//		EarFacetRuntimeHandler.updateModuleProjectRuntime(rootComponent.getProject(), moduleProjects, new NullProgressMonitor());
//	}

	@Override
	protected IDataModelProvider getAddReferenceDataModelProvider(IVirtualComponent component) {
		return new AddComponentToEnterpriseApplicationDataModelProvider();
	}

	@Override
	protected String getAddFolderLabel() {
		
		return Messages.EarModuleDependenciesPropertyPage_0;
	}

	@Override
	protected String getAddReferenceLabel() {
		
		return Messages.EarModuleDependenciesPropertyPage_1;
	}

	@Override
	protected String getEditReferenceLabel() {
		
		return Messages.EarModuleDependenciesPropertyPage_2;
	}

	@Override
	protected String getModuleAssemblyRootPageDescription() {
		
		return Messages.EarModuleDependenciesPropertyPage_3;
	}

	@Override
	protected IDataModelProvider getRemoveReferenceDataModelProvider(IVirtualComponent component) {
		
			return new RemoveComponentFromEnterpriseApplicationDataModelProvider();
		
	}

}
