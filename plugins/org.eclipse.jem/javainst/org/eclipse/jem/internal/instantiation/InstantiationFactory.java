/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.instantiation;
/*
 *  $RCSfile: InstantiationFactory.java,v $
 *  $Revision: 1.5.4.1 $  $Date: 2004/06/24 18:17:07 $ 
 */

import java.util.List;

import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;


/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage
 * @generated
 */
public interface InstantiationFactory extends EFactory{
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	InstantiationFactory eINSTANCE = new org.eclipse.jem.internal.instantiation.impl.InstantiationFactoryImpl();

	/**
	 * Returns a new object of class '<em>Init String Allocation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Init String Allocation</em>'.
	 * @generated
	 */
	InitStringAllocation createInitStringAllocation();

	/**
	 * Returns a new object of class '<em>Implicit Allocation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Implicit Allocation</em>'.
	 * @generated
	 */
	ImplicitAllocation createImplicitAllocation();

	/**
	 * Returns a new object of class '<em>Parse Tree Allocation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Parse Tree Allocation</em>'.
	 * @generated
	 */
	ParseTreeAllocation createParseTreeAllocation();

	/**
	 * Utility to create a parse tree allocation with an expression.
	 * 
	 * @param expression
	 * @return The new parse tree allocation.
	 * 
	 * @since 1.0.0
	 */
	ParseTreeAllocation createParseTreeAllocation(PTExpression expression);
	
	/**
	 * Returns a new object of class '<em>PT Array Access</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>PT Array Access</em>'.
	 * @generated
	 */
	PTArrayAccess createPTArrayAccess();

	/**
	 * Utility to create a new array access with the array and indexes.
	 * 
	 * @param array
	 * @param indexes list of index expressions
	 * @return The new array access.
	 * 
	 * @since 1.0.0
	 */
	PTArrayAccess createPTArrayAccess(PTExpression array, List indexes);

	/**
	 * Returns a new object of class '<em>PT Array Creation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>PT Array Creation</em>'.
	 * @generated
	 */
	PTArrayCreation createPTArrayCreation();

	/**
	 * Utility to create an array creation with the type, dimensions, and initializer.
	 * 
	 * @param type The type, e.g. <code>java.lang.String</code>.
	 * @param dimensions The dimension expressions, <code>null</code> if no dimension expressions.
	 * @param initializer The initializer, <code>null</code> if no initializer.
	 * @return The new array creation.
	 * 
	 * @since 1.0.0
	 */
	PTArrayCreation createPTArrayCreation(String type, List dimensions, PTArrayInitializer initializer);

	/**
	 * Returns a new object of class '<em>PT Array Initializer</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>PT Array Initializer</em>'.
	 * @generated
	 */
	PTArrayInitializer createPTArrayInitializer();

	/**
	 * Utility to create an array initilizer with initialize expessions.
	 * @param expressions The list of expressions, <code>null</code> if empty array initializer.
	 * @return The new array initializer.
	 * 
	 * @since 1.0.0
	 */
	PTArrayInitializer createPTArrayInitializer(List expressions);

	/**
	 * Returns a new object of class '<em>PT Boolean Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>PT Boolean Literal</em>'.
	 * @generated
	 */
	PTBooleanLiteral createPTBooleanLiteral();

	/**
	 * Utility to create a boolean literal with a given value.
	 * @param booleanValue
	 * @return The new boolean literal.
	 * 
	 * @since 1.0.0
	 */
	PTBooleanLiteral createPTBooleanLiteral(boolean booleanValue);
	
	/**
	 * Returns a new object of class '<em>PT Cast Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>PT Cast Expression</em>'.
	 * @generated
	 */
	PTCastExpression createPTCastExpression();

	/**
	 * Utility to create a new cast expression.
	 * 
	 * @param type The type of the expression, e.g. <code>short</code> or <code>java.lang.String</code>
	 * @param expression The expression being cast.
	 * @return The new cast expression.
	 * 
	 * @since 1.0.0
	 */
	PTCastExpression createPTCastExpression(String type, PTExpression expression);

