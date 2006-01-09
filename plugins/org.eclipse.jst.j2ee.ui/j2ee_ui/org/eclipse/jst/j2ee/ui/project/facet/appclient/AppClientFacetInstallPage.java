/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ui.project.facet.appclient;

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

public class AppClientFacetInstallPage extends J2EEModuleFacetInstallPage implements IAppClientFacetInstallDataModelProperties {

	private Label configFolderLabel;
	private Text configFolder;
	private Button createMainClass;
	
	public AppClientFacetInstallPage() {
		super("appclient.facet.install.page"); //$NON-NLS-1$
		setTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_CLIENT_PROJECT_MAIN_PG_TITLE));
		setDescription(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_CLIENT_SETTINGS));
	}

	protected String[] getValidationPropertyNames() {
		return new String[]{EAR_PROJECT_NAME, CONFIG_FOLDER, CREATE_DEFAULT_MAIN_CLASS};
	}

	protected Composite createTopLevelComposite(Composite parent) {
		setInfopopID(IJ2EEUIContextIds.NEW_APPCLIENT_WIZARD_P3);
		final Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		
		//setupEarControl(composite);
		
		configFolderLabel = new Label(composite, SWT.NONE);
		configFolderLabel.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.SOURCEFOLDER));
		configFolderLabel.setLayoutData(gdhfill());

		configFolder = new Text(composite, SWT.BORDER);
		configFolder.setLayoutData(gdhfill());
		configFolder.setData("label", configFolderLabel); //$NON-NLS-1$
		synchHelper.synchText(configFolder, CONFIG_FOLDER, null);
		
		createMainClass = new Button(composite, SWT.CHECK);
		createMainClass.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_CLIENT_CREATE_MAIN));
		synchHelper.synchCheckbox(createMainClass, CREATE_DEFAULT_MAIN_CLASS, null);
		
		return composite;
	}

}
