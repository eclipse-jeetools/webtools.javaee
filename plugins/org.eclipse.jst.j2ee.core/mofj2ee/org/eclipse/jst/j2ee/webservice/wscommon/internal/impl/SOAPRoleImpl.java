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
package org.eclipse.jst.j2ee.webservice.wscommon.internal.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jst.j2ee.common.internal.impl.J2EEEObjectImpl;
import org.eclipse.jst.j2ee.webservice.wscommon.SOAPRole;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SOAP Role</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wscommon.internal.impl.SOAPRoleImpl#getSoapRole <em>Soap Role</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SOAPRoleImpl extends J2EEEObjectImpl implements SOAPRole
{
	/**
	 * The default value of the '{@link #getSoapRole() <em>Soap Role</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getSoapRole()
	 * @generated
	 * @ordered
	 */
  protected static final String SOAP_ROLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSoapRole() <em>Soap Role</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getSoapRole()
	 * @generated
	 * @ordered
	 */
  protected String soapRole = SOAP_ROLE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected SOAPRoleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
protected EClass eStaticClass() {
		return WscommonPackage.Literals.SOAP_ROLE;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public String getSoapRole() {
		return soapRole;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public void setSoapRole(String newSoapRole) {
		String oldSoapRole = soapRole;
		soapRole = newSoapRole;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WscommonPackage.SOAP_ROLE__SOAP_ROLE, oldSoapRole, soapRole));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WscommonPackage.SOAP_ROLE__SOAP_ROLE:
				return getSoapRole();
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
			case WscommonPackage.SOAP_ROLE__SOAP_ROLE:
				setSoapRole((String)newValue);
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
			case WscommonPackage.SOAP_ROLE__SOAP_ROLE:
				setSoapRole(SOAP_ROLE_EDEFAULT);
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
			case WscommonPackage.SOAP_ROLE__SOAP_ROLE:
				return SOAP_ROLE_EDEFAULT == null ? soapRole != null : !SOAP_ROLE_EDEFAULT.equals(soapRole);
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
		result.append(" (soapRole: "); //$NON-NLS-1$
		result.append(soapRole);
		result.append(')');
		return result.toString();
	}

} //SOAPRoleImpl