	/**
	 * Returns a new object of class '<em>PT Character Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>PT Character Literal</em>'.
	 * @generated
	 */
	PTCharacterLiteral createPTCharacterLiteral();

	/**
	 * Utility to create a new character literal.
	 * @param escapedValue The escaped value to use.
	 * @return The new character literal.
	 * 
	 * @since 1.0.0
	 */
	PTCharacterLiteral createPTCharacterLiteral(String escapedValue);	

	/**
	 * Returns a new object of class '<em>PT Class Instance Creation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>PT Class Instance Creation</em>'.
	 * @generated
	 */
	PTClassInstanceCreation createPTClassInstanceCreation();

	/**
	 * Utility to create a new class instance creation.
	 * 
	 * @param type The type of the class, e.g. <code>java.lang.String</code>.
	 * @param arguments The list of expressions for arguments, or <code>null</code> for no args.
	 * @return The new class instance creation.
	 * 
	 * @since 1.0.0
	 */
	PTClassInstanceCreation createPTClassInstanceCreation(String type, List arguments);

	/**
	 * Returns a new object of class '<em>PT Conditional Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>PT Conditional Expression</em>'.
	 * @generated
	 */
	PTConditionalExpression createPTConditionalExpression();

	/**
	 * Utility to create a new conditional expression with the condition and true/false expressions.
	 * 
	 * @param condition
	 * @param trueExpressoin
	 * @param falseExpression
	 * @return
	 * 
	 * @since 1.0.0
	 */
	PTConditionalExpression createPTConditionalExpression(PTExpression condition, PTExpression trueExpressoin, PTExpression falseExpression);

	/**
	 * Returns a new object of class '<em>PT Field Access</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>PT Field Access</em>'.
	 * @generated
	 */
	PTFieldAccess createPTFieldAccess();

	/**
	 * Utility to create a new field access with receiver and field name.
	 * @param receiver The receiver expression, or <code>null</code> if none.
	 * @param field The field name.
	 * @return The new field access.
	 * 
	 * @since 1.0.0
	 */
	PTFieldAccess createPTFieldAccess(PTExpression receiver, String field);	

	/**
	 * Returns a new object of class '<em>PT Infix Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>PT Infix Expression</em>'.
	 * @generated
	 */
	PTInfixExpression createPTInfixExpression();

	/**
	 * Utility to create a new infix expression with the left/right/extended operands and the operator.
	 * @param leftOperand
	 * @param operator
	 * @param rightOperand
	 * @param extendedOperands The list of extended operands, or <code>null</code> if no extended operands.
	 * @return
	 * 
	 * @since 1.0.0
	 */
	PTInfixExpression createPTInfixExpression(PTExpression leftOperand, PTInfixOperator operator, PTExpression rightOperand, List extendedOperands);

	/**
	 * Returns a new object of class '<em>PT Instanceof</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>PT Instanceof</em>'.
	 * @generated
	 */
	PTInstanceof createPTInstanceof();

	/**
	 * Utility to create a new instance of with an operand and a type.
	 * @param operand
	 * @param type
	 * @return The new instance of.
	 * 
	 * @since 1.0.0
	 */
	PTInstanceof createPTInstanceof(PTExpression operand, String type);

	/**
	 * Returns a new object of class '<em>PT Method Invocation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>PT Method Invocation</em>'.
	 * @generated
	 */
	PTMethodInvocation createPTMethodInvocation();

	/**
	 * Utility to create a new method invocation with a receiver, name, and arguments.
	 * 
	 * @param receiver The receiver expression, or <code>null</code> if no receiver.
	 * @param name The name of the method.
	 * @param arguments The list of argument expressions, or <code>null</code> if no arguments.
	 * @return The new method invocation.
	 * 
	 * @since 1.0.0
	 */
	PTMethodInvocation createPTMethodInvocation(PTExpression receiver, String name, List arguments);

	/**
	 * Returns a new object of class '<em>PT Name</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>PT Name</em>'.
	 * @generated
	 */
	PTName createPTName();

