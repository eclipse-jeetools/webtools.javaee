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

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jst.javaee.core.Description;

import org.eclipse.jst.javaee.ejb.InterceptorBindingType;
import org.eclipse.jst.javaee.ejb.InterceptorOrderType;
import org.eclipse.jst.javaee.ejb.NamedMethodType;

import org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Interceptor Binding Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorBindingTypeImpl#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorBindingTypeImpl#getEjbName <em>Ejb Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorBindingTypeImpl#getInterceptorClasses <em>Interceptor Classes</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorBindingTypeImpl#getInterceptorOrder <em>Interceptor Order</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorBindingTypeImpl#isExcludeDefaultInterceptors <em>Exclude Default Interceptors</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorBindingTypeImpl#isExcludeClassInterceptors <em>Exclude Class Interceptors</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorBindingTypeImpl#getMethod <em>Method</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorBindingTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InterceptorBindingTypeImpl extends EObjectImpl implements InterceptorBindingType {
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
	 * The cached value of the '{@link #getInterceptorClasses() <em>Interceptor Classes</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterceptorClasses()
	 * @generated
	 * @ordered
	 */
	protected EList<String> interceptorClasses;

	/**
	 * The cached value of the '{@link #getInterceptorOrder() <em>Interceptor Order</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterceptorOrder()
	 * @generated
	 * @ordered
	 */
	protected InterceptorOrderType interceptorOrder;

	/**
	 * The default value of the '{@link #isExcludeDefaultInterceptors() <em>Exclude Default Interceptors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExcludeDefaultInterceptors()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EXCLUDE_DEFAULT_INTERCEPTORS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isExcludeDefaultInterceptors() <em>Exclude Default Interceptors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExcludeDefaultInterceptors()
	 * @generated
	 * @ordered
	 */
	protected boolean excludeDefaultInterceptors = EXCLUDE_DEFAULT_INTERCEPTORS_EDEFAULT;

	/**
	 * This is true if the Exclude Default Interceptors attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean excludeDefaultInterceptorsESet;

	/**
	 * The default value of the '{@link #isExcludeClassInterceptors() <em>Exclude Class Interceptors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExcludeClassInterceptors()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EXCLUDE_CLASS_INTERCEPTORS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isExcludeClassInterceptors() <em>Exclude Class Interceptors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExcludeClassInterceptors()
	 * @generated
	 * @ordered
	 */
	protected boolean excludeClassInterceptors = EXCLUDE_CLASS_INTERCEPTORS_EDEFAULT;

	/**
	 * This is true if the Exclude Class Interceptors attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean excludeClassInterceptorsESet;

	/**
	 * The cached value of the '{@link #getMethod() <em>Method</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethod()
	 * @generated
	 * @ordered
	 */
	protected NamedMethodType method;

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
	protected InterceptorBindingTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EjbPackage.Literals.INTERCEPTOR_BINDING_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Description> getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList<Description>(Description.class, this, EjbPackage.INTERCEPTOR_BINDING_TYPE__DESCRIPTIONS);
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
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.INTERCEPTOR_BINDING_TYPE__EJB_NAME, oldEjbName, ejbName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<String> getInterceptorClasses() {
		if (interceptorClasses == null) {
			interceptorClasses = new EDataTypeEList<String>(String.class, this, EjbPackage.INTERCEPTOR_BINDING_TYPE__INTERCEPTOR_CLASSES);
		}
		return interceptorClasses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InterceptorOrderType getInterceptorOrder() {
		return interceptorOrder;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInterceptorOrder(InterceptorOrderType newInterceptorOrder, NotificationChain msgs) {
		InterceptorOrderType oldInterceptorOrder = interceptorOrder;
		interceptorOrder = newInterceptorOrder;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.INTERCEPTOR_BINDING_TYPE__INTERCEPTOR_ORDER, oldInterceptorOrder, newInterceptorOrder);
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
	public void setInterceptorOrder(InterceptorOrderType newInterceptorOrder) {
		if (newInterceptorOrder != interceptorOrder) {
			NotificationChain msgs = null;
			if (interceptorOrder != null)
				msgs = ((InternalEObject)interceptorOrder).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.INTERCEPTOR_BINDING_TYPE__INTERCEPTOR_ORDER, null, msgs);
			if (newInterceptorOrder != null)
				msgs = ((InternalEObject)newInterceptorOrder).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EjbPackage.INTERCEPTOR_BINDING_TYPE__INTERCEPTOR_ORDER, null, msgs);
			msgs = basicSetInterceptorOrder(newInterceptorOrder, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.INTERCEPTOR_BINDING_TYPE__INTERCEPTOR_ORDER, newInterceptorOrder, newInterceptorOrder));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isExcludeDefaultInterceptors() {
		return excludeDefaultInterceptors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setExcludeDefaultInterceptors(boolean newExcludeDefaultInterceptors) {
		boolean oldExcludeDefaultInterceptors = excludeDefaultInterceptors;
		excludeDefaultInterceptors = newExcludeDefaultInterceptors;
		boolean oldExcludeDefaultInterceptorsESet = excludeDefaultInterceptorsESet;
		excludeDefaultInterceptorsESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.INTERCEPTOR_BINDING_TYPE__EXCLUDE_DEFAULT_INTERCEPTORS, oldExcludeDefaultInterceptors, excludeDefaultInterceptors, !oldExcludeDefaultInterceptorsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetExcludeDefaultInterceptors() {
		boolean oldExcludeDefaultInterceptors = excludeDefaultInterceptors;
		boolean oldExcludeDefaultInterceptorsESet = excludeDefaultInterceptorsESet;
		excludeDefaultInterceptors = EXCLUDE_DEFAULT_INTERCEPTORS_EDEFAULT;
		excludeDefaultInterceptorsESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, EjbPackage.INTERCEPTOR_BINDING_TYPE__EXCLUDE_DEFAULT_INTERCEPTORS, oldExcludeDefaultInterceptors, EXCLUDE_DEFAULT_INTERCEPTORS_EDEFAULT, oldExcludeDefaultInterceptorsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetExcludeDefaultInterceptors() {
		return excludeDefaultInterceptorsESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isExcludeClassInterceptors() {
		return excludeClassInterceptors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setExcludeClassInterceptors(boolean newExcludeClassInterceptors) {
		boolean oldExcludeClassInterceptors = excludeClassInterceptors;
		excludeClassInterceptors = newExcludeClassInterceptors;
		boolean oldExcludeClassInterceptorsESet = excludeClassInterceptorsESet;
		excludeClassInterceptorsESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.INTERCEPTOR_BINDING_TYPE__EXCLUDE_CLASS_INTERCEPTORS, oldExcludeClassInterceptors, excludeClassInterceptors, !oldExcludeClassInterceptorsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetExcludeClassInterceptors() {
		boolean oldExcludeClassInterceptors = excludeClassInterceptors;
		boolean oldExcludeClassInterceptorsESet = excludeClassInterceptorsESet;
		excludeClassInterceptors = EXCLUDE_CLASS_INTERCEPTORS_EDEFAULT;
		excludeClassInterceptorsESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, EjbPackage.INTERCEPTOR_BINDING_TYPE__EXCLUDE_CLASS_INTERCEPTORS, oldExcludeClassInterceptors, EXCLUDE_CLASS_INTERCEPTORS_EDEFAULT, oldExcludeClassInterceptorsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetExcludeClassInterceptors() {
		return excludeClassInterceptorsESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NamedMethodType getMethod() {
		return method;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMethod(NamedMethodType newMethod, NotificationChain msgs) {
		NamedMethodType oldMethod = method;
		method = newMethod;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.INTERCEPTOR_BINDING_TYPE__METHOD, oldMethod, newMethod);
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
	public void setMethod(NamedMethodType newMethod) {
		if (newMethod != method) {
			NotificationChain msgs = null;
			if (method != null)
				msgs = ((InternalEObject)method).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.INTERCEPTOR_BINDING_TYPE__METHOD, null, msgs);
			if (newMethod != null)
				msgs = ((InternalEObject)newMethod).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EjbPackage.INTERCEPTOR_BINDING_TYPE__METHOD, null, msgs);
			msgs = basicSetMethod(newMethod, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.INTERCEPTOR_BINDING_TYPE__METHOD, newMethod, newMethod));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.INTERCEPTOR_BINDING_TYPE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EjbPackage.INTERCEPTOR_BINDING_TYPE__DESCRIPTIONS:
				return ((InternalEList<?>)getDescriptions()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_BINDING_TYPE__INTERCEPTOR_ORDER:
				return basicSetInterceptorOrder(null, msgs);
			case EjbPackage.INTERCEPTOR_BINDING_TYPE__METHOD:
				return basicSetMethod(null, msgs);
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
			case EjbPackage.INTERCEPTOR_BINDING_TYPE__DESCRIPTIONS:
				return getDescriptions();
			case EjbPackage.INTERCEPTOR_BINDING_TYPE__EJB_NAME:
				return getEjbName();
			case EjbPackage.INTERCEPTOR_BINDING_TYPE__INTERCEPTOR_CLASSES:
				return getInterceptorClasses();
			case EjbPackage.INTERCEPTOR_BINDING_TYPE__INTERCEPTOR_ORDER:
				return getInterceptorOrder();
			case EjbPackage.INTERCEPTOR_BINDING_TYPE__EXCLUDE_DEFAULT_INTERCEPTORS:
				return isExcludeDefaultInterceptors();
			case EjbPackage.INTERCEPTOR_BINDING_TYPE__EXCLUDE_CLASS_INTERCEPTORS:
				return isExcludeClassInterceptors();
			case EjbPackage.INTERCEPTOR_BINDING_TYPE__METHOD:
				return getMethod();
			case EjbPackage.INTERCEPTOR_BINDING_TYPE__ID:
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
			case EjbPackage.INTERCEPTOR_BINDING_TYPE__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection<? extends Description>)newValue);
				return;
			case EjbPackage.INTERCEPTOR_BINDING_TYPE__EJB_NAME:
				setEjbName((String)newValue);
				return;
			case EjbPackage.INTERCEPTOR_BINDING_TYPE__INTERCEPTOR_CLASSES:
				getInterceptorClasses().clear();
				getInterceptorClasses().addAll((Collection<? extends String>)newValue);
				return;
			case EjbPackage.INTERCEPTOR_BINDING_TYPE__INTERCEPTOR_ORDER:
				setInterceptorOrder((InterceptorOrderType)newValue);
				return;
			case EjbPackage.INTERCEPTOR_BINDING_TYPE__EXCLUDE_DEFAULT_INTERCEPTORS:
				setExcludeDefaultInterceptors((Boolean)newValue);
				return;
			case EjbPackage.INTERCEPTOR_BINDING_TYPE__EXCLUDE_CLASS_INTERCEPTORS:
				setExcludeClassInterceptors((Boolean)newValue);
				return;
			case EjbPackage.INTERCEPTOR_BINDING_TYPE__METHOD:
				setMethod((NamedMethodType)newValue);
				return;
			case EjbPackage.INTERCEPTOR_BINDING_TYPE__ID:
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
			case EjbPackage.INTERCEPTOR_BINDING_TYPE__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case EjbPackage.INTERCEPTOR_BINDING_TYPE__EJB_NAME:
				setEjbName(EJB_NAME_EDEFAULT);
				return;
			case EjbPackage.INTERCEPTOR_BINDING_TYPE__INTERCEPTOR_CLASSES:
				getInterceptorClasses().clear();
				return;
			case EjbPackage.INTERCEPTOR_BINDING_TYPE__INTERCEPTOR_ORDER:
				setInterceptorOrder((InterceptorOrderType)null);
				return;
			case EjbPackage.INTERCEPTOR_BINDING_TYPE__EXCLUDE_DEFAULT_INTERCEPTORS:
				unsetExcludeDefaultInterceptors();
				return;
			case EjbPackage.INTERCEPTOR_BINDING_TYPE__EXCLUDE_CLASS_INTERCEPTORS:
				unsetExcludeClassInterceptors();
				return;
			case EjbPackage.INTERCEPTOR_BINDING_TYPE__METHOD:
				setMethod((NamedMethodType)null);
				return;
			case EjbPackage.INTERCEPTOR_BINDING_TYPE__ID:
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
			case EjbPackage.INTERCEPTOR_BINDING_TYPE__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case EjbPackage.INTERCEPTOR_BINDING_TYPE__EJB_NAME:
				return EJB_NAME_EDEFAULT == null ? ejbName != null : !EJB_NAME_EDEFAULT.equals(ejbName);
			case EjbPackage.INTERCEPTOR_BINDING_TYPE__INTERCEPTOR_CLASSES:
				return interceptorClasses != null && !interceptorClasses.isEmpty();
			case EjbPackage.INTERCEPTOR_BINDING_TYPE__INTERCEPTOR_ORDER:
				return interceptorOrder != null;
			case EjbPackage.INTERCEPTOR_BINDING_TYPE__EXCLUDE_DEFAULT_INTERCEPTORS:
				return isSetExcludeDefaultInterceptors();
			case EjbPackage.INTERCEPTOR_BINDING_TYPE__EXCLUDE_CLASS_INTERCEPTORS:
				return isSetExcludeClassInterceptors();
			case EjbPackage.INTERCEPTOR_BINDING_TYPE__METHOD:
				return method != null;
			case EjbPackage.INTERCEPTOR_BINDING_TYPE__ID:
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
		result.append(", interceptorClasses: "); //$NON-NLS-1$
		result.append(interceptorClasses);
		result.append(", excludeDefaultInterceptors: "); //$NON-NLS-1$
		if (excludeDefaultInterceptorsESet) result.append(excludeDefaultInterceptors); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", excludeClassInterceptors: "); //$NON-NLS-1$
		if (excludeClassInterceptorsESet) result.append(excludeClassInterceptors); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //InterceptorBindingTypeImpl