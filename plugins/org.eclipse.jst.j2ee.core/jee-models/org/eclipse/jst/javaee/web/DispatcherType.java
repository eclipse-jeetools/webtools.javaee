/*******************************************************************************
 * Copyright (c) 2007, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.web;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Dispatcher Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 * 
 *         The dispatcher has five legal values: FORWARD, REQUEST,
 *         INCLUDE, ASYNC, and ERROR.
 *         
 *         A value of FORWARD means the Filter will be applied under
 *         RequestDispatcher.forward() calls.
 *         A value of REQUEST means the Filter will be applied under
 *         ordinary client calls to the path or servlet.
 *         A value of INCLUDE means the Filter will be applied under
 *         RequestDispatcher.include() calls.
 *         A value of ASYNC means the Filter will be applied under
 *         calls dispatched from an AsyncContext.
 *         A value of ERROR means the Filter will be applied under the
 *         error page mechanism.
 *         
 *         The absence of any dispatcher elements in a filter-mapping
 *         indicates a default of applying filters only under ordinary
 *         client calls to the path or servlet.
 *         
 *         @since Java EE 5, Web 2.5
 *       
 * <!-- end-model-doc -->
 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getDispatcherType()
 * @generated
 */
public enum DispatcherType implements Enumerator
{
	/**
	 * The '<em><b>FORWARD</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FORWARD
	 * @generated
	 * @ordered
	 */
	FORWARD_LITERAL(0, "FORWARD", "FORWARD"), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>INCLUDE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INCLUDE
	 * @generated
	 * @ordered
	 */
	INCLUDE_LITERAL(1, "INCLUDE", "INCLUDE"), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>REQUEST</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REQUEST
	 * @generated
	 * @ordered
	 */
	REQUEST_LITERAL(2, "REQUEST", "REQUEST"), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>ASYNC</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ASYNC
	 * @generated
	 * @ordered
	 */
	ASYNC_LITERAL(3, "ASYNC", "ASYNC"), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>ERROR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ERROR
	 * @generated
	 * @ordered
	 */
	ERROR_LITERAL(4, "ERROR", "ERROR"); //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>FORWARD</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FORWARD</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FORWARD_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int FORWARD = 0;

	/**
	 * The '<em><b>INCLUDE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>INCLUDE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #INCLUDE_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int INCLUDE = 1;

	/**
	 * The '<em><b>REQUEST</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>REQUEST</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REQUEST_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int REQUEST = 2;

	/**
	 * The '<em><b>ASYNC</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *               @since Java EE 6, Web 3.0
	 *             
	 * <!-- end-model-doc -->
	 * @see #ASYNC_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int ASYNC = 3;

	/**
	 * The '<em><b>ERROR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ERROR</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ERROR_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int ERROR = 4;

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
			ASYNC_LITERAL,
			ERROR_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Dispatcher Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<DispatcherType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Dispatcher Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DispatcherType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DispatcherType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Dispatcher Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DispatcherType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DispatcherType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Dispatcher Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DispatcherType get(int value) {
		switch (value) {
			case FORWARD: return FORWARD_LITERAL;
			case INCLUDE: return INCLUDE_LITERAL;
			case REQUEST: return REQUEST_LITERAL;
			case ASYNC: return ASYNC_LITERAL;
			case ERROR: return ERROR_LITERAL;
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
	private DispatcherType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
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
