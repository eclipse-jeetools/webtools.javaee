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
package org.eclipse.jst.javaee.web.internal.util;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.eclipse.jst.javaee.web.*;

import org.eclipse.jst.javaee.web.internal.metadata.WebPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage
 * @generated
 */
public class WebSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static WebPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WebSwitch() {
		if (modelPackage == null) {
			modelPackage = WebPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case WebPackage.ABSOLUTE_ORDERING_TYPE: {
				AbsoluteOrderingType absoluteOrderingType = (AbsoluteOrderingType)theEObject;
				T result = caseAbsoluteOrderingType(absoluteOrderingType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.AUTH_CONSTRAINT: {
				AuthConstraint authConstraint = (AuthConstraint)theEObject;
				T result = caseAuthConstraint(authConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.COOKIE_COMMENT_TYPE: {
				CookieCommentType cookieCommentType = (CookieCommentType)theEObject;
				T result = caseCookieCommentType(cookieCommentType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.COOKIE_CONFIG_TYPE: {
				CookieConfigType cookieConfigType = (CookieConfigType)theEObject;
				T result = caseCookieConfigType(cookieConfigType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.COOKIE_DOMAIN_TYPE: {
				CookieDomainType cookieDomainType = (CookieDomainType)theEObject;
				T result = caseCookieDomainType(cookieDomainType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.COOKIE_NAME_TYPE: {
				CookieNameType cookieNameType = (CookieNameType)theEObject;
				T result = caseCookieNameType(cookieNameType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.COOKIE_PATH_TYPE: {
				CookiePathType cookiePathType = (CookiePathType)theEObject;
				T result = caseCookiePathType(cookiePathType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.ERROR_PAGE: {
				ErrorPage errorPage = (ErrorPage)theEObject;
				T result = caseErrorPage(errorPage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.FILTER: {
				Filter filter = (Filter)theEObject;
				T result = caseFilter(filter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.FILTER_MAPPING: {
				FilterMapping filterMapping = (FilterMapping)theEObject;
				T result = caseFilterMapping(filterMapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.FORM_LOGIN_CONFIG: {
				FormLoginConfig formLoginConfig = (FormLoginConfig)theEObject;
				T result = caseFormLoginConfig(formLoginConfig);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.LOCALE_ENCODING_MAPPING: {
				LocaleEncodingMapping localeEncodingMapping = (LocaleEncodingMapping)theEObject;
				T result = caseLocaleEncodingMapping(localeEncodingMapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.LOCALE_ENCODING_MAPPING_LIST: {
				LocaleEncodingMappingList localeEncodingMappingList = (LocaleEncodingMappingList)theEObject;
				T result = caseLocaleEncodingMappingList(localeEncodingMappingList);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.LOGIN_CONFIG: {
				LoginConfig loginConfig = (LoginConfig)theEObject;
				T result = caseLoginConfig(loginConfig);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.MIME_MAPPING: {
				MimeMapping mimeMapping = (MimeMapping)theEObject;
				T result = caseMimeMapping(mimeMapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.MULTIPART_CONFIG_TYPE: {
				MultipartConfigType multipartConfigType = (MultipartConfigType)theEObject;
				T result = caseMultipartConfigType(multipartConfigType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.ORDERING_ORDERING_TYPE: {
				OrderingOrderingType orderingOrderingType = (OrderingOrderingType)theEObject;
				T result = caseOrderingOrderingType(orderingOrderingType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.ORDERING_OTHERS_TYPE: {
				OrderingOthersType orderingOthersType = (OrderingOthersType)theEObject;
				T result = caseOrderingOthersType(orderingOthersType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.ORDERING_TYPE: {
				OrderingType orderingType = (OrderingType)theEObject;
				T result = caseOrderingType(orderingType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.SECURITY_CONSTRAINT: {
				SecurityConstraint securityConstraint = (SecurityConstraint)theEObject;
				T result = caseSecurityConstraint(securityConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.SERVLET: {
				Servlet servlet = (Servlet)theEObject;
				T result = caseServlet(servlet);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.SERVLET_MAPPING: {
				ServletMapping servletMapping = (ServletMapping)theEObject;
				T result = caseServletMapping(servletMapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.SESSION_CONFIG: {
				SessionConfig sessionConfig = (SessionConfig)theEObject;
				T result = caseSessionConfig(sessionConfig);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.USER_DATA_CONSTRAINT: {
				UserDataConstraint userDataConstraint = (UserDataConstraint)theEObject;
				T result = caseUserDataConstraint(userDataConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.WEB_APP: {
				WebApp webApp = (WebApp)theEObject;
				T result = caseWebApp(webApp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.WEB_APP_DEPLOYMENT_DESCRIPTOR: {
				WebAppDeploymentDescriptor webAppDeploymentDescriptor = (WebAppDeploymentDescriptor)theEObject;
				T result = caseWebAppDeploymentDescriptor(webAppDeploymentDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.WEB_FRAGMENT: {
				WebFragment webFragment = (WebFragment)theEObject;
				T result = caseWebFragment(webFragment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.WEB_RESOURCE_COLLECTION: {
				WebResourceCollection webResourceCollection = (WebResourceCollection)theEObject;
				T result = caseWebResourceCollection(webResourceCollection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.WELCOME_FILE_LIST: {
				WelcomeFileList welcomeFileList = (WelcomeFileList)theEObject;
				T result = caseWelcomeFileList(welcomeFileList);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Absolute Ordering Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Absolute Ordering Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbsoluteOrderingType(AbsoluteOrderingType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Auth Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Auth Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAuthConstraint(AuthConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Cookie Comment Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Cookie Comment Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCookieCommentType(CookieCommentType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Cookie Config Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Cookie Config Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCookieConfigType(CookieConfigType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Cookie Domain Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Cookie Domain Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCookieDomainType(CookieDomainType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Cookie Name Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Cookie Name Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCookieNameType(CookieNameType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Cookie Path Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Cookie Path Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCookiePathType(CookiePathType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Error Page</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Error Page</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseErrorPage(ErrorPage object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Filter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Filter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFilter(Filter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Filter Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Filter Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFilterMapping(FilterMapping object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Form Login Config</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Form Login Config</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFormLoginConfig(FormLoginConfig object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Locale Encoding Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Locale Encoding Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLocaleEncodingMapping(LocaleEncodingMapping object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Locale Encoding Mapping List</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Locale Encoding Mapping List</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLocaleEncodingMappingList(LocaleEncodingMappingList object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Login Config</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Login Config</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLoginConfig(LoginConfig object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Mime Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Mime Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMimeMapping(MimeMapping object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Multipart Config Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Multipart Config Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMultipartConfigType(MultipartConfigType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ordering Ordering Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ordering Ordering Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOrderingOrderingType(OrderingOrderingType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ordering Others Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ordering Others Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOrderingOthersType(OrderingOthersType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ordering Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ordering Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOrderingType(OrderingType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Security Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Security Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSecurityConstraint(SecurityConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Servlet</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Servlet</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseServlet(Servlet object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Servlet Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Servlet Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseServletMapping(ServletMapping object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Session Config</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Session Config</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSessionConfig(SessionConfig object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>User Data Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User Data Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUserDataConstraint(UserDataConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>App</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>App</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWebApp(WebApp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>App Deployment Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>App Deployment Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWebAppDeploymentDescriptor(WebAppDeploymentDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Fragment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Fragment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWebFragment(WebFragment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource Collection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource Collection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWebResourceCollection(WebResourceCollection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Welcome File List</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Welcome File List</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWelcomeFileList(WelcomeFileList object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //WebSwitch
