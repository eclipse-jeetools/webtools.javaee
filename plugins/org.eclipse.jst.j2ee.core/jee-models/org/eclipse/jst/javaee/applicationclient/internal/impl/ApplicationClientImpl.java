/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.applicationclient.internal.impl;

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

import org.eclipse.jst.javaee.applicationclient.ApplicationClient;

import org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage;

import org.eclipse.jst.javaee.core.AdministeredObjectType;
import org.eclipse.jst.javaee.core.ConnectionFactoryResourceType;
import org.eclipse.jst.javaee.core.DataSourceType;
import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.DisplayName;
import org.eclipse.jst.javaee.core.EjbRef;
import org.eclipse.jst.javaee.core.EnvEntry;
import org.eclipse.jst.javaee.core.Icon;
import org.eclipse.jst.javaee.core.JmsConnectionFactoryType;
import org.eclipse.jst.javaee.core.JmsDestinationType;
import org.eclipse.jst.javaee.core.LifecycleCallback;
import org.eclipse.jst.javaee.core.MailSessionType;
import org.eclipse.jst.javaee.core.MessageDestination;
import org.eclipse.jst.javaee.core.MessageDestinationRef;
import org.eclipse.jst.javaee.core.PersistenceUnitRef;
import org.eclipse.jst.javaee.core.ResourceEnvRef;
import org.eclipse.jst.javaee.core.ResourceRef;
import org.eclipse.jst.javaee.core.ServiceRef;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Application Client</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationClientImpl#getModuleName <em>Module Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationClientImpl#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationClientImpl#getDisplayNames <em>Display Names</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationClientImpl#getIcons <em>Icons</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationClientImpl#getEnvEntries <em>Env Entries</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationClientImpl#getEjbRefs <em>Ejb Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationClientImpl#getServiceRefs <em>Service Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationClientImpl#getResourceRefs <em>Resource Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationClientImpl#getResourceEnvRefs <em>Resource Env Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationClientImpl#getMessageDestinationRefs <em>Message Destination Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationClientImpl#getPersistenceUnitRefs <em>Persistence Unit Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationClientImpl#getPostConstructs <em>Post Constructs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationClientImpl#getPreDestroys <em>Pre Destroys</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationClientImpl#getCallbackHandler <em>Callback Handler</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationClientImpl#getMessageDestinations <em>Message Destinations</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationClientImpl#getDataSource <em>Data Source</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationClientImpl#getJmsConnectionFactory <em>Jms Connection Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationClientImpl#getJmsDestination <em>Jms Destination</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationClientImpl#getMailSession <em>Mail Session</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationClientImpl#getConnectionFactory <em>Connection Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationClientImpl#getAdministeredObject <em>Administered Object</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationClientImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationClientImpl#isMetadataComplete <em>Metadata Complete</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationClientImpl#getVersion <em>Version</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ApplicationClientImpl extends EObjectImpl implements ApplicationClient {
	/**
	 * The default value of the '{@link #getModuleName() <em>Module Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModuleName()
	 * @generated
	 * @ordered
	 */
	protected static final String MODULE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getModuleName() <em>Module Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModuleName()
	 * @generated
	 * @ordered
	 */
	protected String moduleName = MODULE_NAME_EDEFAULT;

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
	 * The default value of the '{@link #getCallbackHandler() <em>Callback Handler</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCallbackHandler()
	 * @generated
	 * @ordered
	 */
	protected static final String CALLBACK_HANDLER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCallbackHandler() <em>Callback Handler</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCallbackHandler()
	 * @generated
	 * @ordered
	 */
	protected String callbackHandler = CALLBACK_HANDLER_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMessageDestinations() <em>Message Destinations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageDestinations()
	 * @generated
	 * @ordered
	 */
	protected EList<MessageDestination> messageDestinations;

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
	 * The default value of the '{@link #isMetadataComplete() <em>Metadata Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMetadataComplete()
	 * @generated
	 * @ordered
	 */
	protected static final boolean METADATA_COMPLETE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMetadataComplete() <em>Metadata Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMetadataComplete()
	 * @generated
	 * @ordered
	 */
	protected boolean metadataComplete = METADATA_COMPLETE_EDEFAULT;

	/**
	 * This is true if the Metadata Complete attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean metadataCompleteESet;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = "8"; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

	/**
	 * This is true if the Version attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean versionESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ApplicationClientImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ApplicationclientPackage.Literals.APPLICATION_CLIENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModuleName(String newModuleName) {
		String oldModuleName = moduleName;
		moduleName = newModuleName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationclientPackage.APPLICATION_CLIENT__MODULE_NAME, oldModuleName, moduleName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Description> getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList<Description>(Description.class, this, ApplicationclientPackage.APPLICATION_CLIENT__DESCRIPTIONS);
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
			displayNames = new EObjectContainmentEList<DisplayName>(DisplayName.class, this, ApplicationclientPackage.APPLICATION_CLIENT__DISPLAY_NAMES);
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
			icons = new EObjectContainmentEList<Icon>(Icon.class, this, ApplicationclientPackage.APPLICATION_CLIENT__ICONS);
		}
		return icons;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<EnvEntry> getEnvEntries() {
		if (envEntries == null) {
			envEntries = new EObjectContainmentEList<EnvEntry>(EnvEntry.class, this, ApplicationclientPackage.APPLICATION_CLIENT__ENV_ENTRIES);
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
			ejbRefs = new EObjectContainmentEList<EjbRef>(EjbRef.class, this, ApplicationclientPackage.APPLICATION_CLIENT__EJB_REFS);
		}
		return ejbRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ServiceRef> getServiceRefs() {
		if (serviceRefs == null) {
			serviceRefs = new EObjectContainmentEList<ServiceRef>(ServiceRef.class, this, ApplicationclientPackage.APPLICATION_CLIENT__SERVICE_REFS);
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
			resourceRefs = new EObjectContainmentEList<ResourceRef>(ResourceRef.class, this, ApplicationclientPackage.APPLICATION_CLIENT__RESOURCE_REFS);
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
			resourceEnvRefs = new EObjectContainmentEList<ResourceEnvRef>(ResourceEnvRef.class, this, ApplicationclientPackage.APPLICATION_CLIENT__RESOURCE_ENV_REFS);
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
			messageDestinationRefs = new EObjectContainmentEList<MessageDestinationRef>(MessageDestinationRef.class, this, ApplicationclientPackage.APPLICATION_CLIENT__MESSAGE_DESTINATION_REFS);
		}
		return messageDestinationRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<PersistenceUnitRef> getPersistenceUnitRefs() {
		if (persistenceUnitRefs == null) {
			persistenceUnitRefs = new EObjectContainmentEList<PersistenceUnitRef>(PersistenceUnitRef.class, this, ApplicationclientPackage.APPLICATION_CLIENT__PERSISTENCE_UNIT_REFS);
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
			postConstructs = new EObjectContainmentEList<LifecycleCallback>(LifecycleCallback.class, this, ApplicationclientPackage.APPLICATION_CLIENT__POST_CONSTRUCTS);
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
			preDestroys = new EObjectContainmentEList<LifecycleCallback>(LifecycleCallback.class, this, ApplicationclientPackage.APPLICATION_CLIENT__PRE_DESTROYS);
		}
		return preDestroys;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCallbackHandler() {
		return callbackHandler;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCallbackHandler(String newCallbackHandler) {
		String oldCallbackHandler = callbackHandler;
		callbackHandler = newCallbackHandler;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationclientPackage.APPLICATION_CLIENT__CALLBACK_HANDLER, oldCallbackHandler, callbackHandler));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<MessageDestination> getMessageDestinations() {
		if (messageDestinations == null) {
			messageDestinations = new EObjectContainmentEList<MessageDestination>(MessageDestination.class, this, ApplicationclientPackage.APPLICATION_CLIENT__MESSAGE_DESTINATIONS);
		}
		return messageDestinations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<DataSourceType> getDataSource() {
		if (dataSource == null) {
			dataSource = new EObjectContainmentEList<DataSourceType>(DataSourceType.class, this, ApplicationclientPackage.APPLICATION_CLIENT__DATA_SOURCE);
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
			jmsConnectionFactory = new EObjectContainmentEList<JmsConnectionFactoryType>(JmsConnectionFactoryType.class, this, ApplicationclientPackage.APPLICATION_CLIENT__JMS_CONNECTION_FACTORY);
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
			jmsDestination = new EObjectContainmentEList<JmsDestinationType>(JmsDestinationType.class, this, ApplicationclientPackage.APPLICATION_CLIENT__JMS_DESTINATION);
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
			mailSession = new EObjectContainmentEList<MailSessionType>(MailSessionType.class, this, ApplicationclientPackage.APPLICATION_CLIENT__MAIL_SESSION);
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
			connectionFactory = new EObjectContainmentEList<ConnectionFactoryResourceType>(ConnectionFactoryResourceType.class, this, ApplicationclientPackage.APPLICATION_CLIENT__CONNECTION_FACTORY);
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
			administeredObject = new EObjectContainmentEList<AdministeredObjectType>(AdministeredObjectType.class, this, ApplicationclientPackage.APPLICATION_CLIENT__ADMINISTERED_OBJECT);
		}
		return administeredObject;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationclientPackage.APPLICATION_CLIENT__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMetadataComplete() {
		return metadataComplete;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMetadataComplete(boolean newMetadataComplete) {
		boolean oldMetadataComplete = metadataComplete;
		metadataComplete = newMetadataComplete;
		boolean oldMetadataCompleteESet = metadataCompleteESet;
		metadataCompleteESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationclientPackage.APPLICATION_CLIENT__METADATA_COMPLETE, oldMetadataComplete, metadataComplete, !oldMetadataCompleteESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetMetadataComplete() {
		boolean oldMetadataComplete = metadataComplete;
		boolean oldMetadataCompleteESet = metadataCompleteESet;
		metadataComplete = METADATA_COMPLETE_EDEFAULT;
		metadataCompleteESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ApplicationclientPackage.APPLICATION_CLIENT__METADATA_COMPLETE, oldMetadataComplete, METADATA_COMPLETE_EDEFAULT, oldMetadataCompleteESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetMetadataComplete() {
		return metadataCompleteESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		boolean oldVersionESet = versionESet;
		versionESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationclientPackage.APPLICATION_CLIENT__VERSION, oldVersion, version, !oldVersionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetVersion() {
		String oldVersion = version;
		boolean oldVersionESet = versionESet;
		version = VERSION_EDEFAULT;
		versionESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ApplicationclientPackage.APPLICATION_CLIENT__VERSION, oldVersion, VERSION_EDEFAULT, oldVersionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetVersion() {
		return versionESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ApplicationclientPackage.APPLICATION_CLIENT__DESCRIPTIONS:
				return ((InternalEList<?>)getDescriptions()).basicRemove(otherEnd, msgs);
			case ApplicationclientPackage.APPLICATION_CLIENT__DISPLAY_NAMES:
				return ((InternalEList<?>)getDisplayNames()).basicRemove(otherEnd, msgs);
			case ApplicationclientPackage.APPLICATION_CLIENT__ICONS:
				return ((InternalEList<?>)getIcons()).basicRemove(otherEnd, msgs);
			case ApplicationclientPackage.APPLICATION_CLIENT__ENV_ENTRIES:
				return ((InternalEList<?>)getEnvEntries()).basicRemove(otherEnd, msgs);
			case ApplicationclientPackage.APPLICATION_CLIENT__EJB_REFS:
				return ((InternalEList<?>)getEjbRefs()).basicRemove(otherEnd, msgs);
			case ApplicationclientPackage.APPLICATION_CLIENT__SERVICE_REFS:
				return ((InternalEList<?>)getServiceRefs()).basicRemove(otherEnd, msgs);
			case ApplicationclientPackage.APPLICATION_CLIENT__RESOURCE_REFS:
				return ((InternalEList<?>)getResourceRefs()).basicRemove(otherEnd, msgs);
			case ApplicationclientPackage.APPLICATION_CLIENT__RESOURCE_ENV_REFS:
				return ((InternalEList<?>)getResourceEnvRefs()).basicRemove(otherEnd, msgs);
			case ApplicationclientPackage.APPLICATION_CLIENT__MESSAGE_DESTINATION_REFS:
				return ((InternalEList<?>)getMessageDestinationRefs()).basicRemove(otherEnd, msgs);
			case ApplicationclientPackage.APPLICATION_CLIENT__PERSISTENCE_UNIT_REFS:
				return ((InternalEList<?>)getPersistenceUnitRefs()).basicRemove(otherEnd, msgs);
			case ApplicationclientPackage.APPLICATION_CLIENT__POST_CONSTRUCTS:
				return ((InternalEList<?>)getPostConstructs()).basicRemove(otherEnd, msgs);
			case ApplicationclientPackage.APPLICATION_CLIENT__PRE_DESTROYS:
				return ((InternalEList<?>)getPreDestroys()).basicRemove(otherEnd, msgs);
			case ApplicationclientPackage.APPLICATION_CLIENT__MESSAGE_DESTINATIONS:
				return ((InternalEList<?>)getMessageDestinations()).basicRemove(otherEnd, msgs);
			case ApplicationclientPackage.APPLICATION_CLIENT__DATA_SOURCE:
				return ((InternalEList<?>)getDataSource()).basicRemove(otherEnd, msgs);
			case ApplicationclientPackage.APPLICATION_CLIENT__JMS_CONNECTION_FACTORY:
				return ((InternalEList<?>)getJmsConnectionFactory()).basicRemove(otherEnd, msgs);
			case ApplicationclientPackage.APPLICATION_CLIENT__JMS_DESTINATION:
				return ((InternalEList<?>)getJmsDestination()).basicRemove(otherEnd, msgs);
			case ApplicationclientPackage.APPLICATION_CLIENT__MAIL_SESSION:
				return ((InternalEList<?>)getMailSession()).basicRemove(otherEnd, msgs);
			case ApplicationclientPackage.APPLICATION_CLIENT__CONNECTION_FACTORY:
				return ((InternalEList<?>)getConnectionFactory()).basicRemove(otherEnd, msgs);
			case ApplicationclientPackage.APPLICATION_CLIENT__ADMINISTERED_OBJECT:
				return ((InternalEList<?>)getAdministeredObject()).basicRemove(otherEnd, msgs);
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
			case ApplicationclientPackage.APPLICATION_CLIENT__MODULE_NAME:
				return getModuleName();
			case ApplicationclientPackage.APPLICATION_CLIENT__DESCRIPTIONS:
				return getDescriptions();
			case ApplicationclientPackage.APPLICATION_CLIENT__DISPLAY_NAMES:
				return getDisplayNames();
			case ApplicationclientPackage.APPLICATION_CLIENT__ICONS:
				return getIcons();
			case ApplicationclientPackage.APPLICATION_CLIENT__ENV_ENTRIES:
				return getEnvEntries();
			case ApplicationclientPackage.APPLICATION_CLIENT__EJB_REFS:
				return getEjbRefs();
			case ApplicationclientPackage.APPLICATION_CLIENT__SERVICE_REFS:
				return getServiceRefs();
			case ApplicationclientPackage.APPLICATION_CLIENT__RESOURCE_REFS:
				return getResourceRefs();
			case ApplicationclientPackage.APPLICATION_CLIENT__RESOURCE_ENV_REFS:
				return getResourceEnvRefs();
			case ApplicationclientPackage.APPLICATION_CLIENT__MESSAGE_DESTINATION_REFS:
				return getMessageDestinationRefs();
			case ApplicationclientPackage.APPLICATION_CLIENT__PERSISTENCE_UNIT_REFS:
				return getPersistenceUnitRefs();
			case ApplicationclientPackage.APPLICATION_CLIENT__POST_CONSTRUCTS:
				return getPostConstructs();
			case ApplicationclientPackage.APPLICATION_CLIENT__PRE_DESTROYS:
				return getPreDestroys();
			case ApplicationclientPackage.APPLICATION_CLIENT__CALLBACK_HANDLER:
				return getCallbackHandler();
			case ApplicationclientPackage.APPLICATION_CLIENT__MESSAGE_DESTINATIONS:
				return getMessageDestinations();
			case ApplicationclientPackage.APPLICATION_CLIENT__DATA_SOURCE:
				return getDataSource();
			case ApplicationclientPackage.APPLICATION_CLIENT__JMS_CONNECTION_FACTORY:
				return getJmsConnectionFactory();
			case ApplicationclientPackage.APPLICATION_CLIENT__JMS_DESTINATION:
				return getJmsDestination();
			case ApplicationclientPackage.APPLICATION_CLIENT__MAIL_SESSION:
				return getMailSession();
			case ApplicationclientPackage.APPLICATION_CLIENT__CONNECTION_FACTORY:
				return getConnectionFactory();
			case ApplicationclientPackage.APPLICATION_CLIENT__ADMINISTERED_OBJECT:
				return getAdministeredObject();
			case ApplicationclientPackage.APPLICATION_CLIENT__ID:
				return getId();
			case ApplicationclientPackage.APPLICATION_CLIENT__METADATA_COMPLETE:
				return isMetadataComplete();
			case ApplicationclientPackage.APPLICATION_CLIENT__VERSION:
				return getVersion();
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
			case ApplicationclientPackage.APPLICATION_CLIENT__MODULE_NAME:
				setModuleName((String)newValue);
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection<? extends Description>)newValue);
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__DISPLAY_NAMES:
				getDisplayNames().clear();
				getDisplayNames().addAll((Collection<? extends DisplayName>)newValue);
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__ICONS:
				getIcons().clear();
				getIcons().addAll((Collection<? extends Icon>)newValue);
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__ENV_ENTRIES:
				getEnvEntries().clear();
				getEnvEntries().addAll((Collection<? extends EnvEntry>)newValue);
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__EJB_REFS:
				getEjbRefs().clear();
				getEjbRefs().addAll((Collection<? extends EjbRef>)newValue);
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__SERVICE_REFS:
				getServiceRefs().clear();
				getServiceRefs().addAll((Collection<? extends ServiceRef>)newValue);
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__RESOURCE_REFS:
				getResourceRefs().clear();
				getResourceRefs().addAll((Collection<? extends ResourceRef>)newValue);
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__RESOURCE_ENV_REFS:
				getResourceEnvRefs().clear();
				getResourceEnvRefs().addAll((Collection<? extends ResourceEnvRef>)newValue);
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__MESSAGE_DESTINATION_REFS:
				getMessageDestinationRefs().clear();
				getMessageDestinationRefs().addAll((Collection<? extends MessageDestinationRef>)newValue);
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__PERSISTENCE_UNIT_REFS:
				getPersistenceUnitRefs().clear();
				getPersistenceUnitRefs().addAll((Collection<? extends PersistenceUnitRef>)newValue);
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__POST_CONSTRUCTS:
				getPostConstructs().clear();
				getPostConstructs().addAll((Collection<? extends LifecycleCallback>)newValue);
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__PRE_DESTROYS:
				getPreDestroys().clear();
				getPreDestroys().addAll((Collection<? extends LifecycleCallback>)newValue);
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__CALLBACK_HANDLER:
				setCallbackHandler((String)newValue);
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__MESSAGE_DESTINATIONS:
				getMessageDestinations().clear();
				getMessageDestinations().addAll((Collection<? extends MessageDestination>)newValue);
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__DATA_SOURCE:
				getDataSource().clear();
				getDataSource().addAll((Collection<? extends DataSourceType>)newValue);
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__JMS_CONNECTION_FACTORY:
				getJmsConnectionFactory().clear();
				getJmsConnectionFactory().addAll((Collection<? extends JmsConnectionFactoryType>)newValue);
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__JMS_DESTINATION:
				getJmsDestination().clear();
				getJmsDestination().addAll((Collection<? extends JmsDestinationType>)newValue);
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__MAIL_SESSION:
				getMailSession().clear();
				getMailSession().addAll((Collection<? extends MailSessionType>)newValue);
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__CONNECTION_FACTORY:
				getConnectionFactory().clear();
				getConnectionFactory().addAll((Collection<? extends ConnectionFactoryResourceType>)newValue);
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__ADMINISTERED_OBJECT:
				getAdministeredObject().clear();
				getAdministeredObject().addAll((Collection<? extends AdministeredObjectType>)newValue);
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__ID:
				setId((String)newValue);
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__METADATA_COMPLETE:
				setMetadataComplete((Boolean)newValue);
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__VERSION:
				setVersion((String)newValue);
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
			case ApplicationclientPackage.APPLICATION_CLIENT__MODULE_NAME:
				setModuleName(MODULE_NAME_EDEFAULT);
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__DISPLAY_NAMES:
				getDisplayNames().clear();
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__ICONS:
				getIcons().clear();
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__ENV_ENTRIES:
				getEnvEntries().clear();
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__EJB_REFS:
				getEjbRefs().clear();
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__SERVICE_REFS:
				getServiceRefs().clear();
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__RESOURCE_REFS:
				getResourceRefs().clear();
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__RESOURCE_ENV_REFS:
				getResourceEnvRefs().clear();
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__MESSAGE_DESTINATION_REFS:
				getMessageDestinationRefs().clear();
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__PERSISTENCE_UNIT_REFS:
				getPersistenceUnitRefs().clear();
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__POST_CONSTRUCTS:
				getPostConstructs().clear();
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__PRE_DESTROYS:
				getPreDestroys().clear();
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__CALLBACK_HANDLER:
				setCallbackHandler(CALLBACK_HANDLER_EDEFAULT);
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__MESSAGE_DESTINATIONS:
				getMessageDestinations().clear();
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__DATA_SOURCE:
				getDataSource().clear();
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__JMS_CONNECTION_FACTORY:
				getJmsConnectionFactory().clear();
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__JMS_DESTINATION:
				getJmsDestination().clear();
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__MAIL_SESSION:
				getMailSession().clear();
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__CONNECTION_FACTORY:
				getConnectionFactory().clear();
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__ADMINISTERED_OBJECT:
				getAdministeredObject().clear();
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__ID:
				setId(ID_EDEFAULT);
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__METADATA_COMPLETE:
				unsetMetadataComplete();
				return;
			case ApplicationclientPackage.APPLICATION_CLIENT__VERSION:
				unsetVersion();
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
			case ApplicationclientPackage.APPLICATION_CLIENT__MODULE_NAME:
				return MODULE_NAME_EDEFAULT == null ? moduleName != null : !MODULE_NAME_EDEFAULT.equals(moduleName);
			case ApplicationclientPackage.APPLICATION_CLIENT__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case ApplicationclientPackage.APPLICATION_CLIENT__DISPLAY_NAMES:
				return displayNames != null && !displayNames.isEmpty();
			case ApplicationclientPackage.APPLICATION_CLIENT__ICONS:
				return icons != null && !icons.isEmpty();
			case ApplicationclientPackage.APPLICATION_CLIENT__ENV_ENTRIES:
				return envEntries != null && !envEntries.isEmpty();
			case ApplicationclientPackage.APPLICATION_CLIENT__EJB_REFS:
				return ejbRefs != null && !ejbRefs.isEmpty();
			case ApplicationclientPackage.APPLICATION_CLIENT__SERVICE_REFS:
				return serviceRefs != null && !serviceRefs.isEmpty();
			case ApplicationclientPackage.APPLICATION_CLIENT__RESOURCE_REFS:
				return resourceRefs != null && !resourceRefs.isEmpty();
			case ApplicationclientPackage.APPLICATION_CLIENT__RESOURCE_ENV_REFS:
				return resourceEnvRefs != null && !resourceEnvRefs.isEmpty();
			case ApplicationclientPackage.APPLICATION_CLIENT__MESSAGE_DESTINATION_REFS:
				return messageDestinationRefs != null && !messageDestinationRefs.isEmpty();
			case ApplicationclientPackage.APPLICATION_CLIENT__PERSISTENCE_UNIT_REFS:
				return persistenceUnitRefs != null && !persistenceUnitRefs.isEmpty();
			case ApplicationclientPackage.APPLICATION_CLIENT__POST_CONSTRUCTS:
				return postConstructs != null && !postConstructs.isEmpty();
			case ApplicationclientPackage.APPLICATION_CLIENT__PRE_DESTROYS:
				return preDestroys != null && !preDestroys.isEmpty();
			case ApplicationclientPackage.APPLICATION_CLIENT__CALLBACK_HANDLER:
				return CALLBACK_HANDLER_EDEFAULT == null ? callbackHandler != null : !CALLBACK_HANDLER_EDEFAULT.equals(callbackHandler);
			case ApplicationclientPackage.APPLICATION_CLIENT__MESSAGE_DESTINATIONS:
				return messageDestinations != null && !messageDestinations.isEmpty();
			case ApplicationclientPackage.APPLICATION_CLIENT__DATA_SOURCE:
				return dataSource != null && !dataSource.isEmpty();
			case ApplicationclientPackage.APPLICATION_CLIENT__JMS_CONNECTION_FACTORY:
				return jmsConnectionFactory != null && !jmsConnectionFactory.isEmpty();
			case ApplicationclientPackage.APPLICATION_CLIENT__JMS_DESTINATION:
				return jmsDestination != null && !jmsDestination.isEmpty();
			case ApplicationclientPackage.APPLICATION_CLIENT__MAIL_SESSION:
				return mailSession != null && !mailSession.isEmpty();
			case ApplicationclientPackage.APPLICATION_CLIENT__CONNECTION_FACTORY:
				return connectionFactory != null && !connectionFactory.isEmpty();
			case ApplicationclientPackage.APPLICATION_CLIENT__ADMINISTERED_OBJECT:
				return administeredObject != null && !administeredObject.isEmpty();
			case ApplicationclientPackage.APPLICATION_CLIENT__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case ApplicationclientPackage.APPLICATION_CLIENT__METADATA_COMPLETE:
				return isSetMetadataComplete();
			case ApplicationclientPackage.APPLICATION_CLIENT__VERSION:
				return isSetVersion();
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
		result.append(" (moduleName: "); //$NON-NLS-1$
		result.append(moduleName);
		result.append(", callbackHandler: "); //$NON-NLS-1$
		result.append(callbackHandler);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(", metadataComplete: "); //$NON-NLS-1$
		if (metadataCompleteESet) result.append(metadataComplete); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", version: "); //$NON-NLS-1$
		if (versionESet) result.append(version); else result.append("<unset>"); //$NON-NLS-1$
		result.append(')');
		return result.toString();
	}

} //ApplicationClientImpl