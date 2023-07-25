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

import org.eclipse.jst.javaee.ejb.MethodInterfaceType;
import org.eclipse.jst.javaee.ejb.MethodParams;
import org.eclipse.jst.javaee.ejb.MethodType;

import org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Method Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MethodTypeImpl#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MethodTypeImpl#getEjbName <em>Ejb Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MethodTypeImpl#getMethodIntf <em>Method Intf</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MethodTypeImpl#getMethodName <em>Method Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MethodTypeImpl#getMethodParams <em>Method Params</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.MethodTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MethodTypeImpl extends EObjectImpl implements MethodType {
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
	 * The default value of the '{@link #getEjbName() <em>Ejb Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbName()
	 * @generated
	 * @ordered
	 */
	protected static final String EJB_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEjbName() <em>Ejb Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbName()
	 * @generated
	 * @ordered
	 */
	protected String ejbName = EJB_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getMethodIntf() <em>Method Intf</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodIntf()
	 * @generated
	 * @ordered
	 */
	protected static final MethodInterfaceType METHOD_INTF_EDEFAULT = MethodInterfaceType.HOME_LITERAL;

	/**
	 * The cached value of the '{@link #getMethodIntf() <em>Method Intf</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodIntf()
	 * @generated
	 * @ordered
	 */
	protected MethodInterfaceType methodIntf = METHOD_INTF_EDEFAULT;

	/**
	 * This is true if the Method Intf attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean methodIntfESet;

	/**
	 * The default value of the '{@link #getMethodName() <em>Method Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodName()
	 * @generated
	 * @ordered
	 */
	protected static final String METHOD_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMethodName() <em>Method Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodName()
	 * @generated
	 * @ordered
	 */
	protected String methodName = METHOD_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMethodParams() <em>Method Params</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodParams()
	 * @generated
	 * @ordered
	 */
	protected MethodParams methodParams;

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
	protected MethodTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EjbPackage.Literals.METHOD_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Description> getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList<Description>(Description.class, this, EjbPackage.METHOD_TYPE__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getEjbName() {
		return ejbName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEjbName(String newEjbName) {
		String oldEjbName = ejbName;
		ejbName = newEjbName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.METHOD_TYPE__EJB_NAME, oldEjbName, ejbName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MethodInterfaceType getMethodIntf() {
		return methodIntf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMethodIntf(MethodInterfaceType newMethodIntf) {
		MethodInterfaceType oldMethodIntf = methodIntf;
		methodIntf = newMethodIntf == null ? METHOD_INTF_EDEFAULT : newMethodIntf;
		boolean oldMethodIntfESet = methodIntfESet;
		methodIntfESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.METHOD_TYPE__METHOD_INTF, oldMethodIntf, methodIntf, !oldMethodIntfESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetMethodIntf() {
		MethodInterfaceType oldMethodIntf = methodIntf;
		boolean oldMethodIntfESet = methodIntfESet;
		methodIntf = METHOD_INTF_EDEFAULT;
		methodIntfESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, EjbPackage.METHOD_TYPE__METHOD_INTF, oldMethodIntf, METHOD_INTF_EDEFAULT, oldMethodIntfESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetMethodIntf() {
		return methodIntfESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMethodName() {
		return methodName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMethodName(String newMethodName) {
		String oldMethodName = methodName;
		methodName = newMethodName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.METHOD_TYPE__METHOD_NAME, oldMethodName, methodName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MethodParams getMethodParams() {
		return methodParams;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMethodParams(MethodParams newMethodParams, NotificationChain msgs) {
		MethodParams oldMethodParams = methodParams;
		methodParams = newMethodParams;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.METHOD_TYPE__METHOD_PARAMS, oldMethodParams, newMethodParams);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMethodParams(MethodParams newMethodParams) {
		if (newMethodParams != methodParams) {
			NotificationChain msgs = null;
			if (methodParams != null)
				msgs = ((InternalEObject)methodParams).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.METHOD_TYPE__METHOD_PARAMS, null, msgs);
			if (newMethodParams != null)
				msgs = ((InternalEObject)newMethodParams).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EjbPackage.METHOD_TYPE__METHOD_PARAMS, null, msgs);
			msgs = basicSetMethodParams(newMethodParams, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.METHOD_TYPE__METHOD_PARAMS, newMethodParams, newMethodParams));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.METHOD_TYPE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EjbPackage.METHOD_TYPE__DESCRIPTIONS:
				return ((InternalEList<?>)getDescriptions()).basicRemove(otherEnd, msgs);
			case EjbPackage.METHOD_TYPE__METHOD_PARAMS:
				return basicSetMethodParams(null, msgs);
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
			case EjbPackage.METHOD_TYPE__DESCRIPTIONS:
				return getDescriptions();
			case EjbPackage.METHOD_TYPE__EJB_NAME:
				return getEjbName();
			case EjbPackage.METHOD_TYPE__METHOD_INTF:
				return getMethodIntf();
			case EjbPackage.METHOD_TYPE__METHOD_NAME:
				return getMethodName();
			case EjbPackage.METHOD_TYPE__METHOD_PARAMS:
				return getMethodParams();
			case EjbPackage.METHOD_TYPE__ID:
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
			case EjbPackage.METHOD_TYPE__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection<? extends Description>)newValue);
				return;
			case EjbPackage.METHOD_TYPE__EJB_NAME:
				setEjbName((String)newValue);
				return;
			case EjbPackage.METHOD_TYPE__METHOD_INTF:
				setMethodIntf((MethodInterfaceType)newValue);
				return;
			case EjbPackage.METHOD_TYPE__METHOD_NAME:
				setMethodName((String)newValue);
				return;
			case EjbPackage.METHOD_TYPE__METHOD_PARAMS:
				setMethodParams((MethodParams)newValue);
				return;
			case EjbPackage.METHOD_TYPE__ID:
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
			case EjbPackage.METHOD_TYPE__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case EjbPackage.METHOD_TYPE__EJB_NAME:
				setEjbName(EJB_NAME_EDEFAULT);
				return;
			case EjbPackage.METHOD_TYPE__METHOD_INTF:
				unsetMethodIntf();
				return;
			case EjbPackage.METHOD_TYPE__METHOD_NAME:
				setMethodName(METHOD_NAME_EDEFAULT);
				return;
			case EjbPackage.METHOD_TYPE__METHOD_PARAMS:
				setMethodParams((MethodParams)null);
				return;
			case EjbPackage.METHOD_TYPE__ID:
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
			case EjbPackage.METHOD_TYPE__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case EjbPackage.METHOD_TYPE__EJB_NAME:
				return EJB_NAME_EDEFAULT == null ? ejbName != null : !EJB_NAME_EDEFAULT.equals(ejbName);
			case EjbPackage.METHOD_TYPE__METHOD_INTF:
				return isSetMethodIntf();
			case EjbPackage.METHOD_TYPE__METHOD_NAME:
				return METHOD_NAME_EDEFAULT == null ? methodName != null : !METHOD_NAME_EDEFAULT.equals(methodName);
			case EjbPackage.METHOD_TYPE__METHOD_PARAMS:
				return methodParams != null;
			case EjbPackage.METHOD_TYPE__ID:
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
		result.append(" (ejbName: "); //$NON-NLS-1$
		result.append(ejbName);
		result.append(", methodIntf: "); //$NON-NLS-1$
		if (methodIntfESet) result.append(methodIntf); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", methodName: "); //$NON-NLS-1$
		result.append(methodName);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //MethodTypeImpl