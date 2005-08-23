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
package org.eclipse.jst.j2ee.ejb.internal.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.common.SecurityIdentity;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Session;
import org.eclipse.jst.j2ee.ejb.SessionType;
import org.eclipse.jst.j2ee.ejb.TransactionType;

/**
 * The session element declares an session bean. The declaration consists of: an optional description; optional display name; optional small icon file name; optional large icon file name; a name assigned
 * to the enterprise bean in the deployment description; the names of the session bean's home and remote interfaces, if any; the names of the session bean's local home and local interfaces, if any; the session bean's implementation class; the session bean's state management type; the session bean's transaction management type; an optional declaration of the bean's environment entries; an optional declaration of the bean's EJB references; an optional declaration of the bean's local EJB references; an optional declaration of the security role references; an optional declaration of the security identity to be used for the execution of the bean's methods; an optional declaration of the bean's resource manager connection factory references; and an optional declaration of the bean's resource environment references. The elements that are optional are "optional" in the sense that they are omitted when if lists represented by them are empty.
 */
public class SessionImpl extends EnterpriseBeanImpl implements Session, EnterpriseBean {

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
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
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
	 * The default value of the '{@link #getSessionType() <em>Session Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSessionType()
	 * @generated
	 * @ordered
	 */
	protected static final SessionType SESSION_TYPE_EDEFAULT = SessionType.STATEFUL_LITERAL;

	/**
	 * @generated This field/method will be replaced during code generation.
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
	 * The cached value of the '{@link #getServiceEndpoint() <em>Service Endpoint</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceEndpoint()
	 * @generated
	 * @ordered
	 */
	protected JavaClass serviceEndpoint = null;

