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
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.j2ee.internal.earcreation.EAREditModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.migration.ComposedMigrationConfig;
import org.eclipse.jst.j2ee.internal.migration.J2EEMigrationConfig;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIContextIds;
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
import org.eclipse.wst.common.framework.operation.WTPOperationDataModelEvent;
import org.eclipse.wst.common.framework.operation.WTPOperationDataModelListener;
import org.eclipse.wst.internal.common.ui.WTPWizardPage;

public class EARMigrationWizardPage extends WTPWizardPage {
	protected Text description;
	protected Label earLabel, j2eeVersionLabel, targetRuntimeLabel;
	protected ComposedMigrationConfig earMigrationConfig;
	protected Button j2eeMigrationButton, moduleMigrationButton, projectStructureButton;
	protected Combo j2eeVersionCombo;
	protected TreeViewer projectViewer;
	protected ServerTargetGroup serverTargetGroup;

	protected class ModulesContentProvider implements ITreeContentProvider {

		public void dispose() {
		}

		public Object[] getChildren(Object parentElement) {
			if (parentElement instanceof List) {
				return ((List) parentElement).toArray();
			} else if (parentElement instanceof IProject) {
				EARNatureRuntime runtime = EARNatureRuntime.getRuntime((IProject) parentElement);
				if (runtime != null)
					return getChildrenForEAR(runtime);
			}
			return null;
		}

		private Object[] getChildrenForEAR(EARNatureRuntime runtime) {
			EAREditModel aModel = runtime.getEarEditModelForRead(this);
			try {
				if (aModel != null) {
					Set projects = aModel.getModuleMappedModuleProjects();
					if (projects != null)
						return projects.toArray();
				}
			} finally {
				if (aModel != null)
					aModel.releaseAccess(this);
			}
			return null;
		}

		public Object[] getElements(Object inputElement) {
			return getChildren(inputElement);
		}

		public Object getParent(Object element) {
			return null;
		}

		public boolean hasChildren(Object element) {
			return (element instanceof List) || ((element instanceof IProject) && EARNatureRuntime.hasRuntime((IProject) element));
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

	}

	/**
	 * Constructor for EARMigrationWizard.
	 * 
	 * @param pageName
	 */
	public EARMigrationWizardPage(String pageName, ComposedMigrationConfig migrationConfig) {
		super(migrationConfig, pageName);
		setTitle(IMigrationWizardConstants.EAR_MIGRATION_WIZARD_TITLE);
		setDescription(IMigrationWizardConstants.EAR_MIGRATION_WIZARD_DESCRIPTION);
		loadData();

	}

	protected void addListeners() {
		projectViewer.getTree().addListener(SWT.Selection, this);
		j2eeMigrationButton.addListener(SWT.Selection, this);
		moduleMigrationButton.addListener(SWT.Selection, this);
		projectStructureButton.addListener(SWT.Selection, this);
	}

