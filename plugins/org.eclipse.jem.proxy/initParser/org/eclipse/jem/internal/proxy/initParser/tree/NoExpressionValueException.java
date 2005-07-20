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
 *  $RCSfile: NoExpressionValueException.java,v $
 *  $Revision: 1.3 $  $Date: 2005/07/20 19:27:25 $ 
 */
package org.eclipse.jem.internal.proxy.initParser.tree;
 

/**
 * The expression (or some nested expression) did not return a value. I.e. it was <code>void</code>.
 * This would occur only if the value of expression was being retrieved through getExpressionValue,
 * or if a nested expression was used, since in that case the value would of been used as an
 * argument or receiver to another expression.
 * 
 * @since 1.0.0
 */
public class NoExpressionValueException extends Exception {
	
	/**
	 * Comment for <code>serialVersionUID</code>
	 * 
	 * @since 1.1.0
	 */
	private static final long serialVersionUID = -7953101867782417964L;
	
	private InternalExpressionProxy proxy;

	/**
	 * Construct with no arguments.
	 * 
	 * @since 1.0.0
	 */
	public NoExpressionValueException() {
		super();
	}
	
	public NoExpressionValueException(Throwable e) {
		super(e);
	}

	/**
	 * Construct with a message.
	 * 
	 * @param message
	 * 
	 * @since 1.0.0
	 */
	public NoExpressionValueException(String message) {
		this(message, null);
	}
	
	/**
	 * Construct with a message and a proxy. This is only used from {@link ExpressionProcesser#getExpressionProxyValue(int, Object[])} when
	 * the proxy existed but it was not set.
	 * 
	 * @param message
	 * @param proxy
	 * 
	 * @since 1.1.0
	 */
	public NoExpressionValueException(String message, InternalExpressionProxy proxy) {
		super (message);
		this.proxy = proxy;
	}
	
	/**
	 * Get the proxy if there is one. It will be a proxy if {@link ExpressionProcesser#getExpressionProxyValue(int, Object[])}
	 * was for an existing proxy but that proxy was not set. Otherwise it will be null. This is here for callers to
	 * put special info in the proxy for the not set condition and report better info.
	 * 
	 * @return the proxy (if not set) or <code>null</code> if no proxy available.
	 * 
	 * @since 1.1.0
	 */
	public InternalExpressionProxy getProxy() {
		return proxy;
	}
}