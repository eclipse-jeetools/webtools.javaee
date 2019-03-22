/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
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
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jst.j2ee.webapplication.ExceptionTypeErrorPage;
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
	@Override
	protected EClass eStaticClass() {
		return WebapplicationPackage.Literals.EXCEPTION_TYPE_ERROR_PAGE;
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
@Override
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
			InternalEObject oldExceptionType = (InternalEObject)exceptionType;
			exceptionType = (JavaClass)eResolveProxy(oldExceptionType);
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
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WebapplicationPackage.EXCEPTION_TYPE_ERROR_PAGE__EXCEPTION_TYPE:
				if (resolve) return getExceptionType();
				return basicGetExceptionType();
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
			case WebapplicationPackage.EXCEPTION_TYPE_ERROR_PAGE__EXCEPTION_TYPE:
				setExceptionType((JavaClass)newValue);
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
			case WebapplicationPackage.EXCEPTION_TYPE_ERROR_PAGE__EXCEPTION_TYPE:
				setExceptionType((JavaClass)null);
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
			case WebapplicationPackage.EXCEPTION_TYPE_ERROR_PAGE__EXCEPTION_TYPE:
				return exceptionType != null;
		}
		return super.eIsSet(featureID);
	}

}















