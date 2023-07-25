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
import org.eclipse.jst.j2ee.webapplication.URLPatternType;
import org.eclipse.jst.j2ee.webapplication.WebResourceCollection;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;

/**
 * @generated
 */
public class URLPatternTypeImpl extends J2EEEObjectImpl implements URLPatternType {

	/**
	 * The default value of the '{@link #getUrlPattern() <em>Url Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUrlPattern()
	 * @generated
	 * @ordered
	 */
	protected static final String URL_PATTERN_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String urlPattern = URL_PATTERN_EDEFAULT;
	public URLPatternTypeImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WebapplicationPackage.Literals.URL_PATTERN_TYPE;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	@Override
	public String getUrlPattern() {
		return urlPattern;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public void setUrlPattern(String newUrlPattern) {
		String oldUrlPattern = urlPattern;
		urlPattern = newUrlPattern;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.URL_PATTERN_TYPE__URL_PATTERN, oldUrlPattern, urlPattern));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	@Override
	public WebResourceCollection getResCollection() {
		if (eContainerFeatureID != WebapplicationPackage.URL_PATTERN_TYPE__RES_COLLECTION) return null;
		return (WebResourceCollection)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetResCollection(WebResourceCollection newResCollection, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newResCollection, WebapplicationPackage.URL_PATTERN_TYPE__RES_COLLECTION, msgs);
		return msgs;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public void setResCollection(WebResourceCollection newResCollection) {
		if (newResCollection != eInternalContainer() || (eContainerFeatureID != WebapplicationPackage.URL_PATTERN_TYPE__RES_COLLECTION && newResCollection != null)) {
			if (EcoreUtil.isAncestor(this, newResCollection))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newResCollection != null)
				msgs = ((InternalEObject)newResCollection).eInverseAdd(this, WebapplicationPackage.WEB_RESOURCE_COLLECTION__UR_LS, WebResourceCollection.class, msgs);
			msgs = basicSetResCollection(newResCollection, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.URL_PATTERN_TYPE__RES_COLLECTION, newResCollection, newResCollection));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WebapplicationPackage.URL_PATTERN_TYPE__RES_COLLECTION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetResCollection((WebResourceCollection)otherEnd, msgs);
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
			case WebapplicationPackage.URL_PATTERN_TYPE__RES_COLLECTION:
				return basicSetResCollection(null, msgs);
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
			case WebapplicationPackage.URL_PATTERN_TYPE__RES_COLLECTION:
				return eInternalContainer().eInverseRemove(this, WebapplicationPackage.WEB_RESOURCE_COLLECTION__UR_LS, WebResourceCollection.class, msgs);
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
			case WebapplicationPackage.URL_PATTERN_TYPE__URL_PATTERN:
				return getUrlPattern();
			case WebapplicationPackage.URL_PATTERN_TYPE__RES_COLLECTION:
				return getResCollection();
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
			case WebapplicationPackage.URL_PATTERN_TYPE__URL_PATTERN:
				setUrlPattern((String)newValue);
				return;
			case WebapplicationPackage.URL_PATTERN_TYPE__RES_COLLECTION:
				setResCollection((WebResourceCollection)newValue);
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
			case WebapplicationPackage.URL_PATTERN_TYPE__URL_PATTERN:
				setUrlPattern(URL_PATTERN_EDEFAULT);
				return;
			case WebapplicationPackage.URL_PATTERN_TYPE__RES_COLLECTION:
				setResCollection((WebResourceCollection)null);
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
			case WebapplicationPackage.URL_PATTERN_TYPE__URL_PATTERN:
				return URL_PATTERN_EDEFAULT == null ? urlPattern != null : !URL_PATTERN_EDEFAULT.equals(urlPattern);
			case WebapplicationPackage.URL_PATTERN_TYPE__RES_COLLECTION:
				return getResCollection() != null;
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
		result.append(" (urlPattern: "); //$NON-NLS-1$
		result.append(urlPattern);
		result.append(')');
		return result.toString();
	}

}














