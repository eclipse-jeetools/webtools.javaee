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
package org.eclipse.jst.javaee.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Env Entry Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 * 	
 * 
 * 	  This type contains the fully-qualified Java type of the
 * 	  environment entry value that is expected by the
 * 	  application's code.
 * 
 * 	  The following are the legal values of env-entry-type-valuesType:
 * 
 * 	      java.lang.Boolean
 * 	      java.lang.Byte
 * 	      java.lang.Character
 * 	      java.lang.String
 * 	      java.lang.Short
 * 	      java.lang.Integer
 * 	      java.lang.Long
 * 	      java.lang.Float
 * 	      java.lang.Double
 * 
 * 	  Example:
 * 
 * 	  &lt;env-entry-type&gt;java.lang.Boolean&lt;/env-entry-type&gt;
 * 
 * 	  
 *       
 * <!-- end-model-doc -->
 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getEnvEntryType()
 * @generated
 */
public final class EnvEntryType extends AbstractEnumerator {
	/**
	 * The '<em><b>Java Lang Boolean</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Java Lang Boolean</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #JAVA_LANG_BOOLEAN_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int JAVA_LANG_BOOLEAN = 0;

	/**
	 * The '<em><b>Java Lang Byte</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Java Lang Byte</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #JAVA_LANG_BYTE_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int JAVA_LANG_BYTE = 1;

	/**
	 * The '<em><b>Java Lang Character</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Java Lang Character</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #JAVA_LANG_CHARACTER_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int JAVA_LANG_CHARACTER = 2;

	/**
	 * The '<em><b>Java Lang String</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Java Lang String</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #JAVA_LANG_STRING_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int JAVA_LANG_STRING = 3;

	/**
	 * The '<em><b>Java Lang Short</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Java Lang Short</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #JAVA_LANG_SHORT_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int JAVA_LANG_SHORT = 4;

	/**
	 * The '<em><b>Java Lang Integer</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Java Lang Integer</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #JAVA_LANG_INTEGER_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int JAVA_LANG_INTEGER = 5;

	/**
	 * The '<em><b>Java Lang Long</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Java Lang Long</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #JAVA_LANG_LONG_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int JAVA_LANG_LONG = 6;

	/**
	 * The '<em><b>Java Lang Float</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Java Lang Float</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #JAVA_LANG_FLOAT_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int JAVA_LANG_FLOAT = 7;

	/**
	 * The '<em><b>Java Lang Double</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Java Lang Double</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #JAVA_LANG_DOUBLE_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int JAVA_LANG_DOUBLE = 8;

	/**
	 * The '<em><b>Java Lang Boolean</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA_LANG_BOOLEAN
	 * @generated
	 * @ordered
	 */
	public static final EnvEntryType JAVA_LANG_BOOLEAN_LITERAL = new EnvEntryType(JAVA_LANG_BOOLEAN, "javaLangBoolean", "java.lang.Boolean"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Java Lang Byte</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA_LANG_BYTE
	 * @generated
	 * @ordered
	 */
	public static final EnvEntryType JAVA_LANG_BYTE_LITERAL = new EnvEntryType(JAVA_LANG_BYTE, "javaLangByte", "java.lang.Byte"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Java Lang Character</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA_LANG_CHARACTER
	 * @generated
	 * @ordered
	 */
	public static final EnvEntryType JAVA_LANG_CHARACTER_LITERAL = new EnvEntryType(JAVA_LANG_CHARACTER, "javaLangCharacter", "java.lang.Character"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Java Lang String</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA_LANG_STRING
	 * @generated
	 * @ordered
	 */
	public static final EnvEntryType JAVA_LANG_STRING_LITERAL = new EnvEntryType(JAVA_LANG_STRING, "javaLangString", "java.lang.String"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Java Lang Short</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA_LANG_SHORT
	 * @generated
	 * @ordered
	 */
	public static final EnvEntryType JAVA_LANG_SHORT_LITERAL = new EnvEntryType(JAVA_LANG_SHORT, "javaLangShort", "java.lang.Short"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Java Lang Integer</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA_LANG_INTEGER
	 * @generated
	 * @ordered
	 */
	public static final EnvEntryType JAVA_LANG_INTEGER_LITERAL = new EnvEntryType(JAVA_LANG_INTEGER, "javaLangInteger", "java.lang.Integer"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Java Lang Long</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA_LANG_LONG
	 * @generated
	 * @ordered
	 */
	public static final EnvEntryType JAVA_LANG_LONG_LITERAL = new EnvEntryType(JAVA_LANG_LONG, "javaLangLong", "java.lang.Long"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Java Lang Float</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA_LANG_FLOAT
	 * @generated
	 * @ordered
	 */
	public static final EnvEntryType JAVA_LANG_FLOAT_LITERAL = new EnvEntryType(JAVA_LANG_FLOAT, "javaLangFloat", "java.lang.Float"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Java Lang Double</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA_LANG_DOUBLE
	 * @generated
	 * @ordered
	 */
	public static final EnvEntryType JAVA_LANG_DOUBLE_LITERAL = new EnvEntryType(JAVA_LANG_DOUBLE, "javaLangDouble", "java.lang.Double"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * An array of all the '<em><b>Env Entry Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final EnvEntryType[] VALUES_ARRAY =
		new EnvEntryType[] {
			JAVA_LANG_BOOLEAN_LITERAL,
			JAVA_LANG_BYTE_LITERAL,
			JAVA_LANG_CHARACTER_LITERAL,
			JAVA_LANG_STRING_LITERAL,
			JAVA_LANG_SHORT_LITERAL,
			JAVA_LANG_INTEGER_LITERAL,
			JAVA_LANG_LONG_LITERAL,
			JAVA_LANG_FLOAT_LITERAL,
			JAVA_LANG_DOUBLE_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Env Entry Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Env Entry Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EnvEntryType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			EnvEntryType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Env Entry Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EnvEntryType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			EnvEntryType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Env Entry Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EnvEntryType get(int value) {
		switch (value) {
			case JAVA_LANG_BOOLEAN: return JAVA_LANG_BOOLEAN_LITERAL;
			case JAVA_LANG_BYTE: return JAVA_LANG_BYTE_LITERAL;
			case JAVA_LANG_CHARACTER: return JAVA_LANG_CHARACTER_LITERAL;
			case JAVA_LANG_STRING: return JAVA_LANG_STRING_LITERAL;
			case JAVA_LANG_SHORT: return JAVA_LANG_SHORT_LITERAL;
			case JAVA_LANG_INTEGER: return JAVA_LANG_INTEGER_LITERAL;
			case JAVA_LANG_LONG: return JAVA_LANG_LONG_LITERAL;
			case JAVA_LANG_FLOAT: return JAVA_LANG_FLOAT_LITERAL;
			case JAVA_LANG_DOUBLE: return JAVA_LANG_DOUBLE_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EnvEntryType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //EnvEntryType
