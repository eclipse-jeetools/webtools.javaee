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
package org.eclipse.jst.j2ee.internal.application.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.impl.EcorePackageImpl;
import org.eclipse.jem.java.impl.JavaRefPackageImpl;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.ApplicationFactory;
import org.eclipse.jst.j2ee.application.ConnectorModule;
import org.eclipse.jst.j2ee.application.EjbModule;
import org.eclipse.jst.j2ee.application.JavaClientModule;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.application.WebModule;
import org.eclipse.jst.j2ee.internal.application.ApplicationPackage;
import org.eclipse.jst.j2ee.internal.client.ClientPackage;
import org.eclipse.jst.j2ee.internal.client.impl.ClientPackageImpl;
import org.eclipse.jst.j2ee.internal.common.CommonPackage;
import org.eclipse.jst.j2ee.internal.common.impl.CommonPackageImpl;
import org.eclipse.jst.j2ee.internal.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.ejb.impl.EjbPackageImpl;
import org.eclipse.jst.j2ee.internal.jca.JcaPackage;
import org.eclipse.jst.j2ee.internal.jca.impl.JcaPackageImpl;
import org.eclipse.jst.j2ee.internal.jsp.JspPackage;
import org.eclipse.jst.j2ee.internal.jsp.impl.JspPackageImpl;
import org.eclipse.jst.j2ee.internal.taglib.TaglibPackage;
import org.eclipse.jst.j2ee.internal.taglib.impl.TaglibPackageImpl;
import org.eclipse.jst.j2ee.internal.webapplication.WebapplicationPackage;
import org.eclipse.jst.j2ee.internal.webapplication.impl.WebapplicationPackageImpl;
import org.eclipse.jst.j2ee.webservice.internal.wsclient.Webservice_clientPackage;
import org.eclipse.jst.j2ee.webservice.internal.wsclient.impl.Webservice_clientPackageImpl;
import org.eclipse.jst.j2ee.webservice.internal.wscommon.WscommonPackage;
import org.eclipse.jst.j2ee.webservice.internal.wscommon.impl.WscommonPackageImpl;
import org.eclipse.jst.j2ee.webservice.internal.wsdd.WsddPackage;
import org.eclipse.jst.j2ee.webservice.internal.wsdd.impl.WsddPackageImpl;


