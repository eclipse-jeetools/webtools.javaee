package org.eclipse.jem.internal.beaninfo.ui;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: BeaninfoUIPlugin.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:20:50 $ 
 */


import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.jem.internal.core.EclipseLogMsgLogger;
import org.eclipse.jem.internal.core.MsgLogger;
import org.eclipse.ui.plugin.AbstractUIPlugin;
/**
 * Plugin class for the Beaninfo UI Plugin
 */

public class BeaninfoUIPlugin extends AbstractUIPlugin {
	public static final String PI_BEANINFO_UI = "org.eclipse.jem.internal.beaninfo.ui";	// Plugin ID	 //$NON-NLS-1$
	private static BeaninfoUIPlugin BEANINFO_UI_PLUGIN = null;
	
	public BeaninfoUIPlugin(IPluginDescriptor pluginDescriptor) {	
		super(pluginDescriptor);
		BEANINFO_UI_PLUGIN = this;
	}
	
	/**
	 * Accessor method to get the singleton plugin.
	 */
	public static BeaninfoUIPlugin getPlugin() {
		return BEANINFO_UI_PLUGIN;
	}
	
	private MsgLogger msgLogger;
	public MsgLogger getMsgLogger() {
		if (msgLogger == null)
			msgLogger = EclipseLogMsgLogger.createLogger(this);
		return msgLogger;
	}
	
}