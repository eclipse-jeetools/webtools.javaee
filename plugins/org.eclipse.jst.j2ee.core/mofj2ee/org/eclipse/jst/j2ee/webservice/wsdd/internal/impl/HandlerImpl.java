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
package org.eclipse.jst.j2ee.webservice.wsdd.internal.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.common.internal.impl.CompatibilityDescriptionGroupImpl;
import org.eclipse.jst.j2ee.webservice.wscommon.InitParam;
import org.eclipse.jst.j2ee.webservice.wscommon.SOAPHeader;
import org.eclipse.jst.j2ee.webservice.wscommon.SOAPRole;
import org.eclipse.jst.j2ee.webservice.wsdd.Handler;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Handler</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.internal.impl.HandlerImpl#getHandlerName <em>Handler Name</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.internal.impl.HandlerImpl#getHandlerClass <em>Handler Class</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.internal.impl.HandlerImpl#getInitParams <em>Init Params</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.internal.impl.HandlerImpl#getSoapHeaders <em>Soap Headers</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.internal.impl.HandlerImpl#getSoapRoles <em>Soap Roles</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class HandlerImpl extends CompatibilityDescriptionGroupImpl implements Handler
{
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
	 * The default value of the '{@link #getHandlerClass() <em>Handler Class</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getHandlerClass()
	 * @generated
	 * @ordered
	 */
  protected static final String HANDLER_CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHandlerClass() <em>Handler Class</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getHandlerClass()
	 * @generated
	 * @ordered
	 */
  protected String handlerClass = HANDLER_CLASS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getInitParams() <em>Init Params</em>}' containment reference list.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getInitParams()
	 * @generated
	 * @ordered
	 */
  protected EList initParams;

	/**
	 * The cached value of the '{@link #getSoapHeaders() <em>Soap Headers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getSoapHeaders()
	 * @generated
	 * @ordered
	 */
  protected EList soapHeaders;

	/**
	 * The cached value of the '{@link #getSoapRoles() <em>Soap Roles</em>}' containment reference list.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getSoapRoles()
	 * @generated
	 * @ordered
	 */
  protected EList soapRoles;

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
		return WsddPackage.Literals.HANDLER;
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
			eNotify(new ENotificationImpl(this, Notification.SET, WsddPackage.HANDLER__HANDLER_NAME, oldHandlerName, handlerName));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public String getHandlerClass() {
		return handlerClass;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public void setHandlerClass(String newHandlerClass) {
		String oldHandlerClass = handlerClass;
		handlerClass = newHandlerClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WsddPackage.HANDLER__HANDLER_CLASS, oldHandlerClass, handlerClass));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public EList getInitParams() {
		if (initParams == null) {
			initParams = new EObjectContainmentEList(InitParam.class, this, WsddPackage.HANDLER__INIT_PARAMS);
		}
		return initParams;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public EList getSoapHeaders() {
		if (soapHeaders == null) {
			soapHeaders = new EObjectContainmentEList(SOAPHeader.class, this, WsddPackage.HANDLER__SOAP_HEADERS);
		}
		return soapHeaders;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public EList getSoapRoles() {
		if (soapRoles == null) {
			soapRoles = new EObjectContainmentEList(SOAPRole.class, this, WsddPackage.HANDLER__SOAP_ROLES);
		}
		return soapRoles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WsddPackage.HANDLER__INIT_PARAMS:
				return ((InternalEList)getInitParams()).basicRemove(otherEnd, msgs);
			case WsddPackage.HANDLER__SOAP_HEADERS:
				return ((InternalEList)getSoapHeaders()).basicRemove(otherEnd, msgs);
			case WsddPackage.HANDLER__SOAP_ROLES:
				return ((InternalEList)getSoapRoles()).basicRemove(otherEnd, msgs);
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
			case WsddPackage.HANDLER__HANDLER_NAME:
				return getHandlerName();
			case WsddPackage.HANDLER__HANDLER_CLASS:
				return getHandlerClass();
			case WsddPackage.HANDLER__INIT_PARAMS:
				return getInitParams();
			case WsddPackage.HANDLER__SOAP_HEADERS:
				return getSoapHeaders();
			case WsddPackage.HANDLER__SOAP_ROLES:
				return getSoapRoles();
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
			case WsddPackage.HANDLER__HANDLER_NAME:
				setHandlerName((String)newValue);
				return;
			case WsddPackage.HANDLER__HANDLER_CLASS:
				setHandlerClass((String)newValue);
				return;
			case WsddPackage.HANDLER__INIT_PARAMS:
				getInitParams().clear();
				getInitParams().addAll((Collection)newValue);
				return;
			case WsddPackage.HANDLER__SOAP_HEADERS:
				getSoapHeaders().clear();
				getSoapHeaders().addAll((Collection)newValue);
				return;
			case WsddPackage.HANDLER__SOAP_ROLES:
				getSoapRoles().clear();
				getSoapRoles().addAll((Collection)newValue);
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
			case WsddPackage.HANDLER__HANDLER_NAME:
				setHandlerName(HANDLER_NAME_EDEFAULT);
				return;
			case WsddPackage.HANDLER__HANDLER_CLASS:
				setHandlerClass(HANDLER_CLASS_EDEFAULT);
				return;
			case WsddPackage.HANDLER__INIT_PARAMS:
				getInitParams().clear();
				return;
			case WsddPackage.HANDLER__SOAP_HEADERS:
				getSoapHeaders().clear();
				return;
			case WsddPackage.HANDLER__SOAP_ROLES:
				getSoapRoles().clear();
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
			case WsddPackage.HANDLER__HANDLER_NAME:
				return HANDLER_NAME_EDEFAULT == null ? handlerName != null : !HANDLER_NAME_EDEFAULT.equals(handlerName);
			case WsddPackage.HANDLER__HANDLER_CLASS:
				return HANDLER_CLASS_EDEFAULT == null ? handlerClass != null : !HANDLER_CLASS_EDEFAULT.equals(handlerClass);
			case WsddPackage.HANDLER__INIT_PARAMS:
				return initParams != null && !initParams.isEmpty();
			case WsddPackage.HANDLER__SOAP_HEADERS:
				return soapHeaders != null && !soapHeaders.isEmpty();
			case WsddPackage.HANDLER__SOAP_ROLES:
				return soapRoles != null && !soapRoles.isEmpty();
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
		result.append(" (handlerName: "); //$NON-NLS-1$
		result.append(handlerName);
		result.append(", handlerClass: "); //$NON-NLS-1$
		result.append(handlerClass);
		result.append(')');
		return result.toString();
	}

} //HandlerImpl
