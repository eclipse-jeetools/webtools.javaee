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
 *  $RCSfile: ConditionalExpressionImpl.java,v $
 *  $Revision: 1.1 $  $Date: 2004/01/19 22:50:15 $ 
 */
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.jem.internal.instantiation.*;
import org.eclipse.jem.internal.instantiation.ConditionalExpression;
import org.eclipse.jem.internal.instantiation.Expression;
import org.eclipse.jem.internal.instantiation.InstantiationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Conditional Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.instantiation.impl.ConditionalExpressionImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.impl.ConditionalExpressionImpl#getTrue <em>True</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.impl.ConditionalExpressionImpl#getFalse <em>False</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConditionalExpressionImpl extends ExpressionImpl implements ConditionalExpression {
	/**
	 * The cached value of the '{@link #getCondition() <em>Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCondition()
	 * @generated
	 * @ordered
	 */
	protected Expression condition = null;

	/**
	 * The cached value of the '{@link #getTrue() <em>True</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrue()
	 * @generated
	 * @ordered
	 */
	protected Expression true_ = null;

	/**
	 * The cached value of the '{@link #getFalse() <em>False</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFalse()
	 * @generated
	 * @ordered
	 */
	protected Expression false_ = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConditionalExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return InstantiationPackage.eINSTANCE.getConditionalExpression();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getCondition() {
		return condition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCondition(Expression newCondition, NotificationChain msgs) {
		Expression oldCondition = condition;
		condition = newCondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InstantiationPackage.CONDITIONAL_EXPRESSION__CONDITION, oldCondition, newCondition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCondition(Expression newCondition) {
		if (newCondition != condition) {
			NotificationChain msgs = null;
			if (condition != null)
				msgs = ((InternalEObject)condition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InstantiationPackage.CONDITIONAL_EXPRESSION__CONDITION, null, msgs);
			if (newCondition != null)
				msgs = ((InternalEObject)newCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InstantiationPackage.CONDITIONAL_EXPRESSION__CONDITION, null, msgs);
			msgs = basicSetCondition(newCondition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InstantiationPackage.CONDITIONAL_EXPRESSION__CONDITION, newCondition, newCondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getTrue() {
		return true_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTrue(Expression newTrue, NotificationChain msgs) {
		Expression oldTrue = true_;
		true_ = newTrue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InstantiationPackage.CONDITIONAL_EXPRESSION__TRUE, oldTrue, newTrue);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTrue(Expression newTrue) {
		if (newTrue != true_) {
			NotificationChain msgs = null;
			if (true_ != null)
				msgs = ((InternalEObject)true_).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InstantiationPackage.CONDITIONAL_EXPRESSION__TRUE, null, msgs);
			if (newTrue != null)
				msgs = ((InternalEObject)newTrue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InstantiationPackage.CONDITIONAL_EXPRESSION__TRUE, null, msgs);
			msgs = basicSetTrue(newTrue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InstantiationPackage.CONDITIONAL_EXPRESSION__TRUE, newTrue, newTrue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getFalse() {
		return false_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFalse(Expression newFalse, NotificationChain msgs) {
		Expression oldFalse = false_;
		false_ = newFalse;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InstantiationPackage.CONDITIONAL_EXPRESSION__FALSE, oldFalse, newFalse);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFalse(Expression newFalse) {
		if (newFalse != false_) {
			NotificationChain msgs = null;
			if (false_ != null)
				msgs = ((InternalEObject)false_).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InstantiationPackage.CONDITIONAL_EXPRESSION__FALSE, null, msgs);
			if (newFalse != null)
				msgs = ((InternalEObject)newFalse).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InstantiationPackage.CONDITIONAL_EXPRESSION__FALSE, null, msgs);
			msgs = basicSetFalse(newFalse, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InstantiationPackage.CONDITIONAL_EXPRESSION__FALSE, newFalse, newFalse));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case InstantiationPackage.CONDITIONAL_EXPRESSION__CONDITION:
					return basicSetCondition(null, msgs);
				case InstantiationPackage.CONDITIONAL_EXPRESSION__TRUE:
					return basicSetTrue(null, msgs);
				case InstantiationPackage.CONDITIONAL_EXPRESSION__FALSE:
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
			case InstantiationPackage.CONDITIONAL_EXPRESSION__CONDITION:
				return getCondition();
			case InstantiationPackage.CONDITIONAL_EXPRESSION__TRUE:
				return getTrue();
			case InstantiationPackage.CONDITIONAL_EXPRESSION__FALSE:
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
			case InstantiationPackage.CONDITIONAL_EXPRESSION__CONDITION:
				setCondition((Expression)newValue);
				return;
			case InstantiationPackage.CONDITIONAL_EXPRESSION__TRUE:
				setTrue((Expression)newValue);
				return;
			case InstantiationPackage.CONDITIONAL_EXPRESSION__FALSE:
				setFalse((Expression)newValue);
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
			case InstantiationPackage.CONDITIONAL_EXPRESSION__CONDITION:
				setCondition((Expression)null);
				return;
			case InstantiationPackage.CONDITIONAL_EXPRESSION__TRUE:
				setTrue((Expression)null);
				return;
			case InstantiationPackage.CONDITIONAL_EXPRESSION__FALSE:
				setFalse((Expression)null);
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
			case InstantiationPackage.CONDITIONAL_EXPRESSION__CONDITION:
				return condition != null;
			case InstantiationPackage.CONDITIONAL_EXPRESSION__TRUE:
				return true_ != null;
			case InstantiationPackage.CONDITIONAL_EXPRESSION__FALSE:
				return false_ != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if (getCondition() == null || getTrue() == null || getFalse() == null)
			return super.toString();
		StringBuffer buf = new StringBuffer(100);
		buf.append(getCondition().toString());
		buf.append(" ? ");
		buf.append(getTrue().toString());
		buf.append(" : ");
		buf.append(getFalse().toString());
		return buf.toString();
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
