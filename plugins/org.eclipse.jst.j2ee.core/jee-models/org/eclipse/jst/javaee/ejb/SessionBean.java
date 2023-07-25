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
import org.eclipse.jst.javaee.core.DisplayName;
import org.eclipse.jst.javaee.core.EjbLocalRef;
import org.eclipse.jst.javaee.core.EjbRef;
import org.eclipse.jst.javaee.core.EmptyType;
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
 * A representation of the model object '<em><b>Session Bean</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The session-beanType declares an session bean. The
 *         declaration consists of:
 *         
 *         - an optional description
 *         - an optional display name
 *         - an optional icon element that contains a small and a large 
 *         icon file name
 *         - a name assigned to the enterprise bean
 *         in the deployment description
 *         - an optional mapped-name element that can be used to provide
 *         vendor-specific deployment information such as the physical
 *         jndi-name of the session bean's remote home/business interface. 
 *         This element is not required to be supported by all 
 *         implementations. Any use of this element is non-portable.
 *         - the names of all the remote or local business interfaces, 
 *         if any
 *         - the names of the session bean's remote home and
 *         remote interfaces, if any
 *         - the names of the session bean's local home and
 *         local interfaces, if any
 *         - an optional declaration that this bean exposes a
 *         no-interface view
 *         - the name of the session bean's web service endpoint
 *         interface, if any
 *         - the session bean's implementation class
 *         - the session bean's state management type
 *         - an optional declaration of a stateful session bean's timeout value
 *         - an optional declaration of the session bean's timeout method for
 *         handling programmatically created timers
 *         - an optional declaration of timers to be automatically created at
 *         deployment time
 *         - an optional declaration that a Singleton bean has eager
 *         initialization
 *         - an optional declaration of a Singleton/Stateful bean's concurrency 
 *         management type
 *         - an optional declaration of the method locking metadata
 *         for a Singleton with container managed concurrency
 *         - an optional declaration of the other Singleton beans in the
 *         application that must be initialized before this bean
 *         - an optional declaration of the session bean's asynchronous 
 *         methods
 *         - the optional session bean's transaction management type. 
 *         If it is not present, it is defaulted to Container.
 *         - an optional declaration of a stateful session bean's 
 *         afterBegin, beforeCompletion, and/or afterCompletion methods
 *         - an optional list of the session bean class and/or
 *         superclass around-invoke methods.
 *         - an optional list of the session bean class and/or
 *         superclass around-timeout methods.
 *         - an optional declaration of the bean's 
 *         environment entries
 *         - an optional declaration of the bean's EJB references
 *         - an optional declaration of the bean's local 
 *         EJB references
 *         - an optional declaration of the bean's web 
 *         service references
 *         - an optional declaration of the security role 
 *         references
 *         - an optional declaration of the security identity 
 *         to be used for the execution of the bean's methods
 *         - an optional declaration of the bean's resource 
 *         manager connection factory references
 *         - an optional declaration of the bean's resource 
 *         environment references.
 *         - an optional declaration of the bean's message 
 *         destination references
 *         - an optional specification as to whether the stateful 
 *         session bean is passivation capable or not. If not 
 *         specified, the bean is assumed to be passivation capable
 *         
 *         The elements that are optional are "optional" in the sense
 *         that they are omitted when if lists represented by them are
 *         empty.
 *         
 *         The service-endpoint element may only be specified if the
 *         bean is a stateless session bean.
 *         
 *         @since Java EE 5, EJB 3.0
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getDisplayNames <em>Display Names</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getIcons <em>Icons</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getEjbName <em>Ejb Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getMappedName <em>Mapped Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getHome <em>Home</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getRemote <em>Remote</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getLocalHome <em>Local Home</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getLocal <em>Local</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getBusinessLocals <em>Business Locals</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getBusinessRemotes <em>Business Remotes</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getLocalBean <em>Local Bean</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getServiceEndpoint <em>Service Endpoint</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getEjbClass <em>Ejb Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getSessionType <em>Session Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getStatefulTimeout <em>Stateful Timeout</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getTimeoutMethod <em>Timeout Method</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getTimer <em>Timer</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#isInitOnStartup <em>Init On Startup</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getConcurrencyManagementType <em>Concurrency Management Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getConcurrentMethod <em>Concurrent Method</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getDependsOn <em>Depends On</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getInitMethods <em>Init Methods</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getRemoveMethods <em>Remove Methods</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getAsyncMethod <em>Async Method</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getTransactionType <em>Transaction Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getAfterBeginMethod <em>After Begin Method</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getBeforeCompletionMethod <em>Before Completion Method</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getAfterCompletionMethod <em>After Completion Method</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getAroundInvokes <em>Around Invokes</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getAroundTimeouts <em>Around Timeouts</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getEnvEntries <em>Env Entries</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getEjbRefs <em>Ejb Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getEjbLocalRefs <em>Ejb Local Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getServiceRefs <em>Service Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getResourceRefs <em>Resource Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getResourceEnvRefs <em>Resource Env Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getMessageDestinationRefs <em>Message Destination Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getPersistenceContextRefs <em>Persistence Context Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getPersistenceUnitRefs <em>Persistence Unit Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getPostConstructs <em>Post Constructs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getPreDestroys <em>Pre Destroys</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getDataSource <em>Data Source</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getJmsConnectionFactory <em>Jms Connection Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getJmsDestination <em>Jms Destination</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getMailSession <em>Mail Session</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getConnectionFactory <em>Connection Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getAdministeredObject <em>Administered Object</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getPostActivates <em>Post Activates</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getPrePassivates <em>Pre Passivates</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getSecurityRoleRefs <em>Security Role Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getSecurityIdentities <em>Security Identities</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#isPassivationCapable <em>Passivation Capable</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean()
 * @extends JavaEEObject
 * @generated
 */
