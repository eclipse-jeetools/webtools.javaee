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
package org.eclipse.jst.j2ee.webapplication;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;


/**
 * 
 * @since 1.0
 *
 */


public final class AuthMethodKind extends AbstractEnumerator{
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int UNSPECIFIED = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int BASIC = 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int DIGEST = 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int FORM = 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int CLIENT_CERT = 4;
	/**
	 * The '<em><b>UNSPECIFIED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UNSPECIFIED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNSPECIFIED
	 * @generated
	 * @ordered
	 */
	public static final AuthMethodKind UNSPECIFIED_LITERAL = new AuthMethodKind(UNSPECIFIED, "UNSPECIFIED", "UNSPECIFIED");//$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>BASIC</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>BASIC</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BASIC
	 * @generated
	 * @ordered
	 */
	public static final AuthMethodKind BASIC_LITERAL = new AuthMethodKind(BASIC, "BASIC", "BASIC");//$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>DIGEST</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DIGEST</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DIGEST
	 * @generated
	 * @ordered
	 */
	public static final AuthMethodKind DIGEST_LITERAL = new AuthMethodKind(DIGEST, "DIGEST", "DIGEST");//$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>FORM</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FORM</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FORM
	 * @generated
	 * @ordered
	 */
	public static final AuthMethodKind FORM_LITERAL = new AuthMethodKind(FORM, "FORM", "FORM");//$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>CLIENT CERT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CLIENT CERT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CLIENT_CERT
	 * @generated
	 * @ordered
	 */
	public static final AuthMethodKind CLIENT_CERT_LITERAL = new AuthMethodKind(CLIENT_CERT, "CLIENT_CERT", "CLIENT_CERT");//$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * An array of all the '<em><b>Auth Method Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final AuthMethodKind[] VALUES_ARRAY =
		new AuthMethodKind[] {
			UNSPECIFIED_LITERAL,
			BASIC_LITERAL,
			DIGEST_LITERAL,
			FORM_LITERAL,
			CLIENT_CERT_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Auth Method Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Auth Method Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * @param name passes literal name
	 * @return literal instance
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AuthMethodKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AuthMethodKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Auth Method Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AuthMethodKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AuthMethodKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Auth Method Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AuthMethodKind get(int value) {
		switch (value) {
			case UNSPECIFIED: return UNSPECIFIED_LITERAL;
			case BASIC: return BASIC_LITERAL;
			case DIGEST: return DIGEST_LITERAL;
			case FORM: return FORM_LITERAL;
			case CLIENT_CERT: return CLIENT_CERT_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private AuthMethodKind(int value, String name, String literal) {
		super(value, name, literal);
	}

} //AuthMethodKind









