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
package org.eclipse.jst.j2ee.ejb.internal.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.ejb.AcknowledgeMode;
import org.eclipse.jst.j2ee.ejb.ActivationConfig;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.MessageDriven;
import org.eclipse.jst.j2ee.ejb.MessageDrivenDestination;
import org.eclipse.jst.j2ee.ejb.TransactionType;

/**
 * @generated
 */
public class MessageDrivenImpl extends EnterpriseBeanImpl implements MessageDriven {

	/**
	 * The default value of the '{@link #getTransactionType() <em>Transaction Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransactionType()
	 * @generated
	 * @ordered
	 */
	protected static final TransactionType TRANSACTION_TYPE_EDEFAULT = TransactionType.BEAN_LITERAL;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected TransactionType transactionType = TRANSACTION_TYPE_EDEFAULT;
	/**
	 * This is true if the Transaction Type attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean transactionTypeESet = false;

	/**
	 * The default value of the '{@link #getMessageSelector() <em>Message Selector</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageSelector()
	 * @generated
	 * @ordered
	 */
	protected static final String MESSAGE_SELECTOR_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String messageSelector = MESSAGE_SELECTOR_EDEFAULT;
	/**
	 * The default value of the '{@link #getAcknowledgeMode() <em>Acknowledge Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAcknowledgeMode()
	 * @generated
	 * @ordered
	 */
	protected static final AcknowledgeMode ACKNOWLEDGE_MODE_EDEFAULT = AcknowledgeMode.AUTO_ACKNOWLEDGE_LITERAL;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected AcknowledgeMode acknowledgeMode = ACKNOWLEDGE_MODE_EDEFAULT;
	/**
	 * This is true if the Acknowledge Mode attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean acknowledgeModeESet = false;

	/**
	 * The default value of the '{@link #getLink() <em>Link</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLink()
	 * @generated
	 * @ordered
	 */
	protected static final String LINK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLink() <em>Link</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLink()
	 * @generated
	 * @ordered
	 */
	protected String link = LINK_EDEFAULT;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected MessageDrivenDestination destination = null;
	/**
	 * The cached value of the '{@link #getActivationConfig() <em>Activation Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivationConfig()
	 * @generated
	 * @ordered
	 */
	protected ActivationConfig activationConfig = null;

	/**
	 * The cached value of the '{@link #getMessageDestination() <em>Message Destination</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageDestination()
	 * @generated
	 * @ordered
	 */
	protected JavaClass messageDestination = null;

	/**
	 * The cached value of the '{@link #getMessagingType() <em>Messaging Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessagingType()
	 * @generated
	 * @ordered
	 */
	protected JavaClass messagingType = null;

	public MessageDrivenImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EjbPackage.Literals.MESSAGE_DRIVEN;
	}

