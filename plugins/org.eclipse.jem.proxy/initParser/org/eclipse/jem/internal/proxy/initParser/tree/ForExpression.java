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
 *  $RCSfile: ForExpression.java,v $
 *  $Revision: 1.2 $  $Date: 2005/05/18 23:11:26 $ 
 */
package org.eclipse.jem.internal.proxy.initParser.tree;

/**
 * Enum for the forExpression parameters.
 * <p>
 * This is not meant to be subclassed by customers.
 * @since 1.1.0
 */
public class ForExpression extends AbstractEnum {

	/**
	 * forExpression: Creating a root expression
	 */
	public static final int ROOTEXPRESSION_VALUE = 0;

	public static final ForExpression ROOTEXPRESSION = new ForExpression(ROOTEXPRESSION_VALUE, "Root"); //$NON-NLS-1$

	/**
	 * forExpression: Creating the array expression for an array access (i.e. the array to access)
	 */
	public static final int ARRAYACCESS_ARRAY_VALUE = 1;

	public static final ForExpression ARRAYACCESS_ARRAY = new ForExpression(ARRAYACCESS_ARRAY_VALUE, "Array Access: Array Operand"); //$NON-NLS-1$

	/**
	 * forExpression: Creating an index expression for an array access (i.e. one of the expressions within the <code>[]</code>).
	 */
	public static final int ARRAYACCESS_INDEX_VALUE = 2;

	public static final ForExpression ARRAYACCESS_INDEX = new ForExpression(ARRAYACCESS_INDEX_VALUE, "Array Access: Index"); //$NON-NLS-1$

	/**
	 * forExpression: Creating an dimension expression for an array creation (i.e. one of the expressions within the <code>[]</code>).
	 */
	public static final int ARRAYCREATION_DIMENSION_VALUE = 3;

	public static final ForExpression ARRAYCREATION_DIMENSION = new ForExpression(ARRAYCREATION_DIMENSION_VALUE, "Array Access: Dimension"); //$NON-NLS-1$

	/**
	 * forExpression: Creating an expression for an array initializer (i.e. one of the expressions with the <code>{}</code>). Special case is that
	 * array initializers are valid as an expression within an array initializer and it doesn't use a for expression.
	 */
	public static final int ARRAYINITIALIZER_EXPRESSION_VALUE = 4;

	public static final ForExpression ARRAYINITIALIZER_EXPRESSION = new ForExpression(ARRAYINITIALIZER_EXPRESSION_VALUE, "Array Initializer"); //$NON-NLS-1$

	/**
	 * forExpression: Creating the expression for the cast (i.e. the expresion after the <code>(type)</code>).
	 */
	public static final int CAST_EXPRESSION_VALUE = 5;

	public static final ForExpression CAST_EXPRESSION = new ForExpression(CAST_EXPRESSION_VALUE, "Cast"); //$NON-NLS-1$

	/**
	 * forExpression: Creating the argument expression for the new instance.
	 */
	public static final int CLASSINSTANCECREATION_ARGUMENT_VALUE = 6;

	public static final ForExpression CLASSINSTANCECREATION_ARGUMENT = new ForExpression(CLASSINSTANCECREATION_ARGUMENT_VALUE,
			"Class Instance Creation: Argument"); //$NON-NLS-1$

	/**
	 * forExpression: Creating the condition for a conditional expression.
	 */
	public static final int CONDITIONAL_CONDITION_VALUE = 7;

	public static final ForExpression CONDITIONAL_CONDITION = new ForExpression(CONDITIONAL_CONDITION_VALUE, "Conditional: Condition"); //$NON-NLS-1$

	/**
	 * forExpression: Creating the true (then) expression for a conditional expression.
	 */
	public static final int CONDITIONAL_TRUE_VALUE = 8;

	public static final ForExpression CONDITIONAL_TRUE = new ForExpression(CONDITIONAL_TRUE_VALUE, "Conditional: True"); //$NON-NLS-1$

	/**
	 * forExpression: Creating the false (else) condition for a conditional expression.
	 */
	public static final int CONDITIONAL_FALSE_VALUE = 9;

	public static final ForExpression CONDITIONAL_FALSE = new ForExpression(CONDITIONAL_FALSE_VALUE, "Conditional: False"); //$NON-NLS-1$

	/**
	 * forExpression: Creating the receiver for a field access (i.e. the expression before the ".")
	 */
	public static final int FIELD_RECEIVER_VALUE = 10;

	public static final ForExpression FIELD_RECEIVER = new ForExpression(FIELD_RECEIVER_VALUE, "Field Access: Receiver"); //$NON-NLS-1$

