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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.internal.taglib.TaglibPackage;

/**
 * @generated
 */
public interface JSPVariable extends EObject{
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the NameGiven attribute
	 * The name-given element provides the name for the scripting variable.
	 * 
	 * One of name-given or name-from-attribute is required.
	 */
	String getNameGiven();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the NameGiven attribute
	 */
	void setNameGiven(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the NameFromAttribute attribute
	 * The name of an attribute whose (translation-time) value will give the name of
	 * the variable.
	 */
	String getNameFromAttribute();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the NameFromAttribute attribute
	 */
	void setNameFromAttribute(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Declare attribute
	 * The value of the declare element indicates whether the scripting variable is to 
	 * be defined or not. See TagExtraInfo for details.
	 * This element is optional and is the default is true.

	 */
	boolean isDeclare();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Declare attribute
	 */
	void setDeclare(boolean value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Scope attribute
	 */
	JSPScriptingVariableScope getScope();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.taglib.JSPVariable#getScope <em>Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scope</em>' attribute.
	 * @see org.eclipse.jst.j2ee.internal.taglib.JSPScriptingVariableScope
	 * @see #getScope()
	 * @generated
	 */
	void setScope(JSPScriptingVariableScope value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The VariableClass reference
	 */
	JavaClass getVariableClass();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the VariableClass reference
	 */
	void setVariableClass(JavaClass value);

	/**
	 * Returns the value of the '<em><b>Descriptions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.internal.common.Description}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Descriptions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Descriptions</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.internal.taglib.TaglibPackage#getJSPVariable_Descriptions()
	 * @model type="org.eclipse.jst.j2ee.internal.common.Description" containment="true"
	 * @generated
	 */
	EList getDescriptions();

}














