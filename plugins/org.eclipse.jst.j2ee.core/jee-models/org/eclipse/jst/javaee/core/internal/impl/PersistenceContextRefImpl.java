/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.eclipse.jst.javaee.core.PersistenceContextRef;
import org.eclipse.jst.javaee.core.PersistenceContextSynchronizationType;
import org.eclipse.jst.javaee.core.PersistenceContextType;
import org.eclipse.jst.javaee.core.PropertyType;

import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Persistence Context Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.PersistenceContextRefImpl#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.PersistenceContextRefImpl#getPersistenceContextRefName <em>Persistence Context Ref Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.PersistenceContextRefImpl#getPersistenceUnitName <em>Persistence Unit Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.PersistenceContextRefImpl#getPersistenceContextType <em>Persistence Context Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.PersistenceContextRefImpl#getPersistenceContextSynchronization <em>Persistence Context Synchronization</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.PersistenceContextRefImpl#getPersistenceProperties <em>Persistence Properties</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.PersistenceContextRefImpl#getMappedName <em>Mapped Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.PersistenceContextRefImpl#getInjectionTargets <em>Injection Targets</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.PersistenceContextRefImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PersistenceContextRefImpl extends EObjectImpl implements PersistenceContextRef {
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
	 * The default value of the '{@link #getPersistenceContextRefName() <em>Persistence Context Ref Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPersistenceContextRefName()
	 * @generated
	 * @ordered
	 */
	protected static final String PERSISTENCE_CONTEXT_REF_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPersistenceContextRefName() <em>Persistence Context Ref Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPersistenceContextRefName()
	 * @generated
	 * @ordered
	 */
	protected String persistenceContextRefName = PERSISTENCE_CONTEXT_REF_NAME_EDEFAULT;

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
	 * The default value of the '{@link #getPersistenceContextType() <em>Persistence Context Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPersistenceContextType()
	 * @generated
	 * @ordered
	 */
	protected static final PersistenceContextType PERSISTENCE_CONTEXT_TYPE_EDEFAULT = PersistenceContextType.TRANSACTION_LITERAL;

	/**
	 * The cached value of the '{@link #getPersistenceContextType() <em>Persistence Context Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPersistenceContextType()
	 * @generated
	 * @ordered
	 */
	protected PersistenceContextType persistenceContextType = PERSISTENCE_CONTEXT_TYPE_EDEFAULT;

	/**
	 * This is true if the Persistence Context Type attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean persistenceContextTypeESet;

	/**
	 * The default value of the '{@link #getPersistenceContextSynchronization() <em>Persistence Context Synchronization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPersistenceContextSynchronization()
	 * @generated
	 * @ordered
	 */
	protected static final PersistenceContextSynchronizationType PERSISTENCE_CONTEXT_SYNCHRONIZATION_EDEFAULT = PersistenceContextSynchronizationType.SYNCHRONIZED;

	/**
	 * The cached value of the '{@link #getPersistenceContextSynchronization() <em>Persistence Context Synchronization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPersistenceContextSynchronization()
	 * @generated
	 * @ordered
	 */
	protected PersistenceContextSynchronizationType persistenceContextSynchronization = PERSISTENCE_CONTEXT_SYNCHRONIZATION_EDEFAULT;

	/**
	 * This is true if the Persistence Context Synchronization attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean persistenceContextSynchronizationESet;

	/**
	 * The cached value of the '{@link #getPersistenceProperties() <em>Persistence Properties</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPersistenceProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<PropertyType> persistenceProperties;

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
	protected PersistenceContextRefImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaeePackage.Literals.PERSISTENCE_CONTEXT_REF;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Description> getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList<Description>(Description.class, this, JavaeePackage.PERSISTENCE_CONTEXT_REF__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPersistenceContextRefName() {
		return persistenceContextRefName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPersistenceContextRefName(String newPersistenceContextRefName) {
		String oldPersistenceContextRefName = persistenceContextRefName;
		persistenceContextRefName = newPersistenceContextRefName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.PERSISTENCE_CONTEXT_REF__PERSISTENCE_CONTEXT_REF_NAME, oldPersistenceContextRefName, persistenceContextRefName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.PERSISTENCE_CONTEXT_REF__PERSISTENCE_UNIT_NAME, oldPersistenceUnitName, persistenceUnitName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PersistenceContextType getPersistenceContextType() {
		return persistenceContextType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPersistenceContextType(PersistenceContextType newPersistenceContextType) {
		PersistenceContextType oldPersistenceContextType = persistenceContextType;
		persistenceContextType = newPersistenceContextType == null ? PERSISTENCE_CONTEXT_TYPE_EDEFAULT : newPersistenceContextType;
		boolean oldPersistenceContextTypeESet = persistenceContextTypeESet;
		persistenceContextTypeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.PERSISTENCE_CONTEXT_REF__PERSISTENCE_CONTEXT_TYPE, oldPersistenceContextType, persistenceContextType, !oldPersistenceContextTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetPersistenceContextType() {
		PersistenceContextType oldPersistenceContextType = persistenceContextType;
		boolean oldPersistenceContextTypeESet = persistenceContextTypeESet;
		persistenceContextType = PERSISTENCE_CONTEXT_TYPE_EDEFAULT;
		persistenceContextTypeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JavaeePackage.PERSISTENCE_CONTEXT_REF__PERSISTENCE_CONTEXT_TYPE, oldPersistenceContextType, PERSISTENCE_CONTEXT_TYPE_EDEFAULT, oldPersistenceContextTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetPersistenceContextType() {
		return persistenceContextTypeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PersistenceContextSynchronizationType getPersistenceContextSynchronization() {
		return persistenceContextSynchronization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPersistenceContextSynchronization(PersistenceContextSynchronizationType newPersistenceContextSynchronization) {
		PersistenceContextSynchronizationType oldPersistenceContextSynchronization = persistenceContextSynchronization;
		persistenceContextSynchronization = newPersistenceContextSynchronization == null ? PERSISTENCE_CONTEXT_SYNCHRONIZATION_EDEFAULT : newPersistenceContextSynchronization;
		boolean oldPersistenceContextSynchronizationESet = persistenceContextSynchronizationESet;
		persistenceContextSynchronizationESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.PERSISTENCE_CONTEXT_REF__PERSISTENCE_CONTEXT_SYNCHRONIZATION, oldPersistenceContextSynchronization, persistenceContextSynchronization, !oldPersistenceContextSynchronizationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetPersistenceContextSynchronization() {
		PersistenceContextSynchronizationType oldPersistenceContextSynchronization = persistenceContextSynchronization;
		boolean oldPersistenceContextSynchronizationESet = persistenceContextSynchronizationESet;
		persistenceContextSynchronization = PERSISTENCE_CONTEXT_SYNCHRONIZATION_EDEFAULT;
		persistenceContextSynchronizationESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JavaeePackage.PERSISTENCE_CONTEXT_REF__PERSISTENCE_CONTEXT_SYNCHRONIZATION, oldPersistenceContextSynchronization, PERSISTENCE_CONTEXT_SYNCHRONIZATION_EDEFAULT, oldPersistenceContextSynchronizationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetPersistenceContextSynchronization() {
		return persistenceContextSynchronizationESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<PropertyType> getPersistenceProperties() {
		if (persistenceProperties == null) {
			persistenceProperties = new EObjectContainmentEList<PropertyType>(PropertyType.class, this, JavaeePackage.PERSISTENCE_CONTEXT_REF__PERSISTENCE_PROPERTIES);
		}
		return persistenceProperties;
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
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.PERSISTENCE_CONTEXT_REF__MAPPED_NAME, oldMappedName, mappedName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<InjectionTarget> getInjectionTargets() {
		if (injectionTargets == null) {
			injectionTargets = new EObjectContainmentEList<InjectionTarget>(InjectionTarget.class, this, JavaeePackage.PERSISTENCE_CONTEXT_REF__INJECTION_TARGETS);
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
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.PERSISTENCE_CONTEXT_REF__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__DESCRIPTIONS:
				return ((InternalEList<?>)getDescriptions()).basicRemove(otherEnd, msgs);
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__PERSISTENCE_PROPERTIES:
				return ((InternalEList<?>)getPersistenceProperties()).basicRemove(otherEnd, msgs);
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__INJECTION_TARGETS:
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
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__DESCRIPTIONS:
				return getDescriptions();
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__PERSISTENCE_CONTEXT_REF_NAME:
				return getPersistenceContextRefName();
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__PERSISTENCE_UNIT_NAME:
				return getPersistenceUnitName();
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__PERSISTENCE_CONTEXT_TYPE:
				return getPersistenceContextType();
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__PERSISTENCE_CONTEXT_SYNCHRONIZATION:
				return getPersistenceContextSynchronization();
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__PERSISTENCE_PROPERTIES:
				return getPersistenceProperties();
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__MAPPED_NAME:
				return getMappedName();
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__INJECTION_TARGETS:
				return getInjectionTargets();
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__ID:
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
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection<? extends Description>)newValue);
				return;
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__PERSISTENCE_CONTEXT_REF_NAME:
				setPersistenceContextRefName((String)newValue);
				return;
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__PERSISTENCE_UNIT_NAME:
				setPersistenceUnitName((String)newValue);
				return;
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__PERSISTENCE_CONTEXT_TYPE:
				setPersistenceContextType((PersistenceContextType)newValue);
				return;
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__PERSISTENCE_CONTEXT_SYNCHRONIZATION:
				setPersistenceContextSynchronization((PersistenceContextSynchronizationType)newValue);
				return;
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__PERSISTENCE_PROPERTIES:
				getPersistenceProperties().clear();
				getPersistenceProperties().addAll((Collection<? extends PropertyType>)newValue);
				return;
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__MAPPED_NAME:
				setMappedName((String)newValue);
				return;
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__INJECTION_TARGETS:
				getInjectionTargets().clear();
				getInjectionTargets().addAll((Collection<? extends InjectionTarget>)newValue);
				return;
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__ID:
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
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__PERSISTENCE_CONTEXT_REF_NAME:
				setPersistenceContextRefName(PERSISTENCE_CONTEXT_REF_NAME_EDEFAULT);
				return;
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__PERSISTENCE_UNIT_NAME:
				setPersistenceUnitName(PERSISTENCE_UNIT_NAME_EDEFAULT);
				return;
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__PERSISTENCE_CONTEXT_TYPE:
				unsetPersistenceContextType();
				return;
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__PERSISTENCE_CONTEXT_SYNCHRONIZATION:
				unsetPersistenceContextSynchronization();
				return;
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__PERSISTENCE_PROPERTIES:
				getPersistenceProperties().clear();
				return;
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__MAPPED_NAME:
				setMappedName(MAPPED_NAME_EDEFAULT);
				return;
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__INJECTION_TARGETS:
				getInjectionTargets().clear();
				return;
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__ID:
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
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__PERSISTENCE_CONTEXT_REF_NAME:
				return PERSISTENCE_CONTEXT_REF_NAME_EDEFAULT == null ? persistenceContextRefName != null : !PERSISTENCE_CONTEXT_REF_NAME_EDEFAULT.equals(persistenceContextRefName);
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__PERSISTENCE_UNIT_NAME:
				return PERSISTENCE_UNIT_NAME_EDEFAULT == null ? persistenceUnitName != null : !PERSISTENCE_UNIT_NAME_EDEFAULT.equals(persistenceUnitName);
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__PERSISTENCE_CONTEXT_TYPE:
				return isSetPersistenceContextType();
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__PERSISTENCE_CONTEXT_SYNCHRONIZATION:
				return isSetPersistenceContextSynchronization();
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__PERSISTENCE_PROPERTIES:
				return persistenceProperties != null && !persistenceProperties.isEmpty();
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__MAPPED_NAME:
				return MAPPED_NAME_EDEFAULT == null ? mappedName != null : !MAPPED_NAME_EDEFAULT.equals(mappedName);
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__INJECTION_TARGETS:
				return injectionTargets != null && !injectionTargets.isEmpty();
			case JavaeePackage.PERSISTENCE_CONTEXT_REF__ID:
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
		result.append(" (persistenceContextRefName: "); //$NON-NLS-1$
		result.append(persistenceContextRefName);
		result.append(", persistenceUnitName: "); //$NON-NLS-1$
		result.append(persistenceUnitName);
		result.append(", persistenceContextType: "); //$NON-NLS-1$
		if (persistenceContextTypeESet) result.append(persistenceContextType); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", persistenceContextSynchronization: "); //$NON-NLS-1$
		if (persistenceContextSynchronizationESet) result.append(persistenceContextSynchronization); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", mappedName: "); //$NON-NLS-1$
		result.append(mappedName);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //PersistenceContextRefImpl