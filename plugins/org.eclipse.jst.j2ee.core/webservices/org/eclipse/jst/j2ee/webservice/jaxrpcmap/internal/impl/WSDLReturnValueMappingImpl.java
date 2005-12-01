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
package org.eclipse.jst.j2ee.webservice.jaxrpcmap.internal.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLMessage;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLReturnValueMapping;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>WSDL Return Value Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.internal.impl.WSDLReturnValueMappingImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.internal.impl.WSDLReturnValueMappingImpl#getMethodReturnValue <em>Method Return Value</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.internal.impl.WSDLReturnValueMappingImpl#getWsdlMessagePartName <em>Wsdl Message Part Name</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.internal.impl.WSDLReturnValueMappingImpl#getWsdlMessage <em>Wsdl Message</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WSDLReturnValueMappingImpl extends EObjectImpl implements WSDLReturnValueMapping
{
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
	 * The default value of the '{@link #getMethodReturnValue() <em>Method Return Value</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getMethodReturnValue()
	 * @generated
	 * @ordered
	 */
  protected static final String METHOD_RETURN_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMethodReturnValue() <em>Method Return Value</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getMethodReturnValue()
	 * @generated
	 * @ordered
	 */
  protected String methodReturnValue = METHOD_RETURN_VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getWsdlMessagePartName() <em>Wsdl Message Part Name</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getWsdlMessagePartName()
	 * @generated
	 * @ordered
	 */
  protected static final String WSDL_MESSAGE_PART_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWsdlMessagePartName() <em>Wsdl Message Part Name</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getWsdlMessagePartName()
	 * @generated
	 * @ordered
	 */
  protected String wsdlMessagePartName = WSDL_MESSAGE_PART_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getWsdlMessage() <em>Wsdl Message</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getWsdlMessage()
	 * @generated
	 * @ordered
	 */
  protected WSDLMessage wsdlMessage = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected WSDLReturnValueMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected EClass eStaticClass() {
		return JaxrpcmapPackage.eINSTANCE.getWSDLReturnValueMapping();
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
			eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.WSDL_RETURN_VALUE_MAPPING__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public String getMethodReturnValue() {
		return methodReturnValue;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setMethodReturnValue(String newMethodReturnValue) {
		String oldMethodReturnValue = methodReturnValue;
		methodReturnValue = newMethodReturnValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.WSDL_RETURN_VALUE_MAPPING__METHOD_RETURN_VALUE, oldMethodReturnValue, methodReturnValue));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public String getWsdlMessagePartName() {
		return wsdlMessagePartName;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setWsdlMessagePartName(String newWsdlMessagePartName) {
		String oldWsdlMessagePartName = wsdlMessagePartName;
		wsdlMessagePartName = newWsdlMessagePartName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.WSDL_RETURN_VALUE_MAPPING__WSDL_MESSAGE_PART_NAME, oldWsdlMessagePartName, wsdlMessagePartName));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public WSDLMessage getWsdlMessage() {
		return wsdlMessage;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public NotificationChain basicSetWsdlMessage(WSDLMessage newWsdlMessage, NotificationChain msgs) {
		WSDLMessage oldWsdlMessage = wsdlMessage;
		wsdlMessage = newWsdlMessage;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.WSDL_RETURN_VALUE_MAPPING__WSDL_MESSAGE, oldWsdlMessage, newWsdlMessage);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setWsdlMessage(WSDLMessage newWsdlMessage) {
		if (newWsdlMessage != wsdlMessage) {
			NotificationChain msgs = null;
			if (wsdlMessage != null)
				msgs = ((InternalEObject)wsdlMessage).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JaxrpcmapPackage.WSDL_RETURN_VALUE_MAPPING__WSDL_MESSAGE, null, msgs);
			if (newWsdlMessage != null)
				msgs = ((InternalEObject)newWsdlMessage).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JaxrpcmapPackage.WSDL_RETURN_VALUE_MAPPING__WSDL_MESSAGE, null, msgs);
			msgs = basicSetWsdlMessage(newWsdlMessage, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.WSDL_RETURN_VALUE_MAPPING__WSDL_MESSAGE, newWsdlMessage, newWsdlMessage));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case JaxrpcmapPackage.WSDL_RETURN_VALUE_MAPPING__WSDL_MESSAGE:
					return basicSetWsdlMessage(null, msgs);
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
			case JaxrpcmapPackage.WSDL_RETURN_VALUE_MAPPING__ID:
				return getId();
			case JaxrpcmapPackage.WSDL_RETURN_VALUE_MAPPING__METHOD_RETURN_VALUE:
				return getMethodReturnValue();
			case JaxrpcmapPackage.WSDL_RETURN_VALUE_MAPPING__WSDL_MESSAGE_PART_NAME:
				return getWsdlMessagePartName();
			case JaxrpcmapPackage.WSDL_RETURN_VALUE_MAPPING__WSDL_MESSAGE:
				return getWsdlMessage();
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
			case JaxrpcmapPackage.WSDL_RETURN_VALUE_MAPPING__ID:
				setId((String)newValue);
				return;
			case JaxrpcmapPackage.WSDL_RETURN_VALUE_MAPPING__METHOD_RETURN_VALUE:
				setMethodReturnValue((String)newValue);
				return;
			case JaxrpcmapPackage.WSDL_RETURN_VALUE_MAPPING__WSDL_MESSAGE_PART_NAME:
				setWsdlMessagePartName((String)newValue);
				return;
			case JaxrpcmapPackage.WSDL_RETURN_VALUE_MAPPING__WSDL_MESSAGE:
				setWsdlMessage((WSDLMessage)newValue);
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
			case JaxrpcmapPackage.WSDL_RETURN_VALUE_MAPPING__ID:
				setId(ID_EDEFAULT);
				return;
			case JaxrpcmapPackage.WSDL_RETURN_VALUE_MAPPING__METHOD_RETURN_VALUE:
				setMethodReturnValue(METHOD_RETURN_VALUE_EDEFAULT);
				return;
			case JaxrpcmapPackage.WSDL_RETURN_VALUE_MAPPING__WSDL_MESSAGE_PART_NAME:
				setWsdlMessagePartName(WSDL_MESSAGE_PART_NAME_EDEFAULT);
				return;
			case JaxrpcmapPackage.WSDL_RETURN_VALUE_MAPPING__WSDL_MESSAGE:
				setWsdlMessage((WSDLMessage)null);
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
			case JaxrpcmapPackage.WSDL_RETURN_VALUE_MAPPING__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case JaxrpcmapPackage.WSDL_RETURN_VALUE_MAPPING__METHOD_RETURN_VALUE:
				return METHOD_RETURN_VALUE_EDEFAULT == null ? methodReturnValue != null : !METHOD_RETURN_VALUE_EDEFAULT.equals(methodReturnValue);
			case JaxrpcmapPackage.WSDL_RETURN_VALUE_MAPPING__WSDL_MESSAGE_PART_NAME:
				return WSDL_MESSAGE_PART_NAME_EDEFAULT == null ? wsdlMessagePartName != null : !WSDL_MESSAGE_PART_NAME_EDEFAULT.equals(wsdlMessagePartName);
			case JaxrpcmapPackage.WSDL_RETURN_VALUE_MAPPING__WSDL_MESSAGE:
				return wsdlMessage != null;
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
		result.append(" (id: ");
		result.append(id);
		result.append(", methodReturnValue: ");
		result.append(methodReturnValue);
		result.append(", wsdlMessagePartName: ");
		result.append(wsdlMessagePartName);
		result.append(')');
		return result.toString();
	}

} //WSDLReturnValueMappingImpl
