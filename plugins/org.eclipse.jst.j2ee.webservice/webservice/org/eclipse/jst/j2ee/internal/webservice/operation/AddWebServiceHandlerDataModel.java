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
 * Created on Feb 27, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.webservice.operation;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.common.operations.J2EEModelModifierOperationDataModel;
import org.eclipse.jst.j2ee.webservice.wsclient.Handler;
import org.eclipse.jst.j2ee.webservice.wsclient.ServiceRef;
import org.eclipse.wst.common.framework.operation.WTPOperation;
import org.eclispe.wst.common.framework.plugin.WTPCommonPlugin;

/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddWebServiceHandlerDataModel extends J2EEModelModifierOperationDataModel {


	public static final String SERVICE_REF = "AddWebServiceHandlerOperationDataModel.SERVICE_REF"; //$NON-NLS-1$
	public static final String DISPLAY_NAME = "AddWebServiceHandlerOperationDataModel.DISPLAY_NAME"; //$NON-NLS-1$
	public static final String DESCRIPTION = "AddWebServiceHandlerOperationDataModel.DESCRIPTION"; //$NON-NLS-1$
	public static final String HANDLER_NAME = "AddWebServiceHandlerOperationDataModel.HANDLER_NAME"; //$NON-NLS-1$
	public static final String HANDLER_CLASS_NAME = "AddWebServiceHandlerOperationDataModel.HANDLER_CLASS_NAME"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new AddWebServiceHandlerOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(SERVICE_REF);
		addValidBaseProperty(DISPLAY_NAME);
		addValidBaseProperty(DESCRIPTION);
		addValidBaseProperty(HANDLER_NAME);
		addValidBaseProperty(HANDLER_CLASS_NAME);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(HANDLER_NAME))
			return validateParameterName(getStringProperty(propertyName));
		return super.doValidateProperty(propertyName);
	}

	private IStatus validateParameterName(String name) {
		if (name == null || name.trim().length() == 0) {
			String msg = WebServiceMessages.getResourceString(WebServiceMessages.ERR_HANDLER_DISPLAY_NAME_EMPTY, new String[]{name});
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		ServiceRef serviceRef = (ServiceRef) getProperty(SERVICE_REF);
		List handlers = serviceRef.getHandlers();
		boolean exists = false;
		if (handlers != null) {
			int size = handlers.size();
			for (int i = 0; i < size; i++) {
				String parName = ((Handler) handlers.get(i)).getHandlerName();
				if (name.equals(parName)) {
					exists = true;
					break;
				}
			}
		}
		if (exists) {
			String msg = WebServiceMessages.getResourceString(WebServiceMessages.ERR_HANDLER_DISPLAY_NAME_EXIST, new String[]{name});
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		return WTPCommonPlugin.OK_STATUS;
	}
}