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
 *  $RCSfile: BeanInfoAdapterMessages.java,v $
 *  $Revision: 1.1 $  $Date: 2004/05/24 23:23:31 $ 
 */
package org.eclipse.jem.internal.beaninfo.adapters;


import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class BeanInfoAdapterMessages {

	private static final String BUNDLE_NAME = "org.eclipse.jem.internal.beaninfo.adapter.messages";	//$NON-NLS-1$

	// Keys for messages/strings within the resource bundle.
	static public final String
		INTROSPECTFAILED = "INTROSPECT_FAILED_EXC_"; //$NON-NLS-1$
	private static final ResourceBundle RESOURCE_BUNDLE =
		ResourceBundle.getBundle(BUNDLE_NAME);

	private BeanInfoAdapterMessages() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}