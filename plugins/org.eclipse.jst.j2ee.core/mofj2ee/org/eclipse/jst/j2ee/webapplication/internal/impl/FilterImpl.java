/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.webapplication.internal.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jst.j2ee.common.ParamValue;
import org.eclipse.jst.j2ee.common.internal.impl.CompatibilityDescriptionGroupImpl;
import org.eclipse.jst.j2ee.webapplication.Filter;
import org.eclipse.jst.j2ee.webapplication.InitParam;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;

/**
 * Declares a filter in the web application application. The filter is mapped to either a servlet or a URL pattern in the filter-mapping element, using the filter-name value to reference. Filters can access the initialization parameters declared in the deployment descriptor at runtime via the FilterConfig interface.

 */
public class FilterImpl extends CompatibilityDescriptionGroupImpl implements Filter {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String name = NAME_EDEFAULT;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList initParams = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected JavaClass filterClass = null;
	/**
	 * The cached value of the '{@link #getInitParamValues() <em>Init Param Values</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitParamValues()
	 * @generated
	 * @ordered
	 */
	protected EList initParamValues = null;

		/**
		 * @generated This field/method will be replaced during code generation.
		 */
	protected FilterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WebapplicationPackage.Literals.FILTER;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The logical name of the filter. This name is used to map the filter.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.FILTER__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * getInitParams() is used for J2EE1.3
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getInitParams() {
		if (initParams == null) {
			initParams = new EObjectContainmentEList(InitParam.class, this, WebapplicationPackage.FILTER__INIT_PARAMS);
		}
		return initParams;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The fully qualified classname of the filter.
	 */
	public JavaClass getFilterClass() {
		if (filterClass != null && filterClass.eIsProxy()) {
			InternalEObject oldFilterClass = (InternalEObject)filterClass;
			filterClass = (JavaClass)eResolveProxy(oldFilterClass);
			if (filterClass != oldFilterClass) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, WebapplicationPackage.FILTER__FILTER_CLASS, oldFilterClass, filterClass));
			}
		}
		return filterClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass basicGetFilterClass() {
		return filterClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setFilterClass(JavaClass newFilterClass) {
		JavaClass oldFilterClass = filterClass;
		filterClass = newFilterClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.FILTER__FILTER_CLASS, oldFilterClass, filterClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * getInitParamValues() is used for J2EE1.4
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getInitParamValues() {
		if (initParamValues == null) {
			initParamValues = new EObjectContainmentEList(ParamValue.class, this, WebapplicationPackage.FILTER__INIT_PARAM_VALUES);
		}
		return initParamValues;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WebapplicationPackage.FILTER__INIT_PARAMS:
				return ((InternalEList)getInitParams()).basicRemove(otherEnd, msgs);
			case WebapplicationPackage.FILTER__INIT_PARAM_VALUES:
				return ((InternalEList)getInitParamValues()).basicRemove(otherEnd, msgs);
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
			case WebapplicationPackage.FILTER__NAME:
				return getName();
			case WebapplicationPackage.FILTER__INIT_PARAMS:
				return getInitParams();
			case WebapplicationPackage.FILTER__FILTER_CLASS:
				if (resolve) return getFilterClass();
				return basicGetFilterClass();
			case WebapplicationPackage.FILTER__INIT_PARAM_VALUES:
				return getInitParamValues();
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
			case WebapplicationPackage.FILTER__NAME:
				setName((String)newValue);
				return;
			case WebapplicationPackage.FILTER__INIT_PARAMS:
				getInitParams().clear();
				getInitParams().addAll((Collection)newValue);
				return;
			case WebapplicationPackage.FILTER__FILTER_CLASS:
				setFilterClass((JavaClass)newValue);
				return;
			case WebapplicationPackage.FILTER__INIT_PARAM_VALUES:
				getInitParamValues().clear();
				getInitParamValues().addAll((Collection)newValue);
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
			case WebapplicationPackage.FILTER__NAME:
				setName(NAME_EDEFAULT);
				return;
			case WebapplicationPackage.FILTER__INIT_PARAMS:
				getInitParams().clear();
				return;
			case WebapplicationPackage.FILTER__FILTER_CLASS:
				setFilterClass((JavaClass)null);
				return;
			case WebapplicationPackage.FILTER__INIT_PARAM_VALUES:
				getInitParamValues().clear();
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
			case WebapplicationPackage.FILTER__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case WebapplicationPackage.FILTER__INIT_PARAMS:
				return initParams != null && !initParams.isEmpty();
			case WebapplicationPackage.FILTER__FILTER_CLASS:
				return filterClass != null;
			case WebapplicationPackage.FILTER__INIT_PARAM_VALUES:
				return initParamValues != null && !initParamValues.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: "); //$NON-NLS-1$
		result.append(name);
		result.append(')');
		return result.toString();
	}

public JavaClass createClassRef(String targetName) {
	return JavaRefFactory.eINSTANCE.createClassRef(targetName);
}


public String getFilterClassName() {
	getFilterClass();
	return (filterClass == null) ? null : filterClass.getQualifiedName();
}
public void setFilterClassName(String filterClassName) {
	eSet(WebapplicationPackage.eINSTANCE.getFilter_FilterClass(), createClassRef(filterClassName));
}
} //FilterImpl















