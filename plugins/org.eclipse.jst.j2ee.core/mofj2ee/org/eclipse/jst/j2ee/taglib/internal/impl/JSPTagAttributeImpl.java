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
package org.eclipse.jst.j2ee.taglib.internal.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.internal.impl.J2EEEObjectImpl;
import org.eclipse.jst.j2ee.taglib.internal.JSPTagAttribute;
import org.eclipse.jst.j2ee.taglib.internal.TaglibPackage;


/**
 * The attribute tag defines an attribute for the nesting tag
 * @generated
 */
public class JSPTagAttributeImpl extends J2EEEObjectImpl implements JSPTagAttribute {

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
	@Override
	protected EClass eStaticClass() {
		return TaglibPackage.Literals.JSP_TAG_ATTRIBUTE;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Defines the canonical name of a tag or attribute being defined (required).
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
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
	@Override
	public boolean isRequired() {
		return required;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
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
	@Override
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
	@Override
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
	@Override
	public boolean isRtExprValue() {
		return rtExprValue;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
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
	@Override
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
	@Override
	public boolean isSetRtExprValue() {
		return rtExprValueESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFragment() {
		return fragment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
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
	@Override
	public JavaClass getType() {
		if (type != null && type.eIsProxy()) {
			InternalEObject oldType = (InternalEObject)type;
			type = (JavaClass)eResolveProxy(oldType);
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
	@Override
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
	@Override
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
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TaglibPackage.JSP_TAG_ATTRIBUTE__DESCRIPTIONS:
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
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
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
		return super.eIsSet(featureID);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: "); //$NON-NLS-1$
		result.append(name);
		result.append(", required: "); //$NON-NLS-1$
		if (requiredESet) result.append(required); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", rtExprValue: "); //$NON-NLS-1$
		if (rtExprValueESet) result.append(rtExprValue); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", fragment: "); //$NON-NLS-1$
		result.append(fragment);
		result.append(')');
		return result.toString();
	}

}














