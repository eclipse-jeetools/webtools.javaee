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
 *  $RCSfile: TypeLiteral.java,v $
 *  $Revision: 1.1 $  $Date: 2004/01/19 22:50:15 $ 
 */

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This represents a literal of the form j.XYZ.class. For instance in a method invocation x.getX(jXYZ.class) the argument will be a TypeLiteral.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.instantiation.TypeLiteral#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getTypeLiteral()
 * @model 
 * @generated
 */
public interface TypeLiteral extends Expression {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The type of the literal.  It must be fully-qualified. If it is an inner class, it needs to be in reflection format, i.e. Y is inner class of j.X, then a reference to Y must be j.X$Y.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getTypeLiteral_Type()
	 * @model 
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.TypeLiteral#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

} // TypeLiteral
