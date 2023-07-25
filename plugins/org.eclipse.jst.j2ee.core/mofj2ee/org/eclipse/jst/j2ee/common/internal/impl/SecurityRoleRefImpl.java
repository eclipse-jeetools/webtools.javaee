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
import org.eclipse.jst.j2ee.common.SecurityRoleRef;


/**
 * @generated
 */
public class SecurityRoleRefImpl extends J2EEEObjectImpl implements SecurityRoleRef {

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String name = NAME_EDEFAULT;
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
	protected String description = DESCRIPTION_EDEFAULT;
	/**
	 * The default value of the '{@link #getLink() <em>Link</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLink()
	 * @generated
	 * @ordered
	 */
	protected static final String LINK_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String link = LINK_EDEFAULT;
	/**
	 * The cached value of the '{@link #getDescriptions() <em>Descriptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptions()
	 * @generated
	 * @ordered
	 */
	protected EList descriptions = null;

	public SecurityRoleRefImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CommonPackage.Literals.SECURITY_ROLE_REF;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SECURITY_ROLE_REF__NAME, oldName, name));
	}

	@Override
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (getDescriptions() != null && !getDescriptions().isEmpty()) {
			Description d = (Description) getDescriptions().get(0);
			if (d != null)
				d.setValue(newDescription);
		}
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SECURITY_ROLE_REF__DESCRIPTION, oldDescription, description));
	}

	@Override
	public String getDescription() {
		if (getDescriptions() != null && !getDescriptions().isEmpty()) {
			Description d = (Description) getDescriptions().get(0);
			if (d != null)
				description = d.getValue();
		}
		return description;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The role-link element is used to link a security role reference to a defined security role. The role-link element must contain the name of one of the security roles defined in the security-role elements.
	 */
	@Override
	public String getLink() {
		return link;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public void setLink(String newLink) {
		String oldLink = link;
		link = newLink;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SECURITY_ROLE_REF__LINK, oldLink, link));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList(Description.class, this, CommonPackage.SECURITY_ROLE_REF__DESCRIPTIONS);
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
			case CommonPackage.SECURITY_ROLE_REF__DESCRIPTIONS:
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
			case CommonPackage.SECURITY_ROLE_REF__NAME:
				return getName();
			case CommonPackage.SECURITY_ROLE_REF__DESCRIPTION:
				return getDescription();
			case CommonPackage.SECURITY_ROLE_REF__LINK:
				return getLink();
			case CommonPackage.SECURITY_ROLE_REF__DESCRIPTIONS:
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
			case CommonPackage.SECURITY_ROLE_REF__NAME:
				setName((String)newValue);
				return;
			case CommonPackage.SECURITY_ROLE_REF__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case CommonPackage.SECURITY_ROLE_REF__LINK:
				setLink((String)newValue);
				return;
			case CommonPackage.SECURITY_ROLE_REF__DESCRIPTIONS:
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
			case CommonPackage.SECURITY_ROLE_REF__NAME:
				setName(NAME_EDEFAULT);
				return;
			case CommonPackage.SECURITY_ROLE_REF__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case CommonPackage.SECURITY_ROLE_REF__LINK:
				setLink(LINK_EDEFAULT);
				return;
			case CommonPackage.SECURITY_ROLE_REF__DESCRIPTIONS:
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
			case CommonPackage.SECURITY_ROLE_REF__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case CommonPackage.SECURITY_ROLE_REF__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case CommonPackage.SECURITY_ROLE_REF__LINK:
				return LINK_EDEFAULT == null ? link != null : !LINK_EDEFAULT.equals(link);
			case CommonPackage.SECURITY_ROLE_REF__DESCRIPTIONS:
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
		result.append(" (name: "); //$NON-NLS-1$
		result.append(name);
		result.append(", description: "); //$NON-NLS-1$
		result.append(description);
		result.append(", link: "); //$NON-NLS-1$
		result.append(link);
		result.append(')');
		return result.toString();
	}

}





