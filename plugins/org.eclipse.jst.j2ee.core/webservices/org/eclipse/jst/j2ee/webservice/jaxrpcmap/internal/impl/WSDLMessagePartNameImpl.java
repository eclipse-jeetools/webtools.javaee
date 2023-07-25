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
package org.eclipse.jst.j2ee.webservice.jaxrpcmap.internal.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jst.j2ee.common.internal.impl.J2EEEObjectImpl;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLMessagePartName;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>WSDL Message Part Name</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.internal.impl.WSDLMessagePartNameImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.internal.impl.WSDLMessagePartNameImpl#getWsdlMessagePartName <em>Wsdl Message Part Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WSDLMessagePartNameImpl extends J2EEEObjectImpl implements WSDLMessagePartName
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
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected WSDLMessagePartNameImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
protected EClass eStaticClass() {
		return JaxrpcmapPackage.Literals.WSDL_MESSAGE_PART_NAME;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.WSDL_MESSAGE_PART_NAME__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public String getWsdlMessagePartName() {
		return wsdlMessagePartName;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public void setWsdlMessagePartName(String newWsdlMessagePartName) {
		String oldWsdlMessagePartName = wsdlMessagePartName;
		wsdlMessagePartName = newWsdlMessagePartName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.WSDL_MESSAGE_PART_NAME__WSDL_MESSAGE_PART_NAME, oldWsdlMessagePartName, wsdlMessagePartName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case JaxrpcmapPackage.WSDL_MESSAGE_PART_NAME__ID:
				return getId();
			case JaxrpcmapPackage.WSDL_MESSAGE_PART_NAME__WSDL_MESSAGE_PART_NAME:
				return getWsdlMessagePartName();
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
			case JaxrpcmapPackage.WSDL_MESSAGE_PART_NAME__ID:
				setId((String)newValue);
				return;
			case JaxrpcmapPackage.WSDL_MESSAGE_PART_NAME__WSDL_MESSAGE_PART_NAME:
				setWsdlMessagePartName((String)newValue);
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
			case JaxrpcmapPackage.WSDL_MESSAGE_PART_NAME__ID:
				setId(ID_EDEFAULT);
				return;
			case JaxrpcmapPackage.WSDL_MESSAGE_PART_NAME__WSDL_MESSAGE_PART_NAME:
				setWsdlMessagePartName(WSDL_MESSAGE_PART_NAME_EDEFAULT);
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
			case JaxrpcmapPackage.WSDL_MESSAGE_PART_NAME__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case JaxrpcmapPackage.WSDL_MESSAGE_PART_NAME__WSDL_MESSAGE_PART_NAME:
				return WSDL_MESSAGE_PART_NAME_EDEFAULT == null ? wsdlMessagePartName != null : !WSDL_MESSAGE_PART_NAME_EDEFAULT.equals(wsdlMessagePartName);
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
		result.append(" (id: "); //$NON-NLS-1$
		result.append(id);
		result.append(", wsdlMessagePartName: "); //$NON-NLS-1$
		result.append(wsdlMessagePartName);
		result.append(')');
		return result.toString();
	}

} //WSDLMessagePartNameImpl
