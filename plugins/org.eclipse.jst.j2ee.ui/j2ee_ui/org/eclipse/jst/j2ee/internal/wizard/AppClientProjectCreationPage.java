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
/*
 * Created on Nov 7, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.jst.j2ee.applicationclient.creation.AppClientModuleCreationDataModel;
import org.eclipse.jst.j2ee.applicationclient.creation.FlexibleAppClientCreationDataModel;
import org.eclipse.jst.j2ee.internal.actions.IJ2EEUIContextIds;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;


/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AppClientProjectCreationPage extends J2EEModuleCreationPage {

	/**
	 * @param model
	 * @param pageName
	 */
	public AppClientProjectCreationPage(FlexibleAppClientCreationDataModel model, String pageName) {
		super(model, pageName);
		setTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_CLIENT_PROJECT_MAIN_PG_TITLE));
		setDescription(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_CLIENT_PROJECT_MAIN_PG_DESC));
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.APP_CLIENT_PROJECT_WIZARD_BANNER));
	}

	protected void addToAdvancedComposite(Composite advanced) {
		super.addToAdvancedComposite(advanced);
		new Label(advanced, SWT.NONE); // pad
		Button createDefaultMain = new Button(advanced, SWT.CHECK);
		createDefaultMain.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_CLIENT_CREATE_MAIN));
		synchHelper.synchCheckbox(createDefaultMain, AppClientModuleCreationDataModel.CREATE_DEFAULT_MAIN_CLASS, null);
	}


	protected String getVersionLabel() {
		return J2EEUIMessages.getResourceString(J2EEUIMessages.APP_CLIENT_VERSION_LBL);
	}

	protected Composite createTopLevelComposite(Composite parent) {
		setInfopopID(IJ2EEUIContextIds.NEW_APPCLIENT_WIZARD_P1);
		return super.createTopLevelComposite(parent);
	}

}