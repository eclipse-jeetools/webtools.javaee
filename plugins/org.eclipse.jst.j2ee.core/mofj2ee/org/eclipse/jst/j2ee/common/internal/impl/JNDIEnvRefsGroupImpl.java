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
package org.eclipse.jst.j2ee.common.internal.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
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
 *   <li>{@link org.eclipse.jst.j2ee.common.internal.impl.JNDIEnvRefsGroupImpl#getEnvironmentProperties <em>Environment Properties</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.common.internal.impl.JNDIEnvRefsGroupImpl#getResourceRefs <em>Resource Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.common.internal.impl.JNDIEnvRefsGroupImpl#getEjbRefs <em>Ejb Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.common.internal.impl.JNDIEnvRefsGroupImpl#getResourceEnvRefs <em>Resource Env Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.common.internal.impl.JNDIEnvRefsGroupImpl#getEjbLocalRefs <em>Ejb Local Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.common.internal.impl.JNDIEnvRefsGroupImpl#getMessageDestinationRefs <em>Message Destination Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.common.internal.impl.JNDIEnvRefsGroupImpl#getServiceRefs <em>Service Refs</em>}</li>
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
	@Override
	protected EClass eStaticClass() {
		return CommonPackage.Literals.JNDI_ENV_REFS_GROUP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
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
		return super.eIsSet(featureID);
	}

} //JNDIEnvRefsGroupImpl
