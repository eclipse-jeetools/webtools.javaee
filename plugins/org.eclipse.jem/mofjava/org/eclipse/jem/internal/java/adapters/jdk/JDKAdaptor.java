/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.java.adapters.jdk;
/*
 *  $RCSfile: JDKAdaptor.java,v $
 *  $Revision: 1.5 $  $Date: 2004/08/27 15:33:17 $ 
 */

import java.lang.reflect.Array;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.jem.java.*;
import org.eclipse.jem.internal.java.adapters.JavaReflectionAdaptor;
/**
 * Insert the type's description here.
 * Creation date: (6/6/2000 4:42:50 PM)
 * @author: Administrator
 */
public abstract class JDKAdaptor extends JavaReflectionAdaptor {
	public JavaJDKAdapterFactory adapterFactory;
	public JDKAdaptor(Notifier target, JavaJDKAdapterFactory anAdapterFactory) {
		super(target);
		setAdapterFactory(anAdapterFactory);
	}
	/**
	 * computeMethodID - generate the unique ID to be used to identify a constructor.
	 * Similar to a Signature, but hopefully more readable.
	 * The name format will be:
	 * 	simpleClassName.simpleClassName(com.fronk.ParmType1_parmType2&V
	 * Note: This implementation is tightly coupled with ReflectionAdapter.getTypeNamesFromMethodID().
	 *
	 * It has to be done separately for JDK because Constructors and Methods are different classes.
	 * However, in the Java Model they are both just Methods with a flag indicating Constructor.
	 */
	public static String computeMethodID(java.lang.reflect.Constructor jdkConstructor) {
		StringBuffer out = new StringBuffer();
		String className = getSimpleName(jdkConstructor.getDeclaringClass().getName());
		out.append(className);
		out.append(C_CLASS_MEMBER_DELIMITER);
		out.append(computeMethodName(jdkConstructor));
		out.append(C_METHOD_PARM_DELIMITER);
		Class[] parmTypes = jdkConstructor.getParameterTypes();
		for (int i = 0; i < parmTypes.length; i++) {
			out.append(parmTypes[i].getName());
			if (i < parmTypes.length - 1)
				out.append(C_PARM_PARM_DELIMITER);
		}
		out.append(S_CONSTRUCTOR_TOKEN); //It's a constructor
		return out.toString();
	}
	/**
	 * computeMethodID - generate the unique ID to be used to identify a method.
	 * Similar to a Signature, but hopefully more readable.
	 * If there are no parms, it will end with a "(" so that it can be distictive from a field.
	 * The name format will be:
	 * 	simpleClassName.methodName(com.fronk.ParmType1_parmType2
	 * Note: This implementation is tightly coupled with ReflectionAdapter.getTypeNamesFromMethodID().
	 */
	public static String computeMethodID(java.lang.reflect.Method jdkMethod) {
		StringBuffer out = new StringBuffer();
		String className = getSimpleName(jdkMethod.getDeclaringClass().getName());
		out.append(className);
		out.append(C_CLASS_MEMBER_DELIMITER);
		out.append(computeMethodName(jdkMethod));
		out.append(C_METHOD_PARM_DELIMITER);
		Class[] parmTypes = jdkMethod.getParameterTypes();
		for (int i = 0; i < parmTypes.length; i++) {
			out.append(parmTypes[i].getName());
			if (i < parmTypes.length - 1)
				out.append(C_PARM_PARM_DELIMITER);
		}
		if (jdkMethod.getDeclaringClass().getName().equals(jdkMethod.getName()))
			out.append(S_CONSTRUCTOR_TOKEN); //It's a constructor
		return out.toString();
	}
	/**
	 * computeMethodName - generate the name to be used to identify a constructor.
	 * For the moment, names are simple, and UUID's are complex.
	 *
	 * It has to be done separately for JDK because Constructors and Methods are different classes.
	 * However, in the Java Model they are both just Methods with a flag indicating Constructor. 
	 */
	public static String computeMethodName(java.lang.reflect.Constructor jdkConstructor) {
		return getSimpleName(jdkConstructor.getName()); // CTOR names come back fully-qualified with package.
	}
	/**
	 * computeMethodName - generate the name to be used to identify a method.
	 * For the moment, names are simple, and UUID's are complex.
	 */
	public static String computeMethodName(java.lang.reflect.Method jdkMethod) {
		return jdkMethod.getName();
	}
	/**
	 * createJavaField - instantiate a Java Field based on the passed Field
	 * We are deferring field contents assuming that its adaptor will reflect its details.
	 */
	public Field createJavaField(java.lang.reflect.Field reflectedField, XMIResource resource) {
		Field newField = getJavaFactory().createField();
		newField.setName(reflectedField.getName());
		String className = getSimpleName(reflectedField.getDeclaringClass().getName());
		resource.setID(newField, className + C_CLASS_MEMBER_DELIMITER + reflectedField.getName());
		return newField;
	}
	/**
	 * createJavaMethod - instantiate a Java Method based on the passed Java Reflection Constructor
	 * We are deferring method contents assuming that its adaptor will reflect its details.
	 * We need to store enough info in the empty Method to find its Java source.
	 * The ID will eventually hold enough info to identify the source, so we use it.
	 */
	public Method createJavaMethod(java.lang.reflect.Constructor jdkConstructor, XMIResource resource) {
		Method newMethod = getJavaFactory().createMethod();
		// We use a simple name, but a complex ID 
		newMethod.setName(computeMethodName(jdkConstructor));
		resource.setID(newMethod, computeMethodID(jdkConstructor));
		return newMethod;
	}
	/**
	 * createJavaMethod - instantiate a Java Method based on the passed Java Reflection Method
	 * We are deferring method contents assuming that its adaptor will reflect its details.
	 * We need to store enough info in the empty Method to find its Java source.
	 * The ID will eventually hold enough info to identify the source, so we use it.
	 */
	public Method createJavaMethod(java.lang.reflect.Method jdkMethod, XMIResource resource) {
		Method newMethod = getJavaFactory().createMethod();
		// We use a simple name, but a complex ID 
		newMethod.setName(computeMethodName(jdkMethod));
		resource.setID(newMethod, computeMethodID(jdkMethod));
		return newMethod;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (11/07/00 3:43:55 PM)
	 * @return org.eclipse.jem.java.adapters.JavaJDKAdapterFactory
	 */
	public JavaJDKAdapterFactory getAdapterFactory() {
		return adapterFactory;
	}
	public ClassLoader getAlternateClassLoader() {
		return getAdapterFactory().getContextClassLoader();
	}
	/*****************************************************************************
	* Method to convert the textual form of a primitive type into its Class object
	*
	* @param type The primitive type's textual representation
	*/
	public static Class getPrimitiveType(String type) {
		Class c = null;

		if (type.equals(byte.class.getName())) {
			c = byte.class;
		} else if (type.equals(short.class.getName())) {
			c = short.class;
		} else if (type.equals(int.class.getName())) {
			c = int.class;
		} else if (type.equals(long.class.getName())) {
			c = long.class;
		} else if (type.equals(float.class.getName())) {
			c = float.class;
		} else if (type.equals(double.class.getName())) {
			c = double.class;
		} else if (type.equals(boolean.class.getName())) {
			c = boolean.class;
		} else if (type.equals(char.class.getName())) {
			c = char.class;
		} else if (type.equals(void.class.getName())) {
			c = void.class;
		}

		return c;
	}
	/**
	 * Returns the last segment of a '.'-separated qualified name.
	 * Returns the given name if it is not qualified.
	 * For example:
	 * <pre>
	 * getSimpleName("java.lang.Object") -> "Object"
	 * </pre>
	 */
	public static String getSimpleName(String name) {
		int lastDot = name.lastIndexOf('.');
		if (lastDot == -1)
			return name;
		return name.substring(lastDot + 1);
	}
	public Class getType(JavaHelpers modelClass) {
		Class result = null;
		if (modelClass.isArray()) {
			ArrayType arrayType = (ArrayType) modelClass;
			Class componentClass = getType(arrayType.getFinalComponentType());
			result = (Array.newInstance(componentClass, new int[arrayType.getArrayDimensions()])).getClass();
		} else if (modelClass.isPrimitive()) {
			result = getType(modelClass.getQualifiedName());
		} else {
			result = getType(((JavaClass) modelClass).getQualifiedNameForReflection());
		}
		return result;
	}
	public Class getType(String qualifiedName) {
		// Try for a primitive type ("int","char",etc.) first
		Class primType = getPrimitiveType(qualifiedName);
		if (primType == null) {
			// Changed for defect #212147 botp@ausaix19.austin.ibm.com@7630 system.
			//
			// Search only one of the following classloaders (the first one that exists) in this order. If not found
			// in a classloader, it will not roll-over to another class loader. This is to avoid problems where a
			// class may exist in more than one classloader. You get errors when this happens due to one class that
			// was found in only one classloader that refers to another class that was found in both classloaders.
			// They don't match when trying to reflect later.
			// 1) Alternate classloader (if exists)
			// 2) Thread context classloader (if exists)
			// 3) System classloader (if exists)
			// 4) Class.forName().
			if (getAlternateClassLoader() != null) {
				try {
					return getAlternateClassLoader().loadClass(qualifiedName);
				} catch (ClassNotFoundException cnf2) {
					return null;
				}
			}
			
			ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
			if (contextClassLoader != null) {
				try {
					return contextClassLoader.loadClass(qualifiedName);
				} catch (ClassNotFoundException e) {
					return null;
				}
			}
			
			ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
			if (systemClassLoader != null) {
				try {
					return systemClassLoader.loadClass(qualifiedName);
				} catch (ClassNotFoundException e) {
					return null;
				}
			}
			
			try {
				return Class.forName(qualifiedName);
			} catch (ClassNotFoundException e) {
				return null;
			}
		} else
			return primType;
	}
	/*
	 * Utility routine to paper over array type names
	 * Borrowed from a package-visibility helper on java.lang.reflect.Field
	 */
	static String getTypeName(Class type) {
		if (type.isArray()) {
			try {
				Class cl = type;
				int dimensions = 0;
				while (cl.isArray()) {
					dimensions++;
					cl = cl.getComponentType();
				}
				StringBuffer sb = new StringBuffer();
				sb.append(cl.getName());
				for (int i = 0; i < dimensions; i++) {
					sb.append("[]"); //$NON-NLS-1$
				}
				return sb.toString();
			} catch (Throwable e) { /*FALLTHRU*/
			}
		}
		return type.getName();
	}

	/*****************************************************************************
	* Method to convert the textual form of a primitive type into its Class object
	*
	* @param type The primitive type's textual representation
	*/
	public static Class primitiveForName(String type) {
		Class c = null;

		if (type.equals(byte.class.getName())) {
			c = byte.class;
		} else if (type.equals(short.class.getName())) {
			c = short.class;
		} else if (type.equals(int.class.getName())) {
			c = int.class;
		} else if (type.equals(long.class.getName())) {
			c = long.class;
		} else if (type.equals(float.class.getName())) {
			c = float.class;
		} else if (type.equals(double.class.getName())) {
			c = double.class;
		} else if (type.equals(boolean.class.getName())) {
			c = boolean.class;
		} else if (type.equals(char.class.getName())) {
			c = char.class;
		} else if (type.equals(void.class.getName())) {
			c = void.class;
		}

		return c;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (11/07/00 3:43:55 PM)
	 * @param newAdapterFactory org.eclipse.jem.java.adapters.JavaJDKAdapterFactory
	 */
	public void setAdapterFactory(JavaJDKAdapterFactory newAdapterFactory) {
		adapterFactory = newAdapterFactory;
	}
}
