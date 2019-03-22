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
package org.eclipse.jst.javaee.web;

import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Form Login Config</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The form-login-configType specifies the login and error
 *         pages that should be used in form based login. If form based
 *         authentication is not used, these elements are ignored.
 *         
 *         Used in: login-config
 * 
 *         @since Java EE 5, Web 2.5        
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.web.FormLoginConfig#getFormLoginPage <em>Form Login Page</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.FormLoginConfig#getFormErrorPage <em>Form Error Page</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.FormLoginConfig#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getFormLoginConfig()
 * @extends JavaEEObject
 * @generated
 */
public interface FormLoginConfig extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Form Login Page</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The form-login-page element defines the location in the web
	 *             app where the page that can be used for login can be
	 *             found.  The path begins with a leading / and is interpreted
	 *             relative to the root of the WAR.
	 *             
	 *             @since Java EE 5, Web 2.5
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Form Login Page</em>' attribute.
	 * @see #setFormLoginPage(String)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getFormLoginConfig_FormLoginPage()
	 * @generated
	 */
	String getFormLoginPage();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.FormLoginConfig#getFormLoginPage <em>Form Login Page</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Form Login Page</em>' attribute.
	 * @see #getFormLoginPage()
	 * @generated
	 */
	void setFormLoginPage(String value);

	/**
	 * Returns the value of the '<em><b>Form Error Page</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The form-error-page element defines the location in
	 *             the web app where the error page that is displayed
	 *             when login is not successful can be found. 
	 *             The path begins with a leading / and is interpreted
	 *             relative to the root of the WAR.
	 *             
	 *             @since Java EE 5, Web 2.5
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Form Error Page</em>' attribute.
	 * @see #setFormErrorPage(String)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getFormLoginConfig_FormErrorPage()
	 * @generated
	 */
	String getFormErrorPage();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.FormLoginConfig#getFormErrorPage <em>Form Error Page</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Form Error Page</em>' attribute.
	 * @see #getFormErrorPage()
	 * @generated
	 */
	void setFormErrorPage(String value);

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
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getFormLoginConfig_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.FormLoginConfig#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // FormLoginConfig