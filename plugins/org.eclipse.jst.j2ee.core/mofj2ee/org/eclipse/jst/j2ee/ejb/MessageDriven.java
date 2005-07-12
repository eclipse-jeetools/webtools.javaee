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
import org.eclipse.jem.java.JavaClass;




/**
 * @generated
 */
public interface MessageDriven extends EnterpriseBean{
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the TransactionType attribute
	 * The transaction-type element specifies an enterprise bean's transaction
	 * management type.
	 * 
	 * The transaction-type element must be one of the two following: Bean, Container
	 */
	TransactionType getTransactionType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.ejb.MessageDriven#getTransactionType <em>Transaction Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transaction Type</em>' attribute.
	 * @see org.eclipse.jst.j2ee.internal.ejb.TransactionType
	 * @see #isSetTransactionType()
	 * @see #unsetTransactionType()
	 * @see #getTransactionType()
	 * @generated
	 */
	void setTransactionType(TransactionType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.j2ee.internal.ejb.MessageDriven#getTransactionType <em>Transaction Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTransactionType()
	 * @see #getTransactionType()
	 * @see #setTransactionType(TransactionType)
	 * @generated
	 */
	void unsetTransactionType();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.j2ee.internal.ejb.MessageDriven#getTransactionType <em>Transaction Type</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Transaction Type</em>' attribute is set.
	 * @see #unsetTransactionType()
	 * @see #getTransactionType()
	 * @see #setTransactionType(TransactionType)
	 * @generated
	 */
	boolean isSetTransactionType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the MessageSelector attribute
	 * The message-selector element is used to specify the JMS message
	 * selector to be used in determining which messages a message-driven
	 * bean is to receive.
	 * 
	 * Example value: 
	 * JMSType = `car' AND color = `blue' AND weight > 2500

	 */
	String getMessageSelector();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the MessageSelector attribute
	 */
	void setMessageSelector(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the AcknowledgeMode attribute
	 * an optional declaration of the acknowledgment mode for the message-driven bean
	 * if bean-managed transaction demarcation is used (ie. self.transactionType =
	 * Bean)
	 * 
	 * The acknowledge-mode specifies whether JMS AUTO_ACKNOWLEDGE or
	 * DUPS_OK_ACKNOWLEDGE message acknowledgment semantics should be used for the
	 * onMessage message of a message-driven bean that uses bean managed transaction
	 * demarcation.
	 * 
	 * The acknowledge-mode must be one of the two following:  Auto-acknowledge,
	 * Dups-ok-acknowledge

	 */
	AcknowledgeMode getAcknowledgeMode();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.ejb.MessageDriven#getAcknowledgeMode <em>Acknowledge Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Acknowledge Mode</em>' attribute.
	 * @see org.eclipse.jst.j2ee.internal.ejb.AcknowledgeMode
	 * @see #isSetAcknowledgeMode()
	 * @see #unsetAcknowledgeMode()
	 * @see #getAcknowledgeMode()
	 * @generated
	 */
	void setAcknowledgeMode(AcknowledgeMode value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.j2ee.internal.ejb.MessageDriven#getAcknowledgeMode <em>Acknowledge Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetAcknowledgeMode()
	 * @see #getAcknowledgeMode()
	 * @see #setAcknowledgeMode(AcknowledgeMode)
	 * @generated
	 */
	void unsetAcknowledgeMode();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.j2ee.internal.ejb.MessageDriven#getAcknowledgeMode <em>Acknowledge Mode</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Acknowledge Mode</em>' attribute is set.
	 * @see #unsetAcknowledgeMode()
	 * @see #getAcknowledgeMode()
	 * @see #setAcknowledgeMode(AcknowledgeMode)
	 * @generated
	 */
	boolean isSetAcknowledgeMode();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The Destination reference
	 * An optional declaration of the intended destination type of the message-driven
	 * bean
	 */
	MessageDrivenDestination getDestination();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the Destination reference
	 */
	void setDestination(MessageDrivenDestination value);

	/**
	 * Returns the value of the '<em><b>Activation Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activation Config</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @version J2EE1.4
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Activation Config</em>' containment reference.
	 * @see #setActivationConfig(ActivationConfig)
	 * @see org.eclipse.jst.j2ee.internal.ejb.EjbPackage#getMessageDriven_ActivationConfig()
	 * @model containment="true"
	 * @generated
	 */
	ActivationConfig getActivationConfig();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.ejb.MessageDriven#getActivationConfig <em>Activation Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activation Config</em>' containment reference.
	 * @see #getActivationConfig()
	 * @generated
	 */
	void setActivationConfig(ActivationConfig value);

	/**
	 * Returns the value of the '<em><b>Message Destination</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Destination</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @version J2EE1.4
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Message Destination</em>' reference.
	 * @see #setMessageDestination(JavaClass)
	 * @see org.eclipse.jst.j2ee.internal.ejb.EjbPackage#getMessageDriven_MessageDestination()
	 * @model 
	 * @generated
	 */
	JavaClass getMessageDestination();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.ejb.MessageDriven#getMessageDestination <em>Message Destination</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Message Destination</em>' reference.
	 * @see #getMessageDestination()
	 * @generated
	 */
	void setMessageDestination(JavaClass value);

	/**
	 * Returns the value of the '<em><b>Link</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Link</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *  - an optional declaration of the bean's
	 * 	      message-destination-link
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Link</em>' attribute.
	 * @see #setLink(String)
	 * @see org.eclipse.jst.j2ee.internal.ejb.EjbPackage#getMessageDriven_Link()
	 * @model 
	 * @generated
	 */
	String getLink();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.ejb.MessageDriven#getLink <em>Link</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Link</em>' attribute.
	 * @see #getLink()
	 * @generated
	 */
	void setLink(String value);

	/**
	 * Returns the value of the '<em><b>Messaging Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Messaging Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @version J2EE1.4
	 * The messaging-type element specifies the message
	 *         listener interface of the message-driven bean. If
	 *         the messaging-type element is not specified, it is
	 *         assumed to be javax.jms.MessageListener.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Messaging Type</em>' reference.
	 * @see #setMessagingType(JavaClass)
	 * @see org.eclipse.jst.j2ee.internal.ejb.EjbPackage#getMessageDriven_MessagingType()
	 * @model 
	 * @generated
	 */
	JavaClass getMessagingType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.ejb.MessageDriven#getMessagingType <em>Messaging Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Messaging Type</em>' reference.
	 * @see #getMessagingType()
	 * @generated
	 */
	void setMessagingType(JavaClass value);

}





