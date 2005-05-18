/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IProxyBeanType.java,v $
 *  $Revision: 1.2 $  $Date: 2005/05/18 18:41:20 $ 
 */
package org.eclipse.jem.internal.proxy.core;
 

/**
 * This interface is for IBeanTypeProxy's and BeanTypeExpressionProxy's so that we can lazily
 * get the beantype proxy and have the expression process it when needed.
 * 
 * @since 1.1.0
 */
public interface IProxyBeanType extends IProxy {

	/**
	 * Answer the name of the type we are proxying This is the fully qualified name. For arrays it will return the format: [Lclassname; 
	 * @return
	 * 
	 * @since 1.1.0
	 */
	public String getTypeName();
	
	/**
	 * Get the IProxyMethod for the method from this beantype. It may be either a resolved method proxy (if already resolved) or
	 * it may be an ExpressionProxy if not yet resolved.
	 * 
	 * @param expression
	 * @param methodName
	 * @param parameterTypes array of parameter types or <code>null</code> if no parameters.
	 * @return
	 * 
	 * @since 1.1.0
	 */
	public IProxyMethod getMethodProxy(IExpression expression, String methodName, IProxyBeanType[] parameterTypes);
	
	/**
	 * Get the IProxyMethod for the method from this beantype where the method has no arguments. It may be either a resolved method proxy (if already resolved) or
	 * it may be an ExpressionProxy if not yet resolved.
	 * <p>
	 * This is a shortcut for getMethodProxy() that takes arguments names where the names have been sent as null.
	 * 
	 * @param expression
	 * @param methodName
	 * @return
	 * 
	 * @since 1.1.0
	 */
	public IProxyMethod getMethodProxy(IExpression expression, String methodName);
	
	/**
	 * Get the IProxyMethod for the method from this beantype. It may be either a resolved method proxy (if already resolved) or
	 * it may be an ExpressionProxy if not yet resolved.
	 * 
	 * @param expression
	 * @param methodName
	 * @param parameterTypes array of parameter types or <code>null</code> if no parameters.
	 * @return
	 * 
	 * @since 1.1.0
	 */
	public IProxyMethod getMethodProxy(IExpression expression, String methodName, String[] parameterTypes);
	
	/**
	 * Get the IProxyyField for the field from this beantype. It may be either a resolved field proxy (if already resolved) or
	 * it may be an ExpressionProxy if not yet resolved.
	 * 
	 * @param expression
	 * @param fieldName
	 * @return
	 * 
	 * @since 1.1.0
	 */
	public IProxyField getFieldProxy(IExpression expression, String fieldName);
}