@Override
public boolean isMessageDriven() {
	return true;
}
	/**
	 * @generated This field/method will be replaced during code generation 
	 * The transaction-type element specifies an enterprise bean's transaction management type.
	 * 
	 * The transaction-type element must be one of the two following: Bean, Container
	 */
	@Override
	public TransactionType getTransactionType() {
		return transactionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTransactionType(TransactionType newTransactionType) {
		TransactionType oldTransactionType = transactionType;
		transactionType = newTransactionType == null ? TRANSACTION_TYPE_EDEFAULT : newTransactionType;
		boolean oldTransactionTypeESet = transactionTypeESet;
		transactionTypeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.MESSAGE_DRIVEN__TRANSACTION_TYPE, oldTransactionType, transactionType, !oldTransactionTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetTransactionType() {
		TransactionType oldTransactionType = transactionType;
		boolean oldTransactionTypeESet = transactionTypeESet;
		transactionType = TRANSACTION_TYPE_EDEFAULT;
		transactionTypeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, EjbPackage.MESSAGE_DRIVEN__TRANSACTION_TYPE, oldTransactionType, TRANSACTION_TYPE_EDEFAULT, oldTransactionTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetTransactionType() {
		return transactionTypeESet;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The message-selector element is used to specify the JMS message
	 * selector to be used in determining which messages a message-driven
	 * bean is to receive.
	 * 
	 * Example value: 
	 * JMSType = `car' AND color = `blue' AND weight > 2500

	 */
	@Override
	public String getMessageSelector() {
		return messageSelector;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public void setMessageSelector(String newMessageSelector) {
		String oldMessageSelector = messageSelector;
		messageSelector = newMessageSelector;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.MESSAGE_DRIVEN__MESSAGE_SELECTOR, oldMessageSelector, messageSelector));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * an optional declaration of the acknowledgment mode for the message-driven bean if bean-managed transaction demarcation is used (ie. self.transactionType = Bean)
	 * 
	 * The acknowledge-mode specifies whether JMS AUTO_ACKNOWLEDGE or DUPS_OK_ACKNOWLEDGE message acknowledgment semantics should be used for the onMessage message of a message-driven bean that uses bean managed transaction demarcation.
	 * 
	 * The acknowledge-mode must be one of the two following:  Auto-acknowledge, Dups-ok-acknowledge

	 */
	@Override
	public AcknowledgeMode getAcknowledgeMode() {
		return acknowledgeMode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAcknowledgeMode(AcknowledgeMode newAcknowledgeMode) {
		AcknowledgeMode oldAcknowledgeMode = acknowledgeMode;
		acknowledgeMode = newAcknowledgeMode == null ? ACKNOWLEDGE_MODE_EDEFAULT : newAcknowledgeMode;
		boolean oldAcknowledgeModeESet = acknowledgeModeESet;
		acknowledgeModeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.MESSAGE_DRIVEN__ACKNOWLEDGE_MODE, oldAcknowledgeMode, acknowledgeMode, !oldAcknowledgeModeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetAcknowledgeMode() {
		AcknowledgeMode oldAcknowledgeMode = acknowledgeMode;
		boolean oldAcknowledgeModeESet = acknowledgeModeESet;
		acknowledgeMode = ACKNOWLEDGE_MODE_EDEFAULT;
		acknowledgeModeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, EjbPackage.MESSAGE_DRIVEN__ACKNOWLEDGE_MODE, oldAcknowledgeMode, ACKNOWLEDGE_MODE_EDEFAULT, oldAcknowledgeModeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetAcknowledgeMode() {
		return acknowledgeModeESet;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * An optional declaration of the intended destination type of the message-driven bean
	 */
	@Override
	public MessageDrivenDestination getDestination() {
		return destination;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDestination(MessageDrivenDestination newDestination, NotificationChain msgs) {
		MessageDrivenDestination oldDestination = destination;
		destination = newDestination;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.MESSAGE_DRIVEN__DESTINATION, oldDestination, newDestination);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public void setDestination(MessageDrivenDestination newDestination) {
		if (newDestination != destination) {
			NotificationChain msgs = null;
			if (destination != null)
				msgs = ((InternalEObject)destination).eInverseRemove(this, EjbPackage.MESSAGE_DRIVEN_DESTINATION__BEAN, MessageDrivenDestination.class, msgs);
			if (newDestination != null)
				msgs = ((InternalEObject)newDestination).eInverseAdd(this, EjbPackage.MESSAGE_DRIVEN_DESTINATION__BEAN, MessageDrivenDestination.class, msgs);
			msgs = basicSetDestination(newDestination, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.MESSAGE_DRIVEN__DESTINATION, newDestination, newDestination));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ActivationConfig getActivationConfig() {
		return activationConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetActivationConfig(ActivationConfig newActivationConfig, NotificationChain msgs) {
		ActivationConfig oldActivationConfig = activationConfig;
		activationConfig = newActivationConfig;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.MESSAGE_DRIVEN__ACTIVATION_CONFIG, oldActivationConfig, newActivationConfig);
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
	public void setActivationConfig(ActivationConfig newActivationConfig) {
		if (newActivationConfig != activationConfig) {
			NotificationChain msgs = null;
			if (activationConfig != null)
				msgs = ((InternalEObject)activationConfig).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.MESSAGE_DRIVEN__ACTIVATION_CONFIG, null, msgs);
			if (newActivationConfig != null)
				msgs = ((InternalEObject)newActivationConfig).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EjbPackage.MESSAGE_DRIVEN__ACTIVATION_CONFIG, null, msgs);
			msgs = basicSetActivationConfig(newActivationConfig, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.MESSAGE_DRIVEN__ACTIVATION_CONFIG, newActivationConfig, newActivationConfig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public JavaClass getMessageDestination() {
		if (messageDestination != null && messageDestination.eIsProxy()) {
			InternalEObject oldMessageDestination = (InternalEObject)messageDestination;
			messageDestination = (JavaClass)eResolveProxy(oldMessageDestination);
			if (messageDestination != oldMessageDestination) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EjbPackage.MESSAGE_DRIVEN__MESSAGE_DESTINATION, oldMessageDestination, messageDestination));
			}
		}
		return messageDestination;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass basicGetMessageDestination() {
		return messageDestination;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMessageDestination(JavaClass newMessageDestination) {
		JavaClass oldMessageDestination = messageDestination;
		messageDestination = newMessageDestination;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.MESSAGE_DRIVEN__MESSAGE_DESTINATION, oldMessageDestination, messageDestination));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLink() {
		return link;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLink(String newLink) {
		String oldLink = link;
		link = newLink;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.MESSAGE_DRIVEN__LINK, oldLink, link));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public JavaClass getMessagingType() {
		if (messagingType != null && messagingType.eIsProxy()) {
			InternalEObject oldMessagingType = (InternalEObject)messagingType;
			messagingType = (JavaClass)eResolveProxy(oldMessagingType);
			if (messagingType != oldMessagingType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EjbPackage.MESSAGE_DRIVEN__MESSAGING_TYPE, oldMessagingType, messagingType));
			}
		}
		return messagingType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass basicGetMessagingType() {
		return messagingType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMessagingType(JavaClass newMessagingType) {
		JavaClass oldMessagingType = messagingType;
		messagingType = newMessagingType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.MESSAGE_DRIVEN__MESSAGING_TYPE, oldMessagingType, messagingType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EjbPackage.MESSAGE_DRIVEN__DESTINATION:
				if (destination != null)
					msgs = ((InternalEObject)destination).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.MESSAGE_DRIVEN__DESTINATION, null, msgs);
				return basicSetDestination((MessageDrivenDestination)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EjbPackage.MESSAGE_DRIVEN__DESTINATION:
				return basicSetDestination(null, msgs);
			case EjbPackage.MESSAGE_DRIVEN__ACTIVATION_CONFIG:
				return basicSetActivationConfig(null, msgs);
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
			case EjbPackage.MESSAGE_DRIVEN__TRANSACTION_TYPE:
				return getTransactionType();
			case EjbPackage.MESSAGE_DRIVEN__MESSAGE_SELECTOR:
				return getMessageSelector();
			case EjbPackage.MESSAGE_DRIVEN__ACKNOWLEDGE_MODE:
				return getAcknowledgeMode();
			case EjbPackage.MESSAGE_DRIVEN__LINK:
				return getLink();
			case EjbPackage.MESSAGE_DRIVEN__DESTINATION:
				return getDestination();
			case EjbPackage.MESSAGE_DRIVEN__ACTIVATION_CONFIG:
				return getActivationConfig();
			case EjbPackage.MESSAGE_DRIVEN__MESSAGE_DESTINATION:
				if (resolve) return getMessageDestination();
				return basicGetMessageDestination();
			case EjbPackage.MESSAGE_DRIVEN__MESSAGING_TYPE:
				if (resolve) return getMessagingType();
				return basicGetMessagingType();
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
			case EjbPackage.MESSAGE_DRIVEN__TRANSACTION_TYPE:
				setTransactionType((TransactionType)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN__MESSAGE_SELECTOR:
				setMessageSelector((String)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN__ACKNOWLEDGE_MODE:
				setAcknowledgeMode((AcknowledgeMode)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN__LINK:
				setLink((String)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN__DESTINATION:
				setDestination((MessageDrivenDestination)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN__ACTIVATION_CONFIG:
				setActivationConfig((ActivationConfig)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN__MESSAGE_DESTINATION:
				setMessageDestination((JavaClass)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN__MESSAGING_TYPE:
				setMessagingType((JavaClass)newValue);
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
			case EjbPackage.MESSAGE_DRIVEN__TRANSACTION_TYPE:
				unsetTransactionType();
				return;
			case EjbPackage.MESSAGE_DRIVEN__MESSAGE_SELECTOR:
				setMessageSelector(MESSAGE_SELECTOR_EDEFAULT);
				return;
			case EjbPackage.MESSAGE_DRIVEN__ACKNOWLEDGE_MODE:
				unsetAcknowledgeMode();
				return;
			case EjbPackage.MESSAGE_DRIVEN__LINK:
				setLink(LINK_EDEFAULT);
				return;
			case EjbPackage.MESSAGE_DRIVEN__DESTINATION:
				setDestination((MessageDrivenDestination)null);
				return;
			case EjbPackage.MESSAGE_DRIVEN__ACTIVATION_CONFIG:
				setActivationConfig((ActivationConfig)null);
				return;
			case EjbPackage.MESSAGE_DRIVEN__MESSAGE_DESTINATION:
				setMessageDestination((JavaClass)null);
				return;
			case EjbPackage.MESSAGE_DRIVEN__MESSAGING_TYPE:
				setMessagingType((JavaClass)null);
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
			case EjbPackage.MESSAGE_DRIVEN__TRANSACTION_TYPE:
				return isSetTransactionType();
			case EjbPackage.MESSAGE_DRIVEN__MESSAGE_SELECTOR:
				return MESSAGE_SELECTOR_EDEFAULT == null ? messageSelector != null : !MESSAGE_SELECTOR_EDEFAULT.equals(messageSelector);
			case EjbPackage.MESSAGE_DRIVEN__ACKNOWLEDGE_MODE:
				return isSetAcknowledgeMode();
			case EjbPackage.MESSAGE_DRIVEN__LINK:
				return LINK_EDEFAULT == null ? link != null : !LINK_EDEFAULT.equals(link);
			case EjbPackage.MESSAGE_DRIVEN__DESTINATION:
				return destination != null;
			case EjbPackage.MESSAGE_DRIVEN__ACTIVATION_CONFIG:
				return activationConfig != null;
			case EjbPackage.MESSAGE_DRIVEN__MESSAGE_DESTINATION:
				return messageDestination != null;
			case EjbPackage.MESSAGE_DRIVEN__MESSAGING_TYPE:
				return messagingType != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (transactionType: "); //$NON-NLS-1$
		if (transactionTypeESet) result.append(transactionType); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", messageSelector: "); //$NON-NLS-1$
		result.append(messageSelector);
		result.append(", acknowledgeMode: "); //$NON-NLS-1$
		if (acknowledgeModeESet) result.append(acknowledgeMode); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", link: "); //$NON-NLS-1$
		result.append(link);
		result.append(')');
		return result.toString();
	}

	/*
	 * @see EnterpriseBean#getAvailableLocalHomeMethodElements()
	 */
	@Override
	public List getAvailableLocalHomeMethodElements() {
		return Collections.EMPTY_LIST;
	}

	/*
	 * @see EnterpriseBean#getAvailableLocalMethodElements()
	 */
	@Override
	public List getAvailableLocalMethodElements() {
		return Collections.EMPTY_LIST;
	}

	/*
	 * @see EnterpriseBean#getAvailableRemoteMethodElements()
	 */
	@Override
	public List getAvailableRemoteMethodElements() {
		return Collections.EMPTY_LIST;
	}

	/*
	 * @see EnterpriseBeanImpl#getAvailableUnspecifiedMethodElementSignatures()
	 */
	@Override
	public List getAvailableUnspecifiedMethodElementSignatures() {
		//There is only the onMessage method
		List sigs = new ArrayList(1);
		sigs.add("onMessage(javax.jms.Message)"); //$NON-NLS-1$
		return sigs;
	}

}





