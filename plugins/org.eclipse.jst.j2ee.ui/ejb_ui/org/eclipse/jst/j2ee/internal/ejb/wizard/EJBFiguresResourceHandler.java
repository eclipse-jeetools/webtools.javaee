/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on May 24, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.ejb.wizard;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.wst.common.framework.Messages;


/**
 * @author vijayb
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class EJBFiguresResourceHandler extends Messages {
	private static EJBFiguresResourceHandler INSTANCE = new EJBFiguresResourceHandler();

	/**
	 * The constructor.
	 */
	private EJBFiguresResourceHandler() {
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
	 * @see org.eclipse.wst.common.framework.Messages#initializeBundle()
	 */
	protected void initializeBundle() {
		try {
			resourceBundle = ResourceBundle.getBundle("ejb_figures"); //$NON-NLS-1$
		} catch (MissingResourceException x) {
		}
	}

}