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
package org.eclipse.jst.javaee.applicationclient;

import java.util.List;

import org.eclipse.jst.javaee.core.AdministeredObjectType;
import org.eclipse.jst.javaee.core.ConnectionFactoryResourceType;
import org.eclipse.jst.javaee.core.DataSourceType;
import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.DisplayName;
import org.eclipse.jst.javaee.core.EjbRef;
import org.eclipse.jst.javaee.core.EnvEntry;
import org.eclipse.jst.javaee.core.Icon;
import org.eclipse.jst.javaee.core.JavaEEObject;
import org.eclipse.jst.javaee.core.JmsConnectionFactoryType;
import org.eclipse.jst.javaee.core.JmsDestinationType;
import org.eclipse.jst.javaee.core.LifecycleCallback;
import org.eclipse.jst.javaee.core.MailSessionType;
import org.eclipse.jst.javaee.core.MessageDestination;
import org.eclipse.jst.javaee.core.MessageDestinationRef;
import org.eclipse.jst.javaee.core.PersistenceUnitRef;
import org.eclipse.jst.javaee.core.ResourceEnvRef;
import org.eclipse.jst.javaee.core.ResourceRef;
import org.eclipse.jst.javaee.core.ServiceRef;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Application Client</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getModuleName <em>Module Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getDisplayNames <em>Display Names</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getIcons <em>Icons</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getEnvEntries <em>Env Entries</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getEjbRefs <em>Ejb Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getServiceRefs <em>Service Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getResourceRefs <em>Resource Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getResourceEnvRefs <em>Resource Env Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getMessageDestinationRefs <em>Message Destination Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getPersistenceUnitRefs <em>Persistence Unit Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getPostConstructs <em>Post Constructs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getPreDestroys <em>Pre Destroys</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getCallbackHandler <em>Callback Handler</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getMessageDestinations <em>Message Destinations</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getDataSource <em>Data Source</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getJmsConnectionFactory <em>Jms Connection Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getJmsDestination <em>Jms Destination</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getMailSession <em>Mail Session</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getConnectionFactory <em>Connection Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getAdministeredObject <em>Administered Object</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#isMetadataComplete <em>Metadata Complete</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getVersion <em>Version</em>}</li>
 * </ul>
 *
 * @see org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage#getApplicationClient()
 * @extends JavaEEObject
 * @generated
 */
