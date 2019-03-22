/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.ejb;

import java.util.List;

import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Container Transaction Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The container-transactionType specifies how the container
 *         must manage transaction scopes for the enterprise bean's
 *         method invocations. It defines an optional description, a
 *         list of method elements, and a transaction attribute. The
 *         transaction attribute is to be applied to all the specified
 *         methods.
 *         
 *         @since Java EE 5, EJB 3.0
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.ContainerTransactionType#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.ContainerTransactionType#getMethods <em>Methods</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.ContainerTransactionType#getTransAttribute <em>Trans Attribute</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.ContainerTransactionType#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getContainerTransactionType()
 * @extends JavaEEObject
 * @generated
 */
public interface ContainerTransactionType extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Descriptions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.Description}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Descriptions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Descriptions</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getContainerTransactionType_Descriptions()
	 * @generated
	 */
	List<Description> getDescriptions();

	/**
	 * Returns the value of the '<em><b>Methods</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.ejb.MethodType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Methods</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Methods</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getContainerTransactionType_Methods()
	 * @generated
	 */
	List<MethodType> getMethods();

	/**
	 * Returns the value of the '<em><b>Trans Attribute</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.jst.javaee.ejb.TransactionAttributeType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trans Attribute</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trans Attribute</em>' attribute.
	 * @see org.eclipse.jst.javaee.ejb.TransactionAttributeType
	 * @see #isSetTransAttribute()
	 * @see #unsetTransAttribute()
	 * @see #setTransAttribute(TransactionAttributeType)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getContainerTransactionType_TransAttribute()
	 * @generated
	 */
	TransactionAttributeType getTransAttribute();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.ContainerTransactionType#getTransAttribute <em>Trans Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trans Attribute</em>' attribute.
	 * @see org.eclipse.jst.javaee.ejb.TransactionAttributeType
	 * @see #isSetTransAttribute()
	 * @see #unsetTransAttribute()
	 * @see #getTransAttribute()
	 * @generated
	 */
	void setTransAttribute(TransactionAttributeType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.ejb.ContainerTransactionType#getTransAttribute <em>Trans Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTransAttribute()
	 * @see #getTransAttribute()
	 * @see #setTransAttribute(TransactionAttributeType)
	 * @generated
	 */
	void unsetTransAttribute();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.ejb.ContainerTransactionType#getTransAttribute <em>Trans Attribute</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Trans Attribute</em>' attribute is set.
	 * @see #unsetTransAttribute()
	 * @see #getTransAttribute()
	 * @see #setTransAttribute(TransactionAttributeType)
	 * @generated
	 */
	boolean isSetTransAttribute();

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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getContainerTransactionType_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.ContainerTransactionType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // ContainerTransactionType