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
 * Created on Mar 24, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.common.Listener;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.wst.common.framework.operation.WTPOperation;
import org.eclispe.wst.common.framework.plugin.WTPCommonPlugin;


/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddListenerDataModel extends AddServletFilterListenerCommonDataModel {
	public final static String[] LISTENER_INTERFACES = {"javax.servlet.ServletContextListener", //$NON-NLS-1$
				"javax.servlet.ServletContextAttributeListener", //$NON-NLS-1$
				"javax.servlet.http.HttpSessionListener", //$NON-NLS-1$
				"javax.servlet.http.HttpSessionAttributeListener", //$NON-NLS-1$
				"javax.servlet.ServletRequestListener", //$NON-NLS-1$
				"javax.servlet.ServletRequestAttributeListener" //$NON-NLS-1$
	};

	private List interfaceList;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new AddListenerOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
	}

	protected IStatus validateDisplayName(String prop) {
		WebApp webApp = (WebApp) getDeploymentDescriptorRoot();
		if (webApp == null)
			return WTPCommonPlugin.createErrorStatus(""); //$NON-NLS-1$
		if (webApp.getJ2EEVersionID() < J2EEVersionConstants.J2EE_1_4_ID) {
			return WTPCommonPlugin.OK_STATUS;
		}
		if (prop == null || prop.trim().length() == 0) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_DISPLAY_NAME_EMPTY);
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		List listeners = webApp.getListeners();
		boolean exists = false;
		if (listeners != null && !listeners.isEmpty()) {
			for (int i = 0; i < listeners.size(); i++) {
				String name = ((Listener) listeners.get(i)).getDisplayName();
				if (prop.equals(name))
					exists = true;
			}
		}
		if (exists) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_LISTENER_DISPLAY_NAME_EXIST, new String[]{prop});
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		//		if (propertyName.equals(USE_EXISTING_LISTENER_CLASS))
		//			return validateExistingClass(getBooleanProperty(propertyName));
		//		if (propertyName.equals(CLASS_NAME))
		//			return validateClassName(getStringProperty(propertyName));
		return super.doValidateProperty(propertyName);
	}

	protected IStatus validateClassName(String prop) {
		// this validation is only for using existing class
		if (!getBooleanProperty(USE_EXISTING_CLASS))
			return WTPCommonPlugin.OK_STATUS;
		// check for empty
		IStatus status = super.validateClassName(prop);
		if (!status.isOK())
			return status;
		// check for duplicate
		WebApp webApp = (WebApp) getDeploymentDescriptorRoot();
		List listeners = webApp.getListeners();
		if (listeners != null && listeners.size() > 0) {
			for (int i = 0; i < listeners.size(); i++) {
				Listener listener = (Listener) listeners.get(i);
				if (prop.equals(listener.getListenerClassName())) {
					String msg = WebMessages.getResourceString(WebMessages.ERR_LISTENER_CLASS_NAME_USED);
					return WTPCommonPlugin.createErrorStatus(msg);
				}
			}
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	public List getFilterInterfaces() {
		if (this.interfaceList == null) {
			this.interfaceList = new ArrayList();
			for (int i = 0; i < LISTENER_INTERFACES.length; i++) {
				this.interfaceList.add(LISTENER_INTERFACES[i]); //$NON-NLS-1$
			}
		}
		return this.interfaceList;
	}
}