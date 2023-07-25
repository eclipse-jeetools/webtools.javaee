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
package org.eclipse.jst.javaee.web.internal.impl;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jst.javaee.core.UrlPatternType;

import org.eclipse.jst.javaee.web.ServletMapping;

import org.eclipse.jst.javaee.web.internal.metadata.WebPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Servlet Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.ServletMappingImpl#getServletName <em>Servlet Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.ServletMappingImpl#getUrlPatterns <em>Url Patterns</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.ServletMappingImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ServletMappingImpl extends EObjectImpl implements ServletMapping {
	/**
	 * The default value of the '{@link #getServletName() <em>Servlet Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServletName()
	 * @generated
	 * @ordered
	 */
	protected static final String SERVLET_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getServletName() <em>Servlet Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServletName()
	 * @generated
	 * @ordered
	 */
	protected String servletName = SERVLET_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getUrlPatterns() <em>Url Patterns</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUrlPatterns()
	 * @generated
	 * @ordered
	 */
	protected EList<UrlPatternType> urlPatterns;

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
	protected ServletMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WebPackage.Literals.SERVLET_MAPPING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getServletName() {
		return servletName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setServletName(String newServletName) {
		String oldServletName = servletName;
		servletName = newServletName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.SERVLET_MAPPING__SERVLET_NAME, oldServletName, servletName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<UrlPatternType> getUrlPatterns() {
		if (urlPatterns == null) {
			urlPatterns = new EObjectContainmentEList<UrlPatternType>(UrlPatternType.class, this, WebPackage.SERVLET_MAPPING__URL_PATTERNS);
		}
		return urlPatterns;
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
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.SERVLET_MAPPING__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WebPackage.SERVLET_MAPPING__URL_PATTERNS:
				return ((InternalEList<?>)getUrlPatterns()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WebPackage.SERVLET_MAPPING__SERVLET_NAME:
				return getServletName();
			case WebPackage.SERVLET_MAPPING__URL_PATTERNS:
				return getUrlPatterns();
			case WebPackage.SERVLET_MAPPING__ID:
				return getId();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case WebPackage.SERVLET_MAPPING__SERVLET_NAME:
				setServletName((String)newValue);
				return;
			case WebPackage.SERVLET_MAPPING__URL_PATTERNS:
				getUrlPatterns().clear();
				getUrlPatterns().addAll((Collection<? extends UrlPatternType>)newValue);
				return;
			case WebPackage.SERVLET_MAPPING__ID:
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
			case WebPackage.SERVLET_MAPPING__SERVLET_NAME:
				setServletName(SERVLET_NAME_EDEFAULT);
				return;
			case WebPackage.SERVLET_MAPPING__URL_PATTERNS:
				getUrlPatterns().clear();
				return;
			case WebPackage.SERVLET_MAPPING__ID:
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
			case WebPackage.SERVLET_MAPPING__SERVLET_NAME:
				return SERVLET_NAME_EDEFAULT == null ? servletName != null : !SERVLET_NAME_EDEFAULT.equals(servletName);
			case WebPackage.SERVLET_MAPPING__URL_PATTERNS:
				return urlPatterns != null && !urlPatterns.isEmpty();
			case WebPackage.SERVLET_MAPPING__ID:
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
		result.append(" (servletName: "); //$NON-NLS-1$
		result.append(servletName);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //ServletMappingImpl