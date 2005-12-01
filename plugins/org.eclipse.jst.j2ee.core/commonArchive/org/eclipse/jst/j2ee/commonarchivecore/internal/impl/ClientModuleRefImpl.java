/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal.impl;


import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.client.ApplicationClient;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ClientModuleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ArchiveWrappedException;


public class ClientModuleRefImpl extends ModuleRefImpl implements ClientModuleRef, ModuleRef {
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected ClientModuleRefImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return CommonarchivePackage.eINSTANCE.getClientModuleRef();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case CommonarchivePackage.CLIENT_MODULE_REF__EAR_FILE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, CommonarchivePackage.CLIENT_MODULE_REF__EAR_FILE, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case CommonarchivePackage.CLIENT_MODULE_REF__EAR_FILE:
					return eBasicSetContainer(null, CommonarchivePackage.CLIENT_MODULE_REF__EAR_FILE, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
		if (eContainerFeatureID >= 0) {
			switch (eContainerFeatureID) {
				case CommonarchivePackage.CLIENT_MODULE_REF__EAR_FILE:
					return eContainer.eInverseRemove(this, CommonarchivePackage.EAR_FILE__MODULE_REFS, EARFile.class, msgs);
				default:
					return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonarchivePackage.CLIENT_MODULE_REF__MODULE_FILE:
				if (resolve) return getModuleFile();
				return basicGetModuleFile();
			case CommonarchivePackage.CLIENT_MODULE_REF__EAR_FILE:
				return getEarFile();
			case CommonarchivePackage.CLIENT_MODULE_REF__MODULE:
				if (resolve) return getModule();
				return basicGetModule();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonarchivePackage.CLIENT_MODULE_REF__MODULE_FILE:
				setModuleFile((ModuleFile)newValue);
				return;
			case CommonarchivePackage.CLIENT_MODULE_REF__EAR_FILE:
				setEarFile((EARFile)newValue);
				return;
			case CommonarchivePackage.CLIENT_MODULE_REF__MODULE:
				setModule((Module)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonarchivePackage.CLIENT_MODULE_REF__MODULE_FILE:
				setModuleFile((ModuleFile)null);
				return;
			case CommonarchivePackage.CLIENT_MODULE_REF__EAR_FILE:
				setEarFile((EARFile)null);
				return;
			case CommonarchivePackage.CLIENT_MODULE_REF__MODULE:
				setModule((Module)null);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonarchivePackage.CLIENT_MODULE_REF__MODULE_FILE:
				return moduleFile != null;
			case CommonarchivePackage.CLIENT_MODULE_REF__EAR_FILE:
				return getEarFile() != null;
			case CommonarchivePackage.CLIENT_MODULE_REF__MODULE:
				return module != null;
		}
		return eDynamicIsSet(eFeature);
	}

	public ApplicationClient getApplicationClient() throws ArchiveWrappedException {
		return (ApplicationClient) getDeploymentDescriptor();
	}


	/*
	 * @see ModuleRef#isClient()
	 */
	public boolean isClient() {
		return true;
	}

} //ClientModuleRefImpl



