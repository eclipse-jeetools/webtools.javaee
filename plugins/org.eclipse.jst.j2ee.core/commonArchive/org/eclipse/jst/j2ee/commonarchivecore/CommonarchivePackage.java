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
package org.eclipse.jst.j2ee.commonarchivecore;


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;


/**
 * @lastgen interface CommonarchivePackage extends EPackage {}
 */
public interface CommonarchivePackage extends EPackage {
	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "commonarchivecore"; //$NON-NLS-1$

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTAINER = 7;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WAR_FILE = 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MODULE_FILE = 6;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EAR_FILE = 4;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MODULE_REF = 10;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_MODULE_REF = 11;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WEB_MODULE_REF = 12;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CLIENT_MODULE_REF = 13;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONNECTOR_MODULE_REF = 14;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */


	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int APPLICATION_CLIENT_FILE = 5;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_JAR_FILE = 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RAR_FILE = 9;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ARCHIVE = 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int FILE = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int FILE__URI = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int FILE__LAST_MODIFIED = 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int FILE__SIZE = 2;
	/**
	 * The feature id for the '<em><b>Directory Entry</b></em>' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FILE__DIRECTORY_ENTRY = 3;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int FILE__ORIGINAL_URI = 4;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int FILE__LOADING_CONTAINER = 5;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int FILE__CONTAINER = 6;

	/**
	 * The number of structural features of the the '<em>File</em>' class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FILE_FEATURE_COUNT = 7;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTAINER__URI = FILE__URI;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTAINER__LAST_MODIFIED = FILE__LAST_MODIFIED;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTAINER__SIZE = FILE__SIZE;
	/**
	 * The feature id for the '<em><b>Directory Entry</b></em>' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTAINER__DIRECTORY_ENTRY = FILE__DIRECTORY_ENTRY;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTAINER__ORIGINAL_URI = FILE__ORIGINAL_URI;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTAINER__LOADING_CONTAINER = FILE__LOADING_CONTAINER;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTAINER__CONTAINER = FILE__CONTAINER;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTAINER__FILES = FILE_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the the '<em>Container</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTAINER_FEATURE_COUNT = FILE_FEATURE_COUNT + 1;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ARCHIVE__URI = CONTAINER__URI;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ARCHIVE__LAST_MODIFIED = CONTAINER__LAST_MODIFIED;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ARCHIVE__SIZE = CONTAINER__SIZE;
	/**
	 * The feature id for the '<em><b>Directory Entry</b></em>' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARCHIVE__DIRECTORY_ENTRY = CONTAINER__DIRECTORY_ENTRY;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ARCHIVE__ORIGINAL_URI = CONTAINER__ORIGINAL_URI;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ARCHIVE__LOADING_CONTAINER = CONTAINER__LOADING_CONTAINER;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ARCHIVE__CONTAINER = CONTAINER__CONTAINER;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ARCHIVE__FILES = CONTAINER__FILES;
	/**
	 * The feature id for the '<em><b>Types</b></em>' attribute list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARCHIVE__TYPES = CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the the '<em>Archive</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARCHIVE_FEATURE_COUNT = CONTAINER_FEATURE_COUNT + 1;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MODULE_FILE__URI = ARCHIVE__URI;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MODULE_FILE__LAST_MODIFIED = ARCHIVE__LAST_MODIFIED;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MODULE_FILE__SIZE = ARCHIVE__SIZE;
	/**
	 * The feature id for the '<em><b>Directory Entry</b></em>' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MODULE_FILE__DIRECTORY_ENTRY = ARCHIVE__DIRECTORY_ENTRY;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MODULE_FILE__ORIGINAL_URI = ARCHIVE__ORIGINAL_URI;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MODULE_FILE__LOADING_CONTAINER = ARCHIVE__LOADING_CONTAINER;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MODULE_FILE__CONTAINER = ARCHIVE__CONTAINER;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MODULE_FILE__FILES = ARCHIVE__FILES;
	/**
	 * The feature id for the '<em><b>Types</b></em>' attribute list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MODULE_FILE__TYPES = ARCHIVE__TYPES;

	/**
	 * The number of structural features of the the '<em>Module File</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MODULE_FILE_FEATURE_COUNT = ARCHIVE_FEATURE_COUNT + 0;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_JAR_FILE__URI = MODULE_FILE__URI;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_JAR_FILE__LAST_MODIFIED = MODULE_FILE__LAST_MODIFIED;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_JAR_FILE__SIZE = MODULE_FILE__SIZE;
	/**
	 * The feature id for the '<em><b>Directory Entry</b></em>' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EJB_JAR_FILE__DIRECTORY_ENTRY = MODULE_FILE__DIRECTORY_ENTRY;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_JAR_FILE__ORIGINAL_URI = MODULE_FILE__ORIGINAL_URI;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_JAR_FILE__LOADING_CONTAINER = MODULE_FILE__LOADING_CONTAINER;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_JAR_FILE__CONTAINER = MODULE_FILE__CONTAINER;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_JAR_FILE__FILES = MODULE_FILE__FILES;
	/**
	 * The feature id for the '<em><b>Types</b></em>' attribute list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EJB_JAR_FILE__TYPES = MODULE_FILE__TYPES;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_JAR_FILE__DEPLOYMENT_DESCRIPTOR = MODULE_FILE_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the the '<em>EJB Jar File</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EJB_JAR_FILE_FEATURE_COUNT = MODULE_FILE_FEATURE_COUNT + 1;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WAR_FILE__URI = MODULE_FILE__URI;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WAR_FILE__LAST_MODIFIED = MODULE_FILE__LAST_MODIFIED;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WAR_FILE__SIZE = MODULE_FILE__SIZE;
	/**
	 * The feature id for the '<em><b>Directory Entry</b></em>' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int WAR_FILE__DIRECTORY_ENTRY = MODULE_FILE__DIRECTORY_ENTRY;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WAR_FILE__ORIGINAL_URI = MODULE_FILE__ORIGINAL_URI;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WAR_FILE__LOADING_CONTAINER = MODULE_FILE__LOADING_CONTAINER;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WAR_FILE__CONTAINER = MODULE_FILE__CONTAINER;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WAR_FILE__FILES = MODULE_FILE__FILES;
	/**
	 * The feature id for the '<em><b>Types</b></em>' attribute list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int WAR_FILE__TYPES = MODULE_FILE__TYPES;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WAR_FILE__DEPLOYMENT_DESCRIPTOR = MODULE_FILE_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the the '<em>WAR File</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int WAR_FILE_FEATURE_COUNT = MODULE_FILE_FEATURE_COUNT + 1;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EAR_FILE__URI = MODULE_FILE__URI;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EAR_FILE__LAST_MODIFIED = MODULE_FILE__LAST_MODIFIED;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EAR_FILE__SIZE = MODULE_FILE__SIZE;
	/**
	 * The feature id for the '<em><b>Directory Entry</b></em>' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EAR_FILE__DIRECTORY_ENTRY = MODULE_FILE__DIRECTORY_ENTRY;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EAR_FILE__ORIGINAL_URI = MODULE_FILE__ORIGINAL_URI;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EAR_FILE__LOADING_CONTAINER = MODULE_FILE__LOADING_CONTAINER;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EAR_FILE__CONTAINER = MODULE_FILE__CONTAINER;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EAR_FILE__FILES = MODULE_FILE__FILES;
	/**
	 * The feature id for the '<em><b>Types</b></em>' attribute list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EAR_FILE__TYPES = MODULE_FILE__TYPES;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EAR_FILE__MODULE_REFS = MODULE_FILE_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EAR_FILE__DEPLOYMENT_DESCRIPTOR = MODULE_FILE_FEATURE_COUNT + 1;
	/**
	 * The number of structural features of the the '<em>EAR File</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EAR_FILE_FEATURE_COUNT = MODULE_FILE_FEATURE_COUNT + 2;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int APPLICATION_CLIENT_FILE__URI = MODULE_FILE__URI;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int APPLICATION_CLIENT_FILE__LAST_MODIFIED = MODULE_FILE__LAST_MODIFIED;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int APPLICATION_CLIENT_FILE__SIZE = MODULE_FILE__SIZE;
	/**
	 * The feature id for the '<em><b>Directory Entry</b></em>' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT_FILE__DIRECTORY_ENTRY = MODULE_FILE__DIRECTORY_ENTRY;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int APPLICATION_CLIENT_FILE__ORIGINAL_URI = MODULE_FILE__ORIGINAL_URI;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int APPLICATION_CLIENT_FILE__LOADING_CONTAINER = MODULE_FILE__LOADING_CONTAINER;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int APPLICATION_CLIENT_FILE__CONTAINER = MODULE_FILE__CONTAINER;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int APPLICATION_CLIENT_FILE__FILES = MODULE_FILE__FILES;
	/**
	 * The feature id for the '<em><b>Types</b></em>' attribute list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT_FILE__TYPES = MODULE_FILE__TYPES;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int APPLICATION_CLIENT_FILE__DEPLOYMENT_DESCRIPTOR = MODULE_FILE_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the the '<em>Application Client File</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT_FILE_FEATURE_COUNT = MODULE_FILE_FEATURE_COUNT + 1;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int READ_ONLY_DIRECTORY = 8;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int READ_ONLY_DIRECTORY__URI = CONTAINER__URI;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int READ_ONLY_DIRECTORY__LAST_MODIFIED = CONTAINER__LAST_MODIFIED;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int READ_ONLY_DIRECTORY__SIZE = CONTAINER__SIZE;
	/**
	 * The feature id for the '<em><b>Directory Entry</b></em>' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int READ_ONLY_DIRECTORY__DIRECTORY_ENTRY = CONTAINER__DIRECTORY_ENTRY;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int READ_ONLY_DIRECTORY__ORIGINAL_URI = CONTAINER__ORIGINAL_URI;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int READ_ONLY_DIRECTORY__LOADING_CONTAINER = CONTAINER__LOADING_CONTAINER;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int READ_ONLY_DIRECTORY__CONTAINER = CONTAINER__CONTAINER;


	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int READ_ONLY_DIRECTORY__FILES = CONTAINER__FILES;
	/**
	 * The number of structural features of the the '<em>Read Only Directory</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int READ_ONLY_DIRECTORY_FEATURE_COUNT = CONTAINER_FEATURE_COUNT + 0;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RAR_FILE__URI = MODULE_FILE__URI;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RAR_FILE__LAST_MODIFIED = MODULE_FILE__LAST_MODIFIED;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RAR_FILE__SIZE = MODULE_FILE__SIZE;
	/**
	 * The feature id for the '<em><b>Directory Entry</b></em>' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RAR_FILE__DIRECTORY_ENTRY = MODULE_FILE__DIRECTORY_ENTRY;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RAR_FILE__ORIGINAL_URI = MODULE_FILE__ORIGINAL_URI;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RAR_FILE__LOADING_CONTAINER = MODULE_FILE__LOADING_CONTAINER;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RAR_FILE__CONTAINER = MODULE_FILE__CONTAINER;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RAR_FILE__FILES = MODULE_FILE__FILES;
	/**
	 * The feature id for the '<em><b>Types</b></em>' attribute list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RAR_FILE__TYPES = MODULE_FILE__TYPES;

	/**
	 * The feature id for the '<em><b>Deployment Descriptor</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RAR_FILE__DEPLOYMENT_DESCRIPTOR = MODULE_FILE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the the '<em>RAR File</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RAR_FILE_FEATURE_COUNT = MODULE_FILE_FEATURE_COUNT + 1;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MODULE_REF__MODULE_FILE = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MODULE_REF__EAR_FILE = 1;

	/**
	 * The feature id for the '<em><b>Module</b></em>' reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MODULE_REF__MODULE = 2;

	/**
	 * The number of structural features of the the '<em>Module Ref</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MODULE_REF_FEATURE_COUNT = 3;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_MODULE_REF__MODULE_FILE = MODULE_REF__MODULE_FILE;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_MODULE_REF__EAR_FILE = MODULE_REF__EAR_FILE;

	/**
	 * The feature id for the '<em><b>Module</b></em>' reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EJB_MODULE_REF__MODULE = MODULE_REF__MODULE;

	/**
	 * The number of structural features of the the '<em>EJB Module Ref</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EJB_MODULE_REF_FEATURE_COUNT = MODULE_REF_FEATURE_COUNT + 0;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WEB_MODULE_REF__MODULE_FILE = MODULE_REF__MODULE_FILE;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WEB_MODULE_REF__EAR_FILE = MODULE_REF__EAR_FILE;

	/**
	 * The feature id for the '<em><b>Module</b></em>' reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int WEB_MODULE_REF__MODULE = MODULE_REF__MODULE;

	/**
	 * The number of structural features of the the '<em>Web Module Ref</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int WEB_MODULE_REF_FEATURE_COUNT = MODULE_REF_FEATURE_COUNT + 0;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CLIENT_MODULE_REF__MODULE_FILE = MODULE_REF__MODULE_FILE;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CLIENT_MODULE_REF__EAR_FILE = MODULE_REF__EAR_FILE;

	/**
	 * The feature id for the '<em><b>Module</b></em>' reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CLIENT_MODULE_REF__MODULE = MODULE_REF__MODULE;

	/**
	 * The number of structural features of the the '<em>Client Module Ref</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CLIENT_MODULE_REF_FEATURE_COUNT = MODULE_REF_FEATURE_COUNT + 0;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONNECTOR_MODULE_REF__MODULE_FILE = MODULE_REF__MODULE_FILE;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONNECTOR_MODULE_REF__EAR_FILE = MODULE_REF__EAR_FILE;

	/**
	 * The feature id for the '<em><b>Module</b></em>' reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_MODULE_REF__MODULE = MODULE_REF__MODULE;

	/**
	 * The number of structural features of the the '<em>Connector Module Ref</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_MODULE_REF_FEATURE_COUNT = MODULE_REF_FEATURE_COUNT + 0;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	String eNS_URI = "commonarchive.xmi"; //$NON-NLS-1$
	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "org.eclipse.jst.j2ee.commonarchivecore"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	CommonarchivePackage eINSTANCE = org.eclipse.jst.j2ee.commonarchivecore.impl.CommonarchivePackageImpl.init();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return Container object
	 */
	EClass getContainer();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getContainer_Files();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return WARFile object
	 */
	EClass getWARFile();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getWARFile_DeploymentDescriptor();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return ModuleFile object
	 */
	EClass getModuleFile();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return EARFile object
	 */
	EClass getEARFile();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getEARFile_DeploymentDescriptor();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getEARFile_ModuleRefs();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return ModuleRef object
	 */
	EClass getModuleRef();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getModuleRef_ModuleFile();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getModuleRef_EarFile();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.eclipse.jst.j2ee.commonarchivecore.ModuleRef#getModule <em>Module</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Module</em>'.
	 * @see org.eclipse.jst.j2ee.commonarchivecore.ModuleRef#getModule()
	 * @see #getModuleRef()
	 * @generated
	 */
	EReference getModuleRef_Module();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return EJBModuleRef object
	 */
	EClass getEJBModuleRef();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return WebModuleRef object
	 */
	EClass getWebModuleRef();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return ClientModuleRef object
	 */
	EClass getClientModuleRef();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return ConnectorModuleRef object
	 */
	EClass getConnectorModuleRef();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return ApplicationClientFile object
	 */
	EClass getApplicationClientFile();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getApplicationClientFile_DeploymentDescriptor();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return EJBJarFile object
	 */
	EClass getEJBJarFile();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getEJBJarFile_DeploymentDescriptor();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return RARFile object
	 */
	EClass getRARFile();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.eclipse.jst.j2ee.commonarchivecore.RARFile#getDeploymentDescriptor <em>Deployment Descriptor</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Deployment Descriptor</em>'.
	 * @see org.eclipse.jst.j2ee.commonarchivecore.RARFile#getDeploymentDescriptor()
	 * @see #getRARFile()
	 * @generated
	 */
	EReference getRARFile_DeploymentDescriptor();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return Archive object
	 */
	EClass getArchive();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link org.eclipse.jst.j2ee.commonarchivecore.Archive#getTypes <em>Types</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Types</em>'.
	 * @see org.eclipse.jst.j2ee.commonarchivecore.Archive#getTypes()
	 * @see #getArchive()
	 * @generated
	 */
	EAttribute getArchive_Types();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return File object
	 */
	EClass getFile();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getFile_URI();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getFile_LastModified();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getFile_Size();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.jst.j2ee.commonarchivecore.File#isDirectoryEntry <em>Directory Entry</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Directory Entry</em>'.
	 * @see org.eclipse.jst.j2ee.commonarchivecore.File#isDirectoryEntry()
	 * @see #getFile()
	 * @generated
	 */
	EAttribute getFile_DirectoryEntry();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getFile_OriginalURI();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getFile_LoadingContainer();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getFile_Container();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return ReadOnlyDirectory object
	 */
	EClass getReadOnlyDirectory();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	CommonarchiveFactory getCommonarchiveFactory();

} //CommonarchivePackage



