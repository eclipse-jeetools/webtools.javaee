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
 * Created on Mar 4, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.migration.actions;

import java.util.Collections;
import java.util.List;

import org.eclipse.jst.j2ee.internal.migration.ComposedMigrationConfig;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIContextIds;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.ui.plugin.AbstractUIPlugin;


/**
 * @author vijayb
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class ConnectorMigrationWizardPage extends GeneralModuleMigrationWizardPage {
	/**
	 * @param pageName
	 * @param config
	 */
	public ConnectorMigrationWizardPage(String pageName, ComposedMigrationConfig config) {
		super(pageName, config);
		setTitle(IMigrationWizardConstants.CONNECTOR_MIGRATE_WIZARD_TITLE);
		setDescription(IMigrationWizardConstants.CONNECTOR_MIGRATE_WIZARD_DESCRIPTION);
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(J2EEUIPlugin.PLUGIN_ID, "connectormigwiz")); //$NON-NLS-1$ 
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.migration.actions.GeneralModuleMigrationWizardPage#getConfigs()
	 */
	protected List getConfigs() {
		return composedConfig == null ? Collections.EMPTY_LIST : composedConfig.getConnectorChildren();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.migration.actions.GeneralModuleMigrationWizardPage#getContextId()
	 */
	protected String getContextId() {
		return J2EEUIContextIds.MIGRATION_WIZARD_CONNECTOR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.ui.wizard.WTPWizardPage#getValidationPropertyNames()
	 */
	protected String[] getValidationPropertyNames() {
		// TODO Auto-generated method stub
		return null;
	}
}