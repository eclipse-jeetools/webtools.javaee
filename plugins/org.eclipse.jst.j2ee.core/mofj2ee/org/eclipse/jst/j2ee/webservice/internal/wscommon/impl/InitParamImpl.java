/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.webservice.internal.wscommon.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.webservice.wscommon.DescriptionType;
import org.eclipse.jst.j2ee.webservice.wscommon.InitParam;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Init Param</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.wscommon.impl.InitParamImpl#getParamName <em>Param Name</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.wscommon.impl.InitParamImpl#getParamValue <em>Param Value</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.wscommon.impl.InitParamImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.wscommon.impl.InitParamImpl#getDescriptionTypes <em>Description Types</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InitParamImpl extends EObjectImpl implements InitParam
{
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
	 * The cached value of the '{@link #getParamName() <em>Param Name</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getParamName()
	 * @generated
	 * @ordered
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
	 * The cached value of the '{@link #getParamValue() <em>Param Value</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getParamValue()
	 * @generated
	 * @ordered
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
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
  protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDescriptionTypes() <em>Description Types</em>}' containment reference list.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getDescriptionTypes()
	 * @generated
	 * @ordered
	 */
  protected EList descriptionTypes = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected InitParamImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected EClass eStaticClass() {
		return WscommonPackage.eINSTANCE.getInitParam();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public String getParamName() {
		return paramName;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setParamName(String newParamName) {
		String oldParamName = paramName;
		paramName = newParamName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WscommonPackage.INIT_PARAM__PARAM_NAME, oldParamName, paramName));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public String getParamValue() {
		return paramValue;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setParamValue(String newParamValue) {
		String oldParamValue = paramValue;
		paramValue = newParamValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WscommonPackage.INIT_PARAM__PARAM_VALUE, oldParamValue, paramValue));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WscommonPackage.INIT_PARAM__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EList getDescriptionTypes() {
		if (descriptionTypes == null) {
			descriptionTypes = new EObjectContainmentEList(DescriptionType.class, this, WscommonPackage.INIT_PARAM__DESCRIPTION_TYPES);
		}
		return descriptionTypes;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case WscommonPackage.INIT_PARAM__DESCRIPTION_TYPES:
					return ((InternalEList)getDescriptionTypes()).basicRemove(otherEnd, msgs);
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
  public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case WscommonPackage.INIT_PARAM__PARAM_NAME:
				return getParamName();
			case WscommonPackage.INIT_PARAM__PARAM_VALUE:
				return getParamValue();
			case WscommonPackage.INIT_PARAM__DESCRIPTION:
				return getDescription();
			case WscommonPackage.INIT_PARAM__DESCRIPTION_TYPES:
				return getDescriptionTypes();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case WscommonPackage.INIT_PARAM__PARAM_NAME:
				setParamName((String)newValue);
				return;
			case WscommonPackage.INIT_PARAM__PARAM_VALUE:
				setParamValue((String)newValue);
				return;
			case WscommonPackage.INIT_PARAM__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case WscommonPackage.INIT_PARAM__DESCRIPTION_TYPES:
				getDescriptionTypes().clear();
				getDescriptionTypes().addAll((Collection)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case WscommonPackage.INIT_PARAM__PARAM_NAME:
				setParamName(PARAM_NAME_EDEFAULT);
				return;
			case WscommonPackage.INIT_PARAM__PARAM_VALUE:
				setParamValue(PARAM_VALUE_EDEFAULT);
				return;
			case WscommonPackage.INIT_PARAM__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case WscommonPackage.INIT_PARAM__DESCRIPTION_TYPES:
				getDescriptionTypes().clear();
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case WscommonPackage.INIT_PARAM__PARAM_NAME:
				return PARAM_NAME_EDEFAULT == null ? paramName != null : !PARAM_NAME_EDEFAULT.equals(paramName);
			case WscommonPackage.INIT_PARAM__PARAM_VALUE:
				return PARAM_VALUE_EDEFAULT == null ? paramValue != null : !PARAM_VALUE_EDEFAULT.equals(paramValue);
			case WscommonPackage.INIT_PARAM__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case WscommonPackage.INIT_PARAM__DESCRIPTION_TYPES:
				return descriptionTypes != null && !descriptionTypes.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (paramName: ");//$NON-NLS-1$
		result.append(paramName);
		result.append(", paramValue: ");//$NON-NLS-1$
		result.append(paramValue);
		result.append(", description: ");//$NON-NLS-1$
		result.append(description);
		result.append(')');
		return result.toString();
	}

} //InitParamImpl
