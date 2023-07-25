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

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.EmptyType;
import org.eclipse.jst.javaee.core.RunAs;

import org.eclipse.jst.javaee.ejb.SecurityIdentityType;

import org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Security Identity Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SecurityIdentityTypeImpl#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SecurityIdentityTypeImpl#getUseCallerIdentity <em>Use Caller Identity</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SecurityIdentityTypeImpl#getRunAs <em>Run As</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SecurityIdentityTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SecurityIdentityTypeImpl extends EObjectImpl implements SecurityIdentityType {
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
	 * The cached value of the '{@link #getUseCallerIdentity() <em>Use Caller Identity</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUseCallerIdentity()
	 * @generated
	 * @ordered
	 */
	protected EmptyType useCallerIdentity;

	/**
	 * The cached value of the '{@link #getRunAs() <em>Run As</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRunAs()
	 * @generated
	 * @ordered
	 */
	protected RunAs runAs;

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
	protected SecurityIdentityTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EjbPackage.Literals.SECURITY_IDENTITY_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Description> getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList<Description>(Description.class, this, EjbPackage.SECURITY_IDENTITY_TYPE__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EmptyType getUseCallerIdentity() {
		return useCallerIdentity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUseCallerIdentity(EmptyType newUseCallerIdentity, NotificationChain msgs) {
		EmptyType oldUseCallerIdentity = useCallerIdentity;
		useCallerIdentity = newUseCallerIdentity;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.SECURITY_IDENTITY_TYPE__USE_CALLER_IDENTITY, oldUseCallerIdentity, newUseCallerIdentity);
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
	public void setUseCallerIdentity(EmptyType newUseCallerIdentity) {
		if (newUseCallerIdentity != useCallerIdentity) {
			NotificationChain msgs = null;
			if (useCallerIdentity != null)
				msgs = ((InternalEObject)useCallerIdentity).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.SECURITY_IDENTITY_TYPE__USE_CALLER_IDENTITY, null, msgs);
			if (newUseCallerIdentity != null)
				msgs = ((InternalEObject)newUseCallerIdentity).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EjbPackage.SECURITY_IDENTITY_TYPE__USE_CALLER_IDENTITY, null, msgs);
			msgs = basicSetUseCallerIdentity(newUseCallerIdentity, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SECURITY_IDENTITY_TYPE__USE_CALLER_IDENTITY, newUseCallerIdentity, newUseCallerIdentity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RunAs getRunAs() {
		return runAs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRunAs(RunAs newRunAs, NotificationChain msgs) {
		RunAs oldRunAs = runAs;
		runAs = newRunAs;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.SECURITY_IDENTITY_TYPE__RUN_AS, oldRunAs, newRunAs);
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
	public void setRunAs(RunAs newRunAs) {
		if (newRunAs != runAs) {
			NotificationChain msgs = null;
			if (runAs != null)
				msgs = ((InternalEObject)runAs).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.SECURITY_IDENTITY_TYPE__RUN_AS, null, msgs);
			if (newRunAs != null)
				msgs = ((InternalEObject)newRunAs).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EjbPackage.SECURITY_IDENTITY_TYPE__RUN_AS, null, msgs);
			msgs = basicSetRunAs(newRunAs, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SECURITY_IDENTITY_TYPE__RUN_AS, newRunAs, newRunAs));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SECURITY_IDENTITY_TYPE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EjbPackage.SECURITY_IDENTITY_TYPE__DESCRIPTIONS:
				return ((InternalEList<?>)getDescriptions()).basicRemove(otherEnd, msgs);
			case EjbPackage.SECURITY_IDENTITY_TYPE__USE_CALLER_IDENTITY:
				return basicSetUseCallerIdentity(null, msgs);
			case EjbPackage.SECURITY_IDENTITY_TYPE__RUN_AS:
				return basicSetRunAs(null, msgs);
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
			case EjbPackage.SECURITY_IDENTITY_TYPE__DESCRIPTIONS:
				return getDescriptions();
			case EjbPackage.SECURITY_IDENTITY_TYPE__USE_CALLER_IDENTITY:
				return getUseCallerIdentity();
			case EjbPackage.SECURITY_IDENTITY_TYPE__RUN_AS:
				return getRunAs();
			case EjbPackage.SECURITY_IDENTITY_TYPE__ID:
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
			case EjbPackage.SECURITY_IDENTITY_TYPE__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection<? extends Description>)newValue);
				return;
			case EjbPackage.SECURITY_IDENTITY_TYPE__USE_CALLER_IDENTITY:
				setUseCallerIdentity((EmptyType)newValue);
				return;
			case EjbPackage.SECURITY_IDENTITY_TYPE__RUN_AS:
				setRunAs((RunAs)newValue);
				return;
			case EjbPackage.SECURITY_IDENTITY_TYPE__ID:
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
			case EjbPackage.SECURITY_IDENTITY_TYPE__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case EjbPackage.SECURITY_IDENTITY_TYPE__USE_CALLER_IDENTITY:
				setUseCallerIdentity((EmptyType)null);
				return;
			case EjbPackage.SECURITY_IDENTITY_TYPE__RUN_AS:
				setRunAs((RunAs)null);
				return;
			case EjbPackage.SECURITY_IDENTITY_TYPE__ID:
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
			case EjbPackage.SECURITY_IDENTITY_TYPE__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case EjbPackage.SECURITY_IDENTITY_TYPE__USE_CALLER_IDENTITY:
				return useCallerIdentity != null;
			case EjbPackage.SECURITY_IDENTITY_TYPE__RUN_AS:
				return runAs != null;
			case EjbPackage.SECURITY_IDENTITY_TYPE__ID:
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
		result.append(" (id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //SecurityIdentityTypeImpl