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
 * Created on Feb 5, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.common.ParamValue;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.operations.J2EEModelModifierOperationDataModel;
import org.eclipse.jst.j2ee.webapplication.ContextParam;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddContextParamDataModel extends J2EEModelModifierOperationDataModel {
	/**
	 * Required - The name of the servlet.
	 * 
	 * @see String
	 */
	public static final String CONTEXT_PARAM_NAME = "AddContextParamOperationDataModel.CONTEXT_PARAM_NAME"; //$NON-NLS-1$
	/**
	 * Required - The name of the initialization parameter.
	 * 
	 * @see String
	 */
	public static final String CONTEXT_PARAM_VALUE = "AddContextParamOperationDataModel.CONTEXT_PARAM_VALUE"; //$NON-NLS-1$

	/**
	 * Required - The value of the initialization parameter.
	 * 
	 * @see String
	 */
	public static final String CONTEXT_PARAM_DESC = "AddContextParamOperationDataModel.CONTEXT_PARAM_DESC"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new AddContextParamOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(CONTEXT_PARAM_NAME);
		addValidBaseProperty(CONTEXT_PARAM_VALUE);
		addValidBaseProperty(CONTEXT_PARAM_DESC);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(CONTEXT_PARAM_NAME))
			return validateParameterName(getStringProperty(propertyName));
		return super.doValidateProperty(propertyName);
	}

	private IStatus validateParameterName(String name) {
		if (name == null || name.trim().length() == 0) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_CONTEXT_PARAMETER_NAME_EMPTY, new String[]{name});
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		WebApp webApp = (WebApp) getDeploymentDescriptorRoot();
		boolean exists = false;
		if (webApp.getJ2EEVersionID() >= J2EEVersionConstants.J2EE_1_4_ID) {
			List params = webApp.getContextParams();
			if (params != null) {
				int size = params.size();
				String parName = null;
				for (int i = 0; i < size; i++) {
					parName = ((ParamValue) params.get(i)).getName();
					if (name.equals(parName)) {
						exists = true;
						break;
					}
				}
			}
		} else {
			List params = webApp.getContexts();
			if (params != null) {
				int size = params.size();
				String parName = null;
				for (int i = 0; i < size; i++) {
					parName = ((ContextParam) params.get(i)).getParamName();
					if (name.equals(parName)) {
						exists = true;
						break;
					}
				}
			}
		}
		if (exists) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_CONTEXT_PARAMETER_NAME_EXIST, new String[]{name});
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		return WTPCommonPlugin.OK_STATUS;
	}
}