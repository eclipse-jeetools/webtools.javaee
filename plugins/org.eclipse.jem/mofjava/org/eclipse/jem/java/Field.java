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
 *  $RCSfile: Field.java,v $
 *  $Revision: 1.2.4.1 $  $Date: 2004/06/24 18:17:06 $ 
 */
import org.eclipse.emf.ecore.ETypedElement;

/**
 * @generated
 */
public interface Field extends ETypedElement {

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the IsFinal attribute
	 */
	boolean isFinal();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.java.Field#isFinal <em>Final</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Final</em>' attribute.
	 * @see #isFinal()
	 * @generated
	 */
	void setFinal(boolean value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the IsStatic attribute
	 */
	boolean isStatic();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.java.Field#isStatic <em>Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Static</em>' attribute.
	 * @see #isStatic()
	 * @generated
	 */
	void setStatic(boolean value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the JavaVisibility attribute
	 */
	JavaVisibilityKind getJavaVisibility();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.java.Field#getJavaVisibility <em>Java Visibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Java Visibility</em>' attribute.
	 * @see org.eclipse.jem.java.JavaVisibilityKind
	 * @see #getJavaVisibility()
	 * @generated
	 */
	void setJavaVisibility(JavaVisibilityKind value);

	/**
	 * Returns the value of the '<em><b>Transient</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transient</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transient</em>' attribute.
	 * @see #setTransient(boolean)
	 * @see org.eclipse.jem.java.JavaRefPackage#getField_Transient()
	 * @model 
	 * @generated
	 */
	boolean isTransient();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.java.Field#isTransient <em>Transient</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transient</em>' attribute.
	 * @see #isTransient()
	 * @generated
	 */
	void setTransient(boolean value);

	/**
	 * Returns the value of the '<em><b>Volatile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Volatile</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Volatile</em>' attribute.
	 * @see #setVolatile(boolean)
	 * @see org.eclipse.jem.java.JavaRefPackage#getField_Volatile()
	 * @model 
	 * @generated
	 */
	boolean isVolatile();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.java.Field#isVolatile <em>Volatile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Volatile</em>' attribute.
	 * @see #isVolatile()
	 * @generated
	 */
	void setVolatile(boolean value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The JavaClass reference
	 */
	JavaClass getJavaClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.java.Field#getJavaClass <em>Java Class</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Java Class</em>' container reference.
	 * @see #getJavaClass()
	 * @generated
	 */
	void setJavaClass(JavaClass value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The Initializer reference
	 */
	Block getInitializer();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.java.Field#getInitializer <em>Initializer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initializer</em>' containment reference.
	 * @see #getInitializer()
	 * @generated
	 */
	void setInitializer(Block value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Get the class that this field is within.
	 */
	JavaClass getContainingJavaClass();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Is this field an array type.
	 */
	boolean isArray();

}





