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
package org.eclipse.jst.j2ee.internal.taglib.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.taglib.JSPScriptingVariableScope;
import org.eclipse.jst.j2ee.taglib.JSPVariable;
import org.eclipse.jst.j2ee.taglib.TaglibPackage;


/**
 * @generated
 */
public class JSPVariableImpl extends EObjectImpl implements JSPVariable{

	/**
	 * The default value of the '{@link #getNameGiven() <em>Name Given</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNameGiven()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_GIVEN_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String nameGiven = NAME_GIVEN_EDEFAULT;
	/**
	 * The default value of the '{@link #getNameFromAttribute() <em>Name From Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNameFromAttribute()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_FROM_ATTRIBUTE_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String nameFromAttribute = NAME_FROM_ATTRIBUTE_EDEFAULT;
	/**
	 * The default value of the '{@link #isDeclare() <em>Declare</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDeclare()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DECLARE_EDEFAULT = false;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected boolean declare = DECLARE_EDEFAULT;
	/**
	 * The default value of the '{@link #getScope() <em>Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScope()
	 * @generated
	 * @ordered
	 */
	protected static final JSPScriptingVariableScope SCOPE_EDEFAULT = JSPScriptingVariableScope.NESTED_LITERAL;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected JSPScriptingVariableScope scope = SCOPE_EDEFAULT;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected JavaClass variableClass = null;
	/**
	 * The cached value of the '{@link #getDescriptions() <em>Descriptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptions()
	 * @generated
	 * @ordered
	 */
	protected EList descriptions = null;

	public JSPVariableImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return TaglibPackage.eINSTANCE.getJSPVariable();
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The name-given element provides the name for the scripting variable.
	 * 
	 * One of name-given or name-from-attribute is required.
	 */
	public String getNameGiven() {
		return nameGiven;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setNameGiven(String newNameGiven) {
		String oldNameGiven = nameGiven;
		nameGiven = newNameGiven;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TaglibPackage.JSP_VARIABLE__NAME_GIVEN, oldNameGiven, nameGiven));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The name of an attribute whose (translation-time) value will give the name of
	 * the variable.
	 */
	public String getNameFromAttribute() {
		return nameFromAttribute;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setNameFromAttribute(String newNameFromAttribute) {
		String oldNameFromAttribute = nameFromAttribute;
		nameFromAttribute = newNameFromAttribute;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TaglibPackage.JSP_VARIABLE__NAME_FROM_ATTRIBUTE, oldNameFromAttribute, nameFromAttribute));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The value of the declare element indicates whether the scripting variable is to 
	 * be defined or not. See TagExtraInfo for details.
	 * This element is optional and is the default is true.

