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
 * Created on Mar 8, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.internal.common.operations.J2EEModelModifierOperationDataModel;
import org.eclipse.jst.j2ee.webapplication.LocalEncodingMapping;
import org.eclipse.jst.j2ee.webapplication.LocalEncodingMappingList;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddLocaleEncodingMappingDataModel extends J2EEModelModifierOperationDataModel {
	public static final String LOCALE_NAME = "AddLocalEncodingMappingDataModel.LOCALE_NAME"; //$NON-NLS-1$
	public static final String ENCODING = "AddLocalEncodingMappingDataModel.ENCODING"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new AddLocaleEncodingMappingOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(LOCALE_NAME);
		addValidBaseProperty(ENCODING);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(LOCALE_NAME))
			return validateResourceName(getStringProperty(propertyName));
		return super.doValidateProperty(propertyName);
	}

	private IStatus validateResourceName(String name) {
		if (name == null || name.trim().length() == 0) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_LOCALE_NAME_EMPTY, new String[]{name});
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		WebApp webApp = (WebApp) getDeploymentDescriptorRoot();
		boolean exists = false;
		LocalEncodingMappingList leml = webApp.getLocalEncodingMappingList();
		if (leml != null) {
			List list = leml.getLocalEncodingMappings();
			int size = list.size();
			for (int i = 0; i < size; i++) {
				LocalEncodingMapping mapping = (LocalEncodingMapping) list.get(i);
				String localeName = mapping.getLocale();
				if (name.equals(localeName)) {
					exists = true;
					break;
				}
			}
		}
		if (exists) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_LOCALE_NAME_EXIST, new String[]{name});
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		return WTPCommonPlugin.OK_STATUS;
	}
}