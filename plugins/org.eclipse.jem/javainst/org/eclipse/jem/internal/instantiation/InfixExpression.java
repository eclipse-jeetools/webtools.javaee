package org.eclipse.jem.internal.instantiation;
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
 *  $RCSfile: InfixExpression.java,v $
 *  $Revision: 1.1 $  $Date: 2004/01/19 22:50:15 $ 
 */
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Infix Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An expression with an operator that has two operands, e.g. 3 + 4.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.instantiation.InfixExpression#getLeftOperand <em>Left Operand</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.InfixExpression#getOperator <em>Operator</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.InfixExpression#getRightOperand <em>Right Operand</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.InfixExpression#getExtendedOperands <em>Extended Operands</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getInfixExpression()
 * @model 
 * @generated
 */
public interface InfixExpression extends Expression {
	/**
	 * Returns the value of the '<em><b>Left Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Operand</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Operand</em>' containment reference.
	 * @see #setLeftOperand(Expression)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getInfixExpression_LeftOperand()
	 * @model containment="true"
	 * @generated
	 */
	Expression getLeftOperand();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.InfixExpression#getLeftOperand <em>Left Operand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Operand</em>' containment reference.
	 * @see #getLeftOperand()
	 * @generated
	 */
	void setLeftOperand(Expression value);

	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.jem.internal.instantiation.InfixOperator}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The operator, e.g. +
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see org.eclipse.jem.internal.instantiation.InfixOperator
	 * @see #setOperator(InfixOperator)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getInfixExpression_Operator()
	 * @model 
	 * @generated
	 */
	InfixOperator getOperator();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.InfixExpression#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see org.eclipse.jem.internal.instantiation.InfixOperator
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(InfixOperator value);

	/**
	 * Returns the value of the '<em><b>Right Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The right expression. i.e. 4 of "3 + 4"
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Right Operand</em>' containment reference.
	 * @see #setRightOperand(Expression)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getInfixExpression_RightOperand()
	 * @model containment="true"
	 * @generated
	 */
	Expression getRightOperand();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.InfixExpression#getRightOperand <em>Right Operand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Operand</em>' containment reference.
	 * @see #getRightOperand()
	 * @generated
	 */
	void setRightOperand(Expression value);

	/**
	 * Returns the value of the '<em><b>Extended Operands</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jem.internal.instantiation.Expression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Used when there are a series of infix operations of the same operator, e.g. 1 + 2 + 3 + 4. 1 and 2 will go into left and right, while {3, 4} will go into the extended operands. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Extended Operands</em>' containment reference list.
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getInfixExpression_ExtendedOperands()
	 * @model type="org.eclipse.jem.internal.instantiation.Expression" containment="true"
	 * @generated
	 */
	EList getExtendedOperands();

} // InfixExpression
