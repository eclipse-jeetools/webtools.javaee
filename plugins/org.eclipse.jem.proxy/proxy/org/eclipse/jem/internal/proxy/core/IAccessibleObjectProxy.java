/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.proxy.core;
/*


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
