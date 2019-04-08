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
package org.eclipse.jst.j2ee.internal.jca.providers;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.jst.j2ee.jca.JcaFactory;
import org.eclipse.jst.j2ee.jca.JcaPackage;
import org.eclipse.jst.j2ee.jca.internal.impl.JcaFactoryImpl;
import org.eclipse.jst.j2ee.jca.internal.plugin.JcaPlugin;
import org.osgi.framework.BundleContext;

/**
 * This is the central singleton for the jca plugin.
 */
public class JcaEditPlugin extends Plugin {
	/**
	 * Keep track of the singleton.
	 */
	protected static JcaEditPlugin plugin;

	/**
	 * Keep track of the jca package.
	 */
	protected JcaPackage jcaPackage;

	/**
	 * Create the instance.
	 */
	public JcaEditPlugin() {
		super();

		// Remember the static instance.
		//
		plugin = this;
	}

	/**
	 * Get the one jca package.
	 */
	public JcaPackage getJcaPackage() {
		return jcaPackage;
	}

	/**
	 * Get the one jca factory.
	 */
	public JcaFactory getJcaFactory() {
		return (JcaFactory) jcaPackage.getEFactoryInstance();
	}

	/**
	 * Do initialization stuff here.
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		jcaPackage = JcaFactoryImpl.getPackage();
	}

	/**
	 * Get the singleton instance.
	 */
	public static JcaEditPlugin getPlugin() {
		return plugin;
	}

	/**
	 * Get a .gif from the icons folder.
	 */
	public Object getImage(String key) {
		try {
			return new URL(getBundle().getEntry("/"), "icons/" + key + ".gif"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		} catch (MalformedURLException c) {
			JcaPlugin.logError("Failed to load image for '" + key + "'", c);//$NON-NLS-1$ //$NON-NLS-2$
		}

		return null;
	}

	/**
	 * Get a translated string from the resource bundle.
	 */
	public String getString(String key) {
		return Platform.getResourceBundle(getBundle()).getString(key);
	}

	/**
	 * Get a translated string from the resource bundle, with an object substitution.
	 */
	public String getString(String key, Object s1) {
		return MessageFormat.format(getString(key), new Object[]{s1});
	}

	/**
	 * Get a translated string from the resource bundle, with two object substitutions.
	 */
	public String getString(String key, Object s1, Object s2) {
		return MessageFormat.format(getString(key), new Object[]{s1, s2});
	}

	/**
	 * Get a translated string from the resource bundle, with three object substitutions.
	 */
	public String getString(String key, Object s1, Object s2, Object s3) {
		return MessageFormat.format(getString(key), new Object[]{s1, s2, s3});
	}
}
