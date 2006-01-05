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
package org.eclipse.jst.j2ee.commonarchivecore.internal.impl;


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.impl.EcorePackageImpl;
import org.eclipse.jem.java.JavaRefPackage;
import org.eclipse.jem.java.internal.impl.JavaRefPackageImpl;
import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.application.internal.impl.ApplicationPackageImpl;
import org.eclipse.jst.j2ee.client.ClientPackage;
import org.eclipse.jst.j2ee.client.internal.impl.ClientPackageImpl;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ApplicationClientFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ClientModuleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ConnectorModuleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Container;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EJBJarFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EJBModuleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.RARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ReadOnlyDirectory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WebModuleRef;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseconfigPackage;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseconfigPackageImpl;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.internal.impl.EjbPackageImpl;
import org.eclipse.jst.j2ee.jca.JcaPackage;
import org.eclipse.jst.j2ee.jca.internal.impl.JcaPackageImpl;
import org.eclipse.jst.j2ee.jsp.JspPackage;
import org.eclipse.jst.j2ee.jsp.internal.impl.JspPackageImpl;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl;
import org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientPackage;
import org.eclipse.jst.j2ee.webservice.wsclient.internal.impl.Webservice_clientPackageImpl;


/**
 * @lastgen class CommonarchivePackageImpl extends EPackageImpl implements CommonarchivePackage,
 *          EPackage {}
 */
