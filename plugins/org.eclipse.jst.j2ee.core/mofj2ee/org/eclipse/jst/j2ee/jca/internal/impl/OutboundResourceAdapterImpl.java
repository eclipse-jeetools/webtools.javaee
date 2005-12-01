/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.jca.internal.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.jca.AuthenticationMechanism;
import org.eclipse.jst.j2ee.jca.ConnectionDefinition;
import org.eclipse.jst.j2ee.jca.JcaPackage;
import org.eclipse.jst.j2ee.jca.OutboundResourceAdapter;
import org.eclipse.jst.j2ee.jca.TransactionSupportKind;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Outbound Resource Adapter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.jca.internal.impl.OutboundResourceAdapterImpl#isReauthenticationSupport <em>Reauthentication Support</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.jca.internal.impl.OutboundResourceAdapterImpl#getTransactionSupport <em>Transaction Support</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.jca.internal.impl.OutboundResourceAdapterImpl#getConnectionDefinitions <em>Connection Definitions</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.jca.internal.impl.OutboundResourceAdapterImpl#getAuthenticationMechanisms <em>Authentication Mechanisms</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OutboundResourceAdapterImpl extends EObjectImpl implements OutboundResourceAdapter {
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
	protected boolean reauthenticationSupportESet = false;

	/**
	 * The default value of the '{@link #getTransactionSupport() <em>Transaction Support</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransactionSupport()
	 * @generated
	 * @ordered
	 */
	protected static final TransactionSupportKind TRANSACTION_SUPPORT_EDEFAULT = TransactionSupportKind.NO_TRANSACTION_LITERAL;

	/**
	 * The cached value of the '{@link #getTransactionSupport() <em>Transaction Support</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransactionSupport()
	 * @generated
	 * @ordered
	 */
	protected TransactionSupportKind transactionSupport = TRANSACTION_SUPPORT_EDEFAULT;

	/**
	 * This is true if the Transaction Support attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean transactionSupportESet = false;

	/**
	 * The cached value of the '{@link #getConnectionDefinitions() <em>Connection Definitions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectionDefinitions()
	 * @generated
	 * @ordered
	 */
	protected EList connectionDefinitions = null;

	/**
	 * The cached value of the '{@link #getAuthenticationMechanisms() <em>Authentication Mechanisms</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthenticationMechanisms()
	 * @generated
	 * @ordered
	 */
	protected EList authenticationMechanisms = null;

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
	protected EClass eStaticClass() {
		return JcaPackage.eINSTANCE.getOutboundResourceAdapter();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isReauthenticationSupport() {
		return reauthenticationSupport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
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
	public boolean isSetReauthenticationSupport() {
		return reauthenticationSupportESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransactionSupportKind getTransactionSupport() {
		return transactionSupport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransactionSupport(TransactionSupportKind newTransactionSupport) {
		TransactionSupportKind oldTransactionSupport = transactionSupport;
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
	public void unsetTransactionSupport() {
		TransactionSupportKind oldTransactionSupport = transactionSupport;
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
	public boolean isSetTransactionSupport() {
		return transactionSupportESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getConnectionDefinitions() {
		if (connectionDefinitions == null) {
			connectionDefinitions = new EObjectContainmentEList(ConnectionDefinition.class, this, JcaPackage.OUTBOUND_RESOURCE_ADAPTER__CONNECTION_DEFINITIONS);
		}
		return connectionDefinitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getAuthenticationMechanisms() {
		if (authenticationMechanisms == null) {
			authenticationMechanisms = new EObjectContainmentEList(AuthenticationMechanism.class, this, JcaPackage.OUTBOUND_RESOURCE_ADAPTER__AUTHENTICATION_MECHANISMS);
		}
		return authenticationMechanisms;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__CONNECTION_DEFINITIONS:
					return ((InternalEList)getConnectionDefinitions()).basicRemove(otherEnd, msgs);
				case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__AUTHENTICATION_MECHANISMS:
					return ((InternalEList)getAuthenticationMechanisms()).basicRemove(otherEnd, msgs);
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
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__REAUTHENTICATION_SUPPORT:
				return isReauthenticationSupport() ? Boolean.TRUE : Boolean.FALSE;
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__TRANSACTION_SUPPORT:
				return getTransactionSupport();
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__CONNECTION_DEFINITIONS:
				return getConnectionDefinitions();
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__AUTHENTICATION_MECHANISMS:
				return getAuthenticationMechanisms();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__REAUTHENTICATION_SUPPORT:
				setReauthenticationSupport(((Boolean)newValue).booleanValue());
				return;
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__TRANSACTION_SUPPORT:
				setTransactionSupport((TransactionSupportKind)newValue);
				return;
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__CONNECTION_DEFINITIONS:
				getConnectionDefinitions().clear();
				getConnectionDefinitions().addAll((Collection)newValue);
				return;
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__AUTHENTICATION_MECHANISMS:
				getAuthenticationMechanisms().clear();
				getAuthenticationMechanisms().addAll((Collection)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__REAUTHENTICATION_SUPPORT:
				unsetReauthenticationSupport();
				return;
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__TRANSACTION_SUPPORT:
				unsetTransactionSupport();
				return;
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__CONNECTION_DEFINITIONS:
				getConnectionDefinitions().clear();
				return;
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__AUTHENTICATION_MECHANISMS:
				getAuthenticationMechanisms().clear();
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__REAUTHENTICATION_SUPPORT:
				return isSetReauthenticationSupport();
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__TRANSACTION_SUPPORT:
				return isSetTransactionSupport();
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__CONNECTION_DEFINITIONS:
				return connectionDefinitions != null && !connectionDefinitions.isEmpty();
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER__AUTHENTICATION_MECHANISMS:
				return authenticationMechanisms != null && !authenticationMechanisms.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (reauthenticationSupport: ");
		if (reauthenticationSupportESet) result.append(reauthenticationSupport); else result.append("<unset>");
		result.append(", transactionSupport: ");
		if (transactionSupportESet) result.append(transactionSupport); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //OutboundResourceAdapterImpl
