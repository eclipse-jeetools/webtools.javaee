/**
 * <copyright>
 * </copyright>
 *
 * $Id: SessionBean.java,v 1.1 2007/05/16 06:42:32 cbridgha Exp $
 */
package org.eclipse.jst.javaee.ejb;

import java.util.List;

import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Session Bean</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 * 	The session-beanType declares an session bean. The
 * 	declaration consists of:
 * 
 * 	    - an optional description
 * 	    - an optional display name
 * 	    - an optional icon element that contains a small and a large
 * 	      icon file name
 * 	    - a name assigned to the enterprise bean
 * 	      in the deployment description
 *             - an optional mapped-name element that can be used to provide
 *               vendor-specific deployment information such as the physical
 *               jndi-name of the session bean's remote home/business interface.
 *               This element is not required to be supported by all
 *               implementations. Any use of this element is non-portable.
 *             - the names of all the remote or local business interfaces,
 *               if any
 * 	    - the names of the session bean's remote home and
 * 	      remote interfaces, if any
 * 	    - the names of the session bean's local home and
 * 	      local interfaces, if any
 * 	    - the name of the session bean's web service endpoint
 * 	      interface, if any
 * 	    - the session bean's implementation class
 * 	    - the session bean's state management type
 *             - an optional declaration of the session bean's timeout method.
 * 	    - the optional session bean's transaction management type.
 *               If it is not present, it is defaulted to Container.
 *             - an optional list of the session bean class and/or
 *               superclass around-invoke methods.
 * 	    - an optional declaration of the bean's
 * 	      environment entries
 * 	    - an optional declaration of the bean's EJB references
 * 	    - an optional declaration of the bean's local
 * 	      EJB references
 * 	    - an optional declaration of the bean's web
 * 	      service references
 * 	    - an optional declaration of the security role
 * 	      references
 * 	    - an optional declaration of the security identity
 * 	      to be used for the execution of the bean's methods
 * 	    - an optional declaration of the bean's resource
 * 	      manager connection factory references
 * 	    - an optional declaration of the bean's resource
 * 	      environment references.
 * 	    - an optional declaration of the bean's message
 * 	      destination references
 * 
 * 	The elements that are optional are "optional" in the sense
 * 	that they are omitted when if lists represented by them are
 * 	empty.
 * 
 * 	Either both the local-home and the local elements or both
 * 	the home and the remote elements must be specified for the
 * 	session bean.
 * 
 * 	The service-endpoint element may only be specified if the
 * 	bean is a stateless session bean.
 * 
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
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getServiceEndpoint <em>Service Endpoint</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getEjbClass <em>Ejb Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getSessionType <em>Session Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getTimeoutMethod <em>Timeout Method</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getInitMethods <em>Init Methods</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getRemoveMethods <em>Remove Methods</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getTransactionType <em>Transaction Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getAroundInvokes <em>Around Invokes</em>}</li>
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
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getPostActivates <em>Post Activates</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getPrePassivates <em>Pre Passivates</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getSecurityRoleRefs <em>Security Role Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SessionBean#getSecurityIdentities <em>Security Identities</em>}</li>
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
	List getDescriptions();

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
	List getDisplayNames();

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
	List getIcons();

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
	List getBusinessLocals();

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
	List getBusinessRemotes();

	/**
	 * Returns the value of the '<em><b>Service Endpoint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 * 	    The service-endpoint element contains the
	 * 	    fully-qualified name of the enterprise bean's web
	 * 	    service endpoint interface. The service-endpoint
	 * 	    element may only be specified for a stateless
	 * 	    session bean. The specified interface must be a
	 * 	    valid JAX-RPC service endpoint interface.
	 * 
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
	 *              The ejb-class element specifies the fully qualified name
	 *              of the bean class for this ejb.  It is required unless
	 *              there is a component-defining annotation for the same
	 *              ejb-name.
	 * 
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
	 * The default value is <code>"Stateful"</code>.
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
	 * Returns the value of the '<em><b>Timeout Method</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timeout Method</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
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
	 * Returns the value of the '<em><b>Init Methods</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.ejb.InitMethodType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 * 	    The init-method element specifies the mappings for
	 * 	    EJB 2.x style create methods for an EJB 3.0 bean.
	 * 	    This element can only be specified for stateful
	 *             session beans.
	 * 
	 * 	  
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Init Methods</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_InitMethods()
	 * @generated
	 */
	List getInitMethods();

	/**
	 * Returns the value of the '<em><b>Remove Methods</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.ejb.RemoveMethodType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 * 	    The remove-method element specifies the mappings for
	 * 	    EJB 2.x style remove methods for an EJB 3.0 bean.
	 * 	    This element can only be specified for stateful
	 *             session beans.
	 * 
	 * 	  
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Remove Methods</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionBean_RemoveMethods()
	 * @generated
	 */
	List getRemoveMethods();

	/**
	 * Returns the value of the '<em><b>Transaction Type</b></em>' attribute.
	 * The default value is <code>"Bean"</code>.
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
	List getAroundInvokes();

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
	List getEnvEntries();

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
	List getEjbRefs();

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
	List getEjbLocalRefs();

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
	List getServiceRefs();

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
	List getResourceRefs();

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
	List getResourceEnvRefs();

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
	List getMessageDestinationRefs();

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
	List getPersistenceContextRefs();

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
	List getPersistenceUnitRefs();

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
	List getPostConstructs();

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
	List getPreDestroys();

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
	List getPostActivates();

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
	List getPrePassivates();

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
	List getSecurityRoleRefs();

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