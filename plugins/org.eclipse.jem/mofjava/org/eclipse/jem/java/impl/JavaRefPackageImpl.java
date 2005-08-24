/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.java.impl;
/*
 *  $RCSfile: JavaRefPackageImpl.java,v $
 *  $Revision: 1.8 $  $Date: 2005/08/24 20:20:25 $ 
 */
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.impl.EcorePackageImpl;


import org.eclipse.jem.java.ArrayType;
import org.eclipse.jem.java.Block;
import org.eclipse.jem.java.Comment;
import org.eclipse.jem.java.Field;
import org.eclipse.jem.java.Initializer;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaDataType;
import org.eclipse.jem.java.JavaEvent;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.JavaPackage;
import org.eclipse.jem.java.JavaParameter;
import org.eclipse.jem.java.JavaParameterKind;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jem.java.JavaRefPackage;
import org.eclipse.jem.java.JavaVisibilityKind;
import org.eclipse.jem.java.Method;
import org.eclipse.jem.java.Statement;
import org.eclipse.jem.java.TypeKind;


/**
 * @lastgen class JavaRefPackageImpl extends EPackageImpl implements JavaRefPackage, EPackage {}
 */
public class JavaRefPackageImpl extends EPackageImpl implements JavaRefPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass javaClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass initializerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass javaParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass methodEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass blockEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass commentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass statementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass javaPackageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass javaDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass arrayTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass javaEventEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum typeKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum javaVisibilityKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum javaParameterKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType jTypeJavaHelpersEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType jTypeListEDataType = null;

		   /**
		 * @generated This field/method will be replaced during code generation.
		 */
	private JavaRefPackageImpl() {
		super(eNS_URI, JavaRefFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static JavaRefPackage init() {
		if (isInited) return (JavaRefPackage)EPackage.Registry.INSTANCE.getEPackage(JavaRefPackage.eNS_URI);

		// Obtain or create and register package
		JavaRefPackageImpl theJavaRefPackage = (JavaRefPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof JavaRefPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new JavaRefPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackageImpl.init();

		// Create package meta-data objects
		theJavaRefPackage.createPackageContents();

		// Initialize created meta-data
		theJavaRefPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theJavaRefPackage.freeze();

		return theJavaRefPackage;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getJavaClass() {
		return javaClassEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getJavaClass_Kind() {
		return (EAttribute)javaClassEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJavaClass_Public() {
		return (EAttribute)javaClassEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJavaClass_Final() {
		return (EAttribute)javaClassEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getJavaClass_ImplementsInterfaces() {
		return (EReference)javaClassEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getJavaClass_ClassImport() {
		return (EReference)javaClassEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getJavaClass_PackageImports() {
		return (EReference)javaClassEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getJavaClass_Fields() {
		return (EReference)javaClassEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getJavaClass_Methods() {
		return (EReference)javaClassEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getJavaClass_Initializers() {
		return (EReference)javaClassEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getJavaClass_DeclaringClass() {
		return (EReference)javaClassEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getJavaClass_DeclaredClasses() {
		return (EReference)javaClassEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getJavaClass_JavaPackage() {
		return (EReference)javaClassEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getJavaClass_Events() {
		return (EReference)javaClassEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getJavaClass_AllEvents() {
		return (EReference)javaClassEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getField() {
		return fieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getField_Final() {
		return (EAttribute)fieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getField_Static() {
		return (EAttribute)fieldEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getField_JavaVisibility() {
		return (EAttribute)fieldEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getField_Transient() {
		return (EAttribute)fieldEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getField_Volatile() {
		return (EAttribute)fieldEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getField_JavaClass() {
		return (EReference)fieldEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getField_Initializer() {
		return (EReference)fieldEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getMethod() {
		return methodEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethod_Abstract() {
		return (EAttribute)methodEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethod_Native() {
		return (EAttribute)methodEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethod_Synchronized() {
		return (EAttribute)methodEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethod_Final() {
		return (EAttribute)methodEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethod_Constructor() {
		return (EAttribute)methodEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethod_Static() {
		return (EAttribute)methodEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getMethod_JavaVisibility() {
		return (EAttribute)methodEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getMethod_Parameters() {
		return (EReference)methodEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getMethod_JavaExceptions() {
		return (EReference)methodEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getMethod_JavaClass() {
		return (EReference)methodEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getMethod_Source() {
		return (EReference)methodEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getJavaParameter() {
		return javaParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJavaParameter_Final() {
		return (EAttribute)javaParameterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getJavaParameter_ParameterKind() {
		return (EAttribute)javaParameterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getArrayType() {
		return arrayTypeEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getArrayType_ArrayDimensions() {
		return (EAttribute)arrayTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getArrayType_ComponentType() {
		return (EReference)arrayTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getJavaDataType() {
		return javaDataTypeEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getJavaEvent() {
		return javaEventEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getJavaPackage() {
		return javaPackageEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getJavaPackage_JavaClasses() {
		return (EReference)javaPackageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getBlock() {
		return blockEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getBlock_Source() {
		return (EAttribute)blockEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getBlock_Name() {
		return (EAttribute)blockEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getBlock_Contents() {
		return (EReference)blockEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getComment() {
		return commentEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getStatement() {
		return statementEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getInitializer() {
		return initializerEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getInitializer_IsStatic() {
		return (EAttribute)initializerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getInitializer_JavaClass() {
		return (EReference)initializerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getInitializer_Source() {
		return (EReference)initializerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EEnum getJavaVisibilityKind() {
		return javaVisibilityKindEEnum;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EEnum getJavaParameterKind() {
		return javaParameterKindEEnum;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EEnum getTypeKind() {
		return typeKindEEnum;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EDataType getJTypeList() {
		return jTypeListEDataType;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EDataType getJTypeJavaHelpers() {
		return jTypeJavaHelpersEDataType;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public JavaRefFactory getJavaRefFactory() {
		return (JavaRefFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		javaClassEClass = createEClass(JAVA_CLASS);
		createEAttribute(javaClassEClass, JAVA_CLASS__KIND);
		createEAttribute(javaClassEClass, JAVA_CLASS__PUBLIC);
		createEAttribute(javaClassEClass, JAVA_CLASS__FINAL);
		createEReference(javaClassEClass, JAVA_CLASS__IMPLEMENTS_INTERFACES);
		createEReference(javaClassEClass, JAVA_CLASS__CLASS_IMPORT);
		createEReference(javaClassEClass, JAVA_CLASS__PACKAGE_IMPORTS);
		createEReference(javaClassEClass, JAVA_CLASS__FIELDS);
		createEReference(javaClassEClass, JAVA_CLASS__METHODS);
		createEReference(javaClassEClass, JAVA_CLASS__INITIALIZERS);
		createEReference(javaClassEClass, JAVA_CLASS__DECLARED_CLASSES);
		createEReference(javaClassEClass, JAVA_CLASS__DECLARING_CLASS);
		createEReference(javaClassEClass, JAVA_CLASS__JAVA_PACKAGE);
		createEReference(javaClassEClass, JAVA_CLASS__EVENTS);
		createEReference(javaClassEClass, JAVA_CLASS__ALL_EVENTS);

		initializerEClass = createEClass(INITIALIZER);
		createEAttribute(initializerEClass, INITIALIZER__IS_STATIC);
		createEReference(initializerEClass, INITIALIZER__JAVA_CLASS);
		createEReference(initializerEClass, INITIALIZER__SOURCE);

		javaParameterEClass = createEClass(JAVA_PARAMETER);
		createEAttribute(javaParameterEClass, JAVA_PARAMETER__FINAL);
		createEAttribute(javaParameterEClass, JAVA_PARAMETER__PARAMETER_KIND);

		methodEClass = createEClass(METHOD);
		createEAttribute(methodEClass, METHOD__ABSTRACT);
		createEAttribute(methodEClass, METHOD__NATIVE);
		createEAttribute(methodEClass, METHOD__SYNCHRONIZED);
		createEAttribute(methodEClass, METHOD__FINAL);
		createEAttribute(methodEClass, METHOD__CONSTRUCTOR);
		createEAttribute(methodEClass, METHOD__STATIC);
		createEAttribute(methodEClass, METHOD__JAVA_VISIBILITY);
		createEReference(methodEClass, METHOD__PARAMETERS);
		createEReference(methodEClass, METHOD__JAVA_EXCEPTIONS);
		createEReference(methodEClass, METHOD__JAVA_CLASS);
		createEReference(methodEClass, METHOD__SOURCE);

		fieldEClass = createEClass(FIELD);
		createEAttribute(fieldEClass, FIELD__FINAL);
		createEAttribute(fieldEClass, FIELD__STATIC);
		createEAttribute(fieldEClass, FIELD__JAVA_VISIBILITY);
		createEAttribute(fieldEClass, FIELD__TRANSIENT);
		createEAttribute(fieldEClass, FIELD__VOLATILE);
		createEReference(fieldEClass, FIELD__JAVA_CLASS);
		createEReference(fieldEClass, FIELD__INITIALIZER);

		blockEClass = createEClass(BLOCK);
		createEAttribute(blockEClass, BLOCK__SOURCE);
		createEAttribute(blockEClass, BLOCK__NAME);
		createEReference(blockEClass, BLOCK__CONTENTS);

		commentEClass = createEClass(COMMENT);

		statementEClass = createEClass(STATEMENT);

		javaPackageEClass = createEClass(JAVA_PACKAGE);
		createEReference(javaPackageEClass, JAVA_PACKAGE__JAVA_CLASSES);

		javaDataTypeEClass = createEClass(JAVA_DATA_TYPE);

		arrayTypeEClass = createEClass(ARRAY_TYPE);
		createEAttribute(arrayTypeEClass, ARRAY_TYPE__ARRAY_DIMENSIONS);
		createEReference(arrayTypeEClass, ARRAY_TYPE__COMPONENT_TYPE);

		javaEventEClass = createEClass(JAVA_EVENT);

		// Create enums
		typeKindEEnum = createEEnum(TYPE_KIND);
		javaVisibilityKindEEnum = createEEnum(JAVA_VISIBILITY_KIND);
		javaParameterKindEEnum = createEEnum(JAVA_PARAMETER_KIND);

		// Create data types
		jTypeJavaHelpersEDataType = createEDataType(JTYPE_JAVA_HELPERS);
		jTypeListEDataType = createEDataType(JTYPE_LIST);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		EcorePackageImpl theEcorePackage = (EcorePackageImpl)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Add supertypes to classes
		javaClassEClass.getESuperTypes().add(theEcorePackage.getEClass());
		javaParameterEClass.getESuperTypes().add(theEcorePackage.getEParameter());
		methodEClass.getESuperTypes().add(theEcorePackage.getEOperation());
		fieldEClass.getESuperTypes().add(theEcorePackage.getETypedElement());
		commentEClass.getESuperTypes().add(this.getBlock());
		statementEClass.getESuperTypes().add(this.getBlock());
		javaPackageEClass.getESuperTypes().add(theEcorePackage.getEPackage());
		javaDataTypeEClass.getESuperTypes().add(theEcorePackage.getEClass());
		arrayTypeEClass.getESuperTypes().add(this.getJavaClass());
		javaEventEClass.getESuperTypes().add(theEcorePackage.getEStructuralFeature());

		// Initialize classes and features; add operations and parameters
		initEClass(javaClassEClass, JavaClass.class, "JavaClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getJavaClass_Kind(), this.getTypeKind(), "kind", null, 0, 1, JavaClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getJavaClass_Public(), ecorePackage.getEBoolean(), "public", null, 0, 1, JavaClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getJavaClass_Final(), ecorePackage.getEBoolean(), "final", null, 0, 1, JavaClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJavaClass_ImplementsInterfaces(), this.getJavaClass(), null, "implementsInterfaces", null, 0, -1, JavaClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJavaClass_ClassImport(), this.getJavaClass(), null, "classImport", null, 0, -1, JavaClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJavaClass_PackageImports(), this.getJavaPackage(), null, "packageImports", null, 0, -1, JavaClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJavaClass_Fields(), this.getField(), this.getField_JavaClass(), "fields", null, 0, -1, JavaClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJavaClass_Methods(), this.getMethod(), this.getMethod_JavaClass(), "methods", null, 0, -1, JavaClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJavaClass_Initializers(), this.getInitializer(), this.getInitializer_JavaClass(), "initializers", null, 0, -1, JavaClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJavaClass_DeclaredClasses(), this.getJavaClass(), this.getJavaClass_DeclaringClass(), "declaredClasses", null, 0, -1, JavaClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJavaClass_DeclaringClass(), this.getJavaClass(), this.getJavaClass_DeclaredClasses(), "declaringClass", null, 0, 1, JavaClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJavaClass_JavaPackage(), this.getJavaPackage(), this.getJavaPackage_JavaClasses(), "javaPackage", null, 0, 1, JavaClass.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJavaClass_Events(), this.getJavaEvent(), null, "events", null, 0, -1, JavaClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJavaClass_AllEvents(), this.getJavaEvent(), null, "allEvents", null, 0, -1, JavaClass.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(javaClassEClass, ecorePackage.getEBoolean(), "isNested");

		EOperation op = addEOperation(javaClassEClass, this.getField(), "getField");
		addEParameter(op, ecorePackage.getEString(), "fieldName");

		op = addEOperation(javaClassEClass, this.getField(), "getFieldExtended");
		addEParameter(op, ecorePackage.getEString(), "fieldName");

		op = addEOperation(javaClassEClass, this.getField(), "getFieldNamed");
		addEParameter(op, ecorePackage.getEString(), "fieldName");

		addEOperation(javaClassEClass, this.getJTypeList(), "getFieldsExtended");

		op = addEOperation(javaClassEClass, this.getMethod(), "getMethod");
		addEParameter(op, ecorePackage.getEString(), "methodName");
		addEParameter(op, this.getJTypeList(), "parameterTypes");

		addEOperation(javaClassEClass, this.getJTypeList(), "getMethodElementSignatures");

		op = addEOperation(javaClassEClass, this.getMethod(), "getMethodExtended");
		addEParameter(op, ecorePackage.getEString(), "methodName");
		addEParameter(op, this.getJTypeList(), "parameterTypes");

		addEOperation(javaClassEClass, this.getJTypeList(), "getMethodsExtended");

		op = addEOperation(javaClassEClass, this.getJTypeList(), "getOnlySpecificMethods");
		addEParameter(op, ecorePackage.getEString(), "aMethodNamePrefix");
		addEParameter(op, this.getJTypeList(), "excludedNames");

		op = addEOperation(javaClassEClass, this.getMethod(), "getPublicMethod");
		addEParameter(op, ecorePackage.getEString(), "methodName");
		addEParameter(op, this.getJTypeList(), "parameterTypes");

		addEOperation(javaClassEClass, this.getJTypeList(), "getPublicMethods");

		addEOperation(javaClassEClass, this.getJTypeList(), "getPublicMethodsExtended");

		op = addEOperation(javaClassEClass, this.getJTypeList(), "getPublicMethodsNamed");
		addEParameter(op, ecorePackage.getEString(), "name");

		addEOperation(javaClassEClass, this.getJavaClass(), "getSupertype");

		op = addEOperation(javaClassEClass, ecorePackage.getEBoolean(), "implementsInterface");
		addEParameter(op, this.getJavaClass(), "interfaceType");

		addEOperation(javaClassEClass, ecorePackage.getEString(), "infoString");

		op = addEOperation(javaClassEClass, ecorePackage.getEBoolean(), "inheritsFrom");
		addEParameter(op, this.getJavaClass(), "javaClass");

		addEOperation(javaClassEClass, ecorePackage.getEBoolean(), "isExistingType");

		addEOperation(javaClassEClass, ecorePackage.getEBoolean(), "isInterface");

		op = addEOperation(javaClassEClass, null, "setSupertype");
		addEParameter(op, this.getJavaClass(), "javaclass");

		initEClass(initializerEClass, Initializer.class, "Initializer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInitializer_IsStatic(), ecorePackage.getEBooleanObject(), "isStatic", null, 0, 1, Initializer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInitializer_JavaClass(), this.getJavaClass(), this.getJavaClass_Initializers(), "javaClass", null, 0, 1, Initializer.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInitializer_Source(), this.getBlock(), null, "source", null, 0, 1, Initializer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(javaParameterEClass, JavaParameter.class, "JavaParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getJavaParameter_Final(), ecorePackage.getEBoolean(), "final", null, 0, 1, JavaParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getJavaParameter_ParameterKind(), this.getJavaParameterKind(), "parameterKind", null, 0, 1, JavaParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(javaParameterEClass, ecorePackage.getEBoolean(), "isArray");

		addEOperation(javaParameterEClass, ecorePackage.getEBoolean(), "isReturn");

		addEOperation(javaParameterEClass, this.getJTypeJavaHelpers(), "getJavaType");

		addEOperation(javaParameterEClass, ecorePackage.getEString(), "getQualifiedName");

		initEClass(methodEClass, Method.class, "Method", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMethod_Abstract(), ecorePackage.getEBoolean(), "abstract", null, 0, 1, Method.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMethod_Native(), ecorePackage.getEBoolean(), "native", null, 0, 1, Method.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMethod_Synchronized(), ecorePackage.getEBoolean(), "synchronized", null, 0, 1, Method.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMethod_Final(), ecorePackage.getEBoolean(), "final", null, 0, 1, Method.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMethod_Constructor(), ecorePackage.getEBoolean(), "constructor", null, 0, 1, Method.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMethod_Static(), ecorePackage.getEBoolean(), "static", null, 0, 1, Method.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMethod_JavaVisibility(), this.getJavaVisibilityKind(), "javaVisibility", null, 0, 1, Method.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMethod_Parameters(), this.getJavaParameter(), null, "parameters", null, 0, -1, Method.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMethod_JavaExceptions(), this.getJavaClass(), null, "javaExceptions", null, 0, -1, Method.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMethod_JavaClass(), this.getJavaClass(), this.getJavaClass_Methods(), "javaClass", null, 0, 1, Method.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMethod_Source(), this.getBlock(), null, "source", null, 0, 1, Method.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(methodEClass, this.getJavaClass(), "getContainingJavaClass");

		addEOperation(methodEClass, ecorePackage.getEString(), "getMethodElementSignature");

		op = addEOperation(methodEClass, this.getJavaParameter(), "getParameter");
		addEParameter(op, ecorePackage.getEString(), "parameterName");

		addEOperation(methodEClass, this.getJTypeJavaHelpers(), "getReturnType");

		op = addEOperation(methodEClass, null, "setReturnType");
		addEParameter(op, this.getJTypeJavaHelpers(), "type");

		addEOperation(methodEClass, ecorePackage.getEString(), "getSignature");

		addEOperation(methodEClass, ecorePackage.getEBoolean(), "isGenerated");

		op = addEOperation(methodEClass, null, "setIsGenerated");
		addEParameter(op, ecorePackage.getEBoolean(), "generated");

		addEOperation(methodEClass, ecorePackage.getEBoolean(), "isVoid");

		initEClass(fieldEClass, Field.class, "Field", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getField_Final(), ecorePackage.getEBoolean(), "final", null, 0, 1, Field.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getField_Static(), ecorePackage.getEBoolean(), "static", null, 0, 1, Field.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getField_JavaVisibility(), this.getJavaVisibilityKind(), "javaVisibility", null, 0, 1, Field.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getField_Transient(), ecorePackage.getEBoolean(), "transient", null, 0, 1, Field.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getField_Volatile(), ecorePackage.getEBoolean(), "volatile", null, 0, 1, Field.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getField_JavaClass(), this.getJavaClass(), this.getJavaClass_Fields(), "javaClass", null, 0, 1, Field.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getField_Initializer(), this.getBlock(), null, "initializer", null, 0, 1, Field.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(fieldEClass, this.getJavaClass(), "getContainingJavaClass");

		addEOperation(fieldEClass, ecorePackage.getEBoolean(), "isArray");

		initEClass(blockEClass, Block.class, "Block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBlock_Source(), ecorePackage.getEString(), "source", null, 0, 1, Block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBlock_Name(), ecorePackage.getEString(), "name", null, 0, 1, Block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBlock_Contents(), this.getBlock(), null, "contents", null, 0, -1, Block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(commentEClass, Comment.class, "Comment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(statementEClass, Statement.class, "Statement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(javaPackageEClass, JavaPackage.class, "JavaPackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getJavaPackage_JavaClasses(), this.getJavaClass(), this.getJavaClass_JavaPackage(), "javaClasses", null, 0, -1, JavaPackage.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(javaDataTypeEClass, JavaDataType.class, "JavaDataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(javaDataTypeEClass, ecorePackage.getEString(), "getDefaultValueString");

		initEClass(arrayTypeEClass, ArrayType.class, "ArrayType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getArrayType_ArrayDimensions(), ecorePackage.getEInt(), "arrayDimensions", null, 0, 1, ArrayType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArrayType_ComponentType(), theEcorePackage.getEClassifier(), null, "componentType", null, 1, 1, ArrayType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(arrayTypeEClass, this.getJTypeJavaHelpers(), "getFinalComponentType");

		addEOperation(arrayTypeEClass, this.getJTypeJavaHelpers(), "getComponentTypeAsHelper");

		addEOperation(arrayTypeEClass, ecorePackage.getEBoolean(), "isPrimitiveArray");

		op = addEOperation(arrayTypeEClass, null, "setComponentType");
		addEParameter(op, this.getJTypeJavaHelpers(), "helperComponentType");

		initEClass(javaEventEClass, JavaEvent.class, "JavaEvent", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Initialize enums and add enum literals
		initEEnum(typeKindEEnum, TypeKind.class, "TypeKind");
		addEEnumLiteral(typeKindEEnum, TypeKind.UNDEFINED_LITERAL);
		addEEnumLiteral(typeKindEEnum, TypeKind.CLASS_LITERAL);
		addEEnumLiteral(typeKindEEnum, TypeKind.INTERFACE_LITERAL);
		addEEnumLiteral(typeKindEEnum, TypeKind.EXCEPTION_LITERAL);

		initEEnum(javaVisibilityKindEEnum, JavaVisibilityKind.class, "JavaVisibilityKind");
		addEEnumLiteral(javaVisibilityKindEEnum, JavaVisibilityKind.PUBLIC_LITERAL);
		addEEnumLiteral(javaVisibilityKindEEnum, JavaVisibilityKind.PRIVATE_LITERAL);
		addEEnumLiteral(javaVisibilityKindEEnum, JavaVisibilityKind.PROTECTED_LITERAL);
		addEEnumLiteral(javaVisibilityKindEEnum, JavaVisibilityKind.PACKAGE_LITERAL);

		initEEnum(javaParameterKindEEnum, JavaParameterKind.class, "JavaParameterKind");
		addEEnumLiteral(javaParameterKindEEnum, JavaParameterKind.IN_LITERAL);
		addEEnumLiteral(javaParameterKindEEnum, JavaParameterKind.OUT_LITERAL);
		addEEnumLiteral(javaParameterKindEEnum, JavaParameterKind.INOUT_LITERAL);
		addEEnumLiteral(javaParameterKindEEnum, JavaParameterKind.RETURN_LITERAL);

		// Initialize data types
		initEDataType(jTypeJavaHelpersEDataType, JavaHelpers.class, "JTypeJavaHelpers", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(jTypeListEDataType, List.class, "JTypeList", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //JavaRefPackageImpl






