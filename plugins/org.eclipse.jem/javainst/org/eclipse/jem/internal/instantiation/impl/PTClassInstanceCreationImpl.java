package org.eclipse.jem.internal.instantiation.impl;
/*******************************************************************************
 * Copyright (c)  2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: PTClassInstanceCreationImpl.java,v $
 *  $Revision: 1.1 $  $Date: 2004/01/23 22:53:22 $ 
 */
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

import org.eclipse.jem.internal.instantiation.*;
import org.eclipse.jem.internal.instantiation.PTClassInstanceCreation;
import org.eclipse.jem.internal.instantiation.PTExpression;
import org.eclipse.jem.internal.instantiation.InstantiationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Class Instance Creation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.instantiation.impl.PTClassInstanceCreationImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.impl.PTClassInstanceCreationImpl#getArguments <em>Arguments</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PTClassInstanceCreationImpl extends PTExpressionImpl implements PTClassInstanceCreation {
	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected String type = TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getArguments() <em>Arguments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArguments()
	 * @generated
	 * @ordered
	 */
	protected EList arguments = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PTClassInstanceCreationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return InstantiationPackage.eINSTANCE.getPTClassInstanceCreation();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InstantiationPackage.PT_CLASS_INSTANCE_CREATION__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getArguments() {
		if (arguments == null) {
			arguments = new EObjectContainmentEList(PTExpression.class, this, InstantiationPackage.PT_CLASS_INSTANCE_CREATION__ARGUMENTS);
		}
		return arguments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case InstantiationPackage.PT_CLASS_INSTANCE_CREATION__ARGUMENTS:
					return ((InternalEList)getArguments()).basicRemove(otherEnd, msgs);
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
			case InstantiationPackage.PT_CLASS_INSTANCE_CREATION__TYPE:
				return getType();
			case InstantiationPackage.PT_CLASS_INSTANCE_CREATION__ARGUMENTS:
				return getArguments();
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
			case InstantiationPackage.PT_CLASS_INSTANCE_CREATION__TYPE:
				setType((String)newValue);
				return;
			case InstantiationPackage.PT_CLASS_INSTANCE_CREATION__ARGUMENTS:
				getArguments().clear();
				getArguments().addAll((Collection)newValue);
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
			case InstantiationPackage.PT_CLASS_INSTANCE_CREATION__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case InstantiationPackage.PT_CLASS_INSTANCE_CREATION__ARGUMENTS:
				getArguments().clear();
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
			case InstantiationPackage.PT_CLASS_INSTANCE_CREATION__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case InstantiationPackage.PT_CLASS_INSTANCE_CREATION__ARGUMENTS:
				return arguments != null && !arguments.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

	public String toString() {
		return super.toString();	// Really want toString to be final at PTExpression, but EMF doesn't allow it.
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.impl.ExpressionImpl#accept0(org.eclipse.jem.internal.instantiation.ParseVisitor)
	 */
	protected void accept0(ParseVisitor visitor) {
		boolean visitChildren = visitor.visit(this);
		if (visitChildren) {
			// visit children in normal left to right reading order
			acceptChildren(visitor, arguments);
		}
		visitor.endVisit(this);
	}
} //ClassInstanceCreationImpl
