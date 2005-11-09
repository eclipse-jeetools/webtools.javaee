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
package org.eclipse.jst.servlet.ui.project.facet;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebFacetProjectCreationDataModelProvider;
import org.eclipse.jst.servlet.ui.internal.wizard.WebComponentFacetCreationWizardPage;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IFacetedProjectTemplate;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.common.project.facet.core.IFacetedProject.Action.Type;
import org.eclipse.wst.common.project.facet.ui.AddRemoveFacetsWizard;
import org.eclipse.wst.common.project.facet.ui.internal.ConflictingFacetsFilter;
import org.eclipse.wst.common.project.facet.ui.internal.FacetsSelectionPanel;

public class WebProjectWizard2 extends AddRemoveFacetsWizard implements INewWizard, IFacetProjectCreationDataModelProperties {

	protected IDataModel dataModel = null;
	private final IFacetedProjectTemplate template;

	private IWizardPage firstPage;

	public WebProjectWizard2() {
		super(null);
		dataModel = DataModelFactory.createDataModel(new WebFacetProjectCreationDataModelProvider());
		template = getTemplate();
	}

	protected IFacetedProjectTemplate getTemplate() {
		return ProjectFacetsManager.getTemplate("template.jst.web"); //$NON-NLS-1$
	}

	public void addPages() {
		firstPage = new WebComponentFacetCreationWizardPage(dataModel, "first.page"); //$NON-NLS-1$
		addPage(firstPage);
		super.addPages();

		
		final Set fixed = this.template.getFixedProjectFacets();

		this.facetsSelectionPage.setFixedProjectFacets(fixed);

		Set facetVersions = new HashSet();
		FacetDataModelMap map = (FacetDataModelMap) dataModel.getProperty(FACET_DM_MAP);
		for(Iterator iterator=map.values().iterator(); iterator.hasNext();){
			IDataModel model = (IDataModel)iterator.next();
			facetVersions.add(model.getProperty(IFacetDataModelProperties.FACET_VERSION));
		}
		this.facetsSelectionPage.setInitialSelection(facetVersions);

		
		final ConflictingFacetsFilter filter = new ConflictingFacetsFilter(fixed);

		this.facetsSelectionPage.setFilters(new FacetsSelectionPanel.IFilter[]{filter});
	}

	public IWizardPage[] getPages() {
		final IWizardPage[] base = super.getPages();
		final IWizardPage[] pages = new IWizardPage[base.length + 1];

		pages[0] = this.firstPage;
		System.arraycopy(base, 0, pages, 1, base.length);

		return pages;
	}

	public String getProjectName() {
		return dataModel.getStringProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME);
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
	}

	public boolean performFinish() {
		IStatus status = dataModel.validate();
		if (status.isOK()) {
			try {
				dataModel.getDefaultOperation().execute(null, null);
				return true;
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public Object getConfig(IProjectFacetVersion fv, Type type, String pjname) throws CoreException{
		FacetDataModelMap map = (FacetDataModelMap) dataModel.getProperty(FACET_DM_MAP);
		IDataModel configDM = (IDataModel)map.get(fv.getProjectFacet().getId()); 
		if(configDM == null){
			configDM = (IDataModel)fv.createActionConfig( type, pjname);
			map.add(configDM);
		}
		return configDM;
	}

}
