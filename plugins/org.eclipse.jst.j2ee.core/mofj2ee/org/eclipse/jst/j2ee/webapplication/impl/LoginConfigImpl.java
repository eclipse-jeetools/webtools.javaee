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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jst.j2ee.webapplication.AuthMethodKind;
import org.eclipse.jst.j2ee.webapplication.FormLoginConfig;
import org.eclipse.jst.j2ee.webapplication.LoginConfig;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;

/**
 * The login-config element is used to configure the authentication method that should be used, the realm name that should be used for this application, and the attributes that are needed by the form login
 * mechanism.
 */
public class LoginConfigImpl extends EObjectImpl implements LoginConfig, EObject {

	/**
	 * The default value of the '{@link #getAuthMethod() <em>Auth Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthMethod()
	 * @generated
	 * @ordered
	 */
	protected static final AuthMethodKind AUTH_METHOD_EDEFAULT = AuthMethodKind.UNSPECIFIED_LITERAL;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected AuthMethodKind authMethod = AUTH_METHOD_EDEFAULT;
	/**
	 * This is true if the Auth Method attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean authMethodESet = false;

	/**
	 * The default value of the '{@link #getRealmName() <em>Realm Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRealmName()
	 * @generated
	 * @ordered
	 */
	protected static final String REALM_NAME_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String realmName = REALM_NAME_EDEFAULT;
	/**
	 * The default value of the '{@link #getAuthorizationMethod() <em>Authorization Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthorizationMethod()
	 * @generated
	 * @ordered
	 */
	protected static final String AUTHORIZATION_METHOD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAuthorizationMethod() <em>Authorization Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthorizationMethod()
	 * @generated
	 * @ordered
	 */
	protected String authorizationMethod = AUTHORIZATION_METHOD_EDEFAULT;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected FormLoginConfig formLoginConfig = null;
	public LoginConfigImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return WebapplicationPackage.eINSTANCE.getLoginConfig();
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The auth-method element is used to configure the authentication mechanism for
	 * the web application. As a prerequisite to gaining access to any web resources
	 * which are protected by an authorization constraint, a user must have
	 * authenticated using the configured mechanism. Legal values for this element are 
	 * "BASIC", "DIGEST", "FORM", or "CLIENT-CERT".
	 */
	public AuthMethodKind getAuthMethod() {
		return authMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAuthMethod(AuthMethodKind newAuthMethod) {
		AuthMethodKind oldAuthMethod = authMethod;
		authMethod = newAuthMethod == null ? AUTH_METHOD_EDEFAULT : newAuthMethod;
		boolean oldAuthMethodESet = authMethodESet;
		authMethodESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.LOGIN_CONFIG__AUTH_METHOD, oldAuthMethod, authMethod, !oldAuthMethodESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetAuthMethod() {
		AuthMethodKind oldAuthMethod = authMethod;
		boolean oldAuthMethodESet = authMethodESet;
		authMethod = AUTH_METHOD_EDEFAULT;
		authMethodESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, WebapplicationPackage.LOGIN_CONFIG__AUTH_METHOD, oldAuthMethod, AUTH_METHOD_EDEFAULT, oldAuthMethodESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetAuthMethod() {
		return authMethodESet;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The realm name element specifies the realm name to use in HTTP Basic
	 * authorization
	 */
	public String getRealmName() {
		return realmName;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setRealmName(String newRealmName) {
		String oldRealmName = realmName;
		realmName = newRealmName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.LOGIN_CONFIG__REALM_NAME, oldRealmName, realmName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAuthorizationMethod() {
		return authorizationMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAuthorizationMethod(String newAuthorizationMethod) {
		String oldAuthorizationMethod = authorizationMethod;
		authorizationMethod = newAuthorizationMethod;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.LOGIN_CONFIG__AUTHORIZATION_METHOD, oldAuthorizationMethod, authorizationMethod));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public WebApp getWebApp() {
		if (eContainerFeatureID != WebapplicationPackage.LOGIN_CONFIG__WEB_APP) return null;
		return (WebApp)eContainer;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setWebApp(WebApp newWebApp) {
		if (newWebApp != eContainer || (eContainerFeatureID != WebapplicationPackage.LOGIN_CONFIG__WEB_APP && newWebApp != null)) {
			if (EcoreUtil.isAncestor(this, newWebApp))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());//$NON-NLS-1$
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newWebApp != null)
				msgs = ((InternalEObject)newWebApp).eInverseAdd(this, WebapplicationPackage.WEB_APP__LOGIN_CONFIG, WebApp.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newWebApp, WebapplicationPackage.LOGIN_CONFIG__WEB_APP, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.LOGIN_CONFIG__WEB_APP, newWebApp, newWebApp));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public FormLoginConfig getFormLoginConfig() {
		return formLoginConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFormLoginConfig(FormLoginConfig newFormLoginConfig, NotificationChain msgs) {
		FormLoginConfig oldFormLoginConfig = formLoginConfig;
		formLoginConfig = newFormLoginConfig;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WebapplicationPackage.LOGIN_CONFIG__FORM_LOGIN_CONFIG, oldFormLoginConfig, newFormLoginConfig);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setFormLoginConfig(FormLoginConfig newFormLoginConfig) {
		if (newFormLoginConfig != formLoginConfig) {
			NotificationChain msgs = null;
			if (formLoginConfig != null)
				msgs = ((InternalEObject)formLoginConfig).eInverseRemove(this, WebapplicationPackage.FORM_LOGIN_CONFIG__LOGIN_CONFIG, FormLoginConfig.class, msgs);
			if (newFormLoginConfig != null)
				msgs = ((InternalEObject)newFormLoginConfig).eInverseAdd(this, WebapplicationPackage.FORM_LOGIN_CONFIG__LOGIN_CONFIG, FormLoginConfig.class, msgs);
			msgs = basicSetFormLoginConfig(newFormLoginConfig, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.LOGIN_CONFIG__FORM_LOGIN_CONFIG, newFormLoginConfig, newFormLoginConfig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case WebapplicationPackage.LOGIN_CONFIG__WEB_APP:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, WebapplicationPackage.LOGIN_CONFIG__WEB_APP, msgs);
				case WebapplicationPackage.LOGIN_CONFIG__FORM_LOGIN_CONFIG:
					if (formLoginConfig != null)
						msgs = ((InternalEObject)formLoginConfig).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WebapplicationPackage.LOGIN_CONFIG__FORM_LOGIN_CONFIG, null, msgs);
					return basicSetFormLoginConfig((FormLoginConfig)otherEnd, msgs);
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
				case WebapplicationPackage.LOGIN_CONFIG__WEB_APP:
					return eBasicSetContainer(null, WebapplicationPackage.LOGIN_CONFIG__WEB_APP, msgs);
				case WebapplicationPackage.LOGIN_CONFIG__FORM_LOGIN_CONFIG:
					return basicSetFormLoginConfig(null, msgs);
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
				case WebapplicationPackage.LOGIN_CONFIG__WEB_APP:
					return eContainer.eInverseRemove(this, WebapplicationPackage.WEB_APP__LOGIN_CONFIG, WebApp.class, msgs);
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
			case WebapplicationPackage.LOGIN_CONFIG__AUTH_METHOD:
				return getAuthMethod();
			case WebapplicationPackage.LOGIN_CONFIG__REALM_NAME:
				return getRealmName();
			case WebapplicationPackage.LOGIN_CONFIG__AUTHORIZATION_METHOD:
				return getAuthorizationMethod();
			case WebapplicationPackage.LOGIN_CONFIG__WEB_APP:
				return getWebApp();
			case WebapplicationPackage.LOGIN_CONFIG__FORM_LOGIN_CONFIG:
				return getFormLoginConfig();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case WebapplicationPackage.LOGIN_CONFIG__AUTH_METHOD:
				return isSetAuthMethod();
			case WebapplicationPackage.LOGIN_CONFIG__REALM_NAME:
				return REALM_NAME_EDEFAULT == null ? realmName != null : !REALM_NAME_EDEFAULT.equals(realmName);
			case WebapplicationPackage.LOGIN_CONFIG__AUTHORIZATION_METHOD:
				return AUTHORIZATION_METHOD_EDEFAULT == null ? authorizationMethod != null : !AUTHORIZATION_METHOD_EDEFAULT.equals(authorizationMethod);
			case WebapplicationPackage.LOGIN_CONFIG__WEB_APP:
				return getWebApp() != null;
			case WebapplicationPackage.LOGIN_CONFIG__FORM_LOGIN_CONFIG:
				return formLoginConfig != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case WebapplicationPackage.LOGIN_CONFIG__AUTH_METHOD:
				setAuthMethod((AuthMethodKind)newValue);
				return;
			case WebapplicationPackage.LOGIN_CONFIG__REALM_NAME:
				setRealmName((String)newValue);
				return;
			case WebapplicationPackage.LOGIN_CONFIG__AUTHORIZATION_METHOD:
				setAuthorizationMethod((String)newValue);
				return;
			case WebapplicationPackage.LOGIN_CONFIG__WEB_APP:
				setWebApp((WebApp)newValue);
				return;
			case WebapplicationPackage.LOGIN_CONFIG__FORM_LOGIN_CONFIG:
				setFormLoginConfig((FormLoginConfig)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case WebapplicationPackage.LOGIN_CONFIG__AUTH_METHOD:
				unsetAuthMethod();
				return;
			case WebapplicationPackage.LOGIN_CONFIG__REALM_NAME:
				setRealmName(REALM_NAME_EDEFAULT);
				return;
			case WebapplicationPackage.LOGIN_CONFIG__AUTHORIZATION_METHOD:
				setAuthorizationMethod(AUTHORIZATION_METHOD_EDEFAULT);
				return;
			case WebapplicationPackage.LOGIN_CONFIG__WEB_APP:
				setWebApp((WebApp)null);
				return;
			case WebapplicationPackage.LOGIN_CONFIG__FORM_LOGIN_CONFIG:
				setFormLoginConfig((FormLoginConfig)null);
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
		result.append(" (authMethod: ");//$NON-NLS-1$
		if (authMethodESet) result.append(authMethod); else result.append("<unset>");//$NON-NLS-1$
		result.append(", realmName: ");//$NON-NLS-1$
		result.append(realmName);
		result.append(", authorizationMethod: ");//$NON-NLS-1$
		result.append(authorizationMethod);
		result.append(')');
		return result.toString();
	}

}














