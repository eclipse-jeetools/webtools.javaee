/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.java;
/*
 *  $RCSfile: JavaClass.java,v $
 *  $Revision: 1.5 $  $Date: 2005/04/14 19:05:33 $ 
 */
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
/**
 * <!-- begin-user-doc -->
 * @extends JavaHelpers
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jem.java.JavaClass#getKind <em>Kind</em>}</li>
 *   <li>{@link org.eclipse.jem.java.JavaClass#isPublic <em>Public</em>}</li>
 *   <li>{@link org.eclipse.jem.java.JavaClass#isFinal <em>Final</em>}</li>
 *   <li>{@link org.eclipse.jem.java.JavaClass#getImplementsInterfaces <em>Implements Interfaces</em>}</li>
 *   <li>{@link org.eclipse.jem.java.JavaClass#getClassImport <em>Class Import</em>}</li>
 *   <li>{@link org.eclipse.jem.java.JavaClass#getPackageImports <em>Package Imports</em>}</li>
 *   <li>{@link org.eclipse.jem.java.JavaClass#getFields <em>Fields</em>}</li>
 *   <li>{@link org.eclipse.jem.java.JavaClass#getMethods <em>Methods</em>}</li>
 *   <li>{@link org.eclipse.jem.java.JavaClass#getInitializers <em>Initializers</em>}</li>
 *   <li>{@link org.eclipse.jem.java.JavaClass#getDeclaredClasses <em>Declared Classes</em>}</li>
 *   <li>{@link org.eclipse.jem.java.JavaClass#getDeclaringClass <em>Declaring Class</em>}</li>
 *   <li>{@link org.eclipse.jem.java.JavaClass#getJavaPackage <em>Java Package</em>}</li>
 *   <li>{@link org.eclipse.jem.java.JavaClass#getEvents <em>Events</em>}</li>
 *   <li>{@link org.eclipse.jem.java.JavaClass#getAllEvents <em>All Events</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jem.java.JavaRefPackage#getJavaClass()
 * @model
 * @generated
 */
public interface JavaClass extends EClass, JavaHelpers{

	public static final String DEFAULT_METHOD_NAME = "*";
	/**
	 * Return a method matching the name, and non-return parameters with fully qualified types matching all the types in the list, if it exists.  It will not look up the supertype hierarchy.
	 */
	public Method getPublicMethodExtended(String methodName, List parameterTypes) ;
	/**
	 * Returns a filtered list on the methods of this class, having a name equal to that of the parameter.
	 */
	public List getPublicMethodsExtendedNamed(String name);

