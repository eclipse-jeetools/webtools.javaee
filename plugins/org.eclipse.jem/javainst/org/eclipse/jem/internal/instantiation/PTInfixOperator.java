/*******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.instantiation;
/*
 *  $RCSfile: PTInfixOperator.java,v $
 *  $Revision: 1.5 $  $Date: 2005/09/15 21:02:19 $ 
 */
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Infix Operator</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Enumerator for the operator on an InfixExpression.
 * <!-- end-model-doc -->
 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTInfixOperator()
 * @model
 * @generated
 */
public final class PTInfixOperator extends AbstractEnumerator {
	/**
	 * The '<em><b>TIMES</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * * operator
	 * <!-- end-model-doc -->
	 * @see #TIMES_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int TIMES = 0;

	/**
	 * The '<em><b>DIVIDE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * / operator
	 * <!-- end-model-doc -->
	 * @see #DIVIDE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DIVIDE = 1;

	/**
	 * The '<em><b>REMAINDER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * % operator
	 * <!-- end-model-doc -->
	 * @see #REMAINDER_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int REMAINDER = 2;

	/**
	 * The '<em><b>PLUS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * + operator
	 * <!-- end-model-doc -->
	 * @see #PLUS_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int PLUS = 3;

	/**
	 * The '<em><b>MINUS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * - operator
	 * <!-- end-model-doc -->
	 * @see #MINUS_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int MINUS = 4;

	/**
	 * The '<em><b>LEFT SHIFT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * << operator
	 * <!-- end-model-doc -->
	 * @see #LEFT_SHIFT_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int LEFT_SHIFT = 5;

	/**
	 * The '<em><b>RIGHT SHIFT SIGNED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * >> operator
	 * <!-- end-model-doc -->
	 * @see #RIGHT_SHIFT_SIGNED_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int RIGHT_SHIFT_SIGNED = 6;

	/**
	 * The '<em><b>RIGHT SHIFT UNSIGNED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * >>> operator
	 * <!-- end-model-doc -->
	 * @see #RIGHT_SHIFT_UNSIGNED_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int RIGHT_SHIFT_UNSIGNED = 7;

	/**
	 * The '<em><b>LESS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * <  operator
	 * <!-- end-model-doc -->
	 * @see #LESS_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int LESS = 8;

	/**
	 * The '<em><b>GREATER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * > operator
	 * <!-- end-model-doc -->
	 * @see #GREATER_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int GREATER = 9;

	/**
	 * The '<em><b>LESS EQUALS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * <= operator
	 * <!-- end-model-doc -->
	 * @see #LESS_EQUALS_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int LESS_EQUALS = 10;

	/**
	 * The '<em><b>GREATER EQUALS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * >= operator
	 * <!-- end-model-doc -->
	 * @see #GREATER_EQUALS_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int GREATER_EQUALS = 11;

	/**
	 * The '<em><b>EQUALS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * == operator
	 * <!-- end-model-doc -->
	 * @see #EQUALS_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int EQUALS = 12;

	/**
	 * The '<em><b>NOT EQUALS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * != operator
	 * <!-- end-model-doc -->
	 * @see #NOT_EQUALS_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NOT_EQUALS = 13;

	/**
	 * The '<em><b>XOR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * ^ operator
	 * <!-- end-model-doc -->
	 * @see #XOR_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int XOR = 14;

	/**
	 * The '<em><b>AND</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * & operator
	 * <!-- end-model-doc -->
	 * @see #AND_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int AND = 15;

	/**
	 * The '<em><b>OR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * |  operator
	 * <!-- end-model-doc -->
	 * @see #OR_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int OR = 16;

	/**
	 * The '<em><b>CONDITIONAL AND</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * && operator
	 * <!-- end-model-doc -->
	 * @see #CONDITIONAL_AND_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CONDITIONAL_AND = 17;

	/**
	 * The '<em><b>CONDITIONAL OR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * || operator
	 * <!-- end-model-doc -->
	 * @see #CONDITIONAL_OR_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CONDITIONAL_OR = 18;

	/**
	 * The '<em><b>TIMES</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TIMES
	 * @generated
	 * @ordered
	 */
	public static final PTInfixOperator TIMES_LITERAL = new PTInfixOperator(TIMES, "TIMES");

	/**
	 * The '<em><b>DIVIDE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DIVIDE
	 * @generated
	 * @ordered
	 */
	public static final PTInfixOperator DIVIDE_LITERAL = new PTInfixOperator(DIVIDE, "DIVIDE");

