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
package org.eclipse.jst.j2ee.application.internal.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.impl.EcorePackageImpl;
import org.eclipse.jem.java.internal.impl.JavaRefPackageImpl;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.ApplicationFactory;
import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.application.ConnectorModule;
import org.eclipse.jst.j2ee.application.EjbModule;
import org.eclipse.jst.j2ee.application.JavaClientModule;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.application.WebModule;
import org.eclipse.jst.j2ee.client.ClientPackage;
import org.eclipse.jst.j2ee.client.internal.impl.ClientPackageImpl;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.internal.impl.EjbPackageImpl;
import org.eclipse.jst.j2ee.jca.JcaPackage;
import org.eclipse.jst.j2ee.jca.internal.impl.JcaPackageImpl;
import org.eclipse.jst.j2ee.jsp.JspPackage;
import org.eclipse.jst.j2ee.jsp.internal.impl.JspPackageImpl;
import org.eclipse.jst.j2ee.taglib.internal.TaglibPackage;
import org.eclipse.jst.j2ee.taglib.internal.impl.TaglibPackageImpl;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl;
import org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientPackage;
import org.eclipse.jst.j2ee.webservice.wsclient.internal.impl.Webservice_clientPackageImpl;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonPackage;
import org.eclipse.jst.j2ee.webservice.wscommon.internal.impl.WscommonPackageImpl;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage;
import org.eclipse.jst.j2ee.webservice.wsdd.internal.impl.WsddPackageImpl;


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

		// Obtain or create and register package
		ApplicationPackageImpl theApplicationPackage = (ApplicationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof ApplicationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new ApplicationPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackageImpl.init();
		JavaRefPackageImpl.init();

		// Obtain or create and register interdependencies
		ClientPackageImpl theClientPackage = (ClientPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ClientPackage.eNS_URI) instanceof ClientPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ClientPackage.eNS_URI) : ClientPackage.eINSTANCE);
		ApplicationPackageImpl theApplicationPackage_1 = (ApplicationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI) instanceof ApplicationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI) : ApplicationPackage.eINSTANCE);
		CommonPackageImpl theCommonPackage = (CommonPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI) instanceof CommonPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI) : CommonPackage.eINSTANCE);
		EjbPackageImpl theEjbPackage = (EjbPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(EjbPackage.eNS_URI) instanceof EjbPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(EjbPackage.eNS_URI) : EjbPackage.eINSTANCE);
		TaglibPackageImpl theTaglibPackage = (TaglibPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(TaglibPackage.eNS_URI) instanceof TaglibPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(TaglibPackage.eNS_URI) : TaglibPackage.eINSTANCE);
		WebapplicationPackageImpl theWebapplicationPackage = (WebapplicationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WebapplicationPackage.eNS_URI) instanceof WebapplicationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(WebapplicationPackage.eNS_URI) : WebapplicationPackage.eINSTANCE);
		JcaPackageImpl theJcaPackage = (JcaPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(JcaPackage.eNS_URI) instanceof JcaPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(JcaPackage.eNS_URI) : JcaPackage.eINSTANCE);
		Webservice_clientPackageImpl theWebservice_clientPackage = (Webservice_clientPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Webservice_clientPackage.eNS_URI) instanceof Webservice_clientPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Webservice_clientPackage.eNS_URI) : Webservice_clientPackage.eINSTANCE);
		JspPackageImpl theJspPackage = (JspPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(JspPackage.eNS_URI) instanceof JspPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(JspPackage.eNS_URI) : JspPackage.eINSTANCE);
		WscommonPackageImpl theWscommonPackage = (WscommonPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WscommonPackage.eNS_URI) instanceof WscommonPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(WscommonPackage.eNS_URI) : WscommonPackage.eINSTANCE);
		WsddPackageImpl theWsddPackage = (WsddPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WsddPackage.eNS_URI) instanceof WsddPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(WsddPackage.eNS_URI) : WsddPackage.eINSTANCE);

		// Create package meta-data objects
		theApplicationPackage.createPackageContents();
		theClientPackage.createPackageContents();
		theApplicationPackage_1.createPackageContents();
		theCommonPackage.createPackageContents();
		theEjbPackage.createPackageContents();
		theTaglibPackage.createPackageContents();
		theWebapplicationPackage.createPackageContents();
		theJcaPackage.createPackageContents();
		theWebservice_clientPackage.createPackageContents();
		theJspPackage.createPackageContents();
		theWscommonPackage.createPackageContents();
		theWsddPackage.createPackageContents();

		// Initialize created meta-data
		theApplicationPackage.initializePackageContents();
		theClientPackage.initializePackageContents();
		theApplicationPackage_1.initializePackageContents();
		theCommonPackage.initializePackageContents();
		theEjbPackage.initializePackageContents();
		theTaglibPackage.initializePackageContents();
		theWebapplicationPackage.initializePackageContents();
		theJcaPackage.initializePackageContents();
		theWebservice_clientPackage.initializePackageContents();
		theJspPackage.initializePackageContents();
		theWscommonPackage.initializePackageContents();
		theWsddPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theApplicationPackage.freeze();

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
		ApplicationPackageImpl theApplicationPackage_1 = (ApplicationPackageImpl)EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI);

		// Add supertypes to classes
		applicationEClass.getESuperTypes().add(theCommonPackage.getCompatibilityDescriptionGroup());
		webModuleEClass.getESuperTypes().add(theApplicationPackage_1.getModule());
		javaClientModuleEClass.getESuperTypes().add(theApplicationPackage_1.getModule());
		ejbModuleEClass.getESuperTypes().add(theApplicationPackage_1.getModule());
		connectorModuleEClass.getESuperTypes().add(theApplicationPackage_1.getModule());

		// Initialize classes and features; add operations and parameters
		initEClass(applicationEClass, Application.class, "Application", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getApplication_Version(), ecorePackage.getEString(), "version", null, 0, 1, Application.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getApplication_SecurityRoles(), theCommonPackage.getSecurityRole(), null, "securityRoles", null, 0, -1, Application.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getApplication_Modules(), theApplicationPackage_1.getModule(), theApplicationPackage_1.getModule_Application(), "modules", null, 1, -1, Application.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(moduleEClass, Module.class, "Module", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getModule_Uri(), ecorePackage.getEString(), "uri", null, 0, 1, Module.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModule_AltDD(), ecorePackage.getEString(), "altDD", null, 0, 1, Module.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModule_Application(), theApplicationPackage_1.getApplication(), theApplicationPackage_1.getApplication_Modules(), "application", null, 0, 1, Module.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(webModuleEClass, WebModule.class, "WebModule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWebModule_ContextRoot(), ecorePackage.getEString(), "contextRoot", null, 0, 1, WebModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(javaClientModuleEClass, JavaClientModule.class, "JavaClientModule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(ejbModuleEClass, EjbModule.class, "EjbModule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(connectorModuleEClass, ConnectorModule.class, "ConnectorModule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
	}

} //ApplicationPackageImpl






