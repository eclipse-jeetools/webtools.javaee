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
package org.eclipse.jst.j2ee.commonarchivecore.internal.looseconfig.impl;


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.impl.EcorePackageImpl;
import org.eclipse.jem.java.impl.JavaRefPackageImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.CommonarchivePackageImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.looseconfig.LooseApplication;
import org.eclipse.jst.j2ee.commonarchivecore.internal.looseconfig.LooseArchive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.looseconfig.LooseConfiguration;
import org.eclipse.jst.j2ee.commonarchivecore.internal.looseconfig.LooseLibrary;
import org.eclipse.jst.j2ee.commonarchivecore.internal.looseconfig.LooseModule;
import org.eclipse.jst.j2ee.commonarchivecore.internal.looseconfig.LooseWARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.looseconfig.LooseconfigFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.looseconfig.LooseconfigPackage;
import org.eclipse.jst.j2ee.internal.application.impl.ApplicationPackageImpl;
import org.eclipse.jst.j2ee.internal.client.impl.ClientPackageImpl;
import org.eclipse.jst.j2ee.internal.common.impl.CommonPackageImpl;
import org.eclipse.jst.j2ee.internal.ejb.impl.EjbPackageImpl;
import org.eclipse.jst.j2ee.internal.jca.impl.JcaPackageImpl;
import org.eclipse.jst.j2ee.internal.jsp.impl.JspPackageImpl;
import org.eclipse.jst.j2ee.internal.webapplication.impl.WebapplicationPackageImpl;
import org.eclipse.jst.j2ee.webservice.internal.wsclient.impl.Webservice_clientPackageImpl;


/**
 * @lastgen class LooseconfigPackageImpl extends EPackageImpl implements LooseconfigPackage,
 *          EPackage {}
 */
public class LooseconfigPackageImpl extends EPackageImpl implements LooseconfigPackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass looseApplicationEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass looseArchiveEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass looseLibraryEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass looseModuleEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass looseConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
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
	 * 
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static LooseconfigPackage init() {
		if (isInited)
			return (LooseconfigPackage) EPackage.Registry.INSTANCE.getEPackage(LooseconfigPackage.eNS_URI);

		// Obtain or create and register package.
		LooseconfigPackageImpl theLooseconfigPackage = (LooseconfigPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new LooseconfigPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		JcaPackageImpl.init();
		Webservice_clientPackageImpl.init();
		EcorePackageImpl.init();
		JavaRefPackageImpl.init();
		CommonPackageImpl.init();
		ClientPackageImpl.init();
		WebapplicationPackageImpl.init();
		EjbPackageImpl.init();
		JspPackageImpl.init();
		ApplicationPackageImpl.init();

		// Obtain or create and register interdependencies
		CommonarchivePackageImpl theCommonarchivePackage = (CommonarchivePackageImpl) (EPackage.Registry.INSTANCE.getEPackage(CommonarchivePackage.eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(CommonarchivePackage.eNS_URI) : CommonarchivePackageImpl.eINSTANCE);

		// Step 1: create meta-model objects
		theLooseconfigPackage.createPackageContents();
		theCommonarchivePackage.createPackageContents();

		// Step 2: complete initialization
		theLooseconfigPackage.initializePackageContents();
		theCommonarchivePackage.initializePackageContents();

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
		return (EReference) looseApplicationEClass.getEReferences().get(0);
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
		return (EAttribute) looseArchiveEClass.getEAttributes().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getLooseArchive_BinariesPath() {
		return (EAttribute) looseArchiveEClass.getEAttributes().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getLooseArchive_ResourcesPath() {
		return (EAttribute) looseArchiveEClass.getEAttributes().get(2);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getLooseArchive_LooseApp() {
		return (EReference) looseArchiveEClass.getEReferences().get(0);
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
		return (EReference) looseLibraryEClass.getEReferences().get(0);
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
		return (EReference) looseWARFileEClass.getEReferences().get(0);
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
		return (EAttribute) looseModuleEClass.getEAttributes().get(0);
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
		return (EReference) looseConfigurationEClass.getEReferences().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public LooseconfigFactory getLooseconfigFactory() {
		return (LooseconfigFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package. This method is guarded to have no affect on
	 * any invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
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
	 * 
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model. This method is guarded to have
	 * no affect on any invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized)
			return;
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
		initEClass(looseApplicationEClass, LooseApplication.class, "LooseApplication", !IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$
		initEReference(getLooseApplication_LooseArchives(), this.getLooseArchive(), this.getLooseArchive_LooseApp(), "looseArchives", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, false); //$NON-NLS-1$

		initEClass(looseArchiveEClass, LooseArchive.class, "LooseArchive", IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$
		initEAttribute(getLooseArchive_Uri(), ecorePackage.getEString(), "uri", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false); //$NON-NLS-1$
		initEAttribute(getLooseArchive_BinariesPath(), ecorePackage.getEString(), "binariesPath", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false); //$NON-NLS-1$
		initEAttribute(getLooseArchive_ResourcesPath(), ecorePackage.getEString(), "resourcesPath", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false); //$NON-NLS-1$
		initEReference(getLooseArchive_LooseApp(), this.getLooseApplication(), this.getLooseApplication_LooseArchives(), "looseApp", null, 0, 1, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, false); //$NON-NLS-1$

		addEOperation(looseArchiveEClass, ecorePackage.getEBoolean(), "isModule"); //$NON-NLS-1$

		initEClass(looseLibraryEClass, LooseLibrary.class, "LooseLibrary", !IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$
		initEReference(getLooseLibrary_LooseWAR(), this.getLooseWARFile(), this.getLooseWARFile_LooseLibs(), "looseWAR", null, 0, 1, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, false); //$NON-NLS-1$

		initEClass(looseModuleEClass, LooseModule.class, "LooseModule", !IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$
		initEAttribute(getLooseModule_AltDD(), ecorePackage.getEString(), "altDD", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false); //$NON-NLS-1$

		initEClass(looseConfigurationEClass, LooseConfiguration.class, "LooseConfiguration", !IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$
		initEReference(getLooseConfiguration_Applications(), this.getLooseApplication(), null, "applications", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, false); //$NON-NLS-1$

		initEClass(looseWARFileEClass, LooseWARFile.class, "LooseWARFile", !IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$
		initEReference(getLooseWARFile_LooseLibs(), this.getLooseLibrary(), this.getLooseLibrary_LooseWAR(), "looseLibs", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, false); //$NON-NLS-1$
	}
} //LooseconfigPackageImpl



