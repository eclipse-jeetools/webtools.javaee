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
 * A representation of the literals of the enumeration '<em><b>CMR Field Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 * 
 *         The cmr-field-type element specifies the class of a
 *         collection-valued logical relationship field in the entity
 *         bean class. The value of an element using cmr-field-typeType
 *         must be either: java.util.Collection or java.util.Set.
 *         
 *         @since Java EE 5, EJB 3.0
 *       
 * <!-- end-model-doc -->
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getCMRFieldType()
 * @generated
 */
public enum CMRFieldType implements Enumerator
{
	/**
	 * The '<em><b>Java Util Collection</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA_UTIL_COLLECTION
	 * @generated
	 * @ordered
	 */
	JAVA_UTIL_COLLECTION_LITERAL(0, "javaUtilCollection", "java.util.Collection"), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>Java Util Set</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA_UTIL_SET
	 * @generated
	 * @ordered
	 */
	JAVA_UTIL_SET_LITERAL(1, "javaUtilSet", "java.util.Set"); //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>Java Util Collection</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Java Util Collection</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #JAVA_UTIL_COLLECTION_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int JAVA_UTIL_COLLECTION = 0;

	/**
	 * The '<em><b>Java Util Set</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Java Util Set</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #JAVA_UTIL_SET_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int JAVA_UTIL_SET = 1;

	/**
	 * An array of all the '<em><b>CMR Field Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final CMRFieldType[] VALUES_ARRAY =
		new CMRFieldType[] {
			JAVA_UTIL_COLLECTION_LITERAL,
			JAVA_UTIL_SET_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>CMR Field Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<CMRFieldType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>CMR Field Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CMRFieldType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			CMRFieldType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>CMR Field Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CMRFieldType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			CMRFieldType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>CMR Field Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CMRFieldType get(int value) {
		switch (value) {
			case JAVA_UTIL_COLLECTION: return JAVA_UTIL_COLLECTION_LITERAL;
			case JAVA_UTIL_SET: return JAVA_UTIL_SET_LITERAL;
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
	private CMRFieldType(int value, String name, String literal) {
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
