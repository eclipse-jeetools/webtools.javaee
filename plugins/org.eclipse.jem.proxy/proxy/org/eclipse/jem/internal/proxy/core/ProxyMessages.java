package org.eclipse.jem.internal.proxy.core;
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
 *  $RCSfile: ProxyMessages.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ProxyMessages {

	private static final String BUNDLE_NAME = "org.eclipse.jem.internal.proxy.core.messages";	//$NON-NLS-1$

	// Keys for messages/strings within the resource bundle.
	static public final String
		CLASSCAST_INCORRECTTYPE = "ClassCast_EXC_.IncorrectType", //$NON-NLS-1$
		UNEXPECTED_EXCEPTION = "UnexpectedException_EXC_", //$NON-NLS-1$
		PROXYFACTORY_NOBEANPROXY = "ProxyFactory_EXC_.NoBeanProxyFactory", //$NON-NLS-1$
		PROXYFACTORY_NOBEANTYPEPROXY = "ProxyFactory_EXC_.NoBeanTypeProxyFactory", //$NON-NLS-1$
		NOT_JAVA_PROJECT = "Not_Java_Project_WARN_", //$NON-NLS-1$
		NO_VM = "No_VM_WARN_", //$NON-NLS-1$
		NO_IMPLEMENTATION = "No_Implementation_WARN_"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE =
		ResourceBundle.getBundle(BUNDLE_NAME);

	private ProxyMessages() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}