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
package org.eclipse.jst.j2ee.internal.webapplication.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jst.j2ee.webapplication.ExceptionTypeErrorPage;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;


/**
 * @generated
 */
public class ExceptionTypeErrorPageImpl extends ErrorPageImpl implements ExceptionTypeErrorPage {

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected JavaClass exceptionType = null;
	public ExceptionTypeErrorPageImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return WebapplicationPackage.eINSTANCE.getExceptionTypeErrorPage();
	}

/**
 * Helper method to set the ExceptionType to a JavaClass having a fully qualified
 * name specified by the parameter
 */
public String getExceptionTypeName() {
	getExceptionType();
	return exceptionType == null ? null : exceptionType.getQualifiedName();
}/**
 * Helper method to set the ExceptionType to a JavaClass having a fully qualified
 * name specified by the parameter
 */ 
public boolean isExceptionType() {
	return true;
}
public void setExceptionTypeName(String exceptionTypeName) {
	setExceptionType(JavaRefFactory.eINSTANCE.createClassRef(exceptionTypeName));
}
/**
 * @deprecated Use @link{#setExceptionTypeName(String)}
 */
public void setExceptionTypeName(String exceptionTypeName, ResourceSet context) {
	setExceptionTypeName(exceptionTypeName);
}
	/**
	 * @generated This field/method will be replaced during code generation 
	 * The exception type contains a fully qualified class name of a Java exception type.
	 */
	public JavaClass getExceptionType() {
		if (exceptionType != null && exceptionType.eIsProxy()) {
			JavaClass oldExceptionType = exceptionType;
			exceptionType = (JavaClass)eResolveProxy((InternalEObject)exceptionType);
			if (exceptionType != oldExceptionType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, WebapplicationPackage.EXCEPTION_TYPE_ERROR_PAGE__EXCEPTION_TYPE, oldExceptionType, exceptionType));
			}
		}
		return exceptionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass basicGetExceptionType() {
		return exceptionType;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setExceptionType(JavaClass newExceptionType) {
		JavaClass oldExceptionType = exceptionType;
		exceptionType = newExceptionType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.EXCEPTION_TYPE_ERROR_PAGE__EXCEPTION_TYPE, oldExceptionType, exceptionType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case WebapplicationPackage.EXCEPTION_TYPE_ERROR_PAGE__WEB_APP:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, WebapplicationPackage.EXCEPTION_TYPE_ERROR_PAGE__WEB_APP, msgs);
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
				case WebapplicationPackage.EXCEPTION_TYPE_ERROR_PAGE__WEB_APP:
					return eBasicSetContainer(null, WebapplicationPackage.EXCEPTION_TYPE_ERROR_PAGE__WEB_APP, msgs);
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
				case WebapplicationPackage.EXCEPTION_TYPE_ERROR_PAGE__WEB_APP:
					return eContainer.eInverseRemove(this, WebapplicationPackage.WEB_APP__ERROR_PAGES, WebApp.class, msgs);
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
			case WebapplicationPackage.EXCEPTION_TYPE_ERROR_PAGE__LOCATION:
				return getLocation();
			case WebapplicationPackage.EXCEPTION_TYPE_ERROR_PAGE__WEB_APP:
				return getWebApp();
			case WebapplicationPackage.EXCEPTION_TYPE_ERROR_PAGE__EXCEPTION_TYPE:
				if (resolve) return getExceptionType();
				return basicGetExceptionType();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case WebapplicationPackage.EXCEPTION_TYPE_ERROR_PAGE__LOCATION:
				return LOCATION_EDEFAULT == null ? location != null : !LOCATION_EDEFAULT.equals(location);
			case WebapplicationPackage.EXCEPTION_TYPE_ERROR_PAGE__WEB_APP:
				return getWebApp() != null;
			case WebapplicationPackage.EXCEPTION_TYPE_ERROR_PAGE__EXCEPTION_TYPE:
				return exceptionType != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case WebapplicationPackage.EXCEPTION_TYPE_ERROR_PAGE__LOCATION:
				setLocation((String)newValue);
				return;
			case WebapplicationPackage.EXCEPTION_TYPE_ERROR_PAGE__WEB_APP:
				setWebApp((WebApp)newValue);
				return;
			case WebapplicationPackage.EXCEPTION_TYPE_ERROR_PAGE__EXCEPTION_TYPE:
				setExceptionType((JavaClass)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case WebapplicationPackage.EXCEPTION_TYPE_ERROR_PAGE__LOCATION:
				setLocation(LOCATION_EDEFAULT);
				return;
			case WebapplicationPackage.EXCEPTION_TYPE_ERROR_PAGE__WEB_APP:
				setWebApp((WebApp)null);
				return;
			case WebapplicationPackage.EXCEPTION_TYPE_ERROR_PAGE__EXCEPTION_TYPE:
				setExceptionType((JavaClass)null);
				return;
		}
		eDynamicUnset(eFeature);
	}

}















