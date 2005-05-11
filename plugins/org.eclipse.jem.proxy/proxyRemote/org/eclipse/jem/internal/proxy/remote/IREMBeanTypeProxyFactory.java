package org.eclipse.jem.internal.proxy.remote;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IREMBeanTypeProxyFactory.java,v $
 *  $Revision: 1.3 $  $Date: 2005/05/11 19:01:12 $ 
 */


import org.eclipse.jem.internal.proxy.core.*;
/**
 * Interface for Remote VM Bean Type Proxy Factories.
 * All extension factories for Remote VM must implement this interface.
 */

public interface IREMBeanTypeProxyFactory extends IBeanTypeProxyFactory {
/**
 * Return a bean type proxy for the class name.
 * Return null if the extension factory doesn't handle this class.
 * Don't register any proxies returned, they will automatically be registered.
 * This should only return bean type proxies where the factory can determine
 * everything it needs, such as supertype and classID.
 * NOTE: This is implemented for the usage of the
 *       standard BeanType proxy factory. It should
 *       NOT be called by anyone else. They should
 *       go through the IStandardBeanTypeFactory
 *       instead. The standard bean type factory
 *       will call this method on the appropriate
 *       extension when it needs to.
 *
 * Creation date: (12/3/99 2:26:12 PM)
 */
public IREMBeanTypeProxy getExtensionBeanTypeProxy(String typeName);	

/**
 * Return a bean type proxy for the class name.
 * Return null if the extension factory doesn't handle this class.
 * Don't register any proxies returned, they will automatically be registered.
 * This should only return bean type proxies where the factory can determine
 * everything it needs, such as supertype and classID.
 * NOTE: This is implemented for the usage of the
 *       standard BeanType proxy factory. It should
 *       NOT be called by anyone else. They should
 *       go through the IStandardBeanTypeFactory
 *       instead. The standard bean type factory
 *       will call this method on the appropriate
 *       extension when it needs to.
 
 * @param typeName
 * @param expression
 * @return
 * 
 * @since 1.1.0
 */
public IProxyBeanType getExtensionBeanTypeProxy(String typeName, IExpression expression);

/**
 * Return a bean type proxy for the class id and class name.
 * Return null if the extension factory doesn't handle this class. 
 * Don't register any proxies returned, they will automatically be registered. 
 * This will be called in the case that a new class id was returned
 * from the server that we don't have registered yet. Implementers will
 * created a proxy out of the name and the id and return it.
 * The supertype will be passed in so that the extension factory doesn't need to
 * try to find it.
 * NOTE: This is implemented for the usage of the
 *       standard BeanType proxy factory. It should
 *       NOT be called by anyone else. They should
 *       go through the IStandardBeanTypeFactory
 *       instead. The standard bean type factory
 *       will call this method on the appropriate
 *       extension when it needs to.
 *
 * Creation date: (12/3/99 2:26:12 PM)
 */
public IREMBeanTypeProxy getExtensionBeanTypeProxy(String typeName, Integer classID, IBeanTypeProxy superType);	
}