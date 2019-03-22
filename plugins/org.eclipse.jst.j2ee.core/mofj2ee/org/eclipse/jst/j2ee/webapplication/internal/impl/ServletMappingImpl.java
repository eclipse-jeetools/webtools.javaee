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
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.ServletMapping;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;

/**
 * The servlet-mapping element defines a mapping between a servlet and a url pattern
 * @generated
 */
public class ServletMappingImpl extends J2EEEObjectImpl implements ServletMapping {

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
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected Servlet servlet = null;
	public ServletMappingImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WebapplicationPackage.Literals.SERVLET_MAPPING;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The url-pattern element contains the url pattern of the mapping. Must follow
	 * the rules specified in Section 10 of the Servlet API Specification.
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
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.SERVLET_MAPPING__URL_PATTERN, oldUrlPattern, urlPattern));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.SERVLET_MAPPING__NAME, oldName, name));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public WebApp getWebApp() {
		if (eContainerFeatureID != WebapplicationPackage.SERVLET_MAPPING__WEB_APP) return null;
		return (WebApp)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWebApp(WebApp newWebApp, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newWebApp, WebapplicationPackage.SERVLET_MAPPING__WEB_APP, msgs);
		return msgs;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setWebApp(WebApp newWebApp) {
		if (newWebApp != eInternalContainer() || (eContainerFeatureID != WebapplicationPackage.SERVLET_MAPPING__WEB_APP && newWebApp != null)) {
			if (EcoreUtil.isAncestor(this, newWebApp))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newWebApp != null)
				msgs = ((InternalEObject)newWebApp).eInverseAdd(this, WebapplicationPackage.WEB_APP__SERVLET_MAPPINGS, WebApp.class, msgs);
			msgs = basicSetWebApp(newWebApp, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.SERVLET_MAPPING__WEB_APP, newWebApp, newWebApp));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public Servlet getServlet() {
		if (servlet != null && servlet.eIsProxy()) {
			InternalEObject oldServlet = (InternalEObject)servlet;
			servlet = (Servlet)eResolveProxy(oldServlet);
			if (servlet != oldServlet) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, WebapplicationPackage.SERVLET_MAPPING__SERVLET, oldServlet, servlet));
			}
		}
		return servlet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Servlet basicGetServlet() {
		return servlet;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setServlet(Servlet newServlet) {
		Servlet oldServlet = servlet;
		servlet = newServlet;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.SERVLET_MAPPING__SERVLET, oldServlet, servlet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WebapplicationPackage.SERVLET_MAPPING__WEB_APP:
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
			case WebapplicationPackage.SERVLET_MAPPING__WEB_APP:
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
			case WebapplicationPackage.SERVLET_MAPPING__WEB_APP:
				return eInternalContainer().eInverseRemove(this, WebapplicationPackage.WEB_APP__SERVLET_MAPPINGS, WebApp.class, msgs);
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
			case WebapplicationPackage.SERVLET_MAPPING__URL_PATTERN:
				return getUrlPattern();
			case WebapplicationPackage.SERVLET_MAPPING__NAME:
				return getName();
			case WebapplicationPackage.SERVLET_MAPPING__WEB_APP:
				return getWebApp();
			case WebapplicationPackage.SERVLET_MAPPING__SERVLET:
				if (resolve) return getServlet();
				return basicGetServlet();
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
			case WebapplicationPackage.SERVLET_MAPPING__URL_PATTERN:
				setUrlPattern((String)newValue);
				return;
			case WebapplicationPackage.SERVLET_MAPPING__NAME:
				setName((String)newValue);
				return;
			case WebapplicationPackage.SERVLET_MAPPING__WEB_APP:
				setWebApp((WebApp)newValue);
				return;
			case WebapplicationPackage.SERVLET_MAPPING__SERVLET:
				setServlet((Servlet)newValue);
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
			case WebapplicationPackage.SERVLET_MAPPING__URL_PATTERN:
				setUrlPattern(URL_PATTERN_EDEFAULT);
				return;
			case WebapplicationPackage.SERVLET_MAPPING__NAME:
				setName(NAME_EDEFAULT);
				return;
			case WebapplicationPackage.SERVLET_MAPPING__WEB_APP:
				setWebApp((WebApp)null);
				return;
			case WebapplicationPackage.SERVLET_MAPPING__SERVLET:
				setServlet((Servlet)null);
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
			case WebapplicationPackage.SERVLET_MAPPING__URL_PATTERN:
				return URL_PATTERN_EDEFAULT == null ? urlPattern != null : !URL_PATTERN_EDEFAULT.equals(urlPattern);
			case WebapplicationPackage.SERVLET_MAPPING__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case WebapplicationPackage.SERVLET_MAPPING__WEB_APP:
				return getWebApp() != null;
			case WebapplicationPackage.SERVLET_MAPPING__SERVLET:
				return servlet != null;
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
		result.append(", name: "); //$NON-NLS-1$
		result.append(name);
		result.append(')');
		return result.toString();
	}

}














