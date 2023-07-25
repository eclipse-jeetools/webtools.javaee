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
package org.eclipse.jst.javaee.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Res Sharing Scope Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 * 
 *         The res-sharing-scope type specifies whether connections
 *         obtained through the given resource manager connection
 *         factory reference can be shared. The value, if specified,
 *         must be one of the two following:
 *         
 *         Shareable
 *         Unshareable
 *         
 *         The default value is Shareable.
 *         
 *         @since Java EE 5
 *       
 * <!-- end-model-doc -->
 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getResSharingScopeType()
 * @generated
 */
public enum ResSharingScopeType implements Enumerator
{
	/**
	 * The '<em><b>Shareable</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SHAREABLE
	 * @generated
	 * @ordered
	 */
	SHAREABLE_LITERAL(0, "Shareable", "Shareable"), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>Unshareable</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNSHAREABLE
	 * @generated
	 * @ordered
	 */
	UNSHAREABLE_LITERAL(1, "Unshareable", "Unshareable"); //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>Shareable</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Shareable</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SHAREABLE_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int SHAREABLE = 0;

	/**
	 * The '<em><b>Unshareable</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Unshareable</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNSHAREABLE_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int UNSHAREABLE = 1;

	/**
	 * An array of all the '<em><b>Res Sharing Scope Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ResSharingScopeType[] VALUES_ARRAY =
		new ResSharingScopeType[] {
			SHAREABLE_LITERAL,
			UNSHAREABLE_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Res Sharing Scope Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ResSharingScopeType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Res Sharing Scope Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ResSharingScopeType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ResSharingScopeType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Res Sharing Scope Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ResSharingScopeType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ResSharingScopeType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Res Sharing Scope Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ResSharingScopeType get(int value) {
		switch (value) {
			case SHAREABLE: return SHAREABLE_LITERAL;
			case UNSHAREABLE: return UNSHAREABLE_LITERAL;
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
	private ResSharingScopeType(int value, String name, String literal) {
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
