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
package org.eclipse.jst.j2ee.internal.wizard;

import java.util.ArrayList;

import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.project.datamodel.properties.IFlexibleJavaProjectCreationDataModelProperties;
import org.eclipse.jst.j2ee.project.datamodel.properties.IJ2EEProjectServerTargetDataModelProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelWizardPage;
import org.eclipse.wst.server.ui.ServerUIUtil;

public abstract class J2EEImportPage extends DataModelWizardPage {

	private Combo componentCombo;
	private Combo fileNameCombo;
	private static final String STORE_LABEL = "J2EE_IMPORT_"; //$NON-NLS-1$
	private static final int SIZING_TEXT_FIELD_WIDTH = 305;
	protected static final String defBrowseButtonLabel = J2EEUIMessages.getResourceString(J2EEUIMessages.BROWSE_LABEL); //$NON-NLS-1$
//	private static final String defNewButtonLabel = J2EEUIMessages.getResourceString(J2EEUIMessages.NEW_THREE_DOTS_E); //$NON-NLS-1$

	/**
	 * @param model
	 * @param pageName
	 */
	public J2EEImportPage(IDataModel model, String pageName) {
		super(model, pageName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizardPage#getValidationPropertyNames()
	 */
	protected String[] getValidationPropertyNames() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizardPage#createTopLevelComposite(org.eclipse.swt.widgets.Composite)
	 */
	protected Composite createTopLevelComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout(3, false);
		composite.setLayout(layout);
		createFileNameComposite(composite);
		createProjectNameComposite(composite);
		restoreWidgetValues();
		return composite;
	}

	/**
	 * @param composite
	 */
	protected void createProjectNameComposite(Composite parent) {
//		if (FlexibleJavaProjectPreferenceUtil.getMultipleModulesPerProjectProp()) {
//			new NewModuleDataModelGroup(parent, getDataModel(), synchHelper);
//		} else {
			Label componentLabel = new Label(parent, SWT.NONE);

			componentLabel.setText(getProjectImportLabel());
			componentLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));

			// setting up combo
			componentCombo = new Combo(parent, SWT.SINGLE | SWT.BORDER);
			GridData data = new GridData(GridData.FILL_HORIZONTAL);
			data.widthHint = SIZING_TEXT_FIELD_WIDTH;
			componentCombo.setLayoutData(data);

