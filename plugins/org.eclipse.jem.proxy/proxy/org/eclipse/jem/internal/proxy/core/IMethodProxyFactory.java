package org.eclipse.jem.internal.proxy.core;
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
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */


/**
 * Factory for creating method proxies.
 *
 * The factory exists for use, but generally it is better
 * to go through the bean type proxy to get a method proxy.
 * This is useful for setting up a bunch of method proxies
 * in initialization routines so you don't need to have
 * a bean type proxy for each.
 *
 * Creation date: (12/3/99 6:21:52 PM)
 * @author: Joe Winchester
 */
public interface IMethodProxyFactory extends IBeanProxyFactory {

/**
 * Return a method proxy for the specified name, arguments from the class.
 * null for parameterTypes means no parameters.
 * Creation date: (12/3/99 6:22:05 PM)
 */
IMethodProxy getMethodProxy(String className, String methodName, String[] parameterTypes);
}
