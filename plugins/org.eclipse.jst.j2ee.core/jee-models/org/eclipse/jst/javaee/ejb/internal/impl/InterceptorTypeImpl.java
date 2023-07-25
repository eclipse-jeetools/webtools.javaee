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
import org.eclipse.jst.javaee.core.EjbLocalRef;
import org.eclipse.jst.javaee.core.EjbRef;
import org.eclipse.jst.javaee.core.EnvEntry;
import org.eclipse.jst.javaee.core.JmsConnectionFactoryType;
import org.eclipse.jst.javaee.core.JmsDestinationType;
import org.eclipse.jst.javaee.core.LifecycleCallback;
import org.eclipse.jst.javaee.core.MailSessionType;
import org.eclipse.jst.javaee.core.MessageDestinationRef;
import org.eclipse.jst.javaee.core.PersistenceContextRef;
import org.eclipse.jst.javaee.core.PersistenceUnitRef;
import org.eclipse.jst.javaee.core.ResourceEnvRef;
import org.eclipse.jst.javaee.core.ResourceRef;
import org.eclipse.jst.javaee.core.ServiceRef;

import org.eclipse.jst.javaee.ejb.AroundInvokeType;
import org.eclipse.jst.javaee.ejb.AroundTimeoutType;
import org.eclipse.jst.javaee.ejb.InterceptorType;