public interface SessionBean extends JavaEEObject {
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_Descriptions()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_DisplayNames()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_Icons()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_EjbName()
	 * @generated
	 */
	String getEjbName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.SessionBean#getEjbName <em>Ejb Name</em>}' attribute.
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_MappedName()
	 * @generated
	 */
	String getMappedName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.SessionBean#getMappedName <em>Mapped Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mapped Name</em>' attribute.
	 * @see #getMappedName()
	 * @generated
	 */
	void setMappedName(String value);

	/**
	 * Returns the value of the '<em><b>Home</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Home</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Home</em>' attribute.
	 * @see #setHome(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_Home()
	 * @generated
	 */
	String getHome();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.SessionBean#getHome <em>Home</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Home</em>' attribute.
	 * @see #getHome()
	 * @generated
	 */
	void setHome(String value);

	/**
	 * Returns the value of the '<em><b>Remote</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remote</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Remote</em>' attribute.
	 * @see #setRemote(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_Remote()
	 * @generated
	 */
	String getRemote();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.SessionBean#getRemote <em>Remote</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remote</em>' attribute.
	 * @see #getRemote()
	 * @generated
	 */
	void setRemote(String value);

	/**
	 * Returns the value of the '<em><b>Local Home</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Local Home</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Local Home</em>' attribute.
	 * @see #setLocalHome(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_LocalHome()
	 * @generated
	 */
	String getLocalHome();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.SessionBean#getLocalHome <em>Local Home</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Local Home</em>' attribute.
	 * @see #getLocalHome()
	 * @generated
	 */
	void setLocalHome(String value);

	/**
	 * Returns the value of the '<em><b>Local</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Local</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Local</em>' attribute.
	 * @see #setLocal(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_Local()
	 * @generated
	 */
	String getLocal();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.SessionBean#getLocal <em>Local</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Local</em>' attribute.
	 * @see #getLocal()
	 * @generated
	 */
	void setLocal(String value);

	/**
	 * Returns the value of the '<em><b>Business Locals</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Business Locals</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Business Locals</em>' attribute list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_BusinessLocals()
	 * @generated
	 */
	List<String> getBusinessLocals();

	/**
	 * Returns the value of the '<em><b>Business Remotes</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Business Remotes</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Business Remotes</em>' attribute list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_BusinessRemotes()
	 * @generated
	 */
	List<String> getBusinessRemotes();

	/**
	 * Returns the value of the '<em><b>Local Bean</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The local-bean element declares that this
	 *             session bean exposes a no-interface Local client view.
	 *             
	 *             @since Java EE 6, EJB 3.1
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Local Bean</em>' containment reference.
	 * @see #setLocalBean(EmptyType)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_LocalBean()
	 * @generated
	 */
	EmptyType getLocalBean();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.SessionBean#getLocalBean <em>Local Bean</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Local Bean</em>' containment reference.
	 * @see #getLocalBean()
	 * @generated
	 */
	void setLocalBean(EmptyType value);

