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
package org.eclipse.jst.javaee.ejb.internal.impl;

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
import org.eclipse.jst.javaee.core.EmptyType;

import org.eclipse.jst.javaee.ejb.CMRField;
import org.eclipse.jst.javaee.ejb.EJBRelationshipRole;
import org.eclipse.jst.javaee.ejb.MultiplicityType;
import org.eclipse.jst.javaee.ejb.RelationshipRoleSourceType;

import org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EJB Relationship Role</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EJBRelationshipRoleImpl#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EJBRelationshipRoleImpl#getEjbRelationshipRoleName <em>Ejb Relationship Role Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EJBRelationshipRoleImpl#getMultiplicity <em>Multiplicity</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EJBRelationshipRoleImpl#getCascadeDelete <em>Cascade Delete</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EJBRelationshipRoleImpl#getRelationshipRoleSource <em>Relationship Role Source</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EJBRelationshipRoleImpl#getCmrField <em>Cmr Field</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EJBRelationshipRoleImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EJBRelationshipRoleImpl extends EObjectImpl implements EJBRelationshipRole {
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
	 * The default value of the '{@link #getEjbRelationshipRoleName() <em>Ejb Relationship Role Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbRelationshipRoleName()
	 * @generated
	 * @ordered
	 */
	protected static final String EJB_RELATIONSHIP_ROLE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEjbRelationshipRoleName() <em>Ejb Relationship Role Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbRelationshipRoleName()
	 * @generated
	 * @ordered
	 */
	protected String ejbRelationshipRoleName = EJB_RELATIONSHIP_ROLE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getMultiplicity() <em>Multiplicity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMultiplicity()
	 * @generated
	 * @ordered
	 */
	protected static final MultiplicityType MULTIPLICITY_EDEFAULT = MultiplicityType.ONE_LITERAL;

	/**
	 * The cached value of the '{@link #getMultiplicity() <em>Multiplicity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMultiplicity()
	 * @generated
	 * @ordered
	 */
	protected MultiplicityType multiplicity = MULTIPLICITY_EDEFAULT;

	/**
	 * This is true if the Multiplicity attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean multiplicityESet;

	/**
	 * The cached value of the '{@link #getCascadeDelete() <em>Cascade Delete</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCascadeDelete()
	 * @generated
	 * @ordered
	 */
	protected EmptyType cascadeDelete;

	/**
	 * The cached value of the '{@link #getRelationshipRoleSource() <em>Relationship Role Source</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelationshipRoleSource()
	 * @generated
	 * @ordered
	 */
	protected RelationshipRoleSourceType relationshipRoleSource;

	/**
	 * The cached value of the '{@link #getCmrField() <em>Cmr Field</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCmrField()
	 * @generated
	 * @ordered
	 */
	protected CMRField cmrField;

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
	protected EJBRelationshipRoleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EjbPackage.Literals.EJB_RELATIONSHIP_ROLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Description> getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList<Description>(Description.class, this, EjbPackage.EJB_RELATIONSHIP_ROLE__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEjbRelationshipRoleName() {
		return ejbRelationshipRoleName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEjbRelationshipRoleName(String newEjbRelationshipRoleName) {
		String oldEjbRelationshipRoleName = ejbRelationshipRoleName;
		ejbRelationshipRoleName = newEjbRelationshipRoleName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_RELATIONSHIP_ROLE__EJB_RELATIONSHIP_ROLE_NAME, oldEjbRelationshipRoleName, ejbRelationshipRoleName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MultiplicityType getMultiplicity() {
		return multiplicity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMultiplicity(MultiplicityType newMultiplicity) {
		MultiplicityType oldMultiplicity = multiplicity;
		multiplicity = newMultiplicity == null ? MULTIPLICITY_EDEFAULT : newMultiplicity;
		boolean oldMultiplicityESet = multiplicityESet;
		multiplicityESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_RELATIONSHIP_ROLE__MULTIPLICITY, oldMultiplicity, multiplicity, !oldMultiplicityESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetMultiplicity() {
		MultiplicityType oldMultiplicity = multiplicity;
		boolean oldMultiplicityESet = multiplicityESet;
		multiplicity = MULTIPLICITY_EDEFAULT;
		multiplicityESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, EjbPackage.EJB_RELATIONSHIP_ROLE__MULTIPLICITY, oldMultiplicity, MULTIPLICITY_EDEFAULT, oldMultiplicityESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetMultiplicity() {
		return multiplicityESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EmptyType getCascadeDelete() {
		return cascadeDelete;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCascadeDelete(EmptyType newCascadeDelete, NotificationChain msgs) {
		EmptyType oldCascadeDelete = cascadeDelete;
		cascadeDelete = newCascadeDelete;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_RELATIONSHIP_ROLE__CASCADE_DELETE, oldCascadeDelete, newCascadeDelete);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCascadeDelete(EmptyType newCascadeDelete) {
		if (newCascadeDelete != cascadeDelete) {
			NotificationChain msgs = null;
			if (cascadeDelete != null)
				msgs = ((InternalEObject)cascadeDelete).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.EJB_RELATIONSHIP_ROLE__CASCADE_DELETE, null, msgs);
			if (newCascadeDelete != null)
				msgs = ((InternalEObject)newCascadeDelete).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EjbPackage.EJB_RELATIONSHIP_ROLE__CASCADE_DELETE, null, msgs);
			msgs = basicSetCascadeDelete(newCascadeDelete, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_RELATIONSHIP_ROLE__CASCADE_DELETE, newCascadeDelete, newCascadeDelete));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelationshipRoleSourceType getRelationshipRoleSource() {
		return relationshipRoleSource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRelationshipRoleSource(RelationshipRoleSourceType newRelationshipRoleSource, NotificationChain msgs) {
		RelationshipRoleSourceType oldRelationshipRoleSource = relationshipRoleSource;
		relationshipRoleSource = newRelationshipRoleSource;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_RELATIONSHIP_ROLE__RELATIONSHIP_ROLE_SOURCE, oldRelationshipRoleSource, newRelationshipRoleSource);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRelationshipRoleSource(RelationshipRoleSourceType newRelationshipRoleSource) {
		if (newRelationshipRoleSource != relationshipRoleSource) {
			NotificationChain msgs = null;
			if (relationshipRoleSource != null)
				msgs = ((InternalEObject)relationshipRoleSource).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.EJB_RELATIONSHIP_ROLE__RELATIONSHIP_ROLE_SOURCE, null, msgs);
			if (newRelationshipRoleSource != null)
				msgs = ((InternalEObject)newRelationshipRoleSource).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EjbPackage.EJB_RELATIONSHIP_ROLE__RELATIONSHIP_ROLE_SOURCE, null, msgs);
			msgs = basicSetRelationshipRoleSource(newRelationshipRoleSource, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_RELATIONSHIP_ROLE__RELATIONSHIP_ROLE_SOURCE, newRelationshipRoleSource, newRelationshipRoleSource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CMRField getCmrField() {
		return cmrField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCmrField(CMRField newCmrField, NotificationChain msgs) {
		CMRField oldCmrField = cmrField;
		cmrField = newCmrField;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_RELATIONSHIP_ROLE__CMR_FIELD, oldCmrField, newCmrField);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCmrField(CMRField newCmrField) {
		if (newCmrField != cmrField) {
			NotificationChain msgs = null;
			if (cmrField != null)
				msgs = ((InternalEObject)cmrField).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.EJB_RELATIONSHIP_ROLE__CMR_FIELD, null, msgs);
			if (newCmrField != null)
				msgs = ((InternalEObject)newCmrField).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EjbPackage.EJB_RELATIONSHIP_ROLE__CMR_FIELD, null, msgs);
			msgs = basicSetCmrField(newCmrField, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_RELATIONSHIP_ROLE__CMR_FIELD, newCmrField, newCmrField));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_RELATIONSHIP_ROLE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EjbPackage.EJB_RELATIONSHIP_ROLE__DESCRIPTIONS:
				return ((InternalEList<?>)getDescriptions()).basicRemove(otherEnd, msgs);
			case EjbPackage.EJB_RELATIONSHIP_ROLE__CASCADE_DELETE:
				return basicSetCascadeDelete(null, msgs);
			case EjbPackage.EJB_RELATIONSHIP_ROLE__RELATIONSHIP_ROLE_SOURCE:
				return basicSetRelationshipRoleSource(null, msgs);
			case EjbPackage.EJB_RELATIONSHIP_ROLE__CMR_FIELD:
				return basicSetCmrField(null, msgs);
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
			case EjbPackage.EJB_RELATIONSHIP_ROLE__DESCRIPTIONS:
				return getDescriptions();
			case EjbPackage.EJB_RELATIONSHIP_ROLE__EJB_RELATIONSHIP_ROLE_NAME:
				return getEjbRelationshipRoleName();
			case EjbPackage.EJB_RELATIONSHIP_ROLE__MULTIPLICITY:
				return getMultiplicity();
			case EjbPackage.EJB_RELATIONSHIP_ROLE__CASCADE_DELETE:
				return getCascadeDelete();
			case EjbPackage.EJB_RELATIONSHIP_ROLE__RELATIONSHIP_ROLE_SOURCE:
				return getRelationshipRoleSource();
			case EjbPackage.EJB_RELATIONSHIP_ROLE__CMR_FIELD:
				return getCmrField();
			case EjbPackage.EJB_RELATIONSHIP_ROLE__ID:
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
			case EjbPackage.EJB_RELATIONSHIP_ROLE__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection<? extends Description>)newValue);
				return;
			case EjbPackage.EJB_RELATIONSHIP_ROLE__EJB_RELATIONSHIP_ROLE_NAME:
				setEjbRelationshipRoleName((String)newValue);
				return;
			case EjbPackage.EJB_RELATIONSHIP_ROLE__MULTIPLICITY:
				setMultiplicity((MultiplicityType)newValue);
				return;
			case EjbPackage.EJB_RELATIONSHIP_ROLE__CASCADE_DELETE:
				setCascadeDelete((EmptyType)newValue);
				return;
			case EjbPackage.EJB_RELATIONSHIP_ROLE__RELATIONSHIP_ROLE_SOURCE:
				setRelationshipRoleSource((RelationshipRoleSourceType)newValue);
				return;
			case EjbPackage.EJB_RELATIONSHIP_ROLE__CMR_FIELD:
				setCmrField((CMRField)newValue);
				return;
			case EjbPackage.EJB_RELATIONSHIP_ROLE__ID:
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
			case EjbPackage.EJB_RELATIONSHIP_ROLE__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case EjbPackage.EJB_RELATIONSHIP_ROLE__EJB_RELATIONSHIP_ROLE_NAME:
				setEjbRelationshipRoleName(EJB_RELATIONSHIP_ROLE_NAME_EDEFAULT);
				return;
			case EjbPackage.EJB_RELATIONSHIP_ROLE__MULTIPLICITY:
				unsetMultiplicity();
				return;
			case EjbPackage.EJB_RELATIONSHIP_ROLE__CASCADE_DELETE:
				setCascadeDelete((EmptyType)null);
				return;
			case EjbPackage.EJB_RELATIONSHIP_ROLE__RELATIONSHIP_ROLE_SOURCE:
				setRelationshipRoleSource((RelationshipRoleSourceType)null);
				return;
			case EjbPackage.EJB_RELATIONSHIP_ROLE__CMR_FIELD:
				setCmrField((CMRField)null);
				return;
			case EjbPackage.EJB_RELATIONSHIP_ROLE__ID:
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
			case EjbPackage.EJB_RELATIONSHIP_ROLE__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case EjbPackage.EJB_RELATIONSHIP_ROLE__EJB_RELATIONSHIP_ROLE_NAME:
				return EJB_RELATIONSHIP_ROLE_NAME_EDEFAULT == null ? ejbRelationshipRoleName != null : !EJB_RELATIONSHIP_ROLE_NAME_EDEFAULT.equals(ejbRelationshipRoleName);
			case EjbPackage.EJB_RELATIONSHIP_ROLE__MULTIPLICITY:
				return isSetMultiplicity();
			case EjbPackage.EJB_RELATIONSHIP_ROLE__CASCADE_DELETE:
				return cascadeDelete != null;
			case EjbPackage.EJB_RELATIONSHIP_ROLE__RELATIONSHIP_ROLE_SOURCE:
				return relationshipRoleSource != null;
			case EjbPackage.EJB_RELATIONSHIP_ROLE__CMR_FIELD:
				return cmrField != null;
			case EjbPackage.EJB_RELATIONSHIP_ROLE__ID:
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
		result.append(" (ejbRelationshipRoleName: "); //$NON-NLS-1$
		result.append(ejbRelationshipRoleName);
		result.append(", multiplicity: "); //$NON-NLS-1$
		if (multiplicityESet) result.append(multiplicity); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //EJBRelationshipRoleImpl