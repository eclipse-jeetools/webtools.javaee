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
 *  $Revision: 1.2 $  $Date: 2005/06/16 17:46:14 $ 
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
		super(message);
	}
}