package org.eclipse.jem.internal.java.adapters.nls;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: ResourceHandler.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:12:30 $ 
 */
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.text.MessageFormat;

public class ResourceHandler {

	private static ResourceBundle fgResourceBundle;

	/**
	 * Returns the resource bundle used by all classes in this Project
	 */
	public static ResourceBundle getResourceBundle() {
		try {
			return ResourceBundle.getBundle("javaadapters");//$NON-NLS-1$
		} catch (MissingResourceException e) {
			// does nothing - this method will return null and
			// getString(String, String) will return the key
			// it was called with
		}
		return null;
	}
	public static String getString(String key) {
		if (fgResourceBundle == null) {
			fgResourceBundle= getResourceBundle();
		}
		
		if (fgResourceBundle != null) {
			try {
				return fgResourceBundle.getString(key);
			} catch (MissingResourceException e) {
				return "!" + key + "!";//$NON-NLS-2$//$NON-NLS-1$
			}
		} else {
			return "!" + key + "!";//$NON-NLS-2$//$NON-NLS-1$
		}
	}
public static String getString(String key, Object[] args) {

	try {return MessageFormat.format(getString(key), args);}
	catch (IllegalArgumentException e) {return getString(key);}

}
}




