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



/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Message Destination</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * @version J2EE1.4
 * The message-destinationType specifies a message
 * destination. The logical destination described by this
 * element is mapped to a physical destination by the Deployer.
 * 
 * The message destination element contains:
 * 
 *         - an optional description
 *         - an optional display-name
 *         - an optional icon
 *         - a message destination name which must be unique
 *           among message destination names within the same
 *           Deployment File.
 * 
 * Example:
 * 
 * <message-destination>
 *         <message-destination-name>CorporateStocks
 *         </message-destination-name>
 * </message-destination>
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.common.MessageDestination#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.common.CommonPackage#getMessageDestination()
 * @model
 * @generated
 * @since 1.0 */
public interface MessageDestination extends CompatibilityDescriptionGroup{
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The message-destination-name element specifies a
	 *         name for a message destination.  This name must be
	 *         unique among the names of message destinations
	 *         within the Deployment File.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.jst.j2ee.common.CommonPackage#getMessageDestination_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.common.MessageDestination#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // MessageDestination
