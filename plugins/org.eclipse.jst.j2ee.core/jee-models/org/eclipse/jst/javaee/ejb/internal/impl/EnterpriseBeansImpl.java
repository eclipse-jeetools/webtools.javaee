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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jst.javaee.ejb.EnterpriseBeans;

import org.eclipse.jst.javaee.ejb.EntityBean;
import org.eclipse.jst.javaee.ejb.MessageDrivenBean;
import org.eclipse.jst.javaee.ejb.SessionBean;
import org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Enterprise Beans</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EnterpriseBeansImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EnterpriseBeansImpl#getSessionBeans <em>Session Beans</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EnterpriseBeansImpl#getEntityBeans <em>Entity Beans</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EnterpriseBeansImpl#getMessageDrivenBeans <em>Message Driven Beans</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EnterpriseBeansImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EnterpriseBeansImpl extends EObjectImpl implements EnterpriseBeans {
	/**
	 * The cached value of the '{@link #getGroup() <em>Group</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group;

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
	protected EnterpriseBeansImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EjbPackage.Literals.ENTERPRISE_BEANS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, EjbPackage.ENTERPRISE_BEANS__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<SessionBean> getSessionBeans() {
		return getGroup().list(EjbPackage.Literals.ENTERPRISE_BEANS__SESSION_BEANS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<EntityBean> getEntityBeans() {
		return getGroup().list(EjbPackage.Literals.ENTERPRISE_BEANS__ENTITY_BEANS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<MessageDrivenBean> getMessageDrivenBeans() {
		return getGroup().list(EjbPackage.Literals.ENTERPRISE_BEANS__MESSAGE_DRIVEN_BEANS);
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
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.ENTERPRISE_BEANS__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EjbPackage.ENTERPRISE_BEANS__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
			case EjbPackage.ENTERPRISE_BEANS__SESSION_BEANS:
				return ((InternalEList<?>)getSessionBeans()).basicRemove(otherEnd, msgs);
			case EjbPackage.ENTERPRISE_BEANS__ENTITY_BEANS:
				return ((InternalEList<?>)getEntityBeans()).basicRemove(otherEnd, msgs);
			case EjbPackage.ENTERPRISE_BEANS__MESSAGE_DRIVEN_BEANS:
				return ((InternalEList<?>)getMessageDrivenBeans()).basicRemove(otherEnd, msgs);
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
			case EjbPackage.ENTERPRISE_BEANS__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case EjbPackage.ENTERPRISE_BEANS__SESSION_BEANS:
				return getSessionBeans();
			case EjbPackage.ENTERPRISE_BEANS__ENTITY_BEANS:
				return getEntityBeans();
			case EjbPackage.ENTERPRISE_BEANS__MESSAGE_DRIVEN_BEANS:
				return getMessageDrivenBeans();
			case EjbPackage.ENTERPRISE_BEANS__ID:
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
			case EjbPackage.ENTERPRISE_BEANS__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case EjbPackage.ENTERPRISE_BEANS__SESSION_BEANS:
				getSessionBeans().clear();
				getSessionBeans().addAll((Collection<? extends SessionBean>)newValue);
				return;
			case EjbPackage.ENTERPRISE_BEANS__ENTITY_BEANS:
				getEntityBeans().clear();
				getEntityBeans().addAll((Collection<? extends EntityBean>)newValue);
				return;
			case EjbPackage.ENTERPRISE_BEANS__MESSAGE_DRIVEN_BEANS:
				getMessageDrivenBeans().clear();
				getMessageDrivenBeans().addAll((Collection<? extends MessageDrivenBean>)newValue);
				return;
			case EjbPackage.ENTERPRISE_BEANS__ID:
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
			case EjbPackage.ENTERPRISE_BEANS__GROUP:
				getGroup().clear();
				return;
			case EjbPackage.ENTERPRISE_BEANS__SESSION_BEANS:
				getSessionBeans().clear();
				return;
			case EjbPackage.ENTERPRISE_BEANS__ENTITY_BEANS:
				getEntityBeans().clear();
				return;
			case EjbPackage.ENTERPRISE_BEANS__MESSAGE_DRIVEN_BEANS:
				getMessageDrivenBeans().clear();
				return;
			case EjbPackage.ENTERPRISE_BEANS__ID:
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
			case EjbPackage.ENTERPRISE_BEANS__GROUP:
				return group != null && !group.isEmpty();
			case EjbPackage.ENTERPRISE_BEANS__SESSION_BEANS:
				return !getSessionBeans().isEmpty();
			case EjbPackage.ENTERPRISE_BEANS__ENTITY_BEANS:
				return !getEntityBeans().isEmpty();
			case EjbPackage.ENTERPRISE_BEANS__MESSAGE_DRIVEN_BEANS:
				return !getMessageDrivenBeans().isEmpty();
			case EjbPackage.ENTERPRISE_BEANS__ID:
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
		result.append(" (group: "); //$NON-NLS-1$
		result.append(group);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //EnterpriseBeansImpl