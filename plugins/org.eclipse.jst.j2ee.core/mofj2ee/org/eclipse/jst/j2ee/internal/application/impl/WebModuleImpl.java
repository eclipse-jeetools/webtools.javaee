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
package org.eclipse.jst.j2ee.internal.application.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.application.WebModule;
import org.eclipse.jst.j2ee.internal.application.ApplicationPackage;


/**
 * Contains the web-uri and context-root of a web application module.

 */
public class WebModuleImpl extends ModuleImpl implements WebModule, Module {

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
	protected EClass eStaticClass() {
		return ApplicationPackage.eINSTANCE.getWebModule();
	}

public boolean isWebModule() {
	return true;
}
	/**
	 * @generated This field/method will be replaced during code generation 
	 * The context-root element specifies the context root of a web application

	 */
	public String getContextRoot() {
		return contextRoot;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
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
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case ApplicationPackage.WEB_MODULE__APPLICATION:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, ApplicationPackage.WEB_MODULE__APPLICATION, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case ApplicationPackage.WEB_MODULE__APPLICATION:
					return eBasicSetContainer(null, ApplicationPackage.WEB_MODULE__APPLICATION, msgs);
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
	public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
		if (eContainerFeatureID >= 0) {
			switch (eContainerFeatureID) {
				case ApplicationPackage.WEB_MODULE__APPLICATION:
					return eContainer.eInverseRemove(this, ApplicationPackage.APPLICATION__MODULES, Application.class, msgs);
				default:
					return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case ApplicationPackage.WEB_MODULE__URI:
				return getUri();
			case ApplicationPackage.WEB_MODULE__ALT_DD:
				return getAltDD();
			case ApplicationPackage.WEB_MODULE__APPLICATION:
				return getApplication();
			case ApplicationPackage.WEB_MODULE__CONTEXT_ROOT:
				return getContextRoot();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case ApplicationPackage.WEB_MODULE__URI:
				return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
			case ApplicationPackage.WEB_MODULE__ALT_DD:
				return ALT_DD_EDEFAULT == null ? altDD != null : !ALT_DD_EDEFAULT.equals(altDD);
			case ApplicationPackage.WEB_MODULE__APPLICATION:
				return getApplication() != null;
			case ApplicationPackage.WEB_MODULE__CONTEXT_ROOT:
				return CONTEXT_ROOT_EDEFAULT == null ? contextRoot != null : !CONTEXT_ROOT_EDEFAULT.equals(contextRoot);
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case ApplicationPackage.WEB_MODULE__URI:
				setUri((String)newValue);
				return;
			case ApplicationPackage.WEB_MODULE__ALT_DD:
				setAltDD((String)newValue);
				return;
			case ApplicationPackage.WEB_MODULE__APPLICATION:
				setApplication((Application)newValue);
				return;
			case ApplicationPackage.WEB_MODULE__CONTEXT_ROOT:
				setContextRoot((String)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case ApplicationPackage.WEB_MODULE__URI:
				setUri(URI_EDEFAULT);
				return;
			case ApplicationPackage.WEB_MODULE__ALT_DD:
				setAltDD(ALT_DD_EDEFAULT);
				return;
			case ApplicationPackage.WEB_MODULE__APPLICATION:
				setApplication((Application)null);
				return;
			case ApplicationPackage.WEB_MODULE__CONTEXT_ROOT:
				setContextRoot(CONTEXT_ROOT_EDEFAULT);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (contextRoot: "); //$NON-NLS-1$
		result.append(contextRoot);
		result.append(')');
		return result.toString();
	}

}





