package org.eclipse.jem.java.util;

/*******************************************************************************
 * Copyright (c) 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: JavaRefSwitch.java,v $
 *  $Revision: 1.1 $  $Date: 2004/01/13 16:16:21 $ 
 */
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;

import org.eclipse.jem.java.*;



/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.jem.java.JavaRefPackage
 * @generated
 */
public class JavaRefSwitch {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static JavaRefPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaRefSwitch()
	{
		if (modelPackage == null) {
			modelPackage = JavaRefPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public Object doSwitch(EObject theEObject)
	{
		EClass theEClass = theEObject.eClass();
		if (theEClass.eContainer() == modelPackage) {
			switch (theEClass.getClassifierID()) {
				case JavaRefPackage.JAVA_CLASS: {
					JavaClass javaClass = (JavaClass)theEObject;
					Object result = caseJavaClass(javaClass);
					if (result == null) result = caseEClass(javaClass);
					if (result == null) result = caseEClassifier(javaClass);
					if (result == null) result = caseENamedElement(javaClass);
					if (result == null) result = caseEModelElement(javaClass);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case JavaRefPackage.INITIALIZER: {
					Initializer initializer = (Initializer)theEObject;
					Object result = caseInitializer(initializer);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case JavaRefPackage.JAVA_PARAMETER: {
					JavaParameter javaParameter = (JavaParameter)theEObject;
					Object result = caseJavaParameter(javaParameter);
					if (result == null) result = caseEParameter(javaParameter);
					if (result == null) result = caseEModelElement(javaParameter);
					if (result == null) result = caseETypedElement(javaParameter);
					if (result == null) result = caseENamedElement(javaParameter);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case JavaRefPackage.METHOD: {
					Method method = (Method)theEObject;
					Object result = caseMethod(method);
					if (result == null) result = caseEOperation(method);
					if (result == null) result = caseETypedElement(method);
					if (result == null) result = caseENamedElement(method);
					if (result == null) result = caseEModelElement(method);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case JavaRefPackage.FIELD: {
					Field field = (Field)theEObject;
					Object result = caseField(field);
					if (result == null) result = caseEAttribute(field);
					if (result == null) result = caseEStructuralFeature(field);
					if (result == null) result = caseETypedElement(field);
					if (result == null) result = caseENamedElement(field);
					if (result == null) result = caseEModelElement(field);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case JavaRefPackage.BLOCK: {
					Block block = (Block)theEObject;
					Object result = caseBlock(block);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case JavaRefPackage.COMMENT: {
					Comment comment = (Comment)theEObject;
					Object result = caseComment(comment);
					if (result == null) result = caseBlock(comment);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case JavaRefPackage.STATEMENT: {
					Statement statement = (Statement)theEObject;
					Object result = caseStatement(statement);
					if (result == null) result = caseBlock(statement);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case JavaRefPackage.JAVA_PACKAGE: {
					JavaPackage javaPackage = (JavaPackage)theEObject;
					Object result = caseJavaPackage(javaPackage);
					if (result == null) result = caseEPackage(javaPackage);
					if (result == null) result = caseENamedElement(javaPackage);
					if (result == null) result = caseEModelElement(javaPackage);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case JavaRefPackage.JAVA_DATA_TYPE: {
					JavaDataType javaDataType = (JavaDataType)theEObject;
					Object result = caseJavaDataType(javaDataType);
					if (result == null) result = caseEClass(javaDataType);
					if (result == null) result = caseEClassifier(javaDataType);
					if (result == null) result = caseENamedElement(javaDataType);
					if (result == null) result = caseEModelElement(javaDataType);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case JavaRefPackage.ARRAY_TYPE: {
					ArrayType arrayType = (ArrayType)theEObject;
					Object result = caseArrayType(arrayType);
					if (result == null) result = caseJavaClass(arrayType);
					if (result == null) result = caseEClass(arrayType);
					if (result == null) result = caseEClassifier(arrayType);
					if (result == null) result = caseENamedElement(arrayType);
					if (result == null) result = caseEModelElement(arrayType);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				default: return defaultCase(theEObject);
			}
		}
		return defaultCase(theEObject);
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Java Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Java Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseJavaClass(JavaClass object)
	{
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Initializer</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Initializer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseInitializer(Initializer object)
	{
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Java Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Java Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseJavaParameter(JavaParameter object)
	{
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Method</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Method</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseMethod(Method object)
	{
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseField(Field object)
	{
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Block</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Block</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseBlock(Block object)
	{
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Comment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Comment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseComment(Comment object)
	{
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Statement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Statement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseStatement(Statement object)
	{
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Java Package</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Java Package</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseJavaPackage(JavaPackage object)
	{
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Java Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Java Data Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseJavaDataType(JavaDataType object)
	{
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Array Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Array Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseArrayType(ArrayType object)
	{
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Java Event</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Java Event</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseJavaEvent(JavaEvent object)
	{
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>EModel Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>EModel Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseEModelElement(EModelElement object)
	{
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>ENamed Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>ENamed Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseENamedElement(ENamedElement object)
	{
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>EClassifier</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>EClassifier</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseEClassifier(EClassifier object)
	{
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>EClass</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>EClass</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseEClass(EClass object)
	{
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>ETyped Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>ETyped Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseETypedElement(ETypedElement object)
	{
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>EParameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>EParameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseEParameter(EParameter object)
	{
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>EOperation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>EOperation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseEOperation(EOperation object)
	{
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>EStructural Feature</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>EStructural Feature</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseEStructuralFeature(EStructuralFeature object)
	{
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>EAttribute</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>EAttribute</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseEAttribute(EAttribute object)
	{
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>EPackage</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>EPackage</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseEPackage(EPackage object)
	{
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public Object defaultCase(EObject object)
	{
		return null;
	}

} //JavaRefSwitch
