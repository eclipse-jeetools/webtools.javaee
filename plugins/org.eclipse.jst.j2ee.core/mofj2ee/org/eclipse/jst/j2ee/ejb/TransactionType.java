/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;





/**
 * @lastgen interface TransactionType {}
 * @since 1.0
 */
public final class TransactionType extends AbstractEnumerator{
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int BEAN= 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int CONTAINER= 1;

	/**
	 * The '<em><b>Bean</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Bean</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BEAN
	 * @generated
	 * @ordered
	 */
	public static final TransactionType BEAN_LITERAL = new TransactionType(BEAN, "Bean"); //$NON-NLS-1$

	/**
	 * The '<em><b>Container</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Container</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONTAINER
	 * @generated
	 * @ordered
	 */
	public static final TransactionType CONTAINER_LITERAL = new TransactionType(CONTAINER, "Container"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Transaction Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final TransactionType[] VALUES_ARRAY =
		new TransactionType[] {
			BEAN_LITERAL,
			CONTAINER_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Transaction Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Transaction Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * @param name passes literal name
	 * @return literal instance
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TransactionType get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TransactionType result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Transaction Type</b></em>' literal with the specified value.
	 * <!-- begin-user-doc -->
	 * @param value passes literal value
	 * @return literal instance
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TransactionType get(int value) {
		switch (value) {
			case BEAN: return BEAN_LITERAL;
			case CONTAINER: return CONTAINER_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private TransactionType(int value, String name) {
		super(value, name);
	}

} //TransactionType
