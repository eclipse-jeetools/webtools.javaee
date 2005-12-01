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
package org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl;


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.impl.EcorePackageImpl;
import org.eclipse.jem.java.JavaRefPackage;
import org.eclipse.jem.java.impl.JavaRefPackageImpl;
import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.application.internal.impl.ApplicationPackageImpl;
import org.eclipse.jst.j2ee.client.ClientPackage;
import org.eclipse.jst.j2ee.client.internal.impl.ClientPackageImpl;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.CommonarchivePackageImpl;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseApplication;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseArchive;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseConfiguration;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseLibrary;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseModule;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseWARFile;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseconfigFactory;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseconfigPackage;
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
 * @lastgen class LooseconfigPackageImpl extends EPackageImpl implements LooseconfigPackage,
 *          EPackage {}
 */
public class LooseconfigPackageImpl extends EPackageImpl implements LooseconfigPackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass looseApplicationEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass looseArchiveEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass looseLibraryEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass looseModuleEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass looseConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass looseWARFileEClass = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	private LooseconfigPackageImpl() {
		super(eNS_URI, LooseconfigFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static LooseconfigPackage init() {
		if (isInited) return (LooseconfigPackage)EPackage.Registry.INSTANCE.getEPackage(LooseconfigPackage.eNS_URI);

		// Obtain or create and register package
		LooseconfigPackageImpl theLooseconfigPackage = (LooseconfigPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof LooseconfigPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new LooseconfigPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackageImpl.init();

		// Obtain or create and register interdependencies
		ApplicationPackageImpl theApplicationPackage = (ApplicationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI) instanceof ApplicationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI) : ApplicationPackage.eINSTANCE);
		ClientPackageImpl theClientPackage = (ClientPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ClientPackage.eNS_URI) instanceof ClientPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ClientPackage.eNS_URI) : ClientPackage.eINSTANCE);
		EjbPackageImpl theEjbPackage = (EjbPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(EjbPackage.eNS_URI) instanceof EjbPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(EjbPackage.eNS_URI) : EjbPackage.eINSTANCE);
		WebapplicationPackageImpl theWebapplicationPackage = (WebapplicationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WebapplicationPackage.eNS_URI) instanceof WebapplicationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(WebapplicationPackage.eNS_URI) : WebapplicationPackage.eINSTANCE);
		CommonarchivePackageImpl theCommonarchivePackage = (CommonarchivePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CommonarchivePackage.eNS_URI) instanceof CommonarchivePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CommonarchivePackage.eNS_URI) : CommonarchivePackage.eINSTANCE);
		JavaRefPackageImpl theJavaRefPackage = (JavaRefPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(JavaRefPackage.eNS_URI) instanceof JavaRefPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(JavaRefPackage.eNS_URI) : JavaRefPackage.eINSTANCE);
		CommonPackageImpl theCommonPackage = (CommonPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI) instanceof CommonPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI) : CommonPackage.eINSTANCE);
		JcaPackageImpl theJcaPackage = (JcaPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(JcaPackage.eNS_URI) instanceof JcaPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(JcaPackage.eNS_URI) : JcaPackage.eINSTANCE);
		Webservice_clientPackageImpl theWebservice_clientPackage = (Webservice_clientPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Webservice_clientPackage.eNS_URI) instanceof Webservice_clientPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Webservice_clientPackage.eNS_URI) : Webservice_clientPackage.eINSTANCE);
		JspPackageImpl theJspPackage = (JspPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(JspPackage.eNS_URI) instanceof JspPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(JspPackage.eNS_URI) : JspPackage.eINSTANCE);

		// Create package meta-data objects
		theLooseconfigPackage.createPackageContents();
		theApplicationPackage.createPackageContents();
		theClientPackage.createPackageContents();
		theEjbPackage.createPackageContents();
		theWebapplicationPackage.createPackageContents();
		theCommonarchivePackage.createPackageContents();
		theJavaRefPackage.createPackageContents();
		theCommonPackage.createPackageContents();
		theJcaPackage.createPackageContents();
		theWebservice_clientPackage.createPackageContents();
		theJspPackage.createPackageContents();

		// Initialize created meta-data
		theLooseconfigPackage.initializePackageContents();
		theApplicationPackage.initializePackageContents();
		theClientPackage.initializePackageContents();
		theEjbPackage.initializePackageContents();
		theWebapplicationPackage.initializePackageContents();
		theCommonarchivePackage.initializePackageContents();
		theJavaRefPackage.initializePackageContents();
		theCommonPackage.initializePackageContents();
		theJcaPackage.initializePackageContents();
		theWebservice_clientPackage.initializePackageContents();
		theJspPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theLooseconfigPackage.freeze();

		return theLooseconfigPackage;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getLooseApplication() {
		return looseApplicationEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getLooseApplication_LooseArchives() {
		return (EReference)looseApplicationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getLooseArchive() {
		return looseArchiveEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getLooseArchive_Uri() {
		return (EAttribute)looseArchiveEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getLooseArchive_BinariesPath() {
		return (EAttribute)looseArchiveEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getLooseArchive_ResourcesPath() {
		return (EAttribute)looseArchiveEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getLooseArchive_LooseApp() {
		return (EReference)looseArchiveEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getLooseLibrary() {
		return looseLibraryEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getLooseLibrary_LooseWAR() {
		return (EReference)looseLibraryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getLooseWARFile() {
		return looseWARFileEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getLooseWARFile_LooseLibs() {
		return (EReference)looseWARFileEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getLooseModule() {
		return looseModuleEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getLooseModule_AltDD() {
		return (EAttribute)looseModuleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getLooseConfiguration() {
		return looseConfigurationEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getLooseConfiguration_Applications() {
		return (EReference)looseConfigurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public LooseconfigFactory getLooseconfigFactory() {
		return (LooseconfigFactory)getEFactoryInstance();
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
		looseApplicationEClass = createEClass(LOOSE_APPLICATION);
		createEReference(looseApplicationEClass, LOOSE_APPLICATION__LOOSE_ARCHIVES);

		looseArchiveEClass = createEClass(LOOSE_ARCHIVE);
		createEAttribute(looseArchiveEClass, LOOSE_ARCHIVE__URI);
		createEAttribute(looseArchiveEClass, LOOSE_ARCHIVE__BINARIES_PATH);
		createEAttribute(looseArchiveEClass, LOOSE_ARCHIVE__RESOURCES_PATH);
		createEReference(looseArchiveEClass, LOOSE_ARCHIVE__LOOSE_APP);

		looseLibraryEClass = createEClass(LOOSE_LIBRARY);
		createEReference(looseLibraryEClass, LOOSE_LIBRARY__LOOSE_WAR);

		looseModuleEClass = createEClass(LOOSE_MODULE);
		createEAttribute(looseModuleEClass, LOOSE_MODULE__ALT_DD);

		looseConfigurationEClass = createEClass(LOOSE_CONFIGURATION);
		createEReference(looseConfigurationEClass, LOOSE_CONFIGURATION__APPLICATIONS);

		looseWARFileEClass = createEClass(LOOSE_WAR_FILE);
		createEReference(looseWARFileEClass, LOOSE_WAR_FILE__LOOSE_LIBS);
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

		// Add supertypes to classes
		looseApplicationEClass.getESuperTypes().add(this.getLooseArchive());
		looseLibraryEClass.getESuperTypes().add(this.getLooseArchive());
		looseModuleEClass.getESuperTypes().add(this.getLooseArchive());
		looseWARFileEClass.getESuperTypes().add(this.getLooseModule());

		// Initialize classes and features; add operations and parameters
		initEClass(looseApplicationEClass, LooseApplication.class, "LooseApplication", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLooseApplication_LooseArchives(), this.getLooseArchive(), this.getLooseArchive_LooseApp(), "looseArchives", null, 0, -1, LooseApplication.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(looseArchiveEClass, LooseArchive.class, "LooseArchive", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLooseArchive_Uri(), ecorePackage.getEString(), "uri", null, 0, 1, LooseArchive.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLooseArchive_BinariesPath(), ecorePackage.getEString(), "binariesPath", null, 0, 1, LooseArchive.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLooseArchive_ResourcesPath(), ecorePackage.getEString(), "resourcesPath", null, 0, 1, LooseArchive.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLooseArchive_LooseApp(), this.getLooseApplication(), this.getLooseApplication_LooseArchives(), "looseApp", null, 0, 1, LooseArchive.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(looseArchiveEClass, ecorePackage.getEBoolean(), "isModule");

		initEClass(looseLibraryEClass, LooseLibrary.class, "LooseLibrary", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLooseLibrary_LooseWAR(), this.getLooseWARFile(), this.getLooseWARFile_LooseLibs(), "looseWAR", null, 0, 1, LooseLibrary.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(looseModuleEClass, LooseModule.class, "LooseModule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLooseModule_AltDD(), ecorePackage.getEString(), "altDD", null, 0, 1, LooseModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(looseConfigurationEClass, LooseConfiguration.class, "LooseConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLooseConfiguration_Applications(), this.getLooseApplication(), null, "applications", null, 0, -1, LooseConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(looseWARFileEClass, LooseWARFile.class, "LooseWARFile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLooseWARFile_LooseLibs(), this.getLooseLibrary(), this.getLooseLibrary_LooseWAR(), "looseLibs", null, 0, -1, LooseWARFile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
	}

} //LooseconfigPackageImpl



