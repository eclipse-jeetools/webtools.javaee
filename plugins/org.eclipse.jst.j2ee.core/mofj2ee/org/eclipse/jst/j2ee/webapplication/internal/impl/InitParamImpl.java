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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jst.j2ee.common.internal.impl.J2EEEObjectImpl;
import org.eclipse.jst.j2ee.webapplication.InitParam;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;

/**
 * The init-param element contains a name//value pair as an initialization param of the servlet
 * @generated
 */
public class InitParamImpl extends J2EEEObjectImpl implements InitParam {

	/**
	 * The default value of the '{@link #getParamName() <em>Param Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParamName()
	 * @generated
	 * @ordered
	 */
	protected static final String PARAM_NAME_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String paramName = PARAM_NAME_EDEFAULT;
	/**
	 * The default value of the '{@link #getParamValue() <em>Param Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParamValue()
	 * @generated
	 * @ordered
	 */
	protected static final String PARAM_VALUE_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String paramValue = PARAM_VALUE_EDEFAULT;
	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String description = DESCRIPTION_EDEFAULT;
	public InitParamImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WebapplicationPackage.Literals.INIT_PARAM;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * 
	 * 

	 */
	public String getParamName() {
		return paramName;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setParamName(String newParamName) {
		String oldParamName = paramName;
		paramName = newParamName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.INIT_PARAM__PARAM_NAME, oldParamName, paramName));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public String getParamValue() {
		return paramValue;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setParamValue(String newParamValue) {
		String oldParamValue = paramValue;
		paramValue = newParamValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.INIT_PARAM__PARAM_VALUE, oldParamValue, paramValue));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.INIT_PARAM__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WebapplicationPackage.INIT_PARAM__PARAM_NAME:
				return getParamName();
			case WebapplicationPackage.INIT_PARAM__PARAM_VALUE:
				return getParamValue();
			case WebapplicationPackage.INIT_PARAM__DESCRIPTION:
				return getDescription();
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
			case WebapplicationPackage.INIT_PARAM__PARAM_NAME:
				setParamName((String)newValue);
				return;
			case WebapplicationPackage.INIT_PARAM__PARAM_VALUE:
				setParamValue((String)newValue);
				return;
			case WebapplicationPackage.INIT_PARAM__DESCRIPTION:
				setDescription((String)newValue);
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
			case WebapplicationPackage.INIT_PARAM__PARAM_NAME:
				setParamName(PARAM_NAME_EDEFAULT);
				return;
			case WebapplicationPackage.INIT_PARAM__PARAM_VALUE:
				setParamValue(PARAM_VALUE_EDEFAULT);
				return;
			case WebapplicationPackage.INIT_PARAM__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
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
			case WebapplicationPackage.INIT_PARAM__PARAM_NAME:
				return PARAM_NAME_EDEFAULT == null ? paramName != null : !PARAM_NAME_EDEFAULT.equals(paramName);
			case WebapplicationPackage.INIT_PARAM__PARAM_VALUE:
				return PARAM_VALUE_EDEFAULT == null ? paramValue != null : !PARAM_VALUE_EDEFAULT.equals(paramValue);
			case WebapplicationPackage.INIT_PARAM__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
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
		result.append(" (paramName: "); //$NON-NLS-1$
		result.append(paramName);
		result.append(", paramValue: "); //$NON-NLS-1$
		result.append(paramValue);
		result.append(", description: "); //$NON-NLS-1$
		result.append(description);
		result.append(')');
		return result.toString();
	}

}














