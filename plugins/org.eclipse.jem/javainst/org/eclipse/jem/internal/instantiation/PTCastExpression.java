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
 *  $RCSfile: PTCastExpression.java,v $
 *  $Revision: 1.1 $  $Date: 2004/01/23 22:53:21 $ 
 */

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cast</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Cast expression, e.g. (short) 10
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.instantiation.Cast#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.Cast#getExpression <em>Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getCast()
 * @model 
 * @generated
 */
public interface PTCastExpression extends PTExpression{
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This is the fully-qualified type of the cast, e.g. int or java.lang.String
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTCastExpression_Type()
	 * @model 
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.PTCastExpression#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The expression being casted.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Expression</em>' containment reference.
	 * @see #setExpression(PTExpression)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTCastExpression_Expression()
	 * @model containment="true"
	 * @generated
	 */
	PTExpression getExpression();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.PTCastExpression#getExpression <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' containment reference.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(PTExpression value);

} // Cast
