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
 *  $Revision: 1.4 $  $Date: 2004/01/19 22:50:15 $ 
 */

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.impl.EcorePackageImpl;

import org.eclipse.jem.internal.instantiation.ArrayAccess;
import org.eclipse.jem.internal.instantiation.ArrayCreation;
import org.eclipse.jem.internal.instantiation.ArrayInitializer;
import org.eclipse.jem.internal.instantiation.BooleanLiteral;
import org.eclipse.jem.internal.instantiation.Cast;
import org.eclipse.jem.internal.instantiation.CharacterLiteral;
import org.eclipse.jem.internal.instantiation.ClassInstanceCreation;
import org.eclipse.jem.internal.instantiation.ConditionalExpression;
import org.eclipse.jem.internal.instantiation.Expression;
import org.eclipse.jem.internal.instantiation.FieldAccess;
import org.eclipse.jem.internal.instantiation.ImplicitAllocation;
import org.eclipse.jem.internal.instantiation.InfixExpression;
import org.eclipse.jem.internal.instantiation.InfixOperator;
import org.eclipse.jem.internal.instantiation.InitStringAllocation;
import org.eclipse.jem.internal.instantiation.Instanceof;
import org.eclipse.jem.internal.instantiation.InstantiationFactory;
import org.eclipse.jem.internal.instantiation.InstantiationPackage;
import org.eclipse.jem.internal.instantiation.InvalidExpression;
import org.eclipse.jem.internal.instantiation.JavaAllocation;

import org.eclipse.jem.internal.instantiation.MethodInvocation;
import org.eclipse.jem.internal.instantiation.Name;
import org.eclipse.jem.internal.instantiation.NullLiteral;
import org.eclipse.jem.internal.instantiation.NumberLiteral;
import org.eclipse.jem.internal.instantiation.ParenthesizedExpression;
import org.eclipse.jem.internal.instantiation.ParseTreeAllocation;

