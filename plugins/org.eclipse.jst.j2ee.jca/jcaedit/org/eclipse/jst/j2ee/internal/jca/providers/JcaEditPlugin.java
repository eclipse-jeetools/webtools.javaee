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
package org.eclipse.jst.j2ee.internal.jca.providers;



import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.jst.j2ee.jca.JcaFactory;
import org.eclipse.jst.j2ee.jca.JcaPackage;
import org.eclipse.jst.j2ee.jca.internal.impl.JcaFactoryImpl;


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
	public JcaEditPlugin(IPluginDescriptor descriptor) {
		super(descriptor);

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
	public void startup() throws CoreException {
		super.startup();
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
			return new URL(getDescriptor().getInstallURL(), "icons/" + key + ".gif"); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (MalformedURLException exception) {
			System.out.println("Failed to load image for '" + key + "'"); //$NON-NLS-1$ //$NON-NLS-2$
			exception.printStackTrace();
		}

		return null;
	}

	/**
	 * Get a translated string from the resource bundle.
	 */
	public String getString(String key) {
		return getDescriptor().getResourceBundle().getString(key);
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
