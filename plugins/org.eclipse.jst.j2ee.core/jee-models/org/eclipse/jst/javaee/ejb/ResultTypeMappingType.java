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
package org.eclipse.jst.javaee.ejb;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Result Type Mapping Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 * 
 *         The result-type-mappingType is used in the query element to
 *         specify whether an abstract schema type returned by a query
 *         for a select method is to be mapped to an EJBLocalObject or
 *         EJBObject type.
 *         
 *         The value must be one of the following:
 *         
 *         Local
 *         Remote
 *         
 *         @since Java EE 5, EJB 3.0
 *       
 * <!-- end-model-doc -->
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getResultTypeMappingType()
 * @generated
 */
public enum ResultTypeMappingType implements Enumerator
{
	/**
	 * The '<em><b>Local</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOCAL
	 * @generated
	 * @ordered
	 */
	LOCAL_LITERAL(0, "Local", "Local"), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>Remote</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REMOTE
	 * @generated
	 * @ordered
	 */
	REMOTE_LITERAL(1, "Remote", "Remote"); //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>Local</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Local</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LOCAL_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int LOCAL = 0;

	/**
	 * The '<em><b>Remote</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Remote</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REMOTE_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int REMOTE = 1;

	/**
	 * An array of all the '<em><b>Result Type Mapping Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ResultTypeMappingType[] VALUES_ARRAY =
		new ResultTypeMappingType[] {
			LOCAL_LITERAL,
			REMOTE_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Result Type Mapping Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ResultTypeMappingType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Result Type Mapping Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ResultTypeMappingType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ResultTypeMappingType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Result Type Mapping Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ResultTypeMappingType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ResultTypeMappingType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Result Type Mapping Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ResultTypeMappingType get(int value) {
		switch (value) {
			case LOCAL: return LOCAL_LITERAL;
			case REMOTE: return REMOTE_LITERAL;
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
	private ResultTypeMappingType(int value, String name, String literal) {
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
