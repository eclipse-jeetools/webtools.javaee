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
package org.eclipse.jst.j2ee.ejb;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;





/**
 * @lastgen interface ReturnTypeMapping {}
 */
public final class ReturnTypeMapping extends AbstractEnumerator{
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int LOCAL= 0;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int REMOTE= 1;
	/**
	 * The '<em><b>Local</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Local</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LOCAL
	 * @generated
	 * @ordered
	 */
	public static final ReturnTypeMapping LOCAL_LITERAL = new ReturnTypeMapping(LOCAL, "Local"); //$NON-NLS-1$

	/**
	 * The '<em><b>Remote</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Remote</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REMOTE
	 * @generated
	 * @ordered
	 */
	public static final ReturnTypeMapping REMOTE_LITERAL = new ReturnTypeMapping(REMOTE, "Remote"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Return Type Mapping</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ReturnTypeMapping[] VALUES_ARRAY =
		new ReturnTypeMapping[] {
			LOCAL_LITERAL,
			REMOTE_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Return Type Mapping</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Return Type Mapping</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ReturnTypeMapping get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ReturnTypeMapping result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Return Type Mapping</b></em>' literal with the specified value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ReturnTypeMapping get(int value) {
		switch (value) {
			case LOCAL: return LOCAL_LITERAL;
			case REMOTE: return REMOTE_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ReturnTypeMapping(int value, String name) {
		super(value, name);
	}

} //ReturnTypeMapping
