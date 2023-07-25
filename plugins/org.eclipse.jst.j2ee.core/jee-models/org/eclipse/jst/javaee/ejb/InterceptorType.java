/*******************************************************************************
 * Copyright (c) 2007, 2019 IBM Corporation and others.
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
import org.eclipse.jst.javaee.core.EjbLocalRef;
import org.eclipse.jst.javaee.core.EjbRef;
import org.eclipse.jst.javaee.core.EnvEntry;
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
import org.eclipse.jst.javaee.core.ServiceRef;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Interceptor Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The interceptorType element declares information about a single
 *         interceptor class.  It consists of :
 *         
 *         - An optional description.
 *         - The fully-qualified name of the interceptor class.
 *         - An optional list of around invoke methods declared on the
 *         interceptor class and/or its super-classes.
 *         - An optional list of around timeout methods declared on the
 *         interceptor class and/or its super-classes.
 *         - An optional list environment dependencies for the interceptor
 *         class and/or its super-classes.
 *         - An optional list of post-activate methods declared on the
 *         interceptor class and/or its super-classes.
 *         - An optional list of pre-passivate methods declared on the
 *         interceptor class and/or its super-classes.
 *         
 *         @since Java EE 5, EJB 3.0
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.InterceptorType#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.InterceptorType#getInterceptorClass <em>Interceptor Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.InterceptorType#getAroundInvokes <em>Around Invokes</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.InterceptorType#getAroundTimeouts <em>Around Timeouts</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.InterceptorType#getAroundConstruct <em>Around Construct</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.InterceptorType#getEnvEntries <em>Env Entries</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.InterceptorType#getEjbRefs <em>Ejb Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.InterceptorType#getEjbLocalRefs <em>Ejb Local Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.InterceptorType#getServiceRefs <em>Service Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.InterceptorType#getResourceRefs <em>Resource Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.InterceptorType#getResourceEnvRefs <em>Resource Env Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.InterceptorType#getMessageDestinationRefs <em>Message Destination Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.InterceptorType#getPersistenceContextRefs <em>Persistence Context Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.InterceptorType#getPersistenceUnitRefs <em>Persistence Unit Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.InterceptorType#getPostConstructs <em>Post Constructs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.InterceptorType#getPreDestroys <em>Pre Destroys</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.InterceptorType#getDataSource <em>Data Source</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.InterceptorType#getJmsConnectionFactory <em>Jms Connection Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.InterceptorType#getJmsDestination <em>Jms Destination</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.InterceptorType#getMailSession <em>Mail Session</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.InterceptorType#getConnectionFactory <em>Connection Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.InterceptorType#getAdministeredObject <em>Administered Object</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.InterceptorType#getPostActivates <em>Post Activates</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.InterceptorType#getPrePassivates <em>Pre Passivates</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.InterceptorType#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInterceptorType()
 * @extends JavaEEObject
 * @generated
 */
public interface InterceptorType extends JavaEEObject {
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInterceptorType_Descriptions()
	 * @generated
	 */
	List<Description> getDescriptions();

	/**
	 * Returns the value of the '<em><b>Interceptor Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interceptor Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interceptor Class</em>' attribute.
	 * @see #setInterceptorClass(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInterceptorType_InterceptorClass()
	 * @generated
	 */
	String getInterceptorClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.InterceptorType#getInterceptorClass <em>Interceptor Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interceptor Class</em>' attribute.
	 * @see #getInterceptorClass()
	 * @generated
	 */
	void setInterceptorClass(String value);

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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInterceptorType_AroundInvokes()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInterceptorType_AroundTimeouts()
	 * @generated
	 */
	List<AroundTimeoutType> getAroundTimeouts();

	/**
	 * Returns the value of the '<em><b>Around Construct</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.LifecycleCallback}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Around Construct</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Around Construct</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInterceptorType_AroundConstruct()
	 * @generated
	 */
	List<LifecycleCallback> getAroundConstruct();

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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInterceptorType_EnvEntries()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInterceptorType_EjbRefs()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInterceptorType_EjbLocalRefs()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInterceptorType_ServiceRefs()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInterceptorType_ResourceRefs()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInterceptorType_ResourceEnvRefs()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInterceptorType_MessageDestinationRefs()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInterceptorType_PersistenceContextRefs()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInterceptorType_PersistenceUnitRefs()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInterceptorType_PostConstructs()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInterceptorType_PreDestroys()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInterceptorType_DataSource()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInterceptorType_JmsConnectionFactory()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInterceptorType_JmsDestination()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInterceptorType_MailSession()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInterceptorType_ConnectionFactory()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInterceptorType_AdministeredObject()
	 * @generated
	 */
	List<AdministeredObjectType> getAdministeredObject();

	/**
	 * Returns the value of the '<em><b>Post Activates</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.LifecycleCallback}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Post Activates</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Post Activates</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInterceptorType_PostActivates()
	 * @generated
	 */
	List<LifecycleCallback> getPostActivates();

	/**
	 * Returns the value of the '<em><b>Pre Passivates</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.LifecycleCallback}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pre Passivates</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pre Passivates</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInterceptorType_PrePassivates()
	 * @generated
	 */
	List<LifecycleCallback> getPrePassivates();

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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInterceptorType_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.InterceptorType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // InterceptorType