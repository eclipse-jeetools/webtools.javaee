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
import org.eclipse.jst.j2ee.application.ConnectorModule;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.internal.application.ApplicationPackage;

/**
 * Specifies the URI of a resource adapter archive file, relative to the top level of the application package.
 */
public class ConnectorModuleImpl extends ModuleImpl implements ConnectorModule, Module{

	public ConnectorModuleImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return ApplicationPackage.eINSTANCE.getConnectorModule();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case ApplicationPackage.CONNECTOR_MODULE__APPLICATION:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, ApplicationPackage.CONNECTOR_MODULE__APPLICATION, msgs);
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
				case ApplicationPackage.CONNECTOR_MODULE__APPLICATION:
					return eBasicSetContainer(null, ApplicationPackage.CONNECTOR_MODULE__APPLICATION, msgs);
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
				case ApplicationPackage.CONNECTOR_MODULE__APPLICATION:
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
			case ApplicationPackage.CONNECTOR_MODULE__URI:
				return getUri();
			case ApplicationPackage.CONNECTOR_MODULE__ALT_DD:
				return getAltDD();
			case ApplicationPackage.CONNECTOR_MODULE__APPLICATION:
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
			case ApplicationPackage.CONNECTOR_MODULE__URI:
				setUri((String)newValue);
				return;
			case ApplicationPackage.CONNECTOR_MODULE__ALT_DD:
				setAltDD((String)newValue);
				return;
			case ApplicationPackage.CONNECTOR_MODULE__APPLICATION:
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
			case ApplicationPackage.CONNECTOR_MODULE__URI:
				setUri(URI_EDEFAULT);
				return;
			case ApplicationPackage.CONNECTOR_MODULE__ALT_DD:
				setAltDD(ALT_DD_EDEFAULT);
				return;
			case ApplicationPackage.CONNECTOR_MODULE__APPLICATION:
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
			case ApplicationPackage.CONNECTOR_MODULE__URI:
				return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
			case ApplicationPackage.CONNECTOR_MODULE__ALT_DD:
				return ALT_DD_EDEFAULT == null ? altDD != null : !ALT_DD_EDEFAULT.equals(altDD);
			case ApplicationPackage.CONNECTOR_MODULE__APPLICATION:
				return getApplication() != null;
		}
		return eDynamicIsSet(eFeature);
	}

public boolean isConnectorModule() {
		return true;
}
}





