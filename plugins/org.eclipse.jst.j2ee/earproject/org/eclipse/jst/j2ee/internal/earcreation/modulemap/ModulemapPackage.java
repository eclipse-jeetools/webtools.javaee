/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.earcreation.modulemap;


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;


/**
 * @lastgen interface ModulemapPackage extends EPackage {}
 */
public interface ModulemapPackage extends EPackage {
	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "modulemap"; //$NON-NLS-1$

	/**
	 * @generated This field/method will be replaced during code generation.
	 */


	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EAR_PROJECT_MAP = 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MODULE_MAPPING = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MODULE_MAPPING__PROJECT_NAME = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MODULE_MAPPING__MODULE = 1;

	/**
	 * The number of structural features of the the '<em>Module Mapping</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MODULE_MAPPING_FEATURE_COUNT = 2;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EAR_PROJECT_MAP__MAPPINGS = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EAR_PROJECT_MAP__UTILITY_JAR_MAPPINGS = 1;

	/**
	 * The number of structural features of the the '<em>EAR Project Map</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EAR_PROJECT_MAP_FEATURE_COUNT = 2;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int UTILITY_JAR_MAPPING = 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int UTILITY_JAR_MAPPING__PROJECT_NAME = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int UTILITY_JAR_MAPPING__URI = 1;


	/**
	 * The number of structural features of the the '<em>Utility JAR Mapping</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int UTILITY_JAR_MAPPING_FEATURE_COUNT = 2;


	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	String eNS_URI = "modulemap.xmi"; //$NON-NLS-1$
	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "modulemap"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	ModulemapPackage eINSTANCE = org.eclipse.jst.j2ee.internal.earcreation.modulemap.ModulemapPackageImpl.init();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return EARProjectMap object
	 */
	EClass getEARProjectMap();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getEARProjectMap_Mappings();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getEARProjectMap_UtilityJARMappings();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return ModuleMapping object
	 */
	EClass getModuleMapping();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getModuleMapping_ProjectName();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getModuleMapping_Module();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return UtilityJARMapping object
	 */
	EClass getUtilityJARMapping();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getUtilityJARMapping_ProjectName();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getUtilityJARMapping_Uri();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	ModulemapFactory getModulemapFactory();

} //ModulemapPackage
