/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.project.operations;


import org.eclipse.jst.j2ee.internal.reference.ReferenceDataModel;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;

public class SecurityRoleReferenceDataModel extends ReferenceDataModel {
	/**
	 * Required, type String
	 */
	public static final String LINK = "SecurityRoleReferenceDataModel.LINK"; //$NON-NLS-1$

	/**
	 * Required, type String
	 */
	public static final String ALLOW_SET_LINK = "SecurityRoleReferenceDataModel.ALLOW_SET_LINK"; //$NON-NLS-1$

	protected void init() {
		super.init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		addValidBaseProperty(LINK);
		addValidBaseProperty(ALLOW_SET_LINK);
		super.initValidBaseProperties();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new SecurityRoleReferenceCreationOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultProperty(java.lang.String)
	 */
	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(ALLOW_SET_LINK)) {
			return Boolean.TRUE;
		}
		return super.getDefaultProperty(propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.reference.ReferenceDataModel#doSetProperty(java.lang.String,
	 *      java.lang.Object)
	 */
	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		if (propertyName.equals(LINK) && !isSet(REF_NAME)) {
			setProperty(REF_NAME, propertyValue);
		}
		return super.doSetProperty(propertyName, propertyValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#basicIsEnabled(java.lang.String)
	 */
	protected Boolean basicIsEnabled(String propertyName) {
		if (propertyName.equals(LINK)) {
			if (getBooleanProperty(ALLOW_SET_LINK))
				return Boolean.TRUE;
			return Boolean.FALSE;
		}
		return super.basicIsEnabled(propertyName);
	}

}