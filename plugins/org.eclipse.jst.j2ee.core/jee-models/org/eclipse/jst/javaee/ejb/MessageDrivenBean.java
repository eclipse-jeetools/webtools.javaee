/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.ejb;

import java.util.List;

import org.eclipse.jst.javaee.core.AdministeredObjectType;
import org.eclipse.jst.javaee.core.ConnectionFactoryResourceType;
import org.eclipse.jst.javaee.core.DataSourceType;
import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.DisplayName;
import org.eclipse.jst.javaee.core.EjbLocalRef;
import org.eclipse.jst.javaee.core.EjbRef;
import org.eclipse.jst.javaee.core.EnvEntry;
import org.eclipse.jst.javaee.core.Icon;
import org.eclipse.jst.javaee.core.JavaEEObject;
import org.eclipse.jst.javaee.core.JmsConnectionFactoryType;
import org.eclipse.jst.javaee.core.JmsDestinationType;
import org.eclipse.jst.javaee.core.LifecycleCallback;
import org.eclipse.jst.javaee.core.MailSessionType;
import org.eclipse.jst.javaee.core.MessageDestinationRef;
import org.eclipse.jst.javaee.core.PersistenceContextRef;
import org.eclipse.jst.javaee.core.PersistenceUnitRef;
import org.eclipse.jst.javaee.core.ResourceEnvRef;
import org.eclipse.jst.javaee.core.ResourceRef;
import org.eclipse.jst.javaee.core.SecurityRoleRef;
import org.eclipse.jst.javaee.core.ServiceRef;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Message Driven Bean</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The message-driven element declares a message-driven
 *         bean. The declaration consists of:
 *         
 *         - an optional description
 *         - an optional display name
 *         - an optional icon element that contains a small and a large 
 *         icon file name. 
 *         - a name assigned to the enterprise bean in
 *         the deployment descriptor
 *         - an optional mapped-name element that can be used to provide
 *         vendor-specific deployment information such as the physical
 *         jndi-name of destination from which this message-driven bean
 *         should consume.  This element is not required to be supported 
 *         by all implementations.  Any use of this element is non-portable.
 *         - the message-driven bean's implementation class
 *         - an optional declaration of the bean's messaging 
 *         type 
 *         - an optional declaration of the bean's timeout method for
 *         handling programmatically created timers
 *         - an optional declaration of timers to be automatically created at
 *         deployment time
 *         - the optional message-driven bean's transaction management 
 *         type. If it is not defined, it is defaulted to Container.
 *         - an optional declaration of the bean's 
 *         message-destination-type
 *         - an optional declaration of the bean's 
 *         message-destination-link
 *         - an optional declaration of the message-driven bean's
 *         activation configuration properties
 *         - an optional list of the message-driven bean class and/or
 *         superclass around-invoke methods.
 *         - an optional list of the message-driven bean class and/or
 *         superclass around-timeout methods.
 *         - an optional declaration of the bean's environment
 *         entries
 *         - an optional declaration of the bean's EJB references
 *         - an optional declaration of the bean's local EJB
 *         references
 *         - an optional declaration of the bean's web service
 *         references
 *         - an optional declaration of the security role 
 *         references
 *         - an optional declaration of the security
 *         identity to be used for the execution of the bean's
 *         methods
 *         - an optional declaration of the bean's 
 *         resource manager connection factory 
 *         references
 *         - an optional declaration of the bean's resource
 *         environment references.
 *         - an optional declaration of the bean's message 
 *         destination references
 *         
 *         @since Java EE 5, EJB 3.0
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getDisplayNames <em>Display Names</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getIcons <em>Icons</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getEjbName <em>Ejb Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getMappedName <em>Mapped Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getEjbClass <em>Ejb Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getMessagingType <em>Messaging Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getTimeoutMethod <em>Timeout Method</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getTimer <em>Timer</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getTransactionType <em>Transaction Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getMessageDestinationType <em>Message Destination Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getMessageDestinationLink <em>Message Destination Link</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getActivationConfig <em>Activation Config</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getAroundInvokes <em>Around Invokes</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getAroundTimeouts <em>Around Timeouts</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getEnvEntries <em>Env Entries</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getEjbRefs <em>Ejb Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getEjbLocalRefs <em>Ejb Local Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getServiceRefs <em>Service Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getResourceRefs <em>Resource Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getResourceEnvRefs <em>Resource Env Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getMessageDestinationRefs <em>Message Destination Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getPersistenceContextRefs <em>Persistence Context Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getPersistenceUnitRefs <em>Persistence Unit Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getPostConstructs <em>Post Constructs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getPreDestroys <em>Pre Destroys</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getDataSource <em>Data Source</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getJmsConnectionFactory <em>Jms Connection Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getJmsDestination <em>Jms Destination</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getMailSession <em>Mail Session</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getConnectionFactory <em>Connection Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getAdministeredObject <em>Administered Object</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getSecurityRoleRef <em>Security Role Ref</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getSecurityIdentity <em>Security Identity</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMessageDrivenBean()
 * @extends JavaEEObject
 * @generated
 */
