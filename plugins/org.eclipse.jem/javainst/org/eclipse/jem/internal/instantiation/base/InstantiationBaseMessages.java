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
 *  $RCSfile: InstantiationBaseMessages.java,v $
 *  $Revision: 1.1 $  $Date: 2004/06/02 19:41:38 $ 
 */
package org.eclipse.jem.internal.instantiation.base;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author sri
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InstantiationBaseMessages {

	private static final String BUNDLE_NAME = "org.eclipse.jem.internal.instantiation.base.messages";//$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private InstantiationBaseMessages() {
	}

	public static String getString(String key) {
		// TODO Auto-generated method stub
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}