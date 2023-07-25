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

import org.eclipse.jst.javaee.web.LocaleEncodingMapping;
import org.eclipse.jst.javaee.web.LocaleEncodingMappingList;

import org.eclipse.jst.javaee.web.internal.metadata.WebPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Locale Encoding Mapping List</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.LocaleEncodingMappingListImpl#getLocalEncodingMappings <em>Local Encoding Mappings</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.LocaleEncodingMappingListImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LocaleEncodingMappingListImpl extends EObjectImpl implements LocaleEncodingMappingList {
	/**
	 * The cached value of the '{@link #getLocalEncodingMappings() <em>Local Encoding Mappings</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocalEncodingMappings()
	 * @generated
	 * @ordered
	 */
	protected EList<LocaleEncodingMapping> localEncodingMappings;

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
	protected LocaleEncodingMappingListImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WebPackage.Literals.LOCALE_ENCODING_MAPPING_LIST;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<LocaleEncodingMapping> getLocalEncodingMappings() {
		if (localEncodingMappings == null) {
			localEncodingMappings = new EObjectContainmentEList<LocaleEncodingMapping>(LocaleEncodingMapping.class, this, WebPackage.LOCALE_ENCODING_MAPPING_LIST__LOCAL_ENCODING_MAPPINGS);
		}
		return localEncodingMappings;
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
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.LOCALE_ENCODING_MAPPING_LIST__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WebPackage.LOCALE_ENCODING_MAPPING_LIST__LOCAL_ENCODING_MAPPINGS:
				return ((InternalEList<?>)getLocalEncodingMappings()).basicRemove(otherEnd, msgs);
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
			case WebPackage.LOCALE_ENCODING_MAPPING_LIST__LOCAL_ENCODING_MAPPINGS:
				return getLocalEncodingMappings();
			case WebPackage.LOCALE_ENCODING_MAPPING_LIST__ID:
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
			case WebPackage.LOCALE_ENCODING_MAPPING_LIST__LOCAL_ENCODING_MAPPINGS:
				getLocalEncodingMappings().clear();
				getLocalEncodingMappings().addAll((Collection<? extends LocaleEncodingMapping>)newValue);
				return;
			case WebPackage.LOCALE_ENCODING_MAPPING_LIST__ID:
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
			case WebPackage.LOCALE_ENCODING_MAPPING_LIST__LOCAL_ENCODING_MAPPINGS:
				getLocalEncodingMappings().clear();
				return;
			case WebPackage.LOCALE_ENCODING_MAPPING_LIST__ID:
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
			case WebPackage.LOCALE_ENCODING_MAPPING_LIST__LOCAL_ENCODING_MAPPINGS:
				return localEncodingMappings != null && !localEncodingMappings.isEmpty();
			case WebPackage.LOCALE_ENCODING_MAPPING_LIST__ID:
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
		result.append(" (id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //LocaleEncodingMappingListImpl