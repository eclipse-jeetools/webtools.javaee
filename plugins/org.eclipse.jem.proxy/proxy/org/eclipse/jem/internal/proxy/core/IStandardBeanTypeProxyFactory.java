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
 *  $RCSfile: IStandardBeanTypeProxyFactory.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */

import java.util.Set;
/**
 * The standard bean type proxy factory.
 * This is the Interface that the desktop will talk
 * to, the one that is returned when getCurrent() is called.
 * Creation date: (3/10/00 11:00:50 AM)
 * @author: Richard Lee Kulp
 */
public interface IStandardBeanTypeProxyFactory extends IBeanProxyFactory {
/**
 * Used by other registered bean type proxy factories to
 * register their bean type proxies with the standard factory
 * so that it will be cached there. 
 *
 * The permanent flag indicates that beantype will never be released,
 * not even if explicit request is made.
 * Creation date: (3/10/00 11:02:11 AM)
 */
void registerBeanTypeProxy(IBeanTypeProxy aBeanTypeProxy, boolean permanent);

/**
 * Return the beanType proxy for the given class name.
 * It must be fully qualified. And for arrays it can handle
 * either the jni type ([Ljava.lang.Object;) or the Java EMF Model
 * formal type (java.lang.Object[]).
 */
IBeanTypeProxy getBeanTypeProxy(String className);

/**
 * Return an Array type proxy for the given class name of
 * the specified dimensions. This is a helper method. The
 * same result can be gotton from getBeanTypeProxy.
 * e.g.
 *      getBeanTypeProxy("java.lang.Object", 3)
 *    is the same as:
 *      getBeanTypeProxy("[[[Ljava.lang.Object;")
 *
 *    They both result in a type of:
 *      Object [][][]
 * 
 *    or if using the JNI format (proxy format)
 *      getBeanTypeProxy("[Ljava.langObject;", 3)
 *    becomes
 *      Object [][][][] 
 * 
 *    or if using the standard java format (as in actual code)
 *      getBeanTypeProxy("java.langObject[];", 3)
 *    becomes
 *      Object [][][][]
 */
IBeanTypeProxy getBeanTypeProxy(String componentClassName, int dimensions);

/**
 * Test if a specific bean type has been registered. Don't access and create
 * if it isn't currently registered.
 */
boolean isBeanTypeRegistered(String className);

/**
 * Registered types. Return a set of strings that are the registered classes.
 * This Set isn't synchronized, there may be changes while accessing it.
 */
Set registeredTypes();

/**
 * Maintain list of not found types. This list is types that were requested,
 * but didn't exist. This method sets whether list should be maintained or not.
 * If set to false, the list will be empty. The default is false.
 * 
 * @param maintain
 */
void setMaintainNotFoundTypes(boolean maintain);

/**
 * Maintain list of not found types. This list is types that were requested,
 * but didn't exist. This method returns whether list should be maintained or not.
 * If false, the list will be empty. The default is false.
 * 
 * @return maintaining not found types.
 */
boolean isMaintainNotFoundTypes();

/**
 * Maintain list of not found types. This list is types that were requested,
 * but didn't exist. 
 *
 * @param className Classname to search for to see if ever not found.
 * @return true if the bean type had been searched for but was not found. If not maintaining, then result will be false.
 */
boolean isBeanTypeNotFound(String className);

}