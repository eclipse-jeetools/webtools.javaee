/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.core.internal.impl;

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
import org.eclipse.jst.javaee.core.InjectionTarget;
import org.eclipse.jst.javaee.core.ResourceEnvRef;

import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource Env Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ResourceEnvRefImpl#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ResourceEnvRefImpl#getResourceEnvRefName <em>Resource Env Ref Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ResourceEnvRefImpl#getResourceEnvRefType <em>Resource Env Ref Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ResourceEnvRefImpl#getMappedName <em>Mapped Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ResourceEnvRefImpl#getInjectionTargets <em>Injection Targets</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ResourceEnvRefImpl#getLookupName <em>Lookup Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ResourceEnvRefImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResourceEnvRefImpl extends EObjectImpl implements ResourceEnvRef {
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
	 * The default value of the '{@link #getResourceEnvRefName() <em>Resource Env Ref Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceEnvRefName()
	 * @generated
	 * @ordered
	 */
	protected static final String RESOURCE_ENV_REF_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResourceEnvRefName() <em>Resource Env Ref Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceEnvRefName()
	 * @generated
	 * @ordered
	 */
	protected String resourceEnvRefName = RESOURCE_ENV_REF_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getResourceEnvRefType() <em>Resource Env Ref Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceEnvRefType()
	 * @generated
	 * @ordered
	 */
	protected static final String RESOURCE_ENV_REF_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResourceEnvRefType() <em>Resource Env Ref Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceEnvRefType()
	 * @generated
	 * @ordered
	 */
	protected String resourceEnvRefType = RESOURCE_ENV_REF_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMappedName() <em>Mapped Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMappedName()
	 * @generated
	 * @ordered
	 */
	protected static final String MAPPED_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMappedName() <em>Mapped Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMappedName()
	 * @generated
	 * @ordered
	 */
	protected String mappedName = MAPPED_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getInjectionTargets() <em>Injection Targets</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInjectionTargets()
	 * @generated
	 * @ordered
	 */
	protected EList<InjectionTarget> injectionTargets;

	/**
	 * The default value of the '{@link #getLookupName() <em>Lookup Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLookupName()
	 * @generated
	 * @ordered
	 */
	protected static final String LOOKUP_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLookupName() <em>Lookup Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLookupName()
	 * @generated
	 * @ordered
	 */
	protected String lookupName = LOOKUP_NAME_EDEFAULT;

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
	protected ResourceEnvRefImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaeePackage.Literals.RESOURCE_ENV_REF;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Description> getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList<Description>(Description.class, this, JavaeePackage.RESOURCE_ENV_REF__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getResourceEnvRefName() {
		return resourceEnvRefName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResourceEnvRefName(String newResourceEnvRefName) {
		String oldResourceEnvRefName = resourceEnvRefName;
		resourceEnvRefName = newResourceEnvRefName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.RESOURCE_ENV_REF__RESOURCE_ENV_REF_NAME, oldResourceEnvRefName, resourceEnvRefName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getResourceEnvRefType() {
		return resourceEnvRefType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResourceEnvRefType(String newResourceEnvRefType) {
		String oldResourceEnvRefType = resourceEnvRefType;
		resourceEnvRefType = newResourceEnvRefType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.RESOURCE_ENV_REF__RESOURCE_ENV_REF_TYPE, oldResourceEnvRefType, resourceEnvRefType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMappedName() {
		return mappedName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMappedName(String newMappedName) {
		String oldMappedName = mappedName;
		mappedName = newMappedName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.RESOURCE_ENV_REF__MAPPED_NAME, oldMappedName, mappedName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<InjectionTarget> getInjectionTargets() {
		if (injectionTargets == null) {
			injectionTargets = new EObjectContainmentEList<InjectionTarget>(InjectionTarget.class, this, JavaeePackage.RESOURCE_ENV_REF__INJECTION_TARGETS);
		}
		return injectionTargets;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLookupName() {
		return lookupName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLookupName(String newLookupName) {
		String oldLookupName = lookupName;
		lookupName = newLookupName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.RESOURCE_ENV_REF__LOOKUP_NAME, oldLookupName, lookupName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.RESOURCE_ENV_REF__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JavaeePackage.RESOURCE_ENV_REF__DESCRIPTIONS:
				return ((InternalEList<?>)getDescriptions()).basicRemove(otherEnd, msgs);
			case JavaeePackage.RESOURCE_ENV_REF__INJECTION_TARGETS:
				return ((InternalEList<?>)getInjectionTargets()).basicRemove(otherEnd, msgs);
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
			case JavaeePackage.RESOURCE_ENV_REF__DESCRIPTIONS:
				return getDescriptions();
			case JavaeePackage.RESOURCE_ENV_REF__RESOURCE_ENV_REF_NAME:
				return getResourceEnvRefName();
			case JavaeePackage.RESOURCE_ENV_REF__RESOURCE_ENV_REF_TYPE:
				return getResourceEnvRefType();
			case JavaeePackage.RESOURCE_ENV_REF__MAPPED_NAME:
				return getMappedName();
			case JavaeePackage.RESOURCE_ENV_REF__INJECTION_TARGETS:
				return getInjectionTargets();
			case JavaeePackage.RESOURCE_ENV_REF__LOOKUP_NAME:
				return getLookupName();
			case JavaeePackage.RESOURCE_ENV_REF__ID:
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
			case JavaeePackage.RESOURCE_ENV_REF__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection<? extends Description>)newValue);
				return;
			case JavaeePackage.RESOURCE_ENV_REF__RESOURCE_ENV_REF_NAME:
				setResourceEnvRefName((String)newValue);
				return;
			case JavaeePackage.RESOURCE_ENV_REF__RESOURCE_ENV_REF_TYPE:
				setResourceEnvRefType((String)newValue);
				return;
			case JavaeePackage.RESOURCE_ENV_REF__MAPPED_NAME:
				setMappedName((String)newValue);
				return;
			case JavaeePackage.RESOURCE_ENV_REF__INJECTION_TARGETS:
				getInjectionTargets().clear();
				getInjectionTargets().addAll((Collection<? extends InjectionTarget>)newValue);
				return;
			case JavaeePackage.RESOURCE_ENV_REF__LOOKUP_NAME:
				setLookupName((String)newValue);
				return;
			case JavaeePackage.RESOURCE_ENV_REF__ID:
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
			case JavaeePackage.RESOURCE_ENV_REF__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case JavaeePackage.RESOURCE_ENV_REF__RESOURCE_ENV_REF_NAME:
				setResourceEnvRefName(RESOURCE_ENV_REF_NAME_EDEFAULT);
				return;
			case JavaeePackage.RESOURCE_ENV_REF__RESOURCE_ENV_REF_TYPE:
				setResourceEnvRefType(RESOURCE_ENV_REF_TYPE_EDEFAULT);
				return;
			case JavaeePackage.RESOURCE_ENV_REF__MAPPED_NAME:
				setMappedName(MAPPED_NAME_EDEFAULT);
				return;
			case JavaeePackage.RESOURCE_ENV_REF__INJECTION_TARGETS:
				getInjectionTargets().clear();
				return;
			case JavaeePackage.RESOURCE_ENV_REF__LOOKUP_NAME:
				setLookupName(LOOKUP_NAME_EDEFAULT);
				return;
			case JavaeePackage.RESOURCE_ENV_REF__ID:
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
			case JavaeePackage.RESOURCE_ENV_REF__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case JavaeePackage.RESOURCE_ENV_REF__RESOURCE_ENV_REF_NAME:
				return RESOURCE_ENV_REF_NAME_EDEFAULT == null ? resourceEnvRefName != null : !RESOURCE_ENV_REF_NAME_EDEFAULT.equals(resourceEnvRefName);
			case JavaeePackage.RESOURCE_ENV_REF__RESOURCE_ENV_REF_TYPE:
				return RESOURCE_ENV_REF_TYPE_EDEFAULT == null ? resourceEnvRefType != null : !RESOURCE_ENV_REF_TYPE_EDEFAULT.equals(resourceEnvRefType);
			case JavaeePackage.RESOURCE_ENV_REF__MAPPED_NAME:
				return MAPPED_NAME_EDEFAULT == null ? mappedName != null : !MAPPED_NAME_EDEFAULT.equals(mappedName);
			case JavaeePackage.RESOURCE_ENV_REF__INJECTION_TARGETS:
				return injectionTargets != null && !injectionTargets.isEmpty();
			case JavaeePackage.RESOURCE_ENV_REF__LOOKUP_NAME:
				return LOOKUP_NAME_EDEFAULT == null ? lookupName != null : !LOOKUP_NAME_EDEFAULT.equals(lookupName);
			case JavaeePackage.RESOURCE_ENV_REF__ID:
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
		result.append(" (resourceEnvRefName: "); //$NON-NLS-1$
		result.append(resourceEnvRefName);
		result.append(", resourceEnvRefType: "); //$NON-NLS-1$
		result.append(resourceEnvRefType);
		result.append(", mappedName: "); //$NON-NLS-1$
		result.append(mappedName);
		result.append(", lookupName: "); //$NON-NLS-1$
		result.append(lookupName);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //ResourceEnvRefImpl