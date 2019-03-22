/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*


 */
package org.eclipse.jem.internal.proxy.core;
 

/**
 * A common interface for proxies. It is used by the expression processor to easily distinquish between
 * a proxy and an Expression proxy. 
 * @since 1.1.0
 */
public interface IProxy {

	/**
	 * Return the registry this proxy was created with.
	 * @return
	 * 
	 * @since 1.1.0
	 */
	public ProxyFactoryRegistry getProxyFactoryRegistry();
	
	/**
	 * Is this a bean proxy (including all of the subinterfaces of it, like IMethodProxy).
	 * @return <code>true</code> if this is a bean proxy.
	 * 
	 * @since 1.1.0
	 */
	public boolean isBeanProxy();

	/**
	 * Is this an expression proxy.
	 * @return <code>true</code> if this is an expression proxy.
	 * 
	 * @since 1.1.0
	 */
	public boolean isExpressionProxy();
}
