/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.ejb;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Transaction Attribute Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 * 
 *         The trans-attributeType specifies how the container must
 *         manage the transaction boundaries when delegating a method 
 *         invocation to an enterprise bean's business method. 
 *         
 *         The value must be one of the following: 
 *         
 *         NotSupported 
 *         Supports 
 *         Required  
 *         RequiresNew 
 *         Mandatory 
 *         Never 
 *         
 *         @since Java EE 5, EJB 3.0
 *       
 * <!-- end-model-doc -->
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getTransactionAttributeType()
 * @generated
 */
public enum TransactionAttributeType implements Enumerator
{
	/**
	 * The '<em><b>Not Supported</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NOT_SUPPORTED
	 * @generated
	 * @ordered
	 */
	NOT_SUPPORTED_LITERAL(0, "NotSupported", "NotSupported"), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>Supports</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SUPPORTS
	 * @generated
	 * @ordered
	 */
	SUPPORTS_LITERAL(1, "Supports", "Supports"), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>Required</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REQUIRED
	 * @generated
	 * @ordered
	 */
	REQUIRED_LITERAL(2, "Required", "Required"), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>Requires New</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REQUIRES_NEW
	 * @generated
	 * @ordered
	 */
	REQUIRES_NEW_LITERAL(3, "RequiresNew", "RequiresNew"), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>Mandatory</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MANDATORY
	 * @generated
	 * @ordered
	 */
	MANDATORY_LITERAL(4, "Mandatory", "Mandatory"), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>Never</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NEVER
	 * @generated
	 * @ordered
	 */
	NEVER_LITERAL(5, "Never", "Never"); //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>Not Supported</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Not Supported</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NOT_SUPPORTED_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int NOT_SUPPORTED = 0;

	/**
	 * The '<em><b>Supports</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Supports</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SUPPORTS_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int SUPPORTS = 1;

	/**
	 * The '<em><b>Required</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Required</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REQUIRED_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int REQUIRED = 2;

	/**
	 * The '<em><b>Requires New</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Requires New</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REQUIRES_NEW_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int REQUIRES_NEW = 3;

	/**
	 * The '<em><b>Mandatory</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Mandatory</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MANDATORY_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int MANDATORY = 4;

	/**
	 * The '<em><b>Never</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Never</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NEVER_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int NEVER = 5;

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
	public static final List<TransactionAttributeType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Transaction Attribute Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TransactionAttributeType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TransactionAttributeType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Transaction Attribute Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TransactionAttributeType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TransactionAttributeType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Transaction Attribute Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
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
	private TransactionAttributeType(int value, String name, String literal) {
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
