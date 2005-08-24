/*******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.instantiation;
/*
 *  $RCSfile: PTInfixExpression.java,v $
 *  $Revision: 1.4 $  $Date: 2005/08/24 20:20:24 $ 
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
 *   <li>{@link org.eclipse.jem.internal.instantiation.PTInfixExpression#getLeftOperand <em>Left Operand</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.PTInfixExpression#getOperator <em>Operator</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.PTInfixExpression#getRightOperand <em>Right Operand</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.PTInfixExpression#getExtendedOperands <em>Extended Operands</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTInfixExpression()
 * @model 
 * @generated
 */
public interface PTInfixExpression extends PTExpression{
	/**
	 * Returns the value of the '<em><b>Left Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Operand</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Operand</em>' containment reference.
	 * @see #setLeftOperand(PTExpression)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTInfixExpression_LeftOperand()
	 * @model containment="true"
	 * @generated
	 */
	PTExpression getLeftOperand();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.PTInfixExpression#getLeftOperand <em>Left Operand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Operand</em>' containment reference.
	 * @see #getLeftOperand()
	 * @generated
	 */
	void setLeftOperand(PTExpression value);

	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.jem.internal.instantiation.PTInfixOperator}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The operator, e.g. +
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see org.eclipse.jem.internal.instantiation.PTInfixOperator
	 * @see #setOperator(PTInfixOperator)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTInfixExpression_Operator()
	 * @model 
	 * @generated
	 */
	PTInfixOperator getOperator();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.PTInfixExpression#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see org.eclipse.jem.internal.instantiation.PTInfixOperator
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(PTInfixOperator value);

	/**
	 * Returns the value of the '<em><b>Right Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The right expression. i.e. 4 of "3 + 4"
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Right Operand</em>' containment reference.
	 * @see #setRightOperand(PTExpression)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTInfixExpression_RightOperand()
	 * @model containment="true"
	 * @generated
	 */
	PTExpression getRightOperand();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.PTInfixExpression#getRightOperand <em>Right Operand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Operand</em>' containment reference.
	 * @see #getRightOperand()
	 * @generated
	 */
	void setRightOperand(PTExpression value);

	/**
	 * Returns the value of the '<em><b>Extended Operands</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jem.internal.instantiation.PTExpression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Used when there are a series of infix operations of the same operator, e.g. 1 + 2 + 3 + 4. 1 and 2 will go into left and right, while {3, 4} will go into the extended operands. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Extended Operands</em>' containment reference list.
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTInfixExpression_ExtendedOperands()
	 * @model type="org.eclipse.jem.internal.instantiation.PTExpression" containment="true"
	 * @generated
	 */
	EList getExtendedOperands();
	
	/**
	 * Compress the expression
	 * If there is no left operand and a right operand, or vice versa, and no extended operands
	 * return the remaining single operand
	 * If there is no left operand and a right and some extended ones shuffle everything along
	 * so that the right becomes the left and the first extended moves into the right
	 * or if no right and a left then just move the first extended into the right
	 * 
	 * @since 1.0.0
	 */
	PTExpression asCompressedExpression();

} // InfixExpression
