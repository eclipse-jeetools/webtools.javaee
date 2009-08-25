/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.workbench.validation;

import java.util.MissingResourceException;

import org.eclipse.jst.j2ee.ejb.internal.plugin.EjbPlugin;
import org.eclipse.jst.j2ee.model.internal.validation.IEJBValidatorConstants;
import org.eclipse.jst.j2ee.model.internal.validation.J2EEValidationResourceHandler;

/**
 * Insert the type's description here. Creation date: (1/22/01 4:13:13 PM)
 * 
 * @author: Administrator
 */
public final class ResourceHandler {
	public final static String VALIDATION_PROP_FILE_NAME = IEJBValidatorConstants.BUNDLE_NAME;
	private static ResourceHandler _handler = null;

	/**
	 * ResourceHandler constructor comment.
	 */
	private ResourceHandler() {
		super();
	}

	public static String getExternalizedMessage(String key) {
		try {
			return J2EEValidationResourceHandler.getExternalizedMessage(VALIDATION_PROP_FILE_NAME, key, getHandler().getClass());
		} catch (NullPointerException exc) {

			EjbPlugin.log(EjbPlugin.createErrorStatus(key, exc));
		}
		return ""; //$NON-NLS-1$
	}

	public static String getExternalizedMessage(String key, String[] parms) {
		String res = ""; //$NON-NLS-1$
		try {
			res = java.text.MessageFormat.format(getExternalizedMessage(key), (Object[])parms);
		} catch (MissingResourceException exc) {

			EjbPlugin.log(EjbPlugin.createErrorStatus(key, exc));
		} catch (NullPointerException exc) {
			EjbPlugin.log(EjbPlugin.createErrorStatus(key, exc));
		}
		return res;
	}

	public static ResourceHandler getHandler() {
		if (_handler == null) {
			_handler = new ResourceHandler();
		}
		return _handler;
	}
}
