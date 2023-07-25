/*******************************************************************************
 * Copyright (c) 2009, 2023 IBM Corporation and others.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/**
 * <copyright>
 * </copyright>
 *
 * $Id: WebFragment.java,v 1.3 2010/02/11 06:22:51 canderson Exp $
 */
package org.eclipse.jst.javaee.web;

import java.util.List;
import org.eclipse.emf.ecore.util.FeatureMap;
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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fragment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 *         @since Java EE 6, Web 3.0
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getGroup <em>Group</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getDisplayNames <em>Display Names</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getIcons <em>Icons</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getDistributables <em>Distributables</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getContextParams <em>Context Params</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getFilters <em>Filters</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getFilterMappings <em>Filter Mappings</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getListeners <em>Listeners</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getServlets <em>Servlets</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getServletMappings <em>Servlet Mappings</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getSessionConfigs <em>Session Configs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getMimeMappings <em>Mime Mappings</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getWelcomeFileLists <em>Welcome File Lists</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getErrorPages <em>Error Pages</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getJspConfigs <em>Jsp Configs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getSecurityConstraints <em>Security Constraints</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getLoginConfigs <em>Login Configs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getSecurityRoles <em>Security Roles</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getEnvEntries <em>Env Entries</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getEjbRefs <em>Ejb Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getEjbLocalRefs <em>Ejb Local Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getServiceRefs <em>Service Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getResourceRefs <em>Resource Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getResourceEnvRefs <em>Resource Env Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getMessageDestinationRefs <em>Message Destination Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getPersistenceContextRefs <em>Persistence Context Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getPersistenceUnitRefs <em>Persistence Unit Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getPostConstructs <em>Post Constructs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getPreDestroys <em>Pre Destroys</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getDataSource <em>Data Source</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getJmsConnectionFactory <em>Jms Connection Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getJmsDestination <em>Jms Destination</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getMailSession <em>Mail Session</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getConnectionFactory <em>Connection Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getAdministeredObject <em>Administered Object</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getMessageDestinations <em>Message Destinations</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getLocalEncodingMappingsLists <em>Local Encoding Mappings Lists</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getOrdering <em>Ordering</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#isMetadataComplete <em>Metadata Complete</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebFragment#getVersion <em>Version</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment()
 * @extends JavaEEObject
 * @generated
 */
public interface WebFragment extends IWebCommon {
	
	/**
	 * Returns the value of the '<em><b>Group</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group</em>' attribute list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_Group()
	 * @generated
	 */
	@Override
	FeatureMap getGroup();


	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_Name()
	 * @generated
	 */
	List<String> getName();


	/**
	 * Returns the value of the '<em><b>Descriptions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.Description}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Descriptions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Descriptions</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_Descriptions()
	 * @generated
	 */
	@Override
	List<Description> getDescriptions();


	/**
	 * Returns the value of the '<em><b>Display Names</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.DisplayName}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Display Names</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Display Names</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_DisplayNames()
	 * @generated
	 */
	@Override
	List<DisplayName> getDisplayNames();


	/**
	 * Returns the value of the '<em><b>Icons</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.Icon}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Icons</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Icons</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_Icons()
	 * @generated
	 */
	@Override
	List<Icon> getIcons();


	/**
	 * Returns the value of the '<em><b>Distributables</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.EmptyType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Distributables</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Distributables</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_Distributables()
	 * @generated
	 */
	@Override
	List<EmptyType> getDistributables();


	/**
	 * Returns the value of the '<em><b>Context Params</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.ParamValue}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The context-param element contains the declaration
	 *             of a web application's servlet context
	 *             initialization parameters.
	 *             
	 *             @since Java EE 5, Web 2.5
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Context Params</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_ContextParams()
	 * @generated
	 */
	@Override
	List<ParamValue> getContextParams();


	/**
	 * Returns the value of the '<em><b>Filters</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.web.Filter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filters</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_Filters()
	 * @generated
	 */
	@Override
	List<Filter> getFilters();


	/**
	 * Returns the value of the '<em><b>Filter Mappings</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.web.FilterMapping}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filter Mappings</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filter Mappings</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_FilterMappings()
	 * @generated
	 */
	@Override
	List<FilterMapping> getFilterMappings();


	/**
	 * Returns the value of the '<em><b>Listeners</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.Listener}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Listeners</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Listeners</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_Listeners()
	 * @generated
	 */
	@Override
	List<Listener> getListeners();


	/**
	 * Returns the value of the '<em><b>Servlets</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.web.Servlet}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Servlets</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Servlets</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_Servlets()
	 * @generated
	 */
	@Override
	List<Servlet> getServlets();


	/**
	 * Returns the value of the '<em><b>Servlet Mappings</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.web.ServletMapping}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Servlet Mappings</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Servlet Mappings</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_ServletMappings()
	 * @generated
	 */
	@Override
	List<ServletMapping> getServletMappings();


	/**
	 * Returns the value of the '<em><b>Session Configs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.web.SessionConfig}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Session Configs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Session Configs</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_SessionConfigs()
	 * @generated
	 */
	@Override
	List<SessionConfig> getSessionConfigs();


