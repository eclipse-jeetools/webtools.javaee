/*******************************************************************************
 * Copyright (c) 2003, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.common.internal.annotations.ui;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import java.lang.Throwable;
import org.eclipse.core.runtime.CoreException;

/**
 * The main plugin class to be used in the desktop.
 */
public class UiPlugin extends AbstractUIPlugin {
	//the ID for this plugin (added automatically by logging quickfix)
	public static final String PLUGIN_ID = "org.eclipse.jst.common.annotations.ui"; //$NON-NLS-1$
	//The shared instance.
	private static UiPlugin plugin;
	//Resource bundle.
	private ResourceBundle resourceBundle;

	/**
	 * The constructor.
	 */
	public UiPlugin() {
		super();
		plugin = this;
		try {
			resourceBundle = ResourceBundle.getBundle("org.eclipse.wst.common.internal.annotations.ui.UiPluginResources"); //$NON-NLS-1$
		} catch (MissingResourceException x) {
			resourceBundle = null;
		}
	}

	/**
	 * Returns the shared instance.
	 */
	public static UiPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns the string from the plugin's resource bundle, or 'key' if not found.
	 */
	public static String getResourceString(String key) {
		ResourceBundle bundle = UiPlugin.getDefault().getResourceBundle();
		try {
			return (bundle != null) ? bundle.getString(key) : key;
		} catch (MissingResourceException e) {
			return key;
		}
	}

	/**
	 * Returns the plugin's resource bundle,
	 */
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	public static IStatus createStatus(int severity, String message, Throwable exception) {
		return new Status(severity, PLUGIN_ID, message, exception);
	}

	public static IStatus createStatus(int severity, String message) {
		return createStatus(severity, message, null);
	}

	public static void logError(Throwable exception) {
		Platform.getLog(Platform.getBundle(PLUGIN_ID)).log( createStatus(IStatus.ERROR, exception.getMessage(), exception));
	}

	public static void logError(CoreException exception) {
		Platform.getLog(Platform.getBundle(PLUGIN_ID)).log( exception.getStatus() );
	}
}