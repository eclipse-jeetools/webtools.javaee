package org.eclipse.jem.internal.proxy.ide;
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
 *  $RCSfile: IDEProxyFactoryRegistry.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */

import java.net.URL;
import java.net.URLClassLoader;

import org.eclipse.core.runtime.IPluginRegistry;
import org.eclipse.core.runtime.Platform;

import org.eclipse.jem.internal.proxy.core.ICallbackRegistry;
import org.eclipse.jem.internal.proxy.core.ProxyPlugin;
/**
 * This implementation runs the Beans inside the Eclipse IDE
 * It should only be used by plugins that can guarantee their their beans do
 * not change during the lifetime of the IDE and can run at the same JDK level
 * that the Eclipse IDE is running
 */

public class IDEProxyFactoryRegistry extends org.eclipse.jem.internal.proxy.core.ProxyFactoryRegistry {

	protected String fName;
	protected ClassLoader fClassLoader;
	protected IDECallbackRegistry fCallbackRegistry;

/**
 * Create a special loader that has the plugin classloader of the passed in plugin
 * as the parent loader, plus all of the urls, plus the remotevm.jar file that is
 * needed by IDE Proxy to work.
 */	
public static ClassLoader createSpecialLoader(String pluginName, URL[] otherURLs) {
	IPluginRegistry registry = Platform.getPluginRegistry();
	// Get the class loader from the plugin.  This is because Class.forName
	// does not work in Eclipse
	ClassLoader loader = null;
	if (pluginName != null && registry.getPluginDescriptor(pluginName) != null ) {
		loader = registry.getPluginDescriptor(pluginName).getPluginClassLoader();
	}
	
	URL[] mustHaveUrls = ProxyPlugin.getPlugin().urlLocalizeFromPluginDescriptorAndFragments(ProxyPlugin.getPlugin().getDescriptor(), "vm/remotevm.jar"); //$NON-NLS-1$
	
	URL[] urls = null;
	if (otherURLs != null) {
		urls = new URL[otherURLs.length+mustHaveUrls.length];
		System.arraycopy(mustHaveUrls, 0, urls, 0, mustHaveUrls.length);
		System.arraycopy(otherURLs, 0, urls, mustHaveUrls.length, otherURLs.length);
	} else 
		urls = mustHaveUrls;
		
	loader = loader != null ? 
		new URLClassLoader(urls, loader) : new URLClassLoader(urls);
	
	return loader;
}

public IDEProxyFactoryRegistry(String aName, ClassLoader loader) {
	fName = aName;
	fClassLoader = loader;
}

ClassLoader getPluginClassLoader(){
	return fClassLoader;
}

public void registryTerminated(){	
}
Class loadClass(String aClassName) throws ClassNotFoundException, ExceptionInInitializerError, LinkageError {
	return fClassLoader.loadClass(aClassName);
}

public ICallbackRegistry getCallbackRegistry(){
	if ( fCallbackRegistry == null ){
		fCallbackRegistry = new IDECallbackRegistry(this);
	}
	return fCallbackRegistry;
}

}