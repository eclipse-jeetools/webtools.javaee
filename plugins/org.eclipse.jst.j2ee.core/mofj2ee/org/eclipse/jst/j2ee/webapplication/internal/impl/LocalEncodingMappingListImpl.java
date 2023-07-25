/*******************************************************************************
 * Copyright (c) 2001, 2023 IBM Corporation and others.
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

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.common.internal.impl.J2EEEObjectImpl;
import org.eclipse.jst.j2ee.webapplication.LocalEncodingMapping;
import org.eclipse.jst.j2ee.webapplication.LocalEncodingMappingList;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Local Encoding Mapping List</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webapplication.internal.impl.LocalEncodingMappingListImpl#getLocalEncodingMappings <em>Local Encoding Mappings</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LocalEncodingMappingListImpl extends J2EEEObjectImpl implements LocalEncodingMappingList {
	/**
	 * The cached value of the '{@link #getLocalEncodingMappings() <em>Local Encoding Mappings</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocalEncodingMappings()
	 * @generated
	 * @ordered
	 */
	protected EList localEncodingMappings = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LocalEncodingMappingListImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WebapplicationPackage.Literals.LOCAL_ENCODING_MAPPING_LIST;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList getLocalEncodingMappings() {
		if (localEncodingMappings == null) {
			localEncodingMappings = new EObjectContainmentEList(LocalEncodingMapping.class, this, WebapplicationPackage.LOCAL_ENCODING_MAPPING_LIST__LOCAL_ENCODING_MAPPINGS);
		}
		return localEncodingMappings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WebapplicationPackage.LOCAL_ENCODING_MAPPING_LIST__LOCAL_ENCODING_MAPPINGS:
				return ((InternalEList)getLocalEncodingMappings()).basicRemove(otherEnd, msgs);
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
			case WebapplicationPackage.LOCAL_ENCODING_MAPPING_LIST__LOCAL_ENCODING_MAPPINGS:
				return getLocalEncodingMappings();
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
			case WebapplicationPackage.LOCAL_ENCODING_MAPPING_LIST__LOCAL_ENCODING_MAPPINGS:
				getLocalEncodingMappings().clear();
				getLocalEncodingMappings().addAll((Collection)newValue);
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
			case WebapplicationPackage.LOCAL_ENCODING_MAPPING_LIST__LOCAL_ENCODING_MAPPINGS:
				getLocalEncodingMappings().clear();
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
			case WebapplicationPackage.LOCAL_ENCODING_MAPPING_LIST__LOCAL_ENCODING_MAPPINGS:
				return localEncodingMappings != null && !localEncodingMappings.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //LocalEncodingMappingListImpl
