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
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.impl.EcorePackageImpl;
import org.eclipse.jem.java.impl.JavaRefPackageImpl;
import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.internal.application.impl.ApplicationPackageImpl;
import org.eclipse.jst.j2ee.internal.common.impl.CommonPackageImpl;



/**
 * @lastgen class ModulemapPackageImpl extends EPackageImpl implements ModulemapPackage, EPackage {}
 * @deprecated
 * Use
 * <p>
 * 		Need to use the ModuleCore and WorkbenchComponent Api to get to the referenced modules
 * as with the Flexible project changes a .modulemaps file will not exist in an EAR module and
 * all the info that was captured in .modulemaps file will is now captured in the .wtpmodules file
 */
public class ModulemapPackageImpl extends EPackageImpl implements ModulemapPackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass moduleMappingEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass earProjectMapEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass utilityJARMappingEClass = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	private ModulemapPackageImpl() {
		super(eNS_URI, ModulemapFactory.eINSTANCE);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static ModulemapPackage init() {
		// Initialize simple dependencies
		CommonPackageImpl.init();
		JavaRefPackageImpl.init();
		ApplicationPackageImpl.init();
		EcorePackageImpl.init();

		// Obtain or create and register package and interdependencies
		ModulemapPackageImpl theModulemapPackage = (ModulemapPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new ModulemapPackageImpl());

		// Step 1: create meta-model objects
		theModulemapPackage.createPackageContents();

		// Step 2: complete initialization
		theModulemapPackage.initializePackageContents();

		return theModulemapPackage;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getEARProjectMap() {
		return earProjectMapEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getEARProjectMap_Mappings() {
		return (EReference) earProjectMapEClass.getEReferences().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getEARProjectMap_UtilityJARMappings() {
		return (EReference) earProjectMapEClass.getEReferences().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getModuleMapping() {
		return moduleMappingEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getModuleMapping_ProjectName() {
		return (EAttribute) moduleMappingEClass.getEAttributes().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getModuleMapping_Module() {
		return (EReference) moduleMappingEClass.getEReferences().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getUtilityJARMapping() {
		return utilityJARMappingEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getUtilityJARMapping_ProjectName() {
		return (EAttribute) utilityJARMappingEClass.getEAttributes().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getUtilityJARMapping_Uri() {
		return (EAttribute) utilityJARMappingEClass.getEAttributes().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public ModulemapFactory getModulemapFactory() {
		return (ModulemapFactory) getEFactoryInstance();
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
		moduleMappingEClass = createEClass(MODULE_MAPPING);
		createEAttribute(moduleMappingEClass, MODULE_MAPPING__PROJECT_NAME);
		createEReference(moduleMappingEClass, MODULE_MAPPING__MODULE);

		earProjectMapEClass = createEClass(EAR_PROJECT_MAP);
		createEReference(earProjectMapEClass, EAR_PROJECT_MAP__MAPPINGS);
		createEReference(earProjectMapEClass, EAR_PROJECT_MAP__UTILITY_JAR_MAPPINGS);

		utilityJARMappingEClass = createEClass(UTILITY_JAR_MAPPING);
		createEAttribute(utilityJARMappingEClass, UTILITY_JAR_MAPPING__PROJECT_NAME);
		createEAttribute(utilityJARMappingEClass, UTILITY_JAR_MAPPING__URI);
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

		// Obtain other dependent packages
		//		CommonPackageImpl theCommonPackage =
		// (CommonPackageImpl)EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI);
		//		JavaRefPackageImpl theJavaRefPackage =
		// (JavaRefPackageImpl)EPackage.Registry.INSTANCE.getEPackage(JavaRefPackage.eNS_URI);
		ApplicationPackageImpl theApplicationPackage = (ApplicationPackageImpl) EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI);
		//		EcorePackageImpl theEcorePackage =
		// (EcorePackageImpl)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(moduleMappingEClass, ModuleMapping.class, "ModuleMapping", !IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$
		initEAttribute(getModuleMapping_ProjectName(), ecorePackage.getEString(), "projectName", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, true, false); //$NON-NLS-1$
		initEReference(getModuleMapping_Module(), theApplicationPackage.getModule(), null, "module", null, 1, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, true, false); //$NON-NLS-1$

		initEClass(earProjectMapEClass, EARProjectMap.class, "EARProjectMap", !IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$
		initEReference(getEARProjectMap_Mappings(), this.getModuleMapping(), null, "mappings", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, true, false); //$NON-NLS-1$
		initEReference(getEARProjectMap_UtilityJARMappings(), this.getUtilityJARMapping(), null, "utilityJARMappings", null, 1, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, true, false); //$NON-NLS-1$

		initEClass(utilityJARMappingEClass, UtilityJARMapping.class, "UtilityJARMapping", !IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$
		initEAttribute(getUtilityJARMapping_ProjectName(), ecorePackage.getEString(), "projectName", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, true, false); //$NON-NLS-1$
		initEAttribute(getUtilityJARMapping_Uri(), ecorePackage.getEString(), "uri", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, true, false); //$NON-NLS-1$

		// Create resource
		createResource(eNS_URI);
	}
} //ModulemapPackageImpl
