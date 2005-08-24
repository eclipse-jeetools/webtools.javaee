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
package org.eclipse.jem.internal.instantiation.impl;
/*
 *  $RCSfile: InstantiationPackageImpl.java,v $
 *  $Revision: 1.10 $  $Date: 2005/08/24 20:20:24 $ 
 */

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.impl.EcorePackageImpl;

import org.eclipse.jem.internal.instantiation.PTArrayAccess;
import org.eclipse.jem.internal.instantiation.PTArrayCreation;
import org.eclipse.jem.internal.instantiation.PTArrayInitializer;
import org.eclipse.jem.internal.instantiation.PTBooleanLiteral;
import org.eclipse.jem.internal.instantiation.PTCastExpression;
import org.eclipse.jem.internal.instantiation.PTCharacterLiteral;
import org.eclipse.jem.internal.instantiation.PTClassInstanceCreation;
import org.eclipse.jem.internal.instantiation.PTConditionalExpression;
import org.eclipse.jem.internal.instantiation.PTExpression;
import org.eclipse.jem.internal.instantiation.PTFieldAccess;
import org.eclipse.jem.internal.instantiation.ImplicitAllocation;
import org.eclipse.jem.internal.instantiation.PTInfixExpression;
import org.eclipse.jem.internal.instantiation.PTInfixOperator;
import org.eclipse.jem.internal.instantiation.PTInstanceReference;
import org.eclipse.jem.internal.instantiation.InitStringAllocation;
import org.eclipse.jem.internal.instantiation.PTInstanceof;
import org.eclipse.jem.internal.instantiation.InstantiationFactory;
import org.eclipse.jem.internal.instantiation.InstantiationPackage;
import org.eclipse.jem.internal.instantiation.PTInvalidExpression;
import org.eclipse.jem.internal.instantiation.JavaAllocation;

import org.eclipse.jem.internal.instantiation.PTMethodInvocation;
import org.eclipse.jem.internal.instantiation.PTName;
import org.eclipse.jem.internal.instantiation.PTNullLiteral;
import org.eclipse.jem.internal.instantiation.PTNumberLiteral;
import org.eclipse.jem.internal.instantiation.PTParenthesizedExpression;
import org.eclipse.jem.internal.instantiation.ParseTreeAllocation;

