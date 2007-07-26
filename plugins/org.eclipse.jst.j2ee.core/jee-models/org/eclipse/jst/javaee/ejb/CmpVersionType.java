/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.ejb;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Cmp Version Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 * 
 * 	The cmp-versionType specifies the version of an entity bean
 * 	with container-managed persistence. It is used by
 * 	cmp-version elements.
 * 
 * 	The value must be one of the two following:
 * 
 * 	    1.x
 * 	    2.x
 * 
 *       
 * <!-- end-model-doc -->
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getCmpVersionType()
 * @generated
 */
public final class CmpVersionType extends AbstractEnumerator {
	/**
	 * The '<em><b>1X</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>1X</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #_1X_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int _1X = 0;

	/**
	 * The '<em><b>2X</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>2X</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #_2X_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int _2X = 1;

	/**
	 * The '<em><b>1X</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #_1X
	 * @generated
	 * @ordered
	 */
	public static final CmpVersionType _1X_LITERAL = new CmpVersionType(_1X, "_1X", "1.x"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>2X</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #_2X
	 * @generated
	 * @ordered
	 */
	public static final CmpVersionType _2X_LITERAL = new CmpVersionType(_2X, "_2X", "2.x"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * An array of all the '<em><b>Cmp Version Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final CmpVersionType[] VALUES_ARRAY =
		new CmpVersionType[] {
			_1X_LITERAL,
			_2X_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Cmp Version Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Cmp Version Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CmpVersionType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			CmpVersionType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Cmp Version Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CmpVersionType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			CmpVersionType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Cmp Version Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CmpVersionType get(int value) {
		switch (value) {
			case _1X: return _1X_LITERAL;
			case _2X: return _2X_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private CmpVersionType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //CmpVersionType
