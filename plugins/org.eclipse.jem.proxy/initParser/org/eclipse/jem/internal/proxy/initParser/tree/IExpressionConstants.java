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
 *  $RCSfile: IExpressionConstants.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:55:20 $ 
 */
package org.eclipse.jem.internal.proxy.initParser.tree;
 
/**
 * These are constants needed by IExpression that will be common between
 * the vm and the IDE. They are also available to users of the IExpression
 * interface.
 * 
 * @since 1.0.0
 */
public interface IExpressionConstants {

	/**
	 * The expression (or some nested expression) did not return a value. I.e. it was <code>void</code>.
	 * This would occur only if the value of expression was being retrieved through getExpressionValue,
	 * or if a nested expression was used, since in that case the value would of been used as an
	 * argument or receiver to another expression.
	 * 
	 * @since 1.0.0
	 */
	public static class NoExpressionValueException extends Exception {
		
		/**
		 * Construct with no arguments.
		 * 
		 * @since 1.0.0
		 */
		public NoExpressionValueException() {
			super();
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

	/**
	 * Infix times "*"
	 */
	int IN_TIMES = 0;
	
	/**
	 * Infix divide "/"
	 */
	int IN_DIVIDE = 1;
	
	/**
	 * Infix remainder "%"
	 */
	int IN_REMAINDER = 2;
	
	/**
	 * Infix plus "+"
	 */
	int IN_PLUS = 3;
	
	/**
	 * Infix minus "-"
	 */
	int IN_MINUS = 4;
	
	/**
	 * Infix left shift "<<"
	 */
	int IN_LEFT_SHIFT = 5;
	
	/**
	 * Infix right shift signed ">>"
	 */
	int IN_RIGHT_SHIFT_SIGNED = 6;
	
	/**
	 * Infix right shift unsigned ">>>"
	 */
	int IN_RIGHT_SHIFT_UNSIGNED = 7;
	
	/**
	 * Infix less "<"
	 */
	int IN_LESS = 8;
	
	/**
	 * Infix greater ">"
	 */
	int IN_GREATER = 9;
	
	/**
	 * Infix less than or equals "<="
	 */
	int IN_LESS_EQUALS = 10;
	
	/**
	 * Infix Greater than or equlas ">="
	 */
	int IN_GREATER_EQUALS = 11;
	
	/**
	 * Infix equals "=="
	 */
	int IN_EQUALS = 12;
	
	/**
	 * Infix not equals "!="
	 */
	int IN_NOT_EQUALS = 13;
	
	/**
	 * Infix exclusive or "^"
	 */
	int IN_XOR = 14;
	
	/**
	 * Infix bitwise and "&"
	 */
	int IN_AND = 15;
	
	/**
	 * Infix bitwise or "|"
	 */
	int IN_OR = 16;
	
	/**
	 * Infix Conditional logical and "&&"
	 */
	int IN_CONDITIONAL_AND = 17;
	
	/**
	 * Infix Conditional logical or "||"
	 */
	int IN_CONDITIONAL_OR = 18;
	
	/**
	 * Max value of Infix operators.
	 */
	int IN_MAX = 18;
	
	/**
	 * Prefix plus "+"
	 */
	int PRE_PLUS = 0;
	
	/**
	 * Prefix minus "-"
	 */
	int PRE_MINUS = 1;
	
	/**
	 * Prefix bitwise complement "~"
	 */
	int PRE_COMPLEMENT = 2;
	
	/**
	 * Prefix logical not "!"
	 */
	int PRE_NOT = 3;
	
	/**
	 * Max value of the Prefix operators.
	 */
	int PRE_MAX = 3;

	/**
	 * forExpression: Creating a root expression
	 */
	int ROOTEXPRESSION = 0;

	/**
	 * forExpression: Creating the array expression for an array access (i.e. the array to access)
	 */
	int ARRAYACCESS_ARRAY = 1;

	/**
	 * forExpression: Creating an index expression for an array access (i.e. one of the expressions within the <code>[]</code>).
	 */
	int ARRAYACCESS_INDEX = 2;

	/**
	 * forExpression: Creating an dimension expression for an array creation (i.e. one of the expressions within the <code>[]</code>).
	 */
	int ARRAYCREATION_DIMENSION = 3;

	/**
	 * forExpression: Creating an expression for an array initializer (i.e. one of the expressions with the <code>{}</code>).
	 * Special case is that array initializers are valid as an expression within an array initializer and it doesn't use
	 * a for expression.
	 */
	int ARRAYINITIALIZER_EXPRESSION = 4;

	/**
	 * forExpression: Creating the expression for the cast (i.e. the expresion after the <code>(type)</code>).
	 */
	int CAST_EXPRESSION = 5;

	/**
	 * forExpression: Creating the argument expression for the new instance.
	 */
	int CLASSINSTANCECREATION_ARGUMENT = 6;

	/**
	 * forExpression: Creating the condition for a conditional expression.
	 */
	int CONDITIONAL_CONDITION = 7;

	/**
	 * forExpression: Creating the true (then) expression for a conditional expression.
	 */
	int CONDITIONAL_TRUE = 8;

	/**
	 * forExpression: Creating the false (else) condition for a conditional expression.
	 */
	int CONDITIONAL_FALSE = 9;

	/**
	 * forExpression: Creating the receiver for a field access (i.e. the expression before the ".")
	 */
	int FIELD_RECEIVER = 10;

	/**
	 * forExpression: Creating the left operand of an infix expression.
	 */
	int INFIX_LEFT = 11;

	/**
	 * forExpression: Creating the right operand of an infix expression.
	 */
	int INFIX_RIGHT = 12;

	/**
	 * forExpression: Creating an extended operand of an infix expression.
	 */
	int INFIX_EXTENDED = 13;

	/**
	 * forExpression: Creating the value expression of an instanceof.
	 */
	int INSTANCEOF_VALUE = 14;

	/**
	 * forExpression: Creating a receiver expression for a method invocation.
	 */
	int METHOD_RECEIVER = 15;

	/**
	 * forExpression: Creating an argument for a method invocation.
	 */
	int METHOD_ARGUMENT = 16;
	
	/**
	 * forExpression: Creating the operand expression for a prefix operator.
	 */
	int PREFIX_OPERAND = 17;
	
}
