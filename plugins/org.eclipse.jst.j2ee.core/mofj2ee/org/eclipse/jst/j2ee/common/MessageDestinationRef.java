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
package org.eclipse.jst.j2ee.common;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Message Destination Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * @version J2EE1.4
 * The message-destination-ref element contains a declaration
 * of Deployment Component's reference to a message destination
 * associated with a resource in Deployment Component's
 * environment. It consists of:
 * 
 *         - an optional description
 *         - the message destination reference name
 *         - the message destination type
 *         - a specification as to whether the
 *           destination is used for
 *           consuming or producing messages, or both
 *         - a link to the message destination
 * 
 * Examples:
 * 
 * <message-destination-ref>
 *         <message-destination-ref-name>jms/StockQueue
 *         </message-destination-ref-name>
 *         <message-destination-type>javax.jms.Queue
 *         </message-destination-type>
 *         <message-destination-usage>Consumes
 *         </message-destination-usage>
 *         <message-destination-link>CorporateStocks
 *         </message-destination-link>
 * </message-destination-ref>
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.internal.common.MessageDestinationRef#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.common.MessageDestinationRef#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.common.MessageDestinationRef#getUsage <em>Usage</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.common.MessageDestinationRef#getLink <em>Link</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.common.MessageDestinationRef#getDescriptions <em>Descriptions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.internal.common.CommonPackage#getMessageDestinationRef()
 * @model 
 * @generated
 */
public interface MessageDestinationRef extends EObject{
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.jst.j2ee.internal.common.CommonPackage#getMessageDestinationRef_Name()
	 * @model 
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.common.MessageDestinationRef#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see org.eclipse.jst.j2ee.internal.common.CommonPackage#getMessageDestinationRef_Type()
	 * @model 
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.common.MessageDestinationRef#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Usage</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.jst.j2ee.internal.common.MessageDestinationUsageType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Usage</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Usage</em>' attribute.
	 * @see org.eclipse.jst.j2ee.internal.common.MessageDestinationUsageType
	 * @see #setUsage(MessageDestinationUsageType)
	 * @see org.eclipse.jst.j2ee.internal.common.CommonPackage#getMessageDestinationRef_Usage()
	 * @model 
	 * @generated
	 */
	MessageDestinationUsageType getUsage();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.common.MessageDestinationRef#getUsage <em>Usage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Usage</em>' attribute.
	 * @see org.eclipse.jst.j2ee.internal.common.MessageDestinationUsageType
	 * @see #getUsage()
	 * @generated
	 */
	void setUsage(MessageDestinationUsageType value);

	/**
	 * Returns the value of the '<em><b>Link</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Link</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Link</em>' attribute.
	 * @see #setLink(String)
	 * @see org.eclipse.jst.j2ee.internal.common.CommonPackage#getMessageDestinationRef_Link()
	 * @model 
	 * @generated
	 */
	String getLink();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.common.MessageDestinationRef#getLink <em>Link</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Link</em>' attribute.
	 * @see #getLink()
	 * @generated
	 */
	void setLink(String value);

	/**
	 * Returns the value of the '<em><b>Descriptions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.internal.common.Description}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Descriptions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Descriptions</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.internal.common.CommonPackage#getMessageDestinationRef_Descriptions()
	 * @model type="org.eclipse.jst.j2ee.internal.common.Description" containment="true"
	 * @generated
	 */
	EList getDescriptions();

} // MessageDestinationRef
