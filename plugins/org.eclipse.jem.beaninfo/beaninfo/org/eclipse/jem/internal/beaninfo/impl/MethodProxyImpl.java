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
 *  $RCSfile: MethodProxyImpl.java,v $
 *  $Revision: 1.3 $  $Date: 2004/08/27 15:33:31 $ 
 */


import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EOperationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jem.internal.beaninfo.BeaninfoPackage;
import org.eclipse.jem.internal.beaninfo.MethodProxy;
import org.eclipse.jem.java.Method;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Method Proxy</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.MethodProxyImpl#getMethod <em>Method</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */


public class MethodProxyImpl extends EOperationImpl implements MethodProxy {
	/**
	 * The cached value of the '{@link #getMethod() <em>Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethod()
	 * @generated
	 * @ordered
	 */
	protected Method method = null;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */	
	protected MethodProxyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return BeaninfoPackage.eINSTANCE.getMethodProxy();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Method getMethod() {
		if (method != null && method.eIsProxy()) {
			Method oldMethod = method;
			method = (Method)eResolveProxy((InternalEObject)method);
			if (method != oldMethod) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BeaninfoPackage.METHOD_PROXY__METHOD, oldMethod, method));
			}
		}
		return method;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Method basicGetMethod() {
		return method;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMethod(Method newMethod) {
		Method oldMethod = method;
		method = newMethod;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.METHOD_PROXY__METHOD, oldMethod, method));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case BeaninfoPackage.METHOD_PROXY__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case BeaninfoPackage.METHOD_PROXY__ECONTAINING_CLASS:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, BeaninfoPackage.METHOD_PROXY__ECONTAINING_CLASS, msgs);
				case BeaninfoPackage.METHOD_PROXY__EPARAMETERS:
					return ((InternalEList)getEParameters()).basicAdd(otherEnd, msgs);
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
				case BeaninfoPackage.METHOD_PROXY__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case BeaninfoPackage.METHOD_PROXY__ECONTAINING_CLASS:
					return eBasicSetContainer(null, BeaninfoPackage.METHOD_PROXY__ECONTAINING_CLASS, msgs);
				case BeaninfoPackage.METHOD_PROXY__EPARAMETERS:
					return ((InternalEList)getEParameters()).basicRemove(otherEnd, msgs);
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
				case BeaninfoPackage.METHOD_PROXY__ECONTAINING_CLASS:
					return ((InternalEObject)eContainer).eInverseRemove(this, EcorePackage.ECLASS__EOPERATIONS, EClass.class, msgs);
				default:
					return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return ((InternalEObject)eContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case BeaninfoPackage.METHOD_PROXY__EANNOTATIONS:
				return getEAnnotations();
			case BeaninfoPackage.METHOD_PROXY__NAME:
				return getName();
			case BeaninfoPackage.METHOD_PROXY__ORDERED:
				return isOrdered() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.METHOD_PROXY__UNIQUE:
				return isUnique() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.METHOD_PROXY__LOWER_BOUND:
				return new Integer(getLowerBound());
			case BeaninfoPackage.METHOD_PROXY__UPPER_BOUND:
				return new Integer(getUpperBound());
			case BeaninfoPackage.METHOD_PROXY__MANY:
				return isMany() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.METHOD_PROXY__REQUIRED:
				return isRequired() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.METHOD_PROXY__ETYPE:
				if (resolve) return getEType();
				return basicGetEType();
			case BeaninfoPackage.METHOD_PROXY__ECONTAINING_CLASS:
				return getEContainingClass();
			case BeaninfoPackage.METHOD_PROXY__EPARAMETERS:
				return getEParameters();
			case BeaninfoPackage.METHOD_PROXY__EEXCEPTIONS:
				return getEExceptions();
			case BeaninfoPackage.METHOD_PROXY__METHOD:
				if (resolve) return getMethod();
				return basicGetMethod();
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
			case BeaninfoPackage.METHOD_PROXY__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.METHOD_PROXY__NAME:
				setName((String)newValue);
				return;
			case BeaninfoPackage.METHOD_PROXY__ORDERED:
				setOrdered(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.METHOD_PROXY__UNIQUE:
				setUnique(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.METHOD_PROXY__LOWER_BOUND:
				setLowerBound(((Integer)newValue).intValue());
				return;
			case BeaninfoPackage.METHOD_PROXY__UPPER_BOUND:
				setUpperBound(((Integer)newValue).intValue());
				return;
			case BeaninfoPackage.METHOD_PROXY__ETYPE:
				setEType((EClassifier)newValue);
				return;
			case BeaninfoPackage.METHOD_PROXY__EPARAMETERS:
				getEParameters().clear();
				getEParameters().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.METHOD_PROXY__EEXCEPTIONS:
				getEExceptions().clear();
				getEExceptions().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.METHOD_PROXY__METHOD:
				setMethod((Method)newValue);
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
			case BeaninfoPackage.METHOD_PROXY__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case BeaninfoPackage.METHOD_PROXY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case BeaninfoPackage.METHOD_PROXY__ORDERED:
				setOrdered(ORDERED_EDEFAULT);
				return;
			case BeaninfoPackage.METHOD_PROXY__UNIQUE:
				setUnique(UNIQUE_EDEFAULT);
				return;
			case BeaninfoPackage.METHOD_PROXY__LOWER_BOUND:
				setLowerBound(LOWER_BOUND_EDEFAULT);
				return;
			case BeaninfoPackage.METHOD_PROXY__UPPER_BOUND:
				setUpperBound(UPPER_BOUND_EDEFAULT);
				return;
			case BeaninfoPackage.METHOD_PROXY__ETYPE:
				setEType((EClassifier)null);
				return;
			case BeaninfoPackage.METHOD_PROXY__EPARAMETERS:
				getEParameters().clear();
				return;
			case BeaninfoPackage.METHOD_PROXY__EEXCEPTIONS:
				getEExceptions().clear();
				return;
			case BeaninfoPackage.METHOD_PROXY__METHOD:
				setMethod((Method)null);
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
			case BeaninfoPackage.METHOD_PROXY__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case BeaninfoPackage.METHOD_PROXY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case BeaninfoPackage.METHOD_PROXY__ORDERED:
				return ordered != ORDERED_EDEFAULT;
			case BeaninfoPackage.METHOD_PROXY__UNIQUE:
				return unique != UNIQUE_EDEFAULT;
			case BeaninfoPackage.METHOD_PROXY__LOWER_BOUND:
				return lowerBound != LOWER_BOUND_EDEFAULT;
			case BeaninfoPackage.METHOD_PROXY__UPPER_BOUND:
				return upperBound != UPPER_BOUND_EDEFAULT;
			case BeaninfoPackage.METHOD_PROXY__MANY:
				return isMany() != false;
			case BeaninfoPackage.METHOD_PROXY__REQUIRED:
				return isRequired() != false;
			case BeaninfoPackage.METHOD_PROXY__ETYPE:
				return eType != null;
			case BeaninfoPackage.METHOD_PROXY__ECONTAINING_CLASS:
				return getEContainingClass() != null;
			case BeaninfoPackage.METHOD_PROXY__EPARAMETERS:
				return eParameters != null && !eParameters.isEmpty();
			case BeaninfoPackage.METHOD_PROXY__EEXCEPTIONS:
				return eExceptions != null && !eExceptions.isEmpty();
			case BeaninfoPackage.METHOD_PROXY__METHOD:
				return method != null;
		}
		return eDynamicIsSet(eFeature);
	}

}
