/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Nov 15, 2004
 */
package org.eclipse.jst.j2ee.jca.ui.internal.plugin;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * @author jlanuti
 */
public class JCAUIPlugin extends AbstractUIPlugin {
	
	public static final String PLUGIN_ID = "org.eclipse.jst.j2ee.jca.ui"; //$NON-NLS-1$
	
	
	//	The shared instance.
	private static JCAUIPlugin plugin;

	/**
	 * The constructor.
	 */
	public JCAUIPlugin() {
		super();
		plugin = this;
	}

	/**
	 * Returns the shared instance.
	 */
	public static JCAUIPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns the workspace instance.
	 */
	public static IWorkspace getWorkspace() {
		return ResourcesPlugin.getWorkspace();
	}
}
