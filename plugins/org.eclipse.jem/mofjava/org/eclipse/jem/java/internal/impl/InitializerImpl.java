/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.java.internal.impl;
/*


 */
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.jem.java.*;
/**
 * @generated
 */
public class InitializerImpl extends EObjectImpl implements Initializer {

	/**
	 * The default value of the '{@link #getIsStatic() <em>Is Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsStatic()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_STATIC_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected Boolean isStatic = IS_STATIC_EDEFAULT;
	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected Block source = null;

	protected InitializerImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return JavaRefPackage.Literals.INITIALIZER;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public Boolean getIsStatic() {
		return isStatic;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setIsStatic(Boolean newIsStatic) {
		Boolean oldIsStatic = isStatic;
		isStatic = newIsStatic;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaRefPackage.INITIALIZER__IS_STATIC, oldIsStatic, isStatic));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public JavaClass getJavaClass() {
		if (eContainerFeatureID != JavaRefPackage.INITIALIZER__JAVA_CLASS) return null;
		return (JavaClass)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetJavaClass(JavaClass newJavaClass, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newJavaClass, JavaRefPackage.INITIALIZER__JAVA_CLASS, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJavaClass(JavaClass newJavaClass) {
		if (newJavaClass != eInternalContainer() || (eContainerFeatureID != JavaRefPackage.INITIALIZER__JAVA_CLASS && newJavaClass != null)) {
			if (EcoreUtil.isAncestor(this, newJavaClass))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newJavaClass != null)
				msgs = ((InternalEObject)newJavaClass).eInverseAdd(this, JavaRefPackage.JAVA_CLASS__INITIALIZERS, JavaClass.class, msgs);
			msgs = basicSetJavaClass(newJavaClass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaRefPackage.INITIALIZER__JAVA_CLASS, newJavaClass, newJavaClass));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public Block getSource() {
		if (source != null && source.eIsProxy()) {
			InternalEObject oldSource = (InternalEObject)source;
			source = (Block)eResolveProxy(oldSource);
			if (source != oldSource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, JavaRefPackage.INITIALIZER__SOURCE, oldSource, source));
			}
		}
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Block basicGetSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(Block newSource) {
		Block oldSource = source;
		source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaRefPackage.INITIALIZER__SOURCE, oldSource, source));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JavaRefPackage.INITIALIZER__JAVA_CLASS:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetJavaClass((JavaClass)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JavaRefPackage.INITIALIZER__JAVA_CLASS:
				return basicSetJavaClass(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID) {
			case JavaRefPackage.INITIALIZER__JAVA_CLASS:
				return eInternalContainer().eInverseRemove(this, JavaRefPackage.JAVA_CLASS__INITIALIZERS, JavaClass.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case JavaRefPackage.INITIALIZER__IS_STATIC:
				return getIsStatic();
			case JavaRefPackage.INITIALIZER__JAVA_CLASS:
				return getJavaClass();
			case JavaRefPackage.INITIALIZER__SOURCE:
				if (resolve) return getSource();
				return basicGetSource();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case JavaRefPackage.INITIALIZER__IS_STATIC:
				setIsStatic((Boolean)newValue);
				return;
			case JavaRefPackage.INITIALIZER__JAVA_CLASS:
				setJavaClass((JavaClass)newValue);
				return;
			case JavaRefPackage.INITIALIZER__SOURCE:
				setSource((Block)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case JavaRefPackage.INITIALIZER__IS_STATIC:
				setIsStatic(IS_STATIC_EDEFAULT);
				return;
			case JavaRefPackage.INITIALIZER__JAVA_CLASS:
				setJavaClass((JavaClass)null);
				return;
			case JavaRefPackage.INITIALIZER__SOURCE:
				setSource((Block)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case JavaRefPackage.INITIALIZER__IS_STATIC:
				return IS_STATIC_EDEFAULT == null ? isStatic != null : !IS_STATIC_EDEFAULT.equals(isStatic);
			case JavaRefPackage.INITIALIZER__JAVA_CLASS:
				return getJavaClass() != null;
			case JavaRefPackage.INITIALIZER__SOURCE:
				return source != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (isStatic: ");
		result.append(isStatic);
		result.append(')');
		return result.toString();
	}

}





