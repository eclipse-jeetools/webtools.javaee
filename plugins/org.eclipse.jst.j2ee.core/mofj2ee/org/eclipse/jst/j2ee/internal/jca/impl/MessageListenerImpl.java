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
package org.eclipse.jst.j2ee.internal.jca.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.jst.j2ee.internal.jca.JcaPackage;
import org.eclipse.jst.j2ee.jca.ActivationSpec;
import org.eclipse.jst.j2ee.jca.MessageListener;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Message Listener</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.internal.jca.impl.MessageListenerImpl#getMessageListenerType <em>Message Listener Type</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.jca.impl.MessageListenerImpl#getActivationSpec <em>Activation Spec</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MessageListenerImpl extends EObjectImpl implements MessageListener {
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
	protected EClass eStaticClass() {
		return JcaPackage.eINSTANCE.getMessageListener();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMessageListenerType() {
		return messageListenerType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
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
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case JcaPackage.MESSAGE_LISTENER__ACTIVATION_SPEC:
					return basicSetActivationSpec(null, msgs);
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
			case JcaPackage.MESSAGE_LISTENER__MESSAGE_LISTENER_TYPE:
				return getMessageListenerType();
			case JcaPackage.MESSAGE_LISTENER__ACTIVATION_SPEC:
				return getActivationSpec();
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
			case JcaPackage.MESSAGE_LISTENER__MESSAGE_LISTENER_TYPE:
				setMessageListenerType((String)newValue);
				return;
			case JcaPackage.MESSAGE_LISTENER__ACTIVATION_SPEC:
				setActivationSpec((ActivationSpec)newValue);
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
			case JcaPackage.MESSAGE_LISTENER__MESSAGE_LISTENER_TYPE:
				setMessageListenerType(MESSAGE_LISTENER_TYPE_EDEFAULT);
				return;
			case JcaPackage.MESSAGE_LISTENER__ACTIVATION_SPEC:
				setActivationSpec((ActivationSpec)null);
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
			case JcaPackage.MESSAGE_LISTENER__MESSAGE_LISTENER_TYPE:
				return MESSAGE_LISTENER_TYPE_EDEFAULT == null ? messageListenerType != null : !MESSAGE_LISTENER_TYPE_EDEFAULT.equals(messageListenerType);
			case JcaPackage.MESSAGE_LISTENER__ACTIVATION_SPEC:
				return activationSpec != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (messageListenerType: ");//$NON-NLS-1$
		result.append(messageListenerType);
		result.append(')');
		return result.toString();
	}

} //MessageListenerImpl
