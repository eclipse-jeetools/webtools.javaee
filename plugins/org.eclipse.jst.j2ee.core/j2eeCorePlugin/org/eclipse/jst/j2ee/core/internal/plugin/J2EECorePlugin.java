/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.core.internal.plugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.jst.j2ee.internal.IEJBModelExtenderManager;

import com.ibm.wtp.common.logger.proxy.Logger;
import com.ibm.wtp.logger.proxyrender.DefaultPluginTraceRenderer;

/*
 * Created on Nov 25, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
/**
 * @author vijayb
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class J2EECorePlugin extends Plugin {
	public static final String PLUGIN_ID = "org.eclipse.jst.j2ee.core"; //$NON-NLS-1$
	private static J2EECorePlugin inst = null;
	protected static Logger logger = null;
	/**
	 * @param descriptor
	 */
	public J2EECorePlugin(IPluginDescriptor descriptor) {
		super(descriptor);
		if (inst==null) inst = this;
	}
	
	public void startup() throws CoreException {
		super.startup();
		IEJBModelExtenderManager.INSTANCE.setProvider(EclipseEJBModelExtenderProvider.getInstance());
	}
	
	public static J2EECorePlugin getPlugin(){
		return inst;
	}
	
	public static String getPluginID() {
		return PLUGIN_ID;
	}
	
	public Logger getMsgLogger() {
		if (logger == null) {
			logger = Logger.getLogger(getPluginID());
			setRenderer(logger);
		}
		return logger;
	}
	
	/**
	 * @param aLogger
	 */
	protected void setRenderer(Logger aLogger) {
		new DefaultPluginTraceRenderer(aLogger);
	}
	
}