import org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Interceptor Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getInterceptorClass <em>Interceptor Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getAroundInvokes <em>Around Invokes</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getAroundTimeouts <em>Around Timeouts</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getAroundConstruct <em>Around Construct</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getEnvEntries <em>Env Entries</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getEjbRefs <em>Ejb Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getEjbLocalRefs <em>Ejb Local Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getServiceRefs <em>Service Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getResourceRefs <em>Resource Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getResourceEnvRefs <em>Resource Env Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getMessageDestinationRefs <em>Message Destination Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getPersistenceContextRefs <em>Persistence Context Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getPersistenceUnitRefs <em>Persistence Unit Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getPostConstructs <em>Post Constructs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getPreDestroys <em>Pre Destroys</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getDataSource <em>Data Source</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getJmsConnectionFactory <em>Jms Connection Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getJmsDestination <em>Jms Destination</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getMailSession <em>Mail Session</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getConnectionFactory <em>Connection Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getAdministeredObject <em>Administered Object</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getPostActivates <em>Post Activates</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getPrePassivates <em>Pre Passivates</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InterceptorTypeImpl extends EObjectImpl implements InterceptorType {
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
	 * The default value of the '{@link #getInterceptorClass() <em>Interceptor Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterceptorClass()
	 * @generated
	 * @ordered
	 */
	protected static final String INTERCEPTOR_CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInterceptorClass() <em>Interceptor Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterceptorClass()
	 * @generated
	 * @ordered
	 */
	protected String interceptorClass = INTERCEPTOR_CLASS_EDEFAULT;

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
	 * The cached value of the '{@link #getAroundConstruct() <em>Around Construct</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAroundConstruct()
	 * @generated
	 * @ordered
	 */
	protected EList<LifecycleCallback> aroundConstruct;

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
	protected InterceptorTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EjbPackage.Literals.INTERCEPTOR_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Description> getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList<Description>(Description.class, this, EjbPackage.INTERCEPTOR_TYPE__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getInterceptorClass() {
		return interceptorClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setInterceptorClass(String newInterceptorClass) {
		String oldInterceptorClass = interceptorClass;
		interceptorClass = newInterceptorClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.INTERCEPTOR_TYPE__INTERCEPTOR_CLASS, oldInterceptorClass, interceptorClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<AroundInvokeType> getAroundInvokes() {
		if (aroundInvokes == null) {
			aroundInvokes = new EObjectContainmentEList<AroundInvokeType>(AroundInvokeType.class, this, EjbPackage.INTERCEPTOR_TYPE__AROUND_INVOKES);
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
			aroundTimeouts = new EObjectContainmentEList<AroundTimeoutType>(AroundTimeoutType.class, this, EjbPackage.INTERCEPTOR_TYPE__AROUND_TIMEOUTS);
		}
		return aroundTimeouts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<LifecycleCallback> getAroundConstruct() {
		if (aroundConstruct == null) {
			aroundConstruct = new EObjectContainmentEList<LifecycleCallback>(LifecycleCallback.class, this, EjbPackage.INTERCEPTOR_TYPE__AROUND_CONSTRUCT);
		}
		return aroundConstruct;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<EnvEntry> getEnvEntries() {
		if (envEntries == null) {
			envEntries = new EObjectContainmentEList<EnvEntry>(EnvEntry.class, this, EjbPackage.INTERCEPTOR_TYPE__ENV_ENTRIES);
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
			ejbRefs = new EObjectContainmentEList<EjbRef>(EjbRef.class, this, EjbPackage.INTERCEPTOR_TYPE__EJB_REFS);
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
			ejbLocalRefs = new EObjectContainmentEList<EjbLocalRef>(EjbLocalRef.class, this, EjbPackage.INTERCEPTOR_TYPE__EJB_LOCAL_REFS);
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
			serviceRefs = new EObjectContainmentEList<ServiceRef>(ServiceRef.class, this, EjbPackage.INTERCEPTOR_TYPE__SERVICE_REFS);
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
			resourceRefs = new EObjectContainmentEList<ResourceRef>(ResourceRef.class, this, EjbPackage.INTERCEPTOR_TYPE__RESOURCE_REFS);
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
			resourceEnvRefs = new EObjectContainmentEList<ResourceEnvRef>(ResourceEnvRef.class, this, EjbPackage.INTERCEPTOR_TYPE__RESOURCE_ENV_REFS);
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
			messageDestinationRefs = new EObjectContainmentEList<MessageDestinationRef>(MessageDestinationRef.class, this, EjbPackage.INTERCEPTOR_TYPE__MESSAGE_DESTINATION_REFS);
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
			persistenceContextRefs = new EObjectContainmentEList<PersistenceContextRef>(PersistenceContextRef.class, this, EjbPackage.INTERCEPTOR_TYPE__PERSISTENCE_CONTEXT_REFS);
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
			persistenceUnitRefs = new EObjectContainmentEList<PersistenceUnitRef>(PersistenceUnitRef.class, this, EjbPackage.INTERCEPTOR_TYPE__PERSISTENCE_UNIT_REFS);
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
			postConstructs = new EObjectContainmentEList<LifecycleCallback>(LifecycleCallback.class, this, EjbPackage.INTERCEPTOR_TYPE__POST_CONSTRUCTS);
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
			preDestroys = new EObjectContainmentEList<LifecycleCallback>(LifecycleCallback.class, this, EjbPackage.INTERCEPTOR_TYPE__PRE_DESTROYS);
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
			dataSource = new EObjectContainmentEList<DataSourceType>(DataSourceType.class, this, EjbPackage.INTERCEPTOR_TYPE__DATA_SOURCE);
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
			jmsConnectionFactory = new EObjectContainmentEList<JmsConnectionFactoryType>(JmsConnectionFactoryType.class, this, EjbPackage.INTERCEPTOR_TYPE__JMS_CONNECTION_FACTORY);
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
			jmsDestination = new EObjectContainmentEList<JmsDestinationType>(JmsDestinationType.class, this, EjbPackage.INTERCEPTOR_TYPE__JMS_DESTINATION);
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
			mailSession = new EObjectContainmentEList<MailSessionType>(MailSessionType.class, this, EjbPackage.INTERCEPTOR_TYPE__MAIL_SESSION);
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
			connectionFactory = new EObjectContainmentEList<ConnectionFactoryResourceType>(ConnectionFactoryResourceType.class, this, EjbPackage.INTERCEPTOR_TYPE__CONNECTION_FACTORY);
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
			administeredObject = new EObjectContainmentEList<AdministeredObjectType>(AdministeredObjectType.class, this, EjbPackage.INTERCEPTOR_TYPE__ADMINISTERED_OBJECT);
		}
		return administeredObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<LifecycleCallback> getPostActivates() {
		if (postActivates == null) {
			postActivates = new EObjectContainmentEList<LifecycleCallback>(LifecycleCallback.class, this, EjbPackage.INTERCEPTOR_TYPE__POST_ACTIVATES);
		}
		return postActivates;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<LifecycleCallback> getPrePassivates() {
		if (prePassivates == null) {
			prePassivates = new EObjectContainmentEList<LifecycleCallback>(LifecycleCallback.class, this, EjbPackage.INTERCEPTOR_TYPE__PRE_PASSIVATES);
		}
		return prePassivates;
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
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.INTERCEPTOR_TYPE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EjbPackage.INTERCEPTOR_TYPE__DESCRIPTIONS:
				return ((InternalEList<?>)getDescriptions()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_TYPE__AROUND_INVOKES:
				return ((InternalEList<?>)getAroundInvokes()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_TYPE__AROUND_TIMEOUTS:
				return ((InternalEList<?>)getAroundTimeouts()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_TYPE__AROUND_CONSTRUCT:
				return ((InternalEList<?>)getAroundConstruct()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_TYPE__ENV_ENTRIES:
				return ((InternalEList<?>)getEnvEntries()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_TYPE__EJB_REFS:
				return ((InternalEList<?>)getEjbRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_TYPE__EJB_LOCAL_REFS:
				return ((InternalEList<?>)getEjbLocalRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_TYPE__SERVICE_REFS:
				return ((InternalEList<?>)getServiceRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_TYPE__RESOURCE_REFS:
				return ((InternalEList<?>)getResourceRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_TYPE__RESOURCE_ENV_REFS:
				return ((InternalEList<?>)getResourceEnvRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_TYPE__MESSAGE_DESTINATION_REFS:
				return ((InternalEList<?>)getMessageDestinationRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_TYPE__PERSISTENCE_CONTEXT_REFS:
				return ((InternalEList<?>)getPersistenceContextRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_TYPE__PERSISTENCE_UNIT_REFS:
				return ((InternalEList<?>)getPersistenceUnitRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_TYPE__POST_CONSTRUCTS:
				return ((InternalEList<?>)getPostConstructs()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_TYPE__PRE_DESTROYS:
				return ((InternalEList<?>)getPreDestroys()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_TYPE__DATA_SOURCE:
				return ((InternalEList<?>)getDataSource()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_TYPE__JMS_CONNECTION_FACTORY:
				return ((InternalEList<?>)getJmsConnectionFactory()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_TYPE__JMS_DESTINATION:
				return ((InternalEList<?>)getJmsDestination()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_TYPE__MAIL_SESSION:
				return ((InternalEList<?>)getMailSession()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_TYPE__CONNECTION_FACTORY:
				return ((InternalEList<?>)getConnectionFactory()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_TYPE__ADMINISTERED_OBJECT:
				return ((InternalEList<?>)getAdministeredObject()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_TYPE__POST_ACTIVATES:
				return ((InternalEList<?>)getPostActivates()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_TYPE__PRE_PASSIVATES:
				return ((InternalEList<?>)getPrePassivates()).basicRemove(otherEnd, msgs);
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
			case EjbPackage.INTERCEPTOR_TYPE__DESCRIPTIONS:
				return getDescriptions();
			case EjbPackage.INTERCEPTOR_TYPE__INTERCEPTOR_CLASS:
				return getInterceptorClass();
			case EjbPackage.INTERCEPTOR_TYPE__AROUND_INVOKES:
				return getAroundInvokes();
			case EjbPackage.INTERCEPTOR_TYPE__AROUND_TIMEOUTS:
				return getAroundTimeouts();
			case EjbPackage.INTERCEPTOR_TYPE__AROUND_CONSTRUCT:
				return getAroundConstruct();
			case EjbPackage.INTERCEPTOR_TYPE__ENV_ENTRIES:
				return getEnvEntries();
			case EjbPackage.INTERCEPTOR_TYPE__EJB_REFS:
				return getEjbRefs();
			case EjbPackage.INTERCEPTOR_TYPE__EJB_LOCAL_REFS:
				return getEjbLocalRefs();
			case EjbPackage.INTERCEPTOR_TYPE__SERVICE_REFS:
				return getServiceRefs();
			case EjbPackage.INTERCEPTOR_TYPE__RESOURCE_REFS:
				return getResourceRefs();
			case EjbPackage.INTERCEPTOR_TYPE__RESOURCE_ENV_REFS:
				return getResourceEnvRefs();
			case EjbPackage.INTERCEPTOR_TYPE__MESSAGE_DESTINATION_REFS:
				return getMessageDestinationRefs();
			case EjbPackage.INTERCEPTOR_TYPE__PERSISTENCE_CONTEXT_REFS:
				return getPersistenceContextRefs();
			case EjbPackage.INTERCEPTOR_TYPE__PERSISTENCE_UNIT_REFS:
				return getPersistenceUnitRefs();
			case EjbPackage.INTERCEPTOR_TYPE__POST_CONSTRUCTS:
				return getPostConstructs();
			case EjbPackage.INTERCEPTOR_TYPE__PRE_DESTROYS:
				return getPreDestroys();
			case EjbPackage.INTERCEPTOR_TYPE__DATA_SOURCE:
				return getDataSource();
			case EjbPackage.INTERCEPTOR_TYPE__JMS_CONNECTION_FACTORY:
				return getJmsConnectionFactory();
			case EjbPackage.INTERCEPTOR_TYPE__JMS_DESTINATION:
				return getJmsDestination();
			case EjbPackage.INTERCEPTOR_TYPE__MAIL_SESSION:
				return getMailSession();
			case EjbPackage.INTERCEPTOR_TYPE__CONNECTION_FACTORY:
				return getConnectionFactory();
			case EjbPackage.INTERCEPTOR_TYPE__ADMINISTERED_OBJECT:
				return getAdministeredObject();
			case EjbPackage.INTERCEPTOR_TYPE__POST_ACTIVATES:
				return getPostActivates();
			case EjbPackage.INTERCEPTOR_TYPE__PRE_PASSIVATES:
				return getPrePassivates();
			case EjbPackage.INTERCEPTOR_TYPE__ID:
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
			case EjbPackage.INTERCEPTOR_TYPE__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection<? extends Description>)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__INTERCEPTOR_CLASS:
				setInterceptorClass((String)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__AROUND_INVOKES:
				getAroundInvokes().clear();
				getAroundInvokes().addAll((Collection<? extends AroundInvokeType>)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__AROUND_TIMEOUTS:
				getAroundTimeouts().clear();
				getAroundTimeouts().addAll((Collection<? extends AroundTimeoutType>)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__AROUND_CONSTRUCT:
				getAroundConstruct().clear();
				getAroundConstruct().addAll((Collection<? extends LifecycleCallback>)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__ENV_ENTRIES:
				getEnvEntries().clear();
				getEnvEntries().addAll((Collection<? extends EnvEntry>)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__EJB_REFS:
				getEjbRefs().clear();
				getEjbRefs().addAll((Collection<? extends EjbRef>)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__EJB_LOCAL_REFS:
				getEjbLocalRefs().clear();
				getEjbLocalRefs().addAll((Collection<? extends EjbLocalRef>)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__SERVICE_REFS:
				getServiceRefs().clear();
				getServiceRefs().addAll((Collection<? extends ServiceRef>)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__RESOURCE_REFS:
				getResourceRefs().clear();
				getResourceRefs().addAll((Collection<? extends ResourceRef>)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__RESOURCE_ENV_REFS:
				getResourceEnvRefs().clear();
				getResourceEnvRefs().addAll((Collection<? extends ResourceEnvRef>)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__MESSAGE_DESTINATION_REFS:
				getMessageDestinationRefs().clear();
				getMessageDestinationRefs().addAll((Collection<? extends MessageDestinationRef>)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__PERSISTENCE_CONTEXT_REFS:
				getPersistenceContextRefs().clear();
				getPersistenceContextRefs().addAll((Collection<? extends PersistenceContextRef>)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__PERSISTENCE_UNIT_REFS:
				getPersistenceUnitRefs().clear();
				getPersistenceUnitRefs().addAll((Collection<? extends PersistenceUnitRef>)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__POST_CONSTRUCTS:
				getPostConstructs().clear();
				getPostConstructs().addAll((Collection<? extends LifecycleCallback>)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__PRE_DESTROYS:
				getPreDestroys().clear();
				getPreDestroys().addAll((Collection<? extends LifecycleCallback>)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__DATA_SOURCE:
				getDataSource().clear();
				getDataSource().addAll((Collection<? extends DataSourceType>)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__JMS_CONNECTION_FACTORY:
				getJmsConnectionFactory().clear();
				getJmsConnectionFactory().addAll((Collection<? extends JmsConnectionFactoryType>)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__JMS_DESTINATION:
				getJmsDestination().clear();
				getJmsDestination().addAll((Collection<? extends JmsDestinationType>)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__MAIL_SESSION:
				getMailSession().clear();
				getMailSession().addAll((Collection<? extends MailSessionType>)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__CONNECTION_FACTORY:
				getConnectionFactory().clear();
				getConnectionFactory().addAll((Collection<? extends ConnectionFactoryResourceType>)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__ADMINISTERED_OBJECT:
				getAdministeredObject().clear();
				getAdministeredObject().addAll((Collection<? extends AdministeredObjectType>)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__POST_ACTIVATES:
				getPostActivates().clear();
				getPostActivates().addAll((Collection<? extends LifecycleCallback>)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__PRE_PASSIVATES:
				getPrePassivates().clear();
				getPrePassivates().addAll((Collection<? extends LifecycleCallback>)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__ID:
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
			case EjbPackage.INTERCEPTOR_TYPE__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__INTERCEPTOR_CLASS:
				setInterceptorClass(INTERCEPTOR_CLASS_EDEFAULT);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__AROUND_INVOKES:
				getAroundInvokes().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__AROUND_TIMEOUTS:
				getAroundTimeouts().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__AROUND_CONSTRUCT:
				getAroundConstruct().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__ENV_ENTRIES:
				getEnvEntries().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__EJB_REFS:
				getEjbRefs().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__EJB_LOCAL_REFS:
				getEjbLocalRefs().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__SERVICE_REFS:
				getServiceRefs().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__RESOURCE_REFS:
				getResourceRefs().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__RESOURCE_ENV_REFS:
				getResourceEnvRefs().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__MESSAGE_DESTINATION_REFS:
				getMessageDestinationRefs().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__PERSISTENCE_CONTEXT_REFS:
				getPersistenceContextRefs().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__PERSISTENCE_UNIT_REFS:
				getPersistenceUnitRefs().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__POST_CONSTRUCTS:
				getPostConstructs().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__PRE_DESTROYS:
				getPreDestroys().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__DATA_SOURCE:
				getDataSource().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__JMS_CONNECTION_FACTORY:
				getJmsConnectionFactory().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__JMS_DESTINATION:
				getJmsDestination().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__MAIL_SESSION:
				getMailSession().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__CONNECTION_FACTORY:
				getConnectionFactory().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__ADMINISTERED_OBJECT:
				getAdministeredObject().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__POST_ACTIVATES:
				getPostActivates().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__PRE_PASSIVATES:
				getPrePassivates().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__ID:
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
			case EjbPackage.INTERCEPTOR_TYPE__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__INTERCEPTOR_CLASS:
				return INTERCEPTOR_CLASS_EDEFAULT == null ? interceptorClass != null : !INTERCEPTOR_CLASS_EDEFAULT.equals(interceptorClass);
			case EjbPackage.INTERCEPTOR_TYPE__AROUND_INVOKES:
				return aroundInvokes != null && !aroundInvokes.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__AROUND_TIMEOUTS:
				return aroundTimeouts != null && !aroundTimeouts.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__AROUND_CONSTRUCT:
				return aroundConstruct != null && !aroundConstruct.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__ENV_ENTRIES:
				return envEntries != null && !envEntries.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__EJB_REFS:
				return ejbRefs != null && !ejbRefs.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__EJB_LOCAL_REFS:
				return ejbLocalRefs != null && !ejbLocalRefs.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__SERVICE_REFS:
				return serviceRefs != null && !serviceRefs.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__RESOURCE_REFS:
				return resourceRefs != null && !resourceRefs.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__RESOURCE_ENV_REFS:
				return resourceEnvRefs != null && !resourceEnvRefs.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__MESSAGE_DESTINATION_REFS:
				return messageDestinationRefs != null && !messageDestinationRefs.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__PERSISTENCE_CONTEXT_REFS:
				return persistenceContextRefs != null && !persistenceContextRefs.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__PERSISTENCE_UNIT_REFS:
				return persistenceUnitRefs != null && !persistenceUnitRefs.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__POST_CONSTRUCTS:
				return postConstructs != null && !postConstructs.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__PRE_DESTROYS:
				return preDestroys != null && !preDestroys.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__DATA_SOURCE:
				return dataSource != null && !dataSource.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__JMS_CONNECTION_FACTORY:
				return jmsConnectionFactory != null && !jmsConnectionFactory.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__JMS_DESTINATION:
				return jmsDestination != null && !jmsDestination.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__MAIL_SESSION:
				return mailSession != null && !mailSession.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__CONNECTION_FACTORY:
				return connectionFactory != null && !connectionFactory.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__ADMINISTERED_OBJECT:
				return administeredObject != null && !administeredObject.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__POST_ACTIVATES:
				return postActivates != null && !postActivates.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__PRE_PASSIVATES:
				return prePassivates != null && !prePassivates.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__ID:
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
		result.append(" (interceptorClass: "); //$NON-NLS-1$
		result.append(interceptorClass);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //InterceptorTypeImpl