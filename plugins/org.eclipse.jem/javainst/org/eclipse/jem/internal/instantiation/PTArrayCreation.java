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
package org.eclipse.jem.internal.instantiation;
/*
 *  $RCSfile: PTArrayCreation.java,v $
 *  $Revision: 1.4 $  $Date: 2005/09/15 21:02:19 $ 
 */
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Array Creation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Array Creation expression, e.g. new int[3] or new java.langString[][] {{"a"}, {"b"}}
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.instantiation.PTArrayCreation#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.PTArrayCreation#getDimensions <em>Dimensions</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.PTArrayCreation#getInitializer <em>Initializer</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTArrayCreation()
 * @model
 * @generated
 */
public interface PTArrayCreation extends PTExpression{
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The fully-qualified type, e.g. int[][] or java.lang.String[], including the number of dimension brackets.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTArrayCreation_Type()
	 * @model
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.PTArrayCreation#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Dimensions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jem.internal.instantiation.PTExpression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The list of expressions for the dimensions. You can have [3][4][] but [][33] is invalid. So for each dimension that has an expression, there will be an entry in the list, in the correct order. When you reach the empty dimensions, the list will end too. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Dimensions</em>' containment reference list.
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTArrayCreation_Dimensions()
	 * @model type="org.eclipse.jem.internal.instantiation.PTExpression" containment="true"
	 * @generated
	 */
	EList getDimensions();

	/**
	 * Returns the value of the '<em><b>Initializer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This is the initializer, if any, for this array. E.g. new int[] {2,3}. It is the {2,3}.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Initializer</em>' containment reference.
	 * @see #setInitializer(PTArrayInitializer)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTArrayCreation_Initializer()
	 * @model containment="true"
	 * @generated
	 */
	PTArrayInitializer getInitializer();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.PTArrayCreation#getInitializer <em>Initializer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initializer</em>' containment reference.
	 * @see #getInitializer()
	 * @generated
	 */
	void setInitializer(PTArrayInitializer value);

} // ArrayCreation
