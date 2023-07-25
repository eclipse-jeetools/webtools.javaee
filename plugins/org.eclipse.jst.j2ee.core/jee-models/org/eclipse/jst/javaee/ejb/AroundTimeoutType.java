/*******************************************************************************
 * Copyright (c) 2009, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.ejb;

import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Around Timeout Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The around-timeout type specifies a method on a
 *         class to be called during the around-timeout portion of
 *         a timer timeout callback.  Note that each class may have 
 *         only one around-timeout method and that the method may not 
 *         be overloaded.
 *         
 *         If the class element is missing then
 *         the class defining the callback is assumed to be the
 *         interceptor class or component class in scope at the
 *         location in the descriptor in which the around-timeout
 *         definition appears.
 *         
 *         @since Java EE 6, EJB 3.1
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.AroundTimeoutType#getClass_ <em>Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.AroundTimeoutType#getMethodName <em>Method Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getAroundTimeoutType()
 * @extends JavaEEObject
 * @generated
 */
public interface AroundTimeoutType extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class</em>' attribute.
	 * @see #setClass(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getAroundTimeoutType_Class()
	 * @generated
	 */
	String getClass_();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.AroundTimeoutType#getClass_ <em>Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class</em>' attribute.
	 * @see #getClass_()
	 * @generated
	 */
	void setClass(String value);

	/**
	 * Returns the value of the '<em><b>Method Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method Name</em>' attribute.
	 * @see #setMethodName(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getAroundTimeoutType_MethodName()
	 * @generated
	 */
	String getMethodName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.AroundTimeoutType#getMethodName <em>Method Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method Name</em>' attribute.
	 * @see #getMethodName()
	 * @generated
	 */
	void setMethodName(String value);

} // AroundTimeoutType
