/**
 * <copyright>
 * </copyright>
 *
 * $Id: SessionBeanImpl.java,v 1.1 2007/03/20 18:04:37 jsholl Exp $
 */
package org.eclipse.jst.javaee.ejb.internal.impl;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.DisplayName;
import org.eclipse.jst.javaee.core.EjbLocalRef;
import org.eclipse.jst.javaee.core.EjbRef;
import org.eclipse.jst.javaee.core.EnvEntry;
import org.eclipse.jst.javaee.core.Icon;
import org.eclipse.jst.javaee.core.LifecycleCallback;
import org.eclipse.jst.javaee.core.MessageDestinationRef;
import org.eclipse.jst.javaee.core.PersistenceContextRef;
import org.eclipse.jst.javaee.core.PersistenceUnitRef;
import org.eclipse.jst.javaee.core.ResourceEnvRef;
import org.eclipse.jst.javaee.core.ResourceRef;
import org.eclipse.jst.javaee.core.SecurityRoleRef;
import org.eclipse.jst.javaee.core.ServiceRef;

import org.eclipse.jst.javaee.ejb.AroundInvokeType;
import org.eclipse.jst.javaee.ejb.InitMethodType;
import org.eclipse.jst.javaee.ejb.NamedMethodType;
import org.eclipse.jst.javaee.ejb.RemoveMethodType;
import org.eclipse.jst.javaee.ejb.SecurityIdentityType;
import org.eclipse.jst.javaee.ejb.SessionBean;
import org.eclipse.jst.javaee.ejb.SessionType;
import org.eclipse.jst.javaee.ejb.TransactionType;

