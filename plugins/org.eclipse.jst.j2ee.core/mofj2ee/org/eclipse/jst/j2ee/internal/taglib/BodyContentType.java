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
package org.eclipse.jst.j2ee.internal.taglib;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;





public final class BodyContentType extends AbstractEnumerator{
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int TAGDEPENDENT = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int JSP = 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int EMPTY = 2;
	/**
	 * The '<em><b>Scriptless</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SCRIPTLESS_LITERAL
	 * @model name="scriptless"
	 * @generated
	 * @ordered
	 */
	public static final int SCRIPTLESS = 3;

	/**
	 * The '<em><b>Tagdependent</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Tagdependent</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TAGDEPENDENT
	 * @generated
	 * @ordered
	 */
	public static final BodyContentType TAGDEPENDENT_LITERAL = new BodyContentType(TAGDEPENDENT, "tagdependent");//$NON-NLS-1$

	/**
	 * The '<em><b>JSP</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>JSP</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #JSP
	 * @generated
	 * @ordered
	 */
	public static final BodyContentType JSP_LITERAL = new BodyContentType(JSP, "JSP");//$NON-NLS-1$

	/**
	 * The '<em><b>Empty</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Empty</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #EMPTY
	 * @generated
	 * @ordered
	 */
	public static final BodyContentType EMPTY_LITERAL = new BodyContentType(EMPTY, "empty");//$NON-NLS-1$

	/**
	 * The '<em><b>Scriptless</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Scriptless</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SCRIPTLESS
	 * @generated
	 * @ordered
	 */
	public static final BodyContentType SCRIPTLESS_LITERAL = new BodyContentType(SCRIPTLESS, "scriptless");//$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Body Content Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final BodyContentType[] VALUES_ARRAY =
		new BodyContentType[] {
			TAGDEPENDENT_LITERAL,
			JSP_LITERAL,
			EMPTY_LITERAL,
			SCRIPTLESS_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Body Content Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Body Content Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BodyContentType get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			BodyContentType result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Body Content Type</b></em>' literal with the specified value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BodyContentType get(int value) {
		switch (value) {
			case TAGDEPENDENT: return TAGDEPENDENT_LITERAL;
			case JSP: return JSP_LITERAL;
			case EMPTY: return EMPTY_LITERAL;
			case SCRIPTLESS: return SCRIPTLESS_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private BodyContentType(int value, String name) {
		super(value, name);
	}

} //BodyContentType









