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

import org.eclipse.emf.ecore.EFactory;
/**
 * @generated
 */
public interface TaglibFactory extends EFactory{
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TaglibFactory eINSTANCE = new org.eclipse.jst.j2ee.internal.taglib.impl.TaglibFactoryImpl();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return TagLib value
	 */
	TagLib createTagLib();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return Validator value
	 */
	Validator createValidator();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return JSPTag value
	 */
	JSPTag createJSPTag();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return JSPTagAttribute value
	 */
	JSPTagAttribute createJSPTagAttribute();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return JSPVariable value
	 */
	JSPVariable createJSPVariable();

	/**
	 * Returns a new object of class '<em>Function</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Function</em>'.
	 * @generated
	 */
	Function createFunction();

	/**
	 * Returns a new object of class '<em>Tag File</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tag File</em>'.
	 * @generated
	 */
	TagFile createTagFile();

	/**
	 * Returns a new object of class '<em>Tld Extension</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tld Extension</em>'.
	 * @generated
	 */
	TldExtension createTldExtension();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	TaglibPackage getTaglibPackage();

}







