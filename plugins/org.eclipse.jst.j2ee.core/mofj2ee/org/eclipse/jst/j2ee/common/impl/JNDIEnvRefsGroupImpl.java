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
package org.eclipse.jst.j2ee.common.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.EJBLocalRef;
import org.eclipse.jst.j2ee.common.EjbRef;
import org.eclipse.jst.j2ee.common.EnvEntry;
import org.eclipse.jst.j2ee.common.JNDIEnvRefsGroup;
import org.eclipse.jst.j2ee.common.MessageDestinationRef;
import org.eclipse.jst.j2ee.common.ResourceEnvRef;
import org.eclipse.jst.j2ee.common.ResourceRef;
import org.eclipse.jst.j2ee.webservice.wsclient.ServiceRef;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>JNDI Env Refs Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.internal.common.impl.JNDIEnvRefsGroupImpl#getEnvironmentProperties <em>Environment Properties</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.common.impl.JNDIEnvRefsGroupImpl#getResourceRefs <em>Resource Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.common.impl.JNDIEnvRefsGroupImpl#getEjbRefs <em>Ejb Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.common.impl.JNDIEnvRefsGroupImpl#getResourceEnvRefs <em>Resource Env Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.common.impl.JNDIEnvRefsGroupImpl#getEjbLocalRefs <em>Ejb Local Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.common.impl.JNDIEnvRefsGroupImpl#getMessageDestinationRefs <em>Message Destination Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.common.impl.JNDIEnvRefsGroupImpl#getServiceRefs <em>Service Refs</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class JNDIEnvRefsGroupImpl extends CompatibilityDescriptionGroupImpl implements JNDIEnvRefsGroup {
	/**
	 * The cached value of the '{@link #getEnvironmentProperties() <em>Environment Properties</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnvironmentProperties()
	 * @generated
	 * @ordered
	 */
	protected EList environmentProperties = null;

	/**
	 * The cached value of the '{@link #getResourceRefs() <em>Resource Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceRefs()
	 * @generated
	 * @ordered
	 */
	protected EList resourceRefs = null;

	/**
	 * The cached value of the '{@link #getEjbRefs() <em>Ejb Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbRefs()
	 * @generated
	 * @ordered
	 */
	protected EList ejbRefs = null;

	/**
	 * The cached value of the '{@link #getResourceEnvRefs() <em>Resource Env Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceEnvRefs()
	 * @generated
	 * @ordered
	 */
	protected EList resourceEnvRefs = null;

	/**
	 * The cached value of the '{@link #getEjbLocalRefs() <em>Ejb Local Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbLocalRefs()
	 * @generated
	 * @ordered
	 */
	protected EList ejbLocalRefs = null;

	/**
	 * The cached value of the '{@link #getMessageDestinationRefs() <em>Message Destination Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageDestinationRefs()
	 * @generated
	 * @ordered
	 */
	protected EList messageDestinationRefs = null;

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
	protected JNDIEnvRefsGroupImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return CommonPackage.eINSTANCE.getJNDIEnvRefsGroup();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getEnvironmentProperties() {
		if (environmentProperties == null) {
			environmentProperties = new EObjectContainmentEList(EnvEntry.class, this, CommonPackage.JNDI_ENV_REFS_GROUP__ENVIRONMENT_PROPERTIES);
		}
		return environmentProperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getResourceRefs() {
		if (resourceRefs == null) {
			resourceRefs = new EObjectContainmentEList(ResourceRef.class, this, CommonPackage.JNDI_ENV_REFS_GROUP__RESOURCE_REFS);
		}
		return resourceRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getEjbRefs() {
		if (ejbRefs == null) {
			ejbRefs = new EObjectContainmentEList(EjbRef.class, this, CommonPackage.JNDI_ENV_REFS_GROUP__EJB_REFS);
		}
		return ejbRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getResourceEnvRefs() {
		if (resourceEnvRefs == null) {
			resourceEnvRefs = new EObjectContainmentEList(ResourceEnvRef.class, this, CommonPackage.JNDI_ENV_REFS_GROUP__RESOURCE_ENV_REFS);
		}
		return resourceEnvRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getEjbLocalRefs() {
		if (ejbLocalRefs == null) {
			ejbLocalRefs = new EObjectContainmentEList(EJBLocalRef.class, this, CommonPackage.JNDI_ENV_REFS_GROUP__EJB_LOCAL_REFS);
		}
		return ejbLocalRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getMessageDestinationRefs() {
		if (messageDestinationRefs == null) {
			messageDestinationRefs = new EObjectContainmentEList(MessageDestinationRef.class, this, CommonPackage.JNDI_ENV_REFS_GROUP__MESSAGE_DESTINATION_REFS);
		}
		return messageDestinationRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getServiceRefs() {
		if (serviceRefs == null) {
			serviceRefs = new EObjectContainmentEList(ServiceRef.class, this, CommonPackage.JNDI_ENV_REFS_GROUP__SERVICE_REFS);
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
				case CommonPackage.JNDI_ENV_REFS_GROUP__ICONS:
					return ((InternalEList)getIcons()).basicRemove(otherEnd, msgs);
				case CommonPackage.JNDI_ENV_REFS_GROUP__DISPLAY_NAMES:
					return ((InternalEList)getDisplayNames()).basicRemove(otherEnd, msgs);
				case CommonPackage.JNDI_ENV_REFS_GROUP__DESCRIPTIONS:
					return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
				case CommonPackage.JNDI_ENV_REFS_GROUP__ENVIRONMENT_PROPERTIES:
					return ((InternalEList)getEnvironmentProperties()).basicRemove(otherEnd, msgs);
				case CommonPackage.JNDI_ENV_REFS_GROUP__RESOURCE_REFS:
					return ((InternalEList)getResourceRefs()).basicRemove(otherEnd, msgs);
				case CommonPackage.JNDI_ENV_REFS_GROUP__EJB_REFS:
					return ((InternalEList)getEjbRefs()).basicRemove(otherEnd, msgs);
				case CommonPackage.JNDI_ENV_REFS_GROUP__RESOURCE_ENV_REFS:
					return ((InternalEList)getResourceEnvRefs()).basicRemove(otherEnd, msgs);
				case CommonPackage.JNDI_ENV_REFS_GROUP__EJB_LOCAL_REFS:
					return ((InternalEList)getEjbLocalRefs()).basicRemove(otherEnd, msgs);
				case CommonPackage.JNDI_ENV_REFS_GROUP__MESSAGE_DESTINATION_REFS:
					return ((InternalEList)getMessageDestinationRefs()).basicRemove(otherEnd, msgs);
				case CommonPackage.JNDI_ENV_REFS_GROUP__SERVICE_REFS:
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
			case CommonPackage.JNDI_ENV_REFS_GROUP__ICONS:
				return getIcons();
			case CommonPackage.JNDI_ENV_REFS_GROUP__DISPLAY_NAMES:
				return getDisplayNames();
			case CommonPackage.JNDI_ENV_REFS_GROUP__DESCRIPTIONS:
				return getDescriptions();
			case CommonPackage.JNDI_ENV_REFS_GROUP__SMALL_ICON:
				return getSmallIcon();
			case CommonPackage.JNDI_ENV_REFS_GROUP__LARGE_ICON:
				return getLargeIcon();
			case CommonPackage.JNDI_ENV_REFS_GROUP__DESCRIPTION:
				return getDescription();
			case CommonPackage.JNDI_ENV_REFS_GROUP__DISPLAY_NAME:
				return getDisplayName();
			case CommonPackage.JNDI_ENV_REFS_GROUP__ENVIRONMENT_PROPERTIES:
				return getEnvironmentProperties();
			case CommonPackage.JNDI_ENV_REFS_GROUP__RESOURCE_REFS:
				return getResourceRefs();
			case CommonPackage.JNDI_ENV_REFS_GROUP__EJB_REFS:
				return getEjbRefs();
			case CommonPackage.JNDI_ENV_REFS_GROUP__RESOURCE_ENV_REFS:
				return getResourceEnvRefs();
			case CommonPackage.JNDI_ENV_REFS_GROUP__EJB_LOCAL_REFS:
				return getEjbLocalRefs();
			case CommonPackage.JNDI_ENV_REFS_GROUP__MESSAGE_DESTINATION_REFS:
				return getMessageDestinationRefs();
			case CommonPackage.JNDI_ENV_REFS_GROUP__SERVICE_REFS:
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
			case CommonPackage.JNDI_ENV_REFS_GROUP__ICONS:
				getIcons().clear();
				getIcons().addAll((Collection)newValue);
				return;
			case CommonPackage.JNDI_ENV_REFS_GROUP__DISPLAY_NAMES:
				getDisplayNames().clear();
				getDisplayNames().addAll((Collection)newValue);
				return;
			case CommonPackage.JNDI_ENV_REFS_GROUP__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
				return;
			case CommonPackage.JNDI_ENV_REFS_GROUP__SMALL_ICON:
				setSmallIcon((String)newValue);
				return;
			case CommonPackage.JNDI_ENV_REFS_GROUP__LARGE_ICON:
				setLargeIcon((String)newValue);
				return;
			case CommonPackage.JNDI_ENV_REFS_GROUP__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case CommonPackage.JNDI_ENV_REFS_GROUP__DISPLAY_NAME:
				setDisplayName((String)newValue);
				return;
			case CommonPackage.JNDI_ENV_REFS_GROUP__ENVIRONMENT_PROPERTIES:
				getEnvironmentProperties().clear();
				getEnvironmentProperties().addAll((Collection)newValue);
				return;
			case CommonPackage.JNDI_ENV_REFS_GROUP__RESOURCE_REFS:
				getResourceRefs().clear();
				getResourceRefs().addAll((Collection)newValue);
				return;
			case CommonPackage.JNDI_ENV_REFS_GROUP__EJB_REFS:
				getEjbRefs().clear();
				getEjbRefs().addAll((Collection)newValue);
				return;
			case CommonPackage.JNDI_ENV_REFS_GROUP__RESOURCE_ENV_REFS:
				getResourceEnvRefs().clear();
				getResourceEnvRefs().addAll((Collection)newValue);
				return;
			case CommonPackage.JNDI_ENV_REFS_GROUP__EJB_LOCAL_REFS:
				getEjbLocalRefs().clear();
				getEjbLocalRefs().addAll((Collection)newValue);
				return;
			case CommonPackage.JNDI_ENV_REFS_GROUP__MESSAGE_DESTINATION_REFS:
				getMessageDestinationRefs().clear();
				getMessageDestinationRefs().addAll((Collection)newValue);
				return;
			case CommonPackage.JNDI_ENV_REFS_GROUP__SERVICE_REFS:
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
			case CommonPackage.JNDI_ENV_REFS_GROUP__ICONS:
				getIcons().clear();
				return;
			case CommonPackage.JNDI_ENV_REFS_GROUP__DISPLAY_NAMES:
				getDisplayNames().clear();
				return;
			case CommonPackage.JNDI_ENV_REFS_GROUP__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case CommonPackage.JNDI_ENV_REFS_GROUP__SMALL_ICON:
				setSmallIcon(SMALL_ICON_EDEFAULT);
				return;
			case CommonPackage.JNDI_ENV_REFS_GROUP__LARGE_ICON:
				setLargeIcon(LARGE_ICON_EDEFAULT);
				return;
			case CommonPackage.JNDI_ENV_REFS_GROUP__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case CommonPackage.JNDI_ENV_REFS_GROUP__DISPLAY_NAME:
				setDisplayName(DISPLAY_NAME_EDEFAULT);
				return;
			case CommonPackage.JNDI_ENV_REFS_GROUP__ENVIRONMENT_PROPERTIES:
				getEnvironmentProperties().clear();
				return;
			case CommonPackage.JNDI_ENV_REFS_GROUP__RESOURCE_REFS:
				getResourceRefs().clear();
				return;
			case CommonPackage.JNDI_ENV_REFS_GROUP__EJB_REFS:
				getEjbRefs().clear();
				return;
			case CommonPackage.JNDI_ENV_REFS_GROUP__RESOURCE_ENV_REFS:
				getResourceEnvRefs().clear();
				return;
			case CommonPackage.JNDI_ENV_REFS_GROUP__EJB_LOCAL_REFS:
				getEjbLocalRefs().clear();
				return;
			case CommonPackage.JNDI_ENV_REFS_GROUP__MESSAGE_DESTINATION_REFS:
				getMessageDestinationRefs().clear();
				return;
			case CommonPackage.JNDI_ENV_REFS_GROUP__SERVICE_REFS:
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
			case CommonPackage.JNDI_ENV_REFS_GROUP__ICONS:
				return icons != null && !icons.isEmpty();
			case CommonPackage.JNDI_ENV_REFS_GROUP__DISPLAY_NAMES:
				return displayNames != null && !displayNames.isEmpty();
			case CommonPackage.JNDI_ENV_REFS_GROUP__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case CommonPackage.JNDI_ENV_REFS_GROUP__SMALL_ICON:
				return SMALL_ICON_EDEFAULT == null ? smallIcon != null : !SMALL_ICON_EDEFAULT.equals(smallIcon);
			case CommonPackage.JNDI_ENV_REFS_GROUP__LARGE_ICON:
				return LARGE_ICON_EDEFAULT == null ? largeIcon != null : !LARGE_ICON_EDEFAULT.equals(largeIcon);
			case CommonPackage.JNDI_ENV_REFS_GROUP__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case CommonPackage.JNDI_ENV_REFS_GROUP__DISPLAY_NAME:
				return DISPLAY_NAME_EDEFAULT == null ? displayName != null : !DISPLAY_NAME_EDEFAULT.equals(displayName);
			case CommonPackage.JNDI_ENV_REFS_GROUP__ENVIRONMENT_PROPERTIES:
				return environmentProperties != null && !environmentProperties.isEmpty();
			case CommonPackage.JNDI_ENV_REFS_GROUP__RESOURCE_REFS:
				return resourceRefs != null && !resourceRefs.isEmpty();
			case CommonPackage.JNDI_ENV_REFS_GROUP__EJB_REFS:
				return ejbRefs != null && !ejbRefs.isEmpty();
			case CommonPackage.JNDI_ENV_REFS_GROUP__RESOURCE_ENV_REFS:
				return resourceEnvRefs != null && !resourceEnvRefs.isEmpty();
			case CommonPackage.JNDI_ENV_REFS_GROUP__EJB_LOCAL_REFS:
				return ejbLocalRefs != null && !ejbLocalRefs.isEmpty();
			case CommonPackage.JNDI_ENV_REFS_GROUP__MESSAGE_DESTINATION_REFS:
				return messageDestinationRefs != null && !messageDestinationRefs.isEmpty();
			case CommonPackage.JNDI_ENV_REFS_GROUP__SERVICE_REFS:
				return serviceRefs != null && !serviceRefs.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //JNDIEnvRefsGroupImpl