	/**
	 * Returns the value of the '<em><b>Service Endpoint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The service-endpoint element contains the
	 *             fully-qualified name of the enterprise bean's web
	 *             service endpoint interface. The service-endpoint
	 *             element may only be specified for a stateless
	 *             session bean. The specified interface must be a
	 *             valid JAX-RPC service endpoint interface.
	 *             
	 *             @since Java EE 5, EJB 3.0
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Service Endpoint</em>' attribute.
	 * @see #setServiceEndpoint(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_ServiceEndpoint()
	 * @generated
	 */
	String getServiceEndpoint();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.SessionBean#getServiceEndpoint <em>Service Endpoint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Service Endpoint</em>' attribute.
	 * @see #getServiceEndpoint()
	 * @generated
	 */
	void setServiceEndpoint(String value);

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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_EjbClass()
	 * @generated
	 */
	String getEjbClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.SessionBean#getEjbClass <em>Ejb Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ejb Class</em>' attribute.
	 * @see #getEjbClass()
	 * @generated
	 */
	void setEjbClass(String value);

	/**
	 * Returns the value of the '<em><b>Session Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.jst.javaee.ejb.SessionType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Session Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Session Type</em>' attribute.
	 * @see org.eclipse.jst.javaee.ejb.SessionType
	 * @see #isSetSessionType()
	 * @see #unsetSessionType()
	 * @see #setSessionType(SessionType)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_SessionType()
	 * @generated
	 */
	SessionType getSessionType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.SessionBean#getSessionType <em>Session Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Session Type</em>' attribute.
	 * @see org.eclipse.jst.javaee.ejb.SessionType
	 * @see #isSetSessionType()
	 * @see #unsetSessionType()
	 * @see #getSessionType()
	 * @generated
	 */
	void setSessionType(SessionType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.ejb.SessionBean#getSessionType <em>Session Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSessionType()
	 * @see #getSessionType()
	 * @see #setSessionType(SessionType)
	 * @generated
	 */
	void unsetSessionType();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.ejb.SessionBean#getSessionType <em>Session Type</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Session Type</em>' attribute is set.
	 * @see #unsetSessionType()
	 * @see #getSessionType()
	 * @see #setSessionType(SessionType)
	 * @generated
	 */
	boolean isSetSessionType();

	/**
	 * Returns the value of the '<em><b>Stateful Timeout</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stateful Timeout</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stateful Timeout</em>' containment reference.
	 * @see #setStatefulTimeout(StatefulTimeoutType)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_StatefulTimeout()
	 * @generated
	 */
	StatefulTimeoutType getStatefulTimeout();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.SessionBean#getStatefulTimeout <em>Stateful Timeout</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stateful Timeout</em>' containment reference.
	 * @see #getStatefulTimeout()
	 * @generated
	 */
	void setStatefulTimeout(StatefulTimeoutType value);

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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_TimeoutMethod()
	 * @generated
	 */
	NamedMethodType getTimeoutMethod();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.SessionBean#getTimeoutMethod <em>Timeout Method</em>}' containment reference.
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_Timer()
	 * @generated
	 */
	List<TimerType> getTimer();

	/**
	 * Returns the value of the '<em><b>Init On Startup</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The init-on-startup element specifies that a Singleton
	 *             bean has eager initialization.
	 *             This element can only be specified for singleton session
	 *             beans.
	 *             
	 *             @since Java EE 5, EJB 3.0
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Init On Startup</em>' attribute.
	 * @see #isSetInitOnStartup()
	 * @see #unsetInitOnStartup()
	 * @see #setInitOnStartup(boolean)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_InitOnStartup()
	 * @generated
	 */
	boolean isInitOnStartup();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.SessionBean#isInitOnStartup <em>Init On Startup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Init On Startup</em>' attribute.
	 * @see #isSetInitOnStartup()
	 * @see #unsetInitOnStartup()
	 * @see #isInitOnStartup()
	 * @generated
	 */
	void setInitOnStartup(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.ejb.SessionBean#isInitOnStartup <em>Init On Startup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetInitOnStartup()
	 * @see #isInitOnStartup()
	 * @see #setInitOnStartup(boolean)
	 * @generated
	 */
	void unsetInitOnStartup();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.ejb.SessionBean#isInitOnStartup <em>Init On Startup</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Init On Startup</em>' attribute is set.
	 * @see #unsetInitOnStartup()
	 * @see #isInitOnStartup()
	 * @see #setInitOnStartup(boolean)
	 * @generated
	 */
	boolean isSetInitOnStartup();

	/**
	 * Returns the value of the '<em><b>Concurrency Management Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.jst.javaee.ejb.ConcurrencyManagementTypeType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Concurrency Management Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Concurrency Management Type</em>' attribute.
	 * @see org.eclipse.jst.javaee.ejb.ConcurrencyManagementTypeType
	 * @see #isSetConcurrencyManagementType()
	 * @see #unsetConcurrencyManagementType()
	 * @see #setConcurrencyManagementType(ConcurrencyManagementTypeType)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_ConcurrencyManagementType()
	 * @generated
	 */
	ConcurrencyManagementTypeType getConcurrencyManagementType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.SessionBean#getConcurrencyManagementType <em>Concurrency Management Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Concurrency Management Type</em>' attribute.
	 * @see org.eclipse.jst.javaee.ejb.ConcurrencyManagementTypeType
	 * @see #isSetConcurrencyManagementType()
	 * @see #unsetConcurrencyManagementType()
	 * @see #getConcurrencyManagementType()
	 * @generated
	 */
	void setConcurrencyManagementType(ConcurrencyManagementTypeType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.ejb.SessionBean#getConcurrencyManagementType <em>Concurrency Management Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetConcurrencyManagementType()
	 * @see #getConcurrencyManagementType()
	 * @see #setConcurrencyManagementType(ConcurrencyManagementTypeType)
	 * @generated
	 */
	void unsetConcurrencyManagementType();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.ejb.SessionBean#getConcurrencyManagementType <em>Concurrency Management Type</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Concurrency Management Type</em>' attribute is set.
	 * @see #unsetConcurrencyManagementType()
	 * @see #getConcurrencyManagementType()
	 * @see #setConcurrencyManagementType(ConcurrencyManagementTypeType)
	 * @generated
	 */
	boolean isSetConcurrencyManagementType();

	/**
	 * Returns the value of the '<em><b>Concurrent Method</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.ejb.ConcurrentMethodType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Concurrent Method</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Concurrent Method</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_ConcurrentMethod()
	 * @generated
	 */
	List<ConcurrentMethodType> getConcurrentMethod();

	/**
	 * Returns the value of the '<em><b>Depends On</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Depends On</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Depends On</em>' containment reference.
	 * @see #setDependsOn(DependsOnType)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_DependsOn()
	 * @generated
	 */
	DependsOnType getDependsOn();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.SessionBean#getDependsOn <em>Depends On</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Depends On</em>' containment reference.
	 * @see #getDependsOn()
	 * @generated
	 */
	void setDependsOn(DependsOnType value);

	/**
	 * Returns the value of the '<em><b>Init Methods</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.ejb.InitMethodType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The init-method element specifies the mappings for
	 *             EJB 2.x style create methods for an EJB 3.x bean.
	 *             This element can only be specified for stateful 
	 *             session beans. 
	 *             
	 *             @since Java EE 5, EJB 3.0
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Init Methods</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_InitMethods()
	 * @generated
	 */
	List<InitMethodType> getInitMethods();

	/**
	 * Returns the value of the '<em><b>Remove Methods</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.ejb.RemoveMethodType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The remove-method element specifies the mappings for
	 *             EJB 2.x style remove methods for an EJB 3.x bean.
	 *             This element can only be specified for stateful 
	 *             session beans. 
	 *             
	 *             @since Java EE 5, EJB 3.0
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Remove Methods</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_RemoveMethods()
	 * @generated
	 */
	List<RemoveMethodType> getRemoveMethods();

	/**
	 * Returns the value of the '<em><b>Async Method</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.ejb.AsyncMethodType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Async Method</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Async Method</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_AsyncMethod()
	 * @generated
	 */
	List<AsyncMethodType> getAsyncMethod();

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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_TransactionType()
	 * @generated
	 */
	TransactionType getTransactionType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.SessionBean#getTransactionType <em>Transaction Type</em>}' attribute.
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
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.ejb.SessionBean#getTransactionType <em>Transaction Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTransactionType()
	 * @see #getTransactionType()
	 * @see #setTransactionType(TransactionType)
	 * @generated
	 */
	void unsetTransactionType();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.ejb.SessionBean#getTransactionType <em>Transaction Type</em>}' attribute is set.
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
	 * Returns the value of the '<em><b>After Begin Method</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 6, EJB 3.1
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>After Begin Method</em>' containment reference.
	 * @see #setAfterBeginMethod(NamedMethodType)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_AfterBeginMethod()
	 * @generated
	 */
	NamedMethodType getAfterBeginMethod();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.SessionBean#getAfterBeginMethod <em>After Begin Method</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>After Begin Method</em>' containment reference.
	 * @see #getAfterBeginMethod()
	 * @generated
	 */
	void setAfterBeginMethod(NamedMethodType value);

	/**
	 * Returns the value of the '<em><b>Before Completion Method</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 6, EJB 3.1
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Before Completion Method</em>' containment reference.
	 * @see #setBeforeCompletionMethod(NamedMethodType)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_BeforeCompletionMethod()
	 * @generated
	 */
	NamedMethodType getBeforeCompletionMethod();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.SessionBean#getBeforeCompletionMethod <em>Before Completion Method</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Before Completion Method</em>' containment reference.
	 * @see #getBeforeCompletionMethod()
	 * @generated
	 */
	void setBeforeCompletionMethod(NamedMethodType value);

	/**
	 * Returns the value of the '<em><b>After Completion Method</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 6, EJB 3.1
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>After Completion Method</em>' containment reference.
	 * @see #setAfterCompletionMethod(NamedMethodType)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_AfterCompletionMethod()
	 * @generated
	 */
	NamedMethodType getAfterCompletionMethod();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.SessionBean#getAfterCompletionMethod <em>After Completion Method</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>After Completion Method</em>' containment reference.
	 * @see #getAfterCompletionMethod()
	 * @generated
	 */
	void setAfterCompletionMethod(NamedMethodType value);

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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_AroundInvokes()
	 * @generated
	 */
	List<AroundInvokeType> getAroundInvokes();

	/**
	 * Returns the value of the '<em><b>Around Timeouts</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.ejb.AroundTimeoutType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 6, EJB 3.1
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Around Timeouts</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_AroundTimeouts()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_EnvEntries()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_EjbRefs()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_EjbLocalRefs()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_ServiceRefs()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_ResourceRefs()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_ResourceEnvRefs()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_MessageDestinationRefs()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_PersistenceContextRefs()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_PersistenceUnitRefs()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_PostConstructs()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_PreDestroys()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_DataSource()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_JmsConnectionFactory()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_JmsDestination()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_MailSession()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_ConnectionFactory()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_AdministeredObject()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_PostActivates()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_PrePassivates()
	 * @generated
	 */
	List<LifecycleCallback> getPrePassivates();

	/**
	 * Returns the value of the '<em><b>Security Role Refs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.SecurityRoleRef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Security Role Refs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Security Role Refs</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_SecurityRoleRefs()
	 * @generated
	 */
	List<SecurityRoleRef> getSecurityRoleRefs();

	/**
	 * Returns the value of the '<em><b>Security Identities</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Security Identities</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Security Identities</em>' containment reference.
	 * @see #setSecurityIdentities(SecurityIdentityType)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_SecurityIdentities()
	 * @generated
	 */
	SecurityIdentityType getSecurityIdentities();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.SessionBean#getSecurityIdentities <em>Security Identities</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Security Identities</em>' containment reference.
	 * @see #getSecurityIdentities()
	 * @generated
	 */
	void setSecurityIdentities(SecurityIdentityType value);

	/**
	 * Returns the value of the '<em><b>Passivation Capable</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The passivation-capable element specifies whether the 
	 *             stateful session bean is passivation capable or not. 
	 *             If not specified, the bean is assumed to be passivation 
	 *             capable.
	 *             
	 *             @since Java EE 7, EJB 3.2
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Passivation Capable</em>' attribute.
	 * @see #isSetPassivationCapable()
	 * @see #unsetPassivationCapable()
	 * @see #setPassivationCapable(boolean)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_PassivationCapable()
	 * @generated
	 */
	boolean isPassivationCapable();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.SessionBean#isPassivationCapable <em>Passivation Capable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Passivation Capable</em>' attribute.
	 * @see #isSetPassivationCapable()
	 * @see #unsetPassivationCapable()
	 * @see #isPassivationCapable()
	 * @generated
	 */
	void setPassivationCapable(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.ejb.SessionBean#isPassivationCapable <em>Passivation Capable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPassivationCapable()
	 * @see #isPassivationCapable()
	 * @see #setPassivationCapable(boolean)
	 * @generated
	 */
	void unsetPassivationCapable();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.ejb.SessionBean#isPassivationCapable <em>Passivation Capable</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Passivation Capable</em>' attribute is set.
	 * @see #unsetPassivationCapable()
	 * @see #isPassivationCapable()
	 * @see #setPassivationCapable(boolean)
	 * @generated
	 */
	boolean isSetPassivationCapable();

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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.SessionBean#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // SessionBean