/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: ProxyRemoteUIMessages.java,v $
 *  $Revision: 1.1 $  $Date: 2004/03/04 16:14:29 $ 
 */
package org.eclipse.jem.internal.ui.proxy.remote;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
 
/**
 * Message for Proxy Remote UI
 * 
 * @since 1.0.0
 */
public class ProxyRemoteUIMessages {

	private static final String BUNDLE_NAME =
		"org.eclipse.jem.internal.ui.proxy.remote.messages";	//$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE =
		ResourceBundle.getBundle(BUNDLE_NAME);

	private ProxyRemoteUIMessages() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
