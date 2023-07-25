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
package org.eclipse.jst.j2ee.common;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;





/**
 * @lastgen interface ResSharingScopeType {}
 * @since 1.0
 */
public final class ResSharingScopeType extends AbstractEnumerator{
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int SHAREABLE= 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int UNSHAREABLE= 1;

	/**
	 * The '<em><b>Shareable</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Shareable</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SHAREABLE
	 * @generated
	 * @ordered
	 */
	public static final ResSharingScopeType SHAREABLE_LITERAL = new ResSharingScopeType(SHAREABLE, "Shareable", "Shareable"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Unshareable</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Unshareable</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNSHAREABLE
	 * @generated
	 * @ordered
	 */
	public static final ResSharingScopeType UNSHAREABLE_LITERAL = new ResSharingScopeType(UNSHAREABLE, "Unshareable", "Unshareable"); //$NON-NLS-1$ //$NON-NLS-2$

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
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Res Sharing Scope Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * @param name passes literal name
	 * @return literal instance
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
	 * @param value passes literal value
	 * @return literal instance
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
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ResSharingScopeType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //ResSharingScopeType
