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
import org.eclipse.jst.j2ee.common.SecurityRoleRef;
import org.eclipse.jst.j2ee.common.operations.J2EEModelModifierOperationDataModel;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.wst.common.framework.operation.WTPOperation;
import org.eclispe.wst.common.framework.plugin.WTPCommonPlugin;

/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddServletSecRoleRefDataModel extends J2EEModelModifierOperationDataModel {
	/**
	 * Required - The name of the servlet.
	 * 
	 * @see String
	 */
	public static final String SERVLET = "AddServletInitParamOperationDataModel.SERVLET"; //$NON-NLS-1$
	/**
	 * Required - The name of the security role.
	 * 
	 * @see String
	 */
	public static final String ROLE_REF_NAME = "AddServletSecRoleRefOperationDataModel.ROLE_REF_NAME"; //$NON-NLS-1$

	/**
	 * Optional - The description of the security role.
	 * 
	 * @see String
	 */
	public static final String ROLE_REF_DESC = "AddServletSecRoleRefOperationDataModel.ROLE_REF_DESC"; //$NON-NLS-1$

	/**
	 * Required - The link of the security role.
	 * 
	 * @see String
	 */
	public static final String ROLE_LINK = "AddServletSecRoleRefOperationDataModel.ROLE_LINK"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new AddServletSecRoleRefOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(SERVLET);
		addValidBaseProperty(ROLE_REF_NAME);
		addValidBaseProperty(ROLE_REF_DESC);
		addValidBaseProperty(ROLE_LINK);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(ROLE_REF_NAME))
			return validateRoleRefName(getStringProperty(propertyName));
		return super.doValidateProperty(propertyName);
	}

	private IStatus validateRoleRefName(String refName) {
		if (refName == null || refName.trim().length() == 0) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_SERVLET_ROLE_REF_NAME_EMPTY, new String[]{refName});
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		Servlet servlet = (Servlet) getProperty(SERVLET);
		List roleRefList = servlet.getSecurityRoleRefs();
		boolean exists = false;
		if (roleRefList != null) {
			int size = roleRefList.size();
			for (int i = 0; i < size; i++) {
				String name = ((SecurityRoleRef) roleRefList.get(i)).getName();
				if (refName.equals(name)) {
					exists = true;
					break;
				}
			}
		}
		if (exists) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_SERVLET_ROLE_REF_NAME_EXIST, new String[]{refName});
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		return WTPCommonPlugin.OK_STATUS;
	}

}