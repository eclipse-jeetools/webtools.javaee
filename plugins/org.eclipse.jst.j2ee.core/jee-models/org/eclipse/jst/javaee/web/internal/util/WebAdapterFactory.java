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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.jst.javaee.web.*;

import org.eclipse.jst.javaee.web.internal.metadata.WebPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage
 * @generated
 */
public class WebAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static WebPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WebAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = WebPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WebSwitch<Adapter> modelSwitch =
		new WebSwitch<Adapter>() {
			@Override
			public Adapter caseAbsoluteOrderingType(AbsoluteOrderingType object) {
				return createAbsoluteOrderingTypeAdapter();
			}
			@Override
			public Adapter caseAuthConstraint(AuthConstraint object) {
				return createAuthConstraintAdapter();
			}
			@Override
			public Adapter caseCookieCommentType(CookieCommentType object) {
				return createCookieCommentTypeAdapter();
			}
			@Override
			public Adapter caseCookieConfigType(CookieConfigType object) {
				return createCookieConfigTypeAdapter();
			}
			@Override
			public Adapter caseCookieDomainType(CookieDomainType object) {
				return createCookieDomainTypeAdapter();
			}
			@Override
			public Adapter caseCookieNameType(CookieNameType object) {
				return createCookieNameTypeAdapter();
			}
			@Override
			public Adapter caseCookiePathType(CookiePathType object) {
				return createCookiePathTypeAdapter();
			}
			@Override
			public Adapter caseErrorPage(ErrorPage object) {
				return createErrorPageAdapter();
			}
			@Override
			public Adapter caseFilter(Filter object) {
				return createFilterAdapter();
			}
			@Override
			public Adapter caseFilterMapping(FilterMapping object) {
				return createFilterMappingAdapter();
			}
			@Override
			public Adapter caseFormLoginConfig(FormLoginConfig object) {
				return createFormLoginConfigAdapter();
			}
			@Override
			public Adapter caseLocaleEncodingMapping(LocaleEncodingMapping object) {
				return createLocaleEncodingMappingAdapter();
			}
			@Override
			public Adapter caseLocaleEncodingMappingList(LocaleEncodingMappingList object) {
				return createLocaleEncodingMappingListAdapter();
			}
			@Override
			public Adapter caseLoginConfig(LoginConfig object) {
				return createLoginConfigAdapter();
			}
			@Override
			public Adapter caseMimeMapping(MimeMapping object) {
				return createMimeMappingAdapter();
			}
			@Override
			public Adapter caseMultipartConfigType(MultipartConfigType object) {
				return createMultipartConfigTypeAdapter();
			}
			@Override
			public Adapter caseOrderingOrderingType(OrderingOrderingType object) {
				return createOrderingOrderingTypeAdapter();
			}
			@Override
			public Adapter caseOrderingOthersType(OrderingOthersType object) {
				return createOrderingOthersTypeAdapter();
			}
			@Override
			public Adapter caseOrderingType(OrderingType object) {
				return createOrderingTypeAdapter();
			}
			@Override
			public Adapter caseSecurityConstraint(SecurityConstraint object) {
				return createSecurityConstraintAdapter();
			}
			@Override
			public Adapter caseServlet(Servlet object) {
				return createServletAdapter();
			}
			@Override
			public Adapter caseServletMapping(ServletMapping object) {
				return createServletMappingAdapter();
			}
			@Override
			public Adapter caseSessionConfig(SessionConfig object) {
				return createSessionConfigAdapter();
			}
			@Override
			public Adapter caseUserDataConstraint(UserDataConstraint object) {
				return createUserDataConstraintAdapter();
			}
			@Override
			public Adapter caseWebApp(WebApp object) {
				return createWebAppAdapter();
			}
			@Override
			public Adapter caseWebAppDeploymentDescriptor(WebAppDeploymentDescriptor object) {
				return createWebAppDeploymentDescriptorAdapter();
			}
			@Override
			public Adapter caseWebFragment(WebFragment object) {
				return createWebFragmentAdapter();
			}
			@Override
			public Adapter caseWebResourceCollection(WebResourceCollection object) {
				return createWebResourceCollectionAdapter();
			}
			@Override
			public Adapter caseWelcomeFileList(WelcomeFileList object) {
				return createWelcomeFileListAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.web.AbsoluteOrderingType <em>Absolute Ordering Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.web.AbsoluteOrderingType
	 * @generated
	 */
	public Adapter createAbsoluteOrderingTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.web.AuthConstraint <em>Auth Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.web.AuthConstraint
	 * @generated
	 */
	public Adapter createAuthConstraintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.web.CookieCommentType <em>Cookie Comment Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.web.CookieCommentType
	 * @generated
	 */
	public Adapter createCookieCommentTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.web.CookieConfigType <em>Cookie Config Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.web.CookieConfigType
	 * @generated
	 */
	public Adapter createCookieConfigTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.web.CookieDomainType <em>Cookie Domain Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.web.CookieDomainType
	 * @generated
	 */
	public Adapter createCookieDomainTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.web.CookieNameType <em>Cookie Name Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.web.CookieNameType
	 * @generated
	 */
	public Adapter createCookieNameTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.web.CookiePathType <em>Cookie Path Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.web.CookiePathType
	 * @generated
	 */
	public Adapter createCookiePathTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.web.ErrorPage <em>Error Page</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.web.ErrorPage
	 * @generated
	 */
	public Adapter createErrorPageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.web.Filter <em>Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.web.Filter
	 * @generated
	 */
	public Adapter createFilterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.web.FilterMapping <em>Filter Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.web.FilterMapping
	 * @generated
	 */
	public Adapter createFilterMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.web.FormLoginConfig <em>Form Login Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.web.FormLoginConfig
	 * @generated
	 */
	public Adapter createFormLoginConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.web.LocaleEncodingMapping <em>Locale Encoding Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.web.LocaleEncodingMapping
	 * @generated
	 */
	public Adapter createLocaleEncodingMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.web.LocaleEncodingMappingList <em>Locale Encoding Mapping List</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.web.LocaleEncodingMappingList
	 * @generated
	 */
	public Adapter createLocaleEncodingMappingListAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.web.LoginConfig <em>Login Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.web.LoginConfig
	 * @generated
	 */
	public Adapter createLoginConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.web.MimeMapping <em>Mime Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.web.MimeMapping
	 * @generated
	 */
	public Adapter createMimeMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.web.MultipartConfigType <em>Multipart Config Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.web.MultipartConfigType
	 * @generated
	 */
	public Adapter createMultipartConfigTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.web.OrderingOrderingType <em>Ordering Ordering Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.web.OrderingOrderingType
	 * @generated
	 */
	public Adapter createOrderingOrderingTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.web.OrderingOthersType <em>Ordering Others Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.web.OrderingOthersType
	 * @generated
	 */
	public Adapter createOrderingOthersTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.web.OrderingType <em>Ordering Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.web.OrderingType
	 * @generated
	 */
	public Adapter createOrderingTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.web.SecurityConstraint <em>Security Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.web.SecurityConstraint
	 * @generated
	 */
	public Adapter createSecurityConstraintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.web.Servlet <em>Servlet</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.web.Servlet
	 * @generated
	 */
	public Adapter createServletAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.web.ServletMapping <em>Servlet Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.web.ServletMapping
	 * @generated
	 */
	public Adapter createServletMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.web.SessionConfig <em>Session Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.web.SessionConfig
	 * @generated
	 */
	public Adapter createSessionConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.web.UserDataConstraint <em>User Data Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.web.UserDataConstraint
	 * @generated
	 */
	public Adapter createUserDataConstraintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.web.WebApp <em>App</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.web.WebApp
	 * @generated
	 */
	public Adapter createWebAppAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.web.WebAppDeploymentDescriptor <em>App Deployment Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.web.WebAppDeploymentDescriptor
	 * @generated
	 */
	public Adapter createWebAppDeploymentDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.web.WebFragment <em>Fragment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.web.WebFragment
	 * @generated
	 */
	public Adapter createWebFragmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.web.WebResourceCollection <em>Resource Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.web.WebResourceCollection
	 * @generated
	 */
	public Adapter createWebResourceCollectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.web.WelcomeFileList <em>Welcome File List</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.web.WelcomeFileList
	 * @generated
	 */
	public Adapter createWelcomeFileListAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //WebAdapterFactory
