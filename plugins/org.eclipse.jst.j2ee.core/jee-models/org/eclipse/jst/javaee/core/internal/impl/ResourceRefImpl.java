/*******************************************************************************
 * Copyright (c) 2007, 2023 IBM Corporation and others.
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
import org.eclipse.jst.javaee.core.ResAuthType;
import org.eclipse.jst.javaee.core.ResSharingScopeType;
import org.eclipse.jst.javaee.core.ResourceRef;

import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ResourceRefImpl#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ResourceRefImpl#getResRefName <em>Res Ref Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ResourceRefImpl#getResType <em>Res Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ResourceRefImpl#getResAuth <em>Res Auth</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ResourceRefImpl#getResSharingScope <em>Res Sharing Scope</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ResourceRefImpl#getMappedName <em>Mapped Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ResourceRefImpl#getInjectionTargets <em>Injection Targets</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ResourceRefImpl#getLookupName <em>Lookup Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ResourceRefImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResourceRefImpl extends EObjectImpl implements ResourceRef {
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
	 * The default value of the '{@link #getResRefName() <em>Res Ref Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResRefName()
	 * @generated
	 * @ordered
	 */
	protected static final String RES_REF_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResRefName() <em>Res Ref Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResRefName()
	 * @generated
	 * @ordered
	 */
	protected String resRefName = RES_REF_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getResType() <em>Res Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResType()
	 * @generated
	 * @ordered
	 */
	protected static final String RES_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResType() <em>Res Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResType()
	 * @generated
	 * @ordered
	 */
	protected String resType = RES_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getResAuth() <em>Res Auth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResAuth()
	 * @generated
	 * @ordered
	 */
	protected static final ResAuthType RES_AUTH_EDEFAULT = ResAuthType.APPLICATION_LITERAL;

	/**
	 * The cached value of the '{@link #getResAuth() <em>Res Auth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResAuth()
	 * @generated
	 * @ordered
	 */
	protected ResAuthType resAuth = RES_AUTH_EDEFAULT;

	/**
	 * This is true if the Res Auth attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean resAuthESet;

	/**
	 * The default value of the '{@link #getResSharingScope() <em>Res Sharing Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResSharingScope()
	 * @generated
	 * @ordered
	 */
	protected static final ResSharingScopeType RES_SHARING_SCOPE_EDEFAULT = ResSharingScopeType.SHAREABLE_LITERAL;

	/**
	 * The cached value of the '{@link #getResSharingScope() <em>Res Sharing Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResSharingScope()
	 * @generated
	 * @ordered
	 */
	protected ResSharingScopeType resSharingScope = RES_SHARING_SCOPE_EDEFAULT;

	/**
	 * This is true if the Res Sharing Scope attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean resSharingScopeESet;

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
	protected ResourceRefImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaeePackage.Literals.RESOURCE_REF;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Description> getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList<Description>(Description.class, this, JavaeePackage.RESOURCE_REF__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getResRefName() {
		return resRefName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setResRefName(String newResRefName) {
		String oldResRefName = resRefName;
		resRefName = newResRefName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.RESOURCE_REF__RES_REF_NAME, oldResRefName, resRefName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getResType() {
		return resType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setResType(String newResType) {
		String oldResType = resType;
		resType = newResType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.RESOURCE_REF__RES_TYPE, oldResType, resType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResAuthType getResAuth() {
		return resAuth;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setResAuth(ResAuthType newResAuth) {
		ResAuthType oldResAuth = resAuth;
		resAuth = newResAuth == null ? RES_AUTH_EDEFAULT : newResAuth;
		boolean oldResAuthESet = resAuthESet;
		resAuthESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.RESOURCE_REF__RES_AUTH, oldResAuth, resAuth, !oldResAuthESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetResAuth() {
		ResAuthType oldResAuth = resAuth;
		boolean oldResAuthESet = resAuthESet;
		resAuth = RES_AUTH_EDEFAULT;
		resAuthESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JavaeePackage.RESOURCE_REF__RES_AUTH, oldResAuth, RES_AUTH_EDEFAULT, oldResAuthESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetResAuth() {
		return resAuthESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResSharingScopeType getResSharingScope() {
		return resSharingScope;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setResSharingScope(ResSharingScopeType newResSharingScope) {
		ResSharingScopeType oldResSharingScope = resSharingScope;
		resSharingScope = newResSharingScope == null ? RES_SHARING_SCOPE_EDEFAULT : newResSharingScope;
		boolean oldResSharingScopeESet = resSharingScopeESet;
		resSharingScopeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.RESOURCE_REF__RES_SHARING_SCOPE, oldResSharingScope, resSharingScope, !oldResSharingScopeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetResSharingScope() {
		ResSharingScopeType oldResSharingScope = resSharingScope;
		boolean oldResSharingScopeESet = resSharingScopeESet;
		resSharingScope = RES_SHARING_SCOPE_EDEFAULT;
		resSharingScopeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JavaeePackage.RESOURCE_REF__RES_SHARING_SCOPE, oldResSharingScope, RES_SHARING_SCOPE_EDEFAULT, oldResSharingScopeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetResSharingScope() {
		return resSharingScopeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMappedName() {
		return mappedName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMappedName(String newMappedName) {
		String oldMappedName = mappedName;
		mappedName = newMappedName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.RESOURCE_REF__MAPPED_NAME, oldMappedName, mappedName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<InjectionTarget> getInjectionTargets() {
		if (injectionTargets == null) {
			injectionTargets = new EObjectContainmentEList<InjectionTarget>(InjectionTarget.class, this, JavaeePackage.RESOURCE_REF__INJECTION_TARGETS);
		}
		return injectionTargets;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLookupName() {
		return lookupName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLookupName(String newLookupName) {
		String oldLookupName = lookupName;
		lookupName = newLookupName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.RESOURCE_REF__LOOKUP_NAME, oldLookupName, lookupName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.RESOURCE_REF__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JavaeePackage.RESOURCE_REF__DESCRIPTIONS:
				return ((InternalEList<?>)getDescriptions()).basicRemove(otherEnd, msgs);
			case JavaeePackage.RESOURCE_REF__INJECTION_TARGETS:
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
			case JavaeePackage.RESOURCE_REF__DESCRIPTIONS:
				return getDescriptions();
			case JavaeePackage.RESOURCE_REF__RES_REF_NAME:
				return getResRefName();
			case JavaeePackage.RESOURCE_REF__RES_TYPE:
				return getResType();
			case JavaeePackage.RESOURCE_REF__RES_AUTH:
				return getResAuth();
			case JavaeePackage.RESOURCE_REF__RES_SHARING_SCOPE:
				return getResSharingScope();
			case JavaeePackage.RESOURCE_REF__MAPPED_NAME:
				return getMappedName();
			case JavaeePackage.RESOURCE_REF__INJECTION_TARGETS:
				return getInjectionTargets();
			case JavaeePackage.RESOURCE_REF__LOOKUP_NAME:
				return getLookupName();
			case JavaeePackage.RESOURCE_REF__ID:
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
			case JavaeePackage.RESOURCE_REF__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection<? extends Description>)newValue);
				return;
			case JavaeePackage.RESOURCE_REF__RES_REF_NAME:
				setResRefName((String)newValue);
				return;
			case JavaeePackage.RESOURCE_REF__RES_TYPE:
				setResType((String)newValue);
				return;
			case JavaeePackage.RESOURCE_REF__RES_AUTH:
				setResAuth((ResAuthType)newValue);
				return;
			case JavaeePackage.RESOURCE_REF__RES_SHARING_SCOPE:
				setResSharingScope((ResSharingScopeType)newValue);
				return;
			case JavaeePackage.RESOURCE_REF__MAPPED_NAME:
				setMappedName((String)newValue);
				return;
			case JavaeePackage.RESOURCE_REF__INJECTION_TARGETS:
				getInjectionTargets().clear();
				getInjectionTargets().addAll((Collection<? extends InjectionTarget>)newValue);
				return;
			case JavaeePackage.RESOURCE_REF__LOOKUP_NAME:
				setLookupName((String)newValue);
				return;
			case JavaeePackage.RESOURCE_REF__ID:
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
			case JavaeePackage.RESOURCE_REF__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case JavaeePackage.RESOURCE_REF__RES_REF_NAME:
				setResRefName(RES_REF_NAME_EDEFAULT);
				return;
			case JavaeePackage.RESOURCE_REF__RES_TYPE:
				setResType(RES_TYPE_EDEFAULT);
				return;
			case JavaeePackage.RESOURCE_REF__RES_AUTH:
				unsetResAuth();
				return;
			case JavaeePackage.RESOURCE_REF__RES_SHARING_SCOPE:
				unsetResSharingScope();
				return;
			case JavaeePackage.RESOURCE_REF__MAPPED_NAME:
				setMappedName(MAPPED_NAME_EDEFAULT);
				return;
			case JavaeePackage.RESOURCE_REF__INJECTION_TARGETS:
				getInjectionTargets().clear();
				return;
			case JavaeePackage.RESOURCE_REF__LOOKUP_NAME:
				setLookupName(LOOKUP_NAME_EDEFAULT);
				return;
			case JavaeePackage.RESOURCE_REF__ID:
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
			case JavaeePackage.RESOURCE_REF__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case JavaeePackage.RESOURCE_REF__RES_REF_NAME:
				return RES_REF_NAME_EDEFAULT == null ? resRefName != null : !RES_REF_NAME_EDEFAULT.equals(resRefName);
			case JavaeePackage.RESOURCE_REF__RES_TYPE:
				return RES_TYPE_EDEFAULT == null ? resType != null : !RES_TYPE_EDEFAULT.equals(resType);
			case JavaeePackage.RESOURCE_REF__RES_AUTH:
				return isSetResAuth();
			case JavaeePackage.RESOURCE_REF__RES_SHARING_SCOPE:
				return isSetResSharingScope();
			case JavaeePackage.RESOURCE_REF__MAPPED_NAME:
				return MAPPED_NAME_EDEFAULT == null ? mappedName != null : !MAPPED_NAME_EDEFAULT.equals(mappedName);
			case JavaeePackage.RESOURCE_REF__INJECTION_TARGETS:
				return injectionTargets != null && !injectionTargets.isEmpty();
			case JavaeePackage.RESOURCE_REF__LOOKUP_NAME:
				return LOOKUP_NAME_EDEFAULT == null ? lookupName != null : !LOOKUP_NAME_EDEFAULT.equals(lookupName);
			case JavaeePackage.RESOURCE_REF__ID:
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
		result.append(" (resRefName: "); //$NON-NLS-1$
		result.append(resRefName);
		result.append(", resType: "); //$NON-NLS-1$
		result.append(resType);
		result.append(", resAuth: "); //$NON-NLS-1$
		if (resAuthESet) result.append(resAuth); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", resSharingScope: "); //$NON-NLS-1$
		if (resSharingScopeESet) result.append(resSharingScope); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", mappedName: "); //$NON-NLS-1$
		result.append(mappedName);
		result.append(", lookupName: "); //$NON-NLS-1$
		result.append(lookupName);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //ResourceRefImpl