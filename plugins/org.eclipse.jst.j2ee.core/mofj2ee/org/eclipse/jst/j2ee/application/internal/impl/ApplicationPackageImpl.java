/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
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
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.jem.java.JavaRefPackage;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.ApplicationFactory;
import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.application.ConnectorModule;
import org.eclipse.jst.j2ee.application.EjbModule;
import org.eclipse.jst.j2ee.application.JavaClientModule;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.application.WebModule;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.core.internal.plugin.J2EECorePlugin;
import org.eclipse.jst.j2ee.internal.J2EEInit;


public class ApplicationPackageImpl extends EPackageImpl implements ApplicationPackage {
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
	private volatile static boolean isInited = false;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static ApplicationPackage init() {
		if (isInited) return (ApplicationPackage)EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI);

		// Obtain or create and register package
		ApplicationPackageImpl theApplicationPackage = (ApplicationPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ApplicationPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ApplicationPackageImpl());
		
		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();
		JavaRefPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies

		// Create package meta-data objects
		theApplicationPackage.createPackageContents();

		// Initialize created meta-data
		theApplicationPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theApplicationPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ApplicationPackage.eNS_URI, theApplicationPackage);

		J2EEInit.initEMFModels();
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
		ApplicationPackage theApplicationPackage_1 = (ApplicationPackage)EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI);

		boolean hasLock = false;
		try {
			hasLock = J2EEInit.aquireInitializePackageContentsLock();
		} catch (InterruptedException e) {
			J2EECorePlugin.logError(e);
		}		
		
		try{
			// Add supertypes to classes
			applicationEClass.getESuperTypes().add(theCommonPackage.getCompatibilityDescriptionGroup());
			moduleEClass.getESuperTypes().add(theCommonPackage.getJ2EEEObject());
			webModuleEClass.getESuperTypes().add(theApplicationPackage_1.getModule());
			javaClientModuleEClass.getESuperTypes().add(theApplicationPackage_1.getModule());
			ejbModuleEClass.getESuperTypes().add(theApplicationPackage_1.getModule());
			connectorModuleEClass.getESuperTypes().add(theApplicationPackage_1.getModule());
	
			// Initialize classes and features; add operations and parameters
			initEClass(applicationEClass, Application.class, "Application", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEAttribute(getApplication_Version(), ecorePackage.getEString(), "version", null, 0, 1, Application.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getApplication_SecurityRoles(), theCommonPackage.getSecurityRole(), null, "securityRoles", null, 0, -1, Application.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getApplication_Modules(), theApplicationPackage_1.getModule(), theApplicationPackage_1.getModule_Application(), "modules", null, 1, -1, Application.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(moduleEClass, Module.class, "Module", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEAttribute(getModule_Uri(), ecorePackage.getEString(), "uri", null, 0, 1, Module.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getModule_AltDD(), ecorePackage.getEString(), "altDD", null, 0, 1, Module.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getModule_Application(), theApplicationPackage_1.getApplication(), theApplicationPackage_1.getApplication_Modules(), "application", null, 0, 1, Module.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(webModuleEClass, WebModule.class, "WebModule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEAttribute(getWebModule_ContextRoot(), ecorePackage.getEString(), "contextRoot", null, 0, 1, WebModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(javaClientModuleEClass, JavaClientModule.class, "JavaClientModule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
	
			initEClass(ejbModuleEClass, EjbModule.class, "EjbModule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
	
			initEClass(connectorModuleEClass, ConnectorModule.class, "ConnectorModule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
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

} //ApplicationPackageImpl






