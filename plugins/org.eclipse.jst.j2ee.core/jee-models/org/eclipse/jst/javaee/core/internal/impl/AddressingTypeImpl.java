/*******************************************************************************
 * Copyright (c) 2009, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.core.internal.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.jst.javaee.core.AddressingResponsesType;
import org.eclipse.jst.javaee.core.AddressingType;

import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Addressing Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.AddressingTypeImpl#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.AddressingTypeImpl#isRequired <em>Required</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.AddressingTypeImpl#getResponses <em>Responses</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AddressingTypeImpl extends EObjectImpl implements AddressingType {
	/**
	 * The default value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENABLED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected boolean enabled = ENABLED_EDEFAULT;

	/**
	 * This is true if the Enabled attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean enabledESet;

	/**
	 * The default value of the '{@link #isRequired() <em>Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRequired()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REQUIRED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRequired() <em>Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRequired()
	 * @generated
	 * @ordered
	 */
	protected boolean required = REQUIRED_EDEFAULT;

	/**
	 * This is true if the Required attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean requiredESet;

	/**
	 * The default value of the '{@link #getResponses() <em>Responses</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResponses()
	 * @generated
	 * @ordered
	 */
	protected static final AddressingResponsesType RESPONSES_EDEFAULT = AddressingResponsesType.ANONYMOUS;

	/**
	 * The cached value of the '{@link #getResponses() <em>Responses</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResponses()
	 * @generated
	 * @ordered
	 */
	protected AddressingResponsesType responses = RESPONSES_EDEFAULT;

	/**
	 * This is true if the Responses attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean responsesESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AddressingTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaeePackage.Literals.ADDRESSING_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEnabled(boolean newEnabled) {
		boolean oldEnabled = enabled;
		enabled = newEnabled;
		boolean oldEnabledESet = enabledESet;
		enabledESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.ADDRESSING_TYPE__ENABLED, oldEnabled, enabled, !oldEnabledESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetEnabled() {
		boolean oldEnabled = enabled;
		boolean oldEnabledESet = enabledESet;
		enabled = ENABLED_EDEFAULT;
		enabledESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JavaeePackage.ADDRESSING_TYPE__ENABLED, oldEnabled, ENABLED_EDEFAULT, oldEnabledESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetEnabled() {
		return enabledESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isRequired() {
		return required;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRequired(boolean newRequired) {
		boolean oldRequired = required;
		required = newRequired;
		boolean oldRequiredESet = requiredESet;
		requiredESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.ADDRESSING_TYPE__REQUIRED, oldRequired, required, !oldRequiredESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetRequired() {
		boolean oldRequired = required;
		boolean oldRequiredESet = requiredESet;
		required = REQUIRED_EDEFAULT;
		requiredESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JavaeePackage.ADDRESSING_TYPE__REQUIRED, oldRequired, REQUIRED_EDEFAULT, oldRequiredESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetRequired() {
		return requiredESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AddressingResponsesType getResponses() {
		return responses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setResponses(AddressingResponsesType newResponses) {
		AddressingResponsesType oldResponses = responses;
		responses = newResponses == null ? RESPONSES_EDEFAULT : newResponses;
		boolean oldResponsesESet = responsesESet;
		responsesESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.ADDRESSING_TYPE__RESPONSES, oldResponses, responses, !oldResponsesESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetResponses() {
		AddressingResponsesType oldResponses = responses;
		boolean oldResponsesESet = responsesESet;
		responses = RESPONSES_EDEFAULT;
		responsesESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JavaeePackage.ADDRESSING_TYPE__RESPONSES, oldResponses, RESPONSES_EDEFAULT, oldResponsesESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetResponses() {
		return responsesESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case JavaeePackage.ADDRESSING_TYPE__ENABLED:
				return isEnabled();
			case JavaeePackage.ADDRESSING_TYPE__REQUIRED:
				return isRequired();
			case JavaeePackage.ADDRESSING_TYPE__RESPONSES:
				return getResponses();
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
			case JavaeePackage.ADDRESSING_TYPE__ENABLED:
				setEnabled((Boolean)newValue);
				return;
			case JavaeePackage.ADDRESSING_TYPE__REQUIRED:
				setRequired((Boolean)newValue);
				return;
			case JavaeePackage.ADDRESSING_TYPE__RESPONSES:
				setResponses((AddressingResponsesType)newValue);
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
			case JavaeePackage.ADDRESSING_TYPE__ENABLED:
				unsetEnabled();
				return;
			case JavaeePackage.ADDRESSING_TYPE__REQUIRED:
				unsetRequired();
				return;
			case JavaeePackage.ADDRESSING_TYPE__RESPONSES:
				unsetResponses();
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
			case JavaeePackage.ADDRESSING_TYPE__ENABLED:
				return isSetEnabled();
			case JavaeePackage.ADDRESSING_TYPE__REQUIRED:
				return isSetRequired();
			case JavaeePackage.ADDRESSING_TYPE__RESPONSES:
				return isSetResponses();
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
		result.append(" (enabled: "); //$NON-NLS-1$
		if (enabledESet) result.append(enabled); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", required: "); //$NON-NLS-1$
		if (requiredESet) result.append(required); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", responses: "); //$NON-NLS-1$
		if (responsesESet) result.append(responses); else result.append("<unset>"); //$NON-NLS-1$
		result.append(')');
		return result.toString();
	}

} //AddressingTypeImpl
