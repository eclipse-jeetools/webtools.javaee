/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.beaninfo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Implicit Item</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * This enum is an internal enum. It is used by BeanInfo for cache maintenance.
 * <p>
 * This enum is not meant to be used by clients.
 * <!-- end-model-doc -->
 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getImplicitItem()
 * @model
 * @generated
 */
public final class ImplicitItem extends AbstractEnumerator {
	/**
	 * The '<em><b>NOT IMPLICIT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NOT_IMPLICIT_LITERAL
	 * @model 
	 * @generated
	 * @ordered
	 */
	public static final int NOT_IMPLICIT = 0;

	/**
	 * The '<em><b>IMPLICIT DECORATOR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #IMPLICIT_DECORATOR_LITERAL
	 * @model 
	 * @generated
	 * @ordered
	 */
	public static final int IMPLICIT_DECORATOR = 1;

	/**
	 * The '<em><b>IMPLICIT DECORATOR AND FEATURE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #IMPLICIT_DECORATOR_AND_FEATURE_LITERAL
	 * @model 
	 * @generated
	 * @ordered
	 */
	public static final int IMPLICIT_DECORATOR_AND_FEATURE = 2;

	/**
	 * The '<em><b>NOT IMPLICIT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Means this decorator is not implicit. That is it was created by customer.
	 * <!-- end-model-doc -->
	 * @see #NOT_IMPLICIT
	 * @generated
	 * @ordered
	 */
	public static final ImplicitItem NOT_IMPLICIT_LITERAL = new ImplicitItem(NOT_IMPLICIT, "NOT_IMPLICIT");

	/**
	 * The '<em><b>IMPLICIT DECORATOR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This means that the decorator is implicit. That is it was not created by the customer.
	 * <!-- end-model-doc -->
	 * @see #IMPLICIT_DECORATOR
	 * @generated
	 * @ordered
	 */
	public static final ImplicitItem IMPLICIT_DECORATOR_LITERAL = new ImplicitItem(IMPLICIT_DECORATOR, "IMPLICIT_DECORATOR");

	/**
	 * The '<em><b>IMPLICIT DECORATOR AND FEATURE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This means the decorator and the feature where implicit. That is they were not created by the customer.
	 * <!-- end-model-doc -->
	 * @see #IMPLICIT_DECORATOR_AND_FEATURE
	 * @generated
	 * @ordered
	 */
	public static final ImplicitItem IMPLICIT_DECORATOR_AND_FEATURE_LITERAL = new ImplicitItem(IMPLICIT_DECORATOR_AND_FEATURE, "IMPLICIT_DECORATOR_AND_FEATURE");

	/**
	 * An array of all the '<em><b>Implicit Item</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ImplicitItem[] VALUES_ARRAY =
		new ImplicitItem[] {
			NOT_IMPLICIT_LITERAL,
			IMPLICIT_DECORATOR_LITERAL,
			IMPLICIT_DECORATOR_AND_FEATURE_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Implicit Item</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Implicit Item</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ImplicitItem get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ImplicitItem result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Implicit Item</b></em>' literal with the specified value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ImplicitItem get(int value) {
		switch (value) {
			case NOT_IMPLICIT: return NOT_IMPLICIT_LITERAL;
			case IMPLICIT_DECORATOR: return IMPLICIT_DECORATOR_LITERAL;
			case IMPLICIT_DECORATOR_AND_FEATURE: return IMPLICIT_DECORATOR_AND_FEATURE_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ImplicitItem(int value, String name) {
		super(value, name);
	}

} //ImplicitItem
