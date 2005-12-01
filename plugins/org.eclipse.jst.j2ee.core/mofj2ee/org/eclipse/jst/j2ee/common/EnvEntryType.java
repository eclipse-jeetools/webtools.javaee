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
package org.eclipse.jst.j2ee.common;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;





/**
 * @lastgen interface EnvEntryType {}
 */
public final class EnvEntryType extends AbstractEnumerator{
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int STRING= 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int INTEGER= 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int BOOLEAN= 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int DOUBLE= 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int BYTE= 4;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int SHORT= 5;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int LONG= 6;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int FLOAT= 7;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int CHARACTER= 8;

	/**
	 * The '<em><b>String</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>String</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STRING
	 * @generated
	 * @ordered
	 */
	public static final EnvEntryType STRING_LITERAL = new EnvEntryType(STRING, "String"); //$NON-NLS-1$

	/**
	 * The '<em><b>Integer</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Integer</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #INTEGER
	 * @generated
	 * @ordered
	 */
	public static final EnvEntryType INTEGER_LITERAL = new EnvEntryType(INTEGER, "Integer"); //$NON-NLS-1$

	/**
	 * The '<em><b>Boolean</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Boolean</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BOOLEAN
	 * @generated
	 * @ordered
	 */
	public static final EnvEntryType BOOLEAN_LITERAL = new EnvEntryType(BOOLEAN, "Boolean"); //$NON-NLS-1$

	/**
	 * The '<em><b>Double</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Double</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DOUBLE
	 * @generated
	 * @ordered
	 */
	public static final EnvEntryType DOUBLE_LITERAL = new EnvEntryType(DOUBLE, "Double"); //$NON-NLS-1$

	/**
	 * The '<em><b>Byte</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Byte</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BYTE
	 * @generated
	 * @ordered
	 */
	public static final EnvEntryType BYTE_LITERAL = new EnvEntryType(BYTE, "Byte"); //$NON-NLS-1$

	/**
	 * The '<em><b>Short</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Short</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SHORT
	 * @generated
	 * @ordered
	 */
	public static final EnvEntryType SHORT_LITERAL = new EnvEntryType(SHORT, "Short"); //$NON-NLS-1$

	/**
	 * The '<em><b>Long</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Long</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LONG
	 * @generated
	 * @ordered
	 */
	public static final EnvEntryType LONG_LITERAL = new EnvEntryType(LONG, "Long"); //$NON-NLS-1$

	/**
	 * The '<em><b>Float</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Float</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FLOAT
	 * @generated
	 * @ordered
	 */
	public static final EnvEntryType FLOAT_LITERAL = new EnvEntryType(FLOAT, "Float"); //$NON-NLS-1$

	/**
	 * The '<em><b>Character</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Character</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CHARACTER
	 * @generated
	 * @ordered
	 */
	public static final EnvEntryType CHARACTER_LITERAL = new EnvEntryType(CHARACTER, "Character"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Env Entry Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final EnvEntryType[] VALUES_ARRAY =
		new EnvEntryType[] {
			STRING_LITERAL,
			INTEGER_LITERAL,
			BOOLEAN_LITERAL,
			DOUBLE_LITERAL,
			BYTE_LITERAL,
			SHORT_LITERAL,
			LONG_LITERAL,
			FLOAT_LITERAL,
			CHARACTER_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Env Entry Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Env Entry Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * @param name passes literal name
	 * @return literal instance
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EnvEntryType get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			EnvEntryType result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Env Entry Type</b></em>' literal with the specified value.
	 * <!-- begin-user-doc -->
	 * @param value passes literal value
	 * @return literal instance
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EnvEntryType get(int value) {
		switch (value) {
			case STRING: return STRING_LITERAL;
			case INTEGER: return INTEGER_LITERAL;
			case BOOLEAN: return BOOLEAN_LITERAL;
			case DOUBLE: return DOUBLE_LITERAL;
			case BYTE: return BYTE_LITERAL;
			case SHORT: return SHORT_LITERAL;
			case LONG: return LONG_LITERAL;
			case FLOAT: return FLOAT_LITERAL;
			case CHARACTER: return CHARACTER_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EnvEntryType(int value, String name) {
		super(value, name);
	}

} //EnvEntryType
