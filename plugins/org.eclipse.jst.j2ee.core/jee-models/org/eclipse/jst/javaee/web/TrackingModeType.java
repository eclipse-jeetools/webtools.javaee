/*******************************************************************************
 * Copyright (c) 2009, 2023 IBM Corporation and others.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/**
 * <copyright>
 * </copyright>
 *
 * $Id: TrackingModeType.java,v 1.1 2009/10/15 18:52:03 canderson Exp $
 */
package org.eclipse.jst.javaee.web;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Tracking Mode Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 * 
 *         The tracking modes for sessions created by this web
 *         application
 *         
 *         Used in: session-config
 *         
 *         @since Java EE 6, Web 3.0
 *       
 * <!-- end-model-doc -->
 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getTrackingModeType()
 * @generated
 */
public enum TrackingModeType implements Enumerator {
	/**
	 * The '<em><b>COOKIE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #COOKIE_VALUE
	 * @generated
	 * @ordered
	 */
	COOKIE(0, "COOKIE", "COOKIE"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>URL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #URL_VALUE
	 * @generated
	 * @ordered
	 */
	URL(1, "URL", "URL"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>SSL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SSL_VALUE
	 * @generated
	 * @ordered
	 */
	SSL(2, "SSL", "SSL"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>COOKIE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>COOKIE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #COOKIE
	 * @generated
	 * @ordered
	 */
	public static final int COOKIE_VALUE = 0;

	/**
	 * The '<em><b>URL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>URL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #URL
	 * @generated
	 * @ordered
	 */
	public static final int URL_VALUE = 1;

	/**
	 * The '<em><b>SSL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SSL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SSL
	 * @generated
	 * @ordered
	 */
	public static final int SSL_VALUE = 2;

	/**
	 * An array of all the '<em><b>Tracking Mode Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final TrackingModeType[] VALUES_ARRAY =
		new TrackingModeType[] {
			COOKIE,
			URL,
			SSL,
		};

	/**
	 * A public read-only list of all the '<em><b>Tracking Mode Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<TrackingModeType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Tracking Mode Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TrackingModeType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TrackingModeType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Tracking Mode Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TrackingModeType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TrackingModeType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Tracking Mode Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TrackingModeType get(int value) {
		switch (value) {
			case COOKIE_VALUE: return COOKIE;
			case URL_VALUE: return URL;
			case SSL_VALUE: return SSL;
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
	private TrackingModeType(int value, String name, String literal) {
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
	
} //TrackingModeType
