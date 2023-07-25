/*******************************************************************************
 * Copyright (c) 2009, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.jca;

import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Inbound Resource Adapter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The inbound-resourceadapterType specifies information
 *         about an inbound resource adapter. This contains information
 *         specific to the implementation of the resource adapter
 *         library as specified through the messageadapter element.
 *         
 *         @since Java EE 6, Connector 1.6
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.jca.InboundResourceAdapter#getMessageadapter <em>Messageadapter</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.InboundResourceAdapter#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getInboundResourceAdapter()
 * @extends JavaEEObject
 * @generated
 */
public interface InboundResourceAdapter extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Messageadapter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Messageadapter</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Messageadapter</em>' containment reference.
	 * @see #setMessageadapter(MessageAdapter)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getInboundResourceAdapter_Messageadapter()
	 * @generated
	 */
	MessageAdapter getMessageadapter();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.InboundResourceAdapter#getMessageadapter <em>Messageadapter</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Messageadapter</em>' containment reference.
	 * @see #getMessageadapter()
	 * @generated
	 */
	void setMessageadapter(MessageAdapter value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getInboundResourceAdapter_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.InboundResourceAdapter#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // InboundResourceAdapter
