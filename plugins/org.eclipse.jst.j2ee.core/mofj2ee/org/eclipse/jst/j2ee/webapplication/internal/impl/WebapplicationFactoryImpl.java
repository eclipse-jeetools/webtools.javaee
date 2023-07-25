/*******************************************************************************
 * Copyright (c) 2001, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.webapplication.internal.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
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
public class WebapplicationFactoryImpl extends EFactoryImpl implements WebapplicationFactory {
 
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static WebapplicationFactory init() {
		try {
			WebapplicationFactory theWebapplicationFactory = (WebapplicationFactory)EPackage.Registry.INSTANCE.getEFactory("webapplication.xmi");  //$NON-NLS-1$
			if (theWebapplicationFactory != null) {
				return theWebapplicationFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new WebapplicationFactoryImpl();
	}

	public static final String MM_VERSION = "1.1";//$NON-NLS-1$
	
	public WebapplicationFactoryImpl() {
		super(); 	
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
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
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case WebapplicationPackage.RES_AUTH_SERVLET_TYPE:
				return createResAuthServletTypeFromString(eDataType, initialValue);
			case WebapplicationPackage.HTTP_METHOD_TYPE_ENUM:
				return createHTTPMethodTypeEnumFromString(eDataType, initialValue);
			case WebapplicationPackage.TRANSPORT_GUARANTEE_TYPE:
				return createTransportGuaranteeTypeFromString(eDataType, initialValue);
			case WebapplicationPackage.DISPATCHER_TYPE:
				return createDispatcherTypeFromString(eDataType, initialValue);
			case WebapplicationPackage.AUTH_METHOD_KIND:
				return createAuthMethodKindFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case WebapplicationPackage.RES_AUTH_SERVLET_TYPE:
				return convertResAuthServletTypeToString(eDataType, instanceValue);
			case WebapplicationPackage.HTTP_METHOD_TYPE_ENUM:
				return convertHTTPMethodTypeEnumToString(eDataType, instanceValue);
			case WebapplicationPackage.TRANSPORT_GUARANTEE_TYPE:
				return convertTransportGuaranteeTypeToString(eDataType, instanceValue);
			case WebapplicationPackage.DISPATCHER_TYPE:
				return convertDispatcherTypeToString(eDataType, instanceValue);
			case WebapplicationPackage.AUTH_METHOD_KIND:
				return convertAuthMethodKindToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

public static WebapplicationFactory getActiveFactory() {
	return (WebapplicationFactory) getPackage().getEFactoryInstance();
}
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public WebApp createWebApp() {
		WebAppImpl webApp = new WebAppImpl();
		return webApp;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public ContextParam createContextParam() {
		ContextParamImpl contextParam = new ContextParamImpl();
		return contextParam;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public ErrorPage createErrorPage() {
		ErrorPageImpl errorPage = new ErrorPageImpl();
		return errorPage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TagLibRef createTagLibRef() {
		TagLibRefImpl tagLibRef = new TagLibRefImpl();
		return tagLibRef;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public ExceptionTypeErrorPage createExceptionTypeErrorPage() {
		ExceptionTypeErrorPageImpl exceptionTypeErrorPage = new ExceptionTypeErrorPageImpl();
		return exceptionTypeErrorPage;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public ErrorCodeErrorPage createErrorCodeErrorPage() {
		ErrorCodeErrorPageImpl errorCodeErrorPage = new ErrorCodeErrorPageImpl();
		return errorCodeErrorPage;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public WelcomeFileList createWelcomeFileList() {
		WelcomeFileListImpl welcomeFileList = new WelcomeFileListImpl();
		return welcomeFileList;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public WelcomeFile createWelcomeFile() {
		WelcomeFileImpl welcomeFile = new WelcomeFileImpl();
		return welcomeFile;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public SecurityConstraint createSecurityConstraint() {
		SecurityConstraintImpl securityConstraint = new SecurityConstraintImpl();
		return securityConstraint;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public WebResourceCollection createWebResourceCollection() {
		WebResourceCollectionImpl webResourceCollection = new WebResourceCollectionImpl();
		return webResourceCollection;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public URLPatternType createURLPatternType() {
		URLPatternTypeImpl urlPatternType = new URLPatternTypeImpl();
		return urlPatternType;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public AuthConstraint createAuthConstraint() {
		AuthConstraintImpl authConstraint = new AuthConstraintImpl();
		return authConstraint;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public UserDataConstraint createUserDataConstraint() {
		UserDataConstraintImpl userDataConstraint = new UserDataConstraintImpl();
		return userDataConstraint;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public LoginConfig createLoginConfig() {
		LoginConfigImpl loginConfig = new LoginConfigImpl();
		return loginConfig;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public FormLoginConfig createFormLoginConfig() {
		FormLoginConfigImpl formLoginConfig = new FormLoginConfigImpl();
		return formLoginConfig;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public MimeMapping createMimeMapping() {
		MimeMappingImpl mimeMapping = new MimeMappingImpl();
		return mimeMapping;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public SessionConfig createSessionConfig() {
		SessionConfigImpl sessionConfig = new SessionConfigImpl();
		return sessionConfig;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public ServletMapping createServletMapping() {
		ServletMappingImpl servletMapping = new ServletMappingImpl();
		return servletMapping;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public Servlet createServlet() {
		ServletImpl servlet = new ServletImpl();
		return servlet;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public ServletType createServletType() {
		ServletTypeImpl servletType = new ServletTypeImpl();
		return servletType;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public JSPType createJSPType() {
		JSPTypeImpl jspType = new JSPTypeImpl();
		return jspType;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public InitParam createInitParam() {
		InitParamImpl initParam = new InitParamImpl();
		return initParam;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public Filter createFilter() {
		FilterImpl filter = new FilterImpl();
		return filter;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public FilterMapping createFilterMapping() {
		FilterMappingImpl filterMapping = new FilterMappingImpl();
		return filterMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LocalEncodingMappingList createLocalEncodingMappingList() {
		LocalEncodingMappingListImpl localEncodingMappingList = new LocalEncodingMappingListImpl();
		return localEncodingMappingList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LocalEncodingMapping createLocalEncodingMapping() {
		LocalEncodingMappingImpl localEncodingMapping = new LocalEncodingMappingImpl();
		return localEncodingMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public HTTPMethodType createHTTPMethodType() {
		HTTPMethodTypeImpl httpMethodType = new HTTPMethodTypeImpl();
		return httpMethodType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResAuthServletType createResAuthServletTypeFromString(EDataType eDataType, String initialValue) {
		ResAuthServletType result = ResAuthServletType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertResAuthServletTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HTTPMethodTypeEnum createHTTPMethodTypeEnumFromString(EDataType eDataType, String initialValue) {
		HTTPMethodTypeEnum result = HTTPMethodTypeEnum.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertHTTPMethodTypeEnumToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransportGuaranteeType createTransportGuaranteeTypeFromString(EDataType eDataType, String initialValue) {
		TransportGuaranteeType result = TransportGuaranteeType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTransportGuaranteeTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DispatcherType createDispatcherTypeFromString(EDataType eDataType, String initialValue) {
		DispatcherType result = DispatcherType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDispatcherTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AuthMethodKind createAuthMethodKindFromString(EDataType eDataType, String initialValue) {
		AuthMethodKind result = AuthMethodKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertAuthMethodKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public RoleNameType createRoleNameType() {
		RoleNameTypeImpl roleNameType = new RoleNameTypeImpl();
		return roleNameType;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
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







