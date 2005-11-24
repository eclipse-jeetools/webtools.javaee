/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.webapplication;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;





public final class TransportGuaranteeType extends AbstractEnumerator{
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int NONE = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int INTEGRAL = 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int CONFIDENTIAL = 2;
	/**
	 * The '<em><b>NONE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NONE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NONE
	 * @generated
	 * @ordered
	 */
	public static final TransportGuaranteeType NONE_LITERAL = new TransportGuaranteeType(NONE, "NONE");//$NON-NLS-1$

	/**
	 * The '<em><b>INTEGRAL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>INTEGRAL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #INTEGRAL
	 * @generated
	 * @ordered
	 */
	public static final TransportGuaranteeType INTEGRAL_LITERAL = new TransportGuaranteeType(INTEGRAL, "INTEGRAL");//$NON-NLS-1$

	/**
	 * The '<em><b>CONFIDENTIAL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CONFIDENTIAL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONFIDENTIAL
	 * @generated
	 * @ordered
	 */
	public static final TransportGuaranteeType CONFIDENTIAL_LITERAL = new TransportGuaranteeType(CONFIDENTIAL, "CONFIDENTIAL");//$NON-NLS-1$

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
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Transport Guarantee Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * @param name passes literal name
	 * @return literal instance
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TransportGuaranteeType get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TransportGuaranteeType result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Transport Guarantee Type</b></em>' literal with the specified value.
	 * <!-- begin-user-doc -->
	 * @param value passes literal value
	 * @return literal instance
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
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private TransportGuaranteeType(int value, String name) {
		super(value, name);
	}

} //TransportGuaranteeType









