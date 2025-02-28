/*******************************************************************************
 * Copyright (c) 2009, 2025 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.jca.internal.impl;

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

import org.eclipse.jst.javaee.jca.Connector;
import org.eclipse.jst.javaee.jca.ConnectorDeploymentDescriptor;

import org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connector Deployment Descriptor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ConnectorDeploymentDescriptorImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ConnectorDeploymentDescriptorImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ConnectorDeploymentDescriptorImpl#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ConnectorDeploymentDescriptorImpl#getConnector <em>Connector</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConnectorDeploymentDescriptorImpl extends EObjectImpl implements ConnectorDeploymentDescriptor {
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
	protected ConnectorDeploymentDescriptorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JcaPackage.Literals.CONNECTOR_DEPLOYMENT_DESCRIPTOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FeatureMap getMixed() {
		if (mixed == null) {
			mixed = new BasicFeatureMap(this, JcaPackage.CONNECTOR_DEPLOYMENT_DESCRIPTOR__MIXED);
		}
		return mixed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Map<String, String> getXMLNSPrefixMap() {
		if (xMLNSPrefixMap == null) {
			xMLNSPrefixMap = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, JcaPackage.CONNECTOR_DEPLOYMENT_DESCRIPTOR__XMLNS_PREFIX_MAP);
		}
		return xMLNSPrefixMap.map();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Map<String, String> getXSISchemaLocation() {
		if (xSISchemaLocation == null) {
			xSISchemaLocation = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, JcaPackage.CONNECTOR_DEPLOYMENT_DESCRIPTOR__XSI_SCHEMA_LOCATION);
		}
		return xSISchemaLocation.map();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Connector getConnector() {
		return (Connector)getMixed().get(JcaPackage.Literals.CONNECTOR_DEPLOYMENT_DESCRIPTOR__CONNECTOR, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConnector(Connector newConnector, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(JcaPackage.Literals.CONNECTOR_DEPLOYMENT_DESCRIPTOR__CONNECTOR, newConnector, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setConnector(Connector newConnector) {
		getMixed().set(JcaPackage.Literals.CONNECTOR_DEPLOYMENT_DESCRIPTOR__CONNECTOR, newConnector);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JcaPackage.CONNECTOR_DEPLOYMENT_DESCRIPTOR__MIXED:
				return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
			case JcaPackage.CONNECTOR_DEPLOYMENT_DESCRIPTOR__XMLNS_PREFIX_MAP:
				return ((InternalEList<?>)((EMap.InternalMapView<String, String>)getXMLNSPrefixMap()).eMap()).basicRemove(otherEnd, msgs);
			case JcaPackage.CONNECTOR_DEPLOYMENT_DESCRIPTOR__XSI_SCHEMA_LOCATION:
				return ((InternalEList<?>)((EMap.InternalMapView<String, String>)getXSISchemaLocation()).eMap()).basicRemove(otherEnd, msgs);
			case JcaPackage.CONNECTOR_DEPLOYMENT_DESCRIPTOR__CONNECTOR:
				return basicSetConnector(null, msgs);
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
			case JcaPackage.CONNECTOR_DEPLOYMENT_DESCRIPTOR__MIXED:
				if (coreType) return getMixed();
				return ((FeatureMap.Internal)getMixed()).getWrapper();
			case JcaPackage.CONNECTOR_DEPLOYMENT_DESCRIPTOR__XMLNS_PREFIX_MAP:
				if (coreType) return ((EMap.InternalMapView<String, String>)getXMLNSPrefixMap()).eMap();
				else return getXMLNSPrefixMap();
			case JcaPackage.CONNECTOR_DEPLOYMENT_DESCRIPTOR__XSI_SCHEMA_LOCATION:
				if (coreType) return ((EMap.InternalMapView<String, String>)getXSISchemaLocation()).eMap();
				else return getXSISchemaLocation();
			case JcaPackage.CONNECTOR_DEPLOYMENT_DESCRIPTOR__CONNECTOR:
				return getConnector();
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
			case JcaPackage.CONNECTOR_DEPLOYMENT_DESCRIPTOR__MIXED:
				((FeatureMap.Internal)getMixed()).set(newValue);
				return;
			case JcaPackage.CONNECTOR_DEPLOYMENT_DESCRIPTOR__XMLNS_PREFIX_MAP:
				((EStructuralFeature.Setting)((EMap.InternalMapView<String, String>)getXMLNSPrefixMap()).eMap()).set(newValue);
				return;
			case JcaPackage.CONNECTOR_DEPLOYMENT_DESCRIPTOR__XSI_SCHEMA_LOCATION:
				((EStructuralFeature.Setting)((EMap.InternalMapView<String, String>)getXSISchemaLocation()).eMap()).set(newValue);
				return;
			case JcaPackage.CONNECTOR_DEPLOYMENT_DESCRIPTOR__CONNECTOR:
				setConnector((Connector)newValue);
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
			case JcaPackage.CONNECTOR_DEPLOYMENT_DESCRIPTOR__MIXED:
				getMixed().clear();
				return;
			case JcaPackage.CONNECTOR_DEPLOYMENT_DESCRIPTOR__XMLNS_PREFIX_MAP:
				getXMLNSPrefixMap().clear();
				return;
			case JcaPackage.CONNECTOR_DEPLOYMENT_DESCRIPTOR__XSI_SCHEMA_LOCATION:
				getXSISchemaLocation().clear();
				return;
			case JcaPackage.CONNECTOR_DEPLOYMENT_DESCRIPTOR__CONNECTOR:
				setConnector((Connector)null);
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
			case JcaPackage.CONNECTOR_DEPLOYMENT_DESCRIPTOR__MIXED:
				return mixed != null && !mixed.isEmpty();
			case JcaPackage.CONNECTOR_DEPLOYMENT_DESCRIPTOR__XMLNS_PREFIX_MAP:
				return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
			case JcaPackage.CONNECTOR_DEPLOYMENT_DESCRIPTOR__XSI_SCHEMA_LOCATION:
				return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
			case JcaPackage.CONNECTOR_DEPLOYMENT_DESCRIPTOR__CONNECTOR:
				return getConnector() != null;
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

} //ConnectorDeploymentDescriptorImpl
