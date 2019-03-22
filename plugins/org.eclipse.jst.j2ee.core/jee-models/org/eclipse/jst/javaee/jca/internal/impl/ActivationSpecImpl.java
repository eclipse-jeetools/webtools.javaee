/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.jca.internal.impl;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jst.javaee.jca.ActivationSpec;
import org.eclipse.jst.javaee.jca.ConfigProperty;
import org.eclipse.jst.javaee.jca.RequiredConfigProperty;

import org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Activation Spec</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ActivationSpecImpl#getActivationspecClass <em>Activationspec Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ActivationSpecImpl#getRequiredConfigProperty <em>Required Config Property</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ActivationSpecImpl#getConfigProperty <em>Config Property</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ActivationSpecImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActivationSpecImpl extends EObjectImpl implements ActivationSpec {
	/**
	 * The default value of the '{@link #getActivationspecClass() <em>Activationspec Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivationspecClass()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTIVATIONSPEC_CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActivationspecClass() <em>Activationspec Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivationspecClass()
	 * @generated
	 * @ordered
	 */
	protected String activationspecClass = ACTIVATIONSPEC_CLASS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRequiredConfigProperty() <em>Required Config Property</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequiredConfigProperty()
	 * @generated
	 * @ordered
	 */
	protected EList<RequiredConfigProperty> requiredConfigProperty;

	/**
	 * The cached value of the '{@link #getConfigProperty() <em>Config Property</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfigProperty()
	 * @generated
	 * @ordered
	 */
	protected EList<ConfigProperty> configProperty;

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
	public String getActivationspecClass() {
		return activationspecClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActivationspecClass(String newActivationspecClass) {
		String oldActivationspecClass = activationspecClass;
		activationspecClass = newActivationspecClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.ACTIVATION_SPEC__ACTIVATIONSPEC_CLASS, oldActivationspecClass, activationspecClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<RequiredConfigProperty> getRequiredConfigProperty() {
		if (requiredConfigProperty == null) {
			requiredConfigProperty = new EObjectContainmentEList<RequiredConfigProperty>(RequiredConfigProperty.class, this, JcaPackage.ACTIVATION_SPEC__REQUIRED_CONFIG_PROPERTY);
		}
		return requiredConfigProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ConfigProperty> getConfigProperty() {
		if (configProperty == null) {
			configProperty = new EObjectContainmentEList<ConfigProperty>(ConfigProperty.class, this, JcaPackage.ACTIVATION_SPEC__CONFIG_PROPERTY);
		}
		return configProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.ACTIVATION_SPEC__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JcaPackage.ACTIVATION_SPEC__REQUIRED_CONFIG_PROPERTY:
				return ((InternalEList<?>)getRequiredConfigProperty()).basicRemove(otherEnd, msgs);
			case JcaPackage.ACTIVATION_SPEC__CONFIG_PROPERTY:
				return ((InternalEList<?>)getConfigProperty()).basicRemove(otherEnd, msgs);
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
			case JcaPackage.ACTIVATION_SPEC__ACTIVATIONSPEC_CLASS:
				return getActivationspecClass();
			case JcaPackage.ACTIVATION_SPEC__REQUIRED_CONFIG_PROPERTY:
				return getRequiredConfigProperty();
			case JcaPackage.ACTIVATION_SPEC__CONFIG_PROPERTY:
				return getConfigProperty();
			case JcaPackage.ACTIVATION_SPEC__ID:
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
			case JcaPackage.ACTIVATION_SPEC__ACTIVATIONSPEC_CLASS:
				setActivationspecClass((String)newValue);
				return;
			case JcaPackage.ACTIVATION_SPEC__REQUIRED_CONFIG_PROPERTY:
				getRequiredConfigProperty().clear();
				getRequiredConfigProperty().addAll((Collection<? extends RequiredConfigProperty>)newValue);
				return;
			case JcaPackage.ACTIVATION_SPEC__CONFIG_PROPERTY:
				getConfigProperty().clear();
				getConfigProperty().addAll((Collection<? extends ConfigProperty>)newValue);
				return;
			case JcaPackage.ACTIVATION_SPEC__ID:
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
			case JcaPackage.ACTIVATION_SPEC__ACTIVATIONSPEC_CLASS:
				setActivationspecClass(ACTIVATIONSPEC_CLASS_EDEFAULT);
				return;
			case JcaPackage.ACTIVATION_SPEC__REQUIRED_CONFIG_PROPERTY:
				getRequiredConfigProperty().clear();
				return;
			case JcaPackage.ACTIVATION_SPEC__CONFIG_PROPERTY:
				getConfigProperty().clear();
				return;
			case JcaPackage.ACTIVATION_SPEC__ID:
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
			case JcaPackage.ACTIVATION_SPEC__ACTIVATIONSPEC_CLASS:
				return ACTIVATIONSPEC_CLASS_EDEFAULT == null ? activationspecClass != null : !ACTIVATIONSPEC_CLASS_EDEFAULT.equals(activationspecClass);
			case JcaPackage.ACTIVATION_SPEC__REQUIRED_CONFIG_PROPERTY:
				return requiredConfigProperty != null && !requiredConfigProperty.isEmpty();
			case JcaPackage.ACTIVATION_SPEC__CONFIG_PROPERTY:
				return configProperty != null && !configProperty.isEmpty();
			case JcaPackage.ACTIVATION_SPEC__ID:
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
		result.append(" (activationspecClass: "); //$NON-NLS-1$
		result.append(activationspecClass);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //ActivationSpecImpl
