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
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:12:30 $ 
 */

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

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
