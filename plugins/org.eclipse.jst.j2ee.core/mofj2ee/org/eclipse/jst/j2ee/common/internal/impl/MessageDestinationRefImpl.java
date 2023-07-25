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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.MessageDestinationRef;
import org.eclipse.jst.j2ee.common.MessageDestinationUsageType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Message Destination Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.common.internal.impl.MessageDestinationRefImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.common.internal.impl.MessageDestinationRefImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.common.internal.impl.MessageDestinationRefImpl#getUsage <em>Usage</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.common.internal.impl.MessageDestinationRefImpl#getLink <em>Link</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.common.internal.impl.MessageDestinationRefImpl#getDescriptions <em>Descriptions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MessageDestinationRefImpl extends J2EEEObjectImpl implements MessageDestinationRef {
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
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected String type = TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getUsage() <em>Usage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsage()
	 * @generated
	 * @ordered
	 */
	protected static final MessageDestinationUsageType USAGE_EDEFAULT = MessageDestinationUsageType.CONSUMES_LITERAL;

	/**
	 * The cached value of the '{@link #getUsage() <em>Usage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsage()
	 * @generated
	 * @ordered
	 */
	protected MessageDestinationUsageType usage = USAGE_EDEFAULT;
	/**
	 * This is true if the Auth attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @ordered
	 */
	protected boolean useESet = false;
	/**
	 * The default value of the '{@link #getLink() <em>Link</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLink()
	 * @generated
	 * @ordered
	 */
	protected static final String LINK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLink() <em>Link</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLink()
	 * @generated
	 * @ordered
	 */
	protected String link = LINK_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDescriptions() <em>Descriptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptions()
	 * @generated
	 * @ordered
	 */
	protected EList descriptions = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MessageDestinationRefImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CommonPackage.Literals.MESSAGE_DESTINATION_REF;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.MESSAGE_DESTINATION_REF__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.MESSAGE_DESTINATION_REF__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MessageDestinationUsageType getUsage() {
		return usage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUsage(MessageDestinationUsageType newUsage) {
		MessageDestinationUsageType oldUsage = usage;
		usage = newUsage == null ? USAGE_EDEFAULT : newUsage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.MESSAGE_DESTINATION_REF__USAGE, oldUsage, usage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public void unsetUsage() {
		MessageDestinationUsageType oldUsage = usage;
		boolean oldUsageESet = useESet;
		usage = USAGE_EDEFAULT;
		useESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CommonPackage.MESSAGE_DESTINATION_REF__USAGE, oldUsage, USAGE_EDEFAULT, oldUsageESet));
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public boolean isSetUse() {
		return useESet;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLink() {
		return link;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLink(String newLink) {
		String oldLink = link;
		link = newLink;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.MESSAGE_DESTINATION_REF__LINK, oldLink, link));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList(Description.class, this, CommonPackage.MESSAGE_DESTINATION_REF__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CommonPackage.MESSAGE_DESTINATION_REF__DESCRIPTIONS:
				return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
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
			case CommonPackage.MESSAGE_DESTINATION_REF__NAME:
				return getName();
			case CommonPackage.MESSAGE_DESTINATION_REF__TYPE:
				return getType();
			case CommonPackage.MESSAGE_DESTINATION_REF__USAGE:
				return getUsage();
			case CommonPackage.MESSAGE_DESTINATION_REF__LINK:
				return getLink();
			case CommonPackage.MESSAGE_DESTINATION_REF__DESCRIPTIONS:
				return getDescriptions();
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
			case CommonPackage.MESSAGE_DESTINATION_REF__NAME:
				setName((String)newValue);
				return;
			case CommonPackage.MESSAGE_DESTINATION_REF__TYPE:
				setType((String)newValue);
				return;
			case CommonPackage.MESSAGE_DESTINATION_REF__USAGE:
				setUsage((MessageDestinationUsageType)newValue);
				return;
			case CommonPackage.MESSAGE_DESTINATION_REF__LINK:
				setLink((String)newValue);
				return;
			case CommonPackage.MESSAGE_DESTINATION_REF__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
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
			case CommonPackage.MESSAGE_DESTINATION_REF__NAME:
				setName(NAME_EDEFAULT);
				return;
			case CommonPackage.MESSAGE_DESTINATION_REF__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case CommonPackage.MESSAGE_DESTINATION_REF__USAGE:
				setUsage(USAGE_EDEFAULT);
				return;
			case CommonPackage.MESSAGE_DESTINATION_REF__LINK:
				setLink(LINK_EDEFAULT);
				return;
			case CommonPackage.MESSAGE_DESTINATION_REF__DESCRIPTIONS:
				getDescriptions().clear();
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
			case CommonPackage.MESSAGE_DESTINATION_REF__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case CommonPackage.MESSAGE_DESTINATION_REF__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case CommonPackage.MESSAGE_DESTINATION_REF__USAGE:
				return usage != USAGE_EDEFAULT;
			case CommonPackage.MESSAGE_DESTINATION_REF__LINK:
				return LINK_EDEFAULT == null ? link != null : !LINK_EDEFAULT.equals(link);
			case CommonPackage.MESSAGE_DESTINATION_REF__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
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
		result.append(" (name: "); //$NON-NLS-1$
		result.append(name);
		result.append(", type: "); //$NON-NLS-1$
		result.append(type);
		result.append(", usage: "); //$NON-NLS-1$
		result.append(usage);
		result.append(", link: "); //$NON-NLS-1$
		result.append(link);
		result.append(')');
		return result.toString();
	}

} //MessageDestinationRefImpl
