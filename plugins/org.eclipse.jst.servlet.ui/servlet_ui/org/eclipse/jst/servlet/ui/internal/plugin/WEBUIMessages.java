/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.servlet.ui.internal.plugin;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.wst.common.frameworks.internal.Messages;


public class WEBUIMessages extends Messages {

	public static final String IMAGE_LOAD_ERROR = "0"; //$NON-NLS-1$
	public static final String WEB_PROJECT_WIZ_TITLE = "1"; //$NON-NLS-1$
	public static final String WEB_VERSION_LBL = "2"; //$NON-NLS-1$
	public static final String WEB_PROJECT_MAIN_PG_TITLE = "3"; //$NON-NLS-1$
	public static final String WEB_PROJECT_MAIN_PG_DESC = "4"; //$NON-NLS-1$
	public static final String EJB_PROJECT_LBL = "5"; //$NON-NLS-1$
	public static final String WEB_FILE_LBL = "6"; //$NON-NLS-1$
	public static final String WEB_IMPORT_MAIN_PG_DESC = "7"; //$NON-NLS-1$
	public static final String WEB_IMPORT_MAIN_PG_TITLE = "8"; //$NON-NLS-1$
	public static final String WEB_EXPORT_MAIN_PG_TITLE = "9"; //$NON-NLS-1$
	public static final String WEB_EXPORT_MAIN_PG_DESC = "10"; //$NON-NLS-1$
	public static final String WEB_IMPORT_CONTEXT_ROOT = "11"; //$NON-NLS-1$

	private static final WEBUIMessages INSTANCE = new WEBUIMessages();


	private WEBUIMessages() {
		super();
	}

	/**
	 * Returns the string from the resource bundle, or 'key' if not found.
	 */
	public static String getResourceString(String key) {
		return INSTANCE.doGetResourceString(key);
	}

	public static String getResourceString(String key, Object[] args) {
		return INSTANCE.doGetResourceString(key, args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.Messages#initializeBundle()
	 */
	protected void initializeBundle() {
		try {
			resourceBundle = ResourceBundle.getBundle("web_ui"); //$NON-NLS-1$
		} catch (MissingResourceException x) {
		}
	}

}