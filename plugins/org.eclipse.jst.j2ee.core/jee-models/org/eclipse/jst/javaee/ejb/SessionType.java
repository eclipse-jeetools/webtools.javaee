/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.ejb;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Session Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 * 
 *         The session-typeType describes whether the session bean is a
 *         singleton, stateful or stateless session. It is used by
 *         session-type elements.
 *         
 *         The value must be one of the three following:
 *         
 *         Singleton
 *         Stateful
 *         Stateless
 *         
 *         @since Java EE 5, EJB 3.0
 *       
 * <!-- end-model-doc -->
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionType()
 * @generated
 */
public enum SessionType implements Enumerator
{
	/**
	 * The '<em><b>Singleton</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SINGLETON
	 * @generated
	 * @ordered
	 */
	SINGLETON_LITERAL(0, "Singleton", "Singleton"), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>Stateful</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STATEFUL
	 * @generated
	 * @ordered
	 */
	STATEFUL_LITERAL(1, "Stateful", "Stateful"), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>Stateless</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STATELESS
	 * @generated
	 * @ordered
	 */
	STATELESS_LITERAL(2, "Stateless", "Stateless"); //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>Singleton</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *               @since Java EE 6, EJB 3.1
	 *             
	 * <!-- end-model-doc -->
	 * @see #SINGLETON_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int SINGLETON = 0;

	/**
	 * The '<em><b>Stateful</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Stateful</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STATEFUL_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int STATEFUL = 1;

	/**
	 * The '<em><b>Stateless</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Stateless</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STATELESS_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int STATELESS = 2;

	/**
	 * An array of all the '<em><b>Session Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final SessionType[] VALUES_ARRAY =
		new SessionType[] {
			SINGLETON_LITERAL,
			STATEFUL_LITERAL,
			STATELESS_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Session Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<SessionType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Session Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
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
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SessionType get(int value) {
		switch (value) {
			case SINGLETON: return SINGLETON_LITERAL;
			case STATEFUL: return STATEFUL_LITERAL;
			case STATELESS: return STATELESS_LITERAL;
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
	private SessionType(int value, String name, String literal) {
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
}
