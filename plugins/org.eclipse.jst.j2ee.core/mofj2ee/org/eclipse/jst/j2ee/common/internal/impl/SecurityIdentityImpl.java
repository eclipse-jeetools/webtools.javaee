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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.SecurityIdentity;


/**
 * The security-identity element specifies whether the caller's security identity is to be used for the execution of the methods of the enterprise bean or whether a specific run-as identity is to be used. It
 * contains an optional description and a specification of the security identity to be used.
 * @generated
 */
public class SecurityIdentityImpl extends J2EEEObjectImpl implements SecurityIdentity {

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String description = DESCRIPTION_EDEFAULT;
	/**
	 * The cached value of the '{@link #getDescriptions() <em>Descriptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptions()
	 * @generated
	 * @ordered
	 */
	protected EList descriptions = null;

	public SecurityIdentityImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CommonPackage.Literals.SECURITY_IDENTITY;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public String getDescriptionGen() {
		return description;
	}
	
	@Override
	public String getDescription() {
		if (!getDescriptions().isEmpty()) {
			Description d = (Description) getDescriptions().get(0);
			if (d != null)
				description = d.getValue();
		}
		return getDescriptionGen();
	}

	@Override
	public void setDescription(String newDescription) {
		String descriptionValue = newDescription;
		if (getDescriptions() != null) {
			if(newDescription == null || newDescription.length() < 1) {
				if(!getDescriptions().isEmpty()) {
					getDescriptions().remove(0);
					if(!getDescriptions().isEmpty()) {
						Description d = (Description) getDescriptions().get(0);
						descriptionValue = d.getValue();
					}
				}
			} else if (getDescriptions().isEmpty()) {
				Description d = new DescriptionImpl();
				d.setValue(descriptionValue);
				getDescriptions().add(d);
			} else {
				Description d = (Description) getDescriptions().get(0);
				if (d != null)
					d.setValue(descriptionValue);
			}
		}
		setDescriptionGen(descriptionValue);
	}
	
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setDescriptionGen(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SECURITY_IDENTITY__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList(Description.class, this, CommonPackage.SECURITY_IDENTITY__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CommonPackage.SECURITY_IDENTITY__DESCRIPTIONS:
				return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
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
			case CommonPackage.SECURITY_IDENTITY__DESCRIPTION:
				return getDescription();
			case CommonPackage.SECURITY_IDENTITY__DESCRIPTIONS:
				return getDescriptions();
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
			case CommonPackage.SECURITY_IDENTITY__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case CommonPackage.SECURITY_IDENTITY__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
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
			case CommonPackage.SECURITY_IDENTITY__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case CommonPackage.SECURITY_IDENTITY__DESCRIPTIONS:
				getDescriptions().clear();
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
			case CommonPackage.SECURITY_IDENTITY__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case CommonPackage.SECURITY_IDENTITY__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (description: "); //$NON-NLS-1$
		result.append(description);
		result.append(')');
		return result.toString();
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.common.SecurityIdentity#isRunAsSpecifiedIdentity()
	 */
	@Override
	public boolean isRunAsSpecifiedIdentity() {
		return false;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.common.SecurityIdentity#isUseCallerIdentity()
	 */
	@Override
	public boolean isUseCallerIdentity() {
		return false;
	}

}





