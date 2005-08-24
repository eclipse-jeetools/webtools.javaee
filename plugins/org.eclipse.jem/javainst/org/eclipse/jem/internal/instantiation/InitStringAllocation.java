/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: InitStringAllocation.java,v $
 *  $Revision: 1.7 $  $Date: 2005/08/24 20:20:24 $ 
 */
package org.eclipse.jem.internal.instantiation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Init String Allocation</b></em>'.
 * <p>
 * This is used when the allocation string can be parsed by the remote vm. It uses the allocString. 
 * </p>
 * @see org.eclipse.jem.internal.instantiation.JavaAllocation#getAllocString()
 * @since 1.0.0
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This is used when the allocation string can be parsed by the remote vm. It uses the allocString.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.instantiation.InitStringAllocation#getInitString <em>Init String</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getInitStringAllocation()
 * @model 
 * @generated
 */
public interface InitStringAllocation extends JavaAllocation{
	/**
	 * Returns the value of the '<em><b>Init String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Init String</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This is the initialization string for this object.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Init String</em>' attribute.
	 * @see #setInitString(String)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getInitStringAllocation_InitString()
	 * @model 
	 * @generated
	 */
	String getInitString();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.InitStringAllocation#getInitString <em>Init String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Init String</em>' attribute.
	 * @see #getInitString()
	 * @generated
	 */
	void setInitString(String value);

} // InitStringAllocation
