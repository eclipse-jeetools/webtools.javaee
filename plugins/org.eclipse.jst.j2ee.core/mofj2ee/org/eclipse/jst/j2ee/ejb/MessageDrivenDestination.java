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
package org.eclipse.jst.j2ee.ejb;

import org.eclipse.emf.ecore.EObject;

/**
 * The message-driven-destination element provides advice to the Deployer as to whether a message-driven bean is intended for a Queue or a Topic. The declaration consists of: the type of the message-driven bean's intended destination and an optional declaration of whether a durable or non-durable subscription should be used if the destination-type is javax.jms.Topic.
 */
public interface MessageDrivenDestination extends EObject{
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Type attribute
	 * The destination-type element specifies the type of the JMS destination. The
	 * type is specified by the Java interface expected to be implemented by the
	 * destination.
	 * 
	 * The destination-type element must be one of the two following: javax.jms.Queue, 
	 * javax.jms.Topic
	 */
	DestinationType getType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.ejb.MessageDrivenDestination#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see org.eclipse.jst.j2ee.internal.ejb.DestinationType
	 * @see #isSetType()
	 * @see #unsetType()
	 * @see #getType()
	 * @generated
	 */
	void setType(DestinationType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.j2ee.internal.ejb.MessageDrivenDestination#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetType()
	 * @see #getType()
	 * @see #setType(DestinationType)
	 * @generated
	 */
	void unsetType();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.j2ee.internal.ejb.MessageDrivenDestination#getType <em>Type</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Type</em>' attribute is set.
	 * @see #unsetType()
	 * @see #getType()
	 * @see #setType(DestinationType)
	 * @generated
	 */
	boolean isSetType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the SubscriptionDurability attribute
	 * The subscription-durability element specifies whether a JMS topic subscription
	 * is intended to be durable or nondurable.
	 * 
	 * The subscription-durability element must be one of the two following:  Durable, 
	 * NonDurable

	 */
	SubscriptionDurabilityKind getSubscriptionDurability();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.ejb.MessageDrivenDestination#getSubscriptionDurability <em>Subscription Durability</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subscription Durability</em>' attribute.
	 * @see org.eclipse.jst.j2ee.internal.ejb.SubscriptionDurabilityKind
	 * @see #isSetSubscriptionDurability()
	 * @see #unsetSubscriptionDurability()
	 * @see #getSubscriptionDurability()
	 * @generated
	 */
	void setSubscriptionDurability(SubscriptionDurabilityKind value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.j2ee.internal.ejb.MessageDrivenDestination#getSubscriptionDurability <em>Subscription Durability</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSubscriptionDurability()
	 * @see #getSubscriptionDurability()
	 * @see #setSubscriptionDurability(SubscriptionDurabilityKind)
	 * @generated
	 */
	void unsetSubscriptionDurability();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.j2ee.internal.ejb.MessageDrivenDestination#getSubscriptionDurability <em>Subscription Durability</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Subscription Durability</em>' attribute is set.
	 * @see #unsetSubscriptionDurability()
	 * @see #getSubscriptionDurability()
	 * @see #setSubscriptionDurability(SubscriptionDurabilityKind)
	 * @generated
	 */
	boolean isSetSubscriptionDurability();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The Bean reference
	 */
	MessageDriven getBean();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the Bean reference
	 */
	void setBean(MessageDriven value);

}





