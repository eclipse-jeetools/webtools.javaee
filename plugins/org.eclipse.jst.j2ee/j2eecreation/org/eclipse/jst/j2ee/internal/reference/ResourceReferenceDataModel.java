/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.reference;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.internal.project.J2EECreationResourceHandler;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;


public class ResourceReferenceDataModel extends ReferenceDataModel {
	/**
	 * Required, type String
	 */
	public static final String TYPE = "ResourseReferenceDataModel.TYPE"; //$NON-NLS-1$
	/**
	 * Required, type String
	 */
	public static final String AUTHENTICATION = "ResourseReferenceDataModel.AUTHENTICATION"; //$NON-NLS-1$
	/**
	 * Required, type String
	 */
	public static final String SHARING_SCOPE = "ResourseReferenceDataModel.SHARING_SCOPE"; //$NON-NLS-1$

	protected void init() {
		super.init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		addValidBaseProperty(TYPE);
		addValidBaseProperty(AUTHENTICATION);
		addValidBaseProperty(SHARING_SCOPE);
		super.initValidBaseProperties();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.reference.ReferenceDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		IStatus status = super.doValidateProperty(propertyName);

		if (TYPE.equals(propertyName) || AUTHENTICATION.equals(propertyName)) {
			String temp = getStringProperty(propertyName);
			if (temp == null || temp.trim().equals("")) { //$NON-NLS-1$
				if (TYPE.equals(propertyName)) {
					return WTPCommonPlugin.createErrorStatus(J2EECreationResourceHandler.getString("ResourceReferenceDataModel_UI_0")); //$NON-NLS-1$
				} else if (AUTHENTICATION.equals(propertyName)) {
					return WTPCommonPlugin.createErrorStatus(J2EECreationResourceHandler.getString("ResourceReferenceDataModel_UI_1")); //$NON-NLS-1$
				}
			}
		}
		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new ResourceReferenceCreationOperation(this);
	}

}