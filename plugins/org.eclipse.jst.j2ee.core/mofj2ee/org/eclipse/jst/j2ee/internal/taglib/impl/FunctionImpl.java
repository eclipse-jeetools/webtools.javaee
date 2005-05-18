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
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.internal.common.impl.DescriptionGroupImpl;
import org.eclipse.jst.j2ee.internal.taglib.Function;
import org.eclipse.jst.j2ee.internal.taglib.TaglibPackage;
import org.eclipse.jst.j2ee.internal.taglib.TldExtension;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Function</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.internal.taglib.impl.FunctionImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.taglib.impl.FunctionImpl#getSignature <em>Signature</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.taglib.impl.FunctionImpl#getExample <em>Example</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.taglib.impl.FunctionImpl#getFunctionClass <em>Function Class</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.taglib.impl.FunctionImpl#getFunctionExtensions <em>Function Extensions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FunctionImpl extends DescriptionGroupImpl implements Function {
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
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getSignature() <em>Signature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSignature()
	 * @generated
	 * @ordered
	 */
	protected static final String SIGNATURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSignature() <em>Signature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSignature()
	 * @generated
	 * @ordered
	 */
	protected String signature = SIGNATURE_EDEFAULT;

	/**
	 * The default value of the '{@link #getExample() <em>Example</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExample()
	 * @generated
	 * @ordered
	 */
	protected static final String EXAMPLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExample() <em>Example</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExample()
	 * @generated
	 * @ordered
	 */
	protected String example = EXAMPLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getFunctionClass() <em>Function Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFunctionClass()
	 * @generated
	 * @ordered
	 */
	protected JavaClass functionClass = null;

	/**
	 * The cached value of the '{@link #getFunctionExtensions() <em>Function Extensions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFunctionExtensions()
	 * @generated
	 * @ordered
	 */
	protected EList functionExtensions = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FunctionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return TaglibPackage.eINSTANCE.getFunction();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TaglibPackage.FUNCTION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSignature(String newSignature) {
		String oldSignature = signature;
		signature = newSignature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TaglibPackage.FUNCTION__SIGNATURE, oldSignature, signature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getExample() {
		return example;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExample(String newExample) {
		String oldExample = example;
		example = newExample;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TaglibPackage.FUNCTION__EXAMPLE, oldExample, example));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass getFunctionClass() {
		if (functionClass != null && functionClass.eIsProxy()) {
			JavaClass oldFunctionClass = functionClass;
			functionClass = (JavaClass)eResolveProxy((InternalEObject)functionClass);
			if (functionClass != oldFunctionClass) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TaglibPackage.FUNCTION__FUNCTION_CLASS, oldFunctionClass, functionClass));
			}
		}
		return functionClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass basicGetFunctionClass() {
		return functionClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFunctionClass(JavaClass newFunctionClass) {
		JavaClass oldFunctionClass = functionClass;
		functionClass = newFunctionClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TaglibPackage.FUNCTION__FUNCTION_CLASS, oldFunctionClass, functionClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getFunctionExtensions() {
		if (functionExtensions == null) {
			functionExtensions = new EObjectContainmentEList(TldExtension.class, this, TaglibPackage.FUNCTION__FUNCTION_EXTENSIONS);
		}
		return functionExtensions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case TaglibPackage.FUNCTION__ICONS:
					return ((InternalEList)getIcons()).basicRemove(otherEnd, msgs);
				case TaglibPackage.FUNCTION__DISPLAY_NAMES:
					return ((InternalEList)getDisplayNames()).basicRemove(otherEnd, msgs);
				case TaglibPackage.FUNCTION__DESCRIPTIONS:
					return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
				case TaglibPackage.FUNCTION__FUNCTION_EXTENSIONS:
					return ((InternalEList)getFunctionExtensions()).basicRemove(otherEnd, msgs);
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
			case TaglibPackage.FUNCTION__ICONS:
				return getIcons();
			case TaglibPackage.FUNCTION__DISPLAY_NAMES:
				return getDisplayNames();
			case TaglibPackage.FUNCTION__DESCRIPTIONS:
				return getDescriptions();
			case TaglibPackage.FUNCTION__NAME:
				return getName();
			case TaglibPackage.FUNCTION__SIGNATURE:
				return getSignature();
			case TaglibPackage.FUNCTION__EXAMPLE:
				return getExample();
			case TaglibPackage.FUNCTION__FUNCTION_CLASS:
				if (resolve) return getFunctionClass();
				return basicGetFunctionClass();
			case TaglibPackage.FUNCTION__FUNCTION_EXTENSIONS:
				return getFunctionExtensions();
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
			case TaglibPackage.FUNCTION__ICONS:
				getIcons().clear();
				getIcons().addAll((Collection)newValue);
				return;
			case TaglibPackage.FUNCTION__DISPLAY_NAMES:
				getDisplayNames().clear();
				getDisplayNames().addAll((Collection)newValue);
				return;
			case TaglibPackage.FUNCTION__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
				return;
			case TaglibPackage.FUNCTION__NAME:
				setName((String)newValue);
				return;
			case TaglibPackage.FUNCTION__SIGNATURE:
				setSignature((String)newValue);
				return;
			case TaglibPackage.FUNCTION__EXAMPLE:
				setExample((String)newValue);
				return;
			case TaglibPackage.FUNCTION__FUNCTION_CLASS:
				setFunctionClass((JavaClass)newValue);
				return;
			case TaglibPackage.FUNCTION__FUNCTION_EXTENSIONS:
				getFunctionExtensions().clear();
				getFunctionExtensions().addAll((Collection)newValue);
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
			case TaglibPackage.FUNCTION__ICONS:
				getIcons().clear();
				return;
			case TaglibPackage.FUNCTION__DISPLAY_NAMES:
				getDisplayNames().clear();
				return;
			case TaglibPackage.FUNCTION__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case TaglibPackage.FUNCTION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case TaglibPackage.FUNCTION__SIGNATURE:
				setSignature(SIGNATURE_EDEFAULT);
				return;
			case TaglibPackage.FUNCTION__EXAMPLE:
				setExample(EXAMPLE_EDEFAULT);
				return;
			case TaglibPackage.FUNCTION__FUNCTION_CLASS:
				setFunctionClass((JavaClass)null);
				return;
			case TaglibPackage.FUNCTION__FUNCTION_EXTENSIONS:
				getFunctionExtensions().clear();
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
			case TaglibPackage.FUNCTION__ICONS:
				return icons != null && !icons.isEmpty();
			case TaglibPackage.FUNCTION__DISPLAY_NAMES:
				return displayNames != null && !displayNames.isEmpty();
			case TaglibPackage.FUNCTION__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case TaglibPackage.FUNCTION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case TaglibPackage.FUNCTION__SIGNATURE:
				return SIGNATURE_EDEFAULT == null ? signature != null : !SIGNATURE_EDEFAULT.equals(signature);
			case TaglibPackage.FUNCTION__EXAMPLE:
				return EXAMPLE_EDEFAULT == null ? example != null : !EXAMPLE_EDEFAULT.equals(example);
			case TaglibPackage.FUNCTION__FUNCTION_CLASS:
				return functionClass != null;
			case TaglibPackage.FUNCTION__FUNCTION_EXTENSIONS:
				return functionExtensions != null && !functionExtensions.isEmpty();
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
		result.append(" (name: ");//$NON-NLS-1$
		result.append(name);
		result.append(", signature: ");//$NON-NLS-1$
		result.append(signature);
		result.append(", example: ");//$NON-NLS-1$
		result.append(example);
		result.append(')');
		return result.toString();
	}

} //FunctionImpl
