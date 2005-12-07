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
package org.eclipse.jst.j2ee.ejb;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;





/**
 * @lastgen interface TransactionAttributeType {}
 * @since 1.0
 */
public final class TransactionAttributeType extends AbstractEnumerator{
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int NOT_SUPPORTED= 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int SUPPORTS= 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int REQUIRED= 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int REQUIRES_NEW= 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int MANDATORY= 4;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int NEVER= 5;

	/**
	 * The '<em><b>Not Supported</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Not Supported</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NOT_SUPPORTED
	 * @generated
	 * @ordered
	 */
	public static final TransactionAttributeType NOT_SUPPORTED_LITERAL = new TransactionAttributeType(NOT_SUPPORTED, "NotSupported"); //$NON-NLS-1$

	/**
	 * The '<em><b>Supports</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Supports</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SUPPORTS
	 * @generated
	 * @ordered
	 */
	public static final TransactionAttributeType SUPPORTS_LITERAL = new TransactionAttributeType(SUPPORTS, "Supports"); //$NON-NLS-1$

	/**
	 * The '<em><b>Required</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Required</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REQUIRED
	 * @generated
	 * @ordered
	 */
	public static final TransactionAttributeType REQUIRED_LITERAL = new TransactionAttributeType(REQUIRED, "Required"); //$NON-NLS-1$

	/**
	 * The '<em><b>Requires New</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Requires New</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REQUIRES_NEW
	 * @generated
	 * @ordered
	 */
	public static final TransactionAttributeType REQUIRES_NEW_LITERAL = new TransactionAttributeType(REQUIRES_NEW, "RequiresNew"); //$NON-NLS-1$

	/**
	 * The '<em><b>Mandatory</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Mandatory</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MANDATORY
	 * @generated
	 * @ordered
	 */
	public static final TransactionAttributeType MANDATORY_LITERAL = new TransactionAttributeType(MANDATORY, "Mandatory"); //$NON-NLS-1$

	/**
	 * The '<em><b>Never</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Never</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NEVER
	 * @generated
	 * @ordered
	 */
	public static final TransactionAttributeType NEVER_LITERAL = new TransactionAttributeType(NEVER, "Never"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Transaction Attribute Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final TransactionAttributeType[] VALUES_ARRAY =
		new TransactionAttributeType[] {
			NOT_SUPPORTED_LITERAL,
			SUPPORTS_LITERAL,
			REQUIRED_LITERAL,
			REQUIRES_NEW_LITERAL,
			MANDATORY_LITERAL,
			NEVER_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Transaction Attribute Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Transaction Attribute Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * @param name passes literal name
	 * @return literal instance
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TransactionAttributeType get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TransactionAttributeType result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Transaction Attribute Type</b></em>' literal with the specified value.
	 * <!-- begin-user-doc -->
	 * @param value passes literal value
	 * @return literal instance
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TransactionAttributeType get(int value) {
		switch (value) {
			case NOT_SUPPORTED: return NOT_SUPPORTED_LITERAL;
			case SUPPORTS: return SUPPORTS_LITERAL;
			case REQUIRED: return REQUIRED_LITERAL;
			case REQUIRES_NEW: return REQUIRES_NEW_LITERAL;
			case MANDATORY: return MANDATORY_LITERAL;
			case NEVER: return NEVER_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private TransactionAttributeType(int value, String name) {
		super(value, name);
	}

} //TransactionAttributeType
