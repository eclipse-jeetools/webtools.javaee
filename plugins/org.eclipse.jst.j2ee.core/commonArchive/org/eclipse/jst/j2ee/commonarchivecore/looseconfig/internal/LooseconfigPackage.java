/*******************************************************************************
 * Copyright (c) 2005, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jst.j2ee.common.CommonPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.LooseconfigFactory
 * @model kind="package"
 * @generated
 */
public interface LooseconfigPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "looseconfig"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "commonarchive.looseconfig.xmi"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.eclipse.jst.j2ee.commonarchivecore.looseconfig"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	LooseconfigPackage eINSTANCE = org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseconfigPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseArchiveImpl <em>Loose Archive</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseArchiveImpl
	 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseconfigPackageImpl#getLooseArchive()
	 * @generated
	 */
	int LOOSE_ARCHIVE = 1;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOSE_ARCHIVE__URI = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Binaries Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOSE_ARCHIVE__BINARIES_PATH = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Resources Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOSE_ARCHIVE__RESOURCES_PATH = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Loose App</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOSE_ARCHIVE__LOOSE_APP = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Loose Archive</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOSE_ARCHIVE_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseApplicationImpl <em>Loose Application</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseApplicationImpl
	 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseconfigPackageImpl#getLooseApplication()
	 * @generated
	 */
	int LOOSE_APPLICATION = 0;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOSE_APPLICATION__URI = LOOSE_ARCHIVE__URI;

	/**
	 * The feature id for the '<em><b>Binaries Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOSE_APPLICATION__BINARIES_PATH = LOOSE_ARCHIVE__BINARIES_PATH;

	/**
	 * The feature id for the '<em><b>Resources Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOSE_APPLICATION__RESOURCES_PATH = LOOSE_ARCHIVE__RESOURCES_PATH;

	/**
	 * The feature id for the '<em><b>Loose App</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOSE_APPLICATION__LOOSE_APP = LOOSE_ARCHIVE__LOOSE_APP;

	/**
	 * The feature id for the '<em><b>Loose Archives</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOSE_APPLICATION__LOOSE_ARCHIVES = LOOSE_ARCHIVE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Loose Application</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOSE_APPLICATION_FEATURE_COUNT = LOOSE_ARCHIVE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseLibraryImpl <em>Loose Library</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseLibraryImpl
	 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseconfigPackageImpl#getLooseLibrary()
	 * @generated
	 */
	int LOOSE_LIBRARY = 2;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOSE_LIBRARY__URI = LOOSE_ARCHIVE__URI;

	/**
	 * The feature id for the '<em><b>Binaries Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOSE_LIBRARY__BINARIES_PATH = LOOSE_ARCHIVE__BINARIES_PATH;

	/**
	 * The feature id for the '<em><b>Resources Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOSE_LIBRARY__RESOURCES_PATH = LOOSE_ARCHIVE__RESOURCES_PATH;

	/**
	 * The feature id for the '<em><b>Loose App</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOSE_LIBRARY__LOOSE_APP = LOOSE_ARCHIVE__LOOSE_APP;

	/**
	 * The feature id for the '<em><b>Loose WAR</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOSE_LIBRARY__LOOSE_WAR = LOOSE_ARCHIVE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Loose Library</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOSE_LIBRARY_FEATURE_COUNT = LOOSE_ARCHIVE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseModuleImpl <em>Loose Module</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseModuleImpl
	 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseconfigPackageImpl#getLooseModule()
	 * @generated
	 */
	int LOOSE_MODULE = 3;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOSE_MODULE__URI = LOOSE_ARCHIVE__URI;

	/**
	 * The feature id for the '<em><b>Binaries Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOSE_MODULE__BINARIES_PATH = LOOSE_ARCHIVE__BINARIES_PATH;

	/**
	 * The feature id for the '<em><b>Resources Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOSE_MODULE__RESOURCES_PATH = LOOSE_ARCHIVE__RESOURCES_PATH;

	/**
	 * The feature id for the '<em><b>Loose App</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOSE_MODULE__LOOSE_APP = LOOSE_ARCHIVE__LOOSE_APP;

	/**
	 * The feature id for the '<em><b>Alt DD</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOSE_MODULE__ALT_DD = LOOSE_ARCHIVE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Loose Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOSE_MODULE_FEATURE_COUNT = LOOSE_ARCHIVE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseConfigurationImpl <em>Loose Configuration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseConfigurationImpl
	 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseconfigPackageImpl#getLooseConfiguration()
	 * @generated
	 */
	int LOOSE_CONFIGURATION = 4;

	/**
	 * The feature id for the '<em><b>Applications</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOSE_CONFIGURATION__APPLICATIONS = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Loose Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOSE_CONFIGURATION_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseWARFileImpl <em>Loose WAR File</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseWARFileImpl
	 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseconfigPackageImpl#getLooseWARFile()
	 * @generated
	 */
	int LOOSE_WAR_FILE = 5;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOSE_WAR_FILE__URI = LOOSE_MODULE__URI;

	/**
	 * The feature id for the '<em><b>Binaries Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOSE_WAR_FILE__BINARIES_PATH = LOOSE_MODULE__BINARIES_PATH;

	/**
	 * The feature id for the '<em><b>Resources Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOSE_WAR_FILE__RESOURCES_PATH = LOOSE_MODULE__RESOURCES_PATH;

	/**
	 * The feature id for the '<em><b>Loose App</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOSE_WAR_FILE__LOOSE_APP = LOOSE_MODULE__LOOSE_APP;

	/**
	 * The feature id for the '<em><b>Alt DD</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOSE_WAR_FILE__ALT_DD = LOOSE_MODULE__ALT_DD;

	/**
	 * The feature id for the '<em><b>Loose Libs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOSE_WAR_FILE__LOOSE_LIBS = LOOSE_MODULE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Loose WAR File</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOSE_WAR_FILE_FEATURE_COUNT = LOOSE_MODULE_FEATURE_COUNT + 1;


	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseApplication <em>Loose Application</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Loose Application</em>'.
	 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseApplication
	 * @generated
	 */
	EClass getLooseApplication();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseApplication#getLooseArchives <em>Loose Archives</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Loose Archives</em>'.
	 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseApplication#getLooseArchives()
	 * @see #getLooseApplication()
	 * @generated
	 */
	EReference getLooseApplication_LooseArchives();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseArchive <em>Loose Archive</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Loose Archive</em>'.
	 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseArchive
	 * @generated
	 */
	EClass getLooseArchive();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseArchive#getUri <em>Uri</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uri</em>'.
	 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseArchive#getUri()
	 * @see #getLooseArchive()
	 * @generated
	 */
	EAttribute getLooseArchive_Uri();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseArchive#getBinariesPath <em>Binaries Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Binaries Path</em>'.
	 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseArchive#getBinariesPath()
	 * @see #getLooseArchive()
	 * @generated
	 */
	EAttribute getLooseArchive_BinariesPath();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseArchive#getResourcesPath <em>Resources Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resources Path</em>'.
	 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseArchive#getResourcesPath()
	 * @see #getLooseArchive()
	 * @generated
	 */
	EAttribute getLooseArchive_ResourcesPath();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseArchive#getLooseApp <em>Loose App</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Loose App</em>'.
	 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseArchive#getLooseApp()
	 * @see #getLooseArchive()
	 * @generated
	 */
	EReference getLooseArchive_LooseApp();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseLibrary <em>Loose Library</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Loose Library</em>'.
	 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseLibrary
	 * @generated
	 */
	EClass getLooseLibrary();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseLibrary#getLooseWAR <em>Loose WAR</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Loose WAR</em>'.
	 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseLibrary#getLooseWAR()
	 * @see #getLooseLibrary()
	 * @generated
	 */
	EReference getLooseLibrary_LooseWAR();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseModule <em>Loose Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Loose Module</em>'.
	 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseModule
	 * @generated
	 */
	EClass getLooseModule();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseModule#getAltDD <em>Alt DD</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Alt DD</em>'.
	 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseModule#getAltDD()
	 * @see #getLooseModule()
	 * @generated
	 */
	EAttribute getLooseModule_AltDD();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseConfiguration <em>Loose Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Loose Configuration</em>'.
	 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseConfiguration
	 * @generated
	 */
	EClass getLooseConfiguration();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseConfiguration#getApplications <em>Applications</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Applications</em>'.
	 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseConfiguration#getApplications()
	 * @see #getLooseConfiguration()
	 * @generated
	 */
	EReference getLooseConfiguration_Applications();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseWARFile <em>Loose WAR File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Loose WAR File</em>'.
	 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseWARFile
	 * @generated
	 */
	EClass getLooseWARFile();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseWARFile#getLooseLibs <em>Loose Libs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Loose Libs</em>'.
	 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseWARFile#getLooseLibs()
	 * @see #getLooseWARFile()
	 * @generated
	 */
	EReference getLooseWARFile_LooseLibs();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	LooseconfigFactory getLooseconfigFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseApplicationImpl <em>Loose Application</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseApplicationImpl
		 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseconfigPackageImpl#getLooseApplication()
		 * @generated
		 */
		EClass LOOSE_APPLICATION = eINSTANCE.getLooseApplication();

		/**
		 * The meta object literal for the '<em><b>Loose Archives</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOOSE_APPLICATION__LOOSE_ARCHIVES = eINSTANCE.getLooseApplication_LooseArchives();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseArchiveImpl <em>Loose Archive</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseArchiveImpl
		 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseconfigPackageImpl#getLooseArchive()
		 * @generated
		 */
		EClass LOOSE_ARCHIVE = eINSTANCE.getLooseArchive();

		/**
		 * The meta object literal for the '<em><b>Uri</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOOSE_ARCHIVE__URI = eINSTANCE.getLooseArchive_Uri();

		/**
		 * The meta object literal for the '<em><b>Binaries Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOOSE_ARCHIVE__BINARIES_PATH = eINSTANCE.getLooseArchive_BinariesPath();

		/**
		 * The meta object literal for the '<em><b>Resources Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOOSE_ARCHIVE__RESOURCES_PATH = eINSTANCE.getLooseArchive_ResourcesPath();

		/**
		 * The meta object literal for the '<em><b>Loose App</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOOSE_ARCHIVE__LOOSE_APP = eINSTANCE.getLooseArchive_LooseApp();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseLibraryImpl <em>Loose Library</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseLibraryImpl
		 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseconfigPackageImpl#getLooseLibrary()
		 * @generated
		 */
		EClass LOOSE_LIBRARY = eINSTANCE.getLooseLibrary();

		/**
		 * The meta object literal for the '<em><b>Loose WAR</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOOSE_LIBRARY__LOOSE_WAR = eINSTANCE.getLooseLibrary_LooseWAR();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseModuleImpl <em>Loose Module</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseModuleImpl
		 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseconfigPackageImpl#getLooseModule()
		 * @generated
		 */
		EClass LOOSE_MODULE = eINSTANCE.getLooseModule();

		/**
		 * The meta object literal for the '<em><b>Alt DD</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOOSE_MODULE__ALT_DD = eINSTANCE.getLooseModule_AltDD();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseConfigurationImpl <em>Loose Configuration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseConfigurationImpl
		 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseconfigPackageImpl#getLooseConfiguration()
		 * @generated
		 */
		EClass LOOSE_CONFIGURATION = eINSTANCE.getLooseConfiguration();

		/**
		 * The meta object literal for the '<em><b>Applications</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOOSE_CONFIGURATION__APPLICATIONS = eINSTANCE.getLooseConfiguration_Applications();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseWARFileImpl <em>Loose WAR File</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseWARFileImpl
		 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseconfigPackageImpl#getLooseWARFile()
		 * @generated
		 */
		EClass LOOSE_WAR_FILE = eINSTANCE.getLooseWARFile();

		/**
		 * The meta object literal for the '<em><b>Loose Libs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOOSE_WAR_FILE__LOOSE_LIBS = eINSTANCE.getLooseWARFile_LooseLibs();

	}

} //LooseconfigPackage
