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
 *  $Revision: 1.4 $  $Date: 2004/01/19 22:50:15 $ 
 */

import org.eclipse.jem.internal.instantiation.*;

import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
	public InstantiationFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case InstantiationPackage.INIT_STRING_ALLOCATION: return createInitStringAllocation();
			case InstantiationPackage.IMPLICIT_ALLOCATION: return createImplicitAllocation();
			case InstantiationPackage.PARSE_TREE_ALLOCATION: return createParseTreeAllocation();
			case InstantiationPackage.ARRAY_ACCESS: return createArrayAccess();
			case InstantiationPackage.ARRAY_CREATION: return createArrayCreation();
			case InstantiationPackage.ARRAY_INITIALIZER: return createArrayInitializer();
			case InstantiationPackage.BOOLEAN_LITERAL: return createBooleanLiteral();
			case InstantiationPackage.CAST: return createCast();
			case InstantiationPackage.CHARACTER_LITERAL: return createCharacterLiteral();
			case InstantiationPackage.CLASS_INSTANCE_CREATION: return createClassInstanceCreation();
			case InstantiationPackage.CONDITIONAL_EXPRESSION: return createConditionalExpression();
			case InstantiationPackage.FIELD_ACCESS: return createFieldAccess();
			case InstantiationPackage.INFIX_EXPRESSION: return createInfixExpression();
			case InstantiationPackage.INSTANCEOF: return createInstanceof();
			case InstantiationPackage.METHOD_INVOCATION: return createMethodInvocation();
			case InstantiationPackage.NAME: return createName();
			case InstantiationPackage.NULL_LITERAL: return createNullLiteral();
			case InstantiationPackage.NUMBER_LITERAL: return createNumberLiteral();
			case InstantiationPackage.PARENTHESIZED_EXPRESSION: return createParenthesizedExpression();
			case InstantiationPackage.PREFIX_EXPRESSION: return createPrefixExpression();
			case InstantiationPackage.STRING_LITERAL: return createStringLiteral();
			case InstantiationPackage.THIS_LITERAL: return createThisLiteral();
			case InstantiationPackage.TYPE_LITERAL: return createTypeLiteral();
			case InstantiationPackage.INVALID_EXPRESSION: return createInvalidExpression();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case InstantiationPackage.INFIX_OPERATOR:
				return InfixOperator.get(initialValue);
			case InstantiationPackage.PREFIX_OPERATOR:
				return PrefixOperator.get(initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case InstantiationPackage.INFIX_OPERATOR:
				return instanceValue == null ? null : instanceValue.toString();
			case InstantiationPackage.PREFIX_OPERATOR:
				return instanceValue == null ? null : instanceValue.toString();
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InitStringAllocation createInitStringAllocation() {
		InitStringAllocationImpl initStringAllocation = new InitStringAllocationImpl();
		return initStringAllocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImplicitAllocation createImplicitAllocation() {
		ImplicitAllocationImpl implicitAllocation = new ImplicitAllocationImpl();
		return implicitAllocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParseTreeAllocation createParseTreeAllocation() {
		ParseTreeAllocationImpl parseTreeAllocation = new ParseTreeAllocationImpl();
		return parseTreeAllocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrayAccess createArrayAccess() {
		ArrayAccessImpl arrayAccess = new ArrayAccessImpl();
		return arrayAccess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrayCreation createArrayCreation() {
		ArrayCreationImpl arrayCreation = new ArrayCreationImpl();
		return arrayCreation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrayInitializer createArrayInitializer() {
		ArrayInitializerImpl arrayInitializer = new ArrayInitializerImpl();
		return arrayInitializer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanLiteral createBooleanLiteral() {
		BooleanLiteralImpl booleanLiteral = new BooleanLiteralImpl();
		return booleanLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Cast createCast() {
		CastImpl cast = new CastImpl();
		return cast;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CharacterLiteral createCharacterLiteral() {
		CharacterLiteralImpl characterLiteral = new CharacterLiteralImpl();
		return characterLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassInstanceCreation createClassInstanceCreation() {
		ClassInstanceCreationImpl classInstanceCreation = new ClassInstanceCreationImpl();
		return classInstanceCreation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConditionalExpression createConditionalExpression() {
		ConditionalExpressionImpl conditionalExpression = new ConditionalExpressionImpl();
		return conditionalExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FieldAccess createFieldAccess() {
		FieldAccessImpl fieldAccess = new FieldAccessImpl();
		return fieldAccess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InfixExpression createInfixExpression() {
		InfixExpressionImpl infixExpression = new InfixExpressionImpl();
		return infixExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Instanceof createInstanceof() {
		InstanceofImpl instanceof_ = new InstanceofImpl();
		return instanceof_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodInvocation createMethodInvocation() {
		MethodInvocationImpl methodInvocation = new MethodInvocationImpl();
		return methodInvocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Name createName() {
		NameImpl name = new NameImpl();
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NullLiteral createNullLiteral() {
		NullLiteralImpl nullLiteral = new NullLiteralImpl();
		return nullLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NumberLiteral createNumberLiteral() {
		NumberLiteralImpl numberLiteral = new NumberLiteralImpl();
		return numberLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParenthesizedExpression createParenthesizedExpression() {
		ParenthesizedExpressionImpl parenthesizedExpression = new ParenthesizedExpressionImpl();
		return parenthesizedExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrefixExpression createPrefixExpression() {
		PrefixExpressionImpl prefixExpression = new PrefixExpressionImpl();
		return prefixExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StringLiteral createStringLiteral() {
		StringLiteralImpl stringLiteral = new StringLiteralImpl();
		return stringLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ThisLiteral createThisLiteral() {
		ThisLiteralImpl thisLiteral = new ThisLiteralImpl();
		return thisLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeLiteral createTypeLiteral() {
		TypeLiteralImpl typeLiteral = new TypeLiteralImpl();
		return typeLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InvalidExpression createInvalidExpression() {
		InvalidExpressionImpl invalidExpression = new InvalidExpressionImpl();
		return invalidExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InstantiationPackage getInstantiationPackage() {
		return (InstantiationPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	public static InstantiationPackage getPackage() {
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