	/**
	 * forExpression: Creating the left operand of an infix expression.
	 */
	public static final int INFIX_LEFT_VALUE = 11;

	public static final ForExpression INFIX_LEFT = new ForExpression(INFIX_LEFT_VALUE, "Infix: Left"); //$NON-NLS-1$

	/**
	 * forExpression: Creating the right operand of an infix expression.
	 */
	public static final int INFIX_RIGHT_VALUE = 12;

	public static final ForExpression INFIX_RIGHT = new ForExpression(INFIX_RIGHT_VALUE, "Infix: Right"); //$NON-NLS-1$

	/**
	 * forExpression: Creating an extended operand of an infix expression.
	 */
	public static final int INFIX_EXTENDED_VALUE = 13;

	public static final ForExpression INFIX_EXTENDED = new ForExpression(INFIX_EXTENDED_VALUE, "Infix: Extended"); //$NON-NLS-1$

	/**
	 * forExpression: Creating the value expression of an instanceof.
	 */
	public static final int INSTANCEOF_VALUE_VALUE = 14;

	public static final ForExpression INSTANCEOF_VALUE = new ForExpression(INSTANCEOF_VALUE_VALUE, "Instanceof"); //$NON-NLS-1$

	/**
	 * forExpression: Creating a receiver expression for a method invocation.
	 */
	public static final int METHOD_RECEIVER_VALUE = 15;

	public static final ForExpression METHOD_RECEIVER = new ForExpression(METHOD_RECEIVER_VALUE, "Method Invoke: Receiver"); //$NON-NLS-1$

	/**
	 * forExpression: Creating an argument for a method invocation.
	 */
	public static final int METHOD_ARGUMENT_VALUE = 16;

	public static final ForExpression METHOD_ARGUMENT = new ForExpression(METHOD_ARGUMENT_VALUE, "Method Invoke: Argument"); //$NON-NLS-1$

	/**
	 * forExpression: Creating the operand expression for a prefix operator.
	 */
	public static final int PREFIX_OPERAND_VALUE = 17;

	public static final ForExpression PREFIX_OPERAND = new ForExpression(PREFIX_OPERAND_VALUE, "Prefix: Operand"); //$NON-NLS-1$

	/**
	 * forExpression: Creating the right operand of an assignment operator.
	 * 
	 * @since 1.1.0
	 */
	public static final int ASSIGNMENT_RIGHT_VALUE = 18;

	public static final ForExpression ASSIGNMENT_RIGHT = new ForExpression(ASSIGNMENT_RIGHT_VALUE, "Assignment: Right"); //$NON-NLS-1$

	/**
	 * forExpression: Creating the left operand of an assignment operator.
	 * <p>
	 * Note: The left expression can only be a FieldAccess or ArrayAccess.
	 */
	public static final int ASSIGNMENT_LEFT_VALUE = 19;

	public static final ForExpression ASSIGNMENT_LEFT = new ForExpression(ASSIGNMENT_LEFT_VALUE, "Assignment: Left"); //$NON-NLS-1$

	/**
	 * forExpression: Creating the throw operand of the throw expression.
	 */
	public static final int THROW_OPERAND_VALUE = 20;

	public static final ForExpression THROW_OPERAND = new ForExpression(THROW_OPERAND_VALUE, "Throw operand"); //$NON-NLS-1$

	/**
	 * forExpression: Creating the if conditional operand of the if expression.
	 */
	public static final int IF_CONDITION_VALUE = 21;

	public static final ForExpression IF_CONDITION = new ForExpression(IF_CONDITION_VALUE, "If condition"); //$NON-NLS-1$

	/**
	 * forExpression: Creating the if true clause of the if expression.
	 */
	public static final int IF_TRUE_VALUE = 22;

	public static final ForExpression IF_TRUE = new ForExpression(IF_TRUE_VALUE, "If true clause"); //$NON-NLS-1$

	/**
	 * forExpression: Creating the if else clause of the if expression.
	 */
	public static final int IF_ELSE_VALUE = 23;

	public static final ForExpression IF_ELSE = new ForExpression(IF_ELSE_VALUE, "If else clause"); //$NON-NLS-1$

	
	/**
	 * This is not private, but protected only so that proxy core can add an internal ForExpression enum.
	 * <p>
	 * This is not meant to be subclassed by customers.
	 * @param value
	 * @param name
	 * 
	 * @since 1.1.0
	 */
	protected ForExpression(int value, String name) {
		super(value, name);
	}

}