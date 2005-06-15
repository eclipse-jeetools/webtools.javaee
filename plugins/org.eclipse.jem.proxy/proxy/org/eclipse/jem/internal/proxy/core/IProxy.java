/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IProxy.java,v $
 *  $Revision: 1.2 $  $Date: 2005/06/15 20:19:11 $ 
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
