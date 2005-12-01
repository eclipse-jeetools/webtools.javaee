/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.webapplication.internal.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jst.j2ee.webapplication.ContextParam;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;

/**
 * The context-param element contains the declaration of a web application's servlet context initialization parameters.
 */
public class ContextParamImpl extends EObjectImpl implements ContextParam, EObject {

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
	public ContextParamImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return WebapplicationPackage.eINSTANCE.getContextParam();
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The param-name element contains the name of a parameter.
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
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.CONTEXT_PARAM__PARAM_NAME, oldParamName, paramName));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The param-value element contains the value of a parameter.
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
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.CONTEXT_PARAM__PARAM_VALUE, oldParamValue, paramValue));
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
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.CONTEXT_PARAM__DESCRIPTION, oldDescription, description));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public WebApp getWebApp() {
		if (eContainerFeatureID != WebapplicationPackage.CONTEXT_PARAM__WEB_APP) return null;
		return (WebApp)eContainer;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setWebApp(WebApp newWebApp) {
		if (newWebApp != eContainer || (eContainerFeatureID != WebapplicationPackage.CONTEXT_PARAM__WEB_APP && newWebApp != null)) {
			if (EcoreUtil.isAncestor(this, newWebApp))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newWebApp != null)
				msgs = ((InternalEObject)newWebApp).eInverseAdd(this, WebapplicationPackage.WEB_APP__CONTEXTS, WebApp.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newWebApp, WebapplicationPackage.CONTEXT_PARAM__WEB_APP, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.CONTEXT_PARAM__WEB_APP, newWebApp, newWebApp));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case WebapplicationPackage.CONTEXT_PARAM__WEB_APP:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, WebapplicationPackage.CONTEXT_PARAM__WEB_APP, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case WebapplicationPackage.CONTEXT_PARAM__WEB_APP:
					return eBasicSetContainer(null, WebapplicationPackage.CONTEXT_PARAM__WEB_APP, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
		if (eContainerFeatureID >= 0) {
			switch (eContainerFeatureID) {
				case WebapplicationPackage.CONTEXT_PARAM__WEB_APP:
					return eContainer.eInverseRemove(this, WebapplicationPackage.WEB_APP__CONTEXTS, WebApp.class, msgs);
				default:
					return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case WebapplicationPackage.CONTEXT_PARAM__PARAM_NAME:
				return getParamName();
			case WebapplicationPackage.CONTEXT_PARAM__PARAM_VALUE:
				return getParamValue();
			case WebapplicationPackage.CONTEXT_PARAM__DESCRIPTION:
				return getDescription();
			case WebapplicationPackage.CONTEXT_PARAM__WEB_APP:
				return getWebApp();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case WebapplicationPackage.CONTEXT_PARAM__PARAM_NAME:
				return PARAM_NAME_EDEFAULT == null ? paramName != null : !PARAM_NAME_EDEFAULT.equals(paramName);
			case WebapplicationPackage.CONTEXT_PARAM__PARAM_VALUE:
				return PARAM_VALUE_EDEFAULT == null ? paramValue != null : !PARAM_VALUE_EDEFAULT.equals(paramValue);
			case WebapplicationPackage.CONTEXT_PARAM__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case WebapplicationPackage.CONTEXT_PARAM__WEB_APP:
				return getWebApp() != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case WebapplicationPackage.CONTEXT_PARAM__PARAM_NAME:
				setParamName((String)newValue);
				return;
			case WebapplicationPackage.CONTEXT_PARAM__PARAM_VALUE:
				setParamValue((String)newValue);
				return;
			case WebapplicationPackage.CONTEXT_PARAM__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case WebapplicationPackage.CONTEXT_PARAM__WEB_APP:
				setWebApp((WebApp)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case WebapplicationPackage.CONTEXT_PARAM__PARAM_NAME:
				setParamName(PARAM_NAME_EDEFAULT);
				return;
			case WebapplicationPackage.CONTEXT_PARAM__PARAM_VALUE:
				setParamValue(PARAM_VALUE_EDEFAULT);
				return;
			case WebapplicationPackage.CONTEXT_PARAM__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case WebapplicationPackage.CONTEXT_PARAM__WEB_APP:
				setWebApp((WebApp)null);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (paramName: ");
		result.append(paramName);
		result.append(", paramValue: ");
		result.append(paramValue);
		result.append(", description: ");
		result.append(description);
		result.append(')');
		return result.toString();
	}

}














