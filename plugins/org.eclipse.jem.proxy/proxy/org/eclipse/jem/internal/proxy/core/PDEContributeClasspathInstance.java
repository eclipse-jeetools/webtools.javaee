/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: PDEContributeClasspathInstance.java,v $
 *  $Revision: 1.3 $  $Date: 2005/05/18 23:11:26 $ 
 */
package org.eclipse.jem.internal.proxy.core;

import java.util.logging.Level;

import org.eclipse.core.runtime.Platform;

 

/**
 * Accessor to the IPDEContributeClasspath instance to use.
 * <pacakge-protected> because only IPDEContributeClasspath should access it.
 * 
 * @since 1.0.2
 */
class PDEContributeClasspathInstance {

	/**
	 * Get the instance to use. 
	 * @return the instance to use or <code>null</code> if PDE not available.
	 * 
	 * @since 1.0.2
	 */
	public static IPDEContributeClasspath getInstance() {
		if (Platform.getBundle("org.eclipse.pde.core") != null) { //$NON-NLS-1$
			try {
				Class pdeClass = Class.forName("org.eclipse.jem.internal.proxy.core.PDEContributeClasspath"); //$NON-NLS-1$
				return (IPDEContributeClasspath) pdeClass.newInstance();
			} catch (ClassNotFoundException e) {
				// PDE not available, this is ok.
			} catch (InstantiationException e) {
				ProxyPlugin.getPlugin().getLogger().log(e.getCause(), Level.WARNING);
			} catch (IllegalAccessException e) {
				ProxyPlugin.getPlugin().getLogger().log(e, Level.WARNING);
			}
		}
		return null;
	}
}
