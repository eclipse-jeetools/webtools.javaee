/*******************************************************************************
 * Copyright (c) 2007, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.web.internal.util;

import java.math.BigInteger;

import java.util.Map;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;

import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil;
import org.eclipse.emf.ecore.xml.type.util.XMLTypeValidator;

import org.eclipse.jst.javaee.web.*;

import org.eclipse.jst.javaee.web.internal.metadata.WebPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage
 * @generated
 */
public class WebValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final WebValidator INSTANCE = new WebValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "org.eclipse.jst.javaee.web"; //$NON-NLS-1$

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * The cached base package validator.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected XMLTypeValidator xmlTypeValidator;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WebValidator() {
		super();
		xmlTypeValidator = XMLTypeValidator.INSTANCE;
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return WebPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case WebPackage.ABSOLUTE_ORDERING_TYPE:
				return validateAbsoluteOrderingType((AbsoluteOrderingType)value, diagnostics, context);
			case WebPackage.AUTH_CONSTRAINT:
				return validateAuthConstraint((AuthConstraint)value, diagnostics, context);
			case WebPackage.COOKIE_COMMENT_TYPE:
				return validateCookieCommentType((CookieCommentType)value, diagnostics, context);
			case WebPackage.COOKIE_CONFIG_TYPE:
				return validateCookieConfigType((CookieConfigType)value, diagnostics, context);
			case WebPackage.COOKIE_DOMAIN_TYPE:
				return validateCookieDomainType((CookieDomainType)value, diagnostics, context);
			case WebPackage.COOKIE_NAME_TYPE:
				return validateCookieNameType((CookieNameType)value, diagnostics, context);
			case WebPackage.COOKIE_PATH_TYPE:
				return validateCookiePathType((CookiePathType)value, diagnostics, context);
			case WebPackage.ERROR_PAGE:
				return validateErrorPage((ErrorPage)value, diagnostics, context);
			case WebPackage.FILTER:
				return validateFilter((Filter)value, diagnostics, context);
			case WebPackage.FILTER_MAPPING:
				return validateFilterMapping((FilterMapping)value, diagnostics, context);
			case WebPackage.FORM_LOGIN_CONFIG:
				return validateFormLoginConfig((FormLoginConfig)value, diagnostics, context);
			case WebPackage.LOCALE_ENCODING_MAPPING:
				return validateLocaleEncodingMapping((LocaleEncodingMapping)value, diagnostics, context);
			case WebPackage.LOCALE_ENCODING_MAPPING_LIST:
				return validateLocaleEncodingMappingList((LocaleEncodingMappingList)value, diagnostics, context);
			case WebPackage.LOGIN_CONFIG:
				return validateLoginConfig((LoginConfig)value, diagnostics, context);
			case WebPackage.MIME_MAPPING:
				return validateMimeMapping((MimeMapping)value, diagnostics, context);
			case WebPackage.MULTIPART_CONFIG_TYPE:
				return validateMultipartConfigType((MultipartConfigType)value, diagnostics, context);
			case WebPackage.ORDERING_ORDERING_TYPE:
				return validateOrderingOrderingType((OrderingOrderingType)value, diagnostics, context);
			case WebPackage.ORDERING_OTHERS_TYPE:
				return validateOrderingOthersType((OrderingOthersType)value, diagnostics, context);
			case WebPackage.ORDERING_TYPE:
				return validateOrderingType((OrderingType)value, diagnostics, context);
			case WebPackage.SECURITY_CONSTRAINT:
				return validateSecurityConstraint((SecurityConstraint)value, diagnostics, context);
			case WebPackage.SERVLET:
				return validateServlet((Servlet)value, diagnostics, context);
			case WebPackage.SERVLET_MAPPING:
				return validateServletMapping((ServletMapping)value, diagnostics, context);
			case WebPackage.SESSION_CONFIG:
				return validateSessionConfig((SessionConfig)value, diagnostics, context);
			case WebPackage.USER_DATA_CONSTRAINT:
				return validateUserDataConstraint((UserDataConstraint)value, diagnostics, context);
			case WebPackage.WEB_APP:
				return validateWebApp((WebApp)value, diagnostics, context);
			case WebPackage.WEB_APP_DEPLOYMENT_DESCRIPTOR:
				return validateWebAppDeploymentDescriptor((WebAppDeploymentDescriptor)value, diagnostics, context);
			case WebPackage.WEB_FRAGMENT:
				return validateWebFragment((WebFragment)value, diagnostics, context);
			case WebPackage.WEB_RESOURCE_COLLECTION:
				return validateWebResourceCollection((WebResourceCollection)value, diagnostics, context);
			case WebPackage.WELCOME_FILE_LIST:
				return validateWelcomeFileList((WelcomeFileList)value, diagnostics, context);
			case WebPackage.DISPATCHER_TYPE:
				return validateDispatcherType((DispatcherType)value, diagnostics, context);
			case WebPackage.NULL_CHAR_TYPE:
				return validateNullCharType((NullCharType)value, diagnostics, context);
			case WebPackage.TRACKING_MODE_TYPE:
				return validateTrackingModeType((TrackingModeType)value, diagnostics, context);
			case WebPackage.TRANSPORT_GUARANTEE_TYPE:
				return validateTransportGuaranteeType((TransportGuaranteeType)value, diagnostics, context);
			case WebPackage.WEB_APP_VERSION_TYPE:
				return validateWebAppVersionType((WebAppVersionType)value, diagnostics, context);
			case WebPackage.AUTH_METHOD_TYPE:
				return validateAuthMethodType((String)value, diagnostics, context);
			case WebPackage.DISPATCHER_TYPE_OBJECT:
				return validateDispatcherTypeObject((DispatcherType)value, diagnostics, context);
			case WebPackage.ENCODING_TYPE:
				return validateEncodingType((String)value, diagnostics, context);
			case WebPackage.ERROR_CODE_TYPE:
				return validateErrorCodeType((BigInteger)value, diagnostics, context);
			case WebPackage.FILTER_NAME_TYPE:
				return validateFilterNameType((String)value, diagnostics, context);
			case WebPackage.HTTP_METHOD_TYPE:
				return validateHttpMethodType((String)value, diagnostics, context);
			case WebPackage.LOAD_ON_STARTUP_TYPE:
				return validateLoadOnStartupType(value, diagnostics, context);
			case WebPackage.LOCALE_TYPE:
				return validateLocaleType((String)value, diagnostics, context);
			case WebPackage.MIME_TYPE_TYPE:
				return validateMimeTypeType((String)value, diagnostics, context);
			case WebPackage.NON_EMPTY_STRING_TYPE:
				return validateNonEmptyStringType((String)value, diagnostics, context);
			case WebPackage.NULL_CHAR_TYPE_OBJECT:
				return validateNullCharTypeObject((NullCharType)value, diagnostics, context);
			case WebPackage.SERVLET_NAME_TYPE:
				return validateServletNameType((String)value, diagnostics, context);
			case WebPackage.TRACKING_MODE_TYPE_OBJECT:
				return validateTrackingModeTypeObject((TrackingModeType)value, diagnostics, context);
			case WebPackage.TRANSPORT_GUARANTEE_TYPE_OBJECT:
				return validateTransportGuaranteeTypeObject((TransportGuaranteeType)value, diagnostics, context);
			case WebPackage.WAR_PATH_TYPE:
				return validateWarPathType((String)value, diagnostics, context);
			case WebPackage.WEB_APP_VERSION_TYPE_OBJECT:
				return validateWebAppVersionTypeObject((WebAppVersionType)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAbsoluteOrderingType(AbsoluteOrderingType absoluteOrderingType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)absoluteOrderingType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAuthConstraint(AuthConstraint authConstraint, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)authConstraint, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCookieCommentType(CookieCommentType cookieCommentType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)cookieCommentType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCookieConfigType(CookieConfigType cookieConfigType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)cookieConfigType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCookieDomainType(CookieDomainType cookieDomainType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)cookieDomainType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCookieNameType(CookieNameType cookieNameType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)cookieNameType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCookiePathType(CookiePathType cookiePathType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)cookiePathType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateErrorPage(ErrorPage errorPage, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)errorPage, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFilter(Filter filter, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)filter, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFilterMapping(FilterMapping filterMapping, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)filterMapping, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFormLoginConfig(FormLoginConfig formLoginConfig, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)formLoginConfig, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLocaleEncodingMapping(LocaleEncodingMapping localeEncodingMapping, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)localeEncodingMapping, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLocaleEncodingMappingList(LocaleEncodingMappingList localeEncodingMappingList, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)localeEncodingMappingList, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLoginConfig(LoginConfig loginConfig, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)loginConfig, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMimeMapping(MimeMapping mimeMapping, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)mimeMapping, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMultipartConfigType(MultipartConfigType multipartConfigType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)multipartConfigType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOrderingOrderingType(OrderingOrderingType orderingOrderingType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)orderingOrderingType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOrderingOthersType(OrderingOthersType orderingOthersType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)orderingOthersType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOrderingType(OrderingType orderingType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)orderingType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSecurityConstraint(SecurityConstraint securityConstraint, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)securityConstraint, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateServlet(Servlet servlet, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)servlet, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateServletMapping(ServletMapping servletMapping, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)servletMapping, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSessionConfig(SessionConfig sessionConfig, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)sessionConfig, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateUserDataConstraint(UserDataConstraint userDataConstraint, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)userDataConstraint, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWebApp(WebApp webApp, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)webApp, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWebAppDeploymentDescriptor(WebAppDeploymentDescriptor webAppDeploymentDescriptor, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)webAppDeploymentDescriptor, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWebFragment(WebFragment webFragment, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)webFragment, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWebResourceCollection(WebResourceCollection webResourceCollection, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)webResourceCollection, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWelcomeFileList(WelcomeFileList welcomeFileList, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)welcomeFileList, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDispatcherType(DispatcherType dispatcherType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNullCharType(NullCharType nullCharType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTrackingModeType(TrackingModeType trackingModeType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTransportGuaranteeType(TransportGuaranteeType transportGuaranteeType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWebAppVersionType(WebAppVersionType webAppVersionType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAuthMethodType(String authMethodType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDispatcherTypeObject(DispatcherType dispatcherTypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEncodingType(String encodingType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateEncodingType_Pattern(encodingType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateEncodingType_Pattern
	 */
	public static final  PatternMatcher [][] ENCODING_TYPE__PATTERN__VALUES =
		new PatternMatcher [][] {
			new PatternMatcher [] {
				XMLTypeUtil.createPatternMatcher("[^\\s]+") //$NON-NLS-1$
			}
		};

	/**
	 * Validates the Pattern constraint of '<em>Encoding Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEncodingType_Pattern(String encodingType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validatePattern(WebPackage.Literals.ENCODING_TYPE, encodingType, ENCODING_TYPE__PATTERN__VALUES, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateErrorCodeType(BigInteger errorCodeType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = xmlTypeValidator.validatePositiveInteger_Min(errorCodeType, diagnostics, context);
		if (result || diagnostics != null) result &= validateErrorCodeType_Pattern(errorCodeType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateErrorCodeType_Pattern
	 */
	public static final  PatternMatcher [][] ERROR_CODE_TYPE__PATTERN__VALUES =
		new PatternMatcher [][] {
			new PatternMatcher [] {
				XMLTypeUtil.createPatternMatcher("\\d{3}") //$NON-NLS-1$
			}
		};

	/**
	 * Validates the Pattern constraint of '<em>Error Code Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateErrorCodeType_Pattern(BigInteger errorCodeType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validatePattern(WebPackage.Literals.ERROR_CODE_TYPE, errorCodeType, ERROR_CODE_TYPE__PATTERN__VALUES, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFilterNameType(String filterNameType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateNonEmptyStringType_MinLength(filterNameType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHttpMethodType(String httpMethodType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateHttpMethodType_Pattern(httpMethodType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateHttpMethodType_Pattern
	 */
	public static final  PatternMatcher [][] HTTP_METHOD_TYPE__PATTERN__VALUES =
		new PatternMatcher [][] {
			new PatternMatcher [] {
				XMLTypeUtil.createPatternMatcher("[!-~-[\\(\\)<>@,;:\"/\\[\\]?=\\{\\}\\\\\\p{Z}]]+") //$NON-NLS-1$
			}
		};

	/**
	 * Validates the Pattern constraint of '<em>Http Method Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHttpMethodType_Pattern(String httpMethodType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validatePattern(WebPackage.Literals.HTTP_METHOD_TYPE, httpMethodType, HTTP_METHOD_TYPE__PATTERN__VALUES, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLoadOnStartupType(Object loadOnStartupType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateLoadOnStartupType_MemberTypes(loadOnStartupType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MemberTypes constraint of '<em>Load On Startup Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLoadOnStartupType_MemberTypes(Object loadOnStartupType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (diagnostics != null) {
			BasicDiagnostic tempDiagnostics = new BasicDiagnostic();
			if (WebPackage.Literals.NULL_CHAR_TYPE.isInstance(loadOnStartupType)) {
				if (validateNullCharType((NullCharType)loadOnStartupType, tempDiagnostics, context)) return true;
			}
			if (XMLTypePackage.Literals.INTEGER.isInstance(loadOnStartupType)) {
				if (xmlTypeValidator.validateInteger((BigInteger)loadOnStartupType, tempDiagnostics, context)) return true;
			}
			for (Diagnostic diagnostic : tempDiagnostics.getChildren()) {
				diagnostics.add(diagnostic);
			}
		}
		else {
			if (WebPackage.Literals.NULL_CHAR_TYPE.isInstance(loadOnStartupType)) {
				if (validateNullCharType((NullCharType)loadOnStartupType, null, context)) return true;
			}
			if (XMLTypePackage.Literals.INTEGER.isInstance(loadOnStartupType)) {
				if (xmlTypeValidator.validateInteger((BigInteger)loadOnStartupType, null, context)) return true;
			}
		}
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLocaleType(String localeType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateLocaleType_Pattern(localeType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateLocaleType_Pattern
	 */
	public static final  PatternMatcher [][] LOCALE_TYPE__PATTERN__VALUES =
		new PatternMatcher [][] {
			new PatternMatcher [] {
				XMLTypeUtil.createPatternMatcher("[a-z]{2}(_|-)?([\\p{L}\\-\\p{Nd}]{2})?") //$NON-NLS-1$
			}
		};

	/**
	 * Validates the Pattern constraint of '<em>Locale Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLocaleType_Pattern(String localeType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validatePattern(WebPackage.Literals.LOCALE_TYPE, localeType, LOCALE_TYPE__PATTERN__VALUES, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMimeTypeType(String mimeTypeType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateMimeTypeType_Pattern(mimeTypeType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateMimeTypeType_Pattern
	 */
	public static final  PatternMatcher [][] MIME_TYPE_TYPE__PATTERN__VALUES =
		new PatternMatcher [][] {
			new PatternMatcher [] {
				XMLTypeUtil.createPatternMatcher("[^\\p{Cc}^\\s]+/[^\\p{Cc}^\\s]+") //$NON-NLS-1$
			}
		};

	/**
	 * Validates the Pattern constraint of '<em>Mime Type Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMimeTypeType_Pattern(String mimeTypeType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validatePattern(WebPackage.Literals.MIME_TYPE_TYPE, mimeTypeType, MIME_TYPE_TYPE__PATTERN__VALUES, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNonEmptyStringType(String nonEmptyStringType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateNonEmptyStringType_MinLength(nonEmptyStringType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MinLength constraint of '<em>Non Empty String Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNonEmptyStringType_MinLength(String nonEmptyStringType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		int length = nonEmptyStringType.length();
		boolean result = length >= 1;
		if (!result && diagnostics != null)
			reportMinLengthViolation(WebPackage.Literals.NON_EMPTY_STRING_TYPE, nonEmptyStringType, length, 1, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNullCharTypeObject(NullCharType nullCharTypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateServletNameType(String servletNameType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateNonEmptyStringType_MinLength(servletNameType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTrackingModeTypeObject(TrackingModeType trackingModeTypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTransportGuaranteeTypeObject(TransportGuaranteeType transportGuaranteeTypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWarPathType(String warPathType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateWarPathType_Pattern(warPathType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateWarPathType_Pattern
	 */
	public static final  PatternMatcher [][] WAR_PATH_TYPE__PATTERN__VALUES =
		new PatternMatcher [][] {
			new PatternMatcher [] {
				XMLTypeUtil.createPatternMatcher("/.*") //$NON-NLS-1$
			}
		};

	/**
	 * Validates the Pattern constraint of '<em>War Path Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWarPathType_Pattern(String warPathType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validatePattern(WebPackage.Literals.WAR_PATH_TYPE, warPathType, WAR_PATH_TYPE__PATTERN__VALUES, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWebAppVersionTypeObject(WebAppVersionType webAppVersionTypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} //WebValidator
