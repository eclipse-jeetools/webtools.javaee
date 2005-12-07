/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.webapplication;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.j2ee.common.JNDIEnvRefsGroup;
import org.eclipse.jst.j2ee.common.SecurityRole;
import org.eclipse.jst.j2ee.jsp.JSPConfig;

/**
 * The web-app element is the root of the deployment descriptor for
 * a web application.
 * @since 1.0
 */
public interface WebApp extends JNDIEnvRefsGroup{

	/**
	 * Returns true if there is a SecurityRole with the name passed
	 * @param name name of the SecurityRole
	 * @return boolean value
	 */
	public boolean containsSecurityRole(String name);
	
	/**
	 * Returns an ErrorPage if it has the specified error code
	 * @param code the error code
	 * @return refernce to ErrorPage
	 */
	ErrorPage getErrorPageByCode(Integer code) ;
	
	/**
	 * Returns an ErrorPage given an exceptionType
	 * @param exceptionType the java Exception Type
	 * @return refernce to ErrorPage
	 */
	ErrorPage getErrorPageByExceptionType(String exceptionType) ;
	/**
	 * Finds a filter mapping for a specified filter.
	 * @param aFilter The filter to find the mappings for.
	 * @return The filter mapping for the specified filter, or null if no
	 * mapping exists for the filter.
	 */

	FilterMapping getFilterMapping(Filter aFilter);
	
	/**
	 * Gets refernce to the instance of Filter which has the specified name
	 * @param name the name of the Filter to look for
	 * @return refernce to teh Filter
	 */
	public Filter getFilterNamed(String name);
	/**
	 * Gets the names of the filters defined for this web application.
	 * @return A list of filter names (Strings).
	 */

	List getFilterNames() ;
	/**
	 * Returns the mime type for the specified extension
	 * @param extension to find the mime type for.
	 * @return String value
	 */
	String getMimeType(String extension);
	
	/**
	 * Returns the SecurityRole with the specified name
	 * @param roleName the name of the SecurityRole to be searched for
	 * @return reference to the model object SecurityRole
	 */
	public SecurityRole getSecurityRoleNamed(String roleName);
	
	/**
	 * Finds a servlet mapping for a specified servlet.
	 * @param aServlet The servlet to find the mappings for.
	 * @return The servlet mapping for the specified servlet, or null if no
	 * mapping exists for the servlet.
	 */
	ServletMapping getServletMapping(Servlet aServlet);
	
	/**
	 * Returns a Servlet with the specified name 
	 * @param name the name of the Servlet to search for
	 * @return reference to the model object Servlet
	 */
	public Servlet getServletNamed(String name);
	
