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
package org.eclipse.jst.javaee.application;

import java.util.List;

import org.eclipse.jst.javaee.core.AdministeredObjectType;
import org.eclipse.jst.javaee.core.ConnectionFactoryResourceType;
import org.eclipse.jst.javaee.core.DataSourceType;
import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.DisplayName;
import org.eclipse.jst.javaee.core.EjbLocalRef;
import org.eclipse.jst.javaee.core.EjbRef;
import org.eclipse.jst.javaee.core.EnvEntry;
import org.eclipse.jst.javaee.core.GenericBooleanType;
import org.eclipse.jst.javaee.core.Icon;
import org.eclipse.jst.javaee.core.JavaEEObject;
import org.eclipse.jst.javaee.core.JmsConnectionFactoryType;
import org.eclipse.jst.javaee.core.JmsDestinationType;
import org.eclipse.jst.javaee.core.MailSessionType;
import org.eclipse.jst.javaee.core.MessageDestination;
import org.eclipse.jst.javaee.core.MessageDestinationRef;
import org.eclipse.jst.javaee.core.PersistenceContextRef;
import org.eclipse.jst.javaee.core.PersistenceUnitRef;
import org.eclipse.jst.javaee.core.ResourceEnvRef;
import org.eclipse.jst.javaee.core.ResourceRef;
import org.eclipse.jst.javaee.core.SecurityRole;
import org.eclipse.jst.javaee.core.ServiceRef;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Application</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The applicationType defines the structure of the
 *         application. 
 *         
 *         @since Java EE 5, Application 5
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.application.Application#getApplicationName <em>Application Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Application#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Application#getDisplayNames <em>Display Names</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Application#getIcons <em>Icons</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Application#getInitializeInOrder <em>Initialize In Order</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Application#getModules <em>Modules</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Application#getSecurityRoles <em>Security Roles</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Application#getLibraryDirectory <em>Library Directory</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Application#getEnvEntry <em>Env Entry</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Application#getEjbRef <em>Ejb Ref</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Application#getEjbLocalRef <em>Ejb Local Ref</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Application#getServiceRefs <em>Service Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Application#getResourceRef <em>Resource Ref</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Application#getResourceEnvRef <em>Resource Env Ref</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Application#getMessageDestinationRef <em>Message Destination Ref</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Application#getPersistenceContextRef <em>Persistence Context Ref</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Application#getPersistenceUnitRef <em>Persistence Unit Ref</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Application#getMessageDestination <em>Message Destination</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Application#getDataSource <em>Data Source</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Application#getJmsConnectionFactory <em>Jms Connection Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Application#getJmsDestination <em>Jms Destination</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Application#getMailSession <em>Mail Session</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Application#getConnectionFactory <em>Connection Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Application#getAdministeredObject <em>Administered Object</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Application#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Application#getVersion <em>Version</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplication()
 * @extends JavaEEObject
 * @generated
 */
