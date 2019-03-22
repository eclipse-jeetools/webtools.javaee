/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.jca;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Transaction Support Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 * 
 *         The transaction-supportType specifies the level of
 *         transaction support provided by the resource adapter. It is
 *         used by transaction-support elements.
 *         
 *         The value must be one of the following:
 *         
 *         NoTransaction
 *         LocalTransaction
 *         XATransaction
 *         
 *         @since Java EE 6, Connector 1.6
 *       
 * <!-- end-model-doc -->
 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getTransactionSupportType()
 * @generated
 */
public enum TransactionSupportType implements Enumerator {
	/**
	 * The '<em><b>No Transaction</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NO_TRANSACTION_VALUE
	 * @generated
	 * @ordered
	 */
	NO_TRANSACTION(0, "NoTransaction", "NoTransaction"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Local Transaction</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOCAL_TRANSACTION_VALUE
	 * @generated
	 * @ordered
	 */
	LOCAL_TRANSACTION(1, "LocalTransaction", "LocalTransaction"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>XA Transaction</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #XA_TRANSACTION_VALUE
	 * @generated
	 * @ordered
	 */
	XA_TRANSACTION(2, "XATransaction", "XATransaction"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>No Transaction</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>No Transaction</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NO_TRANSACTION
	 * @generated
	 * @ordered
	 */
	public static final int NO_TRANSACTION_VALUE = 0;

	/**
	 * The '<em><b>Local Transaction</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Local Transaction</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LOCAL_TRANSACTION
	 * @generated
	 * @ordered
	 */
	public static final int LOCAL_TRANSACTION_VALUE = 1;

	/**
	 * The '<em><b>XA Transaction</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>XA Transaction</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #XA_TRANSACTION
	 * @generated
	 * @ordered
	 */
	public static final int XA_TRANSACTION_VALUE = 2;

	/**
	 * An array of all the '<em><b>Transaction Support Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final TransactionSupportType[] VALUES_ARRAY =
		new TransactionSupportType[] {
			NO_TRANSACTION,
			LOCAL_TRANSACTION,
			XA_TRANSACTION,
		};

	/**
	 * A public read-only list of all the '<em><b>Transaction Support Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<TransactionSupportType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Transaction Support Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TransactionSupportType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TransactionSupportType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Transaction Support Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TransactionSupportType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TransactionSupportType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Transaction Support Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TransactionSupportType get(int value) {
		switch (value) {
			case NO_TRANSACTION_VALUE: return NO_TRANSACTION;
			case LOCAL_TRANSACTION_VALUE: return LOCAL_TRANSACTION;
			case XA_TRANSACTION_VALUE: return XA_TRANSACTION;
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
	private TransactionSupportType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
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
	
} //TransactionSupportType