	/**
	 * Returns the value of the '<em><b>Mime Mappings</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.web.MimeMapping}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mime Mappings</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mime Mappings</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_MimeMappings()
	 * @generated
	 */
	@Override
	List<MimeMapping> getMimeMappings();


	/**
	 * Returns the value of the '<em><b>Welcome File Lists</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.web.WelcomeFileList}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Welcome File Lists</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Welcome File Lists</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_WelcomeFileLists()
	 * @generated
	 */
	@Override
	List<WelcomeFileList> getWelcomeFileLists();


	/**
	 * Returns the value of the '<em><b>Error Pages</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.web.ErrorPage}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Error Pages</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Error Pages</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_ErrorPages()
	 * @generated
	 */
	@Override
	List<ErrorPage> getErrorPages();


	/**
	 * Returns the value of the '<em><b>Jsp Configs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.jsp.JspConfig}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Jsp Configs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Jsp Configs</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_JspConfigs()
	 * @generated
	 */
	@Override
	List<JspConfig> getJspConfigs();


	/**
	 * Returns the value of the '<em><b>Security Constraints</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.web.SecurityConstraint}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Security Constraints</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Security Constraints</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_SecurityConstraints()
	 * @generated
	 */
	@Override
	List<SecurityConstraint> getSecurityConstraints();


	/**
	 * Returns the value of the '<em><b>Login Configs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.web.LoginConfig}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Login Configs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Login Configs</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_LoginConfigs()
	 * @generated
	 */
	@Override
	List<LoginConfig> getLoginConfigs();


	/**
	 * Returns the value of the '<em><b>Security Roles</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.SecurityRole}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Security Roles</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Security Roles</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_SecurityRoles()
	 * @generated
	 */
	@Override
	List<SecurityRole> getSecurityRoles();


	/**
	 * Returns the value of the '<em><b>Env Entries</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.EnvEntry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Env Entries</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Env Entries</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_EnvEntries()
	 * @generated
	 */
	@Override
	List<EnvEntry> getEnvEntries();


	/**
	 * Returns the value of the '<em><b>Ejb Refs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.EjbRef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ejb Refs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ejb Refs</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_EjbRefs()
	 * @generated
	 */
	@Override
	List<EjbRef> getEjbRefs();


	/**
	 * Returns the value of the '<em><b>Ejb Local Refs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.EjbLocalRef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ejb Local Refs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ejb Local Refs</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_EjbLocalRefs()
	 * @generated
	 */
	@Override
	List<EjbLocalRef> getEjbLocalRefs();


	/**
	 * Returns the value of the '<em><b>Service Refs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.ServiceRef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Service Refs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Service Refs</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_ServiceRefs()
	 * @generated
	 */
	@Override
	List<ServiceRef> getServiceRefs();


	/**
	 * Returns the value of the '<em><b>Resource Refs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.ResourceRef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Refs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Refs</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_ResourceRefs()
	 * @generated
	 */
	@Override
	List<ResourceRef> getResourceRefs();


	/**
	 * Returns the value of the '<em><b>Resource Env Refs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.ResourceEnvRef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Env Refs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Env Refs</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_ResourceEnvRefs()
	 * @generated
	 */
	@Override
	List<ResourceEnvRef> getResourceEnvRefs();


	/**
	 * Returns the value of the '<em><b>Message Destination Refs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.MessageDestinationRef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Destination Refs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message Destination Refs</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_MessageDestinationRefs()
	 * @generated
	 */
	@Override
	List<MessageDestinationRef> getMessageDestinationRefs();


	/**
	 * Returns the value of the '<em><b>Persistence Context Refs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.PersistenceContextRef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Persistence Context Refs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Persistence Context Refs</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_PersistenceContextRefs()
	 * @generated
	 */
	@Override
	List<PersistenceContextRef> getPersistenceContextRefs();


	/**
	 * Returns the value of the '<em><b>Persistence Unit Refs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.PersistenceUnitRef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Persistence Unit Refs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Persistence Unit Refs</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_PersistenceUnitRefs()
	 * @generated
	 */
	@Override
	List<PersistenceUnitRef> getPersistenceUnitRefs();


	/**
	 * Returns the value of the '<em><b>Post Constructs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.LifecycleCallback}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Post Constructs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Post Constructs</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_PostConstructs()
	 * @generated
	 */
	@Override
	List<LifecycleCallback> getPostConstructs();


	/**
	 * Returns the value of the '<em><b>Pre Destroys</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.LifecycleCallback}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pre Destroys</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pre Destroys</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_PreDestroys()
	 * @generated
	 */
	@Override
	List<LifecycleCallback> getPreDestroys();


	/**
	 * Returns the value of the '<em><b>Data Source</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.DataSourceType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Source</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Source</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_DataSource()
	 * @generated
	 */
	@Override
	List<DataSourceType> getDataSource();


	/**
	 * Returns the value of the '<em><b>Jms Connection Factory</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.JmsConnectionFactoryType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 7
	 *          
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Jms Connection Factory</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_JmsConnectionFactory()
	 * @generated
	 */
	List<JmsConnectionFactoryType> getJmsConnectionFactory();


