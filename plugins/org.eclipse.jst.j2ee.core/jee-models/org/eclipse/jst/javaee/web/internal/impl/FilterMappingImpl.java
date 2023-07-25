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

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jst.javaee.core.UrlPatternType;
import org.eclipse.jst.javaee.web.DispatcherType;
import org.eclipse.jst.javaee.web.FilterMapping;

import org.eclipse.jst.javaee.web.internal.metadata.WebPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Filter Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.FilterMappingImpl#getFilterName <em>Filter Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.FilterMappingImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.FilterMappingImpl#getUrlPatterns <em>Url Patterns</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.FilterMappingImpl#getServletNames <em>Servlet Names</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.FilterMappingImpl#getDispatchers <em>Dispatchers</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.FilterMappingImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FilterMappingImpl extends EObjectImpl implements FilterMapping {
	/**
	 * The default value of the '{@link #getFilterName() <em>Filter Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilterName()
	 * @generated
	 * @ordered
	 */
	protected static final String FILTER_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFilterName() <em>Filter Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilterName()
	 * @generated
	 * @ordered
	 */
	protected String filterName = FILTER_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getGroup() <em>Group</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group;

	/**
	 * The cached value of the '{@link #getDispatchers() <em>Dispatchers</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDispatchers()
	 * @generated
	 * @ordered
	 */
	protected EList<DispatcherType> dispatchers;

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
	protected FilterMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WebPackage.Literals.FILTER_MAPPING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getFilterName() {
		return filterName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFilterName(String newFilterName) {
		String oldFilterName = filterName;
		filterName = newFilterName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.FILTER_MAPPING__FILTER_NAME, oldFilterName, filterName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, WebPackage.FILTER_MAPPING__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<UrlPatternType> getUrlPatterns() {
		return getGroup().list(WebPackage.Literals.FILTER_MAPPING__URL_PATTERNS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<String> getServletNames() {
		return getGroup().list(WebPackage.Literals.FILTER_MAPPING__SERVLET_NAMES);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<DispatcherType> getDispatchers() {
		if (dispatchers == null) {
			dispatchers = new EDataTypeEList<DispatcherType>(DispatcherType.class, this, WebPackage.FILTER_MAPPING__DISPATCHERS);
		}
		return dispatchers;
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
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.FILTER_MAPPING__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WebPackage.FILTER_MAPPING__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
			case WebPackage.FILTER_MAPPING__URL_PATTERNS:
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
			case WebPackage.FILTER_MAPPING__FILTER_NAME:
				return getFilterName();
			case WebPackage.FILTER_MAPPING__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case WebPackage.FILTER_MAPPING__URL_PATTERNS:
				return getUrlPatterns();
			case WebPackage.FILTER_MAPPING__SERVLET_NAMES:
				return getServletNames();
			case WebPackage.FILTER_MAPPING__DISPATCHERS:
				return getDispatchers();
			case WebPackage.FILTER_MAPPING__ID:
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
			case WebPackage.FILTER_MAPPING__FILTER_NAME:
				setFilterName((String)newValue);
				return;
			case WebPackage.FILTER_MAPPING__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case WebPackage.FILTER_MAPPING__URL_PATTERNS:
				getUrlPatterns().clear();
				getUrlPatterns().addAll((Collection<? extends UrlPatternType>)newValue);
				return;
			case WebPackage.FILTER_MAPPING__SERVLET_NAMES:
				getServletNames().clear();
				getServletNames().addAll((Collection<? extends String>)newValue);
				return;
			case WebPackage.FILTER_MAPPING__DISPATCHERS:
				getDispatchers().clear();
				getDispatchers().addAll((Collection<? extends DispatcherType>)newValue);
				return;
			case WebPackage.FILTER_MAPPING__ID:
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
			case WebPackage.FILTER_MAPPING__FILTER_NAME:
				setFilterName(FILTER_NAME_EDEFAULT);
				return;
			case WebPackage.FILTER_MAPPING__GROUP:
				getGroup().clear();
				return;
			case WebPackage.FILTER_MAPPING__URL_PATTERNS:
				getUrlPatterns().clear();
				return;
			case WebPackage.FILTER_MAPPING__SERVLET_NAMES:
				getServletNames().clear();
				return;
			case WebPackage.FILTER_MAPPING__DISPATCHERS:
				getDispatchers().clear();
				return;
			case WebPackage.FILTER_MAPPING__ID:
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
			case WebPackage.FILTER_MAPPING__FILTER_NAME:
				return FILTER_NAME_EDEFAULT == null ? filterName != null : !FILTER_NAME_EDEFAULT.equals(filterName);
			case WebPackage.FILTER_MAPPING__GROUP:
				return group != null && !group.isEmpty();
			case WebPackage.FILTER_MAPPING__URL_PATTERNS:
				return !getUrlPatterns().isEmpty();
			case WebPackage.FILTER_MAPPING__SERVLET_NAMES:
				return !getServletNames().isEmpty();
			case WebPackage.FILTER_MAPPING__DISPATCHERS:
				return dispatchers != null && !dispatchers.isEmpty();
			case WebPackage.FILTER_MAPPING__ID:
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
		result.append(" (filterName: "); //$NON-NLS-1$
		result.append(filterName);
		result.append(", group: "); //$NON-NLS-1$
		result.append(group);
		result.append(", dispatchers: "); //$NON-NLS-1$
		result.append(dispatchers);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //FilterMappingImpl