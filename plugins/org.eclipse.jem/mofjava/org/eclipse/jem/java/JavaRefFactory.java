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
 *  $Revision: 1.2 $  $Date: 2004/01/13 21:12:07 $ 
 */

import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
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
	 * Create a proxy reference to the class name.
	 * @param targetName Classname to create a proxy reference too.
	 * @return A proxy reference
	 * 
	 * @since 1.0.0
	 */
	public JavaClass createClassRef(String targetName);
	
	/**
	 * reflect - reflect a java type (class or primitive) for a given qualified name.
	 * <p>If the package or type does not exist, one will be created through
	 * the reflection mechanism.
	 * <p>Lookup the JavaClass in the context of the passed object, handling some error cases.
	 * @param aQualifiedName Fully qualified name of class or primitive (e.g. <code>java.lang.Object</code> or <code>int</code>
	 * @param relatedObject EObject that it will be related too (it will look through the resource set's project of the EObject)
	 * @return The type. <code>null</code> if name is not of correct format, relatedObject is not contained by a resource set, or resource set is not associated with a project.
	 * 
	 * @since 1.0.0
	 */
	public JavaHelpers reflectType(String aQualifiedName, EObject relatedObject);
	
	
	/**
	 * reflect - reflect a java type (class or primitive) for a given qualified name.
	 * <p>If the package or class does not exist, one will be created through
	 * the reflection mechanism.
	 * @param aQualifiedName Fully qualified name of class or primitive (e.g. <code>java.lang.Object</code> or <code>int</code>
	 * @param set Resource set to use. Its project will be used to find the type.
	 * @return The type. <code>null</code> if name is not of correct format, or resource set is not associated with a project.
	 * 
	 * @since 1.0.0
	 */
	public JavaHelpers reflectType(String aQualifiedName, ResourceSet set);	
	
	
	/**
	 * reflect - reflect a type for a given package name and class name.
	 * <p>If the package or class does not exist, one will be created through
	 * the reflection mechanism.
	 * @param aPackageName Package name
	 * @param aTypeName Type name 
	 * @param set Resource set to use. Its project will be used to find the type.
	 * @return The type. <code>null</code> if name is not of correct format, or resource set is not associated with a project.
	 * 
	 * @since 1.0.0
	 */
	public JavaHelpers reflectType(String aPackageName, String aTypeName, ResourceSet set);
	
	/**
	 * reflect - reflect a Java package for a given package name.
	 * <p>If the package does not exist, one will be created through
	 * the reflection mechanism.
	 * @param packageName Name of package
	 * @param set Resource set to use. Its project will be used to find the package.
	 * @return The package. <code>null</code> if name is not of correct format, or resource set is not associated with a project.
	 * 
	 * @since 1.0.0
	 */
	public JavaPackage reflectPackage(String packageName, ResourceSet set);
	
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






