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
 *  $RCSfile: InstantiationPackageImpl.java,v $
 *  $Revision: 1.2 $  $Date: 2004/01/12 21:44:21 $ 
 */

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.impl.EcorePackageImpl;

import org.eclipse.jem.internal.instantiation.ImplicitAllocation;
import org.eclipse.jem.internal.instantiation.InitStringAllocation;
import org.eclipse.jem.internal.instantiation.InstantiationFactory;
import org.eclipse.jem.internal.instantiation.InstantiationPackage;
import org.eclipse.jem.internal.instantiation.JavaAllocation;

import org.eclipse.jem.internal.instantiation.base.IJavaDataTypeInstance;
import org.eclipse.jem.internal.instantiation.base.IJavaInstance;
import org.eclipse.jem.internal.instantiation.base.IJavaObjectInstance;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class InstantiationPackageImpl extends EPackageImpl implements InstantiationPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iJavaDataTypeInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iJavaObjectInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass javaAllocationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass initStringAllocationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass implicitAllocationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iJavaInstanceEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private InstantiationPackageImpl()
	{
		super(eNS_URI, InstantiationFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this
	 * model, and for any others upon which it depends.  Simple
	 * dependencies are satisfied by calling this method on all
	 * dependent packages before doing anything else.  This method drives
	 * initialization for interdependent packages directly, in parallel
	 * with this package, itself.
	 * <p>Of this package and its interdependencies, all packages which
	 * have not yet been registered by their URI values are first created
	 * and registered.  The packages are then initialized in two steps:
	 * meta-model objects for all of the packages are created before any
	 * are initialized, since one package's meta-model objects may refer to
	 * those of another.
	 * <p>Invocation of this method will not affect any packages that have
	 * already been initialized.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static InstantiationPackage init()
	{
		if (isInited) return (InstantiationPackage)EPackage.Registry.INSTANCE.get(InstantiationPackage.eNS_URI);

		// Obtain or create and register package.
		InstantiationPackageImpl theInstantiationPackage = (InstantiationPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.get(eNS_URI) : new InstantiationPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackageImpl.init();

		// Obtain or create and register interdependencies

		// Step 1: create meta-model objects
		theInstantiationPackage.createPackageContents();

		// Step 2: complete initialization
		theInstantiationPackage.initializePackageContents();

		return theInstantiationPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIJavaObjectInstance()
	{
		return iJavaObjectInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getJavaAllocation()
	{
		return javaAllocationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJavaAllocation_AllocString()
	{
		return (EAttribute)javaAllocationEClass.getEAttributes().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInitStringAllocation()
	{
		return initStringAllocationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInitStringAllocation_InitString()
	{
		return (EAttribute)initStringAllocationEClass.getEAttributes().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getImplicitAllocation()
	{
		return implicitAllocationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getImplicitAllocation_Parent()
	{
		return (EReference)implicitAllocationEClass.getEReferences().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getImplicitAllocation_Feature()
	{
		return (EReference)implicitAllocationEClass.getEReferences().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIJavaDataTypeInstance()
	{
		return iJavaDataTypeInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIJavaInstance()
	{
		return iJavaInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InstantiationFactory getInstantiationFactory()
	{
		return (InstantiationFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents()
	{
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		iJavaDataTypeInstanceEClass = createEClass(IJAVA_DATA_TYPE_INSTANCE);

		iJavaInstanceEClass = createEClass(IJAVA_INSTANCE);

		iJavaObjectInstanceEClass = createEClass(IJAVA_OBJECT_INSTANCE);

		javaAllocationEClass = createEClass(JAVA_ALLOCATION);
		createEAttribute(javaAllocationEClass, JAVA_ALLOCATION__ALLOC_STRING);

		initStringAllocationEClass = createEClass(INIT_STRING_ALLOCATION);
		createEAttribute(initStringAllocationEClass, INIT_STRING_ALLOCATION__INIT_STRING);

		implicitAllocationEClass = createEClass(IMPLICIT_ALLOCATION);
		createEReference(implicitAllocationEClass, IMPLICIT_ALLOCATION__PARENT);
		createEReference(implicitAllocationEClass, IMPLICIT_ALLOCATION__FEATURE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents()
	{
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		EcorePackageImpl theEcorePackage = (EcorePackageImpl)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Add supertypes to classes
		iJavaDataTypeInstanceEClass.getESuperTypes().add(this.getIJavaInstance());
		iJavaObjectInstanceEClass.getESuperTypes().add(this.getIJavaInstance());
		initStringAllocationEClass.getESuperTypes().add(this.getJavaAllocation());
		implicitAllocationEClass.getESuperTypes().add(this.getJavaAllocation());

		// Initialize classes and features; add operations and parameters
		initEClass(iJavaDataTypeInstanceEClass, IJavaDataTypeInstance.class, "IJavaDataTypeInstance", IS_ABSTRACT, IS_INTERFACE);

		initEClass(iJavaInstanceEClass, IJavaInstance.class, "IJavaInstance", IS_ABSTRACT, IS_INTERFACE);

		initEClass(iJavaObjectInstanceEClass, IJavaObjectInstance.class, "IJavaObjectInstance", IS_ABSTRACT, IS_INTERFACE);

		initEClass(javaAllocationEClass, JavaAllocation.class, "JavaAllocation", IS_ABSTRACT, !IS_INTERFACE);
		initEAttribute(getJavaAllocation_AllocString(), ecorePackage.getEString(), "allocString", null, 0, 1, !IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE);

		initEClass(initStringAllocationEClass, InitStringAllocation.class, "InitStringAllocation", !IS_ABSTRACT, !IS_INTERFACE);
		initEAttribute(getInitStringAllocation_InitString(), ecorePackage.getEString(), "initString", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE);

		initEClass(implicitAllocationEClass, ImplicitAllocation.class, "ImplicitAllocation", !IS_ABSTRACT, !IS_INTERFACE);
		initEReference(getImplicitAllocation_Parent(), theEcorePackage.getEObject(), null, "parent", null, 1, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE);
		initEReference(getImplicitAllocation_Feature(), theEcorePackage.getEStructuralFeature(), null, "feature", null, 1, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE);

		// Create resource
		createResource(eNS_URI);
	}
} //InstantiationPackageImpl
