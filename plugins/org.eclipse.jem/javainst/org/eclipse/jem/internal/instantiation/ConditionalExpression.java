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
 *  $RCSfile: ConditionalExpression.java,v $
 *  $Revision: 1.1 $  $Date: 2004/01/19 22:50:15 $ 
 */

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Conditional Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The test Expresson, e.g. (x=3) ? 1 : 2
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.instantiation.ConditionalExpression#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.ConditionalExpression#getTrue <em>True</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.ConditionalExpression#getFalse <em>False</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getConditionalExpression()
 * @model 
 * @generated
 */
public interface ConditionalExpression extends Expression {
	/**
	 * Returns the value of the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The condition expression.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Condition</em>' containment reference.
	 * @see #setCondition(Expression)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getConditionalExpression_Condition()
	 * @model containment="true"
	 * @generated
	 */
	Expression getCondition();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.ConditionalExpression#getCondition <em>Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' containment reference.
	 * @see #getCondition()
	 * @generated
	 */
	void setCondition(Expression value);

	/**
	 * Returns the value of the '<em><b>True</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The true expression
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>True</em>' containment reference.
	 * @see #setTrue(Expression)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getConditionalExpression_True()
	 * @model containment="true"
	 * @generated
	 */
	Expression getTrue();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.ConditionalExpression#getTrue <em>True</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>True</em>' containment reference.
	 * @see #getTrue()
	 * @generated
	 */
	void setTrue(Expression value);

	/**
	 * Returns the value of the '<em><b>False</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The false expression.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>False</em>' containment reference.
	 * @see #setFalse(Expression)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getConditionalExpression_False()
	 * @model containment="true"
	 * @generated
	 */
	Expression getFalse();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.ConditionalExpression#getFalse <em>False</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>False</em>' containment reference.
	 * @see #getFalse()
	 * @generated
	 */
	void setFalse(Expression value);

} // ConditionalExpression
