/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.tests;
/*
 *  $RCSfile: JavaTestsPlugin.java,v $
 *  $Revision: 1.6 $  $Date: 2005/08/24 20:58:54 $ 
 */
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
	public JavaTestsPlugin() {
		PLUGIN = this;
	}
	
	public static JavaTestsPlugin getPlugin() {
		return PLUGIN;
	}

}
