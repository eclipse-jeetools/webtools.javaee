/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: BeanInfoAdapterMessages.java,v $
 *  $Revision: 1.4 $  $Date: 2005/02/15 22:44:20 $ 
 */
package org.eclipse.jem.internal.beaninfo.adapters;


import java.util.MissingResourceException;
import java.util.ResourceBundle;
/**
 * 
 * 
 * @since 1.0.0
 */
public class BeanInfoAdapterMessages {

	private static final String BUNDLE_NAME = "org.eclipse.jem.internal.beaninfo.adapters.messages";	//$NON-NLS-1$

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
