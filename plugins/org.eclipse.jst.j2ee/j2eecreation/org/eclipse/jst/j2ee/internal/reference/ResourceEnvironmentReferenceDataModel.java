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
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;


public class ResourceEnvironmentReferenceDataModel extends ReferenceDataModel {
	/**
	 * Required, type String
	 */
	public static final String TYPE = "ResourceEnvironmentReferenceDataModel.TYPE"; //$NON-NLS-1$

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
		super.initValidBaseProperties();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.reference.ReferenceDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		IStatus status = super.doValidateProperty(propertyName);
		if (TYPE.equals(propertyName)) {
			String temp = getStringProperty(TYPE);
			if (temp == null || temp.trim().equals("")) { //$NON-NLS-1$
				if (TYPE.equals(propertyName))
					return WTPCommonPlugin.createErrorStatus(J2EECreationResourceHandler.getString("ResourceEnvironmentReferenceDataModel.2")); //$NON-NLS-1$
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
		return new ResourceEnvironmentReferenceCreationOperation(this);
	}
}