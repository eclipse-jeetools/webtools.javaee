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



/**
 * @lastgen class ModulemapSwitch {}
 */
public class ModulemapSwitch {
	/**
	 * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static ModulemapPackage modelPackage;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public ModulemapSwitch() {
		if (modelPackage == null) {
			modelPackage = ModulemapPackage.eINSTANCE;
		}
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object doSwitch(EObject theEObject) {
		EClass theEClass = theEObject.eClass();
		if (theEClass.eContainer() == modelPackage) {
			switch (theEClass.getClassifierID()) {
				case ModulemapPackage.MODULE_MAPPING : {
					ModuleMapping moduleMapping = (ModuleMapping) theEObject;
					Object result = caseModuleMapping(moduleMapping);
					if (result == null)
						result = defaultCase(theEObject);
					return result;
				}
				case ModulemapPackage.EAR_PROJECT_MAP : {
					EARProjectMap earProjectMap = (EARProjectMap) theEObject;
					Object result = caseEARProjectMap(earProjectMap);
					if (result == null)
						result = defaultCase(theEObject);
					return result;
				}
				case ModulemapPackage.UTILITY_JAR_MAPPING : {
					UtilityJARMapping utilityJARMapping = (UtilityJARMapping) theEObject;
					Object result = caseUtilityJARMapping(utilityJARMapping);
					if (result == null)
						result = defaultCase(theEObject);
					return result;
				}
				default :
					return defaultCase(theEObject);
			}
		}
		return defaultCase(theEObject);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object caseEARProjectMap(EARProjectMap object) {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object caseModuleMapping(ModuleMapping object) {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object caseUtilityJARMapping(UtilityJARMapping object) {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object defaultCase(EObject object) {
		return null;
	}

} //ModulemapSwitch
