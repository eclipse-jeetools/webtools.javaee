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
 *  $RCSfile: JavaAllocationImpl.java,v $
 *  $Revision: 1.1 $  $Date: 2004/01/12 21:44:21 $ 
 */
 

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.jem.internal.instantiation.InstantiationPackage;
import org.eclipse.jem.internal.instantiation.JavaAllocation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Java Allocation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.instantiation.impl.JavaAllocationImpl#getAllocString <em>Alloc String</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class JavaAllocationImpl extends EObjectImpl implements JavaAllocation {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected JavaAllocationImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass()
	{
		return InstantiationPackage.eINSTANCE.getJavaAllocation();
	}

	/*
	 * Subclasses will return the computed alloc string. The value will be used
	 * by getAllocString and will return it.
	 * 
	 * @return computed allocation string.
	 * 
	 * @since 1.0.0
	 */
	protected abstract String getComputedAllocString();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public String getAllocString()
	{
		return getComputedAllocString();
	}

	/**
	 * This method does nothing.
	 */
	public void setAllocString(String newAllocString)
	{
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve)
	{
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case InstantiationPackage.JAVA_ALLOCATION__ALLOC_STRING:
				return getAllocString();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue)
	{
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case InstantiationPackage.JAVA_ALLOCATION__ALLOC_STRING:
				setAllocString((String)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature)
	{
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case InstantiationPackage.JAVA_ALLOCATION__ALLOC_STRING:
				setAllocString((String)null);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature)
	{
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case InstantiationPackage.JAVA_ALLOCATION__ALLOC_STRING:
				return getAllocString() != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //JavaAllocationImpl
