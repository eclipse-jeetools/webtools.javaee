/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.Session;
import org.eclipse.jst.j2ee.ejb.SessionType;
import org.eclipse.jst.j2ee.ejb.TransactionType;

/**
 * The session element declares an session bean. The declaration consists of: an optional description; optional display name; optional small icon file name; optional large icon file name; a name assigned
 * to the enterprise bean in the deployment description; the names of the session bean's home and remote interfaces, if any; the names of the session bean's local home and local interfaces, if any; the session bean's implementation class; the session bean's state management type; the session bean's transaction management type; an optional declaration of the bean's environment entries; an optional declaration of the bean's EJB references; an optional declaration of the bean's local EJB references; an optional declaration of the security role references; an optional declaration of the security identity to be used for the execution of the bean's methods; an optional declaration of the bean's resource manager connection factory references; and an optional declaration of the bean's resource environment references. The elements that are optional are "optional" in the sense that they are omitted when if lists represented by them are empty.
 */
public class SessionImpl extends EnterpriseBeanImpl implements Session {

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
	@Override
	protected EClass eStaticClass() {
		return EjbPackage.Literals.SESSION;
	}

	@Override
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
	@Override
	public JavaClass getServiceEndpoint() {
		if (serviceEndpoint != null && serviceEndpoint.eIsProxy()) {
			InternalEObject oldServiceEndpoint = (InternalEObject)serviceEndpoint;
			serviceEndpoint = (JavaClass)eResolveProxy(oldServiceEndpoint);
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
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EjbPackage.SESSION__TRANSACTION_TYPE:
				return getTransactionType();
			case EjbPackage.SESSION__SESSION_TYPE:
				return getSessionType();
			case EjbPackage.SESSION__SERVICE_ENDPOINT:
				if (resolve) return getServiceEndpoint();
				return basicGetServiceEndpoint();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
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
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
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
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case EjbPackage.SESSION__TRANSACTION_TYPE:
				return isSetTransactionType();
			case EjbPackage.SESSION__SESSION_TYPE:
				return isSetSessionType();
			case EjbPackage.SESSION__SERVICE_ENDPOINT:
				return serviceEndpoint != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (transactionType: "); //$NON-NLS-1$
		if (transactionTypeESet) result.append(transactionType); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", sessionType: "); //$NON-NLS-1$
		if (sessionTypeESet) result.append(sessionType); else result.append("<unset>"); //$NON-NLS-1$
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





