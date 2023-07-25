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
 * A representation of the model object '<em><b>Async Method Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The async-methodType element specifies that a session
 *         bean method has asynchronous invocation semantics.
 *         
 *         @since Java EE 6, EJB 3.1
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.AsyncMethodType#getMethodName <em>Method Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.AsyncMethodType#getMethodParams <em>Method Params</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.AsyncMethodType#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getAsyncMethodType()
 * @extends JavaEEObject
 * @generated
 */
public interface AsyncMethodType extends JavaEEObject {
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getAsyncMethodType_MethodName()
	 * @generated
	 */
	String getMethodName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.AsyncMethodType#getMethodName <em>Method Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method Name</em>' attribute.
	 * @see #getMethodName()
	 * @generated
	 */
	void setMethodName(String value);

	/**
	 * Returns the value of the '<em><b>Method Params</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method Params</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method Params</em>' containment reference.
	 * @see #setMethodParams(MethodParams)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getAsyncMethodType_MethodParams()
	 * @generated
	 */
	MethodParams getMethodParams();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.AsyncMethodType#getMethodParams <em>Method Params</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method Params</em>' containment reference.
	 * @see #getMethodParams()
	 * @generated
	 */
	void setMethodParams(MethodParams value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getAsyncMethodType_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.AsyncMethodType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // AsyncMethodType
