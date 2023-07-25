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
package org.eclipse.jst.javaee.ejb.internal.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.jst.javaee.ejb.AccessTimeoutType;
import org.eclipse.jst.javaee.ejb.ConcurrentLockTypeType;
import org.eclipse.jst.javaee.ejb.ConcurrentMethodType;
import org.eclipse.jst.javaee.ejb.NamedMethodType;

import org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Concurrent Method Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.ConcurrentMethodTypeImpl#getMethod <em>Method</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.ConcurrentMethodTypeImpl#getLock <em>Lock</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.ConcurrentMethodTypeImpl#getAccessTimeout <em>Access Timeout</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.ConcurrentMethodTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConcurrentMethodTypeImpl extends EObjectImpl implements ConcurrentMethodType {
	/**
	 * The cached value of the '{@link #getMethod() <em>Method</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethod()
	 * @generated
	 * @ordered
	 */
	protected NamedMethodType method;

	/**
	 * The default value of the '{@link #getLock() <em>Lock</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLock()
	 * @generated
	 * @ordered
	 */
	protected static final ConcurrentLockTypeType LOCK_EDEFAULT = ConcurrentLockTypeType.READ;

	/**
	 * The cached value of the '{@link #getLock() <em>Lock</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLock()
	 * @generated
	 * @ordered
	 */
	protected ConcurrentLockTypeType lock = LOCK_EDEFAULT;

	/**
	 * This is true if the Lock attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean lockESet;

	/**
	 * The cached value of the '{@link #getAccessTimeout() <em>Access Timeout</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAccessTimeout()
	 * @generated
	 * @ordered
	 */
	protected AccessTimeoutType accessTimeout;

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
	protected ConcurrentMethodTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EjbPackage.Literals.CONCURRENT_METHOD_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NamedMethodType getMethod() {
		return method;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMethod(NamedMethodType newMethod, NotificationChain msgs) {
		NamedMethodType oldMethod = method;
		method = newMethod;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.CONCURRENT_METHOD_TYPE__METHOD, oldMethod, newMethod);
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
	public void setMethod(NamedMethodType newMethod) {
		if (newMethod != method) {
			NotificationChain msgs = null;
			if (method != null)
				msgs = ((InternalEObject)method).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.CONCURRENT_METHOD_TYPE__METHOD, null, msgs);
			if (newMethod != null)
				msgs = ((InternalEObject)newMethod).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EjbPackage.CONCURRENT_METHOD_TYPE__METHOD, null, msgs);
			msgs = basicSetMethod(newMethod, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.CONCURRENT_METHOD_TYPE__METHOD, newMethod, newMethod));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ConcurrentLockTypeType getLock() {
		return lock;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLock(ConcurrentLockTypeType newLock) {
		ConcurrentLockTypeType oldLock = lock;
		lock = newLock == null ? LOCK_EDEFAULT : newLock;
		boolean oldLockESet = lockESet;
		lockESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.CONCURRENT_METHOD_TYPE__LOCK, oldLock, lock, !oldLockESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetLock() {
		ConcurrentLockTypeType oldLock = lock;
		boolean oldLockESet = lockESet;
		lock = LOCK_EDEFAULT;
		lockESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, EjbPackage.CONCURRENT_METHOD_TYPE__LOCK, oldLock, LOCK_EDEFAULT, oldLockESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetLock() {
		return lockESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AccessTimeoutType getAccessTimeout() {
		return accessTimeout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAccessTimeout(AccessTimeoutType newAccessTimeout, NotificationChain msgs) {
		AccessTimeoutType oldAccessTimeout = accessTimeout;
		accessTimeout = newAccessTimeout;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.CONCURRENT_METHOD_TYPE__ACCESS_TIMEOUT, oldAccessTimeout, newAccessTimeout);
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
	public void setAccessTimeout(AccessTimeoutType newAccessTimeout) {
		if (newAccessTimeout != accessTimeout) {
			NotificationChain msgs = null;
			if (accessTimeout != null)
				msgs = ((InternalEObject)accessTimeout).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.CONCURRENT_METHOD_TYPE__ACCESS_TIMEOUT, null, msgs);
			if (newAccessTimeout != null)
				msgs = ((InternalEObject)newAccessTimeout).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EjbPackage.CONCURRENT_METHOD_TYPE__ACCESS_TIMEOUT, null, msgs);
			msgs = basicSetAccessTimeout(newAccessTimeout, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.CONCURRENT_METHOD_TYPE__ACCESS_TIMEOUT, newAccessTimeout, newAccessTimeout));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.CONCURRENT_METHOD_TYPE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EjbPackage.CONCURRENT_METHOD_TYPE__METHOD:
				return basicSetMethod(null, msgs);
			case EjbPackage.CONCURRENT_METHOD_TYPE__ACCESS_TIMEOUT:
				return basicSetAccessTimeout(null, msgs);
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
			case EjbPackage.CONCURRENT_METHOD_TYPE__METHOD:
				return getMethod();
			case EjbPackage.CONCURRENT_METHOD_TYPE__LOCK:
				return getLock();
			case EjbPackage.CONCURRENT_METHOD_TYPE__ACCESS_TIMEOUT:
				return getAccessTimeout();
			case EjbPackage.CONCURRENT_METHOD_TYPE__ID:
				return getId();
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
			case EjbPackage.CONCURRENT_METHOD_TYPE__METHOD:
				setMethod((NamedMethodType)newValue);
				return;
			case EjbPackage.CONCURRENT_METHOD_TYPE__LOCK:
				setLock((ConcurrentLockTypeType)newValue);
				return;
			case EjbPackage.CONCURRENT_METHOD_TYPE__ACCESS_TIMEOUT:
				setAccessTimeout((AccessTimeoutType)newValue);
				return;
			case EjbPackage.CONCURRENT_METHOD_TYPE__ID:
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
			case EjbPackage.CONCURRENT_METHOD_TYPE__METHOD:
				setMethod((NamedMethodType)null);
				return;
			case EjbPackage.CONCURRENT_METHOD_TYPE__LOCK:
				unsetLock();
				return;
			case EjbPackage.CONCURRENT_METHOD_TYPE__ACCESS_TIMEOUT:
				setAccessTimeout((AccessTimeoutType)null);
				return;
			case EjbPackage.CONCURRENT_METHOD_TYPE__ID:
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
			case EjbPackage.CONCURRENT_METHOD_TYPE__METHOD:
				return method != null;
			case EjbPackage.CONCURRENT_METHOD_TYPE__LOCK:
				return isSetLock();
			case EjbPackage.CONCURRENT_METHOD_TYPE__ACCESS_TIMEOUT:
				return accessTimeout != null;
			case EjbPackage.CONCURRENT_METHOD_TYPE__ID:
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
		result.append(" (lock: "); //$NON-NLS-1$
		if (lockESet) result.append(lock); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //ConcurrentMethodTypeImpl
