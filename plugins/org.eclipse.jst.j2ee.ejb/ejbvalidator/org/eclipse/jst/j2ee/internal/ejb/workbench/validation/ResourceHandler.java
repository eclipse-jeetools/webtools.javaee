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
package org.eclipse.jst.j2ee.internal.ejb.workbench.validation;

import java.util.MissingResourceException;
import java.util.logging.Level;

import org.eclipse.jst.j2ee.ejb.plugin.EjbPlugin;
import org.eclipse.jst.j2ee.model.validation.IEJBValidatorConstants;
import org.eclipse.jst.j2ee.model.validation.J2EEValidationResourceHandler;

import com.ibm.wtp.common.logger.LogEntry;
import com.ibm.wtp.common.logger.proxy.Logger;

/**
 * Insert the type's description here. Creation date: (1/22/01 4:13:13 PM)
 * 
 * @author: Administrator
 */
public final class ResourceHandler {
	public final static String VALIDATION_PROP_FILE_NAME = IEJBValidatorConstants.BUNDLE_NAME;
	private static ResourceHandler _handler = null;
	private static LogEntry logEntry;
	private static Logger logger;

	/**
	 * ResourceHandler constructor comment.
	 */
	private ResourceHandler() {
		super();
	}

	/**
	 * @return
	 */
	private static LogEntry getLogEntry() {
		if (logEntry == null) {
			logEntry = new LogEntry(IEJBValidatorConstants.BUNDLE_NAME);
		}
		logEntry.reset(); // reset the values so that we're not logging stale data
		return logEntry;
	}

	/**
	 * @return
	 */
	private static Logger getMsgLogger() {
		if (logger == null)
			logger = EjbPlugin.getPlugin().getMsgLogger();
		return logger;
	}

	public static String getExternalizedMessage(String key) {
		try {
			return J2EEValidationResourceHandler.getExternalizedMessage(VALIDATION_PROP_FILE_NAME, key, getHandler().getClass());
		} catch (NullPointerException exc) {

			if (getMsgLogger().isLoggingLevel(Level.SEVERE)) {
				LogEntry entry = getLogEntry();
				entry.setSourceID("ResourceHandler.getExternalizedMessage(String)"); //$NON-NLS-1$
				entry.setText(key);
				entry.setTargetException(exc);
				getMsgLogger().write(Level.SEVERE, entry);
			}
		}
		return ""; //$NON-NLS-1$
	}

	public static String getExternalizedMessage(String key, String[] parms) {
		String res = ""; //$NON-NLS-1$
		try {
			res = java.text.MessageFormat.format(getExternalizedMessage(key), parms);
		} catch (MissingResourceException exc) {

			if (getMsgLogger().isLoggingLevel(Level.SEVERE)) {
				LogEntry entry = getLogEntry();
				entry.setSourceID("ResourceHandler.getExternalizedMessage(String, String[])"); //$NON-NLS-1$
				entry.setTargetException(exc);
				entry.setText(key);
				getMsgLogger().write(Level.SEVERE, entry);
			}
		} catch (NullPointerException exc) {
			if (getMsgLogger().isLoggingLevel(Level.SEVERE)) {
				LogEntry entry = getLogEntry();
				entry.setSourceID("ResourceHandler.getExternalizedMessage(String, String[])"); //$NON-NLS-1$
				entry.setTargetException(exc);
				entry.setText(key);
				getMsgLogger().write(Level.SEVERE, entry);
			}
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