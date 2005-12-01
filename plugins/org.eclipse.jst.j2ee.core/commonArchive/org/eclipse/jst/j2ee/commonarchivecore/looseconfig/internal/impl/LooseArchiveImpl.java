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
package org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseApplication;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseArchive;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseconfigPackage;

public class LooseArchiveImpl extends EObjectImpl implements LooseArchive, EObject {
	/**
	 * The default value of the '{@link #getUri() <em>Uri</em>}' attribute.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @see #getUri()
	 * @generated
	 * @ordered
	 */
	protected static final String URI_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String uri = URI_EDEFAULT;
	/**
	 * The default value of the '{@link #getBinariesPath() <em>Binaries Path</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getBinariesPath()
	 * @generated
	 * @ordered
	 */
	protected static final String BINARIES_PATH_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String binariesPath = BINARIES_PATH_EDEFAULT;
	/**
	 * The default value of the '{@link #getResourcesPath() <em>Resources Path</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getResourcesPath()
	 * @generated
	 * @ordered
	 */
	protected static final String RESOURCES_PATH_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String resourcesPath = RESOURCES_PATH_EDEFAULT;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected LooseArchiveImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LooseconfigPackage.eINSTANCE.getLooseArchive();
	}

	/**
	 * @generated This field/method will be replaced during code generation
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setUri(String newUri) {
		String oldUri = uri;
		uri = newUri;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LooseconfigPackage.LOOSE_ARCHIVE__URI, oldUri, uri));
	}

	public boolean isEAR() {
		return false;
	}

	public boolean isWAR() {
		return false;
	}

	/**
	 * @generated This field/method will be replaced during code generation
	 */
	public String getBinariesPath() {
		return binariesPath;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setBinariesPath(String newBinariesPath) {
		String oldBinariesPath = binariesPath;
		binariesPath = newBinariesPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LooseconfigPackage.LOOSE_ARCHIVE__BINARIES_PATH, oldBinariesPath, binariesPath));
	}

	/**
	 * @generated This field/method will be replaced during code generation
	 */
	public String getResourcesPath() {
		return resourcesPath;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setResourcesPath(String newResourcesPath) {
		String oldResourcesPath = resourcesPath;
		resourcesPath = newResourcesPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LooseconfigPackage.LOOSE_ARCHIVE__RESOURCES_PATH, oldResourcesPath, resourcesPath));
	}

	/**
	 * @generated This field/method will be replaced during code generation
	 */
	public LooseApplication getLooseApp() {
		if (eContainerFeatureID != LooseconfigPackage.LOOSE_ARCHIVE__LOOSE_APP) return null;
		return (LooseApplication)eContainer;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setLooseApp(LooseApplication newLooseApp) {
		if (newLooseApp != eContainer || (eContainerFeatureID != LooseconfigPackage.LOOSE_ARCHIVE__LOOSE_APP && newLooseApp != null)) {
			if (EcoreUtil.isAncestor(this, newLooseApp))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newLooseApp != null)
				msgs = ((InternalEObject)newLooseApp).eInverseAdd(this, LooseconfigPackage.LOOSE_APPLICATION__LOOSE_ARCHIVES, LooseApplication.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newLooseApp, LooseconfigPackage.LOOSE_ARCHIVE__LOOSE_APP, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LooseconfigPackage.LOOSE_ARCHIVE__LOOSE_APP, newLooseApp, newLooseApp));
	}

	public boolean isModule() {
		return false;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case LooseconfigPackage.LOOSE_ARCHIVE__LOOSE_APP:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, LooseconfigPackage.LOOSE_ARCHIVE__LOOSE_APP, msgs);
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
				case LooseconfigPackage.LOOSE_ARCHIVE__LOOSE_APP:
					return eBasicSetContainer(null, LooseconfigPackage.LOOSE_ARCHIVE__LOOSE_APP, msgs);
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
				case LooseconfigPackage.LOOSE_ARCHIVE__LOOSE_APP:
					return eContainer.eInverseRemove(this, LooseconfigPackage.LOOSE_APPLICATION__LOOSE_ARCHIVES, LooseApplication.class, msgs);
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
			case LooseconfigPackage.LOOSE_ARCHIVE__URI:
				return getUri();
			case LooseconfigPackage.LOOSE_ARCHIVE__BINARIES_PATH:
				return getBinariesPath();
			case LooseconfigPackage.LOOSE_ARCHIVE__RESOURCES_PATH:
				return getResourcesPath();
			case LooseconfigPackage.LOOSE_ARCHIVE__LOOSE_APP:
				return getLooseApp();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case LooseconfigPackage.LOOSE_ARCHIVE__URI:
				return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
			case LooseconfigPackage.LOOSE_ARCHIVE__BINARIES_PATH:
				return BINARIES_PATH_EDEFAULT == null ? binariesPath != null : !BINARIES_PATH_EDEFAULT.equals(binariesPath);
			case LooseconfigPackage.LOOSE_ARCHIVE__RESOURCES_PATH:
				return RESOURCES_PATH_EDEFAULT == null ? resourcesPath != null : !RESOURCES_PATH_EDEFAULT.equals(resourcesPath);
			case LooseconfigPackage.LOOSE_ARCHIVE__LOOSE_APP:
				return getLooseApp() != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case LooseconfigPackage.LOOSE_ARCHIVE__URI:
				setUri((String)newValue);
				return;
			case LooseconfigPackage.LOOSE_ARCHIVE__BINARIES_PATH:
				setBinariesPath((String)newValue);
				return;
			case LooseconfigPackage.LOOSE_ARCHIVE__RESOURCES_PATH:
				setResourcesPath((String)newValue);
				return;
			case LooseconfigPackage.LOOSE_ARCHIVE__LOOSE_APP:
				setLooseApp((LooseApplication)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case LooseconfigPackage.LOOSE_ARCHIVE__URI:
				setUri(URI_EDEFAULT);
				return;
			case LooseconfigPackage.LOOSE_ARCHIVE__BINARIES_PATH:
				setBinariesPath(BINARIES_PATH_EDEFAULT);
				return;
			case LooseconfigPackage.LOOSE_ARCHIVE__RESOURCES_PATH:
				setResourcesPath(RESOURCES_PATH_EDEFAULT);
				return;
			case LooseconfigPackage.LOOSE_ARCHIVE__LOOSE_APP:
				setLooseApp((LooseApplication)null);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (uri: ");
		result.append(uri);
		result.append(", binariesPath: ");
		result.append(binariesPath);
		result.append(", resourcesPath: ");
		result.append(resourcesPath);
		result.append(')');
		return result.toString();
	}

} //LooseArchiveImpl



