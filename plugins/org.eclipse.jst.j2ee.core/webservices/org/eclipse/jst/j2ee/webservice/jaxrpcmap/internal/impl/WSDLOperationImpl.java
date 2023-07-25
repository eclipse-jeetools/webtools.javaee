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
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLOperation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>WSDL Operation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.internal.impl.WSDLOperationImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.internal.impl.WSDLOperationImpl#getWsdlOperation <em>Wsdl Operation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WSDLOperationImpl extends J2EEEObjectImpl implements WSDLOperation
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
	 * The default value of the '{@link #getWsdlOperation() <em>Wsdl Operation</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getWsdlOperation()
	 * @generated
	 * @ordered
	 */
  protected static final String WSDL_OPERATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWsdlOperation() <em>Wsdl Operation</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getWsdlOperation()
	 * @generated
	 * @ordered
	 */
  protected String wsdlOperation = WSDL_OPERATION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected WSDLOperationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
protected EClass eStaticClass() {
		return JaxrpcmapPackage.Literals.WSDL_OPERATION;
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
			eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.WSDL_OPERATION__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public String getWsdlOperation() {
		return wsdlOperation;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public void setWsdlOperation(String newWsdlOperation) {
		String oldWsdlOperation = wsdlOperation;
		wsdlOperation = newWsdlOperation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.WSDL_OPERATION__WSDL_OPERATION, oldWsdlOperation, wsdlOperation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case JaxrpcmapPackage.WSDL_OPERATION__ID:
				return getId();
			case JaxrpcmapPackage.WSDL_OPERATION__WSDL_OPERATION:
				return getWsdlOperation();
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
			case JaxrpcmapPackage.WSDL_OPERATION__ID:
				setId((String)newValue);
				return;
			case JaxrpcmapPackage.WSDL_OPERATION__WSDL_OPERATION:
				setWsdlOperation((String)newValue);
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
			case JaxrpcmapPackage.WSDL_OPERATION__ID:
				setId(ID_EDEFAULT);
				return;
			case JaxrpcmapPackage.WSDL_OPERATION__WSDL_OPERATION:
				setWsdlOperation(WSDL_OPERATION_EDEFAULT);
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
			case JaxrpcmapPackage.WSDL_OPERATION__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case JaxrpcmapPackage.WSDL_OPERATION__WSDL_OPERATION:
				return WSDL_OPERATION_EDEFAULT == null ? wsdlOperation != null : !WSDL_OPERATION_EDEFAULT.equals(wsdlOperation);
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
		result.append(", wsdlOperation: "); //$NON-NLS-1$
		result.append(wsdlOperation);
		result.append(')');
		return result.toString();
	}

} //WSDLOperationImpl
