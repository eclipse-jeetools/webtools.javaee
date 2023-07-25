/*******************************************************************************
 * Copyright (c) 2001, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.jsp.internal.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jst.j2ee.common.internal.impl.J2EEEObjectImpl;
import org.eclipse.jst.j2ee.jsp.JspPackage;
import org.eclipse.jst.j2ee.jsp.TagLibRefType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tag Lib Ref Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.jsp.internal.impl.TagLibRefTypeImpl#getTaglibURI <em>Taglib URI</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.jsp.internal.impl.TagLibRefTypeImpl#getTaglibLocation <em>Taglib Location</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TagLibRefTypeImpl extends J2EEEObjectImpl implements TagLibRefType {
	/**
	 * The default value of the '{@link #getTaglibURI() <em>Taglib URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTaglibURI()
	 * @generated
	 * @ordered
	 */
	protected static final String TAGLIB_URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTaglibURI() <em>Taglib URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTaglibURI()
	 * @generated
	 * @ordered
	 */
	protected String taglibURI = TAGLIB_URI_EDEFAULT;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TagLibRefTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JspPackage.Literals.TAG_LIB_REF_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTaglibURI() {
		return taglibURI;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTaglibURI(String newTaglibURI) {
		String oldTaglibURI = taglibURI;
		taglibURI = newTaglibURI;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JspPackage.TAG_LIB_REF_TYPE__TAGLIB_URI, oldTaglibURI, taglibURI));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTaglibLocation() {
		return taglibLocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTaglibLocation(String newTaglibLocation) {
		String oldTaglibLocation = taglibLocation;
		taglibLocation = newTaglibLocation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JspPackage.TAG_LIB_REF_TYPE__TAGLIB_LOCATION, oldTaglibLocation, taglibLocation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case JspPackage.TAG_LIB_REF_TYPE__TAGLIB_URI:
				return getTaglibURI();
			case JspPackage.TAG_LIB_REF_TYPE__TAGLIB_LOCATION:
				return getTaglibLocation();
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
			case JspPackage.TAG_LIB_REF_TYPE__TAGLIB_URI:
				setTaglibURI((String)newValue);
				return;
			case JspPackage.TAG_LIB_REF_TYPE__TAGLIB_LOCATION:
				setTaglibLocation((String)newValue);
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
			case JspPackage.TAG_LIB_REF_TYPE__TAGLIB_URI:
				setTaglibURI(TAGLIB_URI_EDEFAULT);
				return;
			case JspPackage.TAG_LIB_REF_TYPE__TAGLIB_LOCATION:
				setTaglibLocation(TAGLIB_LOCATION_EDEFAULT);
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
			case JspPackage.TAG_LIB_REF_TYPE__TAGLIB_URI:
				return TAGLIB_URI_EDEFAULT == null ? taglibURI != null : !TAGLIB_URI_EDEFAULT.equals(taglibURI);
			case JspPackage.TAG_LIB_REF_TYPE__TAGLIB_LOCATION:
				return TAGLIB_LOCATION_EDEFAULT == null ? taglibLocation != null : !TAGLIB_LOCATION_EDEFAULT.equals(taglibLocation);
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
		result.append(" (taglibURI: "); //$NON-NLS-1$
		result.append(taglibURI);
		result.append(", taglibLocation: "); //$NON-NLS-1$
		result.append(taglibLocation);
		result.append(')');
		return result.toString();
	}

} //TagLibRefTypeImpl
