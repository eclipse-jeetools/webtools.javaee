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
 * @lastgen interface DestinationType {}
 */
public final class DestinationType extends AbstractEnumerator{
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int QUEUE= 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int TOPIC= 1;

	/**
	 * The '<em><b>Queue</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Queue</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #QUEUE
	 * @generated
	 * @ordered
	 */
	public static final DestinationType QUEUE_LITERAL = new DestinationType(QUEUE, "Queue"); //$NON-NLS-1$

	/**
	 * The '<em><b>Topic</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Topic</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TOPIC
	 * @generated
	 * @ordered
	 */
	public static final DestinationType TOPIC_LITERAL = new DestinationType(TOPIC, "Topic"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Destination Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final DestinationType[] VALUES_ARRAY =
		new DestinationType[] {
			QUEUE_LITERAL,
			TOPIC_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Destination Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Destination Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DestinationType get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DestinationType result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Destination Type</b></em>' literal with the specified value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DestinationType get(int value) {
		switch (value) {
			case QUEUE: return QUEUE_LITERAL;
			case TOPIC: return TOPIC_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private DestinationType(int value, String name) {
		super(value, name);
	}

} //DestinationType
