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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jst.j2ee.common.internal.impl.J2EEEObjectImpl;
import org.eclipse.jst.j2ee.jca.InboundResourceAdapter;
import org.eclipse.jst.j2ee.jca.JcaPackage;
import org.eclipse.jst.j2ee.jca.MessageAdapter;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Inbound Resource Adapter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.jca.internal.impl.InboundResourceAdapterImpl#getMessageAdapter <em>Message Adapter</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InboundResourceAdapterImpl extends J2EEEObjectImpl implements InboundResourceAdapter {
	/**
	 * The cached value of the '{@link #getMessageAdapter() <em>Message Adapter</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageAdapter()
	 * @generated
	 * @ordered
	 */
	protected MessageAdapter messageAdapter = null;

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
	public MessageAdapter getMessageAdapter() {
		return messageAdapter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMessageAdapter(MessageAdapter newMessageAdapter, NotificationChain msgs) {
		MessageAdapter oldMessageAdapter = messageAdapter;
		messageAdapter = newMessageAdapter;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JcaPackage.INBOUND_RESOURCE_ADAPTER__MESSAGE_ADAPTER, oldMessageAdapter, newMessageAdapter);
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
	public void setMessageAdapter(MessageAdapter newMessageAdapter) {
		if (newMessageAdapter != messageAdapter) {
			NotificationChain msgs = null;
			if (messageAdapter != null)
				msgs = ((InternalEObject)messageAdapter).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JcaPackage.INBOUND_RESOURCE_ADAPTER__MESSAGE_ADAPTER, null, msgs);
			if (newMessageAdapter != null)
				msgs = ((InternalEObject)newMessageAdapter).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JcaPackage.INBOUND_RESOURCE_ADAPTER__MESSAGE_ADAPTER, null, msgs);
			msgs = basicSetMessageAdapter(newMessageAdapter, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.INBOUND_RESOURCE_ADAPTER__MESSAGE_ADAPTER, newMessageAdapter, newMessageAdapter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JcaPackage.INBOUND_RESOURCE_ADAPTER__MESSAGE_ADAPTER:
				return basicSetMessageAdapter(null, msgs);
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
			case JcaPackage.INBOUND_RESOURCE_ADAPTER__MESSAGE_ADAPTER:
				return getMessageAdapter();
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
			case JcaPackage.INBOUND_RESOURCE_ADAPTER__MESSAGE_ADAPTER:
				setMessageAdapter((MessageAdapter)newValue);
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
			case JcaPackage.INBOUND_RESOURCE_ADAPTER__MESSAGE_ADAPTER:
				setMessageAdapter((MessageAdapter)null);
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
			case JcaPackage.INBOUND_RESOURCE_ADAPTER__MESSAGE_ADAPTER:
				return messageAdapter != null;
		}
		return super.eIsSet(featureID);
	}

} //InboundResourceAdapterImpl
