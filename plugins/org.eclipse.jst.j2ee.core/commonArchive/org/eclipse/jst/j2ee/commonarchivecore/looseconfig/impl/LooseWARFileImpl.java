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
package org.eclipse.jst.j2ee.commonarchivecore.looseconfig.impl;


import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.LooseApplication;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.LooseLibrary;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.LooseModule;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.LooseWARFile;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.LooseconfigPackage;


public class LooseWARFileImpl extends LooseModuleImpl implements LooseWARFile, LooseModule {
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList looseLibs = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected LooseWARFileImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LooseconfigPackage.eINSTANCE.getLooseWARFile();
	}

	public boolean isWAR() {
		return true;
	}

	/**
	 * @generated This field/method will be replaced during code generation
	 */
	public EList getLooseLibs() {
		if (looseLibs == null) {
			looseLibs = new EObjectContainmentWithInverseEList(LooseLibrary.class, this, LooseconfigPackage.LOOSE_WAR_FILE__LOOSE_LIBS, LooseconfigPackage.LOOSE_LIBRARY__LOOSE_WAR);
		}
		return looseLibs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case LooseconfigPackage.LOOSE_WAR_FILE__LOOSE_APP :
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, LooseconfigPackage.LOOSE_WAR_FILE__LOOSE_APP, msgs);
				case LooseconfigPackage.LOOSE_WAR_FILE__LOOSE_LIBS :
					return ((InternalEList) getLooseLibs()).basicAdd(otherEnd, msgs);
				default :
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case LooseconfigPackage.LOOSE_WAR_FILE__LOOSE_APP :
					return eBasicSetContainer(null, LooseconfigPackage.LOOSE_WAR_FILE__LOOSE_APP, msgs);
				case LooseconfigPackage.LOOSE_WAR_FILE__LOOSE_LIBS :
					return ((InternalEList) getLooseLibs()).basicRemove(otherEnd, msgs);
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
	public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
		if (eContainerFeatureID >= 0) {
			switch (eContainerFeatureID) {
				case LooseconfigPackage.LOOSE_WAR_FILE__LOOSE_APP :
					return eContainer.eInverseRemove(this, LooseconfigPackage.LOOSE_APPLICATION__LOOSE_ARCHIVES, LooseApplication.class, msgs);
				default :
					return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case LooseconfigPackage.LOOSE_WAR_FILE__URI :
				return getUri();
			case LooseconfigPackage.LOOSE_WAR_FILE__BINARIES_PATH :
				return getBinariesPath();
			case LooseconfigPackage.LOOSE_WAR_FILE__RESOURCES_PATH :
				return getResourcesPath();
			case LooseconfigPackage.LOOSE_WAR_FILE__LOOSE_APP :
				return getLooseApp();
			case LooseconfigPackage.LOOSE_WAR_FILE__ALT_DD :
				return getAltDD();
			case LooseconfigPackage.LOOSE_WAR_FILE__LOOSE_LIBS :
				return getLooseLibs();
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
			case LooseconfigPackage.LOOSE_WAR_FILE__URI :
				setUri((String) newValue);
				return;
			case LooseconfigPackage.LOOSE_WAR_FILE__BINARIES_PATH :
				setBinariesPath((String) newValue);
				return;
			case LooseconfigPackage.LOOSE_WAR_FILE__RESOURCES_PATH :
				setResourcesPath((String) newValue);
				return;
			case LooseconfigPackage.LOOSE_WAR_FILE__LOOSE_APP :
				setLooseApp((LooseApplication) newValue);
				return;
			case LooseconfigPackage.LOOSE_WAR_FILE__ALT_DD :
				setAltDD((String) newValue);
				return;
			case LooseconfigPackage.LOOSE_WAR_FILE__LOOSE_LIBS :
				getLooseLibs().clear();
				getLooseLibs().addAll((Collection) newValue);
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
			case LooseconfigPackage.LOOSE_WAR_FILE__URI :
				setUri(URI_EDEFAULT);
				return;
			case LooseconfigPackage.LOOSE_WAR_FILE__BINARIES_PATH :
				setBinariesPath(BINARIES_PATH_EDEFAULT);
				return;
			case LooseconfigPackage.LOOSE_WAR_FILE__RESOURCES_PATH :
				setResourcesPath(RESOURCES_PATH_EDEFAULT);
				return;
			case LooseconfigPackage.LOOSE_WAR_FILE__LOOSE_APP :
				setLooseApp((LooseApplication) null);
				return;
			case LooseconfigPackage.LOOSE_WAR_FILE__ALT_DD :
				setAltDD(ALT_DD_EDEFAULT);
				return;
			case LooseconfigPackage.LOOSE_WAR_FILE__LOOSE_LIBS :
				getLooseLibs().clear();
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
			case LooseconfigPackage.LOOSE_WAR_FILE__URI :
				return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
			case LooseconfigPackage.LOOSE_WAR_FILE__BINARIES_PATH :
				return BINARIES_PATH_EDEFAULT == null ? binariesPath != null : !BINARIES_PATH_EDEFAULT.equals(binariesPath);
			case LooseconfigPackage.LOOSE_WAR_FILE__RESOURCES_PATH :
				return RESOURCES_PATH_EDEFAULT == null ? resourcesPath != null : !RESOURCES_PATH_EDEFAULT.equals(resourcesPath);
			case LooseconfigPackage.LOOSE_WAR_FILE__LOOSE_APP :
				return getLooseApp() != null;
			case LooseconfigPackage.LOOSE_WAR_FILE__ALT_DD :
				return ALT_DD_EDEFAULT == null ? altDD != null : !ALT_DD_EDEFAULT.equals(altDD);
			case LooseconfigPackage.LOOSE_WAR_FILE__LOOSE_LIBS :
				return looseLibs != null && !looseLibs.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //LooseWARFileImpl



