package org.eclipse.jem.tests;
/*******************************************************************************
 * Copyright (c)  2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: JavaTestsPlugin.java,v $
 *  $Revision: 1.2 $  $Date: 2003/10/27 17:32:36 $ 
 */
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.Plugin;

/**
 * @author richkulp
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JavaTestsPlugin extends Plugin {

	private static JavaTestsPlugin PLUGIN;
	/**
	 * @param descriptor
	 */
	public JavaTestsPlugin(IPluginDescriptor descriptor) {
		super(descriptor);
		PLUGIN = this;
	}
	
	public static JavaTestsPlugin getPlugin() {
		return PLUGIN;
	}

}