	/**
	 * The '<em><b>REMAINDER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REMAINDER
	 * @generated
	 * @ordered
	 */
	public static final PTInfixOperator REMAINDER_LITERAL = new PTInfixOperator(REMAINDER, "REMAINDER");

	/**
	 * The '<em><b>PLUS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PLUS
	 * @generated
	 * @ordered
	 */
	public static final PTInfixOperator PLUS_LITERAL = new PTInfixOperator(PLUS, "PLUS");

	/**
	 * The '<em><b>MINUS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MINUS
	 * @generated
	 * @ordered
	 */
	public static final PTInfixOperator MINUS_LITERAL = new PTInfixOperator(MINUS, "MINUS");

	/**
	 * The '<em><b>LEFT SHIFT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LEFT_SHIFT
	 * @generated
	 * @ordered
	 */
	public static final PTInfixOperator LEFT_SHIFT_LITERAL = new PTInfixOperator(LEFT_SHIFT, "LEFT_SHIFT");

	/**
	 * The '<em><b>RIGHT SHIFT SIGNED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RIGHT_SHIFT_SIGNED
	 * @generated
	 * @ordered
	 */
	public static final PTInfixOperator RIGHT_SHIFT_SIGNED_LITERAL = new PTInfixOperator(RIGHT_SHIFT_SIGNED, "RIGHT_SHIFT_SIGNED");

	/**
	 * The '<em><b>RIGHT SHIFT UNSIGNED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RIGHT_SHIFT_UNSIGNED
	 * @generated
	 * @ordered
	 */
	public static final PTInfixOperator RIGHT_SHIFT_UNSIGNED_LITERAL = new PTInfixOperator(RIGHT_SHIFT_UNSIGNED, "RIGHT_SHIFT_UNSIGNED");

	/**
	 * The '<em><b>LESS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LESS
	 * @generated
	 * @ordered
	 */
	public static final PTInfixOperator LESS_LITERAL = new PTInfixOperator(LESS, "LESS");

	/**
	 * The '<em><b>GREATER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GREATER
	 * @generated
	 * @ordered
	 */
	public static final PTInfixOperator GREATER_LITERAL = new PTInfixOperator(GREATER, "GREATER");

	/**
	 * The '<em><b>LESS EQUALS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LESS_EQUALS
	 * @generated
	 * @ordered
	 */
	public static final PTInfixOperator LESS_EQUALS_LITERAL = new PTInfixOperator(LESS_EQUALS, "LESS_EQUALS");

	/**
	 * The '<em><b>GREATER EQUALS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GREATER_EQUALS
	 * @generated
	 * @ordered
	 */
	public static final PTInfixOperator GREATER_EQUALS_LITERAL = new PTInfixOperator(GREATER_EQUALS, "GREATER_EQUALS");

	/**
	 * The '<em><b>EQUALS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EQUALS
	 * @generated
	 * @ordered
	 */
	public static final PTInfixOperator EQUALS_LITERAL = new PTInfixOperator(EQUALS, "EQUALS");

	/**
	 * The '<em><b>NOT EQUALS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NOT_EQUALS
	 * @generated
	 * @ordered
	 */
	public static final PTInfixOperator NOT_EQUALS_LITERAL = new PTInfixOperator(NOT_EQUALS, "NOT_EQUALS");

	/**
	 * The '<em><b>XOR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #XOR
	 * @generated
	 * @ordered
	 */
	public static final PTInfixOperator XOR_LITERAL = new PTInfixOperator(XOR, "XOR");

	/**
	 * The '<em><b>AND</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #AND
	 * @generated
	 * @ordered
	 */
	public static final PTInfixOperator AND_LITERAL = new PTInfixOperator(AND, "AND");

	/**
	 * The '<em><b>OR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OR
	 * @generated
	 * @ordered
	 */
	public static final PTInfixOperator OR_LITERAL = new PTInfixOperator(OR, "OR");

	/**
	 * The '<em><b>CONDITIONAL AND</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONDITIONAL_AND
	 * @generated
	 * @ordered
	 */
	public static final PTInfixOperator CONDITIONAL_AND_LITERAL = new PTInfixOperator(CONDITIONAL_AND, "CONDITIONAL_AND");

