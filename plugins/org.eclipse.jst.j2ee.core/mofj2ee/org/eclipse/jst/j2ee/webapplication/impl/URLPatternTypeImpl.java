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
package org.eclipse.jst.j2ee.webapplication.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jst.j2ee.webapplication.URLPatternType;
import org.eclipse.jst.j2ee.webapplication.WebResourceCollection;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;

/**
 * @generated
 */
public class URLPatternTypeImpl extends EObjectImpl implements URLPatternType {

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
	protected EClass eStaticClass() {
		return WebapplicationPackage.eINSTANCE.getURLPatternType();
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public String getUrlPattern() {
		return urlPattern;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setUrlPattern(String newUrlPattern) {
		String oldUrlPattern = urlPattern;
		urlPattern = newUrlPattern;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.URL_PATTERN_TYPE__URL_PATTERN, oldUrlPattern, urlPattern));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public WebResourceCollection getResCollection() {
		if (eContainerFeatureID != WebapplicationPackage.URL_PATTERN_TYPE__RES_COLLECTION) return null;
		return (WebResourceCollection)eContainer;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setResCollection(WebResourceCollection newResCollection) {
		if (newResCollection != eContainer || (eContainerFeatureID != WebapplicationPackage.URL_PATTERN_TYPE__RES_COLLECTION && newResCollection != null)) {
			if (EcoreUtil.isAncestor(this, newResCollection))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newResCollection != null)
				msgs = ((InternalEObject)newResCollection).eInverseAdd(this, WebapplicationPackage.WEB_RESOURCE_COLLECTION__UR_LS, WebResourceCollection.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newResCollection, WebapplicationPackage.URL_PATTERN_TYPE__RES_COLLECTION, msgs);
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
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case WebapplicationPackage.URL_PATTERN_TYPE__RES_COLLECTION:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, WebapplicationPackage.URL_PATTERN_TYPE__RES_COLLECTION, msgs);
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
				case WebapplicationPackage.URL_PATTERN_TYPE__RES_COLLECTION:
					return eBasicSetContainer(null, WebapplicationPackage.URL_PATTERN_TYPE__RES_COLLECTION, msgs);
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
				case WebapplicationPackage.URL_PATTERN_TYPE__RES_COLLECTION:
					return eContainer.eInverseRemove(this, WebapplicationPackage.WEB_RESOURCE_COLLECTION__UR_LS, WebResourceCollection.class, msgs);
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
			case WebapplicationPackage.URL_PATTERN_TYPE__URL_PATTERN:
				return getUrlPattern();
			case WebapplicationPackage.URL_PATTERN_TYPE__RES_COLLECTION:
				return getResCollection();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case WebapplicationPackage.URL_PATTERN_TYPE__URL_PATTERN:
				return URL_PATTERN_EDEFAULT == null ? urlPattern != null : !URL_PATTERN_EDEFAULT.equals(urlPattern);
			case WebapplicationPackage.URL_PATTERN_TYPE__RES_COLLECTION:
				return getResCollection() != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case WebapplicationPackage.URL_PATTERN_TYPE__URL_PATTERN:
				setUrlPattern((String)newValue);
				return;
			case WebapplicationPackage.URL_PATTERN_TYPE__RES_COLLECTION:
				setResCollection((WebResourceCollection)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case WebapplicationPackage.URL_PATTERN_TYPE__URL_PATTERN:
				setUrlPattern(URL_PATTERN_EDEFAULT);
				return;
			case WebapplicationPackage.URL_PATTERN_TYPE__RES_COLLECTION:
				setResCollection((WebResourceCollection)null);
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
		result.append(" (urlPattern: ");//$NON-NLS-1$
		result.append(urlPattern);
		result.append(')');
		return result.toString();
	}

}














