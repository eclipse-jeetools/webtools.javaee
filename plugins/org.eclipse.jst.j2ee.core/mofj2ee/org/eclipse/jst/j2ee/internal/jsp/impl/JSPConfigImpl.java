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
package org.eclipse.jst.j2ee.internal.jsp.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.jsp.JSPConfig;
import org.eclipse.jst.j2ee.jsp.JSPPropertyGroup;
import org.eclipse.jst.j2ee.jsp.JspPackage;
import org.eclipse.jst.j2ee.jsp.TagLibRefType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>JSP Config</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.internal.jsp.impl.JSPConfigImpl#getTagLibs <em>Tag Libs</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.jsp.impl.JSPConfigImpl#getPropertyGroups <em>Property Groups</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class JSPConfigImpl extends EObjectImpl implements JSPConfig {
	/**
	 * The cached value of the '{@link #getTagLibs() <em>Tag Libs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTagLibs()
	 * @generated
	 * @ordered
	 */
	protected EList tagLibs = null;

	/**
	 * The cached value of the '{@link #getPropertyGroups() <em>Property Groups</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPropertyGroups()
	 * @generated
	 * @ordered
	 */
	protected EList propertyGroups = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected JSPConfigImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return JspPackage.eINSTANCE.getJSPConfig();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getTagLibs() {
		if (tagLibs == null) {
			tagLibs = new EObjectContainmentEList(TagLibRefType.class, this, JspPackage.JSP_CONFIG__TAG_LIBS);
		}
		return tagLibs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getPropertyGroups() {
		if (propertyGroups == null) {
			propertyGroups = new EObjectContainmentEList(JSPPropertyGroup.class, this, JspPackage.JSP_CONFIG__PROPERTY_GROUPS);
		}
		return propertyGroups;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case JspPackage.JSP_CONFIG__TAG_LIBS:
					return ((InternalEList)getTagLibs()).basicRemove(otherEnd, msgs);
				case JspPackage.JSP_CONFIG__PROPERTY_GROUPS:
					return ((InternalEList)getPropertyGroups()).basicRemove(otherEnd, msgs);
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
			case JspPackage.JSP_CONFIG__TAG_LIBS:
				return getTagLibs();
			case JspPackage.JSP_CONFIG__PROPERTY_GROUPS:
				return getPropertyGroups();
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
			case JspPackage.JSP_CONFIG__TAG_LIBS:
				getTagLibs().clear();
				getTagLibs().addAll((Collection)newValue);
				return;
			case JspPackage.JSP_CONFIG__PROPERTY_GROUPS:
				getPropertyGroups().clear();
				getPropertyGroups().addAll((Collection)newValue);
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
			case JspPackage.JSP_CONFIG__TAG_LIBS:
				getTagLibs().clear();
				return;
			case JspPackage.JSP_CONFIG__PROPERTY_GROUPS:
				getPropertyGroups().clear();
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
			case JspPackage.JSP_CONFIG__TAG_LIBS:
				return tagLibs != null && !tagLibs.isEmpty();
			case JspPackage.JSP_CONFIG__PROPERTY_GROUPS:
				return propertyGroups != null && !propertyGroups.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //JSPConfigImpl
