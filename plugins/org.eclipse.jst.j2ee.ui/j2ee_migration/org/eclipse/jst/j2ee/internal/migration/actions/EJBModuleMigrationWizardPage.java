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
package org.eclipse.jst.j2ee.internal.migration.actions;


import java.util.Collections;
import java.util.List;

import org.eclipse.jst.j2ee.internal.migration.ComposedMigrationConfig;
import org.eclipse.jst.j2ee.internal.migration.EJBJarMigrationConfig;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIContextIds;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetDataModel;
import org.eclipse.jst.j2ee.plugin.J2EEPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.plugin.AbstractUIPlugin;


/**
 * EJBModuleMigrationWizardPage
 */
public class EJBModuleMigrationWizardPage extends GeneralModuleMigrationWizardPage {
	private boolean cmpSelectionCache = false;
	private boolean firstVisit = true;
	private boolean localClientSelectionCache = false;
	protected Button localCmpMigrationButton, localAddLocalClient;
	protected List modules;

	/**
	 * Constructor for EJBModuleMigrationWizard.
	 * 
	 * @param pageName
	 */
	public EJBModuleMigrationWizardPage(String pageName, ComposedMigrationConfig ejbJarConfig) {
		super(pageName, ejbJarConfig);
		setTitle(IMigrationWizardConstants.EJB_MIGRATE_MODULE_WIZARD_TITLE);
		setDescription(IMigrationWizardConstants.EJB_MIGRATE_MODULE_WIZARD_DESCRIPTION);
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(J2EEUIPlugin.PLUGIN_ID, "ejbmodule_mig_wiz")); //$NON-NLS-1$
	}

	public void addListeners() {
		super.addListeners();
		localCmpMigrationButton.addListener(SWT.Selection, this);
		localAddLocalClient.addListener(SWT.Selection, this);
	}

	protected void createEARButtons(Composite composite) {
		super.createEARButtons(composite);
		localCmpMigrationButton = new Button(composite, SWT.CHECK);
		localCmpMigrationButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		localCmpMigrationButton.setText(IMigrationWizardConstants.EJB_MIGRATE_CMP_BEANS);
		localAddLocalClient = new Button(composite, SWT.CHECK);
		localAddLocalClient.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		localAddLocalClient.setText(IMigrationWizardConstants.EJB_MIGRATE_LOCAL_CLIENTS);
		localAddLocalClient.setSelection(false);
		localAddLocalClient.setEnabled(true);
		handleAddLocalClient(false);
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.wizard.J2EEWizardPage#createTopLevelComposite(Composite)
	 */
	protected Composite createTopLevelComposite(Composite parent) {
		return super.createTopLevelComposite(parent);
	}

	protected void createViewer(Composite parent) {
		super.createViewer(parent);
	}

	public void enter() {
		super.enter();
		if (firstVisit == true) {
			firstVisit = false;
			initialSetup();
		} else
			handleMigrateProjectSelectionChange(super.migrateProject.getSelection());
	}

	protected List getConfigs() {
		return composedConfig == null ? Collections.EMPTY_LIST : composedConfig.getEJBJarChildren();
	}

	protected String getContextId() {
		return J2EEUIContextIds.MIGRATION_WIZARD_EJB;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.ui.wizard.WTPWizardPage#getValidationPropertyNames()
	 */
	protected String[] getValidationPropertyNames() {
		return new String[]{ServerTargetDataModel.RUNTIME_TARGET_ID};
	}

	private void handleAddLocalClient(boolean addClient) {
		List configs = getConfigs();
		for (int i = 0; i < configs.size(); i++) {
			EJBJarMigrationConfig config = (EJBJarMigrationConfig) configs.get(i);
			if (addClient)
				config.selectAllEntities();
			else
				config.deselectAllChildren();
		}
	}

	private void handleCMPMigrationButton(boolean migrateCMP) {
		List configs = getConfigs();
		for (int i = 0; i < configs.size(); i++) {
			EJBJarMigrationConfig config = (EJBJarMigrationConfig) configs.get(i);
			config.setMigrateCMP1xto2x(migrateCMP);
		}
	}

	public void handleEvent(Event event) {
		if (event.widget == super.migrateProject) {
			if (!localCmpMigrationButton.getEnabled())
				localCmpMigrationButton.setEnabled(true);
			if (!localAddLocalClient.getEnabled())
				localAddLocalClient.setEnabled(true);
			handleMigrateProjectSelectionChange(super.migrateProject.getSelection());
		}
		if (event.widget == localCmpMigrationButton) {
			cmpSelectionCache = localCmpMigrationButton.getSelection();
			handleCMPMigrationButton(localCmpMigrationButton.getSelection());
			localAddLocalClient.setSelection(localCmpMigrationButton.getSelection());
			localClientSelectionCache = localAddLocalClient.getSelection();
			handleAddLocalClient(localAddLocalClient.getSelection());
		} else if (event.widget == localAddLocalClient) {
			handleAddLocalClient(localAddLocalClient.getSelection());
		}
		super.handleEvent(event);
	}

	private void handleMigrateProjectSelectionChange(boolean migrateJ2EE) {
		if (migrateJ2EE) {
			localAddLocalClient.setEnabled(true);
			localCmpMigrationButton.setEnabled(true);
			initialSetup();
			localAddLocalClient.setSelection(localClientSelectionCache);
			localCmpMigrationButton.setSelection(cmpSelectionCache);
		} else {
			localClientSelectionCache = localAddLocalClient.getSelection();
			localAddLocalClient.setEnabled(false);
			localAddLocalClient.setSelection(false);

			cmpSelectionCache = localCmpMigrationButton.getSelection();
			localCmpMigrationButton.setEnabled(false);
			localCmpMigrationButton.setSelection(false);
		}
		handleAddLocalClient(localAddLocalClient.getSelection());
		handleCMPMigrationButton(localCmpMigrationButton.getSelection());
	}

	protected void initialSetup() {
		localCmpMigrationButton.setEnabled(true);
		localAddLocalClient.setEnabled(true);
		if (!super.migrateProject.getSelection() || !J2EEPlugin.hasDevelopmentRole()) {
			localCmpMigrationButton.setEnabled(false);
			localCmpMigrationButton.setSelection(false);
			handleCMPMigrationButton(false);

			localAddLocalClient.setEnabled(false);
			localAddLocalClient.setSelection(false);
			handleAddLocalClient(false);
		} else if (super.migrateProject.getSelection()) {
			localCmpMigrationButton.setEnabled(true);
		}
		List ejbHolder = getConfigs();

		if (ejbHolder != null && !ejbHolder.isEmpty() && ((EJBJarMigrationConfig) ejbHolder.get(0)).getChildCount() == 0) {
			handleCMPButtonState(false);
			handleCMPMigrationButton(false);
			handleAddLocalClient(false);
		} else if (ejbHolder != null && !ejbHolder.isEmpty() && ((EJBJarMigrationConfig) ejbHolder.get(0)).getEntityChildren().size() == 0) {
			handleCMPButtonState(false);
			handleCMPMigrationButton(false);
		}
	}

	/**
	 *  
	 */
	private void handleCMPButtonState(boolean bool) {
		localCmpMigrationButton.setEnabled(bool);
		localCmpMigrationButton.setSelection(bool);
		localAddLocalClient.setEnabled(bool);
		localAddLocalClient.setEnabled(bool);
	}

	public boolean isAddLocalClientSelected() {
		return localAddLocalClient.getSelection();
	}

	public void setLocalClientSelection(boolean selection) {
		localAddLocalClient.setSelection(selection);
	}

	protected void validateControls() {
		super.validateControls();
	}


}