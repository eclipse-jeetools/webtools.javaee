/*******************************************************************************
 * Copyright (c) 2001, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.core.internal.plugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jem.util.logger.proxyrender.DefaultPluginTraceRenderer;
import org.eclipse.jst.j2ee.internal.IEJBModelExtenderManager;
import org.eclipse.jst.j2ee.internal.xml.J2EEXmlDtDEntityResolver;
import org.eclipse.wst.common.internal.emf.utilities.DOMUtilities;
import org.osgi.framework.BundleContext;

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
	public J2EECorePlugin() {
		super();
		if (inst==null) inst = this;
	}
	
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		IEJBModelExtenderManager.INSTANCE.setProvider(EclipseEJBModelExtenderProvider.getInstance());
		// No that we can register against OSGI dependencies - lets re-use the catalog resolver
		J2EEXmlDtDEntityResolver.INSTANCE = new CatalogJ2EEXmlDtDEntityResolver();
		DOMUtilities.setDefaultEntityResolver(J2EEXmlDtDEntityResolver.INSTANCE); 
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

	public static IStatus createStatus(int severity, String message, Throwable exception) {
		return new Status(severity, PLUGIN_ID, message, exception);
	}

	public static IStatus createStatus(int severity, String message) {
		return createStatus(severity, message, null);
	}

	public static void logError(Throwable exception) {
		Platform.getLog(Platform.getBundle(PLUGIN_ID)).log( createStatus(IStatus.ERROR, exception.getMessage(), exception));
	}

	public static void logError(CoreException exception) {
		Platform.getLog(Platform.getBundle(PLUGIN_ID)).log( exception.getStatus() );
	}
	
}
