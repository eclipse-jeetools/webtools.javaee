/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: ProxyUIMessages.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 23:02:35 $ 
 */
package org.eclipse.jem.internal.ui.proxy;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
 
/**
 * Message for Proxy Remote UI
 * 
 * @since 1.0.0
 */
public class ProxyUIMessages {

	private static final String BUNDLE_NAME =
		"org.eclipse.jem.internal.ui.proxy.messages";	//$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE =
		ResourceBundle.getBundle(BUNDLE_NAME);

	private ProxyUIMessages() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
