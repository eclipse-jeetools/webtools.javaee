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
 * Created on Nov 7, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.internal.war.wizard;

import org.eclipse.jst.j2ee.application.operations.AddWebModuleToEARDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEProjectCreationDataModel;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.war.ui.util.WEBUIMessages;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebProjectCreationDataModel;
import org.eclipse.jst.j2ee.internal.wizard.AnnotationsStandaloneGroup;
import org.eclipse.jst.j2ee.internal.wizard.J2EEModuleCreationPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.framework.operation.WTPOperationDataModel;
import org.eclipse.wst.common.framework.operation.WTPOperationDataModelEvent;
import org.eclipse.wst.common.framework.operation.WTPOperationDataModelListener;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel;

/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class WEBProjectCreationPage extends J2EEModuleCreationPage {

	public Text contextRootNameField = null;
	public Label contextRootLabel = null;

	private static final int SIZING_TEXT_FIELD_WIDTH = 250;
	private AnnotationsStandaloneGroup annotationsGroup;

	/**
	 * @param model
	 * @param pageName
	 */
	protected WEBProjectCreationPage(WebProjectCreationDataModel model, String pageName) {
		super(model, pageName);
		setTitle(WEBUIMessages.getResourceString(WEBUIMessages.WEB_PROJECT_MAIN_PG_TITLE));
		setDescription(WEBUIMessages.getResourceString(WEBUIMessages.WEB_PROJECT_MAIN_PG_DESC));
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.WEB_PROJECT_WIZARD_BANNER));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.ui.wizard.J2EEModuleCreationPage#createTopLevelComposite(org.eclipse.swt.widgets.Composite)
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
		synchHelper.synchText(contextRootNameField, WebProjectCreationDataModel.CONTEXT_ROOT, new Control[]{contextRootLabel});

		getWebProjectCreationDataModel().addListener(new WTPOperationDataModelListener() {
			public void propertyChanged(WTPOperationDataModelEvent event) {
				if (event.getPropertyName().equals(J2EEModuleCreationDataModel.ADD_TO_EAR)) {
					boolean addToEAR = getWebProjectCreationDataModel().getBooleanProperty(J2EEModuleCreationDataModel.ADD_TO_EAR);
					contextRootLabel.setEnabled(addToEAR);
					contextRootNameField.setEnabled(addToEAR);
				}
			}
		});

		createAnnotationsGroup(advanced);
	}

	private void createAnnotationsGroup(Composite parent) {
		annotationsGroup = new AnnotationsStandaloneGroup(parent, getJ2EEModuleCreationDataModel(), false);
	}

	WebProjectCreationDataModel getWebProjectCreationDataModel() {
		return (WebProjectCreationDataModel) model;
	}


	protected String getVersionLabel() {
		return WEBUIMessages.getResourceString(WEBUIMessages.WEB_VERSION_LBL);
	}

	//TODO: utility to handle additions
	protected String[] getValidationPropertyNames() {
		return new String[]{EditModelOperationDataModel.PROJECT_NAME, J2EEProjectCreationDataModel.PROJECT_LOCATION, J2EEModuleCreationDataModel.J2EE_MODULE_VERSION, WTPOperationDataModel.NESTED_MODEL_VALIDATION_HOOK, J2EEModuleCreationDataModel.EAR_PROJECT_NAME, J2EEModuleCreationDataModel.ADD_TO_EAR, AddWebModuleToEARDataModel.CONTEXT_ROOT};
	}

	public void dispose() {
		super.dispose();
		annotationsGroup.dispose();
	}
}