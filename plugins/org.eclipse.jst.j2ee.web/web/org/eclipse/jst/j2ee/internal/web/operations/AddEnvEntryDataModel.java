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
 * Created on Feb 11, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.operations;


import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.common.EnvEntry;
import org.eclipse.jst.j2ee.internal.common.operations.J2EEModelModifierOperationDataModel;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddEnvEntryDataModel extends J2EEModelModifierOperationDataModel {

	public static final String ENV_ENTRY_NAME = "AddEnvEntriesOperationDataModel.ENV_ENTRY_NAME"; //$NON-NLS-1$
	public static final String ENV_ENTRY_TYPE = "AddEnvEntriesOperationDataModel.ENV_ENTRY_TYPE"; //$NON-NLS-1$
	public static final String ENV_ENTRY_VALUE = "AddEnvEntriesOperationDataModel.ENV_ENTRY_VALUE"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new AddEnvEntryOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(ENV_ENTRY_NAME);
		addValidBaseProperty(ENV_ENTRY_TYPE);
		addValidBaseProperty(ENV_ENTRY_VALUE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(ENV_ENTRY_NAME))
			return validateName(getStringProperty(propertyName));
		return super.doValidateProperty(propertyName);
	}

	private IStatus validateName(String name) {
		if (name == null || name.trim().length() == 0) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_ENV_ENTRY_NAME_EMPTY, new String[]{name});
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		WebApp webApp = (WebApp) getDeploymentDescriptorRoot();
		List entries = webApp.getEnvironmentProperties();
		boolean exists = false;
		if (entries != null) {
			int size = entries.size();
			for (int i = 0; i < size; i++) {
				String entryName = ((EnvEntry) entries.get(i)).getName();
				if (name.equals(entryName)) {
					exists = true;
					break;
				}
			}
		}
		if (exists) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_ENV_ENTRY_NAME_EXIST, new String[]{name});
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		return WTPCommonPlugin.OK_STATUS;
	}
}