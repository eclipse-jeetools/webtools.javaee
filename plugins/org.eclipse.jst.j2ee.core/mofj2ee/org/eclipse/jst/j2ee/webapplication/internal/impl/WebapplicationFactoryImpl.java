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
package org.eclipse.jst.j2ee.webapplication.internal.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.jst.j2ee.webapplication.AuthConstraint;
import org.eclipse.jst.j2ee.webapplication.AuthMethodKind;
import org.eclipse.jst.j2ee.webapplication.ContextParam;
import org.eclipse.jst.j2ee.webapplication.DispatcherType;
import org.eclipse.jst.j2ee.webapplication.ErrorCodeErrorPage;
import org.eclipse.jst.j2ee.webapplication.ErrorPage;
import org.eclipse.jst.j2ee.webapplication.ExceptionTypeErrorPage;
import org.eclipse.jst.j2ee.webapplication.Filter;
import org.eclipse.jst.j2ee.webapplication.FilterMapping;
import org.eclipse.jst.j2ee.webapplication.FormLoginConfig;
import org.eclipse.jst.j2ee.webapplication.HTTPMethodType;
import org.eclipse.jst.j2ee.webapplication.HTTPMethodTypeEnum;
import org.eclipse.jst.j2ee.webapplication.InitParam;
import org.eclipse.jst.j2ee.webapplication.JSPType;
import org.eclipse.jst.j2ee.webapplication.LocalEncodingMapping;
import org.eclipse.jst.j2ee.webapplication.LocalEncodingMappingList;
import org.eclipse.jst.j2ee.webapplication.LoginConfig;
import org.eclipse.jst.j2ee.webapplication.MimeMapping;
import org.eclipse.jst.j2ee.webapplication.ResAuthServletType;
import org.eclipse.jst.j2ee.webapplication.RoleNameType;
import org.eclipse.jst.j2ee.webapplication.SecurityConstraint;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.ServletMapping;
import org.eclipse.jst.j2ee.webapplication.ServletType;
import org.eclipse.jst.j2ee.webapplication.SessionConfig;
import org.eclipse.jst.j2ee.webapplication.TagLibRef;
import org.eclipse.jst.j2ee.webapplication.TransportGuaranteeType;
import org.eclipse.jst.j2ee.webapplication.URLPatternType;
import org.eclipse.jst.j2ee.webapplication.UserDataConstraint;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebResourceCollection;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.jst.j2ee.webapplication.WelcomeFile;
import org.eclipse.jst.j2ee.webapplication.WelcomeFileList;

/**
 * @generated
 */
public class WebapplicationFactoryImpl extends EFactoryImpl implements WebapplicationFactory{
 
	public static final String MM_VERSION = "1.1";//$NON-NLS-1$
	
