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

import org.eclipse.emf.ecore.EFactory;
/**
 * @generated
 */
public interface WebapplicationFactory extends EFactory{
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	WebapplicationFactory eINSTANCE = new org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationFactoryImpl();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return WebApp value
	 */
	WebApp createWebApp();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return ContextParam value
	 */
	ContextParam createContextParam();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return ErrorPage value
	 */
	ErrorPage createErrorPage();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return ExceptionTypeErrorPage value
	 */
	ExceptionTypeErrorPage createExceptionTypeErrorPage();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return ErrorCodeErrorPage value
	 */
	ErrorCodeErrorPage createErrorCodeErrorPage();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return WelcomeFileList value
	 */
	WelcomeFileList createWelcomeFileList();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return WelcomeFile value
	 */
	WelcomeFile createWelcomeFile();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return TagLibRef value
	 */
	TagLibRef createTagLibRef();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return SecurityConstraint value
	 */
	SecurityConstraint createSecurityConstraint();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return WebResourceCollection value
	 */
	WebResourceCollection createWebResourceCollection();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return URLPatternType value
	 */
	URLPatternType createURLPatternType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return AuthConstraint value
	 */
	AuthConstraint createAuthConstraint();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return UserDataConstraint value
	 */
	UserDataConstraint createUserDataConstraint();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return LoginConfig value
	 */
	LoginConfig createLoginConfig();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return FormLoginConfig value
	 */
	FormLoginConfig createFormLoginConfig();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return MimeMapping value
	 */
	MimeMapping createMimeMapping();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return SessionConfig value
	 */
	SessionConfig createSessionConfig();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return ServletMapping value
	 */
	ServletMapping createServletMapping();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return Servlet value
	 */
	Servlet createServlet();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return ServletType value
	 */
	ServletType createServletType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return JSPType value
	 */
	JSPType createJSPType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return InitParam value
	 */
	InitParam createInitParam();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return Filter value
	 */
	Filter createFilter();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return FilterMapping value
	 */
	FilterMapping createFilterMapping();

	/**
	 * Returns a new object of class '<em>Local Encoding Mapping List</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Local Encoding Mapping List</em>'.
	 * @generated
	 */
	LocalEncodingMappingList createLocalEncodingMappingList();

	/**
	 * Returns a new object of class '<em>Local Encoding Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Local Encoding Mapping</em>'.
	 * @generated
	 */
	LocalEncodingMapping createLocalEncodingMapping();

	/**
	 * Returns a new object of class '<em>HTTP Method Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>HTTP Method Type</em>'.
	 * @generated
	 */
	HTTPMethodType createHTTPMethodType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return RoleNameType value
	 */
	RoleNameType createRoleNameType();

	/**
	 * <!-- begin-user-doc -->
	 * This method is NOT api, and may change in future releases.  
	 * EMF package classes should be access via their "eINSTANCE" static fields
	 * <!-- end-user-doc -->
	 * @generated This field/method will be replaced during code generation.
	 */
	WebapplicationPackage getWebapplicationPackage();

}







