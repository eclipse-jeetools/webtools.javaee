/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.jca.internal.impl;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.DisplayName;
import org.eclipse.jst.javaee.core.Icon;

import org.eclipse.jst.javaee.jca.Connector;
import org.eclipse.jst.javaee.jca.License;
import org.eclipse.jst.javaee.jca.ResourceAdapter;

import org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connector</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ConnectorImpl#getModuleName <em>Module Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ConnectorImpl#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ConnectorImpl#getDisplayNames <em>Display Names</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ConnectorImpl#getIcons <em>Icons</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ConnectorImpl#getVendorName <em>Vendor Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ConnectorImpl#getEisType <em>Eis Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ConnectorImpl#getResourceadapterVersion <em>Resourceadapter Version</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ConnectorImpl#getLicense <em>License</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ConnectorImpl#getResourceadapter <em>Resourceadapter</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ConnectorImpl#getRequiredWorkContext <em>Required Work Context</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ConnectorImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ConnectorImpl#isMetadataComplete <em>Metadata Complete</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ConnectorImpl#getVersion <em>Version</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConnectorImpl extends EObjectImpl implements Connector {
	/**
	 * The default value of the '{@link #getModuleName() <em>Module Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModuleName()
	 * @generated
	 * @ordered
	 */
	protected static final String MODULE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getModuleName() <em>Module Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModuleName()
	 * @generated
	 * @ordered
	 */
	protected String moduleName = MODULE_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDescriptions() <em>Descriptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptions()
	 * @generated
	 * @ordered
	 */
	protected EList<Description> descriptions;

	/**
	 * The cached value of the '{@link #getDisplayNames() <em>Display Names</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayNames()
	 * @generated
	 * @ordered
	 */
	protected EList<DisplayName> displayNames;

	/**
	 * The cached value of the '{@link #getIcons() <em>Icons</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIcons()
	 * @generated
	 * @ordered
	 */
	protected EList<Icon> icons;

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
	 * The cached value of the '{@link #getVendorName() <em>Vendor Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVendorName()
	 * @generated
	 * @ordered
	 */
	protected String vendorName = VENDOR_NAME_EDEFAULT;

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
	 * The cached value of the '{@link #getEisType() <em>Eis Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEisType()
	 * @generated
	 * @ordered
	 */
	protected String eisType = EIS_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getResourceadapterVersion() <em>Resourceadapter Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceadapterVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String RESOURCEADAPTER_VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResourceadapterVersion() <em>Resourceadapter Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceadapterVersion()
	 * @generated
	 * @ordered
	 */
	protected String resourceadapterVersion = RESOURCEADAPTER_VERSION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLicense() <em>License</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLicense()
	 * @generated
	 * @ordered
	 */
	protected License license;

	/**
	 * The cached value of the '{@link #getResourceadapter() <em>Resourceadapter</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceadapter()
	 * @generated
	 * @ordered
	 */
	protected ResourceAdapter resourceadapter;

	/**
	 * The cached value of the '{@link #getRequiredWorkContext() <em>Required Work Context</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequiredWorkContext()
	 * @generated
	 * @ordered
	 */
	protected EList<String> requiredWorkContext;

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
	 * The default value of the '{@link #isMetadataComplete() <em>Metadata Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMetadataComplete()
	 * @generated
	 * @ordered
	 */
	protected static final boolean METADATA_COMPLETE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMetadataComplete() <em>Metadata Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMetadataComplete()
	 * @generated
	 * @ordered
	 */
	protected boolean metadataComplete = METADATA_COMPLETE_EDEFAULT;

	/**
	 * This is true if the Metadata Complete attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean metadataCompleteESet;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = "1.7"; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

	/**
	 * This is true if the Version attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean versionESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConnectorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JcaPackage.Literals.CONNECTOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModuleName(String newModuleName) {
		String oldModuleName = moduleName;
		moduleName = newModuleName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.CONNECTOR__MODULE_NAME, oldModuleName, moduleName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Description> getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList<Description>(Description.class, this, JcaPackage.CONNECTOR__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<DisplayName> getDisplayNames() {
		if (displayNames == null) {
			displayNames = new EObjectContainmentEList<DisplayName>(DisplayName.class, this, JcaPackage.CONNECTOR__DISPLAY_NAMES);
		}
		return displayNames;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Icon> getIcons() {
		if (icons == null) {
			icons = new EObjectContainmentEList<Icon>(Icon.class, this, JcaPackage.CONNECTOR__ICONS);
		}
		return icons;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVendorName() {
		return vendorName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVendorName(String newVendorName) {
		String oldVendorName = vendorName;
		vendorName = newVendorName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.CONNECTOR__VENDOR_NAME, oldVendorName, vendorName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEisType() {
		return eisType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEisType(String newEisType) {
		String oldEisType = eisType;
		eisType = newEisType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.CONNECTOR__EIS_TYPE, oldEisType, eisType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getResourceadapterVersion() {
		return resourceadapterVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResourceadapterVersion(String newResourceadapterVersion) {
		String oldResourceadapterVersion = resourceadapterVersion;
		resourceadapterVersion = newResourceadapterVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.CONNECTOR__RESOURCEADAPTER_VERSION, oldResourceadapterVersion, resourceadapterVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceAdapter getResourceadapter() {
		return resourceadapter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetResourceadapter(ResourceAdapter newResourceadapter, NotificationChain msgs) {
		ResourceAdapter oldResourceadapter = resourceadapter;
		resourceadapter = newResourceadapter;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JcaPackage.CONNECTOR__RESOURCEADAPTER, oldResourceadapter, newResourceadapter);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResourceadapter(ResourceAdapter newResourceadapter) {
		if (newResourceadapter != resourceadapter) {
			NotificationChain msgs = null;
			if (resourceadapter != null)
				msgs = ((InternalEObject)resourceadapter).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JcaPackage.CONNECTOR__RESOURCEADAPTER, null, msgs);
			if (newResourceadapter != null)
				msgs = ((InternalEObject)newResourceadapter).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JcaPackage.CONNECTOR__RESOURCEADAPTER, null, msgs);
			msgs = basicSetResourceadapter(newResourceadapter, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.CONNECTOR__RESOURCEADAPTER, newResourceadapter, newResourceadapter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<String> getRequiredWorkContext() {
		if (requiredWorkContext == null) {
			requiredWorkContext = new EDataTypeEList<String>(String.class, this, JcaPackage.CONNECTOR__REQUIRED_WORK_CONTEXT);
		}
		return requiredWorkContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.CONNECTOR__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMetadataComplete() {
		return metadataComplete;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMetadataComplete(boolean newMetadataComplete) {
		boolean oldMetadataComplete = metadataComplete;
		metadataComplete = newMetadataComplete;
		boolean oldMetadataCompleteESet = metadataCompleteESet;
		metadataCompleteESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.CONNECTOR__METADATA_COMPLETE, oldMetadataComplete, metadataComplete, !oldMetadataCompleteESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetMetadataComplete() {
		boolean oldMetadataComplete = metadataComplete;
		boolean oldMetadataCompleteESet = metadataCompleteESet;
		metadataComplete = METADATA_COMPLETE_EDEFAULT;
		metadataCompleteESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JcaPackage.CONNECTOR__METADATA_COMPLETE, oldMetadataComplete, METADATA_COMPLETE_EDEFAULT, oldMetadataCompleteESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetMetadataComplete() {
		return metadataCompleteESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		boolean oldVersionESet = versionESet;
		versionESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.CONNECTOR__VERSION, oldVersion, version, !oldVersionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetVersion() {
		String oldVersion = version;
		boolean oldVersionESet = versionESet;
		version = VERSION_EDEFAULT;
		versionESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JcaPackage.CONNECTOR__VERSION, oldVersion, VERSION_EDEFAULT, oldVersionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetVersion() {
		return versionESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JcaPackage.CONNECTOR__DESCRIPTIONS:
				return ((InternalEList<?>)getDescriptions()).basicRemove(otherEnd, msgs);
			case JcaPackage.CONNECTOR__DISPLAY_NAMES:
				return ((InternalEList<?>)getDisplayNames()).basicRemove(otherEnd, msgs);
			case JcaPackage.CONNECTOR__ICONS:
				return ((InternalEList<?>)getIcons()).basicRemove(otherEnd, msgs);
			case JcaPackage.CONNECTOR__LICENSE:
				return basicSetLicense(null, msgs);
			case JcaPackage.CONNECTOR__RESOURCEADAPTER:
				return basicSetResourceadapter(null, msgs);
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
			case JcaPackage.CONNECTOR__MODULE_NAME:
				return getModuleName();
			case JcaPackage.CONNECTOR__DESCRIPTIONS:
				return getDescriptions();
			case JcaPackage.CONNECTOR__DISPLAY_NAMES:
				return getDisplayNames();
			case JcaPackage.CONNECTOR__ICONS:
				return getIcons();
			case JcaPackage.CONNECTOR__VENDOR_NAME:
				return getVendorName();
			case JcaPackage.CONNECTOR__EIS_TYPE:
				return getEisType();
			case JcaPackage.CONNECTOR__RESOURCEADAPTER_VERSION:
				return getResourceadapterVersion();
			case JcaPackage.CONNECTOR__LICENSE:
				return getLicense();
			case JcaPackage.CONNECTOR__RESOURCEADAPTER:
				return getResourceadapter();
			case JcaPackage.CONNECTOR__REQUIRED_WORK_CONTEXT:
				return getRequiredWorkContext();
			case JcaPackage.CONNECTOR__ID:
				return getId();
			case JcaPackage.CONNECTOR__METADATA_COMPLETE:
				return isMetadataComplete();
			case JcaPackage.CONNECTOR__VERSION:
				return getVersion();
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
			case JcaPackage.CONNECTOR__MODULE_NAME:
				setModuleName((String)newValue);
				return;
			case JcaPackage.CONNECTOR__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection<? extends Description>)newValue);
				return;
			case JcaPackage.CONNECTOR__DISPLAY_NAMES:
				getDisplayNames().clear();
				getDisplayNames().addAll((Collection<? extends DisplayName>)newValue);
				return;
			case JcaPackage.CONNECTOR__ICONS:
				getIcons().clear();
				getIcons().addAll((Collection<? extends Icon>)newValue);
				return;
			case JcaPackage.CONNECTOR__VENDOR_NAME:
				setVendorName((String)newValue);
				return;
			case JcaPackage.CONNECTOR__EIS_TYPE:
				setEisType((String)newValue);
				return;
			case JcaPackage.CONNECTOR__RESOURCEADAPTER_VERSION:
				setResourceadapterVersion((String)newValue);
				return;
			case JcaPackage.CONNECTOR__LICENSE:
				setLicense((License)newValue);
				return;
			case JcaPackage.CONNECTOR__RESOURCEADAPTER:
				setResourceadapter((ResourceAdapter)newValue);
				return;
			case JcaPackage.CONNECTOR__REQUIRED_WORK_CONTEXT:
				getRequiredWorkContext().clear();
				getRequiredWorkContext().addAll((Collection<? extends String>)newValue);
				return;
			case JcaPackage.CONNECTOR__ID:
				setId((String)newValue);
				return;
			case JcaPackage.CONNECTOR__METADATA_COMPLETE:
				setMetadataComplete((Boolean)newValue);
				return;
			case JcaPackage.CONNECTOR__VERSION:
				setVersion((String)newValue);
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
			case JcaPackage.CONNECTOR__MODULE_NAME:
				setModuleName(MODULE_NAME_EDEFAULT);
				return;
			case JcaPackage.CONNECTOR__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case JcaPackage.CONNECTOR__DISPLAY_NAMES:
				getDisplayNames().clear();
				return;
			case JcaPackage.CONNECTOR__ICONS:
				getIcons().clear();
				return;
			case JcaPackage.CONNECTOR__VENDOR_NAME:
				setVendorName(VENDOR_NAME_EDEFAULT);
				return;
			case JcaPackage.CONNECTOR__EIS_TYPE:
				setEisType(EIS_TYPE_EDEFAULT);
				return;
			case JcaPackage.CONNECTOR__RESOURCEADAPTER_VERSION:
				setResourceadapterVersion(RESOURCEADAPTER_VERSION_EDEFAULT);
				return;
			case JcaPackage.CONNECTOR__LICENSE:
				setLicense((License)null);
				return;
			case JcaPackage.CONNECTOR__RESOURCEADAPTER:
				setResourceadapter((ResourceAdapter)null);
				return;
			case JcaPackage.CONNECTOR__REQUIRED_WORK_CONTEXT:
				getRequiredWorkContext().clear();
				return;
			case JcaPackage.CONNECTOR__ID:
				setId(ID_EDEFAULT);
				return;
			case JcaPackage.CONNECTOR__METADATA_COMPLETE:
				unsetMetadataComplete();
				return;
			case JcaPackage.CONNECTOR__VERSION:
				unsetVersion();
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
			case JcaPackage.CONNECTOR__MODULE_NAME:
				return MODULE_NAME_EDEFAULT == null ? moduleName != null : !MODULE_NAME_EDEFAULT.equals(moduleName);
			case JcaPackage.CONNECTOR__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case JcaPackage.CONNECTOR__DISPLAY_NAMES:
				return displayNames != null && !displayNames.isEmpty();
			case JcaPackage.CONNECTOR__ICONS:
				return icons != null && !icons.isEmpty();
			case JcaPackage.CONNECTOR__VENDOR_NAME:
				return VENDOR_NAME_EDEFAULT == null ? vendorName != null : !VENDOR_NAME_EDEFAULT.equals(vendorName);
			case JcaPackage.CONNECTOR__EIS_TYPE:
				return EIS_TYPE_EDEFAULT == null ? eisType != null : !EIS_TYPE_EDEFAULT.equals(eisType);
			case JcaPackage.CONNECTOR__RESOURCEADAPTER_VERSION:
				return RESOURCEADAPTER_VERSION_EDEFAULT == null ? resourceadapterVersion != null : !RESOURCEADAPTER_VERSION_EDEFAULT.equals(resourceadapterVersion);
			case JcaPackage.CONNECTOR__LICENSE:
				return license != null;
			case JcaPackage.CONNECTOR__RESOURCEADAPTER:
				return resourceadapter != null;
			case JcaPackage.CONNECTOR__REQUIRED_WORK_CONTEXT:
				return requiredWorkContext != null && !requiredWorkContext.isEmpty();
			case JcaPackage.CONNECTOR__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case JcaPackage.CONNECTOR__METADATA_COMPLETE:
				return isSetMetadataComplete();
			case JcaPackage.CONNECTOR__VERSION:
				return isSetVersion();
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
		result.append(" (moduleName: "); //$NON-NLS-1$
		result.append(moduleName);
		result.append(", vendorName: "); //$NON-NLS-1$
		result.append(vendorName);
		result.append(", eisType: "); //$NON-NLS-1$
		result.append(eisType);
		result.append(", resourceadapterVersion: "); //$NON-NLS-1$
		result.append(resourceadapterVersion);
		result.append(", requiredWorkContext: "); //$NON-NLS-1$
		result.append(requiredWorkContext);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(", metadataComplete: "); //$NON-NLS-1$
		if (metadataCompleteESet) result.append(metadataComplete); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", version: "); //$NON-NLS-1$
		if (versionESet) result.append(version); else result.append("<unset>"); //$NON-NLS-1$
		result.append(')');
		return result.toString();
	}

} //ConnectorImpl
