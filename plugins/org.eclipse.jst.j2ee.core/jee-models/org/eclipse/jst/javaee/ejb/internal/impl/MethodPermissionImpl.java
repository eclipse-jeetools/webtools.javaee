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
package org.eclipse.jst.javaee.ejb.internal.impl;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.EmptyType;

import org.eclipse.jst.javaee.ejb.MethodPermission;
import org.eclipse.jst.javaee.ejb.MethodType;

import org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Method Permission</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MethodPermissionImpl#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MethodPermissionImpl#getRoleNames <em>Role Names</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MethodPermissionImpl#getUnchecked <em>Unchecked</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MethodPermissionImpl#getMethods <em>Methods</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MethodPermissionImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MethodPermissionImpl extends EObjectImpl implements MethodPermission {
	/**
	 * The cached value of the '{@link #getDescriptions() <em>Descriptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptions()
	 * @generated
	 * @ordered
	 */
	protected EList<Description> descriptions;

	/**
	 * The cached value of the '{@link #getRoleNames() <em>Role Names</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoleNames()
	 * @generated
	 * @ordered
	 */
	protected EList<String> roleNames;

	/**
	 * The cached value of the '{@link #getUnchecked() <em>Unchecked</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnchecked()
	 * @generated
	 * @ordered
	 */
	protected EmptyType unchecked;

	/**
	 * The cached value of the '{@link #getMethods() <em>Methods</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethods()
	 * @generated
	 * @ordered
	 */
	protected EList<MethodType> methods;

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
	protected MethodPermissionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EjbPackage.Literals.METHOD_PERMISSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Description> getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList<Description>(Description.class, this, EjbPackage.METHOD_PERMISSION__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<String> getRoleNames() {
		if (roleNames == null) {
			roleNames = new EDataTypeEList<String>(String.class, this, EjbPackage.METHOD_PERMISSION__ROLE_NAMES);
		}
		return roleNames;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EmptyType getUnchecked() {
		return unchecked;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUnchecked(EmptyType newUnchecked, NotificationChain msgs) {
		EmptyType oldUnchecked = unchecked;
		unchecked = newUnchecked;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.METHOD_PERMISSION__UNCHECKED, oldUnchecked, newUnchecked);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUnchecked(EmptyType newUnchecked) {
		if (newUnchecked != unchecked) {
			NotificationChain msgs = null;
			if (unchecked != null)
				msgs = ((InternalEObject)unchecked).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.METHOD_PERMISSION__UNCHECKED, null, msgs);
			if (newUnchecked != null)
				msgs = ((InternalEObject)newUnchecked).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EjbPackage.METHOD_PERMISSION__UNCHECKED, null, msgs);
			msgs = basicSetUnchecked(newUnchecked, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.METHOD_PERMISSION__UNCHECKED, newUnchecked, newUnchecked));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<MethodType> getMethods() {
		if (methods == null) {
			methods = new EObjectContainmentEList<MethodType>(MethodType.class, this, EjbPackage.METHOD_PERMISSION__METHODS);
		}
		return methods;
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
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.METHOD_PERMISSION__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EjbPackage.METHOD_PERMISSION__DESCRIPTIONS:
				return ((InternalEList<?>)getDescriptions()).basicRemove(otherEnd, msgs);
			case EjbPackage.METHOD_PERMISSION__UNCHECKED:
				return basicSetUnchecked(null, msgs);
			case EjbPackage.METHOD_PERMISSION__METHODS:
				return ((InternalEList<?>)getMethods()).basicRemove(otherEnd, msgs);
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
			case EjbPackage.METHOD_PERMISSION__DESCRIPTIONS:
				return getDescriptions();
			case EjbPackage.METHOD_PERMISSION__ROLE_NAMES:
				return getRoleNames();
			case EjbPackage.METHOD_PERMISSION__UNCHECKED:
				return getUnchecked();
			case EjbPackage.METHOD_PERMISSION__METHODS:
				return getMethods();
			case EjbPackage.METHOD_PERMISSION__ID:
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
			case EjbPackage.METHOD_PERMISSION__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection<? extends Description>)newValue);
				return;
			case EjbPackage.METHOD_PERMISSION__ROLE_NAMES:
				getRoleNames().clear();
				getRoleNames().addAll((Collection<? extends String>)newValue);
				return;
			case EjbPackage.METHOD_PERMISSION__UNCHECKED:
				setUnchecked((EmptyType)newValue);
				return;
			case EjbPackage.METHOD_PERMISSION__METHODS:
				getMethods().clear();
				getMethods().addAll((Collection<? extends MethodType>)newValue);
				return;
			case EjbPackage.METHOD_PERMISSION__ID:
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
			case EjbPackage.METHOD_PERMISSION__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case EjbPackage.METHOD_PERMISSION__ROLE_NAMES:
				getRoleNames().clear();
				return;
			case EjbPackage.METHOD_PERMISSION__UNCHECKED:
				setUnchecked((EmptyType)null);
				return;
			case EjbPackage.METHOD_PERMISSION__METHODS:
				getMethods().clear();
				return;
			case EjbPackage.METHOD_PERMISSION__ID:
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
			case EjbPackage.METHOD_PERMISSION__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case EjbPackage.METHOD_PERMISSION__ROLE_NAMES:
				return roleNames != null && !roleNames.isEmpty();
			case EjbPackage.METHOD_PERMISSION__UNCHECKED:
				return unchecked != null;
			case EjbPackage.METHOD_PERMISSION__METHODS:
				return methods != null && !methods.isEmpty();
			case EjbPackage.METHOD_PERMISSION__ID:
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
		result.append(" (roleNames: "); //$NON-NLS-1$
		result.append(roleNames);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //MethodPermissionImpl