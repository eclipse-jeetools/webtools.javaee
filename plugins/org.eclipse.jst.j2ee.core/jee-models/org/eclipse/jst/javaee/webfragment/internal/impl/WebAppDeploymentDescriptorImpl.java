/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.webfragment.internal.impl;

import java.util.Map;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jst.javaee.web.WebFragment;

import org.eclipse.jst.javaee.webfragment.WebAppDeploymentDescriptor;

import org.eclipse.jst.javaee.webfragment.internal.metadata.WebfragmentPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Web App Deployment Descriptor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.webfragment.internal.impl.WebAppDeploymentDescriptorImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.webfragment.internal.impl.WebAppDeploymentDescriptorImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.webfragment.internal.impl.WebAppDeploymentDescriptorImpl#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.webfragment.internal.impl.WebAppDeploymentDescriptorImpl#getWebFragment <em>Web Fragment</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WebAppDeploymentDescriptorImpl extends EObjectImpl implements WebAppDeploymentDescriptor {
	/**
	 * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMixed()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap mixed;

	/**
	 * The cached value of the '{@link #getXMLNSPrefixMap() <em>XMLNS Prefix Map</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXMLNSPrefixMap()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> xMLNSPrefixMap;

	/**
	 * The cached value of the '{@link #getXSISchemaLocation() <em>XSI Schema Location</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXSISchemaLocation()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> xSISchemaLocation;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WebAppDeploymentDescriptorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WebfragmentPackage.Literals.WEB_APP_DEPLOYMENT_DESCRIPTOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getMixed() {
		if (mixed == null) {
			mixed = new BasicFeatureMap(this, WebfragmentPackage.WEB_APP_DEPLOYMENT_DESCRIPTOR__MIXED);
		}
		return mixed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map<String, String> getXMLNSPrefixMap() {
		if (xMLNSPrefixMap == null) {
			xMLNSPrefixMap = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, WebfragmentPackage.WEB_APP_DEPLOYMENT_DESCRIPTOR__XMLNS_PREFIX_MAP);
		}
		return xMLNSPrefixMap.map();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map<String, String> getXSISchemaLocation() {
		if (xSISchemaLocation == null) {
			xSISchemaLocation = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, WebfragmentPackage.WEB_APP_DEPLOYMENT_DESCRIPTOR__XSI_SCHEMA_LOCATION);
		}
		return xSISchemaLocation.map();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WebFragment getWebFragment() {
		return (WebFragment)getMixed().get(WebfragmentPackage.Literals.WEB_APP_DEPLOYMENT_DESCRIPTOR__WEB_FRAGMENT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWebFragment(WebFragment newWebFragment, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(WebfragmentPackage.Literals.WEB_APP_DEPLOYMENT_DESCRIPTOR__WEB_FRAGMENT, newWebFragment, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWebFragment(WebFragment newWebFragment) {
		((FeatureMap.Internal)getMixed()).set(WebfragmentPackage.Literals.WEB_APP_DEPLOYMENT_DESCRIPTOR__WEB_FRAGMENT, newWebFragment);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WebfragmentPackage.WEB_APP_DEPLOYMENT_DESCRIPTOR__MIXED:
				return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
			case WebfragmentPackage.WEB_APP_DEPLOYMENT_DESCRIPTOR__XMLNS_PREFIX_MAP:
				return ((InternalEList<?>)((EMap.InternalMapView<String, String>)getXMLNSPrefixMap()).eMap()).basicRemove(otherEnd, msgs);
			case WebfragmentPackage.WEB_APP_DEPLOYMENT_DESCRIPTOR__XSI_SCHEMA_LOCATION:
				return ((InternalEList<?>)((EMap.InternalMapView<String, String>)getXSISchemaLocation()).eMap()).basicRemove(otherEnd, msgs);
			case WebfragmentPackage.WEB_APP_DEPLOYMENT_DESCRIPTOR__WEB_FRAGMENT:
				return basicSetWebFragment(null, msgs);
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
			case WebfragmentPackage.WEB_APP_DEPLOYMENT_DESCRIPTOR__MIXED:
				if (coreType) return getMixed();
				return ((FeatureMap.Internal)getMixed()).getWrapper();
			case WebfragmentPackage.WEB_APP_DEPLOYMENT_DESCRIPTOR__XMLNS_PREFIX_MAP:
				if (coreType) return ((EMap.InternalMapView<String, String>)getXMLNSPrefixMap()).eMap();
				else return getXMLNSPrefixMap();
			case WebfragmentPackage.WEB_APP_DEPLOYMENT_DESCRIPTOR__XSI_SCHEMA_LOCATION:
				if (coreType) return ((EMap.InternalMapView<String, String>)getXSISchemaLocation()).eMap();
				else return getXSISchemaLocation();
			case WebfragmentPackage.WEB_APP_DEPLOYMENT_DESCRIPTOR__WEB_FRAGMENT:
				return getWebFragment();
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
			case WebfragmentPackage.WEB_APP_DEPLOYMENT_DESCRIPTOR__MIXED:
				((FeatureMap.Internal)getMixed()).set(newValue);
				return;
			case WebfragmentPackage.WEB_APP_DEPLOYMENT_DESCRIPTOR__XMLNS_PREFIX_MAP:
				((EStructuralFeature.Setting)((EMap.InternalMapView<String, String>)getXMLNSPrefixMap()).eMap()).set(newValue);
				return;
			case WebfragmentPackage.WEB_APP_DEPLOYMENT_DESCRIPTOR__XSI_SCHEMA_LOCATION:
				((EStructuralFeature.Setting)((EMap.InternalMapView<String, String>)getXSISchemaLocation()).eMap()).set(newValue);
				return;
			case WebfragmentPackage.WEB_APP_DEPLOYMENT_DESCRIPTOR__WEB_FRAGMENT:
				setWebFragment((WebFragment)newValue);
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
			case WebfragmentPackage.WEB_APP_DEPLOYMENT_DESCRIPTOR__MIXED:
				getMixed().clear();
				return;
			case WebfragmentPackage.WEB_APP_DEPLOYMENT_DESCRIPTOR__XMLNS_PREFIX_MAP:
				getXMLNSPrefixMap().clear();
				return;
			case WebfragmentPackage.WEB_APP_DEPLOYMENT_DESCRIPTOR__XSI_SCHEMA_LOCATION:
				getXSISchemaLocation().clear();
				return;
			case WebfragmentPackage.WEB_APP_DEPLOYMENT_DESCRIPTOR__WEB_FRAGMENT:
				setWebFragment((WebFragment)null);
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
			case WebfragmentPackage.WEB_APP_DEPLOYMENT_DESCRIPTOR__MIXED:
				return mixed != null && !mixed.isEmpty();
			case WebfragmentPackage.WEB_APP_DEPLOYMENT_DESCRIPTOR__XMLNS_PREFIX_MAP:
				return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
			case WebfragmentPackage.WEB_APP_DEPLOYMENT_DESCRIPTOR__XSI_SCHEMA_LOCATION:
				return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
			case WebfragmentPackage.WEB_APP_DEPLOYMENT_DESCRIPTOR__WEB_FRAGMENT:
				return getWebFragment() != null;
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
		result.append(" (mixed: "); //$NON-NLS-1$
		result.append(mixed);
		result.append(')');
		return result.toString();
	}

} //WebAppDeploymentDescriptorImpl
