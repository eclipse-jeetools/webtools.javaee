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
 *  $RCSfile: InstantiationPackage.java,v $
 *  $Revision: 1.3 $  $Date: 2004/01/13 16:16:21 $ 
 */

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Java Instantiation Package
 * <!-- end-model-doc -->
 * @see org.eclipse.jem.internal.instantiation.InstantiationFactory
 * @generated
 */
public interface InstantiationPackage extends EPackage{
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "instantiation"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///org/eclipse/jem/internal/instantiation.ecore"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.eclipse.jem.internal.instantiation"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	InstantiationPackage eINSTANCE = org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.base.IJavaInstance <em>IJava Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.base.IJavaInstance
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getIJavaInstance()
	 * @generated
	 */
	int IJAVA_INSTANCE = 1;

	/**
	 * The number of structural features of the the '<em>IJava Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IJAVA_INSTANCE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.base.IJavaObjectInstance <em>IJava Object Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.base.IJavaObjectInstance
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getIJavaObjectInstance()
	 * @generated
	 */
	int IJAVA_OBJECT_INSTANCE = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.base.IJavaDataTypeInstance <em>IJava Data Type Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.base.IJavaDataTypeInstance
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getIJavaDataTypeInstance()
	 * @generated
	 */
	int IJAVA_DATA_TYPE_INSTANCE = 0;


	/**
	 * The number of structural features of the the '<em>IJava Data Type Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IJAVA_DATA_TYPE_INSTANCE_FEATURE_COUNT = IJAVA_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the the '<em>IJava Object Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IJAVA_OBJECT_INSTANCE_FEATURE_COUNT = IJAVA_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.JavaAllocationImpl <em>Java Allocation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.JavaAllocationImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getJavaAllocation()
	 * @generated
	 */
	int JAVA_ALLOCATION = 3;

	/**
	 * The feature id for the '<em><b>Alloc String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_ALLOCATION__ALLOC_STRING = 0;

	/**
	 * The number of structural features of the the '<em>Java Allocation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_ALLOCATION_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.InitStringAllocationImpl <em>Init String Allocation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.InitStringAllocationImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getInitStringAllocation()
	 * @generated
	 */
	int INIT_STRING_ALLOCATION = 4;

	/**
	 * The feature id for the '<em><b>Alloc String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INIT_STRING_ALLOCATION__ALLOC_STRING = JAVA_ALLOCATION__ALLOC_STRING;

	/**
	 * The feature id for the '<em><b>Init String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INIT_STRING_ALLOCATION__INIT_STRING = JAVA_ALLOCATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the the '<em>Init String Allocation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INIT_STRING_ALLOCATION_FEATURE_COUNT = JAVA_ALLOCATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.ImplicitAllocationImpl <em>Implicit Allocation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.ImplicitAllocationImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getImplicitAllocation()
	 * @generated
	 */
	int IMPLICIT_ALLOCATION = 5;

	/**
	 * The feature id for the '<em><b>Alloc String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLICIT_ALLOCATION__ALLOC_STRING = JAVA_ALLOCATION__ALLOC_STRING;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLICIT_ALLOCATION__PARENT = JAVA_ALLOCATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLICIT_ALLOCATION__FEATURE = JAVA_ALLOCATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the the '<em>Implicit Allocation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLICIT_ALLOCATION_FEATURE_COUNT = JAVA_ALLOCATION_FEATURE_COUNT + 2;


	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.base.IJavaObjectInstance <em>IJava Object Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IJava Object Instance</em>'.
	 * @see org.eclipse.jem.internal.instantiation.base.IJavaObjectInstance
	 * @model instanceClass="org.eclipse.jem.internal.instantiation.base.IJavaObjectInstance" 
	 * @generated
	 */
	EClass getIJavaObjectInstance();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.JavaAllocation <em>Java Allocation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Java Allocation</em>'.
	 * @see org.eclipse.jem.internal.instantiation.JavaAllocation
	 * @generated
	 */
	EClass getJavaAllocation();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.instantiation.JavaAllocation#getAllocString <em>Alloc String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Alloc String</em>'.
	 * @see org.eclipse.jem.internal.instantiation.JavaAllocation#getAllocString()
	 * @see #getJavaAllocation()
	 * @generated
	 */
	EAttribute getJavaAllocation_AllocString();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.InitStringAllocation <em>Init String Allocation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Init String Allocation</em>'.
	 * @see org.eclipse.jem.internal.instantiation.InitStringAllocation
	 * @generated
	 */
	EClass getInitStringAllocation();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.instantiation.InitStringAllocation#getInitString <em>Init String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Init String</em>'.
	 * @see org.eclipse.jem.internal.instantiation.InitStringAllocation#getInitString()
	 * @see #getInitStringAllocation()
	 * @generated
	 */
	EAttribute getInitStringAllocation_InitString();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.ImplicitAllocation <em>Implicit Allocation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Implicit Allocation</em>'.
	 * @see org.eclipse.jem.internal.instantiation.ImplicitAllocation
	 * @generated
	 */
	EClass getImplicitAllocation();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.jem.internal.instantiation.ImplicitAllocation#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parent</em>'.
	 * @see org.eclipse.jem.internal.instantiation.ImplicitAllocation#getParent()
	 * @see #getImplicitAllocation()
	 * @generated
	 */
	EReference getImplicitAllocation_Parent();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.jem.internal.instantiation.ImplicitAllocation#getFeature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Feature</em>'.
	 * @see org.eclipse.jem.internal.instantiation.ImplicitAllocation#getFeature()
	 * @see #getImplicitAllocation()
	 * @generated
	 */
	EReference getImplicitAllocation_Feature();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.base.IJavaDataTypeInstance <em>IJava Data Type Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IJava Data Type Instance</em>'.
	 * @see org.eclipse.jem.internal.instantiation.base.IJavaDataTypeInstance
	 * @model instanceClass="org.eclipse.jem.internal.instantiation.base.IJavaDataTypeInstance" 
	 * @generated
	 */
	EClass getIJavaDataTypeInstance();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.base.IJavaInstance <em>IJava Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IJava Instance</em>'.
	 * @see org.eclipse.jem.internal.instantiation.base.IJavaInstance
	 * @model instanceClass="org.eclipse.jem.internal.instantiation.base.IJavaInstance" 
	 * @generated
	 */
	EClass getIJavaInstance();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	InstantiationFactory getInstantiationFactory();

} //InstantiationPackage
