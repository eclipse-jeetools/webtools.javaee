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

import org.eclipse.jst.j2ee.ui.project.facet.EarSelectionPanel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetInstalDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelWizardPage;
import org.eclipse.wst.common.project.facet.ui.IFacetWizardPage;
import org.eclipse.wst.common.project.facet.ui.IWizardContext;

public abstract class J2EEModuleFacetInstallPage extends DataModelWizardPage implements IFacetWizardPage, IFacetInstalDataModelProperties {

	protected static GridData gdhfill() {
	    return new GridData( GridData.FILL_HORIZONTAL );
	}

	protected EarSelectionPanel earPanel;

	public J2EEModuleFacetInstallPage(){
    	//TODO figure out a better way to do this without compromising the IDataModelWizard framework.
    	super(DataModelFactory.createDataModel(new AbstractDataModelProvider(){}), "web.facet.install.page"); //$NON-NLS-1$
	}

	public void setWizardContext(IWizardContext context) {
		// Intentionally empty
	}

	public void transferStateToConfig() {
		// Intentionally empty
	}

	public void dispose() {
		if(null != earPanel){
			earPanel.dispose();
		}
		super.dispose();
	}

	public void setConfig(final Object config) {
		model.removeListener(this);
		synchHelper.dispose();
		
	    model = (IDataModel) config;
	    model.addListener(this);
		synchHelper = initializeSynchHelper(model);
	}

	protected void setupEarControl(final Composite parent) {
		 this.earPanel = new EarSelectionPanel( model, parent, SWT.NONE );
	     this.earPanel.setLayoutData( gdhfill() );
	}
	
	
}
