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
import org.eclipse.jst.j2ee.webapplication.FormLoginConfig;
import org.eclipse.jst.j2ee.webapplication.LoginConfig;

/**
 * The form-login-config element specifies the login and error pages that should be used in form based login. If form based authentication is not used, these elements are ignored.
 */
public class FormLoginConfigImpl extends EObjectImpl implements FormLoginConfig, EObject {

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
	protected EClass eStaticClass() {
		return WebapplicationPackage.eINSTANCE.getFormLoginConfig();
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
		return (LoginConfig)eContainer;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setLoginConfig(LoginConfig newLoginConfig) {
		if (newLoginConfig != eContainer || (eContainerFeatureID != WebapplicationPackage.FORM_LOGIN_CONFIG__LOGIN_CONFIG && newLoginConfig != null)) {
			if (EcoreUtil.isAncestor(this, newLoginConfig))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());//$NON-NLS-1$
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newLoginConfig != null)
				msgs = ((InternalEObject)newLoginConfig).eInverseAdd(this, WebapplicationPackage.LOGIN_CONFIG__FORM_LOGIN_CONFIG, LoginConfig.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newLoginConfig, WebapplicationPackage.FORM_LOGIN_CONFIG__LOGIN_CONFIG, msgs);
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
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case WebapplicationPackage.FORM_LOGIN_CONFIG__LOGIN_CONFIG:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, WebapplicationPackage.FORM_LOGIN_CONFIG__LOGIN_CONFIG, msgs);
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
				case WebapplicationPackage.FORM_LOGIN_CONFIG__LOGIN_CONFIG:
					return eBasicSetContainer(null, WebapplicationPackage.FORM_LOGIN_CONFIG__LOGIN_CONFIG, msgs);
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
				case WebapplicationPackage.FORM_LOGIN_CONFIG__LOGIN_CONFIG:
					return eContainer.eInverseRemove(this, WebapplicationPackage.LOGIN_CONFIG__FORM_LOGIN_CONFIG, LoginConfig.class, msgs);
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
			case WebapplicationPackage.FORM_LOGIN_CONFIG__FORM_LOGIN_PAGE:
				return getFormLoginPage();
			case WebapplicationPackage.FORM_LOGIN_CONFIG__FORM_ERROR_PAGE:
				return getFormErrorPage();
			case WebapplicationPackage.FORM_LOGIN_CONFIG__LOGIN_CONFIG:
				return getLoginConfig();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case WebapplicationPackage.FORM_LOGIN_CONFIG__FORM_LOGIN_PAGE:
				return FORM_LOGIN_PAGE_EDEFAULT == null ? formLoginPage != null : !FORM_LOGIN_PAGE_EDEFAULT.equals(formLoginPage);
			case WebapplicationPackage.FORM_LOGIN_CONFIG__FORM_ERROR_PAGE:
				return FORM_ERROR_PAGE_EDEFAULT == null ? formErrorPage != null : !FORM_ERROR_PAGE_EDEFAULT.equals(formErrorPage);
			case WebapplicationPackage.FORM_LOGIN_CONFIG__LOGIN_CONFIG:
				return getLoginConfig() != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
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
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
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
		eDynamicUnset(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (formLoginPage: ");//$NON-NLS-1$
		result.append(formLoginPage);
		result.append(", formErrorPage: ");//$NON-NLS-1$
		result.append(formErrorPage);
		result.append(')');
		return result.toString();
	}

}














