/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.migration.actions;


import java.util.Collections;
import java.util.List;

import org.eclipse.jst.j2ee.internal.migration.ComposedMigrationConfig;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIContextIds;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetDataModel;
import org.eclipse.ui.plugin.AbstractUIPlugin;


public class WebMigrationWizardPage extends GeneralModuleMigrationWizardPage {

	/**
	 * Constructor for WebMigrationWizardPage.
	 * 
	 * @param pageName
	 */
	public WebMigrationWizardPage(String pageName, ComposedMigrationConfig config) {
		super(pageName, config);
		setTitle(IMigrationWizardConstants.WEB_MIGRATE_WIZARD_TITLE);
		setDescription(IMigrationWizardConstants.WEB_MIGRATE_WIZARD_DESCRIPTION);
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(J2EEUIPlugin.PLUGIN_ID, "webmigration_wiz")); //$NON-NLS-1$ 
	}

	protected String getContextId() {
		return J2EEUIContextIds.MIGRATION_WIZARD_WEB;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.migration.actions.GeneralModuleMigrationWizardPage#getConfigs()
	 */
	protected List getConfigs() {
		return composedConfig == null ? Collections.EMPTY_LIST : composedConfig.getWebChildren();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.ui.wizard.WTPWizardPage#getValidationPropertyNames()
	 */
	protected String[] getValidationPropertyNames() {
		// TODO Auto-generated method stub
		return new String[]{ServerTargetDataModel.RUNTIME_TARGET_ID};
	}

}