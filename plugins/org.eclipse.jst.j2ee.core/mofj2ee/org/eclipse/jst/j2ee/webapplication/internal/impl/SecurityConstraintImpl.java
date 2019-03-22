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
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.common.DisplayName;
import org.eclipse.jst.j2ee.common.internal.impl.J2EEEObjectImpl;
import org.eclipse.jst.j2ee.common.internal.impl.SecurityConstraintAdapter;
import org.eclipse.jst.j2ee.webapplication.AuthConstraint;
import org.eclipse.jst.j2ee.webapplication.SecurityConstraint;
import org.eclipse.jst.j2ee.webapplication.UserDataConstraint;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebResourceCollection;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;

/**
 * The security-constraint element is used to associate security constraints with one or more web resource collections
 * @generated
 */
public class SecurityConstraintImpl extends J2EEEObjectImpl implements SecurityConstraint {

	/**
	 * The default value of the '{@link #getDisplayName() <em>Display Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayName()
	 * @generated
	 * @ordered
	 */
	protected static final String DISPLAY_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDisplayName() <em>Display Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayName()
	 * @generated
	 * @ordered
	 */
	protected String displayName = DISPLAY_NAME_EDEFAULT;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList webResourceCollections = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected AuthConstraint authConstraint = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected UserDataConstraint userDataConstraint = null;
	/**
	 * The cached value of the '{@link #getDisplayNames() <em>Display Names</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayNames()
	 * @generated
	 * @ordered
	 */
	protected EList displayNames = null;

