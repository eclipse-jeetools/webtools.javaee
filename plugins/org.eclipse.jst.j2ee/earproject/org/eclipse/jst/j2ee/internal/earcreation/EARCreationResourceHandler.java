/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.earcreation;



import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class EARCreationResourceHandler {

	public static final String ADD_MODULE_MODULE_TYPE = "1"; //$NON-NLS-1$
	public static final String ADD_MODULE_MODULE_EXISTS = "2"; //$NON-NLS-1$
	public static final String ADD_MODULE_MODULE_CLOSED = "3"; //$NON-NLS-1$
	public static final String ADD_MODULE_MODULE_NULL = "4"; //$NON-NLS-1$
	public static final String ADD_PROJECT_URI_EXISTS = "5"; //$NON-NLS-1$
	public static final String ADD_PROJECT_NOT_JAVA = "6"; //$NON-NLS-1$
	public static final String ADD_PROJECT_UTIL_URI = "7"; //$NON-NLS-1$
	public static final String ADD_PROJECT_UTIL_MAPPED = "8"; //$NON-NLS-1$
	public static final String ADD_PROJECT_NOT_EAR = "9"; //$NON-NLS-1$
	public static final String ADD_PROJECT_URI_EMPTY = "10"; //$NON-NLS-1$
	public static final String NOT_AN_APP_CLIENT = "11"; //$NON-NLS-1$
	public static final String NOT_AN_EAR = "12"; //$NON-NLS-1$
	public static final String NOT_AN_EJB = "13"; //$NON-NLS-1$
	public static final String NOT_A_RAR = "14"; //$NON-NLS-1$
	public static final String NOT_A_WAR = "15"; //$NON-NLS-1$
	public static final String SERVER_TARGET_NOT_SUPPORT_EAR = "16"; //$NON-NLS-1$

	private static ResourceBundle fgResourceBundle;



	/**
	 * Returns the resource bundle used by all classes in this Project
	 */
	public static ResourceBundle getResourceBundle() {
		try {
			return ResourceBundle.getBundle("earcreation");//$NON-NLS-1$
		} catch (MissingResourceException e) {
			// does nothing - this method will return null and
			// getString(String, String) will return the key
			// it was called with
		}
		return null;
	}

	public static String getString(String key) {
		if (fgResourceBundle == null) {
			fgResourceBundle = getResourceBundle();
		}

		if (fgResourceBundle != null) {
			try {
				return fgResourceBundle.getString(key);
			} catch (MissingResourceException e) {
				return "!" + key + "!";//$NON-NLS-2$//$NON-NLS-1$
			}
		}
		return "!" + key + "!";//$NON-NLS-2$//$NON-NLS-1$
	}

	public static String getString(String key, Object[] args) {

		try {
			return MessageFormat.format(getString(key), args);
		} catch (IllegalArgumentException e) {
			return getString(key);
		}

	}

	public static void nlsConcatenationFinder() {
		// used to tag methods which have concatenated strings
	}

}