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
package org.eclipse.jst.j2ee.webapplication.internal.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.internal.impl.J2EEEObjectImpl;
import org.eclipse.jst.j2ee.webapplication.SecurityConstraint;
import org.eclipse.jst.j2ee.webapplication.TransportGuaranteeType;
import org.eclipse.jst.j2ee.webapplication.UserDataConstraint;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;


/**
 * The user-data-constraint element is used to indicate how data communicated between the client and container should be protected
 * @generated
 */
public class UserDataConstraintImpl extends J2EEEObjectImpl implements UserDataConstraint {

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String description = DESCRIPTION_EDEFAULT;
	/**
	 * The default value of the '{@link #getTransportGuarantee() <em>Transport Guarantee</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransportGuarantee()
	 * @generated
	 * @ordered
	 */
	protected static final TransportGuaranteeType TRANSPORT_GUARANTEE_EDEFAULT = TransportGuaranteeType.NONE_LITERAL;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected TransportGuaranteeType transportGuarantee = TRANSPORT_GUARANTEE_EDEFAULT;
	/**
	 * This is true if the Transport Guarantee attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean transportGuaranteeESet = false;

	/**
	 * The cached value of the '{@link #getDescriptions() <em>Descriptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptions()
	 * @generated
	 * @ordered
	 */
	protected EList descriptions = null;

	public UserDataConstraintImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WebapplicationPackage.Literals.USER_DATA_CONSTRAINT;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * A description for the user data constraint.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.USER_DATA_CONSTRAINT__DESCRIPTION, oldDescription, description));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The transport-guarantee element specifies that the communication between client 
	 * and server should be NONE, INTEGRAL, or CONFIDENTIAL. NONE means that the
	 * application does not require any
	 * transport guarantees. A value of INTEGRAL means that the application requires
	 * that the data sent between the client and server be sent in such a way that it
	 * can't be changed in transit. CONFIDENTIAL means that the application requires
	 * that the data be transmitted in a fashion that prevents other entities from
	 * observing the contents of the transmission. In most cases, the presence of the
	 * INTEGRAL or CONFIDENTIAL flag will indicate that the use of SSL is required.
	 */
	public TransportGuaranteeType getTransportGuarantee() {
		return transportGuarantee;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransportGuarantee(TransportGuaranteeType newTransportGuarantee) {
		TransportGuaranteeType oldTransportGuarantee = transportGuarantee;
		transportGuarantee = newTransportGuarantee == null ? TRANSPORT_GUARANTEE_EDEFAULT : newTransportGuarantee;
		boolean oldTransportGuaranteeESet = transportGuaranteeESet;
		transportGuaranteeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.USER_DATA_CONSTRAINT__TRANSPORT_GUARANTEE, oldTransportGuarantee, transportGuarantee, !oldTransportGuaranteeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetTransportGuarantee() {
		TransportGuaranteeType oldTransportGuarantee = transportGuarantee;
		boolean oldTransportGuaranteeESet = transportGuaranteeESet;
		transportGuarantee = TRANSPORT_GUARANTEE_EDEFAULT;
		transportGuaranteeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, WebapplicationPackage.USER_DATA_CONSTRAINT__TRANSPORT_GUARANTEE, oldTransportGuarantee, TRANSPORT_GUARANTEE_EDEFAULT, oldTransportGuaranteeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetTransportGuarantee() {
		return transportGuaranteeESet;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public SecurityConstraint getSecConstraint() {
		if (eContainerFeatureID != WebapplicationPackage.USER_DATA_CONSTRAINT__SEC_CONSTRAINT) return null;
		return (SecurityConstraint)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSecConstraint(SecurityConstraint newSecConstraint, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newSecConstraint, WebapplicationPackage.USER_DATA_CONSTRAINT__SEC_CONSTRAINT, msgs);
		return msgs;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setSecConstraint(SecurityConstraint newSecConstraint) {
		if (newSecConstraint != eInternalContainer() || (eContainerFeatureID != WebapplicationPackage.USER_DATA_CONSTRAINT__SEC_CONSTRAINT && newSecConstraint != null)) {
			if (EcoreUtil.isAncestor(this, newSecConstraint))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newSecConstraint != null)
				msgs = ((InternalEObject)newSecConstraint).eInverseAdd(this, WebapplicationPackage.SECURITY_CONSTRAINT__USER_DATA_CONSTRAINT, SecurityConstraint.class, msgs);
			msgs = basicSetSecConstraint(newSecConstraint, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.USER_DATA_CONSTRAINT__SEC_CONSTRAINT, newSecConstraint, newSecConstraint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList(Description.class, this, WebapplicationPackage.USER_DATA_CONSTRAINT__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WebapplicationPackage.USER_DATA_CONSTRAINT__SEC_CONSTRAINT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetSecConstraint((SecurityConstraint)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WebapplicationPackage.USER_DATA_CONSTRAINT__SEC_CONSTRAINT:
				return basicSetSecConstraint(null, msgs);
			case WebapplicationPackage.USER_DATA_CONSTRAINT__DESCRIPTIONS:
				return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID) {
			case WebapplicationPackage.USER_DATA_CONSTRAINT__SEC_CONSTRAINT:
				return eInternalContainer().eInverseRemove(this, WebapplicationPackage.SECURITY_CONSTRAINT__USER_DATA_CONSTRAINT, SecurityConstraint.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WebapplicationPackage.USER_DATA_CONSTRAINT__DESCRIPTION:
				return getDescription();
			case WebapplicationPackage.USER_DATA_CONSTRAINT__TRANSPORT_GUARANTEE:
				return getTransportGuarantee();
			case WebapplicationPackage.USER_DATA_CONSTRAINT__SEC_CONSTRAINT:
				return getSecConstraint();
			case WebapplicationPackage.USER_DATA_CONSTRAINT__DESCRIPTIONS:
				return getDescriptions();
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
			case WebapplicationPackage.USER_DATA_CONSTRAINT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case WebapplicationPackage.USER_DATA_CONSTRAINT__TRANSPORT_GUARANTEE:
				setTransportGuarantee((TransportGuaranteeType)newValue);
				return;
			case WebapplicationPackage.USER_DATA_CONSTRAINT__SEC_CONSTRAINT:
				setSecConstraint((SecurityConstraint)newValue);
				return;
			case WebapplicationPackage.USER_DATA_CONSTRAINT__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
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
			case WebapplicationPackage.USER_DATA_CONSTRAINT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case WebapplicationPackage.USER_DATA_CONSTRAINT__TRANSPORT_GUARANTEE:
				unsetTransportGuarantee();
				return;
			case WebapplicationPackage.USER_DATA_CONSTRAINT__SEC_CONSTRAINT:
				setSecConstraint((SecurityConstraint)null);
				return;
			case WebapplicationPackage.USER_DATA_CONSTRAINT__DESCRIPTIONS:
				getDescriptions().clear();
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
			case WebapplicationPackage.USER_DATA_CONSTRAINT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case WebapplicationPackage.USER_DATA_CONSTRAINT__TRANSPORT_GUARANTEE:
				return isSetTransportGuarantee();
			case WebapplicationPackage.USER_DATA_CONSTRAINT__SEC_CONSTRAINT:
				return getSecConstraint() != null;
			case WebapplicationPackage.USER_DATA_CONSTRAINT__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
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
		result.append(" (description: "); //$NON-NLS-1$
		result.append(description);
		result.append(", transportGuarantee: "); //$NON-NLS-1$
		if (transportGuaranteeESet) result.append(transportGuarantee); else result.append("<unset>"); //$NON-NLS-1$
		result.append(')');
		return result.toString();
	}

}














