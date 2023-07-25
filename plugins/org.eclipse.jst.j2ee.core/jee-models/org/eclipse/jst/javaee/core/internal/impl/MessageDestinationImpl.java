/*******************************************************************************
 * Copyright (c) 2007, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.core.internal.impl;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.DisplayName;
import org.eclipse.jst.javaee.core.Icon;
import org.eclipse.jst.javaee.core.MessageDestination;

import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Message Destination</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.MessageDestinationImpl#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.MessageDestinationImpl#getDisplayNames <em>Display Names</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.MessageDestinationImpl#getIcons <em>Icons</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.MessageDestinationImpl#getMessageDestinationName <em>Message Destination Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.MessageDestinationImpl#getMappedName <em>Mapped Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.MessageDestinationImpl#getLookupName <em>Lookup Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.MessageDestinationImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MessageDestinationImpl extends EObjectImpl implements MessageDestination {
	/**
	 * The cached value of the '{@link #getDescriptions() <em>Descriptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptions()
	 * @generated
	 * @ordered
	 */
	protected EList<Description> descriptions;

	/**
	 * The cached value of the '{@link #getDisplayNames() <em>Display Names</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayNames()
	 * @generated
	 * @ordered
	 */
	protected EList<DisplayName> displayNames;

	/**
	 * The cached value of the '{@link #getIcons() <em>Icons</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIcons()
	 * @generated
	 * @ordered
	 */
	protected EList<Icon> icons;

	/**
	 * The default value of the '{@link #getMessageDestinationName() <em>Message Destination Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageDestinationName()
	 * @generated
	 * @ordered
	 */
	protected static final String MESSAGE_DESTINATION_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMessageDestinationName() <em>Message Destination Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageDestinationName()
	 * @generated
	 * @ordered
	 */
	protected String messageDestinationName = MESSAGE_DESTINATION_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getMappedName() <em>Mapped Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMappedName()
	 * @generated
	 * @ordered
	 */
	protected static final String MAPPED_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMappedName() <em>Mapped Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMappedName()
	 * @generated
	 * @ordered
	 */
	protected String mappedName = MAPPED_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getLookupName() <em>Lookup Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLookupName()
	 * @generated
	 * @ordered
	 */
	protected static final String LOOKUP_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLookupName() <em>Lookup Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLookupName()
	 * @generated
	 * @ordered
	 */
	protected String lookupName = LOOKUP_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

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
	@Override
	protected EClass eStaticClass() {
		return JavaeePackage.Literals.MESSAGE_DESTINATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Description> getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList<Description>(Description.class, this, JavaeePackage.MESSAGE_DESTINATION__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<DisplayName> getDisplayNames() {
		if (displayNames == null) {
			displayNames = new EObjectContainmentEList<DisplayName>(DisplayName.class, this, JavaeePackage.MESSAGE_DESTINATION__DISPLAY_NAMES);
		}
		return displayNames;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Icon> getIcons() {
		if (icons == null) {
			icons = new EObjectContainmentEList<Icon>(Icon.class, this, JavaeePackage.MESSAGE_DESTINATION__ICONS);
		}
		return icons;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMessageDestinationName() {
		return messageDestinationName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMessageDestinationName(String newMessageDestinationName) {
		String oldMessageDestinationName = messageDestinationName;
		messageDestinationName = newMessageDestinationName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.MESSAGE_DESTINATION__MESSAGE_DESTINATION_NAME, oldMessageDestinationName, messageDestinationName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMappedName() {
		return mappedName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMappedName(String newMappedName) {
		String oldMappedName = mappedName;
		mappedName = newMappedName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.MESSAGE_DESTINATION__MAPPED_NAME, oldMappedName, mappedName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLookupName() {
		return lookupName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLookupName(String newLookupName) {
		String oldLookupName = lookupName;
		lookupName = newLookupName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.MESSAGE_DESTINATION__LOOKUP_NAME, oldLookupName, lookupName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.MESSAGE_DESTINATION__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JavaeePackage.MESSAGE_DESTINATION__DESCRIPTIONS:
				return ((InternalEList<?>)getDescriptions()).basicRemove(otherEnd, msgs);
			case JavaeePackage.MESSAGE_DESTINATION__DISPLAY_NAMES:
				return ((InternalEList<?>)getDisplayNames()).basicRemove(otherEnd, msgs);
			case JavaeePackage.MESSAGE_DESTINATION__ICONS:
				return ((InternalEList<?>)getIcons()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case JavaeePackage.MESSAGE_DESTINATION__DESCRIPTIONS:
				return getDescriptions();
			case JavaeePackage.MESSAGE_DESTINATION__DISPLAY_NAMES:
				return getDisplayNames();
			case JavaeePackage.MESSAGE_DESTINATION__ICONS:
				return getIcons();
			case JavaeePackage.MESSAGE_DESTINATION__MESSAGE_DESTINATION_NAME:
				return getMessageDestinationName();
			case JavaeePackage.MESSAGE_DESTINATION__MAPPED_NAME:
				return getMappedName();
			case JavaeePackage.MESSAGE_DESTINATION__LOOKUP_NAME:
				return getLookupName();
			case JavaeePackage.MESSAGE_DESTINATION__ID:
				return getId();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case JavaeePackage.MESSAGE_DESTINATION__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection<? extends Description>)newValue);
				return;
			case JavaeePackage.MESSAGE_DESTINATION__DISPLAY_NAMES:
				getDisplayNames().clear();
				getDisplayNames().addAll((Collection<? extends DisplayName>)newValue);
				return;
			case JavaeePackage.MESSAGE_DESTINATION__ICONS:
				getIcons().clear();
				getIcons().addAll((Collection<? extends Icon>)newValue);
				return;
			case JavaeePackage.MESSAGE_DESTINATION__MESSAGE_DESTINATION_NAME:
				setMessageDestinationName((String)newValue);
				return;
			case JavaeePackage.MESSAGE_DESTINATION__MAPPED_NAME:
				setMappedName((String)newValue);
				return;
			case JavaeePackage.MESSAGE_DESTINATION__LOOKUP_NAME:
				setLookupName((String)newValue);
				return;
			case JavaeePackage.MESSAGE_DESTINATION__ID:
				setId((String)newValue);
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
			case JavaeePackage.MESSAGE_DESTINATION__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case JavaeePackage.MESSAGE_DESTINATION__DISPLAY_NAMES:
				getDisplayNames().clear();
				return;
			case JavaeePackage.MESSAGE_DESTINATION__ICONS:
				getIcons().clear();
				return;
			case JavaeePackage.MESSAGE_DESTINATION__MESSAGE_DESTINATION_NAME:
				setMessageDestinationName(MESSAGE_DESTINATION_NAME_EDEFAULT);
				return;
			case JavaeePackage.MESSAGE_DESTINATION__MAPPED_NAME:
				setMappedName(MAPPED_NAME_EDEFAULT);
				return;
			case JavaeePackage.MESSAGE_DESTINATION__LOOKUP_NAME:
				setLookupName(LOOKUP_NAME_EDEFAULT);
				return;
			case JavaeePackage.MESSAGE_DESTINATION__ID:
				setId(ID_EDEFAULT);
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
			case JavaeePackage.MESSAGE_DESTINATION__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case JavaeePackage.MESSAGE_DESTINATION__DISPLAY_NAMES:
				return displayNames != null && !displayNames.isEmpty();
			case JavaeePackage.MESSAGE_DESTINATION__ICONS:
				return icons != null && !icons.isEmpty();
			case JavaeePackage.MESSAGE_DESTINATION__MESSAGE_DESTINATION_NAME:
				return MESSAGE_DESTINATION_NAME_EDEFAULT == null ? messageDestinationName != null : !MESSAGE_DESTINATION_NAME_EDEFAULT.equals(messageDestinationName);
			case JavaeePackage.MESSAGE_DESTINATION__MAPPED_NAME:
				return MAPPED_NAME_EDEFAULT == null ? mappedName != null : !MAPPED_NAME_EDEFAULT.equals(mappedName);
			case JavaeePackage.MESSAGE_DESTINATION__LOOKUP_NAME:
				return LOOKUP_NAME_EDEFAULT == null ? lookupName != null : !LOOKUP_NAME_EDEFAULT.equals(lookupName);
			case JavaeePackage.MESSAGE_DESTINATION__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (messageDestinationName: "); //$NON-NLS-1$
		result.append(messageDestinationName);
		result.append(", mappedName: "); //$NON-NLS-1$
		result.append(mappedName);
		result.append(", lookupName: "); //$NON-NLS-1$
		result.append(lookupName);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //MessageDestinationImpl