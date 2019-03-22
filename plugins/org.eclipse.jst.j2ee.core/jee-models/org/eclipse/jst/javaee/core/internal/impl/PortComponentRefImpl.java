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
package org.eclipse.jst.javaee.core.internal.impl;

import java.math.BigInteger;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.jst.javaee.core.AddressingType;
import org.eclipse.jst.javaee.core.PortComponentRef;

import org.eclipse.jst.javaee.core.RespectBindingType;
import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Port Component Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.PortComponentRefImpl#getServiceEndpointInterface <em>Service Endpoint Interface</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.PortComponentRefImpl#isEnableMtom <em>Enable Mtom</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.PortComponentRefImpl#getMtomThreshold <em>Mtom Threshold</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.PortComponentRefImpl#getAddressing <em>Addressing</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.PortComponentRefImpl#getRespectBinding <em>Respect Binding</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.PortComponentRefImpl#getPortComponentLink <em>Port Component Link</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.PortComponentRefImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PortComponentRefImpl extends EObjectImpl implements PortComponentRef {
	/**
	 * The default value of the '{@link #getServiceEndpointInterface() <em>Service Endpoint Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceEndpointInterface()
	 * @generated
	 * @ordered
	 */
	protected static final String SERVICE_ENDPOINT_INTERFACE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getServiceEndpointInterface() <em>Service Endpoint Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceEndpointInterface()
	 * @generated
	 * @ordered
	 */
	protected String serviceEndpointInterface = SERVICE_ENDPOINT_INTERFACE_EDEFAULT;

	/**
	 * The default value of the '{@link #isEnableMtom() <em>Enable Mtom</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnableMtom()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENABLE_MTOM_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isEnableMtom() <em>Enable Mtom</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnableMtom()
	 * @generated
	 * @ordered
	 */
	protected boolean enableMtom = ENABLE_MTOM_EDEFAULT;

	/**
	 * This is true if the Enable Mtom attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean enableMtomESet;

	/**
	 * The default value of the '{@link #getMtomThreshold() <em>Mtom Threshold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMtomThreshold()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger MTOM_THRESHOLD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMtomThreshold() <em>Mtom Threshold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMtomThreshold()
	 * @generated
	 * @ordered
	 */
	protected BigInteger mtomThreshold = MTOM_THRESHOLD_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAddressing() <em>Addressing</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAddressing()
	 * @generated
	 * @ordered
	 */
	protected AddressingType addressing;

	/**
	 * The cached value of the '{@link #getRespectBinding() <em>Respect Binding</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRespectBinding()
	 * @generated
	 * @ordered
	 */
	protected RespectBindingType respectBinding;

	/**
	 * The default value of the '{@link #getPortComponentLink() <em>Port Component Link</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortComponentLink()
	 * @generated
	 * @ordered
	 */
	protected static final String PORT_COMPONENT_LINK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPortComponentLink() <em>Port Component Link</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortComponentLink()
	 * @generated
	 * @ordered
	 */
	protected String portComponentLink = PORT_COMPONENT_LINK_EDEFAULT;

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
	protected PortComponentRefImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaeePackage.Literals.PORT_COMPONENT_REF;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getServiceEndpointInterface() {
		return serviceEndpointInterface;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServiceEndpointInterface(String newServiceEndpointInterface) {
		String oldServiceEndpointInterface = serviceEndpointInterface;
		serviceEndpointInterface = newServiceEndpointInterface;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.PORT_COMPONENT_REF__SERVICE_ENDPOINT_INTERFACE, oldServiceEndpointInterface, serviceEndpointInterface));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEnableMtom() {
		return enableMtom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnableMtom(boolean newEnableMtom) {
		boolean oldEnableMtom = enableMtom;
		enableMtom = newEnableMtom;
		boolean oldEnableMtomESet = enableMtomESet;
		enableMtomESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.PORT_COMPONENT_REF__ENABLE_MTOM, oldEnableMtom, enableMtom, !oldEnableMtomESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetEnableMtom() {
		boolean oldEnableMtom = enableMtom;
		boolean oldEnableMtomESet = enableMtomESet;
		enableMtom = ENABLE_MTOM_EDEFAULT;
		enableMtomESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JavaeePackage.PORT_COMPONENT_REF__ENABLE_MTOM, oldEnableMtom, ENABLE_MTOM_EDEFAULT, oldEnableMtomESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetEnableMtom() {
		return enableMtomESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getMtomThreshold() {
		return mtomThreshold;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMtomThreshold(BigInteger newMtomThreshold) {
		BigInteger oldMtomThreshold = mtomThreshold;
		mtomThreshold = newMtomThreshold;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.PORT_COMPONENT_REF__MTOM_THRESHOLD, oldMtomThreshold, mtomThreshold));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AddressingType getAddressing() {
		return addressing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAddressing(AddressingType newAddressing, NotificationChain msgs) {
		AddressingType oldAddressing = addressing;
		addressing = newAddressing;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaeePackage.PORT_COMPONENT_REF__ADDRESSING, oldAddressing, newAddressing);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAddressing(AddressingType newAddressing) {
		if (newAddressing != addressing) {
			NotificationChain msgs = null;
			if (addressing != null)
				msgs = ((InternalEObject)addressing).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaeePackage.PORT_COMPONENT_REF__ADDRESSING, null, msgs);
			if (newAddressing != null)
				msgs = ((InternalEObject)newAddressing).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaeePackage.PORT_COMPONENT_REF__ADDRESSING, null, msgs);
			msgs = basicSetAddressing(newAddressing, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.PORT_COMPONENT_REF__ADDRESSING, newAddressing, newAddressing));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RespectBindingType getRespectBinding() {
		return respectBinding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRespectBinding(RespectBindingType newRespectBinding, NotificationChain msgs) {
		RespectBindingType oldRespectBinding = respectBinding;
		respectBinding = newRespectBinding;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaeePackage.PORT_COMPONENT_REF__RESPECT_BINDING, oldRespectBinding, newRespectBinding);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRespectBinding(RespectBindingType newRespectBinding) {
		if (newRespectBinding != respectBinding) {
			NotificationChain msgs = null;
			if (respectBinding != null)
				msgs = ((InternalEObject)respectBinding).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaeePackage.PORT_COMPONENT_REF__RESPECT_BINDING, null, msgs);
			if (newRespectBinding != null)
				msgs = ((InternalEObject)newRespectBinding).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaeePackage.PORT_COMPONENT_REF__RESPECT_BINDING, null, msgs);
			msgs = basicSetRespectBinding(newRespectBinding, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.PORT_COMPONENT_REF__RESPECT_BINDING, newRespectBinding, newRespectBinding));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPortComponentLink() {
		return portComponentLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPortComponentLink(String newPortComponentLink) {
		String oldPortComponentLink = portComponentLink;
		portComponentLink = newPortComponentLink;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.PORT_COMPONENT_REF__PORT_COMPONENT_LINK, oldPortComponentLink, portComponentLink));
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
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.PORT_COMPONENT_REF__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JavaeePackage.PORT_COMPONENT_REF__ADDRESSING:
				return basicSetAddressing(null, msgs);
			case JavaeePackage.PORT_COMPONENT_REF__RESPECT_BINDING:
				return basicSetRespectBinding(null, msgs);
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
			case JavaeePackage.PORT_COMPONENT_REF__SERVICE_ENDPOINT_INTERFACE:
				return getServiceEndpointInterface();
			case JavaeePackage.PORT_COMPONENT_REF__ENABLE_MTOM:
				return isEnableMtom();
			case JavaeePackage.PORT_COMPONENT_REF__MTOM_THRESHOLD:
				return getMtomThreshold();
			case JavaeePackage.PORT_COMPONENT_REF__ADDRESSING:
				return getAddressing();
			case JavaeePackage.PORT_COMPONENT_REF__RESPECT_BINDING:
				return getRespectBinding();
			case JavaeePackage.PORT_COMPONENT_REF__PORT_COMPONENT_LINK:
				return getPortComponentLink();
			case JavaeePackage.PORT_COMPONENT_REF__ID:
				return getId();
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
			case JavaeePackage.PORT_COMPONENT_REF__SERVICE_ENDPOINT_INTERFACE:
				setServiceEndpointInterface((String)newValue);
				return;
			case JavaeePackage.PORT_COMPONENT_REF__ENABLE_MTOM:
				setEnableMtom((Boolean)newValue);
				return;
			case JavaeePackage.PORT_COMPONENT_REF__MTOM_THRESHOLD:
				setMtomThreshold((BigInteger)newValue);
				return;
			case JavaeePackage.PORT_COMPONENT_REF__ADDRESSING:
				setAddressing((AddressingType)newValue);
				return;
			case JavaeePackage.PORT_COMPONENT_REF__RESPECT_BINDING:
				setRespectBinding((RespectBindingType)newValue);
				return;
			case JavaeePackage.PORT_COMPONENT_REF__PORT_COMPONENT_LINK:
				setPortComponentLink((String)newValue);
				return;
			case JavaeePackage.PORT_COMPONENT_REF__ID:
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
			case JavaeePackage.PORT_COMPONENT_REF__SERVICE_ENDPOINT_INTERFACE:
				setServiceEndpointInterface(SERVICE_ENDPOINT_INTERFACE_EDEFAULT);
				return;
			case JavaeePackage.PORT_COMPONENT_REF__ENABLE_MTOM:
				unsetEnableMtom();
				return;
			case JavaeePackage.PORT_COMPONENT_REF__MTOM_THRESHOLD:
				setMtomThreshold(MTOM_THRESHOLD_EDEFAULT);
				return;
			case JavaeePackage.PORT_COMPONENT_REF__ADDRESSING:
				setAddressing((AddressingType)null);
				return;
			case JavaeePackage.PORT_COMPONENT_REF__RESPECT_BINDING:
				setRespectBinding((RespectBindingType)null);
				return;
			case JavaeePackage.PORT_COMPONENT_REF__PORT_COMPONENT_LINK:
				setPortComponentLink(PORT_COMPONENT_LINK_EDEFAULT);
				return;
			case JavaeePackage.PORT_COMPONENT_REF__ID:
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
			case JavaeePackage.PORT_COMPONENT_REF__SERVICE_ENDPOINT_INTERFACE:
				return SERVICE_ENDPOINT_INTERFACE_EDEFAULT == null ? serviceEndpointInterface != null : !SERVICE_ENDPOINT_INTERFACE_EDEFAULT.equals(serviceEndpointInterface);
			case JavaeePackage.PORT_COMPONENT_REF__ENABLE_MTOM:
				return isSetEnableMtom();
			case JavaeePackage.PORT_COMPONENT_REF__MTOM_THRESHOLD:
				return MTOM_THRESHOLD_EDEFAULT == null ? mtomThreshold != null : !MTOM_THRESHOLD_EDEFAULT.equals(mtomThreshold);
			case JavaeePackage.PORT_COMPONENT_REF__ADDRESSING:
				return addressing != null;
			case JavaeePackage.PORT_COMPONENT_REF__RESPECT_BINDING:
				return respectBinding != null;
			case JavaeePackage.PORT_COMPONENT_REF__PORT_COMPONENT_LINK:
				return PORT_COMPONENT_LINK_EDEFAULT == null ? portComponentLink != null : !PORT_COMPONENT_LINK_EDEFAULT.equals(portComponentLink);
			case JavaeePackage.PORT_COMPONENT_REF__ID:
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
		result.append(" (serviceEndpointInterface: "); //$NON-NLS-1$
		result.append(serviceEndpointInterface);
		result.append(", enableMtom: "); //$NON-NLS-1$
		if (enableMtomESet) result.append(enableMtom); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", mtomThreshold: "); //$NON-NLS-1$
		result.append(mtomThreshold);
		result.append(", portComponentLink: "); //$NON-NLS-1$
		result.append(portComponentLink);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //PortComponentRefImpl