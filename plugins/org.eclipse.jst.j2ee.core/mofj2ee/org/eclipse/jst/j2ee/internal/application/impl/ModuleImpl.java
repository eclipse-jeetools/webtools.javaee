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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.internal.application.ApplicationPackage;


/**
 * The module element represents a single J2EE module and contains an ejb, java, or web element, which indicates the module type and contains a path to the module file, and an optional alt-dd element, which specifies an optional URI to the post-assembly version of the deployment descriptor. The application deployment descriptor must have one module element for each J2EE module in the application package.

 */
public class ModuleImpl extends EObjectImpl implements Module, EObject {

	/**
	 * The default value of the '{@link #getUri() <em>Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUri()
	 * @generated
	 * @ordered
	 */
	protected static final String URI_EDEFAULT = null;

	protected static final char FORWARD_SLASH = '/';
	protected static final char BACKWARD_SLASH = '\\';
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String uri = URI_EDEFAULT;
	/**
	 * The default value of the '{@link #getAltDD() <em>Alt DD</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAltDD()
	 * @generated
	 * @ordered
	 */
	protected static final String ALT_DD_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String altDD = ALT_DD_EDEFAULT;
	public ModuleImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return ApplicationPackage.eINSTANCE.getModule();
	}

public boolean isConnectorModule() {
	return false;
}
/* Returns false by default
 *
 */
public boolean isEjbModule() {
	return false;
}
/* 
 * Returns false by default
 */
public boolean isJavaModule() {
	return false;
}
/* 
 * Returns false by default
 */
public boolean isWebModule() {
	return false;
}
	/**
	 * @generated This field/method will be replaced during code generation 
	 * Specifies the URI of a module, relative to the top level of the application package.

	 */
	public String getUri() {
		return uri;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setUri(String newUri) {
		String oldUri = uri;
		uri = newUri;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.MODULE__URI, oldUri, uri));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The alt-dd element specifies an optional URI to the post-assembly version of the deployment descriptor file for a particular J2EE module. The URI must specify the full pathname of the deployment descriptor file relative to the application's root directory. If alt-dd is not specified, the deployer must read the deployment descriptor from the default location and file name required by the respective component specification.

	 */
	public String getAltDD() {
		return altDD;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setAltDD(String newAltDD) {
		String oldAltDD = altDD;
		altDD = newAltDD;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.MODULE__ALT_DD, oldAltDD, altDD));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public Application getApplication() {
		if (eContainerFeatureID != ApplicationPackage.MODULE__APPLICATION) return null;
		return (Application)eContainer;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setApplication(Application newApplication) {
		if (newApplication != eContainer || (eContainerFeatureID != ApplicationPackage.MODULE__APPLICATION && newApplication != null)) {
			if (EcoreUtil.isAncestor(this, newApplication))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newApplication != null)
				msgs = ((InternalEObject)newApplication).eInverseAdd(this, ApplicationPackage.APPLICATION__MODULES, Application.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newApplication, ApplicationPackage.MODULE__APPLICATION, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.MODULE__APPLICATION, newApplication, newApplication));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case ApplicationPackage.MODULE__APPLICATION:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, ApplicationPackage.MODULE__APPLICATION, msgs);
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
				case ApplicationPackage.MODULE__APPLICATION:
					return eBasicSetContainer(null, ApplicationPackage.MODULE__APPLICATION, msgs);
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
				case ApplicationPackage.MODULE__APPLICATION:
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
			case ApplicationPackage.MODULE__URI:
				return getUri();
			case ApplicationPackage.MODULE__ALT_DD:
				return getAltDD();
			case ApplicationPackage.MODULE__APPLICATION:
				return getApplication();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case ApplicationPackage.MODULE__URI:
				return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
			case ApplicationPackage.MODULE__ALT_DD:
				return ALT_DD_EDEFAULT == null ? altDD != null : !ALT_DD_EDEFAULT.equals(altDD);
			case ApplicationPackage.MODULE__APPLICATION:
				return getApplication() != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case ApplicationPackage.MODULE__URI:
				setUri((String)newValue);
				return;
			case ApplicationPackage.MODULE__ALT_DD:
				setAltDD((String)newValue);
				return;
			case ApplicationPackage.MODULE__APPLICATION:
				setApplication((Application)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case ApplicationPackage.MODULE__URI:
				setUri(URI_EDEFAULT);
				return;
			case ApplicationPackage.MODULE__ALT_DD:
				setAltDD(ALT_DD_EDEFAULT);
				return;
			case ApplicationPackage.MODULE__APPLICATION:
				setApplication((Application)null);
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
		result.append(" (uri: "); //$NON-NLS-1$
		result.append(uri);
		result.append(", altDD: "); //$NON-NLS-1$
		result.append(altDD);
		result.append(')');
		return result.toString();
	}

}





