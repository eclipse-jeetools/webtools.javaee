/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
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
import java.util.Properties;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup;
import org.eclipse.jst.j2ee.common.RunAsSpecifiedIdentity;

/**
 * The servlet element contains the declarative data of a servlet. If a jsp-file is specified and the load-on-startup element is present, then the JSP should be precompiled and loaded.
 */
public interface Servlet extends CompatibilityDescriptionGroup{


/**
 * Returns an unmodifiable list of the mappings that this
 * servlet participates in.  Since servletMappings is not
 * a bi-directional attribute, if you want to add or delete a
 * mapping, this must be done in the web-app.
 * @return java.util.List
 */
public List getMappings();
public Properties getParamsAsProperties() ;
/**
 * Fix the role-link on any contained role refs
 */
public void reSyncSecurityRoleRef(String existingRoleName, String newRoleName);
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the ServletName attribute
	 * The servlet-name element contains the canonical name of the servlet.
	 */
	String getServletName();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the ServletName attribute
	 */
	void setServletName(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the LoadOnStartup attribute
	 * The load-on-startup element indicates that this servlet should be loaded on the 
	 * startup of the web application. The optional contents of these element must be
	 * a positive integer indicating the order in which the servlet should be loaded.
	 * Lower integers are loaded before higher integers. If no value is specified, or
	 * if the value specified is not a positive integer, the container is free to load 
	 * it at any time in the startup sequence.

	 */
	Integer getLoadOnStartup();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.webapplication.Servlet#getLoadOnStartup <em>Load On Startup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Load On Startup</em>' attribute.
	 * @see #isSetLoadOnStartup()
	 * @see #unsetLoadOnStartup()
	 * @see #getLoadOnStartup()
	 * @generated
	 */
	void setLoadOnStartup(Integer value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.j2ee.internal.webapplication.Servlet#getLoadOnStartup <em>Load On Startup</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #isSetLoadOnStartup()
	 * @see #getLoadOnStartup()
	 * @see #setLoadOnStartup(Integer)
	 * @generated
	 */
  void unsetLoadOnStartup();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.j2ee.internal.webapplication.Servlet#getLoadOnStartup <em>Load On Startup</em>}' attribute is set.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Load On Startup</em>' attribute is set.
	 * @see #unsetLoadOnStartup()
	 * @see #getLoadOnStartup()
	 * @see #setLoadOnStartup(Integer)
	 * @generated
	 */
  boolean isSetLoadOnStartup();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The WebApp reference
	 */
	WebApp getWebApp();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the WebApp reference
	 */
	void setWebApp(WebApp value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The WebType reference
	 */
	WebType getWebType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the WebType reference
	 */
	void setWebType(WebType value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of Params references
	 */
	EList getParams();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of SecurityRoleRefs references
	 */
	EList getSecurityRoleRefs();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The RunAs reference
	 * If defined for a servlet, overrides the security identity used to call an EJB
	 * by that servlet in this web application. The role-name is one of the security
	 * roles already defined for this web application.
	 */
	RunAsSpecifiedIdentity getRunAs();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the RunAs reference
	 */
	void setRunAs(RunAsSpecifiedIdentity value);

	/**
	 * Returns the value of the '<em><b>Init Params</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.internal.common.ParamValue}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Init Params</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Init Params</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.internal.webapplication.WebapplicationPackage#getServlet_InitParams()
	 * @model type="org.eclipse.jst.j2ee.internal.common.ParamValue" containment="true"
	 * @generated
	 */
	EList getInitParams();

	/**
	 * return the java servlet class associated with this servlet
	 */
	public JavaClass getServletClass();
}














