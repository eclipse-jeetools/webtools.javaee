/*******************************************************************************
 * Copyright (c) 2003, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     David Schneider, david.schneider@unisys.com - [142500] WTP properties pages fonts don't follow Eclipse preferences
 *     Kaloyan Raev, kaloyan.raev@sap.com
 *******************************************************************************/
package org.eclipse.jst.servlet.ui.internal.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jst.j2ee.internal.web.operations.INewServletClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.web.operations.INewWebClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.wizard.StringArrayTableWizardSection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.componentcore.internal.operation.IArtifactEditOperationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelWizardPage;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

/**
 * Servlet Wizard Setting Page
 */
public class AddServletWizardPage extends DataModelWizardPage {
	final static String[] JSPEXTENSIONS = {"jsp"}; //$NON-NLS-1$

	private Text displayNameText;

	private StringArrayTableWizardSection urlSection;

	public AddServletWizardPage(IDataModel model, String pageName) {
		super(model, pageName);
		setDescription(IWebWizardConstants.ADD_SERVLET_WIZARD_PAGE_DESC);
		this.setTitle(IWebWizardConstants.ADD_SERVLET_WIZARD_PAGE_TITLE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.util.ui.wizard.WTPWizardPage#getValidationPropertyNames()
	 */
	protected String[] getValidationPropertyNames() {
		return new String[] { INewWebClassDataModelProperties.DISPLAY_NAME, INewServletClassDataModelProperties.INIT_PARAM, INewServletClassDataModelProperties.URL_MAPPINGS };
	}

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
				IWebWizardConstants.INIT_PARAM_LABEL, 
				IWebWizardConstants.INIT_PARAM_TITLE, 
				IWebWizardConstants.ADD_BUTTON_LABEL, 
				IWebWizardConstants.EDIT_BUTTON_LABEL, 
				IWebWizardConstants.REMOVE_BUTTON_LABEL, 
				new String[] { IWebWizardConstants.NAME_TITLE, IWebWizardConstants.VALUE_TITLE, IWebWizardConstants.DESCRIPTION_TITLE }, 
				new String[] { IWebWizardConstants.NAME_LABEL, IWebWizardConstants.VALUE_LABEL, IWebWizardConstants.DESCRIPTION_LABEL }, 
				null,// WebPlugin.getDefault().getImage("initializ_parameter"),
				model, 
				INewServletClassDataModelProperties.INIT_PARAM);
		initSection.setCallback(callback);
		urlSection = new StringArrayTableWizardSection(
				composite, 
				IWebWizardConstants.URL_MAPPINGS_LABEL, 
				IWebWizardConstants.URL_MAPPINGS_TITLE, 
				IWebWizardConstants.ADD_BUTTON_LABEL, 
				IWebWizardConstants.EDIT_BUTTON_LABEL, 
				IWebWizardConstants.REMOVE_BUTTON_LABEL,
				new String[] { IWebWizardConstants.URL_PATTERN_TITLE }, 
				new String[] { IWebWizardConstants.URL_PATTERN_LABEL }, 
				null,// WebPlugin.getDefault().getImage("url_type"),
				model, 
				INewServletClassDataModelProperties.URL_MAPPINGS);
		urlSection.setCallback(callback);
		
		String text = displayNameText.getText();
		// Set default URL Pattern
		List input = new ArrayList();
		input.add(new String[]{"/" + text}); //$NON-NLS-1$
		if (urlSection != null)
			urlSection.setInput(input);
		displayNameText.setFocus();

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
		if (model.getStringProperty(IArtifactEditOperationDataModelProperties.PROJECT_NAME) == null || model.getStringProperty(IArtifactEditOperationDataModelProperties.PROJECT_NAME).trim().length() == 0) {
			return WTPCommonPlugin.createErrorStatus(IWebWizardConstants.NO_WEB_PROJECTS);
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	protected void createNameDescription(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		// display name
		Label displayNameLabel = new Label(composite, SWT.LEFT);
		displayNameLabel.setText(IWebWizardConstants.NAME_LABEL);
		displayNameLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
		displayNameText = new Text(composite, SWT.SINGLE | SWT.BORDER);
		displayNameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		displayNameText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String text = displayNameText.getText();
				// Set default URL Pattern
				List input = new ArrayList();
				input.add(new String[]{"/" + text}); //$NON-NLS-1$
				if (urlSection != null)
					urlSection.setInput(input);
			}

		});
		synchHelper.synchText(displayNameText, INewWebClassDataModelProperties.DISPLAY_NAME, null);

		// description
		Label descLabel = new Label(composite, SWT.LEFT);
		descLabel.setText(IWebWizardConstants.DESCRIPTION_LABEL);
		descLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
		Text descText = new Text(composite, SWT.SINGLE | SWT.BORDER);
		descText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		synchHelper.synchText(descText, INewWebClassDataModelProperties.DESCRIPTION, null);
	}

	public String getDisplayName() {
		return displayNameText.getText();
	}
	
	public boolean canFlipToNextPage() {
		if (model.getBooleanProperty(INewWebClassDataModelProperties.USE_EXISTING_CLASS))
			return false;
		return super.canFlipToNextPage();
	}
}
