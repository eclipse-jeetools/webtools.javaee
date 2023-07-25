/*******************************************************************************
 * Copyright (c) 2007, 2023 IBM Corporation and others.
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

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Message Destination Usage Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 * 
 *         The message-destination-usageType specifies the use of the
 *         message destination indicated by the reference.  The value
 *         indicates whether messages are consumed from the message
 *         destination, produced for the destination, or both.  The
 *         Assembler makes use of this information in linking producers
 *         of a destination with its consumers.
 *         
 *         The value of the message-destination-usage element must be
 *         one of the following:
 *         Consumes
 *         Produces
 *         ConsumesProduces
 *         
 *         @since Java EE 5
 *       
 * <!-- end-model-doc -->
 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getMessageDestinationUsageType()
 * @generated
 */
public enum MessageDestinationUsageType implements Enumerator
{
	/**
	 * The '<em><b>Consumes</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONSUMES
	 * @generated
	 * @ordered
	 */
	CONSUMES_LITERAL(0, "Consumes", "Consumes"), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>Produces</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PRODUCES
	 * @generated
	 * @ordered
	 */
	PRODUCES_LITERAL(1, "Produces", "Produces"), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>Consumes Produces</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONSUMES_PRODUCES
	 * @generated
	 * @ordered
	 */
	CONSUMES_PRODUCES_LITERAL(2, "ConsumesProduces", "ConsumesProduces"); //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>Consumes</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Consumes</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONSUMES_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int CONSUMES = 0;

	/**
	 * The '<em><b>Produces</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Produces</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PRODUCES_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int PRODUCES = 1;

	/**
	 * The '<em><b>Consumes Produces</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Consumes Produces</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONSUMES_PRODUCES_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int CONSUMES_PRODUCES = 2;

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
	public static final List<MessageDestinationUsageType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Message Destination Usage Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MessageDestinationUsageType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			MessageDestinationUsageType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Message Destination Usage Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MessageDestinationUsageType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			MessageDestinationUsageType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Message Destination Usage Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
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
	private MessageDestinationUsageType(int value, String name, String literal) {
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
}
