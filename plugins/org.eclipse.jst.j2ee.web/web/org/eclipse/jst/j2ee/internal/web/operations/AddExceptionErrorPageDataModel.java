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
import org.eclipse.jst.j2ee.common.operations.J2EEModelModifierOperationDataModel;
import org.eclipse.jst.j2ee.webapplication.ErrorPage;
import org.eclipse.jst.j2ee.webapplication.ExceptionTypeErrorPage;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.wst.common.framework.operation.WTPOperation;
import org.eclispe.wst.common.framework.plugin.WTPCommonPlugin;

/**
 * @author jlanuti
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddExceptionErrorPageDataModel extends J2EEModelModifierOperationDataModel {

	/**
	 * Required - The location of the error page
	 * 
	 * @see String
	 */
	public static final String EXCEPTION_ERROR_PAGE_LOCATION = "AddExceptionErrorPageOperationDataModel.EXCEPTION_ERROR_PAGE_LOCATION"; //$NON-NLS-1$

	/**
	 * Required - The exception type of the error page
	 * 
	 * @see String
	 */
	public static final String EXCEPTION_ERROR_PAGE_EXCEPTION_TYPE = "AddExceptionErrorPageOperationDataModel.EXCEPTION_ERROR_PAGE_EXCEPTION_TYPE"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new AddExceptionErrorPageOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(EXCEPTION_ERROR_PAGE_LOCATION);
		addValidBaseProperty(EXCEPTION_ERROR_PAGE_EXCEPTION_TYPE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(EXCEPTION_ERROR_PAGE_EXCEPTION_TYPE))
			return validateExceptionType(getStringProperty(propertyName));
		else if (propertyName.equals(EXCEPTION_ERROR_PAGE_LOCATION))
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

	private IStatus validateForDuplicates() {
		// check for duplicate
		List errorPages = ((WebApp) getDeploymentDescriptorRoot()).getErrorPages();
		for (int i = 0; i < errorPages.size(); i++) {
			ErrorPage errorPage = (ErrorPage) errorPages.get(i);
			if (errorPage.isExceptionType()) {
				ExceptionTypeErrorPage ecErrorPage = (ExceptionTypeErrorPage) errorPage;
				if (ecErrorPage.getLocation().equals(getStringProperty(EXCEPTION_ERROR_PAGE_LOCATION)) && ecErrorPage.getExceptionTypeName().equals(getStringProperty(EXCEPTION_ERROR_PAGE_EXCEPTION_TYPE))) {
					String msg = WebMessages.getResourceString(WebMessages.ERR_ERROR_PAGE_LOCATION_EXIST, new String[]{getStringProperty(EXCEPTION_ERROR_PAGE_LOCATION)});
					return WTPCommonPlugin.createErrorStatus(msg);
				}
			}
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	private IStatus validateExceptionType(String exceptionType) {
		// check if empty
		if (exceptionType == null || exceptionType.trim().length() == 0) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_EXCEPTION_TYPE_EMPTY, new String[]{exceptionType});
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		return validateForDuplicates();

	}
}