/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.common.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.Identity;
import org.eclipse.jst.j2ee.common.RunAsSpecifiedIdentity;
import org.eclipse.jst.j2ee.common.SecurityIdentity;


/**
 * The security-identity element specifies whether the caller's security identity is to be used for the execution of the methods of the enterprise bean or whether a specific run-as identity is to be used. It    contains an optional description and a specification of the security identity to be used.

 */
public class RunAsSpecifiedIdentityImpl extends SecurityIdentityImpl implements RunAsSpecifiedIdentity, SecurityIdentity{

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
	protected EClass eStaticClass() {
		return CommonPackage.eINSTANCE.getRunAsSpecifiedIdentity();
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Optional specifies the run-as identity to be used for the execution of the methods of an enterprise bean. It contains an optional description, and the name of a security role.
	 */
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
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case CommonPackage.RUN_AS_SPECIFIED_IDENTITY__DESCRIPTIONS:
					return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
				case CommonPackage.RUN_AS_SPECIFIED_IDENTITY__IDENTITY:
					return basicSetIdentity(null, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonPackage.RUN_AS_SPECIFIED_IDENTITY__DESCRIPTION:
				return getDescription();
			case CommonPackage.RUN_AS_SPECIFIED_IDENTITY__DESCRIPTIONS:
				return getDescriptions();
			case CommonPackage.RUN_AS_SPECIFIED_IDENTITY__IDENTITY:
				return getIdentity();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonPackage.RUN_AS_SPECIFIED_IDENTITY__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case CommonPackage.RUN_AS_SPECIFIED_IDENTITY__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case CommonPackage.RUN_AS_SPECIFIED_IDENTITY__IDENTITY:
				return identity != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonPackage.RUN_AS_SPECIFIED_IDENTITY__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case CommonPackage.RUN_AS_SPECIFIED_IDENTITY__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
				return;
			case CommonPackage.RUN_AS_SPECIFIED_IDENTITY__IDENTITY:
				setIdentity((Identity)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonPackage.RUN_AS_SPECIFIED_IDENTITY__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case CommonPackage.RUN_AS_SPECIFIED_IDENTITY__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case CommonPackage.RUN_AS_SPECIFIED_IDENTITY__IDENTITY:
				setIdentity((Identity)null);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * @see org.eclipse.jst.j2ee.common.SecurityIdentity#isRunAsSpecifiedIdentity()
	 */
	public boolean isRunAsSpecifiedIdentity() {
		return true;
	}

}





