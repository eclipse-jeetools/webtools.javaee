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
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.common.DescriptionGroup;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Function</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * @since J2EE1.4
 * The function element is used to provide information on each
 * function in the tag library that is to be exposed to the EL.
 * 
 * The function element may have several subelements defining:
 * 
 * description         Optional tag-specific information
 * 
 * display-name        A short name that is intended to be
 *                     displayed by tools
 * 
 * icon                Optional icon element that can be used
 *                     by tools
 * 
 * name                A unique name for this function
 * 
 * function-class      Provides the name of the Java class that
 *                     implements the function
 * 
 * function-signature  Provides the signature, as in the Java
 *                     Language Specification, of the Java
 *                     method that is to be used to implement
 *                     the function.
 * 
 * example             Optional informal description of an
 *                     example of a use of this function
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.taglib.Function#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.taglib.Function#getSignature <em>Signature</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.taglib.Function#getExample <em>Example</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.taglib.Function#getFunctionClass <em>Function Class</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.taglib.Function#getFunctionExtensions <em>Function Extensions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.taglib.TaglibPackage#getFunction()
 * @model 
 * @generated
 */
public interface Function extends DescriptionGroup{
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.jst.j2ee.taglib.TaglibPackage#getFunction_Name()
	 * @model 
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.taglib.Function#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Signature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Signature</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Provides the signature, as in the Java Language
	 *         Specification, of the static Java method that is
	 *         to be used to implement the function.
	 * 
	 *         Example:
	 * 
	 *         java.lang.String nickName( java.lang.String, int )
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Signature</em>' attribute.
	 * @see #setSignature(String)
	 * @see org.eclipse.jst.j2ee.taglib.TaglibPackage#getFunction_Signature()
	 * @model 
	 * @generated
	 */
	String getSignature();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.taglib.Function#getSignature <em>Signature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Signature</em>' attribute.
	 * @see #getSignature()
	 * @generated
	 */
	void setSignature(String value);

	/**
	 * Returns the value of the '<em><b>Example</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Example</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The example element contains an informal description
	 *         of an example of the use of this function.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Example</em>' attribute.
	 * @see #setExample(String)
	 * @see org.eclipse.jst.j2ee.taglib.TaglibPackage#getFunction_Example()
	 * @model 
	 * @generated
	 */
	String getExample();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.taglib.Function#getExample <em>Example</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Example</em>' attribute.
	 * @see #getExample()
	 * @generated
	 */
	void setExample(String value);

	/**
	 * Returns the value of the '<em><b>Function Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Function Class</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Provides the fully-qualified class name of the Java
	 *         class containing the static method that implements
	 *         the function.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Function Class</em>' reference.
	 * @see #setFunctionClass(JavaClass)
	 * @see org.eclipse.jst.j2ee.taglib.TaglibPackage#getFunction_FunctionClass()
	 * @model required="true"
	 * @generated
	 */
	JavaClass getFunctionClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.taglib.Function#getFunctionClass <em>Function Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Function Class</em>' reference.
	 * @see #getFunctionClass()
	 * @generated
	 */
	void setFunctionClass(JavaClass value);

	/**
	 * Returns the value of the '<em><b>Function Extensions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.taglib.TldExtension}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Function Extensions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Function Extensions</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.taglib.TaglibPackage#getFunction_FunctionExtensions()
	 * @model type="org.eclipse.jst.j2ee.taglib.TldExtension" containment="true"
	 * @generated
	 */
	EList getFunctionExtensions();

} // Function
