/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
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

import org.eclipse.jst.javaee.jca.ActivationSpec;
import org.eclipse.jst.javaee.jca.MessageListener;

import org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Message Listener</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.MessageListenerImpl#getMessagelistenerType <em>Messagelistener Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.MessageListenerImpl#getActivationspec <em>Activationspec</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.MessageListenerImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MessageListenerImpl extends EObjectImpl implements MessageListener {
	/**
	 * The default value of the '{@link #getMessagelistenerType() <em>Messagelistener Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessagelistenerType()
	 * @generated
	 * @ordered
	 */
	protected static final String MESSAGELISTENER_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMessagelistenerType() <em>Messagelistener Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessagelistenerType()
	 * @generated
	 * @ordered
	 */
	protected String messagelistenerType = MESSAGELISTENER_TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getActivationspec() <em>Activationspec</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivationspec()
	 * @generated
	 * @ordered
	 */
	protected ActivationSpec activationspec;

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
	protected MessageListenerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JcaPackage.Literals.MESSAGE_LISTENER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMessagelistenerType() {
		return messagelistenerType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMessagelistenerType(String newMessagelistenerType) {
		String oldMessagelistenerType = messagelistenerType;
		messagelistenerType = newMessagelistenerType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.MESSAGE_LISTENER__MESSAGELISTENER_TYPE, oldMessagelistenerType, messagelistenerType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivationSpec getActivationspec() {
		return activationspec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetActivationspec(ActivationSpec newActivationspec, NotificationChain msgs) {
		ActivationSpec oldActivationspec = activationspec;
		activationspec = newActivationspec;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JcaPackage.MESSAGE_LISTENER__ACTIVATIONSPEC, oldActivationspec, newActivationspec);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActivationspec(ActivationSpec newActivationspec) {
		if (newActivationspec != activationspec) {
			NotificationChain msgs = null;
			if (activationspec != null)
				msgs = ((InternalEObject)activationspec).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JcaPackage.MESSAGE_LISTENER__ACTIVATIONSPEC, null, msgs);
			if (newActivationspec != null)
				msgs = ((InternalEObject)newActivationspec).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JcaPackage.MESSAGE_LISTENER__ACTIVATIONSPEC, null, msgs);
			msgs = basicSetActivationspec(newActivationspec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.MESSAGE_LISTENER__ACTIVATIONSPEC, newActivationspec, newActivationspec));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.MESSAGE_LISTENER__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JcaPackage.MESSAGE_LISTENER__ACTIVATIONSPEC:
				return basicSetActivationspec(null, msgs);
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
			case JcaPackage.MESSAGE_LISTENER__MESSAGELISTENER_TYPE:
				return getMessagelistenerType();
			case JcaPackage.MESSAGE_LISTENER__ACTIVATIONSPEC:
				return getActivationspec();
			case JcaPackage.MESSAGE_LISTENER__ID:
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
			case JcaPackage.MESSAGE_LISTENER__MESSAGELISTENER_TYPE:
				setMessagelistenerType((String)newValue);
				return;
			case JcaPackage.MESSAGE_LISTENER__ACTIVATIONSPEC:
				setActivationspec((ActivationSpec)newValue);
				return;
			case JcaPackage.MESSAGE_LISTENER__ID:
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
			case JcaPackage.MESSAGE_LISTENER__MESSAGELISTENER_TYPE:
				setMessagelistenerType(MESSAGELISTENER_TYPE_EDEFAULT);
				return;
			case JcaPackage.MESSAGE_LISTENER__ACTIVATIONSPEC:
				setActivationspec((ActivationSpec)null);
				return;
			case JcaPackage.MESSAGE_LISTENER__ID:
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
			case JcaPackage.MESSAGE_LISTENER__MESSAGELISTENER_TYPE:
				return MESSAGELISTENER_TYPE_EDEFAULT == null ? messagelistenerType != null : !MESSAGELISTENER_TYPE_EDEFAULT.equals(messagelistenerType);
			case JcaPackage.MESSAGE_LISTENER__ACTIVATIONSPEC:
				return activationspec != null;
			case JcaPackage.MESSAGE_LISTENER__ID:
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
		result.append(" (messagelistenerType: "); //$NON-NLS-1$
		result.append(messagelistenerType);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //MessageListenerImpl
