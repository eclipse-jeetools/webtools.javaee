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
 *  $RCSfile: PTInfixExpressionImpl.java,v $
 *  $Revision: 1.2 $  $Date: 2004/03/11 01:48:01 $ 
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
import org.eclipse.jem.internal.instantiation.PTExpression;
import org.eclipse.jem.internal.instantiation.PTInfixExpression;
import org.eclipse.jem.internal.instantiation.PTInfixOperator;
import org.eclipse.jem.internal.instantiation.InstantiationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Infix Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.instantiation.impl.PTInfixExpressionImpl#getLeftOperand <em>Left Operand</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.impl.PTInfixExpressionImpl#getOperator <em>Operator</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.impl.PTInfixExpressionImpl#getRightOperand <em>Right Operand</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.impl.PTInfixExpressionImpl#getExtendedOperands <em>Extended Operands</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PTInfixExpressionImpl extends PTExpressionImpl implements PTInfixExpression {
	/**
	 * The cached value of the '{@link #getLeftOperand() <em>Left Operand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeftOperand()
	 * @generated
	 * @ordered
	 */
	protected PTExpression leftOperand = null;

	/**
	 * The default value of the '{@link #getOperator() <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperator()
	 * @generated
	 * @ordered
	 */
	protected static final PTInfixOperator OPERATOR_EDEFAULT = PTInfixOperator.TIMES_LITERAL;

	/**
	 * The cached value of the '{@link #getOperator() <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperator()
	 * @generated
	 * @ordered
	 */
	protected PTInfixOperator operator = OPERATOR_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRightOperand() <em>Right Operand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRightOperand()
	 * @generated
	 * @ordered
	 */
	protected PTExpression rightOperand = null;

	/**
	 * The cached value of the '{@link #getExtendedOperands() <em>Extended Operands</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtendedOperands()
	 * @generated
	 * @ordered
	 */
	protected EList extendedOperands = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PTInfixExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return InstantiationPackage.eINSTANCE.getPTInfixExpression();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PTExpression getLeftOperand() {
		return leftOperand;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLeftOperand(PTExpression newLeftOperand, NotificationChain msgs) {
		PTExpression oldLeftOperand = leftOperand;
		leftOperand = newLeftOperand;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InstantiationPackage.PT_INFIX_EXPRESSION__LEFT_OPERAND, oldLeftOperand, newLeftOperand);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLeftOperand(PTExpression newLeftOperand) {
		if (newLeftOperand != leftOperand) {
			NotificationChain msgs = null;
			if (leftOperand != null)
				msgs = ((InternalEObject)leftOperand).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InstantiationPackage.PT_INFIX_EXPRESSION__LEFT_OPERAND, null, msgs);
			if (newLeftOperand != null)
				msgs = ((InternalEObject)newLeftOperand).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InstantiationPackage.PT_INFIX_EXPRESSION__LEFT_OPERAND, null, msgs);
			msgs = basicSetLeftOperand(newLeftOperand, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InstantiationPackage.PT_INFIX_EXPRESSION__LEFT_OPERAND, newLeftOperand, newLeftOperand));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PTInfixOperator getOperator() {
		return operator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperator(PTInfixOperator newOperator) {
		PTInfixOperator oldOperator = operator;
		operator = newOperator == null ? OPERATOR_EDEFAULT : newOperator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InstantiationPackage.PT_INFIX_EXPRESSION__OPERATOR, oldOperator, operator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PTExpression getRightOperand() {
		return rightOperand;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRightOperand(PTExpression newRightOperand, NotificationChain msgs) {
		PTExpression oldRightOperand = rightOperand;
		rightOperand = newRightOperand;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InstantiationPackage.PT_INFIX_EXPRESSION__RIGHT_OPERAND, oldRightOperand, newRightOperand);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRightOperand(PTExpression newRightOperand) {
		if (newRightOperand != rightOperand) {
			NotificationChain msgs = null;
			if (rightOperand != null)
				msgs = ((InternalEObject)rightOperand).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InstantiationPackage.PT_INFIX_EXPRESSION__RIGHT_OPERAND, null, msgs);
			if (newRightOperand != null)
				msgs = ((InternalEObject)newRightOperand).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InstantiationPackage.PT_INFIX_EXPRESSION__RIGHT_OPERAND, null, msgs);
			msgs = basicSetRightOperand(newRightOperand, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InstantiationPackage.PT_INFIX_EXPRESSION__RIGHT_OPERAND, newRightOperand, newRightOperand));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getExtendedOperands() {
		if (extendedOperands == null) {
			extendedOperands = new EObjectContainmentEList(PTExpression.class, this, InstantiationPackage.PT_INFIX_EXPRESSION__EXTENDED_OPERANDS);
		}
		return extendedOperands;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case InstantiationPackage.PT_INFIX_EXPRESSION__LEFT_OPERAND:
					return basicSetLeftOperand(null, msgs);
				case InstantiationPackage.PT_INFIX_EXPRESSION__RIGHT_OPERAND:
					return basicSetRightOperand(null, msgs);
				case InstantiationPackage.PT_INFIX_EXPRESSION__EXTENDED_OPERANDS:
					return ((InternalEList)getExtendedOperands()).basicRemove(otherEnd, msgs);
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
			case InstantiationPackage.PT_INFIX_EXPRESSION__LEFT_OPERAND:
				return getLeftOperand();
			case InstantiationPackage.PT_INFIX_EXPRESSION__OPERATOR:
				return getOperator();
			case InstantiationPackage.PT_INFIX_EXPRESSION__RIGHT_OPERAND:
				return getRightOperand();
			case InstantiationPackage.PT_INFIX_EXPRESSION__EXTENDED_OPERANDS:
				return getExtendedOperands();
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
			case InstantiationPackage.PT_INFIX_EXPRESSION__LEFT_OPERAND:
				setLeftOperand((PTExpression)newValue);
				return;
			case InstantiationPackage.PT_INFIX_EXPRESSION__OPERATOR:
				setOperator((PTInfixOperator)newValue);
				return;
			case InstantiationPackage.PT_INFIX_EXPRESSION__RIGHT_OPERAND:
				setRightOperand((PTExpression)newValue);
				return;
			case InstantiationPackage.PT_INFIX_EXPRESSION__EXTENDED_OPERANDS:
				getExtendedOperands().clear();
				getExtendedOperands().addAll((Collection)newValue);
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
			case InstantiationPackage.PT_INFIX_EXPRESSION__LEFT_OPERAND:
				setLeftOperand((PTExpression)null);
				return;
			case InstantiationPackage.PT_INFIX_EXPRESSION__OPERATOR:
				setOperator(OPERATOR_EDEFAULT);
				return;
			case InstantiationPackage.PT_INFIX_EXPRESSION__RIGHT_OPERAND:
				setRightOperand((PTExpression)null);
				return;
			case InstantiationPackage.PT_INFIX_EXPRESSION__EXTENDED_OPERANDS:
				getExtendedOperands().clear();
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
			case InstantiationPackage.PT_INFIX_EXPRESSION__LEFT_OPERAND:
				return leftOperand != null;
			case InstantiationPackage.PT_INFIX_EXPRESSION__OPERATOR:
				return operator != OPERATOR_EDEFAULT;
			case InstantiationPackage.PT_INFIX_EXPRESSION__RIGHT_OPERAND:
				return rightOperand != null;
			case InstantiationPackage.PT_INFIX_EXPRESSION__EXTENDED_OPERANDS:
				return extendedOperands != null && !extendedOperands.isEmpty();
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
			acceptChild(visitor, getLeftOperand());
			acceptChild(visitor, getRightOperand());
			acceptChildren(visitor, extendedOperands);
		}
		visitor.endVisit(this);
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.PTInfixExpression#asCompressedExpression()
	 */
	public PTExpression asCompressedExpression() {
		// If no left and no right
		if(getLeftOperand() == null && getRightOperand() != null){
			// no extends so just use the right operand
			if(getExtendedOperands().size() == 0){
				return getRightOperand();
			} else {
				// The right becomes the new left
				setLeftOperand(getRightOperand());
				// The first operand becomes the right one
				setRightOperand((PTExpression) getExtendedOperands().get(0));
				getExtendedOperands().remove(1);
				return this;
			}
		} else if (getRightOperand() == null && getLeftOperand() != null){
			// no extends so just use the left operand
			if(getExtendedOperands().size() == 0){
				return getLeftOperand();
			} else {
				// The right becomes the first extended
				setRightOperand((PTExpression)getExtendedOperands().get(1));
				getExtendedOperands().remove(1);
				return this;
			}			
		}	
		return this;
	}
} //InfixExpressionImpl
