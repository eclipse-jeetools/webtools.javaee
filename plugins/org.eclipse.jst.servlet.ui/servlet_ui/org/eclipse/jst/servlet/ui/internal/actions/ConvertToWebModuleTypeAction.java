package org.eclipse.jst.servlet.ui.internal.actions;

/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jst.common.project.facet.IJavaFacetInstallDataModelProperties;
import org.eclipse.jst.common.project.facet.JavaFacetInstallDataModelProvider;
import org.eclipse.jst.common.project.facet.JavaFacetUtils;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.web.project.facet.IWebFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.web.project.facet.WebFacetInstallDataModelProvider;
import org.eclipse.jst.j2ee.web.project.facet.WebFacetUtils;
import org.eclipse.jst.servlet.ui.internal.wizard.ConvertToWebModuleTypeDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.project.facet.ProductManager;
import org.eclipse.wst.web.ui.internal.Logger;
/**
 * Convert a simple static web project to a J2EE Dynamic Web Project
 */
public class ConvertToWebModuleTypeAction extends Action implements IWorkbenchWindowActionDelegate {

	IStructuredSelection fSelection = null;
	IProject project = null;
	IWorkbenchWindow fWindow;

	/**
	 * ConvertLinksDialog constructor comment.
	 */
	public ConvertToWebModuleTypeAction() {
		super();
	}

	/**
	 * make sure a web project is selected.
	 */
	protected boolean isValidProject(IProject aProject) {
		return J2EEProjectUtilities.isStaticWebProject(aProject);
	}

	/**
	 * selectionChanged method comment.
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		boolean bEnable = false;
		if (selection instanceof IStructuredSelection) {
			fSelection = (IStructuredSelection) selection;
			bEnable = validateSelected(fSelection);
		}
		((Action) action).setEnabled(bEnable);
	}

	/**
	 * selectionChanged method comment.
	 */
	protected boolean validateSelected(ISelection selection) {
		if (!(selection instanceof IStructuredSelection))
			return false;

		fSelection = (IStructuredSelection) selection;

		Object selectedProject = fSelection.getFirstElement();
		if (!(selectedProject instanceof IProject))
			return false;

		project = (IProject) selectedProject;
		return isValidProject(project);
	}

	public void dispose() {
		// Default
	}

	public void init(IWorkbenchWindow window) {
		// Default
	}

	public void run(IAction action) {
		try {
			IWorkbenchWindow window = PlatformUI.getWorkbench().getWorkbenchWindows()[0];
			ConvertToWebModuleTypeDialog dialog = new ConvertToWebModuleTypeDialog(window.getShell());
			dialog.open();
			if (dialog.getReturnCode() == Window.CANCEL)
				return;
			
			doConvert(ConvertToWebModuleTypeDialog.getSelectedVersion());
			
		} catch (Exception e) {
			Logger.logException(e);
		}
	}
	
	protected void doConvert(String selectedVersion) throws Exception {
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
