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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup;
import org.eclipse.jst.j2ee.common.DescriptionGroup;
import org.eclipse.jst.j2ee.common.JNDIEnvRefsGroup;
import org.eclipse.jst.j2ee.common.Listener;
import org.eclipse.jst.j2ee.jsp.TagLibRefType;
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



public class WebapplicationAdapterFactory extends AdapterFactoryImpl {
	protected static WebapplicationPackage modelPackage;

	public WebapplicationAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = (WebapplicationPackage)EPackage.Registry.INSTANCE.getEPackage(WebapplicationPackage.eNS_URI);
		}
	}
	public boolean isFactoryForType(Object type) {
		if (type == modelPackage) {
			return true;
		}
		if (type instanceof EObject) {
			return ((EObject)type).eClass().eContainer() == modelPackage;
		}
		return false;
	}

	protected WebapplicationSwitch sw = new WebapplicationSwitch() {
		public Object caseWebApp(WebApp object) {
			return createWebAppAdapter();
		}
		public Object caseContextParam(ContextParam object) {
			return createContextParamAdapter();
		}
		public Object caseErrorPage(ErrorPage object) {
			return createErrorPageAdapter();
		}
		public Object caseExceptionTypeErrorPage(ExceptionTypeErrorPage object) {
			return createExceptionTypeErrorPageAdapter();
		}
		public Object caseErrorCodeErrorPage(ErrorCodeErrorPage object) {
			return createErrorCodeErrorPageAdapter();
		}
		public Object caseWelcomeFileList(WelcomeFileList object) {
			return createWelcomeFileListAdapter();
		}
		public Object caseWelcomeFile(WelcomeFile object) {
			return createWelcomeFileAdapter();
		}
		public Object caseTagLibRef(TagLibRef object) {
			return createTagLibRefAdapter();
		}
		public Object caseSecurityConstraint(SecurityConstraint object) {
			return createSecurityConstraintAdapter();
		}
		public Object caseWebResourceCollection(WebResourceCollection object) {
			return createWebResourceCollectionAdapter();
		}
		public Object caseURLPatternType(URLPatternType object) {
			return createURLPatternTypeAdapter();
		}
		public Object caseHTTPMethodType(HTTPMethodType object) {
			return createHTTPMethodTypeAdapter();
		}
		public Object caseAuthConstraint(AuthConstraint object) {
			return createAuthConstraintAdapter();
		}
		public Object caseUserDataConstraint(UserDataConstraint object) {
			return createUserDataConstraintAdapter();
		}
		public Object caseLoginConfig(LoginConfig object) {
			return createLoginConfigAdapter();
		}
		public Object caseFormLoginConfig(FormLoginConfig object) {
			return createFormLoginConfigAdapter();
		}
		public Object caseMimeMapping(MimeMapping object) {
			return createMimeMappingAdapter();
		}
		public Object caseSessionConfig(SessionConfig object) {
			return createSessionConfigAdapter();
		}
		public Object caseServletMapping(ServletMapping object) {
			return createServletMappingAdapter();
		}
		public Object caseServlet(Servlet object) {
			return createServletAdapter();
		}
		public Object caseWebType(WebType object) {
			return createWebTypeAdapter();
		}
		public Object caseServletType(ServletType object) {
			return createServletTypeAdapter();
		}
		public Object caseJSPType(JSPType object) {
			return createJSPTypeAdapter();
		}
		public Object caseInitParam(InitParam object) {
			return createInitParamAdapter();
		}
		public Object caseFilter(Filter object) {
			return createFilterAdapter();
		}
		public Object caseFilterMapping(FilterMapping object) {
			return createFilterMappingAdapter();
		}
		public Object caseListener(Listener object) {
			return createListenerAdapter();
		}
		public Object caseRoleNameType(RoleNameType object) {
			return createRoleNameTypeAdapter();
		}
		public Object caseLocalEncodingMappingList(LocalEncodingMappingList object) {
			return createLocalEncodingMappingListAdapter();
		}
		public Object caseLocalEncodingMapping(LocalEncodingMapping object) {
			return createLocalEncodingMappingAdapter();
		}
	};

	public Adapter createAdapter(Notifier target) {
		return (Adapter)sw.doSwitch((EObject)target);
	}

	/**
	 * By default create methods return null so that we can easily ignore cases.
	 * It's useful to ignore a case when inheritance will catch all the cases anyway.
	 */

	public Adapter createWebAppAdapter() {
		return null;
	}

	public Adapter createContextParamAdapter() {
		return null;
	}

	public Adapter createErrorPageAdapter() {
		return null;
	}

	public Adapter createExceptionTypeErrorPageAdapter() {
		return null;
	}

	public Adapter createErrorCodeErrorPageAdapter() {
		return null;
	}

	public Adapter createWelcomeFileListAdapter() {
		return null;
	}

	public Adapter createWelcomeFileAdapter() {
		return null;
	}

	public Adapter createTagLibRefAdapter() {
		return null;
	}

	public Adapter createTagLibRefTypeAdapter() {
		return null;
	}

	public Adapter createSecurityConstraintAdapter() {
		return null;
	}

	public Adapter createWebResourceCollectionAdapter() {
		return null;
	}

	public Adapter createURLPatternTypeAdapter() {
		return null;
	}

	public Adapter createHTTPMethodTypeAdapter() {
		return null;
	}

	public Adapter createAuthConstraintAdapter() {
		return null;
	}

	public Adapter createUserDataConstraintAdapter() {
		return null;
	}

	public Adapter createLoginConfigAdapter() {
		return null;
	}

	public Adapter createFormLoginConfigAdapter() {
		return null;
	}

	public Adapter createMimeMappingAdapter() {
		return null;
	}

	public Adapter createSessionConfigAdapter() {
		return null;
	}

	public Adapter createServletMappingAdapter() {
		return null;
	}

	public Adapter createServletAdapter() {
		return null;
	}

	public Adapter createWebTypeAdapter() {
		return null;
	}

	public Adapter createServletTypeAdapter() {
		return null;
	}

	public Adapter createJSPTypeAdapter() {
		return null;
	}

	public Adapter createInitParamAdapter() {
		return null;
	}

	public Adapter createFilterAdapter() {
		return null;
	}

	public Adapter createFilterMappingAdapter() {
		return null;
	}

	public Adapter createListenerAdapter() {
		return null;
	}

	public Adapter createRoleNameTypeAdapter() {
		return null;
	}
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean isFactoryForTypeGen(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch the delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WebapplicationSwitch modelSwitch =
		new WebapplicationSwitch() {
			public Object caseWebApp(WebApp object) {
				return createWebAppAdapter();
			}
			public Object caseContextParam(ContextParam object) {
				return createContextParamAdapter();
			}
			public Object caseServlet(Servlet object) {
				return createServletAdapter();
			}
			public Object caseServletMapping(ServletMapping object) {
				return createServletMappingAdapter();
			}
			public Object caseSessionConfig(SessionConfig object) {
				return createSessionConfigAdapter();
			}
			public Object caseMimeMapping(MimeMapping object) {
				return createMimeMappingAdapter();
			}
			public Object caseWelcomeFileList(WelcomeFileList object) {
				return createWelcomeFileListAdapter();
			}
			public Object caseErrorPage(ErrorPage object) {
				return createErrorPageAdapter();
			}
			public Object caseTagLibRef(TagLibRef object) {
				return createTagLibRefAdapter();
			}
			public Object caseTagLibRefType(TagLibRefType object) {
				return createTagLibRefTypeAdapter();
			}
			public Object caseSecurityConstraint(SecurityConstraint object) {
				return createSecurityConstraintAdapter();
			}
			public Object caseWebResourceCollection(WebResourceCollection object) {
				return createWebResourceCollectionAdapter();
			}
			public Object caseAuthConstraint(AuthConstraint object) {
				return createAuthConstraintAdapter();
			}
			public Object caseUserDataConstraint(UserDataConstraint object) {
				return createUserDataConstraintAdapter();
			}
			public Object caseLoginConfig(LoginConfig object) {
				return createLoginConfigAdapter();
			}
			public Object caseFormLoginConfig(FormLoginConfig object) {
				return createFormLoginConfigAdapter();
			}
			public Object caseInitParam(InitParam object) {
				return createInitParamAdapter();
			}
			public Object caseWebType(WebType object) {
				return createWebTypeAdapter();
			}
			public Object caseServletType(ServletType object) {
				return createServletTypeAdapter();
			}
			public Object caseJSPType(JSPType object) {
				return createJSPTypeAdapter();
			}
			public Object caseURLPatternType(URLPatternType object) {
				return createURLPatternTypeAdapter();
			}
			public Object caseRoleNameType(RoleNameType object) {
				return createRoleNameTypeAdapter();
			}
			public Object caseWelcomeFile(WelcomeFile object) {
				return createWelcomeFileAdapter();
			}
			public Object caseExceptionTypeErrorPage(ExceptionTypeErrorPage object) {
				return createExceptionTypeErrorPageAdapter();
			}
			public Object caseErrorCodeErrorPage(ErrorCodeErrorPage object) {
				return createErrorCodeErrorPageAdapter();
			}
			public Object caseFilterMapping(FilterMapping object) {
				return createFilterMappingAdapter();
			}
			public Object caseFilter(Filter object) {
				return createFilterAdapter();
			}
			public Object caseLocalEncodingMappingList(LocalEncodingMappingList object) {
				return createLocalEncodingMappingListAdapter();
			}
			public Object caseLocalEncodingMapping(LocalEncodingMapping object) {
				return createLocalEncodingMappingAdapter();
			}
			public Object caseHTTPMethodType(HTTPMethodType object) {
				return createHTTPMethodTypeAdapter();
			}
			public Object caseDescriptionGroup(DescriptionGroup object) {
				return createDescriptionGroupAdapter();
			}
			public Object caseCompatibilityDescriptionGroup(CompatibilityDescriptionGroup object) {
				return createCompatibilityDescriptionGroupAdapter();
			}
			public Object caseJNDIEnvRefsGroup(JNDIEnvRefsGroup object) {
				return createJNDIEnvRefsGroupAdapter();
			}
			public Object defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createAdapterGen(Notifier target) {
		return (Adapter)modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * By default create methods return null so that we can easily ignore cases.
	 * It's useful to ignore a case when inheritance will catch all the cases anyway.
	 */

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createWebAppAdapterGen() {

		return null;
	}
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createContextParamAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createErrorPageAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createExceptionTypeErrorPageAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createErrorCodeErrorPageAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createWelcomeFileListAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createWelcomeFileAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createTagLibRefAdapterGen() {
		return null;
	}

	public Adapter createTagLibRefTypeAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createSecurityConstraintAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createWebResourceCollectionAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createURLPatternTypeAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createAuthConstraintAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createUserDataConstraintAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createLoginConfigAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createFormLoginConfigAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createMimeMappingAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createSessionConfigAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createServletMappingAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createServletAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createWebTypeAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createServletTypeAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createJSPTypeAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createInitParamAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createFilterAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createFilterMappingAdapterGen() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.internal.webapplication.LocalEncodingMappingList <em>Local Encoding Mapping List</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.internal.webapplication.LocalEncodingMappingList
	 * @generated
	 */
	public Adapter createLocalEncodingMappingListAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.internal.webapplication.LocalEncodingMapping <em>Local Encoding Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.internal.webapplication.LocalEncodingMapping
	 * @generated
	 */
	public Adapter createLocalEncodingMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.internal.common.DescriptionGroup <em>Description Group</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.internal.common.DescriptionGroup
	 * @generated
	 */
	public Adapter createDescriptionGroupAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.internal.common.CompatibilityDescriptionGroup <em>Compatibility Description Group</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.internal.common.CompatibilityDescriptionGroup
	 * @generated
	 */
	public Adapter createCompatibilityDescriptionGroupAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.internal.common.JNDIEnvRefsGroup <em>JNDI Env Refs Group</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.internal.common.JNDIEnvRefsGroup
	 * @generated
	 */
	public Adapter createJNDIEnvRefsGroupAdapter() {
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

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createRoleNameTypeAdapterGen() {
		return null;
	}

} //WebapplicationAdapterFactory



