/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
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

import org.eclipse.jst.javaee.jca.AdminObject;
import org.eclipse.jst.javaee.jca.ConfigProperty;

import org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Admin Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.AdminObjectImpl#getAdminobjectInterface <em>Adminobject Interface</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.AdminObjectImpl#getAdminobjectClass <em>Adminobject Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.AdminObjectImpl#getConfigProperty <em>Config Property</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.AdminObjectImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AdminObjectImpl extends EObjectImpl implements AdminObject {
	/**
	 * The default value of the '{@link #getAdminobjectInterface() <em>Adminobject Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdminobjectInterface()
	 * @generated
	 * @ordered
	 */
	protected static final String ADMINOBJECT_INTERFACE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAdminobjectInterface() <em>Adminobject Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdminobjectInterface()
	 * @generated
	 * @ordered
	 */
	protected String adminobjectInterface = ADMINOBJECT_INTERFACE_EDEFAULT;

	/**
	 * The default value of the '{@link #getAdminobjectClass() <em>Adminobject Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdminobjectClass()
	 * @generated
	 * @ordered
	 */
	protected static final String ADMINOBJECT_CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAdminobjectClass() <em>Adminobject Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdminobjectClass()
	 * @generated
	 * @ordered
	 */
	protected String adminobjectClass = ADMINOBJECT_CLASS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getConfigProperty() <em>Config Property</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfigProperty()
	 * @generated
	 * @ordered
	 */
	protected EList<ConfigProperty> configProperty;

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
	protected AdminObjectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JcaPackage.Literals.ADMIN_OBJECT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAdminobjectInterface() {
		return adminobjectInterface;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAdminobjectInterface(String newAdminobjectInterface) {
		String oldAdminobjectInterface = adminobjectInterface;
		adminobjectInterface = newAdminobjectInterface;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.ADMIN_OBJECT__ADMINOBJECT_INTERFACE, oldAdminobjectInterface, adminobjectInterface));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAdminobjectClass() {
		return adminobjectClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAdminobjectClass(String newAdminobjectClass) {
		String oldAdminobjectClass = adminobjectClass;
		adminobjectClass = newAdminobjectClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.ADMIN_OBJECT__ADMINOBJECT_CLASS, oldAdminobjectClass, adminobjectClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ConfigProperty> getConfigProperty() {
		if (configProperty == null) {
			configProperty = new EObjectContainmentEList<ConfigProperty>(ConfigProperty.class, this, JcaPackage.ADMIN_OBJECT__CONFIG_PROPERTY);
		}
		return configProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.ADMIN_OBJECT__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JcaPackage.ADMIN_OBJECT__CONFIG_PROPERTY:
				return ((InternalEList<?>)getConfigProperty()).basicRemove(otherEnd, msgs);
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
			case JcaPackage.ADMIN_OBJECT__ADMINOBJECT_INTERFACE:
				return getAdminobjectInterface();
			case JcaPackage.ADMIN_OBJECT__ADMINOBJECT_CLASS:
				return getAdminobjectClass();
			case JcaPackage.ADMIN_OBJECT__CONFIG_PROPERTY:
				return getConfigProperty();
			case JcaPackage.ADMIN_OBJECT__ID:
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
			case JcaPackage.ADMIN_OBJECT__ADMINOBJECT_INTERFACE:
				setAdminobjectInterface((String)newValue);
				return;
			case JcaPackage.ADMIN_OBJECT__ADMINOBJECT_CLASS:
				setAdminobjectClass((String)newValue);
				return;
			case JcaPackage.ADMIN_OBJECT__CONFIG_PROPERTY:
				getConfigProperty().clear();
				getConfigProperty().addAll((Collection<? extends ConfigProperty>)newValue);
				return;
			case JcaPackage.ADMIN_OBJECT__ID:
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
			case JcaPackage.ADMIN_OBJECT__ADMINOBJECT_INTERFACE:
				setAdminobjectInterface(ADMINOBJECT_INTERFACE_EDEFAULT);
				return;
			case JcaPackage.ADMIN_OBJECT__ADMINOBJECT_CLASS:
				setAdminobjectClass(ADMINOBJECT_CLASS_EDEFAULT);
				return;
			case JcaPackage.ADMIN_OBJECT__CONFIG_PROPERTY:
				getConfigProperty().clear();
				return;
			case JcaPackage.ADMIN_OBJECT__ID:
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
			case JcaPackage.ADMIN_OBJECT__ADMINOBJECT_INTERFACE:
				return ADMINOBJECT_INTERFACE_EDEFAULT == null ? adminobjectInterface != null : !ADMINOBJECT_INTERFACE_EDEFAULT.equals(adminobjectInterface);
			case JcaPackage.ADMIN_OBJECT__ADMINOBJECT_CLASS:
				return ADMINOBJECT_CLASS_EDEFAULT == null ? adminobjectClass != null : !ADMINOBJECT_CLASS_EDEFAULT.equals(adminobjectClass);
			case JcaPackage.ADMIN_OBJECT__CONFIG_PROPERTY:
				return configProperty != null && !configProperty.isEmpty();
			case JcaPackage.ADMIN_OBJECT__ID:
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
		result.append(" (adminobjectInterface: "); //$NON-NLS-1$
		result.append(adminobjectInterface);
		result.append(", adminobjectClass: "); //$NON-NLS-1$
		result.append(adminobjectClass);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //AdminObjectImpl
