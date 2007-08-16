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
package org.eclipse.jem.java;

/*
 *  $RCSfile: JavaHelpers.java,v $
 *  $Revision: 1.7 $  $Date: 2005/08/24 20:20:24 $ 
 */
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;

/**
 * Insert the type's description here.
 * Creation date: (6/27/2000 4:42:04 PM)
 * @author: Administrator
 */
public interface JavaHelpers extends EClass {
	static final String BOOLEAN_NAME = "java.lang.Boolean"; //$NON-NLS-1$
	static final String CHARACTER_NAME = "java.lang.Character"; //$NON-NLS-1$
	static final String BYTE_NAME = "java.lang.Byte"; //$NON-NLS-1$
	static final String DOUBLE_NAME = "java.lang.Double"; //$NON-NLS-1$
	static final String FLOAT_NAME = "java.lang.Float"; //$NON-NLS-1$
	static final String INTEGER_NAME = "java.lang.Integer"; //$NON-NLS-1$
	static final String LONG_NAME = "java.lang.Long"; //$NON-NLS-1$
	static final String SHORT_NAME = "java.lang.Short"; //$NON-NLS-1$
	static final String PRIM_BOOLEAN_NAME = "boolean"; //$NON-NLS-1$
	static final String PRIM_CHARACTER_NAME = "char"; //$NON-NLS-1$
	static final String PRIM_BYTE_NAME = "byte"; //$NON-NLS-1$
	static final String PRIM_DOUBLE_NAME = "double"; //$NON-NLS-1$
	static final String PRIM_FLOAT_NAME = "float"; //$NON-NLS-1$
	static final String PRIM_INTEGER_NAME = "int"; //$NON-NLS-1$
	static final String PRIM_LONG_NAME = "long"; //$NON-NLS-1$
	static final String PRIM_SHORT_NAME = "short"; //$NON-NLS-1$
	
	static final int PRIM_NOT_ID = 0;
	static final int PRIM_BOOLEAN_ID = 1;
	static final int PRIM_CHARACTER_ID = 2;
	static final int PRIM_BYTE_ID = 3;
	static final int PRIM_DOUBLE_ID = 4;
	static final int PRIM_FLOAT_ID = 5;
	static final int PRIM_INTEGER_ID = 6;
	static final int PRIM_LONG_ID = 7;
	static final int PRIM_SHORT_ID = 8;
	
	/**
	 * Get the qualified name (with using '.' for inner classes). Will return the name if primitive too (e.g. "boolean")
	 * Note: This should of been get the simple name and not the qualifed name, but it is too late and has been established
	 * as the API. Use <code>getSimpleName()</code> instead if you want the name not qualified.
	 * @return the qualified name of the type.
	 * @see JavaHelpers#getSimpleName()
	 * @since 1.0.0
	 */
	public String getJavaName();
	/**
	 * Get the simple name. This is the name of the primitive, or if a class, the name without the package. And if it is an
	 * inner class, it will use '.' instead of '$'. If you want the simple name for reflection (i.e. with '$') use <code>getName()</code>.
	 * @return the simple name (no package) of the type.
	 * 
	 * @since 1.0.0
	 */
	public String getSimpleName();

	/**
	 * Get the primitive type that this helper wrappers or is (e.g. "java.lang.Integer" and "int" types return "int" type). If not a primitive
	 * or a wrapper for a primitive, then return null.
	 * @return
	 * 
	 * @since 1.0.0
	 */
	public JavaDataType getPrimitive();
	
	/**
	 * Get the primitive id that this helper wrappers or is (e.g. "java.lang.Integer" and "int" will return {@link JavaHelpers#PRIM_INTEGER_ID}). If
	 * not a wrapper for a primitive then return {@link JavaHelpers#PRIM_NOT_ID}.
	 * @return
	 * 
	 * @since 1.1.0
	 */
	public int getPrimitiveID();
	/**
	 * To be used by people that need to get the qualified name. This would use '.' for inner classes
	 * and include the package name.
	 * @return the qualified name, including package.
	 * 
	 * @since 1.0.0
	 */	
	public String getQualifiedName();
	public JavaClass getWrapper();
	public boolean isArray();
	/**
	 * Can an object of the passed in class be assigned to an
	 * object of this class? In other words is this class a
	 * supertype of the passed in class, or is it superinterface
	 * of it? Or in the case of primitives, are they the same. 
	 * @param aClass
	 * @return <code>true</code> if assignable from the given class.
	 * 
	 * @since 1.0.0
	 */
	public boolean isAssignableFrom(EClassifier aClass);
	public boolean isPrimitive();

	/**
	 * To be used by people that need to get the qualified name used for reflection. This would use '$' instead of '.' for inner classes
	 * and include the package name.
	 * Typically bean info would need to use something like this.
	 * @return the qualified name, use '$' for inner classes
	 * 
	 * @since 1.0.0
	 */
	public String getQualifiedNameForReflection() ;	
}