	/**
	 * Returns the value of the '<em><b>Jms Destination</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.JmsDestinationType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 7
	 *          
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Jms Destination</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_JmsDestination()
	 * @generated
	 */
	List<JmsDestinationType> getJmsDestination();


	/**
	 * Returns the value of the '<em><b>Mail Session</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.MailSessionType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 7
	 *          
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Mail Session</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_MailSession()
	 * @generated
	 */
	List<MailSessionType> getMailSession();


	/**
	 * Returns the value of the '<em><b>Connection Factory</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.ConnectionFactoryResourceType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 7
	 *          
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Connection Factory</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_ConnectionFactory()
	 * @generated
	 */
	List<ConnectionFactoryResourceType> getConnectionFactory();


	/**
	 * Returns the value of the '<em><b>Administered Object</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.AdministeredObjectType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 7
	 *          
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Administered Object</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_AdministeredObject()
	 * @generated
	 */
	List<AdministeredObjectType> getAdministeredObject();


	/**
	 * Returns the value of the '<em><b>Message Destinations</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.MessageDestination}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Destinations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message Destinations</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_MessageDestinations()
	 * @generated
	 */
	@Override
	List<MessageDestination> getMessageDestinations();


	/**
	 * Returns the value of the '<em><b>Local Encoding Mappings Lists</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.web.LocaleEncodingMappingList}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 6, Web 3.0
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Local Encoding Mappings Lists</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_LocalEncodingMappingsLists()
	 * @generated
	 */
	@Override
	List<LocaleEncodingMappingList> getLocalEncodingMappingsLists();


	/**
	 * Returns the value of the '<em><b>Ordering</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.web.OrderingType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ordering</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ordering</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_Ordering()
	 * @generated
	 */
	List<OrderingType> getOrdering();


	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_Id()
	 * @generated
	 */
	@Override
	String getId();


	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.WebFragment#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	@Override
	void setId(String value);


	/**
	 * Returns the value of the '<em><b>Metadata Complete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *           The metadata-complete attribute defines whether this
	 *           deployment descriptor and other related deployment
	 *           descriptors for this module (e.g., web service
	 *           descriptors) are complete, or whether the class
	 *           files available to this module and packaged with
	 *           this application should be examined for annotations
	 *           that specify deployment information.
	 *           
	 *           If metadata-complete is set to "true", the deployment
	 *           tool must ignore any annotations that specify deployment
	 *           information, which might be present in the class files
	 *           of the application.
	 *           
	 *           If metadata-complete is not specified or is set to
	 *           "false", the deployment tool must examine the class
	 *           files of the application for annotations, as
	 *           specified by the specifications.
	 *           
	 *           @since Java EE 5, Web 2.5
	 *         
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Metadata Complete</em>' attribute.
	 * @see #isSetMetadataComplete()
	 * @see #unsetMetadataComplete()
	 * @see #setMetadataComplete(boolean)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_MetadataComplete()
	 * @generated
	 */
	@Override
	boolean isMetadataComplete();


	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.WebFragment#isMetadataComplete <em>Metadata Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Metadata Complete</em>' attribute.
	 * @see #isSetMetadataComplete()
	 * @see #unsetMetadataComplete()
	 * @see #isMetadataComplete()
	 * @generated
	 */
	@Override
	void setMetadataComplete(boolean value);


	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.web.WebFragment#isMetadataComplete <em>Metadata Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMetadataComplete()
	 * @see #isMetadataComplete()
	 * @see #setMetadataComplete(boolean)
	 * @generated
	 */
	@Override
	void unsetMetadataComplete();


	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.web.WebFragment#isMetadataComplete <em>Metadata Complete</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Metadata Complete</em>' attribute is set.
	 * @see #unsetMetadataComplete()
	 * @see #isMetadataComplete()
	 * @see #setMetadataComplete(boolean)
	 * @generated
	 */
	@Override
	boolean isSetMetadataComplete();


	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.jst.javaee.web.WebAppVersionType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see org.eclipse.jst.javaee.web.WebAppVersionType
	 * @see #isSetVersion()
	 * @see #unsetVersion()
	 * @see #setVersion(WebAppVersionType)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebFragment_Version()
	 * @generated
	 */
	@Override
	WebAppVersionType getVersion();


	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.WebFragment#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see org.eclipse.jst.javaee.web.WebAppVersionType
	 * @see #isSetVersion()
	 * @see #unsetVersion()
	 * @see #getVersion()
	 * @generated
	 */
	@Override
	void setVersion(WebAppVersionType value);


	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.web.WebFragment#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetVersion()
	 * @see #getVersion()
	 * @see #setVersion(WebAppVersionType)
	 * @generated
	 */
	@Override
	void unsetVersion();


	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.web.WebFragment#getVersion <em>Version</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Version</em>' attribute is set.
	 * @see #unsetVersion()
	 * @see #getVersion()
	 * @see #setVersion(WebAppVersionType)
	 * @generated
	 */
	@Override
	boolean isSetVersion();

} // WebFragment
