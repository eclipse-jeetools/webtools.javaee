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
 * Created on Feb 12, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.internal.common.operations.J2EEModelModifierOperationDataModel;
import org.eclipse.jst.j2ee.webapplication.ErrorCodeErrorPage;
import org.eclipse.jst.j2ee.webapplication.ErrorPage;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

/**
 * @author jlanuti
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddErrorCodeErrorPageDataModel extends J2EEModelModifierOperationDataModel {

	/**
	 * Required - The location of the error page
	 * 
	 * @see String
	 */
	public static final String ERROR_CODE_ERROR_PAGE_LOCATION = "AddErrorCodeErrorPageOperationDataModel.ERROR_CODE_ERROR_PAGE_LOCATION"; //$NON-NLS-1$

	/**
	 * Required - The error code of the error page
	 * 
	 * @see String
	 */
	public static final String ERROR_CODE_ERROR_PAGE_ERROR_CODE = "AddErrorCodeErrorPageOperationDataModel.ERROR_CODE_ERROR_PAGE_ERROR_CODE"; //$NON-NLS-1$


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new AddErrorCodeErrorPageOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(ERROR_CODE_ERROR_PAGE_LOCATION);
		addValidBaseProperty(ERROR_CODE_ERROR_PAGE_ERROR_CODE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(ERROR_CODE_ERROR_PAGE_ERROR_CODE))
			return validateErrorCode(getStringProperty(propertyName));
		else if (propertyName.equals(ERROR_CODE_ERROR_PAGE_LOCATION))
			return validateLocation(getStringProperty(propertyName));
		return super.doValidateProperty(propertyName);
	}

	private IStatus validateLocation(String location) {
		// check if empty
		if (location == null || location.trim().length() == 0) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_ERROR_PAGE_LOCATION_EMPTY, new String[]{location});
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		// check if it starts with /
		if (!location.startsWith("/")) { //$NON-NLS-1$
			String msg = WebMessages.getResourceString(WebMessages.ERR_ERROR_PAGE_LOCATION_MUST_START_WITH_SLASH);
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		return validateForDuplicates();
	}

	private IStatus validateErrorCode(String errorCode) {
		// check if empty
		if (errorCode == null || errorCode.trim().length() == 0) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_ERROR_CODE_EMPTY);
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		// check pattern
		if (!isThreeDigits(errorCode)) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_ERROR_CODE_MUST_BE_3_DIGITS);
			return WTPCommonPlugin.createErrorStatus(msg);
		}

		return validateForDuplicates();
	}

	private boolean isThreeDigits(String s) {
		char[] a = s.toCharArray();
		int n = a.length;
		if (n != 3)
			return false;
		for (int i = 0; i < 3; i++) {
			if (!Character.isDigit(a[i])) {
				return false;
			}
		}
		return true;
	}

	private IStatus validateForDuplicates() {
		// check for duplicate
		List errorPages = ((WebApp) getDeploymentDescriptorRoot()).getErrorPages();
		for (int i = 0; i < errorPages.size(); i++) {
			ErrorPage errorPage = (ErrorPage) errorPages.get(i);
			if (errorPage.isErrorCode()) {
				ErrorCodeErrorPage ecErrorPage = (ErrorCodeErrorPage) errorPage;
				if (ecErrorPage.getLocation().equals(getStringProperty(ERROR_CODE_ERROR_PAGE_LOCATION)) && ecErrorPage.getErrorCode().equals(getStringProperty(ERROR_CODE_ERROR_PAGE_ERROR_CODE))) {
					String msg = WebMessages.getResourceString(WebMessages.ERR_ERROR_PAGE_LOCATION_EXIST, new String[]{getStringProperty(ERROR_CODE_ERROR_PAGE_LOCATION)});
					return WTPCommonPlugin.createErrorStatus(msg);
				}
			}
		}
		return WTPCommonPlugin.OK_STATUS;
	}


}