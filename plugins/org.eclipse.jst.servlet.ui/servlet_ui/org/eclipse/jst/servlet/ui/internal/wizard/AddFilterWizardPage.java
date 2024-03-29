/*******************************************************************************
 * Copyright (c) 2007, 2019 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.servlet.ui.internal.wizard;

import static org.eclipse.jst.j2ee.internal.web.operations.INewFilterClassDataModelProperties.FILTER_MAPPINGS;
import static org.eclipse.jst.j2ee.internal.web.operations.INewFilterClassDataModelProperties.INIT_PARAM;
import static org.eclipse.jst.j2ee.internal.web.operations.INewFilterClassDataModelProperties.ASYNC_SUPPORT;
import static org.eclipse.jst.j2ee.internal.web.operations.INewWebClassDataModelProperties.DESCRIPTION;
import static org.eclipse.jst.j2ee.internal.web.operations.INewWebClassDataModelProperties.DISPLAY_NAME;
import static org.eclipse.jst.j2ee.internal.web.operations.INewWebClassDataModelProperties.USE_EXISTING_CLASS;
import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.ADD_BUTTON_LABEL_A;
import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.ADD_FILTER_WIZARD_PAGE_DESC;
import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.ADD_FILTER_WIZARD_PAGE_TITLE;
import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.DESCRIPTION_LABEL;
import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.DESCRIPTION_TITLE;
import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.EDIT_BUTTON_LABEL_E;
import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.INIT_PARAM_LABEL;
import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.INIT_PARAM_TITLE;
import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.NAME_LABEL;
import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.NAME_TITLE;
import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.NO_WEB_PROJECTS;
import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.REMOVE_BUTTON_LABEL_R;
import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.VALUE_LABEL;
import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.VALUE_TITLE;
import static org.eclipse.wst.common.componentcore.internal.operation.IArtifactEditOperationDataModelProperties.PROJECT_NAME;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jst.j2ee.internal.web.operations.INewServletClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.wizard.StringArrayTableWizardSection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelWizardPage;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

/**
 * Filter Wizard Setting Page
 */
public class AddFilterWizardPage extends DataModelWizardPage {
	
	final static String[] FILTEREXTENSIONS = {"java"}; //$NON-NLS-1$

	private Text displayNameText;

	FilterMappingsArrayTableWizardSection mappingSection;
	private Button asyncSupportCheckBox;

	public AddFilterWizardPage(IDataModel model, String pageName) {
		super(model, pageName);
		setDescription(ADD_FILTER_WIZARD_PAGE_DESC);
		setTitle(ADD_FILTER_WIZARD_PAGE_TITLE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.util.ui.wizard.WTPWizardPage#getValidationPropertyNames()
	 */
	@Override
	protected String[] getValidationPropertyNames() {
		return new String[] { DISPLAY_NAME, 
		        INIT_PARAM, 
		        FILTER_MAPPINGS };
	}

	@Override
	protected Composite createTopLevelComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout());
		GridData data = new GridData(GridData.FILL_BOTH);
		data.widthHint = 300;
		composite.setLayoutData(data);

		createNameDescription(composite);
		
		StringArrayTableWizardSectionCallback callback = new StringArrayTableWizardSectionCallback();
		StringArrayTableWizardSection initSection = new StringArrayTableWizardSection(
				composite, 
				INIT_PARAM_LABEL, 
				INIT_PARAM_TITLE, 
				ADD_BUTTON_LABEL_A, 
				EDIT_BUTTON_LABEL_E, 
				REMOVE_BUTTON_LABEL_R, 
				new String[] { NAME_TITLE, VALUE_TITLE, DESCRIPTION_TITLE }, 
				new String[] { NAME_LABEL, VALUE_LABEL, DESCRIPTION_LABEL }, 
				null,// WebPlugin.getDefault().getImage("initializ_parameter"),
				model, 
				INIT_PARAM);
		initSection.setCallback(callback);
		
		mappingSection = new FilterMappingsArrayTableWizardSection(composite, 
		         model, FILTER_MAPPINGS);

		displayNameText.setFocus();

		createAsyncSupportGroup(composite);		
	
		
		IStatus projectStatus = validateProjectName();
		if (!projectStatus.isOK()) {
			setErrorMessage(projectStatus.getMessage());
			composite.setEnabled(false);
		}
	    Dialog.applyDialogFont(parent);
		return composite;
	}

	protected IStatus validateProjectName() {
		// check for empty
		if (model.getStringProperty(PROJECT_NAME) == null || model.getStringProperty(PROJECT_NAME).trim().length() == 0) {
			return WTPCommonPlugin.createErrorStatus(NO_WEB_PROJECTS);
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	protected void createNameDescription(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		// display name
		Label displayNameLabel = new Label(composite, SWT.LEFT);
		displayNameLabel.setText(NAME_LABEL);
		displayNameLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
		displayNameText = new Text(composite, SWT.SINGLE | SWT.BORDER);
		displayNameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		synchHelper.synchText(displayNameText, DISPLAY_NAME, null);

		// description
		Label descLabel = new Label(composite, SWT.LEFT);
		descLabel.setText(DESCRIPTION_LABEL);
		descLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
		Text descText = new Text(composite, SWT.SINGLE | SWT.BORDER);
		descText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		synchHelper.synchText(descText, DESCRIPTION, null);
	}
	
	
	private void createAsyncSupportGroup(Composite composite){
		asyncSupportCheckBox = new Button(composite, SWT.CHECK);
		asyncSupportCheckBox.setText(IWebWizardConstants.ASYNC_SUPPORT_LABEL);
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		data.horizontalSpan = 3;
		asyncSupportCheckBox.setLayoutData(data);
		synchHelper.synchCheckbox(asyncSupportCheckBox, ASYNC_SUPPORT, null);
	}

	public String getDisplayName() {
		return displayNameText.getText();
	}
	
	@Override
	public boolean canFlipToNextPage() {
		if (model.getBooleanProperty(USE_EXISTING_CLASS))
			return false;
		return super.canFlipToNextPage();
	}
	
	@Override
	protected boolean showValidationErrorsOnEnter() {
		return true;
	}
	
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		String javaEEVersion = model.getStringProperty(INewServletClassDataModelProperties.JAVA_EE_VERSION);
		if("3.0".equals(javaEEVersion) || "3.1".equals(javaEEVersion) || "4.0".equals(javaEEVersion)){ //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$			
			asyncSupportCheckBox.setVisible(true);
		}
		else
			asyncSupportCheckBox.setVisible(false);
	}
	
}
