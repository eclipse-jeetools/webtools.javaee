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
package org.eclipse.jst.j2ee.webservice.wsclient.internal.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.webservice.wsclient.ComponentScopedRefs;
import org.eclipse.jst.j2ee.webservice.wsclient.ServiceRef;
import org.eclipse.jst.j2ee.webservice.wsclient.WebServicesClient;
import org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Web Services Client</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsclient.internal.impl.WebServicesClientImpl#getComponentScopedRefs <em>Component Scoped Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsclient.internal.impl.WebServicesClientImpl#getServiceRefs <em>Service Refs</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WebServicesClientImpl extends EObjectImpl implements WebServicesClient {
	/**
	 * The cached value of the '{@link #getComponentScopedRefs() <em>Component Scoped Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getComponentScopedRefs()
	 * @generated
	 * @ordered
	 */
    protected EList componentScopedRefs = null;

	/**
	 * The cached value of the '{@link #getServiceRefs() <em>Service Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getServiceRefs()
	 * @generated
	 * @ordered
	 */
    protected EList serviceRefs = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected WebServicesClientImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return Webservice_clientPackage.eINSTANCE.getWebServicesClient();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getComponentScopedRefs() {
		if (componentScopedRefs == null) {
			componentScopedRefs = new EObjectContainmentEList(ComponentScopedRefs.class, this, Webservice_clientPackage.WEB_SERVICES_CLIENT__COMPONENT_SCOPED_REFS);
		}
		return componentScopedRefs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getServiceRefs() {
		if (serviceRefs == null) {
			serviceRefs = new EObjectContainmentEList(ServiceRef.class, this, Webservice_clientPackage.WEB_SERVICES_CLIENT__SERVICE_REFS);
		}
		return serviceRefs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case Webservice_clientPackage.WEB_SERVICES_CLIENT__COMPONENT_SCOPED_REFS:
					return ((InternalEList)getComponentScopedRefs()).basicRemove(otherEnd, msgs);
				case Webservice_clientPackage.WEB_SERVICES_CLIENT__SERVICE_REFS:
					return ((InternalEList)getServiceRefs()).basicRemove(otherEnd, msgs);
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
			case Webservice_clientPackage.WEB_SERVICES_CLIENT__COMPONENT_SCOPED_REFS:
				return getComponentScopedRefs();
			case Webservice_clientPackage.WEB_SERVICES_CLIENT__SERVICE_REFS:
				return getServiceRefs();
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
			case Webservice_clientPackage.WEB_SERVICES_CLIENT__COMPONENT_SCOPED_REFS:
				getComponentScopedRefs().clear();
				getComponentScopedRefs().addAll((Collection)newValue);
				return;
			case Webservice_clientPackage.WEB_SERVICES_CLIENT__SERVICE_REFS:
				getServiceRefs().clear();
				getServiceRefs().addAll((Collection)newValue);
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
			case Webservice_clientPackage.WEB_SERVICES_CLIENT__COMPONENT_SCOPED_REFS:
				getComponentScopedRefs().clear();
				return;
			case Webservice_clientPackage.WEB_SERVICES_CLIENT__SERVICE_REFS:
				getServiceRefs().clear();
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
			case Webservice_clientPackage.WEB_SERVICES_CLIENT__COMPONENT_SCOPED_REFS:
				return componentScopedRefs != null && !componentScopedRefs.isEmpty();
			case Webservice_clientPackage.WEB_SERVICES_CLIENT__SERVICE_REFS:
				return serviceRefs != null && !serviceRefs.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //WebServicesClientImpl
