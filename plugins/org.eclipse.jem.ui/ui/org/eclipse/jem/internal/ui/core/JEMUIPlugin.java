/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: JEMUIPlugin.java,v $
 *  $Revision: 1.1 $  $Date: 2004/03/04 16:14:29 $ 
 */
package org.eclipse.jem.internal.ui.core;


import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.ibm.wtp.common.logger.proxy.Logger;
import com.ibm.wtp.logger.proxyrender.EclipseLogger;
 
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
	public JEMUIPlugin(IPluginDescriptor descriptor) {
		super(descriptor);
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
