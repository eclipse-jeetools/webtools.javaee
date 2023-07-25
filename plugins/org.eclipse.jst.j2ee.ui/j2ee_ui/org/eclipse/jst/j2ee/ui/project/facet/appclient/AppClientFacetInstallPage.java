/*******************************************************************************
 * Copyright (c) 2005, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     David Schneider, david.schneider@unisys.com - [142500] WTP properties pages fonts don't follow Eclipse preferences
 *******************************************************************************/
package org.eclipse.jst.j2ee.ui.project.facet.appclient;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.actions.IJ2EEUIContextIds;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.wizard.J2EEModuleFacetInstallPage;
import org.eclipse.jst.j2ee.project.facet.IAppClientFacetInstallDataModelProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

public class AppClientFacetInstallPage extends J2EEModuleFacetInstallPage implements IAppClientFacetInstallDataModelProperties {

	private Button createMainClass;
	
	public AppClientFacetInstallPage() {
		super("appclient.facet.install.page"); //$NON-NLS-1$
		setTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_CLIENT_PROJECT_MAIN_PG_TITLE));
		setDescription(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_CLIENT_SETTINGS));
	}

	@Override
	protected String[] getValidationPropertyNames() {
		return new String[]{EAR_PROJECT_NAME, CONFIG_FOLDER, CREATE_DEFAULT_MAIN_CLASS};
	}

	@Override
	protected Composite createTopLevelComposite(Composite parent) {
		setInfopopID(IJ2EEUIContextIds.NEW_APPCLIENT_WIZARD_P3);
		final Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));

		createMainClass = new Button(composite, SWT.CHECK);
		createMainClass.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_CLIENT_CREATE_MAIN));
		synchHelper.synchCheckbox(createMainClass, CREATE_DEFAULT_MAIN_CLASS, null);
		
        createGenerateDescriptorControl(composite, J2EEConstants.APP_CLIENT_DD_SHORT_NAME);
        registerFacetVersionChangeListener();
        
	    Dialog.applyDialogFont(parent);
		return composite;
	}
	
    @Override
	protected void handleFacetVersionChangedEvent()
    {
        final IProjectFacetVersion fv = (IProjectFacetVersion) this.model.getProperty( FACET_VERSION );
        boolean isJEE5OrGreater = J2EEVersionUtil.convertVersionStringToInt(fv.getVersionString()) >= J2EEVersionConstants.JEE_5_0_ID;
        this.addDD.setVisible(isJEE5OrGreater);
    }

}
