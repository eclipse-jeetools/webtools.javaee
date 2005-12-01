/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.common.internal.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.IconType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Icon Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.common.internal.impl.IconTypeImpl#getSmallIcon <em>Small Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.common.internal.impl.IconTypeImpl#getLargeIcon <em>Large Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.common.internal.impl.IconTypeImpl#getLang <em>Lang</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IconTypeImpl extends EObjectImpl implements IconType {
	/**
	 * The default value of the '{@link #getSmallIcon() <em>Small Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSmallIcon()
	 * @generated
	 * @ordered
	 */
	protected static final String SMALL_ICON_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSmallIcon() <em>Small Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSmallIcon()
	 * @generated
	 * @ordered
	 */
	protected String smallIcon = SMALL_ICON_EDEFAULT;

	/**
	 * The default value of the '{@link #getLargeIcon() <em>Large Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLargeIcon()
	 * @generated
	 * @ordered
	 */
	protected static final String LARGE_ICON_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLargeIcon() <em>Large Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLargeIcon()
	 * @generated
	 * @ordered
	 */
	protected String largeIcon = LARGE_ICON_EDEFAULT;

	/**
	 * The default value of the '{@link #getLang() <em>Lang</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLang()
	 * @generated
	 * @ordered
	 */
	protected static final String LANG_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLang() <em>Lang</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLang()
	 * @generated
	 * @ordered
	 */
	protected String lang = LANG_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IconTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return CommonPackage.eINSTANCE.getIconType();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSmallIcon() {
		return smallIcon;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSmallIcon(String newSmallIcon) {
		String oldSmallIcon = smallIcon;
		smallIcon = newSmallIcon;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.ICON_TYPE__SMALL_ICON, oldSmallIcon, smallIcon));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLargeIcon() {
		return largeIcon;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLargeIcon(String newLargeIcon) {
		String oldLargeIcon = largeIcon;
		largeIcon = newLargeIcon;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.ICON_TYPE__LARGE_ICON, oldLargeIcon, largeIcon));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLang() {
		return lang;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLang(String newLang) {
		String oldLang = lang;
		lang = newLang;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.ICON_TYPE__LANG, oldLang, lang));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonPackage.ICON_TYPE__SMALL_ICON:
				return getSmallIcon();
			case CommonPackage.ICON_TYPE__LARGE_ICON:
				return getLargeIcon();
			case CommonPackage.ICON_TYPE__LANG:
				return getLang();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonPackage.ICON_TYPE__SMALL_ICON:
				setSmallIcon((String)newValue);
				return;
			case CommonPackage.ICON_TYPE__LARGE_ICON:
				setLargeIcon((String)newValue);
				return;
			case CommonPackage.ICON_TYPE__LANG:
				setLang((String)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonPackage.ICON_TYPE__SMALL_ICON:
				setSmallIcon(SMALL_ICON_EDEFAULT);
				return;
			case CommonPackage.ICON_TYPE__LARGE_ICON:
				setLargeIcon(LARGE_ICON_EDEFAULT);
				return;
			case CommonPackage.ICON_TYPE__LANG:
				setLang(LANG_EDEFAULT);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonPackage.ICON_TYPE__SMALL_ICON:
				return SMALL_ICON_EDEFAULT == null ? smallIcon != null : !SMALL_ICON_EDEFAULT.equals(smallIcon);
			case CommonPackage.ICON_TYPE__LARGE_ICON:
				return LARGE_ICON_EDEFAULT == null ? largeIcon != null : !LARGE_ICON_EDEFAULT.equals(largeIcon);
			case CommonPackage.ICON_TYPE__LANG:
				return LANG_EDEFAULT == null ? lang != null : !LANG_EDEFAULT.equals(lang);
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (smallIcon: ");
		result.append(smallIcon);
		result.append(", largeIcon: ");
		result.append(largeIcon);
		result.append(", lang: ");
		result.append(lang);
		result.append(')');
		return result.toString();
	}

} //IconTypeImpl
