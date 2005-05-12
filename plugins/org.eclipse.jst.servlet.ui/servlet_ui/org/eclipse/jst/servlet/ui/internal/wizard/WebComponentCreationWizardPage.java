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

package org.eclipse.jst.servlet.ui.internal.wizard;

import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.wizard.DataModelAnnotationsStandaloneGroup;
import org.eclipse.jst.j2ee.internal.wizard.J2EEComponentCreationWizardPage;
import org.eclipse.jst.j2ee.web.datamodel.properties.IWebComponentCreationDataModelProperties;
import org.eclipse.jst.servlet.ui.internal.plugin.WEBUIMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * 
 */
public class WebComponentCreationWizardPage extends J2EEComponentCreationWizardPage implements IWebComponentCreationDataModelProperties{

	public Text contextRootNameField = null;
	public Label contextRootLabel = null;

	private static final int SIZING_TEXT_FIELD_WIDTH = 250;
	private DataModelAnnotationsStandaloneGroup annotationsGroup;
	
	/**
	 * @param model
	 * @param pageName
	 */
	public WebComponentCreationWizardPage(IDataModel model, String pageName) {
		super(model, pageName);
		setTitle(WEBUIMessages.getResourceString(WEBUIMessages.WEB_PROJECT_MAIN_PG_TITLE));
		setDescription(WEBUIMessages.getResourceString(WEBUIMessages.WEB_PROJECT_MAIN_PG_DESC));
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.WEB_PROJECT_WIZARD_BANNER));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ui.wizard.J2EEModuleCreationPage#createTopLevelComposite(org.eclipse.swt.widgets.Composite)
	 */
	protected void addToAdvancedComposite(Composite advanced) {
		super.addToAdvancedComposite(advanced);
		contextRootLabel = new Label(advanced, SWT.NULL);
		contextRootLabel.setText(WEBUIMessages.getResourceString(WEBUIMessages.WEB_IMPORT_CONTEXT_ROOT));
		GridData data = new GridData();
		contextRootLabel.setLayoutData(data);
		// set up context root entry field

		contextRootNameField = new Text(advanced, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = SIZING_TEXT_FIELD_WIDTH;
		contextRootNameField.setLayoutData(data);
		synchHelper.synchText(contextRootNameField, CONTEXT_ROOT, new Control[]{contextRootLabel});
		
		createAnnotationsGroup(advanced);
	}

	private void createAnnotationsGroup(Composite parent) {
		annotationsGroup = new DataModelAnnotationsStandaloneGroup(parent, getDataModel(), false, synchHelper);
	}

	//TODO: utility to handle additions
	protected String[] getValidationPropertyNames() {
		return new String[]{IWebComponentCreationDataModelProperties.PROJECT_NAME, COMPONENT_NAME, COMPONENT_VERSION, ADD_TO_EAR, CONTEXT_ROOT};
	}

	public void dispose() {
		super.dispose();
		if (annotationsGroup != null)
			annotationsGroup.dispose();
	}
}