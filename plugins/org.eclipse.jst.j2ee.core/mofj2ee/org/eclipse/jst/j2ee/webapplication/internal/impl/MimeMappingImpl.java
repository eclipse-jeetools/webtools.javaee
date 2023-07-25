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
import org.eclipse.jst.j2ee.webapplication.MimeMapping;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;

/**
 * The mime-mapping element defines a mapping between an extension and a mime type.
 * @generated
 */
public class MimeMappingImpl extends J2EEEObjectImpl implements MimeMapping {

	/**
	 * The default value of the '{@link #getExtension() <em>Extension</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtension()
	 * @generated
	 * @ordered
	 */
	protected static final String EXTENSION_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String extension = EXTENSION_EDEFAULT;
	/**
	 * The default value of the '{@link #getMimeType() <em>Mime Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMimeType()
	 * @generated
	 * @ordered
	 */
	protected static final String MIME_TYPE_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String mimeType = MIME_TYPE_EDEFAULT;
	public MimeMappingImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WebapplicationPackage.Literals.MIME_MAPPING;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The extension element contains a string describing an extension. example: "txt"
	 */
	@Override
	public String getExtension() {
		return extension;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public void setExtension(String newExtension) {
		String oldExtension = extension;
		extension = newExtension;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.MIME_MAPPING__EXTENSION, oldExtension, extension));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The mime-type element contains a defined mime type. example: "text//plain"
	 */
	@Override
	public String getMimeType() {
		return mimeType;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public void setMimeType(String newMimeType) {
		String oldMimeType = mimeType;
		mimeType = newMimeType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.MIME_MAPPING__MIME_TYPE, oldMimeType, mimeType));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	@Override
	public WebApp getWebApp() {
		if (eContainerFeatureID != WebapplicationPackage.MIME_MAPPING__WEB_APP) return null;
		return (WebApp)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWebApp(WebApp newWebApp, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newWebApp, WebapplicationPackage.MIME_MAPPING__WEB_APP, msgs);
		return msgs;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public void setWebApp(WebApp newWebApp) {
		if (newWebApp != eInternalContainer() || (eContainerFeatureID != WebapplicationPackage.MIME_MAPPING__WEB_APP && newWebApp != null)) {
			if (EcoreUtil.isAncestor(this, newWebApp))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newWebApp != null)
				msgs = ((InternalEObject)newWebApp).eInverseAdd(this, WebapplicationPackage.WEB_APP__MIME_MAPPINGS, WebApp.class, msgs);
			msgs = basicSetWebApp(newWebApp, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.MIME_MAPPING__WEB_APP, newWebApp, newWebApp));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WebapplicationPackage.MIME_MAPPING__WEB_APP:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetWebApp((WebApp)otherEnd, msgs);
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
			case WebapplicationPackage.MIME_MAPPING__WEB_APP:
				return basicSetWebApp(null, msgs);
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
			case WebapplicationPackage.MIME_MAPPING__WEB_APP:
				return eInternalContainer().eInverseRemove(this, WebapplicationPackage.WEB_APP__MIME_MAPPINGS, WebApp.class, msgs);
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
			case WebapplicationPackage.MIME_MAPPING__EXTENSION:
				return getExtension();
			case WebapplicationPackage.MIME_MAPPING__MIME_TYPE:
				return getMimeType();
			case WebapplicationPackage.MIME_MAPPING__WEB_APP:
				return getWebApp();
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
			case WebapplicationPackage.MIME_MAPPING__EXTENSION:
				setExtension((String)newValue);
				return;
			case WebapplicationPackage.MIME_MAPPING__MIME_TYPE:
				setMimeType((String)newValue);
				return;
			case WebapplicationPackage.MIME_MAPPING__WEB_APP:
				setWebApp((WebApp)newValue);
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
			case WebapplicationPackage.MIME_MAPPING__EXTENSION:
				setExtension(EXTENSION_EDEFAULT);
				return;
			case WebapplicationPackage.MIME_MAPPING__MIME_TYPE:
				setMimeType(MIME_TYPE_EDEFAULT);
				return;
			case WebapplicationPackage.MIME_MAPPING__WEB_APP:
				setWebApp((WebApp)null);
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
			case WebapplicationPackage.MIME_MAPPING__EXTENSION:
				return EXTENSION_EDEFAULT == null ? extension != null : !EXTENSION_EDEFAULT.equals(extension);
			case WebapplicationPackage.MIME_MAPPING__MIME_TYPE:
				return MIME_TYPE_EDEFAULT == null ? mimeType != null : !MIME_TYPE_EDEFAULT.equals(mimeType);
			case WebapplicationPackage.MIME_MAPPING__WEB_APP:
				return getWebApp() != null;
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
		result.append(" (extension: "); //$NON-NLS-1$
		result.append(extension);
		result.append(", mimeType: "); //$NON-NLS-1$
		result.append(mimeType);
		result.append(')');
		return result.toString();
	}

}














