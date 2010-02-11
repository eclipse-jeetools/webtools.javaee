/**
 * <copyright>
 * </copyright>
 *
 * $Id: WebFragment.java,v 1.3 2010/02/11 06:22:51 canderson Exp $
 */
package org.eclipse.jst.javaee.web;

import java.util.List;

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

} // WebFragment
