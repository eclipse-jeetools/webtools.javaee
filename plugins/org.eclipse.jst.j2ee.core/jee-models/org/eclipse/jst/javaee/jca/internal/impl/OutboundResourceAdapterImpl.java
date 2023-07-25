/*******************************************************************************
 * Copyright (c) 2009, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.jca.internal.impl;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jst.javaee.core.TransactionSupport;
import org.eclipse.jst.javaee.jca.AuthenticationMechanism;
import org.eclipse.jst.javaee.jca.ConnectionDefinition;
import org.eclipse.jst.javaee.jca.OutboundResourceAdapter;

import org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Outbound Resource Adapter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.OutboundResourceAdapterImpl#getConnectionDefinition <em>Connection Definition</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.OutboundResourceAdapterImpl#getTransactionSupport <em>Transaction Support</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.OutboundResourceAdapterImpl#getAuthenticationMechanism <em>Authentication Mechanism</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.OutboundResourceAdapterImpl#isReauthenticationSupport <em>Reauthentication Support</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.OutboundResourceAdapterImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OutboundResourceAdapterImpl extends EObjectImpl implements OutboundResourceAdapter {
	/**
	 * The cached value of the '{@link #getConnectionDefinition() <em>Connection Definition</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectionDefinition()
	 * @generated
	 * @ordered
	 */
	protected EList<ConnectionDefinition> connectionDefinition;

	/**
	 * The default value of the '{@link #getTransactionSupport() <em>Transaction Support</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransactionSupport()
	 * @generated
	 * @ordered
	 */
	protected static final TransactionSupport TRANSACTION_SUPPORT_EDEFAULT = TransactionSupport.NO_TRANSACTION;

	/**
	 * The cached value of the '{@link #getTransactionSupport() <em>Transaction Support</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransactionSupport()
	 * @generated
	 * @ordered
	 */
	protected TransactionSupport transactionSupport = TRANSACTION_SUPPORT_EDEFAULT;

	/**
	 * This is true if the Transaction Support attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean transactionSupportESet;

	/**
	 * The cached value of the '{@link #getAuthenticationMechanism() <em>Authentication Mechanism</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthenticationMechanism()
	 * @generated
	 * @ordered
	 */
	protected EList<AuthenticationMechanism> authenticationMechanism;

	/**
	 * The default value of the '{@link #isReauthenticationSupport() <em>Reauthentication Support</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReauthenticationSupport()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REAUTHENTICATION_SUPPORT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isReauthenticationSupport() <em>Reauthentication Support</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReauthenticationSupport()
	 * @generated
	 * @ordered
	 */
	protected boolean reauthenticationSupport = REAUTHENTICATION_SUPPORT_EDEFAULT;

	/**
	 * This is true if the Reauthentication Support attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean reauthenticationSupportESet;

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
	protected OutboundResourceAdapterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JcaPackage.Literals.OUTBOUND_RESOURCE_ADAPTER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<ConnectionDefinition> getConnectionDefinition() {
		if (connectionDefinition == null) {
			connectionDefinition = new EObjectContainmentEList<ConnectionDefinition>(ConnectionDefinition.class, this, JcaPackage.OUTBOUND_RESOURCE_ADAPTER__CONNECTION_DEFINITION);
		}
		return connectionDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TransactionSupport getTransactionSupport() {
		return transactionSupport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTransactionSupport(TransactionSupport newTransactionSupport) {
		TransactionSupport oldTransactionSupport = transactionSupport;
		transactionSupport = newTransactionSupport == null ? TRANSACTION_SUPPORT_EDEFAULT : newTransactionSupport;
		boolean oldTransactionSupportESet = transactionSupportESet;
		transactionSupportESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.OUTBOUND_RESOURCE_ADAPTER__TRANSACTION_SUPPORT, oldTransactionSupport, transactionSupport, !oldTransactionSupportESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetTransactionSupport() {
		TransactionSupport oldTransactionSupport = transactionSupport;
		boolean oldTransactionSupportESet = transactionSupportESet;
		transactionSupport = TRANSACTION_SUPPORT_EDEFAULT;
		transactionSupportESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JcaPackage.OUTBOUND_RESOURCE_ADAPTER__TRANSACTION_SUPPORT, oldTransactionSupport, TRANSACTION_SUPPORT_EDEFAULT, oldTransactionSupportESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetTransactionSupport() {
		return transactionSupportESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<AuthenticationMechanism> getAuthenticationMechanism() {
		if (authenticationMechanism == null) {
			authenticationMechanism = new EObjectContainmentEList<AuthenticationMechanism>(AuthenticationMechanism.class, this, JcaPackage.OUTBOUND_RESOURCE_ADAPTER__AUTHENTICATION_MECHANISM);
		}
		return authenticationMechanism;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isReauthenticationSupport() {
		return reauthenticationSupport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReauthenticationSupport(boolean newReauthenticationSupport) {
		boolean oldReauthenticationSupport = reauthenticationSupport;
		reauthenticationSupport = newReauthenticationSupport;
		boolean oldReauthenticationSupportESet = reauthenticationSupportESet;
		reauthenticationSupportESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.OUTBOUND_RESOURCE_ADAPTER__REAUTHENTICATION_SUPPORT, oldReauthenticationSupport, reauthenticationSupport, !oldReauthenticationSupportESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetReauthenticationSupport() {
		boolean oldReauthenticationSupport = reauthenticationSupport;
		boolean oldReauthenticationSupportESet = reauthenticationSupportESet;
		reauthenticationSupport = REAUTHENTICATION_SUPPORT_EDEFAULT;
		reauthenticationSupportESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JcaPackage.OUTBOUND_RESOURCE_ADAPTER__REAUTHENTICATION_SUPPORT, oldReauthenticationSupport, REAUTHENTICATION_SUPPORT_EDEFAULT, oldReauthenticationSupportESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetReauthenticationSupport() {
		return reauthenticationSupportESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.OUTBOUND_RESOURCE_ADAPTER__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__CONNECTION_DEFINITION:
				return ((InternalEList<?>)getConnectionDefinition()).basicRemove(otherEnd, msgs);
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__AUTHENTICATION_MECHANISM:
				return ((InternalEList<?>)getAuthenticationMechanism()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__CONNECTION_DEFINITION:
				return getConnectionDefinition();
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__TRANSACTION_SUPPORT:
				return getTransactionSupport();
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__AUTHENTICATION_MECHANISM:
				return getAuthenticationMechanism();
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__REAUTHENTICATION_SUPPORT:
				return isReauthenticationSupport();
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__ID:
				return getId();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__CONNECTION_DEFINITION:
				getConnectionDefinition().clear();
				getConnectionDefinition().addAll((Collection<? extends ConnectionDefinition>)newValue);
				return;
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__TRANSACTION_SUPPORT:
				setTransactionSupport((TransactionSupport)newValue);
				return;
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__AUTHENTICATION_MECHANISM:
				getAuthenticationMechanism().clear();
				getAuthenticationMechanism().addAll((Collection<? extends AuthenticationMechanism>)newValue);
				return;
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__REAUTHENTICATION_SUPPORT:
				setReauthenticationSupport((Boolean)newValue);
				return;
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__ID:
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
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__CONNECTION_DEFINITION:
				getConnectionDefinition().clear();
				return;
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__TRANSACTION_SUPPORT:
				unsetTransactionSupport();
				return;
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__AUTHENTICATION_MECHANISM:
				getAuthenticationMechanism().clear();
				return;
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__REAUTHENTICATION_SUPPORT:
				unsetReauthenticationSupport();
				return;
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__ID:
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
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__CONNECTION_DEFINITION:
				return connectionDefinition != null && !connectionDefinition.isEmpty();
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__TRANSACTION_SUPPORT:
				return isSetTransactionSupport();
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__AUTHENTICATION_MECHANISM:
				return authenticationMechanism != null && !authenticationMechanism.isEmpty();
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__REAUTHENTICATION_SUPPORT:
				return isSetReauthenticationSupport();
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (transactionSupport: "); //$NON-NLS-1$
		if (transactionSupportESet) result.append(transactionSupport); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", reauthenticationSupport: "); //$NON-NLS-1$
		if (reauthenticationSupportESet) result.append(reauthenticationSupport); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //OutboundResourceAdapterImpl