import org.eclipse.jem.internal.instantiation.PrefixExpression;
import org.eclipse.jem.internal.instantiation.PrefixOperator;
import org.eclipse.jem.internal.instantiation.StringLiteral;
import org.eclipse.jem.internal.instantiation.ThisLiteral;
import org.eclipse.jem.internal.instantiation.TypeLiteral;

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
	private EClass expressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass arrayAccessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass arrayCreationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass arrayInitializerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass booleanLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass castEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass characterLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass classInstanceCreationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conditionalExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fieldAccessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass infixExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass instanceofEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass methodInvocationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nameEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nullLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass numberLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parenthesizedExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass prefixExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass thisLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass invalidExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum infixOperatorEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum prefixOperatorEEnum = null;

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
	public EClass getExpression() {
		return expressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArrayAccess() {
		return arrayAccessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrayAccess_Array() {
		return (EReference)arrayAccessEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrayAccess_Indexes() {
		return (EReference)arrayAccessEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArrayCreation() {
		return arrayCreationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArrayCreation_Type() {
		return (EAttribute)arrayCreationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrayCreation_Dimensions() {
		return (EReference)arrayCreationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrayCreation_Initializer() {
		return (EReference)arrayCreationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArrayInitializer() {
		return arrayInitializerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrayInitializer_Expressions() {
		return (EReference)arrayInitializerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBooleanLiteral() {
		return booleanLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBooleanLiteral_BooleanValue() {
		return (EAttribute)booleanLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCast() {
		return castEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCast_Type() {
		return (EAttribute)castEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCast_Expression() {
		return (EReference)castEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCharacterLiteral() {
		return characterLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCharacterLiteral_EscapedValue() {
		return (EAttribute)characterLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCharacterLiteral_CharValue() {
		return (EAttribute)characterLiteralEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClassInstanceCreation() {
		return classInstanceCreationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClassInstanceCreation_Type() {
		return (EAttribute)classInstanceCreationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassInstanceCreation_Arguments() {
		return (EReference)classInstanceCreationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConditionalExpression() {
		return conditionalExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConditionalExpression_Condition() {
		return (EReference)conditionalExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConditionalExpression_True() {
		return (EReference)conditionalExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConditionalExpression_False() {
		return (EReference)conditionalExpressionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFieldAccess() {
		return fieldAccessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFieldAccess_Receiver() {
		return (EReference)fieldAccessEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFieldAccess_Field() {
		return (EAttribute)fieldAccessEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInfixExpression() {
		return infixExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInfixExpression_LeftOperand() {
		return (EReference)infixExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInfixExpression_Operator() {
		return (EAttribute)infixExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInfixExpression_RightOperand() {
		return (EReference)infixExpressionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInfixExpression_ExtendedOperands() {
		return (EReference)infixExpressionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInstanceof() {
		return instanceofEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInstanceof_Operand() {
		return (EReference)instanceofEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInstanceof_Type() {
		return (EAttribute)instanceofEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMethodInvocation() {
		return methodInvocationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodInvocation_Receiver() {
		return (EReference)methodInvocationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodInvocation_Name() {
		return (EAttribute)methodInvocationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodInvocation_Arguments() {
		return (EReference)methodInvocationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getName_() {
		return nameEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getName_Name() {
		return (EAttribute)nameEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNullLiteral() {
		return nullLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNumberLiteral() {
		return numberLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNumberLiteral_Token() {
		return (EAttribute)numberLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParenthesizedExpression() {
		return parenthesizedExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParenthesizedExpression_Expression() {
		return (EReference)parenthesizedExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrefixExpression() {
		return prefixExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPrefixExpression_Operator() {
		return (EAttribute)prefixExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPrefixExpression_Expression() {
		return (EReference)prefixExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringLiteral() {
		return stringLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStringLiteral_EscapedValue() {
		return (EAttribute)stringLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStringLiteral_LiteralValue() {
		return (EAttribute)stringLiteralEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getThisLiteral() {
		return thisLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTypeLiteral() {
		return typeLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTypeLiteral_Type() {
		return (EAttribute)typeLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInvalidExpression() {
		return invalidExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInvalidExpression_Message() {
		return (EAttribute)invalidExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getInfixOperator() {
		return infixOperatorEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getPrefixOperator() {
		return prefixOperatorEEnum;
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

		expressionEClass = createEClass(EXPRESSION);

		arrayAccessEClass = createEClass(ARRAY_ACCESS);
		createEReference(arrayAccessEClass, ARRAY_ACCESS__ARRAY);
		createEReference(arrayAccessEClass, ARRAY_ACCESS__INDEXES);

		arrayCreationEClass = createEClass(ARRAY_CREATION);
		createEAttribute(arrayCreationEClass, ARRAY_CREATION__TYPE);
		createEReference(arrayCreationEClass, ARRAY_CREATION__DIMENSIONS);
		createEReference(arrayCreationEClass, ARRAY_CREATION__INITIALIZER);

		arrayInitializerEClass = createEClass(ARRAY_INITIALIZER);
		createEReference(arrayInitializerEClass, ARRAY_INITIALIZER__EXPRESSIONS);

		booleanLiteralEClass = createEClass(BOOLEAN_LITERAL);
		createEAttribute(booleanLiteralEClass, BOOLEAN_LITERAL__BOOLEAN_VALUE);

		castEClass = createEClass(CAST);
		createEAttribute(castEClass, CAST__TYPE);
		createEReference(castEClass, CAST__EXPRESSION);

		characterLiteralEClass = createEClass(CHARACTER_LITERAL);
		createEAttribute(characterLiteralEClass, CHARACTER_LITERAL__ESCAPED_VALUE);
		createEAttribute(characterLiteralEClass, CHARACTER_LITERAL__CHAR_VALUE);

		classInstanceCreationEClass = createEClass(CLASS_INSTANCE_CREATION);
		createEAttribute(classInstanceCreationEClass, CLASS_INSTANCE_CREATION__TYPE);
		createEReference(classInstanceCreationEClass, CLASS_INSTANCE_CREATION__ARGUMENTS);

		conditionalExpressionEClass = createEClass(CONDITIONAL_EXPRESSION);
		createEReference(conditionalExpressionEClass, CONDITIONAL_EXPRESSION__CONDITION);
		createEReference(conditionalExpressionEClass, CONDITIONAL_EXPRESSION__TRUE);
		createEReference(conditionalExpressionEClass, CONDITIONAL_EXPRESSION__FALSE);

		fieldAccessEClass = createEClass(FIELD_ACCESS);
		createEReference(fieldAccessEClass, FIELD_ACCESS__RECEIVER);
		createEAttribute(fieldAccessEClass, FIELD_ACCESS__FIELD);

		infixExpressionEClass = createEClass(INFIX_EXPRESSION);
		createEReference(infixExpressionEClass, INFIX_EXPRESSION__LEFT_OPERAND);
		createEAttribute(infixExpressionEClass, INFIX_EXPRESSION__OPERATOR);
		createEReference(infixExpressionEClass, INFIX_EXPRESSION__RIGHT_OPERAND);
		createEReference(infixExpressionEClass, INFIX_EXPRESSION__EXTENDED_OPERANDS);

		instanceofEClass = createEClass(INSTANCEOF);
		createEReference(instanceofEClass, INSTANCEOF__OPERAND);
		createEAttribute(instanceofEClass, INSTANCEOF__TYPE);

		methodInvocationEClass = createEClass(METHOD_INVOCATION);
		createEReference(methodInvocationEClass, METHOD_INVOCATION__RECEIVER);
		createEAttribute(methodInvocationEClass, METHOD_INVOCATION__NAME);
		createEReference(methodInvocationEClass, METHOD_INVOCATION__ARGUMENTS);

		nameEClass = createEClass(NAME);
		createEAttribute(nameEClass, NAME__NAME);

		nullLiteralEClass = createEClass(NULL_LITERAL);

		numberLiteralEClass = createEClass(NUMBER_LITERAL);
		createEAttribute(numberLiteralEClass, NUMBER_LITERAL__TOKEN);

		parenthesizedExpressionEClass = createEClass(PARENTHESIZED_EXPRESSION);
		createEReference(parenthesizedExpressionEClass, PARENTHESIZED_EXPRESSION__EXPRESSION);

		prefixExpressionEClass = createEClass(PREFIX_EXPRESSION);
		createEAttribute(prefixExpressionEClass, PREFIX_EXPRESSION__OPERATOR);
		createEReference(prefixExpressionEClass, PREFIX_EXPRESSION__EXPRESSION);

		stringLiteralEClass = createEClass(STRING_LITERAL);
		createEAttribute(stringLiteralEClass, STRING_LITERAL__ESCAPED_VALUE);
		createEAttribute(stringLiteralEClass, STRING_LITERAL__LITERAL_VALUE);

		thisLiteralEClass = createEClass(THIS_LITERAL);

		typeLiteralEClass = createEClass(TYPE_LITERAL);
		createEAttribute(typeLiteralEClass, TYPE_LITERAL__TYPE);

		invalidExpressionEClass = createEClass(INVALID_EXPRESSION);
		createEAttribute(invalidExpressionEClass, INVALID_EXPRESSION__MESSAGE);

		// Create enums
		infixOperatorEEnum = createEEnum(INFIX_OPERATOR);
		prefixOperatorEEnum = createEEnum(PREFIX_OPERATOR);
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
		arrayAccessEClass.getESuperTypes().add(this.getExpression());
		arrayCreationEClass.getESuperTypes().add(this.getExpression());
		arrayInitializerEClass.getESuperTypes().add(this.getExpression());
		booleanLiteralEClass.getESuperTypes().add(this.getExpression());
		castEClass.getESuperTypes().add(this.getExpression());
		characterLiteralEClass.getESuperTypes().add(this.getExpression());
		classInstanceCreationEClass.getESuperTypes().add(this.getExpression());
		conditionalExpressionEClass.getESuperTypes().add(this.getExpression());
		fieldAccessEClass.getESuperTypes().add(this.getExpression());
		infixExpressionEClass.getESuperTypes().add(this.getExpression());
		instanceofEClass.getESuperTypes().add(this.getExpression());
		methodInvocationEClass.getESuperTypes().add(this.getExpression());
		nameEClass.getESuperTypes().add(this.getExpression());
		nullLiteralEClass.getESuperTypes().add(this.getExpression());
		numberLiteralEClass.getESuperTypes().add(this.getExpression());
		parenthesizedExpressionEClass.getESuperTypes().add(this.getExpression());
		prefixExpressionEClass.getESuperTypes().add(this.getExpression());
		stringLiteralEClass.getESuperTypes().add(this.getExpression());
		thisLiteralEClass.getESuperTypes().add(this.getExpression());
		typeLiteralEClass.getESuperTypes().add(this.getExpression());
		invalidExpressionEClass.getESuperTypes().add(this.getExpression());

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
		initEReference(getParseTreeAllocation_Expression(), this.getExpression(), null, "expression", null, 1, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);

		initEClass(expressionEClass, Expression.class, "Expression", IS_ABSTRACT, !IS_INTERFACE);

		initEClass(arrayAccessEClass, ArrayAccess.class, "ArrayAccess", !IS_ABSTRACT, !IS_INTERFACE);
		initEReference(getArrayAccess_Array(), this.getExpression(), null, "array", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);
		initEReference(getArrayAccess_Indexes(), this.getExpression(), null, "indexes", null, 1, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);

		initEClass(arrayCreationEClass, ArrayCreation.class, "ArrayCreation", !IS_ABSTRACT, !IS_INTERFACE);
		initEAttribute(getArrayCreation_Type(), ecorePackage.getEString(), "type", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);
		initEReference(getArrayCreation_Dimensions(), this.getExpression(), null, "dimensions", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);
		initEReference(getArrayCreation_Initializer(), this.getArrayInitializer(), null, "initializer", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);

		initEClass(arrayInitializerEClass, ArrayInitializer.class, "ArrayInitializer", !IS_ABSTRACT, !IS_INTERFACE);
		initEReference(getArrayInitializer_Expressions(), this.getExpression(), null, "expressions", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);

		initEClass(booleanLiteralEClass, BooleanLiteral.class, "BooleanLiteral", !IS_ABSTRACT, !IS_INTERFACE);
		initEAttribute(getBooleanLiteral_BooleanValue(), ecorePackage.getEBoolean(), "booleanValue", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);

		initEClass(castEClass, Cast.class, "Cast", !IS_ABSTRACT, !IS_INTERFACE);
		initEAttribute(getCast_Type(), ecorePackage.getEString(), "type", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);
		initEReference(getCast_Expression(), this.getExpression(), null, "expression", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);

		initEClass(characterLiteralEClass, CharacterLiteral.class, "CharacterLiteral", !IS_ABSTRACT, !IS_INTERFACE);
		initEAttribute(getCharacterLiteral_EscapedValue(), ecorePackage.getEString(), "escapedValue", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);
		initEAttribute(getCharacterLiteral_CharValue(), ecorePackage.getEChar(), "charValue", null, 0, 1, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);

		initEClass(classInstanceCreationEClass, ClassInstanceCreation.class, "ClassInstanceCreation", !IS_ABSTRACT, !IS_INTERFACE);
		initEAttribute(getClassInstanceCreation_Type(), ecorePackage.getEString(), "type", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);
		initEReference(getClassInstanceCreation_Arguments(), this.getExpression(), null, "arguments", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);

		initEClass(conditionalExpressionEClass, ConditionalExpression.class, "ConditionalExpression", !IS_ABSTRACT, !IS_INTERFACE);
		initEReference(getConditionalExpression_Condition(), this.getExpression(), null, "condition", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);
		initEReference(getConditionalExpression_True(), this.getExpression(), null, "true", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);
		initEReference(getConditionalExpression_False(), this.getExpression(), null, "false", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);

		initEClass(fieldAccessEClass, FieldAccess.class, "FieldAccess", !IS_ABSTRACT, !IS_INTERFACE);
		initEReference(getFieldAccess_Receiver(), this.getExpression(), null, "receiver", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);
		initEAttribute(getFieldAccess_Field(), ecorePackage.getEString(), "field", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);

		initEClass(infixExpressionEClass, InfixExpression.class, "InfixExpression", !IS_ABSTRACT, !IS_INTERFACE);
		initEReference(getInfixExpression_LeftOperand(), this.getExpression(), null, "leftOperand", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);
		initEAttribute(getInfixExpression_Operator(), this.getInfixOperator(), "operator", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);
		initEReference(getInfixExpression_RightOperand(), this.getExpression(), null, "rightOperand", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);
		initEReference(getInfixExpression_ExtendedOperands(), this.getExpression(), null, "extendedOperands", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);

		initEClass(instanceofEClass, Instanceof.class, "Instanceof", !IS_ABSTRACT, !IS_INTERFACE);
		initEReference(getInstanceof_Operand(), this.getExpression(), null, "operand", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);
		initEAttribute(getInstanceof_Type(), ecorePackage.getEString(), "type", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);

		initEClass(methodInvocationEClass, MethodInvocation.class, "MethodInvocation", !IS_ABSTRACT, !IS_INTERFACE);
		initEReference(getMethodInvocation_Receiver(), this.getExpression(), null, "receiver", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);
		initEAttribute(getMethodInvocation_Name(), ecorePackage.getEString(), "name", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);
		initEReference(getMethodInvocation_Arguments(), this.getExpression(), null, "arguments", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);

		initEClass(nameEClass, Name.class, "Name", !IS_ABSTRACT, !IS_INTERFACE);
		initEAttribute(getName_Name(), ecorePackage.getEString(), "name", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);

		initEClass(nullLiteralEClass, NullLiteral.class, "NullLiteral", !IS_ABSTRACT, !IS_INTERFACE);

		initEClass(numberLiteralEClass, NumberLiteral.class, "NumberLiteral", !IS_ABSTRACT, !IS_INTERFACE);
		initEAttribute(getNumberLiteral_Token(), ecorePackage.getEString(), "token", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);

		initEClass(parenthesizedExpressionEClass, ParenthesizedExpression.class, "ParenthesizedExpression", !IS_ABSTRACT, !IS_INTERFACE);
		initEReference(getParenthesizedExpression_Expression(), this.getExpression(), null, "expression", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);

		initEClass(prefixExpressionEClass, PrefixExpression.class, "PrefixExpression", !IS_ABSTRACT, !IS_INTERFACE);
		initEAttribute(getPrefixExpression_Operator(), this.getPrefixOperator(), "operator", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);
		initEReference(getPrefixExpression_Expression(), this.getExpression(), null, "expression", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);

		initEClass(stringLiteralEClass, StringLiteral.class, "StringLiteral", !IS_ABSTRACT, !IS_INTERFACE);
		initEAttribute(getStringLiteral_EscapedValue(), ecorePackage.getEString(), "escapedValue", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);
		initEAttribute(getStringLiteral_LiteralValue(), ecorePackage.getEString(), "literalValue", null, 0, 1, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);

		initEClass(thisLiteralEClass, ThisLiteral.class, "ThisLiteral", !IS_ABSTRACT, !IS_INTERFACE);

		initEClass(typeLiteralEClass, TypeLiteral.class, "TypeLiteral", !IS_ABSTRACT, !IS_INTERFACE);
		initEAttribute(getTypeLiteral_Type(), ecorePackage.getEString(), "type", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);

		initEClass(invalidExpressionEClass, InvalidExpression.class, "InvalidExpression", !IS_ABSTRACT, !IS_INTERFACE);
		initEAttribute(getInvalidExpression_Message(), ecorePackage.getEString(), "message", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED);

		// Initialize enums and add enum literals
		initEEnum(infixOperatorEEnum, InfixOperator.class, "InfixOperator");
		addEEnumLiteral(infixOperatorEEnum, InfixOperator.TIMES_LITERAL);
		addEEnumLiteral(infixOperatorEEnum, InfixOperator.DIVIDE_LITERAL);
		addEEnumLiteral(infixOperatorEEnum, InfixOperator.REMAINDER_LITERAL);
		addEEnumLiteral(infixOperatorEEnum, InfixOperator.PLUS_LITERAL);
		addEEnumLiteral(infixOperatorEEnum, InfixOperator.MINUS_LITERAL);
		addEEnumLiteral(infixOperatorEEnum, InfixOperator.LEFT_SHIFT_LITERAL);
		addEEnumLiteral(infixOperatorEEnum, InfixOperator.RIGHT_SHIFT_SIGNED_LITERAL);
		addEEnumLiteral(infixOperatorEEnum, InfixOperator.RIGHT_SHIFT_UNSIGNED_LITERAL);
		addEEnumLiteral(infixOperatorEEnum, InfixOperator.LESS_LITERAL);
		addEEnumLiteral(infixOperatorEEnum, InfixOperator.GREATER_LITERAL);
		addEEnumLiteral(infixOperatorEEnum, InfixOperator.LESS_EQUALS_LITERAL);
		addEEnumLiteral(infixOperatorEEnum, InfixOperator.GREATER_EQUALS_LITERAL);
		addEEnumLiteral(infixOperatorEEnum, InfixOperator.EQUALS_LITERAL);
		addEEnumLiteral(infixOperatorEEnum, InfixOperator.NOT_EQUALS_LITERAL);
		addEEnumLiteral(infixOperatorEEnum, InfixOperator.XOR_LITERAL);
		addEEnumLiteral(infixOperatorEEnum, InfixOperator.AND_LITERAL);
		addEEnumLiteral(infixOperatorEEnum, InfixOperator.OR_LITERAL);
		addEEnumLiteral(infixOperatorEEnum, InfixOperator.CONDITIONAL_ADD_LITERAL);
		addEEnumLiteral(infixOperatorEEnum, InfixOperator.CONDITIONAL_OR_LITERAL);

		initEEnum(prefixOperatorEEnum, PrefixOperator.class, "PrefixOperator");
		addEEnumLiteral(prefixOperatorEEnum, PrefixOperator.PLUS_LITERAL);
		addEEnumLiteral(prefixOperatorEEnum, PrefixOperator.MINUS_LITERAL);
		addEEnumLiteral(prefixOperatorEEnum, PrefixOperator.COMPLEMENT_LITERAL);
		addEEnumLiteral(prefixOperatorEEnum, PrefixOperator.NOT_LITERAL);

		// Create resource
		createResource(eNS_URI);
	}
} //InstantiationPackageImpl
