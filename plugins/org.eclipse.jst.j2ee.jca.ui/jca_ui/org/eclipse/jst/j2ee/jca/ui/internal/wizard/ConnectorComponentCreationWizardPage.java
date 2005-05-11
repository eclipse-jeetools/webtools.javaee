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
package org.eclipse.jst.j2ee.jca.ui.internal.wizard;

import org.eclipse.jst.j2ee.internal.actions.IJ2EEUIContextIds;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.wizard.J2EEComponentCreationWizardPage;
import org.eclipse.jst.j2ee.jca.ui.internal.util.JCAUIMessages;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class ConnectorComponentCreationWizardPage extends J2EEComponentCreationWizardPage {

	/**
	 * @param model
	 * @param pageName
	 */
	public ConnectorComponentCreationWizardPage(IDataModel model, String pageName) {
		super(model, pageName);
		setTitle(JCAUIMessages.getResourceString(JCAUIMessages.JCA_MODULE_MAIN_PG_TITLE));
		setDescription(JCAUIMessages.getResourceString(JCAUIMessages.JCA_MODULE_MAIN_PG_DESC));
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.JCA_PROJECT_WIZARD_BANNER));
	}

	protected String getVersionLabel() {
		return JCAUIMessages.getResourceString(JCAUIMessages.JCA_VERSION_LBL);
	}
	//TODO: utility to handle additions
	protected String[] getValidationPropertyNames() {
		return new String[]{PROJECT_NAME, COMPONENT_NAME, COMPONENT_VERSION, ADD_TO_EAR};
	}

	/**
	 *  
	 */
	protected String getInfopopID() {
		return IJ2EEUIContextIds.NEW_CONNECTOR_WIZARD_P1;
	}
}