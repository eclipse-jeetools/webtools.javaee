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
import org.eclipse.jst.j2ee.webservice.wsdd.ServletLink;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Servlet Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.internal.impl.ServletLinkImpl#getServletLink <em>Servlet Link</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ServletLinkImpl extends BeanLinkImpl implements ServletLink
{
	/**
	 * The default value of the '{@link #getServletLink() <em>Servlet Link</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getServletLink()
	 * @generated
	 * @ordered
	 */
  protected static final String SERVLET_LINK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getServletLink() <em>Servlet Link</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getServletLink()
	 * @generated
	 * @ordered
	 */
  protected String servletLink = SERVLET_LINK_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected ServletLinkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
protected EClass eStaticClass() {
		return WsddPackage.Literals.SERVLET_LINK;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public String getServletLink() {
		return servletLink;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public void setServletLink(String newServletLink) {
		String oldServletLink = servletLink;
		servletLink = newServletLink;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WsddPackage.SERVLET_LINK__SERVLET_LINK, oldServletLink, servletLink));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WsddPackage.SERVLET_LINK__SERVLET_LINK:
				return getServletLink();
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
			case WsddPackage.SERVLET_LINK__SERVLET_LINK:
				setServletLink((String)newValue);
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
			case WsddPackage.SERVLET_LINK__SERVLET_LINK:
				setServletLink(SERVLET_LINK_EDEFAULT);
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
			case WsddPackage.SERVLET_LINK__SERVLET_LINK:
				return SERVLET_LINK_EDEFAULT == null ? servletLink != null : !SERVLET_LINK_EDEFAULT.equals(servletLink);
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
		result.append(" (servletLink: "); //$NON-NLS-1$
		result.append(servletLink);
		result.append(')');
		return result.toString();
	}

} //ServletLinkImpl
