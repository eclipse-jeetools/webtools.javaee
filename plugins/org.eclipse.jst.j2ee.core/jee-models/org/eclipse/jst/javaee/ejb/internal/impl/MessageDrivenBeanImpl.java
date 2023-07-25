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

import org.eclipse.jst.javaee.core.AdministeredObjectType;
import org.eclipse.jst.javaee.core.ConnectionFactoryResourceType;
import org.eclipse.jst.javaee.core.DataSourceType;
import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.DisplayName;
import org.eclipse.jst.javaee.core.EjbLocalRef;
import org.eclipse.jst.javaee.core.EjbRef;
import org.eclipse.jst.javaee.core.EnvEntry;
import org.eclipse.jst.javaee.core.Icon;
import org.eclipse.jst.javaee.core.JmsConnectionFactoryType;
import org.eclipse.jst.javaee.core.JmsDestinationType;
import org.eclipse.jst.javaee.core.LifecycleCallback;
import org.eclipse.jst.javaee.core.MailSessionType;
import org.eclipse.jst.javaee.core.MessageDestinationRef;
import org.eclipse.jst.javaee.core.PersistenceContextRef;
import org.eclipse.jst.javaee.core.PersistenceUnitRef;
import org.eclipse.jst.javaee.core.ResourceEnvRef;
import org.eclipse.jst.javaee.core.ResourceRef;
import org.eclipse.jst.javaee.core.SecurityRoleRef;
import org.eclipse.jst.javaee.core.ServiceRef;

import org.eclipse.jst.javaee.ejb.ActivationConfig;
import org.eclipse.jst.javaee.ejb.AroundInvokeType;
import org.eclipse.jst.javaee.ejb.AroundTimeoutType;
import org.eclipse.jst.javaee.ejb.MessageDrivenBean;
import org.eclipse.jst.javaee.ejb.NamedMethodType;
import org.eclipse.jst.javaee.ejb.SecurityIdentityType;
import org.eclipse.jst.javaee.ejb.TimerType;
import org.eclipse.jst.javaee.ejb.TransactionType;

