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
package org.eclipse.jst.j2ee.taglib;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;





public final class JSPScriptingVariableScope extends AbstractEnumerator{
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int NESTED = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int AT_BEGIN = 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static final int AT_END = 2;
	/**
	 * The '<em><b>NESTED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NESTED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NESTED
	 * @generated
	 * @ordered
	 */
	public static final JSPScriptingVariableScope NESTED_LITERAL = new JSPScriptingVariableScope(NESTED, "NESTED");//$NON-NLS-1$

	/**
	 * The '<em><b>AT BEGIN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>AT BEGIN</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #AT_BEGIN
	 * @generated
	 * @ordered
	 */
	public static final JSPScriptingVariableScope AT_BEGIN_LITERAL = new JSPScriptingVariableScope(AT_BEGIN, "AT_BEGIN");//$NON-NLS-1$

	/**
	 * The '<em><b>AT END</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>AT END</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #AT_END
	 * @generated
	 * @ordered
	 */
	public static final JSPScriptingVariableScope AT_END_LITERAL = new JSPScriptingVariableScope(AT_END, "AT_END");//$NON-NLS-1$

	/**
	 * An array of all the '<em><b>JSP Scripting Variable Scope</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final JSPScriptingVariableScope[] VALUES_ARRAY =
		new JSPScriptingVariableScope[] {
			NESTED_LITERAL,
			AT_BEGIN_LITERAL,
			AT_END_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>JSP Scripting Variable Scope</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>JSP Scripting Variable Scope</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static JSPScriptingVariableScope get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			JSPScriptingVariableScope result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>JSP Scripting Variable Scope</b></em>' literal with the specified value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static JSPScriptingVariableScope get(int value) {
		switch (value) {
			case NESTED: return NESTED_LITERAL;
			case AT_BEGIN: return AT_BEGIN_LITERAL;
			case AT_END: return AT_END_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private JSPScriptingVariableScope(int value, String name) {
		super(value, name);
	}

} //JSPScriptingVariableScope









