/*******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: JEMUIPlugin.java,v $
 *  $Revision: 1.5 $  $Date: 2005/08/24 21:07:12 $ 
 */
package org.eclipse.jem.internal.ui.core;


import org.eclipse.ui.plugin.AbstractUIPlugin;

import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jem.util.logger.proxyrender.EclipseLogger;

 
/**
 * 
 * @since 1.0.0
 */
public class JEMUIPlugin extends AbstractUIPlugin {

	private static JEMUIPlugin PLUGIN;
	public static final String PI_BEANINFO_UI = "org.eclipse.jem.internal.beaninfo.ui";	// Key for dialog settings.	 //$NON-NLS-1$
	
	/**
	 * @param descriptor
	 * 
	 * @since 1.0.0
	 */
	public JEMUIPlugin() {
		PLUGIN = this;
	}
	
	/**
	 * Return the plugin.
	 * 
	 * @return
	 * 
	 * @since 1.0.0
	 */
	public static JEMUIPlugin getPlugin() {
		return PLUGIN;
	}
	
	private Logger logger;
	public Logger getLogger() {
		if (logger == null)
			logger = EclipseLogger.getEclipseLogger(this);
		return logger;
	}	

}
