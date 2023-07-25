/*******************************************************************************
 * Copyright (c) 2003, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.jca.internal.plugin;


import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.jca.internal.impl.ConnectorResourceFactory;
import org.eclipse.wst.common.componentcore.internal.impl.WTPResourceFactoryRegistry;
import org.eclipse.wst.common.frameworks.internal.WTPPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.IStatus;
import java.lang.Throwable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.MultiStatus;


/**
 * This is a top-level class of the j2ee plugin.
 * 
 * @see AbstractUIPlugin for additional information on UI plugins
 */

public class JcaPlugin extends WTPPlugin implements ResourceLocator {
	// Default instance of the receiver
	private static JcaPlugin inst;
	protected final IPath iconsFolder = new Path(Platform.getBundle(PLUGIN_ID).getEntry("icons").getPath()); //$NON-NLS-1$

	public static final String PLUGIN_ID = "org.eclipse.jst.j2ee.jca"; //$NON-NLS-1$
	// Validation part of the plugin
	//Global ResourceSet (somewhat global)

	public static final String[] ICON_DIRS = new String[]{"icons/full/obj16", //$NON-NLS-1$
				"icons/full/cview16", //$NON-NLS-1$
				"icons/full/ctool16", //$NON-NLS-1$
				"icons/full/clcl16", //$NON-NLS-1$
				"icons/full/ovr16", //$NON-NLS-1$
				"icons/full/extra", //$NON-NLS-1$
				"icons/full/wizban", //$NON-NLS-1$
				"icons", //$NON-NLS-1$
				""}; //$NON-NLS-1$

	/**
	 * Create the J2EE plugin and cache its default instance
	 */
	public JcaPlugin() {
		super();
		if (inst == null)
			inst = this;
	}


	/**
	 * Get the plugin singleton.
	 */
	static public JcaPlugin getDefault() {
		return inst;
	}

	/*
	 * Javadoc copied from interface.
	 */
	@Override
	public URL getBaseURL() {
		return getBundle().getEntry("/"); //$NON-NLS-1$
	}

	/**
	 * This gets a .gif from the icons folder.
	 */
	@Override
	public Object getImage(String key) {
		return J2EEPlugin.getImageURL(key, getBundle());
	}


	public static URL getInstallURL() {
		return getDefault().getBundle().getEntry("/"); //$NON-NLS-1$
	}

	/**
	 * Get the singleton instance.
	 */
	public static JcaPlugin getPlugin() {
		return inst;
	}

	/**
	 * Return the plugin directory location- the directory that all the plugins are located in (i.e.
	 * d:\installdir\plugin)
	 */
	public static IPath getPluginLocation(String pluginId) {
//		IExtensionRegistry registry = Platform.getExtensionRegistry();
		Bundle bundle = Platform.getBundle(pluginId);
		if (bundle != null) {
			try {
				IPath installPath = new Path(bundle.getEntry("/").toExternalForm()).removeTrailingSeparator(); //$NON-NLS-1$
				String installStr = Platform.asLocalURL(new URL(installPath.toString())).getFile();
				return new Path(installStr);
			} catch (IOException e) {
				//Ignore
			}
		}
		return null;
	}

	/*
	 * Javadoc copied from interface.
	 */
	@Override
	public String getString(String key) {
		return Platform.getResourceString(getBundle(), key);
	}

	/*
	 * Javadoc copied from interface.
	 */
	@Override
	public String getString(String key, Object[] substitutions) {
		return MessageFormat.format(getString(key), substitutions);
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
	
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		ConnectorResourceFactory.register(WTPResourceFactoryRegistry.INSTANCE);
	}	
	@Override
	public String getString(String key, boolean translate) {
		// TODO For now...  translate not supported
		return getString(key);
	}

	@Override
	public String getString(String key, Object[] substitutions, boolean translate) {
		// TODO For now...  translate not supported
		return getString(key,substitutions);
	}


	public static IStatus createStatus(int severity, String message, Throwable exception) {
		return new Status(severity, PLUGIN_ID, message, exception);
	}


	public static IStatus createStatus(int severity, String message) {
		return createStatus(severity, message, null);
	}


	public static void logError(String message, Throwable exception) {
		Platform.getLog(Platform.getBundle(PLUGIN_ID)).log( createStatus(IStatus.ERROR, message, exception));
	}


	public static void logError(String message, CoreException exception) {
		MultiStatus status = new MultiStatus(PLUGIN_ID,IStatus.ERROR,new IStatus[]{exception.getStatus()},message,exception);
		Platform.getLog(Platform.getBundle(PLUGIN_ID)).log( status );
	}

}
