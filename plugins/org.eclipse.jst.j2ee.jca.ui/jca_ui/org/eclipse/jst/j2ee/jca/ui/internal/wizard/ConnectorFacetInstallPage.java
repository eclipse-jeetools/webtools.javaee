/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.jca.ui.internal.wizard;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.actions.IJ2EEUIContextIds;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.wizard.J2EEModuleFacetInstallPage;
import org.eclipse.jst.j2ee.jca.ui.internal.util.JCAUIMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

public class ConnectorFacetInstallPage extends J2EEModuleFacetInstallPage  {


	
	public ConnectorFacetInstallPage() {
		super("connector.facet.install.page"); //$NON-NLS-1$
		setTitle(JCAUIMessages.JCA_MODULE_MAIN_PG_TITLE);
		setDescription(JCAUIMessages.CONNECTOR_SETTINGS);
	}


	@Override
	protected Composite createTopLevelComposite(Composite parent) {
		setInfopopID(IJ2EEUIContextIds.NEW_CONNECTOR_WIZARD_P3);
		final Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));


        createGenerateDescriptorControl(composite, J2EEConstants.RAR_SHORT_NAME);
        registerFacetVersionChangeListener();
        
	    Dialog.applyDialogFont(parent);
		return composite;
	}
	
    @Override
	protected void handleFacetVersionChangedEvent()
    {
        final IProjectFacetVersion fv = (IProjectFacetVersion) this.model.getProperty( FACET_VERSION );
        boolean isJEE6OrGreater = J2EEVersionUtil.convertVersionStringToInt(fv.getVersionString()) >= J2EEVersionConstants.JCA_1_6_ID;
        this.addDD.setVisible(isJEE6OrGreater);
    }


	@Override
	protected String[] getValidationPropertyNames() {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
	protected void enter() {
		super.enter();
        final IProjectFacetVersion fv = (IProjectFacetVersion) this.model.getProperty( FACET_VERSION );
        boolean isJEE6OrGreater = J2EEVersionUtil.convertVersionStringToInt(fv.getVersionString()) >= J2EEVersionConstants.JCA_1_6_ID;
        if( isJEE6OrGreater ){
        	this.addDD.setVisible(isJEE6OrGreater);
        	this.addDD.setEnabled(isJEE6OrGreater);
        }
        else{
        	this.addDD.setVisible(true);
        	this.addDD.setEnabled(isJEE6OrGreater);
        }		
	}	
}
