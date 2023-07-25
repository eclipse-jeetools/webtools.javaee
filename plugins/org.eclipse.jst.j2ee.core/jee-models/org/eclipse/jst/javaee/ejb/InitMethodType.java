/*******************************************************************************
 * Copyright (c) 2007, 2019 IBM Corporation and others.
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
 * A representation of the model object '<em><b>Init Method Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.InitMethodType#getCreateMethod <em>Create Method</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.InitMethodType#getBeanMethod <em>Bean Method</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.InitMethodType#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInitMethodType()
 * @extends JavaEEObject
 * @generated
 */
public interface InitMethodType extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Create Method</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Create Method</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Create Method</em>' containment reference.
	 * @see #setCreateMethod(NamedMethodType)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInitMethodType_CreateMethod()
	 * @generated
	 */
	NamedMethodType getCreateMethod();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.InitMethodType#getCreateMethod <em>Create Method</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Create Method</em>' containment reference.
	 * @see #getCreateMethod()
	 * @generated
	 */
	void setCreateMethod(NamedMethodType value);

	/**
	 * Returns the value of the '<em><b>Bean Method</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bean Method</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bean Method</em>' containment reference.
	 * @see #setBeanMethod(NamedMethodType)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInitMethodType_BeanMethod()
	 * @generated
	 */
	NamedMethodType getBeanMethod();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.InitMethodType#getBeanMethod <em>Bean Method</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bean Method</em>' containment reference.
	 * @see #getBeanMethod()
	 * @generated
	 */
	void setBeanMethod(NamedMethodType value);

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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInitMethodType_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.InitMethodType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // InitMethodType