package org.eclipse.jem.internal.java;

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
 *  $RCSfile: Method.java,v $
 *  $Revision: 1.1.4.1 $  $Date: 2003/12/16 19:29:34 $ 
 */
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EOperation;
/**
 * @generated
 */
public interface Method extends EOperation{

	public static final String GENERATED_COMMENT_TAG = "@generated";
	/**
	 * Lists all the parameters, excluding the return type
	 */ 
	public JavaParameter[] listParametersWithoutReturn();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the IsAbstract attribute
	 */
	boolean isAbstract();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.java.Method#isAbstract <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Abstract</em>' attribute.
	 * @see #isAbstract()
	 * @generated
	 */
	void setAbstract(boolean value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the IsNative attribute
	 */
	boolean isNative();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.java.Method#isNative <em>Native</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Native</em>' attribute.
	 * @see #isNative()
	 * @generated
	 */
	void setNative(boolean value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the IsSynchronized attribute
	 */
	boolean isSynchronized();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.java.Method#isSynchronized <em>Synchronized</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Synchronized</em>' attribute.
	 * @see #isSynchronized()
	 * @generated
	 */
	void setSynchronized(boolean value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the IsFinal attribute
	 */
	boolean isFinal();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.java.Method#isFinal <em>Final</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Final</em>' attribute.
	 * @see #isFinal()
	 * @generated
	 */
	void setFinal(boolean value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the IsConstructor attribute
	 */
	boolean isConstructor();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.java.Method#isConstructor <em>Constructor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constructor</em>' attribute.
	 * @see #isConstructor()
	 * @generated
	 */
	void setConstructor(boolean value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the IsStatic attribute
	 */
	boolean isStatic();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.java.Method#isStatic <em>Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Static</em>' attribute.
	 * @see #isStatic()
	 * @generated
	 */
	void setStatic(boolean value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the JavaVisibility attribute
	 */
	JavaVisibilityKind getJavaVisibility();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.java.Method#getJavaVisibility <em>Java Visibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Java Visibility</em>' attribute.
	 * @see org.eclipse.jem.internal.java.JavaVisibilityKind
	 * @see #getJavaVisibility()
	 * @generated
	 */
	void setJavaVisibility(JavaVisibilityKind value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of Parameters references
	 */
	EList getParameters();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of JavaExceptions references
	 */
	EList getJavaExceptions();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The JavaClass reference
	 */
	JavaClass getJavaClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.java.Method#getJavaClass <em>Java Class</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Java Class</em>' container reference.
	 * @see #getJavaClass()
	 * @generated
	 */
	void setJavaClass(JavaClass value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The Source reference
	 */
	Block getSource();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.java.Method#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(Block value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Return the java class that this method is defined in.
	 */
	JavaClass getContainingJavaClass();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Return a String with the the method name and its parameters. e.g. <code>
	 * setFirstName(java.lang.String) <//code> .
	 *  
	 */
	String getMethodElementSignature();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Return a Parameter with the passed name, or null.
	 */
	JavaParameter getParameter(String parameterName);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Get the return type.
	 */
	JavaHelpers getReturnType();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Set the return type
	 * <!-- end-model-doc -->
	 * @model parameters="org.eclipse.jem.internal.java.JTypeJavaHelpers"
	 * @generated
	 */
	void setReturnType(JavaHelpers type);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Replicate the functionality of java.lang.reflect.Method.toString().
	 * 
	 * Returns a string describing this Method.  The string is formatted as the method 
	 * access modifiers, if any, followed by the method return type, followed by a
	 * space, followed by the class declaring the method, followed by a period,
	 * followed by the method name, followed by a parenthesized, comma-separated list
	 * of the method's formal parameter types. If the method throws checked
	 * exceptions, the parameter list is followed by a space, followed by the word
	 * throws followed by a comma-separated list of the thrown exception types.
	 * 
	 * For example:
	 * 
	 *     public boolean java.lang.Object.equals(java.lang.Object)
	 * 
	 * The access modifiers are placed in canonical order as specified by "The Java
	 * Language Specification".  This is public, <tt>protected<//tt> or
	 * <tt>private<//tt> first, and then other modifiers in the following order:
	 * <tt>abstract<//tt>, <tt>static<//tt>, <tt>final<//tt>, <tt>synchronized<//tt>
	 * <tt>native<//tt>.

	 */
	String getSignature();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Returns true if the method is system generated.
	 * This is usually determined by the @generated tag in the comment.
	 */
	boolean isGenerated();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Set the isGenerated flag.
	 * <!-- end-model-doc -->
	 * @model 
	 * @generated
	 */
	void setIsGenerated(boolean generated);

    /**
	 * @generated This field/method will be replaced during code generation 
	 * Is this a void return type method.
	 */
	boolean isVoid();

}





