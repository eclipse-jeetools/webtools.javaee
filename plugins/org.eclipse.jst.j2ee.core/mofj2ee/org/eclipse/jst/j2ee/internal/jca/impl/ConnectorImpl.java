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
package org.eclipse.jst.j2ee.internal.jca.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.common.J2EEVersionResource;
import org.eclipse.jst.j2ee.internal.common.impl.CompatibilityDescriptionGroupImpl;
import org.eclipse.jst.j2ee.internal.jca.JcaPackage;
import org.eclipse.jst.j2ee.jca.Connector;
import org.eclipse.jst.j2ee.jca.License;
import org.eclipse.jst.j2ee.jca.ResourceAdapter;


/**
 * The connector element is the root element of the deployment descriptor for the resource adapter. This element includes general information - vendor name, version, specification version supported, icon -  about the resource adapter module. It also includes information specific to the implementation of the resource adapter library as specified through the element resourceadapter.

 */
public class ConnectorImpl extends CompatibilityDescriptionGroupImpl implements Connector{

	/**
	 * The default value of the '{@link #getVendorName() <em>Vendor Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVendorName()
	 * @generated
	 * @ordered
	 */
	protected static final String VENDOR_NAME_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String vendorName = VENDOR_NAME_EDEFAULT;
	/**
	 * The default value of the '{@link #getSpecVersion() <em>Spec Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpecVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String SPEC_VERSION_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String specVersion = SPEC_VERSION_EDEFAULT;
	/**
	 * The default value of the '{@link #getEisType() <em>Eis Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEisType()
	 * @generated
	 * @ordered
	 */
	protected static final String EIS_TYPE_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String eisType = EIS_TYPE_EDEFAULT;
	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String version = VERSION_EDEFAULT;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected License license = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected ResourceAdapter resourceAdapter = null;
	protected ConnectorImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return JcaPackage.eINSTANCE.getConnector();
	}

	/**
	 *
	 */
	public void setDisplayName(String newDisplayName) {
		super.setDisplayName(newDisplayName);
	}
	
	/**
	* This returns the module version id.  Compare with J2EEVersionConstants to determine module level
	*/
	public int getVersionID() throws IllegalStateException {
		J2EEVersionResource res = (J2EEVersionResource) eResource();
		if (res == null) throw new IllegalStateException();
		return res.getModuleVersionID();
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Specifies the name of resource adapter provider vendor.
	 * Example: Wombat Corp.
	 */
	public String getVendorName() {
		return vendorName;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setVendorName(String newVendorName) {
		String oldVendorName = vendorName;
		vendorName = newVendorName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.CONNECTOR__VENDOR_NAME, oldVendorName, vendorName));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Specifies the version of the connector architecture specification that is supported by this resource adapter. This information enables deployer to configure the resource adapter to support deployment and runtime requirements of the corresponding connector architecture specification.

	 */
	public String getSpecVersion() {
		return specVersion;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setSpecVersion(String newSpecVersion) {
		String oldSpecVersion = specVersion;
		specVersion = newSpecVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.CONNECTOR__SPEC_VERSION, oldSpecVersion, specVersion));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Contains information about the type of the EIS. For example, the type of an EIS can be product name of EIS independent of any version info.
	 * 
	 * This helps in identifying EIS instances that can be used with
	 * this resource adapter.

	 */
	public String getEisType() {
		return eisType;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setEisType(String newEisType) {
		String oldEisType = eisType;
		eisType = newEisType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.CONNECTOR__EIS_TYPE, oldEisType, eisType));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Specifies a string-based version of the resource adapter from the resource adapter provider.
	 * 
	 * Example: 1.0
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.CONNECTOR__VERSION, oldVersion, version));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Specifies licensing requirements for the resource adapter module. This element specifies whether a license is required to deploy and use this resource adapter, and an optional description of the licensing terms (examples: duration of license, number of
	 * connection restrictions).
	 */
	public License getLicense() {
		return license;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLicense(License newLicense, NotificationChain msgs) {
		License oldLicense = license;
		license = newLicense;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JcaPackage.CONNECTOR__LICENSE, oldLicense, newLicense);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setLicense(License newLicense) {
		if (newLicense != license) {
			NotificationChain msgs = null;
			if (license != null)
				msgs = ((InternalEObject)license).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JcaPackage.CONNECTOR__LICENSE, null, msgs);
			if (newLicense != null)
				msgs = ((InternalEObject)newLicense).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JcaPackage.CONNECTOR__LICENSE, null, msgs);
			msgs = basicSetLicense(newLicense, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.CONNECTOR__LICENSE, newLicense, newLicense));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public ResourceAdapter getResourceAdapter() {
		return resourceAdapter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetResourceAdapter(ResourceAdapter newResourceAdapter, NotificationChain msgs) {
		ResourceAdapter oldResourceAdapter = resourceAdapter;
		resourceAdapter = newResourceAdapter;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JcaPackage.CONNECTOR__RESOURCE_ADAPTER, oldResourceAdapter, newResourceAdapter);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setResourceAdapter(ResourceAdapter newResourceAdapter) {
		if (newResourceAdapter != resourceAdapter) {
			NotificationChain msgs = null;
			if (resourceAdapter != null)
				msgs = ((InternalEObject)resourceAdapter).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JcaPackage.CONNECTOR__RESOURCE_ADAPTER, null, msgs);
			if (newResourceAdapter != null)
				msgs = ((InternalEObject)newResourceAdapter).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JcaPackage.CONNECTOR__RESOURCE_ADAPTER, null, msgs);
			msgs = basicSetResourceAdapter(newResourceAdapter, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.CONNECTOR__RESOURCE_ADAPTER, newResourceAdapter, newResourceAdapter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case JcaPackage.CONNECTOR__ICONS:
					return ((InternalEList)getIcons()).basicRemove(otherEnd, msgs);
				case JcaPackage.CONNECTOR__DISPLAY_NAMES:
					return ((InternalEList)getDisplayNames()).basicRemove(otherEnd, msgs);
				case JcaPackage.CONNECTOR__DESCRIPTIONS:
					return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
				case JcaPackage.CONNECTOR__LICENSE:
					return basicSetLicense(null, msgs);
				case JcaPackage.CONNECTOR__RESOURCE_ADAPTER:
					return basicSetResourceAdapter(null, msgs);
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
			case JcaPackage.CONNECTOR__ICONS:
				return getIcons();
			case JcaPackage.CONNECTOR__DISPLAY_NAMES:
				return getDisplayNames();
			case JcaPackage.CONNECTOR__DESCRIPTIONS:
				return getDescriptions();
			case JcaPackage.CONNECTOR__SMALL_ICON:
				return getSmallIcon();
			case JcaPackage.CONNECTOR__LARGE_ICON:
				return getLargeIcon();
			case JcaPackage.CONNECTOR__DESCRIPTION:
				return getDescription();
			case JcaPackage.CONNECTOR__DISPLAY_NAME:
				return getDisplayName();
			case JcaPackage.CONNECTOR__VENDOR_NAME:
				return getVendorName();
			case JcaPackage.CONNECTOR__SPEC_VERSION:
				return getSpecVersion();
			case JcaPackage.CONNECTOR__EIS_TYPE:
				return getEisType();
			case JcaPackage.CONNECTOR__VERSION:
				return getVersion();
			case JcaPackage.CONNECTOR__LICENSE:
				return getLicense();
			case JcaPackage.CONNECTOR__RESOURCE_ADAPTER:
				return getResourceAdapter();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JcaPackage.CONNECTOR__ICONS:
				return icons != null && !icons.isEmpty();
			case JcaPackage.CONNECTOR__DISPLAY_NAMES:
				return displayNames != null && !displayNames.isEmpty();
			case JcaPackage.CONNECTOR__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case JcaPackage.CONNECTOR__SMALL_ICON:
				return SMALL_ICON_EDEFAULT == null ? smallIcon != null : !SMALL_ICON_EDEFAULT.equals(smallIcon);
			case JcaPackage.CONNECTOR__LARGE_ICON:
				return LARGE_ICON_EDEFAULT == null ? largeIcon != null : !LARGE_ICON_EDEFAULT.equals(largeIcon);
			case JcaPackage.CONNECTOR__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case JcaPackage.CONNECTOR__DISPLAY_NAME:
				return DISPLAY_NAME_EDEFAULT == null ? displayName != null : !DISPLAY_NAME_EDEFAULT.equals(displayName);
			case JcaPackage.CONNECTOR__VENDOR_NAME:
				return VENDOR_NAME_EDEFAULT == null ? vendorName != null : !VENDOR_NAME_EDEFAULT.equals(vendorName);
			case JcaPackage.CONNECTOR__SPEC_VERSION:
				return SPEC_VERSION_EDEFAULT == null ? specVersion != null : !SPEC_VERSION_EDEFAULT.equals(specVersion);
			case JcaPackage.CONNECTOR__EIS_TYPE:
				return EIS_TYPE_EDEFAULT == null ? eisType != null : !EIS_TYPE_EDEFAULT.equals(eisType);
			case JcaPackage.CONNECTOR__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case JcaPackage.CONNECTOR__LICENSE:
				return license != null;
			case JcaPackage.CONNECTOR__RESOURCE_ADAPTER:
				return resourceAdapter != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JcaPackage.CONNECTOR__ICONS:
				getIcons().clear();
				getIcons().addAll((Collection)newValue);
				return;
			case JcaPackage.CONNECTOR__DISPLAY_NAMES:
				getDisplayNames().clear();
				getDisplayNames().addAll((Collection)newValue);
				return;
			case JcaPackage.CONNECTOR__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
				return;
			case JcaPackage.CONNECTOR__SMALL_ICON:
				setSmallIcon((String)newValue);
				return;
			case JcaPackage.CONNECTOR__LARGE_ICON:
				setLargeIcon((String)newValue);
				return;
			case JcaPackage.CONNECTOR__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case JcaPackage.CONNECTOR__DISPLAY_NAME:
				setDisplayName((String)newValue);
				return;
			case JcaPackage.CONNECTOR__VENDOR_NAME:
				setVendorName((String)newValue);
				return;
			case JcaPackage.CONNECTOR__SPEC_VERSION:
				setSpecVersion((String)newValue);
				return;
			case JcaPackage.CONNECTOR__EIS_TYPE:
				setEisType((String)newValue);
				return;
			case JcaPackage.CONNECTOR__VERSION:
				setVersion((String)newValue);
				return;
			case JcaPackage.CONNECTOR__LICENSE:
				setLicense((License)newValue);
				return;
			case JcaPackage.CONNECTOR__RESOURCE_ADAPTER:
				setResourceAdapter((ResourceAdapter)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JcaPackage.CONNECTOR__ICONS:
				getIcons().clear();
				return;
			case JcaPackage.CONNECTOR__DISPLAY_NAMES:
				getDisplayNames().clear();
				return;
			case JcaPackage.CONNECTOR__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case JcaPackage.CONNECTOR__SMALL_ICON:
				setSmallIcon(SMALL_ICON_EDEFAULT);
				return;
			case JcaPackage.CONNECTOR__LARGE_ICON:
				setLargeIcon(LARGE_ICON_EDEFAULT);
				return;
			case JcaPackage.CONNECTOR__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case JcaPackage.CONNECTOR__DISPLAY_NAME:
				setDisplayName(DISPLAY_NAME_EDEFAULT);
				return;
			case JcaPackage.CONNECTOR__VENDOR_NAME:
				setVendorName(VENDOR_NAME_EDEFAULT);
				return;
			case JcaPackage.CONNECTOR__SPEC_VERSION:
				setSpecVersion(SPEC_VERSION_EDEFAULT);
				return;
			case JcaPackage.CONNECTOR__EIS_TYPE:
				setEisType(EIS_TYPE_EDEFAULT);
				return;
			case JcaPackage.CONNECTOR__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case JcaPackage.CONNECTOR__LICENSE:
				setLicense((License)null);
				return;
			case JcaPackage.CONNECTOR__RESOURCE_ADAPTER:
				setResourceAdapter((ResourceAdapter)null);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (vendorName: ");//$NON-NLS-1$
		result.append(vendorName);
		result.append(", specVersion: ");//$NON-NLS-1$
		result.append(specVersion);
		result.append(", eisType: ");//$NON-NLS-1$
		result.append(eisType);
		result.append(", version: ");//$NON-NLS-1$
		result.append(version);
		result.append(')');
		return result.toString();
	}

}





