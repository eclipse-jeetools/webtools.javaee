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
 *  $RCSfile: InfixOperator.java,v $
 *  $Revision: 1.1 $  $Date: 2005/05/11 19:01:12 $ 
 */
package org.eclipse.jem.internal.proxy.initParser.tree;

/**
 * Enum for Infix operator
 * 
 * @since 1.1.0
 */
public class InfixOperator extends AbstractEnum {

	/**
	 * Infix times "*"
	 */
	public static final int IN_TIMES_VALUE = 0;

	public static final InfixOperator IN_TIMES = new InfixOperator(IN_TIMES_VALUE, "Infix *");

	/**
	 * Infix divide "/"
	 */
	public static final int IN_DIVIDE_VALUE = 1;

	public static final InfixOperator IN_DIVIDE = new InfixOperator(IN_DIVIDE_VALUE, "Infix /");

	/**
	 * Infix remainder "%"
	 */
	public static final int IN_REMAINDER_VALUE = 2;

	public static final InfixOperator IN_REMAINDER = new InfixOperator(IN_REMAINDER_VALUE, "Infix %");

	/**
	 * Infix plus "+"
	 */
	public static final int IN_PLUS_VALUE = 3;

	public static final InfixOperator IN_PLUS = new InfixOperator(IN_PLUS_VALUE, "Infix +");

	/**
	 * Infix minus "-"
	 */
	public static final int IN_MINUS_VALUE = 4;

	public static final InfixOperator IN_MINUS = new InfixOperator(IN_MINUS_VALUE, "Infix -");

	/**
	 * Infix left shift " < <"
	 */
	public static final int IN_LEFT_SHIFT_VALUE = 5;

	public static final InfixOperator IN_LEFT_SHIFT = new InfixOperator(IN_LEFT_SHIFT_VALUE, "Infix <<");

	/**
	 * Infix right shift signed ">>"
	 */
	public static final int IN_RIGHT_SHIFT_SIGNED_VALUE = 6;

	public static final InfixOperator IN_RIGHT_SHIFT_SIGNED = new InfixOperator(IN_RIGHT_SHIFT_SIGNED_VALUE, "Infix >>");

	/**
	 * Infix right shift unsigned ">>>"
	 */
	public static final int IN_RIGHT_SHIFT_UNSIGNED_VALUE = 7;

	public static final InfixOperator IN_RIGHT_SHIFT_UNSIGNED = new InfixOperator(IN_RIGHT_SHIFT_UNSIGNED_VALUE, "Infix >>>");

	/**
	 * Infix less " <"
	 */
	public static final int IN_LESS_VALUE = 8;

	public static final InfixOperator IN_LESS = new InfixOperator(IN_LESS_VALUE, "Infix <");

	/**
	 * Infix greater ">"
	 */
	public static final int IN_GREATER_VALUE = 9;

	public static final InfixOperator IN_GREATER = new InfixOperator(IN_GREATER_VALUE, "Infix >");

	/**
	 * Infix less than or equals " <="
	 */
	public static final int IN_LESS_EQUALS_VALUE = 10;

	public static final InfixOperator IN_LESS_EQUALS = new InfixOperator(IN_LESS_EQUALS_VALUE, "Infix <=");

	/**
	 * Infix Greater than or equlas ">="
	 */
	public static final int IN_GREATER_EQUALS_VALUE = 11;

	public static final InfixOperator IN_GREATER_EQUALS = new InfixOperator(IN_GREATER_EQUALS_VALUE, "Infix >=");

	/**
	 * Infix equals "=="
	 */
	public static final int IN_EQUALS_VALUE = 12;

	public static final InfixOperator IN_EQUALS = new InfixOperator(IN_EQUALS_VALUE, "Infix ==");

	/**
	 * Infix not equals "!="
	 */
	public static final int IN_NOT_EQUALS_VALUE = 13;

	public static final InfixOperator IN_NOT_EQUALS = new InfixOperator(IN_NOT_EQUALS_VALUE, "Infix !=");

	/**
	 * Infix exclusive or "^"
	 */
	public static final int IN_XOR_VALUE = 14;

	public static final InfixOperator IN_XOR = new InfixOperator(IN_XOR_VALUE, "Infix ^");

	/**
	 * Infix bitwise and "&"
	 */
	public static final int IN_AND_VALUE = 15;

	public static final InfixOperator IN_AND = new InfixOperator(IN_AND_VALUE, "Infix &");

	/**
	 * Infix bitwise or "|"
	 */
	public static final int IN_OR_VALUE = 16;

	public static final InfixOperator IN_OR = new InfixOperator(IN_OR_VALUE, "Infix |");

	/**
	 * Infix Conditional logical and "&&"
	 */
	public static final int IN_CONDITIONAL_AND_VALUE = 17;

	public static final InfixOperator IN_CONDITIONAL_AND = new InfixOperator(IN_CONDITIONAL_AND_VALUE, "Infix &&");

	/**
	 * Infix Conditional logical or "||"
	 */
	public static final int IN_CONDITIONAL_OR_VALUE = 18;

	public static final InfixOperator IN_CONDITIONAL_OR = new InfixOperator(IN_CONDITIONAL_OR_VALUE, "Ifnix ||");

	/**
	 * Return the infix operator for the given enum value.
	 * @param value
	 * @return
	 * 
	 * @since 1.1.0
	 */
	public static InfixOperator get(int value) {
		switch (value) {
			case IN_TIMES_VALUE:
				return IN_TIMES;
			case IN_DIVIDE_VALUE:
				return IN_DIVIDE;
			case IN_REMAINDER_VALUE:
				return IN_REMAINDER;
			case IN_PLUS_VALUE:
				return IN_PLUS;
			case IN_MINUS_VALUE:
				return IN_MINUS;
			case IN_LEFT_SHIFT_VALUE:
				return IN_LEFT_SHIFT;
			case IN_RIGHT_SHIFT_SIGNED_VALUE:
				return IN_RIGHT_SHIFT_SIGNED;
			case IN_RIGHT_SHIFT_UNSIGNED_VALUE:
				return IN_RIGHT_SHIFT_UNSIGNED;
			case IN_LESS_VALUE:
				return IN_LESS;
			case IN_GREATER_VALUE:
				return IN_GREATER;
			case IN_LESS_EQUALS_VALUE:
				return IN_LESS_EQUALS;
			case IN_GREATER_EQUALS_VALUE:
				return IN_GREATER_EQUALS;
			case IN_EQUALS_VALUE:
				return IN_EQUALS;
			case IN_NOT_EQUALS_VALUE:
				return IN_NOT_EQUALS;
			case IN_XOR_VALUE:
				return IN_XOR;
			case IN_AND_VALUE:
				return IN_AND;
			case IN_OR_VALUE:
				return IN_OR;
			case IN_CONDITIONAL_AND_VALUE:
				return IN_CONDITIONAL_AND;
			case IN_CONDITIONAL_OR_VALUE:
				return IN_CONDITIONAL_OR;
		}
		return null;
	}

	private InfixOperator(int value, String name) {
		super(value, name);
	}

}