import org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Message Driven Bean</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MessageDrivenBeanImpl#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MessageDrivenBeanImpl#getDisplayNames <em>Display Names</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MessageDrivenBeanImpl#getIcons <em>Icons</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MessageDrivenBeanImpl#getEjbName <em>Ejb Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MessageDrivenBeanImpl#getMappedName <em>Mapped Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MessageDrivenBeanImpl#getEjbClass <em>Ejb Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MessageDrivenBeanImpl#getMessagingType <em>Messaging Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MessageDrivenBeanImpl#getTimeoutMethod <em>Timeout Method</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MessageDrivenBeanImpl#getTimer <em>Timer</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MessageDrivenBeanImpl#getTransactionType <em>Transaction Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MessageDrivenBeanImpl#getMessageDestinationType <em>Message Destination Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MessageDrivenBeanImpl#getMessageDestinationLink <em>Message Destination Link</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MessageDrivenBeanImpl#getActivationConfig <em>Activation Config</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MessageDrivenBeanImpl#getAroundInvokes <em>Around Invokes</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MessageDrivenBeanImpl#getAroundTimeouts <em>Around Timeouts</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MessageDrivenBeanImpl#getEnvEntries <em>Env Entries</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MessageDrivenBeanImpl#getEjbRefs <em>Ejb Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MessageDrivenBeanImpl#getEjbLocalRefs <em>Ejb Local Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MessageDrivenBeanImpl#getServiceRefs <em>Service Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MessageDrivenBeanImpl#getResourceRefs <em>Resource Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MessageDrivenBeanImpl#getResourceEnvRefs <em>Resource Env Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MessageDrivenBeanImpl#getMessageDestinationRefs <em>Message Destination Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MessageDrivenBeanImpl#getPersistenceContextRefs <em>Persistence Context Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MessageDrivenBeanImpl#getPersistenceUnitRefs <em>Persistence Unit Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MessageDrivenBeanImpl#getPostConstructs <em>Post Constructs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MessageDrivenBeanImpl#getPreDestroys <em>Pre Destroys</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MessageDrivenBeanImpl#getDataSource <em>Data Source</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MessageDrivenBeanImpl#getJmsConnectionFactory <em>Jms Connection Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MessageDrivenBeanImpl#getJmsDestination <em>Jms Destination</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MessageDrivenBeanImpl#getMailSession <em>Mail Session</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MessageDrivenBeanImpl#getConnectionFactory <em>Connection Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MessageDrivenBeanImpl#getAdministeredObject <em>Administered Object</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MessageDrivenBeanImpl#getSecurityRoleRef <em>Security Role Ref</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MessageDrivenBeanImpl#getSecurityIdentity <em>Security Identity</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MessageDrivenBeanImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MessageDrivenBeanImpl extends EObjectImpl implements MessageDrivenBean {
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
	 * The cached value of the '{@link #getDisplayNames() <em>Display Names</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayNames()
	 * @generated
	 * @ordered
	 */
	protected EList<DisplayName> displayNames;

	/**
	 * The cached value of the '{@link #getIcons() <em>Icons</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIcons()
	 * @generated
	 * @ordered
	 */
	protected EList<Icon> icons;

	/**
	 * The default value of the '{@link #getEjbName() <em>Ejb Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbName()
	 * @generated
	 * @ordered
	 */
	protected static final String EJB_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEjbName() <em>Ejb Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbName()
	 * @generated
	 * @ordered
	 */
	protected String ejbName = EJB_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getMappedName() <em>Mapped Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMappedName()
	 * @generated
	 * @ordered
	 */
	protected static final String MAPPED_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMappedName() <em>Mapped Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMappedName()
	 * @generated
	 * @ordered
	 */
	protected String mappedName = MAPPED_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getEjbClass() <em>Ejb Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbClass()
	 * @generated
	 * @ordered
	 */
	protected static final String EJB_CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEjbClass() <em>Ejb Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbClass()
	 * @generated
	 * @ordered
	 */
	protected String ejbClass = EJB_CLASS_EDEFAULT;

	/**
	 * The default value of the '{@link #getMessagingType() <em>Messaging Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessagingType()
	 * @generated
	 * @ordered
	 */
	protected static final String MESSAGING_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMessagingType() <em>Messaging Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessagingType()
	 * @generated
	 * @ordered
	 */
	protected String messagingType = MESSAGING_TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTimeoutMethod() <em>Timeout Method</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeoutMethod()
	 * @generated
	 * @ordered
	 */
	protected NamedMethodType timeoutMethod;

	/**
	 * The cached value of the '{@link #getTimer() <em>Timer</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimer()
	 * @generated
	 * @ordered
	 */
	protected EList<TimerType> timer;

	/**
	 * The default value of the '{@link #getTransactionType() <em>Transaction Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransactionType()
	 * @generated
	 * @ordered
	 */
	protected static final TransactionType TRANSACTION_TYPE_EDEFAULT = TransactionType.CONTAINER_LITERAL;

	/**
	 * The cached value of the '{@link #getTransactionType() <em>Transaction Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransactionType()
	 * @generated
	 * @ordered
	 */
	protected TransactionType transactionType = TRANSACTION_TYPE_EDEFAULT;

	/**
	 * This is true if the Transaction Type attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean transactionTypeESet;

	/**
	 * The default value of the '{@link #getMessageDestinationType() <em>Message Destination Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageDestinationType()
	 * @generated
	 * @ordered
	 */
	protected static final String MESSAGE_DESTINATION_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMessageDestinationType() <em>Message Destination Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageDestinationType()
	 * @generated
	 * @ordered
	 */
	protected String messageDestinationType = MESSAGE_DESTINATION_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMessageDestinationLink() <em>Message Destination Link</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageDestinationLink()
	 * @generated
	 * @ordered
	 */
	protected static final String MESSAGE_DESTINATION_LINK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMessageDestinationLink() <em>Message Destination Link</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageDestinationLink()
	 * @generated
	 * @ordered
	 */
	protected String messageDestinationLink = MESSAGE_DESTINATION_LINK_EDEFAULT;

	/**
	 * The cached value of the '{@link #getActivationConfig() <em>Activation Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivationConfig()
	 * @generated
	 * @ordered
	 */
	protected ActivationConfig activationConfig;

	/**
	 * The cached value of the '{@link #getAroundInvokes() <em>Around Invokes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAroundInvokes()
	 * @generated
	 * @ordered
	 */
	protected EList<AroundInvokeType> aroundInvokes;

	/**
	 * The cached value of the '{@link #getAroundTimeouts() <em>Around Timeouts</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAroundTimeouts()
	 * @generated
	 * @ordered
	 */
	protected EList<AroundTimeoutType> aroundTimeouts;

	/**
	 * The cached value of the '{@link #getEnvEntries() <em>Env Entries</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnvEntries()
	 * @generated
	 * @ordered
	 */
	protected EList<EnvEntry> envEntries;

	/**
	 * The cached value of the '{@link #getEjbRefs() <em>Ejb Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbRefs()
	 * @generated
	 * @ordered
	 */
	protected EList<EjbRef> ejbRefs;

	/**
	 * The cached value of the '{@link #getEjbLocalRefs() <em>Ejb Local Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbLocalRefs()
	 * @generated
	 * @ordered
	 */
	protected EList<EjbLocalRef> ejbLocalRefs;

	/**
	 * The cached value of the '{@link #getServiceRefs() <em>Service Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceRefs()
	 * @generated
	 * @ordered
	 */
	protected EList<ServiceRef> serviceRefs;

	/**
	 * The cached value of the '{@link #getResourceRefs() <em>Resource Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceRefs()
	 * @generated
	 * @ordered
	 */
	protected EList<ResourceRef> resourceRefs;

	/**
	 * The cached value of the '{@link #getResourceEnvRefs() <em>Resource Env Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceEnvRefs()
	 * @generated
	 * @ordered
	 */
	protected EList<ResourceEnvRef> resourceEnvRefs;

	/**
	 * The cached value of the '{@link #getMessageDestinationRefs() <em>Message Destination Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageDestinationRefs()
	 * @generated
	 * @ordered
	 */
	protected EList<MessageDestinationRef> messageDestinationRefs;

	/**
	 * The cached value of the '{@link #getPersistenceContextRefs() <em>Persistence Context Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPersistenceContextRefs()
	 * @generated
	 * @ordered
	 */
	protected EList<PersistenceContextRef> persistenceContextRefs;

	/**
	 * The cached value of the '{@link #getPersistenceUnitRefs() <em>Persistence Unit Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPersistenceUnitRefs()
	 * @generated
	 * @ordered
	 */
	protected EList<PersistenceUnitRef> persistenceUnitRefs;

	/**
	 * The cached value of the '{@link #getPostConstructs() <em>Post Constructs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPostConstructs()
	 * @generated
	 * @ordered
	 */
	protected EList<LifecycleCallback> postConstructs;

	/**
	 * The cached value of the '{@link #getPreDestroys() <em>Pre Destroys</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreDestroys()
	 * @generated
	 * @ordered
	 */
	protected EList<LifecycleCallback> preDestroys;

	/**
	 * The cached value of the '{@link #getDataSource() <em>Data Source</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataSource()
	 * @generated
	 * @ordered
	 */
	protected EList<DataSourceType> dataSource;

	/**
	 * The cached value of the '{@link #getJmsConnectionFactory() <em>Jms Connection Factory</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJmsConnectionFactory()
	 * @generated
	 * @ordered
	 */
	protected EList<JmsConnectionFactoryType> jmsConnectionFactory;

	/**
	 * The cached value of the '{@link #getJmsDestination() <em>Jms Destination</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJmsDestination()
	 * @generated
	 * @ordered
	 */
	protected EList<JmsDestinationType> jmsDestination;

	/**
	 * The cached value of the '{@link #getMailSession() <em>Mail Session</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMailSession()
	 * @generated
	 * @ordered
	 */
	protected EList<MailSessionType> mailSession;

	/**
	 * The cached value of the '{@link #getConnectionFactory() <em>Connection Factory</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectionFactory()
	 * @generated
	 * @ordered
	 */
	protected EList<ConnectionFactoryResourceType> connectionFactory;

	/**
	 * The cached value of the '{@link #getAdministeredObject() <em>Administered Object</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdministeredObject()
	 * @generated
	 * @ordered
	 */
	protected EList<AdministeredObjectType> administeredObject;

	/**
	 * The cached value of the '{@link #getSecurityRoleRef() <em>Security Role Ref</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecurityRoleRef()
	 * @generated
	 * @ordered
	 */
	protected EList<SecurityRoleRef> securityRoleRef;

	/**
	 * The cached value of the '{@link #getSecurityIdentity() <em>Security Identity</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecurityIdentity()
	 * @generated
	 * @ordered
	 */
	protected SecurityIdentityType securityIdentity;

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
	protected MessageDrivenBeanImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EjbPackage.Literals.MESSAGE_DRIVEN_BEAN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Description> getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList<Description>(Description.class, this, EjbPackage.MESSAGE_DRIVEN_BEAN__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<DisplayName> getDisplayNames() {
		if (displayNames == null) {
			displayNames = new EObjectContainmentEList<DisplayName>(DisplayName.class, this, EjbPackage.MESSAGE_DRIVEN_BEAN__DISPLAY_NAMES);
		}
		return displayNames;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Icon> getIcons() {
		if (icons == null) {
			icons = new EObjectContainmentEList<Icon>(Icon.class, this, EjbPackage.MESSAGE_DRIVEN_BEAN__ICONS);
		}
		return icons;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getEjbName() {
		return ejbName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEjbName(String newEjbName) {
		String oldEjbName = ejbName;
		ejbName = newEjbName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.MESSAGE_DRIVEN_BEAN__EJB_NAME, oldEjbName, ejbName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMappedName() {
		return mappedName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMappedName(String newMappedName) {
		String oldMappedName = mappedName;
		mappedName = newMappedName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.MESSAGE_DRIVEN_BEAN__MAPPED_NAME, oldMappedName, mappedName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getEjbClass() {
		return ejbClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEjbClass(String newEjbClass) {
		String oldEjbClass = ejbClass;
		ejbClass = newEjbClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.MESSAGE_DRIVEN_BEAN__EJB_CLASS, oldEjbClass, ejbClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMessagingType() {
		return messagingType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMessagingType(String newMessagingType) {
		String oldMessagingType = messagingType;
		messagingType = newMessagingType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.MESSAGE_DRIVEN_BEAN__MESSAGING_TYPE, oldMessagingType, messagingType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NamedMethodType getTimeoutMethod() {
		return timeoutMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTimeoutMethod(NamedMethodType newTimeoutMethod, NotificationChain msgs) {
		NamedMethodType oldTimeoutMethod = timeoutMethod;
		timeoutMethod = newTimeoutMethod;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.MESSAGE_DRIVEN_BEAN__TIMEOUT_METHOD, oldTimeoutMethod, newTimeoutMethod);
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
	public void setTimeoutMethod(NamedMethodType newTimeoutMethod) {
		if (newTimeoutMethod != timeoutMethod) {
			NotificationChain msgs = null;
			if (timeoutMethod != null)
				msgs = ((InternalEObject)timeoutMethod).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.MESSAGE_DRIVEN_BEAN__TIMEOUT_METHOD, null, msgs);
			if (newTimeoutMethod != null)
				msgs = ((InternalEObject)newTimeoutMethod).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EjbPackage.MESSAGE_DRIVEN_BEAN__TIMEOUT_METHOD, null, msgs);
			msgs = basicSetTimeoutMethod(newTimeoutMethod, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.MESSAGE_DRIVEN_BEAN__TIMEOUT_METHOD, newTimeoutMethod, newTimeoutMethod));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<TimerType> getTimer() {
		if (timer == null) {
			timer = new EObjectContainmentEList<TimerType>(TimerType.class, this, EjbPackage.MESSAGE_DRIVEN_BEAN__TIMER);
		}
		return timer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
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
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.MESSAGE_DRIVEN_BEAN__TRANSACTION_TYPE, oldTransactionType, transactionType, !oldTransactionTypeESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, EjbPackage.MESSAGE_DRIVEN_BEAN__TRANSACTION_TYPE, oldTransactionType, TRANSACTION_TYPE_EDEFAULT, oldTransactionTypeESet));
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMessageDestinationType() {
		return messageDestinationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMessageDestinationType(String newMessageDestinationType) {
		String oldMessageDestinationType = messageDestinationType;
		messageDestinationType = newMessageDestinationType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.MESSAGE_DRIVEN_BEAN__MESSAGE_DESTINATION_TYPE, oldMessageDestinationType, messageDestinationType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMessageDestinationLink() {
		return messageDestinationLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMessageDestinationLink(String newMessageDestinationLink) {
		String oldMessageDestinationLink = messageDestinationLink;
		messageDestinationLink = newMessageDestinationLink;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.MESSAGE_DRIVEN_BEAN__MESSAGE_DESTINATION_LINK, oldMessageDestinationLink, messageDestinationLink));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.MESSAGE_DRIVEN_BEAN__ACTIVATION_CONFIG, oldActivationConfig, newActivationConfig);
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
				msgs = ((InternalEObject)activationConfig).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.MESSAGE_DRIVEN_BEAN__ACTIVATION_CONFIG, null, msgs);
			if (newActivationConfig != null)
				msgs = ((InternalEObject)newActivationConfig).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EjbPackage.MESSAGE_DRIVEN_BEAN__ACTIVATION_CONFIG, null, msgs);
			msgs = basicSetActivationConfig(newActivationConfig, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.MESSAGE_DRIVEN_BEAN__ACTIVATION_CONFIG, newActivationConfig, newActivationConfig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<AroundInvokeType> getAroundInvokes() {
		if (aroundInvokes == null) {
			aroundInvokes = new EObjectContainmentEList<AroundInvokeType>(AroundInvokeType.class, this, EjbPackage.MESSAGE_DRIVEN_BEAN__AROUND_INVOKES);
		}
		return aroundInvokes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<AroundTimeoutType> getAroundTimeouts() {
		if (aroundTimeouts == null) {
			aroundTimeouts = new EObjectContainmentEList<AroundTimeoutType>(AroundTimeoutType.class, this, EjbPackage.MESSAGE_DRIVEN_BEAN__AROUND_TIMEOUTS);
		}
		return aroundTimeouts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<EnvEntry> getEnvEntries() {
		if (envEntries == null) {
			envEntries = new EObjectContainmentEList<EnvEntry>(EnvEntry.class, this, EjbPackage.MESSAGE_DRIVEN_BEAN__ENV_ENTRIES);
		}
		return envEntries;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<EjbRef> getEjbRefs() {
		if (ejbRefs == null) {
			ejbRefs = new EObjectContainmentEList<EjbRef>(EjbRef.class, this, EjbPackage.MESSAGE_DRIVEN_BEAN__EJB_REFS);
		}
		return ejbRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<EjbLocalRef> getEjbLocalRefs() {
		if (ejbLocalRefs == null) {
			ejbLocalRefs = new EObjectContainmentEList<EjbLocalRef>(EjbLocalRef.class, this, EjbPackage.MESSAGE_DRIVEN_BEAN__EJB_LOCAL_REFS);
		}
		return ejbLocalRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<ServiceRef> getServiceRefs() {
		if (serviceRefs == null) {
			serviceRefs = new EObjectContainmentEList<ServiceRef>(ServiceRef.class, this, EjbPackage.MESSAGE_DRIVEN_BEAN__SERVICE_REFS);
		}
		return serviceRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<ResourceRef> getResourceRefs() {
		if (resourceRefs == null) {
			resourceRefs = new EObjectContainmentEList<ResourceRef>(ResourceRef.class, this, EjbPackage.MESSAGE_DRIVEN_BEAN__RESOURCE_REFS);
		}
		return resourceRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<ResourceEnvRef> getResourceEnvRefs() {
		if (resourceEnvRefs == null) {
			resourceEnvRefs = new EObjectContainmentEList<ResourceEnvRef>(ResourceEnvRef.class, this, EjbPackage.MESSAGE_DRIVEN_BEAN__RESOURCE_ENV_REFS);
		}
		return resourceEnvRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<MessageDestinationRef> getMessageDestinationRefs() {
		if (messageDestinationRefs == null) {
			messageDestinationRefs = new EObjectContainmentEList<MessageDestinationRef>(MessageDestinationRef.class, this, EjbPackage.MESSAGE_DRIVEN_BEAN__MESSAGE_DESTINATION_REFS);
		}
		return messageDestinationRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<PersistenceContextRef> getPersistenceContextRefs() {
		if (persistenceContextRefs == null) {
			persistenceContextRefs = new EObjectContainmentEList<PersistenceContextRef>(PersistenceContextRef.class, this, EjbPackage.MESSAGE_DRIVEN_BEAN__PERSISTENCE_CONTEXT_REFS);
		}
		return persistenceContextRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<PersistenceUnitRef> getPersistenceUnitRefs() {
		if (persistenceUnitRefs == null) {
			persistenceUnitRefs = new EObjectContainmentEList<PersistenceUnitRef>(PersistenceUnitRef.class, this, EjbPackage.MESSAGE_DRIVEN_BEAN__PERSISTENCE_UNIT_REFS);
		}
		return persistenceUnitRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<LifecycleCallback> getPostConstructs() {
		if (postConstructs == null) {
			postConstructs = new EObjectContainmentEList<LifecycleCallback>(LifecycleCallback.class, this, EjbPackage.MESSAGE_DRIVEN_BEAN__POST_CONSTRUCTS);
		}
		return postConstructs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<LifecycleCallback> getPreDestroys() {
		if (preDestroys == null) {
			preDestroys = new EObjectContainmentEList<LifecycleCallback>(LifecycleCallback.class, this, EjbPackage.MESSAGE_DRIVEN_BEAN__PRE_DESTROYS);
		}
		return preDestroys;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<DataSourceType> getDataSource() {
		if (dataSource == null) {
			dataSource = new EObjectContainmentEList<DataSourceType>(DataSourceType.class, this, EjbPackage.MESSAGE_DRIVEN_BEAN__DATA_SOURCE);
		}
		return dataSource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<JmsConnectionFactoryType> getJmsConnectionFactory() {
		if (jmsConnectionFactory == null) {
			jmsConnectionFactory = new EObjectContainmentEList<JmsConnectionFactoryType>(JmsConnectionFactoryType.class, this, EjbPackage.MESSAGE_DRIVEN_BEAN__JMS_CONNECTION_FACTORY);
		}
		return jmsConnectionFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<JmsDestinationType> getJmsDestination() {
		if (jmsDestination == null) {
			jmsDestination = new EObjectContainmentEList<JmsDestinationType>(JmsDestinationType.class, this, EjbPackage.MESSAGE_DRIVEN_BEAN__JMS_DESTINATION);
		}
		return jmsDestination;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<MailSessionType> getMailSession() {
		if (mailSession == null) {
			mailSession = new EObjectContainmentEList<MailSessionType>(MailSessionType.class, this, EjbPackage.MESSAGE_DRIVEN_BEAN__MAIL_SESSION);
		}
		return mailSession;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<ConnectionFactoryResourceType> getConnectionFactory() {
		if (connectionFactory == null) {
			connectionFactory = new EObjectContainmentEList<ConnectionFactoryResourceType>(ConnectionFactoryResourceType.class, this, EjbPackage.MESSAGE_DRIVEN_BEAN__CONNECTION_FACTORY);
		}
		return connectionFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<AdministeredObjectType> getAdministeredObject() {
		if (administeredObject == null) {
			administeredObject = new EObjectContainmentEList<AdministeredObjectType>(AdministeredObjectType.class, this, EjbPackage.MESSAGE_DRIVEN_BEAN__ADMINISTERED_OBJECT);
		}
		return administeredObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<SecurityRoleRef> getSecurityRoleRef() {
		if (securityRoleRef == null) {
			securityRoleRef = new EObjectContainmentEList<SecurityRoleRef>(SecurityRoleRef.class, this, EjbPackage.MESSAGE_DRIVEN_BEAN__SECURITY_ROLE_REF);
		}
		return securityRoleRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SecurityIdentityType getSecurityIdentity() {
		return securityIdentity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSecurityIdentity(SecurityIdentityType newSecurityIdentity, NotificationChain msgs) {
		SecurityIdentityType oldSecurityIdentity = securityIdentity;
		securityIdentity = newSecurityIdentity;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.MESSAGE_DRIVEN_BEAN__SECURITY_IDENTITY, oldSecurityIdentity, newSecurityIdentity);
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
	public void setSecurityIdentity(SecurityIdentityType newSecurityIdentity) {
		if (newSecurityIdentity != securityIdentity) {
			NotificationChain msgs = null;
			if (securityIdentity != null)
				msgs = ((InternalEObject)securityIdentity).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.MESSAGE_DRIVEN_BEAN__SECURITY_IDENTITY, null, msgs);
			if (newSecurityIdentity != null)
				msgs = ((InternalEObject)newSecurityIdentity).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EjbPackage.MESSAGE_DRIVEN_BEAN__SECURITY_IDENTITY, null, msgs);
			msgs = basicSetSecurityIdentity(newSecurityIdentity, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.MESSAGE_DRIVEN_BEAN__SECURITY_IDENTITY, newSecurityIdentity, newSecurityIdentity));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.MESSAGE_DRIVEN_BEAN__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EjbPackage.MESSAGE_DRIVEN_BEAN__DESCRIPTIONS:
				return ((InternalEList<?>)getDescriptions()).basicRemove(otherEnd, msgs);
			case EjbPackage.MESSAGE_DRIVEN_BEAN__DISPLAY_NAMES:
				return ((InternalEList<?>)getDisplayNames()).basicRemove(otherEnd, msgs);
			case EjbPackage.MESSAGE_DRIVEN_BEAN__ICONS:
				return ((InternalEList<?>)getIcons()).basicRemove(otherEnd, msgs);
			case EjbPackage.MESSAGE_DRIVEN_BEAN__TIMEOUT_METHOD:
				return basicSetTimeoutMethod(null, msgs);
			case EjbPackage.MESSAGE_DRIVEN_BEAN__TIMER:
				return ((InternalEList<?>)getTimer()).basicRemove(otherEnd, msgs);
			case EjbPackage.MESSAGE_DRIVEN_BEAN__ACTIVATION_CONFIG:
				return basicSetActivationConfig(null, msgs);
			case EjbPackage.MESSAGE_DRIVEN_BEAN__AROUND_INVOKES:
				return ((InternalEList<?>)getAroundInvokes()).basicRemove(otherEnd, msgs);
			case EjbPackage.MESSAGE_DRIVEN_BEAN__AROUND_TIMEOUTS:
				return ((InternalEList<?>)getAroundTimeouts()).basicRemove(otherEnd, msgs);
			case EjbPackage.MESSAGE_DRIVEN_BEAN__ENV_ENTRIES:
				return ((InternalEList<?>)getEnvEntries()).basicRemove(otherEnd, msgs);
			case EjbPackage.MESSAGE_DRIVEN_BEAN__EJB_REFS:
				return ((InternalEList<?>)getEjbRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.MESSAGE_DRIVEN_BEAN__EJB_LOCAL_REFS:
				return ((InternalEList<?>)getEjbLocalRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.MESSAGE_DRIVEN_BEAN__SERVICE_REFS:
				return ((InternalEList<?>)getServiceRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.MESSAGE_DRIVEN_BEAN__RESOURCE_REFS:
				return ((InternalEList<?>)getResourceRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.MESSAGE_DRIVEN_BEAN__RESOURCE_ENV_REFS:
				return ((InternalEList<?>)getResourceEnvRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.MESSAGE_DRIVEN_BEAN__MESSAGE_DESTINATION_REFS:
				return ((InternalEList<?>)getMessageDestinationRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.MESSAGE_DRIVEN_BEAN__PERSISTENCE_CONTEXT_REFS:
				return ((InternalEList<?>)getPersistenceContextRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.MESSAGE_DRIVEN_BEAN__PERSISTENCE_UNIT_REFS:
				return ((InternalEList<?>)getPersistenceUnitRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.MESSAGE_DRIVEN_BEAN__POST_CONSTRUCTS:
				return ((InternalEList<?>)getPostConstructs()).basicRemove(otherEnd, msgs);
			case EjbPackage.MESSAGE_DRIVEN_BEAN__PRE_DESTROYS:
				return ((InternalEList<?>)getPreDestroys()).basicRemove(otherEnd, msgs);
			case EjbPackage.MESSAGE_DRIVEN_BEAN__DATA_SOURCE:
				return ((InternalEList<?>)getDataSource()).basicRemove(otherEnd, msgs);
			case EjbPackage.MESSAGE_DRIVEN_BEAN__JMS_CONNECTION_FACTORY:
				return ((InternalEList<?>)getJmsConnectionFactory()).basicRemove(otherEnd, msgs);
			case EjbPackage.MESSAGE_DRIVEN_BEAN__JMS_DESTINATION:
				return ((InternalEList<?>)getJmsDestination()).basicRemove(otherEnd, msgs);
			case EjbPackage.MESSAGE_DRIVEN_BEAN__MAIL_SESSION:
				return ((InternalEList<?>)getMailSession()).basicRemove(otherEnd, msgs);
			case EjbPackage.MESSAGE_DRIVEN_BEAN__CONNECTION_FACTORY:
				return ((InternalEList<?>)getConnectionFactory()).basicRemove(otherEnd, msgs);
			case EjbPackage.MESSAGE_DRIVEN_BEAN__ADMINISTERED_OBJECT:
				return ((InternalEList<?>)getAdministeredObject()).basicRemove(otherEnd, msgs);
			case EjbPackage.MESSAGE_DRIVEN_BEAN__SECURITY_ROLE_REF:
				return ((InternalEList<?>)getSecurityRoleRef()).basicRemove(otherEnd, msgs);
			case EjbPackage.MESSAGE_DRIVEN_BEAN__SECURITY_IDENTITY:
				return basicSetSecurityIdentity(null, msgs);
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
			case EjbPackage.MESSAGE_DRIVEN_BEAN__DESCRIPTIONS:
				return getDescriptions();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__DISPLAY_NAMES:
				return getDisplayNames();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__ICONS:
				return getIcons();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__EJB_NAME:
				return getEjbName();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__MAPPED_NAME:
				return getMappedName();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__EJB_CLASS:
				return getEjbClass();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__MESSAGING_TYPE:
				return getMessagingType();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__TIMEOUT_METHOD:
				return getTimeoutMethod();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__TIMER:
				return getTimer();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__TRANSACTION_TYPE:
				return getTransactionType();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__MESSAGE_DESTINATION_TYPE:
				return getMessageDestinationType();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__MESSAGE_DESTINATION_LINK:
				return getMessageDestinationLink();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__ACTIVATION_CONFIG:
				return getActivationConfig();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__AROUND_INVOKES:
				return getAroundInvokes();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__AROUND_TIMEOUTS:
				return getAroundTimeouts();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__ENV_ENTRIES:
				return getEnvEntries();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__EJB_REFS:
				return getEjbRefs();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__EJB_LOCAL_REFS:
				return getEjbLocalRefs();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__SERVICE_REFS:
				return getServiceRefs();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__RESOURCE_REFS:
				return getResourceRefs();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__RESOURCE_ENV_REFS:
				return getResourceEnvRefs();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__MESSAGE_DESTINATION_REFS:
				return getMessageDestinationRefs();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__PERSISTENCE_CONTEXT_REFS:
				return getPersistenceContextRefs();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__PERSISTENCE_UNIT_REFS:
				return getPersistenceUnitRefs();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__POST_CONSTRUCTS:
				return getPostConstructs();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__PRE_DESTROYS:
				return getPreDestroys();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__DATA_SOURCE:
				return getDataSource();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__JMS_CONNECTION_FACTORY:
				return getJmsConnectionFactory();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__JMS_DESTINATION:
				return getJmsDestination();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__MAIL_SESSION:
				return getMailSession();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__CONNECTION_FACTORY:
				return getConnectionFactory();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__ADMINISTERED_OBJECT:
				return getAdministeredObject();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__SECURITY_ROLE_REF:
				return getSecurityRoleRef();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__SECURITY_IDENTITY:
				return getSecurityIdentity();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__ID:
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
			case EjbPackage.MESSAGE_DRIVEN_BEAN__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection<? extends Description>)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__DISPLAY_NAMES:
				getDisplayNames().clear();
				getDisplayNames().addAll((Collection<? extends DisplayName>)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__ICONS:
				getIcons().clear();
				getIcons().addAll((Collection<? extends Icon>)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__EJB_NAME:
				setEjbName((String)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__MAPPED_NAME:
				setMappedName((String)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__EJB_CLASS:
				setEjbClass((String)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__MESSAGING_TYPE:
				setMessagingType((String)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__TIMEOUT_METHOD:
				setTimeoutMethod((NamedMethodType)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__TIMER:
				getTimer().clear();
				getTimer().addAll((Collection<? extends TimerType>)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__TRANSACTION_TYPE:
				setTransactionType((TransactionType)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__MESSAGE_DESTINATION_TYPE:
				setMessageDestinationType((String)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__MESSAGE_DESTINATION_LINK:
				setMessageDestinationLink((String)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__ACTIVATION_CONFIG:
				setActivationConfig((ActivationConfig)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__AROUND_INVOKES:
				getAroundInvokes().clear();
				getAroundInvokes().addAll((Collection<? extends AroundInvokeType>)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__AROUND_TIMEOUTS:
				getAroundTimeouts().clear();
				getAroundTimeouts().addAll((Collection<? extends AroundTimeoutType>)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__ENV_ENTRIES:
				getEnvEntries().clear();
				getEnvEntries().addAll((Collection<? extends EnvEntry>)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__EJB_REFS:
				getEjbRefs().clear();
				getEjbRefs().addAll((Collection<? extends EjbRef>)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__EJB_LOCAL_REFS:
				getEjbLocalRefs().clear();
				getEjbLocalRefs().addAll((Collection<? extends EjbLocalRef>)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__SERVICE_REFS:
				getServiceRefs().clear();
				getServiceRefs().addAll((Collection<? extends ServiceRef>)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__RESOURCE_REFS:
				getResourceRefs().clear();
				getResourceRefs().addAll((Collection<? extends ResourceRef>)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__RESOURCE_ENV_REFS:
				getResourceEnvRefs().clear();
				getResourceEnvRefs().addAll((Collection<? extends ResourceEnvRef>)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__MESSAGE_DESTINATION_REFS:
				getMessageDestinationRefs().clear();
				getMessageDestinationRefs().addAll((Collection<? extends MessageDestinationRef>)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__PERSISTENCE_CONTEXT_REFS:
				getPersistenceContextRefs().clear();
				getPersistenceContextRefs().addAll((Collection<? extends PersistenceContextRef>)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__PERSISTENCE_UNIT_REFS:
				getPersistenceUnitRefs().clear();
				getPersistenceUnitRefs().addAll((Collection<? extends PersistenceUnitRef>)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__POST_CONSTRUCTS:
				getPostConstructs().clear();
				getPostConstructs().addAll((Collection<? extends LifecycleCallback>)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__PRE_DESTROYS:
				getPreDestroys().clear();
				getPreDestroys().addAll((Collection<? extends LifecycleCallback>)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__DATA_SOURCE:
				getDataSource().clear();
				getDataSource().addAll((Collection<? extends DataSourceType>)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__JMS_CONNECTION_FACTORY:
				getJmsConnectionFactory().clear();
				getJmsConnectionFactory().addAll((Collection<? extends JmsConnectionFactoryType>)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__JMS_DESTINATION:
				getJmsDestination().clear();
				getJmsDestination().addAll((Collection<? extends JmsDestinationType>)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__MAIL_SESSION:
				getMailSession().clear();
				getMailSession().addAll((Collection<? extends MailSessionType>)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__CONNECTION_FACTORY:
				getConnectionFactory().clear();
				getConnectionFactory().addAll((Collection<? extends ConnectionFactoryResourceType>)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__ADMINISTERED_OBJECT:
				getAdministeredObject().clear();
				getAdministeredObject().addAll((Collection<? extends AdministeredObjectType>)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__SECURITY_ROLE_REF:
				getSecurityRoleRef().clear();
				getSecurityRoleRef().addAll((Collection<? extends SecurityRoleRef>)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__SECURITY_IDENTITY:
				setSecurityIdentity((SecurityIdentityType)newValue);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__ID:
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
			case EjbPackage.MESSAGE_DRIVEN_BEAN__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__DISPLAY_NAMES:
				getDisplayNames().clear();
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__ICONS:
				getIcons().clear();
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__EJB_NAME:
				setEjbName(EJB_NAME_EDEFAULT);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__MAPPED_NAME:
				setMappedName(MAPPED_NAME_EDEFAULT);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__EJB_CLASS:
				setEjbClass(EJB_CLASS_EDEFAULT);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__MESSAGING_TYPE:
				setMessagingType(MESSAGING_TYPE_EDEFAULT);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__TIMEOUT_METHOD:
				setTimeoutMethod((NamedMethodType)null);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__TIMER:
				getTimer().clear();
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__TRANSACTION_TYPE:
				unsetTransactionType();
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__MESSAGE_DESTINATION_TYPE:
				setMessageDestinationType(MESSAGE_DESTINATION_TYPE_EDEFAULT);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__MESSAGE_DESTINATION_LINK:
				setMessageDestinationLink(MESSAGE_DESTINATION_LINK_EDEFAULT);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__ACTIVATION_CONFIG:
				setActivationConfig((ActivationConfig)null);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__AROUND_INVOKES:
				getAroundInvokes().clear();
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__AROUND_TIMEOUTS:
				getAroundTimeouts().clear();
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__ENV_ENTRIES:
				getEnvEntries().clear();
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__EJB_REFS:
				getEjbRefs().clear();
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__EJB_LOCAL_REFS:
				getEjbLocalRefs().clear();
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__SERVICE_REFS:
				getServiceRefs().clear();
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__RESOURCE_REFS:
				getResourceRefs().clear();
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__RESOURCE_ENV_REFS:
				getResourceEnvRefs().clear();
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__MESSAGE_DESTINATION_REFS:
				getMessageDestinationRefs().clear();
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__PERSISTENCE_CONTEXT_REFS:
				getPersistenceContextRefs().clear();
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__PERSISTENCE_UNIT_REFS:
				getPersistenceUnitRefs().clear();
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__POST_CONSTRUCTS:
				getPostConstructs().clear();
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__PRE_DESTROYS:
				getPreDestroys().clear();
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__DATA_SOURCE:
				getDataSource().clear();
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__JMS_CONNECTION_FACTORY:
				getJmsConnectionFactory().clear();
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__JMS_DESTINATION:
				getJmsDestination().clear();
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__MAIL_SESSION:
				getMailSession().clear();
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__CONNECTION_FACTORY:
				getConnectionFactory().clear();
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__ADMINISTERED_OBJECT:
				getAdministeredObject().clear();
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__SECURITY_ROLE_REF:
				getSecurityRoleRef().clear();
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__SECURITY_IDENTITY:
				setSecurityIdentity((SecurityIdentityType)null);
				return;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__ID:
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
			case EjbPackage.MESSAGE_DRIVEN_BEAN__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__DISPLAY_NAMES:
				return displayNames != null && !displayNames.isEmpty();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__ICONS:
				return icons != null && !icons.isEmpty();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__EJB_NAME:
				return EJB_NAME_EDEFAULT == null ? ejbName != null : !EJB_NAME_EDEFAULT.equals(ejbName);
			case EjbPackage.MESSAGE_DRIVEN_BEAN__MAPPED_NAME:
				return MAPPED_NAME_EDEFAULT == null ? mappedName != null : !MAPPED_NAME_EDEFAULT.equals(mappedName);
			case EjbPackage.MESSAGE_DRIVEN_BEAN__EJB_CLASS:
				return EJB_CLASS_EDEFAULT == null ? ejbClass != null : !EJB_CLASS_EDEFAULT.equals(ejbClass);
			case EjbPackage.MESSAGE_DRIVEN_BEAN__MESSAGING_TYPE:
				return MESSAGING_TYPE_EDEFAULT == null ? messagingType != null : !MESSAGING_TYPE_EDEFAULT.equals(messagingType);
			case EjbPackage.MESSAGE_DRIVEN_BEAN__TIMEOUT_METHOD:
				return timeoutMethod != null;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__TIMER:
				return timer != null && !timer.isEmpty();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__TRANSACTION_TYPE:
				return isSetTransactionType();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__MESSAGE_DESTINATION_TYPE:
				return MESSAGE_DESTINATION_TYPE_EDEFAULT == null ? messageDestinationType != null : !MESSAGE_DESTINATION_TYPE_EDEFAULT.equals(messageDestinationType);
			case EjbPackage.MESSAGE_DRIVEN_BEAN__MESSAGE_DESTINATION_LINK:
				return MESSAGE_DESTINATION_LINK_EDEFAULT == null ? messageDestinationLink != null : !MESSAGE_DESTINATION_LINK_EDEFAULT.equals(messageDestinationLink);
			case EjbPackage.MESSAGE_DRIVEN_BEAN__ACTIVATION_CONFIG:
				return activationConfig != null;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__AROUND_INVOKES:
				return aroundInvokes != null && !aroundInvokes.isEmpty();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__AROUND_TIMEOUTS:
				return aroundTimeouts != null && !aroundTimeouts.isEmpty();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__ENV_ENTRIES:
				return envEntries != null && !envEntries.isEmpty();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__EJB_REFS:
				return ejbRefs != null && !ejbRefs.isEmpty();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__EJB_LOCAL_REFS:
				return ejbLocalRefs != null && !ejbLocalRefs.isEmpty();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__SERVICE_REFS:
				return serviceRefs != null && !serviceRefs.isEmpty();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__RESOURCE_REFS:
				return resourceRefs != null && !resourceRefs.isEmpty();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__RESOURCE_ENV_REFS:
				return resourceEnvRefs != null && !resourceEnvRefs.isEmpty();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__MESSAGE_DESTINATION_REFS:
				return messageDestinationRefs != null && !messageDestinationRefs.isEmpty();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__PERSISTENCE_CONTEXT_REFS:
				return persistenceContextRefs != null && !persistenceContextRefs.isEmpty();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__PERSISTENCE_UNIT_REFS:
				return persistenceUnitRefs != null && !persistenceUnitRefs.isEmpty();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__POST_CONSTRUCTS:
				return postConstructs != null && !postConstructs.isEmpty();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__PRE_DESTROYS:
				return preDestroys != null && !preDestroys.isEmpty();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__DATA_SOURCE:
				return dataSource != null && !dataSource.isEmpty();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__JMS_CONNECTION_FACTORY:
				return jmsConnectionFactory != null && !jmsConnectionFactory.isEmpty();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__JMS_DESTINATION:
				return jmsDestination != null && !jmsDestination.isEmpty();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__MAIL_SESSION:
				return mailSession != null && !mailSession.isEmpty();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__CONNECTION_FACTORY:
				return connectionFactory != null && !connectionFactory.isEmpty();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__ADMINISTERED_OBJECT:
				return administeredObject != null && !administeredObject.isEmpty();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__SECURITY_ROLE_REF:
				return securityRoleRef != null && !securityRoleRef.isEmpty();
			case EjbPackage.MESSAGE_DRIVEN_BEAN__SECURITY_IDENTITY:
				return securityIdentity != null;
			case EjbPackage.MESSAGE_DRIVEN_BEAN__ID:
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
		result.append(" (ejbName: "); //$NON-NLS-1$
		result.append(ejbName);
		result.append(", mappedName: "); //$NON-NLS-1$
		result.append(mappedName);
		result.append(", ejbClass: "); //$NON-NLS-1$
		result.append(ejbClass);
		result.append(", messagingType: "); //$NON-NLS-1$
		result.append(messagingType);
		result.append(", transactionType: "); //$NON-NLS-1$
		if (transactionTypeESet) result.append(transactionType); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", messageDestinationType: "); //$NON-NLS-1$
		result.append(messageDestinationType);
		result.append(", messageDestinationLink: "); //$NON-NLS-1$
		result.append(messageDestinationLink);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //MessageDrivenBeanImpl