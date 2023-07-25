/*******************************************************************************
 * Copyright (c) 2009, 2023 IBM Corporation and others.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/**
 * <copyright>
 * </copyright>
 *
 * $Id: AbsoluteOrderingTypeImpl.java,v 1.1 2009/10/15 18:52:05 canderson Exp $
 */
package org.eclipse.jst.javaee.web.internal.impl;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jst.javaee.web.AbsoluteOrderingType;
import org.eclipse.jst.javaee.web.OrderingOthersType;

import org.eclipse.jst.javaee.web.internal.metadata.WebPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Absolute Ordering Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.AbsoluteOrderingTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.AbsoluteOrderingTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.AbsoluteOrderingTypeImpl#getOthers <em>Others</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AbsoluteOrderingTypeImpl extends EObjectImpl implements AbsoluteOrderingType {
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbsoluteOrderingTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WebPackage.Literals.ABSOLUTE_ORDERING_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, WebPackage.ABSOLUTE_ORDERING_TYPE__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<String> getName() {
		return getGroup().list(WebPackage.Literals.ABSOLUTE_ORDERING_TYPE__NAME);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<OrderingOthersType> getOthers() {
		return getGroup().list(WebPackage.Literals.ABSOLUTE_ORDERING_TYPE__OTHERS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WebPackage.ABSOLUTE_ORDERING_TYPE__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
			case WebPackage.ABSOLUTE_ORDERING_TYPE__OTHERS:
				return ((InternalEList<?>)getOthers()).basicRemove(otherEnd, msgs);
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
			case WebPackage.ABSOLUTE_ORDERING_TYPE__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case WebPackage.ABSOLUTE_ORDERING_TYPE__NAME:
				return getName();
			case WebPackage.ABSOLUTE_ORDERING_TYPE__OTHERS:
				return getOthers();
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
			case WebPackage.ABSOLUTE_ORDERING_TYPE__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case WebPackage.ABSOLUTE_ORDERING_TYPE__NAME:
				getName().clear();
				getName().addAll((Collection<? extends String>)newValue);
				return;
			case WebPackage.ABSOLUTE_ORDERING_TYPE__OTHERS:
				getOthers().clear();
				getOthers().addAll((Collection<? extends OrderingOthersType>)newValue);
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
			case WebPackage.ABSOLUTE_ORDERING_TYPE__GROUP:
				getGroup().clear();
				return;
			case WebPackage.ABSOLUTE_ORDERING_TYPE__NAME:
				getName().clear();
				return;
			case WebPackage.ABSOLUTE_ORDERING_TYPE__OTHERS:
				getOthers().clear();
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
			case WebPackage.ABSOLUTE_ORDERING_TYPE__GROUP:
				return group != null && !group.isEmpty();
			case WebPackage.ABSOLUTE_ORDERING_TYPE__NAME:
				return !getName().isEmpty();
			case WebPackage.ABSOLUTE_ORDERING_TYPE__OTHERS:
				return !getOthers().isEmpty();
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
		result.append(" (group: "); //$NON-NLS-1$
		result.append(group);
		result.append(')');
		return result.toString();
	}

} //AbsoluteOrderingTypeImpl
