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
package org.eclipse.jst.javaee.core;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Lifecycle Callback</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The lifecycle-callback type specifies a method on a
 *         class to be called when a lifecycle event occurs.
 *         Note that each class may have only one lifecycle callback
 *         method for any given event and that the method may not
 *         be overloaded.
 *         
 *         If the lifefycle-callback-class element is missing then
 *         the class defining the callback is assumed to be the
 *         component class in scope at the place in the descriptor
 *         in which the callback definition appears.
 *         
 *         @since Java EE 5
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.LifecycleCallback#getLifecycleCallbackClass <em>Lifecycle Callback Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.LifecycleCallback#getLifecycleCallbackMethod <em>Lifecycle Callback Method</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getLifecycleCallback()
 * @extends JavaEEObject
 * @generated
 */
public interface LifecycleCallback extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Lifecycle Callback Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lifecycle Callback Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lifecycle Callback Class</em>' attribute.
	 * @see #setLifecycleCallbackClass(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getLifecycleCallback_LifecycleCallbackClass()
	 * @generated
	 */
	String getLifecycleCallbackClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.LifecycleCallback#getLifecycleCallbackClass <em>Lifecycle Callback Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lifecycle Callback Class</em>' attribute.
	 * @see #getLifecycleCallbackClass()
	 * @generated
	 */
	void setLifecycleCallbackClass(String value);

	/**
	 * Returns the value of the '<em><b>Lifecycle Callback Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lifecycle Callback Method</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lifecycle Callback Method</em>' attribute.
	 * @see #setLifecycleCallbackMethod(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getLifecycleCallback_LifecycleCallbackMethod()
	 * @generated
	 */
	String getLifecycleCallbackMethod();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.LifecycleCallback#getLifecycleCallbackMethod <em>Lifecycle Callback Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lifecycle Callback Method</em>' attribute.
	 * @see #getLifecycleCallbackMethod()
	 * @generated
	 */
	void setLifecycleCallbackMethod(String value);

} // LifecycleCallback