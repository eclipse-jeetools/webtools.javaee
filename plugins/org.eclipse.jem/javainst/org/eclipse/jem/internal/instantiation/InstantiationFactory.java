package org.eclipse.jem.internal.instantiation;
/*******************************************************************************
 * Copyright (c)  2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: InstantiationFactory.java,v $
 *  $Revision: 1.2 $  $Date: 2004/01/12 21:44:21 $ 
 */

import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;


/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage
 * @generated
 */
public interface InstantiationFactory extends EFactory{
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	InstantiationFactory eINSTANCE = new org.eclipse.jem.internal.instantiation.impl.InstantiationFactoryImpl();

	/**
	 * Returns a new object of class '<em>Init String Allocation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Init String Allocation</em>'.
	 * @generated
	 */
	InitStringAllocation createInitStringAllocation();

	/**
	 * Returns a new object of class '<em>Implicit Allocation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Implicit Allocation</em>'.
	 * @generated
	 */
	ImplicitAllocation createImplicitAllocation();

	/**
	 * Returns a new object of class '<em>Implicit Allocation</em>' as
	 * utility with a given init string.
	 * @param initString - String to put into initialization.
	 * @return The allocation initialized.
	 * 
	 * @since 1.0.0
	 */
	InitStringAllocation createInitStringAllocation(String initString);
	
	/**
	 * Returns a new object of class '<em>Implicit Allocation</em>' as
	 * utility with a given parent and feature.
	 * @param parent The source to get the value from.
	 * @param sf The feature on the source for the value.
	 * @return The allocation initialized.
	 * 
	 * @since 1.0.0
	 */
	ImplicitAllocation createImplicitAllocation(EObject parent, EStructuralFeature sf);

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	InstantiationPackage getInstantiationPackage();

} //InstantiationFactory
