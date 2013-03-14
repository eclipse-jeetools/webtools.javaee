/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jst.javaee.core.AdministeredObjectType;
import org.eclipse.jst.javaee.core.ConnectionFactoryResourceType;
import org.eclipse.jst.javaee.core.DataSourceType;
import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.DisplayName;
import org.eclipse.jst.javaee.core.EjbLocalRef;
import org.eclipse.jst.javaee.core.EjbRef;
import org.eclipse.jst.javaee.core.EmptyType;
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

import org.eclipse.jst.javaee.ejb.AroundInvokeType;
import org.eclipse.jst.javaee.ejb.AroundTimeoutType;
import org.eclipse.jst.javaee.ejb.AsyncMethodType;
import org.eclipse.jst.javaee.ejb.ConcurrencyManagementTypeType;
import org.eclipse.jst.javaee.ejb.ConcurrentMethodType;
import org.eclipse.jst.javaee.ejb.DependsOnType;
import org.eclipse.jst.javaee.ejb.InitMethodType;
import org.eclipse.jst.javaee.ejb.NamedMethodType;
import org.eclipse.jst.javaee.ejb.RemoveMethodType;
import org.eclipse.jst.javaee.ejb.SecurityIdentityType;
import org.eclipse.jst.javaee.ejb.SessionBean;
import org.eclipse.jst.javaee.ejb.SessionType;
import org.eclipse.jst.javaee.ejb.StatefulTimeoutType;
import org.eclipse.jst.javaee.ejb.TimerType;
import org.eclipse.jst.javaee.ejb.TransactionType;

