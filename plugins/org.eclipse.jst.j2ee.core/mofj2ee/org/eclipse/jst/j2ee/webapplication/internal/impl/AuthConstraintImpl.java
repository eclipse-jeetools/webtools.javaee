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
package org.eclipse.jst.j2ee.webapplication.internal.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.internal.impl.J2EEEObjectImpl;
import org.eclipse.jst.j2ee.webapplication.AuthConstraint;
import org.eclipse.jst.j2ee.webapplication.SecurityConstraint;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;


/**
 * The auth-constraint element indicates the user roles that should be permitted access to this resource collection. The role used here must appear in a security-role-ref element.
 * @generated
 */
public class AuthConstraintImpl extends J2EEEObjectImpl implements AuthConstraint {

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
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList roles = null;
	/**
	 * The cached value of the '{@link #getDescriptions() <em>Descriptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptions()
	 * @generated
	 * @ordered
	 */
	protected EList descriptions = null;

	public AuthConstraintImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WebapplicationPackage.Literals.AUTH_CONSTRAINT;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * A description of the auth constraint.
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.AUTH_CONSTRAINT__DESCRIPTION, oldDescription, description));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * A list of role names for the auth constraint.  Each element specifies the name
	 * of a security role (a String).

	 */
	@Override
	public EList getRoles() {
		if (roles == null) {
			roles = new EDataTypeUniqueEList(String.class, this, WebapplicationPackage.AUTH_CONSTRAINT__ROLES);
		}
		return roles;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	@Override
	public SecurityConstraint getSecConstraint() {
		if (eContainerFeatureID != WebapplicationPackage.AUTH_CONSTRAINT__SEC_CONSTRAINT) return null;
		return (SecurityConstraint)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSecConstraint(SecurityConstraint newSecConstraint, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newSecConstraint, WebapplicationPackage.AUTH_CONSTRAINT__SEC_CONSTRAINT, msgs);
		return msgs;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public void setSecConstraint(SecurityConstraint newSecConstraint) {
		if (newSecConstraint != eInternalContainer() || (eContainerFeatureID != WebapplicationPackage.AUTH_CONSTRAINT__SEC_CONSTRAINT && newSecConstraint != null)) {
			if (EcoreUtil.isAncestor(this, newSecConstraint))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newSecConstraint != null)
				msgs = ((InternalEObject)newSecConstraint).eInverseAdd(this, WebapplicationPackage.SECURITY_CONSTRAINT__AUTH_CONSTRAINT, SecurityConstraint.class, msgs);
			msgs = basicSetSecConstraint(newSecConstraint, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.AUTH_CONSTRAINT__SEC_CONSTRAINT, newSecConstraint, newSecConstraint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList(Description.class, this, WebapplicationPackage.AUTH_CONSTRAINT__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WebapplicationPackage.AUTH_CONSTRAINT__SEC_CONSTRAINT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetSecConstraint((SecurityConstraint)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WebapplicationPackage.AUTH_CONSTRAINT__SEC_CONSTRAINT:
				return basicSetSecConstraint(null, msgs);
			case WebapplicationPackage.AUTH_CONSTRAINT__DESCRIPTIONS:
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
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID) {
			case WebapplicationPackage.AUTH_CONSTRAINT__SEC_CONSTRAINT:
				return eInternalContainer().eInverseRemove(this, WebapplicationPackage.SECURITY_CONSTRAINT__AUTH_CONSTRAINT, SecurityConstraint.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WebapplicationPackage.AUTH_CONSTRAINT__DESCRIPTION:
				return getDescription();
			case WebapplicationPackage.AUTH_CONSTRAINT__ROLES:
				return getRoles();
			case WebapplicationPackage.AUTH_CONSTRAINT__SEC_CONSTRAINT:
				return getSecConstraint();
			case WebapplicationPackage.AUTH_CONSTRAINT__DESCRIPTIONS:
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
			case WebapplicationPackage.AUTH_CONSTRAINT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case WebapplicationPackage.AUTH_CONSTRAINT__ROLES:
				getRoles().clear();
				getRoles().addAll((Collection)newValue);
				return;
			case WebapplicationPackage.AUTH_CONSTRAINT__SEC_CONSTRAINT:
				setSecConstraint((SecurityConstraint)newValue);
				return;
			case WebapplicationPackage.AUTH_CONSTRAINT__DESCRIPTIONS:
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
			case WebapplicationPackage.AUTH_CONSTRAINT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case WebapplicationPackage.AUTH_CONSTRAINT__ROLES:
				getRoles().clear();
				return;
			case WebapplicationPackage.AUTH_CONSTRAINT__SEC_CONSTRAINT:
				setSecConstraint((SecurityConstraint)null);
				return;
			case WebapplicationPackage.AUTH_CONSTRAINT__DESCRIPTIONS:
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
			case WebapplicationPackage.AUTH_CONSTRAINT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case WebapplicationPackage.AUTH_CONSTRAINT__ROLES:
				return roles != null && !roles.isEmpty();
			case WebapplicationPackage.AUTH_CONSTRAINT__SEC_CONSTRAINT:
				return getSecConstraint() != null;
			case WebapplicationPackage.AUTH_CONSTRAINT__DESCRIPTIONS:
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
		result.append(", roles: "); //$NON-NLS-1$
		result.append(roles);
		result.append(')');
		return result.toString();
	}

}














