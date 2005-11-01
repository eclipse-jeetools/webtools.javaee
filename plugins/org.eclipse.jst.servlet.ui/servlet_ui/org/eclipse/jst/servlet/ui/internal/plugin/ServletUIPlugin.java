/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.servlet.ui.internal.plugin;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * @author jlanuti
 *
 */
public class ServletUIPlugin extends AbstractUIPlugin {
	
	public static final String PLUGIN_ID = "org.eclipse.jst.servlet.ui"; //$NON-NLS-1$
	//	The shared instance.
	private static ServletUIPlugin plugin;
	
	/**
	 * The constructor.
	 */
	public ServletUIPlugin() {
		super();
		plugin = this;
	}
	
	
	/**
	 * Returns the shared instance.
	 */
	public static ServletUIPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns the workspace instance.
	 */
	public static IWorkspace getWorkspace() {
		return ResourcesPlugin.getWorkspace();
	}
}
