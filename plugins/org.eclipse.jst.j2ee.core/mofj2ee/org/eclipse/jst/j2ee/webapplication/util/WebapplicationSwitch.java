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
package org.eclipse.jst.j2ee.webapplication.util;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup;
import org.eclipse.jst.j2ee.common.DescriptionGroup;
import org.eclipse.jst.j2ee.common.JNDIEnvRefsGroup;
import org.eclipse.jst.j2ee.webapplication.AuthConstraint;
import org.eclipse.jst.j2ee.webapplication.ContextParam;
import org.eclipse.jst.j2ee.webapplication.ErrorCodeErrorPage;
import org.eclipse.jst.j2ee.webapplication.ErrorPage;
import org.eclipse.jst.j2ee.webapplication.ExceptionTypeErrorPage;
import org.eclipse.jst.j2ee.webapplication.Filter;
import org.eclipse.jst.j2ee.webapplication.FilterMapping;
import org.eclipse.jst.j2ee.webapplication.FormLoginConfig;
import org.eclipse.jst.j2ee.webapplication.HTTPMethodType;
import org.eclipse.jst.j2ee.webapplication.InitParam;
import org.eclipse.jst.j2ee.webapplication.JSPType;
import org.eclipse.jst.j2ee.webapplication.LocalEncodingMapping;
import org.eclipse.jst.j2ee.webapplication.LocalEncodingMappingList;
import org.eclipse.jst.j2ee.webapplication.LoginConfig;
import org.eclipse.jst.j2ee.webapplication.MimeMapping;
import org.eclipse.jst.j2ee.webapplication.RoleNameType;
import org.eclipse.jst.j2ee.webapplication.SecurityConstraint;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.ServletMapping;
import org.eclipse.jst.j2ee.webapplication.ServletType;
import org.eclipse.jst.j2ee.webapplication.SessionConfig;
import org.eclipse.jst.j2ee.webapplication.TagLibRef;
import org.eclipse.jst.j2ee.webapplication.URLPatternType;
import org.eclipse.jst.j2ee.webapplication.UserDataConstraint;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebResourceCollection;
import org.eclipse.jst.j2ee.webapplication.WebType;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.jst.j2ee.webapplication.WelcomeFile;
import org.eclipse.jst.j2ee.webapplication.WelcomeFileList;



/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.j2ee.webapplication.WebapplicationPackage
 * @generated
 */
