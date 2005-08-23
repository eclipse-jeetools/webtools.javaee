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
package org.eclipse.jst.j2ee.ejb.internal.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.common.SecurityIdentity;
import org.eclipse.jst.j2ee.ejb.AcknowledgeMode;
import org.eclipse.jst.j2ee.ejb.ActivationConfig;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.MessageDriven;
import org.eclipse.jst.j2ee.ejb.MessageDrivenDestination;
import org.eclipse.jst.j2ee.ejb.TransactionType;

/**
 * @generated
 */
public class MessageDrivenImpl extends EnterpriseBeanImpl implements MessageDriven{

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
	protected EClass eStaticClass() {
		return EjbPackage.eINSTANCE.getMessageDriven();
	}

public boolean isMessageDriven() {
	return true;
}
	/**
	 * @generated This field/method will be replaced during code generation 
	 * The transaction-type element specifies an enterprise bean's transaction management type.
	 * 
	 * The transaction-type element must be one of the two following: Bean, Container
	 */
	public TransactionType getTransactionType() {
		return transactionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
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
	public String getMessageSelector() {
		return messageSelector;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
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
	public AcknowledgeMode getAcknowledgeMode() {
		return acknowledgeMode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
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
	public boolean isSetAcknowledgeMode() {
		return acknowledgeModeESet;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * An optional declaration of the intended destination type of the message-driven bean
	 */
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
	public JavaClass getMessageDestination() {
		if (messageDestination != null && messageDestination.eIsProxy()) {
			JavaClass oldMessageDestination = messageDestination;
			messageDestination = (JavaClass)eResolveProxy((InternalEObject)messageDestination);
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
	public String getLink() {
		return link;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
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
	public JavaClass getMessagingType() {
		if (messagingType != null && messagingType.eIsProxy()) {
			JavaClass oldMessagingType = messagingType;
			messagingType = (JavaClass)eResolveProxy((InternalEObject)messagingType);
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
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case EjbPackage.MESSAGE_DRIVEN__EJB_JAR:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, EjbPackage.MESSAGE_DRIVEN__EJB_JAR, msgs);
				case EjbPackage.MESSAGE_DRIVEN__DESTINATION:
					if (destination != null)
						msgs = ((InternalEObject)destination).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.MESSAGE_DRIVEN__DESTINATION, null, msgs);
					return basicSetDestination((MessageDrivenDestination)otherEnd, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case EjbPackage.MESSAGE_DRIVEN__ICONS:
					return ((InternalEList)getIcons()).basicRemove(otherEnd, msgs);
				case EjbPackage.MESSAGE_DRIVEN__DISPLAY_NAMES:
					return ((InternalEList)getDisplayNames()).basicRemove(otherEnd, msgs);
				case EjbPackage.MESSAGE_DRIVEN__DESCRIPTIONS:
					return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
				case EjbPackage.MESSAGE_DRIVEN__ENVIRONMENT_PROPERTIES:
					return ((InternalEList)getEnvironmentProperties()).basicRemove(otherEnd, msgs);
				case EjbPackage.MESSAGE_DRIVEN__RESOURCE_REFS:
					return ((InternalEList)getResourceRefs()).basicRemove(otherEnd, msgs);
				case EjbPackage.MESSAGE_DRIVEN__EJB_REFS:
					return ((InternalEList)getEjbRefs()).basicRemove(otherEnd, msgs);
				case EjbPackage.MESSAGE_DRIVEN__RESOURCE_ENV_REFS:
					return ((InternalEList)getResourceEnvRefs()).basicRemove(otherEnd, msgs);
				case EjbPackage.MESSAGE_DRIVEN__EJB_LOCAL_REFS:
					return ((InternalEList)getEjbLocalRefs()).basicRemove(otherEnd, msgs);
				case EjbPackage.MESSAGE_DRIVEN__MESSAGE_DESTINATION_REFS:
					return ((InternalEList)getMessageDestinationRefs()).basicRemove(otherEnd, msgs);
				case EjbPackage.MESSAGE_DRIVEN__SERVICE_REFS:
					return ((InternalEList)getServiceRefs()).basicRemove(otherEnd, msgs);
				case EjbPackage.MESSAGE_DRIVEN__SECURITY_ROLE_REFS:
					return ((InternalEList)getSecurityRoleRefs()).basicRemove(otherEnd, msgs);
				case EjbPackage.MESSAGE_DRIVEN__EJB_JAR:
					return eBasicSetContainer(null, EjbPackage.MESSAGE_DRIVEN__EJB_JAR, msgs);
				case EjbPackage.MESSAGE_DRIVEN__SECURITY_IDENTITY:
					return basicSetSecurityIdentity(null, msgs);
				case EjbPackage.MESSAGE_DRIVEN__DESTINATION:
					return basicSetDestination(null, msgs);
				case EjbPackage.MESSAGE_DRIVEN__ACTIVATION_CONFIG:
					return basicSetActivationConfig(null, msgs);
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
	public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
		if (eContainerFeatureID >= 0) {
			switch (eContainerFeatureID) {
				case EjbPackage.MESSAGE_DRIVEN__EJB_JAR:
					return eContainer.eInverseRemove(this, EjbPackage.EJB_JAR__ENTERPRISE_BEANS, EJBJar.class, msgs);
				default:
					return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.MESSAGE_DRIVEN__ICONS:
				return getIcons();
			case EjbPackage.MESSAGE_DRIVEN__DISPLAY_NAMES:
				return getDisplayNames();
			case EjbPackage.MESSAGE_DRIVEN__DESCRIPTIONS:
				return getDescriptions();
			case EjbPackage.MESSAGE_DRIVEN__SMALL_ICON:
				return getSmallIcon();
			case EjbPackage.MESSAGE_DRIVEN__LARGE_ICON:
				return getLargeIcon();
			case EjbPackage.MESSAGE_DRIVEN__DESCRIPTION:
				return getDescription();
			case EjbPackage.MESSAGE_DRIVEN__DISPLAY_NAME:
				return getDisplayName();
			case EjbPackage.MESSAGE_DRIVEN__ENVIRONMENT_PROPERTIES:
				return getEnvironmentProperties();
			case EjbPackage.MESSAGE_DRIVEN__RESOURCE_REFS:
				return getResourceRefs();
			case EjbPackage.MESSAGE_DRIVEN__EJB_REFS:
				return getEjbRefs();
			case EjbPackage.MESSAGE_DRIVEN__RESOURCE_ENV_REFS:
				return getResourceEnvRefs();
			case EjbPackage.MESSAGE_DRIVEN__EJB_LOCAL_REFS:
				return getEjbLocalRefs();
			case EjbPackage.MESSAGE_DRIVEN__MESSAGE_DESTINATION_REFS:
				return getMessageDestinationRefs();
			case EjbPackage.MESSAGE_DRIVEN__SERVICE_REFS:
				return getServiceRefs();
			case EjbPackage.MESSAGE_DRIVEN__NAME:
				return getName();
			case EjbPackage.MESSAGE_DRIVEN__SECURITY_ROLE_REFS:
				return getSecurityRoleRefs();
			case EjbPackage.MESSAGE_DRIVEN__EJB_CLASS:
				if (resolve) return getEjbClass();
				return basicGetEjbClass();
			case EjbPackage.MESSAGE_DRIVEN__HOME_INTERFACE:
				if (resolve) return getHomeInterface();
				return basicGetHomeInterface();
			case EjbPackage.MESSAGE_DRIVEN__REMOTE_INTERFACE:
				if (resolve) return getRemoteInterface();
				return basicGetRemoteInterface();
			case EjbPackage.MESSAGE_DRIVEN__EJB_JAR:
				return getEjbJar();
			case EjbPackage.MESSAGE_DRIVEN__SECURITY_IDENTITY:
				return getSecurityIdentity();
			case EjbPackage.MESSAGE_DRIVEN__LOCAL_HOME_INTERFACE:
				if (resolve) return getLocalHomeInterface();
				return basicGetLocalHomeInterface();
			case EjbPackage.MESSAGE_DRIVEN__LOCAL_INTERFACE:
				if (resolve) return getLocalInterface();
				return basicGetLocalInterface();
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
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.MESSAGE_DRIVEN__ICONS:
				return icons != null && !icons.isEmpty();
			case EjbPackage.MESSAGE_DRIVEN__DISPLAY_NAMES:
				return displayNames != null && !displayNames.isEmpty();
			case EjbPackage.MESSAGE_DRIVEN__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case EjbPackage.MESSAGE_DRIVEN__SMALL_ICON:
				return SMALL_ICON_EDEFAULT == null ? smallIcon != null : !SMALL_ICON_EDEFAULT.equals(smallIcon);
			case EjbPackage.MESSAGE_DRIVEN__LARGE_ICON:
				return LARGE_ICON_EDEFAULT == null ? largeIcon != null : !LARGE_ICON_EDEFAULT.equals(largeIcon);
			case EjbPackage.MESSAGE_DRIVEN__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case EjbPackage.MESSAGE_DRIVEN__DISPLAY_NAME:
				return DISPLAY_NAME_EDEFAULT == null ? displayName != null : !DISPLAY_NAME_EDEFAULT.equals(displayName);
			case EjbPackage.MESSAGE_DRIVEN__ENVIRONMENT_PROPERTIES:
				return environmentProperties != null && !environmentProperties.isEmpty();
			case EjbPackage.MESSAGE_DRIVEN__RESOURCE_REFS:
				return resourceRefs != null && !resourceRefs.isEmpty();
			case EjbPackage.MESSAGE_DRIVEN__EJB_REFS:
				return ejbRefs != null && !ejbRefs.isEmpty();
			case EjbPackage.MESSAGE_DRIVEN__RESOURCE_ENV_REFS:
				return resourceEnvRefs != null && !resourceEnvRefs.isEmpty();
			case EjbPackage.MESSAGE_DRIVEN__EJB_LOCAL_REFS:
				return ejbLocalRefs != null && !ejbLocalRefs.isEmpty();
			case EjbPackage.MESSAGE_DRIVEN__MESSAGE_DESTINATION_REFS:
				return messageDestinationRefs != null && !messageDestinationRefs.isEmpty();
			case EjbPackage.MESSAGE_DRIVEN__SERVICE_REFS:
				return serviceRefs != null && !serviceRefs.isEmpty();
			case EjbPackage.MESSAGE_DRIVEN__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case EjbPackage.MESSAGE_DRIVEN__SECURITY_ROLE_REFS:
				return securityRoleRefs != null && !securityRoleRefs.isEmpty();
			case EjbPackage.MESSAGE_DRIVEN__EJB_CLASS:
				return ejbClass != null;
			case EjbPackage.MESSAGE_DRIVEN__HOME_INTERFACE:
				return homeInterface != null;
			case EjbPackage.MESSAGE_DRIVEN__REMOTE_INTERFACE:
				return remoteInterface != null;
			case EjbPackage.MESSAGE_DRIVEN__EJB_JAR:
				return getEjbJar() != null;
			case EjbPackage.MESSAGE_DRIVEN__SECURITY_IDENTITY:
				return securityIdentity != null;
			case EjbPackage.MESSAGE_DRIVEN__LOCAL_HOME_INTERFACE:
				return localHomeInterface != null;
			case EjbPackage.MESSAGE_DRIVEN__LOCAL_INTERFACE:
				return localInterface != null;
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
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.MESSAGE_DRIVEN__ICONS:
				getIcons().clear();
				getIcons().addAll((Collection)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN__DISPLAY_NAMES:
				getDisplayNames().clear();
				getDisplayNames().addAll((Collection)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN__SMALL_ICON:
				setSmallIcon((String)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN__LARGE_ICON:
				setLargeIcon((String)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN__DISPLAY_NAME:
				setDisplayName((String)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN__ENVIRONMENT_PROPERTIES:
				getEnvironmentProperties().clear();
				getEnvironmentProperties().addAll((Collection)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN__RESOURCE_REFS:
				getResourceRefs().clear();
				getResourceRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN__EJB_REFS:
				getEjbRefs().clear();
				getEjbRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN__RESOURCE_ENV_REFS:
				getResourceEnvRefs().clear();
				getResourceEnvRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN__EJB_LOCAL_REFS:
				getEjbLocalRefs().clear();
				getEjbLocalRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN__MESSAGE_DESTINATION_REFS:
				getMessageDestinationRefs().clear();
				getMessageDestinationRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN__SERVICE_REFS:
				getServiceRefs().clear();
				getServiceRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN__NAME:
				setName((String)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN__SECURITY_ROLE_REFS:
				getSecurityRoleRefs().clear();
				getSecurityRoleRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN__EJB_CLASS:
				setEjbClass((JavaClass)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN__HOME_INTERFACE:
				setHomeInterface((JavaClass)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN__REMOTE_INTERFACE:
				setRemoteInterface((JavaClass)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN__EJB_JAR:
				setEjbJar((EJBJar)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN__SECURITY_IDENTITY:
				setSecurityIdentity((SecurityIdentity)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN__LOCAL_HOME_INTERFACE:
				setLocalHomeInterface((JavaClass)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN__LOCAL_INTERFACE:
				setLocalInterface((JavaClass)newValue);
				return;
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
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.MESSAGE_DRIVEN__ICONS:
				getIcons().clear();
				return;
			case EjbPackage.MESSAGE_DRIVEN__DISPLAY_NAMES:
				getDisplayNames().clear();
				return;
			case EjbPackage.MESSAGE_DRIVEN__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case EjbPackage.MESSAGE_DRIVEN__SMALL_ICON:
				setSmallIcon(SMALL_ICON_EDEFAULT);
				return;
			case EjbPackage.MESSAGE_DRIVEN__LARGE_ICON:
				setLargeIcon(LARGE_ICON_EDEFAULT);
				return;
			case EjbPackage.MESSAGE_DRIVEN__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case EjbPackage.MESSAGE_DRIVEN__DISPLAY_NAME:
				setDisplayName(DISPLAY_NAME_EDEFAULT);
				return;
			case EjbPackage.MESSAGE_DRIVEN__ENVIRONMENT_PROPERTIES:
				getEnvironmentProperties().clear();
				return;
			case EjbPackage.MESSAGE_DRIVEN__RESOURCE_REFS:
				getResourceRefs().clear();
				return;
			case EjbPackage.MESSAGE_DRIVEN__EJB_REFS:
				getEjbRefs().clear();
				return;
			case EjbPackage.MESSAGE_DRIVEN__RESOURCE_ENV_REFS:
				getResourceEnvRefs().clear();
				return;
			case EjbPackage.MESSAGE_DRIVEN__EJB_LOCAL_REFS:
				getEjbLocalRefs().clear();
				return;
			case EjbPackage.MESSAGE_DRIVEN__MESSAGE_DESTINATION_REFS:
				getMessageDestinationRefs().clear();
				return;
			case EjbPackage.MESSAGE_DRIVEN__SERVICE_REFS:
				getServiceRefs().clear();
				return;
			case EjbPackage.MESSAGE_DRIVEN__NAME:
				setName(NAME_EDEFAULT);
				return;
			case EjbPackage.MESSAGE_DRIVEN__SECURITY_ROLE_REFS:
				getSecurityRoleRefs().clear();
				return;
			case EjbPackage.MESSAGE_DRIVEN__EJB_CLASS:
				setEjbClass((JavaClass)null);
				return;
			case EjbPackage.MESSAGE_DRIVEN__HOME_INTERFACE:
				setHomeInterface((JavaClass)null);
				return;
			case EjbPackage.MESSAGE_DRIVEN__REMOTE_INTERFACE:
				setRemoteInterface((JavaClass)null);
				return;
			case EjbPackage.MESSAGE_DRIVEN__EJB_JAR:
				setEjbJar((EJBJar)null);
				return;
			case EjbPackage.MESSAGE_DRIVEN__SECURITY_IDENTITY:
				setSecurityIdentity((SecurityIdentity)null);
				return;
			case EjbPackage.MESSAGE_DRIVEN__LOCAL_HOME_INTERFACE:
				setLocalHomeInterface((JavaClass)null);
				return;
			case EjbPackage.MESSAGE_DRIVEN__LOCAL_INTERFACE:
				setLocalInterface((JavaClass)null);
				return;
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
		eDynamicUnset(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (transactionType: ");
		if (transactionTypeESet) result.append(transactionType); else result.append("<unset>");
		result.append(", messageSelector: ");
		result.append(messageSelector);
		result.append(", acknowledgeMode: ");
		if (acknowledgeModeESet) result.append(acknowledgeMode); else result.append("<unset>");
		result.append(", link: ");
		result.append(link);
		result.append(')');
		return result.toString();
	}

	/*
	 * @see EnterpriseBean#getAvailableLocalHomeMethodElements()
	 */
	public List getAvailableLocalHomeMethodElements() {
		return Collections.EMPTY_LIST;
	}

	/*
	 * @see EnterpriseBean#getAvailableLocalMethodElements()
	 */
	public List getAvailableLocalMethodElements() {
		return Collections.EMPTY_LIST;
	}

	/*
	 * @see EnterpriseBean#getAvailableRemoteMethodElements()
	 */
	public List getAvailableRemoteMethodElements() {
		return Collections.EMPTY_LIST;
	}

	/*
	 * @see EnterpriseBeanImpl#getAvailableUnspecifiedMethodElementSignatures()
	 */
	public List getAvailableUnspecifiedMethodElementSignatures() {
		//There is only the onMessage method
		List sigs = new ArrayList(1);
		sigs.add("onMessage(javax.jms.Message)"); //$NON-NLS-1$
		return sigs;
	}

}





