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
package org.eclipse.jst.j2ee.internal.webapplication.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jst.j2ee.internal.webapplication.WebapplicationPackage;
import org.eclipse.jst.j2ee.webapplication.WelcomeFile;
import org.eclipse.jst.j2ee.webapplication.WelcomeFileList;

/**
 * The welcome-file element contains file name to use as a default welcome file, such as index.html

 */
public class WelcomeFileImpl extends EObjectImpl implements WelcomeFile, EObject {

	/**
	 * The default value of the '{@link #getWelcomeFile() <em>Welcome File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWelcomeFile()
	 * @generated
	 * @ordered
	 */
	protected static final String WELCOME_FILE_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String welcomeFile = WELCOME_FILE_EDEFAULT;
	public WelcomeFileImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return WebapplicationPackage.eINSTANCE.getWelcomeFile();
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public String getWelcomeFile() {
		return welcomeFile;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setWelcomeFile(String newWelcomeFile) {
		String oldWelcomeFile = welcomeFile;
		welcomeFile = newWelcomeFile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.WELCOME_FILE__WELCOME_FILE, oldWelcomeFile, welcomeFile));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public WelcomeFileList getFileList() {
		if (eContainerFeatureID != WebapplicationPackage.WELCOME_FILE__FILE_LIST) return null;
		return (WelcomeFileList)eContainer;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setFileList(WelcomeFileList newFileList) {
		if (newFileList != eContainer || (eContainerFeatureID != WebapplicationPackage.WELCOME_FILE__FILE_LIST && newFileList != null)) {
			if (EcoreUtil.isAncestor(this, newFileList))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newFileList != null)
				msgs = ((InternalEObject)newFileList).eInverseAdd(this, WebapplicationPackage.WELCOME_FILE_LIST__FILE, WelcomeFileList.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newFileList, WebapplicationPackage.WELCOME_FILE__FILE_LIST, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.WELCOME_FILE__FILE_LIST, newFileList, newFileList));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case WebapplicationPackage.WELCOME_FILE__FILE_LIST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, WebapplicationPackage.WELCOME_FILE__FILE_LIST, msgs);
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
				case WebapplicationPackage.WELCOME_FILE__FILE_LIST:
					return eBasicSetContainer(null, WebapplicationPackage.WELCOME_FILE__FILE_LIST, msgs);
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
				case WebapplicationPackage.WELCOME_FILE__FILE_LIST:
					return eContainer.eInverseRemove(this, WebapplicationPackage.WELCOME_FILE_LIST__FILE, WelcomeFileList.class, msgs);
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
			case WebapplicationPackage.WELCOME_FILE__WELCOME_FILE:
				return getWelcomeFile();
			case WebapplicationPackage.WELCOME_FILE__FILE_LIST:
				return getFileList();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case WebapplicationPackage.WELCOME_FILE__WELCOME_FILE:
				return WELCOME_FILE_EDEFAULT == null ? welcomeFile != null : !WELCOME_FILE_EDEFAULT.equals(welcomeFile);
			case WebapplicationPackage.WELCOME_FILE__FILE_LIST:
				return getFileList() != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case WebapplicationPackage.WELCOME_FILE__WELCOME_FILE:
				setWelcomeFile((String)newValue);
				return;
			case WebapplicationPackage.WELCOME_FILE__FILE_LIST:
				setFileList((WelcomeFileList)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case WebapplicationPackage.WELCOME_FILE__WELCOME_FILE:
				setWelcomeFile(WELCOME_FILE_EDEFAULT);
				return;
			case WebapplicationPackage.WELCOME_FILE__FILE_LIST:
				setFileList((WelcomeFileList)null);
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
		result.append(" (welcomeFile: ");//$NON-NLS-1$
		result.append(welcomeFile);
		result.append(')');
		return result.toString();
	}

}