	/**
	 * The '<em><b>CONDITIONAL OR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONDITIONAL_OR
	 * @generated
	 * @ordered
	 */
	public static final PTInfixOperator CONDITIONAL_OR_LITERAL = new PTInfixOperator(CONDITIONAL_OR, "CONDITIONAL_OR");

	/**
	 * An array of all the '<em><b>PT Infix Operator</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final PTInfixOperator[] VALUES_ARRAY =
		new PTInfixOperator[] {
			TIMES_LITERAL,
			DIVIDE_LITERAL,
			REMAINDER_LITERAL,
			PLUS_LITERAL,
			MINUS_LITERAL,
			LEFT_SHIFT_LITERAL,
			RIGHT_SHIFT_SIGNED_LITERAL,
			RIGHT_SHIFT_UNSIGNED_LITERAL,
			LESS_LITERAL,
			GREATER_LITERAL,
			LESS_EQUALS_LITERAL,
			GREATER_EQUALS_LITERAL,
			EQUALS_LITERAL,
			NOT_EQUALS_LITERAL,
			XOR_LITERAL,
			AND_LITERAL,
			OR_LITERAL,
			CONDITIONAL_AND_LITERAL,
			CONDITIONAL_OR_LITERAL,
		};
	
	private static final String[] OPERATOR_STRING;
	static {
		OPERATOR_STRING = new String[VALUES_ARRAY.length];
		OPERATOR_STRING[TIMES] = "*";
		OPERATOR_STRING[DIVIDE] = "/";
		OPERATOR_STRING[REMAINDER] = "%";
		OPERATOR_STRING[PLUS] = "+";
		OPERATOR_STRING[MINUS] = "-";
		OPERATOR_STRING[LEFT_SHIFT] = "<";
		OPERATOR_STRING[RIGHT_SHIFT_SIGNED] = ">>";
		OPERATOR_STRING[RIGHT_SHIFT_UNSIGNED] = ">>>";
		OPERATOR_STRING[LESS] = "<";
		OPERATOR_STRING[GREATER] = ">";
		OPERATOR_STRING[LESS_EQUALS] = "<=";
		OPERATOR_STRING[GREATER_EQUALS] = ">=";
		OPERATOR_STRING[EQUALS] = "==";
		OPERATOR_STRING[NOT_EQUALS] = "!=";
		OPERATOR_STRING[XOR] = "^";
		OPERATOR_STRING[AND] = "&";
		OPERATOR_STRING[OR] = "|";
		OPERATOR_STRING[CONDITIONAL_AND] = "&&";
		OPERATOR_STRING[CONDITIONAL_OR] = "||";
	}

	/**
	 * A public read-only list of all the '<em><b>PT Infix Operator</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>PT Infix Operator</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PTInfixOperator get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PTInfixOperator result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>PT Infix Operator</b></em>' literal with the specified value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PTInfixOperator get(int value) {
		switch (value) {
			case TIMES: return TIMES_LITERAL;
			case DIVIDE: return DIVIDE_LITERAL;
			case REMAINDER: return REMAINDER_LITERAL;
			case PLUS: return PLUS_LITERAL;
			case MINUS: return MINUS_LITERAL;
			case LEFT_SHIFT: return LEFT_SHIFT_LITERAL;
			case RIGHT_SHIFT_SIGNED: return RIGHT_SHIFT_SIGNED_LITERAL;
			case RIGHT_SHIFT_UNSIGNED: return RIGHT_SHIFT_UNSIGNED_LITERAL;
			case LESS: return LESS_LITERAL;
			case GREATER: return GREATER_LITERAL;
			case LESS_EQUALS: return LESS_EQUALS_LITERAL;
			case GREATER_EQUALS: return GREATER_EQUALS_LITERAL;
			case EQUALS: return EQUALS_LITERAL;
			case NOT_EQUALS: return NOT_EQUALS_LITERAL;
			case XOR: return XOR_LITERAL;
			case AND: return AND_LITERAL;
			case OR: return OR_LITERAL;
			case CONDITIONAL_AND: return CONDITIONAL_AND_LITERAL;
			case CONDITIONAL_OR: return CONDITIONAL_OR_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private PTInfixOperator(int value, String name) {
		super(value, name);
	}

	/**
	 * Return the operator string (i.e. for PLUS return "+").
	 * @return The operator string.
	 * 
	 * @since 1.0.0
	 */
	public String getOperator() {
		return OPERATOR_STRING[getValue()];
	}

} //InfixOperator
