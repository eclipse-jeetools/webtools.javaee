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

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.wst.common.frameworks.internal.Messages;


/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class WebServiceMessages extends Messages {
	// Servlet validation messages.
	public static final String ERR_HANDLER_DISPLAY_NAME_EMPTY = "ERR_HANDLER_DISPLAY_NAME_EMPTY"; //$NON-NLS-1$
	public static final String ERR_HANDLER_DISPLAY_NAME_EXIST = "ERR_HANDLER_DISPLAY_NAME_EXIST"; //$NON-NLS-1$
	public static final String ERR_HANDLER_PARAM_NAME_EMPTY = "ERR_HANDLER_PARAM_NAME_EMPTY"; //$NON-NLS-1$
	public static final String ERR_HANDLER_NAMESPACE_URL_EMPTY = "ERR_HANDLER_NAMESPACE_URL_EMPTY"; //$NON-NLS-1$
	public static final String ERR_HANDLER_PARAM_NAME_EXISTS = "ERR_HANDLER_PARAM_NAME_EXISTS"; //$NON-NLS-1$
	public static final String ERR_HANDLER_NAMESPACE_URL_EXISTS = "ERR_HANDLER_NAMESPACE_URL_EXISTS"; //$NON-NLS-1$

	private static final WebServiceMessages INSTANCE = new WebServiceMessages();

	/**
	 * Returns the string from the resource bundle, or 'key' if not found.
	 */
	public static String getResourceString(String key) {
		return INSTANCE.doGetResourceString(key);
	}

	public static String getResourceString(String key, Object[] args) {
		return INSTANCE.doGetResourceString(key, args);
	}

	private WebServiceMessages() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.Messages#initializeBundle()
	 */
	protected void initializeBundle() {
		try {
			resourceBundle = ResourceBundle.getBundle("webservice"); //$NON-NLS-1$
		} catch (MissingResourceException x) {
			//Ignore
		}
	}
}