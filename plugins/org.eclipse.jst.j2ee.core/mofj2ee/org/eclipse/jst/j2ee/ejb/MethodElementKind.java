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
 * @lastgen interface MethodElementKind {}
 */
public final class MethodElementKind extends AbstractEnumerator{
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
	public static final int REMOTE = 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int HOME = 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int LOCAL = 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int LOCAL_HOME = 4;
	/**
	 * The '<em><b>Service Endpoint</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SERVICE_ENDPOINT_LITERAL
	 * @model name="ServiceEndpoint"
	 * @generated
	 * @ordered
	 */
	public static final int SERVICE_ENDPOINT = 5;

	/**
	 * The '<em><b>Unspecified</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Unspecified</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNSPECIFIED
	 * @generated
	 * @ordered
	 */
	public static final MethodElementKind UNSPECIFIED_LITERAL = new MethodElementKind(UNSPECIFIED, "Unspecified"); //$NON-NLS-1$

	/**
	 * The '<em><b>Remote</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Remote</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REMOTE
	 * @generated
	 * @ordered
	 */
	public static final MethodElementKind REMOTE_LITERAL = new MethodElementKind(REMOTE, "Remote"); //$NON-NLS-1$

	/**
	 * The '<em><b>Home</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Home</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #HOME
	 * @generated
	 * @ordered
	 */
	public static final MethodElementKind HOME_LITERAL = new MethodElementKind(HOME, "Home"); //$NON-NLS-1$

	/**
	 * The '<em><b>Local</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Local</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LOCAL
	 * @generated
	 * @ordered
	 */
	public static final MethodElementKind LOCAL_LITERAL = new MethodElementKind(LOCAL, "Local"); //$NON-NLS-1$

	/**
	 * The '<em><b>Local Home</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Local Home</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LOCAL_HOME
	 * @generated
	 * @ordered
	 */
	public static final MethodElementKind LOCAL_HOME_LITERAL = new MethodElementKind(LOCAL_HOME, "LocalHome"); //$NON-NLS-1$

	/**
	 * The '<em><b>Service Endpoint</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Service Endpoint</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SERVICE_ENDPOINT
	 * @generated
	 * @ordered
	 */
	public static final MethodElementKind SERVICE_ENDPOINT_LITERAL = new MethodElementKind(SERVICE_ENDPOINT, "ServiceEndpoint"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Method Element Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final MethodElementKind[] VALUES_ARRAY =
		new MethodElementKind[] {
			UNSPECIFIED_LITERAL,
			REMOTE_LITERAL,
			HOME_LITERAL,
			LOCAL_LITERAL,
			LOCAL_HOME_LITERAL,
			SERVICE_ENDPOINT_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Method Element Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Method Element Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MethodElementKind get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			MethodElementKind result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Method Element Kind</b></em>' literal with the specified value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MethodElementKind get(int value) {
		switch (value) {
			case UNSPECIFIED: return UNSPECIFIED_LITERAL;
			case REMOTE: return REMOTE_LITERAL;
			case HOME: return HOME_LITERAL;
			case LOCAL: return LOCAL_LITERAL;
			case LOCAL_HOME: return LOCAL_HOME_LITERAL;
			case SERVICE_ENDPOINT: return SERVICE_ENDPOINT_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private MethodElementKind(int value, String name) {
		super(value, name);
	}

} //MethodElementKind


