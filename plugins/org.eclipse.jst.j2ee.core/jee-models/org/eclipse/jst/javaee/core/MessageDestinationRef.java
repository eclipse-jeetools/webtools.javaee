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
package org.eclipse.jst.javaee.core;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Message Destination Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * [
 *         The message-destination-ref element contains a declaration
 *         of Deployment Component's reference to a message destination
 *         associated with a resource in Deployment Component's
 *         environment. It consists of:
 *         
 *         - an optional description
 *         - the message destination reference name
 *         - an optional message destination type
 *         - an optional specification as to whether
 *         the destination is used for 
 *         consuming or producing messages, or both.
 *         if not specified, "both" is assumed.
 *         - an optional link to the message destination
 *         - optional injection targets
 *         
 *         The message destination type must be supplied unless an
 *         injection target is specified, in which case the type
 *         of the target is used.  If both are specified, the type
 *         must be assignment compatible with the type of the injection
 *         target.
 *         
 *         Examples:
 *         
 *         <message-destination-ref>
 *         <message-destination-ref-name>jms/StockQueue
 *         </message-destination-ref-name>
 *         <message-destination-type>javax.jms.Queue
 *         </message-destination-type>
 *         <message-destination-usage>Consumes
 *         </message-destination-usage>
 *         <message-destination-link>CorporateStocks
 *         </message-destination-link>
 *         </message-destination-ref>
 *         
 * 
 *         @since Java EE 5
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.MessageDestinationRef#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.MessageDestinationRef#getMessageDestinationRefName <em>Message Destination Ref Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.MessageDestinationRef#getMessageDestinationType <em>Message Destination Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.MessageDestinationRef#getMessageDestinationUsage <em>Message Destination Usage</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.MessageDestinationRef#getMessageDestinationLink <em>Message Destination Link</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.MessageDestinationRef#getMappedName <em>Mapped Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.MessageDestinationRef#getInjectionTargets <em>Injection Targets</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.MessageDestinationRef#getLookupName <em>Lookup Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.MessageDestinationRef#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getMessageDestinationRef()
 * @extends JavaEEObject
 * @generated
 */
public interface MessageDestinationRef extends JavaEEObject {
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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getMessageDestinationRef_Descriptions()
	 * @generated
	 */
	List<Description> getDescriptions();

	/**
	 * Returns the value of the '<em><b>Message Destination Ref Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The message-destination-ref-name element specifies
	 *             the name of a message destination reference; its
	 *             value is the environment entry name used in
	 *             Deployment Component code.
	 *             
	 *             @since Java EE 5
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Message Destination Ref Name</em>' attribute.
	 * @see #setMessageDestinationRefName(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getMessageDestinationRef_MessageDestinationRefName()
	 * @generated
	 */
	String getMessageDestinationRefName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.MessageDestinationRef#getMessageDestinationRefName <em>Message Destination Ref Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Message Destination Ref Name</em>' attribute.
	 * @see #getMessageDestinationRefName()
	 * @generated
	 */
	void setMessageDestinationRefName(String value);

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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getMessageDestinationRef_MessageDestinationType()
	 * @generated
	 */
	String getMessageDestinationType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.MessageDestinationRef#getMessageDestinationType <em>Message Destination Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Message Destination Type</em>' attribute.
	 * @see #getMessageDestinationType()
	 * @generated
	 */
	void setMessageDestinationType(String value);

	/**
	 * Returns the value of the '<em><b>Message Destination Usage</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.jst.javaee.core.MessageDestinationUsageType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Destination Usage</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message Destination Usage</em>' attribute.
	 * @see org.eclipse.jst.javaee.core.MessageDestinationUsageType
	 * @see #isSetMessageDestinationUsage()
	 * @see #unsetMessageDestinationUsage()
	 * @see #setMessageDestinationUsage(MessageDestinationUsageType)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getMessageDestinationRef_MessageDestinationUsage()
	 * @generated
	 */
	MessageDestinationUsageType getMessageDestinationUsage();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.MessageDestinationRef#getMessageDestinationUsage <em>Message Destination Usage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Message Destination Usage</em>' attribute.
	 * @see org.eclipse.jst.javaee.core.MessageDestinationUsageType
	 * @see #isSetMessageDestinationUsage()
	 * @see #unsetMessageDestinationUsage()
	 * @see #getMessageDestinationUsage()
	 * @generated
	 */
	void setMessageDestinationUsage(MessageDestinationUsageType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.core.MessageDestinationRef#getMessageDestinationUsage <em>Message Destination Usage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMessageDestinationUsage()
	 * @see #getMessageDestinationUsage()
	 * @see #setMessageDestinationUsage(MessageDestinationUsageType)
	 * @generated
	 */
	void unsetMessageDestinationUsage();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.core.MessageDestinationRef#getMessageDestinationUsage <em>Message Destination Usage</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Message Destination Usage</em>' attribute is set.
	 * @see #unsetMessageDestinationUsage()
	 * @see #getMessageDestinationUsage()
	 * @see #setMessageDestinationUsage(MessageDestinationUsageType)
	 * @generated
	 */
	boolean isSetMessageDestinationUsage();

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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getMessageDestinationRef_MessageDestinationLink()
	 * @generated
	 */
	String getMessageDestinationLink();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.MessageDestinationRef#getMessageDestinationLink <em>Message Destination Link</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Message Destination Link</em>' attribute.
	 * @see #getMessageDestinationLink()
	 * @generated
	 */
	void setMessageDestinationLink(String value);

	/**
	 * Returns the value of the '<em><b>Mapped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             A product specific name that this resource should be
	 *             mapped to.  The name of this resource, as defined by the
	 *             resource's name element or defaulted, is a name that is
	 *             local to the application component using the resource.
	 *             (It's a name in the JNDI java:comp/env namespace.)  Many
	 *             application servers provide a way to map these local
	 *             names to names of resources known to the application
	 *             server.  This mapped name is often a global JNDI name,
	 *             but may be a name of any form.
	 *             
	 *             Application servers are not required to support any
	 *             particular form or type of mapped name, nor the ability
	 *             to use mapped names.  The mapped name is
	 *             product-dependent and often installation-dependent.  No
	 *             use of a mapped name is portable.
	 *             
	 *             @since Java EE 5
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Mapped Name</em>' attribute.
	 * @see #setMappedName(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getMessageDestinationRef_MappedName()
	 * @generated
	 */
	String getMappedName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.MessageDestinationRef#getMappedName <em>Mapped Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mapped Name</em>' attribute.
	 * @see #getMappedName()
	 * @generated
	 */
	void setMappedName(String value);

	/**
	 * Returns the value of the '<em><b>Injection Targets</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.InjectionTarget}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Injection Targets</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Injection Targets</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getMessageDestinationRef_InjectionTargets()
	 * @generated
	 */
	List<InjectionTarget> getInjectionTargets();

	/**
	 * Returns the value of the '<em><b>Lookup Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The JNDI name to be looked up to resolve a resource reference.
	 *             
	 *             @since Java EE 6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Lookup Name</em>' attribute.
	 * @see #setLookupName(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getMessageDestinationRef_LookupName()
	 * @generated
	 */
	String getLookupName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.MessageDestinationRef#getLookupName <em>Lookup Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lookup Name</em>' attribute.
	 * @see #getLookupName()
	 * @generated
	 */
	void setLookupName(String value);

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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getMessageDestinationRef_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.MessageDestinationRef#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // MessageDestinationRef