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
 * Created on Jan 19, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.common.ParamValue;
import org.eclipse.jst.j2ee.common.operations.J2EEModelModifierOperationDataModel;
import org.eclipse.jst.j2ee.webapplication.InitParam;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.wst.common.framework.operation.WTPOperation;
import org.eclispe.wst.common.framework.plugin.WTPCommonPlugin;

/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddServletInitParamDataModel extends J2EEModelModifierOperationDataModel {
	/**
	 * Required - The name of the servlet.
	 * 
	 * @see String
	 */
	public static final String SERVLET = "AddServletInitParamOperationDataModel.SERVLET"; //$NON-NLS-1$
	/**
	 * Required - The name of the initialization parameter.
	 * 
	 * @see String
	 */
	public static final String PARAMETER_NAME = "AddServletInitParamOperationDataModel.PARAMETER_NAME"; //$NON-NLS-1$

	/**
	 * Required - The value of the initialization parameter.
	 * 
	 * @see String
	 */
	public static final String PARAMETER_VALUE = "AddServletInitParamOperationDataModel.PARAMETER_VALUE"; //$NON-NLS-1$

	/**
	 * Required - The description
	 * 
	 * @see String
	 */
	public static final String DESCRIPTION = "AddServletInitParamOperationDataModel.DESCRIPTION"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new AddServletInitParamOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(SERVLET);
		addValidBaseProperty(PARAMETER_NAME);
		addValidBaseProperty(PARAMETER_VALUE);
		addValidBaseProperty(DESCRIPTION);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(PARAMETER_NAME))
			return validateParameterName(getStringProperty(propertyName));
		if (propertyName.equals(PARAMETER_VALUE))
			return validateParameterValue(getStringProperty(propertyName));
		return super.doValidateProperty(propertyName);
	}

	private IStatus validateParameterName(String name) {
		if (name == null || name.trim().length() == 0) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_SERVLET_PARAMETER_NAME_EMPTY, new String[]{name});
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		boolean exists = false;
		Servlet servlet = (Servlet) getProperty(SERVLET);
		List list13 = servlet.getParams();
		if (list13 != null) {
			int size = list13.size();
			for (int i = 0; i < size; i++) {
				InitParam p = (InitParam) list13.get(i);
				if (p == null)
					continue;
				String parName = p.getParamName();
				if (name.equals(parName)) {
					exists = true;
					break;
				}
			}
		}
		List list14 = servlet.getInitParams();
		if (list14 != null) {
			int size = list14.size();
			for (int i = 0; i < size; i++) {
				ParamValue p = (ParamValue) list14.get(i);
				if (p == null)
					continue;
				String parName = p.getName();
				if (name.equals(parName)) {
					exists = true;
					break;
				}
			}
		}
		if (exists) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_SERVLET_PARAMETER_NAME_EXIST, new String[]{name});
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	private IStatus validateParameterValue(String value) {
		if (value == null || value.trim().length() == 0) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_SERVLET_PARAMETER_VALUE_EMPTY, new String[]{value});
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		return WTPCommonPlugin.OK_STATUS;
	}
}