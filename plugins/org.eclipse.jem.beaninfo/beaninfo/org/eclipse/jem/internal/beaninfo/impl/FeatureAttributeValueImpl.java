package org.eclipse.jem.internal.beaninfo.impl;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: FeatureAttributeValueImpl.java,v $
 *  $Revision: 1.2 $  $Date: 2004/01/13 16:17:00 $ 
 */


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.jem.internal.beaninfo.BeaninfoPackage;
import org.eclipse.jem.internal.beaninfo.FeatureAttributeValue;
/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature Attribute Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.FeatureAttributeValueImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.FeatureAttributeValueImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.FeatureAttributeValueImpl#getValueProxy <em>Value Proxy</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */


public class FeatureAttributeValueImpl extends EObjectImpl implements FeatureAttributeValue{
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
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;
	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected EObject value = null;
	
	/**
	 * The default value of the '{@link #getValueProxy() <em>Value Proxy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValueProxy()
	 * @generated
	 * @ordered
	 */
	protected static final Object VALUE_PROXY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValueProxy() <em>Value Proxy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValueProxy()
	 * @generated
	 * @ordered
	 */
	protected Object valueProxy = VALUE_PROXY_EDEFAULT;
	/**
	 * This is true if the Value Proxy attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean valueProxyESet = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */	
	protected FeatureAttributeValueImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return BeaninfoPackage.eINSTANCE.getFeatureAttributeValue();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetValue(EObject newValue, NotificationChain msgs) {
		EObject oldValue = value;
		value = newValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE__VALUE, oldValue, newValue);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(EObject newValue) {
		if (newValue != value) {
			NotificationChain msgs = null;
			if (value != null)
				msgs = ((InternalEObject)value).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE__VALUE, null, msgs);
			if (newValue != null)
				msgs = ((InternalEObject)newValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE__VALUE, null, msgs);
			msgs = basicSetValue(newValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE__VALUE, newValue, newValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE__VALUE:
					return basicSetValue(null, msgs);
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
			case BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE__NAME:
				return getName();
			case BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE__VALUE:
				return getValue();
			case BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE__VALUE_PROXY:
				return getValueProxy();
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
			case BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE__NAME:
				setName((String)newValue);
				return;
			case BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE__VALUE:
				setValue((EObject)newValue);
				return;
			case BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE__VALUE_PROXY:
				setValueProxy((Object)newValue);
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
			case BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE__VALUE:
				setValue((EObject)null);
				return;
			case BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE__VALUE_PROXY:
				unsetValueProxy();
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
			case BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE__VALUE:
				return value != null;
			case BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE__VALUE_PROXY:
				return isSetValueProxy();
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
		result.append(" (name: ");
		result.append(name);
		result.append(", valueProxy: ");
		if (valueProxyESet) result.append(valueProxy); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getValueProxy() {
		return valueProxy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValueProxy(Object newValueProxy) {
		Object oldValueProxy = valueProxy;
		valueProxy = newValueProxy;
		boolean oldValueProxyESet = valueProxyESet;
		valueProxyESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE__VALUE_PROXY, oldValueProxy, valueProxy, !oldValueProxyESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetValueProxy() {
		Object oldValueProxy = valueProxy;
		boolean oldValueProxyESet = valueProxyESet;
		valueProxy = VALUE_PROXY_EDEFAULT;
		valueProxyESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE__VALUE_PROXY, oldValueProxy, VALUE_PROXY_EDEFAULT, oldValueProxyESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetValueProxy() {
		return valueProxyESet;
	}

}
