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
 *  $RCSfile: InstantiationFactory.java,v $
 *  $Revision: 1.3 $  $Date: 2004/01/19 22:50:15 $ 
 */

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
	 * Returns a new object of class '<em>Array Access</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Array Access</em>'.
	 * @generated
	 */
	ArrayAccess createArrayAccess();

	/**
	 * Returns a new object of class '<em>Array Creation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Array Creation</em>'.
	 * @generated
	 */
	ArrayCreation createArrayCreation();

	/**
	 * Returns a new object of class '<em>Array Initializer</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Array Initializer</em>'.
	 * @generated
	 */
	ArrayInitializer createArrayInitializer();

	/**
	 * Returns a new object of class '<em>Boolean Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Boolean Literal</em>'.
	 * @generated
	 */
	BooleanLiteral createBooleanLiteral();

	/**
	 * Returns a new object of class '<em>Cast</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Cast</em>'.
	 * @generated
	 */
	Cast createCast();

	/**
	 * Returns a new object of class '<em>Character Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Character Literal</em>'.
	 * @generated
	 */
	CharacterLiteral createCharacterLiteral();

	/**
	 * Returns a new object of class '<em>Class Instance Creation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Class Instance Creation</em>'.
	 * @generated
	 */
	ClassInstanceCreation createClassInstanceCreation();

	/**
	 * Returns a new object of class '<em>Conditional Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Conditional Expression</em>'.
	 * @generated
	 */
	ConditionalExpression createConditionalExpression();

	/**
	 * Returns a new object of class '<em>Field Access</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Field Access</em>'.
	 * @generated
	 */
	FieldAccess createFieldAccess();

	/**
	 * Returns a new object of class '<em>Infix Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Infix Expression</em>'.
	 * @generated
	 */
	InfixExpression createInfixExpression();

	/**
	 * Returns a new object of class '<em>Instanceof</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Instanceof</em>'.
	 * @generated
	 */
	Instanceof createInstanceof();

	/**
	 * Returns a new object of class '<em>Method Invocation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Method Invocation</em>'.
	 * @generated
	 */
	MethodInvocation createMethodInvocation();

	/**
	 * Returns a new object of class '<em>Name</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Name</em>'.
	 * @generated
	 */
	Name createName();

	/**
	 * Returns a new object of class '<em>Null Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Null Literal</em>'.
	 * @generated
	 */
	NullLiteral createNullLiteral();

	/**
	 * Returns a new object of class '<em>Number Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Number Literal</em>'.
	 * @generated
	 */
	NumberLiteral createNumberLiteral();

	/**
	 * Returns a new object of class '<em>Parenthesized Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Parenthesized Expression</em>'.
	 * @generated
	 */
	ParenthesizedExpression createParenthesizedExpression();

	/**
	 * Returns a new object of class '<em>Prefix Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Prefix Expression</em>'.
	 * @generated
	 */
	PrefixExpression createPrefixExpression();

	/**
	 * Returns a new object of class '<em>String Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>String Literal</em>'.
	 * @generated
	 */
	StringLiteral createStringLiteral();

	/**
	 * Returns a new object of class '<em>This Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>This Literal</em>'.
	 * @generated
	 */
	ThisLiteral createThisLiteral();

	/**
	 * Returns a new object of class '<em>Type Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Type Literal</em>'.
	 * @generated
	 */
	TypeLiteral createTypeLiteral();

	/**
	 * Returns a new object of class '<em>Invalid Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Invalid Expression</em>'.
	 * @generated
	 */
	InvalidExpression createInvalidExpression();

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
