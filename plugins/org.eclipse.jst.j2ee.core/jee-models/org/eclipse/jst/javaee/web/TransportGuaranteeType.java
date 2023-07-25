/*******************************************************************************
 * Copyright (c) 2007, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.web;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Transport Guarantee Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 * 
 *         The transport-guaranteeType specifies that the communication
 *         between client and server should be NONE, INTEGRAL, or
 *         CONFIDENTIAL. NONE means that the application does not
 *         require any transport guarantees. A value of INTEGRAL means
 *         that the application requires that the data sent between the
 *         client and server be sent in such a way that it can't be
 *         changed in transit. CONFIDENTIAL means that the application
 *         requires that the data be transmitted in a fashion that
 *         prevents other entities from observing the contents of the
 *         transmission. In most cases, the presence of the INTEGRAL or
 *         CONFIDENTIAL flag will indicate that the use of SSL is
 *         required.
 *         
 *         Used in: user-data-constraint
 *         
 *         @since Java EE 5, Web 2.5
 *       
 * <!-- end-model-doc -->
 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getTransportGuaranteeType()
 * @generated
 */
public enum TransportGuaranteeType implements Enumerator
{
	/**
	 * The '<em><b>NONE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NONE
	 * @generated
	 * @ordered
	 */
	NONE_LITERAL(0, "NONE", "NONE"), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>INTEGRAL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INTEGRAL
	 * @generated
	 * @ordered
	 */
	INTEGRAL_LITERAL(1, "INTEGRAL", "INTEGRAL"), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>CONFIDENTIAL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONFIDENTIAL
	 * @generated
	 * @ordered
	 */
	CONFIDENTIAL_LITERAL(2, "CONFIDENTIAL", "CONFIDENTIAL"); //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>NONE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NONE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NONE_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int NONE = 0;

	/**
	 * The '<em><b>INTEGRAL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>INTEGRAL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #INTEGRAL_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int INTEGRAL = 1;

	/**
	 * The '<em><b>CONFIDENTIAL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CONFIDENTIAL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONFIDENTIAL_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int CONFIDENTIAL = 2;

	/**
	 * An array of all the '<em><b>Transport Guarantee Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final TransportGuaranteeType[] VALUES_ARRAY =
		new TransportGuaranteeType[] {
			NONE_LITERAL,
			INTEGRAL_LITERAL,
			CONFIDENTIAL_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Transport Guarantee Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<TransportGuaranteeType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Transport Guarantee Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TransportGuaranteeType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TransportGuaranteeType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Transport Guarantee Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TransportGuaranteeType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TransportGuaranteeType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Transport Guarantee Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TransportGuaranteeType get(int value) {
		switch (value) {
			case NONE: return NONE_LITERAL;
			case INTEGRAL: return INTEGRAL_LITERAL;
			case CONFIDENTIAL: return CONFIDENTIAL_LITERAL;
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
	private TransportGuaranteeType(int value, String name, String literal) {
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
}
