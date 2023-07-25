/*******************************************************************************
 * Copyright (c) 2001, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.common.internal.impl.J2EEEObjectImpl;
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
public class ActivationSpecImpl extends J2EEEObjectImpl implements ActivationSpec {
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
	@Override
	protected EClass eStaticClass() {
		return JcaPackage.Literals.ACTIVATION_SPEC;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getActivationSpecClass() {
		return activationSpecClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
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
	@Override
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
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JcaPackage.ACTIVATION_SPEC__REQUIRED_CONFIG_PROPERTIES:
				return ((InternalEList)getRequiredConfigProperties()).basicRemove(otherEnd, msgs);
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
			case JcaPackage.ACTIVATION_SPEC__ACTIVATION_SPEC_CLASS:
				return getActivationSpecClass();
			case JcaPackage.ACTIVATION_SPEC__REQUIRED_CONFIG_PROPERTIES:
				return getRequiredConfigProperties();
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
			case JcaPackage.ACTIVATION_SPEC__ACTIVATION_SPEC_CLASS:
				setActivationSpecClass((String)newValue);
				return;
			case JcaPackage.ACTIVATION_SPEC__REQUIRED_CONFIG_PROPERTIES:
				getRequiredConfigProperties().clear();
				getRequiredConfigProperties().addAll((Collection)newValue);
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
			case JcaPackage.ACTIVATION_SPEC__ACTIVATION_SPEC_CLASS:
				setActivationSpecClass(ACTIVATION_SPEC_CLASS_EDEFAULT);
				return;
			case JcaPackage.ACTIVATION_SPEC__REQUIRED_CONFIG_PROPERTIES:
				getRequiredConfigProperties().clear();
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
			case JcaPackage.ACTIVATION_SPEC__ACTIVATION_SPEC_CLASS:
				return ACTIVATION_SPEC_CLASS_EDEFAULT == null ? activationSpecClass != null : !ACTIVATION_SPEC_CLASS_EDEFAULT.equals(activationSpecClass);
			case JcaPackage.ACTIVATION_SPEC__REQUIRED_CONFIG_PROPERTIES:
				return requiredConfigProperties != null && !requiredConfigProperties.isEmpty();
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
		result.append(" (activationSpecClass: "); //$NON-NLS-1$
		result.append(activationSpecClass);
		result.append(')');
		return result.toString();
	}

} //ActivationSpecImpl
