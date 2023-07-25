/*******************************************************************************
 * Copyright (c) 2007, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.core.internal.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.jst.javaee.core.LifecycleCallback;

import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Lifecycle Callback</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.LifecycleCallbackImpl#getLifecycleCallbackClass <em>Lifecycle Callback Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.LifecycleCallbackImpl#getLifecycleCallbackMethod <em>Lifecycle Callback Method</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LifecycleCallbackImpl extends EObjectImpl implements LifecycleCallback {
	/**
	 * The default value of the '{@link #getLifecycleCallbackClass() <em>Lifecycle Callback Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLifecycleCallbackClass()
	 * @generated
	 * @ordered
	 */
	protected static final String LIFECYCLE_CALLBACK_CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLifecycleCallbackClass() <em>Lifecycle Callback Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLifecycleCallbackClass()
	 * @generated
	 * @ordered
	 */
	protected String lifecycleCallbackClass = LIFECYCLE_CALLBACK_CLASS_EDEFAULT;

	/**
	 * The default value of the '{@link #getLifecycleCallbackMethod() <em>Lifecycle Callback Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLifecycleCallbackMethod()
	 * @generated
	 * @ordered
	 */
	protected static final String LIFECYCLE_CALLBACK_METHOD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLifecycleCallbackMethod() <em>Lifecycle Callback Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLifecycleCallbackMethod()
	 * @generated
	 * @ordered
	 */
	protected String lifecycleCallbackMethod = LIFECYCLE_CALLBACK_METHOD_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LifecycleCallbackImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaeePackage.Literals.LIFECYCLE_CALLBACK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLifecycleCallbackClass() {
		return lifecycleCallbackClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLifecycleCallbackClass(String newLifecycleCallbackClass) {
		String oldLifecycleCallbackClass = lifecycleCallbackClass;
		lifecycleCallbackClass = newLifecycleCallbackClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.LIFECYCLE_CALLBACK__LIFECYCLE_CALLBACK_CLASS, oldLifecycleCallbackClass, lifecycleCallbackClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLifecycleCallbackMethod() {
		return lifecycleCallbackMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLifecycleCallbackMethod(String newLifecycleCallbackMethod) {
		String oldLifecycleCallbackMethod = lifecycleCallbackMethod;
		lifecycleCallbackMethod = newLifecycleCallbackMethod;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.LIFECYCLE_CALLBACK__LIFECYCLE_CALLBACK_METHOD, oldLifecycleCallbackMethod, lifecycleCallbackMethod));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case JavaeePackage.LIFECYCLE_CALLBACK__LIFECYCLE_CALLBACK_CLASS:
				return getLifecycleCallbackClass();
			case JavaeePackage.LIFECYCLE_CALLBACK__LIFECYCLE_CALLBACK_METHOD:
				return getLifecycleCallbackMethod();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case JavaeePackage.LIFECYCLE_CALLBACK__LIFECYCLE_CALLBACK_CLASS:
				setLifecycleCallbackClass((String)newValue);
				return;
			case JavaeePackage.LIFECYCLE_CALLBACK__LIFECYCLE_CALLBACK_METHOD:
				setLifecycleCallbackMethod((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case JavaeePackage.LIFECYCLE_CALLBACK__LIFECYCLE_CALLBACK_CLASS:
				setLifecycleCallbackClass(LIFECYCLE_CALLBACK_CLASS_EDEFAULT);
				return;
			case JavaeePackage.LIFECYCLE_CALLBACK__LIFECYCLE_CALLBACK_METHOD:
				setLifecycleCallbackMethod(LIFECYCLE_CALLBACK_METHOD_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case JavaeePackage.LIFECYCLE_CALLBACK__LIFECYCLE_CALLBACK_CLASS:
				return LIFECYCLE_CALLBACK_CLASS_EDEFAULT == null ? lifecycleCallbackClass != null : !LIFECYCLE_CALLBACK_CLASS_EDEFAULT.equals(lifecycleCallbackClass);
			case JavaeePackage.LIFECYCLE_CALLBACK__LIFECYCLE_CALLBACK_METHOD:
				return LIFECYCLE_CALLBACK_METHOD_EDEFAULT == null ? lifecycleCallbackMethod != null : !LIFECYCLE_CALLBACK_METHOD_EDEFAULT.equals(lifecycleCallbackMethod);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (lifecycleCallbackClass: "); //$NON-NLS-1$
		result.append(lifecycleCallbackClass);
		result.append(", lifecycleCallbackMethod: "); //$NON-NLS-1$
		result.append(lifecycleCallbackMethod);
		result.append(')');
		return result.toString();
	}

} //LifecycleCallbackImpl