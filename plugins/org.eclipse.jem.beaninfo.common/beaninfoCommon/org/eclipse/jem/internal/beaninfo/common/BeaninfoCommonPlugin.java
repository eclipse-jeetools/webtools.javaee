/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.beaninfo.common;
/*


 */


import org.eclipse.core.runtime.Plugin;


/**
 * The plugin class for the org.eclipse.jem.internal.proxy.core plugin.
 */

public class BeaninfoCommonPlugin extends Plugin {
	public static final String PI_BEANINFO_PLUGINID = "org.eclipse.jem.beaninfo.common";	// Plugin ID, used for QualifiedName. //$NON-NLS-1$
	
	private static BeaninfoCommonPlugin BEANINFO_PLUGIN = null;
		
	public BeaninfoCommonPlugin() {	
		BEANINFO_PLUGIN = this;
	}
	
	/**
	 * Accessor method to get the singleton plugin.
	 */
	public static BeaninfoCommonPlugin getPlugin() {
		return BEANINFO_PLUGIN;
	}
	
}