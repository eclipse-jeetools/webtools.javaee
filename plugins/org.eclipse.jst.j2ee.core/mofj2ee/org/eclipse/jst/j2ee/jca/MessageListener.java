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
 * A representation of the model object '<em><b>Message Listener</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * @version J2EE1.4
 * The messagelistenerType specifies information about a
 * specific message listener supported by the messaging
 * resource adapter. It contains information on the Java type
 * of the message listener interface and an activation
 * specification.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.internal.jca.MessageListener#getMessageListenerType <em>Message Listener Type</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.jca.MessageListener#getActivationSpec <em>Activation Spec</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.internal.jca.JcaPackage#getMessageListener()
 * @model 
 * @generated
 */
public interface MessageListener extends EObject{
	/**
	 * Returns the value of the '<em><b>Message Listener Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Listener Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The element messagelistener-type specifies the fully
	 *         qualified name of the Java type of a message
	 *         listener interface.
	 * 
	 *         Example:
	 * 
	 *           <messagelistener-type>javax.jms.MessageListener
	 *           </messagelistener-type>
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Message Listener Type</em>' attribute.
	 * @see #setMessageListenerType(String)
	 * @see org.eclipse.jst.j2ee.internal.jca.JcaPackage#getMessageListener_MessageListenerType()
	 * @model 
	 * @generated
	 */
	String getMessageListenerType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.jca.MessageListener#getMessageListenerType <em>Message Listener Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Message Listener Type</em>' attribute.
	 * @see #getMessageListenerType()
	 * @generated
	 */
	void setMessageListenerType(String value);

	/**
	 * Returns the value of the '<em><b>Activation Spec</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activation Spec</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activation Spec</em>' containment reference.
	 * @see #setActivationSpec(ActivationSpec)
	 * @see org.eclipse.jst.j2ee.internal.jca.JcaPackage#getMessageListener_ActivationSpec()
	 * @model containment="true" required="true"
	 * @generated
	 */
	ActivationSpec getActivationSpec();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.jca.MessageListener#getActivationSpec <em>Activation Spec</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activation Spec</em>' containment reference.
	 * @see #getActivationSpec()
	 * @generated
	 */
	void setActivationSpec(ActivationSpec value);

} // MessageListener