public interface Application extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Application Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 6, Application 6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Application Name</em>' attribute.
	 * @see #setApplicationName(String)
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplication_ApplicationName()
	 * @generated
	 */
	String getApplicationName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.application.Application#getApplicationName <em>Application Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Application Name</em>' attribute.
	 * @see #getApplicationName()
	 * @generated
	 */
	void setApplicationName(String value);

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
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplication_Descriptions()
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
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplication_DisplayNames()
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
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplication_Icons()
	 * @generated
	 */
	List<Icon> getIcons();

	/**
	 * Returns the value of the '<em><b>Initialize In Order</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.jst.javaee.core.GenericBooleanType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             If initialize-in-order is true, modules must be initialized
	 *             in the order they're listed in this deployment descriptor,
	 *             with the exception of application client modules, which can
	 *             be initialized in any order.
	 *             If initialize-in-order is not set or set to false, the order
	 *             of initialization is unspecified and may be product-dependent.
	 *             
	 *             @since Java EE 6, Application 6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Initialize In Order</em>' attribute.
	 * @see org.eclipse.jst.javaee.core.GenericBooleanType
	 * @see #isSetInitializeInOrder()
	 * @see #unsetInitializeInOrder()
	 * @see #setInitializeInOrder(GenericBooleanType)
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplication_InitializeInOrder()
	 * @generated
	 */
	GenericBooleanType getInitializeInOrder();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.application.Application#getInitializeInOrder <em>Initialize In Order</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initialize In Order</em>' attribute.
	 * @see org.eclipse.jst.javaee.core.GenericBooleanType
	 * @see #isSetInitializeInOrder()
	 * @see #unsetInitializeInOrder()
	 * @see #getInitializeInOrder()
	 * @generated
	 */
	void setInitializeInOrder(GenericBooleanType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.application.Application#getInitializeInOrder <em>Initialize In Order</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetInitializeInOrder()
	 * @see #getInitializeInOrder()
	 * @see #setInitializeInOrder(GenericBooleanType)
	 * @generated
	 */
	void unsetInitializeInOrder();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.application.Application#getInitializeInOrder <em>Initialize In Order</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Initialize In Order</em>' attribute is set.
	 * @see #unsetInitializeInOrder()
	 * @see #getInitializeInOrder()
	 * @see #setInitializeInOrder(GenericBooleanType)
	 * @generated
	 */
	boolean isSetInitializeInOrder();

	/**
	 * Returns the value of the '<em><b>Modules</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.application.Module}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The application deployment descriptor must have one
	 *             module element for each Java EE module in the
	 *             application package. A module element is defined 
	 *             by moduleType definition. 
	 *             
	 *             @since Java EE 5, Application 5
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Modules</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplication_Modules()
	 * @generated
	 */
	List<Module> getModules();

	/**
	 * Returns the value of the '<em><b>Security Roles</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.SecurityRole}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Security Roles</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 6, Application 6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Security Roles</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplication_SecurityRoles()
	 * @generated
	 */
	List<SecurityRole> getSecurityRoles();

	/**
	 * Returns the value of the '<em><b>Library Directory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The library-directory element specifies the pathname
	 *             of a directory within the application package, relative
	 *             to the top level of the application package.  All files
	 *             named "*.jar" in this directory must be made available
	 *             in the class path of all components included in this
	 *             application package.  If this element isn't specified,
	 *             the directory named "lib" is searched.  An empty element
	 *             may be used to disable searching.
	 *             
	 *             @since Java EE 5, Application 5
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Library Directory</em>' attribute.
	 * @see #setLibraryDirectory(String)
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplication_LibraryDirectory()
	 * @generated
	 */
	String getLibraryDirectory();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.application.Application#getLibraryDirectory <em>Library Directory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Library Directory</em>' attribute.
	 * @see #getLibraryDirectory()
	 * @generated
	 */
	void setLibraryDirectory(String value);

	/**
	 * Returns the value of the '<em><b>Env Entry</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.EnvEntry}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 6, Application 6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Env Entry</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplication_EnvEntry()
	 * @generated
	 */
	List<EnvEntry> getEnvEntry();

	/**
	 * Returns the value of the '<em><b>Ejb Ref</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.EjbRef}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 6, Application 6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Ejb Ref</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplication_EjbRef()
	 * @generated
	 */
	List<EjbRef> getEjbRef();

	/**
	 * Returns the value of the '<em><b>Ejb Local Ref</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.EjbLocalRef}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 6, Application 6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Ejb Local Ref</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplication_EjbLocalRef()
	 * @generated
	 */
	List<EjbLocalRef> getEjbLocalRef();

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
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplication_ServiceRefs()
	 * @generated
	 */
	List<ServiceRef> getServiceRefs();

	/**
	 * Returns the value of the '<em><b>Resource Ref</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.ResourceRef}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 6, Application 6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Resource Ref</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplication_ResourceRef()
	 * @generated
	 */
	List<ResourceRef> getResourceRef();

	/**
	 * Returns the value of the '<em><b>Resource Env Ref</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.ResourceEnvRef}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 6, Application 6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Resource Env Ref</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplication_ResourceEnvRef()
	 * @generated
	 */
	List<ResourceEnvRef> getResourceEnvRef();

	/**
	 * Returns the value of the '<em><b>Message Destination Ref</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.MessageDestinationRef}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 6, Application 6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Message Destination Ref</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplication_MessageDestinationRef()
	 * @generated
	 */
	List<MessageDestinationRef> getMessageDestinationRef();

	/**
	 * Returns the value of the '<em><b>Persistence Context Ref</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.PersistenceContextRef}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 6, Application 6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Persistence Context Ref</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplication_PersistenceContextRef()
	 * @generated
	 */
	List<PersistenceContextRef> getPersistenceContextRef();

	/**
	 * Returns the value of the '<em><b>Persistence Unit Ref</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.PersistenceUnitRef}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 6, Application 6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Persistence Unit Ref</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplication_PersistenceUnitRef()
	 * @generated
	 */
	List<PersistenceUnitRef> getPersistenceUnitRef();

	/**
	 * Returns the value of the '<em><b>Message Destination</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.MessageDestination}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 6, Application 6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Message Destination</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplication_MessageDestination()
	 * @generated
	 */
	List<MessageDestination> getMessageDestination();

	/**
	 * Returns the value of the '<em><b>Data Source</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.DataSourceType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 6, Application 6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Data Source</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplication_DataSource()
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
	 *             @since Java EE 7, Application 7
	 *          
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Jms Connection Factory</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplication_JmsConnectionFactory()
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
	 *             @since Java EE 7, Application 7
	 *          
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Jms Destination</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplication_JmsDestination()
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
	 *             @since Java EE 7, Application 7
	 *          
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Mail Session</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplication_MailSession()
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
	 *             @since Java EE 7, Application 7
	 *          
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Connection Factory</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplication_ConnectionFactory()
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
	 *             @since Java EE 7, Application 7
	 *          
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Administered Object</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplication_AdministeredObject()
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
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplication_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.application.Application#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * The default value is <code>"7"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *           The required value for the version is 7.
	 *           
	 *         
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #isSetVersion()
	 * @see #unsetVersion()
	 * @see #setVersion(String)
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplication_Version()
	 * @generated
	 */
	String getVersion();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.application.Application#getVersion <em>Version</em>}' attribute.
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
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.application.Application#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetVersion()
	 * @see #getVersion()
	 * @see #setVersion(String)
	 * @generated
	 */
	void unsetVersion();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.application.Application#getVersion <em>Version</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Version</em>' attribute is set.
	 * @see #unsetVersion()
	 * @see #getVersion()
	 * @see #setVersion(String)
	 * @generated
	 */
	boolean isSetVersion();

	/**
	 * Gets the first module matching the specified uri
	 * @param uri The uri of a module to find.
	 * @return The first matching module or null if no module is found.
	 */
	public Module getFirstModule(String uri);

	public Module getModule(String uri, String altDD);

} // Application