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
 * A representation of the literals of the enumeration '<em><b>Res Auth Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 * 
 *         The res-authType specifies whether the Deployment Component
 *         code signs on programmatically to the resource manager, or
 *         whether the Container will sign on to the resource manager
 *         on behalf of the Deployment Component. In the latter case,
 *         the Container uses information that is supplied by the
 *         Deployer.
 *         
 *         The value must be one of the two following:
 *         
 *         Application
 *         Container
 *         
 *         @since Java EE 5
 *       
 * <!-- end-model-doc -->
 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getResAuthType()
 * @generated
 */
public enum ResAuthType implements Enumerator
{
	/**
	 * The '<em><b>Application</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #APPLICATION
	 * @generated
	 * @ordered
	 */
	APPLICATION_LITERAL(0, "Application", "Application"), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>Container</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONTAINER
	 * @generated
	 * @ordered
	 */
	CONTAINER_LITERAL(1, "Container", "Container"); //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>Application</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Application</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #APPLICATION_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int APPLICATION = 0;

	/**
	 * The '<em><b>Container</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Container</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONTAINER_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int CONTAINER = 1;

	/**
	 * An array of all the '<em><b>Res Auth Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ResAuthType[] VALUES_ARRAY =
		new ResAuthType[] {
			APPLICATION_LITERAL,
			CONTAINER_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Res Auth Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ResAuthType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Res Auth Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ResAuthType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ResAuthType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Res Auth Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ResAuthType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ResAuthType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Res Auth Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ResAuthType get(int value) {
		switch (value) {
			case APPLICATION: return APPLICATION_LITERAL;
			case CONTAINER: return CONTAINER_LITERAL;
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
	private ResAuthType(int value, String name, String literal) {
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
