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
 *  $RCSfile: PTArrayAccess.java,v $
 *  $Revision: 1.1 $  $Date: 2004/01/23 22:53:21 $ 
 */
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Array Access</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Array access. e.g. x[3]. 
 * 
 * Unlike AST, the entire expression of type x[3][4] will be done in one expression rather than having a separate ArrayAccess for each dimension. This is just a simplification. This can only be used if it is a simple array access where it is of the form {expression}[expression][expression].
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.instantiation.PTArrayAccess#getArray <em>Array</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.PTArrayAccess#getIndexes <em>Indexes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTArrayAccess()
 * @model 
 * @generated
 */
public interface PTArrayAccess extends PTExpression{
	/**
	 * Returns the value of the '<em><b>Array</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The expression representing the array to access.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Array</em>' containment reference.
	 * @see #setArray(PTExpression)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTArrayAccess_Array()
	 * @model containment="true"
	 * @generated
	 */
	PTExpression getArray();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.PTArrayAccess#getArray <em>Array</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Array</em>' containment reference.
	 * @see #getArray()
	 * @generated
	 */
	void setArray(PTExpression value);

	/**
	 * Returns the value of the '<em><b>Indexes</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jem.internal.instantiation.PTExpression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The expressions representing the indexes of the access.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Indexes</em>' containment reference list.
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTArrayAccess_Indexes()
	 * @model type="org.eclipse.jem.internal.instantiation.PTExpression" containment="true" required="true"
	 * @generated
	 */
	EList getIndexes();

} // ArrayAccess
