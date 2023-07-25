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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jst.j2ee.webservice.wsdd.EJBLink;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EJB Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.internal.impl.EJBLinkImpl#getEjbLink <em>Ejb Link</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EJBLinkImpl extends BeanLinkImpl implements EJBLink
{
	/**
	 * The default value of the '{@link #getEjbLink() <em>Ejb Link</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getEjbLink()
	 * @generated
	 * @ordered
	 */
  protected static final String EJB_LINK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEjbLink() <em>Ejb Link</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getEjbLink()
	 * @generated
	 * @ordered
	 */
  protected String ejbLink = EJB_LINK_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected EJBLinkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
protected EClass eStaticClass() {
		return WsddPackage.Literals.EJB_LINK;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public String getEjbLink() {
		return ejbLink;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public void setEjbLink(String newEjbLink) {
		String oldEjbLink = ejbLink;
		ejbLink = newEjbLink;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WsddPackage.EJB_LINK__EJB_LINK, oldEjbLink, ejbLink));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WsddPackage.EJB_LINK__EJB_LINK:
				return getEjbLink();
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
			case WsddPackage.EJB_LINK__EJB_LINK:
				setEjbLink((String)newValue);
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
			case WsddPackage.EJB_LINK__EJB_LINK:
				setEjbLink(EJB_LINK_EDEFAULT);
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
			case WsddPackage.EJB_LINK__EJB_LINK:
				return EJB_LINK_EDEFAULT == null ? ejbLink != null : !EJB_LINK_EDEFAULT.equals(ejbLink);
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
		result.append(" (ejbLink: "); //$NON-NLS-1$
		result.append(ejbLink);
		result.append(')');
		return result.toString();
	}

} //EJBLinkImpl
