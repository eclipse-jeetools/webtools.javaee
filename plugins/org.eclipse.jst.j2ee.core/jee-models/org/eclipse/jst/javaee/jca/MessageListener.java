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
 * A representation of the model object '<em><b>Message Listener</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The messagelistenerType specifies information about a
 *         specific message listener supported by the messaging
 *         resource adapter. It contains information on the Java type
 *         of the message listener interface and an activation
 *         specification.
 *         
 *         @since Java EE 6, Connector 1.6
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.jca.MessageListener#getMessagelistenerType <em>Messagelistener Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.MessageListener#getActivationspec <em>Activationspec</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.MessageListener#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getMessageListener()
 * @extends JavaEEObject
 * @generated
 */
public interface MessageListener extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Messagelistener Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * [
	 *             The element messagelistener-type specifies the fully
	 *             qualified name of the Java type of a message
	 *             listener interface.
	 *             
	 *             Example:
	 *             
	 *             	<messagelistener-type>javax.jms.MessageListener
	 *             	</messagelistener-type>
	 *             
	 * 
	 * 			@since Java EE 6, Connector 1.6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Messagelistener Type</em>' attribute.
	 * @see #setMessagelistenerType(String)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getMessageListener_MessagelistenerType()
	 * @generated
	 */
	String getMessagelistenerType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.MessageListener#getMessagelistenerType <em>Messagelistener Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Messagelistener Type</em>' attribute.
	 * @see #getMessagelistenerType()
	 * @generated
	 */
	void setMessagelistenerType(String value);

	/**
	 * Returns the value of the '<em><b>Activationspec</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activationspec</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activationspec</em>' containment reference.
	 * @see #setActivationspec(ActivationSpec)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getMessageListener_Activationspec()
	 * @generated
	 */
	ActivationSpec getActivationspec();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.MessageListener#getActivationspec <em>Activationspec</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activationspec</em>' containment reference.
	 * @see #getActivationspec()
	 * @generated
	 */
	void setActivationspec(ActivationSpec value);

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
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getMessageListener_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.MessageListener#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // MessageListener
