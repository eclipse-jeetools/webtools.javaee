/*******************************************************************************
 * Copyright (c) 2001, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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
 * @lastgen interface AcknowledgeMode {}
 * @since 1.0
 */
public final class AcknowledgeMode extends AbstractEnumerator{
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int AUTO_ACKNOWLEDGE= 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int DUPS_OK_ACKNOWLEDGE= 1;

	/**
	 * The '<em><b>Auto Acknowledge</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Auto Acknowledge</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #AUTO_ACKNOWLEDGE
	 * @ordered
	 */
	public static final AcknowledgeMode AUTO_ACKNOWLEDGE_LITERAL = new AcknowledgeMode(AUTO_ACKNOWLEDGE, "Auto-acknowledge"); //$NON-NLS-1$

	/**
	 * The '<em><b>Dups Ok Acknowledge</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Dups Ok Acknowledge</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DUPS_OK_ACKNOWLEDGE
	 *TODO: generate correctly
	 * @ordered
	 */
	public static final AcknowledgeMode DUPS_OK_ACKNOWLEDGE_LITERAL = new AcknowledgeMode(DUPS_OK_ACKNOWLEDGE, "Dups-ok-acknowledge"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Acknowledge Mode</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final AcknowledgeMode[] VALUES_ARRAY =
		new AcknowledgeMode[] {
			AUTO_ACKNOWLEDGE_LITERAL,
			DUPS_OK_ACKNOWLEDGE_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Acknowledge Mode</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Acknowledge Mode</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * @param name passes literal name
	 * @return literal instance
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AcknowledgeMode get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AcknowledgeMode result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Acknowledge Mode</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AcknowledgeMode getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AcknowledgeMode result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Acknowledge Mode</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AcknowledgeMode get(int value) {
		switch (value) {
			case AUTO_ACKNOWLEDGE: return AUTO_ACKNOWLEDGE_LITERAL;
			case DUPS_OK_ACKNOWLEDGE: return DUPS_OK_ACKNOWLEDGE_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private AcknowledgeMode(int value, String name, String literal) {
		super(value, name, literal);
	}

	private AcknowledgeMode(int value, String name) {
		this(value, name, name);
	}

} //AcknowledgeMode
