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
 *  $RCSfile: PrefixExpression.java,v $
 *  $Revision: 1.1 $  $Date: 2004/01/19 22:50:15 $ 
 */

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Prefix Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A one operand operator, where the operator comes before the operand, e.g. - (x+3). In AST, even -3 is modeled as a PrefixExpression of operator "-" and expression of NumberLiteral(3). But we optimize this away and just use a NumberLiteral(-3) directly instead.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.instantiation.PrefixExpression#getOperator <em>Operator</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.PrefixExpression#getExpression <em>Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPrefixExpression()
 * @model 
 * @generated
 */
public interface PrefixExpression extends Expression {
	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.jem.internal.instantiation.PrefixOperator}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The operator of this expression.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see org.eclipse.jem.internal.instantiation.PrefixOperator
	 * @see #setOperator(PrefixOperator)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPrefixExpression_Operator()
	 * @model 
	 * @generated
	 */
	PrefixOperator getOperator();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.PrefixExpression#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see org.eclipse.jem.internal.instantiation.PrefixOperator
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(PrefixOperator value);

	/**
	 * Returns the value of the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The expression that the operator is applied to.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Expression</em>' containment reference.
	 * @see #setExpression(Expression)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPrefixExpression_Expression()
	 * @model containment="true"
	 * @generated
	 */
	Expression getExpression();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.PrefixExpression#getExpression <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' containment reference.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(Expression value);

} // PrefixExpression
