/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.jca.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
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
 *   <li>{@link org.eclipse.jst.j2ee.internal.jca.impl.InboundResourceAdapterImpl#getMessageAdapter <em>Message Adapter</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InboundResourceAdapterImpl extends EObjectImpl implements InboundResourceAdapter {
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
	protected EClass eStaticClass() {
		return JcaPackage.eINSTANCE.getInboundResourceAdapter();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
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
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case JcaPackage.INBOUND_RESOURCE_ADAPTER__MESSAGE_ADAPTER:
					return basicSetMessageAdapter(null, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JcaPackage.INBOUND_RESOURCE_ADAPTER__MESSAGE_ADAPTER:
				return getMessageAdapter();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JcaPackage.INBOUND_RESOURCE_ADAPTER__MESSAGE_ADAPTER:
				setMessageAdapter((MessageAdapter)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JcaPackage.INBOUND_RESOURCE_ADAPTER__MESSAGE_ADAPTER:
				setMessageAdapter((MessageAdapter)null);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JcaPackage.INBOUND_RESOURCE_ADAPTER__MESSAGE_ADAPTER:
				return messageAdapter != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //InboundResourceAdapterImpl
