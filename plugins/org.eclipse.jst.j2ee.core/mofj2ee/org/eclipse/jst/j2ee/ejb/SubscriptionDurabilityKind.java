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
package org.eclipse.jst.j2ee.ejb;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;





/**
 * @lastgen interface SubscriptionDurabilityKind {}
 */
public final class SubscriptionDurabilityKind extends AbstractEnumerator{
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int DURABLE= 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int NON_DURABLE= 1;

	/**
	 * The '<em><b>Durable</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Durable</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DURABLE
	 * @generated
	 * @ordered
	 */
	public static final SubscriptionDurabilityKind DURABLE_LITERAL = new SubscriptionDurabilityKind(DURABLE, "Durable"); //$NON-NLS-1$

	/**
	 * The '<em><b>Non Durable</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Non Durable</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NON_DURABLE
	 * @generated
	 * @ordered
	 */
	public static final SubscriptionDurabilityKind NON_DURABLE_LITERAL = new SubscriptionDurabilityKind(NON_DURABLE, "NonDurable"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Subscription Durability Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final SubscriptionDurabilityKind[] VALUES_ARRAY =
		new SubscriptionDurabilityKind[] {
			DURABLE_LITERAL,
			NON_DURABLE_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Subscription Durability Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Subscription Durability Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * @param name passes literal name
	 * @return literal instance
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SubscriptionDurabilityKind get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SubscriptionDurabilityKind result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Subscription Durability Kind</b></em>' literal with the specified value.
	 * <!-- begin-user-doc -->
	 * @param value passes literal value
	 * @return literal instance
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SubscriptionDurabilityKind get(int value) {
		switch (value) {
			case DURABLE: return DURABLE_LITERAL;
			case NON_DURABLE: return NON_DURABLE_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private SubscriptionDurabilityKind(int value, String name) {
		super(value, name);
	}

} //SubscriptionDurabilityKind
