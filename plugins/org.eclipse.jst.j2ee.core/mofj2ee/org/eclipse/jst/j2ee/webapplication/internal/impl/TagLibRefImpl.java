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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jst.j2ee.common.internal.impl.J2EEEObjectImpl;
import org.eclipse.jst.j2ee.webapplication.TagLibRef;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;

/**
 * The taglib element is used to describe a JSP tag library.
 * @generated
 */
public class TagLibRefImpl extends J2EEEObjectImpl implements TagLibRef {

	/**
	 * The default value of the '{@link #getTaglibURI() <em>Taglib URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTaglibURI()
	 * @generated
	 * @ordered
	 */
	protected static final String TAGLIB_URI_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String taglibURI = TAGLIB_URI_EDEFAULT;
	/**
	 * The default value of the '{@link #getTaglibLocation() <em>Taglib Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTaglibLocation()
	 * @generated
	 * @ordered
	 */
	protected static final String TAGLIB_LOCATION_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String taglibLocation = TAGLIB_LOCATION_EDEFAULT;
	public TagLibRefImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WebapplicationPackage.Literals.TAG_LIB_REF;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The taglib-uri element describes a URI, relative to the location of the web.xml 
	 * document, identifying a Tag Library used in the Web Application.
	 */
	public String getTaglibURI() {
		return taglibURI;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setTaglibURI(String newTaglibURI) {
		String oldTaglibURI = taglibURI;
		taglibURI = newTaglibURI;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.TAG_LIB_REF__TAGLIB_URI, oldTaglibURI, taglibURI));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The taglib-location element contains the location (as a resource relative to
	 * the root of the web application) where to find the Tag Libary Description file
	 * for the tag library.
	 */
	public String getTaglibLocation() {
		return taglibLocation;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setTaglibLocation(String newTaglibLocation) {
		String oldTaglibLocation = taglibLocation;
		taglibLocation = newTaglibLocation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.TAG_LIB_REF__TAGLIB_LOCATION, oldTaglibLocation, taglibLocation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WebApp getWebApp() {
		if (eContainerFeatureID != WebapplicationPackage.TAG_LIB_REF__WEB_APP) return null;
		return (WebApp)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWebApp(WebApp newWebApp, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newWebApp, WebapplicationPackage.TAG_LIB_REF__WEB_APP, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWebApp(WebApp newWebApp) {
		if (newWebApp != eInternalContainer() || (eContainerFeatureID != WebapplicationPackage.TAG_LIB_REF__WEB_APP && newWebApp != null)) {
			if (EcoreUtil.isAncestor(this, newWebApp))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newWebApp != null)
				msgs = ((InternalEObject)newWebApp).eInverseAdd(this, WebapplicationPackage.WEB_APP__TAG_LIBS, WebApp.class, msgs);
			msgs = basicSetWebApp(newWebApp, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.TAG_LIB_REF__WEB_APP, newWebApp, newWebApp));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WebapplicationPackage.TAG_LIB_REF__WEB_APP:
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
			case WebapplicationPackage.TAG_LIB_REF__WEB_APP:
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
			case WebapplicationPackage.TAG_LIB_REF__WEB_APP:
				return eInternalContainer().eInverseRemove(this, WebapplicationPackage.WEB_APP__TAG_LIBS, WebApp.class, msgs);
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
			case WebapplicationPackage.TAG_LIB_REF__TAGLIB_URI:
				return getTaglibURI();
			case WebapplicationPackage.TAG_LIB_REF__TAGLIB_LOCATION:
				return getTaglibLocation();
			case WebapplicationPackage.TAG_LIB_REF__WEB_APP:
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
			case WebapplicationPackage.TAG_LIB_REF__TAGLIB_URI:
				setTaglibURI((String)newValue);
				return;
			case WebapplicationPackage.TAG_LIB_REF__TAGLIB_LOCATION:
				setTaglibLocation((String)newValue);
				return;
			case WebapplicationPackage.TAG_LIB_REF__WEB_APP:
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
			case WebapplicationPackage.TAG_LIB_REF__TAGLIB_URI:
				setTaglibURI(TAGLIB_URI_EDEFAULT);
				return;
			case WebapplicationPackage.TAG_LIB_REF__TAGLIB_LOCATION:
				setTaglibLocation(TAGLIB_LOCATION_EDEFAULT);
				return;
			case WebapplicationPackage.TAG_LIB_REF__WEB_APP:
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
			case WebapplicationPackage.TAG_LIB_REF__TAGLIB_URI:
				return TAGLIB_URI_EDEFAULT == null ? taglibURI != null : !TAGLIB_URI_EDEFAULT.equals(taglibURI);
			case WebapplicationPackage.TAG_LIB_REF__TAGLIB_LOCATION:
				return TAGLIB_LOCATION_EDEFAULT == null ? taglibLocation != null : !TAGLIB_LOCATION_EDEFAULT.equals(taglibLocation);
			case WebapplicationPackage.TAG_LIB_REF__WEB_APP:
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
		result.append(" (taglibURI: "); //$NON-NLS-1$
		result.append(taglibURI);
		result.append(", taglibLocation: "); //$NON-NLS-1$
		result.append(taglibLocation);
		result.append(')');
		return result.toString();
	}

}














