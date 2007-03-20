/**
 * <copyright>
 * </copyright>
 *
 * $Id: EJBJarDeploymentDescriptorImpl.java,v 1.2 2007/03/26 21:04:14 jsholl Exp $
 */
package org.eclipse.jst.javaee.ejb.internal.impl;

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

import org.eclipse.jst.javaee.ejb.EJBJar;
import org.eclipse.jst.javaee.ejb.EJBJarDeploymentDescriptor;

import org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EJB Jar Deployment Descriptor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EJBJarDeploymentDescriptorImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EJBJarDeploymentDescriptorImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EJBJarDeploymentDescriptorImpl#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EJBJarDeploymentDescriptorImpl#getEjbJar <em>Ejb Jar</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EJBJarDeploymentDescriptorImpl extends EObjectImpl implements EJBJarDeploymentDescriptor {
	/**
	 * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMixed()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap mixed = null;

	/**
	 * The cached value of the '{@link #getXMLNSPrefixMap() <em>XMLNS Prefix Map</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXMLNSPrefixMap()
	 * @generated
	 * @ordered
	 */
	protected EMap xMLNSPrefixMap = null;

	/**
	 * The cached value of the '{@link #getXSISchemaLocation() <em>XSI Schema Location</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXSISchemaLocation()
	 * @generated
	 * @ordered
	 */
	protected EMap xSISchemaLocation = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EJBJarDeploymentDescriptorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return EjbPackage.Literals.EJB_JAR_DEPLOYMENT_DESCRIPTOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getMixed() {
		if (mixed == null) {
			mixed = new BasicFeatureMap(this, EjbPackage.EJB_JAR_DEPLOYMENT_DESCRIPTOR__MIXED);
		}
		return mixed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map getXMLNSPrefixMap() {
		if (xMLNSPrefixMap == null) {
			xMLNSPrefixMap = new EcoreEMap(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, EjbPackage.EJB_JAR_DEPLOYMENT_DESCRIPTOR__XMLNS_PREFIX_MAP);
		}
		return xMLNSPrefixMap.map();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map getXSISchemaLocation() {
		if (xSISchemaLocation == null) {
			xSISchemaLocation = new EcoreEMap(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, EjbPackage.EJB_JAR_DEPLOYMENT_DESCRIPTOR__XSI_SCHEMA_LOCATION);
		}
		return xSISchemaLocation.map();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EJBJar getEjbJar() {
		return (EJBJar)getMixed().get(EjbPackage.Literals.EJB_JAR_DEPLOYMENT_DESCRIPTOR__EJB_JAR, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEjbJar(EJBJar newEjbJar, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(EjbPackage.Literals.EJB_JAR_DEPLOYMENT_DESCRIPTOR__EJB_JAR, newEjbJar, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEjbJar(EJBJar newEjbJar) {
		((FeatureMap.Internal)getMixed()).set(EjbPackage.Literals.EJB_JAR_DEPLOYMENT_DESCRIPTOR__EJB_JAR, newEjbJar);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EjbPackage.EJB_JAR_DEPLOYMENT_DESCRIPTOR__MIXED:
				return ((InternalEList)getMixed()).basicRemove(otherEnd, msgs);
			case EjbPackage.EJB_JAR_DEPLOYMENT_DESCRIPTOR__XMLNS_PREFIX_MAP:
				return ((InternalEList)((EMap.InternalMapView)getXMLNSPrefixMap()).eMap()).basicRemove(otherEnd, msgs);
			case EjbPackage.EJB_JAR_DEPLOYMENT_DESCRIPTOR__XSI_SCHEMA_LOCATION:
				return ((InternalEList)((EMap.InternalMapView)getXSISchemaLocation()).eMap()).basicRemove(otherEnd, msgs);
			case EjbPackage.EJB_JAR_DEPLOYMENT_DESCRIPTOR__EJB_JAR:
				return basicSetEjbJar(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EjbPackage.EJB_JAR_DEPLOYMENT_DESCRIPTOR__MIXED:
				if (coreType) return getMixed();
				return ((FeatureMap.Internal)getMixed()).getWrapper();
			case EjbPackage.EJB_JAR_DEPLOYMENT_DESCRIPTOR__XMLNS_PREFIX_MAP:
				if (coreType) return ((EMap.InternalMapView)getXMLNSPrefixMap()).eMap();
				else return getXMLNSPrefixMap();
			case EjbPackage.EJB_JAR_DEPLOYMENT_DESCRIPTOR__XSI_SCHEMA_LOCATION:
				if (coreType) return ((EMap.InternalMapView)getXSISchemaLocation()).eMap();
				else return getXSISchemaLocation();
			case EjbPackage.EJB_JAR_DEPLOYMENT_DESCRIPTOR__EJB_JAR:
				return getEjbJar();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case EjbPackage.EJB_JAR_DEPLOYMENT_DESCRIPTOR__MIXED:
				((FeatureMap.Internal)getMixed()).set(newValue);
				return;
			case EjbPackage.EJB_JAR_DEPLOYMENT_DESCRIPTOR__XMLNS_PREFIX_MAP:
				((EStructuralFeature.Setting)((EMap.InternalMapView)getXMLNSPrefixMap()).eMap()).set(newValue);
				return;
			case EjbPackage.EJB_JAR_DEPLOYMENT_DESCRIPTOR__XSI_SCHEMA_LOCATION:
				((EStructuralFeature.Setting)((EMap.InternalMapView)getXSISchemaLocation()).eMap()).set(newValue);
				return;
			case EjbPackage.EJB_JAR_DEPLOYMENT_DESCRIPTOR__EJB_JAR:
				setEjbJar((EJBJar)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case EjbPackage.EJB_JAR_DEPLOYMENT_DESCRIPTOR__MIXED:
				getMixed().clear();
				return;
			case EjbPackage.EJB_JAR_DEPLOYMENT_DESCRIPTOR__XMLNS_PREFIX_MAP:
				getXMLNSPrefixMap().clear();
				return;
			case EjbPackage.EJB_JAR_DEPLOYMENT_DESCRIPTOR__XSI_SCHEMA_LOCATION:
				getXSISchemaLocation().clear();
				return;
			case EjbPackage.EJB_JAR_DEPLOYMENT_DESCRIPTOR__EJB_JAR:
				setEjbJar((EJBJar)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case EjbPackage.EJB_JAR_DEPLOYMENT_DESCRIPTOR__MIXED:
				return mixed != null && !mixed.isEmpty();
			case EjbPackage.EJB_JAR_DEPLOYMENT_DESCRIPTOR__XMLNS_PREFIX_MAP:
				return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
			case EjbPackage.EJB_JAR_DEPLOYMENT_DESCRIPTOR__XSI_SCHEMA_LOCATION:
				return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
			case EjbPackage.EJB_JAR_DEPLOYMENT_DESCRIPTOR__EJB_JAR:
				return getEjbJar() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (mixed: "); //$NON-NLS-1$
		result.append(mixed);
		result.append(')');
		return result.toString();
	}

} //EJBJarDeploymentDescriptorImpl