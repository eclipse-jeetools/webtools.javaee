/*******************************************************************************
 * Copyright (c) 2009, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.jca;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Config Property Type Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 * [
 *         The config-property-typeType contains the fully
 *         qualified Java type of a configuration property.
 *         
 *         The following are the legal values:
 *         java.lang.Boolean, java.lang.String, java.lang.Integer,
 *         java.lang.Double, java.lang.Byte, java.lang.Short,
 *         java.lang.Long, java.lang.Float, java.lang.Character
 *         
 *         Used in: config-property
 *         
 *         Example:
 *         <config-property-type>java.lang.String</config-property-type>
 *         
 * 
 * 		@since Java EE 6, Connector 1.6
 *       
 * <!-- end-model-doc -->
 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getConfigPropertyTypeType()
 * @generated
 */
public enum ConfigPropertyTypeType implements Enumerator {
	/**
	 * The '<em><b>Java Lang Boolean</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA_LANG_BOOLEAN_VALUE
	 * @generated
	 * @ordered
	 */
	JAVA_LANG_BOOLEAN(0, "javaLangBoolean", "java.lang.Boolean"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Java Lang String</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA_LANG_STRING_VALUE
	 * @generated
	 * @ordered
	 */
	JAVA_LANG_STRING(1, "javaLangString", "java.lang.String"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Java Lang Integer</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA_LANG_INTEGER_VALUE
	 * @generated
	 * @ordered
	 */
	JAVA_LANG_INTEGER(2, "javaLangInteger", "java.lang.Integer"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Java Lang Double</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA_LANG_DOUBLE_VALUE
	 * @generated
	 * @ordered
	 */
	JAVA_LANG_DOUBLE(3, "javaLangDouble", "java.lang.Double"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Java Lang Byte</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA_LANG_BYTE_VALUE
	 * @generated
	 * @ordered
	 */
	JAVA_LANG_BYTE(4, "javaLangByte", "java.lang.Byte"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Java Lang Short</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA_LANG_SHORT_VALUE
	 * @generated
	 * @ordered
	 */
	JAVA_LANG_SHORT(5, "javaLangShort", "java.lang.Short"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Java Lang Long</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA_LANG_LONG_VALUE
	 * @generated
	 * @ordered
	 */
	JAVA_LANG_LONG(6, "javaLangLong", "java.lang.Long"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Java Lang Float</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA_LANG_FLOAT_VALUE
	 * @generated
	 * @ordered
	 */
	JAVA_LANG_FLOAT(7, "javaLangFloat", "java.lang.Float"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Java Lang Character</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA_LANG_CHARACTER_VALUE
	 * @generated
	 * @ordered
	 */
	JAVA_LANG_CHARACTER(8, "javaLangCharacter", "java.lang.Character"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Java Lang Boolean</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Java Lang Boolean</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #JAVA_LANG_BOOLEAN
	 * @generated
	 * @ordered
	 */
	public static final int JAVA_LANG_BOOLEAN_VALUE = 0;

	/**
	 * The '<em><b>Java Lang String</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Java Lang String</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #JAVA_LANG_STRING
	 * @generated
	 * @ordered
	 */
	public static final int JAVA_LANG_STRING_VALUE = 1;

	/**
	 * The '<em><b>Java Lang Integer</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Java Lang Integer</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #JAVA_LANG_INTEGER
	 * @generated
	 * @ordered
	 */
	public static final int JAVA_LANG_INTEGER_VALUE = 2;

	/**
	 * The '<em><b>Java Lang Double</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Java Lang Double</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #JAVA_LANG_DOUBLE
	 * @generated
	 * @ordered
	 */
	public static final int JAVA_LANG_DOUBLE_VALUE = 3;

	/**
	 * The '<em><b>Java Lang Byte</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Java Lang Byte</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #JAVA_LANG_BYTE
	 * @generated
	 * @ordered
	 */
	public static final int JAVA_LANG_BYTE_VALUE = 4;

	/**
	 * The '<em><b>Java Lang Short</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Java Lang Short</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #JAVA_LANG_SHORT
	 * @generated
	 * @ordered
	 */
	public static final int JAVA_LANG_SHORT_VALUE = 5;

	/**
	 * The '<em><b>Java Lang Long</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Java Lang Long</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #JAVA_LANG_LONG
	 * @generated
	 * @ordered
	 */
	public static final int JAVA_LANG_LONG_VALUE = 6;

	/**
	 * The '<em><b>Java Lang Float</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Java Lang Float</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #JAVA_LANG_FLOAT
	 * @generated
	 * @ordered
	 */
	public static final int JAVA_LANG_FLOAT_VALUE = 7;

	/**
	 * The '<em><b>Java Lang Character</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Java Lang Character</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #JAVA_LANG_CHARACTER
	 * @generated
	 * @ordered
	 */
	public static final int JAVA_LANG_CHARACTER_VALUE = 8;

	/**
	 * An array of all the '<em><b>Config Property Type Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ConfigPropertyTypeType[] VALUES_ARRAY =
		new ConfigPropertyTypeType[] {
			JAVA_LANG_BOOLEAN,
			JAVA_LANG_STRING,
			JAVA_LANG_INTEGER,
			JAVA_LANG_DOUBLE,
			JAVA_LANG_BYTE,
			JAVA_LANG_SHORT,
			JAVA_LANG_LONG,
			JAVA_LANG_FLOAT,
			JAVA_LANG_CHARACTER,
		};

	/**
	 * A public read-only list of all the '<em><b>Config Property Type Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ConfigPropertyTypeType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Config Property Type Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ConfigPropertyTypeType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ConfigPropertyTypeType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Config Property Type Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ConfigPropertyTypeType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ConfigPropertyTypeType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Config Property Type Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ConfigPropertyTypeType get(int value) {
		switch (value) {
			case JAVA_LANG_BOOLEAN_VALUE: return JAVA_LANG_BOOLEAN;
			case JAVA_LANG_STRING_VALUE: return JAVA_LANG_STRING;
			case JAVA_LANG_INTEGER_VALUE: return JAVA_LANG_INTEGER;
			case JAVA_LANG_DOUBLE_VALUE: return JAVA_LANG_DOUBLE;
			case JAVA_LANG_BYTE_VALUE: return JAVA_LANG_BYTE;
			case JAVA_LANG_SHORT_VALUE: return JAVA_LANG_SHORT;
			case JAVA_LANG_LONG_VALUE: return JAVA_LANG_LONG;
			case JAVA_LANG_FLOAT_VALUE: return JAVA_LANG_FLOAT;
			case JAVA_LANG_CHARACTER_VALUE: return JAVA_LANG_CHARACTER;
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
	private ConfigPropertyTypeType(int value, String name, String literal) {
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
	
} //ConfigPropertyTypeType
