package org.eclipse.jem.internal.instantiation.impl;
/*******************************************************************************
 * Copyright (c)  2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: PTConditionalExpressionImpl.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:36:09 $ 
 */
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.jem.internal.instantiation.*;
import org.eclipse.jem.internal.instantiation.PTConditionalExpression;
import org.eclipse.jem.internal.instantiation.PTExpression;
import org.eclipse.jem.internal.instantiation.InstantiationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Conditional Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.instantiation.impl.PTConditionalExpressionImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.impl.PTConditionalExpressionImpl#getTrue <em>True</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.impl.PTConditionalExpressionImpl#getFalse <em>False</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PTConditionalExpressionImpl extends PTExpressionImpl implements PTConditionalExpression {
	/**
	 * The cached value of the '{@link #getCondition() <em>Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCondition()
	 * @generated
	 * @ordered
	 */
	protected PTExpression condition = null;

	/**
	 * The cached value of the '{@link #getTrue() <em>True</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrue()
	 * @generated
	 * @ordered
	 */
	protected PTExpression true_ = null;

	/**
	 * The cached value of the '{@link #getFalse() <em>False</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFalse()
	 * @generated
	 * @ordered
	 */
	protected PTExpression false_ = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PTConditionalExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return InstantiationPackage.eINSTANCE.getPTConditionalExpression();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PTExpression getCondition() {
		return condition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCondition(PTExpression newCondition, NotificationChain msgs) {
		PTExpression oldCondition = condition;
		condition = newCondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InstantiationPackage.PT_CONDITIONAL_EXPRESSION__CONDITION, oldCondition, newCondition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCondition(PTExpression newCondition) {
		if (newCondition != condition) {
			NotificationChain msgs = null;
			if (condition != null)
				msgs = ((InternalEObject)condition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InstantiationPackage.PT_CONDITIONAL_EXPRESSION__CONDITION, null, msgs);
			if (newCondition != null)
				msgs = ((InternalEObject)newCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InstantiationPackage.PT_CONDITIONAL_EXPRESSION__CONDITION, null, msgs);
			msgs = basicSetCondition(newCondition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InstantiationPackage.PT_CONDITIONAL_EXPRESSION__CONDITION, newCondition, newCondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PTExpression getTrue() {
		return true_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTrue(PTExpression newTrue, NotificationChain msgs) {
		PTExpression oldTrue = true_;
		true_ = newTrue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InstantiationPackage.PT_CONDITIONAL_EXPRESSION__TRUE, oldTrue, newTrue);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTrue(PTExpression newTrue) {
		if (newTrue != true_) {
			NotificationChain msgs = null;
			if (true_ != null)
				msgs = ((InternalEObject)true_).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InstantiationPackage.PT_CONDITIONAL_EXPRESSION__TRUE, null, msgs);
			if (newTrue != null)
				msgs = ((InternalEObject)newTrue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InstantiationPackage.PT_CONDITIONAL_EXPRESSION__TRUE, null, msgs);
			msgs = basicSetTrue(newTrue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InstantiationPackage.PT_CONDITIONAL_EXPRESSION__TRUE, newTrue, newTrue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PTExpression getFalse() {
		return false_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFalse(PTExpression newFalse, NotificationChain msgs) {
		PTExpression oldFalse = false_;
		false_ = newFalse;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InstantiationPackage.PT_CONDITIONAL_EXPRESSION__FALSE, oldFalse, newFalse);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFalse(PTExpression newFalse) {
		if (newFalse != false_) {
			NotificationChain msgs = null;
			if (false_ != null)
				msgs = ((InternalEObject)false_).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InstantiationPackage.PT_CONDITIONAL_EXPRESSION__FALSE, null, msgs);
			if (newFalse != null)
				msgs = ((InternalEObject)newFalse).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InstantiationPackage.PT_CONDITIONAL_EXPRESSION__FALSE, null, msgs);
			msgs = basicSetFalse(newFalse, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InstantiationPackage.PT_CONDITIONAL_EXPRESSION__FALSE, newFalse, newFalse));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case InstantiationPackage.PT_CONDITIONAL_EXPRESSION__CONDITION:
					return basicSetCondition(null, msgs);
				case InstantiationPackage.PT_CONDITIONAL_EXPRESSION__TRUE:
					return basicSetTrue(null, msgs);
				case InstantiationPackage.PT_CONDITIONAL_EXPRESSION__FALSE:
					return basicSetFalse(null, msgs);
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
			case InstantiationPackage.PT_CONDITIONAL_EXPRESSION__CONDITION:
				return getCondition();
			case InstantiationPackage.PT_CONDITIONAL_EXPRESSION__TRUE:
				return getTrue();
			case InstantiationPackage.PT_CONDITIONAL_EXPRESSION__FALSE:
				return getFalse();
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
			case InstantiationPackage.PT_CONDITIONAL_EXPRESSION__CONDITION:
				setCondition((PTExpression)newValue);
				return;
			case InstantiationPackage.PT_CONDITIONAL_EXPRESSION__TRUE:
				setTrue((PTExpression)newValue);
				return;
			case InstantiationPackage.PT_CONDITIONAL_EXPRESSION__FALSE:
				setFalse((PTExpression)newValue);
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
			case InstantiationPackage.PT_CONDITIONAL_EXPRESSION__CONDITION:
				setCondition((PTExpression)null);
				return;
			case InstantiationPackage.PT_CONDITIONAL_EXPRESSION__TRUE:
				setTrue((PTExpression)null);
				return;
			case InstantiationPackage.PT_CONDITIONAL_EXPRESSION__FALSE:
				setFalse((PTExpression)null);
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
			case InstantiationPackage.PT_CONDITIONAL_EXPRESSION__CONDITION:
				return condition != null;
			case InstantiationPackage.PT_CONDITIONAL_EXPRESSION__TRUE:
				return true_ != null;
			case InstantiationPackage.PT_CONDITIONAL_EXPRESSION__FALSE:
				return false_ != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.impl.ExpressionImpl#accept0(org.eclipse.jem.internal.instantiation.ParseVisitor)
	 */
	protected void accept0(ParseVisitor visitor) {
		boolean visitChildren = visitor.visit(this);
		if (visitChildren) {
			// visit children in normal left to right reading order
			acceptChild(visitor, getCondition());
			acceptChild(visitor, getTrue());
			acceptChild(visitor, getFalse());
		}
		visitor.endVisit(this);
	}	

} //ConditionalExpressionImpl
