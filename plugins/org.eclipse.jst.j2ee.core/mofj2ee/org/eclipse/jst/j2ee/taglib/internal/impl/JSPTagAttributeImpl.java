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
package org.eclipse.jst.j2ee.taglib.internal.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.taglib.internal.JSPTagAttribute;
import org.eclipse.jst.j2ee.taglib.internal.TaglibPackage;


/**
 * The attribute tag defines an attribute for the nesting tag
 */
public class JSPTagAttributeImpl extends EObjectImpl implements JSPTagAttribute, EObject{

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String name = NAME_EDEFAULT;
	/**
	 * The default value of the '{@link #isRequired() <em>Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRequired()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REQUIRED_EDEFAULT = false;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected boolean required = REQUIRED_EDEFAULT;
	/**
	 * This is true if the Required attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean requiredESet = false;

	/**
	 * The default value of the '{@link #isRtExprValue() <em>Rt Expr Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRtExprValue()
	 * @generated
	 * @ordered
	 */
	protected static final boolean RT_EXPR_VALUE_EDEFAULT = false;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected boolean rtExprValue = RT_EXPR_VALUE_EDEFAULT;
	/**
	 * This is true if the Rt Expr Value attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean rtExprValueESet = false;

	/**
	 * The default value of the '{@link #isFragment() <em>Fragment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFragment()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FRAGMENT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isFragment() <em>Fragment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFragment()
	 * @generated
	 * @ordered
	 */
	protected boolean fragment = FRAGMENT_EDEFAULT;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected JavaClass type = null;
	/**
	 * The cached value of the '{@link #getDescriptions() <em>Descriptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptions()
	 * @generated
	 * @ordered
	 */
	protected EList descriptions = null;

