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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.common.operations.J2EEModelModifierOperationDataModel;
import org.eclipse.jst.j2ee.webapplication.DispatcherType;
import org.eclipse.jst.j2ee.webapplication.Filter;
import org.eclipse.jst.j2ee.webapplication.FilterMapping;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.wst.common.framework.operation.WTPOperation;
import org.eclispe.wst.common.framework.plugin.WTPCommonPlugin;

/**
 * @author cbridgha
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddFilterMappingToServletDataModel extends J2EEModelModifierOperationDataModel {

	/**
	 * Required - The filter
	 * 
	 * @see Filter
	 */
	public static final String FILTER = "AddFilterMappingToServletOperationDataModel.FILTER"; //$NON-NLS-1$
	/**
	 * Required - The filter
	 * 
	 * @see Filter
	 */
	public static final String DISPATCHER_TYPE_NAME = "AddFilterMappingToServletOperationDataModel.DISPATCHER_TYPE_NAME"; //$NON-NLS-1$
	/**
	 * Required - The name of the filter mapping servlet.
	 * 
	 * @see String
	 */
	public static final String SERVLET_NAME = "AddFilterMappingOperationDataModel.SERVLET_NAME"; //$NON-NLS-1$

	/**
	 * Required - The dispatcher type (ENUM) of the filter map.
	 * 
	 * @see String
	 */
	public static final String DISPATCHER_TYPE = "AddFilterMappingToServletOperationDataModel.DISPATCHER_TYPE"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new AddFilterMappingToServletOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(DISPATCHER_TYPE);
		addValidBaseProperty(SERVLET_NAME);
		addValidBaseProperty(DISPATCHER_TYPE_NAME);
		addValidBaseProperty(FILTER);
	}

	/**
	 * @param servletName
	 * @return
	 */
	private IStatus validateServletName(String servletName) {
		if (servletName == null || servletName.trim().length() == 0) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_FILTER_MAPPING_SERVLET_EMPTY);
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		WebApp webApp = (WebApp) getDeploymentDescriptorRoot();
		// check if servlet exists
		List servlets = webApp.getServlets();
		boolean exists = false;
		if (servlets != null && !servlets.isEmpty()) {
			for (int i = 0; i < servlets.size(); i++) {
				Servlet s = (Servlet) servlets.get(i);
				if (s.getServletName().equals(servletName)) {
					exists = true;
					break;
				}
			}
		}
		if (!exists) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_SERVLET_MAPPING_SERVLET_NOT_EXIST);
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		Filter filter = (Filter) getProperty(FILTER);
		List mappings = webApp.getFilterMappings();
		exists = false;
		if (mappings != null && !mappings.isEmpty()) {
			for (int i = 0; i < mappings.size(); i++) {
				FilterMapping mapping = (FilterMapping) mappings.get(i);
				if (mapping.getFilter() == filter && servletName.equals(mapping.getServletName())) {
					exists = true;
					break;
				}
			}
		}
		if (exists) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_FILTER_MAPPING_SERVLET_EXIST, new String[]{servletName});
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	private IStatus validateDispatcherTypeName(List list) {
		if (list == null || list.size() == 0) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_FILTER_MAPPING_SERVLET_DISPATCHER_TYPES_EMPTY);
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#doSetProperty(java.lang.String,
	 *      java.lang.Object)
	 */
	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		boolean notify = super.doSetProperty(propertyName, propertyValue);
		if (propertyName.equals(DISPATCHER_TYPE_NAME)) {
			setProperty(DISPATCHER_TYPE_NAME, propertyValue);
			setProperty(DISPATCHER_TYPE, getDispatcherValues((List) propertyValue));
		}
		return notify;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(SERVLET_NAME)) {
			return validateServletName(getStringProperty(propertyName));
		}
		if (propertyName.equals(DISPATCHER_TYPE_NAME)) {
			return validateDispatcherTypeName((List) getProperty(propertyName));
		}
		return super.doValidateProperty(propertyName);
	}

	/**
	 * @param list
	 * @return
	 */
	private List getDispatcherValues(List list) {
		List dispatcherTypes = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			String dispatcherTypeName = (String) list.get(i);
			dispatcherTypes.add(DispatcherType.get(dispatcherTypeName));
		}
		return dispatcherTypes;
	}

	protected String[] getStringValues(List values) {
		String[] names = new String[values.size()];
		for (int i = 0; i < values.size(); i++)
			names[i] = values.get(i).toString();
		return names;
	}

	/**
	 * @return
	 */
	private String[] getValidDispatcherTypeNames() {
		return getStringValues(DispatcherType.VALUES);
	}

	/**
	 * @return
	 */
	private String[] getValidServletNames() {
		WebApp webApp = (WebApp) getDeploymentDescriptorRoot();
		List servlets = webApp.getServletNames();
		String[] names = new String[servlets.size()];
		for (int i = 0; i < servlets.size(); i++)
			names[i] = (String) servlets.get(i);
		return names;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.wtp.ejb.operations.CreateEnterpriseBeanDataModel#doGetValidPropertyValues(java.lang.String)
	 */
	protected Object[] doGetValidPropertyValues(String propertyName) {
		if (propertyName.equals(DISPATCHER_TYPE_NAME))
			return getValidDispatcherTypeNames();
		if (propertyName.equals(SERVLET_NAME))
			return getValidServletNames();
		return super.doGetValidPropertyValues(propertyName);
	}
}