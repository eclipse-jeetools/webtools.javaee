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

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.UrlPatternType;

import org.eclipse.jst.javaee.web.WebResourceCollection;

import org.eclipse.jst.javaee.web.internal.metadata.WebPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource Collection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebResourceCollectionImpl#getWebResourceName <em>Web Resource Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebResourceCollectionImpl#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebResourceCollectionImpl#getUrlPatterns <em>Url Patterns</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebResourceCollectionImpl#getHttpMethods <em>Http Methods</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebResourceCollectionImpl#getHttpMethodOmission <em>Http Method Omission</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.WebResourceCollectionImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WebResourceCollectionImpl extends EObjectImpl implements WebResourceCollection {
	/**
	 * The default value of the '{@link #getWebResourceName() <em>Web Resource Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWebResourceName()
	 * @generated
	 * @ordered
	 */
	protected static final String WEB_RESOURCE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWebResourceName() <em>Web Resource Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWebResourceName()
	 * @generated
	 * @ordered
	 */
	protected String webResourceName = WEB_RESOURCE_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDescriptions() <em>Descriptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptions()
	 * @generated
	 * @ordered
	 */
	protected EList<Description> descriptions;

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
	 * The cached value of the '{@link #getHttpMethods() <em>Http Methods</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpMethods()
	 * @generated
	 * @ordered
	 */
	protected EList<String> httpMethods;

	/**
	 * The cached value of the '{@link #getHttpMethodOmission() <em>Http Method Omission</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpMethodOmission()
	 * @generated
	 * @ordered
	 */
	protected EList<String> httpMethodOmission;

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
	protected WebResourceCollectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WebPackage.Literals.WEB_RESOURCE_COLLECTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getWebResourceName() {
		return webResourceName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWebResourceName(String newWebResourceName) {
		String oldWebResourceName = webResourceName;
		webResourceName = newWebResourceName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.WEB_RESOURCE_COLLECTION__WEB_RESOURCE_NAME, oldWebResourceName, webResourceName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Description> getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList<Description>(Description.class, this, WebPackage.WEB_RESOURCE_COLLECTION__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<UrlPatternType> getUrlPatterns() {
		if (urlPatterns == null) {
			urlPatterns = new EObjectContainmentEList<UrlPatternType>(UrlPatternType.class, this, WebPackage.WEB_RESOURCE_COLLECTION__URL_PATTERNS);
		}
		return urlPatterns;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<String> getHttpMethods() {
		if (httpMethods == null) {
			httpMethods = new EDataTypeEList<String>(String.class, this, WebPackage.WEB_RESOURCE_COLLECTION__HTTP_METHODS);
		}
		return httpMethods;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<String> getHttpMethodOmission() {
		if (httpMethodOmission == null) {
			httpMethodOmission = new EDataTypeEList<String>(String.class, this, WebPackage.WEB_RESOURCE_COLLECTION__HTTP_METHOD_OMISSION);
		}
		return httpMethodOmission;
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
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.WEB_RESOURCE_COLLECTION__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WebPackage.WEB_RESOURCE_COLLECTION__DESCRIPTIONS:
				return ((InternalEList<?>)getDescriptions()).basicRemove(otherEnd, msgs);
			case WebPackage.WEB_RESOURCE_COLLECTION__URL_PATTERNS:
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
			case WebPackage.WEB_RESOURCE_COLLECTION__WEB_RESOURCE_NAME:
				return getWebResourceName();
			case WebPackage.WEB_RESOURCE_COLLECTION__DESCRIPTIONS:
				return getDescriptions();
			case WebPackage.WEB_RESOURCE_COLLECTION__URL_PATTERNS:
				return getUrlPatterns();
			case WebPackage.WEB_RESOURCE_COLLECTION__HTTP_METHODS:
				return getHttpMethods();
			case WebPackage.WEB_RESOURCE_COLLECTION__HTTP_METHOD_OMISSION:
				return getHttpMethodOmission();
			case WebPackage.WEB_RESOURCE_COLLECTION__ID:
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
			case WebPackage.WEB_RESOURCE_COLLECTION__WEB_RESOURCE_NAME:
				setWebResourceName((String)newValue);
				return;
			case WebPackage.WEB_RESOURCE_COLLECTION__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection<? extends Description>)newValue);
				return;
			case WebPackage.WEB_RESOURCE_COLLECTION__URL_PATTERNS:
				getUrlPatterns().clear();
				getUrlPatterns().addAll((Collection<? extends UrlPatternType>)newValue);
				return;
			case WebPackage.WEB_RESOURCE_COLLECTION__HTTP_METHODS:
				getHttpMethods().clear();
				getHttpMethods().addAll((Collection<? extends String>)newValue);
				return;
			case WebPackage.WEB_RESOURCE_COLLECTION__HTTP_METHOD_OMISSION:
				getHttpMethodOmission().clear();
				getHttpMethodOmission().addAll((Collection<? extends String>)newValue);
				return;
			case WebPackage.WEB_RESOURCE_COLLECTION__ID:
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
			case WebPackage.WEB_RESOURCE_COLLECTION__WEB_RESOURCE_NAME:
				setWebResourceName(WEB_RESOURCE_NAME_EDEFAULT);
				return;
			case WebPackage.WEB_RESOURCE_COLLECTION__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case WebPackage.WEB_RESOURCE_COLLECTION__URL_PATTERNS:
				getUrlPatterns().clear();
				return;
			case WebPackage.WEB_RESOURCE_COLLECTION__HTTP_METHODS:
				getHttpMethods().clear();
				return;
			case WebPackage.WEB_RESOURCE_COLLECTION__HTTP_METHOD_OMISSION:
				getHttpMethodOmission().clear();
				return;
			case WebPackage.WEB_RESOURCE_COLLECTION__ID:
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
			case WebPackage.WEB_RESOURCE_COLLECTION__WEB_RESOURCE_NAME:
				return WEB_RESOURCE_NAME_EDEFAULT == null ? webResourceName != null : !WEB_RESOURCE_NAME_EDEFAULT.equals(webResourceName);
			case WebPackage.WEB_RESOURCE_COLLECTION__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case WebPackage.WEB_RESOURCE_COLLECTION__URL_PATTERNS:
				return urlPatterns != null && !urlPatterns.isEmpty();
			case WebPackage.WEB_RESOURCE_COLLECTION__HTTP_METHODS:
				return httpMethods != null && !httpMethods.isEmpty();
			case WebPackage.WEB_RESOURCE_COLLECTION__HTTP_METHOD_OMISSION:
				return httpMethodOmission != null && !httpMethodOmission.isEmpty();
			case WebPackage.WEB_RESOURCE_COLLECTION__ID:
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
		result.append(" (webResourceName: "); //$NON-NLS-1$
		result.append(webResourceName);
		result.append(", httpMethods: "); //$NON-NLS-1$
		result.append(httpMethods);
		result.append(", httpMethodOmission: "); //$NON-NLS-1$
		result.append(httpMethodOmission);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //WebResourceCollectionImpl