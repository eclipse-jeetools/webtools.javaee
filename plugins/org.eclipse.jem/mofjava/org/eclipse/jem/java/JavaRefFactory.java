package org.eclipse.jem.java;

/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: JavaRefFactory.java,v $
 *  $Revision: 1.1 $  $Date: 2004/01/13 16:16:21 $ 
 */

import org.eclipse.emf.ecore.EFactory;
/**
 * @generated
 */
public interface JavaRefFactory extends EFactory{

	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	JavaRefFactory eINSTANCE = new org.eclipse.jem.java.impl.JavaRefFactoryImpl();


	/**
	 * @return ArrayType value with an array of the component type
	 */
	public ArrayType createArrayType(JavaHelpers componentType);
	/**
	 * @return ArrayType value with an array of the specified dimensions and final component type.
	 */
	public ArrayType createArrayType(JavaHelpers finalComponentType, int dimensions);
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return Method value
	 */
	Method createMethod();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return JavaClass value
	 */
	JavaClass createJavaClass();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return Field value
	 */
	Field createField();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return Block value
	 */
	Block createBlock();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return Comment value
	 */
	Comment createComment();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return Statement value
	 */
	Statement createStatement();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return Initializer value
	 */
	Initializer createInitializer();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return JavaParameter value
	 */
	JavaParameter createJavaParameter();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return ArrayType value
	 */
	ArrayType createArrayType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return JavaDataType value
	 */
	JavaDataType createJavaDataType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return JavaPackage value
	 */
	JavaPackage createJavaPackage();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	JavaRefPackage getJavaRefPackage();

}






