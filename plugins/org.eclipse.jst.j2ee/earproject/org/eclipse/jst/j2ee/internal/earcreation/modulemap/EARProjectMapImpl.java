/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.earcreation.modulemap;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;



/**
 * @lastgen class EARProjectMapImpl extends EObjectImpl implements EARProjectMap, EObject {}
 * @deprecated
 * Use
 * <p>
 * 		Need to use the ModuleCore and WorkbenchComponent Api to get to the referenced modules
 * as with the Flexible project changes a .modulemaps file will not exist in an EAR module and
 * all the info that was captured in .modulemaps file will is now captured in the .component file
 */
public class EARProjectMapImpl extends EObjectImpl implements EARProjectMap {
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList mappings = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList utilityJARMappings = null;



	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EARProjectMapImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EClass eStaticClass() {
		return ModulemapPackage.eINSTANCE.getEARProjectMap();
	}

	/**
	 * @generated This field/method will be replaced during code generation
	 */
	public EList getMappings() {
		if (mappings == null) {
			mappings = new EObjectContainmentEList(ModuleMapping.class, this, ModulemapPackage.EAR_PROJECT_MAP__MAPPINGS);
		}
		return mappings;
	}

	/**
	 * @generated This field/method will be replaced during code generation
	 */
	public EList getUtilityJARMappings() {
		if (utilityJARMappings == null) {
			utilityJARMappings = new EObjectContainmentEList(UtilityJARMapping.class, this, ModulemapPackage.EAR_PROJECT_MAP__UTILITY_JAR_MAPPINGS);
		}
		return utilityJARMappings;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (baseClass == null ? featureID : eDerivedStructuralFeatureID(featureID, baseClass)) {
				case ModulemapPackage.EAR_PROJECT_MAP__MAPPINGS :
					return ((InternalEList) getMappings()).basicRemove(otherEnd, msgs);
				case ModulemapPackage.EAR_PROJECT_MAP__UTILITY_JAR_MAPPINGS :
					return ((InternalEList) getUtilityJARMappings()).basicRemove(otherEnd, msgs);
				default :
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature.getFeatureID(), eFeature.getContainerClass())) {
			case ModulemapPackage.EAR_PROJECT_MAP__MAPPINGS :
				return getMappings();
			case ModulemapPackage.EAR_PROJECT_MAP__UTILITY_JAR_MAPPINGS :
				return getUtilityJARMappings();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature.getFeatureID(), eFeature.getContainerClass())) {
			case ModulemapPackage.EAR_PROJECT_MAP__MAPPINGS :
				getMappings().clear();
				getMappings().addAll((Collection) newValue);
				return;
			case ModulemapPackage.EAR_PROJECT_MAP__UTILITY_JAR_MAPPINGS :
				getUtilityJARMappings().clear();
				getUtilityJARMappings().addAll((Collection) newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature.getFeatureID(), eFeature.getContainerClass())) {
			case ModulemapPackage.EAR_PROJECT_MAP__MAPPINGS :
				getMappings().clear();
				return;
			case ModulemapPackage.EAR_PROJECT_MAP__UTILITY_JAR_MAPPINGS :
				getUtilityJARMappings().clear();
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature.getFeatureID(), eFeature.getContainerClass())) {
			case ModulemapPackage.EAR_PROJECT_MAP__MAPPINGS :
				return mappings != null && !mappings.isEmpty();
			case ModulemapPackage.EAR_PROJECT_MAP__UTILITY_JAR_MAPPINGS :
				return utilityJARMappings != null && !utilityJARMappings.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //EARProjectMapImpl
