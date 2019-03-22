/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
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
 * A representation of the model object '<em><b>Concurrent Method Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The concurrent-methodType specifies information about a method
 *         of a bean with container managed concurrency.
 *         
 *         The optional lock element specifies the kind of concurrency 
 *         lock asssociated with the method.
 *         
 *         The optional access-timeout element specifies the amount of
 *         time (in a given time unit) the container should wait for a
 *         concurrency lock before throwing an exception to the client.
 *         
 *         @since Java EE 6, EJB 3.1
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.ConcurrentMethodType#getMethod <em>Method</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.ConcurrentMethodType#getLock <em>Lock</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.ConcurrentMethodType#getAccessTimeout <em>Access Timeout</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.ConcurrentMethodType#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getConcurrentMethodType()
 * @extends JavaEEObject
 * @generated
 */
public interface ConcurrentMethodType extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Method</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method</em>' containment reference.
	 * @see #setMethod(NamedMethodType)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getConcurrentMethodType_Method()
	 * @generated
	 */
	NamedMethodType getMethod();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.ConcurrentMethodType#getMethod <em>Method</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method</em>' containment reference.
	 * @see #getMethod()
	 * @generated
	 */
	void setMethod(NamedMethodType value);

	/**
	 * Returns the value of the '<em><b>Lock</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.jst.javaee.ejb.ConcurrentLockTypeType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lock</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lock</em>' attribute.
	 * @see org.eclipse.jst.javaee.ejb.ConcurrentLockTypeType
	 * @see #isSetLock()
	 * @see #unsetLock()
	 * @see #setLock(ConcurrentLockTypeType)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getConcurrentMethodType_Lock()
	 * @generated
	 */
	ConcurrentLockTypeType getLock();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.ConcurrentMethodType#getLock <em>Lock</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lock</em>' attribute.
	 * @see org.eclipse.jst.javaee.ejb.ConcurrentLockTypeType
	 * @see #isSetLock()
	 * @see #unsetLock()
	 * @see #getLock()
	 * @generated
	 */
	void setLock(ConcurrentLockTypeType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.ejb.ConcurrentMethodType#getLock <em>Lock</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetLock()
	 * @see #getLock()
	 * @see #setLock(ConcurrentLockTypeType)
	 * @generated
	 */
	void unsetLock();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.ejb.ConcurrentMethodType#getLock <em>Lock</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Lock</em>' attribute is set.
	 * @see #unsetLock()
	 * @see #getLock()
	 * @see #setLock(ConcurrentLockTypeType)
	 * @generated
	 */
	boolean isSetLock();

	/**
	 * Returns the value of the '<em><b>Access Timeout</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Access Timeout</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Access Timeout</em>' containment reference.
	 * @see #setAccessTimeout(AccessTimeoutType)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getConcurrentMethodType_AccessTimeout()
	 * @generated
	 */
	AccessTimeoutType getAccessTimeout();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.ConcurrentMethodType#getAccessTimeout <em>Access Timeout</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Access Timeout</em>' containment reference.
	 * @see #getAccessTimeout()
	 * @generated
	 */
	void setAccessTimeout(AccessTimeoutType value);

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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getConcurrentMethodType_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.ConcurrentMethodType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // ConcurrentMethodType
