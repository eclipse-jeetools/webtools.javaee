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
package org.eclipse.jst.j2ee.commonarchivecore.looseconfig;



import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;


/**
 * @lastgen interface LooseconfigPackage extends EPackage {}
 */
public interface LooseconfigPackage extends EPackage {
	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "looseconfig"; //$NON-NLS-1$

	/**
	 * @generated This field/method will be replaced during code generation.
	 */


	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOOSE_APPLICATION = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOOSE_ARCHIVE = 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOOSE_ARCHIVE__URI = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOOSE_ARCHIVE__BINARIES_PATH = 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOOSE_ARCHIVE__RESOURCES_PATH = 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOOSE_ARCHIVE__LOOSE_APP = 3;

	/**
	 * The number of structural features of the the '<em>Loose Archive</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOOSE_ARCHIVE_FEATURE_COUNT = 4;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOOSE_APPLICATION__URI = LOOSE_ARCHIVE__URI;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOOSE_APPLICATION__BINARIES_PATH = LOOSE_ARCHIVE__BINARIES_PATH;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOOSE_APPLICATION__RESOURCES_PATH = LOOSE_ARCHIVE__RESOURCES_PATH;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOOSE_APPLICATION__LOOSE_APP = LOOSE_ARCHIVE__LOOSE_APP;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOOSE_APPLICATION__LOOSE_ARCHIVES = LOOSE_ARCHIVE_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the the '<em>Loose Application</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOOSE_APPLICATION_FEATURE_COUNT = LOOSE_ARCHIVE_FEATURE_COUNT + 1;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOOSE_LIBRARY = 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOOSE_LIBRARY__URI = LOOSE_ARCHIVE__URI;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOOSE_LIBRARY__BINARIES_PATH = LOOSE_ARCHIVE__BINARIES_PATH;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOOSE_LIBRARY__RESOURCES_PATH = LOOSE_ARCHIVE__RESOURCES_PATH;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOOSE_LIBRARY__LOOSE_APP = LOOSE_ARCHIVE__LOOSE_APP;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOOSE_LIBRARY__LOOSE_WAR = LOOSE_ARCHIVE_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the the '<em>Loose Library</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOOSE_LIBRARY_FEATURE_COUNT = LOOSE_ARCHIVE_FEATURE_COUNT + 1;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOOSE_WAR_FILE = 5;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOOSE_MODULE = 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOOSE_MODULE__URI = LOOSE_ARCHIVE__URI;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOOSE_MODULE__BINARIES_PATH = LOOSE_ARCHIVE__BINARIES_PATH;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOOSE_MODULE__RESOURCES_PATH = LOOSE_ARCHIVE__RESOURCES_PATH;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOOSE_MODULE__LOOSE_APP = LOOSE_ARCHIVE__LOOSE_APP;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOOSE_MODULE__ALT_DD = LOOSE_ARCHIVE_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the the '<em>Loose Module</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOOSE_MODULE_FEATURE_COUNT = LOOSE_ARCHIVE_FEATURE_COUNT + 1;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOOSE_CONFIGURATION = 4;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOOSE_CONFIGURATION__APPLICATIONS = 0;


	/**
	 * The number of structural features of the the '<em>Loose Configuration</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOOSE_CONFIGURATION_FEATURE_COUNT = 1;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOOSE_WAR_FILE__URI = LOOSE_MODULE__URI;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOOSE_WAR_FILE__BINARIES_PATH = LOOSE_MODULE__BINARIES_PATH;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOOSE_WAR_FILE__RESOURCES_PATH = LOOSE_MODULE__RESOURCES_PATH;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOOSE_WAR_FILE__LOOSE_APP = LOOSE_MODULE__LOOSE_APP;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOOSE_WAR_FILE__ALT_DD = LOOSE_MODULE__ALT_DD;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOOSE_WAR_FILE__LOOSE_LIBS = LOOSE_MODULE_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the the '<em>Loose WAR File</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOOSE_WAR_FILE_FEATURE_COUNT = LOOSE_MODULE_FEATURE_COUNT + 1;


	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	String eNS_URI = "commonarchive.looseconfig.xmi"; //$NON-NLS-1$
	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "org.eclipse.jst.j2ee.commonarchivecore.looseconfig"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	LooseconfigPackage eINSTANCE = org.eclipse.jst.j2ee.commonarchivecore.looseconfig.impl.LooseconfigPackageImpl.init();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return LooseApplication object
	 */
	EClass getLooseApplication();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getLooseApplication_LooseArchives();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return LooseArchive object
	 */
	EClass getLooseArchive();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getLooseArchive_Uri();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getLooseArchive_BinariesPath();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getLooseArchive_ResourcesPath();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getLooseArchive_LooseApp();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return LooseLibrary object
	 */
	EClass getLooseLibrary();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getLooseLibrary_LooseWAR();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return LooseWARFile object
	 */
	EClass getLooseWARFile();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getLooseWARFile_LooseLibs();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return LooseModule object
	 */
	EClass getLooseModule();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getLooseModule_AltDD();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return LooseConfiguration object
	 */
	EClass getLooseConfiguration();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getLooseConfiguration_Applications();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	LooseconfigFactory getLooseconfigFactory();

} //LooseconfigPackage



