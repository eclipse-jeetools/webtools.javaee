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
/*
 *  $RCSfile: PTConditionalExpression.java,v $
 *  $Revision: 1.6 $  $Date: 2005/09/15 21:02:19 $ 
 */
package org.eclipse.jem.internal.instantiation;

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
 *   <li>{@link org.eclipse.jem.internal.instantiation.PTConditionalExpression#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.PTConditionalExpression#getTrue <em>True</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.PTConditionalExpression#getFalse <em>False</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTConditionalExpression()
 * @model
 * @generated
 */
public interface PTConditionalExpression extends PTExpression{
	/**
	 * Returns the value of the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The condition expression.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Condition</em>' containment reference.
	 * @see #setCondition(PTExpression)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTConditionalExpression_Condition()
	 * @model containment="true"
	 * @generated
	 */
	PTExpression getCondition();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.PTConditionalExpression#getCondition <em>Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' containment reference.
	 * @see #getCondition()
	 * @generated
	 */
	void setCondition(PTExpression value);

	/**
	 * Returns the value of the '<em><b>True</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The true expression
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>True</em>' containment reference.
	 * @see #setTrue(PTExpression)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTConditionalExpression_True()
	 * @model containment="true"
	 * @generated
	 */
	PTExpression getTrue();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.PTConditionalExpression#getTrue <em>True</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>True</em>' containment reference.
	 * @see #getTrue()
	 * @generated
	 */
	void setTrue(PTExpression value);

	/**
	 * Returns the value of the '<em><b>False</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The false expression.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>False</em>' containment reference.
	 * @see #setFalse(PTExpression)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTConditionalExpression_False()
	 * @model containment="true"
	 * @generated
	 */
	PTExpression getFalse();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.PTConditionalExpression#getFalse <em>False</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>False</em>' containment reference.
	 * @see #getFalse()
	 * @generated
	 */
	void setFalse(PTExpression value);

} // ConditionalExpression
