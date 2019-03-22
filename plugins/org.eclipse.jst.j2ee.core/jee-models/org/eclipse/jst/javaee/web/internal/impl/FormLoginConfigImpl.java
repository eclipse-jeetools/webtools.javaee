/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.web.internal.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.jst.javaee.web.FormLoginConfig;

import org.eclipse.jst.javaee.web.internal.metadata.WebPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Form Login Config</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.FormLoginConfigImpl#getFormLoginPage <em>Form Login Page</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.FormLoginConfigImpl#getFormErrorPage <em>Form Error Page</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.FormLoginConfigImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FormLoginConfigImpl extends EObjectImpl implements FormLoginConfig {
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
	 * The cached value of the '{@link #getFormLoginPage() <em>Form Login Page</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFormLoginPage()
	 * @generated
	 * @ordered
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
	 * The cached value of the '{@link #getFormErrorPage() <em>Form Error Page</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFormErrorPage()
	 * @generated
	 * @ordered
	 */
	protected String formErrorPage = FORM_ERROR_PAGE_EDEFAULT;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FormLoginConfigImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WebPackage.Literals.FORM_LOGIN_CONFIG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFormLoginPage() {
		return formLoginPage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFormLoginPage(String newFormLoginPage) {
		String oldFormLoginPage = formLoginPage;
		formLoginPage = newFormLoginPage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.FORM_LOGIN_CONFIG__FORM_LOGIN_PAGE, oldFormLoginPage, formLoginPage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFormErrorPage() {
		return formErrorPage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFormErrorPage(String newFormErrorPage) {
		String oldFormErrorPage = formErrorPage;
		formErrorPage = newFormErrorPage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.FORM_LOGIN_CONFIG__FORM_ERROR_PAGE, oldFormErrorPage, formErrorPage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.FORM_LOGIN_CONFIG__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WebPackage.FORM_LOGIN_CONFIG__FORM_LOGIN_PAGE:
				return getFormLoginPage();
			case WebPackage.FORM_LOGIN_CONFIG__FORM_ERROR_PAGE:
				return getFormErrorPage();
			case WebPackage.FORM_LOGIN_CONFIG__ID:
				return getId();
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
			case WebPackage.FORM_LOGIN_CONFIG__FORM_LOGIN_PAGE:
				setFormLoginPage((String)newValue);
				return;
			case WebPackage.FORM_LOGIN_CONFIG__FORM_ERROR_PAGE:
				setFormErrorPage((String)newValue);
				return;
			case WebPackage.FORM_LOGIN_CONFIG__ID:
				setId((String)newValue);
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
			case WebPackage.FORM_LOGIN_CONFIG__FORM_LOGIN_PAGE:
				setFormLoginPage(FORM_LOGIN_PAGE_EDEFAULT);
				return;
			case WebPackage.FORM_LOGIN_CONFIG__FORM_ERROR_PAGE:
				setFormErrorPage(FORM_ERROR_PAGE_EDEFAULT);
				return;
			case WebPackage.FORM_LOGIN_CONFIG__ID:
				setId(ID_EDEFAULT);
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
			case WebPackage.FORM_LOGIN_CONFIG__FORM_LOGIN_PAGE:
				return FORM_LOGIN_PAGE_EDEFAULT == null ? formLoginPage != null : !FORM_LOGIN_PAGE_EDEFAULT.equals(formLoginPage);
			case WebPackage.FORM_LOGIN_CONFIG__FORM_ERROR_PAGE:
				return FORM_ERROR_PAGE_EDEFAULT == null ? formErrorPage != null : !FORM_ERROR_PAGE_EDEFAULT.equals(formErrorPage);
			case WebPackage.FORM_LOGIN_CONFIG__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
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
		result.append(" (formLoginPage: "); //$NON-NLS-1$
		result.append(formLoginPage);
		result.append(", formErrorPage: "); //$NON-NLS-1$
		result.append(formErrorPage);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //FormLoginConfigImpl