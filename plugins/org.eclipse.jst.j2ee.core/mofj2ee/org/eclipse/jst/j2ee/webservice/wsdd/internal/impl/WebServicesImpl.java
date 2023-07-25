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
package org.eclipse.jst.j2ee.webservice.wsdd.internal.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.common.internal.impl.CompatibilityDescriptionGroupImpl;
import org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription;
import org.eclipse.jst.j2ee.webservice.wsdd.WebServices;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Web Services</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.internal.impl.WebServicesImpl#getWebServiceDescriptions <em>Web Service Descriptions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WebServicesImpl extends CompatibilityDescriptionGroupImpl implements WebServices
{
	/**
	 * The cached value of the '{@link #getWebServiceDescriptions() <em>Web Service Descriptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getWebServiceDescriptions()
	 * @generated
	 * @ordered
	 */
  protected EList webServiceDescriptions;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected WebServicesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
protected EClass eStaticClass() {
		return WsddPackage.Literals.WEB_SERVICES;
	}

  /**
   *
   */
  @Override
public void setDisplayName(String newDisplayName) {
  	super.setDisplayName(newDisplayName);
  }
  
	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public EList getWebServiceDescriptions() {
		if (webServiceDescriptions == null) {
			webServiceDescriptions = new EObjectContainmentEList(WebServiceDescription.class, this, WsddPackage.WEB_SERVICES__WEB_SERVICE_DESCRIPTIONS);
		}
		return webServiceDescriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WsddPackage.WEB_SERVICES__WEB_SERVICE_DESCRIPTIONS:
				return ((InternalEList)getWebServiceDescriptions()).basicRemove(otherEnd, msgs);
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
			case WsddPackage.WEB_SERVICES__WEB_SERVICE_DESCRIPTIONS:
				return getWebServiceDescriptions();
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
			case WsddPackage.WEB_SERVICES__WEB_SERVICE_DESCRIPTIONS:
				getWebServiceDescriptions().clear();
				getWebServiceDescriptions().addAll((Collection)newValue);
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
			case WsddPackage.WEB_SERVICES__WEB_SERVICE_DESCRIPTIONS:
				getWebServiceDescriptions().clear();
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
			case WsddPackage.WEB_SERVICES__WEB_SERVICE_DESCRIPTIONS:
				return webServiceDescriptions != null && !webServiceDescriptions.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //WebServicesImpl
