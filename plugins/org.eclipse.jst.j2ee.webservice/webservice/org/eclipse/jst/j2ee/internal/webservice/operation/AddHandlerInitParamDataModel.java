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
 * Created on Mar 2, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.webservice.operation;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.common.ParamValue;
import org.eclipse.jst.j2ee.internal.common.operations.J2EEModelModifierOperationDataModel;
import org.eclipse.jst.j2ee.webservice.wsclient.Handler;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddHandlerInitParamDataModel extends J2EEModelModifierOperationDataModel {

	/**
	 * Required - The filter
	 * 
	 * @see Filter
	 */
	public static final String HANDLER = "AddHandlerInitParamDataModel.HANDLER"; //$NON-NLS-1$

	/**
	 * Required - The param name
	 * 
	 * @see Filter
	 */
	public static final String PARAM_NAME = "AddHandlerInitParamDataModel.PARAM_NAME"; //$NON-NLS-1$

	/**
	 * Required - The param value
	 * 
	 * @see String
	 */
	public static final String PARAM_VALUE = "AddHandlerInitParamDataModel.PARAM_VALUE"; //$NON-NLS-1$

	/**
	 * Required - The description
	 * 
	 * @see String
	 */
	public static final String DESCRIPTION = "AddHandlerInitParamDataModel.DESCRIPTION"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new AddHandlerInitParamOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(PARAM_NAME);
		addValidBaseProperty(PARAM_VALUE);
		addValidBaseProperty(DESCRIPTION);
		addValidBaseProperty(HANDLER);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(PARAM_NAME)) {
			return validateParamName(getStringProperty(propertyName));
		}
		return super.doValidateProperty(propertyName);
	}

	/**
	 * @param servletName
	 * @return
	 */
	private IStatus validateParamName(String name) {
		if (name == null || name.trim().length() == 0) {
			String msg = WebServiceMessages.getResourceString(WebServiceMessages.ERR_HANDLER_PARAM_NAME_EMPTY, new String[]{name});
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		Handler handler = (Handler) getProperty(AddHandlerInitParamDataModel.HANDLER);
		List parms = handler.getInitParams();
		for (int i = 0; i < parms.size(); i++) {
			if (((ParamValue) parms.get(i)).getName().equals(name))
				return WTPCommonPlugin.createErrorStatus(WebServiceMessages.ERR_HANDLER_PARAM_NAME_EXISTS);
		}

		return WTPCommonPlugin.OK_STATUS;
	}
}