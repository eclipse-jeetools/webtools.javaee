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

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.migration.ComposedMigrationConfig;
import org.eclipse.jst.j2ee.internal.migration.J2EEMigrationConfig;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetDataModel;
import org.eclipse.jst.j2ee.internal.wizard.ServerTargetGroup;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.help.WorkbenchHelp;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.wst.internal.common.ui.WTPWizardPage;

/**
 *  
 */
public abstract class GeneralModuleMigrationWizardPage extends WTPWizardPage implements ICheckStateListener {
	protected ServerTargetGroup serverTargetGroup;
	protected Label j2eeVersionLabel;
	protected Combo j2eeVersionCombo;

	protected class ProjectContentProvider implements IStructuredContentProvider {
		public ProjectContentProvider() {
			super();
		}

		public void dispose() {
		}

		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof List)
				return ((List) inputElement).toArray();
			return new Object[0];
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

	private static final Integer PAGE_OK = new Integer(2);
	protected CheckboxTableViewer checkBoxViewer;
	protected Button cmpMigrationButton, addLocalClient, migrateProject, selectAll, deselectAll, migrateProjectStructure;
	protected ComposedMigrationConfig composedConfig;
	protected Text selectStatus;
	protected int totalModules = 0;

	/**
	 * Constructor for EJBModuleMigrationWizard.
	 * 
	 * @param pageName
	 */
	public GeneralModuleMigrationWizardPage(String pageName, ComposedMigrationConfig config) {
		super(config, pageName);
		composedConfig = config;
	}

	protected void addListeners() {
		checkBoxViewer.getControl().addListener(SWT.Selection, this);
		selectAll.addListener(SWT.Selection, this);
		deselectAll.addListener(SWT.Selection, this);
		migrateProject.addListener(SWT.Selection, this);
		migrateProjectStructure.addListener(SWT.Selection, this);
	}

	/**
	 * Method changeMigrateProjectSelection.
	 */
	private void changeMigrateProjectSelection(boolean migrate) {
		List configs = getConfigs();
		for (int i = 0; i < configs.size(); i++) {
			((J2EEMigrationConfig) configs.get(i)).setMigrateVersion(migrate);
		}
	}

	/**
	 * Method changeMigrateProjectSelection.
	 */
	private void changeMigrateProjectStructureSelection(boolean migrate) {
		List configs = getConfigs();
		for (int i = 0; i < configs.size(); i++) {
			((J2EEMigrationConfig) configs.get(i)).setMigrateProjectStructure(migrate);
		}
	}

	/**
	 * @see org.eclipse.jface.viewers.ICheckStateListener#checkStateChanged(CheckStateChangedEvent)
	 */
	public void checkStateChanged(CheckStateChangedEvent event) {
		if (event.getElement() == null)
			return;
		((J2EEMigrationConfig) event.getElement()).setIsSelected(event.getChecked());
	}

	protected void createButtons(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
		selectAll = new Button(composite, SWT.NONE);
		selectAll.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		selectAll.setText(IMigrationWizardConstants.SELECT_ALL);
		deselectAll = new Button(composite, SWT.NONE);
		deselectAll.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		deselectAll.setText(IMigrationWizardConstants.DESELECT_ALL);
	}

	protected void createEARButtons(Composite composite) {
		migrateProjectStructure = new Button(composite, SWT.CHECK);
		migrateProjectStructure.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		migrateProjectStructure.setText(IMigrationWizardConstants.MIGRATE_PROJECT_STRUCTURE_);
		synchHelper.synchCheckbox(migrateProjectStructure, J2EEMigrationConfig.MIGRATE_PROJECT_STRUCTURE, null);
		migrateProject = new Button(composite, SWT.CHECK);
		migrateProject.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		migrateProject.setText(IMigrationWizardConstants.EAR_MIGRATE_J2EE_VERSION);
		synchHelper.synchCheckbox(migrateProject, J2EEMigrationConfig.MIGRATE_VERSION, null);
		if (!earMigrationSelected())
			createJ2EEVersionComposite(composite);
	}

