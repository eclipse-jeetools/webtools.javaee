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

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jst.javaee.core.Description;

import org.eclipse.jst.javaee.web.TransportGuaranteeType;
import org.eclipse.jst.javaee.web.UserDataConstraint;

import org.eclipse.jst.javaee.web.internal.metadata.WebPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>User Data Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.UserDataConstraintImpl#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.UserDataConstraintImpl#getTransportGuarantee <em>Transport Guarantee</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.UserDataConstraintImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UserDataConstraintImpl extends EObjectImpl implements UserDataConstraint {
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
	 * The default value of the '{@link #getTransportGuarantee() <em>Transport Guarantee</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransportGuarantee()
	 * @generated
	 * @ordered
	 */
	protected static final TransportGuaranteeType TRANSPORT_GUARANTEE_EDEFAULT = TransportGuaranteeType.NONE_LITERAL;

	/**
	 * The cached value of the '{@link #getTransportGuarantee() <em>Transport Guarantee</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransportGuarantee()
	 * @generated
	 * @ordered
	 */
	protected TransportGuaranteeType transportGuarantee = TRANSPORT_GUARANTEE_EDEFAULT;

	/**
	 * This is true if the Transport Guarantee attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean transportGuaranteeESet;

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
	protected UserDataConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WebPackage.Literals.USER_DATA_CONSTRAINT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Description> getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList<Description>(Description.class, this, WebPackage.USER_DATA_CONSTRAINT__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TransportGuaranteeType getTransportGuarantee() {
		return transportGuarantee;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTransportGuarantee(TransportGuaranteeType newTransportGuarantee) {
		TransportGuaranteeType oldTransportGuarantee = transportGuarantee;
		transportGuarantee = newTransportGuarantee == null ? TRANSPORT_GUARANTEE_EDEFAULT : newTransportGuarantee;
		boolean oldTransportGuaranteeESet = transportGuaranteeESet;
		transportGuaranteeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.USER_DATA_CONSTRAINT__TRANSPORT_GUARANTEE, oldTransportGuarantee, transportGuarantee, !oldTransportGuaranteeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetTransportGuarantee() {
		TransportGuaranteeType oldTransportGuarantee = transportGuarantee;
		boolean oldTransportGuaranteeESet = transportGuaranteeESet;
		transportGuarantee = TRANSPORT_GUARANTEE_EDEFAULT;
		transportGuaranteeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, WebPackage.USER_DATA_CONSTRAINT__TRANSPORT_GUARANTEE, oldTransportGuarantee, TRANSPORT_GUARANTEE_EDEFAULT, oldTransportGuaranteeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetTransportGuarantee() {
		return transportGuaranteeESet;
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
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.USER_DATA_CONSTRAINT__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WebPackage.USER_DATA_CONSTRAINT__DESCRIPTIONS:
				return ((InternalEList<?>)getDescriptions()).basicRemove(otherEnd, msgs);
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
			case WebPackage.USER_DATA_CONSTRAINT__DESCRIPTIONS:
				return getDescriptions();
			case WebPackage.USER_DATA_CONSTRAINT__TRANSPORT_GUARANTEE:
				return getTransportGuarantee();
			case WebPackage.USER_DATA_CONSTRAINT__ID:
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
			case WebPackage.USER_DATA_CONSTRAINT__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection<? extends Description>)newValue);
				return;
			case WebPackage.USER_DATA_CONSTRAINT__TRANSPORT_GUARANTEE:
				setTransportGuarantee((TransportGuaranteeType)newValue);
				return;
			case WebPackage.USER_DATA_CONSTRAINT__ID:
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
			case WebPackage.USER_DATA_CONSTRAINT__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case WebPackage.USER_DATA_CONSTRAINT__TRANSPORT_GUARANTEE:
				unsetTransportGuarantee();
				return;
			case WebPackage.USER_DATA_CONSTRAINT__ID:
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
			case WebPackage.USER_DATA_CONSTRAINT__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case WebPackage.USER_DATA_CONSTRAINT__TRANSPORT_GUARANTEE:
				return isSetTransportGuarantee();
			case WebPackage.USER_DATA_CONSTRAINT__ID:
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
		result.append(" (transportGuarantee: "); //$NON-NLS-1$
		if (transportGuaranteeESet) result.append(transportGuarantee); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //UserDataConstraintImpl