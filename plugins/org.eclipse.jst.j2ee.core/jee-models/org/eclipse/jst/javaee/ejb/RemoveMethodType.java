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

import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Remove Method Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.RemoveMethodType#getBeanMethod <em>Bean Method</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.RemoveMethodType#isRetainIfException <em>Retain If Exception</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.RemoveMethodType#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getRemoveMethodType()
 * @extends JavaEEObject
 * @generated
 */
public interface RemoveMethodType extends JavaEEObject {
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getRemoveMethodType_BeanMethod()
	 * @generated
	 */
	NamedMethodType getBeanMethod();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.RemoveMethodType#getBeanMethod <em>Bean Method</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bean Method</em>' containment reference.
	 * @see #getBeanMethod()
	 * @generated
	 */
	void setBeanMethod(NamedMethodType value);

	/**
	 * Returns the value of the '<em><b>Retain If Exception</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Retain If Exception</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Retain If Exception</em>' attribute.
	 * @see #isSetRetainIfException()
	 * @see #unsetRetainIfException()
	 * @see #setRetainIfException(boolean)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getRemoveMethodType_RetainIfException()
	 * @generated
	 */
	boolean isRetainIfException();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.RemoveMethodType#isRetainIfException <em>Retain If Exception</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Retain If Exception</em>' attribute.
	 * @see #isSetRetainIfException()
	 * @see #unsetRetainIfException()
	 * @see #isRetainIfException()
	 * @generated
	 */
	void setRetainIfException(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.ejb.RemoveMethodType#isRetainIfException <em>Retain If Exception</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetRetainIfException()
	 * @see #isRetainIfException()
	 * @see #setRetainIfException(boolean)
	 * @generated
	 */
	void unsetRetainIfException();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.ejb.RemoveMethodType#isRetainIfException <em>Retain If Exception</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Retain If Exception</em>' attribute is set.
	 * @see #unsetRetainIfException()
	 * @see #isRetainIfException()
	 * @see #setRetainIfException(boolean)
	 * @generated
	 */
	boolean isSetRetainIfException();

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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getRemoveMethodType_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.RemoveMethodType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // RemoveMethodType