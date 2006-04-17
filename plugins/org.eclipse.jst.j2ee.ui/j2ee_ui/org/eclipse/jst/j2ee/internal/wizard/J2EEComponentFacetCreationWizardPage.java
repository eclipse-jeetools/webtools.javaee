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
import org.eclipse.wst.web.ui.internal.wizards.DataModelFacetCreationWizardPage;

public abstract class J2EEComponentFacetCreationWizardPage extends DataModelFacetCreationWizardPage implements IFacetProjectCreationDataModelProperties {

    private static final String STORE_LABEL = "LASTEARNAME_"; //$NON-NLS-1$
    
	protected EarSelectionPanel earPanel;
  
	public J2EEComponentFacetCreationWizardPage(IDataModel dataModel, String pageName) {
		super(dataModel, pageName);
	}

	protected Composite createTopLevelComposite(Composite parent) {
        final Composite top = super.createTopLevelComposite( parent );
		createEarComposite(top);
		return top;
	}

	private void createEarComposite(Composite top) {
		FacetDataModelMap map = (FacetDataModelMap) model.getProperty(FACET_DM_MAP);
		IDataModel facetModel = (IDataModel) map.get(getModuleFacetID());
		earPanel = new EarSelectionPanel(facetModel, top);
	}

	protected abstract String getModuleFacetID();

	public void dispose() {
		super.dispose();
		if (earPanel != null)
			earPanel.dispose();
	}
	
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
    
	protected IDialogSettings getDialogSettings() {
        return J2EEUIPlugin.getDefault().getDialogSettings();
    }
	
	protected String[] getValidationPropertyNames() {
		String[] superProperties = super.getValidationPropertyNames();
		List list = Arrays.asList(superProperties);
		ArrayList arrayList = new ArrayList();
		arrayList.addAll( list );
		arrayList.add( IJ2EEFacetProjectCreationDataModelProperties.EAR_PROJECT_NAME );
		arrayList.add( IJ2EEFacetProjectCreationDataModelProperties.ADD_TO_EAR );
		return (String[])arrayList.toArray( new String[0] );
	}	
}
