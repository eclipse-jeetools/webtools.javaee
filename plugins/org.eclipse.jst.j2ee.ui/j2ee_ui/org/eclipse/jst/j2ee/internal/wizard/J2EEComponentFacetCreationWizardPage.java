/*******************************************************************************
 * Copyright (c) 2003, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Nov 10, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.internal.wizard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetProjectCreationDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.ui.project.facet.EarSelectionPanel;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IFacetedProjectWorkingCopy;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.web.ui.internal.wizards.DataModelFacetCreationWizardPage;

public abstract class J2EEComponentFacetCreationWizardPage extends DataModelFacetCreationWizardPage {

    private static final String STORE_LABEL = "LASTEARNAME_"; //$NON-NLS-1$
    
	protected EarSelectionPanel earPanel;
	
	private boolean shouldAddEARComposite = true;
  
	

	public J2EEComponentFacetCreationWizardPage(IDataModel dataModel, String pageName) {
		super(dataModel, pageName);
	}

	@Override
	protected Composite createTopLevelComposite(Composite parent) {
        final Composite top = super.createTopLevelComposite(parent);
		if(isShouldAddEARComposite()){
        createEarComposite(top);
        createWorkingSetGroupPanel(top, new String[] { RESOURCE_WORKING_SET, JAVA_WORKING_SET });
		}
        return top;
	}

	private void createEarComposite(Composite top) 
	{
	    final IFacetedProjectWorkingCopy fpjwc
	        = (IFacetedProjectWorkingCopy) this.model.getProperty( FACETED_PROJECT_WORKING_COPY );
	    
	    final String moduleFacetId = getModuleFacetID();
	    final IProjectFacet moduleFacet = ProjectFacetsManager.getProjectFacet( moduleFacetId );
	    final IFacetedProject.Action action = fpjwc.getProjectFacetAction( moduleFacet );
	    
		earPanel = new EarSelectionPanel( (IDataModel) action.getConfig(), top );
	}

	protected abstract String getModuleFacetID();

	@Override
	protected String getModuleTypeID() {
		return getModuleFacetID();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		if (earPanel != null)
			earPanel.dispose();
	}
	
    @Override
	public void storeDefaultSettings() {
    	super.storeDefaultSettings();
        IDialogSettings settings = getDialogSettings();
        if (settings != null) {
        	FacetDataModelMap map = (FacetDataModelMap)model.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
	    	String facetID = getModuleFacetID();
	    	IDataModel j2eeModel = map.getFacetDataModel(facetID);
        	if(j2eeModel.getBooleanProperty(IJ2EEModuleFacetInstallDataModelProperties.ADD_TO_EAR)){
	        	String lastEARName = j2eeModel.getStringProperty(IJ2EEModuleFacetInstallDataModelProperties.EAR_PROJECT_NAME);
	            settings.put(STORE_LABEL, lastEARName);
        	}
        }
    }
    
    @Override
	public void restoreDefaultSettings() {
    	super.restoreDefaultSettings();
        IDialogSettings settings = getDialogSettings();
        if (settings != null) {
            String lastEARName = settings.get(STORE_LABEL);
            if (lastEARName != null){
            	FacetDataModelMap map = (FacetDataModelMap)model.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
            	String facetID = getModuleFacetID();
            	IDataModel j2eeModel = map.getFacetDataModel(facetID);
                j2eeModel.setProperty(IJ2EEModuleFacetInstallDataModelProperties.LAST_EAR_NAME, lastEARName);
            }
		}
	}
    
	@Override
	protected IDialogSettings getDialogSettings() {
        return J2EEUIPlugin.getDefault().getDialogSettings();
    }
	
	@Override
	protected String[] getValidationPropertyNames() {
		String[] superProperties = super.getValidationPropertyNames();
		List list = Arrays.asList(superProperties);
		ArrayList arrayList = new ArrayList();
		arrayList.addAll( list );
		arrayList.add( IJ2EEFacetProjectCreationDataModelProperties.EAR_PROJECT_NAME );
		arrayList.add( IJ2EEFacetProjectCreationDataModelProperties.ADD_TO_EAR );
		return (String[])arrayList.toArray( new String[0] );
	}
	
	protected boolean isShouldAddEARComposite() {
		return shouldAddEARComposite;
	}

	protected void setShouldAddEARComposite(boolean shouldAddEARComposite) {
		this.shouldAddEARComposite = shouldAddEARComposite;
	}
}
