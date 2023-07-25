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
import org.eclipse.jst.j2ee.jca.ActivationSpec;
import org.eclipse.jst.j2ee.jca.JcaPackage;
import org.eclipse.jst.j2ee.jca.MessageListener;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Message Listener</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.jca.internal.impl.MessageListenerImpl#getMessageListenerType <em>Message Listener Type</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.jca.internal.impl.MessageListenerImpl#getActivationSpec <em>Activation Spec</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MessageListenerImpl extends J2EEEObjectImpl implements MessageListener {
	/**
	 * The default value of the '{@link #getMessageListenerType() <em>Message Listener Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageListenerType()
	 * @generated
	 * @ordered
	 */
	protected static final String MESSAGE_LISTENER_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMessageListenerType() <em>Message Listener Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageListenerType()
	 * @generated
	 * @ordered
	 */
	protected String messageListenerType = MESSAGE_LISTENER_TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getActivationSpec() <em>Activation Spec</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivationSpec()
	 * @generated
	 * @ordered
	 */
	protected ActivationSpec activationSpec = null;

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
	@Override
	public String getMessageListenerType() {
		return messageListenerType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMessageListenerType(String newMessageListenerType) {
		String oldMessageListenerType = messageListenerType;
		messageListenerType = newMessageListenerType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.MESSAGE_LISTENER__MESSAGE_LISTENER_TYPE, oldMessageListenerType, messageListenerType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ActivationSpec getActivationSpec() {
		return activationSpec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetActivationSpec(ActivationSpec newActivationSpec, NotificationChain msgs) {
		ActivationSpec oldActivationSpec = activationSpec;
		activationSpec = newActivationSpec;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JcaPackage.MESSAGE_LISTENER__ACTIVATION_SPEC, oldActivationSpec, newActivationSpec);
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
	public void setActivationSpec(ActivationSpec newActivationSpec) {
		if (newActivationSpec != activationSpec) {
			NotificationChain msgs = null;
			if (activationSpec != null)
				msgs = ((InternalEObject)activationSpec).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JcaPackage.MESSAGE_LISTENER__ACTIVATION_SPEC, null, msgs);
			if (newActivationSpec != null)
				msgs = ((InternalEObject)newActivationSpec).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JcaPackage.MESSAGE_LISTENER__ACTIVATION_SPEC, null, msgs);
			msgs = basicSetActivationSpec(newActivationSpec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.MESSAGE_LISTENER__ACTIVATION_SPEC, newActivationSpec, newActivationSpec));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JcaPackage.MESSAGE_LISTENER__ACTIVATION_SPEC:
				return basicSetActivationSpec(null, msgs);
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
			case JcaPackage.MESSAGE_LISTENER__MESSAGE_LISTENER_TYPE:
				return getMessageListenerType();
			case JcaPackage.MESSAGE_LISTENER__ACTIVATION_SPEC:
				return getActivationSpec();
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
			case JcaPackage.MESSAGE_LISTENER__MESSAGE_LISTENER_TYPE:
				setMessageListenerType((String)newValue);
				return;
			case JcaPackage.MESSAGE_LISTENER__ACTIVATION_SPEC:
				setActivationSpec((ActivationSpec)newValue);
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
			case JcaPackage.MESSAGE_LISTENER__MESSAGE_LISTENER_TYPE:
				setMessageListenerType(MESSAGE_LISTENER_TYPE_EDEFAULT);
				return;
			case JcaPackage.MESSAGE_LISTENER__ACTIVATION_SPEC:
				setActivationSpec((ActivationSpec)null);
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
			case JcaPackage.MESSAGE_LISTENER__MESSAGE_LISTENER_TYPE:
				return MESSAGE_LISTENER_TYPE_EDEFAULT == null ? messageListenerType != null : !MESSAGE_LISTENER_TYPE_EDEFAULT.equals(messageListenerType);
			case JcaPackage.MESSAGE_LISTENER__ACTIVATION_SPEC:
				return activationSpec != null;
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
		result.append(" (messageListenerType: "); //$NON-NLS-1$
		result.append(messageListenerType);
		result.append(')');
		return result.toString();
	}

} //MessageListenerImpl