	public SecurityConstraintImpl() {
		super();
		eAdapters().add(new SecurityConstraintAdapter());
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WebapplicationPackage.Literals.SECURITY_CONSTRAINT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDisplayName(String newDisplayName) {
		String oldDisplayName = displayName;
		displayName = newDisplayName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.SECURITY_CONSTRAINT__DISPLAY_NAME, oldDisplayName, displayName));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public WebApp getWebApp() {
		if (eContainerFeatureID != WebapplicationPackage.SECURITY_CONSTRAINT__WEB_APP) return null;
		return (WebApp)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWebApp(WebApp newWebApp, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newWebApp, WebapplicationPackage.SECURITY_CONSTRAINT__WEB_APP, msgs);
		return msgs;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setWebApp(WebApp newWebApp) {
		if (newWebApp != eInternalContainer() || (eContainerFeatureID != WebapplicationPackage.SECURITY_CONSTRAINT__WEB_APP && newWebApp != null)) {
			if (EcoreUtil.isAncestor(this, newWebApp))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newWebApp != null)
				msgs = ((InternalEObject)newWebApp).eInverseAdd(this, WebapplicationPackage.WEB_APP__CONSTRAINTS, WebApp.class, msgs);
			msgs = basicSetWebApp(newWebApp, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.SECURITY_CONSTRAINT__WEB_APP, newWebApp, newWebApp));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public EList getWebResourceCollections() {
		if (webResourceCollections == null) {
			webResourceCollections = new EObjectContainmentWithInverseEList(WebResourceCollection.class, this, WebapplicationPackage.SECURITY_CONSTRAINT__WEB_RESOURCE_COLLECTIONS, WebapplicationPackage.WEB_RESOURCE_COLLECTION__SEC_CONSTRAINT);
		}
		return webResourceCollections;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public AuthConstraint getAuthConstraint() {
		return authConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAuthConstraint(AuthConstraint newAuthConstraint, NotificationChain msgs) {
		AuthConstraint oldAuthConstraint = authConstraint;
		authConstraint = newAuthConstraint;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WebapplicationPackage.SECURITY_CONSTRAINT__AUTH_CONSTRAINT, oldAuthConstraint, newAuthConstraint);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setAuthConstraint(AuthConstraint newAuthConstraint) {
		if (newAuthConstraint != authConstraint) {
			NotificationChain msgs = null;
			if (authConstraint != null)
				msgs = ((InternalEObject)authConstraint).eInverseRemove(this, WebapplicationPackage.AUTH_CONSTRAINT__SEC_CONSTRAINT, AuthConstraint.class, msgs);
			if (newAuthConstraint != null)
				msgs = ((InternalEObject)newAuthConstraint).eInverseAdd(this, WebapplicationPackage.AUTH_CONSTRAINT__SEC_CONSTRAINT, AuthConstraint.class, msgs);
			msgs = basicSetAuthConstraint(newAuthConstraint, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.SECURITY_CONSTRAINT__AUTH_CONSTRAINT, newAuthConstraint, newAuthConstraint));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public UserDataConstraint getUserDataConstraint() {
		return userDataConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUserDataConstraint(UserDataConstraint newUserDataConstraint, NotificationChain msgs) {
		UserDataConstraint oldUserDataConstraint = userDataConstraint;
		userDataConstraint = newUserDataConstraint;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WebapplicationPackage.SECURITY_CONSTRAINT__USER_DATA_CONSTRAINT, oldUserDataConstraint, newUserDataConstraint);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setUserDataConstraint(UserDataConstraint newUserDataConstraint) {
		if (newUserDataConstraint != userDataConstraint) {
			NotificationChain msgs = null;
			if (userDataConstraint != null)
				msgs = ((InternalEObject)userDataConstraint).eInverseRemove(this, WebapplicationPackage.USER_DATA_CONSTRAINT__SEC_CONSTRAINT, UserDataConstraint.class, msgs);
			if (newUserDataConstraint != null)
				msgs = ((InternalEObject)newUserDataConstraint).eInverseAdd(this, WebapplicationPackage.USER_DATA_CONSTRAINT__SEC_CONSTRAINT, UserDataConstraint.class, msgs);
			msgs = basicSetUserDataConstraint(newUserDataConstraint, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.SECURITY_CONSTRAINT__USER_DATA_CONSTRAINT, newUserDataConstraint, newUserDataConstraint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDisplayNames() {
		if (displayNames == null) {
			displayNames = new EObjectContainmentEList(DisplayName.class, this, WebapplicationPackage.SECURITY_CONSTRAINT__DISPLAY_NAMES);
		}
		return displayNames;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WebapplicationPackage.SECURITY_CONSTRAINT__WEB_APP:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetWebApp((WebApp)otherEnd, msgs);
			case WebapplicationPackage.SECURITY_CONSTRAINT__WEB_RESOURCE_COLLECTIONS:
				return ((InternalEList)getWebResourceCollections()).basicAdd(otherEnd, msgs);
			case WebapplicationPackage.SECURITY_CONSTRAINT__AUTH_CONSTRAINT:
				if (authConstraint != null)
					msgs = ((InternalEObject)authConstraint).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WebapplicationPackage.SECURITY_CONSTRAINT__AUTH_CONSTRAINT, null, msgs);
				return basicSetAuthConstraint((AuthConstraint)otherEnd, msgs);
			case WebapplicationPackage.SECURITY_CONSTRAINT__USER_DATA_CONSTRAINT:
				if (userDataConstraint != null)
					msgs = ((InternalEObject)userDataConstraint).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WebapplicationPackage.SECURITY_CONSTRAINT__USER_DATA_CONSTRAINT, null, msgs);
				return basicSetUserDataConstraint((UserDataConstraint)otherEnd, msgs);
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
			case WebapplicationPackage.SECURITY_CONSTRAINT__WEB_APP:
				return basicSetWebApp(null, msgs);
			case WebapplicationPackage.SECURITY_CONSTRAINT__WEB_RESOURCE_COLLECTIONS:
				return ((InternalEList)getWebResourceCollections()).basicRemove(otherEnd, msgs);
			case WebapplicationPackage.SECURITY_CONSTRAINT__AUTH_CONSTRAINT:
				return basicSetAuthConstraint(null, msgs);
			case WebapplicationPackage.SECURITY_CONSTRAINT__USER_DATA_CONSTRAINT:
				return basicSetUserDataConstraint(null, msgs);
			case WebapplicationPackage.SECURITY_CONSTRAINT__DISPLAY_NAMES:
				return ((InternalEList)getDisplayNames()).basicRemove(otherEnd, msgs);
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
			case WebapplicationPackage.SECURITY_CONSTRAINT__WEB_APP:
				return eInternalContainer().eInverseRemove(this, WebapplicationPackage.WEB_APP__CONSTRAINTS, WebApp.class, msgs);
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
			case WebapplicationPackage.SECURITY_CONSTRAINT__DISPLAY_NAME:
				return getDisplayName();
			case WebapplicationPackage.SECURITY_CONSTRAINT__WEB_APP:
				return getWebApp();
			case WebapplicationPackage.SECURITY_CONSTRAINT__WEB_RESOURCE_COLLECTIONS:
				return getWebResourceCollections();
			case WebapplicationPackage.SECURITY_CONSTRAINT__AUTH_CONSTRAINT:
				return getAuthConstraint();
			case WebapplicationPackage.SECURITY_CONSTRAINT__USER_DATA_CONSTRAINT:
				return getUserDataConstraint();
			case WebapplicationPackage.SECURITY_CONSTRAINT__DISPLAY_NAMES:
				return getDisplayNames();
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
			case WebapplicationPackage.SECURITY_CONSTRAINT__DISPLAY_NAME:
				setDisplayName((String)newValue);
				return;
			case WebapplicationPackage.SECURITY_CONSTRAINT__WEB_APP:
				setWebApp((WebApp)newValue);
				return;
			case WebapplicationPackage.SECURITY_CONSTRAINT__WEB_RESOURCE_COLLECTIONS:
				getWebResourceCollections().clear();
				getWebResourceCollections().addAll((Collection)newValue);
				return;
			case WebapplicationPackage.SECURITY_CONSTRAINT__AUTH_CONSTRAINT:
				setAuthConstraint((AuthConstraint)newValue);
				return;
			case WebapplicationPackage.SECURITY_CONSTRAINT__USER_DATA_CONSTRAINT:
				setUserDataConstraint((UserDataConstraint)newValue);
				return;
			case WebapplicationPackage.SECURITY_CONSTRAINT__DISPLAY_NAMES:
				getDisplayNames().clear();
				getDisplayNames().addAll((Collection)newValue);
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
			case WebapplicationPackage.SECURITY_CONSTRAINT__DISPLAY_NAME:
				setDisplayName(DISPLAY_NAME_EDEFAULT);
				return;
			case WebapplicationPackage.SECURITY_CONSTRAINT__WEB_APP:
				setWebApp((WebApp)null);
				return;
			case WebapplicationPackage.SECURITY_CONSTRAINT__WEB_RESOURCE_COLLECTIONS:
				getWebResourceCollections().clear();
				return;
			case WebapplicationPackage.SECURITY_CONSTRAINT__AUTH_CONSTRAINT:
				setAuthConstraint((AuthConstraint)null);
				return;
			case WebapplicationPackage.SECURITY_CONSTRAINT__USER_DATA_CONSTRAINT:
				setUserDataConstraint((UserDataConstraint)null);
				return;
			case WebapplicationPackage.SECURITY_CONSTRAINT__DISPLAY_NAMES:
				getDisplayNames().clear();
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
			case WebapplicationPackage.SECURITY_CONSTRAINT__DISPLAY_NAME:
				return DISPLAY_NAME_EDEFAULT == null ? displayName != null : !DISPLAY_NAME_EDEFAULT.equals(displayName);
			case WebapplicationPackage.SECURITY_CONSTRAINT__WEB_APP:
				return getWebApp() != null;
			case WebapplicationPackage.SECURITY_CONSTRAINT__WEB_RESOURCE_COLLECTIONS:
				return webResourceCollections != null && !webResourceCollections.isEmpty();
			case WebapplicationPackage.SECURITY_CONSTRAINT__AUTH_CONSTRAINT:
				return authConstraint != null;
			case WebapplicationPackage.SECURITY_CONSTRAINT__USER_DATA_CONSTRAINT:
				return userDataConstraint != null;
			case WebapplicationPackage.SECURITY_CONSTRAINT__DISPLAY_NAMES:
				return displayNames != null && !displayNames.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (displayName: "); //$NON-NLS-1$
		result.append(displayName);
		result.append(')');
		return result.toString();
	}

}