public class ApplicationPackageImpl extends EPackageImpl implements ApplicationPackage, EPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass applicationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass moduleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass webModuleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass javaClientModuleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ejbModuleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass connectorModuleEClass = null;

		   /**
		 * @generated This field/method will be replaced during code generation.
		 */
	private ApplicationPackageImpl() {
		super(eNS_URI, ApplicationFactory.eINSTANCE);
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
	public static ApplicationPackage init() {
		if (isInited) return (ApplicationPackage)EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI);

		// Obtain or create and register package.
		ApplicationPackageImpl theApplicationPackage = (ApplicationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new ApplicationPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackageImpl.init();
		JavaRefPackageImpl.init();

		// Obtain or create and register interdependencies
		EjbPackageImpl theEjbPackage = (EjbPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(EjbPackage.eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(EjbPackage.eNS_URI) : EjbPackageImpl.eINSTANCE);
		ClientPackageImpl theClientPackage = (ClientPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ClientPackage.eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(ClientPackage.eNS_URI) : ClientPackageImpl.eINSTANCE);
		CommonPackageImpl theCommonPackage = (CommonPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI) : CommonPackageImpl.eINSTANCE);
		JcaPackageImpl theJcaPackage = (JcaPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(JcaPackage.eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(JcaPackage.eNS_URI) : JcaPackageImpl.eINSTANCE);
		Webservice_clientPackageImpl theWebservice_clientPackage = (Webservice_clientPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Webservice_clientPackage.eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(Webservice_clientPackage.eNS_URI) : Webservice_clientPackageImpl.eINSTANCE);
		WscommonPackageImpl theWscommonPackage = (WscommonPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WscommonPackage.eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(WscommonPackage.eNS_URI) : WscommonPackageImpl.eINSTANCE);
		WsddPackageImpl theWsddPackage = (WsddPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WsddPackage.eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(WsddPackage.eNS_URI) : WsddPackageImpl.eINSTANCE);
		WebapplicationPackageImpl theWebapplicationPackage = (WebapplicationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WebapplicationPackage.eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(WebapplicationPackage.eNS_URI) : WebapplicationPackageImpl.eINSTANCE);
		JspPackageImpl theJspPackage = (JspPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(JspPackage.eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(JspPackage.eNS_URI) : JspPackageImpl.eINSTANCE);
		TaglibPackageImpl theTaglibPackage = (TaglibPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(TaglibPackage.eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(TaglibPackage.eNS_URI) : TaglibPackageImpl.eINSTANCE);

		// Step 1: create meta-model objects
		theApplicationPackage.createPackageContents();
		theEjbPackage.createPackageContents();
		theClientPackage.createPackageContents();
		theCommonPackage.createPackageContents();
		theJcaPackage.createPackageContents();
		theWebservice_clientPackage.createPackageContents();
		theWscommonPackage.createPackageContents();
		theWsddPackage.createPackageContents();
		theWebapplicationPackage.createPackageContents();
		theJspPackage.createPackageContents();
		theTaglibPackage.createPackageContents();

		// Step 2: complete initialization
		theApplicationPackage.initializePackageContents();
		theEjbPackage.initializePackageContents();
		theClientPackage.initializePackageContents();
		theCommonPackage.initializePackageContents();
		theJcaPackage.initializePackageContents();
		theWebservice_clientPackage.initializePackageContents();
		theWscommonPackage.initializePackageContents();
		theWsddPackage.initializePackageContents();
		theWebapplicationPackage.initializePackageContents();
		theJspPackage.initializePackageContents();
		theTaglibPackage.initializePackageContents();

		return theApplicationPackage;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getApplication() {
		return applicationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getApplication_Version() {
		return (EAttribute)applicationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getApplication_SecurityRoles() {
		return (EReference)applicationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getApplication_Modules() {
		return (EReference)applicationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getModule() {
		return moduleEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getModule_Uri() {
		return (EAttribute)moduleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getModule_AltDD() {
		return (EAttribute)moduleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getModule_Application() {
		return (EReference)moduleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getWebModule() {
		return webModuleEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getWebModule_ContextRoot() {
		return (EAttribute)webModuleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getJavaClientModule() {
		return javaClientModuleEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getEjbModule() {
		return ejbModuleEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getConnectorModule() {
		return connectorModuleEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public ApplicationFactory getApplicationFactory() {
		return (ApplicationFactory)getEFactoryInstance();
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
		applicationEClass = createEClass(APPLICATION);
		createEAttribute(applicationEClass, APPLICATION__VERSION);
		createEReference(applicationEClass, APPLICATION__SECURITY_ROLES);
		createEReference(applicationEClass, APPLICATION__MODULES);

		moduleEClass = createEClass(MODULE);
		createEAttribute(moduleEClass, MODULE__URI);
		createEAttribute(moduleEClass, MODULE__ALT_DD);
		createEReference(moduleEClass, MODULE__APPLICATION);

		webModuleEClass = createEClass(WEB_MODULE);
		createEAttribute(webModuleEClass, WEB_MODULE__CONTEXT_ROOT);

		javaClientModuleEClass = createEClass(JAVA_CLIENT_MODULE);

		ejbModuleEClass = createEClass(EJB_MODULE);

		connectorModuleEClass = createEClass(CONNECTOR_MODULE);
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

		// Add supertypes to classes
		applicationEClass.getESuperTypes().add(theCommonPackage.getCompatibilityDescriptionGroup());
		webModuleEClass.getESuperTypes().add(this.getModule());
		javaClientModuleEClass.getESuperTypes().add(this.getModule());
		ejbModuleEClass.getESuperTypes().add(this.getModule());
		connectorModuleEClass.getESuperTypes().add(this.getModule());

		// Initialize classes and features; add operations and parameters
		initEClass(applicationEClass, Application.class, "Application", !IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$
		initEAttribute(getApplication_Version(), ecorePackage.getEString(), "version", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEReference(getApplication_SecurityRoles(), theCommonPackage.getSecurityRole(), null, "securityRoles", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEReference(getApplication_Modules(), this.getModule(), this.getModule_Application(), "modules", null, 1, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$

		initEClass(moduleEClass, Module.class, "Module", !IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$
		initEAttribute(getModule_Uri(), ecorePackage.getEString(), "uri", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEAttribute(getModule_AltDD(), ecorePackage.getEString(), "altDD", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEReference(getModule_Application(), this.getApplication(), this.getApplication_Modules(), "application", null, 0, 1, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$

		initEClass(webModuleEClass, WebModule.class, "WebModule", !IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$
		initEAttribute(getWebModule_ContextRoot(), ecorePackage.getEString(), "contextRoot", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$

		initEClass(javaClientModuleEClass, JavaClientModule.class, "JavaClientModule", !IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$

		initEClass(ejbModuleEClass, EjbModule.class, "EjbModule", !IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$

		initEClass(connectorModuleEClass, ConnectorModule.class, "ConnectorModule", !IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$

		// Create resource
		createResource(eNS_URI);
	}
} //ApplicationPackageImpl






