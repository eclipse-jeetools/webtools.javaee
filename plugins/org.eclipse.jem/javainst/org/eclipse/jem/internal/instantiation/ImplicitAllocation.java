/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.instantiation;
/*
 *  $RCSfile: ImplicitAllocation.java,v $
 *  $Revision: 1.3 $  $Date: 2004/08/27 15:33:17 $ 
 */
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Implicit Allocation</b></em>'.
 * <p>
 * This class is for an implicit allocation where the object comes from the feature of another object. It is the live Java value from the remote vm.
 * </p> 
 * @since 1.0.0
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This class is for an implicit allocation where the object comes from the feature of another object. It is the live Java value from the remote vm.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.instantiation.ImplicitAllocation#getParent <em>Parent</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.ImplicitAllocation#getFeature <em>Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getImplicitAllocation()
 * @model 
 * @generated
 */
public interface ImplicitAllocation extends JavaAllocation{
	/**
	 * Returns the value of the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * The parent who is to give up the value.
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' reference.
	 * @see #setParent(EObject)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getImplicitAllocation_Parent()
	 * @model required="true"
	 * @generated
	 */
	EObject getParent();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.ImplicitAllocation#getParent <em>Parent</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(EObject value);

	/**
	 * Returns the value of the '<em><b>Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * The feature that will be used to query the live value.
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Feature</em>' reference.
	 * @see #setFeature(EStructuralFeature)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getImplicitAllocation_Feature()
	 * @model required="true"
	 * @generated
	 */
	EStructuralFeature getFeature();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.ImplicitAllocation#getFeature <em>Feature</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Feature</em>' reference.
	 * @see #getFeature()
	 * @generated
	 */
	void setFeature(EStructuralFeature value);

} // ImplicitAllocation
