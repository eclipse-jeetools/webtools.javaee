/***************************************************************************************************
 * Copyright (c) 2003, 2019 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.client.actions;



import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class AppClientArchiveUIResourceHandler {

	private static ResourceBundle fgResourceBundle;

	/**
	 * Returns the resource bundle used by all classes in this Project
	 */
	public static ResourceBundle getResourceBundle() {
		try {
			return ResourceBundle.getBundle("appclientarchiveui");//$NON-NLS-1$
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
}