	/**
	 * @param composite
	 */
	protected void createJ2EEVersionComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout(3, false);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalIndent = 15;
		composite.setLayout(layout);
		composite.setLayoutData(data);
		j2eeVersionLabel = new Label(composite, SWT.NULL);
		j2eeVersionLabel.setText(IMigrationWizardConstants.J2EE_VERSION_LABEL);
		j2eeVersionCombo = new Combo(composite, SWT.BORDER | SWT.READ_ONLY);
		data = new GridData(GridData.FILL_HORIZONTAL);
		j2eeVersionCombo.setLayoutData(data);
		Control[] deps = new Control[]{j2eeVersionLabel};
		synchHelper.synchCombo(j2eeVersionCombo, J2EEMigrationConfig.J2EE_VERSIONS_LBL, J2EEMigrationConfig.J2EE_MIGRATION_VERSION, deps);
		new Label(composite, SWT.NULL); //empty label
		serverTargetGroup = new ServerTargetGroup(composite, SWT.NULL, getServerTargetDataModel());
	}

	/**
	 * @return
	 */
	private boolean earMigrationSelected() {
		IProject project = composedConfig.getTargetProject();
		if (project != null && EARNatureRuntime.hasRuntime(project))
			return true;
		return false;
	}

	/**
	 * @return
	 */
	protected ServerTargetDataModel getServerTargetDataModel() {
		return ((ComposedMigrationConfig) model).getServerTargetDataModel();
	}

	protected void createNumberSelected(Composite composite) {
		selectStatus = new Text(composite, SWT.WRAP | SWT.READ_ONLY);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		selectStatus.setLayoutData(data);
		updateSelectedStatus();
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
		WorkbenchHelp.setHelp(composite, getContextId());
		createViewer(composite);
		createButtons(composite);
		createNumberSelected(composite);
		setSpacer(composite);
		createEARButtons(composite);
		setSpacer(composite);
		setSpacer(composite);
		setSpacer(composite);
		setSpacer(composite);
		setSpacer(composite);
		setSpacer(composite);
		return composite;
	}

	/**
	 *  
	 */
	protected void createViewer(Composite parent) {
		Label ejbLabel = new Label(parent, SWT.NULL);
		ejbLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		ejbLabel.setText(IMigrationWizardConstants.EJB_MIGRATE_AVAILABLE_PROJECTS);
		checkBoxViewer = CheckboxTableViewer.newCheckList(parent, SWT.CHECK | SWT.BORDER | SWT.V_SCROLL);
		GridData data = new GridData(GridData.GRAB_HORIZONTAL | GridData.FILL_BOTH);
		data.heightHint = 90;
		checkBoxViewer.getTable().setLayoutData(data);
		checkBoxViewer.setLabelProvider(new WorkbenchLabelProvider());
		checkBoxViewer.setContentProvider(new ProjectContentProvider());
		checkBoxViewer.addCheckStateListener(this);
	}

	public void enter() {
		List configs = getConfigs();
		setTotalModules(configs.size());
		checkBoxViewer.setInput(configs);
		refreshTreeViewer();
		refreshButtonsSelection();
		handleJ2EEButtonEnablement();
		updateSelectedStatus();
		super.enter();
	}

	protected String formatNumSelected(String aPattern, int selected, int total) {
		return MessageFormat.format(aPattern, new String[]{"" + selected, "" + total}); //$NON-NLS-1$ //$NON-NLS-2$
	}

	protected abstract List getConfigs();

	protected abstract String getContextId();

	public void handleEvent(Event event) {
		if (event.widget == checkBoxViewer.getControl()) {
			updateSelectedStatus();
		} else if (event.widget == selectAll) {
			checkBoxViewer.setAllChecked(true);
			setAllChecked(event, true);
			updateSelectedStatus();
		} else if (event.widget == deselectAll) {
			checkBoxViewer.setAllChecked(false);
			setAllChecked(event, false);
			updateSelectedStatus();
		} else if (event.widget == migrateProject) {
			changeMigrateProjectSelection(migrateProject.getSelection());
		} else if (event.widget == migrateProjectStructure) {
			changeMigrateProjectStructureSelection(migrateProjectStructure.getSelection());
		}
		validatePage();
		super.handleEvent(event);
	}

	/**
	 * Method handleJ2EEButtonEnablement.
	 */
	private void handleJ2EEButtonEnablement() {
		List configs = getConfigs();
		if (configs.isEmpty())
			return;
		if (composedConfig != null && composedConfig.isEAR()) {
			if (!((J2EEMigrationConfig) configs.get(0)).shouldMigrateJ2EEVersion())
				migrateProject.setEnabled(false);
			else
				migrateProject.setEnabled(true);
			if (!((J2EEMigrationConfig) configs.get(0)).shouldMigrateProjectStructure())
				migrateProjectStructure.setEnabled(false);
			else
				migrateProjectStructure.setEnabled(true);
		}
	}

	/**
	 * Method refreshButtonsSelection.
	 */
	protected void refreshButtonsSelection() {
		List configs = getConfigs();
		if (configs.isEmpty())
			return;
		migrateProject.setSelection(((J2EEMigrationConfig) configs.get(0)).shouldMigrateJ2EEVersion());
	}

	protected void refreshTreeViewer() {
		List configs = getConfigs();
		for (int i = 0; i < configs.size(); i++) {
			J2EEMigrationConfig config = (J2EEMigrationConfig) configs.get(i);
			checkBoxViewer.setChecked(config, config.isSelected());
		}
	}

	/**
	 * @param event
	 * @param b
	 */
	private void setAllChecked(Event event, boolean b) {
		List configs = getConfigs();
		for (int i = 0; i < configs.size(); i++) {
			J2EEMigrationConfig config = (J2EEMigrationConfig) configs.get(i);
			config.setIsSelected(b);
		}
	}

	public void setSpacer(Composite composite) {
		Label space = new Label(composite, SWT.NONE);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		space.setLayoutData(data);
	}

	public void setTotalModules(int aTotalModules) {
		totalModules = aTotalModules;
	}

	protected void updateSelectedStatus() {
		int numSelected = (Arrays.asList(checkBoxViewer.getCheckedElements())).size();
		selectStatus.setText(formatNumSelected(IMigrationWizardConstants.EJB_MIGRATE_SELECTED_PROJECTS, numSelected, totalModules));
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.wizard.J2EEWizardPage#validateControls()
	 */
	protected void validateControls() {
		setOKStatus(PAGE_OK);
		if ((Arrays.asList(checkBoxViewer.getCheckedElements())).size() == 0 && !composedConfig.isEAR())
			this.setErrorStatus(PAGE_OK, IMigrationWizardConstants.EJB_MIGRATE_ERROR_NO_PROJECT_SELECTED);
		super.validatePage();
	}
}