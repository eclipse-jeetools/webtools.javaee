/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.webservice.wsdd.internal.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.jst.j2ee.common.internal.impl.J2EEEObjectImpl;

import org.eclipse.jst.j2ee.webservice.wsdd.AddressingResponsesType;
import org.eclipse.jst.j2ee.webservice.wsdd.AddressingType;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Addressing Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.internal.impl.AddressingTypeImpl#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.internal.impl.AddressingTypeImpl#isRequired <em>Required</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.internal.impl.AddressingTypeImpl#getResponses <em>Responses</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AddressingTypeImpl extends J2EEEObjectImpl implements AddressingType {
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
	 * The default value of the '{@link #getResponses() <em>Responses</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResponses()
	 * @generated
	 * @ordered
	 */
	protected static final AddressingResponsesType RESPONSES_EDEFAULT = AddressingResponsesType.ANONYMOUS_LITERAL;

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
		return WsddPackage.Literals.ADDRESSING_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnabled(boolean newEnabled) {
		boolean oldEnabled = enabled;
		enabled = newEnabled;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WsddPackage.ADDRESSING_TYPE__ENABLED, oldEnabled, enabled));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRequired() {
		return required;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRequired(boolean newRequired) {
		boolean oldRequired = required;
		required = newRequired;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WsddPackage.ADDRESSING_TYPE__REQUIRED, oldRequired, required));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AddressingResponsesType getResponses() {
		return responses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResponses(AddressingResponsesType newResponses) {
		AddressingResponsesType oldResponses = responses;
		responses = newResponses == null ? RESPONSES_EDEFAULT : newResponses;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WsddPackage.ADDRESSING_TYPE__RESPONSES, oldResponses, responses));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WsddPackage.ADDRESSING_TYPE__ENABLED:
				return isEnabled() ? Boolean.TRUE : Boolean.FALSE;
			case WsddPackage.ADDRESSING_TYPE__REQUIRED:
				return isRequired() ? Boolean.TRUE : Boolean.FALSE;
			case WsddPackage.ADDRESSING_TYPE__RESPONSES:
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
			case WsddPackage.ADDRESSING_TYPE__ENABLED:
				setEnabled(((Boolean)newValue).booleanValue());
				return;
			case WsddPackage.ADDRESSING_TYPE__REQUIRED:
				setRequired(((Boolean)newValue).booleanValue());
				return;
			case WsddPackage.ADDRESSING_TYPE__RESPONSES:
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
			case WsddPackage.ADDRESSING_TYPE__ENABLED:
				setEnabled(ENABLED_EDEFAULT);
				return;
			case WsddPackage.ADDRESSING_TYPE__REQUIRED:
				setRequired(REQUIRED_EDEFAULT);
				return;
			case WsddPackage.ADDRESSING_TYPE__RESPONSES:
				setResponses(RESPONSES_EDEFAULT);
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
			case WsddPackage.ADDRESSING_TYPE__ENABLED:
				return enabled != ENABLED_EDEFAULT;
			case WsddPackage.ADDRESSING_TYPE__REQUIRED:
				return required != REQUIRED_EDEFAULT;
			case WsddPackage.ADDRESSING_TYPE__RESPONSES:
				return responses != RESPONSES_EDEFAULT;
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
		result.append(enabled);
		result.append(", required: "); //$NON-NLS-1$
		result.append(required);
		result.append(", responses: "); //$NON-NLS-1$
		result.append(responses);
		result.append(')');
		return result.toString();
	}

} //AddressingTypeImpl
