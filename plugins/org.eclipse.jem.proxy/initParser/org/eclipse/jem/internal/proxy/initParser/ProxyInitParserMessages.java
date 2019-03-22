/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.proxy.initParser;
/*


 */


import java.util.MissingResourceException;
import java.util.ResourceBundle;
/**
 * General Proxy NLS Constants
 * Creation date: (4/13/00 10:46:58 AM)
 * @author: Administrator
 */
public class ProxyInitParserMessages {
	// Resource Bundle to use for basic Proxy NLS resources.
	static private final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("org.eclipse.jem.internal.proxy.initParser.messages"); //$NON-NLS-1$

	// Keys for messages/strings within the resource bundle.
	static public final String
		UNEXPECTED_TOKEN = "UnexpectedToken_EXC_", //$NON-NLS-1$
		STATEMENT_UNEXPECTED_EXECUTION = "Statement.UnexpectedExecution_EXC_", //$NON-NLS-1$
		STATEMENT_UNEXPECTED_EVALUATION = "Statement.UnexpectedEvaluation_EXC_"		; //$NON-NLS-1$
		
	private ProxyInitParserMessages() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}		

}
