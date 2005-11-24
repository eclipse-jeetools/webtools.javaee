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
 * A representation of the literals of the enumeration '<em><b>Message Destination Usage Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.j2ee.common.CommonPackage#getMessageDestinationUsageType()
 * @model
 * @generated
 */
public final class MessageDestinationUsageType extends AbstractEnumerator {
	/**
	 * The '<em><b>Consumes</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONSUMES_LITERAL
	 * @model name="Consumes"
	 * @generated
	 * @ordered
	 */
	public static final int CONSUMES = 0;

	/**
	 * The '<em><b>Produces</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PRODUCES_LITERAL
	 * @model name="Produces"
	 * @generated
	 * @ordered
	 */
	public static final int PRODUCES = 1;

	/**
	 * The '<em><b>Consumes Produces</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONSUMES_PRODUCES_LITERAL
	 * @model name="ConsumesProduces"
	 * @generated
	 * @ordered
	 */
	public static final int CONSUMES_PRODUCES = 2;

	/**
	 * The '<em><b>Consumes</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Consumes</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONSUMES
	 * @generated
	 * @ordered
	 */
	public static final MessageDestinationUsageType CONSUMES_LITERAL = new MessageDestinationUsageType(CONSUMES, "Consumes"); //$NON-NLS-1$

	/**
	 * The '<em><b>Produces</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Produces</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PRODUCES
	 * @generated
	 * @ordered
	 */
	public static final MessageDestinationUsageType PRODUCES_LITERAL = new MessageDestinationUsageType(PRODUCES, "Produces"); //$NON-NLS-1$

	/**
	 * The '<em><b>Consumes Produces</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Consumes Produces</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONSUMES_PRODUCES
	 * @generated
	 * @ordered
	 */
	public static final MessageDestinationUsageType CONSUMES_PRODUCES_LITERAL = new MessageDestinationUsageType(CONSUMES_PRODUCES, "ConsumesProduces"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Message Destination Usage Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final MessageDestinationUsageType[] VALUES_ARRAY =
		new MessageDestinationUsageType[] {
			CONSUMES_LITERAL,
			PRODUCES_LITERAL,
			CONSUMES_PRODUCES_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Message Destination Usage Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Message Destination Usage Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * @param name passes literal name
	 * @return literal instance
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MessageDestinationUsageType get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			MessageDestinationUsageType result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Message Destination Usage Type</b></em>' literal with the specified value.
	 * <!-- begin-user-doc -->
	 * @param value passes literal value
	 * @return literal instance
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MessageDestinationUsageType get(int value) {
		switch (value) {
			case CONSUMES: return CONSUMES_LITERAL;
			case PRODUCES: return PRODUCES_LITERAL;
			case CONSUMES_PRODUCES: return CONSUMES_PRODUCES_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private MessageDestinationUsageType(int value, String name) {
		super(value, name);
	}

} //MessageDestinationUsageType
