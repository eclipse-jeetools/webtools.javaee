/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.plugin;
/*
 * $RCSfile: JavaPlugin.java,v $ $Revision: 1.12 $ $Date: 2006/05/15 23:12:54 $
 */

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

import org.eclipse.jem.internal.adapters.jdom.JavaJDOMAdapterFactory;
import org.eclipse.jem.internal.java.adapters.jdk.JavaJDKAdapterFactory;
import org.eclipse.jem.java.util.JavaContext;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jem.util.logger.proxyrender.EclipseLogger;
import org.eclipse.jem.util.plugin.JEMUtilPlugin;

/**
 * This is a top-level class of the java plugin tool.
 * 
 * @see AbstractUIPlugin for additional information on UI plugins
 */

public class JavaPlugin extends Plugin {

	private static JavaPlugin INSTANCE;
	private Logger logger;

	/**
	 * Create the Java plugin and cache its default instance
	 */
	public JavaPlugin() {
		INSTANCE = this;
	}

	public Logger getLogger() {
		if (logger == null)
			logger = EclipseLogger.getEclipseLogger(this);
		return logger;
	}

	/**
	 * Get the plugin singleton.
	 */
	static public JavaPlugin getDefault() {
		return INSTANCE;
	}

		/* (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		INSTANCE = this;
		JavaContext.setReflectionAdapterFactoryClass(JavaJDOMAdapterFactory.class);
		JEMUtilPlugin.getPluginResourceSet().getAdapterFactories().add(new JavaJDKAdapterFactory());		
	}

}