public class WebapplicationSwitch {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static WebapplicationPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WebapplicationSwitch() {
		if (modelPackage == null) {
			modelPackage = WebapplicationPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public Object doSwitch(EObject theEObject) {
		EClass theEClass = theEObject.eClass();
		if (theEClass.eContainer() == modelPackage) {
			switch (theEClass.getClassifierID()) {
				case WebapplicationPackage.WEB_APP: {
					WebApp webApp = (WebApp)theEObject;
					Object result = caseWebApp(webApp);
					if (result == null) result = caseJNDIEnvRefsGroup(webApp);
					if (result == null) result = caseCompatibilityDescriptionGroup(webApp);
					if (result == null) result = caseDescriptionGroup(webApp);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case WebapplicationPackage.CONTEXT_PARAM: {
					ContextParam contextParam = (ContextParam)theEObject;
					Object result = caseContextParam(contextParam);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case WebapplicationPackage.SERVLET: {
					Servlet servlet = (Servlet)theEObject;
					Object result = caseServlet(servlet);
					if (result == null) result = caseCompatibilityDescriptionGroup(servlet);
					if (result == null) result = caseDescriptionGroup(servlet);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case WebapplicationPackage.SERVLET_MAPPING: {
					ServletMapping servletMapping = (ServletMapping)theEObject;
					Object result = caseServletMapping(servletMapping);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case WebapplicationPackage.SESSION_CONFIG: {
					SessionConfig sessionConfig = (SessionConfig)theEObject;
					Object result = caseSessionConfig(sessionConfig);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case WebapplicationPackage.MIME_MAPPING: {
					MimeMapping mimeMapping = (MimeMapping)theEObject;
					Object result = caseMimeMapping(mimeMapping);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case WebapplicationPackage.WELCOME_FILE_LIST: {
					WelcomeFileList welcomeFileList = (WelcomeFileList)theEObject;
					Object result = caseWelcomeFileList(welcomeFileList);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case WebapplicationPackage.ERROR_PAGE: {
					ErrorPage errorPage = (ErrorPage)theEObject;
					Object result = caseErrorPage(errorPage);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case WebapplicationPackage.TAG_LIB_REF: {
					TagLibRef tagLibRef = (TagLibRef)theEObject;
					Object result = caseTagLibRef(tagLibRef);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case WebapplicationPackage.SECURITY_CONSTRAINT: {
					SecurityConstraint securityConstraint = (SecurityConstraint)theEObject;
					Object result = caseSecurityConstraint(securityConstraint);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case WebapplicationPackage.WEB_RESOURCE_COLLECTION: {
					WebResourceCollection webResourceCollection = (WebResourceCollection)theEObject;
					Object result = caseWebResourceCollection(webResourceCollection);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case WebapplicationPackage.AUTH_CONSTRAINT: {
					AuthConstraint authConstraint = (AuthConstraint)theEObject;
					Object result = caseAuthConstraint(authConstraint);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case WebapplicationPackage.USER_DATA_CONSTRAINT: {
					UserDataConstraint userDataConstraint = (UserDataConstraint)theEObject;
					Object result = caseUserDataConstraint(userDataConstraint);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case WebapplicationPackage.LOGIN_CONFIG: {
					LoginConfig loginConfig = (LoginConfig)theEObject;
					Object result = caseLoginConfig(loginConfig);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case WebapplicationPackage.FORM_LOGIN_CONFIG: {
					FormLoginConfig formLoginConfig = (FormLoginConfig)theEObject;
					Object result = caseFormLoginConfig(formLoginConfig);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case WebapplicationPackage.INIT_PARAM: {
					InitParam initParam = (InitParam)theEObject;
					Object result = caseInitParam(initParam);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case WebapplicationPackage.SERVLET_TYPE: {
					ServletType servletType = (ServletType)theEObject;
					Object result = caseServletType(servletType);
					if (result == null) result = caseWebType(servletType);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case WebapplicationPackage.JSP_TYPE: {
					JSPType jspType = (JSPType)theEObject;
					Object result = caseJSPType(jspType);
					if (result == null) result = caseWebType(jspType);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case WebapplicationPackage.URL_PATTERN_TYPE: {
					URLPatternType urlPatternType = (URLPatternType)theEObject;
					Object result = caseURLPatternType(urlPatternType);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case WebapplicationPackage.ROLE_NAME_TYPE: {
					RoleNameType roleNameType = (RoleNameType)theEObject;
					Object result = caseRoleNameType(roleNameType);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case WebapplicationPackage.WELCOME_FILE: {
					WelcomeFile welcomeFile = (WelcomeFile)theEObject;
					Object result = caseWelcomeFile(welcomeFile);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case WebapplicationPackage.EXCEPTION_TYPE_ERROR_PAGE: {
					ExceptionTypeErrorPage exceptionTypeErrorPage = (ExceptionTypeErrorPage)theEObject;
					Object result = caseExceptionTypeErrorPage(exceptionTypeErrorPage);
					if (result == null) result = caseErrorPage(exceptionTypeErrorPage);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case WebapplicationPackage.ERROR_CODE_ERROR_PAGE: {
					ErrorCodeErrorPage errorCodeErrorPage = (ErrorCodeErrorPage)theEObject;
					Object result = caseErrorCodeErrorPage(errorCodeErrorPage);
					if (result == null) result = caseErrorPage(errorCodeErrorPage);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case WebapplicationPackage.FILTER_MAPPING: {
					FilterMapping filterMapping = (FilterMapping)theEObject;
					Object result = caseFilterMapping(filterMapping);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case WebapplicationPackage.FILTER: {
					Filter filter = (Filter)theEObject;
					Object result = caseFilter(filter);
					if (result == null) result = caseCompatibilityDescriptionGroup(filter);
					if (result == null) result = caseDescriptionGroup(filter);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case WebapplicationPackage.LOCAL_ENCODING_MAPPING_LIST: {
					LocalEncodingMappingList localEncodingMappingList = (LocalEncodingMappingList)theEObject;
					Object result = caseLocalEncodingMappingList(localEncodingMappingList);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case WebapplicationPackage.LOCAL_ENCODING_MAPPING: {
					LocalEncodingMapping localEncodingMapping = (LocalEncodingMapping)theEObject;
					Object result = caseLocalEncodingMapping(localEncodingMapping);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case WebapplicationPackage.HTTP_METHOD_TYPE: {
					HTTPMethodType httpMethodType = (HTTPMethodType)theEObject;
					Object result = caseHTTPMethodType(httpMethodType);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				default: return defaultCase(theEObject);
			}
		}
		return defaultCase(theEObject);
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Web App</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Web App</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseWebApp(WebApp object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Context Param</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Context Param</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseContextParam(ContextParam object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Servlet</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Servlet</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseServlet(Servlet object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Servlet Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Servlet Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseServletMapping(ServletMapping object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Session Config</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Session Config</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSessionConfig(SessionConfig object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Mime Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Mime Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseMimeMapping(MimeMapping object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Welcome File List</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Welcome File List</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseWelcomeFileList(WelcomeFileList object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Error Page</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Error Page</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseErrorPage(ErrorPage object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Tag Lib Ref</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Tag Lib Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseTagLibRef(TagLibRef object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Security Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Security Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSecurityConstraint(SecurityConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Web Resource Collection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Web Resource Collection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseWebResourceCollection(WebResourceCollection object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Auth Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Auth Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseAuthConstraint(AuthConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>User Data Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>User Data Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseUserDataConstraint(UserDataConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Login Config</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Login Config</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLoginConfig(LoginConfig object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Form Login Config</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Form Login Config</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseFormLoginConfig(FormLoginConfig object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Init Param</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Init Param</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseInitParam(InitParam object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Web Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Web Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseWebType(WebType object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Servlet Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Servlet Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseServletType(ServletType object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>JSP Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>JSP Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseJSPType(JSPType object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>URL Pattern Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>URL Pattern Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseURLPatternType(URLPatternType object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Role Name Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Role Name Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseRoleNameType(RoleNameType object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Welcome File</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Welcome File</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseWelcomeFile(WelcomeFile object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Exception Type Error Page</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Exception Type Error Page</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseExceptionTypeErrorPage(ExceptionTypeErrorPage object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Error Code Error Page</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Error Code Error Page</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseErrorCodeErrorPage(ErrorCodeErrorPage object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Filter Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Filter Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseFilterMapping(FilterMapping object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Filter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Filter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseFilter(Filter object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Local Encoding Mapping List</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Local Encoding Mapping List</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLocalEncodingMappingList(LocalEncodingMappingList object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Local Encoding Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Local Encoding Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLocalEncodingMapping(LocalEncodingMapping object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>HTTP Method Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>HTTP Method Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseHTTPMethodType(HTTPMethodType object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Description Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Description Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDescriptionGroup(DescriptionGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Compatibility Description Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Compatibility Description Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseCompatibilityDescriptionGroup(CompatibilityDescriptionGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>JNDI Env Refs Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>JNDI Env Refs Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseJNDIEnvRefsGroup(JNDIEnvRefsGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public Object defaultCase(EObject object) {
		return null;
	}

} //WebapplicationSwitch
