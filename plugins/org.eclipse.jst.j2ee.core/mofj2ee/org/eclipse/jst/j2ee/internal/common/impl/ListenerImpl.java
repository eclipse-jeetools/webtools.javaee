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
package org.eclipse.jst.j2ee.internal.common.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jst.j2ee.common.Listener;
import org.eclipse.jst.j2ee.internal.common.CommonPackage;


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
	protected EClass eStaticClass() {
		return CommonPackage.eINSTANCE.getListener();
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The listener-class element declares a class in the application must be registered as a web application listener bean.
	 */
	public JavaClass getListenerClass() {
		if (listenerClass != null && listenerClass.eIsProxy()) {
			JavaClass oldListenerClass = listenerClass;
			listenerClass = (JavaClass)eResolveProxy((InternalEObject)listenerClass);
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
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case CommonPackage.LISTENER__ICONS:
					return ((InternalEList)getIcons()).basicRemove(otherEnd, msgs);
				case CommonPackage.LISTENER__DISPLAY_NAMES:
					return ((InternalEList)getDisplayNames()).basicRemove(otherEnd, msgs);
				case CommonPackage.LISTENER__DESCRIPTIONS:
					return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
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
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonPackage.LISTENER__ICONS:
				return getIcons();
			case CommonPackage.LISTENER__DISPLAY_NAMES:
				return getDisplayNames();
			case CommonPackage.LISTENER__DESCRIPTIONS:
				return getDescriptions();
			case CommonPackage.LISTENER__SMALL_ICON:
				return getSmallIcon();
			case CommonPackage.LISTENER__LARGE_ICON:
				return getLargeIcon();
			case CommonPackage.LISTENER__DESCRIPTION:
				return getDescription();
			case CommonPackage.LISTENER__DISPLAY_NAME:
				return getDisplayName();
			case CommonPackage.LISTENER__LISTENER_CLASS:
				if (resolve) return getListenerClass();
				return basicGetListenerClass();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonPackage.LISTENER__ICONS:
				return icons != null && !icons.isEmpty();
			case CommonPackage.LISTENER__DISPLAY_NAMES:
				return displayNames != null && !displayNames.isEmpty();
			case CommonPackage.LISTENER__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case CommonPackage.LISTENER__SMALL_ICON:
				return SMALL_ICON_EDEFAULT == null ? smallIcon != null : !SMALL_ICON_EDEFAULT.equals(smallIcon);
			case CommonPackage.LISTENER__LARGE_ICON:
				return LARGE_ICON_EDEFAULT == null ? largeIcon != null : !LARGE_ICON_EDEFAULT.equals(largeIcon);
			case CommonPackage.LISTENER__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case CommonPackage.LISTENER__DISPLAY_NAME:
				return DISPLAY_NAME_EDEFAULT == null ? displayName != null : !DISPLAY_NAME_EDEFAULT.equals(displayName);
			case CommonPackage.LISTENER__LISTENER_CLASS:
				return listenerClass != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonPackage.LISTENER__ICONS:
				getIcons().clear();
				getIcons().addAll((Collection)newValue);
				return;
			case CommonPackage.LISTENER__DISPLAY_NAMES:
				getDisplayNames().clear();
				getDisplayNames().addAll((Collection)newValue);
				return;
			case CommonPackage.LISTENER__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
				return;
			case CommonPackage.LISTENER__SMALL_ICON:
				setSmallIcon((String)newValue);
				return;
			case CommonPackage.LISTENER__LARGE_ICON:
				setLargeIcon((String)newValue);
				return;
			case CommonPackage.LISTENER__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case CommonPackage.LISTENER__DISPLAY_NAME:
				setDisplayName((String)newValue);
				return;
			case CommonPackage.LISTENER__LISTENER_CLASS:
				setListenerClass((JavaClass)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonPackage.LISTENER__ICONS:
				getIcons().clear();
				return;
			case CommonPackage.LISTENER__DISPLAY_NAMES:
				getDisplayNames().clear();
				return;
			case CommonPackage.LISTENER__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case CommonPackage.LISTENER__SMALL_ICON:
				setSmallIcon(SMALL_ICON_EDEFAULT);
				return;
			case CommonPackage.LISTENER__LARGE_ICON:
				setLargeIcon(LARGE_ICON_EDEFAULT);
				return;
			case CommonPackage.LISTENER__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case CommonPackage.LISTENER__DISPLAY_NAME:
				setDisplayName(DISPLAY_NAME_EDEFAULT);
				return;
			case CommonPackage.LISTENER__LISTENER_CLASS:
				setListenerClass((JavaClass)null);
				return;
		}
		eDynamicUnset(eFeature);
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
public String getListenerClassName() {
	getListenerClass();
	return (listenerClass == null) ? null : listenerClass.getQualifiedName();
}
/**
 * Sets the listener class name.
 */
public void setListenerClassName(String listenerClassName) {
	eSet(CommonPackage.eINSTANCE.getListener_ListenerClass(), createClassRef(listenerClassName));
}
} //ListenerImpl















