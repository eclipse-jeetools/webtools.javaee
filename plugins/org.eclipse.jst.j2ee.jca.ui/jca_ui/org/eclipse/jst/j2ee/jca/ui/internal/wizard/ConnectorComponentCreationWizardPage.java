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
package org.eclipse.jst.j2ee.jca.ui.internal.wizard;

import org.eclipse.jst.j2ee.internal.actions.IJ2EEUIContextIds;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.wizard.J2EEComponentCreationWizardPage;
import org.eclipse.jst.j2ee.jca.ui.internal.util.JCAUIMessages;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class ConnectorComponentCreationWizardPage extends J2EEComponentCreationWizardPage {

	/**
	 * @param model
	 * @param pageName
	 */
	public ConnectorComponentCreationWizardPage(IDataModel model, String pageName) {
		super(model, pageName);
		setTitle(JCAUIMessages.JCA_MODULE_MAIN_PG_TITLE);
		setDescription(JCAUIMessages.JCA_MODULE_MAIN_PG_DESC);
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.JCA_PROJECT_WIZARD_BANNER));
	}

	protected String getVersionLabel() {
		return JCAUIMessages.JCA_VERSION_LBL;
	}

	protected String[] getValidationPropertyNames() {
		return super.getValidationPropertyNames();
	}

	/**
	 *  
	 */
	protected String getInfopopID() {
		return IJ2EEUIContextIds.NEW_CONNECTOR_WIZARD_P1;
	}
	
	protected void addToAdvancedComposite(Composite advanced) {
		super.addToAdvancedComposite(advanced);
		super.createSourceFolderComposite(advanced);
	}	
//	protected void addToAdvancedComposite(Composite advanced) {
////		super.addToAdvancedComposite(advanced);
////		createAnnotationsGroup(advanced);
//		super.createMultipleModulesComposite(advanced);
//	}	
}
