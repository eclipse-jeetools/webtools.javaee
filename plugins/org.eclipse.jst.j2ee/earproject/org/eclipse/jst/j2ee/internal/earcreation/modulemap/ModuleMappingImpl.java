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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.internal.earcreation.EARCreationResourceHandler;


/**
 * @lastgen class ModuleMappingImpl extends EObjectImpl implements ModuleMapping, EObject {}
 * * @deprecated
 * Use
 * <p>
 * Need to use the ModuleCore and WorkbenchComponent Api to get to the referenced modules
 * as with the Flexible project changes a .modulemaps file will not exist in an EAR module and
 * all the info that was captured in .modulemaps file will is now captured in the .wtpmodules file
 */
public class ModuleMappingImpl extends EObjectImpl implements ModuleMapping {
	/**
	 * The default value of the '{@link #getProjectName() <em>Project Name</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getProjectName()
	 * @generated
	 * @ordered
	 */
	protected static final String PROJECT_NAME_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String projectName = PROJECT_NAME_EDEFAULT;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected Module module = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected ModuleMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EClass eStaticClass() {
		return ModulemapPackage.eINSTANCE.getModuleMapping();
	}

	/**
	 * @generated This field/method will be replaced during code generation
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setProjectName(String newProjectName) {
		String oldProjectName = projectName;
		projectName = newProjectName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModulemapPackage.MODULE_MAPPING__PROJECT_NAME, oldProjectName, projectName));
	}

	/**
	 * @generated This field/method will be replaced during code generation
	 */
	public Module getModule() {
		if (module != null && module.eIsProxy()) {
			Module oldModule = module;
			module = (Module) EcoreUtil.resolve(module, this);
			if (module != oldModule) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModulemapPackage.MODULE_MAPPING__MODULE, oldModule, module));
			}
		}
		return module;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Module basicGetModule() {
		return module;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setModule(Module newModule) {
		Module oldModule = module;
		module = newModule;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModulemapPackage.MODULE_MAPPING__MODULE, oldModule, module));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature.getFeatureID(), eFeature.getContainerClass())) {
			case ModulemapPackage.MODULE_MAPPING__PROJECT_NAME :
				return getProjectName();
			case ModulemapPackage.MODULE_MAPPING__MODULE :
				if (resolve)
					return getModule();
				return basicGetModule();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature.getFeatureID(), eFeature.getContainerClass())) {
			case ModulemapPackage.MODULE_MAPPING__PROJECT_NAME :
				return PROJECT_NAME_EDEFAULT == null ? projectName != null : !PROJECT_NAME_EDEFAULT.equals(projectName);
			case ModulemapPackage.MODULE_MAPPING__MODULE :
				return module != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature.getFeatureID(), eFeature.getContainerClass())) {
			case ModulemapPackage.MODULE_MAPPING__PROJECT_NAME :
				setProjectName((String) newValue);
				return;
			case ModulemapPackage.MODULE_MAPPING__MODULE :
				setModule((Module) newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature.getFeatureID(), eFeature.getContainerClass())) {
			case ModulemapPackage.MODULE_MAPPING__PROJECT_NAME :
				setProjectName(PROJECT_NAME_EDEFAULT);
				return;
			case ModulemapPackage.MODULE_MAPPING__MODULE :
				setModule((Module) null);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(EARCreationResourceHandler.getString("ModuleMappingImpl_UI_0", new Object[]{projectName})); //$NON-NLS-1$
		return result.toString();
	}

} //ModuleMappingImpl
