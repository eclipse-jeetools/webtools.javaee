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
package org.eclipse.jst.j2ee.application.internal.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.application.WebModule;


/**
 * Contains the web-uri and context-root of a web application module.
 * @generated
 */
public class WebModuleImpl extends ModuleImpl implements WebModule {

	/**
	 * The default value of the '{@link #getContextRoot() <em>Context Root</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContextRoot()
	 * @generated
	 * @ordered
	 */
	protected static final String CONTEXT_ROOT_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String contextRoot = CONTEXT_ROOT_EDEFAULT;
	public WebModuleImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ApplicationPackage.Literals.WEB_MODULE;
	}

@Override
public boolean isWebModule() {
	return true;
}
	/**
	 * @generated This field/method will be replaced during code generation 
	 * The context-root element specifies the context root of a web application

	 */
	@Override
	public String getContextRoot() {
		return contextRoot;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public void setContextRoot(String newContextRoot) {
		String oldContextRoot = contextRoot;
		contextRoot = newContextRoot;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.WEB_MODULE__CONTEXT_ROOT, oldContextRoot, contextRoot));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ApplicationPackage.WEB_MODULE__CONTEXT_ROOT:
				return getContextRoot();
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
			case ApplicationPackage.WEB_MODULE__CONTEXT_ROOT:
				setContextRoot((String)newValue);
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
			case ApplicationPackage.WEB_MODULE__CONTEXT_ROOT:
				setContextRoot(CONTEXT_ROOT_EDEFAULT);
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
			case ApplicationPackage.WEB_MODULE__CONTEXT_ROOT:
				return CONTEXT_ROOT_EDEFAULT == null ? contextRoot != null : !CONTEXT_ROOT_EDEFAULT.equals(contextRoot);
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
		result.append(" (contextRoot: "); //$NON-NLS-1$
		result.append(contextRoot);
		result.append(')');
		return result.toString();
	}

}