	/**
	 * Return an array listing our Fields, including inherited fields.
	 * The field relationship is derived from contents.
	 */  
	public Field[] listFieldExtended();
	/**
	 * Return an array listing our Methods, including inherited methods.
	 * The method relationship is derived from contents.
	 */  
	public Method[] listMethodExtended();
//FB   /**
//FB    * This is required for internal reflection. Do not use.
//FB    */
//FB   Object primRefValue(EObject feature);
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Kind attribute
	 */
	TypeKind getKind();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.java.JavaClass#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see org.eclipse.jem.java.TypeKind
	 * @see #getKind()
	 * @generated
	 */
	void setKind(TypeKind value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the IsPublic attribute
	 */
	boolean isPublic();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.java.JavaClass#isPublic <em>Public</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Public</em>' attribute.
	 * @see #isPublic()
	 * @generated
	 */
	void setPublic(boolean value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the IsFinal attribute
	 */
	boolean isFinal();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.java.JavaClass#isFinal <em>Final</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Final</em>' attribute.
	 * @see #isFinal()
	 * @generated
	 */
	void setFinal(boolean value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of ImplementsInterfaces references
	 */
	EList getImplementsInterfaces();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of ClassImport references
	 */
	EList getClassImport();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of PackageImports references
	 */
	EList getPackageImports();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of Fields references
	 */
	EList getFields();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of Methods references
	 */
	EList getMethods();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of Initializers references
	 */
	EList getInitializers();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The DeclaringClass reference
	 */
	JavaClass getDeclaringClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.java.JavaClass#getDeclaringClass <em>Declaring Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Declaring Class</em>' reference.
	 * @see #getDeclaringClass()
	 * @generated
	 */
	void setDeclaringClass(JavaClass value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of DeclaredClasses references
	 */
	EList getDeclaredClasses();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The JavaPackage reference
	 */
	JavaPackage getJavaPackage();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of Events references
	 */
	EList getEvents();

	/**
	 * Returns the value of the '<em><b>All Events</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.jem.java.JavaEvent}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>All Events</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>All Events</em>' reference list.
	 * @see org.eclipse.jem.java.JavaRefPackage#getJavaClass_AllEvents()
	 * @model type="org.eclipse.jem.java.JavaEvent" transient="true" volatile="true"
	 * @generated
	 */
	EList getAllEvents();

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	boolean isNested();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Return an Field with the passed name, or null.
	 */
	Field getField(String fieldName);

	/**
	 * @generated This field/method will be replaced during code generation 
	 *  Return an Field with the passed name from this JavaClass or any supertypes.
	 * 
	 * Return null if a Field named fieldName is not found.
	 */
	Field getFieldExtended(String fieldName);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Return an Field with the passed name, or null.
	 */
	Field getFieldNamed(String fieldName);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Return all fields, including those from supertypes.
	 */
	List getFieldsExtended();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Get the method of this name and these parameters. It will not look up the
	 * supertype hierarchy.
	 */
	Method getMethod(String methodName, List parameterTypes);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Return a List of Strings that represent MethodElement signatures from most
	 * general to most specific.
	 */
	List getMethodElementSignatures();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Get the method of this name and these parameters. It will look up the supertype 
	 * hierarchy.
	 */
	Method getMethodExtended(String methodName, List parameterTypes);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Return all methods, including those from supertypes.
	 */
	List getMethodsExtended();

	/**
	 * Return all methods, including those from supertypes excluding particular classes and methods.
	 * @param excludedClasses - A list of JavaClass instances to be ignored.
	 * @param excludedMethods - A list of Method instances to be ignored.
	 */
	List getMethodsExtendedWithFilters(List excludedClasses, List excludedMethods) ;

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Return a List of Methods that begins with @aMethodNamePrefix and is not
	 * included in the @excludedNames list. If @aMethodNamePrefix is null, all methods 
	 * will be returned.

	 */
	List getOnlySpecificMethods(String aMethodNamePrefix, List excludedNames);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Return a method matching the name, and non-return parameters with fully
	 * qualified types matching all the types in the list, if it exists.  It will not
	 * look up the supertype hierarchy.
	 */
	Method getPublicMethod(String methodName, List parameterTypes);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Return all methods, it will not go up the supertype hierarchy.
	 */
	List getPublicMethods();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Return all public methods, including those from supertypes.
	 */
	List getPublicMethodsExtended();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Returns a filtered list on the methods of this class, having a name equal to
	 * that of the parameter.
	 */
	List getPublicMethodsNamed(String name);

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	JavaClass getSupertype();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Test whether the receiver implements the passed interface (or one of its
	 * supertypes).
	 */
	boolean implementsInterface(JavaClass interfaceType);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Return a string showing our details.
	 */
	String infoString();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Tests whether this class inherits from the passed in class.
	 */
	boolean inheritsFrom(JavaClass javaClass);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Does this type exist.
	 */
	boolean isExistingType();

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Is this an interface.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
  boolean isInterface();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Set the supertype.
	 */
	void setSupertype(JavaClass javaclass) throws InheritanceCycleException;

	/**
	 * Return true if the passed JavaClass can be set as the supertype
	 * of this JavaClass.  This method will test to see if aJavaClass will
	 * create a cycle in the inheritance structure.  Return true if no
	 * inheritance cycle will be created.
	 */
	public boolean isValidSupertype(JavaClass aJavaClass) ;

	/**
	 * Get the EStructuralFeatures that represent the BeanInfo properties for
	 * just this Java class, not including inherited. It will return any EStructuralFeature
	 * that was specified as being at this class either through the XMI override document or
	 * through the BeanInfo. Structural features through the XMI may not be bean properties,
	 * but they are part of the features available at this class only. 
	 * 
	 * This is a combination of getEAttributes() and getEReferences().
	 * 
	 * To retrieve only the Bean Properties, then use Beaninfo 
	 * Utilities.getPropertiesIterator(javaclass.getProperties()) and this will iterate over
	 * the Bean PropertyDecorators, and filter out the non-bean features.
	 * 
	 * @return The list of properties (as EStructuralFeature's) for this java class. This list is
	 * not modifiable.
	 */
	EList getProperties();
	
	/**
	 * Get all of the EStructuralFeatures that represent the BeanInfo properties for
	 * just this Java class, including inherited. It will return any EStructuralFeature
	 * that was specified through the XMI override document or
	 * through the BeanInfo. Structural features through the XMI may not be bean properties,
	 * but they are part of the features available.
	 * 
	 * The is like getEAllStructuralFeatures() except that if BeanInfo hides any bean properties
	 * from the super class, this list will also hide them, while getEAllStructuralFeatures
	 * will not.
	 * 
	 * To retrieve only the Bean Properties, then use Beaninfo 
	 * Utilities.getPropertiesIterator(javaclass.getAllProperties()) and this will iterate over
	 * the Bean PropertyDecorators, and filter out the non-bean features.
	 * 
	 * @return The list of all properties, including inherited, (as EStructuralFeature's) for this class. This list is
	 * not modifiable.
	 */
	EList getAllProperties();
	
	
	/**
	 * Return the reflection type, if it exists, for this class. If running within Eclipse this
	 * will be an {@link org.eclipse.jdt.core.IType}, or running outside of Eclipse it will be a {@link java.lang.Class}.
	 * 
	 * @return IType if in Eclipse, Class if outside Eclipse, or <code>null</code> if type not found.
	 * 
	 * @since 1.1.0
	 */
	Object getReflectionType();

}





