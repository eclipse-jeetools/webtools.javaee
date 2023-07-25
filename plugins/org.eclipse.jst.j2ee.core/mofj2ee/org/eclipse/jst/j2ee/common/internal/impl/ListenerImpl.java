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
package org.eclipse.jst.j2ee.common.internal.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.Listener;


/**
 * Declares a class in the application must be registered as a web application listener bean.
 */
public class ListenerImpl extends CompatibilityDescriptionGroupImpl implements Listener {
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected JavaClass listenerClass = null;
		/**
		 * @generated This field/method will be replaced during code generation.
		 */
	protected ListenerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CommonPackage.Literals.LISTENER;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The listener-class element declares a class in the application must be registered as a web application listener bean.
	 */
	@Override
	public JavaClass getListenerClass() {
		if (listenerClass != null && listenerClass.eIsProxy()) {
			InternalEObject oldListenerClass = (InternalEObject)listenerClass;
			listenerClass = (JavaClass)eResolveProxy(oldListenerClass);
			if (listenerClass != oldListenerClass) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CommonPackage.LISTENER__LISTENER_CLASS, oldListenerClass, listenerClass));
			}
		}
		return listenerClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass basicGetListenerClass() {
		return listenerClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public void setListenerClass(JavaClass newListenerClass) {
		JavaClass oldListenerClass = listenerClass;
		listenerClass = newListenerClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.LISTENER__LISTENER_CLASS, oldListenerClass, listenerClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommonPackage.LISTENER__LISTENER_CLASS:
				if (resolve) return getListenerClass();
				return basicGetListenerClass();
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
			case CommonPackage.LISTENER__LISTENER_CLASS:
				setListenerClass((JavaClass)newValue);
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
			case CommonPackage.LISTENER__LISTENER_CLASS:
				setListenerClass((JavaClass)null);
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
			case CommonPackage.LISTENER__LISTENER_CLASS:
				return listenerClass != null;
		}
		return super.eIsSet(featureID);
	}

/**
 * createClassRef - return a JavaURL reference to the named Java class
 */
public JavaClass createClassRef(String targetName) {
	return JavaRefFactory.eINSTANCE.createClassRef(targetName);
}
/**
 * Get the fully qualified class name for the listener.
 */
@Override
public String getListenerClassName() {
	getListenerClass();
	return (listenerClass == null) ? null : listenerClass.getQualifiedName();
}
/**
 * Sets the listener class name.
 */
@Override
public void setListenerClassName(String listenerClassName) {
	eSet(CommonPackage.eINSTANCE.getListener_ListenerClass(), createClassRef(listenerClassName));
}
} //ListenerImpl















