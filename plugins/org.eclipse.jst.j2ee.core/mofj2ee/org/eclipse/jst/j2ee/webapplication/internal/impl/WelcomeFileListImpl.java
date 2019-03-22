/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.webapplication.internal.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.common.internal.impl.J2EEEObjectImpl;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.jst.j2ee.webapplication.WelcomeFile;
import org.eclipse.jst.j2ee.webapplication.WelcomeFileList;

/**
 * The welcome-file-list contains an ordered list of welcome files elements.
 * @generated
 */
public class WelcomeFileListImpl extends J2EEEObjectImpl implements WelcomeFileList {

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList file = null;
	public WelcomeFileListImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WebapplicationPackage.Literals.WELCOME_FILE_LIST;
	}

/**
 * addFileNamed method comment.
 */
public void addFileNamed(java.lang.String name) {
	WelcomeFile lfile = ((WebapplicationPackage)EPackage.Registry.INSTANCE.getEPackage(WebapplicationPackage.eNS_URI)).getWebapplicationFactory().createWelcomeFile();
	lfile.setWelcomeFile(name);
	getFile().add(lfile);
}
	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public WebApp getWebApp() {
		if (eContainerFeatureID != WebapplicationPackage.WELCOME_FILE_LIST__WEB_APP) return null;
		return (WebApp)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWebApp(WebApp newWebApp, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newWebApp, WebapplicationPackage.WELCOME_FILE_LIST__WEB_APP, msgs);
		return msgs;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setWebApp(WebApp newWebApp) {
		if (newWebApp != eInternalContainer() || (eContainerFeatureID != WebapplicationPackage.WELCOME_FILE_LIST__WEB_APP && newWebApp != null)) {
			if (EcoreUtil.isAncestor(this, newWebApp))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newWebApp != null)
				msgs = ((InternalEObject)newWebApp).eInverseAdd(this, WebapplicationPackage.WEB_APP__FILE_LIST, WebApp.class, msgs);
			msgs = basicSetWebApp(newWebApp, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.WELCOME_FILE_LIST__WEB_APP, newWebApp, newWebApp));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public EList getFile() {
		if (file == null) {
			file = new EObjectContainmentWithInverseEList(WelcomeFile.class, this, WebapplicationPackage.WELCOME_FILE_LIST__FILE, WebapplicationPackage.WELCOME_FILE__FILE_LIST);
		}
		return file;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WebapplicationPackage.WELCOME_FILE_LIST__WEB_APP:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetWebApp((WebApp)otherEnd, msgs);
			case WebapplicationPackage.WELCOME_FILE_LIST__FILE:
				return ((InternalEList)getFile()).basicAdd(otherEnd, msgs);
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
			case WebapplicationPackage.WELCOME_FILE_LIST__WEB_APP:
				return basicSetWebApp(null, msgs);
			case WebapplicationPackage.WELCOME_FILE_LIST__FILE:
				return ((InternalEList)getFile()).basicRemove(otherEnd, msgs);
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
			case WebapplicationPackage.WELCOME_FILE_LIST__WEB_APP:
				return eInternalContainer().eInverseRemove(this, WebapplicationPackage.WEB_APP__FILE_LIST, WebApp.class, msgs);
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
			case WebapplicationPackage.WELCOME_FILE_LIST__WEB_APP:
				return getWebApp();
			case WebapplicationPackage.WELCOME_FILE_LIST__FILE:
				return getFile();
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
			case WebapplicationPackage.WELCOME_FILE_LIST__WEB_APP:
				setWebApp((WebApp)newValue);
				return;
			case WebapplicationPackage.WELCOME_FILE_LIST__FILE:
				getFile().clear();
				getFile().addAll((Collection)newValue);
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
			case WebapplicationPackage.WELCOME_FILE_LIST__WEB_APP:
				setWebApp((WebApp)null);
				return;
			case WebapplicationPackage.WELCOME_FILE_LIST__FILE:
				getFile().clear();
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
			case WebapplicationPackage.WELCOME_FILE_LIST__WEB_APP:
				return getWebApp() != null;
			case WebapplicationPackage.WELCOME_FILE_LIST__FILE:
				return file != null && !file.isEmpty();
		}
		return super.eIsSet(featureID);
	}

}














