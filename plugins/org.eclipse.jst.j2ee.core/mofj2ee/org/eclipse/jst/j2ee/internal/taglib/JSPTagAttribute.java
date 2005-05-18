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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jem.java.JavaClass;

/**
 * The attribute tag defines an attribute for the nesting tag
 */
public interface JSPTagAttribute extends EObject{
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Name attribute
	 * Defines the canonical name of a tag or attribute being defined (required).
	 */
	String getName();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Name attribute
	 */
	void setName(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Required attribute
	 * Defines if the nesting attribute is required or optional.
	 */
	boolean isRequired();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Required attribute
	 */
	void setRequired(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.j2ee.internal.taglib.JSPTagAttribute#isRequired <em>Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetRequired()
	 * @see #isRequired()
	 * @see #setRequired(boolean)
	 * @generated
	 */
	void unsetRequired();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.j2ee.internal.taglib.JSPTagAttribute#isRequired <em>Required</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Required</em>' attribute is set.
	 * @see #unsetRequired()
	 * @see #isRequired()
	 * @see #setRequired(boolean)
	 * @generated
	 */
	boolean isSetRequired();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the RtExprValue attribute
	 * Defines if the nesting attribute can have scriptlet expressions asa value, i.e
	 * the value of the attribute may be dynamically calculatedat request time, as
	 * opposed to a static value determined at translationtime. (optional)
	 * Default = false
	 */
	boolean isRtExprValue();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the RtExprValue attribute
	 */
	void setRtExprValue(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.j2ee.internal.taglib.JSPTagAttribute#isRtExprValue <em>Rt Expr Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetRtExprValue()
	 * @see #isRtExprValue()
	 * @see #setRtExprValue(boolean)
	 * @generated
	 */
	void unsetRtExprValue();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.j2ee.internal.taglib.JSPTagAttribute#isRtExprValue <em>Rt Expr Value</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Rt Expr Value</em>' attribute is set.
	 * @see #unsetRtExprValue()
	 * @see #isRtExprValue()
	 * @see #setRtExprValue(boolean)
	 * @generated
	 */
	boolean isSetRtExprValue();

	/**
	 * Returns the value of the '<em><b>Fragment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fragment</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @since J2EE1.4
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Fragment</em>' attribute.
	 * @see #setFragment(boolean)
	 * @see org.eclipse.jst.j2ee.internal.taglib.TaglibPackage#getJSPTagAttribute_Fragment()
	 * @model 
	 * @generated
	 */
	boolean isFragment();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.taglib.JSPTagAttribute#isFragment <em>Fragment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fragment</em>' attribute.
	 * @see #isFragment()
	 * @generated
	 */
	void setFragment(boolean value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The Type reference
	 * The value of the type element describes the Java type of the attributes value.
	 * For static values (those determined at translation time) the type is always
	 * java.lang.String.
	 */
	JavaClass getType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the Type reference
	 */
	void setType(JavaClass value);

	/**
	 * Returns the value of the '<em><b>Descriptions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.internal.common.Description}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Descriptions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @since J2EE1.4
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Descriptions</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.internal.taglib.TaglibPackage#getJSPTagAttribute_Descriptions()
	 * @model type="org.eclipse.jst.j2ee.internal.common.Description" containment="true"
	 * @generated
	 */
	EList getDescriptions();

}














