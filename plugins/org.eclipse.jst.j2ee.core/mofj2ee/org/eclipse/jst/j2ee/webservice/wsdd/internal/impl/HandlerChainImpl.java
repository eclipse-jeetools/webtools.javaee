/*******************************************************************************
 * Copyright (c) 2007, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.webservice.wsdd.internal.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jst.j2ee.common.internal.impl.J2EEEObjectImpl;

import org.eclipse.jst.j2ee.webservice.wsdd.Handler;
import org.eclipse.jst.j2ee.webservice.wsdd.HandlerChain;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Handler Chain</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.internal.impl.HandlerChainImpl#getServiceNamePattern <em>Service Name Pattern</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.internal.impl.HandlerChainImpl#getPortNamePattern <em>Port Name Pattern</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.internal.impl.HandlerChainImpl#getProtocolBindings <em>Protocol Bindings</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.internal.impl.HandlerChainImpl#getHandlers <em>Handlers</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class HandlerChainImpl extends J2EEEObjectImpl implements HandlerChain {
	/**
	 * The default value of the '{@link #getServiceNamePattern() <em>Service Name Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceNamePattern()
	 * @generated
	 * @ordered
	 */
	protected static final String SERVICE_NAME_PATTERN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getServiceNamePattern() <em>Service Name Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceNamePattern()
	 * @generated
	 * @ordered
	 */
	protected String serviceNamePattern = SERVICE_NAME_PATTERN_EDEFAULT;

	/**
	 * The default value of the '{@link #getPortNamePattern() <em>Port Name Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortNamePattern()
	 * @generated
	 * @ordered
	 */
	protected static final String PORT_NAME_PATTERN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPortNamePattern() <em>Port Name Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortNamePattern()
	 * @generated
	 * @ordered
	 */
	protected String portNamePattern = PORT_NAME_PATTERN_EDEFAULT;

	/**
	 * The cached value of the '{@link #getProtocolBindings() <em>Protocol Bindings</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProtocolBindings()
	 * @generated
	 * @ordered
	 */
	protected EList protocolBindings;

	/**
	 * The cached value of the '{@link #getHandlers() <em>Handlers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHandlers()
	 * @generated
	 * @ordered
	 */
	protected EList handlers;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HandlerChainImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WsddPackage.Literals.HANDLER_CHAIN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getServiceNamePattern() {
		return serviceNamePattern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServiceNamePattern(String newServiceNamePattern) {
		String oldServiceNamePattern = serviceNamePattern;
		serviceNamePattern = newServiceNamePattern;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WsddPackage.HANDLER_CHAIN__SERVICE_NAME_PATTERN, oldServiceNamePattern, serviceNamePattern));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPortNamePattern() {
		return portNamePattern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPortNamePattern(String newPortNamePattern) {
		String oldPortNamePattern = portNamePattern;
		portNamePattern = newPortNamePattern;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WsddPackage.HANDLER_CHAIN__PORT_NAME_PATTERN, oldPortNamePattern, portNamePattern));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getProtocolBindings() {
		if (protocolBindings == null) {
			protocolBindings = new EDataTypeUniqueEList(String.class, this, WsddPackage.HANDLER_CHAIN__PROTOCOL_BINDINGS);
		}
		return protocolBindings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getHandlers() {
		if (handlers == null) {
			handlers = new EObjectContainmentEList(Handler.class, this, WsddPackage.HANDLER_CHAIN__HANDLERS);
		}
		return handlers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WsddPackage.HANDLER_CHAIN__HANDLERS:
				return ((InternalEList)getHandlers()).basicRemove(otherEnd, msgs);
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
			case WsddPackage.HANDLER_CHAIN__SERVICE_NAME_PATTERN:
				return getServiceNamePattern();
			case WsddPackage.HANDLER_CHAIN__PORT_NAME_PATTERN:
				return getPortNamePattern();
			case WsddPackage.HANDLER_CHAIN__PROTOCOL_BINDINGS:
				return getProtocolBindings();
			case WsddPackage.HANDLER_CHAIN__HANDLERS:
				return getHandlers();
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
			case WsddPackage.HANDLER_CHAIN__SERVICE_NAME_PATTERN:
				setServiceNamePattern((String)newValue);
				return;
			case WsddPackage.HANDLER_CHAIN__PORT_NAME_PATTERN:
				setPortNamePattern((String)newValue);
				return;
			case WsddPackage.HANDLER_CHAIN__PROTOCOL_BINDINGS:
				getProtocolBindings().clear();
				getProtocolBindings().addAll((Collection)newValue);
				return;
			case WsddPackage.HANDLER_CHAIN__HANDLERS:
				getHandlers().clear();
				getHandlers().addAll((Collection)newValue);
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
			case WsddPackage.HANDLER_CHAIN__SERVICE_NAME_PATTERN:
				setServiceNamePattern(SERVICE_NAME_PATTERN_EDEFAULT);
				return;
			case WsddPackage.HANDLER_CHAIN__PORT_NAME_PATTERN:
				setPortNamePattern(PORT_NAME_PATTERN_EDEFAULT);
				return;
			case WsddPackage.HANDLER_CHAIN__PROTOCOL_BINDINGS:
				getProtocolBindings().clear();
				return;
			case WsddPackage.HANDLER_CHAIN__HANDLERS:
				getHandlers().clear();
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
			case WsddPackage.HANDLER_CHAIN__SERVICE_NAME_PATTERN:
				return SERVICE_NAME_PATTERN_EDEFAULT == null ? serviceNamePattern != null : !SERVICE_NAME_PATTERN_EDEFAULT.equals(serviceNamePattern);
			case WsddPackage.HANDLER_CHAIN__PORT_NAME_PATTERN:
				return PORT_NAME_PATTERN_EDEFAULT == null ? portNamePattern != null : !PORT_NAME_PATTERN_EDEFAULT.equals(portNamePattern);
			case WsddPackage.HANDLER_CHAIN__PROTOCOL_BINDINGS:
				return protocolBindings != null && !protocolBindings.isEmpty();
			case WsddPackage.HANDLER_CHAIN__HANDLERS:
				return handlers != null && !handlers.isEmpty();
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
		result.append(" (serviceNamePattern: "); //$NON-NLS-1$
		result.append(serviceNamePattern);
		result.append(", portNamePattern: "); //$NON-NLS-1$
		result.append(portNamePattern);
		result.append(", protocolBindings: "); //$NON-NLS-1$
		result.append(protocolBindings);
		result.append(')');
		return result.toString();
	}

} //HandlerChainImpl
