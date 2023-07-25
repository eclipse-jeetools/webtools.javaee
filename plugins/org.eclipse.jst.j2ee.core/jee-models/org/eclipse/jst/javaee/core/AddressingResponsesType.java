/*******************************************************************************
 * Copyright (c) 2009, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Addressing Responses Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 * 
 *         If WS-Addressing is enabled, this type determines if an endpoint
 *         requires the use of only anonymous responses, or only non-anonymous
 *         responses, or all.
 *         
 *         @since Java EE 6, Web Services Client 1.3
 *       
 * <!-- end-model-doc -->
 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getAddressingResponsesType()
 * @generated
 */
public enum AddressingResponsesType implements Enumerator {
	/**
	 * The '<em><b>ANONYMOUS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ANONYMOUS_VALUE
	 * @generated
	 * @ordered
	 */
	ANONYMOUS(0, "ANONYMOUS", "ANONYMOUS"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>NONANONYMOUS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NONANONYMOUS_VALUE
	 * @generated
	 * @ordered
	 */
	NONANONYMOUS(1, "NONANONYMOUS", "NON_ANONYMOUS"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>ALL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ALL_VALUE
	 * @generated
	 * @ordered
	 */
	ALL(2, "ALL", "ALL"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>ANONYMOUS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ANONYMOUS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ANONYMOUS
	 * @generated
	 * @ordered
	 */
	public static final int ANONYMOUS_VALUE = 0;

	/**
	 * The '<em><b>NONANONYMOUS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NONANONYMOUS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NONANONYMOUS
	 * @generated
	 * @ordered
	 */
	public static final int NONANONYMOUS_VALUE = 1;

	/**
	 * The '<em><b>ALL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ALL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ALL
	 * @generated
	 * @ordered
	 */
	public static final int ALL_VALUE = 2;

	/**
	 * An array of all the '<em><b>Addressing Responses Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final AddressingResponsesType[] VALUES_ARRAY =
		new AddressingResponsesType[] {
			ANONYMOUS,
			NONANONYMOUS,
			ALL,
		};

	/**
	 * A public read-only list of all the '<em><b>Addressing Responses Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<AddressingResponsesType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Addressing Responses Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AddressingResponsesType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AddressingResponsesType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Addressing Responses Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AddressingResponsesType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AddressingResponsesType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Addressing Responses Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AddressingResponsesType get(int value) {
		switch (value) {
			case ANONYMOUS_VALUE: return ANONYMOUS;
			case NONANONYMOUS_VALUE: return NONANONYMOUS;
			case ALL_VALUE: return ALL;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private AddressingResponsesType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //AddressingResponsesType
