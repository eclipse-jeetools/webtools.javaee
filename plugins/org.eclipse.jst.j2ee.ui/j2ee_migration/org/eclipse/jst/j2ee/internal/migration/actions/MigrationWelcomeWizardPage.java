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


import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIContextIds;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.help.WorkbenchHelp;


/**
 * MigrationWelcomeWizardPage
 */
public class MigrationWelcomeWizardPage extends WizardPage {

	/**
	 * Constructor for MigrationWelcomeWizardPage.
	 * 
	 * @param pageName
	 */
	public MigrationWelcomeWizardPage(String pageName) {
		super(pageName);
		setTitle(IMigrationWizardConstants.MIGRATION_WIZARD_WElCOME_TITLE);
		setDescription(IMigrationWizardConstants.MIGRATION_WIZARD_WELCOME_BANNER);
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.wizard.J2EEWizardPage#createTopLevelComposite(Composite)
	 */
	protected Composite createTopLevelComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));
		WorkbenchHelp.setHelp(parent, J2EEUIContextIds.MIGRATION_WIZARD_GENERAL);

		createGeneralWarnings(composite);
		createBulletedSteps(composite);
		createFinishingRemarks(composite);

		return composite;
	}

	private void createGeneralWarnings(Composite parent) {
		Composite info = new Composite(parent, SWT.NULL);
		info.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		info.setLayout(layout);

		Label generalInfo = new Label(info, SWT.WRAP);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.heightHint = 30;
		data.widthHint = 470;
		generalInfo.setLayoutData(data);
		generalInfo.setText(IMigrationWizardConstants.MIGRATION_WIZARD_TEXT_ONE);

		Label migrationDesc = new Label(info, SWT.WRAP);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.heightHint = 40;
		data.widthHint = 450;
		migrationDesc.setLayoutData(data);
		migrationDesc.setText(IMigrationWizardConstants.MIGRATION_WIZARD_TEXT_TWO);
	}

	private void createBulletedSteps(Composite composite) {
		Composite info = new Composite(composite, SWT.NULL);
		info.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		info.setLayout(layout);

		Label warningIcon = new Label(info, SWT.WRAP);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.heightHint = 16;
		data.widthHint = 10;
		data.verticalAlignment = 0;
		data.horizontalSpan = 1;
		warningIcon.setLayoutData(data);
		warningIcon.setImage(J2EEUIPlugin.getDefault().getImage(J2EEUIPluginIcons.WARNING_TASK));

		Label bulletHeader = new Label(info, SWT.WRAP);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.heightHint = 35;
		data.horizontalSpan = 1;
		bulletHeader.setLayoutData(data);
		bulletHeader.setText(IMigrationWizardConstants.MIGRATION_WIZARD_TEXT_THREE);

		Label firstBullet = new Label(info, SWT.WRAP);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = 13;
		data.horizontalIndent = 20;
		data.heightHint = 30;
		firstBullet.setLayoutData(data);
		firstBullet.setText("-"); //$NON-NLS-1$

		Label bulletOne = new Label(info, SWT.WRAP);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = 425;
		bulletOne.setLayoutData(data);
		bulletOne.setText(IMigrationWizardConstants.MIGRATION_WIZARD_BULLET_ONE);

		Label secondBullet = new Label(info, SWT.WRAP);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = 13;
		data.horizontalIndent = 20;
		data.heightHint = 30;
		secondBullet.setLayoutData(data);
		secondBullet.setText("-"); //$NON-NLS-1$

		Label bulletTwo = new Label(info, SWT.WRAP);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = 425;
		bulletTwo.setLayoutData(data);
		bulletTwo.setText(IMigrationWizardConstants.MIGRATION_WIZARD_BULLET_TWO);

		Label thirdBullet = new Label(info, SWT.WRAP);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = 13;
		data.heightHint = 30;
		data.horizontalIndent = 20;
		thirdBullet.setLayoutData(data);
		thirdBullet.setText("-"); //$NON-NLS-1$

		Label bulletThree = new Label(info, SWT.WRAP);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = 425;
		bulletThree.setLayoutData(data);
		bulletThree.setText(IMigrationWizardConstants.MIGRATION_WIZARD_BULLET_THREE);
	}

	private void createFinishingRemarks(Composite composite) {
		Composite info = new Composite(composite, SWT.NULL);
		info.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		info.setLayout(layout);

		Label onceComplete = new Label(info, SWT.WRAP);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = 470;
		onceComplete.setLayoutData(data);
		onceComplete.setText(IMigrationWizardConstants.MIGRATION_WIZARD_TEXT_FOUR);

		setSpacer(info, 10);

		Label timeMessage = new Label(info, SWT.WRAP);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = 450;
		timeMessage.setLayoutData(data);
		timeMessage.setText(IMigrationWizardConstants.MIGRATION_WIZARD_TEXT_FIVE);
	}



	private void setSpacer(Composite composite, int height) {
		Label space = new Label(composite, SWT.NONE);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.heightHint = height;
		space.setLayoutData(data);
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.wizard.J2EEWizardPage#validateControls()
	 */
	protected void validateControls() {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		Composite comp = createTopLevelComposite(parent);
		setControl(comp);
	}

}