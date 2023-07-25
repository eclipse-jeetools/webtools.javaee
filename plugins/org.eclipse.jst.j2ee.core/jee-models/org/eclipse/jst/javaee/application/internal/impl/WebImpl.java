/*******************************************************************************
 * Copyright (c) 2007, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.application.internal.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.jst.javaee.application.Web;

import org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Web</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.WebImpl#getWebUri <em>Web Uri</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.WebImpl#getContextRoot <em>Context Root</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.WebImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WebImpl extends EObjectImpl implements Web {
	/**
	 * The default value of the '{@link #getWebUri() <em>Web Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWebUri()
	 * @generated
	 * @ordered
	 */
	protected static final String WEB_URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWebUri() <em>Web Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWebUri()
	 * @generated
	 * @ordered
	 */
	protected String webUri = WEB_URI_EDEFAULT;

	/**
	 * The default value of the '{@link #getContextRoot() <em>Context Root</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContextRoot()
	 * @generated
	 * @ordered
	 */
	protected static final String CONTEXT_ROOT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getContextRoot() <em>Context Root</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContextRoot()
	 * @generated
	 * @ordered
	 */
	protected String contextRoot = CONTEXT_ROOT_EDEFAULT;

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
	protected WebImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ApplicationPackage.Literals.WEB;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getWebUri() {
		return webUri;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWebUri(String newWebUri) {
		String oldWebUri = webUri;
		webUri = newWebUri;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.WEB__WEB_URI, oldWebUri, webUri));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getContextRoot() {
		return contextRoot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setContextRoot(String newContextRoot) {
		String oldContextRoot = contextRoot;
		contextRoot = newContextRoot;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.WEB__CONTEXT_ROOT, oldContextRoot, contextRoot));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.WEB__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ApplicationPackage.WEB__WEB_URI:
				return getWebUri();
			case ApplicationPackage.WEB__CONTEXT_ROOT:
				return getContextRoot();
			case ApplicationPackage.WEB__ID:
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
			case ApplicationPackage.WEB__WEB_URI:
				setWebUri((String)newValue);
				return;
			case ApplicationPackage.WEB__CONTEXT_ROOT:
				setContextRoot((String)newValue);
				return;
			case ApplicationPackage.WEB__ID:
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
			case ApplicationPackage.WEB__WEB_URI:
				setWebUri(WEB_URI_EDEFAULT);
				return;
			case ApplicationPackage.WEB__CONTEXT_ROOT:
				setContextRoot(CONTEXT_ROOT_EDEFAULT);
				return;
			case ApplicationPackage.WEB__ID:
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
			case ApplicationPackage.WEB__WEB_URI:
				return WEB_URI_EDEFAULT == null ? webUri != null : !WEB_URI_EDEFAULT.equals(webUri);
			case ApplicationPackage.WEB__CONTEXT_ROOT:
				return CONTEXT_ROOT_EDEFAULT == null ? contextRoot != null : !CONTEXT_ROOT_EDEFAULT.equals(contextRoot);
			case ApplicationPackage.WEB__ID:
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
		result.append(" (webUri: "); //$NON-NLS-1$
		result.append(webUri);
		result.append(", contextRoot: "); //$NON-NLS-1$
		result.append(contextRoot);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //WebImpl