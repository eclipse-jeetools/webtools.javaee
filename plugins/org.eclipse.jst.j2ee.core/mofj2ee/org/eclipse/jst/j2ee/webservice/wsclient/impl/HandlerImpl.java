/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.webservice.wsclient.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.common.ParamValue;
import org.eclipse.jst.j2ee.common.QName;
import org.eclipse.jst.j2ee.common.impl.CompatibilityDescriptionGroupImpl;
import org.eclipse.jst.j2ee.webservice.wsclient.Handler;
import org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Handler</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsclient.impl.HandlerImpl#getHandlerName <em>Handler Name</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsclient.impl.HandlerImpl#getSoapRoles <em>Soap Roles</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsclient.impl.HandlerImpl#getPortNames <em>Port Names</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsclient.impl.HandlerImpl#getHandlerClass <em>Handler Class</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsclient.impl.HandlerImpl#getInitParams <em>Init Params</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsclient.impl.HandlerImpl#getSoapHeaders <em>Soap Headers</em>}</li>
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
	protected EClass eStaticClass() {
		return Webservice_clientPackage.eINSTANCE.getHandler();
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
			eNotify(new ENotificationImpl(this, Notification.SET, Webservice_clientPackage.HANDLER__HANDLER_NAME, oldHandlerName, handlerName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
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
	public JavaClass getHandlerClass() {
		if (handlerClass != null && handlerClass.eIsProxy()) {
			JavaClass oldHandlerClass = handlerClass;
			handlerClass = (JavaClass)eResolveProxy((InternalEObject)handlerClass);
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
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case Webservice_clientPackage.HANDLER__ICONS:
					return ((InternalEList)getIcons()).basicRemove(otherEnd, msgs);
				case Webservice_clientPackage.HANDLER__DISPLAY_NAMES:
					return ((InternalEList)getDisplayNames()).basicRemove(otherEnd, msgs);
				case Webservice_clientPackage.HANDLER__DESCRIPTIONS:
					return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
				case Webservice_clientPackage.HANDLER__INIT_PARAMS:
					return ((InternalEList)getInitParams()).basicRemove(otherEnd, msgs);
				case Webservice_clientPackage.HANDLER__SOAP_HEADERS:
					return ((InternalEList)getSoapHeaders()).basicRemove(otherEnd, msgs);
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
			case Webservice_clientPackage.HANDLER__ICONS:
				return getIcons();
			case Webservice_clientPackage.HANDLER__DISPLAY_NAMES:
				return getDisplayNames();
			case Webservice_clientPackage.HANDLER__DESCRIPTIONS:
				return getDescriptions();
			case Webservice_clientPackage.HANDLER__SMALL_ICON:
				return getSmallIcon();
			case Webservice_clientPackage.HANDLER__LARGE_ICON:
				return getLargeIcon();
			case Webservice_clientPackage.HANDLER__DESCRIPTION:
				return getDescription();
			case Webservice_clientPackage.HANDLER__DISPLAY_NAME:
				return getDisplayName();
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
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case Webservice_clientPackage.HANDLER__ICONS:
				getIcons().clear();
				getIcons().addAll((Collection)newValue);
				return;
			case Webservice_clientPackage.HANDLER__DISPLAY_NAMES:
				getDisplayNames().clear();
				getDisplayNames().addAll((Collection)newValue);
				return;
			case Webservice_clientPackage.HANDLER__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
				return;
			case Webservice_clientPackage.HANDLER__SMALL_ICON:
				setSmallIcon((String)newValue);
				return;
			case Webservice_clientPackage.HANDLER__LARGE_ICON:
				setLargeIcon((String)newValue);
				return;
			case Webservice_clientPackage.HANDLER__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case Webservice_clientPackage.HANDLER__DISPLAY_NAME:
				setDisplayName((String)newValue);
				return;
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
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case Webservice_clientPackage.HANDLER__ICONS:
				getIcons().clear();
				return;
			case Webservice_clientPackage.HANDLER__DISPLAY_NAMES:
				getDisplayNames().clear();
				return;
			case Webservice_clientPackage.HANDLER__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case Webservice_clientPackage.HANDLER__SMALL_ICON:
				setSmallIcon(SMALL_ICON_EDEFAULT);
				return;
			case Webservice_clientPackage.HANDLER__LARGE_ICON:
				setLargeIcon(LARGE_ICON_EDEFAULT);
				return;
			case Webservice_clientPackage.HANDLER__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case Webservice_clientPackage.HANDLER__DISPLAY_NAME:
				setDisplayName(DISPLAY_NAME_EDEFAULT);
				return;
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
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case Webservice_clientPackage.HANDLER__ICONS:
				return icons != null && !icons.isEmpty();
			case Webservice_clientPackage.HANDLER__DISPLAY_NAMES:
				return displayNames != null && !displayNames.isEmpty();
			case Webservice_clientPackage.HANDLER__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case Webservice_clientPackage.HANDLER__SMALL_ICON:
				return SMALL_ICON_EDEFAULT == null ? smallIcon != null : !SMALL_ICON_EDEFAULT.equals(smallIcon);
			case Webservice_clientPackage.HANDLER__LARGE_ICON:
				return LARGE_ICON_EDEFAULT == null ? largeIcon != null : !LARGE_ICON_EDEFAULT.equals(largeIcon);
			case Webservice_clientPackage.HANDLER__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case Webservice_clientPackage.HANDLER__DISPLAY_NAME:
				return DISPLAY_NAME_EDEFAULT == null ? displayName != null : !DISPLAY_NAME_EDEFAULT.equals(displayName);
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
		result.append(" (handlerName: ");//$NON-NLS-1$
		result.append(handlerName);
		result.append(", soapRoles: ");//$NON-NLS-1$
		result.append(soapRoles);
		result.append(", portNames: ");//$NON-NLS-1$
		result.append(portNames);
		result.append(')');
		return result.toString();
	}

} //HandlerImpl
