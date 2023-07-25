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

package org.eclipse.jst.j2ee.webservice.wsdd.internal.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jst.j2ee.common.internal.impl.J2EEEObjectImpl;

import org.eclipse.jst.j2ee.webservice.wsdd.HandlerChain;
import org.eclipse.jst.j2ee.webservice.wsdd.HandlersChains;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Handlers Chains</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.internal.impl.HandlersChainsImpl#getHandlerChain <em>Handler Chain</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class HandlersChainsImpl extends J2EEEObjectImpl implements HandlersChains {
	/**
	 * The cached value of the '{@link #getHandlerChain() <em>Handler Chain</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHandlerChain()
	 * @generated
	 * @ordered
	 */
	protected EList handlerChain;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HandlersChainsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WsddPackage.Literals.HANDLERS_CHAINS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList getHandlerChain() {
		if (handlerChain == null) {
			handlerChain = new EObjectContainmentEList(HandlerChain.class, this, WsddPackage.HANDLERS_CHAINS__HANDLER_CHAIN);
		}
		return handlerChain;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WsddPackage.HANDLERS_CHAINS__HANDLER_CHAIN:
				return ((InternalEList)getHandlerChain()).basicRemove(otherEnd, msgs);
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
			case WsddPackage.HANDLERS_CHAINS__HANDLER_CHAIN:
				return getHandlerChain();
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
			case WsddPackage.HANDLERS_CHAINS__HANDLER_CHAIN:
				getHandlerChain().clear();
				getHandlerChain().addAll((Collection)newValue);
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
			case WsddPackage.HANDLERS_CHAINS__HANDLER_CHAIN:
				getHandlerChain().clear();
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
			case WsddPackage.HANDLERS_CHAINS__HANDLER_CHAIN:
				return handlerChain != null && !handlerChain.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //HandlersChainsImpl
