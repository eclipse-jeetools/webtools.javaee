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
package org.eclipse.jst.javaee.application.internal.impl;

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
import org.eclipse.jst.javaee.application.Application;
import org.eclipse.jst.javaee.application.Module;
import org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage;
import org.eclipse.jst.javaee.core.AdministeredObjectType;
import org.eclipse.jst.javaee.core.ConnectionFactoryResourceType;
import org.eclipse.jst.javaee.core.DataSourceType;
import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.DisplayName;
import org.eclipse.jst.javaee.core.EjbLocalRef;
import org.eclipse.jst.javaee.core.EjbRef;
import org.eclipse.jst.javaee.core.EnvEntry;
import org.eclipse.jst.javaee.core.GenericBooleanType;
import org.eclipse.jst.javaee.core.Icon;
import org.eclipse.jst.javaee.core.JmsConnectionFactoryType;
import org.eclipse.jst.javaee.core.JmsDestinationType;
import org.eclipse.jst.javaee.core.MailSessionType;
import org.eclipse.jst.javaee.core.MessageDestination;
import org.eclipse.jst.javaee.core.MessageDestinationRef;
import org.eclipse.jst.javaee.core.PersistenceContextRef;
import org.eclipse.jst.javaee.core.PersistenceUnitRef;
import org.eclipse.jst.javaee.core.ResourceEnvRef;
import org.eclipse.jst.javaee.core.ResourceRef;
import org.eclipse.jst.javaee.core.SecurityRole;
import org.eclipse.jst.javaee.core.ServiceRef;
import org.eclipse.jst.jee.application.ICommonApplication;
import org.eclipse.jst.jee.application.ICommonModule;
import org.eclipse.wst.common.internal.emf.utilities.StringUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Application</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl#getApplicationName <em>Application Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl#getDisplayNames <em>Display Names</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl#getIcons <em>Icons</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl#getInitializeInOrder <em>Initialize In Order</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl#getModules <em>Modules</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl#getSecurityRoles <em>Security Roles</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl#getLibraryDirectory <em>Library Directory</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl#getEnvEntry <em>Env Entry</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl#getEjbRef <em>Ejb Ref</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl#getEjbLocalRef <em>Ejb Local Ref</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl#getServiceRefs <em>Service Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl#getResourceRef <em>Resource Ref</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl#getResourceEnvRef <em>Resource Env Ref</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl#getMessageDestinationRef <em>Message Destination Ref</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl#getPersistenceContextRef <em>Persistence Context Ref</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl#getPersistenceUnitRef <em>Persistence Unit Ref</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl#getMessageDestination <em>Message Destination</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl#getDataSource <em>Data Source</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl#getJmsConnectionFactory <em>Jms Connection Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl#getJmsDestination <em>Jms Destination</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl#getMailSession <em>Mail Session</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl#getConnectionFactory <em>Connection Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl#getAdministeredObject <em>Administered Object</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl#getVersion <em>Version</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ApplicationImpl extends EObjectImpl implements Application, ICommonApplication {
	/**
	 * The default value of the '{@link #getApplicationName() <em>Application Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getApplicationName()
	 * @generated
	 * @ordered
	 */
	protected static final String APPLICATION_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getApplicationName() <em>Application Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getApplicationName()
	 * @generated
	 * @ordered
	 */
	protected String applicationName = APPLICATION_NAME_EDEFAULT;

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
	 * The default value of the '{@link #getInitializeInOrder() <em>Initialize In Order</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitializeInOrder()
	 * @generated
	 * @ordered
	 */
	protected static final GenericBooleanType INITIALIZE_IN_ORDER_EDEFAULT = GenericBooleanType.TRUE;

	/**
	 * The cached value of the '{@link #getInitializeInOrder() <em>Initialize In Order</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitializeInOrder()
	 * @generated
	 * @ordered
	 */
	protected GenericBooleanType initializeInOrder = INITIALIZE_IN_ORDER_EDEFAULT;

	/**
	 * This is true if the Initialize In Order attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean initializeInOrderESet;

	/**
	 * The cached value of the '{@link #getModules() <em>Modules</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModules()
	 * @generated
	 * @ordered
	 */
	protected EList<Module> modules;

	/**
	 * The cached value of the '{@link #getSecurityRoles() <em>Security Roles</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecurityRoles()
	 * @generated
	 * @ordered
	 */
	protected EList<SecurityRole> securityRoles;

	/**
	 * The default value of the '{@link #getLibraryDirectory() <em>Library Directory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLibraryDirectory()
	 * @generated
	 * @ordered
	 */
	protected static final String LIBRARY_DIRECTORY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLibraryDirectory() <em>Library Directory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLibraryDirectory()
	 * @generated
	 * @ordered
	 */
	protected String libraryDirectory = LIBRARY_DIRECTORY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getEnvEntry() <em>Env Entry</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnvEntry()
	 * @generated
	 * @ordered
	 */
	protected EList<EnvEntry> envEntry;

	/**
	 * The cached value of the '{@link #getEjbRef() <em>Ejb Ref</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbRef()
	 * @generated
	 * @ordered
	 */
	protected EList<EjbRef> ejbRef;

	/**
	 * The cached value of the '{@link #getEjbLocalRef() <em>Ejb Local Ref</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbLocalRef()
	 * @generated
	 * @ordered
	 */
	protected EList<EjbLocalRef> ejbLocalRef;

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
	 * The cached value of the '{@link #getResourceRef() <em>Resource Ref</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceRef()
	 * @generated
	 * @ordered
	 */
	protected EList<ResourceRef> resourceRef;

	/**
	 * The cached value of the '{@link #getResourceEnvRef() <em>Resource Env Ref</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceEnvRef()
	 * @generated
	 * @ordered
	 */
	protected EList<ResourceEnvRef> resourceEnvRef;

	/**
	 * The cached value of the '{@link #getMessageDestinationRef() <em>Message Destination Ref</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageDestinationRef()
	 * @generated
	 * @ordered
	 */
	protected EList<MessageDestinationRef> messageDestinationRef;

	/**
	 * The cached value of the '{@link #getPersistenceContextRef() <em>Persistence Context Ref</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPersistenceContextRef()
	 * @generated
	 * @ordered
	 */
	protected EList<PersistenceContextRef> persistenceContextRef;

	/**
	 * The cached value of the '{@link #getPersistenceUnitRef() <em>Persistence Unit Ref</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPersistenceUnitRef()
	 * @generated
	 * @ordered
	 */
	protected EList<PersistenceUnitRef> persistenceUnitRef;

	/**
	 * The cached value of the '{@link #getMessageDestination() <em>Message Destination</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageDestination()
	 * @generated
	 * @ordered
	 */
	protected EList<MessageDestination> messageDestination;

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
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = "7"; //$NON-NLS-1$

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
	protected ApplicationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ApplicationPackage.Literals.APPLICATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getApplicationName() {
		return applicationName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setApplicationName(String newApplicationName) {
		String oldApplicationName = applicationName;
		applicationName = newApplicationName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.APPLICATION__APPLICATION_NAME, oldApplicationName, applicationName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Description> getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList<Description>(Description.class, this, ApplicationPackage.APPLICATION__DESCRIPTIONS);
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
			displayNames = new EObjectContainmentEList<DisplayName>(DisplayName.class, this, ApplicationPackage.APPLICATION__DISPLAY_NAMES);
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
			icons = new EObjectContainmentEList<Icon>(Icon.class, this, ApplicationPackage.APPLICATION__ICONS);
		}
		return icons;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GenericBooleanType getInitializeInOrder() {
		return initializeInOrder;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setInitializeInOrder(GenericBooleanType newInitializeInOrder) {
		GenericBooleanType oldInitializeInOrder = initializeInOrder;
		initializeInOrder = newInitializeInOrder == null ? INITIALIZE_IN_ORDER_EDEFAULT : newInitializeInOrder;
		boolean oldInitializeInOrderESet = initializeInOrderESet;
		initializeInOrderESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.APPLICATION__INITIALIZE_IN_ORDER, oldInitializeInOrder, initializeInOrder, !oldInitializeInOrderESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetInitializeInOrder() {
		GenericBooleanType oldInitializeInOrder = initializeInOrder;
		boolean oldInitializeInOrderESet = initializeInOrderESet;
		initializeInOrder = INITIALIZE_IN_ORDER_EDEFAULT;
		initializeInOrderESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ApplicationPackage.APPLICATION__INITIALIZE_IN_ORDER, oldInitializeInOrder, INITIALIZE_IN_ORDER_EDEFAULT, oldInitializeInOrderESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetInitializeInOrder() {
		return initializeInOrderESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Module> getModules() {
		if (modules == null) {
			modules = new EObjectContainmentEList<Module>(Module.class, this, ApplicationPackage.APPLICATION__MODULES);
		}
		return modules;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<SecurityRole> getSecurityRoles() {
		if (securityRoles == null) {
			securityRoles = new EObjectContainmentEList<SecurityRole>(SecurityRole.class, this, ApplicationPackage.APPLICATION__SECURITY_ROLES);
		}
		return securityRoles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLibraryDirectory() {
		return libraryDirectory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLibraryDirectory(String newLibraryDirectory) {
		String oldLibraryDirectory = libraryDirectory;
		libraryDirectory = newLibraryDirectory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.APPLICATION__LIBRARY_DIRECTORY, oldLibraryDirectory, libraryDirectory));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<EnvEntry> getEnvEntry() {
		if (envEntry == null) {
			envEntry = new EObjectContainmentEList<EnvEntry>(EnvEntry.class, this, ApplicationPackage.APPLICATION__ENV_ENTRY);
		}
		return envEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<EjbRef> getEjbRef() {
		if (ejbRef == null) {
			ejbRef = new EObjectContainmentEList<EjbRef>(EjbRef.class, this, ApplicationPackage.APPLICATION__EJB_REF);
		}
		return ejbRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<EjbLocalRef> getEjbLocalRef() {
		if (ejbLocalRef == null) {
			ejbLocalRef = new EObjectContainmentEList<EjbLocalRef>(EjbLocalRef.class, this, ApplicationPackage.APPLICATION__EJB_LOCAL_REF);
		}
		return ejbLocalRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<ServiceRef> getServiceRefs() {
		if (serviceRefs == null) {
			serviceRefs = new EObjectContainmentEList<ServiceRef>(ServiceRef.class, this, ApplicationPackage.APPLICATION__SERVICE_REFS);
		}
		return serviceRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<ResourceRef> getResourceRef() {
		if (resourceRef == null) {
			resourceRef = new EObjectContainmentEList<ResourceRef>(ResourceRef.class, this, ApplicationPackage.APPLICATION__RESOURCE_REF);
		}
		return resourceRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<ResourceEnvRef> getResourceEnvRef() {
		if (resourceEnvRef == null) {
			resourceEnvRef = new EObjectContainmentEList<ResourceEnvRef>(ResourceEnvRef.class, this, ApplicationPackage.APPLICATION__RESOURCE_ENV_REF);
		}
		return resourceEnvRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<MessageDestinationRef> getMessageDestinationRef() {
		if (messageDestinationRef == null) {
			messageDestinationRef = new EObjectContainmentEList<MessageDestinationRef>(MessageDestinationRef.class, this, ApplicationPackage.APPLICATION__MESSAGE_DESTINATION_REF);
		}
		return messageDestinationRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<PersistenceContextRef> getPersistenceContextRef() {
		if (persistenceContextRef == null) {
			persistenceContextRef = new EObjectContainmentEList<PersistenceContextRef>(PersistenceContextRef.class, this, ApplicationPackage.APPLICATION__PERSISTENCE_CONTEXT_REF);
		}
		return persistenceContextRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<PersistenceUnitRef> getPersistenceUnitRef() {
		if (persistenceUnitRef == null) {
			persistenceUnitRef = new EObjectContainmentEList<PersistenceUnitRef>(PersistenceUnitRef.class, this, ApplicationPackage.APPLICATION__PERSISTENCE_UNIT_REF);
		}
		return persistenceUnitRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<MessageDestination> getMessageDestination() {
		if (messageDestination == null) {
			messageDestination = new EObjectContainmentEList<MessageDestination>(MessageDestination.class, this, ApplicationPackage.APPLICATION__MESSAGE_DESTINATION);
		}
		return messageDestination;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<DataSourceType> getDataSource() {
		if (dataSource == null) {
			dataSource = new EObjectContainmentEList<DataSourceType>(DataSourceType.class, this, ApplicationPackage.APPLICATION__DATA_SOURCE);
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
			jmsConnectionFactory = new EObjectContainmentEList<JmsConnectionFactoryType>(JmsConnectionFactoryType.class, this, ApplicationPackage.APPLICATION__JMS_CONNECTION_FACTORY);
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
			jmsDestination = new EObjectContainmentEList<JmsDestinationType>(JmsDestinationType.class, this, ApplicationPackage.APPLICATION__JMS_DESTINATION);
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
			mailSession = new EObjectContainmentEList<MailSessionType>(MailSessionType.class, this, ApplicationPackage.APPLICATION__MAIL_SESSION);
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
			connectionFactory = new EObjectContainmentEList<ConnectionFactoryResourceType>(ConnectionFactoryResourceType.class, this, ApplicationPackage.APPLICATION__CONNECTION_FACTORY);
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
			administeredObject = new EObjectContainmentEList<AdministeredObjectType>(AdministeredObjectType.class, this, ApplicationPackage.APPLICATION__ADMINISTERED_OBJECT);
		}
		return administeredObject;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.APPLICATION__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		boolean oldVersionESet = versionESet;
		versionESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.APPLICATION__VERSION, oldVersion, version, !oldVersionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetVersion() {
		String oldVersion = version;
		boolean oldVersionESet = versionESet;
		version = VERSION_EDEFAULT;
		versionESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ApplicationPackage.APPLICATION__VERSION, oldVersion, VERSION_EDEFAULT, oldVersionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
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
			case ApplicationPackage.APPLICATION__DESCRIPTIONS:
				return ((InternalEList<?>)getDescriptions()).basicRemove(otherEnd, msgs);
			case ApplicationPackage.APPLICATION__DISPLAY_NAMES:
				return ((InternalEList<?>)getDisplayNames()).basicRemove(otherEnd, msgs);
			case ApplicationPackage.APPLICATION__ICONS:
				return ((InternalEList<?>)getIcons()).basicRemove(otherEnd, msgs);
			case ApplicationPackage.APPLICATION__MODULES:
				return ((InternalEList<?>)getModules()).basicRemove(otherEnd, msgs);
			case ApplicationPackage.APPLICATION__SECURITY_ROLES:
				return ((InternalEList<?>)getSecurityRoles()).basicRemove(otherEnd, msgs);
			case ApplicationPackage.APPLICATION__ENV_ENTRY:
				return ((InternalEList<?>)getEnvEntry()).basicRemove(otherEnd, msgs);
			case ApplicationPackage.APPLICATION__EJB_REF:
				return ((InternalEList<?>)getEjbRef()).basicRemove(otherEnd, msgs);
			case ApplicationPackage.APPLICATION__EJB_LOCAL_REF:
				return ((InternalEList<?>)getEjbLocalRef()).basicRemove(otherEnd, msgs);
			case ApplicationPackage.APPLICATION__SERVICE_REFS:
				return ((InternalEList<?>)getServiceRefs()).basicRemove(otherEnd, msgs);
			case ApplicationPackage.APPLICATION__RESOURCE_REF:
				return ((InternalEList<?>)getResourceRef()).basicRemove(otherEnd, msgs);
			case ApplicationPackage.APPLICATION__RESOURCE_ENV_REF:
				return ((InternalEList<?>)getResourceEnvRef()).basicRemove(otherEnd, msgs);
			case ApplicationPackage.APPLICATION__MESSAGE_DESTINATION_REF:
				return ((InternalEList<?>)getMessageDestinationRef()).basicRemove(otherEnd, msgs);
			case ApplicationPackage.APPLICATION__PERSISTENCE_CONTEXT_REF:
				return ((InternalEList<?>)getPersistenceContextRef()).basicRemove(otherEnd, msgs);
			case ApplicationPackage.APPLICATION__PERSISTENCE_UNIT_REF:
				return ((InternalEList<?>)getPersistenceUnitRef()).basicRemove(otherEnd, msgs);
			case ApplicationPackage.APPLICATION__MESSAGE_DESTINATION:
				return ((InternalEList<?>)getMessageDestination()).basicRemove(otherEnd, msgs);
			case ApplicationPackage.APPLICATION__DATA_SOURCE:
				return ((InternalEList<?>)getDataSource()).basicRemove(otherEnd, msgs);
			case ApplicationPackage.APPLICATION__JMS_CONNECTION_FACTORY:
				return ((InternalEList<?>)getJmsConnectionFactory()).basicRemove(otherEnd, msgs);
			case ApplicationPackage.APPLICATION__JMS_DESTINATION:
				return ((InternalEList<?>)getJmsDestination()).basicRemove(otherEnd, msgs);
			case ApplicationPackage.APPLICATION__MAIL_SESSION:
				return ((InternalEList<?>)getMailSession()).basicRemove(otherEnd, msgs);
			case ApplicationPackage.APPLICATION__CONNECTION_FACTORY:
				return ((InternalEList<?>)getConnectionFactory()).basicRemove(otherEnd, msgs);
			case ApplicationPackage.APPLICATION__ADMINISTERED_OBJECT:
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
			case ApplicationPackage.APPLICATION__APPLICATION_NAME:
				return getApplicationName();
			case ApplicationPackage.APPLICATION__DESCRIPTIONS:
				return getDescriptions();
			case ApplicationPackage.APPLICATION__DISPLAY_NAMES:
				return getDisplayNames();
			case ApplicationPackage.APPLICATION__ICONS:
				return getIcons();
			case ApplicationPackage.APPLICATION__INITIALIZE_IN_ORDER:
				return getInitializeInOrder();
			case ApplicationPackage.APPLICATION__MODULES:
				return getModules();
			case ApplicationPackage.APPLICATION__SECURITY_ROLES:
				return getSecurityRoles();
			case ApplicationPackage.APPLICATION__LIBRARY_DIRECTORY:
				return getLibraryDirectory();
			case ApplicationPackage.APPLICATION__ENV_ENTRY:
				return getEnvEntry();
			case ApplicationPackage.APPLICATION__EJB_REF:
				return getEjbRef();
			case ApplicationPackage.APPLICATION__EJB_LOCAL_REF:
				return getEjbLocalRef();
			case ApplicationPackage.APPLICATION__SERVICE_REFS:
				return getServiceRefs();
			case ApplicationPackage.APPLICATION__RESOURCE_REF:
				return getResourceRef();
			case ApplicationPackage.APPLICATION__RESOURCE_ENV_REF:
				return getResourceEnvRef();
			case ApplicationPackage.APPLICATION__MESSAGE_DESTINATION_REF:
				return getMessageDestinationRef();
			case ApplicationPackage.APPLICATION__PERSISTENCE_CONTEXT_REF:
				return getPersistenceContextRef();
			case ApplicationPackage.APPLICATION__PERSISTENCE_UNIT_REF:
				return getPersistenceUnitRef();
			case ApplicationPackage.APPLICATION__MESSAGE_DESTINATION:
				return getMessageDestination();
			case ApplicationPackage.APPLICATION__DATA_SOURCE:
				return getDataSource();
			case ApplicationPackage.APPLICATION__JMS_CONNECTION_FACTORY:
				return getJmsConnectionFactory();
			case ApplicationPackage.APPLICATION__JMS_DESTINATION:
				return getJmsDestination();
			case ApplicationPackage.APPLICATION__MAIL_SESSION:
				return getMailSession();
			case ApplicationPackage.APPLICATION__CONNECTION_FACTORY:
				return getConnectionFactory();
			case ApplicationPackage.APPLICATION__ADMINISTERED_OBJECT:
				return getAdministeredObject();
			case ApplicationPackage.APPLICATION__ID:
				return getId();
			case ApplicationPackage.APPLICATION__VERSION:
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
			case ApplicationPackage.APPLICATION__APPLICATION_NAME:
				setApplicationName((String)newValue);
				return;
			case ApplicationPackage.APPLICATION__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection<? extends Description>)newValue);
				return;
			case ApplicationPackage.APPLICATION__DISPLAY_NAMES:
				getDisplayNames().clear();
				getDisplayNames().addAll((Collection<? extends DisplayName>)newValue);
				return;
			case ApplicationPackage.APPLICATION__ICONS:
				getIcons().clear();
				getIcons().addAll((Collection<? extends Icon>)newValue);
				return;
			case ApplicationPackage.APPLICATION__INITIALIZE_IN_ORDER:
				setInitializeInOrder((GenericBooleanType)newValue);
				return;
			case ApplicationPackage.APPLICATION__MODULES:
				getModules().clear();
				getModules().addAll((Collection<? extends Module>)newValue);
				return;
			case ApplicationPackage.APPLICATION__SECURITY_ROLES:
				getSecurityRoles().clear();
				getSecurityRoles().addAll((Collection<? extends SecurityRole>)newValue);
				return;
			case ApplicationPackage.APPLICATION__LIBRARY_DIRECTORY:
				setLibraryDirectory((String)newValue);
				return;
			case ApplicationPackage.APPLICATION__ENV_ENTRY:
				getEnvEntry().clear();
				getEnvEntry().addAll((Collection<? extends EnvEntry>)newValue);
				return;
			case ApplicationPackage.APPLICATION__EJB_REF:
				getEjbRef().clear();
				getEjbRef().addAll((Collection<? extends EjbRef>)newValue);
				return;
			case ApplicationPackage.APPLICATION__EJB_LOCAL_REF:
				getEjbLocalRef().clear();
				getEjbLocalRef().addAll((Collection<? extends EjbLocalRef>)newValue);
				return;
			case ApplicationPackage.APPLICATION__SERVICE_REFS:
				getServiceRefs().clear();
				getServiceRefs().addAll((Collection<? extends ServiceRef>)newValue);
				return;
			case ApplicationPackage.APPLICATION__RESOURCE_REF:
				getResourceRef().clear();
				getResourceRef().addAll((Collection<? extends ResourceRef>)newValue);
				return;
			case ApplicationPackage.APPLICATION__RESOURCE_ENV_REF:
				getResourceEnvRef().clear();
				getResourceEnvRef().addAll((Collection<? extends ResourceEnvRef>)newValue);
				return;
			case ApplicationPackage.APPLICATION__MESSAGE_DESTINATION_REF:
				getMessageDestinationRef().clear();
				getMessageDestinationRef().addAll((Collection<? extends MessageDestinationRef>)newValue);
				return;
			case ApplicationPackage.APPLICATION__PERSISTENCE_CONTEXT_REF:
				getPersistenceContextRef().clear();
				getPersistenceContextRef().addAll((Collection<? extends PersistenceContextRef>)newValue);
				return;
			case ApplicationPackage.APPLICATION__PERSISTENCE_UNIT_REF:
				getPersistenceUnitRef().clear();
				getPersistenceUnitRef().addAll((Collection<? extends PersistenceUnitRef>)newValue);
				return;
			case ApplicationPackage.APPLICATION__MESSAGE_DESTINATION:
				getMessageDestination().clear();
				getMessageDestination().addAll((Collection<? extends MessageDestination>)newValue);
				return;
			case ApplicationPackage.APPLICATION__DATA_SOURCE:
				getDataSource().clear();
				getDataSource().addAll((Collection<? extends DataSourceType>)newValue);
				return;
			case ApplicationPackage.APPLICATION__JMS_CONNECTION_FACTORY:
				getJmsConnectionFactory().clear();
				getJmsConnectionFactory().addAll((Collection<? extends JmsConnectionFactoryType>)newValue);
				return;
			case ApplicationPackage.APPLICATION__JMS_DESTINATION:
				getJmsDestination().clear();
				getJmsDestination().addAll((Collection<? extends JmsDestinationType>)newValue);
				return;
			case ApplicationPackage.APPLICATION__MAIL_SESSION:
				getMailSession().clear();
				getMailSession().addAll((Collection<? extends MailSessionType>)newValue);
				return;
			case ApplicationPackage.APPLICATION__CONNECTION_FACTORY:
				getConnectionFactory().clear();
				getConnectionFactory().addAll((Collection<? extends ConnectionFactoryResourceType>)newValue);
				return;
			case ApplicationPackage.APPLICATION__ADMINISTERED_OBJECT:
				getAdministeredObject().clear();
				getAdministeredObject().addAll((Collection<? extends AdministeredObjectType>)newValue);
				return;
			case ApplicationPackage.APPLICATION__ID:
				setId((String)newValue);
				return;
			case ApplicationPackage.APPLICATION__VERSION:
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
			case ApplicationPackage.APPLICATION__APPLICATION_NAME:
				setApplicationName(APPLICATION_NAME_EDEFAULT);
				return;
			case ApplicationPackage.APPLICATION__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case ApplicationPackage.APPLICATION__DISPLAY_NAMES:
				getDisplayNames().clear();
				return;
			case ApplicationPackage.APPLICATION__ICONS:
				getIcons().clear();
				return;
			case ApplicationPackage.APPLICATION__INITIALIZE_IN_ORDER:
				unsetInitializeInOrder();
				return;
			case ApplicationPackage.APPLICATION__MODULES:
				getModules().clear();
				return;
			case ApplicationPackage.APPLICATION__SECURITY_ROLES:
				getSecurityRoles().clear();
				return;
			case ApplicationPackage.APPLICATION__LIBRARY_DIRECTORY:
				setLibraryDirectory(LIBRARY_DIRECTORY_EDEFAULT);
				return;
			case ApplicationPackage.APPLICATION__ENV_ENTRY:
				getEnvEntry().clear();
				return;
			case ApplicationPackage.APPLICATION__EJB_REF:
				getEjbRef().clear();
				return;
			case ApplicationPackage.APPLICATION__EJB_LOCAL_REF:
				getEjbLocalRef().clear();
				return;
			case ApplicationPackage.APPLICATION__SERVICE_REFS:
				getServiceRefs().clear();
				return;
			case ApplicationPackage.APPLICATION__RESOURCE_REF:
				getResourceRef().clear();
				return;
			case ApplicationPackage.APPLICATION__RESOURCE_ENV_REF:
				getResourceEnvRef().clear();
				return;
			case ApplicationPackage.APPLICATION__MESSAGE_DESTINATION_REF:
				getMessageDestinationRef().clear();
				return;
			case ApplicationPackage.APPLICATION__PERSISTENCE_CONTEXT_REF:
				getPersistenceContextRef().clear();
				return;
			case ApplicationPackage.APPLICATION__PERSISTENCE_UNIT_REF:
				getPersistenceUnitRef().clear();
				return;
			case ApplicationPackage.APPLICATION__MESSAGE_DESTINATION:
				getMessageDestination().clear();
				return;
			case ApplicationPackage.APPLICATION__DATA_SOURCE:
				getDataSource().clear();
				return;
			case ApplicationPackage.APPLICATION__JMS_CONNECTION_FACTORY:
				getJmsConnectionFactory().clear();
				return;
			case ApplicationPackage.APPLICATION__JMS_DESTINATION:
				getJmsDestination().clear();
				return;
			case ApplicationPackage.APPLICATION__MAIL_SESSION:
				getMailSession().clear();
				return;
			case ApplicationPackage.APPLICATION__CONNECTION_FACTORY:
				getConnectionFactory().clear();
				return;
			case ApplicationPackage.APPLICATION__ADMINISTERED_OBJECT:
				getAdministeredObject().clear();
				return;
			case ApplicationPackage.APPLICATION__ID:
				setId(ID_EDEFAULT);
				return;
			case ApplicationPackage.APPLICATION__VERSION:
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
			case ApplicationPackage.APPLICATION__APPLICATION_NAME:
				return APPLICATION_NAME_EDEFAULT == null ? applicationName != null : !APPLICATION_NAME_EDEFAULT.equals(applicationName);
			case ApplicationPackage.APPLICATION__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case ApplicationPackage.APPLICATION__DISPLAY_NAMES:
				return displayNames != null && !displayNames.isEmpty();
			case ApplicationPackage.APPLICATION__ICONS:
				return icons != null && !icons.isEmpty();
			case ApplicationPackage.APPLICATION__INITIALIZE_IN_ORDER:
				return isSetInitializeInOrder();
			case ApplicationPackage.APPLICATION__MODULES:
				return modules != null && !modules.isEmpty();
			case ApplicationPackage.APPLICATION__SECURITY_ROLES:
				return securityRoles != null && !securityRoles.isEmpty();
			case ApplicationPackage.APPLICATION__LIBRARY_DIRECTORY:
				return LIBRARY_DIRECTORY_EDEFAULT == null ? libraryDirectory != null : !LIBRARY_DIRECTORY_EDEFAULT.equals(libraryDirectory);
			case ApplicationPackage.APPLICATION__ENV_ENTRY:
				return envEntry != null && !envEntry.isEmpty();
			case ApplicationPackage.APPLICATION__EJB_REF:
				return ejbRef != null && !ejbRef.isEmpty();
			case ApplicationPackage.APPLICATION__EJB_LOCAL_REF:
				return ejbLocalRef != null && !ejbLocalRef.isEmpty();
			case ApplicationPackage.APPLICATION__SERVICE_REFS:
				return serviceRefs != null && !serviceRefs.isEmpty();
			case ApplicationPackage.APPLICATION__RESOURCE_REF:
				return resourceRef != null && !resourceRef.isEmpty();
			case ApplicationPackage.APPLICATION__RESOURCE_ENV_REF:
				return resourceEnvRef != null && !resourceEnvRef.isEmpty();
			case ApplicationPackage.APPLICATION__MESSAGE_DESTINATION_REF:
				return messageDestinationRef != null && !messageDestinationRef.isEmpty();
			case ApplicationPackage.APPLICATION__PERSISTENCE_CONTEXT_REF:
				return persistenceContextRef != null && !persistenceContextRef.isEmpty();
			case ApplicationPackage.APPLICATION__PERSISTENCE_UNIT_REF:
				return persistenceUnitRef != null && !persistenceUnitRef.isEmpty();
			case ApplicationPackage.APPLICATION__MESSAGE_DESTINATION:
				return messageDestination != null && !messageDestination.isEmpty();
			case ApplicationPackage.APPLICATION__DATA_SOURCE:
				return dataSource != null && !dataSource.isEmpty();
			case ApplicationPackage.APPLICATION__JMS_CONNECTION_FACTORY:
				return jmsConnectionFactory != null && !jmsConnectionFactory.isEmpty();
			case ApplicationPackage.APPLICATION__JMS_DESTINATION:
				return jmsDestination != null && !jmsDestination.isEmpty();
			case ApplicationPackage.APPLICATION__MAIL_SESSION:
				return mailSession != null && !mailSession.isEmpty();
			case ApplicationPackage.APPLICATION__CONNECTION_FACTORY:
				return connectionFactory != null && !connectionFactory.isEmpty();
			case ApplicationPackage.APPLICATION__ADMINISTERED_OBJECT:
				return administeredObject != null && !administeredObject.isEmpty();
			case ApplicationPackage.APPLICATION__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case ApplicationPackage.APPLICATION__VERSION:
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
		result.append(" (applicationName: "); //$NON-NLS-1$
		result.append(applicationName);
		result.append(", initializeInOrder: "); //$NON-NLS-1$
		if (initializeInOrderESet) result.append(initializeInOrder); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", libraryDirectory: "); //$NON-NLS-1$
		result.append(libraryDirectory);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(", version: "); //$NON-NLS-1$
		if (versionESet) result.append(version); else result.append("<unset>"); //$NON-NLS-1$
		result.append(')');
		return result.toString();
	}

	/**
	 * Gets the first module matching the specified uri
	 * @param uri The uri of a module to find.
	 * @return The first matching module or null if no module is found.
	 */
	@Override
	public Module getFirstModule(String uri){
		if (uri == null) return null;
		java.util.Iterator allModules = getModules().iterator();
		while (allModules.hasNext()){
			Module aModule = (Module)allModules.next();
			if(uri.equals(aModule.getUri())) return aModule;
		}
		return null;
	}

	@Override
	public Module getModule(String uri, String altDD) {
		List allModules = getModules();
		for (int i = 0; i < allModules.size(); i++) {
			Module aModule = (Module) allModules.get(i);
			if (StringUtil.stringsEqual(uri, aModule.getUri()) && StringUtil.stringsEqual(altDD, aModule.getAltDd()))
				return aModule;
		}
		return null;
	}

	@Override
	public List getEARModules() {
		return getModules();
	}

	@Override
	public ICommonModule getFirstEARModule(String uri) {
		return (ICommonModule) getFirstModule(uri);
	}

} //ApplicationImpl