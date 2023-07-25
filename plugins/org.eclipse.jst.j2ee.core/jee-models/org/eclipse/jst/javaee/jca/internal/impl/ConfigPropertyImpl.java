/*******************************************************************************
 * Copyright (c) 2009, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jst.javaee.core.Description;

import org.eclipse.jst.javaee.jca.ConfigProperty;
import org.eclipse.jst.javaee.jca.ConfigPropertyTypeType;

import org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Config Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ConfigPropertyImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ConfigPropertyImpl#getConfigPropertyName <em>Config Property Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ConfigPropertyImpl#getConfigPropertyType <em>Config Property Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ConfigPropertyImpl#getConfigPropertyValue <em>Config Property Value</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ConfigPropertyImpl#isConfigPropertyIgnore <em>Config Property Ignore</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ConfigPropertyImpl#isConfigPropertySupportsDynamicUpdates <em>Config Property Supports Dynamic Updates</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ConfigPropertyImpl#isConfigPropertyConfidential <em>Config Property Confidential</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ConfigPropertyImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConfigPropertyImpl extends EObjectImpl implements ConfigProperty {
	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected EList<Description> description;

	/**
	 * The default value of the '{@link #getConfigPropertyName() <em>Config Property Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfigPropertyName()
	 * @generated
	 * @ordered
	 */
	protected static final String CONFIG_PROPERTY_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConfigPropertyName() <em>Config Property Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfigPropertyName()
	 * @generated
	 * @ordered
	 */
	protected String configPropertyName = CONFIG_PROPERTY_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getConfigPropertyType() <em>Config Property Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfigPropertyType()
	 * @generated
	 * @ordered
	 */
	protected static final ConfigPropertyTypeType CONFIG_PROPERTY_TYPE_EDEFAULT = ConfigPropertyTypeType.JAVA_LANG_BOOLEAN;

	/**
	 * The cached value of the '{@link #getConfigPropertyType() <em>Config Property Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfigPropertyType()
	 * @generated
	 * @ordered
	 */
	protected ConfigPropertyTypeType configPropertyType = CONFIG_PROPERTY_TYPE_EDEFAULT;

	/**
	 * This is true if the Config Property Type attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean configPropertyTypeESet;

	/**
	 * The default value of the '{@link #getConfigPropertyValue() <em>Config Property Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfigPropertyValue()
	 * @generated
	 * @ordered
	 */
	protected static final String CONFIG_PROPERTY_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConfigPropertyValue() <em>Config Property Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfigPropertyValue()
	 * @generated
	 * @ordered
	 */
	protected String configPropertyValue = CONFIG_PROPERTY_VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #isConfigPropertyIgnore() <em>Config Property Ignore</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConfigPropertyIgnore()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CONFIG_PROPERTY_IGNORE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isConfigPropertyIgnore() <em>Config Property Ignore</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConfigPropertyIgnore()
	 * @generated
	 * @ordered
	 */
	protected boolean configPropertyIgnore = CONFIG_PROPERTY_IGNORE_EDEFAULT;

	/**
	 * This is true if the Config Property Ignore attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean configPropertyIgnoreESet;

	/**
	 * The default value of the '{@link #isConfigPropertySupportsDynamicUpdates() <em>Config Property Supports Dynamic Updates</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConfigPropertySupportsDynamicUpdates()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CONFIG_PROPERTY_SUPPORTS_DYNAMIC_UPDATES_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isConfigPropertySupportsDynamicUpdates() <em>Config Property Supports Dynamic Updates</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConfigPropertySupportsDynamicUpdates()
	 * @generated
	 * @ordered
	 */
	protected boolean configPropertySupportsDynamicUpdates = CONFIG_PROPERTY_SUPPORTS_DYNAMIC_UPDATES_EDEFAULT;

	/**
	 * This is true if the Config Property Supports Dynamic Updates attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean configPropertySupportsDynamicUpdatesESet;

	/**
	 * The default value of the '{@link #isConfigPropertyConfidential() <em>Config Property Confidential</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConfigPropertyConfidential()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CONFIG_PROPERTY_CONFIDENTIAL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isConfigPropertyConfidential() <em>Config Property Confidential</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConfigPropertyConfidential()
	 * @generated
	 * @ordered
	 */
	protected boolean configPropertyConfidential = CONFIG_PROPERTY_CONFIDENTIAL_EDEFAULT;

	/**
	 * This is true if the Config Property Confidential attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean configPropertyConfidentialESet;

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
	protected ConfigPropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JcaPackage.Literals.CONFIG_PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Description> getDescription() {
		if (description == null) {
			description = new EObjectContainmentEList<Description>(Description.class, this, JcaPackage.CONFIG_PROPERTY__DESCRIPTION);
		}
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getConfigPropertyName() {
		return configPropertyName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setConfigPropertyName(String newConfigPropertyName) {
		String oldConfigPropertyName = configPropertyName;
		configPropertyName = newConfigPropertyName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.CONFIG_PROPERTY__CONFIG_PROPERTY_NAME, oldConfigPropertyName, configPropertyName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ConfigPropertyTypeType getConfigPropertyType() {
		return configPropertyType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setConfigPropertyType(ConfigPropertyTypeType newConfigPropertyType) {
		ConfigPropertyTypeType oldConfigPropertyType = configPropertyType;
		configPropertyType = newConfigPropertyType == null ? CONFIG_PROPERTY_TYPE_EDEFAULT : newConfigPropertyType;
		boolean oldConfigPropertyTypeESet = configPropertyTypeESet;
		configPropertyTypeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.CONFIG_PROPERTY__CONFIG_PROPERTY_TYPE, oldConfigPropertyType, configPropertyType, !oldConfigPropertyTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetConfigPropertyType() {
		ConfigPropertyTypeType oldConfigPropertyType = configPropertyType;
		boolean oldConfigPropertyTypeESet = configPropertyTypeESet;
		configPropertyType = CONFIG_PROPERTY_TYPE_EDEFAULT;
		configPropertyTypeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JcaPackage.CONFIG_PROPERTY__CONFIG_PROPERTY_TYPE, oldConfigPropertyType, CONFIG_PROPERTY_TYPE_EDEFAULT, oldConfigPropertyTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetConfigPropertyType() {
		return configPropertyTypeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getConfigPropertyValue() {
		return configPropertyValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setConfigPropertyValue(String newConfigPropertyValue) {
		String oldConfigPropertyValue = configPropertyValue;
		configPropertyValue = newConfigPropertyValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.CONFIG_PROPERTY__CONFIG_PROPERTY_VALUE, oldConfigPropertyValue, configPropertyValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isConfigPropertyIgnore() {
		return configPropertyIgnore;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setConfigPropertyIgnore(boolean newConfigPropertyIgnore) {
		boolean oldConfigPropertyIgnore = configPropertyIgnore;
		configPropertyIgnore = newConfigPropertyIgnore;
		boolean oldConfigPropertyIgnoreESet = configPropertyIgnoreESet;
		configPropertyIgnoreESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.CONFIG_PROPERTY__CONFIG_PROPERTY_IGNORE, oldConfigPropertyIgnore, configPropertyIgnore, !oldConfigPropertyIgnoreESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetConfigPropertyIgnore() {
		boolean oldConfigPropertyIgnore = configPropertyIgnore;
		boolean oldConfigPropertyIgnoreESet = configPropertyIgnoreESet;
		configPropertyIgnore = CONFIG_PROPERTY_IGNORE_EDEFAULT;
		configPropertyIgnoreESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JcaPackage.CONFIG_PROPERTY__CONFIG_PROPERTY_IGNORE, oldConfigPropertyIgnore, CONFIG_PROPERTY_IGNORE_EDEFAULT, oldConfigPropertyIgnoreESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetConfigPropertyIgnore() {
		return configPropertyIgnoreESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isConfigPropertySupportsDynamicUpdates() {
		return configPropertySupportsDynamicUpdates;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setConfigPropertySupportsDynamicUpdates(boolean newConfigPropertySupportsDynamicUpdates) {
		boolean oldConfigPropertySupportsDynamicUpdates = configPropertySupportsDynamicUpdates;
		configPropertySupportsDynamicUpdates = newConfigPropertySupportsDynamicUpdates;
		boolean oldConfigPropertySupportsDynamicUpdatesESet = configPropertySupportsDynamicUpdatesESet;
		configPropertySupportsDynamicUpdatesESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.CONFIG_PROPERTY__CONFIG_PROPERTY_SUPPORTS_DYNAMIC_UPDATES, oldConfigPropertySupportsDynamicUpdates, configPropertySupportsDynamicUpdates, !oldConfigPropertySupportsDynamicUpdatesESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetConfigPropertySupportsDynamicUpdates() {
		boolean oldConfigPropertySupportsDynamicUpdates = configPropertySupportsDynamicUpdates;
		boolean oldConfigPropertySupportsDynamicUpdatesESet = configPropertySupportsDynamicUpdatesESet;
		configPropertySupportsDynamicUpdates = CONFIG_PROPERTY_SUPPORTS_DYNAMIC_UPDATES_EDEFAULT;
		configPropertySupportsDynamicUpdatesESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JcaPackage.CONFIG_PROPERTY__CONFIG_PROPERTY_SUPPORTS_DYNAMIC_UPDATES, oldConfigPropertySupportsDynamicUpdates, CONFIG_PROPERTY_SUPPORTS_DYNAMIC_UPDATES_EDEFAULT, oldConfigPropertySupportsDynamicUpdatesESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetConfigPropertySupportsDynamicUpdates() {
		return configPropertySupportsDynamicUpdatesESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isConfigPropertyConfidential() {
		return configPropertyConfidential;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setConfigPropertyConfidential(boolean newConfigPropertyConfidential) {
		boolean oldConfigPropertyConfidential = configPropertyConfidential;
		configPropertyConfidential = newConfigPropertyConfidential;
		boolean oldConfigPropertyConfidentialESet = configPropertyConfidentialESet;
		configPropertyConfidentialESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.CONFIG_PROPERTY__CONFIG_PROPERTY_CONFIDENTIAL, oldConfigPropertyConfidential, configPropertyConfidential, !oldConfigPropertyConfidentialESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetConfigPropertyConfidential() {
		boolean oldConfigPropertyConfidential = configPropertyConfidential;
		boolean oldConfigPropertyConfidentialESet = configPropertyConfidentialESet;
		configPropertyConfidential = CONFIG_PROPERTY_CONFIDENTIAL_EDEFAULT;
		configPropertyConfidentialESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JcaPackage.CONFIG_PROPERTY__CONFIG_PROPERTY_CONFIDENTIAL, oldConfigPropertyConfidential, CONFIG_PROPERTY_CONFIDENTIAL_EDEFAULT, oldConfigPropertyConfidentialESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetConfigPropertyConfidential() {
		return configPropertyConfidentialESet;
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
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.CONFIG_PROPERTY__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JcaPackage.CONFIG_PROPERTY__DESCRIPTION:
				return ((InternalEList<?>)getDescription()).basicRemove(otherEnd, msgs);
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
			case JcaPackage.CONFIG_PROPERTY__DESCRIPTION:
				return getDescription();
			case JcaPackage.CONFIG_PROPERTY__CONFIG_PROPERTY_NAME:
				return getConfigPropertyName();
			case JcaPackage.CONFIG_PROPERTY__CONFIG_PROPERTY_TYPE:
				return getConfigPropertyType();
			case JcaPackage.CONFIG_PROPERTY__CONFIG_PROPERTY_VALUE:
				return getConfigPropertyValue();
			case JcaPackage.CONFIG_PROPERTY__CONFIG_PROPERTY_IGNORE:
				return isConfigPropertyIgnore();
			case JcaPackage.CONFIG_PROPERTY__CONFIG_PROPERTY_SUPPORTS_DYNAMIC_UPDATES:
				return isConfigPropertySupportsDynamicUpdates();
			case JcaPackage.CONFIG_PROPERTY__CONFIG_PROPERTY_CONFIDENTIAL:
				return isConfigPropertyConfidential();
			case JcaPackage.CONFIG_PROPERTY__ID:
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
			case JcaPackage.CONFIG_PROPERTY__DESCRIPTION:
				getDescription().clear();
				getDescription().addAll((Collection<? extends Description>)newValue);
				return;
			case JcaPackage.CONFIG_PROPERTY__CONFIG_PROPERTY_NAME:
				setConfigPropertyName((String)newValue);
				return;
			case JcaPackage.CONFIG_PROPERTY__CONFIG_PROPERTY_TYPE:
				setConfigPropertyType((ConfigPropertyTypeType)newValue);
				return;
			case JcaPackage.CONFIG_PROPERTY__CONFIG_PROPERTY_VALUE:
				setConfigPropertyValue((String)newValue);
				return;
			case JcaPackage.CONFIG_PROPERTY__CONFIG_PROPERTY_IGNORE:
				setConfigPropertyIgnore((Boolean)newValue);
				return;
			case JcaPackage.CONFIG_PROPERTY__CONFIG_PROPERTY_SUPPORTS_DYNAMIC_UPDATES:
				setConfigPropertySupportsDynamicUpdates((Boolean)newValue);
				return;
			case JcaPackage.CONFIG_PROPERTY__CONFIG_PROPERTY_CONFIDENTIAL:
				setConfigPropertyConfidential((Boolean)newValue);
				return;
			case JcaPackage.CONFIG_PROPERTY__ID:
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
			case JcaPackage.CONFIG_PROPERTY__DESCRIPTION:
				getDescription().clear();
				return;
			case JcaPackage.CONFIG_PROPERTY__CONFIG_PROPERTY_NAME:
				setConfigPropertyName(CONFIG_PROPERTY_NAME_EDEFAULT);
				return;
			case JcaPackage.CONFIG_PROPERTY__CONFIG_PROPERTY_TYPE:
				unsetConfigPropertyType();
				return;
			case JcaPackage.CONFIG_PROPERTY__CONFIG_PROPERTY_VALUE:
				setConfigPropertyValue(CONFIG_PROPERTY_VALUE_EDEFAULT);
				return;
			case JcaPackage.CONFIG_PROPERTY__CONFIG_PROPERTY_IGNORE:
				unsetConfigPropertyIgnore();
				return;
			case JcaPackage.CONFIG_PROPERTY__CONFIG_PROPERTY_SUPPORTS_DYNAMIC_UPDATES:
				unsetConfigPropertySupportsDynamicUpdates();
				return;
			case JcaPackage.CONFIG_PROPERTY__CONFIG_PROPERTY_CONFIDENTIAL:
				unsetConfigPropertyConfidential();
				return;
			case JcaPackage.CONFIG_PROPERTY__ID:
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
			case JcaPackage.CONFIG_PROPERTY__DESCRIPTION:
				return description != null && !description.isEmpty();
			case JcaPackage.CONFIG_PROPERTY__CONFIG_PROPERTY_NAME:
				return CONFIG_PROPERTY_NAME_EDEFAULT == null ? configPropertyName != null : !CONFIG_PROPERTY_NAME_EDEFAULT.equals(configPropertyName);
			case JcaPackage.CONFIG_PROPERTY__CONFIG_PROPERTY_TYPE:
				return isSetConfigPropertyType();
			case JcaPackage.CONFIG_PROPERTY__CONFIG_PROPERTY_VALUE:
				return CONFIG_PROPERTY_VALUE_EDEFAULT == null ? configPropertyValue != null : !CONFIG_PROPERTY_VALUE_EDEFAULT.equals(configPropertyValue);
			case JcaPackage.CONFIG_PROPERTY__CONFIG_PROPERTY_IGNORE:
				return isSetConfigPropertyIgnore();
			case JcaPackage.CONFIG_PROPERTY__CONFIG_PROPERTY_SUPPORTS_DYNAMIC_UPDATES:
				return isSetConfigPropertySupportsDynamicUpdates();
			case JcaPackage.CONFIG_PROPERTY__CONFIG_PROPERTY_CONFIDENTIAL:
				return isSetConfigPropertyConfidential();
			case JcaPackage.CONFIG_PROPERTY__ID:
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
		result.append(" (configPropertyName: "); //$NON-NLS-1$
		result.append(configPropertyName);
		result.append(", configPropertyType: "); //$NON-NLS-1$
		if (configPropertyTypeESet) result.append(configPropertyType); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", configPropertyValue: "); //$NON-NLS-1$
		result.append(configPropertyValue);
		result.append(", configPropertyIgnore: "); //$NON-NLS-1$
		if (configPropertyIgnoreESet) result.append(configPropertyIgnore); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", configPropertySupportsDynamicUpdates: "); //$NON-NLS-1$
		if (configPropertySupportsDynamicUpdatesESet) result.append(configPropertySupportsDynamicUpdates); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", configPropertyConfidential: "); //$NON-NLS-1$
		if (configPropertyConfidentialESet) result.append(configPropertyConfidential); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //ConfigPropertyImpl
