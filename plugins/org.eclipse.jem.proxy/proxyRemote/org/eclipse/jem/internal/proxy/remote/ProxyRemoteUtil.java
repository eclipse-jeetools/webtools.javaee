package org.eclipse.jem.internal.proxy.remote;
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
 *  $RCSfile: ProxyRemoteUtil.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */


import java.util.List;

import org.eclipse.jem.internal.proxy.core.IClasspathContributionController;
import org.eclipse.jem.internal.proxy.core.ProxyPlugin;


public class ProxyRemoteUtil {

	// Debug options
	public static final String DEBUG_VM = "/remote/debug/debugvm", // Bring up debugger on remote vm's. //$NON-NLS-1$
		IO_CONSOLE = "/remote/debug/ioconsole", // IO Through console (system in and out) for asking questions. //$NON-NLS-1$
		DEBUG_VM_TRACEOUT = "/remote/debug/vmtraceout"; // Trace the output from the remote vm's. //$NON-NLS-1$

	private static REMRegistryController pluginRegistryController;
	
	/**
	 * Return the registry controller.
	 */
	public static REMRegistryController getRegistryController() {
		return pluginRegistryController != null ? pluginRegistryController : (pluginRegistryController = new REMRegistryController());
	}
	
	/**
	 * Shuts down this plug-in and discards all plug-in state.
	 *
	 * In this case, terminate all of the active registries so that they can be shutdown.
	 * Don't want them hanging around after termination of the desktop.
	 *
	 * @exception CoreException if this method fails to shut down
	 *   this plug-in 
	 */
	public static void shutdown() {
		getRegistryController().shutdown();
	}	
	
	/**
	 * Method to update any class paths with any
	 * paths that need to be added to a VM. In this case, it is
	 * the remotevm.jar and the remotecommon.jar that need to be added. The first jar contains
	 * the code necessary to run the remote vm and listen for commands. The second jar contains
	 * the common code that is required to be shared on both the IDE side and remote side. It
	 * is the interface code so that they both talk the same language.
	 * support.
	 */
	public static void updateClassPaths(List classPaths, IClasspathContributionController controller) {
		ProxyPlugin proxyPlugin = ProxyPlugin.getPlugin();
		// Need to have our jars at the beginning. (Struts causes a problem because 
		// they have a jar (commons-fileupload.jar) that if when searching for a class and
		// this jar is reached, it causes the class loader to barf. Our jars used to be at
		// the end, and so it found the fileupload jar first and no remote vm could be started
		// because of this.
		controller.contributeClasspath(proxyPlugin.localizeFromPlugin(proxyPlugin, "vm/remotevm.jar"), classPaths, 0); //$NON-NLS-1$
		controller.contributeClasspath(proxyPlugin.localizeFromPlugin(proxyPlugin, "remotecommon.jar"), classPaths, 1); //$NON-NLS-1$
	}
}