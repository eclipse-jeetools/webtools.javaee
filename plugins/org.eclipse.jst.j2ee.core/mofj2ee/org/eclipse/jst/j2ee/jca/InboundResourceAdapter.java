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
package org.eclipse.jst.j2ee.jca;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Inbound Resource Adapter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * @since J2EE1.4
 * The inbound-resourceadapterType specifies information
 * about an inbound resource adapter. This contains information
 * specific to the implementation of the resource adapter
 * library as specified through the messageadapter element.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.internal.jca.InboundResourceAdapter#getMessageAdapter <em>Message Adapter</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.internal.jca.JcaPackage#getInboundResourceAdapter()
 * @model 
 * @generated
 */
public interface InboundResourceAdapter extends EObject{
	/**
	 * Returns the value of the '<em><b>Message Adapter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Adapter</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The messagelistener-type element content must be
	 *         unique in the messageadapter. Several messagelisteners
	 *         can not use the same messagelistener-type.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Message Adapter</em>' containment reference.
	 * @see #setMessageAdapter(MessageAdapter)
	 * @see org.eclipse.jst.j2ee.internal.jca.JcaPackage#getInboundResourceAdapter_MessageAdapter()
	 * @model containment="true"
	 * @generated
	 */
	MessageAdapter getMessageAdapter();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.jca.InboundResourceAdapter#getMessageAdapter <em>Message Adapter</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Message Adapter</em>' containment reference.
	 * @see #getMessageAdapter()
	 * @generated
	 */
	void setMessageAdapter(MessageAdapter value);

} // InboundResourceAdapter
