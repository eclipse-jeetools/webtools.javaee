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
package org.eclipse.jst.j2ee.common.internal.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.DescriptionGroup;
import org.eclipse.jst.j2ee.common.DisplayName;
import org.eclipse.jst.j2ee.common.IconType;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionResource;


/**
 * <!-- begin-user-doc -->An implementation of the model object '<em><b>Description Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.common.internal.impl.DescriptionGroupImpl#getIcons <em>Icons</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.common.internal.impl.DescriptionGroupImpl#getDisplayNames <em>Display Names</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.common.internal.impl.DescriptionGroupImpl#getDescriptions <em>Descriptions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DescriptionGroupImpl extends EObjectImpl implements DescriptionGroup {
	/**
	 * The cached value of the '{@link #getIcons() <em>Icons</em>}' containment reference list.
	 * <!-- begin-user-doc --><!-- end-user-doc -->
	 * 
	 * @see #getIcons() @generated @ordered
	 */
	protected EList icons = null;

	/**
	 * The cached value of the '{@link #getDisplayNames() <em>Display Names</em>}' containment
	 * reference list. <!-- begin-user-doc --><!-- end-user-doc -->
	 * 
	 * @see #getDisplayNames() @generated @ordered
	 */
	protected EList displayNames = null;

	/**
	 * The cached value of the '{@link #getDescriptions() <em>Descriptions</em>}' containment
	 * reference list. <!-- begin-user-doc --><!-- end-user-doc -->
	 * 
	 * @see #getDescriptions() @generated @ordered
	 */
	protected EList descriptions = null;

	/**
	 * <!-- begin-user-doc --><!-- end-user-doc -->@generated
	 */
	protected DescriptionGroupImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --><!-- end-user-doc -->@generated
	 */
	protected EClass eStaticClass() {
		return CommonPackage.eINSTANCE.getDescriptionGroup();
	}

	/**
	 * <!-- begin-user-doc --><!-- end-user-doc -->@generated
	 */
	public EList getIcons() {
		if (icons == null) {
			icons = new EObjectContainmentEList(IconType.class, this, CommonPackage.DESCRIPTION_GROUP__ICONS);
		}
		return icons;
	}

	/**
	 * <!-- begin-user-doc --><!-- end-user-doc -->@generated
	 */
	public EList getDisplayNames() {
		if (displayNames == null) {
			displayNames = new EObjectContainmentEList(DisplayName.class, this, CommonPackage.DESCRIPTION_GROUP__DISPLAY_NAMES);
		}
		return displayNames;
	}

	/**
	 * <!-- begin-user-doc --><!-- end-user-doc -->@generated
	 */
	public EList getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList(Description.class, this, CommonPackage.DESCRIPTION_GROUP__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc --><!-- end-user-doc -->@generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case CommonPackage.DESCRIPTION_GROUP__ICONS:
					return ((InternalEList)getIcons()).basicRemove(otherEnd, msgs);
				case CommonPackage.DESCRIPTION_GROUP__DISPLAY_NAMES:
					return ((InternalEList)getDisplayNames()).basicRemove(otherEnd, msgs);
				case CommonPackage.DESCRIPTION_GROUP__DESCRIPTIONS:
					return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --><!-- end-user-doc -->@generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonPackage.DESCRIPTION_GROUP__ICONS:
				return getIcons();
			case CommonPackage.DESCRIPTION_GROUP__DISPLAY_NAMES:
				return getDisplayNames();
			case CommonPackage.DESCRIPTION_GROUP__DESCRIPTIONS:
				return getDescriptions();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc --><!-- end-user-doc -->@generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonPackage.DESCRIPTION_GROUP__ICONS:
				getIcons().clear();
				getIcons().addAll((Collection)newValue);
				return;
			case CommonPackage.DESCRIPTION_GROUP__DISPLAY_NAMES:
				getDisplayNames().clear();
				getDisplayNames().addAll((Collection)newValue);
				return;
			case CommonPackage.DESCRIPTION_GROUP__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc --><!-- end-user-doc -->@generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonPackage.DESCRIPTION_GROUP__ICONS:
				getIcons().clear();
				return;
			case CommonPackage.DESCRIPTION_GROUP__DISPLAY_NAMES:
				getDisplayNames().clear();
				return;
			case CommonPackage.DESCRIPTION_GROUP__DESCRIPTIONS:
				getDescriptions().clear();
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc --><!-- end-user-doc -->@generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonPackage.DESCRIPTION_GROUP__ICONS:
				return icons != null && !icons.isEmpty();
			case CommonPackage.DESCRIPTION_GROUP__DISPLAY_NAMES:
				return displayNames != null && !displayNames.isEmpty();
			case CommonPackage.DESCRIPTION_GROUP__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.common.DescriptionGroup#getJ2EEVersionID()
	 */
	public int getJ2EEVersionID() throws IllegalStateException {
		
		try {
			J2EEVersionResource res = (J2EEVersionResource) eResource();
				if (res == null)
					throw new IllegalStateException();
				return res.getJ2EEVersionID();
		} catch (ClassCastException exception) {
			return J2EEVersionConstants.J2EE_1_3_ID;
		}
		
	}

} //DescriptionGroupImpl
