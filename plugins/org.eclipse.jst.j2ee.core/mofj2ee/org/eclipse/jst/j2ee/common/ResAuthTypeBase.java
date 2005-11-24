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
package org.eclipse.jst.j2ee.common;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Res Auth Type Base</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * CONTAINER=0
 * Container=1
 * Application=2
 * SERVLET=3
 * 
 * <!-- end-model-doc -->
 * @see org.eclipse.jst.j2ee.common.CommonPackage#getResAuthTypeBase()
 * @model
 * @generated
 */
public final class ResAuthTypeBase extends AbstractEnumerator {
	/**
	 * The '<em><b>Container</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONTAINER_LITERAL
	 * @model name="Container"
	 * @generated
	 * @ordered
	 */
	public static final int CONTAINER = 0;

	/**
	 * The '<em><b>Application</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #APPLICATION_LITERAL
	 * @model name="Application"
	 * @generated
	 * @ordered
	 */
	public static final int APPLICATION = 1;

	/**
	 * The '<em><b>SERVLET</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SERVLET_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SERVLET = 2;

	/**
	 * The '<em><b>Container</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Container</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONTAINER
	 * @generated
	 * @ordered
	 */
	public static final ResAuthTypeBase CONTAINER_LITERAL = new ResAuthTypeBase(CONTAINER, "Container"); //$NON-NLS-1$

	/**
	 * The '<em><b>Application</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Application</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #APPLICATION
	 * @generated
	 * @ordered
	 */
	public static final ResAuthTypeBase APPLICATION_LITERAL = new ResAuthTypeBase(APPLICATION, "Application"); //$NON-NLS-1$

	/**
	 * The '<em><b>SERVLET</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SERVLET</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SERVLET
	 * @generated
	 * @ordered
	 */
	public static final ResAuthTypeBase SERVLET_LITERAL = new ResAuthTypeBase(SERVLET, "SERVLET"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Res Auth Type Base</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ResAuthTypeBase[] VALUES_ARRAY =
		new ResAuthTypeBase[] {
			CONTAINER_LITERAL,
			APPLICATION_LITERAL,
			SERVLET_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Res Auth Type Base</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Res Auth Type Base</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * @param name passes literal name
	 * @return literal instance
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ResAuthTypeBase get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ResAuthTypeBase result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Res Auth Type Base</b></em>' literal with the specified value.
	 * <!-- begin-user-doc -->
	 * @param value passes literal value
	 * @return literal instance
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ResAuthTypeBase get(int value) {
		switch (value) {
			case CONTAINER: return CONTAINER_LITERAL;
			case APPLICATION: return APPLICATION_LITERAL;
			case SERVLET: return SERVLET_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ResAuthTypeBase(int value, String name) {
		super(value, name);
	}

} //ResAuthTypeBase