public class CommonarchivePackageImpl extends EPackageImpl implements CommonarchivePackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fileEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass archiveEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ejbJarFileEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass warFileEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass earFileEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass applicationClientFileEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass moduleFileEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass containerEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass readOnlyDirectoryEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rarFileEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass moduleRefEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ejbModuleRefEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass webModuleRefEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass clientModuleRefEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass connectorModuleRefEClass = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	private CommonarchivePackageImpl() {
		super(eNS_URI, CommonarchiveFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static CommonarchivePackage init() {
		if (isInited) return (CommonarchivePackage)EPackage.Registry.INSTANCE.getEPackage(CommonarchivePackage.eNS_URI);

		// Obtain or create and register package
		CommonarchivePackageImpl theCommonarchivePackage = (CommonarchivePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof CommonarchivePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new CommonarchivePackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackageImpl.init();

		// Obtain or create and register interdependencies
		ApplicationPackageImpl theApplicationPackage = (ApplicationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI) instanceof ApplicationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI) : ApplicationPackage.eINSTANCE);
		ClientPackageImpl theClientPackage = (ClientPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ClientPackage.eNS_URI) instanceof ClientPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ClientPackage.eNS_URI) : ClientPackage.eINSTANCE);
		EjbPackageImpl theEjbPackage = (EjbPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(EjbPackage.eNS_URI) instanceof EjbPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(EjbPackage.eNS_URI) : EjbPackage.eINSTANCE);
		WebapplicationPackageImpl theWebapplicationPackage = (WebapplicationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WebapplicationPackage.eNS_URI) instanceof WebapplicationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(WebapplicationPackage.eNS_URI) : WebapplicationPackage.eINSTANCE);
		LooseconfigPackageImpl theLooseconfigPackage = (LooseconfigPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(LooseconfigPackage.eNS_URI) instanceof LooseconfigPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(LooseconfigPackage.eNS_URI) : LooseconfigPackage.eINSTANCE);
		JavaRefPackageImpl theJavaRefPackage = (JavaRefPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(JavaRefPackage.eNS_URI) instanceof JavaRefPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(JavaRefPackage.eNS_URI) : JavaRefPackage.eINSTANCE);
		CommonPackageImpl theCommonPackage = (CommonPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI) instanceof CommonPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI) : CommonPackage.eINSTANCE);
		JcaPackageImpl theJcaPackage = (JcaPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(JcaPackage.eNS_URI) instanceof JcaPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(JcaPackage.eNS_URI) : JcaPackage.eINSTANCE);
		Webservice_clientPackageImpl theWebservice_clientPackage = (Webservice_clientPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Webservice_clientPackage.eNS_URI) instanceof Webservice_clientPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Webservice_clientPackage.eNS_URI) : Webservice_clientPackage.eINSTANCE);
		JspPackageImpl theJspPackage = (JspPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(JspPackage.eNS_URI) instanceof JspPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(JspPackage.eNS_URI) : JspPackage.eINSTANCE);

		// Create package meta-data objects
		theCommonarchivePackage.createPackageContents();
		theApplicationPackage.createPackageContents();
		theClientPackage.createPackageContents();
		theEjbPackage.createPackageContents();
		theWebapplicationPackage.createPackageContents();
		theLooseconfigPackage.createPackageContents();
		theJavaRefPackage.createPackageContents();
		theCommonPackage.createPackageContents();
		theJcaPackage.createPackageContents();
		theWebservice_clientPackage.createPackageContents();
		theJspPackage.createPackageContents();

		// Initialize created meta-data
		theCommonarchivePackage.initializePackageContents();
		theApplicationPackage.initializePackageContents();
		theClientPackage.initializePackageContents();
		theEjbPackage.initializePackageContents();
		theWebapplicationPackage.initializePackageContents();
		theLooseconfigPackage.initializePackageContents();
		theJavaRefPackage.initializePackageContents();
		theCommonPackage.initializePackageContents();
		theJcaPackage.initializePackageContents();
		theWebservice_clientPackage.initializePackageContents();
		theJspPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theCommonarchivePackage.freeze();

		return theCommonarchivePackage;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getContainer() {
		return containerEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getContainer_Files() {
		return (EReference)containerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getWARFile() {
		return warFileEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getWARFile_DeploymentDescriptor() {
		return (EReference)warFileEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getModuleFile() {
		return moduleFileEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getEARFile() {
		return earFileEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getEARFile_DeploymentDescriptor() {
		return (EReference)earFileEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getEARFile_ModuleRefs() {
		return (EReference)earFileEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getModuleRef() {
		return moduleRefEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getModuleRef_ModuleFile() {
		return (EReference)moduleRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getModuleRef_EarFile() {
		return (EReference)moduleRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModuleRef_Module() {
		return (EReference)moduleRefEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getEJBModuleRef() {
		return ejbModuleRefEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getWebModuleRef() {
		return webModuleRefEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getClientModuleRef() {
		return clientModuleRefEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getConnectorModuleRef() {
		return connectorModuleRefEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getApplicationClientFile() {
		return applicationClientFileEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getApplicationClientFile_DeploymentDescriptor() {
		return (EReference)applicationClientFileEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getEJBJarFile() {
		return ejbJarFileEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getEJBJarFile_DeploymentDescriptor() {
		return (EReference)ejbJarFileEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getRARFile() {
		return rarFileEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRARFile_DeploymentDescriptor() {
		return (EReference)rarFileEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getArchive() {
		return archiveEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArchive_Types() {
		return (EAttribute)archiveEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getFile() {
		return fileEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getFile_URI() {
		return (EAttribute)fileEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getFile_LastModified() {
		return (EAttribute)fileEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getFile_Size() {
		return (EAttribute)fileEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFile_DirectoryEntry() {
		return (EAttribute)fileEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getFile_OriginalURI() {
		return (EAttribute)fileEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getFile_LoadingContainer() {
		return (EReference)fileEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getFile_Container() {
		return (EReference)fileEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getReadOnlyDirectory() {
		return readOnlyDirectoryEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public CommonarchiveFactory getCommonarchiveFactory() {
		return (CommonarchiveFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		fileEClass = createEClass(FILE);
		createEAttribute(fileEClass, FILE__URI);
		createEAttribute(fileEClass, FILE__LAST_MODIFIED);
		createEAttribute(fileEClass, FILE__SIZE);
		createEAttribute(fileEClass, FILE__DIRECTORY_ENTRY);
		createEAttribute(fileEClass, FILE__ORIGINAL_URI);
		createEReference(fileEClass, FILE__LOADING_CONTAINER);
		createEReference(fileEClass, FILE__CONTAINER);

		archiveEClass = createEClass(ARCHIVE);
		createEAttribute(archiveEClass, ARCHIVE__TYPES);

		ejbJarFileEClass = createEClass(EJB_JAR_FILE);
		createEReference(ejbJarFileEClass, EJB_JAR_FILE__DEPLOYMENT_DESCRIPTOR);

		warFileEClass = createEClass(WAR_FILE);
		createEReference(warFileEClass, WAR_FILE__DEPLOYMENT_DESCRIPTOR);

		earFileEClass = createEClass(EAR_FILE);
		createEReference(earFileEClass, EAR_FILE__MODULE_REFS);
		createEReference(earFileEClass, EAR_FILE__DEPLOYMENT_DESCRIPTOR);

		applicationClientFileEClass = createEClass(APPLICATION_CLIENT_FILE);
		createEReference(applicationClientFileEClass, APPLICATION_CLIENT_FILE__DEPLOYMENT_DESCRIPTOR);

		moduleFileEClass = createEClass(MODULE_FILE);

		containerEClass = createEClass(CONTAINER);
		createEReference(containerEClass, CONTAINER__FILES);

		readOnlyDirectoryEClass = createEClass(READ_ONLY_DIRECTORY);

		rarFileEClass = createEClass(RAR_FILE);
		createEReference(rarFileEClass, RAR_FILE__DEPLOYMENT_DESCRIPTOR);

		moduleRefEClass = createEClass(MODULE_REF);
		createEReference(moduleRefEClass, MODULE_REF__MODULE_FILE);
		createEReference(moduleRefEClass, MODULE_REF__EAR_FILE);
		createEReference(moduleRefEClass, MODULE_REF__MODULE);

		ejbModuleRefEClass = createEClass(EJB_MODULE_REF);

		webModuleRefEClass = createEClass(WEB_MODULE_REF);

		clientModuleRefEClass = createEClass(CLIENT_MODULE_REF);

		connectorModuleRefEClass = createEClass(CONNECTOR_MODULE_REF);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
		LooseconfigPackageImpl theLooseconfigPackage = (LooseconfigPackageImpl)EPackage.Registry.INSTANCE.getEPackage(LooseconfigPackage.eNS_URI);
		EjbPackageImpl theEjbPackage = (EjbPackageImpl)EPackage.Registry.INSTANCE.getEPackage(EjbPackage.eNS_URI);
		WebapplicationPackageImpl theWebapplicationPackage = (WebapplicationPackageImpl)EPackage.Registry.INSTANCE.getEPackage(WebapplicationPackage.eNS_URI);
		ApplicationPackageImpl theApplicationPackage = (ApplicationPackageImpl)EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI);
		ClientPackageImpl theClientPackage = (ClientPackageImpl)EPackage.Registry.INSTANCE.getEPackage(ClientPackage.eNS_URI);
		JcaPackageImpl theJcaPackage = (JcaPackageImpl)EPackage.Registry.INSTANCE.getEPackage(JcaPackage.eNS_URI);

		// Add subpackages
		getESubpackages().add(theLooseconfigPackage);

		// Add supertypes to classes
		archiveEClass.getESuperTypes().add(this.getContainer());
		ejbJarFileEClass.getESuperTypes().add(this.getModuleFile());
		warFileEClass.getESuperTypes().add(this.getModuleFile());
		earFileEClass.getESuperTypes().add(this.getModuleFile());
		applicationClientFileEClass.getESuperTypes().add(this.getModuleFile());
		moduleFileEClass.getESuperTypes().add(this.getArchive());
		containerEClass.getESuperTypes().add(this.getFile());
		readOnlyDirectoryEClass.getESuperTypes().add(this.getContainer());
		rarFileEClass.getESuperTypes().add(this.getModuleFile());
		ejbModuleRefEClass.getESuperTypes().add(this.getModuleRef());
		webModuleRefEClass.getESuperTypes().add(this.getModuleRef());
		clientModuleRefEClass.getESuperTypes().add(this.getModuleRef());
		connectorModuleRefEClass.getESuperTypes().add(this.getModuleRef());

		// Initialize classes and features; add operations and parameters
		initEClass(fileEClass, File.class, "File", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFile_URI(), ecorePackage.getEString(), "URI", null, 0, 1, File.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFile_LastModified(), ecorePackage.getELong(), "lastModified", null, 0, 1, File.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFile_Size(), ecorePackage.getELong(), "size", null, 0, 1, File.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFile_DirectoryEntry(), ecorePackage.getEBoolean(), "directoryEntry", null, 0, 1, File.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFile_OriginalURI(), ecorePackage.getEString(), "originalURI", null, 0, 1, File.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFile_LoadingContainer(), this.getContainer(), null, "loadingContainer", null, 1, 1, File.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFile_Container(), this.getContainer(), this.getContainer_Files(), "container", null, 0, 1, File.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(archiveEClass, Archive.class, "Archive", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getArchive_Types(), ecorePackage.getEString(), "types", null, 0, -1, Archive.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ejbJarFileEClass, EJBJarFile.class, "EJBJarFile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEJBJarFile_DeploymentDescriptor(), theEjbPackage.getEJBJar(), null, "deploymentDescriptor", null, 1, 1, EJBJarFile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(warFileEClass, WARFile.class, "WARFile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getWARFile_DeploymentDescriptor(), theWebapplicationPackage.getWebApp(), null, "deploymentDescriptor", null, 1, 1, WARFile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(earFileEClass, EARFile.class, "EARFile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEARFile_ModuleRefs(), this.getModuleRef(), this.getModuleRef_EarFile(), "moduleRefs", null, 1, -1, EARFile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEARFile_DeploymentDescriptor(), theApplicationPackage.getApplication(), null, "deploymentDescriptor", null, 1, 1, EARFile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(applicationClientFileEClass, ApplicationClientFile.class, "ApplicationClientFile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getApplicationClientFile_DeploymentDescriptor(), theClientPackage.getApplicationClient(), null, "deploymentDescriptor", null, 1, 1, ApplicationClientFile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(moduleFileEClass, ModuleFile.class, "ModuleFile", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(containerEClass, Container.class, "Container", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getContainer_Files(), this.getFile(), this.getFile_Container(), "files", null, 0, -1, Container.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(readOnlyDirectoryEClass, ReadOnlyDirectory.class, "ReadOnlyDirectory", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(rarFileEClass, RARFile.class, "RARFile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRARFile_DeploymentDescriptor(), theJcaPackage.getConnector(), null, "deploymentDescriptor", null, 1, 1, RARFile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(moduleRefEClass, ModuleRef.class, "ModuleRef", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getModuleRef_ModuleFile(), this.getModuleFile(), null, "moduleFile", null, 1, 1, ModuleRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModuleRef_EarFile(), this.getEARFile(), this.getEARFile_ModuleRefs(), "earFile", null, 1, 1, ModuleRef.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModuleRef_Module(), theApplicationPackage.getModule(), null, "module", null, 1, 1, ModuleRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ejbModuleRefEClass, EJBModuleRef.class, "EJBModuleRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(webModuleRefEClass, WebModuleRef.class, "WebModuleRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(clientModuleRefEClass, ClientModuleRef.class, "ClientModuleRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(connectorModuleRefEClass, ConnectorModuleRef.class, "ConnectorModuleRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //CommonarchivePackageImpl



