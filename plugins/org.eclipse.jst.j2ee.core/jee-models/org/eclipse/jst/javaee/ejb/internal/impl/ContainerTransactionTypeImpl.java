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

import org.eclipse.jst.javaee.ejb.ContainerTransactionType;
import org.eclipse.jst.javaee.ejb.MethodType;
import org.eclipse.jst.javaee.ejb.TransactionAttributeType;

import org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Container Transaction Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.ContainerTransactionTypeImpl#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.ContainerTransactionTypeImpl#getMethods <em>Methods</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.ContainerTransactionTypeImpl#getTransAttribute <em>Trans Attribute</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.ContainerTransactionTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ContainerTransactionTypeImpl extends EObjectImpl implements ContainerTransactionType {
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
	 * The cached value of the '{@link #getMethods() <em>Methods</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethods()
	 * @generated
	 * @ordered
	 */
	protected EList<MethodType> methods;

	/**
	 * The default value of the '{@link #getTransAttribute() <em>Trans Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransAttribute()
	 * @generated
	 * @ordered
	 */
	protected static final TransactionAttributeType TRANS_ATTRIBUTE_EDEFAULT = TransactionAttributeType.NOT_SUPPORTED_LITERAL;

	/**
	 * The cached value of the '{@link #getTransAttribute() <em>Trans Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransAttribute()
	 * @generated
	 * @ordered
	 */
	protected TransactionAttributeType transAttribute = TRANS_ATTRIBUTE_EDEFAULT;

	/**
	 * This is true if the Trans Attribute attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean transAttributeESet;

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
	protected ContainerTransactionTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EjbPackage.Literals.CONTAINER_TRANSACTION_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Description> getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList<Description>(Description.class, this, EjbPackage.CONTAINER_TRANSACTION_TYPE__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<MethodType> getMethods() {
		if (methods == null) {
			methods = new EObjectContainmentEList<MethodType>(MethodType.class, this, EjbPackage.CONTAINER_TRANSACTION_TYPE__METHODS);
		}
		return methods;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TransactionAttributeType getTransAttribute() {
		return transAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTransAttribute(TransactionAttributeType newTransAttribute) {
		TransactionAttributeType oldTransAttribute = transAttribute;
		transAttribute = newTransAttribute == null ? TRANS_ATTRIBUTE_EDEFAULT : newTransAttribute;
		boolean oldTransAttributeESet = transAttributeESet;
		transAttributeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.CONTAINER_TRANSACTION_TYPE__TRANS_ATTRIBUTE, oldTransAttribute, transAttribute, !oldTransAttributeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetTransAttribute() {
		TransactionAttributeType oldTransAttribute = transAttribute;
		boolean oldTransAttributeESet = transAttributeESet;
		transAttribute = TRANS_ATTRIBUTE_EDEFAULT;
		transAttributeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, EjbPackage.CONTAINER_TRANSACTION_TYPE__TRANS_ATTRIBUTE, oldTransAttribute, TRANS_ATTRIBUTE_EDEFAULT, oldTransAttributeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetTransAttribute() {
		return transAttributeESet;
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
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.CONTAINER_TRANSACTION_TYPE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EjbPackage.CONTAINER_TRANSACTION_TYPE__DESCRIPTIONS:
				return ((InternalEList<?>)getDescriptions()).basicRemove(otherEnd, msgs);
			case EjbPackage.CONTAINER_TRANSACTION_TYPE__METHODS:
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
			case EjbPackage.CONTAINER_TRANSACTION_TYPE__DESCRIPTIONS:
				return getDescriptions();
			case EjbPackage.CONTAINER_TRANSACTION_TYPE__METHODS:
				return getMethods();
			case EjbPackage.CONTAINER_TRANSACTION_TYPE__TRANS_ATTRIBUTE:
				return getTransAttribute();
			case EjbPackage.CONTAINER_TRANSACTION_TYPE__ID:
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
			case EjbPackage.CONTAINER_TRANSACTION_TYPE__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection<? extends Description>)newValue);
				return;
			case EjbPackage.CONTAINER_TRANSACTION_TYPE__METHODS:
				getMethods().clear();
				getMethods().addAll((Collection<? extends MethodType>)newValue);
				return;
			case EjbPackage.CONTAINER_TRANSACTION_TYPE__TRANS_ATTRIBUTE:
				setTransAttribute((TransactionAttributeType)newValue);
				return;
			case EjbPackage.CONTAINER_TRANSACTION_TYPE__ID:
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
			case EjbPackage.CONTAINER_TRANSACTION_TYPE__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case EjbPackage.CONTAINER_TRANSACTION_TYPE__METHODS:
				getMethods().clear();
				return;
			case EjbPackage.CONTAINER_TRANSACTION_TYPE__TRANS_ATTRIBUTE:
				unsetTransAttribute();
				return;
			case EjbPackage.CONTAINER_TRANSACTION_TYPE__ID:
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
			case EjbPackage.CONTAINER_TRANSACTION_TYPE__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case EjbPackage.CONTAINER_TRANSACTION_TYPE__METHODS:
				return methods != null && !methods.isEmpty();
			case EjbPackage.CONTAINER_TRANSACTION_TYPE__TRANS_ATTRIBUTE:
				return isSetTransAttribute();
			case EjbPackage.CONTAINER_TRANSACTION_TYPE__ID:
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
		result.append(" (transAttribute: "); //$NON-NLS-1$
		if (transAttributeESet) result.append(transAttribute); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //ContainerTransactionTypeImpl