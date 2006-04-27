package org.eclipse.jst.servlet.ui.internal.actions;

/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
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
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebFacetProjectCreationDataModelProvider;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
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
			IFacetedProject facetedProject = ProjectFacetsManager.create(project);
			Set fixedFacets = new HashSet();
			fixedFacets.addAll(facetedProject.getFixedProjectFacets());
			IProjectFacet webFacet = ProjectFacetsManager.getProjectFacet(IModuleConstants.WST_WEB_MODULE);
			fixedFacets.remove(webFacet);
			facetedProject.setFixedProjectFacets(fixedFacets);
			facetedProject.uninstallProjectFacet(facetedProject.getInstalledVersion(webFacet), null, new NullProgressMonitor());
			IDataModel model = DataModelFactory.createDataModel(new WebFacetProjectCreationDataModelProvider());
			model.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, project.getName());
			model.getDefaultOperation().execute(new NullProgressMonitor(), null);
		} catch (Exception e) {
			Logger.logException(e);
		}
	}
}