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
package org.eclipse.jst.javaee.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Persistence Context Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 * 
 *         The persistence-context-typeType specifies the transactional
 *         nature of a persistence context reference.  
 *         
 *         The value of the persistence-context-type element must be
 *         one of the following:
 *         Transaction
 *         Extended
 *         
 *         @since Java EE 5
 *       
 * <!-- end-model-doc -->
 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getPersistenceContextType()
 * @generated
 */
public enum PersistenceContextType implements Enumerator
{
	/**
	 * The '<em><b>Transaction</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TRANSACTION
	 * @generated
	 * @ordered
	 */
	TRANSACTION_LITERAL(0, "Transaction", "Transaction"), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>Extended</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EXTENDED
	 * @generated
	 * @ordered
	 */
	EXTENDED_LITERAL(1, "Extended", "Extended"); //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>Transaction</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Transaction</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TRANSACTION_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int TRANSACTION = 0;

	/**
	 * The '<em><b>Extended</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Extended</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #EXTENDED_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int EXTENDED = 1;

	/**
	 * An array of all the '<em><b>Persistence Context Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final PersistenceContextType[] VALUES_ARRAY =
		new PersistenceContextType[] {
			TRANSACTION_LITERAL,
			EXTENDED_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Persistence Context Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<PersistenceContextType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Persistence Context Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PersistenceContextType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PersistenceContextType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Persistence Context Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PersistenceContextType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PersistenceContextType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Persistence Context Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PersistenceContextType get(int value) {
		switch (value) {
			case TRANSACTION: return TRANSACTION_LITERAL;
			case EXTENDED: return EXTENDED_LITERAL;
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
	private PersistenceContextType(int value, String name, String literal) {
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
