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
package org.eclipse.jem.internal.beaninfo.ui;
/*
 *  $RCSfile: BeanInfoUIMessages.java,v $
 *  $Revision: 1.4 $  $Date: 2005/06/21 19:08:31 $ 
 */

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class BeanInfoUIMessages {
	private static final ResourceBundle RESOURCE_BUNDLE =
		ResourceBundle.getBundle("org.eclipse.jem.internal.beaninfo.ui.messages");

	private BeanInfoUIMessages() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
