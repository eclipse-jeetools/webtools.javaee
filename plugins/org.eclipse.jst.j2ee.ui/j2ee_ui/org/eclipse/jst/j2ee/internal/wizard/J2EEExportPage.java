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
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.wizard;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.application.operations.EARExportDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEExportDataModel;
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
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.help.WorkbenchHelp;
import org.eclipse.wst.common.frameworks.internal.ui.WTPWizardPage;

import com.ibm.wtp.emf.workbench.ProjectUtilities;
import com.ibm.wtp.emf.workbench.nature.EMFNature;

/**
 * @author cbridgha
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public abstract class J2EEExportPage extends WTPWizardPage {

	public static boolean isWindows = SWT.getPlatform().toLowerCase().startsWith("win"); //$NON-NLS-1$

	protected IStructuredSelection currentResourceSelection;
	private static final int SIZING_TEXT_FIELD_WIDTH = 305;
	private static final String STORE_LABEL = "J2EE_EXPORT_"; //$NON-NLS-1$
	private static final String OVERWRITE_LABEL = "OVERWRITE"; //$NON-NLS-1$
	private static final String SOURCE_LABEL = "SOURCE"; //$NON-NLS-1$
	private static final String META_LABEL = "META"; //$NON-NLS-1$
	protected static final String defBrowseButtonLabel = J2EEUIMessages.getResourceString(J2EEUIMessages.BROWSE_LABEL);
	protected String LABEL_DESTINATION = J2EEUIMessages.getResourceString(J2EEUIMessages.J2EE_EXPORT_DESTINATION);
	private Combo resourceNameCombo;
	private Combo destinationNameCombo;
	private Button destinationBrowseButton;
	protected Button overwriteExistingFilesCheckbox;
	private Button sourceFilesCheckbox;

	/**
	 * @param model
	 * @param pageName
	 */
	public J2EEExportPage(J2EEExportDataModel model, String pageName, IStructuredSelection selection) {
		super(model, pageName);
		currentResourceSelection = selection;

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
		WorkbenchHelp.setHelp(composite, getInfopopID());
		GridLayout layout = new GridLayout(1, false);
		composite.setLayout(layout);

		createSourceAndDestinationGroup(composite);
		createOptionsGroup(composite);

		//setupBasedOnInitialSelections();
		setupInfopop(composite);
		return composite;
	}

	/**
	 * @param composite
	 */
	private void createSourceAndDestinationGroup(Composite parent) {

		Composite composite = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout(3, false);
		composite.setLayout(layout);
		createExportProjectGroup(composite);
		createDestinationGroup(composite);

	}

	protected void createOverwriteExistingFilesCheckbox(Composite optionsGroup) {
		//Overwrite checkbox
		overwriteExistingFilesCheckbox = new Button(optionsGroup, SWT.CHECK | SWT.LEFT);
		overwriteExistingFilesCheckbox.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.J2EE_EXPORT_OVERWRITE_CHECKBOX)); //$NON-NLS-1$ = "Overwrite existing files without warning"
		overwriteExistingFilesCheckbox.setEnabled(true);
		synchHelper.synchCheckbox(overwriteExistingFilesCheckbox, J2EEExportDataModel.OVERWRITE_EXISTING, null);
	}

	protected void createSourceFilesCheckbox(Composite optionsGroup) {
		sourceFilesCheckbox = new Button(optionsGroup, SWT.CHECK | SWT.LEFT);
		sourceFilesCheckbox.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.J2EE_EXPORT_SOURCE_CHECKBOX)); //$NON-NLS-1$
		synchHelper.synchCheckbox(sourceFilesCheckbox, J2EEExportDataModel.EXPORT_SOURCE_FILES, null);
	}

	/**
	 * Create the export options specification widgets.
	 * 
	 * @param parent
	 *            org.eclipse.swt.widgets.Composite
	 */
	protected void createOptionsGroup(Composite parent) {

		// options group
		Composite optionsGroup = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout(1, false);
		optionsGroup.setLayout(layout);


		// source files... checkbox
		createSourceFilesCheckbox(optionsGroup);

		// overwrite... checkbox
		createOverwriteExistingFilesCheckbox(optionsGroup);

		// advanced button
		if (shouldShowProjectFilesCheckbox()) {
			createProjectFilesCheckbox(optionsGroup);
		}
	}

	/**
	 * @return
	 */
	protected boolean shouldShowProjectFilesCheckbox() {
		return false;
	}

	protected void createProjectFilesCheckbox(Composite composite) {
	}

	/**
	 * Populates the resource name field based upon the currently-selected resources.
	 */
	protected void setupBasedOnInitialSelections() {

		if (currentResourceSelection.isEmpty() || setupBasedOnRefObjectSelection())
			return; // no setup needed

		java.util.List selections = new ArrayList();
		Iterator aenum = currentResourceSelection.iterator();
		while (aenum.hasNext()) {
			IResource currentResource = (IResource) aenum.next();
			// do not add inaccessible elements
			if (currentResource.isAccessible())
				selections.add(currentResource);
		}
		if (selections.isEmpty())
			return; // setup not needed anymore

		int selectedResourceCount = selections.size();
		if (selectedResourceCount == 1) {
			IResource resource = (IResource) selections.get(0);
			if ((resource instanceof IProject) && checkForNature((IProject) resource)) {
				resourceNameCombo.setText(resource.getName().toString());
			}
		}
	}

	/**
	 * @return
	 */
	protected String getProjectImportLabel() {
		return null;
	}

	/**
	 *  
	 */
	protected void handleDestinationBrowseButtonPressed() {

		FileDialog dialog = new FileDialog(destinationNameCombo.getShell(), SWT.SAVE);
		String fileName = getJ2EEExportDataModel().getStringProperty(J2EEExportDataModel.PROJECT_NAME);
		String[] filters = getFilterExpression();
		if (!isWindows) {
			if (filters.length != 0 && filters[0] != null && filters[0].indexOf('.') != -1) {
				fileName += filters[0].substring(filters[0].indexOf('.'));
			}
		}
		dialog.setFileName(fileName);
		if (isWindows) {
			dialog.setFilterExtensions(filters);
		}
		String filename = dialog.open();
		if (filename != null)
			destinationNameCombo.setText(filename);
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
			destinationNameCombo.setItems(sourceNames);
			boolean overwrite = settings.getBoolean(STORE_LABEL + OVERWRITE_LABEL);
			model.setBooleanProperty(J2EEExportDataModel.OVERWRITE_EXISTING, overwrite);
			boolean includeSource = settings.getBoolean(STORE_LABEL + SOURCE_LABEL);
			model.setBooleanProperty(J2EEExportDataModel.EXPORT_SOURCE_FILES, includeSource);
			if (model.isProperty(EARExportDataModel.INCLUDE_BUILD_PATH_AND_META_FILES)) {
				boolean includeMeta = settings.getBoolean(STORE_LABEL + META_LABEL);
				model.setBooleanProperty(EARExportDataModel.INCLUDE_BUILD_PATH_AND_META_FILES, includeMeta);
			}
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

			String newName = destinationNameCombo.getText();

			//rip out any empty filenames and trim length to 5
			ArrayList newNames = new ArrayList();
			for (int i = 0; i < sourceNames.length && i < 5; i++) {
				if (sourceNames[i].trim().length() > 0 && !newName.equals(sourceNames[i])) {
					newNames.add(sourceNames[i]);
				}
			}
			newNames.add(0, destinationNameCombo.getText());
			sourceNames = new String[newNames.size()];
			newNames.toArray(sourceNames);

			settings.put(STORE_LABEL + getFileNamesStoreID(), sourceNames);
			settings.put(STORE_LABEL + OVERWRITE_LABEL, model.getBooleanProperty(J2EEExportDataModel.OVERWRITE_EXISTING));
			settings.put(STORE_LABEL + SOURCE_LABEL, model.getBooleanProperty(J2EEExportDataModel.EXPORT_SOURCE_FILES));
			if (model.isProperty(EARExportDataModel.INCLUDE_BUILD_PATH_AND_META_FILES)) {
				boolean includeMeta = model.getBooleanProperty(EARExportDataModel.INCLUDE_BUILD_PATH_AND_META_FILES);
				settings.put(STORE_LABEL + META_LABEL, includeMeta);
			}
		}
	}

	/**
	 * Creates the export source resource specification widgets.
	 * 
	 * @param parent
	 *            a <code>Composite</code> that is to be used as the parent of this group's
	 *            collection of visual components
	 * @see org.eclipse.swt.widgets.Composite
	 */
	protected void createExportProjectGroup(Composite parent) {
		//Project label
		Label projectLabel = new Label(parent, SWT.NONE);
		projectLabel.setText(getProjectLabel());
		//Project combo
		resourceNameCombo = new Combo(parent, SWT.SINGLE | SWT.BORDER);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = SIZING_TEXT_FIELD_WIDTH;
		resourceNameCombo.setLayoutData(data);
		synchHelper.synchCombo(resourceNameCombo, J2EEExportDataModel.PROJECT_NAME, J2EEExportDataModel.PROJECT_NAME, null);
		new Label(parent, SWT.NONE);//Pad label
	}

	/**
	 * @return
	 */
	protected abstract String getProjectLabel();

	protected void createDestinationGroup(org.eclipse.swt.widgets.Composite parent) {

		//Destination label
		Label destinationLabel = new Label(parent, SWT.NONE);
		destinationLabel.setText(LABEL_DESTINATION);
		// destination name combo field
		destinationNameCombo = new Combo(parent, SWT.SINGLE | SWT.BORDER);
		destinationNameCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		synchHelper.synchCombo(destinationNameCombo, J2EEExportDataModel.ARCHIVE_DESTINATION, J2EEExportDataModel.ARCHIVE_DESTINATION, null);

		// destination browse button
		destinationBrowseButton = new Button(parent, SWT.PUSH);
		destinationBrowseButton.setText(defBrowseButtonLabel); //$NON-NLS-1$
		destinationBrowseButton.setLayoutData((new GridData(GridData.FILL_HORIZONTAL)));
		destinationBrowseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleDestinationBrowseButtonPressed();
			}
		});
		destinationBrowseButton.setEnabled(true);

	}

	/**
	 *  
	 */
	private J2EEExportDataModel getJ2EEExportDataModel() {
		return (J2EEExportDataModel) model;
	}

	/**
	 * @return
	 */
	protected String getFileNamesStoreID() {
		return getNatureID();
	}

	/**
	 * @return
	 */
	protected String[] getFilterExpression() {
		return new String[0];
	}

	protected boolean checkForNature(IProject project) {
		return EMFNature.hasRuntime(project, getNatureID());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizardPage#enter()
	 */
	protected void enter() {
		super.enter();
		restoreWidgetValues();
	}

	/**
	 * @return
	 */
	protected abstract String getNatureID();

	protected abstract boolean isMetaTypeSupported(Object o);

	/**
	 * Populates the resource name field based upon the currently-selected resources.
	 */
	protected boolean setupBasedOnRefObjectSelection() {

		if (currentResourceSelection.size() != 1)
			return false;

		Object o = currentResourceSelection.getFirstElement();
		if (!isMetaTypeSupported(o))
			return false;

		EObject ref = (EObject) o;
		IResource resource = ProjectUtilities.getProject(ref);
		if (resource != null) {
			resourceNameCombo.setText(resource.getName().toString());
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.WizardPage#getDialogSettings()
	 */
	protected IDialogSettings getDialogSettings() {
		return J2EEUIPlugin.getDefault().getDialogSettings();
	}

}