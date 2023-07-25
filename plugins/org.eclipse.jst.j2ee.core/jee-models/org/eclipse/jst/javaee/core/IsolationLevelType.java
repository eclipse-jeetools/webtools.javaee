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
 * A representation of the literals of the enumeration '<em><b>Isolation Level Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 * 
 *         	The following transaction isolation levels are allowed
 *         	(see documentation for the java.sql.Connection interface):
 *         TRANSACTION_READ_UNCOMMITTED
 *         TRANSACTION_READ_COMMITTED
 *         TRANSACTION_REPEATABLE_READ
 *         TRANSACTION_SERIALIZABLE
 *         
 *         @since Java EE 6
 *       
 * <!-- end-model-doc -->
 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getIsolationLevelType()
 * @generated
 */
public enum IsolationLevelType implements Enumerator {

	/**
	 * The '<em><b>TRANSACTIONREADUNCOMMITTED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TRANSACTIONREADUNCOMMITTED_VALUE
	 * @generated
	 * @ordered
	 */
	TRANSACTIONREADUNCOMMITTED(0, "TRANSACTIONREADUNCOMMITTED", "TRANSACTION_READ_UNCOMMITTED"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>TRANSACTIONREADCOMMITTED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TRANSACTIONREADCOMMITTED_VALUE
	 * @generated
	 * @ordered
	 */
	TRANSACTIONREADCOMMITTED(1, "TRANSACTIONREADCOMMITTED", "TRANSACTION_READ_COMMITTED"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>TRANSACTIONREPEATABLEREAD</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TRANSACTIONREPEATABLEREAD_VALUE
	 * @generated
	 * @ordered
	 */
	TRANSACTIONREPEATABLEREAD(2, "TRANSACTIONREPEATABLEREAD", "TRANSACTION_REPEATABLE_READ"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>TRANSACTIONSERIALIZABLE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TRANSACTIONSERIALIZABLE_VALUE
	 * @generated
	 * @ordered
	 */
	TRANSACTIONSERIALIZABLE(3, "TRANSACTIONSERIALIZABLE", "TRANSACTION_SERIALIZABLE"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>TRANSACTIONREADUNCOMMITTED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TRANSACTIONREADUNCOMMITTED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TRANSACTIONREADUNCOMMITTED
	 * @generated
	 * @ordered
	 */
	public static final int TRANSACTIONREADUNCOMMITTED_VALUE = 0;

	/**
	 * The '<em><b>TRANSACTIONREADCOMMITTED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TRANSACTIONREADCOMMITTED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TRANSACTIONREADCOMMITTED
	 * @generated
	 * @ordered
	 */
	public static final int TRANSACTIONREADCOMMITTED_VALUE = 1;

	/**
	 * The '<em><b>TRANSACTIONREPEATABLEREAD</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TRANSACTIONREPEATABLEREAD</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TRANSACTIONREPEATABLEREAD
	 * @generated
	 * @ordered
	 */
	public static final int TRANSACTIONREPEATABLEREAD_VALUE = 2;

	/**
	 * The '<em><b>TRANSACTIONSERIALIZABLE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TRANSACTIONSERIALIZABLE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TRANSACTIONSERIALIZABLE
	 * @generated
	 * @ordered
	 */
	public static final int TRANSACTIONSERIALIZABLE_VALUE = 3;

	/**
	 * An array of all the '<em><b>Isolation Level Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final IsolationLevelType[] VALUES_ARRAY =
		new IsolationLevelType[] {
			TRANSACTIONREADUNCOMMITTED,
			TRANSACTIONREADCOMMITTED,
			TRANSACTIONREPEATABLEREAD,
			TRANSACTIONSERIALIZABLE,
		};

	/**
	 * A public read-only list of all the '<em><b>Isolation Level Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<IsolationLevelType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Isolation Level Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static IsolationLevelType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			IsolationLevelType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Isolation Level Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static IsolationLevelType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			IsolationLevelType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Isolation Level Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static IsolationLevelType get(int value) {
		switch (value) {
			case TRANSACTIONREADUNCOMMITTED_VALUE: return TRANSACTIONREADUNCOMMITTED;
			case TRANSACTIONREADCOMMITTED_VALUE: return TRANSACTIONREADCOMMITTED;
			case TRANSACTIONREPEATABLEREAD_VALUE: return TRANSACTIONREPEATABLEREAD;
			case TRANSACTIONSERIALIZABLE_VALUE: return TRANSACTIONSERIALIZABLE;
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
	private IsolationLevelType(int value, String name, String literal) {
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
	
} //IsolationLevelType
