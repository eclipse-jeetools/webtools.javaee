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
package org.eclipse.jst.j2ee.webservice.wsclient.internal.impl;

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
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.common.ParamValue;
import org.eclipse.jst.j2ee.common.QName;
import org.eclipse.jst.j2ee.common.internal.impl.CompatibilityDescriptionGroupImpl;
import org.eclipse.jst.j2ee.webservice.wsclient.Handler;
import org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Handler</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsclient.internal.impl.HandlerImpl#getHandlerName <em>Handler Name</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsclient.internal.impl.HandlerImpl#getSoapRoles <em>Soap Roles</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsclient.internal.impl.HandlerImpl#getPortNames <em>Port Names</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsclient.internal.impl.HandlerImpl#getHandlerClass <em>Handler Class</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsclient.internal.impl.HandlerImpl#getInitParams <em>Init Params</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsclient.internal.impl.HandlerImpl#getSoapHeaders <em>Soap Headers</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class HandlerImpl extends CompatibilityDescriptionGroupImpl implements Handler {
	/**
	 * The default value of the '{@link #getHandlerName() <em>Handler Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHandlerName()
	 * @generated
	 * @ordered
	 */
	protected static final String HANDLER_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHandlerName() <em>Handler Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHandlerName()
	 * @generated
	 * @ordered
	 */
	protected String handlerName = HANDLER_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSoapRoles() <em>Soap Roles</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSoapRoles()
	 * @generated
	 * @ordered
	 */
	protected EList soapRoles = null;

	/**
	 * The cached value of the '{@link #getPortNames() <em>Port Names</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortNames()
	 * @generated
	 * @ordered
	 */
	protected EList portNames = null;

	/**
	 * The cached value of the '{@link #getHandlerClass() <em>Handler Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHandlerClass()
	 * @generated
	 * @ordered
	 */
	protected JavaClass handlerClass = null;

	/**
	 * The cached value of the '{@link #getInitParams() <em>Init Params</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitParams()
	 * @generated
	 * @ordered
	 */
	protected EList initParams = null;

	/**
	 * The cached value of the '{@link #getSoapHeaders() <em>Soap Headers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSoapHeaders()
	 * @generated
	 * @ordered
	 */
	protected EList soapHeaders = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HandlerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Webservice_clientPackage.Literals.HANDLER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getHandlerName() {
		return handlerName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHandlerName(String newHandlerName) {
		String oldHandlerName = handlerName;
		handlerName = newHandlerName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Webservice_clientPackage.HANDLER__HANDLER_NAME, oldHandlerName, handlerName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList getSoapHeaders() {
		if (soapHeaders == null) {
			soapHeaders = new EObjectContainmentEList(QName.class, this, Webservice_clientPackage.HANDLER__SOAP_HEADERS);
		}
		return soapHeaders;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Webservice_clientPackage.HANDLER__INIT_PARAMS:
				return ((InternalEList)getInitParams()).basicRemove(otherEnd, msgs);
			case Webservice_clientPackage.HANDLER__SOAP_HEADERS:
				return ((InternalEList)getSoapHeaders()).basicRemove(otherEnd, msgs);
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
			case Webservice_clientPackage.HANDLER__HANDLER_NAME:
				return getHandlerName();
			case Webservice_clientPackage.HANDLER__SOAP_ROLES:
				return getSoapRoles();
			case Webservice_clientPackage.HANDLER__PORT_NAMES:
				return getPortNames();
			case Webservice_clientPackage.HANDLER__HANDLER_CLASS:
				if (resolve) return getHandlerClass();
				return basicGetHandlerClass();
			case Webservice_clientPackage.HANDLER__INIT_PARAMS:
				return getInitParams();
			case Webservice_clientPackage.HANDLER__SOAP_HEADERS:
				return getSoapHeaders();
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
			case Webservice_clientPackage.HANDLER__HANDLER_NAME:
				setHandlerName((String)newValue);
				return;
			case Webservice_clientPackage.HANDLER__SOAP_ROLES:
				getSoapRoles().clear();
				getSoapRoles().addAll((Collection)newValue);
				return;
			case Webservice_clientPackage.HANDLER__PORT_NAMES:
				getPortNames().clear();
				getPortNames().addAll((Collection)newValue);
				return;
			case Webservice_clientPackage.HANDLER__HANDLER_CLASS:
				setHandlerClass((JavaClass)newValue);
				return;
			case Webservice_clientPackage.HANDLER__INIT_PARAMS:
				getInitParams().clear();
				getInitParams().addAll((Collection)newValue);
				return;
			case Webservice_clientPackage.HANDLER__SOAP_HEADERS:
				getSoapHeaders().clear();
				getSoapHeaders().addAll((Collection)newValue);
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
			case Webservice_clientPackage.HANDLER__HANDLER_NAME:
				setHandlerName(HANDLER_NAME_EDEFAULT);
				return;
			case Webservice_clientPackage.HANDLER__SOAP_ROLES:
				getSoapRoles().clear();
				return;
			case Webservice_clientPackage.HANDLER__PORT_NAMES:
				getPortNames().clear();
				return;
			case Webservice_clientPackage.HANDLER__HANDLER_CLASS:
				setHandlerClass((JavaClass)null);
				return;
			case Webservice_clientPackage.HANDLER__INIT_PARAMS:
				getInitParams().clear();
				return;
			case Webservice_clientPackage.HANDLER__SOAP_HEADERS:
				getSoapHeaders().clear();
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
			case Webservice_clientPackage.HANDLER__HANDLER_NAME:
				return HANDLER_NAME_EDEFAULT == null ? handlerName != null : !HANDLER_NAME_EDEFAULT.equals(handlerName);
			case Webservice_clientPackage.HANDLER__SOAP_ROLES:
				return soapRoles != null && !soapRoles.isEmpty();
			case Webservice_clientPackage.HANDLER__PORT_NAMES:
				return portNames != null && !portNames.isEmpty();
			case Webservice_clientPackage.HANDLER__HANDLER_CLASS:
				return handlerClass != null;
			case Webservice_clientPackage.HANDLER__INIT_PARAMS:
				return initParams != null && !initParams.isEmpty();
			case Webservice_clientPackage.HANDLER__SOAP_HEADERS:
				return soapHeaders != null && !soapHeaders.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList getSoapRoles() {
		if (soapRoles == null) {
			soapRoles = new EDataTypeUniqueEList(String.class, this, Webservice_clientPackage.HANDLER__SOAP_ROLES);
		}
		return soapRoles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList getPortNames() {
		if (portNames == null) {
			portNames = new EDataTypeUniqueEList(String.class, this, Webservice_clientPackage.HANDLER__PORT_NAMES);
		}
		return portNames;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public JavaClass getHandlerClass() {
		if (handlerClass != null && handlerClass.eIsProxy()) {
			InternalEObject oldHandlerClass = (InternalEObject)handlerClass;
			handlerClass = (JavaClass)eResolveProxy(oldHandlerClass);
			if (handlerClass != oldHandlerClass) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Webservice_clientPackage.HANDLER__HANDLER_CLASS, oldHandlerClass, handlerClass));
			}
		}
		return handlerClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass basicGetHandlerClass() {
		return handlerClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHandlerClass(JavaClass newHandlerClass) {
		JavaClass oldHandlerClass = handlerClass;
		handlerClass = newHandlerClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Webservice_clientPackage.HANDLER__HANDLER_CLASS, oldHandlerClass, handlerClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList getInitParams() {
		if (initParams == null) {
			initParams = new EObjectContainmentEList(ParamValue.class, this, Webservice_clientPackage.HANDLER__INIT_PARAMS);
		}
		return initParams;
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
		result.append(" (handlerName: "); //$NON-NLS-1$
		result.append(handlerName);
		result.append(", soapRoles: "); //$NON-NLS-1$
		result.append(soapRoles);
		result.append(", portNames: "); //$NON-NLS-1$
		result.append(portNames);
		result.append(')');
		return result.toString();
	}

} //HandlerImpl
