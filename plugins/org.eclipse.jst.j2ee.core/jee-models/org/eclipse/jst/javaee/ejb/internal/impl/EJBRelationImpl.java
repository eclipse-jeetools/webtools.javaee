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

import org.eclipse.jst.javaee.ejb.EJBRelation;
import org.eclipse.jst.javaee.ejb.EJBRelationshipRole;

import org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EJB Relation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EJBRelationImpl#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EJBRelationImpl#getEjbRelationName <em>Ejb Relation Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EJBRelationImpl#getEjbRelationshipRoles <em>Ejb Relationship Roles</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EJBRelationImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EJBRelationImpl extends EObjectImpl implements EJBRelation {
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
	 * The default value of the '{@link #getEjbRelationName() <em>Ejb Relation Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbRelationName()
	 * @generated
	 * @ordered
	 */
	protected static final String EJB_RELATION_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEjbRelationName() <em>Ejb Relation Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbRelationName()
	 * @generated
	 * @ordered
	 */
	protected String ejbRelationName = EJB_RELATION_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getEjbRelationshipRoles() <em>Ejb Relationship Roles</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbRelationshipRoles()
	 * @generated
	 * @ordered
	 */
	protected EList<EJBRelationshipRole> ejbRelationshipRoles;

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
	protected EJBRelationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EjbPackage.Literals.EJB_RELATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Description> getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList<Description>(Description.class, this, EjbPackage.EJB_RELATION__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getEjbRelationName() {
		return ejbRelationName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEjbRelationName(String newEjbRelationName) {
		String oldEjbRelationName = ejbRelationName;
		ejbRelationName = newEjbRelationName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_RELATION__EJB_RELATION_NAME, oldEjbRelationName, ejbRelationName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<EJBRelationshipRole> getEjbRelationshipRoles() {
		if (ejbRelationshipRoles == null) {
			ejbRelationshipRoles = new EObjectContainmentEList<EJBRelationshipRole>(EJBRelationshipRole.class, this, EjbPackage.EJB_RELATION__EJB_RELATIONSHIP_ROLES);
		}
		return ejbRelationshipRoles;
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
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_RELATION__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EjbPackage.EJB_RELATION__DESCRIPTIONS:
				return ((InternalEList<?>)getDescriptions()).basicRemove(otherEnd, msgs);
			case EjbPackage.EJB_RELATION__EJB_RELATIONSHIP_ROLES:
				return ((InternalEList<?>)getEjbRelationshipRoles()).basicRemove(otherEnd, msgs);
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
			case EjbPackage.EJB_RELATION__DESCRIPTIONS:
				return getDescriptions();
			case EjbPackage.EJB_RELATION__EJB_RELATION_NAME:
				return getEjbRelationName();
			case EjbPackage.EJB_RELATION__EJB_RELATIONSHIP_ROLES:
				return getEjbRelationshipRoles();
			case EjbPackage.EJB_RELATION__ID:
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
			case EjbPackage.EJB_RELATION__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection<? extends Description>)newValue);
				return;
			case EjbPackage.EJB_RELATION__EJB_RELATION_NAME:
				setEjbRelationName((String)newValue);
				return;
			case EjbPackage.EJB_RELATION__EJB_RELATIONSHIP_ROLES:
				getEjbRelationshipRoles().clear();
				getEjbRelationshipRoles().addAll((Collection<? extends EJBRelationshipRole>)newValue);
				return;
			case EjbPackage.EJB_RELATION__ID:
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
			case EjbPackage.EJB_RELATION__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case EjbPackage.EJB_RELATION__EJB_RELATION_NAME:
				setEjbRelationName(EJB_RELATION_NAME_EDEFAULT);
				return;
			case EjbPackage.EJB_RELATION__EJB_RELATIONSHIP_ROLES:
				getEjbRelationshipRoles().clear();
				return;
			case EjbPackage.EJB_RELATION__ID:
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
			case EjbPackage.EJB_RELATION__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case EjbPackage.EJB_RELATION__EJB_RELATION_NAME:
				return EJB_RELATION_NAME_EDEFAULT == null ? ejbRelationName != null : !EJB_RELATION_NAME_EDEFAULT.equals(ejbRelationName);
			case EjbPackage.EJB_RELATION__EJB_RELATIONSHIP_ROLES:
				return ejbRelationshipRoles != null && !ejbRelationshipRoles.isEmpty();
			case EjbPackage.EJB_RELATION__ID:
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
		result.append(" (ejbRelationName: "); //$NON-NLS-1$
		result.append(ejbRelationName);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //EJBRelationImpl