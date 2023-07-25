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

import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.DisplayName;
import org.eclipse.jst.javaee.core.Icon;
import org.eclipse.jst.javaee.core.ParamValue;

import org.eclipse.jst.javaee.web.Filter;

import org.eclipse.jst.javaee.web.internal.metadata.WebPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Filter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.FilterImpl#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.FilterImpl#getDisplayNames <em>Display Names</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.FilterImpl#getIcons <em>Icons</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.FilterImpl#getFilterName <em>Filter Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.FilterImpl#getFilterClass <em>Filter Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.FilterImpl#isAsyncSupported <em>Async Supported</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.FilterImpl#getInitParams <em>Init Params</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.FilterImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FilterImpl extends EObjectImpl implements Filter {
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
	 * The cached value of the '{@link #getDisplayNames() <em>Display Names</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayNames()
	 * @generated
	 * @ordered
	 */
	protected EList<DisplayName> displayNames;

	/**
	 * The cached value of the '{@link #getIcons() <em>Icons</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIcons()
	 * @generated
	 * @ordered
	 */
	protected EList<Icon> icons;

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
	 * The default value of the '{@link #getFilterClass() <em>Filter Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilterClass()
	 * @generated
	 * @ordered
	 */
	protected static final String FILTER_CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFilterClass() <em>Filter Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilterClass()
	 * @generated
	 * @ordered
	 */
	protected String filterClass = FILTER_CLASS_EDEFAULT;

	/**
	 * The default value of the '{@link #isAsyncSupported() <em>Async Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAsyncSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ASYNC_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAsyncSupported() <em>Async Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAsyncSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean asyncSupported = ASYNC_SUPPORTED_EDEFAULT;

	/**
	 * This is true if the Async Supported attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean asyncSupportedESet;

	/**
	 * The cached value of the '{@link #getInitParams() <em>Init Params</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitParams()
	 * @generated
	 * @ordered
	 */
	protected EList<ParamValue> initParams;

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
	protected FilterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WebPackage.Literals.FILTER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Description> getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList<Description>(Description.class, this, WebPackage.FILTER__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<DisplayName> getDisplayNames() {
		if (displayNames == null) {
			displayNames = new EObjectContainmentEList<DisplayName>(DisplayName.class, this, WebPackage.FILTER__DISPLAY_NAMES);
		}
		return displayNames;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Icon> getIcons() {
		if (icons == null) {
			icons = new EObjectContainmentEList<Icon>(Icon.class, this, WebPackage.FILTER__ICONS);
		}
		return icons;
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
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.FILTER__FILTER_NAME, oldFilterName, filterName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getFilterClass() {
		return filterClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFilterClass(String newFilterClass) {
		String oldFilterClass = filterClass;
		filterClass = newFilterClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.FILTER__FILTER_CLASS, oldFilterClass, filterClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isAsyncSupported() {
		return asyncSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAsyncSupported(boolean newAsyncSupported) {
		boolean oldAsyncSupported = asyncSupported;
		asyncSupported = newAsyncSupported;
		boolean oldAsyncSupportedESet = asyncSupportedESet;
		asyncSupportedESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.FILTER__ASYNC_SUPPORTED, oldAsyncSupported, asyncSupported, !oldAsyncSupportedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetAsyncSupported() {
		boolean oldAsyncSupported = asyncSupported;
		boolean oldAsyncSupportedESet = asyncSupportedESet;
		asyncSupported = ASYNC_SUPPORTED_EDEFAULT;
		asyncSupportedESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, WebPackage.FILTER__ASYNC_SUPPORTED, oldAsyncSupported, ASYNC_SUPPORTED_EDEFAULT, oldAsyncSupportedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetAsyncSupported() {
		return asyncSupportedESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<ParamValue> getInitParams() {
		if (initParams == null) {
			initParams = new EObjectContainmentEList<ParamValue>(ParamValue.class, this, WebPackage.FILTER__INIT_PARAMS);
		}
		return initParams;
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
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.FILTER__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WebPackage.FILTER__DESCRIPTIONS:
				return ((InternalEList<?>)getDescriptions()).basicRemove(otherEnd, msgs);
			case WebPackage.FILTER__DISPLAY_NAMES:
				return ((InternalEList<?>)getDisplayNames()).basicRemove(otherEnd, msgs);
			case WebPackage.FILTER__ICONS:
				return ((InternalEList<?>)getIcons()).basicRemove(otherEnd, msgs);
			case WebPackage.FILTER__INIT_PARAMS:
				return ((InternalEList<?>)getInitParams()).basicRemove(otherEnd, msgs);
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
			case WebPackage.FILTER__DESCRIPTIONS:
				return getDescriptions();
			case WebPackage.FILTER__DISPLAY_NAMES:
				return getDisplayNames();
			case WebPackage.FILTER__ICONS:
				return getIcons();
			case WebPackage.FILTER__FILTER_NAME:
				return getFilterName();
			case WebPackage.FILTER__FILTER_CLASS:
				return getFilterClass();
			case WebPackage.FILTER__ASYNC_SUPPORTED:
				return isAsyncSupported();
			case WebPackage.FILTER__INIT_PARAMS:
				return getInitParams();
			case WebPackage.FILTER__ID:
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
			case WebPackage.FILTER__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection<? extends Description>)newValue);
				return;
			case WebPackage.FILTER__DISPLAY_NAMES:
				getDisplayNames().clear();
				getDisplayNames().addAll((Collection<? extends DisplayName>)newValue);
				return;
			case WebPackage.FILTER__ICONS:
				getIcons().clear();
				getIcons().addAll((Collection<? extends Icon>)newValue);
				return;
			case WebPackage.FILTER__FILTER_NAME:
				setFilterName((String)newValue);
				return;
			case WebPackage.FILTER__FILTER_CLASS:
				setFilterClass((String)newValue);
				return;
			case WebPackage.FILTER__ASYNC_SUPPORTED:
				setAsyncSupported((Boolean)newValue);
				return;
			case WebPackage.FILTER__INIT_PARAMS:
				getInitParams().clear();
				getInitParams().addAll((Collection<? extends ParamValue>)newValue);
				return;
			case WebPackage.FILTER__ID:
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
			case WebPackage.FILTER__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case WebPackage.FILTER__DISPLAY_NAMES:
				getDisplayNames().clear();
				return;
			case WebPackage.FILTER__ICONS:
				getIcons().clear();
				return;
			case WebPackage.FILTER__FILTER_NAME:
				setFilterName(FILTER_NAME_EDEFAULT);
				return;
			case WebPackage.FILTER__FILTER_CLASS:
				setFilterClass(FILTER_CLASS_EDEFAULT);
				return;
			case WebPackage.FILTER__ASYNC_SUPPORTED:
				unsetAsyncSupported();
				return;
			case WebPackage.FILTER__INIT_PARAMS:
				getInitParams().clear();
				return;
			case WebPackage.FILTER__ID:
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
			case WebPackage.FILTER__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case WebPackage.FILTER__DISPLAY_NAMES:
				return displayNames != null && !displayNames.isEmpty();
			case WebPackage.FILTER__ICONS:
				return icons != null && !icons.isEmpty();
			case WebPackage.FILTER__FILTER_NAME:
				return FILTER_NAME_EDEFAULT == null ? filterName != null : !FILTER_NAME_EDEFAULT.equals(filterName);
			case WebPackage.FILTER__FILTER_CLASS:
				return FILTER_CLASS_EDEFAULT == null ? filterClass != null : !FILTER_CLASS_EDEFAULT.equals(filterClass);
			case WebPackage.FILTER__ASYNC_SUPPORTED:
				return isSetAsyncSupported();
			case WebPackage.FILTER__INIT_PARAMS:
				return initParams != null && !initParams.isEmpty();
			case WebPackage.FILTER__ID:
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
		result.append(", filterClass: "); //$NON-NLS-1$
		result.append(filterClass);
		result.append(", asyncSupported: "); //$NON-NLS-1$
		if (asyncSupportedESet) result.append(asyncSupported); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //FilterImpl