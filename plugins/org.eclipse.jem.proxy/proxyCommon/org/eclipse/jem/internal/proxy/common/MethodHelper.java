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
package org.eclipse.jem.internal.proxy.common;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a class to do message/constructor work. 
 * Specifically to find the most appropriate method.
 */
public class MethodHelper {
	
	/*
	 * The class that is used to represent Null class type.
	 * 
	 * @since 1.0.0
	 */
	private static class NULL_CLASS {
	}
	
	public static final Class NULL_TYPE = NULL_CLASS.class;
	
	static final ArrayList sPrimitivesOrder;
	static final int sCharPos;
	
	static {
		sPrimitivesOrder = new ArrayList(6);
		sPrimitivesOrder.add(Byte.TYPE);
		sPrimitivesOrder.add(Short.TYPE);
		sPrimitivesOrder.add(Integer.TYPE);
		sPrimitivesOrder.add(Long.TYPE);
		sPrimitivesOrder.add(Float.TYPE);
		sPrimitivesOrder.add(Double.TYPE);

		// char can be treated like a short for purposes of ordering.
		sCharPos = sPrimitivesOrder.indexOf(Short.TYPE);
	}
	
	/**
	 * Return whether the type2 can be assigned to type1 in
	 * method argument conversion.
	 */
	public static boolean isAssignableFrom(Class type1, Class type2) {
		if (type1 == type2)
			return true;	// They're the same, so assignable.
		if (type1.isPrimitive()) {
			if (type2.isPrimitive()) {
				if (type1 == Boolean.TYPE || type2 == Boolean.TYPE)
					return false;	// Since not equal and one is boolean and the other isn't, not assignable
				int type1Pos = (type1 != Character.TYPE) ? sPrimitivesOrder.indexOf(type1) : sCharPos;
				int type2Pos = (type2 != Character.TYPE) ? sPrimitivesOrder.indexOf(type2) : sCharPos;
				return type1Pos > type2Pos;	// It can be widened if type1 is higher in the order
			}
			return false;	// primitive to non-primitive, not assignable.
		} else
			if (type2 == NULL_TYPE)
				return true;	// NULL_TYPE represents null for us, and null can be assigned to any object
			else		
				return type1.isAssignableFrom(type2);	// Can type2 be assigned to type1
	}
	
	
	/**
	 * Every entry in Array2 can be assigned to the corresponding entry in Array1.
	 */
	public static boolean isAssignableFrom(Class[] types1, Class[] types2) {
		if (types1.length != types2.length)
			return false;	// Not the same size, so not compatible.
		for (int i=0; i<types1.length; i++) {
			if (!isAssignableFrom(types1[i], types2[i]))
				return false;
		}
		return true;	// All are assignable
	}
	
	/**
	 * Return the index of the most compatible method/constructor from the lists passed in.
	 * MethodsList: List of methods (if null then this is for constructors)
	 * ParmsList: List of parms for each method (each entry will be Class[]).
	 */
	private static int findMostCompatible(List methods, List parms, String ambiguousName) throws AmbiguousMethodException {
		// The algorithm used is from the Java Language Specification 15.12.2.2
		// Find the maximally specific ones
		// This is defined as the one that is more specific then all of the rest.
		// If there are duplicates parms that are maximally specific, then it doesn't matter which choosen
		// because when invoked the JVM will make sure the right thing is done.
		// 
		Class[][] parmsCopy = (Class[][]) parms.toArray(new Class[parms.size()][]);
		int size = parmsCopy.length;
		// For each entry see if it is maximally specific, i.e. it is more specific then all of the others.
nextMethod:	for (int i=0; i<size; i++) {
			// For ctors we don't need to test the declaring class because it will always be the same class.
			Class dclClassi = methods != null ? ((Method) methods.get(i)).getDeclaringClass() : null;
			Class[] parmsi = parmsCopy[i];
			for (int j=0; j<size; j++) {
				if (i == j)
					continue;
				// Methodi is more specific if
				//   a) Methodi declaring class is assignable to Methodj declaring class
				//   b) Methodi parms are assignable to Methodj parms
				//
				// First see if Methodi is more specific, if it is
				// then throw out Methodj and continue
				// If Methodi is not compatible to Methodj, go to the next method for i. Methodi is not the most specific
				// Something else is either more specific or none are ma
				if (dclClassi != null) {
					// Step a
					if (!isAssignableFrom(((Method) methods.get(j)).getDeclaringClass(), dclClassi))
						continue nextMethod;	// Methodi is not more specific than Methodj, so try next i.
				}
				
				// Step b
				Class[] parmsj = parmsCopy[j];
				if (!isAssignableFrom(parmsj, parmsi)) {
					// Methodi is not more specific than Methodj, so go to next i.
					continue nextMethod;
				}
			}
			return i;	// Methodi is more specific than all of the other ones.
		}

		throw new AmbiguousMethodException(ambiguousName);	// There was not one more specific than all of the others.
	}
	
