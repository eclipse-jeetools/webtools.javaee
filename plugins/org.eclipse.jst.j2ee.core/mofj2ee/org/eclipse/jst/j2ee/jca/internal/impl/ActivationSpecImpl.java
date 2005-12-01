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
package org.eclipse.jst.j2ee.jca.internal.impl;

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
import org.eclipse.jst.j2ee.jca.ActivationSpec;
import org.eclipse.jst.j2ee.jca.JcaPackage;
import org.eclipse.jst.j2ee.jca.RequiredConfigPropertyType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Activation Spec</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.jca.internal.impl.ActivationSpecImpl#getActivationSpecClass <em>Activation Spec Class</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.jca.internal.impl.ActivationSpecImpl#getRequiredConfigProperties <em>Required Config Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActivationSpecImpl extends EObjectImpl implements ActivationSpec {
	/**
	 * The default value of the '{@link #getActivationSpecClass() <em>Activation Spec Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivationSpecClass()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTIVATION_SPEC_CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActivationSpecClass() <em>Activation Spec Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivationSpecClass()
	 * @generated
	 * @ordered
	 */
	protected String activationSpecClass = ACTIVATION_SPEC_CLASS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRequiredConfigProperties() <em>Required Config Properties</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequiredConfigProperties()
	 * @generated
	 * @ordered
	 */
	protected EList requiredConfigProperties = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActivationSpecImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return JcaPackage.eINSTANCE.getActivationSpec();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getActivationSpecClass() {
		return activationSpecClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActivationSpecClass(String newActivationSpecClass) {
		String oldActivationSpecClass = activationSpecClass;
		activationSpecClass = newActivationSpecClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.ACTIVATION_SPEC__ACTIVATION_SPEC_CLASS, oldActivationSpecClass, activationSpecClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getRequiredConfigProperties() {
		if (requiredConfigProperties == null) {
			requiredConfigProperties = new EObjectContainmentEList(RequiredConfigPropertyType.class, this, JcaPackage.ACTIVATION_SPEC__REQUIRED_CONFIG_PROPERTIES);
		}
		return requiredConfigProperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case JcaPackage.ACTIVATION_SPEC__REQUIRED_CONFIG_PROPERTIES:
					return ((InternalEList)getRequiredConfigProperties()).basicRemove(otherEnd, msgs);
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
			case JcaPackage.ACTIVATION_SPEC__ACTIVATION_SPEC_CLASS:
				return getActivationSpecClass();
			case JcaPackage.ACTIVATION_SPEC__REQUIRED_CONFIG_PROPERTIES:
				return getRequiredConfigProperties();
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
			case JcaPackage.ACTIVATION_SPEC__ACTIVATION_SPEC_CLASS:
				setActivationSpecClass((String)newValue);
				return;
			case JcaPackage.ACTIVATION_SPEC__REQUIRED_CONFIG_PROPERTIES:
				getRequiredConfigProperties().clear();
				getRequiredConfigProperties().addAll((Collection)newValue);
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
			case JcaPackage.ACTIVATION_SPEC__ACTIVATION_SPEC_CLASS:
				setActivationSpecClass(ACTIVATION_SPEC_CLASS_EDEFAULT);
				return;
			case JcaPackage.ACTIVATION_SPEC__REQUIRED_CONFIG_PROPERTIES:
				getRequiredConfigProperties().clear();
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
			case JcaPackage.ACTIVATION_SPEC__ACTIVATION_SPEC_CLASS:
				return ACTIVATION_SPEC_CLASS_EDEFAULT == null ? activationSpecClass != null : !ACTIVATION_SPEC_CLASS_EDEFAULT.equals(activationSpecClass);
			case JcaPackage.ACTIVATION_SPEC__REQUIRED_CONFIG_PROPERTIES:
				return requiredConfigProperties != null && !requiredConfigProperties.isEmpty();
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
		result.append(" (activationSpecClass: ");
		result.append(activationSpecClass);
		result.append(')');
		return result.toString();
	}

} //ActivationSpecImpl
