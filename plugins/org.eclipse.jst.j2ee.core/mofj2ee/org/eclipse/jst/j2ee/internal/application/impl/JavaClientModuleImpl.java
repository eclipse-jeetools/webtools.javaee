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
package org.eclipse.jst.j2ee.internal.application.impl;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.application.JavaClientModule;
import org.eclipse.jst.j2ee.application.Module;

/**
 * specifies the URI of a java application client module, relative to the top level of the application package.

 */
public class JavaClientModuleImpl extends ModuleImpl implements JavaClientModule, Module {

	public JavaClientModuleImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return ApplicationPackage.eINSTANCE.getJavaClientModule();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case ApplicationPackage.JAVA_CLIENT_MODULE__APPLICATION:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, ApplicationPackage.JAVA_CLIENT_MODULE__APPLICATION, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case ApplicationPackage.JAVA_CLIENT_MODULE__APPLICATION:
					return eBasicSetContainer(null, ApplicationPackage.JAVA_CLIENT_MODULE__APPLICATION, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
		if (eContainerFeatureID >= 0) {
			switch (eContainerFeatureID) {
				case ApplicationPackage.JAVA_CLIENT_MODULE__APPLICATION:
					return eContainer.eInverseRemove(this, ApplicationPackage.APPLICATION__MODULES, Application.class, msgs);
				default:
					return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case ApplicationPackage.JAVA_CLIENT_MODULE__URI:
				return getUri();
			case ApplicationPackage.JAVA_CLIENT_MODULE__ALT_DD:
				return getAltDD();
			case ApplicationPackage.JAVA_CLIENT_MODULE__APPLICATION:
				return getApplication();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case ApplicationPackage.JAVA_CLIENT_MODULE__URI:
				setUri((String)newValue);
				return;
			case ApplicationPackage.JAVA_CLIENT_MODULE__ALT_DD:
				setAltDD((String)newValue);
				return;
			case ApplicationPackage.JAVA_CLIENT_MODULE__APPLICATION:
				setApplication((Application)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case ApplicationPackage.JAVA_CLIENT_MODULE__URI:
				setUri(URI_EDEFAULT);
				return;
			case ApplicationPackage.JAVA_CLIENT_MODULE__ALT_DD:
				setAltDD(ALT_DD_EDEFAULT);
				return;
			case ApplicationPackage.JAVA_CLIENT_MODULE__APPLICATION:
				setApplication((Application)null);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case ApplicationPackage.JAVA_CLIENT_MODULE__URI:
				return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
			case ApplicationPackage.JAVA_CLIENT_MODULE__ALT_DD:
				return ALT_DD_EDEFAULT == null ? altDD != null : !ALT_DD_EDEFAULT.equals(altDD);
			case ApplicationPackage.JAVA_CLIENT_MODULE__APPLICATION:
				return getApplication() != null;
		}
		return eDynamicIsSet(eFeature);
	}

public boolean isJavaModule() {
	return true;
}
}





