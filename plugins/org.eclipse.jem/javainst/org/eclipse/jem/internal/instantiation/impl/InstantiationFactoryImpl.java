package org.eclipse.jem.internal.instantiation.impl;
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
 *  $RCSfile: InstantiationFactoryImpl.java,v $
 *  $Revision: 1.2 $  $Date: 2004/01/12 21:44:21 $ 
 */

import org.eclipse.jem.internal.instantiation.*;

import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EFactoryImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class InstantiationFactoryImpl extends EFactoryImpl implements InstantiationFactory {
	/**
	 * Creates and instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InstantiationFactoryImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass)
	{
		switch (eClass.getClassifierID()) {
			case InstantiationPackage.INIT_STRING_ALLOCATION: return createInitStringAllocation();
			case InstantiationPackage.IMPLICIT_ALLOCATION: return createImplicitAllocation();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InitStringAllocation createInitStringAllocation()
	{
		InitStringAllocationImpl initStringAllocation = new InitStringAllocationImpl();
		return initStringAllocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImplicitAllocation createImplicitAllocation()
	{
		ImplicitAllocationImpl implicitAllocation = new ImplicitAllocationImpl();
		return implicitAllocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InstantiationPackage getInstantiationPackage()
	{
		return (InstantiationPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static InstantiationPackage getPackage()
	{
		return InstantiationPackage.eINSTANCE;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationFactory#createImplicitAllocation(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature)
	 */
	public ImplicitAllocation createImplicitAllocation(EObject parent, EStructuralFeature sf) {
		ImplicitAllocation alloc = createImplicitAllocation();
		alloc.setParent(parent);
		alloc.setFeature(sf);
		return alloc;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationFactory#createInitStringAllocation(java.lang.String)
	 */
	public InitStringAllocation createInitStringAllocation(String initString) {
		InitStringAllocation alloc = createInitStringAllocation();
		alloc.setInitString(initString);
		return alloc;
	}

} //InstantiationFactoryImpl
