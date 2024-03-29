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
package org.eclipse.jst.j2ee.webservice.wsclient.internal.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.jem.java.JavaRefPackage;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.core.internal.plugin.J2EECorePlugin;
import org.eclipse.jst.j2ee.internal.J2EEInit;
import org.eclipse.jst.j2ee.webservice.wsclient.ComponentScopedRefs;
import org.eclipse.jst.j2ee.webservice.wsclient.Handler;
import org.eclipse.jst.j2ee.webservice.wsclient.PortComponentRef;
import org.eclipse.jst.j2ee.webservice.wsclient.ServiceRef;
import org.eclipse.jst.j2ee.webservice.wsclient.WebServicesClient;
import org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientFactory;
import org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Webservice_clientPackageImpl extends EPackageImpl implements Webservice_clientPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass serviceRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass portComponentRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass handlerEClass = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass webServicesClientEClass = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass componentScopedRefsEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * { @link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private Webservice_clientPackageImpl() {
		super(eNS_URI, Webservice_clientFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private volatile static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this
	 * model, and for any others upon which it depends.  Simple
	 * dependencies are satisfied by calling this method on all
	 * dependent packages before doing anything else.  This method drives
	 * initialization for interdependent packages directly, in parallel
	 * with this package, itself.
	 * <p>Of this package and its interdependencies, all packages which
	 * have not yet been registered by their URI values are first created
	 * and registered.  The packages are then initialized in two steps:
	 * meta-model objects for all of the packages are created before any
	 * are initialized, since one package's meta-model objects may refer to
	 * those of another.
	 * <p>Invocation of this method will not affect any packages that have
	 * already been initialized.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static Webservice_clientPackage init() {
		if (isInited) return (Webservice_clientPackage)EPackage.Registry.INSTANCE.getEPackage(Webservice_clientPackage.eNS_URI);

		// Obtain or create and register package
		Webservice_clientPackageImpl theWebservice_clientPackage = 
			(Webservice_clientPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof
					Webservice_clientPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new
							Webservice_clientPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();
		JavaRefPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theWebservice_clientPackage.createPackageContents();

		// Initialize created meta-data
		theWebservice_clientPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theWebservice_clientPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(Webservice_clientPackage.eNS_URI, theWebservice_clientPackage);

		J2EEInit.initEMFModels();

		return theWebservice_clientPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getServiceRef() {
		return serviceRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getServiceRef_ServiceRefName() {
		return (EAttribute)serviceRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getServiceRef_WsdlFile() {
		return (EAttribute)serviceRefEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getServiceRef_JaxrpcMappingFile() {
		return (EAttribute)serviceRefEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getServiceRef_ServiceInterface() {
		return (EReference)serviceRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getServiceRef_PortComponentRefs() {
		return (EReference)serviceRefEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getServiceRef_Handlers() {
		return (EReference)serviceRefEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getServiceRef_ServiceQname() {
		return (EReference)serviceRefEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPortComponentRef() {
		return portComponentRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPortComponentRef_PortComponentLink() {
		return (EAttribute)portComponentRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPortComponentRef_ServiceEndpointInterface() {
		return (EReference)portComponentRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getHandler() {
		return handlerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHandler_HandlerName() {
		return (EAttribute)handlerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getHandler_SoapHeaders() {
		return (EReference)handlerEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHandler_SoapRoles() {
		return (EAttribute)handlerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHandler_PortNames() {
		return (EAttribute)handlerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getHandler_HandlerClass() {
		return (EReference)handlerEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getHandler_InitParams() {
		return (EReference)handlerEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
	public EClass getWebServicesClient() {
		return webServicesClientEClass;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
	public EReference getWebServicesClient_ComponentScopedRefs() {
		return (EReference)webServicesClientEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
	public EReference getWebServicesClient_ServiceRefs() {
		return (EReference)webServicesClientEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
	public EClass getComponentScopedRefs() {
		return componentScopedRefsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
	public EAttribute getComponentScopedRefs_ComponentName() {
		return (EAttribute)componentScopedRefsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
	public EReference getComponentScopedRefs_ServiceRefs() {
		return (EReference)componentScopedRefsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Webservice_clientFactory getWebservice_clientFactory() {
		return (Webservice_clientFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private volatile boolean isCreated = false;

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
		serviceRefEClass = createEClass(SERVICE_REF);
		createEReference(serviceRefEClass, SERVICE_REF__SERVICE_INTERFACE);
		createEAttribute(serviceRefEClass, SERVICE_REF__SERVICE_REF_NAME);
		createEAttribute(serviceRefEClass, SERVICE_REF__WSDL_FILE);
		createEAttribute(serviceRefEClass, SERVICE_REF__JAXRPC_MAPPING_FILE);
		createEReference(serviceRefEClass, SERVICE_REF__PORT_COMPONENT_REFS);
		createEReference(serviceRefEClass, SERVICE_REF__HANDLERS);
		createEReference(serviceRefEClass, SERVICE_REF__SERVICE_QNAME);

		portComponentRefEClass = createEClass(PORT_COMPONENT_REF);
		createEAttribute(portComponentRefEClass, PORT_COMPONENT_REF__PORT_COMPONENT_LINK);
		createEReference(portComponentRefEClass, PORT_COMPONENT_REF__SERVICE_ENDPOINT_INTERFACE);

		handlerEClass = createEClass(HANDLER);
		createEAttribute(handlerEClass, HANDLER__HANDLER_NAME);
		createEAttribute(handlerEClass, HANDLER__SOAP_ROLES);
		createEAttribute(handlerEClass, HANDLER__PORT_NAMES);
		createEReference(handlerEClass, HANDLER__HANDLER_CLASS);
		createEReference(handlerEClass, HANDLER__INIT_PARAMS);
		createEReference(handlerEClass, HANDLER__SOAP_HEADERS);

		webServicesClientEClass = createEClass(WEB_SERVICES_CLIENT);
		createEReference(webServicesClientEClass, WEB_SERVICES_CLIENT__COMPONENT_SCOPED_REFS);
		createEReference(webServicesClientEClass, WEB_SERVICES_CLIENT__SERVICE_REFS);

		componentScopedRefsEClass = createEClass(COMPONENT_SCOPED_REFS);
		createEAttribute(componentScopedRefsEClass, COMPONENT_SCOPED_REFS__COMPONENT_NAME);
		createEReference(componentScopedRefsEClass, COMPONENT_SCOPED_REFS__SERVICE_REFS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private volatile boolean isInitialized = false;

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
		CommonPackage theCommonPackage = (CommonPackage)EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI);
		JavaRefPackage theJavaRefPackage = (JavaRefPackage)EPackage.Registry.INSTANCE.getEPackage(JavaRefPackage.eNS_URI);

		boolean hasLock = false;
		try {
			hasLock = J2EEInit.aquireInitializePackageContentsLock();
		} catch (InterruptedException e) {
			J2EECorePlugin.logError(e);
		}		
		
		try{
			// Add supertypes to classes
			serviceRefEClass.getESuperTypes().add(theCommonPackage.getCompatibilityDescriptionGroup());
			portComponentRefEClass.getESuperTypes().add(theCommonPackage.getJ2EEEObject());
			handlerEClass.getESuperTypes().add(theCommonPackage.getCompatibilityDescriptionGroup());
			webServicesClientEClass.getESuperTypes().add(theCommonPackage.getJ2EEEObject());
			componentScopedRefsEClass.getESuperTypes().add(theCommonPackage.getJ2EEEObject());
	
			// Initialize classes and features; add operations and parameters
			initEClass(serviceRefEClass, ServiceRef.class, "ServiceRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEReference(getServiceRef_ServiceInterface(), theJavaRefPackage.getJavaClass(), null, "serviceInterface", null, 1, 1, ServiceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getServiceRef_ServiceRefName(), ecorePackage.getEString(), "serviceRefName", null, 0, 1, ServiceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getServiceRef_WsdlFile(), ecorePackage.getEString(), "wsdlFile", null, 0, 1, ServiceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getServiceRef_JaxrpcMappingFile(), ecorePackage.getEString(), "jaxrpcMappingFile", null, 0, 1, ServiceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getServiceRef_PortComponentRefs(), this.getPortComponentRef(), null, "portComponentRefs", null, 0, -1, ServiceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getServiceRef_Handlers(), this.getHandler(), null, "handlers", null, 0, -1, ServiceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getServiceRef_ServiceQname(), theCommonPackage.getQName(), null, "serviceQname", null, 1, 1, ServiceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(portComponentRefEClass, PortComponentRef.class, "PortComponentRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEAttribute(getPortComponentRef_PortComponentLink(), ecorePackage.getEString(), "portComponentLink", null, 0, 1, PortComponentRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getPortComponentRef_ServiceEndpointInterface(), theJavaRefPackage.getJavaClass(), null, "serviceEndpointInterface", null, 1, 1, PortComponentRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(handlerEClass, Handler.class, "Handler", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEAttribute(getHandler_HandlerName(), ecorePackage.getEString(), "handlerName", null, 0, 1, Handler.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getHandler_SoapRoles(), ecorePackage.getEString(), "soapRoles", null, 0, -1, Handler.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getHandler_PortNames(), ecorePackage.getEString(), "portNames", null, 0, -1, Handler.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getHandler_HandlerClass(), theJavaRefPackage.getJavaClass(), null, "handlerClass", null, 1, 1, Handler.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getHandler_InitParams(), theCommonPackage.getParamValue(), null, "initParams", null, 0, -1, Handler.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getHandler_SoapHeaders(), theCommonPackage.getQName(), null, "soapHeaders", null, 0, -1, Handler.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(webServicesClientEClass, WebServicesClient.class, "WebServicesClient", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEReference(getWebServicesClient_ComponentScopedRefs(), this.getComponentScopedRefs(), null, "componentScopedRefs", null, 0, -1, WebServicesClient.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getWebServicesClient_ServiceRefs(), this.getServiceRef(), null, "serviceRefs", null, 0, -1, WebServicesClient.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(componentScopedRefsEClass, ComponentScopedRefs.class, "ComponentScopedRefs", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEAttribute(getComponentScopedRefs_ComponentName(), ecorePackage.getEString(), "componentName", null, 0, 1, ComponentScopedRefs.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getComponentScopedRefs_ServiceRefs(), this.getServiceRef(), null, "serviceRefs", null, 1, -1, ComponentScopedRefs.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			// Create resource
			createResource(eNS_URI);
		}finally{
			if( hasLock )
				J2EEInit.releaseInitializePackageContentsLock();
		}
	}

	@Override
	public void freeze()
	{
		// since EClassImpl.freeze() does a clear() on all of the subClasses, we need to protect initializePackageContents() against it.
		boolean hasLock = false;
		try {
			hasLock = J2EEInit.aquireInitializePackageContentsLock();
		} catch (InterruptedException e) {
			J2EECorePlugin.logError(e);
		}
		finally {
			super.freeze();
			if( hasLock )
				J2EEInit.releaseInitializePackageContentsLock();
		}
	}

} //Webservice_clientPackageImpl
