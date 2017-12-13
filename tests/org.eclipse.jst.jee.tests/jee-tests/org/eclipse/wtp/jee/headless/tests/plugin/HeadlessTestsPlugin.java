/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.wtp.jee.headless.tests.plugin;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.wst.common.tests.DataModelVerifierFactory;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers.DataModelVerifierListGenerator;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class HeadlessTestsPlugin extends Plugin {
	//The shared instance.
	private static HeadlessTestsPlugin plugin;
	//Resource bundle.
	private ResourceBundle resourceBundle;
	
	/**
	 * The constructor.
	 */
	public HeadlessTestsPlugin() {
		super();
		plugin = this;
		try {
			resourceBundle= ResourceBundle.getBundle("org.eclipse.wtp.jee.wb.tests.TestsPluginResources");
		} catch (MissingResourceException x) {
			resourceBundle = null;
		}
	}

	/**
	 * Returns the shared instance.
	 */
	public static HeadlessTestsPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns the workspace instance.
	 */
	public static IWorkspace getWorkspace() {
		return ResourcesPlugin.getWorkspace();
	}

	/**
	 * Returns the string from the plugin's resource bundle,
	 * or 'key' if not found.
	 */
	public static String getResourceString(String key) {
		ResourceBundle bundle= getDefault().getResourceBundle();
		try {
			return (bundle!=null ? bundle.getString(key) : key);
		} catch (MissingResourceException e) {
			return key;
		}
	}
	
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		
		//needed so that jee operation tests will verify correctly
		DataModelVerifierFactory.getInstance().addToDataModelVerifiersMap(DataModelVerifierListGenerator.getVerifiers());
	}
	
	/**
	 * Returns the plugin's resource bundle,
	 */
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}
}
