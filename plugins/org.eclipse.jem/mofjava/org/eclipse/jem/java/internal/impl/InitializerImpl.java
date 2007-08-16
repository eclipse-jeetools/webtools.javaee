/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.java.internal.impl;
/*
 *  $RCSfile: InitializerImpl.java,v $
 *  $Revision: 1.1 $  $Date: 2005/09/14 23:30:32 $ 
 */
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.jem.java.Block;
import org.eclipse.jem.java.Initializer;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaRefPackage;
/**
 * @generated
 */
public class InitializerImpl extends EObjectImpl implements Initializer{

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
		return JavaRefPackage.eINSTANCE.getInitializer();
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
		return (JavaClass)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJavaClass(JavaClass newJavaClass) {
		if (newJavaClass != eContainer || (eContainerFeatureID != JavaRefPackage.INITIALIZER__JAVA_CLASS && newJavaClass != null)) {
			if (EcoreUtil.isAncestor(this, newJavaClass))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newJavaClass != null)
				msgs = ((InternalEObject)newJavaClass).eInverseAdd(this, JavaRefPackage.JAVA_CLASS__INITIALIZERS, JavaClass.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newJavaClass, JavaRefPackage.INITIALIZER__JAVA_CLASS, msgs);
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
			Block oldSource = source;
			source = (Block)eResolveProxy((InternalEObject)source);
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
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case JavaRefPackage.INITIALIZER__JAVA_CLASS:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, JavaRefPackage.INITIALIZER__JAVA_CLASS, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case JavaRefPackage.INITIALIZER__JAVA_CLASS:
					return eBasicSetContainer(null, JavaRefPackage.INITIALIZER__JAVA_CLASS, msgs);
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
	public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
		if (eContainerFeatureID >= 0) {
			switch (eContainerFeatureID) {
				case JavaRefPackage.INITIALIZER__JAVA_CLASS:
					return eContainer.eInverseRemove(this, JavaRefPackage.JAVA_CLASS__INITIALIZERS, JavaClass.class, msgs);
				default:
					return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JavaRefPackage.INITIALIZER__IS_STATIC:
				return getIsStatic();
			case JavaRefPackage.INITIALIZER__JAVA_CLASS:
				return getJavaClass();
			case JavaRefPackage.INITIALIZER__SOURCE:
				if (resolve) return getSource();
				return basicGetSource();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JavaRefPackage.INITIALIZER__IS_STATIC:
				return IS_STATIC_EDEFAULT == null ? isStatic != null : !IS_STATIC_EDEFAULT.equals(isStatic);
			case JavaRefPackage.INITIALIZER__JAVA_CLASS:
				return getJavaClass() != null;
			case JavaRefPackage.INITIALIZER__SOURCE:
				return source != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
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
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
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
		eDynamicUnset(eFeature);
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





