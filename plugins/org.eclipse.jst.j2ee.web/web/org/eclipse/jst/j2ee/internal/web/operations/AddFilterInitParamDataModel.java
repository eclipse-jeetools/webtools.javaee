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
 * Created on Jan 13, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.common.ParamValue;
import org.eclipse.jst.j2ee.internal.common.operations.J2EEModelModifierOperationDataModel;
import org.eclipse.jst.j2ee.webapplication.Filter;
import org.eclipse.jst.j2ee.webapplication.InitParam;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

/**
 * @author cbridgha
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddFilterInitParamDataModel extends J2EEModelModifierOperationDataModel {

	/**
	 * Required - The filter
	 * 
	 * @see Filter
	 */
	public static final String FILTER = "AddFilterInitParamDataModel.FILTER"; //$NON-NLS-1$
	/**
	 * Required - The param name
	 * 
	 * @see Filter
	 */
	public static final String PARAM_NAME = "AddFilterInitParamDataModel.PARAM_NAME"; //$NON-NLS-1$
	/**
	 * Required - The param value
	 * 
	 * @see String
	 */
	public static final String PARAM_VALUE = "AddFilterInitParamDataModel.PARAM_VALUE"; //$NON-NLS-1$

	/**
	 * Required - The description
	 * 
	 * @see String
	 */
	public static final String DESCRIPTION = "AddFilterInitParamDataModel.DESCRIPTION"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new AddFilterInitParamOperation(this);
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
		addValidBaseProperty(FILTER);
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
			String msg = WebMessages.getResourceString(WebMessages.ERR_FILTER_PARAM_NAME_EMPTY, new String[]{name});
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		boolean exists = false;
		Filter filter = (Filter) getProperty(FILTER);
		List list13 = filter.getInitParams();
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
		List list14 = filter.getInitParamValues();
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
			String msg = WebMessages.getResourceString(WebMessages.ERR_FILTER_PARAM_NAME_EXISTS, new String[]{name});
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		return WTPCommonPlugin.OK_STATUS;
	}
}