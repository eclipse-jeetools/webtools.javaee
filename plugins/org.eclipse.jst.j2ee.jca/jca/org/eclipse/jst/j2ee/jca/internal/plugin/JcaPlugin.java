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
package org.eclipse.jst.j2ee.jca.internal.plugin;


import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.MissingResourceException;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.IPluginRegistry;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.wst.common.frameworks.internal.WTPPlugin;


/**
 * This is a top-level class of the j2ee plugin.
 * 
 * @see AbstractUIPlugin for additional information on UI plugins
 */

public class JcaPlugin extends WTPPlugin implements ResourceLocator {
	// Default instance of the receiver
	private static JcaPlugin inst;
	protected final IPath iconsFolder = new Path(getDescriptor().getInstallURL().getFile()).append("icons"); //$NON-NLS-1$

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
	public JcaPlugin(IPluginDescriptor descriptor) {
		super(descriptor);
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
	public URL getBaseURL() {
		return getDescriptor().getInstallURL();
	}

	/**
	 * This gets a .gif from the icons folder.
	 */
	public Object getImage(String key) {
		return J2EEPlugin.getImageURL(key, getDescriptor());
	}


	public static URL getInstallURL() {
		return getDefault().getDescriptor().getInstallURL();
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
		IPluginRegistry registry = Platform.getPluginRegistry();
		IPluginDescriptor pd = registry.getPluginDescriptor(pluginId);
		if (pd != null) {
			try {
				IPath installPath = new Path(pd.getInstallURL().toExternalForm()).removeTrailingSeparator();
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
	public String getString(String key) {
		try {
			return getDescriptor().getResourceBundle().getString(key);
		} catch (MissingResourceException ex) {
			//TODO Don't throw error - just show unresolved key
			//Logger.getLogger().logError(ex);
			return '!' + key + '!';
		}
	}

	/*
	 * Javadoc copied from interface.
	 */
	public String getString(String key, Object[] substitutions) {
		return MessageFormat.format(getString(key), substitutions);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.WTPPlugin#getPluginID()
	 */
	public String getPluginID() {
		return PLUGIN_ID;
	}
}