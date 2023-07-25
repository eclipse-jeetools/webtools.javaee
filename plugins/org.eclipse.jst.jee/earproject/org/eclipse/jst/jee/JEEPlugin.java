/*******************************************************************************
 * Copyright (c) 2005, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee;

import org.eclipse.wst.common.frameworks.internal.WTPPlugin;
import org.osgi.framework.BundleContext;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import java.lang.Throwable;
import org.eclipse.core.runtime.CoreException;

/**
 * The activator class controls the plug-in life cycle
 */
public class JEEPlugin extends WTPPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.jst.jee"; //$NON-NLS-1$

	// The shared instance
	private static JEEPlugin plugin;
	
	private JEEPreferences preferences = null;
	
	/**
	 * The constructor
	 */
	public JEEPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static JEEPlugin getDefault() {
		return plugin;
	}

	   /*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.WTPPlugin#getPluginID()
	 */
	@Override
	public String getPluginID() {
		return PLUGIN_ID;
	}
	
	/**
	 * @return Returns the preferences.
	 */
	public JEEPreferences getJEEPreferences() {
		if (this.preferences == null)
			this.preferences = new JEEPreferences(this);
		return this.preferences;
	}
	
	@Override
	protected void initializeDefaultPluginPreferences() {
		getJEEPreferences().initializeDefaultPreferences();
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
