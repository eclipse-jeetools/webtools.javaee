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
 *  $Revision: 1.4 $  $Date: 2004/01/19 22:50:15 $ 
 */

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
	 * The number of structural features of the the '<em>Java Allocation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_ALLOCATION_FEATURE_COUNT = 0;

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
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.ParseTreeAllocationImpl <em>Parse Tree Allocation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.ParseTreeAllocationImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getParseTreeAllocation()
	 * @generated
	 */
	int PARSE_TREE_ALLOCATION = 6;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARSE_TREE_ALLOCATION__EXPRESSION = JAVA_ALLOCATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the the '<em>Parse Tree Allocation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARSE_TREE_ALLOCATION_FEATURE_COUNT = JAVA_ALLOCATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.ExpressionImpl <em>Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.ExpressionImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getExpression()
	 * @generated
	 */
	int EXPRESSION = 7;

	/**
	 * The number of structural features of the the '<em>Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.ArrayAccessImpl <em>Array Access</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.ArrayAccessImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getArrayAccess()
	 * @generated
	 */
	int ARRAY_ACCESS = 8;

	/**
	 * The feature id for the '<em><b>Array</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_ACCESS__ARRAY = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Indexes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_ACCESS__INDEXES = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the the '<em>Array Access</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_ACCESS_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;


	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.ArrayCreationImpl <em>Array Creation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.ArrayCreationImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getArrayCreation()
	 * @generated
	 */
	int ARRAY_CREATION = 9;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_CREATION__TYPE = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dimensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_CREATION__DIMENSIONS = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Initializer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_CREATION__INITIALIZER = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the the '<em>Array Creation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_CREATION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.ArrayInitializerImpl <em>Array Initializer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.ArrayInitializerImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getArrayInitializer()
	 * @generated
	 */
	int ARRAY_INITIALIZER = 10;

	/**
	 * The feature id for the '<em><b>Expressions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_INITIALIZER__EXPRESSIONS = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the the '<em>Array Initializer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_INITIALIZER_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.BooleanLiteralImpl <em>Boolean Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.BooleanLiteralImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getBooleanLiteral()
	 * @generated
	 */
	int BOOLEAN_LITERAL = 11;

	/**
	 * The feature id for the '<em><b>Boolean Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_LITERAL__BOOLEAN_VALUE = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the the '<em>Boolean Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.CastImpl <em>Cast</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.CastImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getCast()
	 * @generated
	 */
	int CAST = 12;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAST__TYPE = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAST__EXPRESSION = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the the '<em>Cast</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAST_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.CharacterLiteralImpl <em>Character Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.CharacterLiteralImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getCharacterLiteral()
	 * @generated
	 */
	int CHARACTER_LITERAL = 13;

	/**
	 * The feature id for the '<em><b>Escaped Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_LITERAL__ESCAPED_VALUE = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Char Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_LITERAL__CHAR_VALUE = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the the '<em>Character Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.ClassInstanceCreationImpl <em>Class Instance Creation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.ClassInstanceCreationImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getClassInstanceCreation()
	 * @generated
	 */
	int CLASS_INSTANCE_CREATION = 14;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_INSTANCE_CREATION__TYPE = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_INSTANCE_CREATION__ARGUMENTS = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the the '<em>Class Instance Creation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_INSTANCE_CREATION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.ConditionalExpressionImpl <em>Conditional Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.ConditionalExpressionImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getConditionalExpression()
	 * @generated
	 */
	int CONDITIONAL_EXPRESSION = 15;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_EXPRESSION__CONDITION = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>True</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_EXPRESSION__TRUE = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>False</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_EXPRESSION__FALSE = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the the '<em>Conditional Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.FieldAccessImpl <em>Field Access</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.FieldAccessImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getFieldAccess()
	 * @generated
	 */
	int FIELD_ACCESS = 16;

	/**
	 * The feature id for the '<em><b>Receiver</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_ACCESS__RECEIVER = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Field</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_ACCESS__FIELD = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the the '<em>Field Access</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_ACCESS_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.InfixExpressionImpl <em>Infix Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.InfixExpressionImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getInfixExpression()
	 * @generated
	 */
	int INFIX_EXPRESSION = 17;

	/**
	 * The feature id for the '<em><b>Left Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFIX_EXPRESSION__LEFT_OPERAND = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFIX_EXPRESSION__OPERATOR = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Right Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFIX_EXPRESSION__RIGHT_OPERAND = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Extended Operands</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFIX_EXPRESSION__EXTENDED_OPERANDS = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the the '<em>Infix Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFIX_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.InstanceofImpl <em>Instanceof</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.InstanceofImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getInstanceof()
	 * @generated
	 */
	int INSTANCEOF = 18;

	/**
	 * The feature id for the '<em><b>Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCEOF__OPERAND = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCEOF__TYPE = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the the '<em>Instanceof</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCEOF_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.MethodInvocationImpl <em>Method Invocation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.MethodInvocationImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getMethodInvocation()
	 * @generated
	 */
	int METHOD_INVOCATION = 19;

	/**
	 * The feature id for the '<em><b>Receiver</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_INVOCATION__RECEIVER = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_INVOCATION__NAME = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_INVOCATION__ARGUMENTS = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the the '<em>Method Invocation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_INVOCATION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.NameImpl <em>Name</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.NameImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getName_()
	 * @generated
	 */
	int NAME = 20;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME__NAME = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the the '<em>Name</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.NullLiteralImpl <em>Null Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.NullLiteralImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getNullLiteral()
	 * @generated
	 */
	int NULL_LITERAL = 21;

	/**
	 * The number of structural features of the the '<em>Null Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NULL_LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.NumberLiteralImpl <em>Number Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.NumberLiteralImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getNumberLiteral()
	 * @generated
	 */
	int NUMBER_LITERAL = 22;

	/**
	 * The feature id for the '<em><b>Token</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_LITERAL__TOKEN = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the the '<em>Number Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.ParenthesizedExpressionImpl <em>Parenthesized Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.ParenthesizedExpressionImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getParenthesizedExpression()
	 * @generated
	 */
	int PARENTHESIZED_EXPRESSION = 23;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARENTHESIZED_EXPRESSION__EXPRESSION = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the the '<em>Parenthesized Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARENTHESIZED_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.PrefixExpressionImpl <em>Prefix Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.PrefixExpressionImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getPrefixExpression()
	 * @generated
	 */
	int PREFIX_EXPRESSION = 24;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFIX_EXPRESSION__OPERATOR = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFIX_EXPRESSION__EXPRESSION = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the the '<em>Prefix Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFIX_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.StringLiteralImpl <em>String Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.StringLiteralImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getStringLiteral()
	 * @generated
	 */
	int STRING_LITERAL = 25;

	/**
	 * The feature id for the '<em><b>Escaped Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_LITERAL__ESCAPED_VALUE = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Literal Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_LITERAL__LITERAL_VALUE = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the the '<em>String Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.ThisLiteralImpl <em>This Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.ThisLiteralImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getThisLiteral()
	 * @generated
	 */
	int THIS_LITERAL = 26;

	/**
	 * The number of structural features of the the '<em>This Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THIS_LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.TypeLiteralImpl <em>Type Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.TypeLiteralImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getTypeLiteral()
	 * @generated
	 */
	int TYPE_LITERAL = 27;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_LITERAL__TYPE = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the the '<em>Type Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.InvalidExpressionImpl <em>Invalid Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.InvalidExpressionImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getInvalidExpression()
	 * @generated
	 */
	int INVALID_EXPRESSION = 28;

	/**
	 * The feature id for the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_EXPRESSION__MESSAGE = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the the '<em>Invalid Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.InfixOperator <em>Infix Operator</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.InfixOperator
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getInfixOperator()
	 * @generated
	 */
	int INFIX_OPERATOR = 29;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.PrefixOperator <em>Prefix Operator</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.PrefixOperator
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getPrefixOperator()
	 * @generated
	 */
	int PREFIX_OPERATOR = 30;


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
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.ParseTreeAllocation <em>Parse Tree Allocation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parse Tree Allocation</em>'.
	 * @see org.eclipse.jem.internal.instantiation.ParseTreeAllocation
	 * @generated
	 */
	EClass getParseTreeAllocation();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jem.internal.instantiation.ParseTreeAllocation#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see org.eclipse.jem.internal.instantiation.ParseTreeAllocation#getExpression()
	 * @see #getParseTreeAllocation()
	 * @generated
	 */
	EReference getParseTreeAllocation_Expression();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.Expression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expression</em>'.
	 * @see org.eclipse.jem.internal.instantiation.Expression
	 * @generated
	 */
	EClass getExpression();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.ArrayAccess <em>Array Access</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Array Access</em>'.
	 * @see org.eclipse.jem.internal.instantiation.ArrayAccess
	 * @generated
	 */
	EClass getArrayAccess();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jem.internal.instantiation.ArrayAccess#getArray <em>Array</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Array</em>'.
	 * @see org.eclipse.jem.internal.instantiation.ArrayAccess#getArray()
	 * @see #getArrayAccess()
	 * @generated
	 */
	EReference getArrayAccess_Array();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jem.internal.instantiation.ArrayAccess#getIndexes <em>Indexes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Indexes</em>'.
	 * @see org.eclipse.jem.internal.instantiation.ArrayAccess#getIndexes()
	 * @see #getArrayAccess()
	 * @generated
	 */
	EReference getArrayAccess_Indexes();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.ArrayCreation <em>Array Creation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Array Creation</em>'.
	 * @see org.eclipse.jem.internal.instantiation.ArrayCreation
	 * @generated
	 */
	EClass getArrayCreation();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.instantiation.ArrayCreation#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.jem.internal.instantiation.ArrayCreation#getType()
	 * @see #getArrayCreation()
	 * @generated
	 */
	EAttribute getArrayCreation_Type();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jem.internal.instantiation.ArrayCreation#getDimensions <em>Dimensions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Dimensions</em>'.
	 * @see org.eclipse.jem.internal.instantiation.ArrayCreation#getDimensions()
	 * @see #getArrayCreation()
	 * @generated
	 */
	EReference getArrayCreation_Dimensions();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jem.internal.instantiation.ArrayCreation#getInitializer <em>Initializer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Initializer</em>'.
	 * @see org.eclipse.jem.internal.instantiation.ArrayCreation#getInitializer()
	 * @see #getArrayCreation()
	 * @generated
	 */
	EReference getArrayCreation_Initializer();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.ArrayInitializer <em>Array Initializer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Array Initializer</em>'.
	 * @see org.eclipse.jem.internal.instantiation.ArrayInitializer
	 * @generated
	 */
	EClass getArrayInitializer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jem.internal.instantiation.ArrayInitializer#getExpressions <em>Expressions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Expressions</em>'.
	 * @see org.eclipse.jem.internal.instantiation.ArrayInitializer#getExpressions()
	 * @see #getArrayInitializer()
	 * @generated
	 */
	EReference getArrayInitializer_Expressions();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.BooleanLiteral <em>Boolean Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Literal</em>'.
	 * @see org.eclipse.jem.internal.instantiation.BooleanLiteral
	 * @generated
	 */
	EClass getBooleanLiteral();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.instantiation.BooleanLiteral#isBooleanValue <em>Boolean Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Boolean Value</em>'.
	 * @see org.eclipse.jem.internal.instantiation.BooleanLiteral#isBooleanValue()
	 * @see #getBooleanLiteral()
	 * @generated
	 */
	EAttribute getBooleanLiteral_BooleanValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.Cast <em>Cast</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cast</em>'.
	 * @see org.eclipse.jem.internal.instantiation.Cast
	 * @generated
	 */
	EClass getCast();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.instantiation.Cast#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.jem.internal.instantiation.Cast#getType()
	 * @see #getCast()
	 * @generated
	 */
	EAttribute getCast_Type();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jem.internal.instantiation.Cast#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see org.eclipse.jem.internal.instantiation.Cast#getExpression()
	 * @see #getCast()
	 * @generated
	 */
	EReference getCast_Expression();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.CharacterLiteral <em>Character Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Character Literal</em>'.
	 * @see org.eclipse.jem.internal.instantiation.CharacterLiteral
	 * @generated
	 */
	EClass getCharacterLiteral();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.instantiation.CharacterLiteral#getEscapedValue <em>Escaped Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Escaped Value</em>'.
	 * @see org.eclipse.jem.internal.instantiation.CharacterLiteral#getEscapedValue()
	 * @see #getCharacterLiteral()
	 * @generated
	 */
	EAttribute getCharacterLiteral_EscapedValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.instantiation.CharacterLiteral#getCharValue <em>Char Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Char Value</em>'.
	 * @see org.eclipse.jem.internal.instantiation.CharacterLiteral#getCharValue()
	 * @see #getCharacterLiteral()
	 * @generated
	 */
	EAttribute getCharacterLiteral_CharValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.ClassInstanceCreation <em>Class Instance Creation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class Instance Creation</em>'.
	 * @see org.eclipse.jem.internal.instantiation.ClassInstanceCreation
	 * @generated
	 */
	EClass getClassInstanceCreation();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.instantiation.ClassInstanceCreation#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.jem.internal.instantiation.ClassInstanceCreation#getType()
	 * @see #getClassInstanceCreation()
	 * @generated
	 */
	EAttribute getClassInstanceCreation_Type();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jem.internal.instantiation.ClassInstanceCreation#getArguments <em>Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Arguments</em>'.
	 * @see org.eclipse.jem.internal.instantiation.ClassInstanceCreation#getArguments()
	 * @see #getClassInstanceCreation()
	 * @generated
	 */
	EReference getClassInstanceCreation_Arguments();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.ConditionalExpression <em>Conditional Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Conditional Expression</em>'.
	 * @see org.eclipse.jem.internal.instantiation.ConditionalExpression
	 * @generated
	 */
	EClass getConditionalExpression();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jem.internal.instantiation.ConditionalExpression#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Condition</em>'.
	 * @see org.eclipse.jem.internal.instantiation.ConditionalExpression#getCondition()
	 * @see #getConditionalExpression()
	 * @generated
	 */
	EReference getConditionalExpression_Condition();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jem.internal.instantiation.ConditionalExpression#getTrue <em>True</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>True</em>'.
	 * @see org.eclipse.jem.internal.instantiation.ConditionalExpression#getTrue()
	 * @see #getConditionalExpression()
	 * @generated
	 */
	EReference getConditionalExpression_True();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jem.internal.instantiation.ConditionalExpression#getFalse <em>False</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>False</em>'.
	 * @see org.eclipse.jem.internal.instantiation.ConditionalExpression#getFalse()
	 * @see #getConditionalExpression()
	 * @generated
	 */
	EReference getConditionalExpression_False();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.FieldAccess <em>Field Access</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Field Access</em>'.
	 * @see org.eclipse.jem.internal.instantiation.FieldAccess
	 * @generated
	 */
	EClass getFieldAccess();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jem.internal.instantiation.FieldAccess#getReceiver <em>Receiver</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Receiver</em>'.
	 * @see org.eclipse.jem.internal.instantiation.FieldAccess#getReceiver()
	 * @see #getFieldAccess()
	 * @generated
	 */
	EReference getFieldAccess_Receiver();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.instantiation.FieldAccess#getField <em>Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Field</em>'.
	 * @see org.eclipse.jem.internal.instantiation.FieldAccess#getField()
	 * @see #getFieldAccess()
	 * @generated
	 */
	EAttribute getFieldAccess_Field();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.InfixExpression <em>Infix Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Infix Expression</em>'.
	 * @see org.eclipse.jem.internal.instantiation.InfixExpression
	 * @generated
	 */
	EClass getInfixExpression();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jem.internal.instantiation.InfixExpression#getLeftOperand <em>Left Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left Operand</em>'.
	 * @see org.eclipse.jem.internal.instantiation.InfixExpression#getLeftOperand()
	 * @see #getInfixExpression()
	 * @generated
	 */
	EReference getInfixExpression_LeftOperand();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.instantiation.InfixExpression#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see org.eclipse.jem.internal.instantiation.InfixExpression#getOperator()
	 * @see #getInfixExpression()
	 * @generated
	 */
	EAttribute getInfixExpression_Operator();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jem.internal.instantiation.InfixExpression#getRightOperand <em>Right Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right Operand</em>'.
	 * @see org.eclipse.jem.internal.instantiation.InfixExpression#getRightOperand()
	 * @see #getInfixExpression()
	 * @generated
	 */
	EReference getInfixExpression_RightOperand();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jem.internal.instantiation.InfixExpression#getExtendedOperands <em>Extended Operands</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Extended Operands</em>'.
	 * @see org.eclipse.jem.internal.instantiation.InfixExpression#getExtendedOperands()
	 * @see #getInfixExpression()
	 * @generated
	 */
	EReference getInfixExpression_ExtendedOperands();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.Instanceof <em>Instanceof</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Instanceof</em>'.
	 * @see org.eclipse.jem.internal.instantiation.Instanceof
	 * @generated
	 */
	EClass getInstanceof();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jem.internal.instantiation.Instanceof#getOperand <em>Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Operand</em>'.
	 * @see org.eclipse.jem.internal.instantiation.Instanceof#getOperand()
	 * @see #getInstanceof()
	 * @generated
	 */
	EReference getInstanceof_Operand();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.instantiation.Instanceof#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.jem.internal.instantiation.Instanceof#getType()
	 * @see #getInstanceof()
	 * @generated
	 */
	EAttribute getInstanceof_Type();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.MethodInvocation <em>Method Invocation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Method Invocation</em>'.
	 * @see org.eclipse.jem.internal.instantiation.MethodInvocation
	 * @generated
	 */
	EClass getMethodInvocation();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jem.internal.instantiation.MethodInvocation#getReceiver <em>Receiver</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Receiver</em>'.
	 * @see org.eclipse.jem.internal.instantiation.MethodInvocation#getReceiver()
	 * @see #getMethodInvocation()
	 * @generated
	 */
	EReference getMethodInvocation_Receiver();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.instantiation.MethodInvocation#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.jem.internal.instantiation.MethodInvocation#getName()
	 * @see #getMethodInvocation()
	 * @generated
	 */
	EAttribute getMethodInvocation_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jem.internal.instantiation.MethodInvocation#getArguments <em>Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Arguments</em>'.
	 * @see org.eclipse.jem.internal.instantiation.MethodInvocation#getArguments()
	 * @see #getMethodInvocation()
	 * @generated
	 */
	EReference getMethodInvocation_Arguments();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.Name <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Name</em>'.
	 * @see org.eclipse.jem.internal.instantiation.Name
	 * @generated
	 */
	EClass getName_();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.instantiation.Name#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.jem.internal.instantiation.Name#getName()
	 * @see #getName_()
	 * @generated
	 */
	EAttribute getName_Name();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.NullLiteral <em>Null Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Null Literal</em>'.
	 * @see org.eclipse.jem.internal.instantiation.NullLiteral
	 * @generated
	 */
	EClass getNullLiteral();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.NumberLiteral <em>Number Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Number Literal</em>'.
	 * @see org.eclipse.jem.internal.instantiation.NumberLiteral
	 * @generated
	 */
	EClass getNumberLiteral();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.instantiation.NumberLiteral#getToken <em>Token</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Token</em>'.
	 * @see org.eclipse.jem.internal.instantiation.NumberLiteral#getToken()
	 * @see #getNumberLiteral()
	 * @generated
	 */
	EAttribute getNumberLiteral_Token();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.ParenthesizedExpression <em>Parenthesized Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parenthesized Expression</em>'.
	 * @see org.eclipse.jem.internal.instantiation.ParenthesizedExpression
	 * @generated
	 */
	EClass getParenthesizedExpression();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jem.internal.instantiation.ParenthesizedExpression#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see org.eclipse.jem.internal.instantiation.ParenthesizedExpression#getExpression()
	 * @see #getParenthesizedExpression()
	 * @generated
	 */
	EReference getParenthesizedExpression_Expression();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.PrefixExpression <em>Prefix Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Prefix Expression</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PrefixExpression
	 * @generated
	 */
	EClass getPrefixExpression();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.instantiation.PrefixExpression#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PrefixExpression#getOperator()
	 * @see #getPrefixExpression()
	 * @generated
	 */
	EAttribute getPrefixExpression_Operator();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jem.internal.instantiation.PrefixExpression#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PrefixExpression#getExpression()
	 * @see #getPrefixExpression()
	 * @generated
	 */
	EReference getPrefixExpression_Expression();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.StringLiteral <em>String Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Literal</em>'.
	 * @see org.eclipse.jem.internal.instantiation.StringLiteral
	 * @generated
	 */
	EClass getStringLiteral();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.instantiation.StringLiteral#getEscapedValue <em>Escaped Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Escaped Value</em>'.
	 * @see org.eclipse.jem.internal.instantiation.StringLiteral#getEscapedValue()
	 * @see #getStringLiteral()
	 * @generated
	 */
	EAttribute getStringLiteral_EscapedValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.instantiation.StringLiteral#getLiteralValue <em>Literal Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Literal Value</em>'.
	 * @see org.eclipse.jem.internal.instantiation.StringLiteral#getLiteralValue()
	 * @see #getStringLiteral()
	 * @generated
	 */
	EAttribute getStringLiteral_LiteralValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.ThisLiteral <em>This Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>This Literal</em>'.
	 * @see org.eclipse.jem.internal.instantiation.ThisLiteral
	 * @generated
	 */
	EClass getThisLiteral();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.TypeLiteral <em>Type Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Literal</em>'.
	 * @see org.eclipse.jem.internal.instantiation.TypeLiteral
	 * @generated
	 */
	EClass getTypeLiteral();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.instantiation.TypeLiteral#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.jem.internal.instantiation.TypeLiteral#getType()
	 * @see #getTypeLiteral()
	 * @generated
	 */
	EAttribute getTypeLiteral_Type();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.InvalidExpression <em>Invalid Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Invalid Expression</em>'.
	 * @see org.eclipse.jem.internal.instantiation.InvalidExpression
	 * @generated
	 */
	EClass getInvalidExpression();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.instantiation.InvalidExpression#getMessage <em>Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message</em>'.
	 * @see org.eclipse.jem.internal.instantiation.InvalidExpression#getMessage()
	 * @see #getInvalidExpression()
	 * @generated
	 */
	EAttribute getInvalidExpression_Message();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.jem.internal.instantiation.InfixOperator <em>Infix Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Infix Operator</em>'.
	 * @see org.eclipse.jem.internal.instantiation.InfixOperator
	 * @generated
	 */
	EEnum getInfixOperator();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.jem.internal.instantiation.PrefixOperator <em>Prefix Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Prefix Operator</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PrefixOperator
	 * @generated
	 */
	EEnum getPrefixOperator();

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
