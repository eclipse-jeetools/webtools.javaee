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
import org.eclipse.jst.j2ee.internal.common.operations.J2EEModelModifierOperationDataModel;
import org.eclipse.jst.j2ee.webapplication.DispatcherType;
import org.eclipse.jst.j2ee.webapplication.Filter;
import org.eclipse.jst.j2ee.webapplication.FilterMapping;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.internal.operations.WTPPropertyDescriptor;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

/**
 * @author cbridgha
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddFilterMappingDataModel extends J2EEModelModifierOperationDataModel {

	/**
	 * Required - The filter
	 * 
	 * @see Filter
	 */
	public static final String FILTER = "AddFilterMappingOperationDataModel.FILTER"; //$NON-NLS-1$
	/**
	 * Required - The filter
	 * 
	 * @see Filter
	 */
	public static final String DISPATCHER_TYPE_NAME = "AddFilterMappingOperationDataModel.DISPATCHER_TYPE_NAME"; //$NON-NLS-1$
	/**
	 * Required - The name of the filter mapping pattern.
	 * 
	 * @see String
	 */
	public static final String URL_PATTERN = "AddFilterMappingOperationDataModel.URL_PATTERN"; //$NON-NLS-1$

	/**
	 * Required - The dispatcher type (ENUM) of the filter map.
	 * 
	 * @see String
	 */
	public static final String DISPATCHER_TYPE = "AddFilterMappingOperationDataModel.DISPATCHER_TYPE"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new AddFilterMappingOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(DISPATCHER_TYPE);
		addValidBaseProperty(URL_PATTERN);
		addValidBaseProperty(DISPATCHER_TYPE_NAME);
		addValidBaseProperty(FILTER);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		IStatus status = validateFilterName(getStringProperty(FILTER));
		if (!status.isOK())
			return status;
		if (propertyName.equals(URL_PATTERN)) {
			return validateURLPattern(getStringProperty(propertyName));
		}
		return super.doValidateProperty(propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doSetProperty(java.lang.String,
	 *      java.lang.Object)
	 */
	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		boolean notify = super.doSetProperty(propertyName, propertyValue);
		if (propertyName.equals(DISPATCHER_TYPE_NAME))
			setProperty(DISPATCHER_TYPE, getDispatcherValues((List) propertyValue));
		return notify;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.wtp.ejb.operations.CreateEnterpriseBeanDataModel#doGetValidPropertyValues(java.lang.String)
	 */
	protected WTPPropertyDescriptor[] doGetValidPropertyDescriptors(String propertyName) {
		if (propertyName.equals(DISPATCHER_TYPE_NAME))
			return WTPPropertyDescriptor.createDescriptors(getValidDispatcherTypeNames());
		return super.doGetValidPropertyDescriptors(propertyName);
	}

	/**
	 * @param servletName
	 * @return
	 */
	private IStatus validateURLPattern(String urlPattern) {
		if (urlPattern == null || urlPattern.trim().length() == 0) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_FILTER_MAPPING_URL_PATTERN_EMPTY, new String[]{urlPattern});
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		WebApp webApp = (WebApp) getDeploymentDescriptorRoot();
		String filterName = getStringProperty(FILTER);
		Filter filter = webApp.getFilterNamed(filterName);
		List mappings = webApp.getFilterMappings();
		boolean exists = false;
		if (mappings != null && !mappings.isEmpty()) {
			for (int i = 0; i < mappings.size(); i++) {
				FilterMapping mapping = (FilterMapping) mappings.get(i);
				if (mapping.getFilter() == filter && urlPattern.equals(mapping.getUrlPattern())) {
					exists = true;
					break;
				}
			}
		}
		if (exists) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_FILTER_MAPPING_URL_PATTERN_EXIST, new String[]{urlPattern});
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	private IStatus validateFilterName(String filterName) {
		if (filterName == null || filterName.trim().length() == 0) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_DISPLAY_NAME_EMPTY);
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		return WTPCommonPlugin.OK_STATUS;
	}
}