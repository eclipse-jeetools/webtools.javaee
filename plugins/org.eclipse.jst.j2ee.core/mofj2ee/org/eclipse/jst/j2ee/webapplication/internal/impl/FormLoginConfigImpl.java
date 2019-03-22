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
import org.eclipse.jst.j2ee.webapplication.FormLoginConfig;
import org.eclipse.jst.j2ee.webapplication.LoginConfig;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;

/**
 * The form-login-config element specifies the login and error pages that should be used in form based login. If form based authentication is not used, these elements are ignored.
 * @generated
 */
public class FormLoginConfigImpl extends J2EEEObjectImpl implements FormLoginConfig {

	/**
	 * The default value of the '{@link #getFormLoginPage() <em>Form Login Page</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFormLoginPage()
	 * @generated
	 * @ordered
	 */
	protected static final String FORM_LOGIN_PAGE_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String formLoginPage = FORM_LOGIN_PAGE_EDEFAULT;
	/**
	 * The default value of the '{@link #getFormErrorPage() <em>Form Error Page</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFormErrorPage()
	 * @generated
	 * @ordered
	 */
	protected static final String FORM_ERROR_PAGE_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String formErrorPage = FORM_ERROR_PAGE_EDEFAULT;
	public FormLoginConfigImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WebapplicationPackage.Literals.FORM_LOGIN_CONFIG;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The form-login-page element defines the location in the web app where the page
	 * that can be used for login can be found
	 */
	public String getFormLoginPage() {
		return formLoginPage;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setFormLoginPage(String newFormLoginPage) {
		String oldFormLoginPage = formLoginPage;
		formLoginPage = newFormLoginPage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.FORM_LOGIN_CONFIG__FORM_LOGIN_PAGE, oldFormLoginPage, formLoginPage));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The form-error-page element defines the location in the web app where the error 
	 * page that is displayed when login is not successful can be found
	 */
	public String getFormErrorPage() {
		return formErrorPage;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setFormErrorPage(String newFormErrorPage) {
		String oldFormErrorPage = formErrorPage;
		formErrorPage = newFormErrorPage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.FORM_LOGIN_CONFIG__FORM_ERROR_PAGE, oldFormErrorPage, formErrorPage));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public LoginConfig getLoginConfig() {
		if (eContainerFeatureID != WebapplicationPackage.FORM_LOGIN_CONFIG__LOGIN_CONFIG) return null;
		return (LoginConfig)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLoginConfig(LoginConfig newLoginConfig, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newLoginConfig, WebapplicationPackage.FORM_LOGIN_CONFIG__LOGIN_CONFIG, msgs);
		return msgs;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setLoginConfig(LoginConfig newLoginConfig) {
		if (newLoginConfig != eInternalContainer() || (eContainerFeatureID != WebapplicationPackage.FORM_LOGIN_CONFIG__LOGIN_CONFIG && newLoginConfig != null)) {
			if (EcoreUtil.isAncestor(this, newLoginConfig))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newLoginConfig != null)
				msgs = ((InternalEObject)newLoginConfig).eInverseAdd(this, WebapplicationPackage.LOGIN_CONFIG__FORM_LOGIN_CONFIG, LoginConfig.class, msgs);
			msgs = basicSetLoginConfig(newLoginConfig, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.FORM_LOGIN_CONFIG__LOGIN_CONFIG, newLoginConfig, newLoginConfig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WebapplicationPackage.FORM_LOGIN_CONFIG__LOGIN_CONFIG:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetLoginConfig((LoginConfig)otherEnd, msgs);
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
			case WebapplicationPackage.FORM_LOGIN_CONFIG__LOGIN_CONFIG:
				return basicSetLoginConfig(null, msgs);
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
			case WebapplicationPackage.FORM_LOGIN_CONFIG__LOGIN_CONFIG:
				return eInternalContainer().eInverseRemove(this, WebapplicationPackage.LOGIN_CONFIG__FORM_LOGIN_CONFIG, LoginConfig.class, msgs);
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
			case WebapplicationPackage.FORM_LOGIN_CONFIG__FORM_LOGIN_PAGE:
				return getFormLoginPage();
			case WebapplicationPackage.FORM_LOGIN_CONFIG__FORM_ERROR_PAGE:
				return getFormErrorPage();
			case WebapplicationPackage.FORM_LOGIN_CONFIG__LOGIN_CONFIG:
				return getLoginConfig();
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
			case WebapplicationPackage.FORM_LOGIN_CONFIG__FORM_LOGIN_PAGE:
				setFormLoginPage((String)newValue);
				return;
			case WebapplicationPackage.FORM_LOGIN_CONFIG__FORM_ERROR_PAGE:
				setFormErrorPage((String)newValue);
				return;
			case WebapplicationPackage.FORM_LOGIN_CONFIG__LOGIN_CONFIG:
				setLoginConfig((LoginConfig)newValue);
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
			case WebapplicationPackage.FORM_LOGIN_CONFIG__FORM_LOGIN_PAGE:
				setFormLoginPage(FORM_LOGIN_PAGE_EDEFAULT);
				return;
			case WebapplicationPackage.FORM_LOGIN_CONFIG__FORM_ERROR_PAGE:
				setFormErrorPage(FORM_ERROR_PAGE_EDEFAULT);
				return;
			case WebapplicationPackage.FORM_LOGIN_CONFIG__LOGIN_CONFIG:
				setLoginConfig((LoginConfig)null);
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
			case WebapplicationPackage.FORM_LOGIN_CONFIG__FORM_LOGIN_PAGE:
				return FORM_LOGIN_PAGE_EDEFAULT == null ? formLoginPage != null : !FORM_LOGIN_PAGE_EDEFAULT.equals(formLoginPage);
			case WebapplicationPackage.FORM_LOGIN_CONFIG__FORM_ERROR_PAGE:
				return FORM_ERROR_PAGE_EDEFAULT == null ? formErrorPage != null : !FORM_ERROR_PAGE_EDEFAULT.equals(formErrorPage);
			case WebapplicationPackage.FORM_LOGIN_CONFIG__LOGIN_CONFIG:
				return getLoginConfig() != null;
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
		result.append(" (formLoginPage: "); //$NON-NLS-1$
		result.append(formLoginPage);
		result.append(", formErrorPage: "); //$NON-NLS-1$
		result.append(formErrorPage);
		result.append(')');
		return result.toString();
	}

}














