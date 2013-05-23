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
package org.eclipse.jst.javaee.web.internal.impl;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
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
import org.eclipse.jst.javaee.core.Listener;
import org.eclipse.jst.javaee.core.MailSessionType;
import org.eclipse.jst.javaee.core.MessageDestination;
import org.eclipse.jst.javaee.core.MessageDestinationRef;
import org.eclipse.jst.javaee.core.ParamValue;
import org.eclipse.jst.javaee.core.PersistenceContextRef;
import org.eclipse.jst.javaee.core.PersistenceUnitRef;
import org.eclipse.jst.javaee.core.ResourceEnvRef;
import org.eclipse.jst.javaee.core.ResourceRef;
import org.eclipse.jst.javaee.core.SecurityRole;
import org.eclipse.jst.javaee.core.ServiceRef;
import org.eclipse.jst.javaee.jsp.JspConfig;
import org.eclipse.jst.javaee.web.AbsoluteOrderingType;
import org.eclipse.jst.javaee.web.ErrorPage;
import org.eclipse.jst.javaee.web.Filter;
import org.eclipse.jst.javaee.web.FilterMapping;
import org.eclipse.jst.javaee.web.LocaleEncodingMappingList;
import org.eclipse.jst.javaee.web.LoginConfig;
import org.eclipse.jst.javaee.web.MimeMapping;
import org.eclipse.jst.javaee.web.SecurityConstraint;
import org.eclipse.jst.javaee.web.Servlet;
import org.eclipse.jst.javaee.web.ServletMapping;
import org.eclipse.jst.javaee.web.SessionConfig;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.javaee.web.WebAppVersionType;

