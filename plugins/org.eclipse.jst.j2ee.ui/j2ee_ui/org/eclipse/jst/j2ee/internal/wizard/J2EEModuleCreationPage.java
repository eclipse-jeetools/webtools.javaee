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
 * Created on Nov 10, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEProjectCreationDataModel;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetDataModel;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.framework.operation.WTPOperationDataModel;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel;

/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class J2EEModuleCreationPage extends J2EEProjectCreationPage {

	//	private static final int SIZING_TEXT_FIELD_WIDTH = 305;
	private ServerEarAndStandaloneGroup earGroup;

	public J2EEModuleCreationPage(J2EEModuleCreationDataModel dataModel, String pageName) {
		super(dataModel, pageName);
	}

	protected void addToAdvancedComposite(Composite advanced) {
		super.addToAdvancedComposite(advanced);
		createServerEarAndStandaloneGroup(advanced);
	}

	protected void createServerEarAndStandaloneGroup(Composite parent) {
		earGroup = new ServerEarAndStandaloneGroup(parent, getJ2EEModuleCreationDataModel());
	}

	protected J2EEModuleCreationDataModel getJ2EEModuleCreationDataModel() {
		return (J2EEModuleCreationDataModel) model;
	}

	protected void validatePage() {
		super.validatePage();
		if (!showAdvanced && !isPageComplete()) {
			String prop = validateControlsBase();
			if (null != prop) {
				String[] advancedProperties = {WTPOperationDataModel.NESTED_MODEL_VALIDATION_HOOK, J2EEModuleCreationDataModel.J2EE_MODULE_VERSION, J2EEProjectCreationDataModel.SERVER_TARGET_ID, ServerTargetDataModel.RUNTIME_TARGET_NAME, J2EEModuleCreationDataModel.EAR_PROJECT_NAME, J2EEModuleCreationDataModel.ADD_TO_EAR};
				for (int i = 0; i < advancedProperties.length; i++) {
					if (prop.equals(advancedProperties[i])) {
						toggleAdvanced(true);
						return;
					}
				}
			}
		}
	}

	protected String[] getValidationPropertyNames() {
		return new String[]{EditModelOperationDataModel.PROJECT_NAME, J2EEProjectCreationDataModel.PROJECT_LOCATION, J2EEModuleCreationDataModel.J2EE_MODULE_VERSION, J2EEProjectCreationDataModel.SERVER_TARGET_ID, ServerTargetDataModel.RUNTIME_TARGET_NAME, J2EEModuleCreationDataModel.EAR_PROJECT_NAME, J2EEModuleCreationDataModel.ADD_TO_EAR};
	}

	protected void createVersionComposite(Composite parent) {
		createVersionComposite(parent, getVersionLabel(), J2EEModuleCreationDataModel.J2EE_MODULE_VERSION, J2EEModuleCreationDataModel.J2EE_MODULE_VERSION_LBL);
	}

	protected abstract String getVersionLabel();

	public void dispose() {
		super.dispose();
		if (earGroup != null)
			earGroup.dispose();
	}

}