	 */
	public boolean isDeclare() {
		return declare;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setDeclare(boolean newDeclare) {
		boolean oldDeclare = declare;
		declare = newDeclare;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TaglibPackage.JSP_VARIABLE__DECLARE, oldDeclare, declare));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public JSPScriptingVariableScope getScope() {
		return scope;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScope(JSPScriptingVariableScope newScope) {
		JSPScriptingVariableScope oldScope = scope;
		scope = newScope == null ? SCOPE_EDEFAULT : newScope;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TaglibPackage.JSP_VARIABLE__SCOPE, oldScope, scope));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public JavaClass getVariableClass() {
		if (variableClass != null && variableClass.eIsProxy()) {
			JavaClass oldVariableClass = variableClass;
			variableClass = (JavaClass)eResolveProxy((InternalEObject)variableClass);
			if (variableClass != oldVariableClass) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TaglibPackage.JSP_VARIABLE__VARIABLE_CLASS, oldVariableClass, variableClass));
			}
		}
		return variableClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass basicGetVariableClass() {
		return variableClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setVariableClass(JavaClass newVariableClass) {
		JavaClass oldVariableClass = variableClass;
		variableClass = newVariableClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TaglibPackage.JSP_VARIABLE__VARIABLE_CLASS, oldVariableClass, variableClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList(Description.class, this, TaglibPackage.JSP_VARIABLE__DESCRIPTIONS);
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
				case TaglibPackage.JSP_VARIABLE__DESCRIPTIONS:
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
			case TaglibPackage.JSP_VARIABLE__NAME_GIVEN:
				return getNameGiven();
			case TaglibPackage.JSP_VARIABLE__NAME_FROM_ATTRIBUTE:
				return getNameFromAttribute();
			case TaglibPackage.JSP_VARIABLE__DECLARE:
				return isDeclare() ? Boolean.TRUE : Boolean.FALSE;
			case TaglibPackage.JSP_VARIABLE__SCOPE:
				return getScope();
			case TaglibPackage.JSP_VARIABLE__VARIABLE_CLASS:
				if (resolve) return getVariableClass();
				return basicGetVariableClass();
			case TaglibPackage.JSP_VARIABLE__DESCRIPTIONS:
				return getDescriptions();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case TaglibPackage.JSP_VARIABLE__NAME_GIVEN:
				return NAME_GIVEN_EDEFAULT == null ? nameGiven != null : !NAME_GIVEN_EDEFAULT.equals(nameGiven);
			case TaglibPackage.JSP_VARIABLE__NAME_FROM_ATTRIBUTE:
				return NAME_FROM_ATTRIBUTE_EDEFAULT == null ? nameFromAttribute != null : !NAME_FROM_ATTRIBUTE_EDEFAULT.equals(nameFromAttribute);
			case TaglibPackage.JSP_VARIABLE__DECLARE:
				return declare != DECLARE_EDEFAULT;
			case TaglibPackage.JSP_VARIABLE__SCOPE:
				return scope != SCOPE_EDEFAULT;
			case TaglibPackage.JSP_VARIABLE__VARIABLE_CLASS:
				return variableClass != null;
			case TaglibPackage.JSP_VARIABLE__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case TaglibPackage.JSP_VARIABLE__NAME_GIVEN:
				setNameGiven((String)newValue);
				return;
			case TaglibPackage.JSP_VARIABLE__NAME_FROM_ATTRIBUTE:
				setNameFromAttribute((String)newValue);
				return;
			case TaglibPackage.JSP_VARIABLE__DECLARE:
				setDeclare(((Boolean)newValue).booleanValue());
				return;
			case TaglibPackage.JSP_VARIABLE__SCOPE:
				setScope((JSPScriptingVariableScope)newValue);
				return;
			case TaglibPackage.JSP_VARIABLE__VARIABLE_CLASS:
				setVariableClass((JavaClass)newValue);
				return;
			case TaglibPackage.JSP_VARIABLE__DESCRIPTIONS:
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
			case TaglibPackage.JSP_VARIABLE__NAME_GIVEN:
				setNameGiven(NAME_GIVEN_EDEFAULT);
				return;
			case TaglibPackage.JSP_VARIABLE__NAME_FROM_ATTRIBUTE:
				setNameFromAttribute(NAME_FROM_ATTRIBUTE_EDEFAULT);
				return;
			case TaglibPackage.JSP_VARIABLE__DECLARE:
				setDeclare(DECLARE_EDEFAULT);
				return;
			case TaglibPackage.JSP_VARIABLE__SCOPE:
				setScope(SCOPE_EDEFAULT);
				return;
			case TaglibPackage.JSP_VARIABLE__VARIABLE_CLASS:
				setVariableClass((JavaClass)null);
				return;
			case TaglibPackage.JSP_VARIABLE__DESCRIPTIONS:
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
		result.append(" (nameGiven: ");//$NON-NLS-1$
		result.append(nameGiven);
		result.append(", nameFromAttribute: ");//$NON-NLS-1$
		result.append(nameFromAttribute);
		result.append(", declare: ");//$NON-NLS-1$
		result.append(declare);
		result.append(", scope: ");//$NON-NLS-1$
		result.append(scope);
		result.append(')');
		return result.toString();
	}

}