import org.eclipse.jem.internal.instantiation.PTPrefixExpression;
import org.eclipse.jem.internal.instantiation.PTPrefixOperator;
import org.eclipse.jem.internal.instantiation.PTStringLiteral;
import org.eclipse.jem.internal.instantiation.PTThisLiteral;
import org.eclipse.jem.internal.instantiation.PTTypeLiteral;

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
	private EClass parseTreeAllocationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ptExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ptArrayAccessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ptArrayCreationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ptArrayInitializerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ptBooleanLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ptCastExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ptCharacterLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ptClassInstanceCreationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ptConditionalExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ptFieldAccessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ptInfixExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ptInstanceofEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ptMethodInvocationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ptNameEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ptNullLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ptNumberLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ptParenthesizedExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ptPrefixExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ptStringLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ptThisLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ptTypeLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ptInvalidExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ptInstanceReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum ptInfixOperatorEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum ptPrefixOperatorEEnum = null;

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
	private InstantiationPackageImpl() {
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
	public static InstantiationPackage init() {
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
	public EClass getIJavaObjectInstance() {
		return iJavaObjectInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getJavaAllocation() {
		return javaAllocationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInitStringAllocation() {
		return initStringAllocationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInitStringAllocation_InitString() {
		return (EAttribute)initStringAllocationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getImplicitAllocation() {
		return implicitAllocationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getImplicitAllocation_Parent() {
		return (EReference)implicitAllocationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getImplicitAllocation_Feature() {
		return (EReference)implicitAllocationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParseTreeAllocation() {
		return parseTreeAllocationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParseTreeAllocation_Expression() {
		return (EReference)parseTreeAllocationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPTExpression() {
		return ptExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPTArrayAccess() {
		return ptArrayAccessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPTArrayAccess_Array() {
		return (EReference)ptArrayAccessEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPTArrayAccess_Indexes() {
		return (EReference)ptArrayAccessEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPTArrayCreation() {
		return ptArrayCreationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPTArrayCreation_Type() {
		return (EAttribute)ptArrayCreationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPTArrayCreation_Dimensions() {
		return (EReference)ptArrayCreationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPTArrayCreation_Initializer() {
		return (EReference)ptArrayCreationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPTArrayInitializer() {
		return ptArrayInitializerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPTArrayInitializer_Expressions() {
		return (EReference)ptArrayInitializerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPTBooleanLiteral() {
		return ptBooleanLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPTBooleanLiteral_BooleanValue() {
		return (EAttribute)ptBooleanLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPTCastExpression() {
		return ptCastExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPTCastExpression_Type() {
		return (EAttribute)ptCastExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPTCastExpression_Expression() {
		return (EReference)ptCastExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPTCharacterLiteral() {
		return ptCharacterLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPTCharacterLiteral_EscapedValue() {
		return (EAttribute)ptCharacterLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPTCharacterLiteral_CharValue() {
		return (EAttribute)ptCharacterLiteralEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPTClassInstanceCreation() {
		return ptClassInstanceCreationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPTClassInstanceCreation_Type() {
		return (EAttribute)ptClassInstanceCreationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPTClassInstanceCreation_Arguments() {
		return (EReference)ptClassInstanceCreationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPTConditionalExpression() {
		return ptConditionalExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPTConditionalExpression_Condition() {
		return (EReference)ptConditionalExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPTConditionalExpression_True() {
		return (EReference)ptConditionalExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPTConditionalExpression_False() {
		return (EReference)ptConditionalExpressionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPTFieldAccess() {
		return ptFieldAccessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPTFieldAccess_Receiver() {
		return (EReference)ptFieldAccessEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPTFieldAccess_Field() {
		return (EAttribute)ptFieldAccessEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPTInfixExpression() {
		return ptInfixExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPTInfixExpression_LeftOperand() {
		return (EReference)ptInfixExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPTInfixExpression_Operator() {
		return (EAttribute)ptInfixExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPTInfixExpression_RightOperand() {
		return (EReference)ptInfixExpressionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPTInfixExpression_ExtendedOperands() {
		return (EReference)ptInfixExpressionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPTInstanceof() {
		return ptInstanceofEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPTInstanceof_Operand() {
		return (EReference)ptInstanceofEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPTInstanceof_Type() {
		return (EAttribute)ptInstanceofEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPTMethodInvocation() {
		return ptMethodInvocationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPTMethodInvocation_Receiver() {
		return (EReference)ptMethodInvocationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPTMethodInvocation_Name() {
		return (EAttribute)ptMethodInvocationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPTMethodInvocation_Arguments() {
		return (EReference)ptMethodInvocationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPTName() {
		return ptNameEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPTName_Name() {
		return (EAttribute)ptNameEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPTNullLiteral() {
		return ptNullLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPTNumberLiteral() {
		return ptNumberLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPTNumberLiteral_Token() {
		return (EAttribute)ptNumberLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPTParenthesizedExpression() {
		return ptParenthesizedExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPTParenthesizedExpression_Expression() {
		return (EReference)ptParenthesizedExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPTPrefixExpression() {
		return ptPrefixExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPTPrefixExpression_Operator() {
		return (EAttribute)ptPrefixExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPTPrefixExpression_Expression() {
		return (EReference)ptPrefixExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPTStringLiteral() {
		return ptStringLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPTStringLiteral_EscapedValue() {
		return (EAttribute)ptStringLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPTStringLiteral_LiteralValue() {
		return (EAttribute)ptStringLiteralEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPTThisLiteral() {
		return ptThisLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPTTypeLiteral() {
		return ptTypeLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPTTypeLiteral_Type() {
		return (EAttribute)ptTypeLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPTInvalidExpression() {
		return ptInvalidExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPTInvalidExpression_Message() {
		return (EAttribute)ptInvalidExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPTInstanceReference() {
		return ptInstanceReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPTInstanceReference_Object() {
		return (EReference)ptInstanceReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getPTInfixOperator() {
		return ptInfixOperatorEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getPTPrefixOperator() {
		return ptPrefixOperatorEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIJavaDataTypeInstance() {
		return iJavaDataTypeInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIJavaInstance() {
		return iJavaInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InstantiationFactory getInstantiationFactory() {
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
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		iJavaDataTypeInstanceEClass = createEClass(IJAVA_DATA_TYPE_INSTANCE);

		iJavaInstanceEClass = createEClass(IJAVA_INSTANCE);

		iJavaObjectInstanceEClass = createEClass(IJAVA_OBJECT_INSTANCE);

		javaAllocationEClass = createEClass(JAVA_ALLOCATION);

		initStringAllocationEClass = createEClass(INIT_STRING_ALLOCATION);
		createEAttribute(initStringAllocationEClass, INIT_STRING_ALLOCATION__INIT_STRING);

		implicitAllocationEClass = createEClass(IMPLICIT_ALLOCATION);
		createEReference(implicitAllocationEClass, IMPLICIT_ALLOCATION__PARENT);
		createEReference(implicitAllocationEClass, IMPLICIT_ALLOCATION__FEATURE);

		parseTreeAllocationEClass = createEClass(PARSE_TREE_ALLOCATION);
		createEReference(parseTreeAllocationEClass, PARSE_TREE_ALLOCATION__EXPRESSION);

		ptExpressionEClass = createEClass(PT_EXPRESSION);

		ptArrayAccessEClass = createEClass(PT_ARRAY_ACCESS);
		createEReference(ptArrayAccessEClass, PT_ARRAY_ACCESS__ARRAY);
		createEReference(ptArrayAccessEClass, PT_ARRAY_ACCESS__INDEXES);

		ptArrayCreationEClass = createEClass(PT_ARRAY_CREATION);
		createEAttribute(ptArrayCreationEClass, PT_ARRAY_CREATION__TYPE);
		createEReference(ptArrayCreationEClass, PT_ARRAY_CREATION__DIMENSIONS);
		createEReference(ptArrayCreationEClass, PT_ARRAY_CREATION__INITIALIZER);

		ptArrayInitializerEClass = createEClass(PT_ARRAY_INITIALIZER);
		createEReference(ptArrayInitializerEClass, PT_ARRAY_INITIALIZER__EXPRESSIONS);

		ptBooleanLiteralEClass = createEClass(PT_BOOLEAN_LITERAL);
		createEAttribute(ptBooleanLiteralEClass, PT_BOOLEAN_LITERAL__BOOLEAN_VALUE);

		ptCastExpressionEClass = createEClass(PT_CAST_EXPRESSION);
		createEAttribute(ptCastExpressionEClass, PT_CAST_EXPRESSION__TYPE);
		createEReference(ptCastExpressionEClass, PT_CAST_EXPRESSION__EXPRESSION);

		ptCharacterLiteralEClass = createEClass(PT_CHARACTER_LITERAL);
		createEAttribute(ptCharacterLiteralEClass, PT_CHARACTER_LITERAL__ESCAPED_VALUE);
		createEAttribute(ptCharacterLiteralEClass, PT_CHARACTER_LITERAL__CHAR_VALUE);

		ptClassInstanceCreationEClass = createEClass(PT_CLASS_INSTANCE_CREATION);
		createEAttribute(ptClassInstanceCreationEClass, PT_CLASS_INSTANCE_CREATION__TYPE);
		createEReference(ptClassInstanceCreationEClass, PT_CLASS_INSTANCE_CREATION__ARGUMENTS);

		ptConditionalExpressionEClass = createEClass(PT_CONDITIONAL_EXPRESSION);
		createEReference(ptConditionalExpressionEClass, PT_CONDITIONAL_EXPRESSION__CONDITION);
		createEReference(ptConditionalExpressionEClass, PT_CONDITIONAL_EXPRESSION__TRUE);
		createEReference(ptConditionalExpressionEClass, PT_CONDITIONAL_EXPRESSION__FALSE);

		ptFieldAccessEClass = createEClass(PT_FIELD_ACCESS);
		createEReference(ptFieldAccessEClass, PT_FIELD_ACCESS__RECEIVER);
		createEAttribute(ptFieldAccessEClass, PT_FIELD_ACCESS__FIELD);

		ptInfixExpressionEClass = createEClass(PT_INFIX_EXPRESSION);
		createEReference(ptInfixExpressionEClass, PT_INFIX_EXPRESSION__LEFT_OPERAND);
		createEAttribute(ptInfixExpressionEClass, PT_INFIX_EXPRESSION__OPERATOR);
		createEReference(ptInfixExpressionEClass, PT_INFIX_EXPRESSION__RIGHT_OPERAND);
		createEReference(ptInfixExpressionEClass, PT_INFIX_EXPRESSION__EXTENDED_OPERANDS);

		ptInstanceofEClass = createEClass(PT_INSTANCEOF);
		createEReference(ptInstanceofEClass, PT_INSTANCEOF__OPERAND);
		createEAttribute(ptInstanceofEClass, PT_INSTANCEOF__TYPE);

		ptMethodInvocationEClass = createEClass(PT_METHOD_INVOCATION);
		createEReference(ptMethodInvocationEClass, PT_METHOD_INVOCATION__RECEIVER);
		createEAttribute(ptMethodInvocationEClass, PT_METHOD_INVOCATION__NAME);
		createEReference(ptMethodInvocationEClass, PT_METHOD_INVOCATION__ARGUMENTS);

		ptNameEClass = createEClass(PT_NAME);
		createEAttribute(ptNameEClass, PT_NAME__NAME);

		ptNullLiteralEClass = createEClass(PT_NULL_LITERAL);

		ptNumberLiteralEClass = createEClass(PT_NUMBER_LITERAL);
		createEAttribute(ptNumberLiteralEClass, PT_NUMBER_LITERAL__TOKEN);

		ptParenthesizedExpressionEClass = createEClass(PT_PARENTHESIZED_EXPRESSION);
		createEReference(ptParenthesizedExpressionEClass, PT_PARENTHESIZED_EXPRESSION__EXPRESSION);

		ptPrefixExpressionEClass = createEClass(PT_PREFIX_EXPRESSION);
		createEAttribute(ptPrefixExpressionEClass, PT_PREFIX_EXPRESSION__OPERATOR);
		createEReference(ptPrefixExpressionEClass, PT_PREFIX_EXPRESSION__EXPRESSION);

		ptStringLiteralEClass = createEClass(PT_STRING_LITERAL);
		createEAttribute(ptStringLiteralEClass, PT_STRING_LITERAL__ESCAPED_VALUE);
		createEAttribute(ptStringLiteralEClass, PT_STRING_LITERAL__LITERAL_VALUE);

		ptThisLiteralEClass = createEClass(PT_THIS_LITERAL);

		ptTypeLiteralEClass = createEClass(PT_TYPE_LITERAL);
		createEAttribute(ptTypeLiteralEClass, PT_TYPE_LITERAL__TYPE);

		ptInvalidExpressionEClass = createEClass(PT_INVALID_EXPRESSION);
		createEAttribute(ptInvalidExpressionEClass, PT_INVALID_EXPRESSION__MESSAGE);

		ptInstanceReferenceEClass = createEClass(PT_INSTANCE_REFERENCE);
		createEReference(ptInstanceReferenceEClass, PT_INSTANCE_REFERENCE__OBJECT);

		// Create enums
		ptInfixOperatorEEnum = createEEnum(PT_INFIX_OPERATOR);
		ptPrefixOperatorEEnum = createEEnum(PT_PREFIX_OPERATOR);
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
	public void initializePackageContents() {
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
		parseTreeAllocationEClass.getESuperTypes().add(this.getJavaAllocation());
		ptArrayAccessEClass.getESuperTypes().add(this.getPTExpression());
		ptArrayCreationEClass.getESuperTypes().add(this.getPTExpression());
		ptArrayInitializerEClass.getESuperTypes().add(this.getPTExpression());
		ptBooleanLiteralEClass.getESuperTypes().add(this.getPTExpression());
		ptCastExpressionEClass.getESuperTypes().add(this.getPTExpression());
		ptCharacterLiteralEClass.getESuperTypes().add(this.getPTExpression());
		ptClassInstanceCreationEClass.getESuperTypes().add(this.getPTExpression());
		ptConditionalExpressionEClass.getESuperTypes().add(this.getPTExpression());
		ptFieldAccessEClass.getESuperTypes().add(this.getPTExpression());
		ptInfixExpressionEClass.getESuperTypes().add(this.getPTExpression());
		ptInstanceofEClass.getESuperTypes().add(this.getPTExpression());
		ptMethodInvocationEClass.getESuperTypes().add(this.getPTExpression());
		ptNameEClass.getESuperTypes().add(this.getPTExpression());
		ptNullLiteralEClass.getESuperTypes().add(this.getPTExpression());
		ptNumberLiteralEClass.getESuperTypes().add(this.getPTExpression());
		ptParenthesizedExpressionEClass.getESuperTypes().add(this.getPTExpression());
		ptPrefixExpressionEClass.getESuperTypes().add(this.getPTExpression());
		ptStringLiteralEClass.getESuperTypes().add(this.getPTExpression());
		ptThisLiteralEClass.getESuperTypes().add(this.getPTExpression());
		ptTypeLiteralEClass.getESuperTypes().add(this.getPTExpression());
		ptInvalidExpressionEClass.getESuperTypes().add(this.getPTExpression());
		ptInstanceReferenceEClass.getESuperTypes().add(this.getPTExpression());

		// Initialize classes and features; add operations and parameters
		initEClass(iJavaDataTypeInstanceEClass, IJavaDataTypeInstance.class, "IJavaDataTypeInstance", IS_ABSTRACT, IS_INTERFACE);

		initEClass(iJavaInstanceEClass, IJavaInstance.class, "IJavaInstance", IS_ABSTRACT, IS_INTERFACE);

		initEClass(iJavaObjectInstanceEClass, IJavaObjectInstance.class, "IJavaObjectInstance", IS_ABSTRACT, IS_INTERFACE);

		initEClass(javaAllocationEClass, JavaAllocation.class, "JavaAllocation", IS_ABSTRACT, !IS_INTERFACE);

		initEClass(initStringAllocationEClass, InitStringAllocation.class, "InitStringAllocation", !IS_ABSTRACT, !IS_INTERFACE);
		initEAttribute(getInitStringAllocation_InitString(), ecorePackage.getEString(), "initString", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);

		initEClass(implicitAllocationEClass, ImplicitAllocation.class, "ImplicitAllocation", !IS_ABSTRACT, !IS_INTERFACE);
		initEReference(getImplicitAllocation_Parent(), theEcorePackage.getEObject(), null, "parent", null, 1, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);
		initEReference(getImplicitAllocation_Feature(), theEcorePackage.getEStructuralFeature(), null, "feature", null, 1, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);

		initEClass(parseTreeAllocationEClass, ParseTreeAllocation.class, "ParseTreeAllocation", !IS_ABSTRACT, !IS_INTERFACE);
		initEReference(getParseTreeAllocation_Expression(), this.getPTExpression(), null, "expression", null, 1, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);

		initEClass(ptExpressionEClass, PTExpression.class, "PTExpression", IS_ABSTRACT, !IS_INTERFACE);

		initEClass(ptArrayAccessEClass, PTArrayAccess.class, "PTArrayAccess", !IS_ABSTRACT, !IS_INTERFACE);
		initEReference(getPTArrayAccess_Array(), this.getPTExpression(), null, "array", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);
		initEReference(getPTArrayAccess_Indexes(), this.getPTExpression(), null, "indexes", null, 1, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);

		initEClass(ptArrayCreationEClass, PTArrayCreation.class, "PTArrayCreation", !IS_ABSTRACT, !IS_INTERFACE);
		initEAttribute(getPTArrayCreation_Type(), ecorePackage.getEString(), "type", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);
		initEReference(getPTArrayCreation_Dimensions(), this.getPTExpression(), null, "dimensions", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);
		initEReference(getPTArrayCreation_Initializer(), this.getPTArrayInitializer(), null, "initializer", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);

		initEClass(ptArrayInitializerEClass, PTArrayInitializer.class, "PTArrayInitializer", !IS_ABSTRACT, !IS_INTERFACE);
		initEReference(getPTArrayInitializer_Expressions(), this.getPTExpression(), null, "expressions", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);

		initEClass(ptBooleanLiteralEClass, PTBooleanLiteral.class, "PTBooleanLiteral", !IS_ABSTRACT, !IS_INTERFACE);
		initEAttribute(getPTBooleanLiteral_BooleanValue(), ecorePackage.getEBoolean(), "booleanValue", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);

		initEClass(ptCastExpressionEClass, PTCastExpression.class, "PTCastExpression", !IS_ABSTRACT, !IS_INTERFACE);
		initEAttribute(getPTCastExpression_Type(), ecorePackage.getEString(), "type", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);
		initEReference(getPTCastExpression_Expression(), this.getPTExpression(), null, "expression", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);

		initEClass(ptCharacterLiteralEClass, PTCharacterLiteral.class, "PTCharacterLiteral", !IS_ABSTRACT, !IS_INTERFACE);
		initEAttribute(getPTCharacterLiteral_EscapedValue(), ecorePackage.getEString(), "escapedValue", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);
		initEAttribute(getPTCharacterLiteral_CharValue(), ecorePackage.getEChar(), "charValue", null, 0, 1, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);

		initEClass(ptClassInstanceCreationEClass, PTClassInstanceCreation.class, "PTClassInstanceCreation", !IS_ABSTRACT, !IS_INTERFACE);
		initEAttribute(getPTClassInstanceCreation_Type(), ecorePackage.getEString(), "type", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);
		initEReference(getPTClassInstanceCreation_Arguments(), this.getPTExpression(), null, "arguments", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);

		initEClass(ptConditionalExpressionEClass, PTConditionalExpression.class, "PTConditionalExpression", !IS_ABSTRACT, !IS_INTERFACE);
		initEReference(getPTConditionalExpression_Condition(), this.getPTExpression(), null, "condition", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);
		initEReference(getPTConditionalExpression_True(), this.getPTExpression(), null, "true", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);
		initEReference(getPTConditionalExpression_False(), this.getPTExpression(), null, "false", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);

		initEClass(ptFieldAccessEClass, PTFieldAccess.class, "PTFieldAccess", !IS_ABSTRACT, !IS_INTERFACE);
		initEReference(getPTFieldAccess_Receiver(), this.getPTExpression(), null, "receiver", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);
		initEAttribute(getPTFieldAccess_Field(), ecorePackage.getEString(), "field", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);

		initEClass(ptInfixExpressionEClass, PTInfixExpression.class, "PTInfixExpression", !IS_ABSTRACT, !IS_INTERFACE);
		initEReference(getPTInfixExpression_LeftOperand(), this.getPTExpression(), null, "leftOperand", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);
		initEAttribute(getPTInfixExpression_Operator(), this.getPTInfixOperator(), "operator", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);
		initEReference(getPTInfixExpression_RightOperand(), this.getPTExpression(), null, "rightOperand", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);
		initEReference(getPTInfixExpression_ExtendedOperands(), this.getPTExpression(), null, "extendedOperands", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);

		initEClass(ptInstanceofEClass, PTInstanceof.class, "PTInstanceof", !IS_ABSTRACT, !IS_INTERFACE);
		initEReference(getPTInstanceof_Operand(), this.getPTExpression(), null, "operand", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);
		initEAttribute(getPTInstanceof_Type(), ecorePackage.getEString(), "type", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);

		initEClass(ptMethodInvocationEClass, PTMethodInvocation.class, "PTMethodInvocation", !IS_ABSTRACT, !IS_INTERFACE);
		initEReference(getPTMethodInvocation_Receiver(), this.getPTExpression(), null, "receiver", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);
		initEAttribute(getPTMethodInvocation_Name(), ecorePackage.getEString(), "name", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);
		initEReference(getPTMethodInvocation_Arguments(), this.getPTExpression(), null, "arguments", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);

		initEClass(ptNameEClass, PTName.class, "PTName", !IS_ABSTRACT, !IS_INTERFACE);
		initEAttribute(getPTName_Name(), ecorePackage.getEString(), "name", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);

		initEClass(ptNullLiteralEClass, PTNullLiteral.class, "PTNullLiteral", !IS_ABSTRACT, !IS_INTERFACE);

		initEClass(ptNumberLiteralEClass, PTNumberLiteral.class, "PTNumberLiteral", !IS_ABSTRACT, !IS_INTERFACE);
		initEAttribute(getPTNumberLiteral_Token(), ecorePackage.getEString(), "token", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);

		initEClass(ptParenthesizedExpressionEClass, PTParenthesizedExpression.class, "PTParenthesizedExpression", !IS_ABSTRACT, !IS_INTERFACE);
		initEReference(getPTParenthesizedExpression_Expression(), this.getPTExpression(), null, "expression", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);

		initEClass(ptPrefixExpressionEClass, PTPrefixExpression.class, "PTPrefixExpression", !IS_ABSTRACT, !IS_INTERFACE);
		initEAttribute(getPTPrefixExpression_Operator(), this.getPTPrefixOperator(), "operator", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);
		initEReference(getPTPrefixExpression_Expression(), this.getPTExpression(), null, "expression", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);

		initEClass(ptStringLiteralEClass, PTStringLiteral.class, "PTStringLiteral", !IS_ABSTRACT, !IS_INTERFACE);
		initEAttribute(getPTStringLiteral_EscapedValue(), ecorePackage.getEString(), "escapedValue", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);
		initEAttribute(getPTStringLiteral_LiteralValue(), ecorePackage.getEString(), "literalValue", null, 0, 1, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);

		initEClass(ptThisLiteralEClass, PTThisLiteral.class, "PTThisLiteral", !IS_ABSTRACT, !IS_INTERFACE);

		initEClass(ptTypeLiteralEClass, PTTypeLiteral.class, "PTTypeLiteral", !IS_ABSTRACT, !IS_INTERFACE);
		initEAttribute(getPTTypeLiteral_Type(), ecorePackage.getEString(), "type", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);

		initEClass(ptInvalidExpressionEClass, PTInvalidExpression.class, "PTInvalidExpression", !IS_ABSTRACT, !IS_INTERFACE);
		initEAttribute(getPTInvalidExpression_Message(), ecorePackage.getEString(), "message", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);

		initEClass(ptInstanceReferenceEClass, PTInstanceReference.class, "PTInstanceReference", !IS_ABSTRACT, !IS_INTERFACE);
		initEReference(getPTInstanceReference_Object(), this.getIJavaObjectInstance(), null, "object", null, 1, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);

		// Initialize enums and add enum literals
		initEEnum(ptInfixOperatorEEnum, PTInfixOperator.class, "PTInfixOperator");
		addEEnumLiteral(ptInfixOperatorEEnum, PTInfixOperator.TIMES_LITERAL);
		addEEnumLiteral(ptInfixOperatorEEnum, PTInfixOperator.DIVIDE_LITERAL);
		addEEnumLiteral(ptInfixOperatorEEnum, PTInfixOperator.REMAINDER_LITERAL);
		addEEnumLiteral(ptInfixOperatorEEnum, PTInfixOperator.PLUS_LITERAL);
		addEEnumLiteral(ptInfixOperatorEEnum, PTInfixOperator.MINUS_LITERAL);
		addEEnumLiteral(ptInfixOperatorEEnum, PTInfixOperator.LEFT_SHIFT_LITERAL);
		addEEnumLiteral(ptInfixOperatorEEnum, PTInfixOperator.RIGHT_SHIFT_SIGNED_LITERAL);
		addEEnumLiteral(ptInfixOperatorEEnum, PTInfixOperator.RIGHT_SHIFT_UNSIGNED_LITERAL);
		addEEnumLiteral(ptInfixOperatorEEnum, PTInfixOperator.LESS_LITERAL);
		addEEnumLiteral(ptInfixOperatorEEnum, PTInfixOperator.GREATER_LITERAL);
		addEEnumLiteral(ptInfixOperatorEEnum, PTInfixOperator.LESS_EQUALS_LITERAL);
		addEEnumLiteral(ptInfixOperatorEEnum, PTInfixOperator.GREATER_EQUALS_LITERAL);
		addEEnumLiteral(ptInfixOperatorEEnum, PTInfixOperator.EQUALS_LITERAL);
		addEEnumLiteral(ptInfixOperatorEEnum, PTInfixOperator.NOT_EQUALS_LITERAL);
		addEEnumLiteral(ptInfixOperatorEEnum, PTInfixOperator.XOR_LITERAL);
		addEEnumLiteral(ptInfixOperatorEEnum, PTInfixOperator.AND_LITERAL);
		addEEnumLiteral(ptInfixOperatorEEnum, PTInfixOperator.OR_LITERAL);
		addEEnumLiteral(ptInfixOperatorEEnum, PTInfixOperator.CONDITIONAL_AND_LITERAL);
		addEEnumLiteral(ptInfixOperatorEEnum, PTInfixOperator.CONDITIONAL_OR_LITERAL);

		initEEnum(ptPrefixOperatorEEnum, PTPrefixOperator.class, "PTPrefixOperator");
		addEEnumLiteral(ptPrefixOperatorEEnum, PTPrefixOperator.PLUS_LITERAL);
		addEEnumLiteral(ptPrefixOperatorEEnum, PTPrefixOperator.MINUS_LITERAL);
		addEEnumLiteral(ptPrefixOperatorEEnum, PTPrefixOperator.COMPLEMENT_LITERAL);
		addEEnumLiteral(ptPrefixOperatorEEnum, PTPrefixOperator.NOT_LITERAL);

		// Create resource
		createResource(eNS_URI);
	}
} //InstantiationPackageImpl