			// // setting up button
			// Button newProjectButton = new Button(parent, SWT.PUSH);
			// newProjectButton.setText(defNewButtonLabel); //$NON-NLS-1$
			// newProjectButton.setLayoutData((new GridData(GridData.FILL_HORIZONTAL)));
			// newProjectButton.addSelectionListener(new SelectionAdapter() {
			// public void widgetSelected(SelectionEvent e) {
			// // handleNewProjectButtonPressed();
			// }
			// });
			// newProjectButton.setEnabled(true);
			synchHelper.synchCombo(componentCombo, IJ2EEComponentImportDataModelProperties.COMPONENT_NAME, new Control[]{componentLabel});
			new Label(parent, SWT.NULL);
			createServerTargetComposite(parent);
		//}
	}

	protected void createServerTargetComposite(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.TARGET_RUNTIME_LBL));
		Combo serverTargetCombo = new Combo(parent, SWT.BORDER | SWT.READ_ONLY);
		serverTargetCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		Button newServerTargetButton = new Button(parent, SWT.NONE);
		newServerTargetButton.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.NEW_THREE_DOTS_E));
		newServerTargetButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		newServerTargetButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				//FlexibleProjectCreationWizardPage.launchNewRuntimeWizard(getShell(), model);
				launchNewRuntimeWizard(getShell(), model);
			}
		});
		Control[] deps = new Control[]{label, newServerTargetButton};
		synchHelper.synchCombo(serverTargetCombo, IJ2EEProjectServerTargetDataModelProperties.RUNTIME_TARGET_ID, deps);
		if (serverTargetCombo.getVisibleItemCount() != 0)
			serverTargetCombo.select(0);
	}

	/**
	 * 
	 */
	// protected void handleNewProjectButtonPressed() {
	// J2EEComponentCreationDataModel dm = getNewProjectCreationDataModel();
	//
	// WizardDialog dialog = new WizardDialog(projectCombo.getShell(), getNewProjectWizard(dm));
	// dialog.create();
	// dialog.getShell().setSize(500, 500);
	// if (dialog.open() == Window.OK) {
	// model.notifyValidValuesChange(J2EEArtifactImportDataModel.PROJECT_NAME);
	// String projectName = dm.getStringProperty(EditModelOperationDataModel.PROJECT_NAME);
	// model.setProperty(J2EEArtifactImportDataModel.PROJECT_NAME, projectName);
	// model.setBooleanProperty(J2EEArtifactImportDataModel.OVERWRITE_PROJECT, true);
	// }
	// }
	// protected J2EEComponentCreationDataModel getNewProjectCreationDataModel() {
	// return null;
	// }
	//
	// protected WTPWizard getNewProjectWizard(J2EEComponentCreationDataModel aModel) {
	// return null;
	// }
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.WizardPage#getDialogSettings()
	 */
	protected IDialogSettings getDialogSettings() {
		return J2EEUIPlugin.getDefault().getDialogSettings();
	}

	/**
	 * @return
	 */
	protected String getProjectImportLabel() {
		return null;
	}

	/**
	 * @param composite
	 */
	protected void createFileNameComposite(Composite parent) {
		Label fileLabel = new Label(parent, SWT.NONE);
		fileLabel.setText(getFileImportLabel());

		// setup combo
		fileNameCombo = new Combo(parent, SWT.SINGLE | SWT.BORDER);
		fileNameCombo.setLayoutData((new GridData(GridData.FILL_HORIZONTAL)));

		// setup browse button
		Button browseButton = new Button(parent, SWT.PUSH);
		browseButton.setText(defBrowseButtonLabel);
		browseButton.setLayoutData((new GridData(GridData.FILL_HORIZONTAL)));
		browseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleFileBrowseButtonPressed();
			}
		});
		browseButton.setEnabled(true);

		synchHelper.synchCombo(fileNameCombo, IJ2EEComponentImportDataModelProperties.FILE_NAME, new Control[]{fileLabel, browseButton});
	}

	/**
	 * 
	 */
	protected void handleFileBrowseButtonPressed() {

		FileDialog dialog = new FileDialog(fileNameCombo.getShell());
		dialog.setFilterExtensions(getFilterExpression());
		String filename = dialog.open();
		if (filename != null)
			model.setProperty(IJ2EEComponentImportDataModelProperties.FILE_NAME, filename);
	}

	/**
	 * @return
	 */
	protected String[] getFilterExpression() {
		return new String[0];
	}

	protected void restoreWidgetValues() {

		IDialogSettings settings = getDialogSettings();
		if (settings != null) {
			String[] sourceNames = settings.getArray(STORE_LABEL + getFileNamesStoreID());
			if (sourceNames == null)
				return; // ie.- no settings stored
			for (int i = 0; i < sourceNames.length; i++) {
				if (sourceNames[i] == null)
					sourceNames[i] = ""; //$NON-NLS-1$
			}
			fileNameCombo.setItems(sourceNames);
		}
	}

	public void storeDefaultSettings() {
		IDialogSettings settings = getDialogSettings();
		if (settings != null) {
			// update source names history
			String[] sourceNames = settings.getArray(STORE_LABEL + getFileNamesStoreID());
			if (sourceNames == null) {
				sourceNames = new String[0];
			}
			// rip out any empty filenames and trim length to 5
			ArrayList newNames = new ArrayList();
			for (int i = 0; i < sourceNames.length && i < 5; i++) {
				if (sourceNames[i].trim().length() > 0) {
					newNames.add(sourceNames[i]);
				}
			}
			String text = fileNameCombo.getText();
			newNames.remove(text);
			newNames.add(0, text);
			sourceNames = new String[newNames.size()];
			newNames.toArray(sourceNames);

			// sourceNames = addToHistory(sourceNames,
			// getJ2EEImportDataModel().getStringProperty(J2EEImportDataModel.FILE_NAME));
			settings.put(STORE_LABEL + getFileNamesStoreID(), sourceNames);
		}
	}

	/**
	 * @return
	 */
	protected String getFileNamesStoreID() {
		return null;
	}

	/**
	 * Must override
	 */
	protected String getFileImportLabel() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizardPage#enter()
	 */
	protected void enter() {
		super.enter();
	}

	private boolean launchNewRuntimeWizard(Shell shell, IDataModel model) {
		DataModelPropertyDescriptor[] preAdditionDescriptors = model.getValidPropertyDescriptors(IFlexibleJavaProjectCreationDataModelProperties.RUNTIME_TARGET_ID);
		boolean isOK = ServerUIUtil.showNewRuntimeWizard(shell, "", "");  //$NON-NLS-1$  //$NON-NLS-2$
		if (isOK && model != null) {
			model.notifyPropertyChange(IFlexibleJavaProjectCreationDataModelProperties.RUNTIME_TARGET_ID, IDataModel.VALID_VALUES_CHG);
			DataModelPropertyDescriptor[] postAdditionDescriptors = model.getValidPropertyDescriptors(IFlexibleJavaProjectCreationDataModelProperties.RUNTIME_TARGET_ID);
			Object[] preAddition = new Object[preAdditionDescriptors.length];
			for (int i = 0; i < preAddition.length; i++) {
				preAddition[i] = preAdditionDescriptors[i].getPropertyValue();
			}
			Object[] postAddition = new Object[postAdditionDescriptors.length];
			for (int i = 0; i < postAddition.length; i++) {
				postAddition[i] = postAdditionDescriptors[i].getPropertyValue();
			}
			Object newAddition = ProjectUtilities.getNewObject(preAddition, postAddition);

            model.notifyPropertyChange(IFlexibleJavaProjectCreationDataModelProperties.RUNTIME_TARGET_ID, IDataModel.VALID_VALUES_CHG);
			if (newAddition != null)
				model.setProperty(IFlexibleJavaProjectCreationDataModelProperties.RUNTIME_TARGET_ID, newAddition);
		}
		return isOK;
	}

}