	public JSPTagAttributeImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return TaglibPackage.eINSTANCE.getJSPTagAttribute();
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Defines the canonical name of a tag or attribute being defined (required).
	 */
	public String getName() {
		return name;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TaglibPackage.JSP_TAG_ATTRIBUTE__NAME, oldName, name));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Defines if the nesting attribute is required or optional.
	 */
	public boolean isRequired() {
		return required;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setRequired(boolean newRequired) {
		boolean oldRequired = required;
		required = newRequired;
		boolean oldRequiredESet = requiredESet;
		requiredESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TaglibPackage.JSP_TAG_ATTRIBUTE__REQUIRED, oldRequired, required, !oldRequiredESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetRequired() {
		boolean oldRequired = required;
		boolean oldRequiredESet = requiredESet;
		required = REQUIRED_EDEFAULT;
		requiredESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, TaglibPackage.JSP_TAG_ATTRIBUTE__REQUIRED, oldRequired, REQUIRED_EDEFAULT, oldRequiredESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetRequired() {
		return requiredESet;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Defines if the nesting attribute can have scriptlet expressions asa value, i.e
	 * the value of the attribute may be dynamically calculatedat request time, as
	 * opposed to a static value determined at translationtime. (optional)
	 * Default = false
	 */
	public boolean isRtExprValue() {
		return rtExprValue;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setRtExprValue(boolean newRtExprValue) {
		boolean oldRtExprValue = rtExprValue;
		rtExprValue = newRtExprValue;
		boolean oldRtExprValueESet = rtExprValueESet;
		rtExprValueESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TaglibPackage.JSP_TAG_ATTRIBUTE__RT_EXPR_VALUE, oldRtExprValue, rtExprValue, !oldRtExprValueESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetRtExprValue() {
		boolean oldRtExprValue = rtExprValue;
		boolean oldRtExprValueESet = rtExprValueESet;
		rtExprValue = RT_EXPR_VALUE_EDEFAULT;
		rtExprValueESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, TaglibPackage.JSP_TAG_ATTRIBUTE__RT_EXPR_VALUE, oldRtExprValue, RT_EXPR_VALUE_EDEFAULT, oldRtExprValueESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetRtExprValue() {
		return rtExprValueESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFragment() {
		return fragment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFragment(boolean newFragment) {
		boolean oldFragment = fragment;
		fragment = newFragment;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TaglibPackage.JSP_TAG_ATTRIBUTE__FRAGMENT, oldFragment, fragment));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The value of the type element describes the Java type of the attributes value.
	 * For static values (those determined at translation time) the type is always
	 * java.lang.String.
	 */
	public JavaClass getType() {
		if (type != null && type.eIsProxy()) {
			JavaClass oldType = type;
			type = (JavaClass)eResolveProxy((InternalEObject)type);
			if (type != oldType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TaglibPackage.JSP_TAG_ATTRIBUTE__TYPE, oldType, type));
			}
		}
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass basicGetType() {
		return type;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setType(JavaClass newType) {
		JavaClass oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TaglibPackage.JSP_TAG_ATTRIBUTE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList(Description.class, this, TaglibPackage.JSP_TAG_ATTRIBUTE__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case TaglibPackage.JSP_TAG_ATTRIBUTE__DESCRIPTIONS:
					return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
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
			case TaglibPackage.JSP_TAG_ATTRIBUTE__NAME:
				return getName();
			case TaglibPackage.JSP_TAG_ATTRIBUTE__REQUIRED:
				return isRequired() ? Boolean.TRUE : Boolean.FALSE;
			case TaglibPackage.JSP_TAG_ATTRIBUTE__RT_EXPR_VALUE:
				return isRtExprValue() ? Boolean.TRUE : Boolean.FALSE;
			case TaglibPackage.JSP_TAG_ATTRIBUTE__FRAGMENT:
				return isFragment() ? Boolean.TRUE : Boolean.FALSE;
			case TaglibPackage.JSP_TAG_ATTRIBUTE__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case TaglibPackage.JSP_TAG_ATTRIBUTE__DESCRIPTIONS:
				return getDescriptions();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case TaglibPackage.JSP_TAG_ATTRIBUTE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case TaglibPackage.JSP_TAG_ATTRIBUTE__REQUIRED:
				return isSetRequired();
			case TaglibPackage.JSP_TAG_ATTRIBUTE__RT_EXPR_VALUE:
				return isSetRtExprValue();
			case TaglibPackage.JSP_TAG_ATTRIBUTE__FRAGMENT:
				return fragment != FRAGMENT_EDEFAULT;
			case TaglibPackage.JSP_TAG_ATTRIBUTE__TYPE:
				return type != null;
			case TaglibPackage.JSP_TAG_ATTRIBUTE__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case TaglibPackage.JSP_TAG_ATTRIBUTE__NAME:
				setName((String)newValue);
				return;
			case TaglibPackage.JSP_TAG_ATTRIBUTE__REQUIRED:
				setRequired(((Boolean)newValue).booleanValue());
				return;
			case TaglibPackage.JSP_TAG_ATTRIBUTE__RT_EXPR_VALUE:
				setRtExprValue(((Boolean)newValue).booleanValue());
				return;
			case TaglibPackage.JSP_TAG_ATTRIBUTE__FRAGMENT:
				setFragment(((Boolean)newValue).booleanValue());
				return;
			case TaglibPackage.JSP_TAG_ATTRIBUTE__TYPE:
				setType((JavaClass)newValue);
				return;
			case TaglibPackage.JSP_TAG_ATTRIBUTE__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case TaglibPackage.JSP_TAG_ATTRIBUTE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case TaglibPackage.JSP_TAG_ATTRIBUTE__REQUIRED:
				unsetRequired();
				return;
			case TaglibPackage.JSP_TAG_ATTRIBUTE__RT_EXPR_VALUE:
				unsetRtExprValue();
				return;
			case TaglibPackage.JSP_TAG_ATTRIBUTE__FRAGMENT:
				setFragment(FRAGMENT_EDEFAULT);
				return;
			case TaglibPackage.JSP_TAG_ATTRIBUTE__TYPE:
				setType((JavaClass)null);
				return;
			case TaglibPackage.JSP_TAG_ATTRIBUTE__DESCRIPTIONS:
				getDescriptions().clear();
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", required: ");
		if (requiredESet) result.append(required); else result.append("<unset>");
		result.append(", rtExprValue: ");
		if (rtExprValueESet) result.append(rtExprValue); else result.append("<unset>");
		result.append(", fragment: ");
		result.append(fragment);
		result.append(')');
		return result.toString();
	}

}














