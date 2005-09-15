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
 *  $RCSfile: PTNumberLiteral.java,v $
 *  $Revision: 1.6 $  $Date: 2005/09/15 21:02:19 $ 
 */
package org.eclipse.jem.internal.instantiation;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Number Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represent a number primitive literal, e.g. 10. One optimization that will be performed is that if an expression is of the form +number or -number, it will be converted from a PrefixExpression with a NumberLiteral to just a NumberLiteral. This will simplify the model.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.instantiation.PTNumberLiteral#getToken <em>Token</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTNumberLiteral()
 * @model
 * @generated
 */
public interface PTNumberLiteral extends PTExpression{
	/**
	 * Returns the value of the '<em><b>Token</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The token value, e.g. 10, 10d, or -10.75
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Token</em>' attribute.
	 * @see #setToken(String)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTNumberLiteral_Token()
	 * @model
	 * @generated
	 */
	String getToken();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.PTNumberLiteral#getToken <em>Token</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Token</em>' attribute.
	 * @see #getToken()
	 * @generated
	 */
	void setToken(String value);

} // NumberLiteral
