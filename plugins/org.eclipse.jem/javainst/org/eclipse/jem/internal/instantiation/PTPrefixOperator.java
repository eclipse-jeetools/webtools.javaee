package org.eclipse.jem.internal.instantiation;
/*******************************************************************************
 * Copyright (c)  2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: PTPrefixOperator.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:36:09 $ 
 */
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Prefix Operator</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * The prefix expression operator. We are not modeling ++ and --.
 * <!-- end-model-doc -->
 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTPrefixOperator()
 * @model
 * @generated
 */
public final class PTPrefixOperator extends AbstractEnumerator {
	/**
	 * The '<em><b>PLUS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PLUS_LITERAL
	 * @model 
	 * @generated
	 * @ordered
	 */
	public static final int PLUS = 0;

	/**
	 * The '<em><b>MINUS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MINUS_LITERAL
	 * @model 
	 * @generated
	 * @ordered
	 */
	public static final int MINUS = 1;

	/**
	 * The '<em><b>COMPLEMENT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #COMPLEMENT_LITERAL
	 * @model 
	 * @generated
	 * @ordered
	 */
	public static final int COMPLEMENT = 2;

	/**
	 * The '<em><b>NOT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NOT_LITERAL
	 * @model 
	 * @generated
	 * @ordered
	 */
	public static final int NOT = 3;

	/**
	 * The '<em><b>PLUS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * + operator
	 * <!-- end-model-doc -->
	 * @see #PLUS
	 * @generated
	 * @ordered
	 */
	public static final PTPrefixOperator PLUS_LITERAL = new PTPrefixOperator(PLUS, "PLUS");

	/**
	 * The '<em><b>MINUS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * - operator
	 * <!-- end-model-doc -->
	 * @see #MINUS
	 * @generated
	 * @ordered
	 */
	public static final PTPrefixOperator MINUS_LITERAL = new PTPrefixOperator(MINUS, "MINUS");

	/**
	 * The '<em><b>COMPLEMENT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * ~ operator
	 * <!-- end-model-doc -->
	 * @see #COMPLEMENT
	 * @generated
	 * @ordered
	 */
	public static final PTPrefixOperator COMPLEMENT_LITERAL = new PTPrefixOperator(COMPLEMENT, "COMPLEMENT");

	/**
	 * The '<em><b>NOT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * ! operator
	 * <!-- end-model-doc -->
	 * @see #NOT
	 * @generated
	 * @ordered
	 */
	public static final PTPrefixOperator NOT_LITERAL = new PTPrefixOperator(NOT, "NOT");

	/**
	 * An array of all the '<em><b>PT Prefix Operator</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final PTPrefixOperator[] VALUES_ARRAY =
		new PTPrefixOperator[] {
			PLUS_LITERAL,
			MINUS_LITERAL,
			COMPLEMENT_LITERAL,
			NOT_LITERAL,
		};
	
	private static final String[] OPERATOR_STRING;
	static {
		OPERATOR_STRING = new String[VALUES_ARRAY.length];
		OPERATOR_STRING[PLUS] = "+";
		OPERATOR_STRING[MINUS] = "-";
		OPERATOR_STRING[COMPLEMENT] = "~";
		OPERATOR_STRING[NOT] = "!";
	}

	/**
	 * A public read-only list of all the '<em><b>PT Prefix Operator</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>PT Prefix Operator</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PTPrefixOperator get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PTPrefixOperator result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>PT Prefix Operator</b></em>' literal with the specified value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PTPrefixOperator get(int value) {
		switch (value) {
			case PLUS: return PLUS_LITERAL;
			case MINUS: return MINUS_LITERAL;
			case COMPLEMENT: return COMPLEMENT_LITERAL;
			case NOT: return NOT_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private PTPrefixOperator(int value, String name) {
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
} //PrefixOperator
