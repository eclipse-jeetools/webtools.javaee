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
package org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseApplication;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseArchive;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseLibrary;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseWARFile;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseconfigPackage;


public class LooseLibraryImpl extends LooseArchiveImpl implements LooseLibrary, LooseArchive {
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected LooseLibraryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LooseconfigPackage.eINSTANCE.getLooseLibrary();
	}

	/**
	 * @generated This field/method will be replaced during code generation
	 */
	public LooseWARFile getLooseWAR() {
		if (eContainerFeatureID != LooseconfigPackage.LOOSE_LIBRARY__LOOSE_WAR) return null;
		return (LooseWARFile)eContainer;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setLooseWAR(LooseWARFile newLooseWAR) {
		if (newLooseWAR != eContainer || (eContainerFeatureID != LooseconfigPackage.LOOSE_LIBRARY__LOOSE_WAR && newLooseWAR != null)) {
			if (EcoreUtil.isAncestor(this, newLooseWAR))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newLooseWAR != null)
				msgs = ((InternalEObject)newLooseWAR).eInverseAdd(this, LooseconfigPackage.LOOSE_WAR_FILE__LOOSE_LIBS, LooseWARFile.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newLooseWAR, LooseconfigPackage.LOOSE_LIBRARY__LOOSE_WAR, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LooseconfigPackage.LOOSE_LIBRARY__LOOSE_WAR, newLooseWAR, newLooseWAR));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case LooseconfigPackage.LOOSE_LIBRARY__LOOSE_APP:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, LooseconfigPackage.LOOSE_LIBRARY__LOOSE_APP, msgs);
				case LooseconfigPackage.LOOSE_LIBRARY__LOOSE_WAR:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, LooseconfigPackage.LOOSE_LIBRARY__LOOSE_WAR, msgs);
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
				case LooseconfigPackage.LOOSE_LIBRARY__LOOSE_APP:
					return eBasicSetContainer(null, LooseconfigPackage.LOOSE_LIBRARY__LOOSE_APP, msgs);
				case LooseconfigPackage.LOOSE_LIBRARY__LOOSE_WAR:
					return eBasicSetContainer(null, LooseconfigPackage.LOOSE_LIBRARY__LOOSE_WAR, msgs);
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
				case LooseconfigPackage.LOOSE_LIBRARY__LOOSE_APP:
					return eContainer.eInverseRemove(this, LooseconfigPackage.LOOSE_APPLICATION__LOOSE_ARCHIVES, LooseApplication.class, msgs);
				case LooseconfigPackage.LOOSE_LIBRARY__LOOSE_WAR:
					return eContainer.eInverseRemove(this, LooseconfigPackage.LOOSE_WAR_FILE__LOOSE_LIBS, LooseWARFile.class, msgs);
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
			case LooseconfigPackage.LOOSE_LIBRARY__URI:
				return getUri();
			case LooseconfigPackage.LOOSE_LIBRARY__BINARIES_PATH:
				return getBinariesPath();
			case LooseconfigPackage.LOOSE_LIBRARY__RESOURCES_PATH:
				return getResourcesPath();
			case LooseconfigPackage.LOOSE_LIBRARY__LOOSE_APP:
				return getLooseApp();
			case LooseconfigPackage.LOOSE_LIBRARY__LOOSE_WAR:
				return getLooseWAR();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case LooseconfigPackage.LOOSE_LIBRARY__URI:
				return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
			case LooseconfigPackage.LOOSE_LIBRARY__BINARIES_PATH:
				return BINARIES_PATH_EDEFAULT == null ? binariesPath != null : !BINARIES_PATH_EDEFAULT.equals(binariesPath);
			case LooseconfigPackage.LOOSE_LIBRARY__RESOURCES_PATH:
				return RESOURCES_PATH_EDEFAULT == null ? resourcesPath != null : !RESOURCES_PATH_EDEFAULT.equals(resourcesPath);
			case LooseconfigPackage.LOOSE_LIBRARY__LOOSE_APP:
				return getLooseApp() != null;
			case LooseconfigPackage.LOOSE_LIBRARY__LOOSE_WAR:
				return getLooseWAR() != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case LooseconfigPackage.LOOSE_LIBRARY__URI:
				setUri((String)newValue);
				return;
			case LooseconfigPackage.LOOSE_LIBRARY__BINARIES_PATH:
				setBinariesPath((String)newValue);
				return;
			case LooseconfigPackage.LOOSE_LIBRARY__RESOURCES_PATH:
				setResourcesPath((String)newValue);
				return;
			case LooseconfigPackage.LOOSE_LIBRARY__LOOSE_APP:
				setLooseApp((LooseApplication)newValue);
				return;
			case LooseconfigPackage.LOOSE_LIBRARY__LOOSE_WAR:
				setLooseWAR((LooseWARFile)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case LooseconfigPackage.LOOSE_LIBRARY__URI:
				setUri(URI_EDEFAULT);
				return;
			case LooseconfigPackage.LOOSE_LIBRARY__BINARIES_PATH:
				setBinariesPath(BINARIES_PATH_EDEFAULT);
				return;
			case LooseconfigPackage.LOOSE_LIBRARY__RESOURCES_PATH:
				setResourcesPath(RESOURCES_PATH_EDEFAULT);
				return;
			case LooseconfigPackage.LOOSE_LIBRARY__LOOSE_APP:
				setLooseApp((LooseApplication)null);
				return;
			case LooseconfigPackage.LOOSE_LIBRARY__LOOSE_WAR:
				setLooseWAR((LooseWARFile)null);
				return;
		}
		eDynamicUnset(eFeature);
	}

} //LooseLibraryImpl



