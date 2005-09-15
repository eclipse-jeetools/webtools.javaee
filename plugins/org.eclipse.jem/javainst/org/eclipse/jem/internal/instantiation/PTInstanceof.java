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
 *  $RCSfile: PTInstanceof.java,v $
 *  $Revision: 1.6 $  $Date: 2005/09/15 21:02:19 $ 
 */
package org.eclipse.jem.internal.instantiation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Instanceof</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Instanceof expression, e.g. x instanceof java.lang.String
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.instantiation.PTInstanceof#getOperand <em>Operand</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.PTInstanceof#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTInstanceof()
 * @model
 * @generated
 */
public interface PTInstanceof extends PTExpression{
	/**
	 * Returns the value of the '<em><b>Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The value to test, e.g. x in x instanceof java.lang.String
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Operand</em>' containment reference.
	 * @see #setOperand(PTExpression)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTInstanceof_Operand()
	 * @model containment="true"
	 * @generated
	 */
	PTExpression getOperand();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.PTInstanceof#getOperand <em>Operand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operand</em>' containment reference.
	 * @see #getOperand()
	 * @generated
	 */
	void setOperand(PTExpression value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The type of the instance test, e.g. java.lang.String. It must be fully-qualified. If it is an inner class, it needs to be in reflection format, i.e. Y is inner class of j.X, then a reference to Y must be j.X$Y.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTInstanceof_Type()
	 * @model
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.PTInstanceof#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

} // Instanceof
