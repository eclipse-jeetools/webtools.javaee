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
package org.eclipse.jst.j2ee.webapplication.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.impl.EcorePackageImpl;
import org.eclipse.jem.java.JavaRefPackage;
import org.eclipse.jem.java.impl.JavaRefPackageImpl;
import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.application.impl.ApplicationPackageImpl;
import org.eclipse.jst.j2ee.client.ClientPackage;
import org.eclipse.jst.j2ee.client.impl.ClientPackageImpl;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.impl.CommonPackageImpl;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.impl.EjbPackageImpl;
import org.eclipse.jst.j2ee.jca.JcaPackage;
import org.eclipse.jst.j2ee.jca.impl.JcaPackageImpl;
import org.eclipse.jst.j2ee.jsp.JspPackage;
import org.eclipse.jst.j2ee.jsp.impl.JspPackageImpl;
import org.eclipse.jst.j2ee.taglib.TaglibPackage;
import org.eclipse.jst.j2ee.taglib.impl.TaglibPackageImpl;
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
import org.eclipse.jst.j2ee.webapplication.WebType;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.jst.j2ee.webapplication.WelcomeFile;
import org.eclipse.jst.j2ee.webapplication.WelcomeFileList;
import org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientPackage;
import org.eclipse.jst.j2ee.webservice.wsclient.impl.Webservice_clientPackageImpl;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonPackage;
import org.eclipse.jst.j2ee.webservice.wscommon.impl.WscommonPackageImpl;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage;
import org.eclipse.jst.j2ee.webservice.wsdd.impl.WsddPackageImpl;


