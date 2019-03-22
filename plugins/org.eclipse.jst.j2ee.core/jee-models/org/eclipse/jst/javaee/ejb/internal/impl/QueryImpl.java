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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.jst.javaee.core.Description;

import org.eclipse.jst.javaee.ejb.Query;
import org.eclipse.jst.javaee.ejb.QueryMethod;
import org.eclipse.jst.javaee.ejb.ResultTypeMappingType;

import org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Query</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.QueryImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.QueryImpl#getQueryMethod <em>Query Method</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.QueryImpl#getResultTypeMapping <em>Result Type Mapping</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.QueryImpl#getEjbQl <em>Ejb Ql</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.QueryImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class QueryImpl extends EObjectImpl implements Query {
	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected Description description;

	/**
	 * The cached value of the '{@link #getQueryMethod() <em>Query Method</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQueryMethod()
	 * @generated
	 * @ordered
	 */
	protected QueryMethod queryMethod;

	/**
	 * The default value of the '{@link #getResultTypeMapping() <em>Result Type Mapping</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResultTypeMapping()
	 * @generated
	 * @ordered
	 */
	protected static final ResultTypeMappingType RESULT_TYPE_MAPPING_EDEFAULT = ResultTypeMappingType.LOCAL_LITERAL;

	/**
	 * The cached value of the '{@link #getResultTypeMapping() <em>Result Type Mapping</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResultTypeMapping()
	 * @generated
	 * @ordered
	 */
	protected ResultTypeMappingType resultTypeMapping = RESULT_TYPE_MAPPING_EDEFAULT;

	/**
	 * This is true if the Result Type Mapping attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean resultTypeMappingESet;

	/**
	 * The default value of the '{@link #getEjbQl() <em>Ejb Ql</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbQl()
	 * @generated
	 * @ordered
	 */
	protected static final String EJB_QL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEjbQl() <em>Ejb Ql</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbQl()
	 * @generated
	 * @ordered
	 */
	protected String ejbQl = EJB_QL_EDEFAULT;

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
	protected QueryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EjbPackage.Literals.QUERY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Description getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDescription(Description newDescription, NotificationChain msgs) {
		Description oldDescription = description;
		description = newDescription;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.QUERY__DESCRIPTION, oldDescription, newDescription);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(Description newDescription) {
		if (newDescription != description) {
			NotificationChain msgs = null;
			if (description != null)
				msgs = ((InternalEObject)description).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.QUERY__DESCRIPTION, null, msgs);
			if (newDescription != null)
				msgs = ((InternalEObject)newDescription).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EjbPackage.QUERY__DESCRIPTION, null, msgs);
			msgs = basicSetDescription(newDescription, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.QUERY__DESCRIPTION, newDescription, newDescription));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QueryMethod getQueryMethod() {
		return queryMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetQueryMethod(QueryMethod newQueryMethod, NotificationChain msgs) {
		QueryMethod oldQueryMethod = queryMethod;
		queryMethod = newQueryMethod;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.QUERY__QUERY_METHOD, oldQueryMethod, newQueryMethod);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQueryMethod(QueryMethod newQueryMethod) {
		if (newQueryMethod != queryMethod) {
			NotificationChain msgs = null;
			if (queryMethod != null)
				msgs = ((InternalEObject)queryMethod).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.QUERY__QUERY_METHOD, null, msgs);
			if (newQueryMethod != null)
				msgs = ((InternalEObject)newQueryMethod).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EjbPackage.QUERY__QUERY_METHOD, null, msgs);
			msgs = basicSetQueryMethod(newQueryMethod, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.QUERY__QUERY_METHOD, newQueryMethod, newQueryMethod));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResultTypeMappingType getResultTypeMapping() {
		return resultTypeMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResultTypeMapping(ResultTypeMappingType newResultTypeMapping) {
		ResultTypeMappingType oldResultTypeMapping = resultTypeMapping;
		resultTypeMapping = newResultTypeMapping == null ? RESULT_TYPE_MAPPING_EDEFAULT : newResultTypeMapping;
		boolean oldResultTypeMappingESet = resultTypeMappingESet;
		resultTypeMappingESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.QUERY__RESULT_TYPE_MAPPING, oldResultTypeMapping, resultTypeMapping, !oldResultTypeMappingESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetResultTypeMapping() {
		ResultTypeMappingType oldResultTypeMapping = resultTypeMapping;
		boolean oldResultTypeMappingESet = resultTypeMappingESet;
		resultTypeMapping = RESULT_TYPE_MAPPING_EDEFAULT;
		resultTypeMappingESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, EjbPackage.QUERY__RESULT_TYPE_MAPPING, oldResultTypeMapping, RESULT_TYPE_MAPPING_EDEFAULT, oldResultTypeMappingESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetResultTypeMapping() {
		return resultTypeMappingESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEjbQl() {
		return ejbQl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEjbQl(String newEjbQl) {
		String oldEjbQl = ejbQl;
		ejbQl = newEjbQl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.QUERY__EJB_QL, oldEjbQl, ejbQl));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.QUERY__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EjbPackage.QUERY__DESCRIPTION:
				return basicSetDescription(null, msgs);
			case EjbPackage.QUERY__QUERY_METHOD:
				return basicSetQueryMethod(null, msgs);
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
			case EjbPackage.QUERY__DESCRIPTION:
				return getDescription();
			case EjbPackage.QUERY__QUERY_METHOD:
				return getQueryMethod();
			case EjbPackage.QUERY__RESULT_TYPE_MAPPING:
				return getResultTypeMapping();
			case EjbPackage.QUERY__EJB_QL:
				return getEjbQl();
			case EjbPackage.QUERY__ID:
				return getId();
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
			case EjbPackage.QUERY__DESCRIPTION:
				setDescription((Description)newValue);
				return;
			case EjbPackage.QUERY__QUERY_METHOD:
				setQueryMethod((QueryMethod)newValue);
				return;
			case EjbPackage.QUERY__RESULT_TYPE_MAPPING:
				setResultTypeMapping((ResultTypeMappingType)newValue);
				return;
			case EjbPackage.QUERY__EJB_QL:
				setEjbQl((String)newValue);
				return;
			case EjbPackage.QUERY__ID:
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
			case EjbPackage.QUERY__DESCRIPTION:
				setDescription((Description)null);
				return;
			case EjbPackage.QUERY__QUERY_METHOD:
				setQueryMethod((QueryMethod)null);
				return;
			case EjbPackage.QUERY__RESULT_TYPE_MAPPING:
				unsetResultTypeMapping();
				return;
			case EjbPackage.QUERY__EJB_QL:
				setEjbQl(EJB_QL_EDEFAULT);
				return;
			case EjbPackage.QUERY__ID:
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
			case EjbPackage.QUERY__DESCRIPTION:
				return description != null;
			case EjbPackage.QUERY__QUERY_METHOD:
				return queryMethod != null;
			case EjbPackage.QUERY__RESULT_TYPE_MAPPING:
				return isSetResultTypeMapping();
			case EjbPackage.QUERY__EJB_QL:
				return EJB_QL_EDEFAULT == null ? ejbQl != null : !EJB_QL_EDEFAULT.equals(ejbQl);
			case EjbPackage.QUERY__ID:
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
		result.append(" (resultTypeMapping: "); //$NON-NLS-1$
		if (resultTypeMappingESet) result.append(resultTypeMapping); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", ejbQl: "); //$NON-NLS-1$
		result.append(ejbQl);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //QueryImpl