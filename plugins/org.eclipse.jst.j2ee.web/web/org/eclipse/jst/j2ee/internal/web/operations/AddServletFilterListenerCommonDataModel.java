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
 * Created on Mar 29, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.common.J2EECommonMessages;
import org.eclipse.jst.j2ee.common.operations.J2EEModelModifierOperationDataModel;
import org.eclipse.wst.common.framework.operation.WTPOperation;
import org.eclispe.wst.common.framework.plugin.WTPCommonPlugin;

/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public abstract class AddServletFilterListenerCommonDataModel extends J2EEModelModifierOperationDataModel {

	public static final String DISPLAY_NAME = "AddServletFilterListenerCommonDataModel.DISPLAY_NAME"; //$NON-NLS-1$
	public static final String DESCRIPTION = "AddServletFilterListenerCommonDataModel.DESCRIPTION"; //$NON-NLS-1$
	public static final String USE_EXISTING_CLASS = "AddServletFilterListenerCommonDataModel.USE_EXISTING_CLASS"; //$NON-NLS-1$
	public static final String CLASS_NAME = "AddServletFilterListenerCommonDataModel.CLASS_NAME"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(DISPLAY_NAME);
		addValidBaseProperty(DESCRIPTION);
		addValidBaseProperty(USE_EXISTING_CLASS);
		addValidBaseProperty(CLASS_NAME);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(DISPLAY_NAME))
			return validateDisplayName(getStringProperty(propertyName));
		if (propertyName.equals(USE_EXISTING_CLASS))
			return validateExistingClass(getBooleanProperty(propertyName));
		if (propertyName.equals(CLASS_NAME))
			return validateClassName(getStringProperty(propertyName));
		return super.doValidateProperty(propertyName);
	}

	protected abstract IStatus validateDisplayName(String prop);

	private IStatus validateExistingClass(boolean prop) {
		if (prop) {
			return validateClassName(getStringProperty(CLASS_NAME));
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	protected IStatus validateClassName(String prop) {
		if (!getBooleanProperty(USE_EXISTING_CLASS))
			return WTPCommonPlugin.OK_STATUS;
		// check for empty
		if (prop == null || prop.trim().length() == 0) {
			String msg = J2EECommonMessages.getResourceString(J2EECommonMessages.ERR_JAVA_CLASS_NAME_EMPTY, new String[]{prop});
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		return WTPCommonPlugin.OK_STATUS;
	}
}