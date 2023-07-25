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
import org.eclipse.jst.j2ee.common.SecurityRole;
import org.eclipse.wst.common.frameworks.internal.enablement.nonui.WorkbenchUtil;

/**
 * A semantic grouping of operations that may be performed by a subject.
 * The security-role element contains the declaration of a security role which is used in the security-constraints placed on the web application.
 * @generated
 */
public class SecurityRoleImpl extends J2EEEObjectImpl implements SecurityRole {

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	static boolean inEclipse = WorkbenchUtil.workbenchIsRunning();
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String description = DESCRIPTION_EDEFAULT;
	/**
	 * The default value of the '{@link #getRoleName() <em>Role Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoleName()
	 * @generated
	 * @ordered
	 */
	protected static final String ROLE_NAME_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String roleName = ROLE_NAME_EDEFAULT;
	/**
	 * The cached value of the '{@link #getDescriptions() <em>Descriptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptions()
	 * @generated
	 * @ordered
	 */
	protected EList descriptions = null;

	public SecurityRoleImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CommonPackage.Literals.SECURITY_ROLE;
	}

@Override
public boolean equals(Object o) {
	if (inEclipse)
		return super.equals(o);
		
	if (o instanceof SecurityRoleImpl) {
		String r1 = getRoleName();
		String r2 = ((SecurityRole) o).getRoleName();
		if ((r1 != null) && (r2 != null)) {
			if (r1.equals(r2)) {
				return true;
			}
		} else if (r1 == null && r2 == null)
			return super.equals(o);
	}
	return false;
}
@Override
public int hashCode() {
	if (inEclipse || getRoleName() == null)
		return super.hashCode();
	return getRoleName().hashCode();
}
@Override
public String toString() {
	String name = getRoleName() == null ? "<unknown>" : getRoleName(); //$NON-NLS-1$
	return getClass().getName() + "(" + name + ")"; //$NON-NLS-1$ //$NON-NLS-2$
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
		if (getDescriptions() != null) {
			if (getDescriptions().isEmpty()) {
				Description d = new DescriptionImpl();
				d.setValue(newDescription);
				getDescriptions().add(d);
			} else {
				Description d = (Description) getDescriptions().get(0);
				if (d != null)
					d.setValue(newDescription);
			}
		}
		setDescriptionGen(newDescription);
	}
	
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setDescriptionGen(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SECURITY_ROLE__DESCRIPTION, oldDescription, description));
	}

	/**
	 * @generated This field/method will be replaced during code generation The
	 *            role-name element contains the name of a role. This element
	 *            must contain a non-empty string.
	 */
	@Override
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public void setRoleName(String newRoleName) {
		String oldRoleName = roleName;
		roleName = newRoleName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SECURITY_ROLE__ROLE_NAME, oldRoleName, roleName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList(Description.class, this, CommonPackage.SECURITY_ROLE__DESCRIPTIONS);
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
			case CommonPackage.SECURITY_ROLE__DESCRIPTIONS:
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
			case CommonPackage.SECURITY_ROLE__DESCRIPTION:
				return getDescription();
			case CommonPackage.SECURITY_ROLE__ROLE_NAME:
				return getRoleName();
			case CommonPackage.SECURITY_ROLE__DESCRIPTIONS:
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
			case CommonPackage.SECURITY_ROLE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case CommonPackage.SECURITY_ROLE__ROLE_NAME:
				setRoleName((String)newValue);
				return;
			case CommonPackage.SECURITY_ROLE__DESCRIPTIONS:
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
			case CommonPackage.SECURITY_ROLE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case CommonPackage.SECURITY_ROLE__ROLE_NAME:
				setRoleName(ROLE_NAME_EDEFAULT);
				return;
			case CommonPackage.SECURITY_ROLE__DESCRIPTIONS:
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
			case CommonPackage.SECURITY_ROLE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case CommonPackage.SECURITY_ROLE__ROLE_NAME:
				return ROLE_NAME_EDEFAULT == null ? roleName != null : !ROLE_NAME_EDEFAULT.equals(roleName);
			case CommonPackage.SECURITY_ROLE__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public String toStringGen() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (description: "); //$NON-NLS-1$
		result.append(description);
		result.append(", roleName: "); //$NON-NLS-1$
		result.append(roleName);
		result.append(')');
		return result.toString();
	}

}





