/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
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
 *@since 1.0
 */
public final class ResAuthServletType extends AbstractEnumerator{
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int CONTAINER = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int SERVLET = 1;
	/**
	 * The '<em><b>CONTAINER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CONTAINER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONTAINER
	 * @generated
	 * @ordered
	 */
	public static final ResAuthServletType CONTAINER_LITERAL = new ResAuthServletType(CONTAINER, "CONTAINER");//$NON-NLS-1$

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
	public static final ResAuthServletType SERVLET_LITERAL = new ResAuthServletType(SERVLET, "SERVLET");//$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Res Auth Servlet Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ResAuthServletType[] VALUES_ARRAY =
		new ResAuthServletType[] {
			CONTAINER_LITERAL,
			SERVLET_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Res Auth Servlet Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Res Auth Servlet Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * @param name passes literal name
	 * @return literal instance
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ResAuthServletType get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ResAuthServletType result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Res Auth Servlet Type</b></em>' literal with the specified value.
	 * <!-- begin-user-doc -->
	 * @param value passes literal value
	 * @return literal instance
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ResAuthServletType get(int value) {
		switch (value) {
			case CONTAINER: return CONTAINER_LITERAL;
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
	private ResAuthServletType(int value, String name) {
		super(value, name);
	}

} //ResAuthServletType









