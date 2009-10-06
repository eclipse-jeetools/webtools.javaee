/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     David Schneider, david.schneider@unisys.com - [142500] WTP properties pages fonts don't follow Eclipse preferences
 *******************************************************************************/
package org.eclipse.jst.j2ee.ui.project.facet.appclient;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jst.j2ee.internal.actions.IJ2EEUIContextIds;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.wizard.J2EEModuleFacetInstallPage;
import org.eclipse.jst.j2ee.project.facet.IAppClientFacetInstallDataModelProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

public class AppClientFacetInstallPage extends J2EEModuleFacetInstallPage implements IAppClientFacetInstallDataModelProperties {

	private Label configFolderLabel;
	private Text configFolder;
	private Button createMainClass;
	private Text outputFolder;
	private Label outputFolderLabel;
	
	public AppClientFacetInstallPage() {
		super("appclient.facet.install.page"); //$NON-NLS-1$
		setTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_CLIENT_PROJECT_MAIN_PG_TITLE));
		setDescription(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_CLIENT_SETTINGS));
	}

	protected String[] getValidationPropertyNames() {
		return new String[]{EAR_PROJECT_NAME, CONFIG_FOLDER, CREATE_DEFAULT_MAIN_CLASS, OUTPUT_FOLDER};
	}

	protected Composite createTopLevelComposite(Composite parent) {
		setInfopopID(IJ2EEUIContextIds.NEW_APPCLIENT_WIZARD_P3);
		final Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));

		configFolderLabel = new Label(composite, SWT.NONE);
		configFolderLabel.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.SOURCEFOLDER));
		configFolderLabel.setLayoutData(gdhfill());

		configFolder = new Text(composite, SWT.BORDER);
		configFolder.setLayoutData(gdhfill());
		configFolder.setData("label", configFolderLabel); //$NON-NLS-1$
		synchHelper.synchText(configFolder, CONFIG_FOLDER, null);
		
		outputFolderLabel = new Label(composite, SWT.NONE);
		outputFolderLabel.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.OUTPUTFOLDER));
		outputFolderLabel.setLayoutData(gdhfill());
		
		outputFolder = new Text(composite, SWT.BORDER);
		outputFolder.setLayoutData(gdhfill());
		outputFolder.setData("label", this.outputFolderLabel); //$NON-NLS-1$
		synchHelper.synchText(outputFolder, OUTPUT_FOLDER, null);
		
		createMainClass = new Button(composite, SWT.CHECK);
		createMainClass.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_CLIENT_CREATE_MAIN));
		synchHelper.synchCheckbox(createMainClass, CREATE_DEFAULT_MAIN_CLASS, null);
		
        createGenerateDescriptorControl( composite );
        registerFacetVersionChangeListener();
        
	    Dialog.applyDialogFont(parent);
		return composite;
	}
	
    protected void handleFacetVersionChangedEvent()
    {
        final IProjectFacetVersion fv = (IProjectFacetVersion) this.model.getProperty( FACET_VERSION );
        this.addDD.setVisible( fv == APPLICATION_CLIENT_50 );
    }

}
