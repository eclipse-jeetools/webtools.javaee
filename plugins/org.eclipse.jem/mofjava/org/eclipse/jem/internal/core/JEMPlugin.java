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
/*
 *  $RCSfile: JEMPlugin.java,v $
 *  $Revision: 1.6 $  $Date: 2005/01/07 20:51:26 $ 
 */
package org.eclipse.jem.internal.core;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jem.util.logger.proxyrender.EclipseLogger;


/**
 * org.eclipse.jem plugin
 */
public class JEMPlugin extends Plugin {

	private static JEMPlugin PLUGIN;
	private Logger logger;
	
	public JEMPlugin() {
		PLUGIN = this;
	}
	
	public static JEMPlugin getPlugin() {
		return PLUGIN;
	}

	
	/* (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		logger = EclipseLogger.getEclipseLogger(this);		
	}
	
	public Logger getLogger() {
		return logger;
	}

}
