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
 *  $RCSfile: InternalExpressionProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2005/05/11 19:01:12 $ 
 */
package org.eclipse.jem.internal.proxy.initParser.tree;
 

/**
 * This interface is used for expression proxies in the evaluation side (in {@link org.eclipse.jem.internal.proxy.initParser.tree.ExpressionProcesser}).
 * <p>
 * It is meant to be implemented only by users of the ExpressionProcessor.
 * 
 * @since 1.1.0
 */
public interface InternalExpressionProxy {
	
	/**
	 * Get the id of the proxy.
	 * @return
	 * 
	 * @since 1.1.0
	 */
	public int getProxyID();
	
	/**
	 * Called by ExpressionProcesser to give the expression proxy its value and type, i.e. it has been evaluated.
	 * <p>
	 * The type may not be the actual type of the value, it may be a superclass of it. It is the type that the proxy
	 * represents from the return of the expression. For instance it may of returned null but it is supposed to be
	 * java.lang.String. Or it is supposed to be a primitive int, since we can't store that, we need to store
	 * an Integer value with the type indicating it is Interger.TYPE.
	 * 
	 * @param value
	 * @param type
	 * 
	 * @since 1.1.0
	 */
	public void setProxy(Object value, Class type);
	
	/**
	 * Get the value of the proxy. 
	 * <p>
	 * Note: The value may be a {@link VariableReference}, in which
	 * case further dereferencing may be with the returned value.
	 * @return the value of the proxy, it may be an ExpressionProcesser.VariableReference.
	 * 
	 * @since 1.1.0
	 */
	public Object getValue();
	
	/**
	 * Get the type of the proxy.
	 * @return
	 * 
	 * @see InternalExpressionProxy#setProxy(Object, Class) for what type means.
	 * @since 1.1.0
	 */
	public Class getType();

	/**
	 * Return whether the proxy has had a value/class set into it yet.
	 * @return <code>true</code> if it has been set.
	 * 
	 * @since 1.1.0
	 */
	public boolean isSet();
}
