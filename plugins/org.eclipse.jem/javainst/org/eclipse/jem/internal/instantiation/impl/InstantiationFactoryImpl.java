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
 *  $Revision: 1.6 $  $Date: 2004/01/28 17:37:58 $ 
 */

import java.util.List;

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
			case InstantiationPackage.PT_ARRAY_ACCESS: return createPTArrayAccess();
			case InstantiationPackage.PT_ARRAY_CREATION: return createPTArrayCreation();
			case InstantiationPackage.PT_ARRAY_INITIALIZER: return createPTArrayInitializer();
			case InstantiationPackage.PT_BOOLEAN_LITERAL: return createPTBooleanLiteral();
			case InstantiationPackage.PT_CAST_EXPRESSION: return createPTCastExpression();
			case InstantiationPackage.PT_CHARACTER_LITERAL: return createPTCharacterLiteral();
			case InstantiationPackage.PT_CLASS_INSTANCE_CREATION: return createPTClassInstanceCreation();
			case InstantiationPackage.PT_CONDITIONAL_EXPRESSION: return createPTConditionalExpression();
			case InstantiationPackage.PT_FIELD_ACCESS: return createPTFieldAccess();
			case InstantiationPackage.PT_INFIX_EXPRESSION: return createPTInfixExpression();
			case InstantiationPackage.PT_INSTANCEOF: return createPTInstanceof();
			case InstantiationPackage.PT_METHOD_INVOCATION: return createPTMethodInvocation();
			case InstantiationPackage.PT_NAME: return createPTName();
			case InstantiationPackage.PT_NULL_LITERAL: return createPTNullLiteral();
			case InstantiationPackage.PT_NUMBER_LITERAL: return createPTNumberLiteral();
			case InstantiationPackage.PT_PARENTHESIZED_EXPRESSION: return createPTParenthesizedExpression();
			case InstantiationPackage.PT_PREFIX_EXPRESSION: return createPTPrefixExpression();
			case InstantiationPackage.PT_STRING_LITERAL: return createPTStringLiteral();
			case InstantiationPackage.PT_THIS_LITERAL: return createPTThisLiteral();
			case InstantiationPackage.PT_TYPE_LITERAL: return createPTTypeLiteral();
			case InstantiationPackage.PT_INVALID_EXPRESSION: return createPTInvalidExpression();
			case InstantiationPackage.PT_INSTANCE_REFERENCE: return createPTInstanceReference();
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
			case InstantiationPackage.PT_INFIX_OPERATOR:
				return PTInfixOperator.get(initialValue);
			case InstantiationPackage.PT_PREFIX_OPERATOR:
				return PTPrefixOperator.get(initialValue);
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
			case InstantiationPackage.PT_INFIX_OPERATOR:
				return instanceValue == null ? null : instanceValue.toString();
			case InstantiationPackage.PT_PREFIX_OPERATOR:
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
	public PTArrayAccess createPTArrayAccess() {
		PTArrayAccessImpl ptArrayAccess = new PTArrayAccessImpl();
		return ptArrayAccess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PTArrayCreation createPTArrayCreation() {
		PTArrayCreationImpl ptArrayCreation = new PTArrayCreationImpl();
		return ptArrayCreation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PTArrayInitializer createPTArrayInitializer() {
		PTArrayInitializerImpl ptArrayInitializer = new PTArrayInitializerImpl();
		return ptArrayInitializer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PTBooleanLiteral createPTBooleanLiteral() {
		PTBooleanLiteralImpl ptBooleanLiteral = new PTBooleanLiteralImpl();
		return ptBooleanLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PTCastExpression createPTCastExpression() {
		PTCastExpressionImpl ptCastExpression = new PTCastExpressionImpl();
		return ptCastExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PTCharacterLiteral createPTCharacterLiteral() {
		PTCharacterLiteralImpl ptCharacterLiteral = new PTCharacterLiteralImpl();
		return ptCharacterLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PTClassInstanceCreation createPTClassInstanceCreation() {
		PTClassInstanceCreationImpl ptClassInstanceCreation = new PTClassInstanceCreationImpl();
		return ptClassInstanceCreation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PTConditionalExpression createPTConditionalExpression() {
		PTConditionalExpressionImpl ptConditionalExpression = new PTConditionalExpressionImpl();
		return ptConditionalExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PTFieldAccess createPTFieldAccess() {
		PTFieldAccessImpl ptFieldAccess = new PTFieldAccessImpl();
		return ptFieldAccess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PTInfixExpression createPTInfixExpression() {
		PTInfixExpressionImpl ptInfixExpression = new PTInfixExpressionImpl();
		return ptInfixExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PTInstanceof createPTInstanceof() {
		PTInstanceofImpl ptInstanceof = new PTInstanceofImpl();
		return ptInstanceof;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PTMethodInvocation createPTMethodInvocation() {
		PTMethodInvocationImpl ptMethodInvocation = new PTMethodInvocationImpl();
		return ptMethodInvocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PTName createPTName() {
		PTNameImpl ptName = new PTNameImpl();
		return ptName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PTNullLiteral createPTNullLiteral() {
		PTNullLiteralImpl ptNullLiteral = new PTNullLiteralImpl();
		return ptNullLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PTNumberLiteral createPTNumberLiteral() {
		PTNumberLiteralImpl ptNumberLiteral = new PTNumberLiteralImpl();
		return ptNumberLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PTParenthesizedExpression createPTParenthesizedExpression() {
		PTParenthesizedExpressionImpl ptParenthesizedExpression = new PTParenthesizedExpressionImpl();
		return ptParenthesizedExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PTPrefixExpression createPTPrefixExpression() {
		PTPrefixExpressionImpl ptPrefixExpression = new PTPrefixExpressionImpl();
		return ptPrefixExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PTStringLiteral createPTStringLiteral() {
		PTStringLiteralImpl ptStringLiteral = new PTStringLiteralImpl();
		return ptStringLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PTThisLiteral createPTThisLiteral() {
		PTThisLiteralImpl ptThisLiteral = new PTThisLiteralImpl();
		return ptThisLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PTTypeLiteral createPTTypeLiteral() {
		PTTypeLiteralImpl ptTypeLiteral = new PTTypeLiteralImpl();
		return ptTypeLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PTInvalidExpression createPTInvalidExpression() {
		PTInvalidExpressionImpl ptInvalidExpression = new PTInvalidExpressionImpl();
		return ptInvalidExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PTInstanceReference createPTInstanceReference() {
		PTInstanceReferenceImpl ptInstanceReference = new PTInstanceReferenceImpl();
		return ptInstanceReference;
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

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationFactory#createParseTreeAllocation(org.eclipse.jem.internal.instantiation.PTExpression)
	 */
	public ParseTreeAllocation createParseTreeAllocation(PTExpression expression) {
		ParseTreeAllocation a = createParseTreeAllocation();
		a.setExpression(expression);
		return a;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationFactory#createPTArrayAccess(org.eclipse.jem.internal.instantiation.PTExpression, java.util.List)
	 */
	public PTArrayAccess createPTArrayAccess(PTExpression array, List indexes) {
		PTArrayAccess a = createPTArrayAccess();
		a.setArray(array);
		a.getIndexes().addAll(indexes);
		return a;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationFactory#createPTArrayCreation(java.lang.String, java.util.List, org.eclipse.jem.internal.instantiation.PTArrayInitializer)
	 */
	public PTArrayCreation createPTArrayCreation(String type, List dimensions, PTArrayInitializer initializer) {
		PTArrayCreation a = createPTArrayCreation();
		a.setType(type);
		if (dimensions != null)
			a.getDimensions().addAll(dimensions);
		a.setInitializer(initializer);
		return a;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationFactory#createPTArrayInitializer(java.util.List)
	 */
	public PTArrayInitializer createPTArrayInitializer(List expressions) {
		PTArrayInitializer a = createPTArrayInitializer();
		if (expressions != null)
			a.getExpressions().addAll(expressions);
		return a;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationFactory#createPTBooleanLiteral(boolean)
	 */
	public PTBooleanLiteral createPTBooleanLiteral(boolean booleanValue) {
		PTBooleanLiteral a = createPTBooleanLiteral();
		a.setBooleanValue(booleanValue);
		return a;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationFactory#createPTCastExpression(java.lang.String, org.eclipse.jem.internal.instantiation.PTExpression)
	 */
	public PTCastExpression createPTCastExpression(String type, PTExpression expression) {
		PTCastExpression a = createPTCastExpression();
		a.setType(type);
		a.setExpression(expression);
		return a;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationFactory#createPTCharacterLiteral(java.lang.String)
	 */
	public PTCharacterLiteral createPTCharacterLiteral(String escapedValue) {
		PTCharacterLiteral a = createPTCharacterLiteral();
		a.setEscapedValue(escapedValue);
		return a;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationFactory#createPTClassInstanceCreation(java.lang.String, java.util.List)
	 */
	public PTClassInstanceCreation createPTClassInstanceCreation(String type, List arguments) {
		PTClassInstanceCreation a = createPTClassInstanceCreation();
		a.setType(type);
		if (arguments != null)
			a.getArguments().addAll(arguments);
		return a;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationFactory#createPTConditionalExpression(org.eclipse.jem.internal.instantiation.PTExpression, org.eclipse.jem.internal.instantiation.PTExpression, org.eclipse.jem.internal.instantiation.PTExpression)
	 */
	public PTConditionalExpression createPTConditionalExpression(
		PTExpression condition,
		PTExpression trueExpressoin,
		PTExpression falseExpression) {
		PTConditionalExpression a = createPTConditionalExpression();
		a.setCondition(condition);
		a.setTrue(trueExpressoin);
		a.setFalse(falseExpression);
		return a;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationFactory#createPTFieldAccess(org.eclipse.jem.internal.instantiation.PTExpression, java.lang.String)
	 */
	public PTFieldAccess createPTFieldAccess(PTExpression receiver, String field) {
		PTFieldAccess a = createPTFieldAccess();
		a.setReceiver(receiver);
		a.setField(field);
		return a;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationFactory#createPTInfixExpression(org.eclipse.jem.internal.instantiation.PTExpression, org.eclipse.jem.internal.instantiation.PTInfixOperator, org.eclipse.jem.internal.instantiation.PTExpression, java.util.List)
	 */
	public PTInfixExpression createPTInfixExpression(
		PTExpression leftOperand,
		PTInfixOperator operator,
		PTExpression rightOperand,
		List extendedOperands) {
		PTInfixExpression a = createPTInfixExpression();
		a.setLeftOperand(leftOperand);
		a.setOperator(operator);
		a.setRightOperand(rightOperand);
		if (extendedOperands != null)
			a.getExtendedOperands().addAll(extendedOperands);
		return a;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationFactory#createPTInstanceof(org.eclipse.jem.internal.instantiation.PTExpression, java.lang.String)
	 */
	public PTInstanceof createPTInstanceof(PTExpression operand, String type) {
		PTInstanceof a = createPTInstanceof();
		a.setOperand(operand);
		a.setType(type);
		return a;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationFactory#createPTInvalidExpression(java.lang.String)
	 */
	public PTInvalidExpression createPTInvalidExpression(String message) {
		PTInvalidExpression a = createPTInvalidExpression();
		a.setMessage(message);
		return a;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationFactory#createPTMethodInvocation(org.eclipse.jem.internal.instantiation.PTExpression, java.lang.String, java.util.List)
	 */
	public PTMethodInvocation createPTMethodInvocation(PTExpression receiver, String name, List arguments) {
		PTMethodInvocation a = createPTMethodInvocation();
		a.setReceiver(receiver);
		a.setName(name);
		if (arguments != null)
			a.getArguments().addAll(arguments);
		return a;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationFactory#createPTName(java.lang.String)
	 */
	public PTName createPTName(String name) {
		PTName a = createPTName();
		a.setName(name);
		return a;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationFactory#createPTNumberLiteral(java.lang.String)
	 */
	public PTNumberLiteral createPTNumberLiteral(String token) {
		PTNumberLiteral a = createPTNumberLiteral();
		a.setToken(token);
		return a;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationFactory#createPTParenthesizedExpression(org.eclipse.jem.internal.instantiation.PTExpression)
	 */
	public PTParenthesizedExpression createPTParenthesizedExpression(PTExpression expression) {
		PTParenthesizedExpression a = createPTParenthesizedExpression();
		a.setExpression(expression);
		return a;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationFactory#createPTPrefixExpression(org.eclipse.jem.internal.instantiation.PTPrefixOperator, org.eclipse.jem.internal.instantiation.PTExpression)
	 */
	public PTPrefixExpression createPTPrefixExpression(PTPrefixOperator operator, PTExpression expression) {
		PTPrefixExpression a = createPTPrefixExpression();
		a.setOperator(operator);
		a.setExpression(expression);
		return a;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationFactory#createPTStringLiteral(java.lang.String)
	 */
	public PTStringLiteral createPTStringLiteral(String escapeLiteral) {
		PTStringLiteral a = createPTStringLiteral();
		a.setEscapedValue(escapeLiteral);
		return a;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationFactory#createPTTypeLiteral(java.lang.String)
	 */
	public PTTypeLiteral createPTTypeLiteral(String type) {
		PTTypeLiteral a = createPTTypeLiteral();
		a.setType(type);
		return a;
	}

} //InstantiationFactoryImpl
