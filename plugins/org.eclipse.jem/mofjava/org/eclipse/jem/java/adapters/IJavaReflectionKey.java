/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IJavaReflectionKey.java,v $
 *  $Revision: 1.1 $  $Date: 2005/09/14 23:30:32 $ 
 */
package org.eclipse.jem.java.adapters;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.jem.java.*;

public interface IJavaReflectionKey {

	/* Constants for the primitive type names
	 * These are the values which are used to refer to a primitive type,
	 * i.e. new JavaURL("int")  or new URL("java://#int")
	 * These types are cached by pre-loading documents for the primitives,
	 * in "java://", and for the java.lang (Object and String) classes
	 */
	static final public String N_VOID = "void";	 //$NON-NLS-1$
	static final public String N_INT = "int";//$NON-NLS-1$
	static final public String N_BOOLEAN = "boolean";//$NON-NLS-1$
	static final public String N_CHAR = "char";//$NON-NLS-1$
	static final public String N_BYTE = "byte";//$NON-NLS-1$
	static final public String N_LONG = "long";//$NON-NLS-1$
	static final public String N_FLOAT = "float";//$NON-NLS-1$
	static final public String N_DOUBLE = "double";//$NON-NLS-1$
	static final public String N_SHORT = "short";//$NON-NLS-1$

	/* 
	 * Create an ArrayType instance in the current document
	 * Handles the push and pop of the current document.
	 */
	public abstract ArrayType createArrayType();

	/* 
	 * Create a JavaClass instance in the current document
	 * Handles the push and pop of the current document.
	 */
	public abstract JavaClass createJavaClass();

	/* 
	 * Create a Field instance in the current document
	 * Handles the push and pop of the current document.
	 */
	public abstract Field createJavaField();

	/* 
	 * Create a Method instance in the current document
	 * Handles the push and pop of the current document.
	 */
	public abstract Method createJavaMethod();

	/* 
	 * Create a JavaParameter instance in the current document
	 */
	public abstract JavaParameter createJavaParameter();

	//FB/**
	//FB * Override to try to introspect objects
	//FB * that aren't found using the default mechanism.
	//FB */
	//FBpublic Object get(Object key) {
	//FB 	Object javaObject = super.get(key);
	//FB 	if (javaObject == null) {
	//FB 		javaObject = getObjectFromExtensions((String) key);
	//FB 		if (javaObject == null)
	//FB 			javaObject = getJavaObject((String) key);
	//FB 	}
	//FB	return javaObject;
	//FB}

	//FB ADDED
	public abstract Object get(String key);

	//FB END

	/*
	 * Instantiate the named class.
	 * If we make it to this point, the class has not yet been instantiated or reflected.
	 */
	public abstract EClassifier getArrayType(String typeName);

	/*
	 * Instantiate the named class.
	 * If we make it to this point, teh class has not yet been instantiated or reflected.
	 */
	public abstract EClassifier getJavaClass(String typeName);

	/**
	 * This allows for the retrieval of attributes/methods with the following notation:
	 * field: java:/com.ibm.foo#Test.foo
	 * method: java:/com.ibm.foo#Test.foofoo(
	 * parameter: java:/com.ibm.foo#Test.foofoo(-arg0
	 * 
	 */
	public abstract EObject getJavaObject(String keyValue);

	/**
	 * Insert the method's description here.
	 * Creation date: (10/4/2000 9:52:28 AM)
	 * @return org.eclipse.emf.ecore.EClassifier
	 * @param typeName java.lang.String
	 */
	public abstract EClassifier getJavaType(String typeName);

	/**
	 * Get the object from the key but don't do any 
	 * tries at creation of the key if not found.
	 * The Key must be an ID for it to be found.
	 */
	public abstract Object primGet(String key);

	/**
	 * Return true if the passed type represents a Java Array type
	 */
	public abstract boolean isArray(String typeName);

}