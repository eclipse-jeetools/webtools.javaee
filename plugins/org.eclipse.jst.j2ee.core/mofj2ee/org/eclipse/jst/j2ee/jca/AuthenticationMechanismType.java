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
package org.eclipse.jst.j2ee.jca;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;





/**
 * @lastgen interface AuthenticationMechanismType {}
 * @since 1.0
 */
public final class AuthenticationMechanismType extends AbstractEnumerator{
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int BASIC_PASSWORD= 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int KERBV5= 1;

	/**
	 * The '<em><b>Basic Password</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Basic Password</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BASIC_PASSWORD
	 * @generated
	 * @ordered
	 */
	public static final AuthenticationMechanismType BASIC_PASSWORD_LITERAL = new AuthenticationMechanismType(BASIC_PASSWORD, "BasicPassword");//$NON-NLS-1$

	/**
	 * The '<em><b>Kerbv5</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Kerbv5</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #KERBV5
	 * @generated
	 * @ordered
	 */
	public static final AuthenticationMechanismType KERBV5_LITERAL = new AuthenticationMechanismType(KERBV5, "Kerbv5");//$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Authentication Mechanism Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final AuthenticationMechanismType[] VALUES_ARRAY =
		new AuthenticationMechanismType[] {
			BASIC_PASSWORD_LITERAL,
			KERBV5_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Authentication Mechanism Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Authentication Mechanism Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * @param name passes literal name
	 * @return literal instance
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AuthenticationMechanismType get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AuthenticationMechanismType result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Authentication Mechanism Type</b></em>' literal with the specified value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @param value passes literal value
	 * @return literal instance
	 */
	public static AuthenticationMechanismType get(int value) {
		switch (value) {
			case BASIC_PASSWORD: return BASIC_PASSWORD_LITERAL;
			case KERBV5: return KERBV5_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private AuthenticationMechanismType(int value, String name) {
		super(value, name);
	}

} //AuthenticationMechanismType