	/**
	 * Find the most compatible method for the given arguments.
	 */
	public static Method findCompatibleMethod(Class receiver, String methodName, Class[] arguments) throws NoSuchMethodException, AmbiguousMethodException {
		try {
			Method mthd = receiver.getMethod(methodName, arguments);
			return mthd;	// Found exact match
		} catch (NoSuchMethodException exc) {
			if (arguments != null) {
				// Need to find most compatible one.
				Method mthds[] = receiver.getMethods();
				ArrayList parmsList = new ArrayList(mthds.length); // The parm list from each compatible method.
				ArrayList mthdsList = new ArrayList(mthds.length); // The list of compatible methods, same order as the parms above.
				for (int i = 0; i < mthds.length; i++) {
					Method mthd = mthds[i];
					if (!mthd.getName().equals(methodName))
						continue; // Not compatible, not same name
					Class[] parms = mthd.getParameterTypes();
					if (!isAssignableFrom(parms, arguments))
						continue; // Not compatible, parms
					// It is compatible with the requested method
					parmsList.add(parms);
					mthdsList.add(mthd);
				}

				// Now have list of compatible methods.
				if (parmsList.size() == 0)
					throw exc; // None found, so rethrow the exception
				if (parmsList.size() == 1)
					return (Method) mthdsList.get(0); // Only one, so return it

				// Now find the most compatible method
				int mostCompatible = findMostCompatible(mthdsList, parmsList, methodName);
				return (Method) mthdsList.get(mostCompatible);
			} else
				throw exc; // None found, so rethrow the exception
		}
	}
	
	/**
	 * Find the most compatible constructor for the given arguments.
	 */
	public static Constructor findCompatibleConstructor(Class receiver, Class[] arguments) throws NoSuchMethodException, AmbiguousMethodException {
		try {
			java.lang.reflect.Constructor ctor = receiver.getDeclaredConstructor(arguments);
			ctor.setAccessible(true);	// We allow all access, let ide and compiler handle security.
			return ctor;	// Found exact match
		} catch (NoSuchMethodException exc) {
			if (arguments != null) {
				// Need to find most compatible one.
				java.lang.reflect.Constructor ctors[] = receiver.getDeclaredConstructors();
				ArrayList parmsList = new ArrayList(ctors.length); // The parm list from each compatible method.
				ArrayList ctorsList = new ArrayList(ctors.length); // The list of compatible methods, same order as the parms above.
				for (int i = 0; i < ctors.length; i++) {
					java.lang.reflect.Constructor ctor = ctors[i];
					Class[] parms = ctor.getParameterTypes();
					if (!isAssignableFrom(parms, arguments))
						continue; // Not compatible, parms
					// It is compatible with the requested method
					parmsList.add(parms);
					ctorsList.add(ctor);
				}

				// Now have list of compatible methods.
				if (parmsList.size() == 0)
					throw exc; // None found, so rethrow the exception
				if (parmsList.size() == 1) {
					java.lang.reflect.Constructor ctor = (java.lang.reflect.Constructor) ctorsList.get(0); // Only one, so return it
					ctor.setAccessible(true); // We allow all access, let ide and compilor handle security.
					return ctor;
				}

				// Now find the most compatible ctor
				int mostCompatible = findMostCompatible(null, parmsList, receiver.getName());
				java.lang.reflect.Constructor ctor = (java.lang.reflect.Constructor) ctorsList.get(mostCompatible);
				ctor.setAccessible(true); // We allow all access, let ide and compilor handle security.
				return ctor;
			} else
				throw exc; // None found, so rethrow the exception
		}
	}	
				
		
}
