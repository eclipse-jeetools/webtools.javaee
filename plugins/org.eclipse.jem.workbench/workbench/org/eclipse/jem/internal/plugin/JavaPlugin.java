/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.plugin;
/*
 * $RCSfile: JavaPlugin.java,v $ $Revision: 1.5.2.1 $ $Date: 2004/06/24 18:17:18 $
 */

import org.eclipse.core.runtime.*;
import org.osgi.framework.BundleContext;

import com.ibm.wtp.common.logger.proxy.Logger;
import com.ibm.wtp.emf.workbench.plugin.EMFWorkbenchPlugin;
import com.ibm.wtp.logger.proxyrender.EclipseLogger;

import org.eclipse.jem.internal.adapters.jdom.JavaJDOMAdapterFactory;
import org.eclipse.jem.internal.java.adapters.jdk.JavaJDKAdapterFactory;
import org.eclipse.jem.java.impl.JavaRefFactoryImpl;

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
		JavaRefFactoryImpl.setReflectionAdapterFactoryClass(JavaJDOMAdapterFactory.class);
		EMFWorkbenchPlugin.getPluginResourceSet().getAdapterFactories().add(new JavaJDKAdapterFactory());		
	}

}