public interface ApplicationClient extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Module Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 6, Application Client 6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Module Name</em>' attribute.
	 * @see #setModuleName(String)
	 * @see org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage#getApplicationClient_ModuleName()
	 * @generated
	 */
	String getModuleName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getModuleName <em>Module Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Module Name</em>' attribute.
	 * @see #getModuleName()
	 * @generated
	 */
	void setModuleName(String value);

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
	 * @see org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage#getApplicationClient_Descriptions()
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
	 * @see org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage#getApplicationClient_DisplayNames()
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
	 * @see org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage#getApplicationClient_Icons()
	 * @generated
	 */
	List<Icon> getIcons();

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
	 * @see org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage#getApplicationClient_EnvEntries()
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
	 * @see org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage#getApplicationClient_EjbRefs()
	 * @generated
	 */
	List<EjbRef> getEjbRefs();

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
	 * @see org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage#getApplicationClient_ServiceRefs()
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
	 * @see org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage#getApplicationClient_ResourceRefs()
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
	 * @see org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage#getApplicationClient_ResourceEnvRefs()
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
	 * @see org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage#getApplicationClient_MessageDestinationRefs()
	 * @generated
	 */
	List<MessageDestinationRef> getMessageDestinationRefs();

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
	 * @see org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage#getApplicationClient_PersistenceUnitRefs()
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
	 * @see org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage#getApplicationClient_PostConstructs()
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
	 * @see org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage#getApplicationClient_PreDestroys()
	 * @generated
	 */
	List<LifecycleCallback> getPreDestroys();

	/**
	 * Returns the value of the '<em><b>Callback Handler</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The callback-handler element names a class provided by
	 *             the application.  The class must have a no args
	 *             constructor and must implement the
	 *             javax.security.auth.callback.CallbackHandler
	 *             interface.  The class will be instantiated by the
	 *             application client container and used by the container
	 *             to collect authentication information from the user.
	 *             
	 *             @since Java EE 5, Application Client 5
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Callback Handler</em>' attribute.
	 * @see #setCallbackHandler(String)
	 * @see org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage#getApplicationClient_CallbackHandler()
	 * @generated
	 */
	String getCallbackHandler();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getCallbackHandler <em>Callback Handler</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Callback Handler</em>' attribute.
	 * @see #getCallbackHandler()
	 * @generated
	 */
	void setCallbackHandler(String value);

	/**
	 * Returns the value of the '<em><b>Message Destinations</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.MessageDestination}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Destinations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message Destinations</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage#getApplicationClient_MessageDestinations()
	 * @generated
	 */
	List<MessageDestination> getMessageDestinations();

	/**
	 * Returns the value of the '<em><b>Data Source</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.DataSourceType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 6, Application Client 6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Data Source</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage#getApplicationClient_DataSource()
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
	 *             @since Java EE 7, Application Client 7
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Jms Connection Factory</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage#getApplicationClient_JmsConnectionFactory()
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
	 *             @since Java EE 7, Application Client 7
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Jms Destination</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage#getApplicationClient_JmsDestination()
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
	 *             @since Java EE 7, Application Client 7
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Mail Session</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage#getApplicationClient_MailSession()
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
	 *             @since Java EE 7, Application Client 7
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Connection Factory</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage#getApplicationClient_ConnectionFactory()
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
	 *             @since Java EE 7, Application Client 7
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Administered Object</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage#getApplicationClient_AdministeredObject()
	 * @generated
	 */
	List<AdministeredObjectType> getAdministeredObject();

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
	 * @see org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage#getApplicationClient_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Metadata Complete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *           The metadata-complete attribute defines whether this
	 *           deployment descriptor and other related deployment
	 *           descriptors for this module (e.g., web service
	 *           descriptors) are complete, or whether the class
	 *           files available to this module and packaged with
	 *           this application should be examined for annotations
	 *           that specify deployment information.
	 *           
	 *           If metadata-complete is set to "true", the deployment
	 *           tool must ignore any annotations that specify deployment
	 *           information, which might be present in the class files
	 *           of the application.
	 *           
	 *           If metadata-complete is not specified or is set to
	 *           "false", the deployment tool must examine the class
	 *           files of the application for annotations, as
	 *           specified by the specifications.
	 *           
	 *           @since Java EE 5, Application Client 5
	 *         
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Metadata Complete</em>' attribute.
	 * @see #isSetMetadataComplete()
	 * @see #unsetMetadataComplete()
	 * @see #setMetadataComplete(boolean)
	 * @see org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage#getApplicationClient_MetadataComplete()
	 * @generated
	 */
	boolean isMetadataComplete();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#isMetadataComplete <em>Metadata Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Metadata Complete</em>' attribute.
	 * @see #isSetMetadataComplete()
	 * @see #unsetMetadataComplete()
	 * @see #isMetadataComplete()
	 * @generated
	 */
	void setMetadataComplete(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#isMetadataComplete <em>Metadata Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMetadataComplete()
	 * @see #isMetadataComplete()
	 * @see #setMetadataComplete(boolean)
	 * @generated
	 */
	void unsetMetadataComplete();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#isMetadataComplete <em>Metadata Complete</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Metadata Complete</em>' attribute is set.
	 * @see #unsetMetadataComplete()
	 * @see #isMetadataComplete()
	 * @see #setMetadataComplete(boolean)
	 * @generated
	 */
	boolean isSetMetadataComplete();

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * The default value is <code>"8"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *           The required value for the version is 8.
	 *           
	 *         
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #isSetVersion()
	 * @see #unsetVersion()
	 * @see #setVersion(String)
	 * @see org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage#getApplicationClient_Version()
	 * @generated
	 */
	String getVersion();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #isSetVersion()
	 * @see #unsetVersion()
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetVersion()
	 * @see #getVersion()
	 * @see #setVersion(String)
	 * @generated
	 */
	void unsetVersion();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getVersion <em>Version</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Version</em>' attribute is set.
	 * @see #unsetVersion()
	 * @see #getVersion()
	 * @see #setVersion(String)
	 * @generated
	 */
	boolean isSetVersion();

} // ApplicationClient