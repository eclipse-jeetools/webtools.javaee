/*******************************************************************************
 * Copyright (c) 2008, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.operations;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jst.common.project.facet.IJavaFacetInstallDataModelProperties;
import org.eclipse.jst.common.project.facet.JavaFacetInstallDataModelProvider;
import org.eclipse.jst.common.project.facet.JavaFacetUtils;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.web.plugin.WebPlugin;
import org.eclipse.jst.j2ee.web.datamodel.properties.IConvertStaticWebToDyamnicDataModelProperties;
import org.eclipse.jst.j2ee.web.project.facet.IWebFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.web.project.facet.WebFacetInstallDataModelProvider;
import org.eclipse.jst.j2ee.web.project.facet.WebFacetUtils;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.project.facet.ProductManager;

public class ConvertStaticWebToDyamnicOperation extends AbstractDataModelOperation {

	public ConvertStaticWebToDyamnicOperation() {
		super();
	}
	
	public ConvertStaticWebToDyamnicOperation(IDataModel dm) {
		super(dm);
	}

	@Override
	public IStatus execute(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
			try {
				doConvert();
			} catch (Exception e) {
				WebPlugin.log(e);
			}
		
		
		return OK_STATUS;
	}
	
	protected void doConvert() throws Exception {
		
		
		String selectedVersion = model.getStringProperty(IConvertStaticWebToDyamnicDataModelProperties.CONVERT_TO_WEB_VERSION);
		IProject project = (IProject) model.getProperty(IConvertStaticWebToDyamnicDataModelProperties.PROJECT);
		IFacetedProject facetedProject = ProjectFacetsManager.create(project);
		Set fixedFacets = new HashSet();
		fixedFacets.addAll(facetedProject.getFixedProjectFacets());
		IProjectFacet webFacet = ProjectFacetsManager.getProjectFacet(IModuleConstants.WST_WEB_MODULE);
		fixedFacets.remove(webFacet);
		fixedFacets.add(WebFacetUtils.WEB_FACET);
		fixedFacets.add(JavaFacetUtils.JAVA_FACET);
		facetedProject.setFixedProjectFacets(fixedFacets);
		IProjectFacetVersion webFv = WebFacetUtils.WEB_FACET.getVersion(selectedVersion);
		IProjectFacetVersion javaFv = JavaFacetUtils.compilerLevelToFacet(JavaFacetUtils.getCompilerLevel(project));
		IFacetedProject.Action uninstall = new IFacetedProject.Action(IFacetedProject.Action.Type.UNINSTALL, facetedProject.getInstalledVersion(webFacet), null);
		IDataModel webModelCfg = DataModelFactory.createDataModel(new WebFacetInstallDataModelProvider());
		webModelCfg.setBooleanProperty(IWebFacetInstallDataModelProperties.ADD_TO_EAR, false);
		// Get the default web root folder name (just in case the .component file doesn't exist for some reason)
		String webRoot = webModelCfg.getStringProperty(IWebFacetInstallDataModelProperties.CONFIG_FOLDER);
		
		IVirtualComponent c = ComponentCore.createComponent(project);
		c.create(0, null);
		if (c.exists()) {
			// Get the web root folder from the .component file 
			IVirtualFolder root = c.getRootFolder();
			webRoot = root.getUnderlyingFolder().getName();
			// Store the name into the properties for use during facet instal
			webModelCfg.setStringProperty(IWebFacetInstallDataModelProperties.CONFIG_FOLDER, webRoot);
		}

		IDataModel javaModelCfg = null;
		if (ProductManager.shouldUseSingleRootStructure()){
			javaModelCfg = DataModelFactory.createDataModel(new JavaFacetInstallDataModelProvider());
			javaModelCfg.setProperty(IJavaFacetInstallDataModelProperties.DEFAULT_OUTPUT_FOLDER_NAME,
					webRoot+"/"+ J2EEConstants.WEB_INF_CLASSES); //$NON-NLS-1$
		}		
		
		IFacetedProject.Action install = new IFacetedProject.Action(IFacetedProject.Action.Type.INSTALL,webFv,webModelCfg);
		IFacetedProject.Action javaInstall = new IFacetedProject.Action(IFacetedProject.Action.Type.INSTALL, javaFv, javaModelCfg);
		Set set = new HashSet();
		set.add(uninstall);
		set.add(install);
		set.add(javaInstall);
		facetedProject.modify(set, new NullProgressMonitor());
	}
	
}
