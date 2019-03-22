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
package org.eclipse.jst.javaee.jsp.internal.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.jst.javaee.jsp.TagLib;

import org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tag Lib</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.jsp.internal.impl.TagLibImpl#getTaglibUri <em>Taglib Uri</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jsp.internal.impl.TagLibImpl#getTaglibLocation <em>Taglib Location</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jsp.internal.impl.TagLibImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TagLibImpl extends EObjectImpl implements TagLib {
	/**
	 * The default value of the '{@link #getTaglibUri() <em>Taglib Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTaglibUri()
	 * @generated
	 * @ordered
	 */
	protected static final String TAGLIB_URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTaglibUri() <em>Taglib Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTaglibUri()
	 * @generated
	 * @ordered
	 */
	protected String taglibUri = TAGLIB_URI_EDEFAULT;

	/**
	 * The default value of the '{@link #getTaglibLocation() <em>Taglib Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTaglibLocation()
	 * @generated
	 * @ordered
	 */
	protected static final String TAGLIB_LOCATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTaglibLocation() <em>Taglib Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTaglibLocation()
	 * @generated
	 * @ordered
	 */
	protected String taglibLocation = TAGLIB_LOCATION_EDEFAULT;

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
	protected TagLibImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JspPackage.Literals.TAG_LIB;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTaglibUri() {
		return taglibUri;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTaglibUri(String newTaglibUri) {
		String oldTaglibUri = taglibUri;
		taglibUri = newTaglibUri;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JspPackage.TAG_LIB__TAGLIB_URI, oldTaglibUri, taglibUri));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTaglibLocation() {
		return taglibLocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTaglibLocation(String newTaglibLocation) {
		String oldTaglibLocation = taglibLocation;
		taglibLocation = newTaglibLocation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JspPackage.TAG_LIB__TAGLIB_LOCATION, oldTaglibLocation, taglibLocation));
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
			eNotify(new ENotificationImpl(this, Notification.SET, JspPackage.TAG_LIB__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case JspPackage.TAG_LIB__TAGLIB_URI:
				return getTaglibUri();
			case JspPackage.TAG_LIB__TAGLIB_LOCATION:
				return getTaglibLocation();
			case JspPackage.TAG_LIB__ID:
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
			case JspPackage.TAG_LIB__TAGLIB_URI:
				setTaglibUri((String)newValue);
				return;
			case JspPackage.TAG_LIB__TAGLIB_LOCATION:
				setTaglibLocation((String)newValue);
				return;
			case JspPackage.TAG_LIB__ID:
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
			case JspPackage.TAG_LIB__TAGLIB_URI:
				setTaglibUri(TAGLIB_URI_EDEFAULT);
				return;
			case JspPackage.TAG_LIB__TAGLIB_LOCATION:
				setTaglibLocation(TAGLIB_LOCATION_EDEFAULT);
				return;
			case JspPackage.TAG_LIB__ID:
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
			case JspPackage.TAG_LIB__TAGLIB_URI:
				return TAGLIB_URI_EDEFAULT == null ? taglibUri != null : !TAGLIB_URI_EDEFAULT.equals(taglibUri);
			case JspPackage.TAG_LIB__TAGLIB_LOCATION:
				return TAGLIB_LOCATION_EDEFAULT == null ? taglibLocation != null : !TAGLIB_LOCATION_EDEFAULT.equals(taglibLocation);
			case JspPackage.TAG_LIB__ID:
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
		result.append(" (taglibUri: "); //$NON-NLS-1$
		result.append(taglibUri);
		result.append(", taglibLocation: "); //$NON-NLS-1$
		result.append(taglibLocation);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //TagLibImpl