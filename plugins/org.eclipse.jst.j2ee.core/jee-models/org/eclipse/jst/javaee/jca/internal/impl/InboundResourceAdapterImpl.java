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
package org.eclipse.jst.javaee.jca.internal.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.jst.javaee.jca.InboundResourceAdapter;
import org.eclipse.jst.javaee.jca.MessageAdapter;

import org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Inbound Resource Adapter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.InboundResourceAdapterImpl#getMessageadapter <em>Messageadapter</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.InboundResourceAdapterImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InboundResourceAdapterImpl extends EObjectImpl implements InboundResourceAdapter {
	/**
	 * The cached value of the '{@link #getMessageadapter() <em>Messageadapter</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageadapter()
	 * @generated
	 * @ordered
	 */
	protected MessageAdapter messageadapter;

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
	protected InboundResourceAdapterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JcaPackage.Literals.INBOUND_RESOURCE_ADAPTER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MessageAdapter getMessageadapter() {
		return messageadapter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMessageadapter(MessageAdapter newMessageadapter, NotificationChain msgs) {
		MessageAdapter oldMessageadapter = messageadapter;
		messageadapter = newMessageadapter;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JcaPackage.INBOUND_RESOURCE_ADAPTER__MESSAGEADAPTER, oldMessageadapter, newMessageadapter);
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
	public void setMessageadapter(MessageAdapter newMessageadapter) {
		if (newMessageadapter != messageadapter) {
			NotificationChain msgs = null;
			if (messageadapter != null)
				msgs = ((InternalEObject)messageadapter).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JcaPackage.INBOUND_RESOURCE_ADAPTER__MESSAGEADAPTER, null, msgs);
			if (newMessageadapter != null)
				msgs = ((InternalEObject)newMessageadapter).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JcaPackage.INBOUND_RESOURCE_ADAPTER__MESSAGEADAPTER, null, msgs);
			msgs = basicSetMessageadapter(newMessageadapter, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.INBOUND_RESOURCE_ADAPTER__MESSAGEADAPTER, newMessageadapter, newMessageadapter));
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
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.INBOUND_RESOURCE_ADAPTER__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JcaPackage.INBOUND_RESOURCE_ADAPTER__MESSAGEADAPTER:
				return basicSetMessageadapter(null, msgs);
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
			case JcaPackage.INBOUND_RESOURCE_ADAPTER__MESSAGEADAPTER:
				return getMessageadapter();
			case JcaPackage.INBOUND_RESOURCE_ADAPTER__ID:
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
			case JcaPackage.INBOUND_RESOURCE_ADAPTER__MESSAGEADAPTER:
				setMessageadapter((MessageAdapter)newValue);
				return;
			case JcaPackage.INBOUND_RESOURCE_ADAPTER__ID:
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
			case JcaPackage.INBOUND_RESOURCE_ADAPTER__MESSAGEADAPTER:
				setMessageadapter((MessageAdapter)null);
				return;
			case JcaPackage.INBOUND_RESOURCE_ADAPTER__ID:
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
			case JcaPackage.INBOUND_RESOURCE_ADAPTER__MESSAGEADAPTER:
				return messageadapter != null;
			case JcaPackage.INBOUND_RESOURCE_ADAPTER__ID:
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

} //InboundResourceAdapterImpl
