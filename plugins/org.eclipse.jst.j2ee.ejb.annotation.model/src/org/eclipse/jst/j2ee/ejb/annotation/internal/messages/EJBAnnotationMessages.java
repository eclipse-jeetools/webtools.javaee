/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotation.internal.messages;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.wst.common.frameworks.internal.Messages;


public class EJBAnnotationMessages extends Messages {

	public static final String IMAGE_LOAD_ERROR = "0"; //$NON-NLS-1$
	

	
	private static final EJBAnnotationMessages INSTANCE = new EJBAnnotationMessages();


	private EJBAnnotationMessages() {
		super();
	}

	/**
	 * Returns the string from the resource bundle, or 'key' if not found.
	 */
	public static String getResourceString(String key) {
		return INSTANCE.doGetResourceString(key);
	}

	public static String getResourceString(String key, Object[] args) {
		return INSTANCE.doGetResourceString(key, args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.Messages#initializeBundle()
	 */
	protected void initializeBundle() {
		try {
			resourceBundle = ResourceBundle.getBundle("web_ui"); //$NON-NLS-1$
		} catch (MissingResourceException x) {
			// Do nothing
		}
	}

}