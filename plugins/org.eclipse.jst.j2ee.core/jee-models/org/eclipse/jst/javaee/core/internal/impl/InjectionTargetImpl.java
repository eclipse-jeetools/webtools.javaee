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

import org.eclipse.jst.javaee.core.InjectionTarget;

import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Injection Target</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.InjectionTargetImpl#getInjectionTargetClass <em>Injection Target Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.InjectionTargetImpl#getInjectionTargetName <em>Injection Target Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InjectionTargetImpl extends EObjectImpl implements InjectionTarget {
	/**
	 * The default value of the '{@link #getInjectionTargetClass() <em>Injection Target Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInjectionTargetClass()
	 * @generated
	 * @ordered
	 */
	protected static final String INJECTION_TARGET_CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInjectionTargetClass() <em>Injection Target Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInjectionTargetClass()
	 * @generated
	 * @ordered
	 */
	protected String injectionTargetClass = INJECTION_TARGET_CLASS_EDEFAULT;

	/**
	 * The default value of the '{@link #getInjectionTargetName() <em>Injection Target Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInjectionTargetName()
	 * @generated
	 * @ordered
	 */
	protected static final String INJECTION_TARGET_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInjectionTargetName() <em>Injection Target Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInjectionTargetName()
	 * @generated
	 * @ordered
	 */
	protected String injectionTargetName = INJECTION_TARGET_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InjectionTargetImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaeePackage.Literals.INJECTION_TARGET;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getInjectionTargetClass() {
		return injectionTargetClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setInjectionTargetClass(String newInjectionTargetClass) {
		String oldInjectionTargetClass = injectionTargetClass;
		injectionTargetClass = newInjectionTargetClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.INJECTION_TARGET__INJECTION_TARGET_CLASS, oldInjectionTargetClass, injectionTargetClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getInjectionTargetName() {
		return injectionTargetName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setInjectionTargetName(String newInjectionTargetName) {
		String oldInjectionTargetName = injectionTargetName;
		injectionTargetName = newInjectionTargetName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.INJECTION_TARGET__INJECTION_TARGET_NAME, oldInjectionTargetName, injectionTargetName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case JavaeePackage.INJECTION_TARGET__INJECTION_TARGET_CLASS:
				return getInjectionTargetClass();
			case JavaeePackage.INJECTION_TARGET__INJECTION_TARGET_NAME:
				return getInjectionTargetName();
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
			case JavaeePackage.INJECTION_TARGET__INJECTION_TARGET_CLASS:
				setInjectionTargetClass((String)newValue);
				return;
			case JavaeePackage.INJECTION_TARGET__INJECTION_TARGET_NAME:
				setInjectionTargetName((String)newValue);
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
			case JavaeePackage.INJECTION_TARGET__INJECTION_TARGET_CLASS:
				setInjectionTargetClass(INJECTION_TARGET_CLASS_EDEFAULT);
				return;
			case JavaeePackage.INJECTION_TARGET__INJECTION_TARGET_NAME:
				setInjectionTargetName(INJECTION_TARGET_NAME_EDEFAULT);
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
			case JavaeePackage.INJECTION_TARGET__INJECTION_TARGET_CLASS:
				return INJECTION_TARGET_CLASS_EDEFAULT == null ? injectionTargetClass != null : !INJECTION_TARGET_CLASS_EDEFAULT.equals(injectionTargetClass);
			case JavaeePackage.INJECTION_TARGET__INJECTION_TARGET_NAME:
				return INJECTION_TARGET_NAME_EDEFAULT == null ? injectionTargetName != null : !INJECTION_TARGET_NAME_EDEFAULT.equals(injectionTargetName);
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
		result.append(" (injectionTargetClass: "); //$NON-NLS-1$
		result.append(injectionTargetClass);
		result.append(", injectionTargetName: "); //$NON-NLS-1$
		result.append(injectionTargetName);
		result.append(')');
		return result.toString();
	}

} //InjectionTargetImpl