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
import org.eclipse.jst.j2ee.internal.common.operations.J2EEModelModifierOperationDataModel;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WelcomeFile;
import org.eclipse.jst.j2ee.webapplication.WelcomeFileList;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

/**
 * @author jlanuti
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddWelcomePageDataModel extends J2EEModelModifierOperationDataModel {

	/**
	 * Required - The file name to use as default welcome page
	 * 
	 * @see String
	 */
	public static final String WELCOME_PAGE_WELCOME_FILE = "AddWelcomePageOperationDataModel.WELCOME_PAGE_WELCOME_FILE"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new AddWelcomePageOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(WELCOME_PAGE_WELCOME_FILE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(WELCOME_PAGE_WELCOME_FILE))
			return validateWelcomeFileName(getStringProperty(propertyName));
		return super.doValidateProperty(propertyName);
	}

	protected IStatus validateWelcomeFileName(String welcomeFile) {
		// check if empty
		if (welcomeFile == null || welcomeFile.trim().length() == 0) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_WELCOME_PAGE_EMPTY, new String[]{welcomeFile});
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		// check for duplicate
		WelcomeFileList wfl = ((WebApp) getDeploymentDescriptorRoot()).getFileList();
		if (wfl != null && wfl.getFile() != null) {
			List welcomeFileList = wfl.getFile();
			for (int i = 0; i < welcomeFileList.size(); i++) {
				WelcomeFile wf = (WelcomeFile) welcomeFileList.get(i);
				if (wf.getWelcomeFile().equals(welcomeFile)) {
					String msg = WebMessages.getResourceString(WebMessages.ERR_WELCOME_PAGE_EXIST, new String[]{welcomeFile});
					return WTPCommonPlugin.createErrorStatus(msg);
				}
			}
		}
		return WTPCommonPlugin.OK_STATUS;
	}

}