/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.rar.ui.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.wst.common.framework.Messages;


public class JCAUIMessages extends Messages {

	public static final String IMAGE_LOAD_ERROR = "0"; //$NON-NLS-1$
	public static final String JCA_PROJECT_WIZ_TITLE = "1"; //$NON-NLS-1$
	public static final String JCA_VERSION_LBL = "2"; //$NON-NLS-1$
	public static final String JCA_PROJECT_MAIN_PG_TITLE = "3"; //$NON-NLS-1$
	public static final String JCA_PROJECT_MAIN_PG_DESC = "4"; //$NON-NLS-1$
	public static final String JCA_IMPORT_MAIN_PG_DESC = "5"; //$NON-NLS-1$
	public static final String JCA_IMPORT_MAIN_PG_TITLE = "6"; //$NON-NLS-1$
	public static final String JCA_FILE_LBL = "7"; //$NON-NLS-1$
	public static final String JCA_PROJECT_LBL = "8"; //$NON-NLS-1$
	public static final String JCA_EXPORT_MAIN_PG_TITLE = "9"; //$NON-NLS-1$
	public static final String JCA_EXPORT_MAIN_PG_DESC = "10"; //$NON-NLS-1$

	private static final JCAUIMessages INSTANCE = new JCAUIMessages();


	private JCAUIMessages() {
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
	 * @see org.eclipse.wst.common.framework.Messages#initializeBundle()
	 */
	protected void initializeBundle() {
		try {
			resourceBundle = ResourceBundle.getBundle("jca_ui"); //$NON-NLS-1$
		} catch (MissingResourceException x) {
		}
	}
}