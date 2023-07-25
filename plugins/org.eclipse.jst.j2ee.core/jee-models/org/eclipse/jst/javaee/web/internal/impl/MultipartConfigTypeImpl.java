/*******************************************************************************
 * Copyright (c) 2009, 2023 IBM Corporation and others.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/**
 * <copyright>
 * </copyright>
 *
 * $Id: MultipartConfigTypeImpl.java,v 1.1 2009/10/15 18:52:05 canderson Exp $
 */
package org.eclipse.jst.javaee.web.internal.impl;

import java.math.BigInteger;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.jst.javaee.web.MultipartConfigType;

import org.eclipse.jst.javaee.web.internal.metadata.WebPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Multipart Config Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.MultipartConfigTypeImpl#getLocation <em>Location</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.MultipartConfigTypeImpl#getMaxFileSize <em>Max File Size</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.MultipartConfigTypeImpl#getMaxRequestSize <em>Max Request Size</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.MultipartConfigTypeImpl#getFileSizeThreshold <em>File Size Threshold</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MultipartConfigTypeImpl extends EObjectImpl implements MultipartConfigType {
	/**
	 * The default value of the '{@link #getLocation() <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected static final String LOCATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLocation() <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected String location = LOCATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxFileSize() <em>Max File Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxFileSize()
	 * @generated
	 * @ordered
	 */
	protected static final long MAX_FILE_SIZE_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getMaxFileSize() <em>Max File Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxFileSize()
	 * @generated
	 * @ordered
	 */
	protected long maxFileSize = MAX_FILE_SIZE_EDEFAULT;

	/**
	 * This is true if the Max File Size attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean maxFileSizeESet;

	/**
	 * The default value of the '{@link #getMaxRequestSize() <em>Max Request Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxRequestSize()
	 * @generated
	 * @ordered
	 */
	protected static final long MAX_REQUEST_SIZE_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getMaxRequestSize() <em>Max Request Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxRequestSize()
	 * @generated
	 * @ordered
	 */
	protected long maxRequestSize = MAX_REQUEST_SIZE_EDEFAULT;

	/**
	 * This is true if the Max Request Size attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean maxRequestSizeESet;

	/**
	 * The default value of the '{@link #getFileSizeThreshold() <em>File Size Threshold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileSizeThreshold()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger FILE_SIZE_THRESHOLD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFileSizeThreshold() <em>File Size Threshold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileSizeThreshold()
	 * @generated
	 * @ordered
	 */
	protected BigInteger fileSizeThreshold = FILE_SIZE_THRESHOLD_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MultipartConfigTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WebPackage.Literals.MULTIPART_CONFIG_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLocation() {
		return location;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLocation(String newLocation) {
		String oldLocation = location;
		location = newLocation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.MULTIPART_CONFIG_TYPE__LOCATION, oldLocation, location));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public long getMaxFileSize() {
		return maxFileSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMaxFileSize(long newMaxFileSize) {
		long oldMaxFileSize = maxFileSize;
		maxFileSize = newMaxFileSize;
		boolean oldMaxFileSizeESet = maxFileSizeESet;
		maxFileSizeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.MULTIPART_CONFIG_TYPE__MAX_FILE_SIZE, oldMaxFileSize, maxFileSize, !oldMaxFileSizeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetMaxFileSize() {
		long oldMaxFileSize = maxFileSize;
		boolean oldMaxFileSizeESet = maxFileSizeESet;
		maxFileSize = MAX_FILE_SIZE_EDEFAULT;
		maxFileSizeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, WebPackage.MULTIPART_CONFIG_TYPE__MAX_FILE_SIZE, oldMaxFileSize, MAX_FILE_SIZE_EDEFAULT, oldMaxFileSizeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetMaxFileSize() {
		return maxFileSizeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public long getMaxRequestSize() {
		return maxRequestSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMaxRequestSize(long newMaxRequestSize) {
		long oldMaxRequestSize = maxRequestSize;
		maxRequestSize = newMaxRequestSize;
		boolean oldMaxRequestSizeESet = maxRequestSizeESet;
		maxRequestSizeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.MULTIPART_CONFIG_TYPE__MAX_REQUEST_SIZE, oldMaxRequestSize, maxRequestSize, !oldMaxRequestSizeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetMaxRequestSize() {
		long oldMaxRequestSize = maxRequestSize;
		boolean oldMaxRequestSizeESet = maxRequestSizeESet;
		maxRequestSize = MAX_REQUEST_SIZE_EDEFAULT;
		maxRequestSizeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, WebPackage.MULTIPART_CONFIG_TYPE__MAX_REQUEST_SIZE, oldMaxRequestSize, MAX_REQUEST_SIZE_EDEFAULT, oldMaxRequestSizeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetMaxRequestSize() {
		return maxRequestSizeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BigInteger getFileSizeThreshold() {
		return fileSizeThreshold;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFileSizeThreshold(BigInteger newFileSizeThreshold) {
		BigInteger oldFileSizeThreshold = fileSizeThreshold;
		fileSizeThreshold = newFileSizeThreshold;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.MULTIPART_CONFIG_TYPE__FILE_SIZE_THRESHOLD, oldFileSizeThreshold, fileSizeThreshold));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WebPackage.MULTIPART_CONFIG_TYPE__LOCATION:
				return getLocation();
			case WebPackage.MULTIPART_CONFIG_TYPE__MAX_FILE_SIZE:
				return getMaxFileSize();
			case WebPackage.MULTIPART_CONFIG_TYPE__MAX_REQUEST_SIZE:
				return getMaxRequestSize();
			case WebPackage.MULTIPART_CONFIG_TYPE__FILE_SIZE_THRESHOLD:
				return getFileSizeThreshold();
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
			case WebPackage.MULTIPART_CONFIG_TYPE__LOCATION:
				setLocation((String)newValue);
				return;
			case WebPackage.MULTIPART_CONFIG_TYPE__MAX_FILE_SIZE:
				setMaxFileSize((Long)newValue);
				return;
			case WebPackage.MULTIPART_CONFIG_TYPE__MAX_REQUEST_SIZE:
				setMaxRequestSize((Long)newValue);
				return;
			case WebPackage.MULTIPART_CONFIG_TYPE__FILE_SIZE_THRESHOLD:
				setFileSizeThreshold((BigInteger)newValue);
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
			case WebPackage.MULTIPART_CONFIG_TYPE__LOCATION:
				setLocation(LOCATION_EDEFAULT);
				return;
			case WebPackage.MULTIPART_CONFIG_TYPE__MAX_FILE_SIZE:
				unsetMaxFileSize();
				return;
			case WebPackage.MULTIPART_CONFIG_TYPE__MAX_REQUEST_SIZE:
				unsetMaxRequestSize();
				return;
			case WebPackage.MULTIPART_CONFIG_TYPE__FILE_SIZE_THRESHOLD:
				setFileSizeThreshold(FILE_SIZE_THRESHOLD_EDEFAULT);
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
			case WebPackage.MULTIPART_CONFIG_TYPE__LOCATION:
				return LOCATION_EDEFAULT == null ? location != null : !LOCATION_EDEFAULT.equals(location);
			case WebPackage.MULTIPART_CONFIG_TYPE__MAX_FILE_SIZE:
				return isSetMaxFileSize();
			case WebPackage.MULTIPART_CONFIG_TYPE__MAX_REQUEST_SIZE:
				return isSetMaxRequestSize();
			case WebPackage.MULTIPART_CONFIG_TYPE__FILE_SIZE_THRESHOLD:
				return FILE_SIZE_THRESHOLD_EDEFAULT == null ? fileSizeThreshold != null : !FILE_SIZE_THRESHOLD_EDEFAULT.equals(fileSizeThreshold);
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
		result.append(" (location: "); //$NON-NLS-1$
		result.append(location);
		result.append(", maxFileSize: "); //$NON-NLS-1$
		if (maxFileSizeESet) result.append(maxFileSize); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", maxRequestSize: "); //$NON-NLS-1$
		if (maxRequestSizeESet) result.append(maxRequestSize); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", fileSizeThreshold: "); //$NON-NLS-1$
		result.append(fileSizeThreshold);
		result.append(')');
		return result.toString();
	}

} //MultipartConfigTypeImpl
