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
package org.eclipse.jst.j2ee.internal.provider;


import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.jst.j2ee.internal.application.provider.ApplicationProvidersResourceHandler;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.ModulemapFactory;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.ModulemapPackage;



/**
 * @deprecated Leaving this in for now, to minimize breakage to all the providers; should delete
 *             when we regen all providers for mof5
 * 
 * This is the central singleton for the modulemap plugin.
 */
public class ModulemapEditPlugin extends Plugin {
	/**
	 * Keep track of the singleton.
	 */
	protected static ModulemapEditPlugin plugin;

	/**
	 * Keep track of the modulemap package.
	 */
	protected ModulemapPackage modulemapPackage;

	/**
	 * Create the instance.
	 */
	public ModulemapEditPlugin(IPluginDescriptor descriptor) {
		super(descriptor);

		// Remember the static instance.
		//
		plugin = this;
	}

	/**
	 * Get the one modulemap package.
	 */
	public ModulemapPackage getModulemapPackage() {
		return modulemapPackage;
	}

	/**
	 * Get the one modulemap factory.
	 */
	public ModulemapFactory getModulemapFactory() {
		return (ModulemapFactory) modulemapPackage.getEFactoryInstance();
	}

	/**
	 * Do initialization stuff here.
	 */
	public void startup() throws CoreException {
		super.startup();
		modulemapPackage = org.eclipse.jst.j2ee.internal.earcreation.modulemap.ModulemapFactoryImpl.getPackage();
	}

	/**
	 * Get the singleton instance.
	 */
	public static ModulemapEditPlugin getPlugin() {
		return plugin;
	}

	/**
	 * Get a .gif from the icons folder.
	 */
	public Object getImage(String key) {
		try {
			return new URL(getDescriptor().getInstallURL(), "icons/" + key + ".gif"); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (MalformedURLException exception) {
			System.out.println(ApplicationProvidersResourceHandler.getString("Failed_to_load_image_for_ERROR_") + key + "'"); //$NON-NLS-1$ //$NON-NLS-2$
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