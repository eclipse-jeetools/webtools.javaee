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
 * @lastgen interface SessionType {}
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
	public static final SessionType STATEFUL_LITERAL = new SessionType(STATEFUL, "Stateful"); //$NON-NLS-1$

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
	public static final SessionType STATELESS_LITERAL = new SessionType(STATELESS, "Stateless"); //$NON-NLS-1$

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
		};

	/**
	 * A public read-only list of all the '<em><b>Session Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Session Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * @param name passes literal name
	 * @return literal instance
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SessionType get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SessionType result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Session Type</b></em>' literal with the specified value.
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
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private SessionType(int value, String name) {
		super(value, name);
	}

} //SessionType
