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
 *  $RCSfile: PTParenthesizedExpression.java,v $
 *  $Revision: 1.1 $  $Date: 2004/01/23 22:53:21 $ 
 */

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parenthesized Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An expression within parenthesis, e.g. (x+3)
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.instantiation.ParenthesizedExpression#getExpression <em>Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getParenthesizedExpression()
 * @model 
 * @generated
 */
public interface PTParenthesizedExpression extends PTExpression{
	/**
	 * Returns the value of the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The expression wrapped by the parenthesis, e.g. (x+3) then the expression is "x+3"
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Expression</em>' containment reference.
	 * @see #setExpression(PTExpression)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTParenthesizedExpression_Expression()
	 * @model containment="true"
	 * @generated
	 */
	PTExpression getExpression();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.PTParenthesizedExpression#getExpression <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' containment reference.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(PTExpression value);

} // ParenthesizedExpression
