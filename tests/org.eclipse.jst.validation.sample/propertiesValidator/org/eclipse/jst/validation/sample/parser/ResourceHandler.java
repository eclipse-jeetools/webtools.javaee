package org.eclipse.jst.validation.sample.parser;
/*
 * Licensed Material - Property of IBM 
 * (C) Copyright IBM Corp. 2002, 2003 - All Rights Reserved. 
 * US Government Users Restricted Rights - Use, duplication or disclosure 
 * restricted by GSA ADP Schedule Contract with IBM Corp. 
 *
 * DISCLAIMER OF WARRANTIES.
 * The following [enclosed] code is sample code created by IBM
 * Corporation. This sample code is not part of any standard or IBM
 * product and is provided to you solely for the purpose of assisting
 * you in the development of your applications.  The code is provided
 * "AS IS". IBM MAKES NO WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE, REGARDING THE FUNCTION OR PERFORMANCE OF
 * THIS CODE.  THIS CODE MAY CONTAIN ERRORS.  IBM shall not be liable
 * for any damages arising out of your use of the sample code, even
 * if it has been advised of the possibility of such damages.
 * 
 */

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * This class retrieves the Strings from the .properties file appropriate
 * for the machine's Locale.
 */
public final class ResourceHandler {
	private static ResourceBundle _bundle = null;
	
	private ResourceHandler() {
		super();
	}
	
	/**
	 * Return the resource bundle which contains the messages
	 * in the resource identified by bundleName.
	 */
	private static ResourceBundle getBundle(String bundleName) {
		if (_bundle == null) {
			try {
				_bundle = ResourceBundle.getBundle(bundleName);
			}
			catch (MissingResourceException exc) {
				_bundle = null;
				exc.printStackTrace();
			}
		}
		return _bundle;
	}

	/**
	 * Given the name of the bundle and the message id (key) into the
	 * bundle, return the message text identified by the id.
	 */	
	public static String getExternalizedMessage(String bundleName, String key) {
		try {
			ResourceBundle bundle = getBundle(bundleName);
			if (bundle == null) {
				return key;
			}

			return bundle.getString(key);
		}
		catch (NullPointerException exc) {
			exc.printStackTrace();
		}
		return key;
	}
	
	/**
	 * Given the name of the bundle, the message id (key) into the
	 * bundle, and parameters to be substituted into the java.text.MessageFormat's
	 * patterns, return the message with the parameters substituted in.
	 */	
	public static String getExternalizedMessage(String bundleName, String key, String[] parms) {
		String res = ""; //$NON-NLS-1$
		try {
			res = java.text.MessageFormat.format(getExternalizedMessage(bundleName, key), parms);
		}
		catch (MissingResourceException exc) {
			exc.printStackTrace();
		}
		catch (NullPointerException exc) {
			exc.printStackTrace();
		}
		return res;
	}
}