	protected void createEARButtons(Composite composite) {

		projectStructureButton = new Button(composite, SWT.CHECK);
		projectStructureButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		projectStructureButton.setText(IMigrationWizardConstants.MIGRATE_PROJECT_STRUCTURE_);
		synchHelper.synchCheckbox(projectStructureButton, J2EEMigrationConfig.MIGRATE_PROJECT_STRUCTURE, null);

		j2eeMigrationButton = new Button(composite, SWT.CHECK);
		j2eeMigrationButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		j2eeMigrationButton.setText(IMigrationWizardConstants.EAR_MIGRATE_J2EE_VERSION);
		synchHelper.synchCheckbox(j2eeMigrationButton, J2EEMigrationConfig.MIGRATE_VERSION, null);
		synchHelper.getDataModel().addListener(new WTPOperationDataModelListener() {
			public void propertyChanged(WTPOperationDataModelEvent event) {
				if (event.getPropertyName().equals(J2EEMigrationConfig.MIGRATE_VERSION)) {
					boolean val = ((Boolean) model.getProperty(J2EEMigrationConfig.MIGRATE_VERSION)).booleanValue();
					if (val) {
						j2eeVersionCombo.setEnabled(true);
						serverTargetGroup.getTargetServerCombo().setEnabled(true);
						serverTargetGroup.getNewTargetServerButton().setEnabled(true);
					} else {
						j2eeVersionCombo.setEnabled(false);
						serverTargetGroup.getTargetServerCombo().setEnabled(false);
						serverTargetGroup.getNewTargetServerButton().setEnabled(false);
					}

				}
			}
		});

		createJ2EEVersionComposite(composite);

		Control[] deps = new Control[]{j2eeVersionCombo, serverTargetGroup.getTargetServerCombo()};
		synchHelper.synchCheckbox(j2eeMigrationButton, J2EEMigrationConfig.MIGRATE_VERSION, deps);

		moduleMigrationButton = new Button(composite, SWT.CHECK);
		moduleMigrationButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		moduleMigrationButton.setText(IMigrationWizardConstants.EAR_MIGRATE_MODULES);
		moduleMigrationButton.setSelection(true);
		synchHelper.synchCheckbox(moduleMigrationButton, J2EEMigrationConfig.IS_SELECTED, null);

		Label moduleMigrationDesc = new Label(composite, SWT.WRAP);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.heightHint = 60;
		data.horizontalIndent = 15;
		data.widthHint = 350;
		moduleMigrationDesc.setLayoutData(data);
		moduleMigrationDesc.setText(IMigrationWizardConstants.EAR_MIGRATE_MODULES_DESC);

		Label finalInformation = new Label(composite, SWT.WRAP);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.heightHint = 50;
		data.widthHint = 400;
		finalInformation.setLayoutData(data);
		finalInformation.setText(IMigrationWizardConstants.EAR_MIGRATE_FINAL_DESCRIPTION);
	}

	/**
	 * @param composite
	 */
	private void createJ2EEVersionComposite(Composite parent) {
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

		new Label(composite, SWT.NULL);

		serverTargetGroup = new ServerTargetGroup(composite, SWT.NULL, getServerTargetDataModel());
	}

	/**
	 * @return
	 */
	protected ServerTargetDataModel getServerTargetDataModel() {
		return ((ComposedMigrationConfig) model).getServerTargetDataModel();
	}

	protected void createEARLabel(Composite composite) {
		earLabel = new Label(composite, SWT.NONE);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		earLabel.setLayoutData(data);
	}

	protected void createEARViewer(Composite composite) {
		GridData data = new GridData(GridData.FILL_BOTH);
		projectViewer = new TreeViewer(composite, SWT.BORDER);
		data.heightHint = 100;
		projectViewer.getTree().setLayoutData(data);
		projectViewer.setLabelProvider(new WorkbenchLabelProvider());
		projectViewer.setContentProvider(new ModulesContentProvider());
		projectViewer.setAutoExpandLevel(2);
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.wizard.J2EEWizardPage#createTopLevelComposite(Composite)
	 */
	protected Composite createTopLevelComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		WorkbenchHelp.setHelp(composite, J2EEUIContextIds.MIGRATION_WIZARD_EAR);

		createEARLabel(composite);
		createEARViewer(composite);
		setSpacer(composite);
		createEARButtons(composite);
		addListeners();

		return composite;
	}

	public void enter() {
		super.enter();
		earMigrationConfig = ((J2EEMigrationWizard) getWizard()).getMigrationConfig();
		if (earMigrationConfig != null) {
			earLabel.setText(IMigrationWizardConstants.EAR_MIGRATION_PROJECT_TITLE + " " + earMigrationConfig.getTargetProject().getName()); //$NON-NLS-1$
			projectViewer.setInput(Collections.singletonList(earMigrationConfig.getTargetProject()));
		}
	}

	public void exit() {
		super.exit();
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

	public void handleEvent(Event event) {
		if (event.widget == projectViewer.getTree()) {
		} else if (event.widget == j2eeMigrationButton) {
			earMigrationConfig.setMigrateVersionForAll(j2eeMigrationButton.getSelection());
		} else if (event.widget == moduleMigrationButton) {
			earMigrationConfig.setAllChildrenSelected(moduleMigrationButton.getSelection());
		} else if (event.widget == projectStructureButton) {
			earMigrationConfig.setMigrateStructureForAll(projectStructureButton.getSelection());
		}
		super.handleEvent(event);
	}

	private void loadData() {
	}

	private void setSpacer(Composite composite) {
		Label space = new Label(composite, SWT.NONE);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		space.setLayoutData(data);
	}


	protected void validateControls() {
	}


}