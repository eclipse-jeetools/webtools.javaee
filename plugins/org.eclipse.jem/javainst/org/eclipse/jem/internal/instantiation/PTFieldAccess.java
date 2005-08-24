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
 *  $RCSfile: PTFieldAccess.java,v $
 *  $Revision: 1.5 $  $Date: 2005/08/24 20:20:24 $ 
 */
package org.eclipse.jem.internal.instantiation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Field Access</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Access a field expression, e.g. java.awt.Color.red
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.instantiation.PTFieldAccess#getReceiver <em>Receiver</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.PTFieldAccess#getField <em>Field</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTFieldAccess()
 * @model 
 * @generated
 */
public interface PTFieldAccess extends PTExpression{
	/**
	 * Returns the value of the '<em><b>Receiver</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The expression representing the object that the field is in, e.g. java.awt.Color.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Receiver</em>' containment reference.
	 * @see #setReceiver(PTExpression)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTFieldAccess_Receiver()
	 * @model containment="true"
	 * @generated
	 */
	PTExpression getReceiver();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.PTFieldAccess#getReceiver <em>Receiver</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Receiver</em>' containment reference.
	 * @see #getReceiver()
	 * @generated
	 */
	void setReceiver(PTExpression value);

	/**
	 * Returns the value of the '<em><b>Field</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The name of the field being accessed, e.g. red.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Field</em>' attribute.
	 * @see #setField(String)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTFieldAccess_Field()
	 * @model 
	 * @generated
	 */
	String getField();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.PTFieldAccess#getField <em>Field</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Field</em>' attribute.
	 * @see #getField()
	 * @generated
	 */
	void setField(String value);

} // FieldAccess
