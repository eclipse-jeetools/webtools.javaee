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
 *  $RCSfile: JavaHelpers.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:12:30 $ 
 */
import org.eclipse.emf.ecore.EClassifier;

/**
 * Insert the type's description here.
 * Creation date: (6/27/2000 4:42:04 PM)
 * @author: Administrator
 */
public interface JavaHelpers extends EClassifier {
	static final String BOOLEAN_NAME = "java.lang.Boolean";
	static final String CHARACTER_NAME = "java.lang.Character";
	static final String BYTE_NAME = "java.lang.Byte";
	static final String DOUBLE_NAME = "java.lang.Double";
	static final String FLOAT_NAME = "java.lang.Float";
	static final String INTEGER_NAME = "java.lang.Integer";
	static final String LONG_NAME = "java.lang.Long";
	static final String SHORT_NAME = "java.lang.Short";
	static final String PRIM_BOOLEAN_NAME = "boolean";
	static final String PRIM_CHARACTER_NAME = "char";
	static final String PRIM_BYTE_NAME = "byte";
	static final String PRIM_DOUBLE_NAME = "double";
	static final String PRIM_FLOAT_NAME = "float";
	static final String PRIM_INTEGER_NAME = "int";
	static final String PRIM_LONG_NAME = "long";
	static final String PRIM_SHORT_NAME = "short";
	public String getJavaName();
	public JavaDataType getPrimitive();
	public String getQualifiedName();
	public JavaClass getWrapper();
	public boolean isArray();
	/**
	 * Can an object of the passed in class be assigned to an
	 * object of this class? In other words is this class a
	 * supertype of the passed in class, or is it superinterface
	 * of it? Or in the case of primitives, are they the same.
	 */
	public boolean isAssignableFrom(EClassifier aClass);
	public boolean isPrimitive();
}