import org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Session Bean</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getDisplayNames <em>Display Names</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getIcons <em>Icons</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getEjbName <em>Ejb Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getMappedName <em>Mapped Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getHome <em>Home</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getRemote <em>Remote</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getLocalHome <em>Local Home</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getLocal <em>Local</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getBusinessLocals <em>Business Locals</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getBusinessRemotes <em>Business Remotes</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getServiceEndpoint <em>Service Endpoint</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getEjbClass <em>Ejb Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getSessionType <em>Session Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getTimeoutMethod <em>Timeout Method</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getInitMethods <em>Init Methods</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getRemoveMethods <em>Remove Methods</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getTransactionType <em>Transaction Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getAroundInvokes <em>Around Invokes</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getEnvEntries <em>Env Entries</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getEjbRefs <em>Ejb Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getEjbLocalRefs <em>Ejb Local Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getServiceRefs <em>Service Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getResourceRefs <em>Resource Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getResourceEnvRefs <em>Resource Env Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getMessageDestinationRefs <em>Message Destination Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getPersistenceContextRefs <em>Persistence Context Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getPersistenceUnitRefs <em>Persistence Unit Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getPostConstructs <em>Post Constructs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getPreDestroys <em>Pre Destroys</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getPostActivates <em>Post Activates</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getPrePassivates <em>Pre Passivates</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getSecurityRoleRefs <em>Security Role Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getSecurityIdentities <em>Security Identities</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.SessionBeanImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SessionBeanImpl extends EObjectImpl implements SessionBean {
	/**
	 * The cached value of the '{@link #getDescriptions() <em>Descriptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptions()
	 * @generated
	 * @ordered
	 */
	protected EList descriptions = null;

	/**
	 * The cached value of the '{@link #getDisplayNames() <em>Display Names</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayNames()
	 * @generated
	 * @ordered
	 */
	protected EList displayNames = null;

	/**
	 * The cached value of the '{@link #getIcons() <em>Icons</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIcons()
	 * @generated
	 * @ordered
	 */
	protected EList icons = null;

	/**
	 * The default value of the '{@link #getEjbName() <em>Ejb Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbName()
	 * @generated
	 * @ordered
	 */
	protected static final String EJB_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEjbName() <em>Ejb Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbName()
	 * @generated
	 * @ordered
	 */
	protected String ejbName = EJB_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getMappedName() <em>Mapped Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMappedName()
	 * @generated
	 * @ordered
	 */
	protected static final String MAPPED_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMappedName() <em>Mapped Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMappedName()
	 * @generated
	 * @ordered
	 */
	protected String mappedName = MAPPED_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getHome() <em>Home</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHome()
	 * @generated
	 * @ordered
	 */
	protected static final String HOME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHome() <em>Home</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHome()
	 * @generated
	 * @ordered
	 */
	protected String home = HOME_EDEFAULT;

	/**
	 * The default value of the '{@link #getRemote() <em>Remote</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemote()
	 * @generated
	 * @ordered
	 */
	protected static final String REMOTE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRemote() <em>Remote</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemote()
	 * @generated
	 * @ordered
	 */
	protected String remote = REMOTE_EDEFAULT;

	/**
	 * The default value of the '{@link #getLocalHome() <em>Local Home</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocalHome()
	 * @generated
	 * @ordered
	 */
	protected static final String LOCAL_HOME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLocalHome() <em>Local Home</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocalHome()
	 * @generated
	 * @ordered
	 */
	protected String localHome = LOCAL_HOME_EDEFAULT;

	/**
	 * The default value of the '{@link #getLocal() <em>Local</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocal()
	 * @generated
	 * @ordered
	 */
	protected static final String LOCAL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLocal() <em>Local</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocal()
	 * @generated
	 * @ordered
	 */
	protected String local = LOCAL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getBusinessLocals() <em>Business Locals</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBusinessLocals()
	 * @generated
	 * @ordered
	 */
	protected EList businessLocals = null;

	/**
	 * The cached value of the '{@link #getBusinessRemotes() <em>Business Remotes</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBusinessRemotes()
	 * @generated
	 * @ordered
	 */
	protected EList businessRemotes = null;

	/**
	 * The default value of the '{@link #getServiceEndpoint() <em>Service Endpoint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceEndpoint()
	 * @generated
	 * @ordered
	 */
	protected static final String SERVICE_ENDPOINT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getServiceEndpoint() <em>Service Endpoint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceEndpoint()
	 * @generated
	 * @ordered
	 */
	protected String serviceEndpoint = SERVICE_ENDPOINT_EDEFAULT;

	/**
	 * The default value of the '{@link #getEjbClass() <em>Ejb Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbClass()
	 * @generated
	 * @ordered
	 */
	protected static final String EJB_CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEjbClass() <em>Ejb Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbClass()
	 * @generated
	 * @ordered
	 */
	protected String ejbClass = EJB_CLASS_EDEFAULT;

	/**
	 * The default value of the '{@link #getSessionType() <em>Session Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSessionType()
	 * @generated
	 * @ordered
	 */
	protected static final SessionType SESSION_TYPE_EDEFAULT = SessionType.STATEFUL_LITERAL;

	/**
	 * The cached value of the '{@link #getSessionType() <em>Session Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSessionType()
	 * @generated
	 * @ordered
	 */
	protected SessionType sessionType = SESSION_TYPE_EDEFAULT;

	/**
	 * This is true if the Session Type attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean sessionTypeESet = false;

	/**
	 * The cached value of the '{@link #getTimeoutMethod() <em>Timeout Method</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeoutMethod()
	 * @generated
	 * @ordered
	 */
	protected NamedMethodType timeoutMethod = null;

	/**
	 * The cached value of the '{@link #getInitMethods() <em>Init Methods</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitMethods()
	 * @generated
	 * @ordered
	 */
	protected EList initMethods = null;

	/**
	 * The cached value of the '{@link #getRemoveMethods() <em>Remove Methods</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemoveMethods()
	 * @generated
	 * @ordered
	 */
	protected EList removeMethods = null;

	/**
	 * The default value of the '{@link #getTransactionType() <em>Transaction Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransactionType()
	 * @generated
	 * @ordered
	 */
	protected static final TransactionType TRANSACTION_TYPE_EDEFAULT = TransactionType.BEAN_LITERAL;

	/**
	 * The cached value of the '{@link #getTransactionType() <em>Transaction Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransactionType()
	 * @generated
	 * @ordered
	 */
	protected TransactionType transactionType = TRANSACTION_TYPE_EDEFAULT;

	/**
	 * This is true if the Transaction Type attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean transactionTypeESet = false;

	/**
	 * The cached value of the '{@link #getAroundInvokes() <em>Around Invokes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAroundInvokes()
	 * @generated
	 * @ordered
	 */
	protected EList aroundInvokes = null;

	/**
	 * The cached value of the '{@link #getEnvEntries() <em>Env Entries</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnvEntries()
	 * @generated
	 * @ordered
	 */
	protected EList envEntries = null;

	/**
	 * The cached value of the '{@link #getEjbRefs() <em>Ejb Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbRefs()
	 * @generated
	 * @ordered
	 */
	protected EList ejbRefs = null;

	/**
	 * The cached value of the '{@link #getEjbLocalRefs() <em>Ejb Local Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbLocalRefs()
	 * @generated
	 * @ordered
	 */
	protected EList ejbLocalRefs = null;

	/**
	 * The cached value of the '{@link #getServiceRefs() <em>Service Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceRefs()
	 * @generated
	 * @ordered
	 */
	protected EList serviceRefs = null;

	/**
	 * The cached value of the '{@link #getResourceRefs() <em>Resource Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceRefs()
	 * @generated
	 * @ordered
	 */
	protected EList resourceRefs = null;

	/**
	 * The cached value of the '{@link #getResourceEnvRefs() <em>Resource Env Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceEnvRefs()
	 * @generated
	 * @ordered
	 */
	protected EList resourceEnvRefs = null;

	/**
	 * The cached value of the '{@link #getMessageDestinationRefs() <em>Message Destination Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageDestinationRefs()
	 * @generated
	 * @ordered
	 */
	protected EList messageDestinationRefs = null;

	/**
	 * The cached value of the '{@link #getPersistenceContextRefs() <em>Persistence Context Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPersistenceContextRefs()
	 * @generated
	 * @ordered
	 */
	protected EList persistenceContextRefs = null;

	/**
	 * The cached value of the '{@link #getPersistenceUnitRefs() <em>Persistence Unit Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPersistenceUnitRefs()
	 * @generated
	 * @ordered
	 */
	protected EList persistenceUnitRefs = null;

	/**
	 * The cached value of the '{@link #getPostConstructs() <em>Post Constructs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPostConstructs()
	 * @generated
	 * @ordered
	 */
	protected EList postConstructs = null;

	/**
	 * The cached value of the '{@link #getPreDestroys() <em>Pre Destroys</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreDestroys()
	 * @generated
	 * @ordered
	 */
	protected EList preDestroys = null;

	/**
	 * The cached value of the '{@link #getPostActivates() <em>Post Activates</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPostActivates()
	 * @generated
	 * @ordered
	 */
	protected EList postActivates = null;

	/**
	 * The cached value of the '{@link #getPrePassivates() <em>Pre Passivates</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrePassivates()
	 * @generated
	 * @ordered
	 */
	protected EList prePassivates = null;

	/**
	 * The cached value of the '{@link #getSecurityRoleRefs() <em>Security Role Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecurityRoleRefs()
	 * @generated
	 * @ordered
	 */
	protected EList securityRoleRefs = null;

	/**
	 * The cached value of the '{@link #getSecurityIdentities() <em>Security Identities</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecurityIdentities()
	 * @generated
	 * @ordered
	 */
	protected SecurityIdentityType securityIdentities = null;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SessionBeanImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return EjbPackage.Literals.SESSION_BEAN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList(Description.class, this, EjbPackage.SESSION_BEAN__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getDisplayNames() {
		if (displayNames == null) {
			displayNames = new EObjectContainmentEList(DisplayName.class, this, EjbPackage.SESSION_BEAN__DISPLAY_NAMES);
		}
		return displayNames;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getIcons() {
		if (icons == null) {
			icons = new EObjectContainmentEList(Icon.class, this, EjbPackage.SESSION_BEAN__ICONS);
		}
		return icons;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEjbName() {
		return ejbName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEjbName(String newEjbName) {
		String oldEjbName = ejbName;
		ejbName = newEjbName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__EJB_NAME, oldEjbName, ejbName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMappedName() {
		return mappedName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMappedName(String newMappedName) {
		String oldMappedName = mappedName;
		mappedName = newMappedName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__MAPPED_NAME, oldMappedName, mappedName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHome() {
		return home;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHome(String newHome) {
		String oldHome = home;
		home = newHome;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__HOME, oldHome, home));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRemote() {
		return remote;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRemote(String newRemote) {
		String oldRemote = remote;
		remote = newRemote;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__REMOTE, oldRemote, remote));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLocalHome() {
		return localHome;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocalHome(String newLocalHome) {
		String oldLocalHome = localHome;
		localHome = newLocalHome;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__LOCAL_HOME, oldLocalHome, localHome));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLocal() {
		return local;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocal(String newLocal) {
		String oldLocal = local;
		local = newLocal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__LOCAL, oldLocal, local));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getBusinessLocals() {
		if (businessLocals == null) {
			businessLocals = new EDataTypeEList(String.class, this, EjbPackage.SESSION_BEAN__BUSINESS_LOCALS);
		}
		return businessLocals;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getBusinessRemotes() {
		if (businessRemotes == null) {
			businessRemotes = new EDataTypeEList(String.class, this, EjbPackage.SESSION_BEAN__BUSINESS_REMOTES);
		}
		return businessRemotes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getServiceEndpoint() {
		return serviceEndpoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServiceEndpoint(String newServiceEndpoint) {
		String oldServiceEndpoint = serviceEndpoint;
		serviceEndpoint = newServiceEndpoint;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__SERVICE_ENDPOINT, oldServiceEndpoint, serviceEndpoint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEjbClass() {
		return ejbClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEjbClass(String newEjbClass) {
		String oldEjbClass = ejbClass;
		ejbClass = newEjbClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__EJB_CLASS, oldEjbClass, ejbClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SessionType getSessionType() {
		return sessionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSessionType(SessionType newSessionType) {
		SessionType oldSessionType = sessionType;
		sessionType = newSessionType == null ? SESSION_TYPE_EDEFAULT : newSessionType;
		boolean oldSessionTypeESet = sessionTypeESet;
		sessionTypeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__SESSION_TYPE, oldSessionType, sessionType, !oldSessionTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetSessionType() {
		SessionType oldSessionType = sessionType;
		boolean oldSessionTypeESet = sessionTypeESet;
		sessionType = SESSION_TYPE_EDEFAULT;
		sessionTypeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, EjbPackage.SESSION_BEAN__SESSION_TYPE, oldSessionType, SESSION_TYPE_EDEFAULT, oldSessionTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetSessionType() {
		return sessionTypeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NamedMethodType getTimeoutMethod() {
		return timeoutMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTimeoutMethod(NamedMethodType newTimeoutMethod, NotificationChain msgs) {
		NamedMethodType oldTimeoutMethod = timeoutMethod;
		timeoutMethod = newTimeoutMethod;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__TIMEOUT_METHOD, oldTimeoutMethod, newTimeoutMethod);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimeoutMethod(NamedMethodType newTimeoutMethod) {
		if (newTimeoutMethod != timeoutMethod) {
			NotificationChain msgs = null;
			if (timeoutMethod != null)
				msgs = ((InternalEObject)timeoutMethod).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.SESSION_BEAN__TIMEOUT_METHOD, null, msgs);
			if (newTimeoutMethod != null)
				msgs = ((InternalEObject)newTimeoutMethod).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EjbPackage.SESSION_BEAN__TIMEOUT_METHOD, null, msgs);
			msgs = basicSetTimeoutMethod(newTimeoutMethod, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__TIMEOUT_METHOD, newTimeoutMethod, newTimeoutMethod));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getInitMethods() {
		if (initMethods == null) {
			initMethods = new EObjectContainmentEList(InitMethodType.class, this, EjbPackage.SESSION_BEAN__INIT_METHODS);
		}
		return initMethods;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getRemoveMethods() {
		if (removeMethods == null) {
			removeMethods = new EObjectContainmentEList(RemoveMethodType.class, this, EjbPackage.SESSION_BEAN__REMOVE_METHODS);
		}
		return removeMethods;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransactionType getTransactionType() {
		return transactionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransactionType(TransactionType newTransactionType) {
		TransactionType oldTransactionType = transactionType;
		transactionType = newTransactionType == null ? TRANSACTION_TYPE_EDEFAULT : newTransactionType;
		boolean oldTransactionTypeESet = transactionTypeESet;
		transactionTypeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__TRANSACTION_TYPE, oldTransactionType, transactionType, !oldTransactionTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetTransactionType() {
		TransactionType oldTransactionType = transactionType;
		boolean oldTransactionTypeESet = transactionTypeESet;
		transactionType = TRANSACTION_TYPE_EDEFAULT;
		transactionTypeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, EjbPackage.SESSION_BEAN__TRANSACTION_TYPE, oldTransactionType, TRANSACTION_TYPE_EDEFAULT, oldTransactionTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetTransactionType() {
		return transactionTypeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getAroundInvokes() {
		if (aroundInvokes == null) {
			aroundInvokes = new EObjectContainmentEList(AroundInvokeType.class, this, EjbPackage.SESSION_BEAN__AROUND_INVOKES);
		}
		return aroundInvokes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getEnvEntries() {
		if (envEntries == null) {
			envEntries = new EObjectContainmentEList(EnvEntry.class, this, EjbPackage.SESSION_BEAN__ENV_ENTRIES);
		}
		return envEntries;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getEjbRefs() {
		if (ejbRefs == null) {
			ejbRefs = new EObjectContainmentEList(EjbRef.class, this, EjbPackage.SESSION_BEAN__EJB_REFS);
		}
		return ejbRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getEjbLocalRefs() {
		if (ejbLocalRefs == null) {
			ejbLocalRefs = new EObjectContainmentEList(EjbLocalRef.class, this, EjbPackage.SESSION_BEAN__EJB_LOCAL_REFS);
		}
		return ejbLocalRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getServiceRefs() {
		if (serviceRefs == null) {
			serviceRefs = new EObjectContainmentEList(ServiceRef.class, this, EjbPackage.SESSION_BEAN__SERVICE_REFS);
		}
		return serviceRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getResourceRefs() {
		if (resourceRefs == null) {
			resourceRefs = new EObjectContainmentEList(ResourceRef.class, this, EjbPackage.SESSION_BEAN__RESOURCE_REFS);
		}
		return resourceRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getResourceEnvRefs() {
		if (resourceEnvRefs == null) {
			resourceEnvRefs = new EObjectContainmentEList(ResourceEnvRef.class, this, EjbPackage.SESSION_BEAN__RESOURCE_ENV_REFS);
		}
		return resourceEnvRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getMessageDestinationRefs() {
		if (messageDestinationRefs == null) {
			messageDestinationRefs = new EObjectContainmentEList(MessageDestinationRef.class, this, EjbPackage.SESSION_BEAN__MESSAGE_DESTINATION_REFS);
		}
		return messageDestinationRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getPersistenceContextRefs() {
		if (persistenceContextRefs == null) {
			persistenceContextRefs = new EObjectContainmentEList(PersistenceContextRef.class, this, EjbPackage.SESSION_BEAN__PERSISTENCE_CONTEXT_REFS);
		}
		return persistenceContextRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getPersistenceUnitRefs() {
		if (persistenceUnitRefs == null) {
			persistenceUnitRefs = new EObjectContainmentEList(PersistenceUnitRef.class, this, EjbPackage.SESSION_BEAN__PERSISTENCE_UNIT_REFS);
		}
		return persistenceUnitRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getPostConstructs() {
		if (postConstructs == null) {
			postConstructs = new EObjectContainmentEList(LifecycleCallback.class, this, EjbPackage.SESSION_BEAN__POST_CONSTRUCTS);
		}
		return postConstructs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getPreDestroys() {
		if (preDestroys == null) {
			preDestroys = new EObjectContainmentEList(LifecycleCallback.class, this, EjbPackage.SESSION_BEAN__PRE_DESTROYS);
		}
		return preDestroys;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getPostActivates() {
		if (postActivates == null) {
			postActivates = new EObjectContainmentEList(LifecycleCallback.class, this, EjbPackage.SESSION_BEAN__POST_ACTIVATES);
		}
		return postActivates;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getPrePassivates() {
		if (prePassivates == null) {
			prePassivates = new EObjectContainmentEList(LifecycleCallback.class, this, EjbPackage.SESSION_BEAN__PRE_PASSIVATES);
		}
		return prePassivates;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getSecurityRoleRefs() {
		if (securityRoleRefs == null) {
			securityRoleRefs = new EObjectContainmentEList(SecurityRoleRef.class, this, EjbPackage.SESSION_BEAN__SECURITY_ROLE_REFS);
		}
		return securityRoleRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SecurityIdentityType getSecurityIdentities() {
		return securityIdentities;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSecurityIdentities(SecurityIdentityType newSecurityIdentities, NotificationChain msgs) {
		SecurityIdentityType oldSecurityIdentities = securityIdentities;
		securityIdentities = newSecurityIdentities;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__SECURITY_IDENTITIES, oldSecurityIdentities, newSecurityIdentities);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSecurityIdentities(SecurityIdentityType newSecurityIdentities) {
		if (newSecurityIdentities != securityIdentities) {
			NotificationChain msgs = null;
			if (securityIdentities != null)
				msgs = ((InternalEObject)securityIdentities).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.SESSION_BEAN__SECURITY_IDENTITIES, null, msgs);
			if (newSecurityIdentities != null)
				msgs = ((InternalEObject)newSecurityIdentities).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EjbPackage.SESSION_BEAN__SECURITY_IDENTITIES, null, msgs);
			msgs = basicSetSecurityIdentities(newSecurityIdentities, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__SECURITY_IDENTITIES, newSecurityIdentities, newSecurityIdentities));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION_BEAN__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EjbPackage.SESSION_BEAN__DESCRIPTIONS:
				return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__DISPLAY_NAMES:
				return ((InternalEList)getDisplayNames()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__ICONS:
				return ((InternalEList)getIcons()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__TIMEOUT_METHOD:
				return basicSetTimeoutMethod(null, msgs);
			case EjbPackage.SESSION_BEAN__INIT_METHODS:
				return ((InternalEList)getInitMethods()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__REMOVE_METHODS:
				return ((InternalEList)getRemoveMethods()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__AROUND_INVOKES:
				return ((InternalEList)getAroundInvokes()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__ENV_ENTRIES:
				return ((InternalEList)getEnvEntries()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__EJB_REFS:
				return ((InternalEList)getEjbRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__EJB_LOCAL_REFS:
				return ((InternalEList)getEjbLocalRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__SERVICE_REFS:
				return ((InternalEList)getServiceRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__RESOURCE_REFS:
				return ((InternalEList)getResourceRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__RESOURCE_ENV_REFS:
				return ((InternalEList)getResourceEnvRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__MESSAGE_DESTINATION_REFS:
				return ((InternalEList)getMessageDestinationRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__PERSISTENCE_CONTEXT_REFS:
				return ((InternalEList)getPersistenceContextRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__PERSISTENCE_UNIT_REFS:
				return ((InternalEList)getPersistenceUnitRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__POST_CONSTRUCTS:
				return ((InternalEList)getPostConstructs()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__PRE_DESTROYS:
				return ((InternalEList)getPreDestroys()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__POST_ACTIVATES:
				return ((InternalEList)getPostActivates()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__PRE_PASSIVATES:
				return ((InternalEList)getPrePassivates()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__SECURITY_ROLE_REFS:
				return ((InternalEList)getSecurityRoleRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.SESSION_BEAN__SECURITY_IDENTITIES:
				return basicSetSecurityIdentities(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EjbPackage.SESSION_BEAN__DESCRIPTIONS:
				return getDescriptions();
			case EjbPackage.SESSION_BEAN__DISPLAY_NAMES:
				return getDisplayNames();
			case EjbPackage.SESSION_BEAN__ICONS:
				return getIcons();
			case EjbPackage.SESSION_BEAN__EJB_NAME:
				return getEjbName();
			case EjbPackage.SESSION_BEAN__MAPPED_NAME:
				return getMappedName();
			case EjbPackage.SESSION_BEAN__HOME:
				return getHome();
			case EjbPackage.SESSION_BEAN__REMOTE:
				return getRemote();
			case EjbPackage.SESSION_BEAN__LOCAL_HOME:
				return getLocalHome();
			case EjbPackage.SESSION_BEAN__LOCAL:
				return getLocal();
			case EjbPackage.SESSION_BEAN__BUSINESS_LOCALS:
				return getBusinessLocals();
			case EjbPackage.SESSION_BEAN__BUSINESS_REMOTES:
				return getBusinessRemotes();
			case EjbPackage.SESSION_BEAN__SERVICE_ENDPOINT:
				return getServiceEndpoint();
			case EjbPackage.SESSION_BEAN__EJB_CLASS:
				return getEjbClass();
			case EjbPackage.SESSION_BEAN__SESSION_TYPE:
				return getSessionType();
			case EjbPackage.SESSION_BEAN__TIMEOUT_METHOD:
				return getTimeoutMethod();
			case EjbPackage.SESSION_BEAN__INIT_METHODS:
				return getInitMethods();
			case EjbPackage.SESSION_BEAN__REMOVE_METHODS:
				return getRemoveMethods();
			case EjbPackage.SESSION_BEAN__TRANSACTION_TYPE:
				return getTransactionType();
			case EjbPackage.SESSION_BEAN__AROUND_INVOKES:
				return getAroundInvokes();
			case EjbPackage.SESSION_BEAN__ENV_ENTRIES:
				return getEnvEntries();
			case EjbPackage.SESSION_BEAN__EJB_REFS:
				return getEjbRefs();
			case EjbPackage.SESSION_BEAN__EJB_LOCAL_REFS:
				return getEjbLocalRefs();
			case EjbPackage.SESSION_BEAN__SERVICE_REFS:
				return getServiceRefs();
			case EjbPackage.SESSION_BEAN__RESOURCE_REFS:
				return getResourceRefs();
			case EjbPackage.SESSION_BEAN__RESOURCE_ENV_REFS:
				return getResourceEnvRefs();
			case EjbPackage.SESSION_BEAN__MESSAGE_DESTINATION_REFS:
				return getMessageDestinationRefs();
			case EjbPackage.SESSION_BEAN__PERSISTENCE_CONTEXT_REFS:
				return getPersistenceContextRefs();
			case EjbPackage.SESSION_BEAN__PERSISTENCE_UNIT_REFS:
				return getPersistenceUnitRefs();
			case EjbPackage.SESSION_BEAN__POST_CONSTRUCTS:
				return getPostConstructs();
			case EjbPackage.SESSION_BEAN__PRE_DESTROYS:
				return getPreDestroys();
			case EjbPackage.SESSION_BEAN__POST_ACTIVATES:
				return getPostActivates();
			case EjbPackage.SESSION_BEAN__PRE_PASSIVATES:
				return getPrePassivates();
			case EjbPackage.SESSION_BEAN__SECURITY_ROLE_REFS:
				return getSecurityRoleRefs();
			case EjbPackage.SESSION_BEAN__SECURITY_IDENTITIES:
				return getSecurityIdentities();
			case EjbPackage.SESSION_BEAN__ID:
				return getId();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case EjbPackage.SESSION_BEAN__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
				return;
			case EjbPackage.SESSION_BEAN__DISPLAY_NAMES:
				getDisplayNames().clear();
				getDisplayNames().addAll((Collection)newValue);
				return;
			case EjbPackage.SESSION_BEAN__ICONS:
				getIcons().clear();
				getIcons().addAll((Collection)newValue);
				return;
			case EjbPackage.SESSION_BEAN__EJB_NAME:
				setEjbName((String)newValue);
				return;
			case EjbPackage.SESSION_BEAN__MAPPED_NAME:
				setMappedName((String)newValue);
				return;
			case EjbPackage.SESSION_BEAN__HOME:
				setHome((String)newValue);
				return;
			case EjbPackage.SESSION_BEAN__REMOTE:
				setRemote((String)newValue);
				return;
			case EjbPackage.SESSION_BEAN__LOCAL_HOME:
				setLocalHome((String)newValue);
				return;
			case EjbPackage.SESSION_BEAN__LOCAL:
				setLocal((String)newValue);
				return;
			case EjbPackage.SESSION_BEAN__BUSINESS_LOCALS:
				getBusinessLocals().clear();
				getBusinessLocals().addAll((Collection)newValue);
				return;
			case EjbPackage.SESSION_BEAN__BUSINESS_REMOTES:
				getBusinessRemotes().clear();
				getBusinessRemotes().addAll((Collection)newValue);
				return;
			case EjbPackage.SESSION_BEAN__SERVICE_ENDPOINT:
				setServiceEndpoint((String)newValue);
				return;
			case EjbPackage.SESSION_BEAN__EJB_CLASS:
				setEjbClass((String)newValue);
				return;
			case EjbPackage.SESSION_BEAN__SESSION_TYPE:
				setSessionType((SessionType)newValue);
				return;
			case EjbPackage.SESSION_BEAN__TIMEOUT_METHOD:
				setTimeoutMethod((NamedMethodType)newValue);
				return;
			case EjbPackage.SESSION_BEAN__INIT_METHODS:
				getInitMethods().clear();
				getInitMethods().addAll((Collection)newValue);
				return;
			case EjbPackage.SESSION_BEAN__REMOVE_METHODS:
				getRemoveMethods().clear();
				getRemoveMethods().addAll((Collection)newValue);
				return;
			case EjbPackage.SESSION_BEAN__TRANSACTION_TYPE:
				setTransactionType((TransactionType)newValue);
				return;
			case EjbPackage.SESSION_BEAN__AROUND_INVOKES:
				getAroundInvokes().clear();
				getAroundInvokes().addAll((Collection)newValue);
				return;
			case EjbPackage.SESSION_BEAN__ENV_ENTRIES:
				getEnvEntries().clear();
				getEnvEntries().addAll((Collection)newValue);
				return;
			case EjbPackage.SESSION_BEAN__EJB_REFS:
				getEjbRefs().clear();
				getEjbRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.SESSION_BEAN__EJB_LOCAL_REFS:
				getEjbLocalRefs().clear();
				getEjbLocalRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.SESSION_BEAN__SERVICE_REFS:
				getServiceRefs().clear();
				getServiceRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.SESSION_BEAN__RESOURCE_REFS:
				getResourceRefs().clear();
				getResourceRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.SESSION_BEAN__RESOURCE_ENV_REFS:
				getResourceEnvRefs().clear();
				getResourceEnvRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.SESSION_BEAN__MESSAGE_DESTINATION_REFS:
				getMessageDestinationRefs().clear();
				getMessageDestinationRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.SESSION_BEAN__PERSISTENCE_CONTEXT_REFS:
				getPersistenceContextRefs().clear();
				getPersistenceContextRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.SESSION_BEAN__PERSISTENCE_UNIT_REFS:
				getPersistenceUnitRefs().clear();
				getPersistenceUnitRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.SESSION_BEAN__POST_CONSTRUCTS:
				getPostConstructs().clear();
				getPostConstructs().addAll((Collection)newValue);
				return;
			case EjbPackage.SESSION_BEAN__PRE_DESTROYS:
				getPreDestroys().clear();
				getPreDestroys().addAll((Collection)newValue);
				return;
			case EjbPackage.SESSION_BEAN__POST_ACTIVATES:
				getPostActivates().clear();
				getPostActivates().addAll((Collection)newValue);
				return;
			case EjbPackage.SESSION_BEAN__PRE_PASSIVATES:
				getPrePassivates().clear();
				getPrePassivates().addAll((Collection)newValue);
				return;
			case EjbPackage.SESSION_BEAN__SECURITY_ROLE_REFS:
				getSecurityRoleRefs().clear();
				getSecurityRoleRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.SESSION_BEAN__SECURITY_IDENTITIES:
				setSecurityIdentities((SecurityIdentityType)newValue);
				return;
			case EjbPackage.SESSION_BEAN__ID:
				setId((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case EjbPackage.SESSION_BEAN__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case EjbPackage.SESSION_BEAN__DISPLAY_NAMES:
				getDisplayNames().clear();
				return;
			case EjbPackage.SESSION_BEAN__ICONS:
				getIcons().clear();
				return;
			case EjbPackage.SESSION_BEAN__EJB_NAME:
				setEjbName(EJB_NAME_EDEFAULT);
				return;
			case EjbPackage.SESSION_BEAN__MAPPED_NAME:
				setMappedName(MAPPED_NAME_EDEFAULT);
				return;
			case EjbPackage.SESSION_BEAN__HOME:
				setHome(HOME_EDEFAULT);
				return;
			case EjbPackage.SESSION_BEAN__REMOTE:
				setRemote(REMOTE_EDEFAULT);
				return;
			case EjbPackage.SESSION_BEAN__LOCAL_HOME:
				setLocalHome(LOCAL_HOME_EDEFAULT);
				return;
			case EjbPackage.SESSION_BEAN__LOCAL:
				setLocal(LOCAL_EDEFAULT);
				return;
			case EjbPackage.SESSION_BEAN__BUSINESS_LOCALS:
				getBusinessLocals().clear();
				return;
			case EjbPackage.SESSION_BEAN__BUSINESS_REMOTES:
				getBusinessRemotes().clear();
				return;
			case EjbPackage.SESSION_BEAN__SERVICE_ENDPOINT:
				setServiceEndpoint(SERVICE_ENDPOINT_EDEFAULT);
				return;
			case EjbPackage.SESSION_BEAN__EJB_CLASS:
				setEjbClass(EJB_CLASS_EDEFAULT);
				return;
			case EjbPackage.SESSION_BEAN__SESSION_TYPE:
				unsetSessionType();
				return;
			case EjbPackage.SESSION_BEAN__TIMEOUT_METHOD:
				setTimeoutMethod((NamedMethodType)null);
				return;
			case EjbPackage.SESSION_BEAN__INIT_METHODS:
				getInitMethods().clear();
				return;
			case EjbPackage.SESSION_BEAN__REMOVE_METHODS:
				getRemoveMethods().clear();
				return;
			case EjbPackage.SESSION_BEAN__TRANSACTION_TYPE:
				unsetTransactionType();
				return;
			case EjbPackage.SESSION_BEAN__AROUND_INVOKES:
				getAroundInvokes().clear();
				return;
			case EjbPackage.SESSION_BEAN__ENV_ENTRIES:
				getEnvEntries().clear();
				return;
			case EjbPackage.SESSION_BEAN__EJB_REFS:
				getEjbRefs().clear();
				return;
			case EjbPackage.SESSION_BEAN__EJB_LOCAL_REFS:
				getEjbLocalRefs().clear();
				return;
			case EjbPackage.SESSION_BEAN__SERVICE_REFS:
				getServiceRefs().clear();
				return;
			case EjbPackage.SESSION_BEAN__RESOURCE_REFS:
				getResourceRefs().clear();
				return;
			case EjbPackage.SESSION_BEAN__RESOURCE_ENV_REFS:
				getResourceEnvRefs().clear();
				return;
			case EjbPackage.SESSION_BEAN__MESSAGE_DESTINATION_REFS:
				getMessageDestinationRefs().clear();
				return;
			case EjbPackage.SESSION_BEAN__PERSISTENCE_CONTEXT_REFS:
				getPersistenceContextRefs().clear();
				return;
			case EjbPackage.SESSION_BEAN__PERSISTENCE_UNIT_REFS:
				getPersistenceUnitRefs().clear();
				return;
			case EjbPackage.SESSION_BEAN__POST_CONSTRUCTS:
				getPostConstructs().clear();
				return;
			case EjbPackage.SESSION_BEAN__PRE_DESTROYS:
				getPreDestroys().clear();
				return;
			case EjbPackage.SESSION_BEAN__POST_ACTIVATES:
				getPostActivates().clear();
				return;
			case EjbPackage.SESSION_BEAN__PRE_PASSIVATES:
				getPrePassivates().clear();
				return;
			case EjbPackage.SESSION_BEAN__SECURITY_ROLE_REFS:
				getSecurityRoleRefs().clear();
				return;
			case EjbPackage.SESSION_BEAN__SECURITY_IDENTITIES:
				setSecurityIdentities((SecurityIdentityType)null);
				return;
			case EjbPackage.SESSION_BEAN__ID:
				setId(ID_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case EjbPackage.SESSION_BEAN__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case EjbPackage.SESSION_BEAN__DISPLAY_NAMES:
				return displayNames != null && !displayNames.isEmpty();
			case EjbPackage.SESSION_BEAN__ICONS:
				return icons != null && !icons.isEmpty();
			case EjbPackage.SESSION_BEAN__EJB_NAME:
				return EJB_NAME_EDEFAULT == null ? ejbName != null : !EJB_NAME_EDEFAULT.equals(ejbName);
			case EjbPackage.SESSION_BEAN__MAPPED_NAME:
				return MAPPED_NAME_EDEFAULT == null ? mappedName != null : !MAPPED_NAME_EDEFAULT.equals(mappedName);
			case EjbPackage.SESSION_BEAN__HOME:
				return HOME_EDEFAULT == null ? home != null : !HOME_EDEFAULT.equals(home);
			case EjbPackage.SESSION_BEAN__REMOTE:
				return REMOTE_EDEFAULT == null ? remote != null : !REMOTE_EDEFAULT.equals(remote);
			case EjbPackage.SESSION_BEAN__LOCAL_HOME:
				return LOCAL_HOME_EDEFAULT == null ? localHome != null : !LOCAL_HOME_EDEFAULT.equals(localHome);
			case EjbPackage.SESSION_BEAN__LOCAL:
				return LOCAL_EDEFAULT == null ? local != null : !LOCAL_EDEFAULT.equals(local);
			case EjbPackage.SESSION_BEAN__BUSINESS_LOCALS:
				return businessLocals != null && !businessLocals.isEmpty();
			case EjbPackage.SESSION_BEAN__BUSINESS_REMOTES:
				return businessRemotes != null && !businessRemotes.isEmpty();
			case EjbPackage.SESSION_BEAN__SERVICE_ENDPOINT:
				return SERVICE_ENDPOINT_EDEFAULT == null ? serviceEndpoint != null : !SERVICE_ENDPOINT_EDEFAULT.equals(serviceEndpoint);
			case EjbPackage.SESSION_BEAN__EJB_CLASS:
				return EJB_CLASS_EDEFAULT == null ? ejbClass != null : !EJB_CLASS_EDEFAULT.equals(ejbClass);
			case EjbPackage.SESSION_BEAN__SESSION_TYPE:
				return isSetSessionType();
			case EjbPackage.SESSION_BEAN__TIMEOUT_METHOD:
				return timeoutMethod != null;
			case EjbPackage.SESSION_BEAN__INIT_METHODS:
				return initMethods != null && !initMethods.isEmpty();
			case EjbPackage.SESSION_BEAN__REMOVE_METHODS:
				return removeMethods != null && !removeMethods.isEmpty();
			case EjbPackage.SESSION_BEAN__TRANSACTION_TYPE:
				return isSetTransactionType();
			case EjbPackage.SESSION_BEAN__AROUND_INVOKES:
				return aroundInvokes != null && !aroundInvokes.isEmpty();
			case EjbPackage.SESSION_BEAN__ENV_ENTRIES:
				return envEntries != null && !envEntries.isEmpty();
			case EjbPackage.SESSION_BEAN__EJB_REFS:
				return ejbRefs != null && !ejbRefs.isEmpty();
			case EjbPackage.SESSION_BEAN__EJB_LOCAL_REFS:
				return ejbLocalRefs != null && !ejbLocalRefs.isEmpty();
			case EjbPackage.SESSION_BEAN__SERVICE_REFS:
				return serviceRefs != null && !serviceRefs.isEmpty();
			case EjbPackage.SESSION_BEAN__RESOURCE_REFS:
				return resourceRefs != null && !resourceRefs.isEmpty();
			case EjbPackage.SESSION_BEAN__RESOURCE_ENV_REFS:
				return resourceEnvRefs != null && !resourceEnvRefs.isEmpty();
			case EjbPackage.SESSION_BEAN__MESSAGE_DESTINATION_REFS:
				return messageDestinationRefs != null && !messageDestinationRefs.isEmpty();
			case EjbPackage.SESSION_BEAN__PERSISTENCE_CONTEXT_REFS:
				return persistenceContextRefs != null && !persistenceContextRefs.isEmpty();
			case EjbPackage.SESSION_BEAN__PERSISTENCE_UNIT_REFS:
				return persistenceUnitRefs != null && !persistenceUnitRefs.isEmpty();
			case EjbPackage.SESSION_BEAN__POST_CONSTRUCTS:
				return postConstructs != null && !postConstructs.isEmpty();
			case EjbPackage.SESSION_BEAN__PRE_DESTROYS:
				return preDestroys != null && !preDestroys.isEmpty();
			case EjbPackage.SESSION_BEAN__POST_ACTIVATES:
				return postActivates != null && !postActivates.isEmpty();
			case EjbPackage.SESSION_BEAN__PRE_PASSIVATES:
				return prePassivates != null && !prePassivates.isEmpty();
			case EjbPackage.SESSION_BEAN__SECURITY_ROLE_REFS:
				return securityRoleRefs != null && !securityRoleRefs.isEmpty();
			case EjbPackage.SESSION_BEAN__SECURITY_IDENTITIES:
				return securityIdentities != null;
			case EjbPackage.SESSION_BEAN__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (ejbName: "); //$NON-NLS-1$
		result.append(ejbName);
		result.append(", mappedName: "); //$NON-NLS-1$
		result.append(mappedName);
		result.append(", home: "); //$NON-NLS-1$
		result.append(home);
		result.append(", remote: "); //$NON-NLS-1$
		result.append(remote);
		result.append(", localHome: "); //$NON-NLS-1$
		result.append(localHome);
		result.append(", local: "); //$NON-NLS-1$
		result.append(local);
		result.append(", businessLocals: "); //$NON-NLS-1$
		result.append(businessLocals);
		result.append(", businessRemotes: "); //$NON-NLS-1$
		result.append(businessRemotes);
		result.append(", serviceEndpoint: "); //$NON-NLS-1$
		result.append(serviceEndpoint);
		result.append(", ejbClass: "); //$NON-NLS-1$
		result.append(ejbClass);
		result.append(", sessionType: "); //$NON-NLS-1$
		if (sessionTypeESet) result.append(sessionType); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", transactionType: "); //$NON-NLS-1$
		if (transactionTypeESet) result.append(transactionType); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //SessionBeanImpl