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
 * Created on Dec 3, 2003
 * 
 * To change the template for this generated file go to Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.wizard;

import java.util.ArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jst.j2ee.application.operations.J2EEImportDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEProjectCreationDataModel;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
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
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModelEvent;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModelListener;
import org.eclipse.wst.common.frameworks.internal.ui.WTPDataModelSynchHelper;
import org.eclipse.wst.common.frameworks.internal.ui.WTPWizard;
import org.eclipse.wst.common.frameworks.internal.ui.WTPWizardPage;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel;

/**
 * @author cbridgha
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public abstract class J2EEImportPage extends WTPWizardPage {

	private Combo projectCombo;
	private Combo fileNameCombo;
	private static final String STORE_LABEL = "J2EE_IMPORT_"; //$NON-NLS-1$
	private static final int SIZING_TEXT_FIELD_WIDTH = 305;
	protected static final String defBrowseButtonLabel = J2EEUIMessages.getResourceString(J2EEUIMessages.BROWSE_LABEL); //$NON-NLS-1$
	private static final String defNewButtonLabel = J2EEUIMessages.getResourceString(J2EEUIMessages.NEW_THREE_DOTS_E); //$NON-NLS-1$

	/**
	 * @param model
	 * @param pageName
	 */
	public J2EEImportPage(WTPOperationDataModel model, String pageName) {
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
		createOverwriteComposite(10, 20, composite, synchHelper);
		restoreWidgetValues();
		return composite;
	}

	/**
	 * @param composite
	 */
	protected void createProjectNameComposite(Composite parent) {

		Label projectLabel = new Label(parent, SWT.NONE);

		projectLabel.setText(getProjectImportLabel());
		projectLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));

		// setting up combo
		projectCombo = new Combo(parent, SWT.SINGLE | SWT.BORDER);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = SIZING_TEXT_FIELD_WIDTH;
		projectCombo.setLayoutData(data);

		// setting up button
		Button newProjectButton = new Button(parent, SWT.PUSH);
		newProjectButton.setText(defNewButtonLabel); //$NON-NLS-1$
		newProjectButton.setLayoutData((new GridData(GridData.FILL_HORIZONTAL)));
		newProjectButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleNewProjectButtonPressed();
			}
		});
		newProjectButton.setEnabled(true);

		synchHelper.synchCombo(projectCombo, J2EEImportDataModel.PROJECT_NAME, J2EEImportDataModel.PROJECT_NAME, new Control[]{projectLabel, newProjectButton});
	}

	/**
	 *  
	 */
	protected void handleNewProjectButtonPressed() {
		J2EEProjectCreationDataModel dm = getNewProjectCreationDataModel();

		WizardDialog dialog = new WizardDialog(projectCombo.getShell(), getNewProjectWizard(dm));
		dialog.create();
		dialog.getShell().setSize(500, 500);
		if (dialog.open() == Window.OK) {
			model.notifyValidValuesChange(J2EEImportDataModel.PROJECT_NAME);
			String projectName = dm.getStringProperty(EditModelOperationDataModel.PROJECT_NAME);
			model.setProperty(J2EEImportDataModel.PROJECT_NAME, projectName);
			model.setBooleanProperty(J2EEImportDataModel.OVERWRITE_PROJECT, true);
		}
	}

	protected J2EEProjectCreationDataModel getNewProjectCreationDataModel() {
		return null;
	}

	protected WTPWizard getNewProjectWizard(J2EEProjectCreationDataModel aModel) {
		return null;
	}

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

		synchHelper.synchCombo(fileNameCombo, J2EEImportDataModel.FILE_NAME, J2EEImportDataModel.FILE_NAME, new Control[]{fileLabel, browseButton});
	}

	/**
	 *  
	 */
	protected void handleFileBrowseButtonPressed() {

		FileDialog dialog = new FileDialog(fileNameCombo.getShell());
		dialog.setFilterExtensions(getFilterExpression());
		String filename = dialog.open();
		if (filename != null)
			model.setProperty(J2EEImportDataModel.FILE_NAME, filename);
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
			model.setProperty(J2EEImportDataModel.FILE_SELECTION_HISTORY, sourceNames);
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
			//rip out any empty filenames and trim length to 5
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

			//sourceNames = addToHistory(sourceNames,
			// getJ2EEImportDataModel().getStringProperty(J2EEImportDataModel.FILE_NAME));
			settings.put(STORE_LABEL + getFileNamesStoreID(), sourceNames);
		}
	}

	/**
	 * @param composite
	 */
	protected static Button[] createOverwriteComposite(int indent1, int indent2, Composite parent, WTPDataModelSynchHelper sHelper) {
		final Button overwrite = new Button(parent, SWT.CHECK);
		IProject project = ((J2EEImportDataModel) sHelper.getDataModel()).getProject();
		overwrite.setEnabled(null != project && project.exists());
		overwrite.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.OVERWRITE_RESOURCES));
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalIndent = indent1;
		gd.horizontalSpan = 3;
		overwrite.setLayoutData(gd);
		sHelper.synchCheckbox(overwrite, J2EEImportDataModel.OVERWRITE_PROJECT, null);

		final Button delete = new Button(parent, SWT.CHECK);
		delete.setEnabled(overwrite.getEnabled() && sHelper.getDataModel().getBooleanProperty(J2EEImportDataModel.OVERWRITE_PROJECT));
		delete.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.DELETE_PROJECT));
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalIndent = indent2;
		gd.horizontalSpan = 3;
		delete.setLayoutData(gd);
		sHelper.synchCheckbox(delete, J2EEImportDataModel.DELETE_BEFORE_OVERWRITE_PROJECT, null);

		sHelper.getDataModel().addListener(new WTPOperationDataModelListener() {
			public void propertyChanged(WTPOperationDataModelEvent event) {
				if (event.getPropertyName().equals(J2EEImportDataModel.OVERWRITE_PROJECT)) {
					delete.setEnabled(((Boolean) event.getNewValue()).booleanValue());
				} else if (event.getPropertyName().equals(J2EEImportDataModel.PROJECT_NAME)) {
					IProject aProject = ((J2EEImportDataModel) event.getDataModel()).getProject();
					boolean exists = null != aProject && aProject.exists();
					overwrite.setEnabled(exists);
					delete.setEnabled(exists && event.getDataModel().getBooleanProperty(J2EEImportDataModel.OVERWRITE_PROJECT));
				}
			}
		});
		return new Button[]{overwrite, delete};
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

	protected Button importEARButton;

}