	public SessionImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return EjbPackage.eINSTANCE.getSession();
	}

	public boolean isSession() {
		return true;
	}
	/**
	 * @generated This field/method will be replaced during code generation 
	 * The transaction-type element specifies an enterprise bean's transaction management type.
	 * 

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
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION__TRANSACTION_TYPE, oldTransactionType, transactionType, !oldTransactionTypeESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, EjbPackage.SESSION__TRANSACTION_TYPE, oldTransactionType, TRANSACTION_TYPE_EDEFAULT, oldTransactionTypeESet));
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
	 * @generated This field/method will be replaced during code generation 
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
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION__SESSION_TYPE, oldSessionType, sessionType, !oldSessionTypeESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, EjbPackage.SESSION__SESSION_TYPE, oldSessionType, SESSION_TYPE_EDEFAULT, oldSessionTypeESet));
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
	public JavaClass getServiceEndpoint() {
		if (serviceEndpoint != null && serviceEndpoint.eIsProxy()) {
			JavaClass oldServiceEndpoint = serviceEndpoint;
			serviceEndpoint = (JavaClass)eResolveProxy((InternalEObject)serviceEndpoint);
			if (serviceEndpoint != oldServiceEndpoint) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EjbPackage.SESSION__SERVICE_ENDPOINT, oldServiceEndpoint, serviceEndpoint));
			}
		}
		return serviceEndpoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass basicGetServiceEndpoint() {
		return serviceEndpoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServiceEndpoint(JavaClass newServiceEndpoint) {
		JavaClass oldServiceEndpoint = serviceEndpoint;
		serviceEndpoint = newServiceEndpoint;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.SESSION__SERVICE_ENDPOINT, oldServiceEndpoint, serviceEndpoint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case EjbPackage.SESSION__EJB_JAR:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, EjbPackage.SESSION__EJB_JAR, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case EjbPackage.SESSION__ICONS:
					return ((InternalEList)getIcons()).basicRemove(otherEnd, msgs);
				case EjbPackage.SESSION__DISPLAY_NAMES:
					return ((InternalEList)getDisplayNames()).basicRemove(otherEnd, msgs);
				case EjbPackage.SESSION__DESCRIPTIONS:
					return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
				case EjbPackage.SESSION__ENVIRONMENT_PROPERTIES:
					return ((InternalEList)getEnvironmentProperties()).basicRemove(otherEnd, msgs);
				case EjbPackage.SESSION__RESOURCE_REFS:
					return ((InternalEList)getResourceRefs()).basicRemove(otherEnd, msgs);
				case EjbPackage.SESSION__EJB_REFS:
					return ((InternalEList)getEjbRefs()).basicRemove(otherEnd, msgs);
				case EjbPackage.SESSION__RESOURCE_ENV_REFS:
					return ((InternalEList)getResourceEnvRefs()).basicRemove(otherEnd, msgs);
				case EjbPackage.SESSION__EJB_LOCAL_REFS:
					return ((InternalEList)getEjbLocalRefs()).basicRemove(otherEnd, msgs);
				case EjbPackage.SESSION__MESSAGE_DESTINATION_REFS:
					return ((InternalEList)getMessageDestinationRefs()).basicRemove(otherEnd, msgs);
				case EjbPackage.SESSION__SERVICE_REFS:
					return ((InternalEList)getServiceRefs()).basicRemove(otherEnd, msgs);
				case EjbPackage.SESSION__SECURITY_ROLE_REFS:
					return ((InternalEList)getSecurityRoleRefs()).basicRemove(otherEnd, msgs);
				case EjbPackage.SESSION__EJB_JAR:
					return eBasicSetContainer(null, EjbPackage.SESSION__EJB_JAR, msgs);
				case EjbPackage.SESSION__SECURITY_IDENTITY:
					return basicSetSecurityIdentity(null, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
		if (eContainerFeatureID >= 0) {
			switch (eContainerFeatureID) {
				case EjbPackage.SESSION__EJB_JAR:
					return eContainer.eInverseRemove(this, EjbPackage.EJB_JAR__ENTERPRISE_BEANS, EJBJar.class, msgs);
				default:
					return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.SESSION__ICONS:
				return getIcons();
			case EjbPackage.SESSION__DISPLAY_NAMES:
				return getDisplayNames();
			case EjbPackage.SESSION__DESCRIPTIONS:
				return getDescriptions();
			case EjbPackage.SESSION__SMALL_ICON:
				return getSmallIcon();
			case EjbPackage.SESSION__LARGE_ICON:
				return getLargeIcon();
			case EjbPackage.SESSION__DESCRIPTION:
				return getDescription();
			case EjbPackage.SESSION__DISPLAY_NAME:
				return getDisplayName();
			case EjbPackage.SESSION__ENVIRONMENT_PROPERTIES:
				return getEnvironmentProperties();
			case EjbPackage.SESSION__RESOURCE_REFS:
				return getResourceRefs();
			case EjbPackage.SESSION__EJB_REFS:
				return getEjbRefs();
			case EjbPackage.SESSION__RESOURCE_ENV_REFS:
				return getResourceEnvRefs();
			case EjbPackage.SESSION__EJB_LOCAL_REFS:
				return getEjbLocalRefs();
			case EjbPackage.SESSION__MESSAGE_DESTINATION_REFS:
				return getMessageDestinationRefs();
			case EjbPackage.SESSION__SERVICE_REFS:
				return getServiceRefs();
			case EjbPackage.SESSION__NAME:
				return getName();
			case EjbPackage.SESSION__SECURITY_ROLE_REFS:
				return getSecurityRoleRefs();
			case EjbPackage.SESSION__EJB_CLASS:
				if (resolve) return getEjbClass();
				return basicGetEjbClass();
			case EjbPackage.SESSION__HOME_INTERFACE:
				if (resolve) return getHomeInterface();
				return basicGetHomeInterface();
			case EjbPackage.SESSION__REMOTE_INTERFACE:
				if (resolve) return getRemoteInterface();
				return basicGetRemoteInterface();
			case EjbPackage.SESSION__EJB_JAR:
				return getEjbJar();
			case EjbPackage.SESSION__SECURITY_IDENTITY:
				return getSecurityIdentity();
			case EjbPackage.SESSION__LOCAL_HOME_INTERFACE:
				if (resolve) return getLocalHomeInterface();
				return basicGetLocalHomeInterface();
			case EjbPackage.SESSION__LOCAL_INTERFACE:
				if (resolve) return getLocalInterface();
				return basicGetLocalInterface();
			case EjbPackage.SESSION__TRANSACTION_TYPE:
				return getTransactionType();
			case EjbPackage.SESSION__SESSION_TYPE:
				return getSessionType();
			case EjbPackage.SESSION__SERVICE_ENDPOINT:
				if (resolve) return getServiceEndpoint();
				return basicGetServiceEndpoint();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.SESSION__ICONS:
				return icons != null && !icons.isEmpty();
			case EjbPackage.SESSION__DISPLAY_NAMES:
				return displayNames != null && !displayNames.isEmpty();
			case EjbPackage.SESSION__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case EjbPackage.SESSION__SMALL_ICON:
				return SMALL_ICON_EDEFAULT == null ? smallIcon != null : !SMALL_ICON_EDEFAULT.equals(smallIcon);
			case EjbPackage.SESSION__LARGE_ICON:
				return LARGE_ICON_EDEFAULT == null ? largeIcon != null : !LARGE_ICON_EDEFAULT.equals(largeIcon);
			case EjbPackage.SESSION__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case EjbPackage.SESSION__DISPLAY_NAME:
				return DISPLAY_NAME_EDEFAULT == null ? displayName != null : !DISPLAY_NAME_EDEFAULT.equals(displayName);
			case EjbPackage.SESSION__ENVIRONMENT_PROPERTIES:
				return environmentProperties != null && !environmentProperties.isEmpty();
			case EjbPackage.SESSION__RESOURCE_REFS:
				return resourceRefs != null && !resourceRefs.isEmpty();
			case EjbPackage.SESSION__EJB_REFS:
				return ejbRefs != null && !ejbRefs.isEmpty();
			case EjbPackage.SESSION__RESOURCE_ENV_REFS:
				return resourceEnvRefs != null && !resourceEnvRefs.isEmpty();
			case EjbPackage.SESSION__EJB_LOCAL_REFS:
				return ejbLocalRefs != null && !ejbLocalRefs.isEmpty();
			case EjbPackage.SESSION__MESSAGE_DESTINATION_REFS:
				return messageDestinationRefs != null && !messageDestinationRefs.isEmpty();
			case EjbPackage.SESSION__SERVICE_REFS:
				return serviceRefs != null && !serviceRefs.isEmpty();
			case EjbPackage.SESSION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case EjbPackage.SESSION__SECURITY_ROLE_REFS:
				return securityRoleRefs != null && !securityRoleRefs.isEmpty();
			case EjbPackage.SESSION__EJB_CLASS:
				return ejbClass != null;
			case EjbPackage.SESSION__HOME_INTERFACE:
				return homeInterface != null;
			case EjbPackage.SESSION__REMOTE_INTERFACE:
				return remoteInterface != null;
			case EjbPackage.SESSION__EJB_JAR:
				return getEjbJar() != null;
			case EjbPackage.SESSION__SECURITY_IDENTITY:
				return securityIdentity != null;
			case EjbPackage.SESSION__LOCAL_HOME_INTERFACE:
				return localHomeInterface != null;
			case EjbPackage.SESSION__LOCAL_INTERFACE:
				return localInterface != null;
			case EjbPackage.SESSION__TRANSACTION_TYPE:
				return isSetTransactionType();
			case EjbPackage.SESSION__SESSION_TYPE:
				return isSetSessionType();
			case EjbPackage.SESSION__SERVICE_ENDPOINT:
				return serviceEndpoint != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.SESSION__ICONS:
				getIcons().clear();
				getIcons().addAll((Collection)newValue);
				return;
			case EjbPackage.SESSION__DISPLAY_NAMES:
				getDisplayNames().clear();
				getDisplayNames().addAll((Collection)newValue);
				return;
			case EjbPackage.SESSION__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
				return;
			case EjbPackage.SESSION__SMALL_ICON:
				setSmallIcon((String)newValue);
				return;
			case EjbPackage.SESSION__LARGE_ICON:
				setLargeIcon((String)newValue);
				return;
			case EjbPackage.SESSION__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case EjbPackage.SESSION__DISPLAY_NAME:
				setDisplayName((String)newValue);
				return;
			case EjbPackage.SESSION__ENVIRONMENT_PROPERTIES:
				getEnvironmentProperties().clear();
				getEnvironmentProperties().addAll((Collection)newValue);
				return;
			case EjbPackage.SESSION__RESOURCE_REFS:
				getResourceRefs().clear();
				getResourceRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.SESSION__EJB_REFS:
				getEjbRefs().clear();
				getEjbRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.SESSION__RESOURCE_ENV_REFS:
				getResourceEnvRefs().clear();
				getResourceEnvRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.SESSION__EJB_LOCAL_REFS:
				getEjbLocalRefs().clear();
				getEjbLocalRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.SESSION__MESSAGE_DESTINATION_REFS:
				getMessageDestinationRefs().clear();
				getMessageDestinationRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.SESSION__SERVICE_REFS:
				getServiceRefs().clear();
				getServiceRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.SESSION__NAME:
				setName((String)newValue);
				return;
			case EjbPackage.SESSION__SECURITY_ROLE_REFS:
				getSecurityRoleRefs().clear();
				getSecurityRoleRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.SESSION__EJB_CLASS:
				setEjbClass((JavaClass)newValue);
				return;
			case EjbPackage.SESSION__HOME_INTERFACE:
				setHomeInterface((JavaClass)newValue);
				return;
			case EjbPackage.SESSION__REMOTE_INTERFACE:
				setRemoteInterface((JavaClass)newValue);
				return;
			case EjbPackage.SESSION__EJB_JAR:
				setEjbJar((EJBJar)newValue);
				return;
			case EjbPackage.SESSION__SECURITY_IDENTITY:
				setSecurityIdentity((SecurityIdentity)newValue);
				return;
			case EjbPackage.SESSION__LOCAL_HOME_INTERFACE:
				setLocalHomeInterface((JavaClass)newValue);
				return;
			case EjbPackage.SESSION__LOCAL_INTERFACE:
				setLocalInterface((JavaClass)newValue);
				return;
			case EjbPackage.SESSION__TRANSACTION_TYPE:
				setTransactionType((TransactionType)newValue);
				return;
			case EjbPackage.SESSION__SESSION_TYPE:
				setSessionType((SessionType)newValue);
				return;
			case EjbPackage.SESSION__SERVICE_ENDPOINT:
				setServiceEndpoint((JavaClass)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.SESSION__ICONS:
				getIcons().clear();
				return;
			case EjbPackage.SESSION__DISPLAY_NAMES:
				getDisplayNames().clear();
				return;
			case EjbPackage.SESSION__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case EjbPackage.SESSION__SMALL_ICON:
				setSmallIcon(SMALL_ICON_EDEFAULT);
				return;
			case EjbPackage.SESSION__LARGE_ICON:
				setLargeIcon(LARGE_ICON_EDEFAULT);
				return;
			case EjbPackage.SESSION__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case EjbPackage.SESSION__DISPLAY_NAME:
				setDisplayName(DISPLAY_NAME_EDEFAULT);
				return;
			case EjbPackage.SESSION__ENVIRONMENT_PROPERTIES:
				getEnvironmentProperties().clear();
				return;
			case EjbPackage.SESSION__RESOURCE_REFS:
				getResourceRefs().clear();
				return;
			case EjbPackage.SESSION__EJB_REFS:
				getEjbRefs().clear();
				return;
			case EjbPackage.SESSION__RESOURCE_ENV_REFS:
				getResourceEnvRefs().clear();
				return;
			case EjbPackage.SESSION__EJB_LOCAL_REFS:
				getEjbLocalRefs().clear();
				return;
			case EjbPackage.SESSION__MESSAGE_DESTINATION_REFS:
				getMessageDestinationRefs().clear();
				return;
			case EjbPackage.SESSION__SERVICE_REFS:
				getServiceRefs().clear();
				return;
			case EjbPackage.SESSION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case EjbPackage.SESSION__SECURITY_ROLE_REFS:
				getSecurityRoleRefs().clear();
				return;
			case EjbPackage.SESSION__EJB_CLASS:
				setEjbClass((JavaClass)null);
				return;
			case EjbPackage.SESSION__HOME_INTERFACE:
				setHomeInterface((JavaClass)null);
				return;
			case EjbPackage.SESSION__REMOTE_INTERFACE:
				setRemoteInterface((JavaClass)null);
				return;
			case EjbPackage.SESSION__EJB_JAR:
				setEjbJar((EJBJar)null);
				return;
			case EjbPackage.SESSION__SECURITY_IDENTITY:
				setSecurityIdentity((SecurityIdentity)null);
				return;
			case EjbPackage.SESSION__LOCAL_HOME_INTERFACE:
				setLocalHomeInterface((JavaClass)null);
				return;
			case EjbPackage.SESSION__LOCAL_INTERFACE:
				setLocalInterface((JavaClass)null);
				return;
			case EjbPackage.SESSION__TRANSACTION_TYPE:
				unsetTransactionType();
				return;
			case EjbPackage.SESSION__SESSION_TYPE:
				unsetSessionType();
				return;
			case EjbPackage.SESSION__SERVICE_ENDPOINT:
				setServiceEndpoint((JavaClass)null);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (transactionType: ");
		if (transactionTypeESet) result.append(transactionType); else result.append("<unset>");
		result.append(", sessionType: ");
		if (sessionTypeESet) result.append(sessionType); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

    /* (non-Javadoc)
     * @see org.eclipse.jst.j2ee.internal.ejb.Session#setServiceEndpointName(java.lang.String)
     */
    public void setServiceEndpointName(String serviceEndpointName) {
        eSet(EjbPackage.eINSTANCE.getSession_ServiceEndpoint(), createClassRef(serviceEndpointName));
    }
	/*
	 * @see EnterpriseBean#hasLocalClient()
	 */
	public boolean hasServiceEndpoint() {
		return getServiceEndpoint() != null;
	}
	public String getServiceEndpointName() {
		getServiceEndpoint();
		return serviceEndpoint == null ? null : serviceEndpoint.getQualifiedName();
	}
}





