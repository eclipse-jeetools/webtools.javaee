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
package org.eclipse.jst.j2ee.common.internal.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
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
public class DescriptionGroupImpl extends J2EEEObjectImpl implements DescriptionGroup {
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
	@Override
	protected EClass eStaticClass() {
		return CommonPackage.Literals.DESCRIPTION_GROUP;
	}

	/**
	 * <!-- begin-user-doc --><!-- end-user-doc -->@generated
	 */
	@Override
	public EList getIcons() {
		if (icons == null) {
			icons = new EObjectContainmentEList(IconType.class, this, CommonPackage.DESCRIPTION_GROUP__ICONS);
		}
		return icons;
	}

	/**
	 * <!-- begin-user-doc --><!-- end-user-doc -->@generated
	 */
	@Override
	public EList getDisplayNames() {
		if (displayNames == null) {
			displayNames = new EObjectContainmentEList(DisplayName.class, this, CommonPackage.DESCRIPTION_GROUP__DISPLAY_NAMES);
		}
		return displayNames;
	}

	/**
	 * <!-- begin-user-doc --><!-- end-user-doc -->@generated
	 */
	@Override
	public EList getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList(Description.class, this, CommonPackage.DESCRIPTION_GROUP__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CommonPackage.DESCRIPTION_GROUP__ICONS:
				return ((InternalEList)getIcons()).basicRemove(otherEnd, msgs);
			case CommonPackage.DESCRIPTION_GROUP__DISPLAY_NAMES:
				return ((InternalEList)getDisplayNames()).basicRemove(otherEnd, msgs);
			case CommonPackage.DESCRIPTION_GROUP__DESCRIPTIONS:
				return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
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
			case CommonPackage.DESCRIPTION_GROUP__ICONS:
				return getIcons();
			case CommonPackage.DESCRIPTION_GROUP__DISPLAY_NAMES:
				return getDisplayNames();
			case CommonPackage.DESCRIPTION_GROUP__DESCRIPTIONS:
				return getDescriptions();
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
			case CommonPackage.DESCRIPTION_GROUP__ICONS:
				return icons != null && !icons.isEmpty();
			case CommonPackage.DESCRIPTION_GROUP__DISPLAY_NAMES:
				return displayNames != null && !displayNames.isEmpty();
			case CommonPackage.DESCRIPTION_GROUP__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
		}
		return super.eIsSet(featureID);
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
