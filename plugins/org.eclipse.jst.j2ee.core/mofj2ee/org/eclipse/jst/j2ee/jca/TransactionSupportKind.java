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
package org.eclipse.jst.j2ee.jca;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;





/**
 * @lastgen interface TransactionSupportKind {}
 */
public final class TransactionSupportKind extends AbstractEnumerator{
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int NO_TRANSACTION= 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int LOCAL_TRANSACTION= 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int XA_TRANSACTION= 2;

	/**
	 * The '<em><b>No Transaction</b></em>' literal object.
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
	public static final TransactionSupportKind NO_TRANSACTION_LITERAL = new TransactionSupportKind(NO_TRANSACTION, "NoTransaction");//$NON-NLS-1$

	/**
	 * The '<em><b>Local Transaction</b></em>' literal object.
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
	public static final TransactionSupportKind LOCAL_TRANSACTION_LITERAL = new TransactionSupportKind(LOCAL_TRANSACTION, "LocalTransaction");//$NON-NLS-1$

	/**
	 * The '<em><b>XA Transaction</b></em>' literal object.
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
	public static final TransactionSupportKind XA_TRANSACTION_LITERAL = new TransactionSupportKind(XA_TRANSACTION, "XATransaction");//$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Transaction Support Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final TransactionSupportKind[] VALUES_ARRAY =
		new TransactionSupportKind[] {
			NO_TRANSACTION_LITERAL,
			LOCAL_TRANSACTION_LITERAL,
			XA_TRANSACTION_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Transaction Support Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Transaction Support Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TransactionSupportKind get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TransactionSupportKind result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Transaction Support Kind</b></em>' literal with the specified value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TransactionSupportKind get(int value) {
		switch (value) {
			case NO_TRANSACTION: return NO_TRANSACTION_LITERAL;
			case LOCAL_TRANSACTION: return LOCAL_TRANSACTION_LITERAL;
			case XA_TRANSACTION: return XA_TRANSACTION_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private TransactionSupportKind(int value, String name) {
		super(value, name);
	}

} //TransactionSupportKind
