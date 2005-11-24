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
 * A representation of the literals of the enumeration '<em><b>Dispatcher Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.j2ee.webapplication.WebapplicationPackage#getDispatcherType()
 * @model
 * @generated
 */
public final class DispatcherType extends AbstractEnumerator
{
	/**
	 * The '<em><b>FORWARD</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FORWARD_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int FORWARD = 0;

	/**
	 * The '<em><b>INCLUDE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INCLUDE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int INCLUDE = 1;

	/**
	 * The '<em><b>REQUEST</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REQUEST_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int REQUEST = 2;

	/**
	 * The '<em><b>ERROR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ERROR_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ERROR = 3;

	/**
	 * The '<em><b>FORWARD</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FORWARD</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FORWARD
	 * @generated
	 * @ordered
	 */
	public static final DispatcherType FORWARD_LITERAL = new DispatcherType(FORWARD, "FORWARD");//$NON-NLS-1$

	/**
	 * The '<em><b>INCLUDE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>INCLUDE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #INCLUDE
	 * @generated
	 * @ordered
	 */
	public static final DispatcherType INCLUDE_LITERAL = new DispatcherType(INCLUDE, "INCLUDE");//$NON-NLS-1$

	/**
	 * The '<em><b>REQUEST</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>REQUEST</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REQUEST
	 * @generated
	 * @ordered
	 */
	public static final DispatcherType REQUEST_LITERAL = new DispatcherType(REQUEST, "REQUEST");//$NON-NLS-1$

	/**
	 * The '<em><b>ERROR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ERROR</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ERROR
	 * @generated
	 * @ordered
	 */
	public static final DispatcherType ERROR_LITERAL = new DispatcherType(ERROR, "ERROR");//$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Dispatcher Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final DispatcherType[] VALUES_ARRAY =
		new DispatcherType[] {
			FORWARD_LITERAL,
			INCLUDE_LITERAL,
			REQUEST_LITERAL,
			ERROR_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Dispatcher Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Dispatcher Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * @param name passes literal name
	 * @return literal instance
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DispatcherType get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DispatcherType result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Dispatcher Type</b></em>' literal with the specified value.
	 * <!-- begin-user-doc -->
	 * @param value passes literal value
	 * @return literal instance
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DispatcherType get(int value) {
		switch (value) {
			case FORWARD: return FORWARD_LITERAL;
			case INCLUDE: return INCLUDE_LITERAL;
			case REQUEST: return REQUEST_LITERAL;
			case ERROR: return ERROR_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private DispatcherType(int value, String name) {
		super(value, name);
	}

} //DispatcherType
