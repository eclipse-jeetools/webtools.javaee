/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IInternalExpressionConstants.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:55:20 $ 
 */
package org.eclipse.jem.internal.proxy.initParser.tree;
 
/**
 * These constants are for communicating between the IDE and the proxy side
 * for expression evaluation.
 * 
 * @since 1.0.0
 */
public interface IInternalExpressionConstants {
	
	/**
	 * ARRAY ACCESS Expression
	 */
	int ARRAY_ACCESS_EXPRESSION = 1;
	
	/**
	 * ARRAY CREATION Expression
	 */
	int ARRAY_CREATION_EXPRESSION = 2;
	
	/**
	 * ARRAY INITIALIZER expression
	 */
	int ARRAY_INITIALIZER_EXPRESSION = 3;
	
	/**
	 * CAST Expression.
	 */
	int CAST_EXPRESSION = 4;
	
	/**
	 * CONDITIONAL expression
	 */
	int CONDITIONAL_EXPRESSION = 5;
	
	/**
	 * CLASS INSTANCE CREATION expression
	 */
	int CLASS_INSTANCE_CREATION_EXPRESSION = 6;
	
	/**
	 * FIELD ACCESS expression.
	 */
	int FIELD_ACCESS_EXPRESSION = 7;
	
	/**
	 * INSTANCEOF Expression.
	 */
	int INSTANCEOF_EXPRESSION = 8;

	/**
	 * Infix expression
	 */
	int INFIX_EXPRESSION = 9;
	
	/**
	 * Method expression.
	 */
	int METHOD_EXPRESSION = 10;
	
	/**
	 * Prefix expression
	 */
	int PREFIX_EXPRESSION = 11;
	
	/**
	 * Push to proxy expression.
	 */
	int PUSH_TO_PROXY_EXPRESSION = 12;
	
	/**
	 * Type literal Expression.
	 */
	int TYPELITERAL_EXPRESSION = 13;
	
	/**
	 * Type receiver expression.
	 */
	int TYPERECEIVER_EXPRESSION = 14;

	
	/**
	 * Used in Infix processing. Left operand on expression stack
	 */
	int INFIX_LEFT_OPERAND = 0;
	
	/**
	 * Used in Infix processing. Other operand (but not last) on expression stack 
	 */
	int INFIX_OTHER_OPERAND = 1;
	
	/**
	 * Used in Infix processing. Rightmost (last) operand on expression stack
	 */
	int INFIX_LAST_OPERAND = 2;
}
