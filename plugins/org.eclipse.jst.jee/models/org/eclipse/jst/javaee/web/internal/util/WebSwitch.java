/**
 * <copyright>
 * </copyright>
 *
 * $Id: WebSwitch.java,v 1.1 2007/03/20 18:04:40 jsholl Exp $
 */
package org.eclipse.jst.javaee.web.internal.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

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
public class WebSwitch {
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
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public Object doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected Object doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch((EClass)eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected Object doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case WebPackage.AUTH_CONSTRAINT: {
				AuthConstraint authConstraint = (AuthConstraint)theEObject;
				Object result = caseAuthConstraint(authConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.ERROR_PAGE: {
				ErrorPage errorPage = (ErrorPage)theEObject;
				Object result = caseErrorPage(errorPage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.FILTER: {
				Filter filter = (Filter)theEObject;
				Object result = caseFilter(filter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.FILTER_MAPPING: {
				FilterMapping filterMapping = (FilterMapping)theEObject;
				Object result = caseFilterMapping(filterMapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.FORM_LOGIN_CONFIG: {
				FormLoginConfig formLoginConfig = (FormLoginConfig)theEObject;
				Object result = caseFormLoginConfig(formLoginConfig);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.LOCALE_ENCODING_MAPPING: {
				LocaleEncodingMapping localeEncodingMapping = (LocaleEncodingMapping)theEObject;
				Object result = caseLocaleEncodingMapping(localeEncodingMapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.LOCALE_ENCODING_MAPPING_LIST: {
				LocaleEncodingMappingList localeEncodingMappingList = (LocaleEncodingMappingList)theEObject;
				Object result = caseLocaleEncodingMappingList(localeEncodingMappingList);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.LOGIN_CONFIG: {
				LoginConfig loginConfig = (LoginConfig)theEObject;
				Object result = caseLoginConfig(loginConfig);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.MIME_MAPPING: {
				MimeMapping mimeMapping = (MimeMapping)theEObject;
				Object result = caseMimeMapping(mimeMapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.SECURITY_CONSTRAINT: {
				SecurityConstraint securityConstraint = (SecurityConstraint)theEObject;
				Object result = caseSecurityConstraint(securityConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.SERVLET: {
				Servlet servlet = (Servlet)theEObject;
				Object result = caseServlet(servlet);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.SERVLET_MAPPING: {
				ServletMapping servletMapping = (ServletMapping)theEObject;
				Object result = caseServletMapping(servletMapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.SESSION_CONFIG: {
				SessionConfig sessionConfig = (SessionConfig)theEObject;
				Object result = caseSessionConfig(sessionConfig);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.USER_DATA_CONSTRAINT: {
				UserDataConstraint userDataConstraint = (UserDataConstraint)theEObject;
				Object result = caseUserDataConstraint(userDataConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.WEB_APP: {
				WebApp webApp = (WebApp)theEObject;
				Object result = caseWebApp(webApp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.WEB_APP_DEPLOYMENT_DESCRIPTOR: {
				WebAppDeploymentDescriptor webAppDeploymentDescriptor = (WebAppDeploymentDescriptor)theEObject;
				Object result = caseWebAppDeploymentDescriptor(webAppDeploymentDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.WEB_RESOURCE_COLLECTION: {
				WebResourceCollection webResourceCollection = (WebResourceCollection)theEObject;
				Object result = caseWebResourceCollection(webResourceCollection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WebPackage.WELCOME_FILE_LIST: {
				WelcomeFileList welcomeFileList = (WelcomeFileList)theEObject;
				Object result = caseWelcomeFileList(welcomeFileList);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
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
	 * Returns the result of interpretting the object as an instance of '<em>Locale Encoding Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Locale Encoding Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLocaleEncodingMapping(LocaleEncodingMapping object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Locale Encoding Mapping List</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Locale Encoding Mapping List</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLocaleEncodingMappingList(LocaleEncodingMappingList object) {
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
	 * Returns the result of interpretting the object as an instance of '<em>App</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>App</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseWebApp(WebApp object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>App Deployment Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>App Deployment Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseWebAppDeploymentDescriptor(WebAppDeploymentDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Resource Collection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Resource Collection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseWebResourceCollection(WebResourceCollection object) {
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

} //WebSwitch
