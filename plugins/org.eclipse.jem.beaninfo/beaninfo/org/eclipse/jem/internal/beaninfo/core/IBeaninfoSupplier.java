/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.beaninfo.core;
/*
 *  $RCSfile: IBeaninfoSupplier.java,v $
 *  $Revision: 1.2 $  $Date: 2004/08/27 15:33:31 $ 
 */


import org.eclipse.core.resources.IProject;

import org.eclipse.jem.internal.proxy.core.ProxyFactoryRegistry;
/**
 * Interface to supply information for beaninfo to decouple
 * the introspection from the desktop.
 */

public interface IBeaninfoSupplier {
	
	/**
	 * @return Return the registry to use. Initialize it if not already initialized.
	 */
	public ProxyFactoryRegistry getRegistry();
	
	/**
	 * Used to know if we currently have a registry created in the supplier.
	 * 
	 * @return true if there is a registry currently in the supplier.
	 */
	public boolean isRegistryCreated();
	
	/**
	 * Close the registry. This tells the registry to close. This is necessary
	 * at times because of changes to classes require the registry to be
	 * reconstructed.
	 */
	public void closeRegistry();
	
	/**
	 * Return the project that this supplier is for.
	 * @return
	 * 
	 * @since 1.0.0
	 */
	public IProject getProject();

}
