package org.eclipse.jem.internal.core;
/*******************************************************************************
 * Copyright (c) 2001, 2003 IBM Corporation and others.
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
 *  $Revision: 1.3 $  $Date: 2004/02/24 19:33:42 $ 
 */
import org.eclipse.core.runtime.*;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.Plugin;

import com.ibm.wtp.common.logger.proxy.Logger;
import com.ibm.wtp.logger.proxyrender.EclipseLogger;

/**
 * org.eclipse.jem plugin
 */
public class JEMPlugin extends Plugin {

	private static JEMPlugin PLUGIN;
	private Logger logger;
	
	public JEMPlugin(IPluginDescriptor descriptor) {
		super(descriptor);
		PLUGIN = this;
	}
	
	public static JEMPlugin getPlugin() {
		return PLUGIN;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugin#startup()
	 */
	public void startup() throws CoreException {
		super.startup();
		logger = EclipseLogger.getEclipseLogger(this);
	}
	
	public Logger getLogger() {
		return logger;
	}

}
