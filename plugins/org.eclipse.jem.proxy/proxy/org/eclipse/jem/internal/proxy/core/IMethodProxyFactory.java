/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IMethodProxyFactory.java,v $
 *  $Revision: 1.2 $  $Date: 2004/08/10 17:52:10 $ 
 */
package org.eclipse.jem.internal.proxy.core;

/**
 * Factory for creating method proxies.
 * 
 * The factory exists for use, but generally it is better to go through the bean type proxy to get a method proxy. This is useful for setting up a
 * bunch of method proxies in initialization routines so you don't need to have a bean type proxy for each.
 * 
 * Creation date: (12/3/99 6:21:52 PM)
 * 
 * @author: Joe Winchester
 */
public interface IMethodProxyFactory extends IBeanProxyFactory {

	/**
	 * Return a method proxy for the specified name, arguments from the class.
	 * 
	 * @param className
	 * @param methodName
	 * @param parameterTypes
	 *            parameter type names or <code>null</code> for parameterTypes means no parameters.
	 * @return a method proxy or null if can't be found. 
	 * @since 1.0.0
	 */
	IMethodProxy getMethodProxy(String className, String methodName, String[] parameterTypes);

	/**
	 * Return an invokable for the specified name, arguments from the class. The method proxy is not retrieved. Instead the method will be looked up
	 * each time on the vm. Because of this these are suitable only for one-shot invokations. If it is to be invoked often, then a method proxy should
	 * be retrieved instead.
	 * <p>
	 * Though typical for one-shot deal the invokable can be used over and over. There is just overhead because the method is looked up each time. So a reasonable compromise
	 * would be if using it infrequently or is used closely together once or twice it is better to use a Invokable instead of a method proxy.
	 * <p>
	 * Note there is no guarantee that the method is available. This won't be known until the actual invoke is done.
	 * 
	 * @param className
	 * @param methodName
	 * @param parameterTypes
	 *            parameter type names or <code>null</code> for parameterTypes means no parameters.
	 * @return an invokable 
	 * @since 1.0.0
	 */
	IInvokable getInvokable(String className, String methodName, String[] parameterTypes);
}