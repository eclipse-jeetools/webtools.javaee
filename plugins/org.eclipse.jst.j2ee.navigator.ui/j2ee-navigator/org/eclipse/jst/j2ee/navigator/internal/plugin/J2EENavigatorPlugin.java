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
package org.eclipse.jst.j2ee.navigator.internal.plugin;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.navigator.internal.J2EEEMFAdapterFactory;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.wst.common.internal.emfworkbench.WorkbenchResourceHelper;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class J2EENavigatorPlugin extends AbstractUIPlugin {
	//The shared instance.
	private static J2EENavigatorPlugin plugin;
	public static final String PLUGIN_ID = "org.eclipse.jst.j2ee.navigator.ui"; //$NON-NLS-1$


	/**
	 * The constructor.
	 */
	public J2EENavigatorPlugin() {
		super();
		plugin = this;
	}

	/**
	 * Returns the shared instance.
	 */
	public static J2EENavigatorPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns the workspace instance.
	 */
	public static IWorkspace getWorkspace() {
		return ResourcesPlugin.getWorkspace();
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
		WorkbenchResourceHelper.initializeFileAdapterFactory();
		IAdapterManager manager = Platform.getAdapterManager();
		manager.registerAdapters(new J2EEEMFAdapterFactory(), EObject.class);
	}
}
