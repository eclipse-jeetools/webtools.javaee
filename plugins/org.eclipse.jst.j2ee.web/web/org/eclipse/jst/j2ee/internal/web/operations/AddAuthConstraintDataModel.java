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
 * Created on Feb 9, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import java.util.Collections;

import org.eclipse.jst.j2ee.internal.common.operations.J2EEModelModifierOperationDataModel;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;

/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddAuthConstraintDataModel extends J2EEModelModifierOperationDataModel {
	/**
	 * Required - The name of the servlet.
	 * 
	 * @see String
	 */
	public static final String SECURITY_CONSTRAINT = "AddAuthConstraintOperationDataModel.SECURITY_CONSTRAINT"; //$NON-NLS-1$

	/**
	 * Required - The value of the initialization parameter.
	 * 
	 * @see String
	 */
	public static final String AUTH_DESCRIPTION = "AddAuthConstraintOperationDataModel.AUTH_DESCRIPTION"; //$NON-NLS-1$

	public static final String AUTH_ROLES = "AddAuthConstraintOperationDataModel.AUTH_ROLES"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new AddAuthConstraintOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(SECURITY_CONSTRAINT);
		addValidBaseProperty(AUTH_DESCRIPTION);
		addValidBaseProperty(AUTH_ROLES);
	}

	protected Object getDefaultProperty(String propertyName) {
		if (AUTH_ROLES.equals(propertyName)) {
			return Collections.EMPTY_LIST;
		}
		return super.getDefaultProperty(propertyName);
	}
}