	/**
	 * Gets the names of the servlets defined for this web application.
	 * @return A list of servlet names (Strings).
	 */
	List getServletNames() ;
	/**
	 * Return boolean indicating if this Web App was populated from an Servlet2.2 compliant descriptor
	 * @return boolean
	 * @deprecated Use getVersionID() to determine module level
	 */
	public boolean isVersion2_2Descriptor();
	/**
	 * Return boolean indicating if this Web App was populated from an Servlet2.3 compliant descriptor
	 * @return boolean
	 * @deprecated Use getVersionID() to determine module level
	 */
	public boolean isVersion2_3Descriptor();
	/**
	 * Rename the security role if it exists; for each servlet, fix the role-link on any contained role
	 * refs
	 * @param existingRoleName curent security role name
	 * @param newRoleName new security role name
	 */
	public void renameSecurityRole(String existingRoleName, String newRoleName);
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Distributable attribute
	 * The distributable element, by its presence in a web application deployment
	 * descriptor, indicates that this web application is programmed appropriately to
	 * be deployed into a distributed servlet container
	 */
	boolean isDistributable();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Distributable attribute
	 */
	void setDistributable(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.j2ee.webapplication.WebApp#isDistributable <em>Distributable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetDistributable()
	 * @see #isDistributable()
	 * @see #setDistributable(boolean)
	 * @generated
	 */
	void unsetDistributable();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.j2ee.webapplication.WebApp#isDistributable <em>Distributable</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Distributable</em>' attribute is set.
	 * @see #unsetDistributable()
	 * @see #isDistributable()
	 * @see #setDistributable(boolean)
	 * @generated
	 */
	boolean isSetDistributable();

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see org.eclipse.jst.j2ee.webapplication.WebapplicationPackage#getWebApp_Version()
	 * @model
	 * @generated
	 */
	String getVersion();

	/**
	* This returns the module version id. Compare with J2EEVersionConstants to determine module level
	 *@throws IllegalStateException the exception is thrown if a problem is encountered in finding the 
	 *module version. 	* 
	* @return the module version id represented in the int e.g. 22, 23, 24
	*/
	public int getVersionID() throws IllegalStateException ;
	/**
	 *This returns the j2ee version id. Compare with J2EEVersionConstants to determine j2ee level
	 *@throws IllegalStateException the exception is thrown if a problem is encountered in finding the 
	 *j2ee version. 
	 *@return the j2ee version represented in the int
	 */
	public int getJ2EEVersionID() throws IllegalStateException ;
	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webapplication.WebApp#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of Contexts references
	 */
	EList getContexts();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of ErrorPages references
	 */
	EList getErrorPages();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The FileList reference
	 */
	WelcomeFileList getFileList();

	/**
	 * @deprecated : Use getEnvironmentProperties()
	 * @return reference to EList
	 */
	EList getEnvEntries();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the FileList reference
	 */
	void setFileList(WelcomeFileList value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of TagLibs references
	 */
	EList getTagLibs();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of Constraints references
	 */
	EList getConstraints();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The LoginConfig reference
	 */
	LoginConfig getLoginConfig();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the LoginConfig reference
	 */
	void setLoginConfig(LoginConfig value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of MimeMappings references
	 */
	EList getMimeMappings();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The SessionConfig reference
	 */
	SessionConfig getSessionConfig();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the SessionConfig reference
	 */
	void setSessionConfig(SessionConfig value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of ServletMappings references
	 */
	EList getServletMappings();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of Servlets references
	 */
	EList getServlets();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of SecurityRoles references
	 */
	EList getSecurityRoles();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of Filters references
	 */
	EList getFilters();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of FilterMappings references
	 * Filter mappings defined for the web app
	 */
	EList getFilterMappings();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of Listeners references
	 * The listeners collection contains deployment properties for a web application
	 * listener beans in the web app.
	 */
	EList getListeners();

	/**
	 * Returns the value of the '<em><b>Context Params</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.common.ParamValue}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Context Params</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Context Params</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.webapplication.WebapplicationPackage#getWebApp_ContextParams()
	 * @model type="org.eclipse.jst.j2ee.common.ParamValue" containment="true"
	 * @generated
	 */
	EList getContextParams();

	/**
	 * Returns the value of the '<em><b>Jsp Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Jsp Config</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Jsp Config</em>' containment reference.
	 * @see #setJspConfig(JSPConfig)
	 * @see org.eclipse.jst.j2ee.webapplication.WebapplicationPackage#getWebApp_JspConfig()
	 * @model containment="true"
	 * @generated
	 */
	JSPConfig getJspConfig();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webapplication.WebApp#getJspConfig <em>Jsp Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Jsp Config</em>' containment reference.
	 * @see #getJspConfig()
	 * @generated
	 */
	void setJspConfig(JSPConfig value);

	/**
	 * Returns the value of the '<em><b>Message Destinations</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.common.MessageDestination}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Destinations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message Destinations</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.webapplication.WebapplicationPackage#getWebApp_MessageDestinations()
	 * @model type="org.eclipse.jst.j2ee.common.MessageDestination" containment="true"
	 * @generated
	 */
	EList getMessageDestinations();

	/**
	 * Returns the value of the '<em><b>Local Encoding Mapping List</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Local Encoding Mapping List</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Local Encoding Mapping List</em>' containment reference.
	 * @see #setLocalEncodingMappingList(LocalEncodingMappingList)
	 * @see org.eclipse.jst.j2ee.webapplication.WebapplicationPackage#getWebApp_LocalEncodingMappingList()
	 * @model containment="true"
	 * @generated
	 */
	LocalEncodingMappingList getLocalEncodingMappingList();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webapplication.WebApp#getLocalEncodingMappingList <em>Local Encoding Mapping List</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Local Encoding Mapping List</em>' containment reference.
	 * @see #getLocalEncodingMappingList()
	 * @generated
	 */
	void setLocalEncodingMappingList(LocalEncodingMappingList value);

}














