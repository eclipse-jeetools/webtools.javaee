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
package org.eclipse.jst.j2ee.webapplication;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>HTTP Method Type Enum</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.j2ee.internal.webapplication.WebapplicationPackage#getHTTPMethodTypeEnum()
 * @model
 * @generated
 */
public final class HTTPMethodTypeEnum extends AbstractEnumerator {
	/**
	 * The '<em><b>GET</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GET_LITERAL
	 * @model 
	 * @generated
	 * @ordered
	 */
	public static final int GET = 0;

	/**
	 * The '<em><b>POST</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #POST_LITERAL
	 * @model 
	 * @generated
	 * @ordered
	 */
	public static final int POST = 1;

	/**
	 * The '<em><b>PUT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PUT_LITERAL
	 * @model 
	 * @generated
	 * @ordered
	 */
	public static final int PUT = 2;

	/**
	 * The '<em><b>DELETE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DELETE_LITERAL
	 * @model 
	 * @generated
	 * @ordered
	 */
	public static final int DELETE = 3;

	/**
	 * The '<em><b>HEAD</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HEAD_LITERAL
	 * @model 
	 * @generated
	 * @ordered
	 */
	public static final int HEAD = 4;

	/**
	 * The '<em><b>OPTIONS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OPTIONS_LITERAL
	 * @model 
	 * @generated
	 * @ordered
	 */
	public static final int OPTIONS = 5;

	/**
	 * The '<em><b>TRACE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TRACE_LITERAL
	 * @model 
	 * @generated
	 * @ordered
	 */
	public static final int TRACE = 6;

	/**
	 * The '<em><b>GET</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>GET</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The http-method contains an HTTP method (GET | POST |...)
	 * <!-- end-model-doc -->
	 * @see #GET
	 * @generated
	 * @ordered
	 */
	public static final HTTPMethodTypeEnum GET_LITERAL = new HTTPMethodTypeEnum(GET, "GET");//$NON-NLS-1$

	/**
	 * The '<em><b>POST</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>POST</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #POST
	 * @generated
	 * @ordered
	 */
	public static final HTTPMethodTypeEnum POST_LITERAL = new HTTPMethodTypeEnum(POST, "POST");//$NON-NLS-1$

	/**
	 * The '<em><b>PUT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PUT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PUT
	 * @generated
	 * @ordered
	 */
	public static final HTTPMethodTypeEnum PUT_LITERAL = new HTTPMethodTypeEnum(PUT, "PUT");//$NON-NLS-1$

	/**
	 * The '<em><b>DELETE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DELETE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DELETE
	 * @generated
	 * @ordered
	 */
	public static final HTTPMethodTypeEnum DELETE_LITERAL = new HTTPMethodTypeEnum(DELETE, "DELETE");//$NON-NLS-1$

	/**
	 * The '<em><b>HEAD</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>HEAD</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #HEAD
	 * @generated
	 * @ordered
	 */
	public static final HTTPMethodTypeEnum HEAD_LITERAL = new HTTPMethodTypeEnum(HEAD, "HEAD");//$NON-NLS-1$

	/**
	 * The '<em><b>OPTIONS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>OPTIONS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #OPTIONS
	 * @generated
	 * @ordered
	 */
	public static final HTTPMethodTypeEnum OPTIONS_LITERAL = new HTTPMethodTypeEnum(OPTIONS, "OPTIONS");//$NON-NLS-1$

	/**
	 * The '<em><b>TRACE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TRACE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TRACE
	 * @generated
	 * @ordered
	 */
	public static final HTTPMethodTypeEnum TRACE_LITERAL = new HTTPMethodTypeEnum(TRACE, "TRACE");//$NON-NLS-1$

	/**
	 * An array of all the '<em><b>HTTP Method Type Enum</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final HTTPMethodTypeEnum[] VALUES_ARRAY =
		new HTTPMethodTypeEnum[] {
			GET_LITERAL,
			POST_LITERAL,
			PUT_LITERAL,
			DELETE_LITERAL,
			HEAD_LITERAL,
			OPTIONS_LITERAL,
			TRACE_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>HTTP Method Type Enum</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>HTTP Method Type Enum</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static HTTPMethodTypeEnum get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			HTTPMethodTypeEnum result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>HTTP Method Type Enum</b></em>' literal with the specified value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static HTTPMethodTypeEnum get(int value) {
		switch (value) {
			case GET: return GET_LITERAL;
			case POST: return POST_LITERAL;
			case PUT: return PUT_LITERAL;
			case DELETE: return DELETE_LITERAL;
			case HEAD: return HEAD_LITERAL;
			case OPTIONS: return OPTIONS_LITERAL;
			case TRACE: return TRACE_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private HTTPMethodTypeEnum(int value, String name) {
		super(value, name);
	}

} //HTTPMethodTypeEnum
