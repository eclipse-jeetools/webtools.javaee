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
package org.eclipse.jst.javaee.web;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>App</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getGroup <em>Group</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getModuleName <em>Module Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getDisplayNames <em>Display Names</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getIcons <em>Icons</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getDistributables <em>Distributables</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getContextParams <em>Context Params</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getFilters <em>Filters</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getFilterMappings <em>Filter Mappings</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getListeners <em>Listeners</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getServlets <em>Servlets</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getServletMappings <em>Servlet Mappings</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getSessionConfigs <em>Session Configs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getMimeMappings <em>Mime Mappings</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getWelcomeFileLists <em>Welcome File Lists</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getErrorPages <em>Error Pages</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getJspConfigs <em>Jsp Configs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getSecurityConstraints <em>Security Constraints</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getLoginConfigs <em>Login Configs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getSecurityRoles <em>Security Roles</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getEnvEntries <em>Env Entries</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getEjbRefs <em>Ejb Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getEjbLocalRefs <em>Ejb Local Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getServiceRefs <em>Service Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getResourceRefs <em>Resource Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getResourceEnvRefs <em>Resource Env Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getMessageDestinationRefs <em>Message Destination Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getPersistenceContextRefs <em>Persistence Context Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getPersistenceUnitRefs <em>Persistence Unit Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getPostConstructs <em>Post Constructs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getPreDestroys <em>Pre Destroys</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getDataSource <em>Data Source</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getMessageDestinations <em>Message Destinations</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getLocalEncodingMappingsLists <em>Local Encoding Mappings Lists</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getAbsoluteOrdering <em>Absolute Ordering</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#isMetadataComplete <em>Metadata Complete</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebApp#getVersion <em>Version</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebApp()
 * @extends JavaEEObject
 * @generated
 */
public interface WebApp extends IWebCommon {

	/**
	 * Returns the value of the '<em><b>Module Name</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 6, Web 3.0
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Module Name</em>' attribute list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebApp_ModuleName()
	 * @generated
	 */
	List<String> getModuleName();

	/**
	 * Returns the value of the '<em><b>Absolute Ordering</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.web.AbsoluteOrderingType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 6, Web 3.0
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Absolute Ordering</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebApp_AbsoluteOrdering()
	 * @generated
	 */
	List<AbsoluteOrderingType> getAbsoluteOrdering();


} // WebApp