public class WebapplicationPackageImpl extends EPackageImpl implements WebapplicationPackage, EPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass webAppEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass contextParamEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass servletEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass servletMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sessionConfigEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mimeMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass welcomeFileListEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass errorPageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tagLibRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass securityConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass webResourceCollectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass authConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass userDataConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass loginConfigEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass formLoginConfigEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass initParamEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass webTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass servletTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass jspTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass urlPatternTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass roleNameTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass welcomeFileEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass exceptionTypeErrorPageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass errorCodeErrorPageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass filterMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass filterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass localEncodingMappingListEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass localEncodingMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass httpMethodTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum resAuthServletTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum httpMethodTypeEnumEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum transportGuaranteeTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum dispatcherTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum authMethodKindEEnum = null;

		   /**
		 * @generated This field/method will be replaced during code generation.
		 */
	private WebapplicationPackageImpl() {
		super(eNS_URI, WebapplicationFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static WebapplicationPackage init() {
		if (isInited) return (WebapplicationPackage)EPackage.Registry.INSTANCE.getEPackage(WebapplicationPackage.eNS_URI);

		// Obtain or create and register package.
		WebapplicationPackageImpl theWebapplicationPackage = (WebapplicationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new WebapplicationPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackageImpl.init();
		JavaRefPackageImpl.init();

		// Obtain or create and register interdependencies
		EjbPackageImpl theEjbPackage = (EjbPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(EjbPackage.eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(EjbPackage.eNS_URI) : EjbPackage.eINSTANCE);
		ClientPackageImpl theClientPackage = (ClientPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ClientPackage.eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(ClientPackage.eNS_URI) : ClientPackage.eINSTANCE);
		CommonPackageImpl theCommonPackage = (CommonPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI) : CommonPackage.eINSTANCE);
		JcaPackageImpl theJcaPackage = (JcaPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(JcaPackage.eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(JcaPackage.eNS_URI) : JcaPackage.eINSTANCE);
		Webservice_clientPackageImpl theWebservice_clientPackage = (Webservice_clientPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Webservice_clientPackage.eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(Webservice_clientPackage.eNS_URI) : Webservice_clientPackage.eINSTANCE);
		WscommonPackageImpl theWscommonPackage = (WscommonPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WscommonPackage.eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(WscommonPackage.eNS_URI) : WscommonPackage.eINSTANCE);
		WsddPackageImpl theWsddPackage = (WsddPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WsddPackage.eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(WsddPackage.eNS_URI) : WsddPackage.eINSTANCE);
		ApplicationPackageImpl theApplicationPackage = (ApplicationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI) : ApplicationPackage.eINSTANCE);
		JspPackageImpl theJspPackage = (JspPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(JspPackage.eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(JspPackage.eNS_URI) : JspPackage.eINSTANCE);
		TaglibPackageImpl theTaglibPackage = (TaglibPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(TaglibPackage.eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(TaglibPackage.eNS_URI) : TaglibPackage.eINSTANCE);

		// Step 1: create meta-model objects
		theWebapplicationPackage.createPackageContents();
		theEjbPackage.createPackageContents();
		theClientPackage.createPackageContents();
		theCommonPackage.createPackageContents();
		theJcaPackage.createPackageContents();
		theWebservice_clientPackage.createPackageContents();
		theWscommonPackage.createPackageContents();
		theWsddPackage.createPackageContents();
		theApplicationPackage.createPackageContents();
		theJspPackage.createPackageContents();
		theTaglibPackage.createPackageContents();

		// Step 2: complete initialization
		theWebapplicationPackage.initializePackageContents();
		theEjbPackage.initializePackageContents();
		theClientPackage.initializePackageContents();
		theCommonPackage.initializePackageContents();
		theJcaPackage.initializePackageContents();
		theWebservice_clientPackage.initializePackageContents();
		theWscommonPackage.initializePackageContents();
		theWsddPackage.initializePackageContents();
		theApplicationPackage.initializePackageContents();
		theJspPackage.initializePackageContents();
		theTaglibPackage.initializePackageContents();

		return theWebapplicationPackage;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getWebApp() {
		return webAppEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getWebApp_Distributable() {
		return (EAttribute)webAppEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWebApp_Version() {
		return (EAttribute)webAppEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getWebApp_Contexts() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getWebApp_ErrorPages() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getWebApp_FileList() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getWebApp_TagLibs() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getWebApp_Constraints() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getWebApp_LoginConfig() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getWebApp_MimeMappings() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getWebApp_SessionConfig() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getWebApp_ServletMappings() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getWebApp_Servlets() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getWebApp_SecurityRoles() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getWebApp_Filters() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getWebApp_FilterMappings() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getWebApp_Listeners() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebApp_ContextParams() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebApp_JspConfig() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebApp_MessageDestinations() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebApp_LocalEncodingMappingList() {
		return (EReference)webAppEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getContextParam() {
		return contextParamEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getContextParam_ParamName() {
		return (EAttribute)contextParamEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getContextParam_ParamValue() {
		return (EAttribute)contextParamEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getContextParam_Description() {
		return (EAttribute)contextParamEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getContextParam_WebApp() {
		return (EReference)contextParamEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getErrorPage() {
		return errorPageEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getErrorPage_Location() {
		return (EAttribute)errorPageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getErrorPage_WebApp() {
		return (EReference)errorPageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getExceptionTypeErrorPage() {
		return exceptionTypeErrorPageEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getExceptionTypeErrorPage_ExceptionType() {
		return (EReference)exceptionTypeErrorPageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getErrorCodeErrorPage() {
		return errorCodeErrorPageEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getErrorCodeErrorPage_ErrorCode() {
		return (EAttribute)errorCodeErrorPageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getWelcomeFileList() {
		return welcomeFileListEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getWelcomeFileList_WebApp() {
		return (EReference)welcomeFileListEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getWelcomeFileList_File() {
		return (EReference)welcomeFileListEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getWelcomeFile() {
		return welcomeFileEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getWelcomeFile_WelcomeFile() {
		return (EAttribute)welcomeFileEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getWelcomeFile_FileList() {
		return (EReference)welcomeFileEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getTagLibRef() {
		return tagLibRefEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getTagLibRef_TaglibURI() {
		return (EAttribute)tagLibRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getTagLibRef_TaglibLocation() {
		return (EAttribute)tagLibRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTagLibRef_WebApp() {
		return (EReference)tagLibRefEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getSecurityConstraint() {
		return securityConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSecurityConstraint_DisplayName() {
		return (EAttribute)securityConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getSecurityConstraint_WebApp() {
		return (EReference)securityConstraintEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getSecurityConstraint_WebResourceCollections() {
		return (EReference)securityConstraintEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getSecurityConstraint_AuthConstraint() {
		return (EReference)securityConstraintEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getSecurityConstraint_UserDataConstraint() {
		return (EReference)securityConstraintEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSecurityConstraint_DisplayNames() {
		return (EReference)securityConstraintEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getWebResourceCollection() {
		return webResourceCollectionEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getWebResourceCollection_WebResourceName() {
		return (EAttribute)webResourceCollectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getWebResourceCollection_Description() {
		return (EAttribute)webResourceCollectionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWebResourceCollection_UrlPattern() {
		return (EAttribute)webResourceCollectionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWebResourceCollection_HttpMethod() {
		return (EAttribute)webResourceCollectionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getWebResourceCollection_SecConstraint() {
		return (EReference)webResourceCollectionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getWebResourceCollection_URLs() {
		return (EReference)webResourceCollectionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebResourceCollection_Descriptions() {
		return (EReference)webResourceCollectionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebResourceCollection_HTTPs() {
		return (EReference)webResourceCollectionEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getURLPatternType() {
		return urlPatternTypeEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getURLPatternType_UrlPattern() {
		return (EAttribute)urlPatternTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getURLPatternType_ResCollection() {
		return (EReference)urlPatternTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getHTTPMethodType() {
		return httpMethodTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHTTPMethodType_HttpMethod() {
		return (EAttribute)httpMethodTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getAuthConstraint() {
		return authConstraintEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getAuthConstraint_Description() {
		return (EAttribute)authConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getAuthConstraint_Roles() {
		return (EAttribute)authConstraintEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getAuthConstraint_SecConstraint() {
		return (EReference)authConstraintEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAuthConstraint_Descriptions() {
		return (EReference)authConstraintEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getUserDataConstraint() {
		return userDataConstraintEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getUserDataConstraint_Description() {
		return (EAttribute)userDataConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getUserDataConstraint_TransportGuarantee() {
		return (EAttribute)userDataConstraintEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getUserDataConstraint_SecConstraint() {
		return (EReference)userDataConstraintEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUserDataConstraint_Descriptions() {
		return (EReference)userDataConstraintEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getLoginConfig() {
		return loginConfigEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getLoginConfig_AuthMethod() {
		return (EAttribute)loginConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getLoginConfig_RealmName() {
		return (EAttribute)loginConfigEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLoginConfig_AuthorizationMethod() {
		return (EAttribute)loginConfigEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getLoginConfig_WebApp() {
		return (EReference)loginConfigEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getLoginConfig_FormLoginConfig() {
		return (EReference)loginConfigEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getFormLoginConfig() {
		return formLoginConfigEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getFormLoginConfig_FormLoginPage() {
		return (EAttribute)formLoginConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getFormLoginConfig_FormErrorPage() {
		return (EAttribute)formLoginConfigEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getFormLoginConfig_LoginConfig() {
		return (EReference)formLoginConfigEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getMimeMapping() {
		return mimeMappingEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getMimeMapping_Extension() {
		return (EAttribute)mimeMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getMimeMapping_MimeType() {
		return (EAttribute)mimeMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getMimeMapping_WebApp() {
		return (EReference)mimeMappingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getSessionConfig() {
		return sessionConfigEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getSessionConfig_SessionTimeout() {
		return (EAttribute)sessionConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getSessionConfig_WebApp() {
		return (EReference)sessionConfigEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getServletMapping() {
		return servletMappingEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getServletMapping_UrlPattern() {
		return (EAttribute)servletMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServletMapping_Name() {
		return (EAttribute)servletMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getServletMapping_WebApp() {
		return (EReference)servletMappingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getServletMapping_Servlet() {
		return (EReference)servletMappingEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getServlet() {
		return servletEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getServlet_ServletName() {
		return (EAttribute)servletEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getServlet_LoadOnStartup() {
		return (EAttribute)servletEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getServlet_WebApp() {
		return (EReference)servletEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getServlet_WebType() {
		return (EReference)servletEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getServlet_Params() {
		return (EReference)servletEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getServlet_SecurityRoleRefs() {
		return (EReference)servletEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getServlet_RunAs() {
		return (EReference)servletEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServlet_InitParams() {
		return (EReference)servletEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getWebType() {
		return webTypeEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getServletType() {
		return servletTypeEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getServletType_ClassName() {
		return (EAttribute)servletTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getJSPType() {
		return jspTypeEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getJSPType_JspFile() {
		return (EAttribute)jspTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getInitParam() {
		return initParamEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getInitParam_ParamName() {
		return (EAttribute)initParamEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getInitParam_ParamValue() {
		return (EAttribute)initParamEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getInitParam_Description() {
		return (EAttribute)initParamEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getFilter() {
		return filterEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getFilter_Name() {
		return (EAttribute)filterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getFilter_InitParams() {
		return (EReference)filterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getFilter_FilterClass() {
		return (EReference)filterEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFilter_InitParamValues() {
		return (EReference)filterEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getFilterMapping() {
		return filterMappingEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getFilterMapping_UrlPattern() {
		return (EAttribute)filterMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFilterMapping_DispatcherType() {
		return (EAttribute)filterMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getFilterMapping_Filter() {
		return (EReference)filterMappingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getFilterMapping_Servlet() {
		return (EReference)filterMappingEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLocalEncodingMappingList() {
		return localEncodingMappingListEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLocalEncodingMappingList_LocalEncodingMappings() {
		return (EReference)localEncodingMappingListEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLocalEncodingMapping() {
		return localEncodingMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLocalEncodingMapping_Locale() {
		return (EAttribute)localEncodingMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLocalEncodingMapping_Encoding() {
		return (EAttribute)localEncodingMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getResAuthServletType() {
		return resAuthServletTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getHTTPMethodTypeEnum() {
		return httpMethodTypeEnumEEnum;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getRoleNameType() {
		return roleNameTypeEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getRoleNameType_RoleName() {
		return (EAttribute)roleNameTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EEnum getTransportGuaranteeType() {
		return transportGuaranteeTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getDispatcherType() {
		return dispatcherTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getAuthMethodKind() {
		return authMethodKindEEnum;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public WebapplicationFactory getWebapplicationFactory() {
		return (WebapplicationFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		webAppEClass = createEClass(WEB_APP);
		createEAttribute(webAppEClass, WEB_APP__DISTRIBUTABLE);
		createEAttribute(webAppEClass, WEB_APP__VERSION);
		createEReference(webAppEClass, WEB_APP__CONTEXTS);
		createEReference(webAppEClass, WEB_APP__ERROR_PAGES);
		createEReference(webAppEClass, WEB_APP__FILE_LIST);
		createEReference(webAppEClass, WEB_APP__TAG_LIBS);
		createEReference(webAppEClass, WEB_APP__CONSTRAINTS);
		createEReference(webAppEClass, WEB_APP__LOGIN_CONFIG);
		createEReference(webAppEClass, WEB_APP__MIME_MAPPINGS);
		createEReference(webAppEClass, WEB_APP__SESSION_CONFIG);
		createEReference(webAppEClass, WEB_APP__SERVLET_MAPPINGS);
		createEReference(webAppEClass, WEB_APP__SERVLETS);
		createEReference(webAppEClass, WEB_APP__SECURITY_ROLES);
		createEReference(webAppEClass, WEB_APP__FILTERS);
		createEReference(webAppEClass, WEB_APP__FILTER_MAPPINGS);
		createEReference(webAppEClass, WEB_APP__LISTENERS);
		createEReference(webAppEClass, WEB_APP__CONTEXT_PARAMS);
		createEReference(webAppEClass, WEB_APP__JSP_CONFIG);
		createEReference(webAppEClass, WEB_APP__MESSAGE_DESTINATIONS);
		createEReference(webAppEClass, WEB_APP__LOCAL_ENCODING_MAPPING_LIST);

		contextParamEClass = createEClass(CONTEXT_PARAM);
		createEAttribute(contextParamEClass, CONTEXT_PARAM__PARAM_NAME);
		createEAttribute(contextParamEClass, CONTEXT_PARAM__PARAM_VALUE);
		createEAttribute(contextParamEClass, CONTEXT_PARAM__DESCRIPTION);
		createEReference(contextParamEClass, CONTEXT_PARAM__WEB_APP);

		servletEClass = createEClass(SERVLET);
		createEAttribute(servletEClass, SERVLET__SERVLET_NAME);
		createEAttribute(servletEClass, SERVLET__LOAD_ON_STARTUP);
		createEReference(servletEClass, SERVLET__WEB_APP);
		createEReference(servletEClass, SERVLET__WEB_TYPE);
		createEReference(servletEClass, SERVLET__PARAMS);
		createEReference(servletEClass, SERVLET__SECURITY_ROLE_REFS);
		createEReference(servletEClass, SERVLET__RUN_AS);
		createEReference(servletEClass, SERVLET__INIT_PARAMS);

		servletMappingEClass = createEClass(SERVLET_MAPPING);
		createEAttribute(servletMappingEClass, SERVLET_MAPPING__URL_PATTERN);
		createEAttribute(servletMappingEClass, SERVLET_MAPPING__NAME);
		createEReference(servletMappingEClass, SERVLET_MAPPING__WEB_APP);
		createEReference(servletMappingEClass, SERVLET_MAPPING__SERVLET);

		sessionConfigEClass = createEClass(SESSION_CONFIG);
		createEAttribute(sessionConfigEClass, SESSION_CONFIG__SESSION_TIMEOUT);
		createEReference(sessionConfigEClass, SESSION_CONFIG__WEB_APP);

		mimeMappingEClass = createEClass(MIME_MAPPING);
		createEAttribute(mimeMappingEClass, MIME_MAPPING__EXTENSION);
		createEAttribute(mimeMappingEClass, MIME_MAPPING__MIME_TYPE);
		createEReference(mimeMappingEClass, MIME_MAPPING__WEB_APP);

		welcomeFileListEClass = createEClass(WELCOME_FILE_LIST);
		createEReference(welcomeFileListEClass, WELCOME_FILE_LIST__WEB_APP);
		createEReference(welcomeFileListEClass, WELCOME_FILE_LIST__FILE);

		errorPageEClass = createEClass(ERROR_PAGE);
		createEAttribute(errorPageEClass, ERROR_PAGE__LOCATION);
		createEReference(errorPageEClass, ERROR_PAGE__WEB_APP);

		tagLibRefEClass = createEClass(TAG_LIB_REF);
		createEAttribute(tagLibRefEClass, TAG_LIB_REF__TAGLIB_URI);
		createEAttribute(tagLibRefEClass, TAG_LIB_REF__TAGLIB_LOCATION);
		createEReference(tagLibRefEClass, TAG_LIB_REF__WEB_APP);

		securityConstraintEClass = createEClass(SECURITY_CONSTRAINT);
		createEAttribute(securityConstraintEClass, SECURITY_CONSTRAINT__DISPLAY_NAME);
		createEReference(securityConstraintEClass, SECURITY_CONSTRAINT__WEB_APP);
		createEReference(securityConstraintEClass, SECURITY_CONSTRAINT__WEB_RESOURCE_COLLECTIONS);
		createEReference(securityConstraintEClass, SECURITY_CONSTRAINT__AUTH_CONSTRAINT);
		createEReference(securityConstraintEClass, SECURITY_CONSTRAINT__USER_DATA_CONSTRAINT);
		createEReference(securityConstraintEClass, SECURITY_CONSTRAINT__DISPLAY_NAMES);

		webResourceCollectionEClass = createEClass(WEB_RESOURCE_COLLECTION);
		createEAttribute(webResourceCollectionEClass, WEB_RESOURCE_COLLECTION__WEB_RESOURCE_NAME);
		createEAttribute(webResourceCollectionEClass, WEB_RESOURCE_COLLECTION__DESCRIPTION);
		createEAttribute(webResourceCollectionEClass, WEB_RESOURCE_COLLECTION__URL_PATTERN);
		createEAttribute(webResourceCollectionEClass, WEB_RESOURCE_COLLECTION__HTTP_METHOD);
		createEReference(webResourceCollectionEClass, WEB_RESOURCE_COLLECTION__SEC_CONSTRAINT);
		createEReference(webResourceCollectionEClass, WEB_RESOURCE_COLLECTION__UR_LS);
		createEReference(webResourceCollectionEClass, WEB_RESOURCE_COLLECTION__DESCRIPTIONS);
		createEReference(webResourceCollectionEClass, WEB_RESOURCE_COLLECTION__HTT_PS);

		authConstraintEClass = createEClass(AUTH_CONSTRAINT);
		createEAttribute(authConstraintEClass, AUTH_CONSTRAINT__DESCRIPTION);
		createEAttribute(authConstraintEClass, AUTH_CONSTRAINT__ROLES);
		createEReference(authConstraintEClass, AUTH_CONSTRAINT__SEC_CONSTRAINT);
		createEReference(authConstraintEClass, AUTH_CONSTRAINT__DESCRIPTIONS);

		userDataConstraintEClass = createEClass(USER_DATA_CONSTRAINT);
		createEAttribute(userDataConstraintEClass, USER_DATA_CONSTRAINT__DESCRIPTION);
		createEAttribute(userDataConstraintEClass, USER_DATA_CONSTRAINT__TRANSPORT_GUARANTEE);
		createEReference(userDataConstraintEClass, USER_DATA_CONSTRAINT__SEC_CONSTRAINT);
		createEReference(userDataConstraintEClass, USER_DATA_CONSTRAINT__DESCRIPTIONS);

		loginConfigEClass = createEClass(LOGIN_CONFIG);
		createEAttribute(loginConfigEClass, LOGIN_CONFIG__AUTH_METHOD);
		createEAttribute(loginConfigEClass, LOGIN_CONFIG__REALM_NAME);
		createEAttribute(loginConfigEClass, LOGIN_CONFIG__AUTHORIZATION_METHOD);
		createEReference(loginConfigEClass, LOGIN_CONFIG__WEB_APP);
		createEReference(loginConfigEClass, LOGIN_CONFIG__FORM_LOGIN_CONFIG);

		formLoginConfigEClass = createEClass(FORM_LOGIN_CONFIG);
		createEAttribute(formLoginConfigEClass, FORM_LOGIN_CONFIG__FORM_LOGIN_PAGE);
		createEAttribute(formLoginConfigEClass, FORM_LOGIN_CONFIG__FORM_ERROR_PAGE);
		createEReference(formLoginConfigEClass, FORM_LOGIN_CONFIG__LOGIN_CONFIG);

		initParamEClass = createEClass(INIT_PARAM);
		createEAttribute(initParamEClass, INIT_PARAM__PARAM_NAME);
		createEAttribute(initParamEClass, INIT_PARAM__PARAM_VALUE);
		createEAttribute(initParamEClass, INIT_PARAM__DESCRIPTION);

		webTypeEClass = createEClass(WEB_TYPE);

		servletTypeEClass = createEClass(SERVLET_TYPE);
		createEAttribute(servletTypeEClass, SERVLET_TYPE__CLASS_NAME);

		jspTypeEClass = createEClass(JSP_TYPE);
		createEAttribute(jspTypeEClass, JSP_TYPE__JSP_FILE);

		urlPatternTypeEClass = createEClass(URL_PATTERN_TYPE);
		createEAttribute(urlPatternTypeEClass, URL_PATTERN_TYPE__URL_PATTERN);
		createEReference(urlPatternTypeEClass, URL_PATTERN_TYPE__RES_COLLECTION);

		roleNameTypeEClass = createEClass(ROLE_NAME_TYPE);
		createEAttribute(roleNameTypeEClass, ROLE_NAME_TYPE__ROLE_NAME);

		welcomeFileEClass = createEClass(WELCOME_FILE);
		createEAttribute(welcomeFileEClass, WELCOME_FILE__WELCOME_FILE);
		createEReference(welcomeFileEClass, WELCOME_FILE__FILE_LIST);

		exceptionTypeErrorPageEClass = createEClass(EXCEPTION_TYPE_ERROR_PAGE);
		createEReference(exceptionTypeErrorPageEClass, EXCEPTION_TYPE_ERROR_PAGE__EXCEPTION_TYPE);

		errorCodeErrorPageEClass = createEClass(ERROR_CODE_ERROR_PAGE);
		createEAttribute(errorCodeErrorPageEClass, ERROR_CODE_ERROR_PAGE__ERROR_CODE);

		filterMappingEClass = createEClass(FILTER_MAPPING);
		createEAttribute(filterMappingEClass, FILTER_MAPPING__URL_PATTERN);
		createEAttribute(filterMappingEClass, FILTER_MAPPING__DISPATCHER_TYPE);
		createEReference(filterMappingEClass, FILTER_MAPPING__FILTER);
		createEReference(filterMappingEClass, FILTER_MAPPING__SERVLET);

		filterEClass = createEClass(FILTER);
		createEAttribute(filterEClass, FILTER__NAME);
		createEReference(filterEClass, FILTER__INIT_PARAMS);
		createEReference(filterEClass, FILTER__FILTER_CLASS);
		createEReference(filterEClass, FILTER__INIT_PARAM_VALUES);

		localEncodingMappingListEClass = createEClass(LOCAL_ENCODING_MAPPING_LIST);
		createEReference(localEncodingMappingListEClass, LOCAL_ENCODING_MAPPING_LIST__LOCAL_ENCODING_MAPPINGS);

		localEncodingMappingEClass = createEClass(LOCAL_ENCODING_MAPPING);
		createEAttribute(localEncodingMappingEClass, LOCAL_ENCODING_MAPPING__LOCALE);
		createEAttribute(localEncodingMappingEClass, LOCAL_ENCODING_MAPPING__ENCODING);

		httpMethodTypeEClass = createEClass(HTTP_METHOD_TYPE);
		createEAttribute(httpMethodTypeEClass, HTTP_METHOD_TYPE__HTTP_METHOD);

		// Create enums
		resAuthServletTypeEEnum = createEEnum(RES_AUTH_SERVLET_TYPE);
		httpMethodTypeEnumEEnum = createEEnum(HTTP_METHOD_TYPE_ENUM);
		transportGuaranteeTypeEEnum = createEEnum(TRANSPORT_GUARANTEE_TYPE);
		dispatcherTypeEEnum = createEEnum(DISPATCHER_TYPE);
		authMethodKindEEnum = createEEnum(AUTH_METHOD_KIND);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		CommonPackageImpl theCommonPackage = (CommonPackageImpl)EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI);
		JspPackageImpl theJspPackage = (JspPackageImpl)EPackage.Registry.INSTANCE.getEPackage(JspPackage.eNS_URI);
		JavaRefPackageImpl theJavaRefPackage = (JavaRefPackageImpl)EPackage.Registry.INSTANCE.getEPackage(JavaRefPackage.eNS_URI);

		// Add supertypes to classes
		webAppEClass.getESuperTypes().add(theCommonPackage.getJNDIEnvRefsGroup());
		servletEClass.getESuperTypes().add(theCommonPackage.getCompatibilityDescriptionGroup());
		servletTypeEClass.getESuperTypes().add(this.getWebType());
		jspTypeEClass.getESuperTypes().add(this.getWebType());
		exceptionTypeErrorPageEClass.getESuperTypes().add(this.getErrorPage());
		errorCodeErrorPageEClass.getESuperTypes().add(this.getErrorPage());
		filterEClass.getESuperTypes().add(theCommonPackage.getCompatibilityDescriptionGroup());

		// Initialize classes and features; add operations and parameters
		initEClass(webAppEClass, WebApp.class, "WebApp", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
		initEAttribute(getWebApp_Distributable(), ecorePackage.getEBoolean(), "distributable", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEAttribute(getWebApp_Version(), ecorePackage.getEString(), "version", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getWebApp_Contexts(), this.getContextParam(), this.getContextParam_WebApp(), "contexts", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getWebApp_ErrorPages(), this.getErrorPage(), this.getErrorPage_WebApp(), "errorPages", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getWebApp_FileList(), this.getWelcomeFileList(), this.getWelcomeFileList_WebApp(), "fileList", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getWebApp_TagLibs(), this.getTagLibRef(), this.getTagLibRef_WebApp(), "tagLibs", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getWebApp_Constraints(), this.getSecurityConstraint(), this.getSecurityConstraint_WebApp(), "constraints", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getWebApp_LoginConfig(), this.getLoginConfig(), this.getLoginConfig_WebApp(), "loginConfig", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getWebApp_MimeMappings(), this.getMimeMapping(), this.getMimeMapping_WebApp(), "mimeMappings", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getWebApp_SessionConfig(), this.getSessionConfig(), this.getSessionConfig_WebApp(), "sessionConfig", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getWebApp_ServletMappings(), this.getServletMapping(), this.getServletMapping_WebApp(), "servletMappings", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getWebApp_Servlets(), this.getServlet(), this.getServlet_WebApp(), "servlets", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getWebApp_SecurityRoles(), theCommonPackage.getSecurityRole(), null, "securityRoles", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getWebApp_Filters(), this.getFilter(), null, "filters", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getWebApp_FilterMappings(), this.getFilterMapping(), null, "filterMappings", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getWebApp_Listeners(), theCommonPackage.getListener(), null, "listeners", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getWebApp_ContextParams(), theCommonPackage.getParamValue(), null, "contextParams", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getWebApp_JspConfig(), theJspPackage.getJSPConfig(), null, "jspConfig", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getWebApp_MessageDestinations(), theCommonPackage.getMessageDestination(), null, "messageDestinations", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getWebApp_LocalEncodingMappingList(), this.getLocalEncodingMappingList(), null, "localEncodingMappingList", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$

		initEClass(contextParamEClass, ContextParam.class, "ContextParam", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
		initEAttribute(getContextParam_ParamName(), ecorePackage.getEString(), "paramName", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEAttribute(getContextParam_ParamValue(), ecorePackage.getEString(), "paramValue", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEAttribute(getContextParam_Description(), ecorePackage.getEString(), "description", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getContextParam_WebApp(), this.getWebApp(), this.getWebApp_Contexts(), "webApp", null, 0, 1, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$

		initEClass(servletEClass, Servlet.class, "Servlet", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
		initEAttribute(getServlet_ServletName(), ecorePackage.getEString(), "servletName", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEAttribute(getServlet_LoadOnStartup(), ecorePackage.getEIntegerObject(), "loadOnStartup", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getServlet_WebApp(), this.getWebApp(), this.getWebApp_Servlets(), "webApp", null, 0, 1, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getServlet_WebType(), this.getWebType(), null, "webType", null, 1, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getServlet_Params(), this.getInitParam(), null, "params", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getServlet_SecurityRoleRefs(), theCommonPackage.getSecurityRoleRef(), null, "securityRoleRefs", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getServlet_RunAs(), theCommonPackage.getRunAsSpecifiedIdentity(), null, "runAs", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getServlet_InitParams(), theCommonPackage.getParamValue(), null, "initParams", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$

		initEClass(servletMappingEClass, ServletMapping.class, "ServletMapping", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
		initEAttribute(getServletMapping_UrlPattern(), ecorePackage.getEString(), "urlPattern", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEAttribute(getServletMapping_Name(), ecorePackage.getEString(), "name", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getServletMapping_WebApp(), this.getWebApp(), this.getWebApp_ServletMappings(), "webApp", null, 0, 1, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getServletMapping_Servlet(), this.getServlet(), null, "servlet", null, 1, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$

		initEClass(sessionConfigEClass, SessionConfig.class, "SessionConfig", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
		initEAttribute(getSessionConfig_SessionTimeout(), ecorePackage.getEInt(), "sessionTimeout", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getSessionConfig_WebApp(), this.getWebApp(), this.getWebApp_SessionConfig(), "webApp", null, 0, 1, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$

		initEClass(mimeMappingEClass, MimeMapping.class, "MimeMapping", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
		initEAttribute(getMimeMapping_Extension(), ecorePackage.getEString(), "extension", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEAttribute(getMimeMapping_MimeType(), ecorePackage.getEString(), "mimeType", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getMimeMapping_WebApp(), this.getWebApp(), this.getWebApp_MimeMappings(), "webApp", null, 0, 1, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$

		initEClass(welcomeFileListEClass, WelcomeFileList.class, "WelcomeFileList", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
		initEReference(getWelcomeFileList_WebApp(), this.getWebApp(), this.getWebApp_FileList(), "webApp", null, 0, 1, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getWelcomeFileList_File(), this.getWelcomeFile(), this.getWelcomeFile_FileList(), "file", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$

		initEClass(errorPageEClass, ErrorPage.class, "ErrorPage", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
		initEAttribute(getErrorPage_Location(), ecorePackage.getEString(), "location", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getErrorPage_WebApp(), this.getWebApp(), this.getWebApp_ErrorPages(), "webApp", null, 0, 1, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$

		initEClass(tagLibRefEClass, TagLibRef.class, "TagLibRef", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
		initEAttribute(getTagLibRef_TaglibURI(), ecorePackage.getEString(), "taglibURI", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEAttribute(getTagLibRef_TaglibLocation(), ecorePackage.getEString(), "taglibLocation", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getTagLibRef_WebApp(), this.getWebApp(), this.getWebApp_TagLibs(), "webApp", null, 0, 1, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$

		initEClass(securityConstraintEClass, SecurityConstraint.class, "SecurityConstraint", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
		initEAttribute(getSecurityConstraint_DisplayName(), ecorePackage.getEString(), "displayName", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getSecurityConstraint_WebApp(), this.getWebApp(), this.getWebApp_Constraints(), "webApp", null, 0, 1, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getSecurityConstraint_WebResourceCollections(), this.getWebResourceCollection(), this.getWebResourceCollection_SecConstraint(), "webResourceCollections", null, 1, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getSecurityConstraint_AuthConstraint(), this.getAuthConstraint(), this.getAuthConstraint_SecConstraint(), "authConstraint", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getSecurityConstraint_UserDataConstraint(), this.getUserDataConstraint(), this.getUserDataConstraint_SecConstraint(), "userDataConstraint", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getSecurityConstraint_DisplayNames(), theCommonPackage.getDisplayName(), null, "displayNames", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$

		initEClass(webResourceCollectionEClass, WebResourceCollection.class, "WebResourceCollection", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
		initEAttribute(getWebResourceCollection_WebResourceName(), ecorePackage.getEString(), "webResourceName", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEAttribute(getWebResourceCollection_Description(), ecorePackage.getEString(), "description", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEAttribute(getWebResourceCollection_UrlPattern(), ecorePackage.getEString(), "urlPattern", null, 1, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEAttribute(getWebResourceCollection_HttpMethod(), this.getHTTPMethodTypeEnum(), "httpMethod", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getWebResourceCollection_SecConstraint(), this.getSecurityConstraint(), this.getSecurityConstraint_WebResourceCollections(), "secConstraint", null, 0, 1, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getWebResourceCollection_URLs(), this.getURLPatternType(), this.getURLPatternType_ResCollection(), "URLs", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getWebResourceCollection_Descriptions(), theCommonPackage.getDescription(), null, "descriptions", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getWebResourceCollection_HTTPs(), this.getHTTPMethodType(), null, "HTTPs", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$

		initEClass(authConstraintEClass, AuthConstraint.class, "AuthConstraint", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
		initEAttribute(getAuthConstraint_Description(), ecorePackage.getEString(), "description", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEAttribute(getAuthConstraint_Roles(), ecorePackage.getEString(), "roles", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getAuthConstraint_SecConstraint(), this.getSecurityConstraint(), this.getSecurityConstraint_AuthConstraint(), "secConstraint", null, 0, 1, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getAuthConstraint_Descriptions(), theCommonPackage.getDescription(), null, "descriptions", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$

		initEClass(userDataConstraintEClass, UserDataConstraint.class, "UserDataConstraint", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
		initEAttribute(getUserDataConstraint_Description(), ecorePackage.getEString(), "description", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEAttribute(getUserDataConstraint_TransportGuarantee(), this.getTransportGuaranteeType(), "transportGuarantee", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getUserDataConstraint_SecConstraint(), this.getSecurityConstraint(), this.getSecurityConstraint_UserDataConstraint(), "secConstraint", null, 0, 1, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getUserDataConstraint_Descriptions(), theCommonPackage.getDescription(), null, "descriptions", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$

		initEClass(loginConfigEClass, LoginConfig.class, "LoginConfig", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
		initEAttribute(getLoginConfig_AuthMethod(), this.getAuthMethodKind(), "authMethod", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEAttribute(getLoginConfig_RealmName(), ecorePackage.getEString(), "realmName", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEAttribute(getLoginConfig_AuthorizationMethod(), ecorePackage.getEString(), "authorizationMethod", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getLoginConfig_WebApp(), this.getWebApp(), this.getWebApp_LoginConfig(), "webApp", null, 0, 1, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getLoginConfig_FormLoginConfig(), this.getFormLoginConfig(), this.getFormLoginConfig_LoginConfig(), "formLoginConfig", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$

		initEClass(formLoginConfigEClass, FormLoginConfig.class, "FormLoginConfig", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
		initEAttribute(getFormLoginConfig_FormLoginPage(), ecorePackage.getEString(), "formLoginPage", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEAttribute(getFormLoginConfig_FormErrorPage(), ecorePackage.getEString(), "formErrorPage", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getFormLoginConfig_LoginConfig(), this.getLoginConfig(), this.getLoginConfig_FormLoginConfig(), "loginConfig", null, 0, 1, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$

		initEClass(initParamEClass, InitParam.class, "InitParam", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
		initEAttribute(getInitParam_ParamName(), ecorePackage.getEString(), "paramName", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEAttribute(getInitParam_ParamValue(), ecorePackage.getEString(), "paramValue", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEAttribute(getInitParam_Description(), ecorePackage.getEString(), "description", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$

		initEClass(webTypeEClass, WebType.class, "WebType", IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$

		initEClass(servletTypeEClass, ServletType.class, "ServletType", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
		initEAttribute(getServletType_ClassName(), ecorePackage.getEString(), "className", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$

		initEClass(jspTypeEClass, JSPType.class, "JSPType", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
		initEAttribute(getJSPType_JspFile(), ecorePackage.getEString(), "jspFile", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$

		initEClass(urlPatternTypeEClass, URLPatternType.class, "URLPatternType", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
		initEAttribute(getURLPatternType_UrlPattern(), ecorePackage.getEString(), "urlPattern", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getURLPatternType_ResCollection(), this.getWebResourceCollection(), this.getWebResourceCollection_URLs(), "resCollection", null, 0, 1, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$

		initEClass(roleNameTypeEClass, RoleNameType.class, "RoleNameType", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
		initEAttribute(getRoleNameType_RoleName(), ecorePackage.getEString(), "roleName", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$

		initEClass(welcomeFileEClass, WelcomeFile.class, "WelcomeFile", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
		initEAttribute(getWelcomeFile_WelcomeFile(), ecorePackage.getEString(), "welcomeFile", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getWelcomeFile_FileList(), this.getWelcomeFileList(), this.getWelcomeFileList_File(), "fileList", null, 0, 1, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$

		initEClass(exceptionTypeErrorPageEClass, ExceptionTypeErrorPage.class, "ExceptionTypeErrorPage", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
		initEReference(getExceptionTypeErrorPage_ExceptionType(), theJavaRefPackage.getJavaClass(), null, "exceptionType", null, 1, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$

		initEClass(errorCodeErrorPageEClass, ErrorCodeErrorPage.class, "ErrorCodeErrorPage", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
		initEAttribute(getErrorCodeErrorPage_ErrorCode(), ecorePackage.getEString(), "errorCode", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$

		initEClass(filterMappingEClass, FilterMapping.class, "FilterMapping", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
		initEAttribute(getFilterMapping_UrlPattern(), ecorePackage.getEString(), "urlPattern", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEAttribute(getFilterMapping_DispatcherType(), this.getDispatcherType(), "dispatcherType", null, 0, 4, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getFilterMapping_Filter(), this.getFilter(), null, "filter", null, 1, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getFilterMapping_Servlet(), this.getServlet(), null, "servlet", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$

		initEClass(filterEClass, Filter.class, "Filter", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
		initEAttribute(getFilter_Name(), ecorePackage.getEString(), "name", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getFilter_InitParams(), this.getInitParam(), null, "initParams", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getFilter_FilterClass(), theJavaRefPackage.getJavaClass(), null, "filterClass", null, 1, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEReference(getFilter_InitParamValues(), theCommonPackage.getParamValue(), null, "initParamValues", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$

		initEClass(localEncodingMappingListEClass, LocalEncodingMappingList.class, "LocalEncodingMappingList", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
		initEReference(getLocalEncodingMappingList_LocalEncodingMappings(), this.getLocalEncodingMapping(), null, "localEncodingMappings", null, 1, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$

		initEClass(localEncodingMappingEClass, LocalEncodingMapping.class, "LocalEncodingMapping", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
		initEAttribute(getLocalEncodingMapping_Locale(), ecorePackage.getEString(), "locale", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$
		initEAttribute(getLocalEncodingMapping_Encoding(), ecorePackage.getEString(), "encoding", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$

		initEClass(httpMethodTypeEClass, HTTPMethodType.class, "HTTPMethodType", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
		initEAttribute(getHTTPMethodType_HttpMethod(), ecorePackage.getEString(), "httpMethod", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);//$NON-NLS-1$

		// Initialize enums and add enum literals
		initEEnum(resAuthServletTypeEEnum, ResAuthServletType.class, "ResAuthServletType");//$NON-NLS-1$
		addEEnumLiteral(resAuthServletTypeEEnum, ResAuthServletType.CONTAINER_LITERAL);
		addEEnumLiteral(resAuthServletTypeEEnum, ResAuthServletType.SERVLET_LITERAL);

		initEEnum(httpMethodTypeEnumEEnum, HTTPMethodTypeEnum.class, "HTTPMethodTypeEnum");//$NON-NLS-1$
		addEEnumLiteral(httpMethodTypeEnumEEnum, HTTPMethodTypeEnum.GET_LITERAL);
		addEEnumLiteral(httpMethodTypeEnumEEnum, HTTPMethodTypeEnum.POST_LITERAL);
		addEEnumLiteral(httpMethodTypeEnumEEnum, HTTPMethodTypeEnum.PUT_LITERAL);
		addEEnumLiteral(httpMethodTypeEnumEEnum, HTTPMethodTypeEnum.DELETE_LITERAL);
		addEEnumLiteral(httpMethodTypeEnumEEnum, HTTPMethodTypeEnum.HEAD_LITERAL);
		addEEnumLiteral(httpMethodTypeEnumEEnum, HTTPMethodTypeEnum.OPTIONS_LITERAL);
		addEEnumLiteral(httpMethodTypeEnumEEnum, HTTPMethodTypeEnum.TRACE_LITERAL);

		initEEnum(transportGuaranteeTypeEEnum, TransportGuaranteeType.class, "TransportGuaranteeType");//$NON-NLS-1$
		addEEnumLiteral(transportGuaranteeTypeEEnum, TransportGuaranteeType.NONE_LITERAL);
		addEEnumLiteral(transportGuaranteeTypeEEnum, TransportGuaranteeType.INTEGRAL_LITERAL);
		addEEnumLiteral(transportGuaranteeTypeEEnum, TransportGuaranteeType.CONFIDENTIAL_LITERAL);

		initEEnum(dispatcherTypeEEnum, DispatcherType.class, "DispatcherType");//$NON-NLS-1$
		addEEnumLiteral(dispatcherTypeEEnum, DispatcherType.FORWARD_LITERAL);
		addEEnumLiteral(dispatcherTypeEEnum, DispatcherType.INCLUDE_LITERAL);
		addEEnumLiteral(dispatcherTypeEEnum, DispatcherType.REQUEST_LITERAL);
		addEEnumLiteral(dispatcherTypeEEnum, DispatcherType.ERROR_LITERAL);

		initEEnum(authMethodKindEEnum, AuthMethodKind.class, "AuthMethodKind");//$NON-NLS-1$
		addEEnumLiteral(authMethodKindEEnum, AuthMethodKind.UNSPECIFIED_LITERAL);
		addEEnumLiteral(authMethodKindEEnum, AuthMethodKind.BASIC_LITERAL);
		addEEnumLiteral(authMethodKindEEnum, AuthMethodKind.DIGEST_LITERAL);
		addEEnumLiteral(authMethodKindEEnum, AuthMethodKind.FORM_LITERAL);
		addEEnumLiteral(authMethodKindEEnum, AuthMethodKind.CLIENT_CERT_LITERAL);

		// Create resource
		createResource(eNS_URI);
	}
} //WebapplicationPackageImpl







