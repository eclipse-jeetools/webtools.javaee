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
package org.eclipse.jst.j2ee.webapplication.internal.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jst.j2ee.webapplication.JSPType;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;


/**
 * @generated
 */
public class JSPTypeImpl extends WebTypeImpl implements JSPType {

	/**
	 * The default value of the '{@link #getJspFile() <em>Jsp File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJspFile()
	 * @generated
	 * @ordered
	 */
	protected static final String JSP_FILE_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String jspFile = JSP_FILE_EDEFAULT;
	public JSPTypeImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WebapplicationPackage.Literals.JSP_TYPE;
	}

@Override
public boolean isJspType() {
	return true;
}
	/**
	 * @generated This field/method will be replaced during code generation 
	 * The jsp-file element contains the full path to a JSP file within the web
	 * application.

	 */
	@Override
	public String getJspFile() {
		return jspFile;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public void setJspFile(String newJspFile) {
		String oldJspFile = jspFile;
		jspFile = newJspFile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.JSP_TYPE__JSP_FILE, oldJspFile, jspFile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WebapplicationPackage.JSP_TYPE__JSP_FILE:
				return getJspFile();
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
			case WebapplicationPackage.JSP_TYPE__JSP_FILE:
				setJspFile((String)newValue);
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
			case WebapplicationPackage.JSP_TYPE__JSP_FILE:
				setJspFile(JSP_FILE_EDEFAULT);
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
			case WebapplicationPackage.JSP_TYPE__JSP_FILE:
				return JSP_FILE_EDEFAULT == null ? jspFile != null : !JSP_FILE_EDEFAULT.equals(jspFile);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (jspFile: "); //$NON-NLS-1$
		result.append(jspFile);
		result.append(')');
		return result.toString();
	}

}














