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
package org.eclipse.jst.j2ee.commonarchivecore.internal.looseconfig.impl;


import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.commonarchivecore.internal.looseconfig.LooseApplication;
import org.eclipse.jst.j2ee.commonarchivecore.internal.looseconfig.LooseConfiguration;
import org.eclipse.jst.j2ee.commonarchivecore.internal.looseconfig.LooseconfigPackage;


public class LooseConfigurationImpl extends EObjectImpl implements LooseConfiguration, EObject {
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList applications = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected LooseConfigurationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LooseconfigPackage.eINSTANCE.getLooseConfiguration();
	}

	/**
	 * @generated This field/method will be replaced during code generation
	 */
	public EList getApplications() {
		if (applications == null) {
			applications = new EObjectContainmentEList(LooseApplication.class, this, LooseconfigPackage.LOOSE_CONFIGURATION__APPLICATIONS);
		}
		return applications;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case LooseconfigPackage.LOOSE_CONFIGURATION__APPLICATIONS :
					return ((InternalEList) getApplications()).basicRemove(otherEnd, msgs);
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
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case LooseconfigPackage.LOOSE_CONFIGURATION__APPLICATIONS :
				return getApplications();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case LooseconfigPackage.LOOSE_CONFIGURATION__APPLICATIONS :
				getApplications().clear();
				getApplications().addAll((Collection) newValue);
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
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case LooseconfigPackage.LOOSE_CONFIGURATION__APPLICATIONS :
				getApplications().clear();
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
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case LooseconfigPackage.LOOSE_CONFIGURATION__APPLICATIONS :
				return applications != null && !applications.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //LooseConfigurationImpl



