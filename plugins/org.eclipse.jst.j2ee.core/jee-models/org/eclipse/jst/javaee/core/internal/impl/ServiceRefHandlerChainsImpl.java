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
package org.eclipse.jst.javaee.core.internal.impl;

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

import org.eclipse.jst.javaee.core.ServiceRefHandlerChain;
import org.eclipse.jst.javaee.core.ServiceRefHandlerChains;

import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service Ref Handler Chains</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ServiceRefHandlerChainsImpl#getHandlerChains <em>Handler Chains</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ServiceRefHandlerChainsImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ServiceRefHandlerChainsImpl extends EObjectImpl implements ServiceRefHandlerChains {
	/**
	 * The cached value of the '{@link #getHandlerChains() <em>Handler Chains</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHandlerChains()
	 * @generated
	 * @ordered
	 */
	protected EList<ServiceRefHandlerChain> handlerChains;

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
	protected ServiceRefHandlerChainsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaeePackage.Literals.SERVICE_REF_HANDLER_CHAINS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<ServiceRefHandlerChain> getHandlerChains() {
		if (handlerChains == null) {
			handlerChains = new EObjectContainmentEList<ServiceRefHandlerChain>(ServiceRefHandlerChain.class, this, JavaeePackage.SERVICE_REF_HANDLER_CHAINS__HANDLER_CHAINS);
		}
		return handlerChains;
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
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.SERVICE_REF_HANDLER_CHAINS__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JavaeePackage.SERVICE_REF_HANDLER_CHAINS__HANDLER_CHAINS:
				return ((InternalEList<?>)getHandlerChains()).basicRemove(otherEnd, msgs);
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
			case JavaeePackage.SERVICE_REF_HANDLER_CHAINS__HANDLER_CHAINS:
				return getHandlerChains();
			case JavaeePackage.SERVICE_REF_HANDLER_CHAINS__ID:
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
			case JavaeePackage.SERVICE_REF_HANDLER_CHAINS__HANDLER_CHAINS:
				getHandlerChains().clear();
				getHandlerChains().addAll((Collection<? extends ServiceRefHandlerChain>)newValue);
				return;
			case JavaeePackage.SERVICE_REF_HANDLER_CHAINS__ID:
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
			case JavaeePackage.SERVICE_REF_HANDLER_CHAINS__HANDLER_CHAINS:
				getHandlerChains().clear();
				return;
			case JavaeePackage.SERVICE_REF_HANDLER_CHAINS__ID:
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
			case JavaeePackage.SERVICE_REF_HANDLER_CHAINS__HANDLER_CHAINS:
				return handlerChains != null && !handlerChains.isEmpty();
			case JavaeePackage.SERVICE_REF_HANDLER_CHAINS__ID:
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

} //ServiceRefHandlerChainsImpl