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
 *  $RCSfile: InfixExpressionImpl.java,v $
 *  $Revision: 1.1 $  $Date: 2004/01/19 22:50:15 $ 
 */
import java.util.Collection;
import java.util.List;

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
import org.eclipse.jem.internal.instantiation.Expression;
import org.eclipse.jem.internal.instantiation.InfixExpression;
import org.eclipse.jem.internal.instantiation.InfixOperator;
import org.eclipse.jem.internal.instantiation.InstantiationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Infix Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.instantiation.impl.InfixExpressionImpl#getLeftOperand <em>Left Operand</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.impl.InfixExpressionImpl#getOperator <em>Operator</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.impl.InfixExpressionImpl#getRightOperand <em>Right Operand</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.impl.InfixExpressionImpl#getExtendedOperands <em>Extended Operands</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InfixExpressionImpl extends ExpressionImpl implements InfixExpression {
	/**
	 * The cached value of the '{@link #getLeftOperand() <em>Left Operand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeftOperand()
	 * @generated
	 * @ordered
	 */
	protected Expression leftOperand = null;

	/**
	 * The default value of the '{@link #getOperator() <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperator()
	 * @generated
	 * @ordered
	 */
	protected static final InfixOperator OPERATOR_EDEFAULT = InfixOperator.TIMES_LITERAL;

	/**
	 * The cached value of the '{@link #getOperator() <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperator()
	 * @generated
	 * @ordered
	 */
	protected InfixOperator operator = OPERATOR_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRightOperand() <em>Right Operand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRightOperand()
	 * @generated
	 * @ordered
	 */
	protected Expression rightOperand = null;

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
	protected InfixExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return InstantiationPackage.eINSTANCE.getInfixExpression();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getLeftOperand() {
		return leftOperand;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLeftOperand(Expression newLeftOperand, NotificationChain msgs) {
		Expression oldLeftOperand = leftOperand;
		leftOperand = newLeftOperand;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InstantiationPackage.INFIX_EXPRESSION__LEFT_OPERAND, oldLeftOperand, newLeftOperand);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLeftOperand(Expression newLeftOperand) {
		if (newLeftOperand != leftOperand) {
			NotificationChain msgs = null;
			if (leftOperand != null)
				msgs = ((InternalEObject)leftOperand).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InstantiationPackage.INFIX_EXPRESSION__LEFT_OPERAND, null, msgs);
			if (newLeftOperand != null)
				msgs = ((InternalEObject)newLeftOperand).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InstantiationPackage.INFIX_EXPRESSION__LEFT_OPERAND, null, msgs);
			msgs = basicSetLeftOperand(newLeftOperand, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InstantiationPackage.INFIX_EXPRESSION__LEFT_OPERAND, newLeftOperand, newLeftOperand));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InfixOperator getOperator() {
		return operator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperator(InfixOperator newOperator) {
		InfixOperator oldOperator = operator;
		operator = newOperator == null ? OPERATOR_EDEFAULT : newOperator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InstantiationPackage.INFIX_EXPRESSION__OPERATOR, oldOperator, operator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getRightOperand() {
		return rightOperand;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRightOperand(Expression newRightOperand, NotificationChain msgs) {
		Expression oldRightOperand = rightOperand;
		rightOperand = newRightOperand;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InstantiationPackage.INFIX_EXPRESSION__RIGHT_OPERAND, oldRightOperand, newRightOperand);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRightOperand(Expression newRightOperand) {
		if (newRightOperand != rightOperand) {
			NotificationChain msgs = null;
			if (rightOperand != null)
				msgs = ((InternalEObject)rightOperand).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InstantiationPackage.INFIX_EXPRESSION__RIGHT_OPERAND, null, msgs);
			if (newRightOperand != null)
				msgs = ((InternalEObject)newRightOperand).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InstantiationPackage.INFIX_EXPRESSION__RIGHT_OPERAND, null, msgs);
			msgs = basicSetRightOperand(newRightOperand, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InstantiationPackage.INFIX_EXPRESSION__RIGHT_OPERAND, newRightOperand, newRightOperand));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getExtendedOperands() {
		if (extendedOperands == null) {
			extendedOperands = new EObjectContainmentEList(Expression.class, this, InstantiationPackage.INFIX_EXPRESSION__EXTENDED_OPERANDS);
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
				case InstantiationPackage.INFIX_EXPRESSION__LEFT_OPERAND:
					return basicSetLeftOperand(null, msgs);
				case InstantiationPackage.INFIX_EXPRESSION__RIGHT_OPERAND:
					return basicSetRightOperand(null, msgs);
				case InstantiationPackage.INFIX_EXPRESSION__EXTENDED_OPERANDS:
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
			case InstantiationPackage.INFIX_EXPRESSION__LEFT_OPERAND:
				return getLeftOperand();
			case InstantiationPackage.INFIX_EXPRESSION__OPERATOR:
				return getOperator();
			case InstantiationPackage.INFIX_EXPRESSION__RIGHT_OPERAND:
				return getRightOperand();
			case InstantiationPackage.INFIX_EXPRESSION__EXTENDED_OPERANDS:
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
			case InstantiationPackage.INFIX_EXPRESSION__LEFT_OPERAND:
				setLeftOperand((Expression)newValue);
				return;
			case InstantiationPackage.INFIX_EXPRESSION__OPERATOR:
				setOperator((InfixOperator)newValue);
				return;
			case InstantiationPackage.INFIX_EXPRESSION__RIGHT_OPERAND:
				setRightOperand((Expression)newValue);
				return;
			case InstantiationPackage.INFIX_EXPRESSION__EXTENDED_OPERANDS:
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
			case InstantiationPackage.INFIX_EXPRESSION__LEFT_OPERAND:
				setLeftOperand((Expression)null);
				return;
			case InstantiationPackage.INFIX_EXPRESSION__OPERATOR:
				setOperator(OPERATOR_EDEFAULT);
				return;
			case InstantiationPackage.INFIX_EXPRESSION__RIGHT_OPERAND:
				setRightOperand((Expression)null);
				return;
			case InstantiationPackage.INFIX_EXPRESSION__EXTENDED_OPERANDS:
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
			case InstantiationPackage.INFIX_EXPRESSION__LEFT_OPERAND:
				return leftOperand != null;
			case InstantiationPackage.INFIX_EXPRESSION__OPERATOR:
				return operator != OPERATOR_EDEFAULT;
			case InstantiationPackage.INFIX_EXPRESSION__RIGHT_OPERAND:
				return rightOperand != null;
			case InstantiationPackage.INFIX_EXPRESSION__EXTENDED_OPERANDS:
				return extendedOperands != null && !extendedOperands.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if (eIsProxy() || getLeftOperand() == null || getOperator() == null || getRightOperand() == null) return super.toString();
	
		StringBuffer result = new StringBuffer(100);
		result.append(getLeftOperand());
		result.append(' ');
		String oper = getOperator().getOperator();
		result.append(oper);
		result.append(' ');
		result.append(getRightOperand());
		List ext = getExtendedOperands();
		for (int i = 0; i < ext.size(); i++) {
			result.append(' ');
			result.append(oper);
			result.append(' ');
			result.append(ext.get(i).toString());
		}
		return result.toString();
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
} //InfixExpressionImpl
