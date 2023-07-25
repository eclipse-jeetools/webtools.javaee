/*******************************************************************************
 * Copyright (c) 2001, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.common.internal.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.Identity;
import org.eclipse.jst.j2ee.common.RunAsSpecifiedIdentity;


/**
 * The security-identity element specifies whether the caller's security identity is to be used for the execution of the methods of the enterprise bean or whether a specific run-as identity is to be used. It    contains an optional description and a specification of the security identity to be used.
 * @generated
 */
public class RunAsSpecifiedIdentityImpl extends SecurityIdentityImpl implements RunAsSpecifiedIdentity {

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected Identity identity = null;
	public RunAsSpecifiedIdentityImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CommonPackage.Literals.RUN_AS_SPECIFIED_IDENTITY;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Optional specifies the run-as identity to be used for the execution of the methods of an enterprise bean. It contains an optional description, and the name of a security role.
	 */
	@Override
	public Identity getIdentity() {
		return identity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIdentity(Identity newIdentity, NotificationChain msgs) {
		Identity oldIdentity = identity;
		identity = newIdentity;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CommonPackage.RUN_AS_SPECIFIED_IDENTITY__IDENTITY, oldIdentity, newIdentity);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public void setIdentity(Identity newIdentity) {
		if (newIdentity != identity) {
			NotificationChain msgs = null;
			if (identity != null)
				msgs = ((InternalEObject)identity).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CommonPackage.RUN_AS_SPECIFIED_IDENTITY__IDENTITY, null, msgs);
			if (newIdentity != null)
				msgs = ((InternalEObject)newIdentity).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CommonPackage.RUN_AS_SPECIFIED_IDENTITY__IDENTITY, null, msgs);
			msgs = basicSetIdentity(newIdentity, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.RUN_AS_SPECIFIED_IDENTITY__IDENTITY, newIdentity, newIdentity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CommonPackage.RUN_AS_SPECIFIED_IDENTITY__IDENTITY:
				return basicSetIdentity(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommonPackage.RUN_AS_SPECIFIED_IDENTITY__IDENTITY:
				return getIdentity();
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
			case CommonPackage.RUN_AS_SPECIFIED_IDENTITY__IDENTITY:
				setIdentity((Identity)newValue);
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
			case CommonPackage.RUN_AS_SPECIFIED_IDENTITY__IDENTITY:
				setIdentity((Identity)null);
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
			case CommonPackage.RUN_AS_SPECIFIED_IDENTITY__IDENTITY:
				return identity != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.common.SecurityIdentity#isRunAsSpecifiedIdentity()
	 */
	@Override
	public boolean isRunAsSpecifiedIdentity() {
		return true;
	}

}





