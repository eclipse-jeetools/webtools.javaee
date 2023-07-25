/*******************************************************************************
 * Copyright (c) 2009, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.jca.internal.impl;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jst.javaee.core.Description;

import org.eclipse.jst.javaee.jca.AuthenticationMechanism;
import org.eclipse.jst.javaee.jca.CredentialInterfaceType;

import org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Authentication Mechanism</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.AuthenticationMechanismImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.AuthenticationMechanismImpl#getAuthenticationMechanismType <em>Authentication Mechanism Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.AuthenticationMechanismImpl#getCredentialInterface <em>Credential Interface</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.AuthenticationMechanismImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AuthenticationMechanismImpl extends EObjectImpl implements AuthenticationMechanism {
	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected EList<Description> description;

	/**
	 * The default value of the '{@link #getAuthenticationMechanismType() <em>Authentication Mechanism Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthenticationMechanismType()
	 * @generated
	 * @ordered
	 */
	protected static final String AUTHENTICATION_MECHANISM_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAuthenticationMechanismType() <em>Authentication Mechanism Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthenticationMechanismType()
	 * @generated
	 * @ordered
	 */
	protected String authenticationMechanismType = AUTHENTICATION_MECHANISM_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getCredentialInterface() <em>Credential Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCredentialInterface()
	 * @generated
	 * @ordered
	 */
	protected static final CredentialInterfaceType CREDENTIAL_INTERFACE_EDEFAULT = CredentialInterfaceType.JAVAX_RESOURCE_SPI_SECURITY_PASSWORD_CREDENTIAL;

	/**
	 * The cached value of the '{@link #getCredentialInterface() <em>Credential Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCredentialInterface()
	 * @generated
	 * @ordered
	 */
	protected CredentialInterfaceType credentialInterface = CREDENTIAL_INTERFACE_EDEFAULT;

	/**
	 * This is true if the Credential Interface attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean credentialInterfaceESet;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AuthenticationMechanismImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JcaPackage.Literals.AUTHENTICATION_MECHANISM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Description> getDescription() {
		if (description == null) {
			description = new EObjectContainmentEList<Description>(Description.class, this, JcaPackage.AUTHENTICATION_MECHANISM__DESCRIPTION);
		}
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getAuthenticationMechanismType() {
		return authenticationMechanismType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAuthenticationMechanismType(String newAuthenticationMechanismType) {
		String oldAuthenticationMechanismType = authenticationMechanismType;
		authenticationMechanismType = newAuthenticationMechanismType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.AUTHENTICATION_MECHANISM__AUTHENTICATION_MECHANISM_TYPE, oldAuthenticationMechanismType, authenticationMechanismType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CredentialInterfaceType getCredentialInterface() {
		return credentialInterface;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCredentialInterface(CredentialInterfaceType newCredentialInterface) {
		CredentialInterfaceType oldCredentialInterface = credentialInterface;
		credentialInterface = newCredentialInterface == null ? CREDENTIAL_INTERFACE_EDEFAULT : newCredentialInterface;
		boolean oldCredentialInterfaceESet = credentialInterfaceESet;
		credentialInterfaceESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.AUTHENTICATION_MECHANISM__CREDENTIAL_INTERFACE, oldCredentialInterface, credentialInterface, !oldCredentialInterfaceESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetCredentialInterface() {
		CredentialInterfaceType oldCredentialInterface = credentialInterface;
		boolean oldCredentialInterfaceESet = credentialInterfaceESet;
		credentialInterface = CREDENTIAL_INTERFACE_EDEFAULT;
		credentialInterfaceESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JcaPackage.AUTHENTICATION_MECHANISM__CREDENTIAL_INTERFACE, oldCredentialInterface, CREDENTIAL_INTERFACE_EDEFAULT, oldCredentialInterfaceESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetCredentialInterface() {
		return credentialInterfaceESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.AUTHENTICATION_MECHANISM__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JcaPackage.AUTHENTICATION_MECHANISM__DESCRIPTION:
				return ((InternalEList<?>)getDescription()).basicRemove(otherEnd, msgs);
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
			case JcaPackage.AUTHENTICATION_MECHANISM__DESCRIPTION:
				return getDescription();
			case JcaPackage.AUTHENTICATION_MECHANISM__AUTHENTICATION_MECHANISM_TYPE:
				return getAuthenticationMechanismType();
			case JcaPackage.AUTHENTICATION_MECHANISM__CREDENTIAL_INTERFACE:
				return getCredentialInterface();
			case JcaPackage.AUTHENTICATION_MECHANISM__ID:
				return getId();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case JcaPackage.AUTHENTICATION_MECHANISM__DESCRIPTION:
				getDescription().clear();
				getDescription().addAll((Collection<? extends Description>)newValue);
				return;
			case JcaPackage.AUTHENTICATION_MECHANISM__AUTHENTICATION_MECHANISM_TYPE:
				setAuthenticationMechanismType((String)newValue);
				return;
			case JcaPackage.AUTHENTICATION_MECHANISM__CREDENTIAL_INTERFACE:
				setCredentialInterface((CredentialInterfaceType)newValue);
				return;
			case JcaPackage.AUTHENTICATION_MECHANISM__ID:
				setId((String)newValue);
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
			case JcaPackage.AUTHENTICATION_MECHANISM__DESCRIPTION:
				getDescription().clear();
				return;
			case JcaPackage.AUTHENTICATION_MECHANISM__AUTHENTICATION_MECHANISM_TYPE:
				setAuthenticationMechanismType(AUTHENTICATION_MECHANISM_TYPE_EDEFAULT);
				return;
			case JcaPackage.AUTHENTICATION_MECHANISM__CREDENTIAL_INTERFACE:
				unsetCredentialInterface();
				return;
			case JcaPackage.AUTHENTICATION_MECHANISM__ID:
				setId(ID_EDEFAULT);
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
			case JcaPackage.AUTHENTICATION_MECHANISM__DESCRIPTION:
				return description != null && !description.isEmpty();
			case JcaPackage.AUTHENTICATION_MECHANISM__AUTHENTICATION_MECHANISM_TYPE:
				return AUTHENTICATION_MECHANISM_TYPE_EDEFAULT == null ? authenticationMechanismType != null : !AUTHENTICATION_MECHANISM_TYPE_EDEFAULT.equals(authenticationMechanismType);
			case JcaPackage.AUTHENTICATION_MECHANISM__CREDENTIAL_INTERFACE:
				return isSetCredentialInterface();
			case JcaPackage.AUTHENTICATION_MECHANISM__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
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
		result.append(" (authenticationMechanismType: "); //$NON-NLS-1$
		result.append(authenticationMechanismType);
		result.append(", credentialInterface: "); //$NON-NLS-1$
		if (credentialInterfaceESet) result.append(credentialInterface); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //AuthenticationMechanismImpl
