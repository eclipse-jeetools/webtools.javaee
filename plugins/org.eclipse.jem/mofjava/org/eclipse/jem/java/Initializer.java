/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.java;

/*
 *  $RCSfile: Initializer.java,v $
 *  $Revision: 1.1.4.1 $  $Date: 2004/06/24 18:17:06 $ 
 */
import org.eclipse.emf.ecore.EObject;
/**
 * @generated
 */
public interface Initializer extends EObject{

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the IsStatic attribute
	 */
	Boolean getIsStatic();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the IsStatic attribute
	 */
	void setIsStatic(Boolean value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The JavaClass reference
	 */
	JavaClass getJavaClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.java.Initializer#getJavaClass <em>Java Class</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Java Class</em>' container reference.
	 * @see #getJavaClass()
	 * @generated
	 */
	void setJavaClass(JavaClass value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The Source reference
	 */
	Block getSource();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.java.Initializer#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(Block value);

}





