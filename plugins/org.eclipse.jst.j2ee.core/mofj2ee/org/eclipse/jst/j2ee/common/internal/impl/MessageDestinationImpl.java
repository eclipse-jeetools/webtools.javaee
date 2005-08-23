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
package org.eclipse.jst.j2ee.common.internal.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.MessageDestination;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Message Destination</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.common.internal.impl.MessageDestinationImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MessageDestinationImpl extends CompatibilityDescriptionGroupImpl implements MessageDestination {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MessageDestinationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return CommonPackage.eINSTANCE.getMessageDestination();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.MESSAGE_DESTINATION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case CommonPackage.MESSAGE_DESTINATION__ICONS:
					return ((InternalEList)getIcons()).basicRemove(otherEnd, msgs);
				case CommonPackage.MESSAGE_DESTINATION__DISPLAY_NAMES:
					return ((InternalEList)getDisplayNames()).basicRemove(otherEnd, msgs);
				case CommonPackage.MESSAGE_DESTINATION__DESCRIPTIONS:
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
			case CommonPackage.MESSAGE_DESTINATION__ICONS:
				return getIcons();
			case CommonPackage.MESSAGE_DESTINATION__DISPLAY_NAMES:
				return getDisplayNames();
			case CommonPackage.MESSAGE_DESTINATION__DESCRIPTIONS:
				return getDescriptions();
			case CommonPackage.MESSAGE_DESTINATION__SMALL_ICON:
				return getSmallIcon();
			case CommonPackage.MESSAGE_DESTINATION__LARGE_ICON:
				return getLargeIcon();
			case CommonPackage.MESSAGE_DESTINATION__DESCRIPTION:
				return getDescription();
			case CommonPackage.MESSAGE_DESTINATION__DISPLAY_NAME:
				return getDisplayName();
			case CommonPackage.MESSAGE_DESTINATION__NAME:
				return getName();
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
			case CommonPackage.MESSAGE_DESTINATION__ICONS:
				getIcons().clear();
				getIcons().addAll((Collection)newValue);
				return;
			case CommonPackage.MESSAGE_DESTINATION__DISPLAY_NAMES:
				getDisplayNames().clear();
				getDisplayNames().addAll((Collection)newValue);
				return;
			case CommonPackage.MESSAGE_DESTINATION__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
				return;
			case CommonPackage.MESSAGE_DESTINATION__SMALL_ICON:
				setSmallIcon((String)newValue);
				return;
			case CommonPackage.MESSAGE_DESTINATION__LARGE_ICON:
				setLargeIcon((String)newValue);
				return;
			case CommonPackage.MESSAGE_DESTINATION__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case CommonPackage.MESSAGE_DESTINATION__DISPLAY_NAME:
				setDisplayName((String)newValue);
				return;
			case CommonPackage.MESSAGE_DESTINATION__NAME:
				setName((String)newValue);
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
			case CommonPackage.MESSAGE_DESTINATION__ICONS:
				getIcons().clear();
				return;
			case CommonPackage.MESSAGE_DESTINATION__DISPLAY_NAMES:
				getDisplayNames().clear();
				return;
			case CommonPackage.MESSAGE_DESTINATION__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case CommonPackage.MESSAGE_DESTINATION__SMALL_ICON:
				setSmallIcon(SMALL_ICON_EDEFAULT);
				return;
			case CommonPackage.MESSAGE_DESTINATION__LARGE_ICON:
				setLargeIcon(LARGE_ICON_EDEFAULT);
				return;
			case CommonPackage.MESSAGE_DESTINATION__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case CommonPackage.MESSAGE_DESTINATION__DISPLAY_NAME:
				setDisplayName(DISPLAY_NAME_EDEFAULT);
				return;
			case CommonPackage.MESSAGE_DESTINATION__NAME:
				setName(NAME_EDEFAULT);
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
			case CommonPackage.MESSAGE_DESTINATION__ICONS:
				return icons != null && !icons.isEmpty();
			case CommonPackage.MESSAGE_DESTINATION__DISPLAY_NAMES:
				return displayNames != null && !displayNames.isEmpty();
			case CommonPackage.MESSAGE_DESTINATION__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case CommonPackage.MESSAGE_DESTINATION__SMALL_ICON:
				return SMALL_ICON_EDEFAULT == null ? smallIcon != null : !SMALL_ICON_EDEFAULT.equals(smallIcon);
			case CommonPackage.MESSAGE_DESTINATION__LARGE_ICON:
				return LARGE_ICON_EDEFAULT == null ? largeIcon != null : !LARGE_ICON_EDEFAULT.equals(largeIcon);
			case CommonPackage.MESSAGE_DESTINATION__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case CommonPackage.MESSAGE_DESTINATION__DISPLAY_NAME:
				return DISPLAY_NAME_EDEFAULT == null ? displayName != null : !DISPLAY_NAME_EDEFAULT.equals(displayName);
			case CommonPackage.MESSAGE_DESTINATION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //MessageDestinationImpl
