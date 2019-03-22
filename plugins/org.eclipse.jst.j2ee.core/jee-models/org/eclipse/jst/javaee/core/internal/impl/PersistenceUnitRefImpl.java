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
import org.eclipse.jst.javaee.core.PersistenceUnitRef;

import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Persistence Unit Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.PersistenceUnitRefImpl#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.PersistenceUnitRefImpl#getPersistenceUnitRefName <em>Persistence Unit Ref Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.PersistenceUnitRefImpl#getPersistenceUnitName <em>Persistence Unit Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.PersistenceUnitRefImpl#getMappedName <em>Mapped Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.PersistenceUnitRefImpl#getInjectionTargets <em>Injection Targets</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.PersistenceUnitRefImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PersistenceUnitRefImpl extends EObjectImpl implements PersistenceUnitRef {
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
	 * The default value of the '{@link #getPersistenceUnitRefName() <em>Persistence Unit Ref Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPersistenceUnitRefName()
	 * @generated
	 * @ordered
	 */
	protected static final String PERSISTENCE_UNIT_REF_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPersistenceUnitRefName() <em>Persistence Unit Ref Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPersistenceUnitRefName()
	 * @generated
	 * @ordered
	 */
	protected String persistenceUnitRefName = PERSISTENCE_UNIT_REF_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getPersistenceUnitName() <em>Persistence Unit Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPersistenceUnitName()
	 * @generated
	 * @ordered
	 */
	protected static final String PERSISTENCE_UNIT_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPersistenceUnitName() <em>Persistence Unit Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPersistenceUnitName()
	 * @generated
	 * @ordered
	 */
	protected String persistenceUnitName = PERSISTENCE_UNIT_NAME_EDEFAULT;

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
	protected PersistenceUnitRefImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaeePackage.Literals.PERSISTENCE_UNIT_REF;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Description> getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList<Description>(Description.class, this, JavaeePackage.PERSISTENCE_UNIT_REF__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPersistenceUnitRefName() {
		return persistenceUnitRefName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPersistenceUnitRefName(String newPersistenceUnitRefName) {
		String oldPersistenceUnitRefName = persistenceUnitRefName;
		persistenceUnitRefName = newPersistenceUnitRefName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.PERSISTENCE_UNIT_REF__PERSISTENCE_UNIT_REF_NAME, oldPersistenceUnitRefName, persistenceUnitRefName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPersistenceUnitName() {
		return persistenceUnitName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPersistenceUnitName(String newPersistenceUnitName) {
		String oldPersistenceUnitName = persistenceUnitName;
		persistenceUnitName = newPersistenceUnitName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.PERSISTENCE_UNIT_REF__PERSISTENCE_UNIT_NAME, oldPersistenceUnitName, persistenceUnitName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.PERSISTENCE_UNIT_REF__MAPPED_NAME, oldMappedName, mappedName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<InjectionTarget> getInjectionTargets() {
		if (injectionTargets == null) {
			injectionTargets = new EObjectContainmentEList<InjectionTarget>(InjectionTarget.class, this, JavaeePackage.PERSISTENCE_UNIT_REF__INJECTION_TARGETS);
		}
		return injectionTargets;
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
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.PERSISTENCE_UNIT_REF__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JavaeePackage.PERSISTENCE_UNIT_REF__DESCRIPTIONS:
				return ((InternalEList<?>)getDescriptions()).basicRemove(otherEnd, msgs);
			case JavaeePackage.PERSISTENCE_UNIT_REF__INJECTION_TARGETS:
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
			case JavaeePackage.PERSISTENCE_UNIT_REF__DESCRIPTIONS:
				return getDescriptions();
			case JavaeePackage.PERSISTENCE_UNIT_REF__PERSISTENCE_UNIT_REF_NAME:
				return getPersistenceUnitRefName();
			case JavaeePackage.PERSISTENCE_UNIT_REF__PERSISTENCE_UNIT_NAME:
				return getPersistenceUnitName();
			case JavaeePackage.PERSISTENCE_UNIT_REF__MAPPED_NAME:
				return getMappedName();
			case JavaeePackage.PERSISTENCE_UNIT_REF__INJECTION_TARGETS:
				return getInjectionTargets();
			case JavaeePackage.PERSISTENCE_UNIT_REF__ID:
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
			case JavaeePackage.PERSISTENCE_UNIT_REF__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection<? extends Description>)newValue);
				return;
			case JavaeePackage.PERSISTENCE_UNIT_REF__PERSISTENCE_UNIT_REF_NAME:
				setPersistenceUnitRefName((String)newValue);
				return;
			case JavaeePackage.PERSISTENCE_UNIT_REF__PERSISTENCE_UNIT_NAME:
				setPersistenceUnitName((String)newValue);
				return;
			case JavaeePackage.PERSISTENCE_UNIT_REF__MAPPED_NAME:
				setMappedName((String)newValue);
				return;
			case JavaeePackage.PERSISTENCE_UNIT_REF__INJECTION_TARGETS:
				getInjectionTargets().clear();
				getInjectionTargets().addAll((Collection<? extends InjectionTarget>)newValue);
				return;
			case JavaeePackage.PERSISTENCE_UNIT_REF__ID:
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
			case JavaeePackage.PERSISTENCE_UNIT_REF__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case JavaeePackage.PERSISTENCE_UNIT_REF__PERSISTENCE_UNIT_REF_NAME:
				setPersistenceUnitRefName(PERSISTENCE_UNIT_REF_NAME_EDEFAULT);
				return;
			case JavaeePackage.PERSISTENCE_UNIT_REF__PERSISTENCE_UNIT_NAME:
				setPersistenceUnitName(PERSISTENCE_UNIT_NAME_EDEFAULT);
				return;
			case JavaeePackage.PERSISTENCE_UNIT_REF__MAPPED_NAME:
				setMappedName(MAPPED_NAME_EDEFAULT);
				return;
			case JavaeePackage.PERSISTENCE_UNIT_REF__INJECTION_TARGETS:
				getInjectionTargets().clear();
				return;
			case JavaeePackage.PERSISTENCE_UNIT_REF__ID:
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
			case JavaeePackage.PERSISTENCE_UNIT_REF__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case JavaeePackage.PERSISTENCE_UNIT_REF__PERSISTENCE_UNIT_REF_NAME:
				return PERSISTENCE_UNIT_REF_NAME_EDEFAULT == null ? persistenceUnitRefName != null : !PERSISTENCE_UNIT_REF_NAME_EDEFAULT.equals(persistenceUnitRefName);
			case JavaeePackage.PERSISTENCE_UNIT_REF__PERSISTENCE_UNIT_NAME:
				return PERSISTENCE_UNIT_NAME_EDEFAULT == null ? persistenceUnitName != null : !PERSISTENCE_UNIT_NAME_EDEFAULT.equals(persistenceUnitName);
			case JavaeePackage.PERSISTENCE_UNIT_REF__MAPPED_NAME:
				return MAPPED_NAME_EDEFAULT == null ? mappedName != null : !MAPPED_NAME_EDEFAULT.equals(mappedName);
			case JavaeePackage.PERSISTENCE_UNIT_REF__INJECTION_TARGETS:
				return injectionTargets != null && !injectionTargets.isEmpty();
			case JavaeePackage.PERSISTENCE_UNIT_REF__ID:
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
		result.append(" (persistenceUnitRefName: "); //$NON-NLS-1$
		result.append(persistenceUnitRefName);
		result.append(", persistenceUnitName: "); //$NON-NLS-1$
		result.append(persistenceUnitName);
		result.append(", mappedName: "); //$NON-NLS-1$
		result.append(mappedName);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //PersistenceUnitRefImpl