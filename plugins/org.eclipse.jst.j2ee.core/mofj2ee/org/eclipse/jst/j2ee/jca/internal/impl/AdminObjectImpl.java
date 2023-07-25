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
package org.eclipse.jst.j2ee.jca.internal.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.common.internal.impl.J2EEEObjectImpl;
import org.eclipse.jst.j2ee.jca.AdminObject;
import org.eclipse.jst.j2ee.jca.ConfigProperty;
import org.eclipse.jst.j2ee.jca.JcaPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Admin Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.jca.internal.impl.AdminObjectImpl#getAdminObjectInterface <em>Admin Object Interface</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.jca.internal.impl.AdminObjectImpl#getAdminObjectClass <em>Admin Object Class</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.jca.internal.impl.AdminObjectImpl#getConfigProperties <em>Config Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AdminObjectImpl extends J2EEEObjectImpl implements AdminObject {
	/**
	 * The default value of the '{@link #getAdminObjectInterface() <em>Admin Object Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdminObjectInterface()
	 * @generated
	 * @ordered
	 */
	protected static final String ADMIN_OBJECT_INTERFACE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAdminObjectInterface() <em>Admin Object Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdminObjectInterface()
	 * @generated
	 * @ordered
	 */
	protected String adminObjectInterface = ADMIN_OBJECT_INTERFACE_EDEFAULT;

	/**
	 * The default value of the '{@link #getAdminObjectClass() <em>Admin Object Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdminObjectClass()
	 * @generated
	 * @ordered
	 */
	protected static final String ADMIN_OBJECT_CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAdminObjectClass() <em>Admin Object Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdminObjectClass()
	 * @generated
	 * @ordered
	 */
	protected String adminObjectClass = ADMIN_OBJECT_CLASS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getConfigProperties() <em>Config Properties</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfigProperties()
	 * @generated
	 * @ordered
	 */
	protected EList configProperties = null;

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
	@Override
	public String getAdminObjectInterface() {
		return adminObjectInterface;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAdminObjectInterface(String newAdminObjectInterface) {
		String oldAdminObjectInterface = adminObjectInterface;
		adminObjectInterface = newAdminObjectInterface;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.ADMIN_OBJECT__ADMIN_OBJECT_INTERFACE, oldAdminObjectInterface, adminObjectInterface));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getAdminObjectClass() {
		return adminObjectClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAdminObjectClass(String newAdminObjectClass) {
		String oldAdminObjectClass = adminObjectClass;
		adminObjectClass = newAdminObjectClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.ADMIN_OBJECT__ADMIN_OBJECT_CLASS, oldAdminObjectClass, adminObjectClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList getConfigProperties() {
		if (configProperties == null) {
			configProperties = new EObjectContainmentEList(ConfigProperty.class, this, JcaPackage.ADMIN_OBJECT__CONFIG_PROPERTIES);
		}
		return configProperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JcaPackage.ADMIN_OBJECT__CONFIG_PROPERTIES:
				return ((InternalEList)getConfigProperties()).basicRemove(otherEnd, msgs);
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
			case JcaPackage.ADMIN_OBJECT__ADMIN_OBJECT_INTERFACE:
				return getAdminObjectInterface();
			case JcaPackage.ADMIN_OBJECT__ADMIN_OBJECT_CLASS:
				return getAdminObjectClass();
			case JcaPackage.ADMIN_OBJECT__CONFIG_PROPERTIES:
				return getConfigProperties();
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
			case JcaPackage.ADMIN_OBJECT__ADMIN_OBJECT_INTERFACE:
				setAdminObjectInterface((String)newValue);
				return;
			case JcaPackage.ADMIN_OBJECT__ADMIN_OBJECT_CLASS:
				setAdminObjectClass((String)newValue);
				return;
			case JcaPackage.ADMIN_OBJECT__CONFIG_PROPERTIES:
				getConfigProperties().clear();
				getConfigProperties().addAll((Collection)newValue);
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
			case JcaPackage.ADMIN_OBJECT__ADMIN_OBJECT_INTERFACE:
				setAdminObjectInterface(ADMIN_OBJECT_INTERFACE_EDEFAULT);
				return;
			case JcaPackage.ADMIN_OBJECT__ADMIN_OBJECT_CLASS:
				setAdminObjectClass(ADMIN_OBJECT_CLASS_EDEFAULT);
				return;
			case JcaPackage.ADMIN_OBJECT__CONFIG_PROPERTIES:
				getConfigProperties().clear();
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
			case JcaPackage.ADMIN_OBJECT__ADMIN_OBJECT_INTERFACE:
				return ADMIN_OBJECT_INTERFACE_EDEFAULT == null ? adminObjectInterface != null : !ADMIN_OBJECT_INTERFACE_EDEFAULT.equals(adminObjectInterface);
			case JcaPackage.ADMIN_OBJECT__ADMIN_OBJECT_CLASS:
				return ADMIN_OBJECT_CLASS_EDEFAULT == null ? adminObjectClass != null : !ADMIN_OBJECT_CLASS_EDEFAULT.equals(adminObjectClass);
			case JcaPackage.ADMIN_OBJECT__CONFIG_PROPERTIES:
				return configProperties != null && !configProperties.isEmpty();
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
		result.append(" (adminObjectInterface: "); //$NON-NLS-1$
		result.append(adminObjectInterface);
		result.append(", adminObjectClass: "); //$NON-NLS-1$
		result.append(adminObjectClass);
		result.append(')');
		return result.toString();
	}

} //AdminObjectImpl
