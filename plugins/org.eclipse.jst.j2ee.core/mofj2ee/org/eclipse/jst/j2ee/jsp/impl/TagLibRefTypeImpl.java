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
package org.eclipse.jst.j2ee.jsp.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.jst.j2ee.jsp.JspPackage;
import org.eclipse.jst.j2ee.jsp.TagLibRefType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tag Lib Ref Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.internal.jsp.impl.TagLibRefTypeImpl#getTaglibURI <em>Taglib URI</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.jsp.impl.TagLibRefTypeImpl#getTaglibLocation <em>Taglib Location</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TagLibRefTypeImpl extends EObjectImpl implements TagLibRefType {
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
	protected EClass eStaticClass() {
		return JspPackage.eINSTANCE.getTagLibRefType();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTaglibURI() {
		return taglibURI;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
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
			eNotify(new ENotificationImpl(this, Notification.SET, JspPackage.TAG_LIB_REF_TYPE__TAGLIB_LOCATION, oldTaglibLocation, taglibLocation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JspPackage.TAG_LIB_REF_TYPE__TAGLIB_URI:
				return getTaglibURI();
			case JspPackage.TAG_LIB_REF_TYPE__TAGLIB_LOCATION:
				return getTaglibLocation();
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
			case JspPackage.TAG_LIB_REF_TYPE__TAGLIB_URI:
				setTaglibURI((String)newValue);
				return;
			case JspPackage.TAG_LIB_REF_TYPE__TAGLIB_LOCATION:
				setTaglibLocation((String)newValue);
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
			case JspPackage.TAG_LIB_REF_TYPE__TAGLIB_URI:
				setTaglibURI(TAGLIB_URI_EDEFAULT);
				return;
			case JspPackage.TAG_LIB_REF_TYPE__TAGLIB_LOCATION:
				setTaglibLocation(TAGLIB_LOCATION_EDEFAULT);
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
			case JspPackage.TAG_LIB_REF_TYPE__TAGLIB_URI:
				return TAGLIB_URI_EDEFAULT == null ? taglibURI != null : !TAGLIB_URI_EDEFAULT.equals(taglibURI);
			case JspPackage.TAG_LIB_REF_TYPE__TAGLIB_LOCATION:
				return TAGLIB_LOCATION_EDEFAULT == null ? taglibLocation != null : !TAGLIB_LOCATION_EDEFAULT.equals(taglibLocation);
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
		result.append(" (taglibURI: ");//$NON-NLS-1$
		result.append(taglibURI);
		result.append(", taglibLocation: ");//$NON-NLS-1$
		result.append(taglibLocation);
		result.append(')');
		return result.toString();
	}

} //TagLibRefTypeImpl
