package org.eclipse.jem.internal.plugin;
/***************************************************************************************************
 * Copyright (c) 2001, 2003 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
/*
 * $RCSfile: JavaPlugin.java,v $ $Revision: 1.4 $ $Date: 2004/02/24 19:33:35 $
 */

import org.eclipse.core.runtime.*;

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

	private static JavaPlugin inst;
	private Logger logger;

	/**
	 * Create the Java plugin and cache its default instance
	 */
	public JavaPlugin(IPluginDescriptor descriptor) {
		super(descriptor);
		inst = this;
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
		return inst;
	}

	public void startup() throws CoreException {
		JavaRefFactoryImpl.setReflectionAdapterFactoryClass(JavaJDOMAdapterFactory.class);
		EMFWorkbenchPlugin.getPluginResourceSet().getAdapterFactories().add(new JavaJDKAdapterFactory());
	}

}
