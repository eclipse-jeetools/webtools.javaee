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
package org.eclipse.jst.javaee.core.internal.impl;

import java.util.Collection;
import java.util.List;

import javax.xml.namespace.QName;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.DisplayName;
import org.eclipse.jst.javaee.core.Icon;
import org.eclipse.jst.javaee.core.ParamValue;
import org.eclipse.jst.javaee.core.ServiceRefHandler;

import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service Ref Handler</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ServiceRefHandlerImpl#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ServiceRefHandlerImpl#getDisplayNames <em>Display Names</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ServiceRefHandlerImpl#getIcons <em>Icons</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ServiceRefHandlerImpl#getHandlerName <em>Handler Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ServiceRefHandlerImpl#getHandlerClass <em>Handler Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ServiceRefHandlerImpl#getInitParams <em>Init Params</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ServiceRefHandlerImpl#getSoapHeaders <em>Soap Headers</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ServiceRefHandlerImpl#getSoapRoles <em>Soap Roles</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ServiceRefHandlerImpl#getPortNames <em>Port Names</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ServiceRefHandlerImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ServiceRefHandlerImpl extends EObjectImpl implements ServiceRefHandler {
	/**
	 * The cached value of the '{@link #getDescriptions() <em>Descriptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptions()
	 * @generated
	 * @ordered
	 */
	protected EList<Description> descriptions;

	/**
	 * The cached value of the '{@link #getDisplayNames() <em>Display Names</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayNames()
	 * @generated
	 * @ordered
	 */
	protected EList<DisplayName> displayNames;

	/**
	 * The cached value of the '{@link #getIcons() <em>Icons</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIcons()
	 * @generated
	 * @ordered
	 */
	protected EList<Icon> icons;

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
	protected EList<ParamValue> initParams;

	/**
	 * The cached value of the '{@link #getSoapHeaders() <em>Soap Headers</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSoapHeaders()
	 * @generated
	 * @ordered
	 */
	protected EList<QName> soapHeaders;

	/**
	 * The cached value of the '{@link #getSoapRoles() <em>Soap Roles</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSoapRoles()
	 * @generated
	 * @ordered
	 */
	protected EList<String> soapRoles;

	/**
	 * The cached value of the '{@link #getPortNames() <em>Port Names</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortNames()
	 * @generated
	 * @ordered
	 */
	protected EList<String> portNames;

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
	protected ServiceRefHandlerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaeePackage.Literals.SERVICE_REF_HANDLER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Description> getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList<Description>(Description.class, this, JavaeePackage.SERVICE_REF_HANDLER__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<DisplayName> getDisplayNames() {
		if (displayNames == null) {
			displayNames = new EObjectContainmentEList<DisplayName>(DisplayName.class, this, JavaeePackage.SERVICE_REF_HANDLER__DISPLAY_NAMES);
		}
		return displayNames;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Icon> getIcons() {
		if (icons == null) {
			icons = new EObjectContainmentEList<Icon>(Icon.class, this, JavaeePackage.SERVICE_REF_HANDLER__ICONS);
		}
		return icons;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHandlerName() {
		return handlerName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHandlerName(String newHandlerName) {
		String oldHandlerName = handlerName;
		handlerName = newHandlerName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.SERVICE_REF_HANDLER__HANDLER_NAME, oldHandlerName, handlerName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHandlerClass() {
		return handlerClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHandlerClass(String newHandlerClass) {
		String oldHandlerClass = handlerClass;
		handlerClass = newHandlerClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.SERVICE_REF_HANDLER__HANDLER_CLASS, oldHandlerClass, handlerClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ParamValue> getInitParams() {
		if (initParams == null) {
			initParams = new EObjectContainmentEList<ParamValue>(ParamValue.class, this, JavaeePackage.SERVICE_REF_HANDLER__INIT_PARAMS);
		}
		return initParams;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<QName> getSoapHeaders() {
		if (soapHeaders == null) {
			soapHeaders = new EDataTypeEList<QName>(QName.class, this, JavaeePackage.SERVICE_REF_HANDLER__SOAP_HEADERS);
		}
		return soapHeaders;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<String> getSoapRoles() {
		if (soapRoles == null) {
			soapRoles = new EDataTypeEList<String>(String.class, this, JavaeePackage.SERVICE_REF_HANDLER__SOAP_ROLES);
		}
		return soapRoles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<String> getPortNames() {
		if (portNames == null) {
			portNames = new EDataTypeEList<String>(String.class, this, JavaeePackage.SERVICE_REF_HANDLER__PORT_NAMES);
		}
		return portNames;
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
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.SERVICE_REF_HANDLER__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JavaeePackage.SERVICE_REF_HANDLER__DESCRIPTIONS:
				return ((InternalEList<?>)getDescriptions()).basicRemove(otherEnd, msgs);
			case JavaeePackage.SERVICE_REF_HANDLER__DISPLAY_NAMES:
				return ((InternalEList<?>)getDisplayNames()).basicRemove(otherEnd, msgs);
			case JavaeePackage.SERVICE_REF_HANDLER__ICONS:
				return ((InternalEList<?>)getIcons()).basicRemove(otherEnd, msgs);
			case JavaeePackage.SERVICE_REF_HANDLER__INIT_PARAMS:
				return ((InternalEList<?>)getInitParams()).basicRemove(otherEnd, msgs);
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
			case JavaeePackage.SERVICE_REF_HANDLER__DESCRIPTIONS:
				return getDescriptions();
			case JavaeePackage.SERVICE_REF_HANDLER__DISPLAY_NAMES:
				return getDisplayNames();
			case JavaeePackage.SERVICE_REF_HANDLER__ICONS:
				return getIcons();
			case JavaeePackage.SERVICE_REF_HANDLER__HANDLER_NAME:
				return getHandlerName();
			case JavaeePackage.SERVICE_REF_HANDLER__HANDLER_CLASS:
				return getHandlerClass();
			case JavaeePackage.SERVICE_REF_HANDLER__INIT_PARAMS:
				return getInitParams();
			case JavaeePackage.SERVICE_REF_HANDLER__SOAP_HEADERS:
				return getSoapHeaders();
			case JavaeePackage.SERVICE_REF_HANDLER__SOAP_ROLES:
				return getSoapRoles();
			case JavaeePackage.SERVICE_REF_HANDLER__PORT_NAMES:
				return getPortNames();
			case JavaeePackage.SERVICE_REF_HANDLER__ID:
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
			case JavaeePackage.SERVICE_REF_HANDLER__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection<? extends Description>)newValue);
				return;
			case JavaeePackage.SERVICE_REF_HANDLER__DISPLAY_NAMES:
				getDisplayNames().clear();
				getDisplayNames().addAll((Collection<? extends DisplayName>)newValue);
				return;
			case JavaeePackage.SERVICE_REF_HANDLER__ICONS:
				getIcons().clear();
				getIcons().addAll((Collection<? extends Icon>)newValue);
				return;
			case JavaeePackage.SERVICE_REF_HANDLER__HANDLER_NAME:
				setHandlerName((String)newValue);
				return;
			case JavaeePackage.SERVICE_REF_HANDLER__HANDLER_CLASS:
				setHandlerClass((String)newValue);
				return;
			case JavaeePackage.SERVICE_REF_HANDLER__INIT_PARAMS:
				getInitParams().clear();
				getInitParams().addAll((Collection<? extends ParamValue>)newValue);
				return;
			case JavaeePackage.SERVICE_REF_HANDLER__SOAP_HEADERS:
				getSoapHeaders().clear();
				getSoapHeaders().addAll((Collection<? extends QName>)newValue);
				return;
			case JavaeePackage.SERVICE_REF_HANDLER__SOAP_ROLES:
				getSoapRoles().clear();
				getSoapRoles().addAll((Collection<? extends String>)newValue);
				return;
			case JavaeePackage.SERVICE_REF_HANDLER__PORT_NAMES:
				getPortNames().clear();
				getPortNames().addAll((Collection<? extends String>)newValue);
				return;
			case JavaeePackage.SERVICE_REF_HANDLER__ID:
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
			case JavaeePackage.SERVICE_REF_HANDLER__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case JavaeePackage.SERVICE_REF_HANDLER__DISPLAY_NAMES:
				getDisplayNames().clear();
				return;
			case JavaeePackage.SERVICE_REF_HANDLER__ICONS:
				getIcons().clear();
				return;
			case JavaeePackage.SERVICE_REF_HANDLER__HANDLER_NAME:
				setHandlerName(HANDLER_NAME_EDEFAULT);
				return;
			case JavaeePackage.SERVICE_REF_HANDLER__HANDLER_CLASS:
				setHandlerClass(HANDLER_CLASS_EDEFAULT);
				return;
			case JavaeePackage.SERVICE_REF_HANDLER__INIT_PARAMS:
				getInitParams().clear();
				return;
			case JavaeePackage.SERVICE_REF_HANDLER__SOAP_HEADERS:
				getSoapHeaders().clear();
				return;
			case JavaeePackage.SERVICE_REF_HANDLER__SOAP_ROLES:
				getSoapRoles().clear();
				return;
			case JavaeePackage.SERVICE_REF_HANDLER__PORT_NAMES:
				getPortNames().clear();
				return;
			case JavaeePackage.SERVICE_REF_HANDLER__ID:
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
			case JavaeePackage.SERVICE_REF_HANDLER__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case JavaeePackage.SERVICE_REF_HANDLER__DISPLAY_NAMES:
				return displayNames != null && !displayNames.isEmpty();
			case JavaeePackage.SERVICE_REF_HANDLER__ICONS:
				return icons != null && !icons.isEmpty();
			case JavaeePackage.SERVICE_REF_HANDLER__HANDLER_NAME:
				return HANDLER_NAME_EDEFAULT == null ? handlerName != null : !HANDLER_NAME_EDEFAULT.equals(handlerName);
			case JavaeePackage.SERVICE_REF_HANDLER__HANDLER_CLASS:
				return HANDLER_CLASS_EDEFAULT == null ? handlerClass != null : !HANDLER_CLASS_EDEFAULT.equals(handlerClass);
			case JavaeePackage.SERVICE_REF_HANDLER__INIT_PARAMS:
				return initParams != null && !initParams.isEmpty();
			case JavaeePackage.SERVICE_REF_HANDLER__SOAP_HEADERS:
				return soapHeaders != null && !soapHeaders.isEmpty();
			case JavaeePackage.SERVICE_REF_HANDLER__SOAP_ROLES:
				return soapRoles != null && !soapRoles.isEmpty();
			case JavaeePackage.SERVICE_REF_HANDLER__PORT_NAMES:
				return portNames != null && !portNames.isEmpty();
			case JavaeePackage.SERVICE_REF_HANDLER__ID:
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
		result.append(" (handlerName: "); //$NON-NLS-1$
		result.append(handlerName);
		result.append(", handlerClass: "); //$NON-NLS-1$
		result.append(handlerClass);
		result.append(", soapHeaders: "); //$NON-NLS-1$
		result.append(soapHeaders);
		result.append(", soapRoles: "); //$NON-NLS-1$
		result.append(soapRoles);
		result.append(", portNames: "); //$NON-NLS-1$
		result.append(portNames);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //ServiceRefHandlerImpl