	public WebapplicationFactoryImpl() {
		super(); 	
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case WebapplicationPackage.WEB_APP: return createWebApp();
			case WebapplicationPackage.CONTEXT_PARAM: return createContextParam();
			case WebapplicationPackage.SERVLET: return createServlet();
			case WebapplicationPackage.SERVLET_MAPPING: return createServletMapping();
			case WebapplicationPackage.SESSION_CONFIG: return createSessionConfig();
			case WebapplicationPackage.MIME_MAPPING: return createMimeMapping();
			case WebapplicationPackage.WELCOME_FILE_LIST: return createWelcomeFileList();
			case WebapplicationPackage.ERROR_PAGE: return createErrorPage();
			case WebapplicationPackage.TAG_LIB_REF: return createTagLibRef();
			case WebapplicationPackage.SECURITY_CONSTRAINT: return createSecurityConstraint();
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION: return createWebResourceCollection();
			case WebapplicationPackage.AUTH_CONSTRAINT: return createAuthConstraint();
			case WebapplicationPackage.USER_DATA_CONSTRAINT: return createUserDataConstraint();
			case WebapplicationPackage.LOGIN_CONFIG: return createLoginConfig();
			case WebapplicationPackage.FORM_LOGIN_CONFIG: return createFormLoginConfig();
			case WebapplicationPackage.INIT_PARAM: return createInitParam();
			case WebapplicationPackage.SERVLET_TYPE: return createServletType();
			case WebapplicationPackage.JSP_TYPE: return createJSPType();
			case WebapplicationPackage.URL_PATTERN_TYPE: return createURLPatternType();
			case WebapplicationPackage.ROLE_NAME_TYPE: return createRoleNameType();
			case WebapplicationPackage.WELCOME_FILE: return createWelcomeFile();
			case WebapplicationPackage.EXCEPTION_TYPE_ERROR_PAGE: return createExceptionTypeErrorPage();
			case WebapplicationPackage.ERROR_CODE_ERROR_PAGE: return createErrorCodeErrorPage();
			case WebapplicationPackage.FILTER_MAPPING: return createFilterMapping();
			case WebapplicationPackage.FILTER: return createFilter();
			case WebapplicationPackage.LOCAL_ENCODING_MAPPING_LIST: return createLocalEncodingMappingList();
			case WebapplicationPackage.LOCAL_ENCODING_MAPPING: return createLocalEncodingMapping();
			case WebapplicationPackage.HTTP_METHOD_TYPE: return createHTTPMethodType();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case WebapplicationPackage.RES_AUTH_SERVLET_TYPE: {
				ResAuthServletType result = ResAuthServletType.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
				return result;
			}
			case WebapplicationPackage.HTTP_METHOD_TYPE_ENUM: {
				HTTPMethodTypeEnum result = HTTPMethodTypeEnum.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
				return result;
			}
			case WebapplicationPackage.TRANSPORT_GUARANTEE_TYPE: {
				TransportGuaranteeType result = TransportGuaranteeType.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
				return result;
			}
			case WebapplicationPackage.DISPATCHER_TYPE: {
				DispatcherType result = DispatcherType.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
				return result;
			}
			case WebapplicationPackage.AUTH_METHOD_KIND: {
				AuthMethodKind result = AuthMethodKind.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
				return result;
			}
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case WebapplicationPackage.RES_AUTH_SERVLET_TYPE:
				return instanceValue == null ? null : instanceValue.toString();
			case WebapplicationPackage.HTTP_METHOD_TYPE_ENUM:
				return instanceValue == null ? null : instanceValue.toString();
			case WebapplicationPackage.TRANSPORT_GUARANTEE_TYPE:
				return instanceValue == null ? null : instanceValue.toString();
			case WebapplicationPackage.DISPATCHER_TYPE:
				return instanceValue == null ? null : instanceValue.toString();
			case WebapplicationPackage.AUTH_METHOD_KIND:
				return instanceValue == null ? null : instanceValue.toString();
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

public static WebapplicationFactory getActiveFactory() {
	return (WebapplicationFactory) getPackage().getEFactoryInstance();
}
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public WebApp createWebApp() {
		WebAppImpl webApp = new WebAppImpl();
		return webApp;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public ContextParam createContextParam() {
		ContextParamImpl contextParam = new ContextParamImpl();
		return contextParam;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public ErrorPage createErrorPage() {
		ErrorPageImpl errorPage = new ErrorPageImpl();
		return errorPage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TagLibRef createTagLibRef() {
		TagLibRefImpl tagLibRef = new TagLibRefImpl();
		return tagLibRef;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public ExceptionTypeErrorPage createExceptionTypeErrorPage() {
		ExceptionTypeErrorPageImpl exceptionTypeErrorPage = new ExceptionTypeErrorPageImpl();
		return exceptionTypeErrorPage;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public ErrorCodeErrorPage createErrorCodeErrorPage() {
		ErrorCodeErrorPageImpl errorCodeErrorPage = new ErrorCodeErrorPageImpl();
		return errorCodeErrorPage;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public WelcomeFileList createWelcomeFileList() {
		WelcomeFileListImpl welcomeFileList = new WelcomeFileListImpl();
		return welcomeFileList;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public WelcomeFile createWelcomeFile() {
		WelcomeFileImpl welcomeFile = new WelcomeFileImpl();
		return welcomeFile;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public SecurityConstraint createSecurityConstraint() {
		SecurityConstraintImpl securityConstraint = new SecurityConstraintImpl();
		return securityConstraint;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public WebResourceCollection createWebResourceCollection() {
		WebResourceCollectionImpl webResourceCollection = new WebResourceCollectionImpl();
		return webResourceCollection;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public URLPatternType createURLPatternType() {
		URLPatternTypeImpl urlPatternType = new URLPatternTypeImpl();
		return urlPatternType;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public AuthConstraint createAuthConstraint() {
		AuthConstraintImpl authConstraint = new AuthConstraintImpl();
		return authConstraint;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public UserDataConstraint createUserDataConstraint() {
		UserDataConstraintImpl userDataConstraint = new UserDataConstraintImpl();
		return userDataConstraint;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public LoginConfig createLoginConfig() {
		LoginConfigImpl loginConfig = new LoginConfigImpl();
		return loginConfig;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public FormLoginConfig createFormLoginConfig() {
		FormLoginConfigImpl formLoginConfig = new FormLoginConfigImpl();
		return formLoginConfig;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public MimeMapping createMimeMapping() {
		MimeMappingImpl mimeMapping = new MimeMappingImpl();
		return mimeMapping;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public SessionConfig createSessionConfig() {
		SessionConfigImpl sessionConfig = new SessionConfigImpl();
		return sessionConfig;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public ServletMapping createServletMapping() {
		ServletMappingImpl servletMapping = new ServletMappingImpl();
		return servletMapping;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Servlet createServlet() {
		ServletImpl servlet = new ServletImpl();
		return servlet;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public ServletType createServletType() {
		ServletTypeImpl servletType = new ServletTypeImpl();
		return servletType;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public JSPType createJSPType() {
		JSPTypeImpl jspType = new JSPTypeImpl();
		return jspType;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public InitParam createInitParam() {
		InitParamImpl initParam = new InitParamImpl();
		return initParam;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Filter createFilter() {
		FilterImpl filter = new FilterImpl();
		return filter;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public FilterMapping createFilterMapping() {
		FilterMappingImpl filterMapping = new FilterMappingImpl();
		return filterMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LocalEncodingMappingList createLocalEncodingMappingList() {
		LocalEncodingMappingListImpl localEncodingMappingList = new LocalEncodingMappingListImpl();
		return localEncodingMappingList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LocalEncodingMapping createLocalEncodingMapping() {
		LocalEncodingMappingImpl localEncodingMapping = new LocalEncodingMappingImpl();
		return localEncodingMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HTTPMethodType createHTTPMethodType() {
		HTTPMethodTypeImpl httpMethodType = new HTTPMethodTypeImpl();
		return httpMethodType;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public RoleNameType createRoleNameType() {
		RoleNameTypeImpl roleNameType = new RoleNameTypeImpl();
		return roleNameType;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public WebapplicationPackage getWebapplicationPackage() {
		return (WebapplicationPackage)getEPackage();
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static WebapplicationPackage getPackage() {
		return WebapplicationPackage.eINSTANCE;
	}

}







