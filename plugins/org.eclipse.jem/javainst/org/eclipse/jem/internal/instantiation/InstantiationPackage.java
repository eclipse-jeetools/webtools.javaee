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
package org.eclipse.jem.internal.instantiation;
/*
 *  $RCSfile: InstantiationPackage.java,v $
 *  $Revision: 1.11 $  $Date: 2005/10/03 19:20:41 $ 
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
 * @model kind="package"
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
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.PTExpressionImpl <em>PT Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.PTExpressionImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getPTExpression()
	 * @generated
	 */
	int PT_EXPRESSION = 7;

	/**
	 * The number of structural features of the the '<em>PT Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_EXPRESSION_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.PTArrayAccessImpl <em>PT Array Access</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.PTArrayAccessImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getPTArrayAccess()
	 * @generated
	 */
	int PT_ARRAY_ACCESS = 8;

	/**
	 * The feature id for the '<em><b>Array</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_ARRAY_ACCESS__ARRAY = PT_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Indexes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_ARRAY_ACCESS__INDEXES = PT_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the the '<em>PT Array Access</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_ARRAY_ACCESS_FEATURE_COUNT = PT_EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.PTArrayCreationImpl <em>PT Array Creation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.PTArrayCreationImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getPTArrayCreation()
	 * @generated
	 */
	int PT_ARRAY_CREATION = 9;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_ARRAY_CREATION__TYPE = PT_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dimensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_ARRAY_CREATION__DIMENSIONS = PT_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Initializer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_ARRAY_CREATION__INITIALIZER = PT_EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the the '<em>PT Array Creation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_ARRAY_CREATION_FEATURE_COUNT = PT_EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.PTArrayInitializerImpl <em>PT Array Initializer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.PTArrayInitializerImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getPTArrayInitializer()
	 * @generated
	 */
	int PT_ARRAY_INITIALIZER = 10;

	/**
	 * The feature id for the '<em><b>Expressions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_ARRAY_INITIALIZER__EXPRESSIONS = PT_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the the '<em>PT Array Initializer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_ARRAY_INITIALIZER_FEATURE_COUNT = PT_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.PTBooleanLiteralImpl <em>PT Boolean Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.PTBooleanLiteralImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getPTBooleanLiteral()
	 * @generated
	 */
	int PT_BOOLEAN_LITERAL = 11;

	/**
	 * The feature id for the '<em><b>Boolean Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_BOOLEAN_LITERAL__BOOLEAN_VALUE = PT_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the the '<em>PT Boolean Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_BOOLEAN_LITERAL_FEATURE_COUNT = PT_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.PTCastExpressionImpl <em>PT Cast Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.PTCastExpressionImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getPTCastExpression()
	 * @generated
	 */
	int PT_CAST_EXPRESSION = 12;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_CAST_EXPRESSION__TYPE = PT_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_CAST_EXPRESSION__EXPRESSION = PT_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the the '<em>PT Cast Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_CAST_EXPRESSION_FEATURE_COUNT = PT_EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.PTCharacterLiteralImpl <em>PT Character Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.PTCharacterLiteralImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getPTCharacterLiteral()
	 * @generated
	 */
	int PT_CHARACTER_LITERAL = 13;

	/**
	 * The feature id for the '<em><b>Escaped Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_CHARACTER_LITERAL__ESCAPED_VALUE = PT_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Char Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_CHARACTER_LITERAL__CHAR_VALUE = PT_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the the '<em>PT Character Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_CHARACTER_LITERAL_FEATURE_COUNT = PT_EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.PTClassInstanceCreationImpl <em>PT Class Instance Creation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.PTClassInstanceCreationImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getPTClassInstanceCreation()
	 * @generated
	 */
	int PT_CLASS_INSTANCE_CREATION = 14;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_CLASS_INSTANCE_CREATION__TYPE = PT_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_CLASS_INSTANCE_CREATION__ARGUMENTS = PT_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the the '<em>PT Class Instance Creation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_CLASS_INSTANCE_CREATION_FEATURE_COUNT = PT_EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.PTConditionalExpressionImpl <em>PT Conditional Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.PTConditionalExpressionImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getPTConditionalExpression()
	 * @generated
	 */
	int PT_CONDITIONAL_EXPRESSION = 15;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_CONDITIONAL_EXPRESSION__CONDITION = PT_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>True</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_CONDITIONAL_EXPRESSION__TRUE = PT_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>False</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_CONDITIONAL_EXPRESSION__FALSE = PT_EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the the '<em>PT Conditional Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_CONDITIONAL_EXPRESSION_FEATURE_COUNT = PT_EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.PTFieldAccessImpl <em>PT Field Access</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.PTFieldAccessImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getPTFieldAccess()
	 * @generated
	 */
	int PT_FIELD_ACCESS = 16;

	/**
	 * The feature id for the '<em><b>Receiver</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_FIELD_ACCESS__RECEIVER = PT_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Field</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_FIELD_ACCESS__FIELD = PT_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the the '<em>PT Field Access</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_FIELD_ACCESS_FEATURE_COUNT = PT_EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.PTInfixExpressionImpl <em>PT Infix Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.PTInfixExpressionImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getPTInfixExpression()
	 * @generated
	 */
	int PT_INFIX_EXPRESSION = 17;

	/**
	 * The feature id for the '<em><b>Left Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_INFIX_EXPRESSION__LEFT_OPERAND = PT_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_INFIX_EXPRESSION__OPERATOR = PT_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Right Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_INFIX_EXPRESSION__RIGHT_OPERAND = PT_EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Extended Operands</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_INFIX_EXPRESSION__EXTENDED_OPERANDS = PT_EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the the '<em>PT Infix Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_INFIX_EXPRESSION_FEATURE_COUNT = PT_EXPRESSION_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.PTInstanceofImpl <em>PT Instanceof</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.PTInstanceofImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getPTInstanceof()
	 * @generated
	 */
	int PT_INSTANCEOF = 18;

	/**
	 * The feature id for the '<em><b>Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_INSTANCEOF__OPERAND = PT_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_INSTANCEOF__TYPE = PT_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the the '<em>PT Instanceof</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_INSTANCEOF_FEATURE_COUNT = PT_EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.PTMethodInvocationImpl <em>PT Method Invocation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.PTMethodInvocationImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getPTMethodInvocation()
	 * @generated
	 */
	int PT_METHOD_INVOCATION = 19;

	/**
	 * The feature id for the '<em><b>Receiver</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_METHOD_INVOCATION__RECEIVER = PT_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_METHOD_INVOCATION__NAME = PT_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_METHOD_INVOCATION__ARGUMENTS = PT_EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the the '<em>PT Method Invocation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_METHOD_INVOCATION_FEATURE_COUNT = PT_EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.PTNameImpl <em>PT Name</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.PTNameImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getPTName()
	 * @generated
	 */
	int PT_NAME = 20;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_NAME__NAME = PT_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the the '<em>PT Name</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_NAME_FEATURE_COUNT = PT_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.PTNullLiteralImpl <em>PT Null Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.PTNullLiteralImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getPTNullLiteral()
	 * @generated
	 */
	int PT_NULL_LITERAL = 21;

	/**
	 * The number of structural features of the the '<em>PT Null Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_NULL_LITERAL_FEATURE_COUNT = PT_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.PTNumberLiteralImpl <em>PT Number Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.PTNumberLiteralImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getPTNumberLiteral()
	 * @generated
	 */
	int PT_NUMBER_LITERAL = 22;

	/**
	 * The feature id for the '<em><b>Token</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_NUMBER_LITERAL__TOKEN = PT_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the the '<em>PT Number Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_NUMBER_LITERAL_FEATURE_COUNT = PT_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.PTParenthesizedExpressionImpl <em>PT Parenthesized Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.PTParenthesizedExpressionImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getPTParenthesizedExpression()
	 * @generated
	 */
	int PT_PARENTHESIZED_EXPRESSION = 23;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_PARENTHESIZED_EXPRESSION__EXPRESSION = PT_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the the '<em>PT Parenthesized Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_PARENTHESIZED_EXPRESSION_FEATURE_COUNT = PT_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.PTPrefixExpressionImpl <em>PT Prefix Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.PTPrefixExpressionImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getPTPrefixExpression()
	 * @generated
	 */
	int PT_PREFIX_EXPRESSION = 24;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_PREFIX_EXPRESSION__OPERATOR = PT_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_PREFIX_EXPRESSION__EXPRESSION = PT_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the the '<em>PT Prefix Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_PREFIX_EXPRESSION_FEATURE_COUNT = PT_EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.PTStringLiteralImpl <em>PT String Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.PTStringLiteralImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getPTStringLiteral()
	 * @generated
	 */
	int PT_STRING_LITERAL = 25;

	/**
	 * The feature id for the '<em><b>Escaped Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_STRING_LITERAL__ESCAPED_VALUE = PT_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Literal Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_STRING_LITERAL__LITERAL_VALUE = PT_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the the '<em>PT String Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_STRING_LITERAL_FEATURE_COUNT = PT_EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.PTThisLiteralImpl <em>PT This Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.PTThisLiteralImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getPTThisLiteral()
	 * @generated
	 */
	int PT_THIS_LITERAL = 26;

	/**
	 * The number of structural features of the the '<em>PT This Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_THIS_LITERAL_FEATURE_COUNT = PT_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.PTTypeLiteralImpl <em>PT Type Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.PTTypeLiteralImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getPTTypeLiteral()
	 * @generated
	 */
	int PT_TYPE_LITERAL = 27;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_TYPE_LITERAL__TYPE = PT_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the the '<em>PT Type Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_TYPE_LITERAL_FEATURE_COUNT = PT_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.PTInvalidExpressionImpl <em>PT Invalid Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.PTInvalidExpressionImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getPTInvalidExpression()
	 * @generated
	 */
	int PT_INVALID_EXPRESSION = 28;

	/**
	 * The feature id for the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_INVALID_EXPRESSION__MESSAGE = PT_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the the '<em>PT Invalid Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_INVALID_EXPRESSION_FEATURE_COUNT = PT_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.impl.PTInstanceReferenceImpl <em>PT Instance Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.impl.PTInstanceReferenceImpl
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getPTInstanceReference()
	 * @generated
	 */
	int PT_INSTANCE_REFERENCE = 29;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_INSTANCE_REFERENCE__REFERENCE = PT_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the the '<em>PT Instance Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_INSTANCE_REFERENCE_FEATURE_COUNT = PT_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.PTInfixOperator <em>PT Infix Operator</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.PTInfixOperator
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getPTInfixOperator()
	 * @generated
	 */
	int PT_INFIX_OPERATOR = 30;

	/**
	 * The meta object id for the '{@link org.eclipse.jem.internal.instantiation.PTPrefixOperator <em>PT Prefix Operator</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jem.internal.instantiation.PTPrefixOperator
	 * @see org.eclipse.jem.internal.instantiation.impl.InstantiationPackageImpl#getPTPrefixOperator()
	 * @generated
	 */
	int PT_PREFIX_OPERATOR = 31;


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
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.PTExpression <em>PT Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>PT Expression</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTExpression
	 * @generated
	 */
	EClass getPTExpression();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.PTArrayAccess <em>PT Array Access</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>PT Array Access</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTArrayAccess
	 * @generated
	 */
	EClass getPTArrayAccess();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jem.internal.instantiation.PTArrayAccess#getArray <em>Array</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Array</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTArrayAccess#getArray()
	 * @see #getPTArrayAccess()
	 * @generated
	 */
	EReference getPTArrayAccess_Array();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jem.internal.instantiation.PTArrayAccess#getIndexes <em>Indexes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Indexes</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTArrayAccess#getIndexes()
	 * @see #getPTArrayAccess()
	 * @generated
	 */
	EReference getPTArrayAccess_Indexes();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.PTArrayCreation <em>PT Array Creation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>PT Array Creation</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTArrayCreation
	 * @generated
	 */
	EClass getPTArrayCreation();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.instantiation.PTArrayCreation#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTArrayCreation#getType()
	 * @see #getPTArrayCreation()
	 * @generated
	 */
	EAttribute getPTArrayCreation_Type();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jem.internal.instantiation.PTArrayCreation#getDimensions <em>Dimensions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Dimensions</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTArrayCreation#getDimensions()
	 * @see #getPTArrayCreation()
	 * @generated
	 */
	EReference getPTArrayCreation_Dimensions();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jem.internal.instantiation.PTArrayCreation#getInitializer <em>Initializer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Initializer</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTArrayCreation#getInitializer()
	 * @see #getPTArrayCreation()
	 * @generated
	 */
	EReference getPTArrayCreation_Initializer();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.PTArrayInitializer <em>PT Array Initializer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>PT Array Initializer</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTArrayInitializer
	 * @generated
	 */
	EClass getPTArrayInitializer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jem.internal.instantiation.PTArrayInitializer#getExpressions <em>Expressions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Expressions</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTArrayInitializer#getExpressions()
	 * @see #getPTArrayInitializer()
	 * @generated
	 */
	EReference getPTArrayInitializer_Expressions();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.PTBooleanLiteral <em>PT Boolean Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>PT Boolean Literal</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTBooleanLiteral
	 * @generated
	 */
	EClass getPTBooleanLiteral();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.instantiation.PTBooleanLiteral#isBooleanValue <em>Boolean Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Boolean Value</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTBooleanLiteral#isBooleanValue()
	 * @see #getPTBooleanLiteral()
	 * @generated
	 */
	EAttribute getPTBooleanLiteral_BooleanValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.PTCastExpression <em>PT Cast Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>PT Cast Expression</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTCastExpression
	 * @generated
	 */
	EClass getPTCastExpression();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.instantiation.PTCastExpression#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTCastExpression#getType()
	 * @see #getPTCastExpression()
	 * @generated
	 */
	EAttribute getPTCastExpression_Type();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jem.internal.instantiation.PTCastExpression#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTCastExpression#getExpression()
	 * @see #getPTCastExpression()
	 * @generated
	 */
	EReference getPTCastExpression_Expression();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.PTCharacterLiteral <em>PT Character Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>PT Character Literal</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTCharacterLiteral
	 * @generated
	 */
	EClass getPTCharacterLiteral();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.instantiation.PTCharacterLiteral#getEscapedValue <em>Escaped Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Escaped Value</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTCharacterLiteral#getEscapedValue()
	 * @see #getPTCharacterLiteral()
	 * @generated
	 */
	EAttribute getPTCharacterLiteral_EscapedValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.instantiation.PTCharacterLiteral#getCharValue <em>Char Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Char Value</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTCharacterLiteral#getCharValue()
	 * @see #getPTCharacterLiteral()
	 * @generated
	 */
	EAttribute getPTCharacterLiteral_CharValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.PTClassInstanceCreation <em>PT Class Instance Creation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>PT Class Instance Creation</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTClassInstanceCreation
	 * @generated
	 */
	EClass getPTClassInstanceCreation();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.instantiation.PTClassInstanceCreation#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTClassInstanceCreation#getType()
	 * @see #getPTClassInstanceCreation()
	 * @generated
	 */
	EAttribute getPTClassInstanceCreation_Type();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jem.internal.instantiation.PTClassInstanceCreation#getArguments <em>Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Arguments</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTClassInstanceCreation#getArguments()
	 * @see #getPTClassInstanceCreation()
	 * @generated
	 */
	EReference getPTClassInstanceCreation_Arguments();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.PTConditionalExpression <em>PT Conditional Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>PT Conditional Expression</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTConditionalExpression
	 * @generated
	 */
	EClass getPTConditionalExpression();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jem.internal.instantiation.PTConditionalExpression#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Condition</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTConditionalExpression#getCondition()
	 * @see #getPTConditionalExpression()
	 * @generated
	 */
	EReference getPTConditionalExpression_Condition();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jem.internal.instantiation.PTConditionalExpression#getTrue <em>True</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>True</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTConditionalExpression#getTrue()
	 * @see #getPTConditionalExpression()
	 * @generated
	 */
	EReference getPTConditionalExpression_True();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jem.internal.instantiation.PTConditionalExpression#getFalse <em>False</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>False</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTConditionalExpression#getFalse()
	 * @see #getPTConditionalExpression()
	 * @generated
	 */
	EReference getPTConditionalExpression_False();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.PTFieldAccess <em>PT Field Access</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>PT Field Access</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTFieldAccess
	 * @generated
	 */
	EClass getPTFieldAccess();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jem.internal.instantiation.PTFieldAccess#getReceiver <em>Receiver</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Receiver</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTFieldAccess#getReceiver()
	 * @see #getPTFieldAccess()
	 * @generated
	 */
	EReference getPTFieldAccess_Receiver();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.instantiation.PTFieldAccess#getField <em>Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Field</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTFieldAccess#getField()
	 * @see #getPTFieldAccess()
	 * @generated
	 */
	EAttribute getPTFieldAccess_Field();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.PTInfixExpression <em>PT Infix Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>PT Infix Expression</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTInfixExpression
	 * @generated
	 */
	EClass getPTInfixExpression();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jem.internal.instantiation.PTInfixExpression#getLeftOperand <em>Left Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left Operand</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTInfixExpression#getLeftOperand()
	 * @see #getPTInfixExpression()
	 * @generated
	 */
	EReference getPTInfixExpression_LeftOperand();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.instantiation.PTInfixExpression#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTInfixExpression#getOperator()
	 * @see #getPTInfixExpression()
	 * @generated
	 */
	EAttribute getPTInfixExpression_Operator();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jem.internal.instantiation.PTInfixExpression#getRightOperand <em>Right Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right Operand</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTInfixExpression#getRightOperand()
	 * @see #getPTInfixExpression()
	 * @generated
	 */
	EReference getPTInfixExpression_RightOperand();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jem.internal.instantiation.PTInfixExpression#getExtendedOperands <em>Extended Operands</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Extended Operands</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTInfixExpression#getExtendedOperands()
	 * @see #getPTInfixExpression()
	 * @generated
	 */
	EReference getPTInfixExpression_ExtendedOperands();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.PTInstanceof <em>PT Instanceof</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>PT Instanceof</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTInstanceof
	 * @generated
	 */
	EClass getPTInstanceof();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jem.internal.instantiation.PTInstanceof#getOperand <em>Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Operand</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTInstanceof#getOperand()
	 * @see #getPTInstanceof()
	 * @generated
	 */
	EReference getPTInstanceof_Operand();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.instantiation.PTInstanceof#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTInstanceof#getType()
	 * @see #getPTInstanceof()
	 * @generated
	 */
	EAttribute getPTInstanceof_Type();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.PTMethodInvocation <em>PT Method Invocation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>PT Method Invocation</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTMethodInvocation
	 * @generated
	 */
	EClass getPTMethodInvocation();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jem.internal.instantiation.PTMethodInvocation#getReceiver <em>Receiver</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Receiver</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTMethodInvocation#getReceiver()
	 * @see #getPTMethodInvocation()
	 * @generated
	 */
	EReference getPTMethodInvocation_Receiver();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.instantiation.PTMethodInvocation#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTMethodInvocation#getName()
	 * @see #getPTMethodInvocation()
	 * @generated
	 */
	EAttribute getPTMethodInvocation_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jem.internal.instantiation.PTMethodInvocation#getArguments <em>Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Arguments</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTMethodInvocation#getArguments()
	 * @see #getPTMethodInvocation()
	 * @generated
	 */
	EReference getPTMethodInvocation_Arguments();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.PTName <em>PT Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>PT Name</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTName
	 * @generated
	 */
	EClass getPTName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.instantiation.PTName#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTName#getName()
	 * @see #getPTName()
	 * @generated
	 */
	EAttribute getPTName_Name();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.PTNullLiteral <em>PT Null Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>PT Null Literal</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTNullLiteral
	 * @generated
	 */
	EClass getPTNullLiteral();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.PTNumberLiteral <em>PT Number Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>PT Number Literal</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTNumberLiteral
	 * @generated
	 */
	EClass getPTNumberLiteral();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.instantiation.PTNumberLiteral#getToken <em>Token</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Token</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTNumberLiteral#getToken()
	 * @see #getPTNumberLiteral()
	 * @generated
	 */
	EAttribute getPTNumberLiteral_Token();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.PTParenthesizedExpression <em>PT Parenthesized Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>PT Parenthesized Expression</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTParenthesizedExpression
	 * @generated
	 */
	EClass getPTParenthesizedExpression();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jem.internal.instantiation.PTParenthesizedExpression#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTParenthesizedExpression#getExpression()
	 * @see #getPTParenthesizedExpression()
	 * @generated
	 */
	EReference getPTParenthesizedExpression_Expression();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.PTPrefixExpression <em>PT Prefix Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>PT Prefix Expression</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTPrefixExpression
	 * @generated
	 */
	EClass getPTPrefixExpression();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.instantiation.PTPrefixExpression#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTPrefixExpression#getOperator()
	 * @see #getPTPrefixExpression()
	 * @generated
	 */
	EAttribute getPTPrefixExpression_Operator();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jem.internal.instantiation.PTPrefixExpression#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTPrefixExpression#getExpression()
	 * @see #getPTPrefixExpression()
	 * @generated
	 */
	EReference getPTPrefixExpression_Expression();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.PTStringLiteral <em>PT String Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>PT String Literal</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTStringLiteral
	 * @generated
	 */
	EClass getPTStringLiteral();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.instantiation.PTStringLiteral#getEscapedValue <em>Escaped Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Escaped Value</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTStringLiteral#getEscapedValue()
	 * @see #getPTStringLiteral()
	 * @generated
	 */
	EAttribute getPTStringLiteral_EscapedValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.instantiation.PTStringLiteral#getLiteralValue <em>Literal Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Literal Value</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTStringLiteral#getLiteralValue()
	 * @see #getPTStringLiteral()
	 * @generated
	 */
	EAttribute getPTStringLiteral_LiteralValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.PTThisLiteral <em>PT This Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>PT This Literal</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTThisLiteral
	 * @generated
	 */
	EClass getPTThisLiteral();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.PTTypeLiteral <em>PT Type Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>PT Type Literal</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTTypeLiteral
	 * @generated
	 */
	EClass getPTTypeLiteral();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.instantiation.PTTypeLiteral#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTTypeLiteral#getType()
	 * @see #getPTTypeLiteral()
	 * @generated
	 */
	EAttribute getPTTypeLiteral_Type();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.PTInvalidExpression <em>PT Invalid Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>PT Invalid Expression</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTInvalidExpression
	 * @generated
	 */
	EClass getPTInvalidExpression();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jem.internal.instantiation.PTInvalidExpression#getMessage <em>Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTInvalidExpression#getMessage()
	 * @see #getPTInvalidExpression()
	 * @generated
	 */
	EAttribute getPTInvalidExpression_Message();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jem.internal.instantiation.PTInstanceReference <em>PT Instance Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>PT Instance Reference</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTInstanceReference
	 * @generated
	 */
	EClass getPTInstanceReference();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.jem.internal.instantiation.PTInstanceReference#getReference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Reference</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTInstanceReference#getReference()
	 * @see #getPTInstanceReference()
	 * @generated
	 */
	EReference getPTInstanceReference_Reference();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.jem.internal.instantiation.PTInfixOperator <em>PT Infix Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>PT Infix Operator</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTInfixOperator
	 * @generated
	 */
	EEnum getPTInfixOperator();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.jem.internal.instantiation.PTPrefixOperator <em>PT Prefix Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>PT Prefix Operator</em>'.
	 * @see org.eclipse.jem.internal.instantiation.PTPrefixOperator
	 * @generated
	 */
	EEnum getPTPrefixOperator();

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
