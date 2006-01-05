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
package org.eclipse.jst.j2ee.client.internal.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.impl.EcorePackageImpl;
import org.eclipse.jem.java.JavaRefPackage;
import org.eclipse.jem.java.internal.impl.JavaRefPackageImpl;
import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.application.internal.impl.ApplicationPackageImpl;
import org.eclipse.jst.j2ee.client.ApplicationClient;
import org.eclipse.jst.j2ee.client.ClientFactory;
import org.eclipse.jst.j2ee.client.ClientPackage;
import org.eclipse.jst.j2ee.client.ResAuthApplicationType;
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


public class ClientPackageImpl extends EPackageImpl implements ClientPackage, EPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass applicationClientEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum resAuthApplicationTypeEEnum = null;

		   /**
		 * @generated This field/method will be replaced during code generation.
		 */
	private ClientPackageImpl() {
		super(eNS_URI, ClientFactory.eINSTANCE);
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
	public static ClientPackage init() {
		if (isInited) return (ClientPackage)EPackage.Registry.INSTANCE.getEPackage(ClientPackage.eNS_URI);

		// Obtain or create and register package
		ClientPackageImpl theClientPackage = (ClientPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof ClientPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new ClientPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackageImpl.init();
		JavaRefPackageImpl.init();

		// Obtain or create and register interdependencies
		ApplicationPackageImpl theApplicationPackage = (ApplicationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI) instanceof ApplicationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI) : ApplicationPackage.eINSTANCE);
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
		theClientPackage.createPackageContents();
		theApplicationPackage.createPackageContents();
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
		theClientPackage.initializePackageContents();
		theApplicationPackage.initializePackageContents();
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
		theClientPackage.freeze();

		return theClientPackage;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getApplicationClient() {
		return applicationClientEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getApplicationClient_Version() {
		return (EAttribute)applicationClientEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getApplicationClient_ResourceRefs() {
		return (EReference)applicationClientEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getApplicationClient_EnvironmentProps() {
		return (EReference)applicationClientEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getApplicationClient_EjbReferences() {
		return (EReference)applicationClientEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getApplicationClient_ResourceEnvRefs() {
		return (EReference)applicationClientEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getApplicationClient_CallbackHandler() {
		return (EReference)applicationClientEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getApplicationClient_ServiceRefs() {
		return (EReference)applicationClientEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getApplicationClient_MessageDestinationRefs() {
		return (EReference)applicationClientEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getApplicationClient_MessageDestinations() {
		return (EReference)applicationClientEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getResAuthApplicationType() {
		return resAuthApplicationTypeEEnum;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public ClientFactory getClientFactory() {
		return (ClientFactory)getEFactoryInstance();
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
		applicationClientEClass = createEClass(APPLICATION_CLIENT);
		createEAttribute(applicationClientEClass, APPLICATION_CLIENT__VERSION);
		createEReference(applicationClientEClass, APPLICATION_CLIENT__RESOURCE_REFS);
		createEReference(applicationClientEClass, APPLICATION_CLIENT__ENVIRONMENT_PROPS);
		createEReference(applicationClientEClass, APPLICATION_CLIENT__EJB_REFERENCES);
		createEReference(applicationClientEClass, APPLICATION_CLIENT__RESOURCE_ENV_REFS);
		createEReference(applicationClientEClass, APPLICATION_CLIENT__CALLBACK_HANDLER);
		createEReference(applicationClientEClass, APPLICATION_CLIENT__SERVICE_REFS);
		createEReference(applicationClientEClass, APPLICATION_CLIENT__MESSAGE_DESTINATION_REFS);
		createEReference(applicationClientEClass, APPLICATION_CLIENT__MESSAGE_DESTINATIONS);

		// Create enums
		resAuthApplicationTypeEEnum = createEEnum(RES_AUTH_APPLICATION_TYPE);
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
		JavaRefPackageImpl theJavaRefPackage = (JavaRefPackageImpl)EPackage.Registry.INSTANCE.getEPackage(JavaRefPackage.eNS_URI);
		Webservice_clientPackageImpl theWebservice_clientPackage = (Webservice_clientPackageImpl)EPackage.Registry.INSTANCE.getEPackage(Webservice_clientPackage.eNS_URI);

		// Add supertypes to classes
		applicationClientEClass.getESuperTypes().add(theCommonPackage.getCompatibilityDescriptionGroup());

		// Initialize classes and features; add operations and parameters
		initEClass(applicationClientEClass, ApplicationClient.class, "ApplicationClient", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getApplicationClient_Version(), ecorePackage.getEString(), "version", null, 0, 1, ApplicationClient.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getApplicationClient_ResourceRefs(), theCommonPackage.getResourceRef(), null, "resourceRefs", null, 0, -1, ApplicationClient.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getApplicationClient_EnvironmentProps(), theCommonPackage.getEnvEntry(), null, "environmentProps", null, 0, -1, ApplicationClient.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getApplicationClient_EjbReferences(), theCommonPackage.getEjbRef(), null, "ejbReferences", null, 0, -1, ApplicationClient.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getApplicationClient_ResourceEnvRefs(), theCommonPackage.getResourceEnvRef(), null, "resourceEnvRefs", null, 0, -1, ApplicationClient.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getApplicationClient_CallbackHandler(), theJavaRefPackage.getJavaClass(), null, "callbackHandler", null, 0, 1, ApplicationClient.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getApplicationClient_ServiceRefs(), theWebservice_clientPackage.getServiceRef(), null, "serviceRefs", null, 0, -1, ApplicationClient.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getApplicationClient_MessageDestinationRefs(), theCommonPackage.getMessageDestinationRef(), null, "messageDestinationRefs", null, 0, -1, ApplicationClient.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getApplicationClient_MessageDestinations(), theCommonPackage.getMessageDestination(), null, "messageDestinations", null, 0, -1, ApplicationClient.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(resAuthApplicationTypeEEnum, ResAuthApplicationType.class, "ResAuthApplicationType");
		addEEnumLiteral(resAuthApplicationTypeEEnum, ResAuthApplicationType.APPLICATION_LITERAL);
		addEEnumLiteral(resAuthApplicationTypeEEnum, ResAuthApplicationType.CONTAINER_LITERAL);

		// Create resource
		createResource(eNS_URI);
	}

} //ClientPackageImpl






