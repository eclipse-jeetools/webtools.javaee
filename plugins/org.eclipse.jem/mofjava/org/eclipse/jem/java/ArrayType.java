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
 *  $RCSfile: ArrayType.java,v $
 *  $Revision: 1.3 $  $Date: 2005/02/15 22:37:02 $ 
 */

import org.eclipse.emf.ecore.EClassifier;
/**
 * Describes a Java Array type
 *    For multi-dimensional arrays, it is unlikely that the component type will be
 *    specified directly.  This would require instantiating a chain of component types
 *    such as String[][][][]->String[][][]->String[][]->String[]->String.
 * 
 *   The component type relationship will be computed if the finalComponentType
 *   and array dimensions is specified.
 *  
 *   For this reason, the preferred way to create is through the JavaRefFactory factory method:
 *        createArrayType(JavaClass finalComponentType, int dimensions)
 */
public interface ArrayType extends JavaClass{

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the ArrayDimensions attribute
	 */
	int getArrayDimensions();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the ArrayDimensions attribute
	 */
	void setArrayDimensions(int value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The ComponentType reference
	 */
	EClassifier getComponentType();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.java.ArrayType#getComponentType <em>Component Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component Type</em>' reference.
	 * @see #getComponentType()
	 * @generated
	 */
	void setComponentType(EClassifier value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Get the final component type for this Array Type.
	 * 
	 * In order to ensure a unique instance, we will resolve this type using
	 * reflection. It turns out to be most efficient to just do this by trimming the
	 * name.
	 */
	JavaHelpers getFinalComponentType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Get the component type of this array. 
	 * 
	 * If this is a multi-dimensional array, the component type will be the nested
	 * array type.
	 */
	JavaHelpers getComponentTypeAsHelper();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Is this an array of java primitives
	 */
	boolean isPrimitiveArray();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Set the component type.
	 */
	void setComponentType(JavaHelpers helperComponentType);

}





