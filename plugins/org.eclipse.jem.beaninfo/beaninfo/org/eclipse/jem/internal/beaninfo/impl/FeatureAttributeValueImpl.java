/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.beaninfo.impl;
/*
 *  $RCSfile: FeatureAttributeValueImpl.java,v $
 *  $Revision: 1.4 $  $Date: 2004/08/27 15:33:31 $ 
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
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.FeatureAttributeValueImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.FeatureAttributeValueImpl#getValueJava <em>Value Java</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.FeatureAttributeValueImpl#getValueProxy <em>Value Proxy</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */


public class FeatureAttributeValueImpl extends EObjectImpl implements FeatureAttributeValue{
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
	 * This is true if the Value containment reference has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean valueESet = false;

	/**
	 * The default value of the '{@link #getValueJava() <em>Value Java</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValueJava()
	 * @generated
	 * @ordered
	 */
	protected static final Object VALUE_JAVA_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValueJava() <em>Value Java</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValueJava()
	 * @generated
	 * @ordered
	 */
	protected Object valueJava = VALUE_JAVA_EDEFAULT;

	/**
	 * This is true if the Value Java attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean valueJavaESet = false;

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
		boolean oldValueESet = valueESet;
		valueESet = true;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE__VALUE, oldValue, newValue, !oldValueESet);
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
		else {
			boolean oldValueESet = valueESet;
			valueESet = true;
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE__VALUE, newValue, newValue, !oldValueESet));
    	}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicUnsetValue(NotificationChain msgs) {
		EObject oldValue = value;
		value = null;
		boolean oldValueESet = valueESet;
		valueESet = false;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.UNSET, BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE__VALUE, oldValue, null, oldValueESet);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetValue() {
		if (value != null) {
			NotificationChain msgs = null;
			msgs = ((InternalEObject)value).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE__VALUE, null, msgs);
			msgs = basicUnsetValue(msgs);
			if (msgs != null) msgs.dispatch();
		}
		else {
			boolean oldValueESet = valueESet;
			valueESet = false;
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.UNSET, BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE__VALUE, null, null, oldValueESet));
    	}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetValue() {
		return valueESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getValueJava() {
		return valueJava;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValueJava(Object newValueJava) {
		Object oldValueJava = valueJava;
		valueJava = newValueJava;
		boolean oldValueJavaESet = valueJavaESet;
		valueJavaESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE__VALUE_JAVA, oldValueJava, valueJava, !oldValueJavaESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetValueJava() {
		Object oldValueJava = valueJava;
		boolean oldValueJavaESet = valueJavaESet;
		valueJava = VALUE_JAVA_EDEFAULT;
		valueJavaESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE__VALUE_JAVA, oldValueJava, VALUE_JAVA_EDEFAULT, oldValueJavaESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetValueJava() {
		return valueJavaESet;
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
					return basicUnsetValue(msgs);
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
			case BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE__VALUE:
				return getValue();
			case BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE__VALUE_JAVA:
				return getValueJava();
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
			case BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE__VALUE:
				setValue((EObject)newValue);
				return;
			case BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE__VALUE_JAVA:
				setValueJava((Object)newValue);
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
			case BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE__VALUE:
				unsetValue();
				return;
			case BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE__VALUE_JAVA:
				unsetValueJava();
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
			case BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE__VALUE:
				return isSetValue();
			case BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE__VALUE_JAVA:
				return isSetValueJava();
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
		result.append(" (valueJava: ");
		if (valueJavaESet) result.append(valueJava); else result.append("<unset>");
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

	public void setValueProxy(Object newValueProxy) {
		unsetValue();
		unsetValueJava();
		setValueProxyGen(newValueProxy);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValueProxyGen(Object newValueProxy) {
		Object oldValueProxy = valueProxy;
		valueProxy = newValueProxy;
		boolean oldValueProxyESet = valueProxyESet;
		valueProxyESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.FEATURE_ATTRIBUTE_VALUE__VALUE_PROXY, oldValueProxy, valueProxy, !oldValueProxyESet));
	}

	public void unsetValueProxy() {
		if (isSetValueProxy()) {
			unsetValue();
			unsetValueJava();
		}
		unsetValueProxyGen();
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetValueProxyGen() {
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
