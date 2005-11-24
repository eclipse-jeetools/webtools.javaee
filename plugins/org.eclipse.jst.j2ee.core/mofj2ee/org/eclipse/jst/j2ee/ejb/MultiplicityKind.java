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
 * @lastgen interface MultiplicityKind {}
 */
public final class MultiplicityKind extends AbstractEnumerator{
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int ONE= 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int MANY= 1;

	/**
	 * The '<em><b>One</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>One</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ONE
	 * @generated
	 * @ordered
	 */
	public static final MultiplicityKind ONE_LITERAL = new MultiplicityKind(ONE, "One"); //$NON-NLS-1$

	/**
	 * The '<em><b>Many</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Many</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MANY
	 * @generated
	 * @ordered
	 */
	public static final MultiplicityKind MANY_LITERAL = new MultiplicityKind(MANY, "Many"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Multiplicity Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final MultiplicityKind[] VALUES_ARRAY =
		new MultiplicityKind[] {
			ONE_LITERAL,
			MANY_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Multiplicity Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Multiplicity Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * @param name passes literal name
	 * @return literal instance
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MultiplicityKind get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			MultiplicityKind result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Multiplicity Kind</b></em>' literal with the specified value.
	 * <!-- begin-user-doc -->
	 * @param value passes literal value
	 * @return literal instance
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MultiplicityKind get(int value) {
		switch (value) {
			case ONE: return ONE_LITERAL;
			case MANY: return MANY_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private MultiplicityKind(int value, String name) {
		super(value, name);
	}

} //MultiplicityKind
