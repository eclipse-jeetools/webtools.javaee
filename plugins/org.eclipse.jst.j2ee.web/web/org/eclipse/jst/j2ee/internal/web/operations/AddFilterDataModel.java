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
 * Created on Mar 30, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.webapplication.Filter;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddFilterDataModel extends AddServletFilterListenerCommonDataModel {
	public final static String[] FILTER_INTERFACES = {"javax.servlet.Filter" //$NON-NLS-1$
	};
	public static final String INIT_PARAM = "AddFilterDataModel.INIT_PARAM"; //$NON-NLS-1$
	public static final String URL_MAPPINGS = "AddFilterDataModel.URL_MAPPINGS"; //$NON-NLS-1$
	public static final String SERVLET_MAPPINGS = "AddFilterDataModel.SERVLET_MAPPINGS"; //$NON-NLS-1$

	private List interfaceList;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new AddFilterOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(INIT_PARAM);
		addValidBaseProperty(URL_MAPPINGS);
		addValidBaseProperty(SERVLET_MAPPINGS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(INIT_PARAM))
			return validateInitParamList((List) getProperty(propertyName));
		if (propertyName.equals(URL_MAPPINGS))
			return validateURLMappingList((List) getProperty(propertyName));
		return super.doValidateProperty(propertyName);
	}

	protected IStatus validateDisplayName(String prop) {
		if (prop == null || prop.trim().length() == 0) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_DISPLAY_NAME_EMPTY);
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		WebApp webApp = (WebApp) getDeploymentDescriptorRoot();
		List filters = webApp.getFilters();
		boolean exists = false;
		if (filters != null && !filters.isEmpty()) {
			for (int i = 0; i < filters.size(); i++) {
				String name = ((Filter) filters.get(i)).getName();
				if (prop.equals(name))
					exists = true;
			}
		}
		if (exists) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_FILTER_DISPLAY_NAME_EXIST, new String[]{prop});
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	protected IStatus validateClassName(String prop) {
		// this validation is only for using existing class
		if (!getBooleanProperty(USE_EXISTING_CLASS))
			return WTPCommonPlugin.OK_STATUS;
		// check for empty
		IStatus status = super.validateClassName(prop);
		if (!status.isOK())
			return status;
		// check for duplicate
		WebApp webApp = (WebApp) getDeploymentDescriptorRoot();
		List filters = webApp.getFilters();
		if (filters != null && filters.size() > 0) {
			for (int i = 0; i < filters.size(); i++) {
				Filter filter = (Filter) filters.get(i);
				if (prop.equals(filter.getFilterClass().getQualifiedName())) {
					String msg = WebMessages.getResourceString(WebMessages.ERR_FILTER_CLASS_NAME_USED);
					return WTPCommonPlugin.createErrorStatus(msg);
				}
			}
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	private IStatus validateInitParamList(List prop) {
		if (prop != null && !prop.isEmpty()) {
			boolean dup = hasDuplicatesInStringArrayList(prop);
			if (dup) {
				String msg = WebMessages.getResourceString(WebMessages.ERR_DUPLICATED_INIT_PARAMETER);
				return WTPCommonPlugin.createErrorStatus(msg);
			}
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	private IStatus validateURLMappingList(List prop) {
		if (prop != null && !prop.isEmpty()) {
			boolean dup = hasDuplicatesInStringArrayList(prop);
			if (dup) {
				String msg = WebMessages.getResourceString(WebMessages.ERR_DUPLICATED_URL_MAPPING);
				return WTPCommonPlugin.createErrorStatus(msg);
			}
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	public List getFilterInterfaces() {
		if (this.interfaceList == null) {
			this.interfaceList = new ArrayList();
			for (int i = 0; i < FILTER_INTERFACES.length; i++) {
				this.interfaceList.add(FILTER_INTERFACES[i]);
			}
		}
		return this.interfaceList;
	}

	private boolean hasDuplicatesInStringArrayList(List input) {
		if (input == null)
			return false;
		int n = input.size();
		boolean dup = false;
		for (int i = 0; i < n; i++) {
			String[] sArray1 = (String[]) input.get(i);
			for (int j = i + 1; j < n; j++) {
				String[] sArray2 = (String[]) input.get(j);
				if (isTwoStringArraysEqual(sArray1, sArray2)) {
					dup = true;
					break;
				}
			}
			if (dup)
				break;
		}
		return dup;
	}

	private boolean isTwoStringArraysEqual(String[] sArray1, String[] sArray2) {
		if (sArray1 == null || sArray2 == null)
			return false;
		int n1 = sArray1.length;
		int n2 = sArray1.length;
		if (n1 == 0 || n2 == 0)
			return false;
		if (n1 != n2)
			return false;
		if (!sArray1[0].equals(sArray2[0]))
			return false;
		return true;
	}
}