/*******************************************************************************
 * Copyright (c) 2009, 2023 IBM Corporation and others.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/**
 * <copyright>
 * </copyright>
 *
 * $Id: OrderingOrderingTypeImpl.java,v 1.1 2009/10/15 18:52:05 canderson Exp $
 */
package org.eclipse.jst.javaee.web.internal.impl;

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

import org.eclipse.jst.javaee.web.OrderingOrderingType;
import org.eclipse.jst.javaee.web.OrderingOthersType;

import org.eclipse.jst.javaee.web.internal.metadata.WebPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Ordering Ordering Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.OrderingOrderingTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.OrderingOrderingTypeImpl#getOthers <em>Others</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OrderingOrderingTypeImpl extends EObjectImpl implements OrderingOrderingType {
	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected EList<String> name;

	/**
	 * The cached value of the '{@link #getOthers() <em>Others</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOthers()
	 * @generated
	 * @ordered
	 */
	protected OrderingOthersType others;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OrderingOrderingTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WebPackage.Literals.ORDERING_ORDERING_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<String> getName() {
		if (name == null) {
			name = new EDataTypeEList<String>(String.class, this, WebPackage.ORDERING_ORDERING_TYPE__NAME);
		}
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OrderingOthersType getOthers() {
		return others;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOthers(OrderingOthersType newOthers, NotificationChain msgs) {
		OrderingOthersType oldOthers = others;
		others = newOthers;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WebPackage.ORDERING_ORDERING_TYPE__OTHERS, oldOthers, newOthers);
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
	public void setOthers(OrderingOthersType newOthers) {
		if (newOthers != others) {
			NotificationChain msgs = null;
			if (others != null)
				msgs = ((InternalEObject)others).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WebPackage.ORDERING_ORDERING_TYPE__OTHERS, null, msgs);
			if (newOthers != null)
				msgs = ((InternalEObject)newOthers).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - WebPackage.ORDERING_ORDERING_TYPE__OTHERS, null, msgs);
			msgs = basicSetOthers(newOthers, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.ORDERING_ORDERING_TYPE__OTHERS, newOthers, newOthers));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WebPackage.ORDERING_ORDERING_TYPE__OTHERS:
				return basicSetOthers(null, msgs);
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
			case WebPackage.ORDERING_ORDERING_TYPE__NAME:
				return getName();
			case WebPackage.ORDERING_ORDERING_TYPE__OTHERS:
				return getOthers();
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
			case WebPackage.ORDERING_ORDERING_TYPE__NAME:
				getName().clear();
				getName().addAll((Collection<? extends String>)newValue);
				return;
			case WebPackage.ORDERING_ORDERING_TYPE__OTHERS:
				setOthers((OrderingOthersType)newValue);
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
			case WebPackage.ORDERING_ORDERING_TYPE__NAME:
				getName().clear();
				return;
			case WebPackage.ORDERING_ORDERING_TYPE__OTHERS:
				setOthers((OrderingOthersType)null);
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
			case WebPackage.ORDERING_ORDERING_TYPE__NAME:
				return name != null && !name.isEmpty();
			case WebPackage.ORDERING_ORDERING_TYPE__OTHERS:
				return others != null;
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
		result.append(" (name: "); //$NON-NLS-1$
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //OrderingOrderingTypeImpl
