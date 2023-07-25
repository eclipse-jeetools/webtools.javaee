/*******************************************************************************
 * Copyright (c) 2007, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.web.internal.impl;

import java.math.BigInteger;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.jst.javaee.web.*;
import org.eclipse.jst.javaee.web.internal.metadata.WebPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class WebFactoryImpl extends EFactoryImpl implements WebFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static WebFactory init() {
		try {
			WebFactory theWebFactory = (WebFactory)EPackage.Registry.INSTANCE.getEFactory("http://xmlns.jcp.org/xml/ns/javaee/web"); //$NON-NLS-1$ 
			if (theWebFactory != null) {
				return theWebFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new WebFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WebFactoryImpl() {
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
			case WebPackage.ABSOLUTE_ORDERING_TYPE: return (EObject)createAbsoluteOrderingType();
			case WebPackage.AUTH_CONSTRAINT: return (EObject)createAuthConstraint();
			case WebPackage.COOKIE_COMMENT_TYPE: return (EObject)createCookieCommentType();
			case WebPackage.COOKIE_CONFIG_TYPE: return (EObject)createCookieConfigType();
			case WebPackage.COOKIE_DOMAIN_TYPE: return (EObject)createCookieDomainType();
			case WebPackage.COOKIE_NAME_TYPE: return (EObject)createCookieNameType();
			case WebPackage.COOKIE_PATH_TYPE: return (EObject)createCookiePathType();
			case WebPackage.ERROR_PAGE: return (EObject)createErrorPage();
			case WebPackage.FILTER: return (EObject)createFilter();
			case WebPackage.FILTER_MAPPING: return (EObject)createFilterMapping();
			case WebPackage.FORM_LOGIN_CONFIG: return (EObject)createFormLoginConfig();
			case WebPackage.LOCALE_ENCODING_MAPPING: return (EObject)createLocaleEncodingMapping();
			case WebPackage.LOCALE_ENCODING_MAPPING_LIST: return (EObject)createLocaleEncodingMappingList();
			case WebPackage.LOGIN_CONFIG: return (EObject)createLoginConfig();
			case WebPackage.MIME_MAPPING: return (EObject)createMimeMapping();
			case WebPackage.MULTIPART_CONFIG_TYPE: return (EObject)createMultipartConfigType();
			case WebPackage.ORDERING_ORDERING_TYPE: return (EObject)createOrderingOrderingType();
			case WebPackage.ORDERING_OTHERS_TYPE: return (EObject)createOrderingOthersType();
			case WebPackage.ORDERING_TYPE: return (EObject)createOrderingType();
			case WebPackage.SECURITY_CONSTRAINT: return (EObject)createSecurityConstraint();
			case WebPackage.SERVLET: return (EObject)createServlet();
			case WebPackage.SERVLET_MAPPING: return (EObject)createServletMapping();
			case WebPackage.SESSION_CONFIG: return (EObject)createSessionConfig();
			case WebPackage.USER_DATA_CONSTRAINT: return (EObject)createUserDataConstraint();
			case WebPackage.WEB_APP: return (EObject)createWebApp();
			case WebPackage.WEB_APP_DEPLOYMENT_DESCRIPTOR: return (EObject)createWebAppDeploymentDescriptor();
			case WebPackage.WEB_FRAGMENT: return (EObject)createWebFragment();
			case WebPackage.WEB_RESOURCE_COLLECTION: return (EObject)createWebResourceCollection();
			case WebPackage.WELCOME_FILE_LIST: return (EObject)createWelcomeFileList();
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
			case WebPackage.DISPATCHER_TYPE:
				return createDispatcherTypeFromString(eDataType, initialValue);
			case WebPackage.NULL_CHAR_TYPE:
				return createNullCharTypeFromString(eDataType, initialValue);
			case WebPackage.TRACKING_MODE_TYPE:
				return createTrackingModeTypeFromString(eDataType, initialValue);
			case WebPackage.TRANSPORT_GUARANTEE_TYPE:
				return createTransportGuaranteeTypeFromString(eDataType, initialValue);
			case WebPackage.WEB_APP_VERSION_TYPE:
				return createWebAppVersionTypeFromString(eDataType, initialValue);
			case WebPackage.AUTH_METHOD_TYPE:
				return createAuthMethodTypeFromString(eDataType, initialValue);
			case WebPackage.DISPATCHER_TYPE_OBJECT:
				return createDispatcherTypeObjectFromString(eDataType, initialValue);
			case WebPackage.ENCODING_TYPE:
				return createEncodingTypeFromString(eDataType, initialValue);
			case WebPackage.ERROR_CODE_TYPE:
				return createErrorCodeTypeFromString(eDataType, initialValue);
			case WebPackage.FILTER_NAME_TYPE:
				return createFilterNameTypeFromString(eDataType, initialValue);
			case WebPackage.HTTP_METHOD_TYPE:
				return createHttpMethodTypeFromString(eDataType, initialValue);
			case WebPackage.LOAD_ON_STARTUP_TYPE:
				return createLoadOnStartupTypeFromString(eDataType, initialValue);
			case WebPackage.LOCALE_TYPE:
				return createLocaleTypeFromString(eDataType, initialValue);
			case WebPackage.MIME_TYPE_TYPE:
				return createMimeTypeTypeFromString(eDataType, initialValue);
			case WebPackage.NON_EMPTY_STRING_TYPE:
				return createNonEmptyStringTypeFromString(eDataType, initialValue);
			case WebPackage.NULL_CHAR_TYPE_OBJECT:
				return createNullCharTypeObjectFromString(eDataType, initialValue);
			case WebPackage.SERVLET_NAME_TYPE:
				return createServletNameTypeFromString(eDataType, initialValue);
			case WebPackage.TRACKING_MODE_TYPE_OBJECT:
				return createTrackingModeTypeObjectFromString(eDataType, initialValue);
			case WebPackage.TRANSPORT_GUARANTEE_TYPE_OBJECT:
				return createTransportGuaranteeTypeObjectFromString(eDataType, initialValue);
			case WebPackage.WAR_PATH_TYPE:
				return createWarPathTypeFromString(eDataType, initialValue);
			case WebPackage.WEB_APP_VERSION_TYPE_OBJECT:
				return createWebAppVersionTypeObjectFromString(eDataType, initialValue);
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
			case WebPackage.DISPATCHER_TYPE:
				return convertDispatcherTypeToString(eDataType, instanceValue);
			case WebPackage.NULL_CHAR_TYPE:
				return convertNullCharTypeToString(eDataType, instanceValue);
			case WebPackage.TRACKING_MODE_TYPE:
				return convertTrackingModeTypeToString(eDataType, instanceValue);
			case WebPackage.TRANSPORT_GUARANTEE_TYPE:
				return convertTransportGuaranteeTypeToString(eDataType, instanceValue);
			case WebPackage.WEB_APP_VERSION_TYPE:
				return convertWebAppVersionTypeToString(eDataType, instanceValue);
			case WebPackage.AUTH_METHOD_TYPE:
				return convertAuthMethodTypeToString(eDataType, instanceValue);
			case WebPackage.DISPATCHER_TYPE_OBJECT:
				return convertDispatcherTypeObjectToString(eDataType, instanceValue);
			case WebPackage.ENCODING_TYPE:
				return convertEncodingTypeToString(eDataType, instanceValue);
			case WebPackage.ERROR_CODE_TYPE:
				return convertErrorCodeTypeToString(eDataType, instanceValue);
			case WebPackage.FILTER_NAME_TYPE:
				return convertFilterNameTypeToString(eDataType, instanceValue);
			case WebPackage.HTTP_METHOD_TYPE:
				return convertHttpMethodTypeToString(eDataType, instanceValue);
			case WebPackage.LOAD_ON_STARTUP_TYPE:
				return convertLoadOnStartupTypeToString(eDataType, instanceValue);
			case WebPackage.LOCALE_TYPE:
				return convertLocaleTypeToString(eDataType, instanceValue);
			case WebPackage.MIME_TYPE_TYPE:
				return convertMimeTypeTypeToString(eDataType, instanceValue);
			case WebPackage.NON_EMPTY_STRING_TYPE:
				return convertNonEmptyStringTypeToString(eDataType, instanceValue);
			case WebPackage.NULL_CHAR_TYPE_OBJECT:
				return convertNullCharTypeObjectToString(eDataType, instanceValue);
			case WebPackage.SERVLET_NAME_TYPE:
				return convertServletNameTypeToString(eDataType, instanceValue);
			case WebPackage.TRACKING_MODE_TYPE_OBJECT:
				return convertTrackingModeTypeObjectToString(eDataType, instanceValue);
			case WebPackage.TRANSPORT_GUARANTEE_TYPE_OBJECT:
				return convertTransportGuaranteeTypeObjectToString(eDataType, instanceValue);
			case WebPackage.WAR_PATH_TYPE:
				return convertWarPathTypeToString(eDataType, instanceValue);
			case WebPackage.WEB_APP_VERSION_TYPE_OBJECT:
				return convertWebAppVersionTypeObjectToString(eDataType, instanceValue);
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
	public AbsoluteOrderingType createAbsoluteOrderingType() {
		AbsoluteOrderingTypeImpl absoluteOrderingType = new AbsoluteOrderingTypeImpl();
		return absoluteOrderingType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AuthConstraint createAuthConstraint() {
		AuthConstraintImpl authConstraint = new AuthConstraintImpl();
		return authConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CookieCommentType createCookieCommentType() {
		CookieCommentTypeImpl cookieCommentType = new CookieCommentTypeImpl();
		return cookieCommentType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CookieConfigType createCookieConfigType() {
		CookieConfigTypeImpl cookieConfigType = new CookieConfigTypeImpl();
		return cookieConfigType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CookieDomainType createCookieDomainType() {
		CookieDomainTypeImpl cookieDomainType = new CookieDomainTypeImpl();
		return cookieDomainType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CookieNameType createCookieNameType() {
		CookieNameTypeImpl cookieNameType = new CookieNameTypeImpl();
		return cookieNameType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CookiePathType createCookiePathType() {
		CookiePathTypeImpl cookiePathType = new CookiePathTypeImpl();
		return cookiePathType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
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
	public Filter createFilter() {
		FilterImpl filter = new FilterImpl();
		return filter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
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
	public FormLoginConfig createFormLoginConfig() {
		FormLoginConfigImpl formLoginConfig = new FormLoginConfigImpl();
		return formLoginConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LocaleEncodingMapping createLocaleEncodingMapping() {
		LocaleEncodingMappingImpl localeEncodingMapping = new LocaleEncodingMappingImpl();
		return localeEncodingMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LocaleEncodingMappingList createLocaleEncodingMappingList() {
		LocaleEncodingMappingListImpl localeEncodingMappingList = new LocaleEncodingMappingListImpl();
		return localeEncodingMappingList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LoginConfig createLoginConfig() {
		LoginConfigImpl loginConfig = new LoginConfigImpl();
		return loginConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MimeMapping createMimeMapping() {
		MimeMappingImpl mimeMapping = new MimeMappingImpl();
		return mimeMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MultipartConfigType createMultipartConfigType() {
		MultipartConfigTypeImpl multipartConfigType = new MultipartConfigTypeImpl();
		return multipartConfigType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OrderingOrderingType createOrderingOrderingType() {
		OrderingOrderingTypeImpl orderingOrderingType = new OrderingOrderingTypeImpl();
		return orderingOrderingType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OrderingOthersType createOrderingOthersType() {
		OrderingOthersTypeImpl orderingOthersType = new OrderingOthersTypeImpl();
		return orderingOthersType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OrderingType createOrderingType() {
		OrderingTypeImpl orderingType = new OrderingTypeImpl();
		return orderingType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SecurityConstraint createSecurityConstraint() {
		SecurityConstraintImpl securityConstraint = new SecurityConstraintImpl();
		return securityConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Servlet createServlet() {
		ServletImpl servlet = new ServletImpl();
		return servlet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ServletMapping createServletMapping() {
		ServletMappingImpl servletMapping = new ServletMappingImpl();
		return servletMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SessionConfig createSessionConfig() {
		SessionConfigImpl sessionConfig = new SessionConfigImpl();
		return sessionConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public UserDataConstraint createUserDataConstraint() {
		UserDataConstraintImpl userDataConstraint = new UserDataConstraintImpl();
		return userDataConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WebApp createWebApp() {
		WebAppImpl webApp = new WebAppImpl();
		return webApp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WebAppDeploymentDescriptor createWebAppDeploymentDescriptor() {
		WebAppDeploymentDescriptorImpl webAppDeploymentDescriptor = new WebAppDeploymentDescriptorImpl();
		return webAppDeploymentDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WebFragment createWebFragment() {
		WebFragmentImpl webFragment = new WebFragmentImpl();
		return webFragment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WebResourceCollection createWebResourceCollection() {
		WebResourceCollectionImpl webResourceCollection = new WebResourceCollectionImpl();
		return webResourceCollection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WelcomeFileList createWelcomeFileList() {
		WelcomeFileListImpl welcomeFileList = new WelcomeFileListImpl();
		return welcomeFileList;
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
	public NullCharType createNullCharTypeFromString(EDataType eDataType, String initialValue) {
		NullCharType result = NullCharType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertNullCharTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TrackingModeType createTrackingModeTypeFromString(EDataType eDataType, String initialValue) {
		TrackingModeType result = TrackingModeType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTrackingModeTypeToString(EDataType eDataType, Object instanceValue) {
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
	public WebAppVersionType createWebAppVersionTypeFromString(EDataType eDataType, String initialValue) {
		WebAppVersionType result = WebAppVersionType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertWebAppVersionTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createAuthMethodTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.TOKEN, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertAuthMethodTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.TOKEN, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DispatcherType createDispatcherTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createDispatcherTypeFromString(WebPackage.Literals.DISPATCHER_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDispatcherTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertDispatcherTypeToString(WebPackage.Literals.DISPATCHER_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createEncodingTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEncodingTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.STRING, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createErrorCodeTypeFromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.POSITIVE_INTEGER, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertErrorCodeTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.POSITIVE_INTEGER, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createFilterNameTypeFromString(EDataType eDataType, String initialValue) {
		return createNonEmptyStringTypeFromString(WebPackage.Literals.NON_EMPTY_STRING_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertFilterNameTypeToString(EDataType eDataType, Object instanceValue) {
		return convertNonEmptyStringTypeToString(WebPackage.Literals.NON_EMPTY_STRING_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createHttpMethodTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.TOKEN, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertHttpMethodTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.TOKEN, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object createLoadOnStartupTypeFromString(EDataType eDataType, String initialValue) {
		if (initialValue == null) return null;
		Object result = null;
		RuntimeException exception = null;
		try {
			result = createNullCharTypeFromString(WebPackage.Literals.NULL_CHAR_TYPE, initialValue);
			if (result != null && Diagnostician.INSTANCE.validate(eDataType, result, null, null)) {
				return result;
			}
		}
		catch (RuntimeException e) {
			exception = e;
		}
		try {
			result = XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.INTEGER, initialValue);
			if (result != null && Diagnostician.INSTANCE.validate(eDataType, result, null, null)) {
				return result;
			}
		}
		catch (RuntimeException e) {
			exception = e;
		}
		if (result != null || exception == null) return result;
    
		throw exception;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public String convertLoadOnStartupTypeToString(EDataType eDataType, Object instanceValue) {
		if (instanceValue == null) return null;
		if (WebPackage.Literals.NULL_CHAR_TYPE.isInstance(instanceValue)) {
			try {
				String value = convertNullCharTypeToString(WebPackage.Literals.NULL_CHAR_TYPE, instanceValue);
				if (value != null) return value;
			}
			catch (Exception e) {
			}
		}
		if (XMLTypePackage.Literals.INTEGER.isInstance(instanceValue)) {
			try {
				String value = XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.INTEGER, instanceValue);
				if (value != null) return value;
			}
			catch (Exception e) {
			}
		}
		String value = instanceValue.toString();
		if (value != null) return value;
		throw new IllegalArgumentException("Invalid value: '"+instanceValue+"' for datatype :"+eDataType.getName()); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createLocaleTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLocaleTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.STRING, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createMimeTypeTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.TOKEN, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMimeTypeTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.TOKEN, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createNonEmptyStringTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.TOKEN, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertNonEmptyStringTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.TOKEN, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NullCharType createNullCharTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createNullCharTypeFromString(WebPackage.Literals.NULL_CHAR_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertNullCharTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertNullCharTypeToString(WebPackage.Literals.NULL_CHAR_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createServletNameTypeFromString(EDataType eDataType, String initialValue) {
		return createNonEmptyStringTypeFromString(WebPackage.Literals.NON_EMPTY_STRING_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertServletNameTypeToString(EDataType eDataType, Object instanceValue) {
		return convertNonEmptyStringTypeToString(WebPackage.Literals.NON_EMPTY_STRING_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TrackingModeType createTrackingModeTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createTrackingModeTypeFromString(WebPackage.Literals.TRACKING_MODE_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTrackingModeTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertTrackingModeTypeToString(WebPackage.Literals.TRACKING_MODE_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransportGuaranteeType createTransportGuaranteeTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createTransportGuaranteeTypeFromString(WebPackage.Literals.TRANSPORT_GUARANTEE_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTransportGuaranteeTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertTransportGuaranteeTypeToString(WebPackage.Literals.TRANSPORT_GUARANTEE_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createWarPathTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.TOKEN, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertWarPathTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.TOKEN, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WebAppVersionType createWebAppVersionTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createWebAppVersionTypeFromString(WebPackage.Literals.WEB_APP_VERSION_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertWebAppVersionTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertWebAppVersionTypeToString(WebPackage.Literals.WEB_APP_VERSION_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WebPackage getWebPackage() {
		return (WebPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static WebPackage getPackage() {
		return WebPackage.eINSTANCE;
	}

} //WebFactoryImpl
