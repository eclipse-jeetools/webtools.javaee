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
 * Created on Jun 22, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.common.operations;

import org.eclipse.wst.common.frameworks.operations.WTPOperation;


/**
 * @author jialin
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class RemoveSecurityRoleDataModel extends J2EEModelModifierOperationDataModel {
	public static final String ROLE_LIST = "RemoveSecurityRoleDataModel.ROLE_LIST"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new RemoveSecurityRoleOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(ROLE_LIST);
	}

	//	private IStatus validateRoleList(List prop) {
	//		if (prop == null || prop.isEmpty()) {
	//			String msg =
	// J2EECommonMessages.getResourceString(J2EECommonMessages.ERR_SECURITY_ROLE_LIST_EMPTY);
	//			return WTPCommonPlugin.createErrorStatus(msg);
	//		}
	//		return WTPCommonPlugin.OK_STATUS;
	//	}
}