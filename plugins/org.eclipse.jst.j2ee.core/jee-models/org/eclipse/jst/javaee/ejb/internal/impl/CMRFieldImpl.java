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

import org.eclipse.jst.javaee.ejb.CMRField;
import org.eclipse.jst.javaee.ejb.CMRFieldType;

import org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CMR Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.CMRFieldImpl#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.CMRFieldImpl#getCmrFieldName <em>Cmr Field Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.CMRFieldImpl#getCmrFieldType <em>Cmr Field Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.CMRFieldImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CMRFieldImpl extends EObjectImpl implements CMRField {
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
	 * The default value of the '{@link #getCmrFieldName() <em>Cmr Field Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCmrFieldName()
	 * @generated
	 * @ordered
	 */
	protected static final String CMR_FIELD_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCmrFieldName() <em>Cmr Field Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCmrFieldName()
	 * @generated
	 * @ordered
	 */
	protected String cmrFieldName = CMR_FIELD_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getCmrFieldType() <em>Cmr Field Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCmrFieldType()
	 * @generated
	 * @ordered
	 */
	protected static final CMRFieldType CMR_FIELD_TYPE_EDEFAULT = CMRFieldType.JAVA_UTIL_COLLECTION_LITERAL;

	/**
	 * The cached value of the '{@link #getCmrFieldType() <em>Cmr Field Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCmrFieldType()
	 * @generated
	 * @ordered
	 */
	protected CMRFieldType cmrFieldType = CMR_FIELD_TYPE_EDEFAULT;

	/**
	 * This is true if the Cmr Field Type attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean cmrFieldTypeESet;

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
	protected CMRFieldImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EjbPackage.Literals.CMR_FIELD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Description> getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList<Description>(Description.class, this, EjbPackage.CMR_FIELD__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCmrFieldName() {
		return cmrFieldName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCmrFieldName(String newCmrFieldName) {
		String oldCmrFieldName = cmrFieldName;
		cmrFieldName = newCmrFieldName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.CMR_FIELD__CMR_FIELD_NAME, oldCmrFieldName, cmrFieldName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CMRFieldType getCmrFieldType() {
		return cmrFieldType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCmrFieldType(CMRFieldType newCmrFieldType) {
		CMRFieldType oldCmrFieldType = cmrFieldType;
		cmrFieldType = newCmrFieldType == null ? CMR_FIELD_TYPE_EDEFAULT : newCmrFieldType;
		boolean oldCmrFieldTypeESet = cmrFieldTypeESet;
		cmrFieldTypeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.CMR_FIELD__CMR_FIELD_TYPE, oldCmrFieldType, cmrFieldType, !oldCmrFieldTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetCmrFieldType() {
		CMRFieldType oldCmrFieldType = cmrFieldType;
		boolean oldCmrFieldTypeESet = cmrFieldTypeESet;
		cmrFieldType = CMR_FIELD_TYPE_EDEFAULT;
		cmrFieldTypeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, EjbPackage.CMR_FIELD__CMR_FIELD_TYPE, oldCmrFieldType, CMR_FIELD_TYPE_EDEFAULT, oldCmrFieldTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetCmrFieldType() {
		return cmrFieldTypeESet;
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
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.CMR_FIELD__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EjbPackage.CMR_FIELD__DESCRIPTIONS:
				return ((InternalEList<?>)getDescriptions()).basicRemove(otherEnd, msgs);
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
			case EjbPackage.CMR_FIELD__DESCRIPTIONS:
				return getDescriptions();
			case EjbPackage.CMR_FIELD__CMR_FIELD_NAME:
				return getCmrFieldName();
			case EjbPackage.CMR_FIELD__CMR_FIELD_TYPE:
				return getCmrFieldType();
			case EjbPackage.CMR_FIELD__ID:
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
			case EjbPackage.CMR_FIELD__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection<? extends Description>)newValue);
				return;
			case EjbPackage.CMR_FIELD__CMR_FIELD_NAME:
				setCmrFieldName((String)newValue);
				return;
			case EjbPackage.CMR_FIELD__CMR_FIELD_TYPE:
				setCmrFieldType((CMRFieldType)newValue);
				return;
			case EjbPackage.CMR_FIELD__ID:
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
			case EjbPackage.CMR_FIELD__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case EjbPackage.CMR_FIELD__CMR_FIELD_NAME:
				setCmrFieldName(CMR_FIELD_NAME_EDEFAULT);
				return;
			case EjbPackage.CMR_FIELD__CMR_FIELD_TYPE:
				unsetCmrFieldType();
				return;
			case EjbPackage.CMR_FIELD__ID:
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
			case EjbPackage.CMR_FIELD__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case EjbPackage.CMR_FIELD__CMR_FIELD_NAME:
				return CMR_FIELD_NAME_EDEFAULT == null ? cmrFieldName != null : !CMR_FIELD_NAME_EDEFAULT.equals(cmrFieldName);
			case EjbPackage.CMR_FIELD__CMR_FIELD_TYPE:
				return isSetCmrFieldType();
			case EjbPackage.CMR_FIELD__ID:
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
		result.append(" (cmrFieldName: "); //$NON-NLS-1$
		result.append(cmrFieldName);
		result.append(", cmrFieldType: "); //$NON-NLS-1$
		if (cmrFieldTypeESet) result.append(cmrFieldType); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //CMRFieldImpl