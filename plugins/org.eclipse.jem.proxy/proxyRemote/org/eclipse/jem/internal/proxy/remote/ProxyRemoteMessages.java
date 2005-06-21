package org.eclipse.jem.internal.proxy.remote;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: ProxyRemoteMessages.java,v $
 *  $Revision: 1.5 $  $Date: 2005/06/21 20:27:52 $ 
 */

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ProxyRemoteMessages {

	private static final String BUNDLE_NAME =
		"org.eclipse.jem.internal.proxy.remote.messages";	//$NON-NLS-1$
	private static final ResourceBundle RESOURCE_BUNDLE =
		ResourceBundle.getBundle(BUNDLE_NAME);

	private ProxyRemoteMessages() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}