	/**
	 * Utility to create a new name with the name.
	 * 
	 * @param name
	 * @return The new name.
	 * 
	 * @since 1.0.0
	 */
	PTName createPTName(String name);

	/**
	 * Returns a new object of class '<em>PT Null Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>PT Null Literal</em>'.
	 * @generated
	 */
	PTNullLiteral createPTNullLiteral();

	/**
	 * Returns a new object of class '<em>PT Number Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>PT Number Literal</em>'.
	 * @generated
	 */
	PTNumberLiteral createPTNumberLiteral();

	/**
	 * Utility to create a new number literal with the token.
	 * @param token
	 * @return The new number literal.
	 * 
	 * @since 1.0.0
	 */
	PTNumberLiteral createPTNumberLiteral(String token);

	/**
	 * Returns a new object of class '<em>PT Parenthesized Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>PT Parenthesized Expression</em>'.
	 * @generated
	 */
	PTParenthesizedExpression createPTParenthesizedExpression();

	/**
	 * Utility to create a new parenthesized expression with the expression.
	 * @param expression The expression that is in the parenthesis.
	 * @return The new parenthesized expression.
	 * 
	 * @since 1.0.0
	 */
	PTParenthesizedExpression createPTParenthesizedExpression(PTExpression expression);

	/**
	 * Returns a new object of class '<em>PT Prefix Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>PT Prefix Expression</em>'.
	 * @generated
	 */
	PTPrefixExpression createPTPrefixExpression();

	/**
	 * Utility to create a new prefix expression given an operator and expression.
	 * @param operator
	 * @param expression
	 * @return The new prefix expression.
	 * 
	 * @since 1.0.0
	 */
	PTPrefixExpression createPTPrefixExpression(PTPrefixOperator operator, PTExpression expression);

	/**
	 * Returns a new object of class '<em>PT String Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>PT String Literal</em>'.
	 * @generated
	 */
	PTStringLiteral createPTStringLiteral();

	/**
	 * Utility to create a new string literal given the escaped literal string.
	 * @param escapeLiteral
	 * @return The new string literal.
	 * 
	 * @since 1.0.0
	 */
	PTStringLiteral createPTStringLiteral(String escapeLiteral);

	/**
	 * Returns a new object of class '<em>PT This Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>PT This Literal</em>'.
	 * @generated
	 */
	PTThisLiteral createPTThisLiteral();

	/**
	 * Returns a new object of class '<em>PT Type Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>PT Type Literal</em>'.
	 * @generated
	 */
	PTTypeLiteral createPTTypeLiteral();

	/**
	 * Utility to create a new type literal given the type.
	 * @param type
	 * @return The new type literal.
	 * 
	 * @since 1.0.0
	 */
	PTTypeLiteral createPTTypeLiteral(String type);

	/**
	 * Returns a new object of class '<em>PT Invalid Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>PT Invalid Expression</em>'.
	 * @generated
	 */
	PTInvalidExpression createPTInvalidExpression();

	/**
	 * Returns a new object of class '<em>PT Instance Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>PT Instance Reference</em>'.
	 * @generated
	 */
	PTInstanceReference createPTInstanceReference();

	/**
	 * Utility to create a new invalid expression with a message.
	 * @param message
	 * @return The new invalid expression.
	 * 
	 * @since 1.0.0
	 */
	PTInvalidExpression createPTInvalidExpression(String message);

	/**
	 * Returns a new object of class '<em>Implicit Allocation</em>' as
	 * utility with a given init string.
	 * @param initString - String to put into initialization.
	 * @return The allocation initialized.
	 * 
	 * @since 1.0.0
	 */
	InitStringAllocation createInitStringAllocation(String initString);
	
	/**
	 * Returns a new object of class '<em>Implicit Allocation</em>' as
	 * utility with a given parent and feature.
	 * @param parent The source to get the value from.
	 * @param sf The feature on the source for the value.
	 * @return The allocation initialized.
	 * 
	 * @since 1.0.0
	 */
	ImplicitAllocation createImplicitAllocation(EObject parent, EStructuralFeature sf);

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	InstantiationPackage getInstantiationPackage();

} //InstantiationFactory