import org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Session Bean</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getDisplayNames <em>Display Names</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getIcons <em>Icons</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getEjbName <em>Ejb Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getMappedName <em>Mapped Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getHome <em>Home</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getRemote <em>Remote</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getLocalHome <em>Local Home</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getLocal <em>Local</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getBusinessLocals <em>Business Locals</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getBusinessRemotes <em>Business Remotes</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getLocalBean <em>Local Bean</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getServiceEndpoint <em>Service Endpoint</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getEjbClass <em>Ejb Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getSessionType <em>Session Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getStatefulTimeout <em>Stateful Timeout</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getTimeoutMethod <em>Timeout Method</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getTimer <em>Timer</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#isInitOnStartup <em>Init On Startup</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getConcurrencyManagementType <em>Concurrency Management Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getConcurrentMethod <em>Concurrent Method</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getDependsOn <em>Depends On</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getInitMethods <em>Init Methods</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getRemoveMethods <em>Remove Methods</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getAsyncMethod <em>Async Method</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getTransactionType <em>Transaction Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getAfterBeginMethod <em>After Begin Method</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getBeforeCompletionMethod <em>Before Completion Method</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getAfterCompletionMethod <em>After Completion Method</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getAroundInvokes <em>Around Invokes</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getAroundTimeouts <em>Around Timeouts</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getEnvEntries <em>Env Entries</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getEjbRefs <em>Ejb Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getEjbLocalRefs <em>Ejb Local Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getServiceRefs <em>Service Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getResourceRefs <em>Resource Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getResourceEnvRefs <em>Resource Env Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getMessageDestinationRefs <em>Message Destination Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getPersistenceContextRefs <em>Persistence Context Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getPersistenceUnitRefs <em>Persistence Unit Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getPostConstructs <em>Post Constructs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getPreDestroys <em>Pre Destroys</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getDataSource <em>Data Source</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getJmsConnectionFactory <em>Jms Connection Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getJmsDestination <em>Jms Destination</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getMailSession <em>Mail Session</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getConnectionFactory <em>Connection Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getAdministeredObject <em>Administered Object</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getPostActivates <em>Post Activates</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getPrePassivates <em>Pre Passivates</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getSecurityRoleRefs <em>Security Role Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getSecurityIdentities <em>Security Identities</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#isPassivationCapable <em>Passivation Capable</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SessionBeanImpl extends EObjectImpl implements SessionBean {
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
	 * The default value of the '{@link #getHome() <em>Home</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHome()
	 * @generated
	 * @ordered
	 */
	protected static final String HOME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHome() <em>Home</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHome()
	 * @generated
	 * @ordered
	 */
	protected String home = HOME_EDEFAULT;

	/**
	 * The default value of the '{@link #getRemote() <em>Remote</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemote()
	 * @generated
	 * @ordered
	 */
	protected static final String REMOTE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRemote() <em>Remote</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemote()
	 * @generated
	 * @ordered
	 */
	protected String remote = REMOTE_EDEFAULT;

	/**
	 * The default value of the '{@link #getLocalHome() <em>Local Home</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocalHome()
	 * @generated
	 * @ordered
	 */
	protected static final String LOCAL_HOME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLocalHome() <em>Local Home</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocalHome()
	 * @generated
	 * @ordered
	 */
	protected String localHome = LOCAL_HOME_EDEFAULT;

	/**
	 * The default value of the '{@link #getLocal() <em>Local</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocal()
	 * @generated
	 * @ordered
	 */
	protected static final String LOCAL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLocal() <em>Local</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocal()
	 * @generated
	 * @ordered
	 */
	protected String local = LOCAL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getBusinessLocals() <em>Business Locals</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBusinessLocals()
	 * @generated
	 * @ordered
	 */
	protected EList<String> businessLocals;

	/**
	 * The cached value of the '{@link #getBusinessRemotes() <em>Business Remotes</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBusinessRemotes()
	 * @generated
	 * @ordered
	 */
	protected EList<String> businessRemotes;

	/**
	 * The cached value of the '{@link #getLocalBean() <em>Local Bean</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocalBean()
	 * @generated
	 * @ordered
	 */
	protected EmptyType localBean;

	/**
	 * The default value of the '{@link #getServiceEndpoint() <em>Service Endpoint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceEndpoint()
	 * @generated
	 * @ordered
	 */
	protected static final String SERVICE_ENDPOINT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getServiceEndpoint() <em>Service Endpoint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceEndpoint()
	 * @generated
	 * @ordered
	 */
	protected String serviceEndpoint = SERVICE_ENDPOINT_EDEFAULT;

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
	 * The default value of the '{@link #getSessionType() <em>Session Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSessionType()
	 * @generated
	 * @ordered
	 */
	protected static final SessionType SESSION_TYPE_EDEFAULT = SessionType.SINGLETON_LITERAL;

	/**
	 * The cached value of the '{@link #getSessionType() <em>Session Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSessionType()
	 * @generated
	 * @ordered
	 */
	protected SessionType sessionType = SESSION_TYPE_EDEFAULT;

	/**
	 * This is true if the Session Type attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean sessionTypeESet;

	/**
	 * The cached value of the '{@link #getStatefulTimeout() <em>Stateful Timeout</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatefulTimeout()
	 * @generated
	 * @ordered
	 */
	protected StatefulTimeoutType statefulTimeout;

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
	 * The default value of the '{@link #isInitOnStartup() <em>Init On Startup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInitOnStartup()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INIT_ON_STARTUP_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isInitOnStartup() <em>Init On Startup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInitOnStartup()
	 * @generated
	 * @ordered
	 */
	protected boolean initOnStartup = INIT_ON_STARTUP_EDEFAULT;

	/**
	 * This is true if the Init On Startup attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean initOnStartupESet;

	/**
	 * The default value of the '{@link #getConcurrencyManagementType() <em>Concurrency Management Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConcurrencyManagementType()
	 * @generated
	 * @ordered
	 */
	protected static final ConcurrencyManagementTypeType CONCURRENCY_MANAGEMENT_TYPE_EDEFAULT = ConcurrencyManagementTypeType.BEAN;

	/**
	 * The cached value of the '{@link #getConcurrencyManagementType() <em>Concurrency Management Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConcurrencyManagementType()
	 * @generated
	 * @ordered
	 */
	protected ConcurrencyManagementTypeType concurrencyManagementType = CONCURRENCY_MANAGEMENT_TYPE_EDEFAULT;

	/**
	 * This is true if the Concurrency Management Type attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean concurrencyManagementTypeESet;

	/**
	 * The cached value of the '{@link #getConcurrentMethod() <em>Concurrent Method</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConcurrentMethod()
	 * @generated
	 * @ordered
	 */
	protected EList<ConcurrentMethodType> concurrentMethod;

	/**
	 * The cached value of the '{@link #getDependsOn() <em>Depends On</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDependsOn()
	 * @generated
	 * @ordered
	 */
	protected DependsOnType dependsOn;

	/**
	 * The cached value of the '{@link #getInitMethods() <em>Init Methods</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitMethods()
	 * @generated
	 * @ordered
	 */
	protected EList<InitMethodType> initMethods;

	/**
	 * The cached value of the '{@link #getRemoveMethods() <em>Remove Methods</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemoveMethods()
	 * @generated
	 * @ordered
	 */
	protected EList<RemoveMethodType> removeMethods;

	/**
	 * The cached value of the '{@link #getAsyncMethod() <em>Async Method</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAsyncMethod()
	 * @generated
	 * @ordered
	 */
	protected EList<AsyncMethodType> asyncMethod;

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
	 * The cached value of the '{@link #getAfterBeginMethod() <em>After Begin Method</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAfterBeginMethod()
	 * @generated
	 * @ordered
	 */
	protected NamedMethodType afterBeginMethod;

	/**
	 * The cached value of the '{@link #getBeforeCompletionMethod() <em>Before Completion Method</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBeforeCompletionMethod()
	 * @generated
	 * @ordered
	 */
	protected NamedMethodType beforeCompletionMethod;

	/**
	 * The cached value of the '{@link #getAfterCompletionMethod() <em>After Completion Method</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAfterCompletionMethod()
	 * @generated
	 * @ordered
	 */
	protected NamedMethodType afterCompletionMethod;

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
	 * The cached value of the '{@link #getPostActivates() <em>Post Activates</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPostActivates()
	 * @generated
	 * @ordered
	 */
	protected EList<LifecycleCallback> postActivates;

	/**
	 * The cached value of the '{@link #getPrePassivates() <em>Pre Passivates</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrePassivates()
	 * @generated
	 * @ordered
	 */
	protected EList<LifecycleCallback> prePassivates;

	/**
	 * The cached value of the '{@link #getSecurityRoleRefs() <em>Security Role Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecurityRoleRefs()
	 * @generated
	 * @ordered
	 */
	protected EList<SecurityRoleRef> securityRoleRefs;

	/**
	 * The cached value of the '{@link #getSecurityIdentities() <em>Security Identities</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecurityIdentities()
	 * @generated
	 * @ordered
	 */
	protected SecurityIdentityType securityIdentities;

	/**
	 * The default value of the '{@link #isPassivationCapable() <em>Passivation Capable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPassivationCapable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PASSIVATION_CAPABLE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isPassivationCapable() <em>Passivation Capable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPassivationCapable()
	 * @generated
	 * @ordered
	 */
	protected boolean passivationCapable = PASSIVATION_CAPABLE_EDEFAULT;

	/**
	 * This is true if the Passivation Capable attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean passivationCapableESet;

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
	protected SessionBeanImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EjbPackage.Literals.SESSION_BEAN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Description> getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList<Description>(Description.class, this, EjbPackage.SESSION_BEAN__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<DisplayName> getDisplayNames() {
		if (displayNames == null) {
			displayNames = new EObjectContainmentEList<DisplayName>(DisplayName.class, this, EjbPackage.SESSION_BEAN__DISPLAY_NAMES);
		}
		return displayNames;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Icon> getIcons() {
		if (icons == null) {
			icons = new EObjectContainmentEList<Icon>(Icon.class, this, EjbPackage.SESSION_BEAN__ICONS);
		}
		return icons;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEjbName() {
		return ejbName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEjbName(String newEjbName) {
		String oldEjbName = ejbName;
		ejbName = newEjbName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__EJB_NAME, oldEjbName, ejbName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMappedName() {
		return mappedName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMappedName(String newMappedName) {
		String oldMappedName = mappedName;
		mappedName = newMappedName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__MAPPED_NAME, oldMappedName, mappedName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHome() {
		return home;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHome(String newHome) {
		String oldHome = home;
		home = newHome;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__HOME, oldHome, home));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRemote() {
		return remote;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRemote(String newRemote) {
		String oldRemote = remote;
		remote = newRemote;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__REMOTE, oldRemote, remote));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLocalHome() {
		return localHome;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocalHome(String newLocalHome) {
		String oldLocalHome = localHome;
		localHome = newLocalHome;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__LOCAL_HOME, oldLocalHome, localHome));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLocal() {
		return local;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocal(String newLocal) {
		String oldLocal = local;
		local = newLocal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__LOCAL, oldLocal, local));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<String> getBusinessLocals() {
		if (businessLocals == null) {
			businessLocals = new EDataTypeEList<String>(String.class, this, EjbPackage.SESSION_BEAN__BUSINESS_LOCALS);
		}
		return businessLocals;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<String> getBusinessRemotes() {
		if (businessRemotes == null) {
			businessRemotes = new EDataTypeEList<String>(String.class, this, EjbPackage.SESSION_BEAN__BUSINESS_REMOTES);
		}
		return businessRemotes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EmptyType getLocalBean() {
		return localBean;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLocalBean(EmptyType newLocalBean, NotificationChain msgs) {
		EmptyType oldLocalBean = localBean;
		localBean = newLocalBean;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__LOCAL_BEAN, oldLocalBean, newLocalBean);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocalBean(EmptyType newLocalBean) {
		if (newLocalBean != localBean) {
			NotificationChain msgs = null;
			if (localBean != null)
				msgs = ((InternalEObject)localBean).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.SESSION_BEAN__LOCAL_BEAN, null, msgs);
			if (newLocalBean != null)
				msgs = ((InternalEObject)newLocalBean).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EjbPackage.SESSION_BEAN__LOCAL_BEAN, null, msgs);
			msgs = basicSetLocalBean(newLocalBean, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__LOCAL_BEAN, newLocalBean, newLocalBean));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getServiceEndpoint() {
		return serviceEndpoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServiceEndpoint(String newServiceEndpoint) {
		String oldServiceEndpoint = serviceEndpoint;
		serviceEndpoint = newServiceEndpoint;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__SERVICE_ENDPOINT, oldServiceEndpoint, serviceEndpoint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEjbClass() {
		return ejbClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEjbClass(String newEjbClass) {
		String oldEjbClass = ejbClass;
		ejbClass = newEjbClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__EJB_CLASS, oldEjbClass, ejbClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SessionType getSessionType() {
		return sessionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSessionType(SessionType newSessionType) {
		SessionType oldSessionType = sessionType;
		sessionType = newSessionType == null ? SESSION_TYPE_EDEFAULT : newSessionType;
		boolean oldSessionTypeESet = sessionTypeESet;
		sessionTypeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__SESSION_TYPE, oldSessionType, sessionType, !oldSessionTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetSessionType() {
		SessionType oldSessionType = sessionType;
		boolean oldSessionTypeESet = sessionTypeESet;
		sessionType = SESSION_TYPE_EDEFAULT;
		sessionTypeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, EjbPackage.SESSION_BEAN__SESSION_TYPE, oldSessionType, SESSION_TYPE_EDEFAULT, oldSessionTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetSessionType() {
		return sessionTypeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatefulTimeoutType getStatefulTimeout() {
		return statefulTimeout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStatefulTimeout(StatefulTimeoutType newStatefulTimeout, NotificationChain msgs) {
		StatefulTimeoutType oldStatefulTimeout = statefulTimeout;
		statefulTimeout = newStatefulTimeout;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__STATEFUL_TIMEOUT, oldStatefulTimeout, newStatefulTimeout);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStatefulTimeout(StatefulTimeoutType newStatefulTimeout) {
		if (newStatefulTimeout != statefulTimeout) {
			NotificationChain msgs = null;
			if (statefulTimeout != null)
				msgs = ((InternalEObject)statefulTimeout).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.SESSION_BEAN__STATEFUL_TIMEOUT, null, msgs);
			if (newStatefulTimeout != null)
				msgs = ((InternalEObject)newStatefulTimeout).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EjbPackage.SESSION_BEAN__STATEFUL_TIMEOUT, null, msgs);
			msgs = basicSetStatefulTimeout(newStatefulTimeout, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__STATEFUL_TIMEOUT, newStatefulTimeout, newStatefulTimeout));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__TIMEOUT_METHOD, oldTimeoutMethod, newTimeoutMethod);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimeoutMethod(NamedMethodType newTimeoutMethod) {
		if (newTimeoutMethod != timeoutMethod) {
			NotificationChain msgs = null;
			if (timeoutMethod != null)
				msgs = ((InternalEObject)timeoutMethod).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.SESSION_BEAN__TIMEOUT_METHOD, null, msgs);
			if (newTimeoutMethod != null)
				msgs = ((InternalEObject)newTimeoutMethod).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EjbPackage.SESSION_BEAN__TIMEOUT_METHOD, null, msgs);
			msgs = basicSetTimeoutMethod(newTimeoutMethod, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__TIMEOUT_METHOD, newTimeoutMethod, newTimeoutMethod));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<TimerType> getTimer() {
		if (timer == null) {
			timer = new EObjectContainmentEList<TimerType>(TimerType.class, this, EjbPackage.SESSION_BEAN__TIMER);
		}
		return timer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isInitOnStartup() {
		return initOnStartup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInitOnStartup(boolean newInitOnStartup) {
		boolean oldInitOnStartup = initOnStartup;
		initOnStartup = newInitOnStartup;
		boolean oldInitOnStartupESet = initOnStartupESet;
		initOnStartupESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__INIT_ON_STARTUP, oldInitOnStartup, initOnStartup, !oldInitOnStartupESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetInitOnStartup() {
		boolean oldInitOnStartup = initOnStartup;
		boolean oldInitOnStartupESet = initOnStartupESet;
		initOnStartup = INIT_ON_STARTUP_EDEFAULT;
		initOnStartupESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, EjbPackage.SESSION_BEAN__INIT_ON_STARTUP, oldInitOnStartup, INIT_ON_STARTUP_EDEFAULT, oldInitOnStartupESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetInitOnStartup() {
		return initOnStartupESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConcurrencyManagementTypeType getConcurrencyManagementType() {
		return concurrencyManagementType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConcurrencyManagementType(ConcurrencyManagementTypeType newConcurrencyManagementType) {
		ConcurrencyManagementTypeType oldConcurrencyManagementType = concurrencyManagementType;
		concurrencyManagementType = newConcurrencyManagementType == null ? CONCURRENCY_MANAGEMENT_TYPE_EDEFAULT : newConcurrencyManagementType;
		boolean oldConcurrencyManagementTypeESet = concurrencyManagementTypeESet;
		concurrencyManagementTypeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__CONCURRENCY_MANAGEMENT_TYPE, oldConcurrencyManagementType, concurrencyManagementType, !oldConcurrencyManagementTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetConcurrencyManagementType() {
		ConcurrencyManagementTypeType oldConcurrencyManagementType = concurrencyManagementType;
		boolean oldConcurrencyManagementTypeESet = concurrencyManagementTypeESet;
		concurrencyManagementType = CONCURRENCY_MANAGEMENT_TYPE_EDEFAULT;
		concurrencyManagementTypeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, EjbPackage.SESSION_BEAN__CONCURRENCY_MANAGEMENT_TYPE, oldConcurrencyManagementType, CONCURRENCY_MANAGEMENT_TYPE_EDEFAULT, oldConcurrencyManagementTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetConcurrencyManagementType() {
		return concurrencyManagementTypeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ConcurrentMethodType> getConcurrentMethod() {
		if (concurrentMethod == null) {
			concurrentMethod = new EObjectContainmentEList<ConcurrentMethodType>(ConcurrentMethodType.class, this, EjbPackage.SESSION_BEAN__CONCURRENT_METHOD);
		}
		return concurrentMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DependsOnType getDependsOn() {
		return dependsOn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDependsOn(DependsOnType newDependsOn, NotificationChain msgs) {
		DependsOnType oldDependsOn = dependsOn;
		dependsOn = newDependsOn;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__DEPENDS_ON, oldDependsOn, newDependsOn);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDependsOn(DependsOnType newDependsOn) {
		if (newDependsOn != dependsOn) {
			NotificationChain msgs = null;
			if (dependsOn != null)
				msgs = ((InternalEObject)dependsOn).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.SESSION_BEAN__DEPENDS_ON, null, msgs);
			if (newDependsOn != null)
				msgs = ((InternalEObject)newDependsOn).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EjbPackage.SESSION_BEAN__DEPENDS_ON, null, msgs);
			msgs = basicSetDependsOn(newDependsOn, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__DEPENDS_ON, newDependsOn, newDependsOn));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<InitMethodType> getInitMethods() {
		if (initMethods == null) {
			initMethods = new EObjectContainmentEList<InitMethodType>(InitMethodType.class, this, EjbPackage.SESSION_BEAN__INIT_METHODS);
		}
		return initMethods;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<RemoveMethodType> getRemoveMethods() {
		if (removeMethods == null) {
			removeMethods = new EObjectContainmentEList<RemoveMethodType>(RemoveMethodType.class, this, EjbPackage.SESSION_BEAN__REMOVE_METHODS);
		}
		return removeMethods;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<AsyncMethodType> getAsyncMethod() {
		if (asyncMethod == null) {
			asyncMethod = new EObjectContainmentEList<AsyncMethodType>(AsyncMethodType.class, this, EjbPackage.SESSION_BEAN__ASYNC_METHOD);
		}
		return asyncMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
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
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__TRANSACTION_TYPE, oldTransactionType, transactionType, !oldTransactionTypeESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, EjbPackage.SESSION_BEAN__TRANSACTION_TYPE, oldTransactionType, TRANSACTION_TYPE_EDEFAULT, oldTransactionTypeESet));
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NamedMethodType getAfterBeginMethod() {
		return afterBeginMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAfterBeginMethod(NamedMethodType newAfterBeginMethod, NotificationChain msgs) {
		NamedMethodType oldAfterBeginMethod = afterBeginMethod;
		afterBeginMethod = newAfterBeginMethod;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__AFTER_BEGIN_METHOD, oldAfterBeginMethod, newAfterBeginMethod);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAfterBeginMethod(NamedMethodType newAfterBeginMethod) {
		if (newAfterBeginMethod != afterBeginMethod) {
			NotificationChain msgs = null;
			if (afterBeginMethod != null)
				msgs = ((InternalEObject)afterBeginMethod).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.SESSION_BEAN__AFTER_BEGIN_METHOD, null, msgs);
			if (newAfterBeginMethod != null)
				msgs = ((InternalEObject)newAfterBeginMethod).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EjbPackage.SESSION_BEAN__AFTER_BEGIN_METHOD, null, msgs);
			msgs = basicSetAfterBeginMethod(newAfterBeginMethod, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__AFTER_BEGIN_METHOD, newAfterBeginMethod, newAfterBeginMethod));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NamedMethodType getBeforeCompletionMethod() {
		return beforeCompletionMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBeforeCompletionMethod(NamedMethodType newBeforeCompletionMethod, NotificationChain msgs) {
		NamedMethodType oldBeforeCompletionMethod = beforeCompletionMethod;
		beforeCompletionMethod = newBeforeCompletionMethod;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__BEFORE_COMPLETION_METHOD, oldBeforeCompletionMethod, newBeforeCompletionMethod);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBeforeCompletionMethod(NamedMethodType newBeforeCompletionMethod) {
		if (newBeforeCompletionMethod != beforeCompletionMethod) {
			NotificationChain msgs = null;
			if (beforeCompletionMethod != null)
				msgs = ((InternalEObject)beforeCompletionMethod).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.SESSION_BEAN__BEFORE_COMPLETION_METHOD, null, msgs);
			if (newBeforeCompletionMethod != null)
				msgs = ((InternalEObject)newBeforeCompletionMethod).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EjbPackage.SESSION_BEAN__BEFORE_COMPLETION_METHOD, null, msgs);
			msgs = basicSetBeforeCompletionMethod(newBeforeCompletionMethod, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__BEFORE_COMPLETION_METHOD, newBeforeCompletionMethod, newBeforeCompletionMethod));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NamedMethodType getAfterCompletionMethod() {
		return afterCompletionMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAfterCompletionMethod(NamedMethodType newAfterCompletionMethod, NotificationChain msgs) {
		NamedMethodType oldAfterCompletionMethod = afterCompletionMethod;
		afterCompletionMethod = newAfterCompletionMethod;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__AFTER_COMPLETION_METHOD, oldAfterCompletionMethod, newAfterCompletionMethod);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAfterCompletionMethod(NamedMethodType newAfterCompletionMethod) {
		if (newAfterCompletionMethod != afterCompletionMethod) {
			NotificationChain msgs = null;
			if (afterCompletionMethod != null)
				msgs = ((InternalEObject)afterCompletionMethod).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.SESSION_BEAN__AFTER_COMPLETION_METHOD, null, msgs);
			if (newAfterCompletionMethod != null)
				msgs = ((InternalEObject)newAfterCompletionMethod).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EjbPackage.SESSION_BEAN__AFTER_COMPLETION_METHOD, null, msgs);
			msgs = basicSetAfterCompletionMethod(newAfterCompletionMethod, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__AFTER_COMPLETION_METHOD, newAfterCompletionMethod, newAfterCompletionMethod));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<AroundInvokeType> getAroundInvokes() {
		if (aroundInvokes == null) {
			aroundInvokes = new EObjectContainmentEList<AroundInvokeType>(AroundInvokeType.class, this, EjbPackage.SESSION_BEAN__AROUND_INVOKES);
		}
		return aroundInvokes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<AroundTimeoutType> getAroundTimeouts() {
		if (aroundTimeouts == null) {
			aroundTimeouts = new EObjectContainmentEList<AroundTimeoutType>(AroundTimeoutType.class, this, EjbPackage.SESSION_BEAN__AROUND_TIMEOUTS);
		}
		return aroundTimeouts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<EnvEntry> getEnvEntries() {
		if (envEntries == null) {
			envEntries = new EObjectContainmentEList<EnvEntry>(EnvEntry.class, this, EjbPackage.SESSION_BEAN__ENV_ENTRIES);
		}
		return envEntries;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<EjbRef> getEjbRefs() {
		if (ejbRefs == null) {
			ejbRefs = new EObjectContainmentEList<EjbRef>(EjbRef.class, this, EjbPackage.SESSION_BEAN__EJB_REFS);
		}
		return ejbRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<EjbLocalRef> getEjbLocalRefs() {
		if (ejbLocalRefs == null) {
			ejbLocalRefs = new EObjectContainmentEList<EjbLocalRef>(EjbLocalRef.class, this, EjbPackage.SESSION_BEAN__EJB_LOCAL_REFS);
		}
		return ejbLocalRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ServiceRef> getServiceRefs() {
		if (serviceRefs == null) {
			serviceRefs = new EObjectContainmentEList<ServiceRef>(ServiceRef.class, this, EjbPackage.SESSION_BEAN__SERVICE_REFS);
		}
		return serviceRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ResourceRef> getResourceRefs() {
		if (resourceRefs == null) {
			resourceRefs = new EObjectContainmentEList<ResourceRef>(ResourceRef.class, this, EjbPackage.SESSION_BEAN__RESOURCE_REFS);
		}
		return resourceRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ResourceEnvRef> getResourceEnvRefs() {
		if (resourceEnvRefs == null) {
			resourceEnvRefs = new EObjectContainmentEList<ResourceEnvRef>(ResourceEnvRef.class, this, EjbPackage.SESSION_BEAN__RESOURCE_ENV_REFS);
		}
		return resourceEnvRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<MessageDestinationRef> getMessageDestinationRefs() {
		if (messageDestinationRefs == null) {
			messageDestinationRefs = new EObjectContainmentEList<MessageDestinationRef>(MessageDestinationRef.class, this, EjbPackage.SESSION_BEAN__MESSAGE_DESTINATION_REFS);
		}
		return messageDestinationRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<PersistenceContextRef> getPersistenceContextRefs() {
		if (persistenceContextRefs == null) {
			persistenceContextRefs = new EObjectContainmentEList<PersistenceContextRef>(PersistenceContextRef.class, this, EjbPackage.SESSION_BEAN__PERSISTENCE_CONTEXT_REFS);
		}
		return persistenceContextRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<PersistenceUnitRef> getPersistenceUnitRefs() {
		if (persistenceUnitRefs == null) {
			persistenceUnitRefs = new EObjectContainmentEList<PersistenceUnitRef>(PersistenceUnitRef.class, this, EjbPackage.SESSION_BEAN__PERSISTENCE_UNIT_REFS);
		}
		return persistenceUnitRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<LifecycleCallback> getPostConstructs() {
		if (postConstructs == null) {
			postConstructs = new EObjectContainmentEList<LifecycleCallback>(LifecycleCallback.class, this, EjbPackage.SESSION_BEAN__POST_CONSTRUCTS);
		}
		return postConstructs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<LifecycleCallback> getPreDestroys() {
		if (preDestroys == null) {
			preDestroys = new EObjectContainmentEList<LifecycleCallback>(LifecycleCallback.class, this, EjbPackage.SESSION_BEAN__PRE_DESTROYS);
		}
		return preDestroys;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<DataSourceType> getDataSource() {
		if (dataSource == null) {
			dataSource = new EObjectContainmentEList<DataSourceType>(DataSourceType.class, this, EjbPackage.SESSION_BEAN__DATA_SOURCE);
		}
		return dataSource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<JmsConnectionFactoryType> getJmsConnectionFactory() {
		if (jmsConnectionFactory == null) {
			jmsConnectionFactory = new EObjectContainmentEList<JmsConnectionFactoryType>(JmsConnectionFactoryType.class, this, EjbPackage.SESSION_BEAN__JMS_CONNECTION_FACTORY);
		}
		return jmsConnectionFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<JmsDestinationType> getJmsDestination() {
		if (jmsDestination == null) {
			jmsDestination = new EObjectContainmentEList<JmsDestinationType>(JmsDestinationType.class, this, EjbPackage.SESSION_BEAN__JMS_DESTINATION);
		}
		return jmsDestination;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<MailSessionType> getMailSession() {
		if (mailSession == null) {
			mailSession = new EObjectContainmentEList<MailSessionType>(MailSessionType.class, this, EjbPackage.SESSION_BEAN__MAIL_SESSION);
		}
		return mailSession;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ConnectionFactoryResourceType> getConnectionFactory() {
		if (connectionFactory == null) {
			connectionFactory = new EObjectContainmentEList<ConnectionFactoryResourceType>(ConnectionFactoryResourceType.class, this, EjbPackage.SESSION_BEAN__CONNECTION_FACTORY);
		}
		return connectionFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<AdministeredObjectType> getAdministeredObject() {
		if (administeredObject == null) {
			administeredObject = new EObjectContainmentEList<AdministeredObjectType>(AdministeredObjectType.class, this, EjbPackage.SESSION_BEAN__ADMINISTERED_OBJECT);
		}
		return administeredObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<LifecycleCallback> getPostActivates() {
		if (postActivates == null) {
			postActivates = new EObjectContainmentEList<LifecycleCallback>(LifecycleCallback.class, this, EjbPackage.SESSION_BEAN__POST_ACTIVATES);
		}
		return postActivates;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<LifecycleCallback> getPrePassivates() {
		if (prePassivates == null) {
			prePassivates = new EObjectContainmentEList<LifecycleCallback>(LifecycleCallback.class, this, EjbPackage.SESSION_BEAN__PRE_PASSIVATES);
		}
		return prePassivates;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<SecurityRoleRef> getSecurityRoleRefs() {
		if (securityRoleRefs == null) {
			securityRoleRefs = new EObjectContainmentEList<SecurityRoleRef>(SecurityRoleRef.class, this, EjbPackage.SESSION_BEAN__SECURITY_ROLE_REFS);
		}
		return securityRoleRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SecurityIdentityType getSecurityIdentities() {
		return securityIdentities;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSecurityIdentities(SecurityIdentityType newSecurityIdentities, NotificationChain msgs) {
		SecurityIdentityType oldSecurityIdentities = securityIdentities;
		securityIdentities = newSecurityIdentities;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__SECURITY_IDENTITIES, oldSecurityIdentities, newSecurityIdentities);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSecurityIdentities(SecurityIdentityType newSecurityIdentities) {
		if (newSecurityIdentities != securityIdentities) {
			NotificationChain msgs = null;
			if (securityIdentities != null)
				msgs = ((InternalEObject)securityIdentities).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.SESSION_BEAN__SECURITY_IDENTITIES, null, msgs);
			if (newSecurityIdentities != null)
				msgs = ((InternalEObject)newSecurityIdentities).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EjbPackage.SESSION_BEAN__SECURITY_IDENTITIES, null, msgs);
			msgs = basicSetSecurityIdentities(newSecurityIdentities, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__SECURITY_IDENTITIES, newSecurityIdentities, newSecurityIdentities));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isPassivationCapable() {
		return passivationCapable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPassivationCapable(boolean newPassivationCapable) {
		boolean oldPassivationCapable = passivationCapable;
		passivationCapable = newPassivationCapable;
		boolean oldPassivationCapableESet = passivationCapableESet;
		passivationCapableESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__PASSIVATION_CAPABLE, oldPassivationCapable, passivationCapable, !oldPassivationCapableESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetPassivationCapable() {
		boolean oldPassivationCapable = passivationCapable;
		boolean oldPassivationCapableESet = passivationCapableESet;
		passivationCapable = PASSIVATION_CAPABLE_EDEFAULT;
		passivationCapableESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, EjbPackage.SESSION_BEAN__PASSIVATION_CAPABLE, oldPassivationCapable, PASSIVATION_CAPABLE_EDEFAULT, oldPassivationCapableESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetPassivationCapable() {
		return passivationCapableESet;
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
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EjbPackage.SESSION_BEAN__DESCRIPTIONS:
				return ((InternalEList<?>)getDescriptions()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__DISPLAY_NAMES:
				return ((InternalEList<?>)getDisplayNames()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__ICONS:
				return ((InternalEList<?>)getIcons()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__LOCAL_BEAN:
				return basicSetLocalBean(null, msgs);
			case EjbPackage.SESSION_BEAN__STATEFUL_TIMEOUT:
				return basicSetStatefulTimeout(null, msgs);
			case EjbPackage.SESSION_BEAN__TIMEOUT_METHOD:
				return basicSetTimeoutMethod(null, msgs);
			case EjbPackage.SESSION_BEAN__TIMER:
				return ((InternalEList<?>)getTimer()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__CONCURRENT_METHOD:
				return ((InternalEList<?>)getConcurrentMethod()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__DEPENDS_ON:
				return basicSetDependsOn(null, msgs);
			case EjbPackage.SESSION_BEAN__INIT_METHODS:
				return ((InternalEList<?>)getInitMethods()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__REMOVE_METHODS:
				return ((InternalEList<?>)getRemoveMethods()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__ASYNC_METHOD:
				return ((InternalEList<?>)getAsyncMethod()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__AFTER_BEGIN_METHOD:
				return basicSetAfterBeginMethod(null, msgs);
			case EjbPackage.SESSION_BEAN__BEFORE_COMPLETION_METHOD:
				return basicSetBeforeCompletionMethod(null, msgs);
			case EjbPackage.SESSION_BEAN__AFTER_COMPLETION_METHOD:
				return basicSetAfterCompletionMethod(null, msgs);
			case EjbPackage.SESSION_BEAN__AROUND_INVOKES:
				return ((InternalEList<?>)getAroundInvokes()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__AROUND_TIMEOUTS:
				return ((InternalEList<?>)getAroundTimeouts()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__ENV_ENTRIES:
				return ((InternalEList<?>)getEnvEntries()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__EJB_REFS:
				return ((InternalEList<?>)getEjbRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__EJB_LOCAL_REFS:
				return ((InternalEList<?>)getEjbLocalRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__SERVICE_REFS:
				return ((InternalEList<?>)getServiceRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__RESOURCE_REFS:
				return ((InternalEList<?>)getResourceRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__RESOURCE_ENV_REFS:
				return ((InternalEList<?>)getResourceEnvRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__MESSAGE_DESTINATION_REFS:
				return ((InternalEList<?>)getMessageDestinationRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__PERSISTENCE_CONTEXT_REFS:
				return ((InternalEList<?>)getPersistenceContextRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__PERSISTENCE_UNIT_REFS:
				return ((InternalEList<?>)getPersistenceUnitRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__POST_CONSTRUCTS:
				return ((InternalEList<?>)getPostConstructs()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__PRE_DESTROYS:
				return ((InternalEList<?>)getPreDestroys()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__DATA_SOURCE:
				return ((InternalEList<?>)getDataSource()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__JMS_CONNECTION_FACTORY:
				return ((InternalEList<?>)getJmsConnectionFactory()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__JMS_DESTINATION:
				return ((InternalEList<?>)getJmsDestination()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__MAIL_SESSION:
				return ((InternalEList<?>)getMailSession()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__CONNECTION_FACTORY:
				return ((InternalEList<?>)getConnectionFactory()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__ADMINISTERED_OBJECT:
				return ((InternalEList<?>)getAdministeredObject()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__POST_ACTIVATES:
				return ((InternalEList<?>)getPostActivates()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__PRE_PASSIVATES:
				return ((InternalEList<?>)getPrePassivates()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__SECURITY_ROLE_REFS:
				return ((InternalEList<?>)getSecurityRoleRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__SECURITY_IDENTITIES:
				return basicSetSecurityIdentities(null, msgs);
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
			case EjbPackage.SESSION_BEAN__DESCRIPTIONS:
				return getDescriptions();
			case EjbPackage.SESSION_BEAN__DISPLAY_NAMES:
				return getDisplayNames();
			case EjbPackage.SESSION_BEAN__ICONS:
				return getIcons();
			case EjbPackage.SESSION_BEAN__EJB_NAME:
				return getEjbName();
			case EjbPackage.SESSION_BEAN__MAPPED_NAME:
				return getMappedName();
			case EjbPackage.SESSION_BEAN__HOME:
				return getHome();
			case EjbPackage.SESSION_BEAN__REMOTE:
				return getRemote();
			case EjbPackage.SESSION_BEAN__LOCAL_HOME:
				return getLocalHome();
			case EjbPackage.SESSION_BEAN__LOCAL:
				return getLocal();
			case EjbPackage.SESSION_BEAN__BUSINESS_LOCALS:
				return getBusinessLocals();
			case EjbPackage.SESSION_BEAN__BUSINESS_REMOTES:
				return getBusinessRemotes();
			case EjbPackage.SESSION_BEAN__LOCAL_BEAN:
				return getLocalBean();
			case EjbPackage.SESSION_BEAN__SERVICE_ENDPOINT:
				return getServiceEndpoint();
			case EjbPackage.SESSION_BEAN__EJB_CLASS:
				return getEjbClass();
			case EjbPackage.SESSION_BEAN__SESSION_TYPE:
				return getSessionType();
			case EjbPackage.SESSION_BEAN__STATEFUL_TIMEOUT:
				return getStatefulTimeout();
			case EjbPackage.SESSION_BEAN__TIMEOUT_METHOD:
				return getTimeoutMethod();
			case EjbPackage.SESSION_BEAN__TIMER:
				return getTimer();
			case EjbPackage.SESSION_BEAN__INIT_ON_STARTUP:
				return isInitOnStartup();
			case EjbPackage.SESSION_BEAN__CONCURRENCY_MANAGEMENT_TYPE:
				return getConcurrencyManagementType();
			case EjbPackage.SESSION_BEAN__CONCURRENT_METHOD:
				return getConcurrentMethod();
			case EjbPackage.SESSION_BEAN__DEPENDS_ON:
				return getDependsOn();
			case EjbPackage.SESSION_BEAN__INIT_METHODS:
				return getInitMethods();
			case EjbPackage.SESSION_BEAN__REMOVE_METHODS:
				return getRemoveMethods();
			case EjbPackage.SESSION_BEAN__ASYNC_METHOD:
				return getAsyncMethod();
			case EjbPackage.SESSION_BEAN__TRANSACTION_TYPE:
				return getTransactionType();
			case EjbPackage.SESSION_BEAN__AFTER_BEGIN_METHOD:
				return getAfterBeginMethod();
			case EjbPackage.SESSION_BEAN__BEFORE_COMPLETION_METHOD:
				return getBeforeCompletionMethod();
			case EjbPackage.SESSION_BEAN__AFTER_COMPLETION_METHOD:
				return getAfterCompletionMethod();
			case EjbPackage.SESSION_BEAN__AROUND_INVOKES:
				return getAroundInvokes();
			case EjbPackage.SESSION_BEAN__AROUND_TIMEOUTS:
				return getAroundTimeouts();
			case EjbPackage.SESSION_BEAN__ENV_ENTRIES:
				return getEnvEntries();
			case EjbPackage.SESSION_BEAN__EJB_REFS:
				return getEjbRefs();
			case EjbPackage.SESSION_BEAN__EJB_LOCAL_REFS:
				return getEjbLocalRefs();
			case EjbPackage.SESSION_BEAN__SERVICE_REFS:
				return getServiceRefs();
			case EjbPackage.SESSION_BEAN__RESOURCE_REFS:
				return getResourceRefs();
			case EjbPackage.SESSION_BEAN__RESOURCE_ENV_REFS:
				return getResourceEnvRefs();
			case EjbPackage.SESSION_BEAN__MESSAGE_DESTINATION_REFS:
				return getMessageDestinationRefs();
			case EjbPackage.SESSION_BEAN__PERSISTENCE_CONTEXT_REFS:
				return getPersistenceContextRefs();
			case EjbPackage.SESSION_BEAN__PERSISTENCE_UNIT_REFS:
				return getPersistenceUnitRefs();
			case EjbPackage.SESSION_BEAN__POST_CONSTRUCTS:
				return getPostConstructs();
			case EjbPackage.SESSION_BEAN__PRE_DESTROYS:
				return getPreDestroys();
			case EjbPackage.SESSION_BEAN__DATA_SOURCE:
				return getDataSource();
			case EjbPackage.SESSION_BEAN__JMS_CONNECTION_FACTORY:
				return getJmsConnectionFactory();
			case EjbPackage.SESSION_BEAN__JMS_DESTINATION:
				return getJmsDestination();
			case EjbPackage.SESSION_BEAN__MAIL_SESSION:
				return getMailSession();
			case EjbPackage.SESSION_BEAN__CONNECTION_FACTORY:
				return getConnectionFactory();
			case EjbPackage.SESSION_BEAN__ADMINISTERED_OBJECT:
				return getAdministeredObject();
			case EjbPackage.SESSION_BEAN__POST_ACTIVATES:
				return getPostActivates();
			case EjbPackage.SESSION_BEAN__PRE_PASSIVATES:
				return getPrePassivates();
			case EjbPackage.SESSION_BEAN__SECURITY_ROLE_REFS:
				return getSecurityRoleRefs();
			case EjbPackage.SESSION_BEAN__SECURITY_IDENTITIES:
				return getSecurityIdentities();
			case EjbPackage.SESSION_BEAN__PASSIVATION_CAPABLE:
				return isPassivationCapable();
			case EjbPackage.SESSION_BEAN__ID:
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
			case EjbPackage.SESSION_BEAN__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection<? extends Description>)newValue);
				return;
			case EjbPackage.SESSION_BEAN__DISPLAY_NAMES:
				getDisplayNames().clear();
				getDisplayNames().addAll((Collection<? extends DisplayName>)newValue);
				return;
			case EjbPackage.SESSION_BEAN__ICONS:
				getIcons().clear();
				getIcons().addAll((Collection<? extends Icon>)newValue);
				return;
			case EjbPackage.SESSION_BEAN__EJB_NAME:
				setEjbName((String)newValue);
				return;
			case EjbPackage.SESSION_BEAN__MAPPED_NAME:
				setMappedName((String)newValue);
				return;
			case EjbPackage.SESSION_BEAN__HOME:
				setHome((String)newValue);
				return;
			case EjbPackage.SESSION_BEAN__REMOTE:
				setRemote((String)newValue);
				return;
			case EjbPackage.SESSION_BEAN__LOCAL_HOME:
				setLocalHome((String)newValue);
				return;
			case EjbPackage.SESSION_BEAN__LOCAL:
				setLocal((String)newValue);
				return;
			case EjbPackage.SESSION_BEAN__BUSINESS_LOCALS:
				getBusinessLocals().clear();
				getBusinessLocals().addAll((Collection<? extends String>)newValue);
				return;
			case EjbPackage.SESSION_BEAN__BUSINESS_REMOTES:
				getBusinessRemotes().clear();
				getBusinessRemotes().addAll((Collection<? extends String>)newValue);
				return;
			case EjbPackage.SESSION_BEAN__LOCAL_BEAN:
				setLocalBean((EmptyType)newValue);
				return;
			case EjbPackage.SESSION_BEAN__SERVICE_ENDPOINT:
				setServiceEndpoint((String)newValue);
				return;
			case EjbPackage.SESSION_BEAN__EJB_CLASS:
				setEjbClass((String)newValue);
				return;
			case EjbPackage.SESSION_BEAN__SESSION_TYPE:
				setSessionType((SessionType)newValue);
				return;
			case EjbPackage.SESSION_BEAN__STATEFUL_TIMEOUT:
				setStatefulTimeout((StatefulTimeoutType)newValue);
				return;
			case EjbPackage.SESSION_BEAN__TIMEOUT_METHOD:
				setTimeoutMethod((NamedMethodType)newValue);
				return;
			case EjbPackage.SESSION_BEAN__TIMER:
				getTimer().clear();
				getTimer().addAll((Collection<? extends TimerType>)newValue);
				return;
			case EjbPackage.SESSION_BEAN__INIT_ON_STARTUP:
				setInitOnStartup((Boolean)newValue);
				return;
			case EjbPackage.SESSION_BEAN__CONCURRENCY_MANAGEMENT_TYPE:
				setConcurrencyManagementType((ConcurrencyManagementTypeType)newValue);
				return;
			case EjbPackage.SESSION_BEAN__CONCURRENT_METHOD:
				getConcurrentMethod().clear();
				getConcurrentMethod().addAll((Collection<? extends ConcurrentMethodType>)newValue);
				return;
			case EjbPackage.SESSION_BEAN__DEPENDS_ON:
				setDependsOn((DependsOnType)newValue);
				return;
			case EjbPackage.SESSION_BEAN__INIT_METHODS:
				getInitMethods().clear();
				getInitMethods().addAll((Collection<? extends InitMethodType>)newValue);
				return;
			case EjbPackage.SESSION_BEAN__REMOVE_METHODS:
				getRemoveMethods().clear();
				getRemoveMethods().addAll((Collection<? extends RemoveMethodType>)newValue);
				return;
			case EjbPackage.SESSION_BEAN__ASYNC_METHOD:
				getAsyncMethod().clear();
				getAsyncMethod().addAll((Collection<? extends AsyncMethodType>)newValue);
				return;
			case EjbPackage.SESSION_BEAN__TRANSACTION_TYPE:
				setTransactionType((TransactionType)newValue);
				return;
			case EjbPackage.SESSION_BEAN__AFTER_BEGIN_METHOD:
				setAfterBeginMethod((NamedMethodType)newValue);
				return;
			case EjbPackage.SESSION_BEAN__BEFORE_COMPLETION_METHOD:
				setBeforeCompletionMethod((NamedMethodType)newValue);
				return;
			case EjbPackage.SESSION_BEAN__AFTER_COMPLETION_METHOD:
				setAfterCompletionMethod((NamedMethodType)newValue);
				return;
			case EjbPackage.SESSION_BEAN__AROUND_INVOKES:
				getAroundInvokes().clear();
				getAroundInvokes().addAll((Collection<? extends AroundInvokeType>)newValue);
				return;
			case EjbPackage.SESSION_BEAN__AROUND_TIMEOUTS:
				getAroundTimeouts().clear();
				getAroundTimeouts().addAll((Collection<? extends AroundTimeoutType>)newValue);
				return;
			case EjbPackage.SESSION_BEAN__ENV_ENTRIES:
				getEnvEntries().clear();
				getEnvEntries().addAll((Collection<? extends EnvEntry>)newValue);
				return;
			case EjbPackage.SESSION_BEAN__EJB_REFS:
				getEjbRefs().clear();
				getEjbRefs().addAll((Collection<? extends EjbRef>)newValue);
				return;
			case EjbPackage.SESSION_BEAN__EJB_LOCAL_REFS:
				getEjbLocalRefs().clear();
				getEjbLocalRefs().addAll((Collection<? extends EjbLocalRef>)newValue);
				return;
			case EjbPackage.SESSION_BEAN__SERVICE_REFS:
				getServiceRefs().clear();
				getServiceRefs().addAll((Collection<? extends ServiceRef>)newValue);
				return;
			case EjbPackage.SESSION_BEAN__RESOURCE_REFS:
				getResourceRefs().clear();
				getResourceRefs().addAll((Collection<? extends ResourceRef>)newValue);
				return;
			case EjbPackage.SESSION_BEAN__RESOURCE_ENV_REFS:
				getResourceEnvRefs().clear();
				getResourceEnvRefs().addAll((Collection<? extends ResourceEnvRef>)newValue);
				return;
			case EjbPackage.SESSION_BEAN__MESSAGE_DESTINATION_REFS:
				getMessageDestinationRefs().clear();
				getMessageDestinationRefs().addAll((Collection<? extends MessageDestinationRef>)newValue);
				return;
			case EjbPackage.SESSION_BEAN__PERSISTENCE_CONTEXT_REFS:
				getPersistenceContextRefs().clear();
				getPersistenceContextRefs().addAll((Collection<? extends PersistenceContextRef>)newValue);
				return;
			case EjbPackage.SESSION_BEAN__PERSISTENCE_UNIT_REFS:
				getPersistenceUnitRefs().clear();
				getPersistenceUnitRefs().addAll((Collection<? extends PersistenceUnitRef>)newValue);
				return;
			case EjbPackage.SESSION_BEAN__POST_CONSTRUCTS:
				getPostConstructs().clear();
				getPostConstructs().addAll((Collection<? extends LifecycleCallback>)newValue);
				return;
			case EjbPackage.SESSION_BEAN__PRE_DESTROYS:
				getPreDestroys().clear();
				getPreDestroys().addAll((Collection<? extends LifecycleCallback>)newValue);
				return;
			case EjbPackage.SESSION_BEAN__DATA_SOURCE:
				getDataSource().clear();
				getDataSource().addAll((Collection<? extends DataSourceType>)newValue);
				return;
			case EjbPackage.SESSION_BEAN__JMS_CONNECTION_FACTORY:
				getJmsConnectionFactory().clear();
				getJmsConnectionFactory().addAll((Collection<? extends JmsConnectionFactoryType>)newValue);
				return;
			case EjbPackage.SESSION_BEAN__JMS_DESTINATION:
				getJmsDestination().clear();
				getJmsDestination().addAll((Collection<? extends JmsDestinationType>)newValue);
				return;
			case EjbPackage.SESSION_BEAN__MAIL_SESSION:
				getMailSession().clear();
				getMailSession().addAll((Collection<? extends MailSessionType>)newValue);
				return;
			case EjbPackage.SESSION_BEAN__CONNECTION_FACTORY:
				getConnectionFactory().clear();
				getConnectionFactory().addAll((Collection<? extends ConnectionFactoryResourceType>)newValue);
				return;
			case EjbPackage.SESSION_BEAN__ADMINISTERED_OBJECT:
				getAdministeredObject().clear();
				getAdministeredObject().addAll((Collection<? extends AdministeredObjectType>)newValue);
				return;
			case EjbPackage.SESSION_BEAN__POST_ACTIVATES:
				getPostActivates().clear();
				getPostActivates().addAll((Collection<? extends LifecycleCallback>)newValue);
				return;
			case EjbPackage.SESSION_BEAN__PRE_PASSIVATES:
				getPrePassivates().clear();
				getPrePassivates().addAll((Collection<? extends LifecycleCallback>)newValue);
				return;
			case EjbPackage.SESSION_BEAN__SECURITY_ROLE_REFS:
				getSecurityRoleRefs().clear();
				getSecurityRoleRefs().addAll((Collection<? extends SecurityRoleRef>)newValue);
				return;
			case EjbPackage.SESSION_BEAN__SECURITY_IDENTITIES:
				setSecurityIdentities((SecurityIdentityType)newValue);
				return;
			case EjbPackage.SESSION_BEAN__PASSIVATION_CAPABLE:
				setPassivationCapable((Boolean)newValue);
				return;
			case EjbPackage.SESSION_BEAN__ID:
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
			case EjbPackage.SESSION_BEAN__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case EjbPackage.SESSION_BEAN__DISPLAY_NAMES:
				getDisplayNames().clear();
				return;
			case EjbPackage.SESSION_BEAN__ICONS:
				getIcons().clear();
				return;
			case EjbPackage.SESSION_BEAN__EJB_NAME:
				setEjbName(EJB_NAME_EDEFAULT);
				return;
			case EjbPackage.SESSION_BEAN__MAPPED_NAME:
				setMappedName(MAPPED_NAME_EDEFAULT);
				return;
			case EjbPackage.SESSION_BEAN__HOME:
				setHome(HOME_EDEFAULT);
				return;
			case EjbPackage.SESSION_BEAN__REMOTE:
				setRemote(REMOTE_EDEFAULT);
				return;
			case EjbPackage.SESSION_BEAN__LOCAL_HOME:
				setLocalHome(LOCAL_HOME_EDEFAULT);
				return;
			case EjbPackage.SESSION_BEAN__LOCAL:
				setLocal(LOCAL_EDEFAULT);
				return;
			case EjbPackage.SESSION_BEAN__BUSINESS_LOCALS:
				getBusinessLocals().clear();
				return;
			case EjbPackage.SESSION_BEAN__BUSINESS_REMOTES:
				getBusinessRemotes().clear();
				return;
			case EjbPackage.SESSION_BEAN__LOCAL_BEAN:
				setLocalBean((EmptyType)null);
				return;
			case EjbPackage.SESSION_BEAN__SERVICE_ENDPOINT:
				setServiceEndpoint(SERVICE_ENDPOINT_EDEFAULT);
				return;
			case EjbPackage.SESSION_BEAN__EJB_CLASS:
				setEjbClass(EJB_CLASS_EDEFAULT);
				return;
			case EjbPackage.SESSION_BEAN__SESSION_TYPE:
				unsetSessionType();
				return;
			case EjbPackage.SESSION_BEAN__STATEFUL_TIMEOUT:
				setStatefulTimeout((StatefulTimeoutType)null);
				return;
			case EjbPackage.SESSION_BEAN__TIMEOUT_METHOD:
				setTimeoutMethod((NamedMethodType)null);
				return;
			case EjbPackage.SESSION_BEAN__TIMER:
				getTimer().clear();
				return;
			case EjbPackage.SESSION_BEAN__INIT_ON_STARTUP:
				unsetInitOnStartup();
				return;
			case EjbPackage.SESSION_BEAN__CONCURRENCY_MANAGEMENT_TYPE:
				unsetConcurrencyManagementType();
				return;
			case EjbPackage.SESSION_BEAN__CONCURRENT_METHOD:
				getConcurrentMethod().clear();
				return;
			case EjbPackage.SESSION_BEAN__DEPENDS_ON:
				setDependsOn((DependsOnType)null);
				return;
			case EjbPackage.SESSION_BEAN__INIT_METHODS:
				getInitMethods().clear();
				return;
			case EjbPackage.SESSION_BEAN__REMOVE_METHODS:
				getRemoveMethods().clear();
				return;
			case EjbPackage.SESSION_BEAN__ASYNC_METHOD:
				getAsyncMethod().clear();
				return;
			case EjbPackage.SESSION_BEAN__TRANSACTION_TYPE:
				unsetTransactionType();
				return;
			case EjbPackage.SESSION_BEAN__AFTER_BEGIN_METHOD:
				setAfterBeginMethod((NamedMethodType)null);
				return;
			case EjbPackage.SESSION_BEAN__BEFORE_COMPLETION_METHOD:
				setBeforeCompletionMethod((NamedMethodType)null);
				return;
			case EjbPackage.SESSION_BEAN__AFTER_COMPLETION_METHOD:
				setAfterCompletionMethod((NamedMethodType)null);
				return;
			case EjbPackage.SESSION_BEAN__AROUND_INVOKES:
				getAroundInvokes().clear();
				return;
			case EjbPackage.SESSION_BEAN__AROUND_TIMEOUTS:
				getAroundTimeouts().clear();
				return;
			case EjbPackage.SESSION_BEAN__ENV_ENTRIES:
				getEnvEntries().clear();
				return;
			case EjbPackage.SESSION_BEAN__EJB_REFS:
				getEjbRefs().clear();
				return;
			case EjbPackage.SESSION_BEAN__EJB_LOCAL_REFS:
				getEjbLocalRefs().clear();
				return;
			case EjbPackage.SESSION_BEAN__SERVICE_REFS:
				getServiceRefs().clear();
				return;
			case EjbPackage.SESSION_BEAN__RESOURCE_REFS:
				getResourceRefs().clear();
				return;
			case EjbPackage.SESSION_BEAN__RESOURCE_ENV_REFS:
				getResourceEnvRefs().clear();
				return;
			case EjbPackage.SESSION_BEAN__MESSAGE_DESTINATION_REFS:
				getMessageDestinationRefs().clear();
				return;
			case EjbPackage.SESSION_BEAN__PERSISTENCE_CONTEXT_REFS:
				getPersistenceContextRefs().clear();
				return;
			case EjbPackage.SESSION_BEAN__PERSISTENCE_UNIT_REFS:
				getPersistenceUnitRefs().clear();
				return;
			case EjbPackage.SESSION_BEAN__POST_CONSTRUCTS:
				getPostConstructs().clear();
				return;
			case EjbPackage.SESSION_BEAN__PRE_DESTROYS:
				getPreDestroys().clear();
				return;
			case EjbPackage.SESSION_BEAN__DATA_SOURCE:
				getDataSource().clear();
				return;
			case EjbPackage.SESSION_BEAN__JMS_CONNECTION_FACTORY:
				getJmsConnectionFactory().clear();
				return;
			case EjbPackage.SESSION_BEAN__JMS_DESTINATION:
				getJmsDestination().clear();
				return;
			case EjbPackage.SESSION_BEAN__MAIL_SESSION:
				getMailSession().clear();
				return;
			case EjbPackage.SESSION_BEAN__CONNECTION_FACTORY:
				getConnectionFactory().clear();
				return;
			case EjbPackage.SESSION_BEAN__ADMINISTERED_OBJECT:
				getAdministeredObject().clear();
				return;
			case EjbPackage.SESSION_BEAN__POST_ACTIVATES:
				getPostActivates().clear();
				return;
			case EjbPackage.SESSION_BEAN__PRE_PASSIVATES:
				getPrePassivates().clear();
				return;
			case EjbPackage.SESSION_BEAN__SECURITY_ROLE_REFS:
				getSecurityRoleRefs().clear();
				return;
			case EjbPackage.SESSION_BEAN__SECURITY_IDENTITIES:
				setSecurityIdentities((SecurityIdentityType)null);
				return;
			case EjbPackage.SESSION_BEAN__PASSIVATION_CAPABLE:
				unsetPassivationCapable();
				return;
			case EjbPackage.SESSION_BEAN__ID:
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
			case EjbPackage.SESSION_BEAN__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case EjbPackage.SESSION_BEAN__DISPLAY_NAMES:
				return displayNames != null && !displayNames.isEmpty();
			case EjbPackage.SESSION_BEAN__ICONS:
				return icons != null && !icons.isEmpty();
			case EjbPackage.SESSION_BEAN__EJB_NAME:
				return EJB_NAME_EDEFAULT == null ? ejbName != null : !EJB_NAME_EDEFAULT.equals(ejbName);
			case EjbPackage.SESSION_BEAN__MAPPED_NAME:
				return MAPPED_NAME_EDEFAULT == null ? mappedName != null : !MAPPED_NAME_EDEFAULT.equals(mappedName);
			case EjbPackage.SESSION_BEAN__HOME:
				return HOME_EDEFAULT == null ? home != null : !HOME_EDEFAULT.equals(home);
			case EjbPackage.SESSION_BEAN__REMOTE:
				return REMOTE_EDEFAULT == null ? remote != null : !REMOTE_EDEFAULT.equals(remote);
			case EjbPackage.SESSION_BEAN__LOCAL_HOME:
				return LOCAL_HOME_EDEFAULT == null ? localHome != null : !LOCAL_HOME_EDEFAULT.equals(localHome);
			case EjbPackage.SESSION_BEAN__LOCAL:
				return LOCAL_EDEFAULT == null ? local != null : !LOCAL_EDEFAULT.equals(local);
			case EjbPackage.SESSION_BEAN__BUSINESS_LOCALS:
				return businessLocals != null && !businessLocals.isEmpty();
			case EjbPackage.SESSION_BEAN__BUSINESS_REMOTES:
				return businessRemotes != null && !businessRemotes.isEmpty();
			case EjbPackage.SESSION_BEAN__LOCAL_BEAN:
				return localBean != null;
			case EjbPackage.SESSION_BEAN__SERVICE_ENDPOINT:
				return SERVICE_ENDPOINT_EDEFAULT == null ? serviceEndpoint != null : !SERVICE_ENDPOINT_EDEFAULT.equals(serviceEndpoint);
			case EjbPackage.SESSION_BEAN__EJB_CLASS:
				return EJB_CLASS_EDEFAULT == null ? ejbClass != null : !EJB_CLASS_EDEFAULT.equals(ejbClass);
			case EjbPackage.SESSION_BEAN__SESSION_TYPE:
				return isSetSessionType();
			case EjbPackage.SESSION_BEAN__STATEFUL_TIMEOUT:
				return statefulTimeout != null;
			case EjbPackage.SESSION_BEAN__TIMEOUT_METHOD:
				return timeoutMethod != null;
			case EjbPackage.SESSION_BEAN__TIMER:
				return timer != null && !timer.isEmpty();
			case EjbPackage.SESSION_BEAN__INIT_ON_STARTUP:
				return isSetInitOnStartup();
			case EjbPackage.SESSION_BEAN__CONCURRENCY_MANAGEMENT_TYPE:
				return isSetConcurrencyManagementType();
			case EjbPackage.SESSION_BEAN__CONCURRENT_METHOD:
				return concurrentMethod != null && !concurrentMethod.isEmpty();
			case EjbPackage.SESSION_BEAN__DEPENDS_ON:
				return dependsOn != null;
			case EjbPackage.SESSION_BEAN__INIT_METHODS:
				return initMethods != null && !initMethods.isEmpty();
			case EjbPackage.SESSION_BEAN__REMOVE_METHODS:
				return removeMethods != null && !removeMethods.isEmpty();
			case EjbPackage.SESSION_BEAN__ASYNC_METHOD:
				return asyncMethod != null && !asyncMethod.isEmpty();
			case EjbPackage.SESSION_BEAN__TRANSACTION_TYPE:
				return isSetTransactionType();
			case EjbPackage.SESSION_BEAN__AFTER_BEGIN_METHOD:
				return afterBeginMethod != null;
			case EjbPackage.SESSION_BEAN__BEFORE_COMPLETION_METHOD:
				return beforeCompletionMethod != null;
			case EjbPackage.SESSION_BEAN__AFTER_COMPLETION_METHOD:
				return afterCompletionMethod != null;
			case EjbPackage.SESSION_BEAN__AROUND_INVOKES:
				return aroundInvokes != null && !aroundInvokes.isEmpty();
			case EjbPackage.SESSION_BEAN__AROUND_TIMEOUTS:
				return aroundTimeouts != null && !aroundTimeouts.isEmpty();
			case EjbPackage.SESSION_BEAN__ENV_ENTRIES:
				return envEntries != null && !envEntries.isEmpty();
			case EjbPackage.SESSION_BEAN__EJB_REFS:
				return ejbRefs != null && !ejbRefs.isEmpty();
			case EjbPackage.SESSION_BEAN__EJB_LOCAL_REFS:
				return ejbLocalRefs != null && !ejbLocalRefs.isEmpty();
			case EjbPackage.SESSION_BEAN__SERVICE_REFS:
				return serviceRefs != null && !serviceRefs.isEmpty();
			case EjbPackage.SESSION_BEAN__RESOURCE_REFS:
				return resourceRefs != null && !resourceRefs.isEmpty();
			case EjbPackage.SESSION_BEAN__RESOURCE_ENV_REFS:
				return resourceEnvRefs != null && !resourceEnvRefs.isEmpty();
			case EjbPackage.SESSION_BEAN__MESSAGE_DESTINATION_REFS:
				return messageDestinationRefs != null && !messageDestinationRefs.isEmpty();
			case EjbPackage.SESSION_BEAN__PERSISTENCE_CONTEXT_REFS:
				return persistenceContextRefs != null && !persistenceContextRefs.isEmpty();
			case EjbPackage.SESSION_BEAN__PERSISTENCE_UNIT_REFS:
				return persistenceUnitRefs != null && !persistenceUnitRefs.isEmpty();
			case EjbPackage.SESSION_BEAN__POST_CONSTRUCTS:
				return postConstructs != null && !postConstructs.isEmpty();
			case EjbPackage.SESSION_BEAN__PRE_DESTROYS:
				return preDestroys != null && !preDestroys.isEmpty();
			case EjbPackage.SESSION_BEAN__DATA_SOURCE:
				return dataSource != null && !dataSource.isEmpty();
			case EjbPackage.SESSION_BEAN__JMS_CONNECTION_FACTORY:
				return jmsConnectionFactory != null && !jmsConnectionFactory.isEmpty();
			case EjbPackage.SESSION_BEAN__JMS_DESTINATION:
				return jmsDestination != null && !jmsDestination.isEmpty();
			case EjbPackage.SESSION_BEAN__MAIL_SESSION:
				return mailSession != null && !mailSession.isEmpty();
			case EjbPackage.SESSION_BEAN__CONNECTION_FACTORY:
				return connectionFactory != null && !connectionFactory.isEmpty();
			case EjbPackage.SESSION_BEAN__ADMINISTERED_OBJECT:
				return administeredObject != null && !administeredObject.isEmpty();
			case EjbPackage.SESSION_BEAN__POST_ACTIVATES:
				return postActivates != null && !postActivates.isEmpty();
			case EjbPackage.SESSION_BEAN__PRE_PASSIVATES:
				return prePassivates != null && !prePassivates.isEmpty();
			case EjbPackage.SESSION_BEAN__SECURITY_ROLE_REFS:
				return securityRoleRefs != null && !securityRoleRefs.isEmpty();
			case EjbPackage.SESSION_BEAN__SECURITY_IDENTITIES:
				return securityIdentities != null;
			case EjbPackage.SESSION_BEAN__PASSIVATION_CAPABLE:
				return isSetPassivationCapable();
			case EjbPackage.SESSION_BEAN__ID:
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
		result.append(", home: "); //$NON-NLS-1$
		result.append(home);
		result.append(", remote: "); //$NON-NLS-1$
		result.append(remote);
		result.append(", localHome: "); //$NON-NLS-1$
		result.append(localHome);
		result.append(", local: "); //$NON-NLS-1$
		result.append(local);
		result.append(", businessLocals: "); //$NON-NLS-1$
		result.append(businessLocals);
		result.append(", businessRemotes: "); //$NON-NLS-1$
		result.append(businessRemotes);
		result.append(", serviceEndpoint: "); //$NON-NLS-1$
		result.append(serviceEndpoint);
		result.append(", ejbClass: "); //$NON-NLS-1$
		result.append(ejbClass);
		result.append(", sessionType: "); //$NON-NLS-1$
		if (sessionTypeESet) result.append(sessionType); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", initOnStartup: "); //$NON-NLS-1$
		if (initOnStartupESet) result.append(initOnStartup); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", concurrencyManagementType: "); //$NON-NLS-1$
		if (concurrencyManagementTypeESet) result.append(concurrencyManagementType); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", transactionType: "); //$NON-NLS-1$
		if (transactionTypeESet) result.append(transactionType); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", passivationCapable: "); //$NON-NLS-1$
		if (passivationCapableESet) result.append(passivationCapable); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //SessionBeanImpl