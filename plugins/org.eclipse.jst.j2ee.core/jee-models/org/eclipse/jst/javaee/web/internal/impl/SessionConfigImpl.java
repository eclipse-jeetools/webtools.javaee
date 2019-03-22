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
package org.eclipse.jst.javaee.web.internal.impl;

import java.math.BigInteger;

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
import org.eclipse.jst.javaee.web.CookieConfigType;
import org.eclipse.jst.javaee.web.SessionConfig;

import org.eclipse.jst.javaee.web.TrackingModeType;
import org.eclipse.jst.javaee.web.internal.metadata.WebPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Session Config</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.SessionConfigImpl#getSessionTimeout <em>Session Timeout</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.SessionConfigImpl#getCookieConfig <em>Cookie Config</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.SessionConfigImpl#getTrackingMode <em>Tracking Mode</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.SessionConfigImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SessionConfigImpl extends EObjectImpl implements SessionConfig {
	/**
	 * The default value of the '{@link #getSessionTimeout() <em>Session Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSessionTimeout()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger SESSION_TIMEOUT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSessionTimeout() <em>Session Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSessionTimeout()
	 * @generated
	 * @ordered
	 */
	protected BigInteger sessionTimeout = SESSION_TIMEOUT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCookieConfig() <em>Cookie Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCookieConfig()
	 * @generated
	 * @ordered
	 */
	protected CookieConfigType cookieConfig;

	/**
	 * The cached value of the '{@link #getTrackingMode() <em>Tracking Mode</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrackingMode()
	 * @generated
	 * @ordered
	 */
	protected EList<TrackingModeType> trackingMode;

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
	protected SessionConfigImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WebPackage.Literals.SESSION_CONFIG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getSessionTimeout() {
		return sessionTimeout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSessionTimeout(BigInteger newSessionTimeout) {
		BigInteger oldSessionTimeout = sessionTimeout;
		sessionTimeout = newSessionTimeout;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.SESSION_CONFIG__SESSION_TIMEOUT, oldSessionTimeout, sessionTimeout));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CookieConfigType getCookieConfig() {
		return cookieConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCookieConfig(CookieConfigType newCookieConfig, NotificationChain msgs) {
		CookieConfigType oldCookieConfig = cookieConfig;
		cookieConfig = newCookieConfig;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WebPackage.SESSION_CONFIG__COOKIE_CONFIG, oldCookieConfig, newCookieConfig);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCookieConfig(CookieConfigType newCookieConfig) {
		if (newCookieConfig != cookieConfig) {
			NotificationChain msgs = null;
			if (cookieConfig != null)
				msgs = ((InternalEObject)cookieConfig).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WebPackage.SESSION_CONFIG__COOKIE_CONFIG, null, msgs);
			if (newCookieConfig != null)
				msgs = ((InternalEObject)newCookieConfig).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - WebPackage.SESSION_CONFIG__COOKIE_CONFIG, null, msgs);
			msgs = basicSetCookieConfig(newCookieConfig, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.SESSION_CONFIG__COOKIE_CONFIG, newCookieConfig, newCookieConfig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<TrackingModeType> getTrackingMode() {
		if (trackingMode == null) {
			trackingMode = new EDataTypeEList<TrackingModeType>(TrackingModeType.class, this, WebPackage.SESSION_CONFIG__TRACKING_MODE);
		}
		return trackingMode;
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
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.SESSION_CONFIG__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WebPackage.SESSION_CONFIG__COOKIE_CONFIG:
				return basicSetCookieConfig(null, msgs);
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
			case WebPackage.SESSION_CONFIG__SESSION_TIMEOUT:
				return getSessionTimeout();
			case WebPackage.SESSION_CONFIG__COOKIE_CONFIG:
				return getCookieConfig();
			case WebPackage.SESSION_CONFIG__TRACKING_MODE:
				return getTrackingMode();
			case WebPackage.SESSION_CONFIG__ID:
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
			case WebPackage.SESSION_CONFIG__SESSION_TIMEOUT:
				if(newValue.getClass() == Integer.class) {
			        Integer i = (Integer) newValue;
				    setSessionTimeout(BigInteger.valueOf(i));
				} else if (newValue.getClass() == BigInteger.class)
					setSessionTimeout( (BigInteger) newValue);
				else {
					// do nothing if value is not Integer or BigInteger.
				}
				return;
			case WebPackage.SESSION_CONFIG__COOKIE_CONFIG:
				setCookieConfig((CookieConfigType)newValue);
				return;
			case WebPackage.SESSION_CONFIG__TRACKING_MODE:
				getTrackingMode().clear();
				getTrackingMode().addAll((Collection<? extends TrackingModeType>)newValue);
				return;
			case WebPackage.SESSION_CONFIG__ID:
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
			case WebPackage.SESSION_CONFIG__SESSION_TIMEOUT:
				setSessionTimeout(SESSION_TIMEOUT_EDEFAULT);
				return;
			case WebPackage.SESSION_CONFIG__COOKIE_CONFIG:
				setCookieConfig((CookieConfigType)null);
				return;
			case WebPackage.SESSION_CONFIG__TRACKING_MODE:
				getTrackingMode().clear();
				return;
			case WebPackage.SESSION_CONFIG__ID:
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
			case WebPackage.SESSION_CONFIG__SESSION_TIMEOUT:
				return SESSION_TIMEOUT_EDEFAULT == null ? sessionTimeout != null : !SESSION_TIMEOUT_EDEFAULT.equals(sessionTimeout);
			case WebPackage.SESSION_CONFIG__COOKIE_CONFIG:
				return cookieConfig != null;
			case WebPackage.SESSION_CONFIG__TRACKING_MODE:
				return trackingMode != null && !trackingMode.isEmpty();
			case WebPackage.SESSION_CONFIG__ID:
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
		result.append(" (sessionTimeout: "); //$NON-NLS-1$
		result.append(sessionTimeout);
		result.append(", trackingMode: "); //$NON-NLS-1$
		result.append(trackingMode);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //SessionConfigImpl