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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EFactoryImpl;



/**
 * @lastgen class ModulemapFactoryImpl extends EFactoryImpl implements ModulemapFactory, EFactory {}
 */
public class ModulemapFactoryImpl extends EFactoryImpl implements ModulemapFactory {

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public ModulemapFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ModulemapPackage.MODULE_MAPPING :
				return createModuleMapping();
			case ModulemapPackage.EAR_PROJECT_MAP :
				return createEARProjectMap();
			case ModulemapPackage.UTILITY_JAR_MAPPING :
				return createUtilityJARMapping();
		}
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EARProjectMap createEARProjectMap() {
		EARProjectMapImpl earProjectMap = new EARProjectMapImpl();
		return earProjectMap;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public ModuleMapping createModuleMapping() {
		ModuleMappingImpl moduleMapping = new ModuleMappingImpl();
		return moduleMapping;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public UtilityJARMapping createUtilityJARMapping() {
		UtilityJARMappingImpl utilityJARMapping = new UtilityJARMappingImpl();
		return utilityJARMapping;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public ModulemapPackage getModulemapPackage() {
		return (ModulemapPackage) getEPackage();
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static ModulemapPackage getPackage() {
		return ModulemapPackage.eINSTANCE;
	}

} //ModulemapFactoryImpl
