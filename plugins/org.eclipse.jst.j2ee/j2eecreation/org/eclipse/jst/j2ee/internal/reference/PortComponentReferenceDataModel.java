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
 * Created on Feb 12, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.reference;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.internal.common.operations.J2EEModelModifierOperationDataModel;
import org.eclipse.jst.j2ee.internal.project.J2EECreationResourceHandler;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;


/**
 * @author dfholttp
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class PortComponentReferenceDataModel extends J2EEModelModifierOperationDataModel {
	/**
	 * Required, type String
	 */
	public static final String OWNER = "PortComponentReferenceDataModel.OWNER"; //$NON-NLS-1$
	/**
	 * Required, type String
	 */
	public static final String SERVICE_ENDPOINT_INTERFACE = "PortComponentReferenceDataModel.SERVICE_ENDPOINT_INTERFACE"; //$NON-NLS-1$
	/**
	 * Required, type String
	 */
	public static final String LINK = "PortComponentReferenceDataModel.LINK"; //$NON-NLS-1$


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		addValidBaseProperty(OWNER);
		addValidBaseProperty(SERVICE_ENDPOINT_INTERFACE);
		addValidBaseProperty(LINK);
		super.initValidBaseProperties();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		IStatus status = super.doValidateProperty(propertyName);
		if (SERVICE_ENDPOINT_INTERFACE.equals(propertyName)) {
			String temp = getStringProperty(propertyName);
			if (temp == null || temp.trim().equals("")) { //$NON-NLS-1$
				return WTPCommonPlugin.createErrorStatus(J2EECreationResourceHandler.getString("PortComponentReferenceDataModel_ERROR_4")); //$NON-NLS-1$
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
		return new PortComponentReferenceCreationOperation(this);
	}

}