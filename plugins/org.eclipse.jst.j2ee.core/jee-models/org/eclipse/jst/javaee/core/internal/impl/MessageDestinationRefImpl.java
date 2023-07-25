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
import org.eclipse.jst.javaee.core.InjectionTarget;
import org.eclipse.jst.javaee.core.MessageDestinationRef;
import org.eclipse.jst.javaee.core.MessageDestinationUsageType;

import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Message Destination Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.MessageDestinationRefImpl#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.MessageDestinationRefImpl#getMessageDestinationRefName <em>Message Destination Ref Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.MessageDestinationRefImpl#getMessageDestinationType <em>Message Destination Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.MessageDestinationRefImpl#getMessageDestinationUsage <em>Message Destination Usage</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.MessageDestinationRefImpl#getMessageDestinationLink <em>Message Destination Link</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.MessageDestinationRefImpl#getMappedName <em>Mapped Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.MessageDestinationRefImpl#getInjectionTargets <em>Injection Targets</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.MessageDestinationRefImpl#getLookupName <em>Lookup Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.MessageDestinationRefImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MessageDestinationRefImpl extends EObjectImpl implements MessageDestinationRef {
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
	 * The default value of the '{@link #getMessageDestinationRefName() <em>Message Destination Ref Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageDestinationRefName()
	 * @generated
	 * @ordered
	 */
	protected static final String MESSAGE_DESTINATION_REF_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMessageDestinationRefName() <em>Message Destination Ref Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageDestinationRefName()
	 * @generated
	 * @ordered
	 */
	protected String messageDestinationRefName = MESSAGE_DESTINATION_REF_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getMessageDestinationType() <em>Message Destination Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageDestinationType()
	 * @generated
	 * @ordered
	 */
	protected static final String MESSAGE_DESTINATION_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMessageDestinationType() <em>Message Destination Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageDestinationType()
	 * @generated
	 * @ordered
	 */
	protected String messageDestinationType = MESSAGE_DESTINATION_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMessageDestinationUsage() <em>Message Destination Usage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageDestinationUsage()
	 * @generated
	 * @ordered
	 */
	protected static final MessageDestinationUsageType MESSAGE_DESTINATION_USAGE_EDEFAULT = MessageDestinationUsageType.CONSUMES_LITERAL;

	/**
	 * The cached value of the '{@link #getMessageDestinationUsage() <em>Message Destination Usage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageDestinationUsage()
	 * @generated
	 * @ordered
	 */
	protected MessageDestinationUsageType messageDestinationUsage = MESSAGE_DESTINATION_USAGE_EDEFAULT;

	/**
	 * This is true if the Message Destination Usage attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean messageDestinationUsageESet;

	/**
	 * The default value of the '{@link #getMessageDestinationLink() <em>Message Destination Link</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageDestinationLink()
	 * @generated
	 * @ordered
	 */
	protected static final String MESSAGE_DESTINATION_LINK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMessageDestinationLink() <em>Message Destination Link</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageDestinationLink()
	 * @generated
	 * @ordered
	 */
	protected String messageDestinationLink = MESSAGE_DESTINATION_LINK_EDEFAULT;

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
	 * The cached value of the '{@link #getInjectionTargets() <em>Injection Targets</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInjectionTargets()
	 * @generated
	 * @ordered
	 */
	protected EList<InjectionTarget> injectionTargets;

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
		return JavaeePackage.Literals.MESSAGE_DESTINATION_REF;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Description> getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList<Description>(Description.class, this, JavaeePackage.MESSAGE_DESTINATION_REF__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMessageDestinationRefName() {
		return messageDestinationRefName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMessageDestinationRefName(String newMessageDestinationRefName) {
		String oldMessageDestinationRefName = messageDestinationRefName;
		messageDestinationRefName = newMessageDestinationRefName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.MESSAGE_DESTINATION_REF__MESSAGE_DESTINATION_REF_NAME, oldMessageDestinationRefName, messageDestinationRefName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMessageDestinationType() {
		return messageDestinationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMessageDestinationType(String newMessageDestinationType) {
		String oldMessageDestinationType = messageDestinationType;
		messageDestinationType = newMessageDestinationType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.MESSAGE_DESTINATION_REF__MESSAGE_DESTINATION_TYPE, oldMessageDestinationType, messageDestinationType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MessageDestinationUsageType getMessageDestinationUsage() {
		return messageDestinationUsage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMessageDestinationUsage(MessageDestinationUsageType newMessageDestinationUsage) {
		MessageDestinationUsageType oldMessageDestinationUsage = messageDestinationUsage;
		messageDestinationUsage = newMessageDestinationUsage == null ? MESSAGE_DESTINATION_USAGE_EDEFAULT : newMessageDestinationUsage;
		boolean oldMessageDestinationUsageESet = messageDestinationUsageESet;
		messageDestinationUsageESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.MESSAGE_DESTINATION_REF__MESSAGE_DESTINATION_USAGE, oldMessageDestinationUsage, messageDestinationUsage, !oldMessageDestinationUsageESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetMessageDestinationUsage() {
		MessageDestinationUsageType oldMessageDestinationUsage = messageDestinationUsage;
		boolean oldMessageDestinationUsageESet = messageDestinationUsageESet;
		messageDestinationUsage = MESSAGE_DESTINATION_USAGE_EDEFAULT;
		messageDestinationUsageESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JavaeePackage.MESSAGE_DESTINATION_REF__MESSAGE_DESTINATION_USAGE, oldMessageDestinationUsage, MESSAGE_DESTINATION_USAGE_EDEFAULT, oldMessageDestinationUsageESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetMessageDestinationUsage() {
		return messageDestinationUsageESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMessageDestinationLink() {
		return messageDestinationLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMessageDestinationLink(String newMessageDestinationLink) {
		String oldMessageDestinationLink = messageDestinationLink;
		messageDestinationLink = newMessageDestinationLink;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.MESSAGE_DESTINATION_REF__MESSAGE_DESTINATION_LINK, oldMessageDestinationLink, messageDestinationLink));
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
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.MESSAGE_DESTINATION_REF__MAPPED_NAME, oldMappedName, mappedName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<InjectionTarget> getInjectionTargets() {
		if (injectionTargets == null) {
			injectionTargets = new EObjectContainmentEList<InjectionTarget>(InjectionTarget.class, this, JavaeePackage.MESSAGE_DESTINATION_REF__INJECTION_TARGETS);
		}
		return injectionTargets;
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
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.MESSAGE_DESTINATION_REF__LOOKUP_NAME, oldLookupName, lookupName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.MESSAGE_DESTINATION_REF__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JavaeePackage.MESSAGE_DESTINATION_REF__DESCRIPTIONS:
				return ((InternalEList<?>)getDescriptions()).basicRemove(otherEnd, msgs);
			case JavaeePackage.MESSAGE_DESTINATION_REF__INJECTION_TARGETS:
				return ((InternalEList<?>)getInjectionTargets()).basicRemove(otherEnd, msgs);
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
			case JavaeePackage.MESSAGE_DESTINATION_REF__DESCRIPTIONS:
				return getDescriptions();
			case JavaeePackage.MESSAGE_DESTINATION_REF__MESSAGE_DESTINATION_REF_NAME:
				return getMessageDestinationRefName();
			case JavaeePackage.MESSAGE_DESTINATION_REF__MESSAGE_DESTINATION_TYPE:
				return getMessageDestinationType();
			case JavaeePackage.MESSAGE_DESTINATION_REF__MESSAGE_DESTINATION_USAGE:
				return getMessageDestinationUsage();
			case JavaeePackage.MESSAGE_DESTINATION_REF__MESSAGE_DESTINATION_LINK:
				return getMessageDestinationLink();
			case JavaeePackage.MESSAGE_DESTINATION_REF__MAPPED_NAME:
				return getMappedName();
			case JavaeePackage.MESSAGE_DESTINATION_REF__INJECTION_TARGETS:
				return getInjectionTargets();
			case JavaeePackage.MESSAGE_DESTINATION_REF__LOOKUP_NAME:
				return getLookupName();
			case JavaeePackage.MESSAGE_DESTINATION_REF__ID:
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
			case JavaeePackage.MESSAGE_DESTINATION_REF__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection<? extends Description>)newValue);
				return;
			case JavaeePackage.MESSAGE_DESTINATION_REF__MESSAGE_DESTINATION_REF_NAME:
				setMessageDestinationRefName((String)newValue);
				return;
			case JavaeePackage.MESSAGE_DESTINATION_REF__MESSAGE_DESTINATION_TYPE:
				setMessageDestinationType((String)newValue);
				return;
			case JavaeePackage.MESSAGE_DESTINATION_REF__MESSAGE_DESTINATION_USAGE:
				setMessageDestinationUsage((MessageDestinationUsageType)newValue);
				return;
			case JavaeePackage.MESSAGE_DESTINATION_REF__MESSAGE_DESTINATION_LINK:
				setMessageDestinationLink((String)newValue);
				return;
			case JavaeePackage.MESSAGE_DESTINATION_REF__MAPPED_NAME:
				setMappedName((String)newValue);
				return;
			case JavaeePackage.MESSAGE_DESTINATION_REF__INJECTION_TARGETS:
				getInjectionTargets().clear();
				getInjectionTargets().addAll((Collection<? extends InjectionTarget>)newValue);
				return;
			case JavaeePackage.MESSAGE_DESTINATION_REF__LOOKUP_NAME:
				setLookupName((String)newValue);
				return;
			case JavaeePackage.MESSAGE_DESTINATION_REF__ID:
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
			case JavaeePackage.MESSAGE_DESTINATION_REF__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case JavaeePackage.MESSAGE_DESTINATION_REF__MESSAGE_DESTINATION_REF_NAME:
				setMessageDestinationRefName(MESSAGE_DESTINATION_REF_NAME_EDEFAULT);
				return;
			case JavaeePackage.MESSAGE_DESTINATION_REF__MESSAGE_DESTINATION_TYPE:
				setMessageDestinationType(MESSAGE_DESTINATION_TYPE_EDEFAULT);
				return;
			case JavaeePackage.MESSAGE_DESTINATION_REF__MESSAGE_DESTINATION_USAGE:
				unsetMessageDestinationUsage();
				return;
			case JavaeePackage.MESSAGE_DESTINATION_REF__MESSAGE_DESTINATION_LINK:
				setMessageDestinationLink(MESSAGE_DESTINATION_LINK_EDEFAULT);
				return;
			case JavaeePackage.MESSAGE_DESTINATION_REF__MAPPED_NAME:
				setMappedName(MAPPED_NAME_EDEFAULT);
				return;
			case JavaeePackage.MESSAGE_DESTINATION_REF__INJECTION_TARGETS:
				getInjectionTargets().clear();
				return;
			case JavaeePackage.MESSAGE_DESTINATION_REF__LOOKUP_NAME:
				setLookupName(LOOKUP_NAME_EDEFAULT);
				return;
			case JavaeePackage.MESSAGE_DESTINATION_REF__ID:
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
			case JavaeePackage.MESSAGE_DESTINATION_REF__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case JavaeePackage.MESSAGE_DESTINATION_REF__MESSAGE_DESTINATION_REF_NAME:
				return MESSAGE_DESTINATION_REF_NAME_EDEFAULT == null ? messageDestinationRefName != null : !MESSAGE_DESTINATION_REF_NAME_EDEFAULT.equals(messageDestinationRefName);
			case JavaeePackage.MESSAGE_DESTINATION_REF__MESSAGE_DESTINATION_TYPE:
				return MESSAGE_DESTINATION_TYPE_EDEFAULT == null ? messageDestinationType != null : !MESSAGE_DESTINATION_TYPE_EDEFAULT.equals(messageDestinationType);
			case JavaeePackage.MESSAGE_DESTINATION_REF__MESSAGE_DESTINATION_USAGE:
				return isSetMessageDestinationUsage();
			case JavaeePackage.MESSAGE_DESTINATION_REF__MESSAGE_DESTINATION_LINK:
				return MESSAGE_DESTINATION_LINK_EDEFAULT == null ? messageDestinationLink != null : !MESSAGE_DESTINATION_LINK_EDEFAULT.equals(messageDestinationLink);
			case JavaeePackage.MESSAGE_DESTINATION_REF__MAPPED_NAME:
				return MAPPED_NAME_EDEFAULT == null ? mappedName != null : !MAPPED_NAME_EDEFAULT.equals(mappedName);
			case JavaeePackage.MESSAGE_DESTINATION_REF__INJECTION_TARGETS:
				return injectionTargets != null && !injectionTargets.isEmpty();
			case JavaeePackage.MESSAGE_DESTINATION_REF__LOOKUP_NAME:
				return LOOKUP_NAME_EDEFAULT == null ? lookupName != null : !LOOKUP_NAME_EDEFAULT.equals(lookupName);
			case JavaeePackage.MESSAGE_DESTINATION_REF__ID:
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
		result.append(" (messageDestinationRefName: "); //$NON-NLS-1$
		result.append(messageDestinationRefName);
		result.append(", messageDestinationType: "); //$NON-NLS-1$
		result.append(messageDestinationType);
		result.append(", messageDestinationUsage: "); //$NON-NLS-1$
		if (messageDestinationUsageESet) result.append(messageDestinationUsage); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", messageDestinationLink: "); //$NON-NLS-1$
		result.append(messageDestinationLink);
		result.append(", mappedName: "); //$NON-NLS-1$
		result.append(mappedName);
		result.append(", lookupName: "); //$NON-NLS-1$
		result.append(lookupName);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //MessageDestinationRefImpl