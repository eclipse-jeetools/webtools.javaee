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
import org.eclipse.jst.j2ee.common.operations.J2EEModelModifierOperationDataModel;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.ServletMapping;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.wst.common.framework.operation.WTPOperation;
import org.eclispe.wst.common.framework.plugin.WTPCommonPlugin;

/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddServletMappingDataModel extends J2EEModelModifierOperationDataModel {
	/**
	 * Required - The name of the servlet.
	 * 
	 * @see String
	 */
	public static final String SERVLET = "AddServletMappingOperationDataModel.SERVLET"; //$NON-NLS-1$

	/**
	 * Required - The name of the servlet mapping pattern.
	 * 
	 * @see String
	 */
	public static final String URL_PATTERN = "AddServletMappingOperationDataModel.URL_PATTERN"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new AddServletMappingOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(SERVLET);
		addValidBaseProperty(URL_PATTERN);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		IStatus status = validateServletName(getStringProperty(SERVLET));
		if (!status.isOK())
			return status;
		if (propertyName.equals(URL_PATTERN)) {
			return validateURLPattern(getStringProperty(propertyName));
		}
		return super.doValidateProperty(propertyName);
	}

	/**
	 * @param servletName
	 * @return
	 */
	private IStatus validateURLPattern(String urlPattern) {
		if (urlPattern == null || urlPattern.trim().length() == 0) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_SERVLET_MAPPING_URL_PATTERN_EMPTY, new String[]{urlPattern});
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		WebApp webApp = (WebApp) getDeploymentDescriptorRoot();
		String servletName = getStringProperty(SERVLET);
		Servlet servlet = webApp.getServletNamed(servletName);
		List mappings = webApp.getServletMappings();
		boolean exists = false;
		if (mappings != null && !mappings.isEmpty()) {
			for (int i = 0; i < mappings.size(); i++) {
				ServletMapping mapping = (ServletMapping) mappings.get(i);
				if (mapping.getServlet() == servlet && urlPattern.equals(mapping.getUrlPattern())) {
					exists = true;
					break;
				}
			}

		}
		if (exists) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_SERVLET_MAPPING_URL_PATTERN_EXIST, new String[]{urlPattern});
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	private IStatus validateServletName(String servletName) {
		if (servletName == null || servletName.trim().length() == 0) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_SERVLET_MAPPING_SERVLET_NOT_EXIST);
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		return WTPCommonPlugin.OK_STATUS;
	}
}