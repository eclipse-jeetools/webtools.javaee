package org.eclipse.jem.java.impl;
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
 *  $RCSfile: JavaRefFactoryImpl.java,v $
 *  $Revision: 1.1 $  $Date: 2004/01/13 16:16:21 $ 
 */
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import org.eclipse.jem.java.*;
import org.eclipse.jem.internal.java.adapters.jdk.JavaJDKAdapterFactory;



/**
 * @generated
 */
public class JavaRefFactoryImpl extends EFactoryImpl implements JavaRefFactory {

	
	protected static Class ReflectionFactoryClass = JavaJDKAdapterFactory.class;
	public JavaRefFactoryImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass)
	{
		switch (eClass.getClassifierID()) {
			case JavaRefPackage.JAVA_CLASS: return createJavaClass();
			case JavaRefPackage.INITIALIZER: return createInitializer();
			case JavaRefPackage.JAVA_PARAMETER: return createJavaParameter();
			case JavaRefPackage.METHOD: return createMethod();
			case JavaRefPackage.FIELD: return createField();
			case JavaRefPackage.BLOCK: return createBlock();
			case JavaRefPackage.COMMENT: return createComment();
			case JavaRefPackage.STATEMENT: return createStatement();
			case JavaRefPackage.JAVA_PACKAGE: return createJavaPackage();
			case JavaRefPackage.JAVA_DATA_TYPE: return createJavaDataType();
			case JavaRefPackage.ARRAY_TYPE: return createArrayType();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object createFromString(EDataType eDataType, String initialValue)
	{
		switch (eDataType.getClassifierID()) {
			case JavaRefPackage.TYPE_KIND:
				return TypeKind.get(initialValue);
			case JavaRefPackage.JAVA_VISIBILITY_KIND:
				return JavaVisibilityKind.get(initialValue);
			case JavaRefPackage.JAVA_PARAMETER_KIND:
				return JavaParameterKind.get(initialValue);
			case JavaRefPackage.JTYPE_JAVA_HELPERS:
				return createJTypeJavaHelpersFromString(eDataType, initialValue);
			case JavaRefPackage.JTYPE_LIST:
				return createJTypeListFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertToString(EDataType eDataType, Object instanceValue)
	{
		switch (eDataType.getClassifierID()) {
			case JavaRefPackage.TYPE_KIND:
				return instanceValue == null ? null : instanceValue.toString();
			case JavaRefPackage.JAVA_VISIBILITY_KIND:
				return instanceValue == null ? null : instanceValue.toString();
			case JavaRefPackage.JAVA_PARAMETER_KIND:
				return instanceValue == null ? null : instanceValue.toString();
			case JavaRefPackage.JTYPE_JAVA_HELPERS:
				return convertJTypeJavaHelpersToString(eDataType, instanceValue);
			case JavaRefPackage.JTYPE_LIST:
				return convertJTypeListToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	public ArrayType createArrayType(JavaHelpers componentType) {
		ArrayType at = createArrayType();
		
		String computedName = componentType.getQualifiedName() + "[]";
		at.setName(computedName);
		return at;
	}
	public ArrayType createArrayType(JavaHelpers finalComponentType, int dimensions) {
		ArrayType at = createArrayType();
		
		String computedName = finalComponentType.getQualifiedName();
		for (int i = 0; i < dimensions; i++){
			computedName = computedName + "[]";
		}
		at.setName(computedName);
		return at;
	}
  public static ResourceSet createJavaContext() {
    org.eclipse.jem.internal.java.init.JavaInit.init();
    ResourceSet set = new ResourceSetImpl();
    set.getAdapterFactories().add(createJavaReflectionAdapterFactory());
    return set;
  }
	protected static AdapterFactory createJavaReflectionAdapterFactory() {
		AdapterFactory factoryInstance = null;
		try {
			if (getReflectionAdapterFactoryClass() != null)
				factoryInstance = (AdapterFactory) getReflectionAdapterFactoryClass().newInstance();
		} catch (Exception e) {
			// Reflection or instantiation problems.
			// OK, can't do Java Model reflection
		}
		return factoryInstance;
	}
	/**
	 * Return the Class for the Java refection adapter factory.
	 */
	public static Class getReflectionAdapterFactoryClass() {
		return ReflectionFactoryClass;
	}
	/**
	 * Set the Class for the Java refection adapter factory.
	 * 
	 * @see JavaPlugin.startup()
	 */
	public static void setReflectionAdapterFactoryClass(Class javaReflectionFactoryClass) {
		ReflectionFactoryClass = javaReflectionFactoryClass;
	}
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Method createMethod()
	{
		MethodImpl method = new MethodImpl();
		return method;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public JavaClass createJavaClass()
	{
		JavaClassImpl javaClass = new JavaClassImpl();
		return javaClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Field createField()
	{
		FieldImpl field = new FieldImpl();
		return field;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Block createBlock()
	{
		BlockImpl block = new BlockImpl();
		return block;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Comment createComment()
	{
		CommentImpl comment = new CommentImpl();
		return comment;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Statement createStatement()
	{
		StatementImpl statement = new StatementImpl();
		return statement;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Initializer createInitializer()
	{
		InitializerImpl initializer = new InitializerImpl();
		return initializer;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public JavaParameter createJavaParameter()
	{
		JavaParameterImpl javaParameter = new JavaParameterImpl();
		return javaParameter;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public ArrayType createArrayType()
	{
		ArrayTypeImpl arrayType = new ArrayTypeImpl();
		return arrayType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaHelpers createJTypeJavaHelpersFromString(EDataType eDataType, String initialValue)
	{
		return (JavaHelpers)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertJTypeJavaHelpersToString(EDataType eDataType, Object instanceValue)
	{
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List createJTypeListFromString(EDataType eDataType, String initialValue)
	{
		return (List)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertJTypeListToString(EDataType eDataType, Object instanceValue)
	{
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public JavaDataType createJavaDataType()
	{
		JavaDataTypeImpl javaDataType = new JavaDataTypeImpl();
		return javaDataType;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public JavaPackage createJavaPackage()
	{
		JavaPackageImpl javaPackage = new JavaPackageImpl();
		return javaPackage;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public JavaRefPackage getJavaRefPackage()
	{
		return (JavaRefPackage)getEPackage();
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static JavaRefPackage getPackage()
	{
		return JavaRefPackage.eINSTANCE;
	}
  public static JavaRefFactory getActiveFactory()
  {
    JavaRefPackage pkg = getPackage();
    if (pkg != null) return pkg.getJavaRefFactory();
    else return null;
  }}






