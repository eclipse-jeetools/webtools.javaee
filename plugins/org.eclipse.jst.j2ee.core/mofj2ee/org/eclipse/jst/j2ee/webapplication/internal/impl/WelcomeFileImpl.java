/*******************************************************************************
 * Copyright (c) 2001, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.webapplication.internal.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jst.j2ee.common.internal.impl.J2EEEObjectImpl;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.jst.j2ee.webapplication.WelcomeFile;
import org.eclipse.jst.j2ee.webapplication.WelcomeFileList;

/**
 * The welcome-file element contains file name to use as a default welcome file, such as index.html
 * @generated
 */
public class WelcomeFileImpl extends J2EEEObjectImpl implements WelcomeFile {

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
	@Override
	protected EClass eStaticClass() {
		return WebapplicationPackage.Literals.WELCOME_FILE;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	@Override
	public String getWelcomeFile() {
		return welcomeFile;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public void setWelcomeFile(String newWelcomeFile) {
		String oldWelcomeFile = welcomeFile;
		welcomeFile = newWelcomeFile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.WELCOME_FILE__WELCOME_FILE, oldWelcomeFile, welcomeFile));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	@Override
	public WelcomeFileList getFileList() {
		if (eContainerFeatureID != WebapplicationPackage.WELCOME_FILE__FILE_LIST) return null;
		return (WelcomeFileList)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFileList(WelcomeFileList newFileList, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newFileList, WebapplicationPackage.WELCOME_FILE__FILE_LIST, msgs);
		return msgs;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public void setFileList(WelcomeFileList newFileList) {
		if (newFileList != eInternalContainer() || (eContainerFeatureID != WebapplicationPackage.WELCOME_FILE__FILE_LIST && newFileList != null)) {
			if (EcoreUtil.isAncestor(this, newFileList))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newFileList != null)
				msgs = ((InternalEObject)newFileList).eInverseAdd(this, WebapplicationPackage.WELCOME_FILE_LIST__FILE, WelcomeFileList.class, msgs);
			msgs = basicSetFileList(newFileList, msgs);
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
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WebapplicationPackage.WELCOME_FILE__FILE_LIST:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetFileList((WelcomeFileList)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WebapplicationPackage.WELCOME_FILE__FILE_LIST:
				return basicSetFileList(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID) {
			case WebapplicationPackage.WELCOME_FILE__FILE_LIST:
				return eInternalContainer().eInverseRemove(this, WebapplicationPackage.WELCOME_FILE_LIST__FILE, WelcomeFileList.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WebapplicationPackage.WELCOME_FILE__WELCOME_FILE:
				return getWelcomeFile();
			case WebapplicationPackage.WELCOME_FILE__FILE_LIST:
				return getFileList();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case WebapplicationPackage.WELCOME_FILE__WELCOME_FILE:
				setWelcomeFile((String)newValue);
				return;
			case WebapplicationPackage.WELCOME_FILE__FILE_LIST:
				setFileList((WelcomeFileList)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case WebapplicationPackage.WELCOME_FILE__WELCOME_FILE:
				setWelcomeFile(WELCOME_FILE_EDEFAULT);
				return;
			case WebapplicationPackage.WELCOME_FILE__FILE_LIST:
				setFileList((WelcomeFileList)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case WebapplicationPackage.WELCOME_FILE__WELCOME_FILE:
				return WELCOME_FILE_EDEFAULT == null ? welcomeFile != null : !WELCOME_FILE_EDEFAULT.equals(welcomeFile);
			case WebapplicationPackage.WELCOME_FILE__FILE_LIST:
				return getFileList() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (welcomeFile: "); //$NON-NLS-1$
		result.append(welcomeFile);
		result.append(')');
		return result.toString();
	}

}