public interface MessageDrivenBean extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Descriptions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.Description}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Descriptions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Descriptions</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMessageDrivenBean_Descriptions()
	 * @generated
	 */
	List<Description> getDescriptions();

	/**
	 * Returns the value of the '<em><b>Display Names</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.DisplayName}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Display Names</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Display Names</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMessageDrivenBean_DisplayNames()
	 * @generated
	 */
	List<DisplayName> getDisplayNames();

	/**
	 * Returns the value of the '<em><b>Icons</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.Icon}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Icons</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Icons</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMessageDrivenBean_Icons()
	 * @generated
	 */
	List<Icon> getIcons();

	/**
	 * Returns the value of the '<em><b>Ejb Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ejb Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ejb Name</em>' attribute.
	 * @see #setEjbName(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMessageDrivenBean_EjbName()
	 * @generated
	 */
	String getEjbName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getEjbName <em>Ejb Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ejb Name</em>' attribute.
	 * @see #getEjbName()
	 * @generated
	 */
	void setEjbName(String value);

	/**
	 * Returns the value of the '<em><b>Mapped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mapped Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mapped Name</em>' attribute.
	 * @see #setMappedName(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMessageDrivenBean_MappedName()
	 * @generated
	 */
	String getMappedName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getMappedName <em>Mapped Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mapped Name</em>' attribute.
	 * @see #getMappedName()
	 * @generated
	 */
	void setMappedName(String value);

	/**
	 * Returns the value of the '<em><b>Ejb Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The ejb-class element specifies the fully qualified name
	 *             of the bean class for this ejb.  It is required unless
	 *             there is a component-defining annotation for the same
	 *             ejb-name.
	 *             
	 *             @since Java EE 5, EJB 3.0
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Ejb Class</em>' attribute.
	 * @see #setEjbClass(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMessageDrivenBean_EjbClass()
	 * @generated
	 */
	String getEjbClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getEjbClass <em>Ejb Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ejb Class</em>' attribute.
	 * @see #getEjbClass()
	 * @generated
	 */
	void setEjbClass(String value);

	/**
	 * Returns the value of the '<em><b>Messaging Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The messaging-type element specifies the message
	 *             listener interface of the message-driven bean. 
	 *             
	 *             @since Java EE 5, EJB 3.0
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Messaging Type</em>' attribute.
	 * @see #setMessagingType(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMessageDrivenBean_MessagingType()
	 * @generated
	 */
	String getMessagingType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getMessagingType <em>Messaging Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Messaging Type</em>' attribute.
	 * @see #getMessagingType()
	 * @generated
	 */
	void setMessagingType(String value);

	/**
	 * Returns the value of the '<em><b>Timeout Method</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timeout Method</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The timeout-method element specifies the method that
	 *             will receive callbacks for programmatically
	 *             created timers.
	 *             
	 *             @since Java EE 5, EJB 3.0
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Timeout Method</em>' containment reference.
	 * @see #setTimeoutMethod(NamedMethodType)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMessageDrivenBean_TimeoutMethod()
	 * @generated
	 */
	NamedMethodType getTimeoutMethod();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getTimeoutMethod <em>Timeout Method</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timeout Method</em>' containment reference.
	 * @see #getTimeoutMethod()
	 * @generated
	 */
	void setTimeoutMethod(NamedMethodType value);

	/**
	 * Returns the value of the '<em><b>Timer</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.ejb.TimerType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timer</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timer</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMessageDrivenBean_Timer()
	 * @generated
	 */
	List<TimerType> getTimer();

	/**
	 * Returns the value of the '<em><b>Transaction Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.jst.javaee.ejb.TransactionType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transaction Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transaction Type</em>' attribute.
	 * @see org.eclipse.jst.javaee.ejb.TransactionType
	 * @see #isSetTransactionType()
	 * @see #unsetTransactionType()
	 * @see #setTransactionType(TransactionType)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMessageDrivenBean_TransactionType()
	 * @generated
	 */
	TransactionType getTransactionType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getTransactionType <em>Transaction Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transaction Type</em>' attribute.
	 * @see org.eclipse.jst.javaee.ejb.TransactionType
	 * @see #isSetTransactionType()
	 * @see #unsetTransactionType()
	 * @see #getTransactionType()
	 * @generated
	 */
	void setTransactionType(TransactionType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getTransactionType <em>Transaction Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTransactionType()
	 * @see #getTransactionType()
	 * @see #setTransactionType(TransactionType)
	 * @generated
	 */
	void unsetTransactionType();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getTransactionType <em>Transaction Type</em>}' attribute is set.
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
	 * Returns the value of the '<em><b>Message Destination Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Destination Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message Destination Type</em>' attribute.
	 * @see #setMessageDestinationType(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMessageDrivenBean_MessageDestinationType()
	 * @generated
	 */
	String getMessageDestinationType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getMessageDestinationType <em>Message Destination Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Message Destination Type</em>' attribute.
	 * @see #getMessageDestinationType()
	 * @generated
	 */
	void setMessageDestinationType(String value);

	/**
	 * Returns the value of the '<em><b>Message Destination Link</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Destination Link</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message Destination Link</em>' attribute.
	 * @see #setMessageDestinationLink(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMessageDrivenBean_MessageDestinationLink()
	 * @generated
	 */
	String getMessageDestinationLink();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getMessageDestinationLink <em>Message Destination Link</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Message Destination Link</em>' attribute.
	 * @see #getMessageDestinationLink()
	 * @generated
	 */
	void setMessageDestinationLink(String value);

	/**
	 * Returns the value of the '<em><b>Activation Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activation Config</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activation Config</em>' containment reference.
	 * @see #setActivationConfig(ActivationConfig)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMessageDrivenBean_ActivationConfig()
	 * @generated
	 */
	ActivationConfig getActivationConfig();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getActivationConfig <em>Activation Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activation Config</em>' containment reference.
	 * @see #getActivationConfig()
	 * @generated
	 */
	void setActivationConfig(ActivationConfig value);

	/**
	 * Returns the value of the '<em><b>Around Invokes</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.ejb.AroundInvokeType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Around Invokes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Around Invokes</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMessageDrivenBean_AroundInvokes()
	 * @generated
	 */
	List<AroundInvokeType> getAroundInvokes();

	/**
	 * Returns the value of the '<em><b>Around Timeouts</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.ejb.AroundTimeoutType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Around Timeouts</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Around Timeouts</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMessageDrivenBean_AroundTimeouts()
	 * @generated
	 */
	List<AroundTimeoutType> getAroundTimeouts();

	/**
	 * Returns the value of the '<em><b>Env Entries</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.EnvEntry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Env Entries</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Env Entries</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMessageDrivenBean_EnvEntries()
	 * @generated
	 */
	List<EnvEntry> getEnvEntries();

	/**
	 * Returns the value of the '<em><b>Ejb Refs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.EjbRef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ejb Refs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ejb Refs</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMessageDrivenBean_EjbRefs()
	 * @generated
	 */
	List<EjbRef> getEjbRefs();

	/**
	 * Returns the value of the '<em><b>Ejb Local Refs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.EjbLocalRef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ejb Local Refs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ejb Local Refs</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMessageDrivenBean_EjbLocalRefs()
	 * @generated
	 */
	List<EjbLocalRef> getEjbLocalRefs();

	/**
	 * Returns the value of the '<em><b>Service Refs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.ServiceRef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Service Refs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Service Refs</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMessageDrivenBean_ServiceRefs()
	 * @generated
	 */
	List<ServiceRef> getServiceRefs();

	/**
	 * Returns the value of the '<em><b>Resource Refs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.ResourceRef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Refs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Refs</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMessageDrivenBean_ResourceRefs()
	 * @generated
	 */
	List<ResourceRef> getResourceRefs();

	/**
	 * Returns the value of the '<em><b>Resource Env Refs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.ResourceEnvRef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Env Refs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Env Refs</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMessageDrivenBean_ResourceEnvRefs()
	 * @generated
	 */
	List<ResourceEnvRef> getResourceEnvRefs();

	/**
	 * Returns the value of the '<em><b>Message Destination Refs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.MessageDestinationRef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Destination Refs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message Destination Refs</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMessageDrivenBean_MessageDestinationRefs()
	 * @generated
	 */
	List<MessageDestinationRef> getMessageDestinationRefs();

	/**
	 * Returns the value of the '<em><b>Persistence Context Refs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.PersistenceContextRef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Persistence Context Refs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Persistence Context Refs</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMessageDrivenBean_PersistenceContextRefs()
	 * @generated
	 */
	List<PersistenceContextRef> getPersistenceContextRefs();

	/**
	 * Returns the value of the '<em><b>Persistence Unit Refs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.PersistenceUnitRef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Persistence Unit Refs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Persistence Unit Refs</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMessageDrivenBean_PersistenceUnitRefs()
	 * @generated
	 */
	List<PersistenceUnitRef> getPersistenceUnitRefs();

	/**
	 * Returns the value of the '<em><b>Post Constructs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.LifecycleCallback}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Post Constructs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Post Constructs</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMessageDrivenBean_PostConstructs()
	 * @generated
	 */
	List<LifecycleCallback> getPostConstructs();

	/**
	 * Returns the value of the '<em><b>Pre Destroys</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.LifecycleCallback}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pre Destroys</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pre Destroys</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMessageDrivenBean_PreDestroys()
	 * @generated
	 */
	List<LifecycleCallback> getPreDestroys();

	/**
	 * Returns the value of the '<em><b>Data Source</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.DataSourceType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Source</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Source</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMessageDrivenBean_DataSource()
	 * @generated
	 */
	List<DataSourceType> getDataSource();

	/**
	 * Returns the value of the '<em><b>Jms Connection Factory</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.JmsConnectionFactoryType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 7
	 *          
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Jms Connection Factory</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMessageDrivenBean_JmsConnectionFactory()
	 * @generated
	 */
	List<JmsConnectionFactoryType> getJmsConnectionFactory();

	/**
	 * Returns the value of the '<em><b>Jms Destination</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.JmsDestinationType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 7
	 *          
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Jms Destination</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMessageDrivenBean_JmsDestination()
	 * @generated
	 */
	List<JmsDestinationType> getJmsDestination();

	/**
	 * Returns the value of the '<em><b>Mail Session</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.MailSessionType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 7
	 *          
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Mail Session</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMessageDrivenBean_MailSession()
	 * @generated
	 */
	List<MailSessionType> getMailSession();

	/**
	 * Returns the value of the '<em><b>Connection Factory</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.ConnectionFactoryResourceType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 7
	 *          
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Connection Factory</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMessageDrivenBean_ConnectionFactory()
	 * @generated
	 */
	List<ConnectionFactoryResourceType> getConnectionFactory();

	/**
	 * Returns the value of the '<em><b>Administered Object</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.AdministeredObjectType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 7
	 *          
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Administered Object</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMessageDrivenBean_AdministeredObject()
	 * @generated
	 */
	List<AdministeredObjectType> getAdministeredObject();

	/**
	 * Returns the value of the '<em><b>Security Role Ref</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.SecurityRoleRef}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 6, EJB 3.1
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Security Role Ref</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMessageDrivenBean_SecurityRoleRef()
	 * @generated
	 */
	List<SecurityRoleRef> getSecurityRoleRef();

	/**
	 * Returns the value of the '<em><b>Security Identity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Security Identity</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Security Identity</em>' containment reference.
	 * @see #setSecurityIdentity(SecurityIdentityType)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMessageDrivenBean_SecurityIdentity()
	 * @generated
	 */
	SecurityIdentityType getSecurityIdentity();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getSecurityIdentity <em>Security Identity</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Security Identity</em>' containment reference.
	 * @see #getSecurityIdentity()
	 * @generated
	 */
	void setSecurityIdentity(SecurityIdentityType value);

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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMessageDrivenBean_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // MessageDrivenBean