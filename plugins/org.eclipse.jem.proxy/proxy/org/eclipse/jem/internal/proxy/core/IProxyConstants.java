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
 *  $RCSfile: IProxyConstants.java,v $
 *  $Revision: 1.1 $  $Date: 2004/03/04 16:14:04 $ 
 */
package org.eclipse.jem.internal.proxy.core;
 
/**
 * Constants used with the plugin xml.
 * 
 * @since 1.0.0
 */
public interface IProxyConstants {
	
	/**
	 * Launch group id for proxy launch configurations.
	 */	
	public static final String ID_PROXY_LAUNCH_GROUP = "org.eclipse.jem.proxy";
	
	/**
	 * Attribute on Proxy Launch Configuration:
	 * 
	 * Key used in Launch Configuration for attaching AWT/Swing to the registry. This means simply
	 * that AWT/Swing should be treated as being in the registry or not. It doesn't actually prevent
	 * them from being there.
	 *
	 * The default value for this attribute is "true", so it should be set only to "false".
	 */
	public static final String ATTRIBUTE_AWT_SWING = "org.eclipse.jem.proxy.AWT/Swing";
	
	/**
	 * Attribute on Proxy Launch Configuration:
	 * 
	 * Key used in Launch Configuration for the contributors and returning the registry. After retrieving key,
	 * go back to ProxyLaunchSupport to retrieve the contributors. And it uses it to return the registry.
	 * 
	 * Not to be set by contributors. This will be set only by the ProxyLaunchSupport start implementation methods.
	 * It is here only because external developer's launch configurations will need to be able to access this.
	 */
	public static final String ATTRIBUTE_LAUNCH_KEY = "org.eclipse.jem.proxy.LaunchKey";
	
	/**
	 * Attribute on Proxy Launch Configuration:
	 *  
	 * VM title used for the launch registry.
	 * 
	 * Not to be set by contributors. This will be set only by the ProxyLaunchSupport start implementation methods.
	 * It is here only because external developer's launch configurations will need to be able to access this.
	 */
	public static final String ATTRIBUTE_VM_TITLE = "org.eclipse.jem.proxy.vmtitle";
}
