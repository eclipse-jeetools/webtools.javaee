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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;



/**
 * @lastgen class ModulemapAdapterFactory extends AdapterFactoryImpl {}
 * @deprecated
 * Use
 * <p>
 * 		Need to use the ModuleCore and WorkbenchComponent Api to get to the referenced modules
 * as with the Flexible project changes a .modulemaps file will not exist in an EAR module and
 * all the info that was captured in .modulemaps file will is now captured in the .wtpmodules file
 */
public class ModulemapAdapterFactory extends AdapterFactoryImpl {
	/**
	 * @generated This field/method will be replaced during code generation.
	 */

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected static ModulemapPackage modelPackage;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public ModulemapAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ModulemapPackage.eINSTANCE;
		}
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject) object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch the delegates to the <code>createXXX</code> methods. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ModulemapSwitch modelSwitch = new ModulemapSwitch() {
		public Object caseModuleMapping(ModuleMapping object) {
			return createModuleMappingAdapter();
		}

		public Object caseEARProjectMap(EARProjectMap object) {
			return createEARProjectMapAdapter();
		}

		public Object caseUtilityJARMapping(UtilityJARMapping object) {
			return createUtilityJARMappingAdapter();
		}

		public Object defaultCase(EObject object) {
			return createEObjectAdapter();
		}
	};

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createAdapter(Notifier target) {
		return (Adapter) modelSwitch.doSwitch((EObject) target);
	}


	/**
	 * By default create methods return null so that we can easily ignore cases. It's useful to
	 * ignore a case when inheritance will catch all the cases anyway.
	 */

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createEARProjectMapAdapter() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createModuleMappingAdapter() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createUtilityJARMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case. <!-- begin-user-doc --> This default
	 * implementation returns null. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //ModulemapAdapterFactory
