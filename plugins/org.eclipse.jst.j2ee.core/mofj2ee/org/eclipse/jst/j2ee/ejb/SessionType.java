/*******************************************************************************
 * Copyright (c) 2001, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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
 * @lastgen interface SessionType {}
 * @since 1.0
 */
public final class SessionType extends AbstractEnumerator{
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int STATEFUL= 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int STATELESS= 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int SINGLETON= 2;

	/**
	 * The '<em><b>Stateful</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Stateful</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STATEFUL
	 * @generated
	 * @ordered
	 */
	public static final SessionType STATEFUL_LITERAL = new SessionType(STATEFUL, "Stateful", "Stateful"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Stateless</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Stateless</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STATELESS
	 * @generated
	 * @ordered
	 */
	public static final SessionType STATELESS_LITERAL = new SessionType(STATELESS, "Stateless", "Stateless"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Singleton</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Singleton</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SINGLETON
	 * @generated
	 * @ordered
	 */
	public static final SessionType SINGLETON_LITERAL = new SessionType(SINGLETON, "Singleton", "Singleton"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * An array of all the '<em><b>Session Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final SessionType[] VALUES_ARRAY =
		new SessionType[] {
			STATEFUL_LITERAL,
			STATELESS_LITERAL,
			SINGLETON_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Session Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Session Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * @param name passes literal name
	 * @return literal instance
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SessionType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SessionType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Session Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SessionType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SessionType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Session Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * @param value passes literal value
	 * @return literal instance
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SessionType get(int value) {
		switch (value) {
			case STATEFUL: return STATEFUL_LITERAL;
			case STATELESS: return STATELESS_LITERAL;
			case SINGLETON: return SINGLETON_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private SessionType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //SessionType
