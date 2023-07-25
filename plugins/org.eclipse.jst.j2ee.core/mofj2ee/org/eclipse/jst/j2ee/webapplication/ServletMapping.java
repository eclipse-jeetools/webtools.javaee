/*******************************************************************************
 * Copyright (c) 2001, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.webapplication;

import org.eclipse.jst.j2ee.common.J2EEEObject;
/**
 * The servlet-mapping element defines a mapping between a servlet and a url pattern
 * @since 1.0
 * @generated
 */
public interface ServletMapping extends J2EEEObject {
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the UrlPattern attribute
	 * The url-pattern element contains the url pattern of the mapping. Must follow
	 * the rules specified in Section 10 of the Servlet API Specification.
	 */
	String getUrlPattern();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the UrlPattern attribute
	 */
	void setUrlPattern(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @version J2EE1.4
	 * The servlet-name element contains the canonical name of the
	 * servlet. Each servlet name is unique within the web
	 * application.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.jst.j2ee.webapplication.WebapplicationPackage#getServletMapping_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webapplication.ServletMapping#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The WebApp reference
	 */
	WebApp getWebApp();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the WebApp reference
	 */
	void setWebApp(WebApp value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The Servlet reference
	 */
	Servlet getServlet();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Servlet reference
	 */
	void setServlet(Servlet value);

}














