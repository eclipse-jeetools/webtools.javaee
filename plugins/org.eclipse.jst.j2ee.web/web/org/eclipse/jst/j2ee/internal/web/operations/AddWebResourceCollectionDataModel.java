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
 * Created on Feb 6, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.internal.common.operations.J2EEModelModifierOperationDataModel;
import org.eclipse.jst.j2ee.webapplication.SecurityConstraint;
import org.eclipse.jst.j2ee.webapplication.WebResourceCollection;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddWebResourceCollectionDataModel extends J2EEModelModifierOperationDataModel {
	public static final String ID = "AddWebResourceCollectionOperationDataModel.ID"; //$NON-NLS-1$
	public static final String SECURITY_CONSTRAINT = "AddWebResourceCollectionOperationDataModel.SECURITY_CONSTRAINT"; //$NON-NLS-1$
	public static final String RESOURCE_NAME = "AddWebResourceCollectionOperationDataModel.RESOURCE_NAME"; //$NON-NLS-1$
	public static final String RESOURCE_DESCRIPTION = "AddWebResourceCollectionOperationDataModel.RESOURCE_DESCRIPTION"; //$NON-NLS-1$
	public static final String HTTP_METHODS = "AddWebResourceCollectionOperationDataModel.HTTP_METHODS"; //$NON-NLS-1$
	public static final String URL_PATTERNS = "AddWebResourceCollectionOperationDataModel.URL_PATTERNS"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new AddWebResourceCollectionOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(SECURITY_CONSTRAINT);
		addValidBaseProperty(RESOURCE_NAME);
		addValidBaseProperty(RESOURCE_DESCRIPTION);
		addValidBaseProperty(HTTP_METHODS);
		addValidBaseProperty(URL_PATTERNS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(RESOURCE_NAME))
			return validateResourceName(getStringProperty(propertyName));
		if (propertyName.equals(URL_PATTERNS))
			return validateUrlPatterns((List) getProperty(propertyName));
		return super.doValidateProperty(propertyName);
	}

	private IStatus validateResourceName(String name) {
		if (name == null || name.trim().length() == 0) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_RESOURCE_NAME_EMPTY, new String[]{name});
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		SecurityConstraint sc = (SecurityConstraint) getProperty(SECURITY_CONSTRAINT);
		boolean exists = false;
		if (sc != null) {
			List collections = sc.getWebResourceCollections();
			int size = collections.size();
			for (int i = 0; i < size; i++) {
				String wrName = ((WebResourceCollection) collections.get(i)).getWebResourceName();
				if (name.equals(wrName)) {
					exists = true;
					break;
				}
			}
		}
		if (exists) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_RESOURCE_NAME_EXIST, new String[]{name});
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	private IStatus validateUrlPatterns(List urlList) {
		if (urlList == null || urlList.isEmpty()) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_URL_PATTERNS_EMPTY);
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		return WTPCommonPlugin.OK_STATUS;
	}
}