import org.eclipse.jst.javaee.web.WelcomeFileList;
import org.eclipse.jst.javaee.web.internal.metadata.WebPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>App</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getModuleName <em>Module Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getDisplayNames <em>Display Names</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getIcons <em>Icons</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getDistributables <em>Distributables</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getContextParams <em>Context Params</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getFilters <em>Filters</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getFilterMappings <em>Filter Mappings</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getListeners <em>Listeners</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getServlets <em>Servlets</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getServletMappings <em>Servlet Mappings</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getSessionConfigs <em>Session Configs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getMimeMappings <em>Mime Mappings</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getWelcomeFileLists <em>Welcome File Lists</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getErrorPages <em>Error Pages</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getJspConfigs <em>Jsp Configs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getSecurityConstraints <em>Security Constraints</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getLoginConfigs <em>Login Configs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getSecurityRoles <em>Security Roles</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getEnvEntries <em>Env Entries</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getEjbRefs <em>Ejb Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getEjbLocalRefs <em>Ejb Local Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getServiceRefs <em>Service Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getResourceRefs <em>Resource Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getResourceEnvRefs <em>Resource Env Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getMessageDestinationRefs <em>Message Destination Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getPersistenceContextRefs <em>Persistence Context Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getPersistenceUnitRefs <em>Persistence Unit Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getPostConstructs <em>Post Constructs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getPreDestroys <em>Pre Destroys</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getDataSource <em>Data Source</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getJmsConnectionFactory <em>Jms Connection Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getJmsDestination <em>Jms Destination</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getMailSession <em>Mail Session</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getConnectionFactory <em>Connection Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getAdministeredObject <em>Administered Object</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getMessageDestinations <em>Message Destinations</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getLocalEncodingMappingsLists <em>Local Encoding Mappings Lists</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getDenyUncoveredHttpMethods <em>Deny Uncovered Http Methods</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getAbsoluteOrdering <em>Absolute Ordering</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#isMetadataComplete <em>Metadata Complete</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl#getVersion <em>Version</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WebAppImpl extends EObjectImpl implements WebApp {
	/**
	 * The cached value of the '{@link #getGroup() <em>Group</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group;

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
	protected static final WebAppVersionType VERSION_EDEFAULT = WebAppVersionType._31;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected WebAppVersionType version = VERSION_EDEFAULT;

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
	protected WebAppImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WebPackage.Literals.WEB_APP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, WebPackage.WEB_APP__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Description> getDescriptions() {
		return getGroup().list(WebPackage.Literals.WEB_APP__DESCRIPTIONS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<DisplayName> getDisplayNames() {
		return getGroup().list(WebPackage.Literals.WEB_APP__DISPLAY_NAMES);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Icon> getIcons() {
		return getGroup().list(WebPackage.Literals.WEB_APP__ICONS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<EmptyType> getDistributables() {
		return getGroup().list(WebPackage.Literals.WEB_APP__DISTRIBUTABLES);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ParamValue> getContextParams() {
		return getGroup().list(WebPackage.Literals.WEB_APP__CONTEXT_PARAMS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Filter> getFilters() {
		return getGroup().list(WebPackage.Literals.WEB_APP__FILTERS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<FilterMapping> getFilterMappings() {
		return getGroup().list(WebPackage.Literals.WEB_APP__FILTER_MAPPINGS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Listener> getListeners() {
		return getGroup().list(WebPackage.Literals.WEB_APP__LISTENERS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Servlet> getServlets() {
		return getGroup().list(WebPackage.Literals.WEB_APP__SERVLETS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ServletMapping> getServletMappings() {
		return getGroup().list(WebPackage.Literals.WEB_APP__SERVLET_MAPPINGS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<SessionConfig> getSessionConfigs() {
		return getGroup().list(WebPackage.Literals.WEB_APP__SESSION_CONFIGS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<MimeMapping> getMimeMappings() {
		return getGroup().list(WebPackage.Literals.WEB_APP__MIME_MAPPINGS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<WelcomeFileList> getWelcomeFileLists() {
		return getGroup().list(WebPackage.Literals.WEB_APP__WELCOME_FILE_LISTS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ErrorPage> getErrorPages() {
		return getGroup().list(WebPackage.Literals.WEB_APP__ERROR_PAGES);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<JspConfig> getJspConfigs() {
		return getGroup().list(WebPackage.Literals.WEB_APP__JSP_CONFIGS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<SecurityConstraint> getSecurityConstraints() {
		return getGroup().list(WebPackage.Literals.WEB_APP__SECURITY_CONSTRAINTS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<LoginConfig> getLoginConfigs() {
		return getGroup().list(WebPackage.Literals.WEB_APP__LOGIN_CONFIGS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<SecurityRole> getSecurityRoles() {
		return getGroup().list(WebPackage.Literals.WEB_APP__SECURITY_ROLES);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<EnvEntry> getEnvEntries() {
		return getGroup().list(WebPackage.Literals.WEB_APP__ENV_ENTRIES);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<EjbRef> getEjbRefs() {
		return getGroup().list(WebPackage.Literals.WEB_APP__EJB_REFS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<EjbLocalRef> getEjbLocalRefs() {
		return getGroup().list(WebPackage.Literals.WEB_APP__EJB_LOCAL_REFS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ServiceRef> getServiceRefs() {
		return getGroup().list(WebPackage.Literals.WEB_APP__SERVICE_REFS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ResourceRef> getResourceRefs() {
		return getGroup().list(WebPackage.Literals.WEB_APP__RESOURCE_REFS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ResourceEnvRef> getResourceEnvRefs() {
		return getGroup().list(WebPackage.Literals.WEB_APP__RESOURCE_ENV_REFS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<MessageDestinationRef> getMessageDestinationRefs() {
		return getGroup().list(WebPackage.Literals.WEB_APP__MESSAGE_DESTINATION_REFS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<PersistenceContextRef> getPersistenceContextRefs() {
		return getGroup().list(WebPackage.Literals.WEB_APP__PERSISTENCE_CONTEXT_REFS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<PersistenceUnitRef> getPersistenceUnitRefs() {
		return getGroup().list(WebPackage.Literals.WEB_APP__PERSISTENCE_UNIT_REFS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<LifecycleCallback> getPostConstructs() {
		return getGroup().list(WebPackage.Literals.WEB_APP__POST_CONSTRUCTS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<LifecycleCallback> getPreDestroys() {
		return getGroup().list(WebPackage.Literals.WEB_APP__PRE_DESTROYS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<DataSourceType> getDataSource() {
		return getGroup().list(WebPackage.Literals.WEB_APP__DATA_SOURCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<JmsConnectionFactoryType> getJmsConnectionFactory() {
		return getGroup().list(WebPackage.Literals.WEB_APP__JMS_CONNECTION_FACTORY);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<JmsDestinationType> getJmsDestination() {
		return getGroup().list(WebPackage.Literals.WEB_APP__JMS_DESTINATION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<MailSessionType> getMailSession() {
		return getGroup().list(WebPackage.Literals.WEB_APP__MAIL_SESSION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ConnectionFactoryResourceType> getConnectionFactory() {
		return getGroup().list(WebPackage.Literals.WEB_APP__CONNECTION_FACTORY);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<AdministeredObjectType> getAdministeredObject() {
		return getGroup().list(WebPackage.Literals.WEB_APP__ADMINISTERED_OBJECT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<MessageDestination> getMessageDestinations() {
		return getGroup().list(WebPackage.Literals.WEB_APP__MESSAGE_DESTINATIONS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<LocaleEncodingMappingList> getLocalEncodingMappingsLists() {
		return getGroup().list(WebPackage.Literals.WEB_APP__LOCAL_ENCODING_MAPPINGS_LISTS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<EmptyType> getDenyUncoveredHttpMethods() {
		return getGroup().list(WebPackage.Literals.WEB_APP__DENY_UNCOVERED_HTTP_METHODS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<String> getModuleName() {
		return getGroup().list(WebPackage.Literals.WEB_APP__MODULE_NAME);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<AbsoluteOrderingType> getAbsoluteOrdering() {
		return getGroup().list(WebPackage.Literals.WEB_APP__ABSOLUTE_ORDERING);
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
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.WEB_APP__ID, oldId, id));
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
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.WEB_APP__METADATA_COMPLETE, oldMetadataComplete, metadataComplete, !oldMetadataCompleteESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, WebPackage.WEB_APP__METADATA_COMPLETE, oldMetadataComplete, METADATA_COMPLETE_EDEFAULT, oldMetadataCompleteESet));
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
	public WebAppVersionType getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersion(WebAppVersionType newVersion) {
		WebAppVersionType oldVersion = version;
		version = newVersion == null ? VERSION_EDEFAULT : newVersion;
		boolean oldVersionESet = versionESet;
		versionESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.WEB_APP__VERSION, oldVersion, version, !oldVersionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetVersion() {
		WebAppVersionType oldVersion = version;
		boolean oldVersionESet = versionESet;
		version = VERSION_EDEFAULT;
		versionESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, WebPackage.WEB_APP__VERSION, oldVersion, VERSION_EDEFAULT, oldVersionESet));
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
			case WebPackage.WEB_APP__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__DESCRIPTIONS:
				return ((InternalEList<?>)getDescriptions()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__DISPLAY_NAMES:
				return ((InternalEList<?>)getDisplayNames()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__ICONS:
				return ((InternalEList<?>)getIcons()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__DISTRIBUTABLES:
				return ((InternalEList<?>)getDistributables()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__CONTEXT_PARAMS:
				return ((InternalEList<?>)getContextParams()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__FILTERS:
				return ((InternalEList<?>)getFilters()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__FILTER_MAPPINGS:
				return ((InternalEList<?>)getFilterMappings()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__LISTENERS:
				return ((InternalEList<?>)getListeners()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__SERVLETS:
				return ((InternalEList<?>)getServlets()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__SERVLET_MAPPINGS:
				return ((InternalEList<?>)getServletMappings()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__SESSION_CONFIGS:
				return ((InternalEList<?>)getSessionConfigs()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__MIME_MAPPINGS:
				return ((InternalEList<?>)getMimeMappings()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__WELCOME_FILE_LISTS:
				return ((InternalEList<?>)getWelcomeFileLists()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__ERROR_PAGES:
				return ((InternalEList<?>)getErrorPages()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__JSP_CONFIGS:
				return ((InternalEList<?>)getJspConfigs()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__SECURITY_CONSTRAINTS:
				return ((InternalEList<?>)getSecurityConstraints()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__LOGIN_CONFIGS:
				return ((InternalEList<?>)getLoginConfigs()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__SECURITY_ROLES:
				return ((InternalEList<?>)getSecurityRoles()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__ENV_ENTRIES:
				return ((InternalEList<?>)getEnvEntries()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__EJB_REFS:
				return ((InternalEList<?>)getEjbRefs()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__EJB_LOCAL_REFS:
				return ((InternalEList<?>)getEjbLocalRefs()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__SERVICE_REFS:
				return ((InternalEList<?>)getServiceRefs()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__RESOURCE_REFS:
				return ((InternalEList<?>)getResourceRefs()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__RESOURCE_ENV_REFS:
				return ((InternalEList<?>)getResourceEnvRefs()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__MESSAGE_DESTINATION_REFS:
				return ((InternalEList<?>)getMessageDestinationRefs()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__PERSISTENCE_CONTEXT_REFS:
				return ((InternalEList<?>)getPersistenceContextRefs()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__PERSISTENCE_UNIT_REFS:
				return ((InternalEList<?>)getPersistenceUnitRefs()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__POST_CONSTRUCTS:
				return ((InternalEList<?>)getPostConstructs()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__PRE_DESTROYS:
				return ((InternalEList<?>)getPreDestroys()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__DATA_SOURCE:
				return ((InternalEList<?>)getDataSource()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__JMS_CONNECTION_FACTORY:
				return ((InternalEList<?>)getJmsConnectionFactory()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__JMS_DESTINATION:
				return ((InternalEList<?>)getJmsDestination()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__MAIL_SESSION:
				return ((InternalEList<?>)getMailSession()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__CONNECTION_FACTORY:
				return ((InternalEList<?>)getConnectionFactory()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__ADMINISTERED_OBJECT:
				return ((InternalEList<?>)getAdministeredObject()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__MESSAGE_DESTINATIONS:
				return ((InternalEList<?>)getMessageDestinations()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__LOCAL_ENCODING_MAPPINGS_LISTS:
				return ((InternalEList<?>)getLocalEncodingMappingsLists()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__DENY_UNCOVERED_HTTP_METHODS:
				return ((InternalEList<?>)getDenyUncoveredHttpMethods()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_APP__ABSOLUTE_ORDERING:
				return ((InternalEList<?>)getAbsoluteOrdering()).basicRemove(otherEnd, msgs);
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
			case WebPackage.WEB_APP__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case WebPackage.WEB_APP__MODULE_NAME:
				return getModuleName();
			case WebPackage.WEB_APP__DESCRIPTIONS:
				return getDescriptions();
			case WebPackage.WEB_APP__DISPLAY_NAMES:
				return getDisplayNames();
			case WebPackage.WEB_APP__ICONS:
				return getIcons();
			case WebPackage.WEB_APP__DISTRIBUTABLES:
				return getDistributables();
			case WebPackage.WEB_APP__CONTEXT_PARAMS:
				return getContextParams();
			case WebPackage.WEB_APP__FILTERS:
				return getFilters();
			case WebPackage.WEB_APP__FILTER_MAPPINGS:
				return getFilterMappings();
			case WebPackage.WEB_APP__LISTENERS:
				return getListeners();
			case WebPackage.WEB_APP__SERVLETS:
				return getServlets();
			case WebPackage.WEB_APP__SERVLET_MAPPINGS:
				return getServletMappings();
			case WebPackage.WEB_APP__SESSION_CONFIGS:
				return getSessionConfigs();
			case WebPackage.WEB_APP__MIME_MAPPINGS:
				return getMimeMappings();
			case WebPackage.WEB_APP__WELCOME_FILE_LISTS:
				return getWelcomeFileLists();
			case WebPackage.WEB_APP__ERROR_PAGES:
				return getErrorPages();
			case WebPackage.WEB_APP__JSP_CONFIGS:
				return getJspConfigs();
			case WebPackage.WEB_APP__SECURITY_CONSTRAINTS:
				return getSecurityConstraints();
			case WebPackage.WEB_APP__LOGIN_CONFIGS:
				return getLoginConfigs();
			case WebPackage.WEB_APP__SECURITY_ROLES:
				return getSecurityRoles();
			case WebPackage.WEB_APP__ENV_ENTRIES:
				return getEnvEntries();
			case WebPackage.WEB_APP__EJB_REFS:
				return getEjbRefs();
			case WebPackage.WEB_APP__EJB_LOCAL_REFS:
				return getEjbLocalRefs();
			case WebPackage.WEB_APP__SERVICE_REFS:
				return getServiceRefs();
			case WebPackage.WEB_APP__RESOURCE_REFS:
				return getResourceRefs();
			case WebPackage.WEB_APP__RESOURCE_ENV_REFS:
				return getResourceEnvRefs();
			case WebPackage.WEB_APP__MESSAGE_DESTINATION_REFS:
				return getMessageDestinationRefs();
			case WebPackage.WEB_APP__PERSISTENCE_CONTEXT_REFS:
				return getPersistenceContextRefs();
			case WebPackage.WEB_APP__PERSISTENCE_UNIT_REFS:
				return getPersistenceUnitRefs();
			case WebPackage.WEB_APP__POST_CONSTRUCTS:
				return getPostConstructs();
			case WebPackage.WEB_APP__PRE_DESTROYS:
				return getPreDestroys();
			case WebPackage.WEB_APP__DATA_SOURCE:
				return getDataSource();
			case WebPackage.WEB_APP__JMS_CONNECTION_FACTORY:
				return getJmsConnectionFactory();
			case WebPackage.WEB_APP__JMS_DESTINATION:
				return getJmsDestination();
			case WebPackage.WEB_APP__MAIL_SESSION:
				return getMailSession();
			case WebPackage.WEB_APP__CONNECTION_FACTORY:
				return getConnectionFactory();
			case WebPackage.WEB_APP__ADMINISTERED_OBJECT:
				return getAdministeredObject();
			case WebPackage.WEB_APP__MESSAGE_DESTINATIONS:
				return getMessageDestinations();
			case WebPackage.WEB_APP__LOCAL_ENCODING_MAPPINGS_LISTS:
				return getLocalEncodingMappingsLists();
			case WebPackage.WEB_APP__DENY_UNCOVERED_HTTP_METHODS:
				return getDenyUncoveredHttpMethods();
			case WebPackage.WEB_APP__ABSOLUTE_ORDERING:
				return getAbsoluteOrdering();
			case WebPackage.WEB_APP__ID:
				return getId();
			case WebPackage.WEB_APP__METADATA_COMPLETE:
				return isMetadataComplete();
			case WebPackage.WEB_APP__VERSION:
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
			case WebPackage.WEB_APP__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case WebPackage.WEB_APP__MODULE_NAME:
				getModuleName().clear();
				getModuleName().addAll((Collection<? extends String>)newValue);
				return;
			case WebPackage.WEB_APP__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection<? extends Description>)newValue);
				return;
			case WebPackage.WEB_APP__DISPLAY_NAMES:
				getDisplayNames().clear();
				getDisplayNames().addAll((Collection<? extends DisplayName>)newValue);
				return;
			case WebPackage.WEB_APP__ICONS:
				getIcons().clear();
				getIcons().addAll((Collection<? extends Icon>)newValue);
				return;
			case WebPackage.WEB_APP__DISTRIBUTABLES:
				getDistributables().clear();
				getDistributables().addAll((Collection<? extends EmptyType>)newValue);
				return;
			case WebPackage.WEB_APP__CONTEXT_PARAMS:
				getContextParams().clear();
				getContextParams().addAll((Collection<? extends ParamValue>)newValue);
				return;
			case WebPackage.WEB_APP__FILTERS:
				getFilters().clear();
				getFilters().addAll((Collection<? extends Filter>)newValue);
				return;
			case WebPackage.WEB_APP__FILTER_MAPPINGS:
				getFilterMappings().clear();
				getFilterMappings().addAll((Collection<? extends FilterMapping>)newValue);
				return;
			case WebPackage.WEB_APP__LISTENERS:
				getListeners().clear();
				getListeners().addAll((Collection<? extends Listener>)newValue);
				return;
			case WebPackage.WEB_APP__SERVLETS:
				getServlets().clear();
				getServlets().addAll((Collection<? extends Servlet>)newValue);
				return;
			case WebPackage.WEB_APP__SERVLET_MAPPINGS:
				getServletMappings().clear();
				getServletMappings().addAll((Collection<? extends ServletMapping>)newValue);
				return;
			case WebPackage.WEB_APP__SESSION_CONFIGS:
				getSessionConfigs().clear();
				getSessionConfigs().addAll((Collection<? extends SessionConfig>)newValue);
				return;
			case WebPackage.WEB_APP__MIME_MAPPINGS:
				getMimeMappings().clear();
				getMimeMappings().addAll((Collection<? extends MimeMapping>)newValue);
				return;
			case WebPackage.WEB_APP__WELCOME_FILE_LISTS:
				getWelcomeFileLists().clear();
				getWelcomeFileLists().addAll((Collection<? extends WelcomeFileList>)newValue);
				return;
			case WebPackage.WEB_APP__ERROR_PAGES:
				getErrorPages().clear();
				getErrorPages().addAll((Collection<? extends ErrorPage>)newValue);
				return;
			case WebPackage.WEB_APP__JSP_CONFIGS:
				getJspConfigs().clear();
				getJspConfigs().addAll((Collection<? extends JspConfig>)newValue);
				return;
			case WebPackage.WEB_APP__SECURITY_CONSTRAINTS:
				getSecurityConstraints().clear();
				getSecurityConstraints().addAll((Collection<? extends SecurityConstraint>)newValue);
				return;
			case WebPackage.WEB_APP__LOGIN_CONFIGS:
				getLoginConfigs().clear();
				getLoginConfigs().addAll((Collection<? extends LoginConfig>)newValue);
				return;
			case WebPackage.WEB_APP__SECURITY_ROLES:
				getSecurityRoles().clear();
				getSecurityRoles().addAll((Collection<? extends SecurityRole>)newValue);
				return;
			case WebPackage.WEB_APP__ENV_ENTRIES:
				getEnvEntries().clear();
				getEnvEntries().addAll((Collection<? extends EnvEntry>)newValue);
				return;
			case WebPackage.WEB_APP__EJB_REFS:
				getEjbRefs().clear();
				getEjbRefs().addAll((Collection<? extends EjbRef>)newValue);
				return;
			case WebPackage.WEB_APP__EJB_LOCAL_REFS:
				getEjbLocalRefs().clear();
				getEjbLocalRefs().addAll((Collection<? extends EjbLocalRef>)newValue);
				return;
			case WebPackage.WEB_APP__SERVICE_REFS:
				getServiceRefs().clear();
				getServiceRefs().addAll((Collection<? extends ServiceRef>)newValue);
				return;
			case WebPackage.WEB_APP__RESOURCE_REFS:
				getResourceRefs().clear();
				getResourceRefs().addAll((Collection<? extends ResourceRef>)newValue);
				return;
			case WebPackage.WEB_APP__RESOURCE_ENV_REFS:
				getResourceEnvRefs().clear();
				getResourceEnvRefs().addAll((Collection<? extends ResourceEnvRef>)newValue);
				return;
			case WebPackage.WEB_APP__MESSAGE_DESTINATION_REFS:
				getMessageDestinationRefs().clear();
				getMessageDestinationRefs().addAll((Collection<? extends MessageDestinationRef>)newValue);
				return;
			case WebPackage.WEB_APP__PERSISTENCE_CONTEXT_REFS:
				getPersistenceContextRefs().clear();
				getPersistenceContextRefs().addAll((Collection<? extends PersistenceContextRef>)newValue);
				return;
			case WebPackage.WEB_APP__PERSISTENCE_UNIT_REFS:
				getPersistenceUnitRefs().clear();
				getPersistenceUnitRefs().addAll((Collection<? extends PersistenceUnitRef>)newValue);
				return;
			case WebPackage.WEB_APP__POST_CONSTRUCTS:
				getPostConstructs().clear();
				getPostConstructs().addAll((Collection<? extends LifecycleCallback>)newValue);
				return;
			case WebPackage.WEB_APP__PRE_DESTROYS:
				getPreDestroys().clear();
				getPreDestroys().addAll((Collection<? extends LifecycleCallback>)newValue);
				return;
			case WebPackage.WEB_APP__DATA_SOURCE:
				getDataSource().clear();
				getDataSource().addAll((Collection<? extends DataSourceType>)newValue);
				return;
			case WebPackage.WEB_APP__JMS_CONNECTION_FACTORY:
				getJmsConnectionFactory().clear();
				getJmsConnectionFactory().addAll((Collection<? extends JmsConnectionFactoryType>)newValue);
				return;
			case WebPackage.WEB_APP__JMS_DESTINATION:
				getJmsDestination().clear();
				getJmsDestination().addAll((Collection<? extends JmsDestinationType>)newValue);
				return;
			case WebPackage.WEB_APP__MAIL_SESSION:
				getMailSession().clear();
				getMailSession().addAll((Collection<? extends MailSessionType>)newValue);
				return;
			case WebPackage.WEB_APP__CONNECTION_FACTORY:
				getConnectionFactory().clear();
				getConnectionFactory().addAll((Collection<? extends ConnectionFactoryResourceType>)newValue);
				return;
			case WebPackage.WEB_APP__ADMINISTERED_OBJECT:
				getAdministeredObject().clear();
				getAdministeredObject().addAll((Collection<? extends AdministeredObjectType>)newValue);
				return;
			case WebPackage.WEB_APP__MESSAGE_DESTINATIONS:
				getMessageDestinations().clear();
				getMessageDestinations().addAll((Collection<? extends MessageDestination>)newValue);
				return;
			case WebPackage.WEB_APP__LOCAL_ENCODING_MAPPINGS_LISTS:
				getLocalEncodingMappingsLists().clear();
				getLocalEncodingMappingsLists().addAll((Collection<? extends LocaleEncodingMappingList>)newValue);
				return;
			case WebPackage.WEB_APP__DENY_UNCOVERED_HTTP_METHODS:
				getDenyUncoveredHttpMethods().clear();
				getDenyUncoveredHttpMethods().addAll((Collection<? extends EmptyType>)newValue);
				return;
			case WebPackage.WEB_APP__ABSOLUTE_ORDERING:
				getAbsoluteOrdering().clear();
				getAbsoluteOrdering().addAll((Collection<? extends AbsoluteOrderingType>)newValue);
				return;
			case WebPackage.WEB_APP__ID:
				setId((String)newValue);
				return;
			case WebPackage.WEB_APP__METADATA_COMPLETE:
				setMetadataComplete((Boolean)newValue);
				return;
			case WebPackage.WEB_APP__VERSION:
				setVersion((WebAppVersionType)newValue);
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
			case WebPackage.WEB_APP__GROUP:
				getGroup().clear();
				return;
			case WebPackage.WEB_APP__MODULE_NAME:
				getModuleName().clear();
				return;
			case WebPackage.WEB_APP__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case WebPackage.WEB_APP__DISPLAY_NAMES:
				getDisplayNames().clear();
				return;
			case WebPackage.WEB_APP__ICONS:
				getIcons().clear();
				return;
			case WebPackage.WEB_APP__DISTRIBUTABLES:
				getDistributables().clear();
				return;
			case WebPackage.WEB_APP__CONTEXT_PARAMS:
				getContextParams().clear();
				return;
			case WebPackage.WEB_APP__FILTERS:
				getFilters().clear();
				return;
			case WebPackage.WEB_APP__FILTER_MAPPINGS:
				getFilterMappings().clear();
				return;
			case WebPackage.WEB_APP__LISTENERS:
				getListeners().clear();
				return;
			case WebPackage.WEB_APP__SERVLETS:
				getServlets().clear();
				return;
			case WebPackage.WEB_APP__SERVLET_MAPPINGS:
				getServletMappings().clear();
				return;
			case WebPackage.WEB_APP__SESSION_CONFIGS:
				getSessionConfigs().clear();
				return;
			case WebPackage.WEB_APP__MIME_MAPPINGS:
				getMimeMappings().clear();
				return;
			case WebPackage.WEB_APP__WELCOME_FILE_LISTS:
				getWelcomeFileLists().clear();
				return;
			case WebPackage.WEB_APP__ERROR_PAGES:
				getErrorPages().clear();
				return;
			case WebPackage.WEB_APP__JSP_CONFIGS:
				getJspConfigs().clear();
				return;
			case WebPackage.WEB_APP__SECURITY_CONSTRAINTS:
				getSecurityConstraints().clear();
				return;
			case WebPackage.WEB_APP__LOGIN_CONFIGS:
				getLoginConfigs().clear();
				return;
			case WebPackage.WEB_APP__SECURITY_ROLES:
				getSecurityRoles().clear();
				return;
			case WebPackage.WEB_APP__ENV_ENTRIES:
				getEnvEntries().clear();
				return;
			case WebPackage.WEB_APP__EJB_REFS:
				getEjbRefs().clear();
				return;
			case WebPackage.WEB_APP__EJB_LOCAL_REFS:
				getEjbLocalRefs().clear();
				return;
			case WebPackage.WEB_APP__SERVICE_REFS:
				getServiceRefs().clear();
				return;
			case WebPackage.WEB_APP__RESOURCE_REFS:
				getResourceRefs().clear();
				return;
			case WebPackage.WEB_APP__RESOURCE_ENV_REFS:
				getResourceEnvRefs().clear();
				return;
			case WebPackage.WEB_APP__MESSAGE_DESTINATION_REFS:
				getMessageDestinationRefs().clear();
				return;
			case WebPackage.WEB_APP__PERSISTENCE_CONTEXT_REFS:
				getPersistenceContextRefs().clear();
				return;
			case WebPackage.WEB_APP__PERSISTENCE_UNIT_REFS:
				getPersistenceUnitRefs().clear();
				return;
			case WebPackage.WEB_APP__POST_CONSTRUCTS:
				getPostConstructs().clear();
				return;
			case WebPackage.WEB_APP__PRE_DESTROYS:
				getPreDestroys().clear();
				return;
			case WebPackage.WEB_APP__DATA_SOURCE:
				getDataSource().clear();
				return;
			case WebPackage.WEB_APP__JMS_CONNECTION_FACTORY:
				getJmsConnectionFactory().clear();
				return;
			case WebPackage.WEB_APP__JMS_DESTINATION:
				getJmsDestination().clear();
				return;
			case WebPackage.WEB_APP__MAIL_SESSION:
				getMailSession().clear();
				return;
			case WebPackage.WEB_APP__CONNECTION_FACTORY:
				getConnectionFactory().clear();
				return;
			case WebPackage.WEB_APP__ADMINISTERED_OBJECT:
				getAdministeredObject().clear();
				return;
			case WebPackage.WEB_APP__MESSAGE_DESTINATIONS:
				getMessageDestinations().clear();
				return;
			case WebPackage.WEB_APP__LOCAL_ENCODING_MAPPINGS_LISTS:
				getLocalEncodingMappingsLists().clear();
				return;
			case WebPackage.WEB_APP__DENY_UNCOVERED_HTTP_METHODS:
				getDenyUncoveredHttpMethods().clear();
				return;
			case WebPackage.WEB_APP__ABSOLUTE_ORDERING:
				getAbsoluteOrdering().clear();
				return;
			case WebPackage.WEB_APP__ID:
				setId(ID_EDEFAULT);
				return;
			case WebPackage.WEB_APP__METADATA_COMPLETE:
				unsetMetadataComplete();
				return;
			case WebPackage.WEB_APP__VERSION:
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
			case WebPackage.WEB_APP__GROUP:
				return group != null && !group.isEmpty();
			case WebPackage.WEB_APP__MODULE_NAME:
				return !getModuleName().isEmpty();
			case WebPackage.WEB_APP__DESCRIPTIONS:
				return !getDescriptions().isEmpty();
			case WebPackage.WEB_APP__DISPLAY_NAMES:
				return !getDisplayNames().isEmpty();
			case WebPackage.WEB_APP__ICONS:
				return !getIcons().isEmpty();
			case WebPackage.WEB_APP__DISTRIBUTABLES:
				return !getDistributables().isEmpty();
			case WebPackage.WEB_APP__CONTEXT_PARAMS:
				return !getContextParams().isEmpty();
			case WebPackage.WEB_APP__FILTERS:
				return !getFilters().isEmpty();
			case WebPackage.WEB_APP__FILTER_MAPPINGS:
				return !getFilterMappings().isEmpty();
			case WebPackage.WEB_APP__LISTENERS:
				return !getListeners().isEmpty();
			case WebPackage.WEB_APP__SERVLETS:
				return !getServlets().isEmpty();
			case WebPackage.WEB_APP__SERVLET_MAPPINGS:
				return !getServletMappings().isEmpty();
			case WebPackage.WEB_APP__SESSION_CONFIGS:
				return !getSessionConfigs().isEmpty();
			case WebPackage.WEB_APP__MIME_MAPPINGS:
				return !getMimeMappings().isEmpty();
			case WebPackage.WEB_APP__WELCOME_FILE_LISTS:
				return !getWelcomeFileLists().isEmpty();
			case WebPackage.WEB_APP__ERROR_PAGES:
				return !getErrorPages().isEmpty();
			case WebPackage.WEB_APP__JSP_CONFIGS:
				return !getJspConfigs().isEmpty();
			case WebPackage.WEB_APP__SECURITY_CONSTRAINTS:
				return !getSecurityConstraints().isEmpty();
			case WebPackage.WEB_APP__LOGIN_CONFIGS:
				return !getLoginConfigs().isEmpty();
			case WebPackage.WEB_APP__SECURITY_ROLES:
				return !getSecurityRoles().isEmpty();
			case WebPackage.WEB_APP__ENV_ENTRIES:
				return !getEnvEntries().isEmpty();
			case WebPackage.WEB_APP__EJB_REFS:
				return !getEjbRefs().isEmpty();
			case WebPackage.WEB_APP__EJB_LOCAL_REFS:
				return !getEjbLocalRefs().isEmpty();
			case WebPackage.WEB_APP__SERVICE_REFS:
				return !getServiceRefs().isEmpty();
			case WebPackage.WEB_APP__RESOURCE_REFS:
				return !getResourceRefs().isEmpty();
			case WebPackage.WEB_APP__RESOURCE_ENV_REFS:
				return !getResourceEnvRefs().isEmpty();
			case WebPackage.WEB_APP__MESSAGE_DESTINATION_REFS:
				return !getMessageDestinationRefs().isEmpty();
			case WebPackage.WEB_APP__PERSISTENCE_CONTEXT_REFS:
				return !getPersistenceContextRefs().isEmpty();
			case WebPackage.WEB_APP__PERSISTENCE_UNIT_REFS:
				return !getPersistenceUnitRefs().isEmpty();
			case WebPackage.WEB_APP__POST_CONSTRUCTS:
				return !getPostConstructs().isEmpty();
			case WebPackage.WEB_APP__PRE_DESTROYS:
				return !getPreDestroys().isEmpty();
			case WebPackage.WEB_APP__DATA_SOURCE:
				return !getDataSource().isEmpty();
			case WebPackage.WEB_APP__JMS_CONNECTION_FACTORY:
				return !getJmsConnectionFactory().isEmpty();
			case WebPackage.WEB_APP__JMS_DESTINATION:
				return !getJmsDestination().isEmpty();
			case WebPackage.WEB_APP__MAIL_SESSION:
				return !getMailSession().isEmpty();
			case WebPackage.WEB_APP__CONNECTION_FACTORY:
				return !getConnectionFactory().isEmpty();
			case WebPackage.WEB_APP__ADMINISTERED_OBJECT:
				return !getAdministeredObject().isEmpty();
			case WebPackage.WEB_APP__MESSAGE_DESTINATIONS:
				return !getMessageDestinations().isEmpty();
			case WebPackage.WEB_APP__LOCAL_ENCODING_MAPPINGS_LISTS:
				return !getLocalEncodingMappingsLists().isEmpty();
			case WebPackage.WEB_APP__DENY_UNCOVERED_HTTP_METHODS:
				return !getDenyUncoveredHttpMethods().isEmpty();
			case WebPackage.WEB_APP__ABSOLUTE_ORDERING:
				return !getAbsoluteOrdering().isEmpty();
			case WebPackage.WEB_APP__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case WebPackage.WEB_APP__METADATA_COMPLETE:
				return isSetMetadataComplete();
			case WebPackage.WEB_APP__VERSION:
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
		result.append(" (group: "); //$NON-NLS-1$
		result.append(group);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(", metadataComplete: "); //$NON-NLS-1$
		if (metadataCompleteESet) result.append(metadataComplete); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", version: "); //$NON-NLS-1$
		if (versionESet) result.append(version); else result.append("<unset>"); //$NON-NLS-1$
		result.append(')');
		return result.toString();
	}

} //WebAppImpl