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
package org.eclipse.jst.j2ee.internal.webapplication.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
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
 *   <li>{@link org.eclipse.jst.j2ee.internal.webapplication.impl.LocalEncodingMappingListImpl#getLocalEncodingMappings <em>Local Encoding Mappings</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LocalEncodingMappingListImpl extends EObjectImpl implements LocalEncodingMappingList {
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
	protected EClass eStaticClass() {
		return WebapplicationPackage.eINSTANCE.getLocalEncodingMappingList();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
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
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case WebapplicationPackage.LOCAL_ENCODING_MAPPING_LIST__LOCAL_ENCODING_MAPPINGS:
					return ((InternalEList)getLocalEncodingMappings()).basicRemove(otherEnd, msgs);
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
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case WebapplicationPackage.LOCAL_ENCODING_MAPPING_LIST__LOCAL_ENCODING_MAPPINGS:
				return getLocalEncodingMappings();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case WebapplicationPackage.LOCAL_ENCODING_MAPPING_LIST__LOCAL_ENCODING_MAPPINGS:
				getLocalEncodingMappings().clear();
				getLocalEncodingMappings().addAll((Collection)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case WebapplicationPackage.LOCAL_ENCODING_MAPPING_LIST__LOCAL_ENCODING_MAPPINGS:
				getLocalEncodingMappings().clear();
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case WebapplicationPackage.LOCAL_ENCODING_MAPPING_LIST__LOCAL_ENCODING_MAPPINGS:
				return localEncodingMappings != null && !localEncodingMappings.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //LocalEncodingMappingListImpl
