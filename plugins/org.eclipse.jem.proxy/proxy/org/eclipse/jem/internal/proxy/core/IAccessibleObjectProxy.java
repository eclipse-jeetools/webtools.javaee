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
package org.eclipse.jem.internal.proxy.core;
/*
 *  $RCSfile: IAccessibleObjectProxy.java,v $
 *  $Revision: 1.2 $  $Date: 2004/08/27 15:35:20 $ 
 */

/**
 * Proxy for an AccessibleObject.
 * 
 * @since 1.0.0 
 */ 
public interface IAccessibleObjectProxy extends IBeanProxy {
	
	/**
	 * Is the proxy accessible or not?
	 * 
	 * @return <code>true</code> if accessible.
	 */
	public boolean isAccessible() throws ThrowableProxy;
	
	/**
	 * Set the accessible flag on the proxy.
	 * 
	 * @param flag <code>true</code> if accessible.
	 * @throws ThrowableProxy
	 */
	public void setAccessible(boolean flag) throws ThrowableProxy;

}
