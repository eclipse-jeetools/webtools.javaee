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

import org.eclipse.jst.j2ee.application.operations.AddWebModuleToEARDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.wizard.AnnotationsStandaloneGroup;
import org.eclipse.jst.j2ee.internal.wizard.J2EEModuleCreationPage;
import org.eclipse.jst.servlet.ui.internal.plugin.WEBUIMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModel;
import org.eclipse.wst.common.modulecore.internal.operation.ArtifactEditOperationDataModel;

/**
 * 
 */
public class WebModuleCreationPage extends J2EEModuleCreationPage {

	public Text contextRootNameField = null;
	public Label contextRootLabel = null;

	private static final int SIZING_TEXT_FIELD_WIDTH = 250;
	private AnnotationsStandaloneGroup annotationsGroup;
	
	/**
	 * @param model
	 * @param pageName
	 */
	public WebModuleCreationPage(WebComponentCreationDataModel  model, String pageName) {
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
		synchHelper.synchText(contextRootNameField, WebComponentCreationDataModel.CONTEXT_ROOT, new Control[]{contextRootLabel});
		
		createAnnotationsGroup(advanced);
	}

	private void createAnnotationsGroup(Composite parent) {
		annotationsGroup = new AnnotationsStandaloneGroup(parent, getJ2EEModuleCreationDataModel(), false);
	}
	
	WebComponentCreationDataModel getWebProjectCreationDataModel() {
		return (WebComponentCreationDataModel) model;
	}

	//TODO: utility to handle additions
	protected String[] getValidationPropertyNames() {
		return new String[]{ArtifactEditOperationDataModel.PROJECT_NAME, J2EEComponentCreationDataModel.J2EE_MODULE_VERSION, WTPOperationDataModel.NESTED_MODEL_VALIDATION_HOOK, J2EEComponentCreationDataModel.ADD_TO_EAR, AddWebModuleToEARDataModel.CONTEXT_ROOT};
	}

	public void dispose() {
		super.dispose();
		annotationsGroup.dispose();
	}
}