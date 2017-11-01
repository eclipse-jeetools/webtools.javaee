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
package org.eclipse.jst.javaee.web;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>App Version Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 * 
 *         This type contains the recognized versions of
 *         web-application supported. It is used to designate the
 *         version of the web application.
 *         
 *         @since Java EE 5, Web 2.5
 *       
 * <!-- end-model-doc -->
 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebAppVersionType()
 * @generated
 */
public enum WebAppVersionType implements Enumerator
{
	/**
	 * The '<em><b>22</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #_22
	 * @generated
	 * @ordered
	 */
	_22_LITERAL(-1, "_22", "2.2"), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>23</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #_23
	 * @generated
	 * @ordered
	 */
	_23_LITERAL(-2, "_23", "2.3"), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>24</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #_24
	 * @generated
	 * @ordered
	 */
	_24_LITERAL(-1, "_24", "2.4"), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>25</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #_25
	 * @generated
	 * @ordered
	 */
	_25_LITERAL(0, "_25", "2.5"), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>30</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #_30
	 * @generated
	 * @ordered
	 */
	_30_LITERAL(1, "_30", "3.0"), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>31</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #_31_VALUE
	 * @generated
	 * @ordered
	 */
	_31(2, "_31", "3.1"), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>40</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #_40_VALUE
	 * @generated
	 * @ordered
	 */
	_40(2, "_40", "4.0"); //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>22</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>22</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #_22_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int _22 = -3;
	/**
	 * The '<em><b>23</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>23</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #_23_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int _23 = -2;
	/**
	 * The '<em><b>24</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>24</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #_24_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int _24 = -1;
	/**
	 * The '<em><b>25</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>25</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #_25_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int _25 = 0;

	/**
	 * The '<em><b>30</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>30</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #_30_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int _30 = 1;
	/**
	 * The '<em><b>31</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>31</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #_31
	 * @generated
	 * @ordered
	 */
	public static final int _31_VALUE = 2;
	/**
	 * The '<em><b>40</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>31</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #_40
	 * @generated
	 * @ordered
	 */
	public static final int _40_VALUE = 3;

	/**
	 * An array of all the '<em><b>App Version Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final WebAppVersionType[] VALUES_ARRAY =
		new WebAppVersionType[] {
			_22_LITERAL,
			_23_LITERAL,
			_24_LITERAL,
			_25_LITERAL,
			_30_LITERAL,
			_31,
			_40,
		};

	/**
	 * A public read-only list of all the '<em><b>App Version Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<WebAppVersionType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>App Version Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static WebAppVersionType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			WebAppVersionType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>App Version Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static WebAppVersionType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			WebAppVersionType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>App Version Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static WebAppVersionType get(int value) {
		switch (value) {
			case _22: return _25_LITERAL;
			case _23: return _25_LITERAL;
			case _24: return _25_LITERAL;
			case _25: return _25_LITERAL;
			case _30: return _30_LITERAL;
			case _31_VALUE: return _31;
			case _40_VALUE: return _40;
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
	private WebAppVersionType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
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
