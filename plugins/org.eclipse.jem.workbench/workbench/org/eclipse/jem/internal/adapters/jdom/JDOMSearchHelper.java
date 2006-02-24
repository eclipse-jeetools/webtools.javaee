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
package org.eclipse.jem.internal.adapters.jdom;
/*
 *  $RCSfile: JDOMSearchHelper.java,v $
 *  $Revision: 1.7 $  $Date: 2006/02/24 00:19:15 $ 
 */

import java.io.File;
import java.util.*;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.*;

import org.eclipse.jem.internal.java.adapters.nls.ResourceHandler;
/**
 * Insert the type's description here.
 * Creation date: (9/26/2001 11:09:30 AM)
 * @author: Administrator
 */
public class JDOMSearchHelper {
	
	private static final String RESOLVED_NAME = " :: RESOLVED_NAME :: "; //$NON-NLS-1$
	private static final String PERIOD = "."; //$NON-NLS-1$
	private static final String BOOLEAN = "boolean"; //$NON-NLS-1$
	private static final String BYTE = "byte"; //$NON-NLS-1$
	private static final String CHAR = "char"; //$NON-NLS-1$
	private static final String SHORT = "short"; //$NON-NLS-1$
	private static final String INT = "int"; //$NON-NLS-1$
	private static final String LONG = "long"; //$NON-NLS-1$
	private static final String FLOAT = "float"; //$NON-NLS-1$
	private static final String DOUBLE = "double"; //$NON-NLS-1$
	private static final String VOID = "void"; //$NON-NLS-1$
	/**
	 * JDOMSearchHelper constructor comment.
	 */
	public JDOMSearchHelper() {
		super();
	}
	/**
	 * If the @simpleName is an inner class, we need to resolve only the declaring class.
	 */
	private static String[][] getTypeNameInfo(IType type, String simpleName, boolean isForReflection) throws JavaModelException {
		String[][] result = null;
		String declaringName, typeName;
		typeName = simpleName;
		if (type != null) {
			if (isForReflection)
				typeName = typeName.replace('.', '$');
			int index = typeName.indexOf("$"); //$NON-NLS-1$
			if (index > 0) {
				declaringName = typeName.substring(0, index);
				result = type.resolveType(declaringName);
				if (result != null) {
					if (isForReflection)
						result[0][1] = result[0][1].replace('.', '$');
					result[0][1] += typeName.substring(index, typeName.length());
				}
			} else {
				index = typeName.indexOf("["); //$NON-NLS-1$
				if (index > 0) {
					declaringName = typeName.substring(0, index);
					result = type.resolveType(declaringName);
					if (result != null)
						result[0][1] = result[0][1] + typeName.substring(index);
				} else
					result = type.resolveType(typeName);
			}
		}
		return result;
	}
	/**
	 * Returns true if the type is a primitive.
	 */
	public final static boolean isPrimitive(String type) {
		return (BOOLEAN.equals(type) || INT.equals(type) || CHAR.equals(type) || SHORT.equals(type) || LONG.equals(type) || FLOAT.equals(type) || DOUBLE.equals(type) || BYTE.equals(type));
	}
	/**
	 * Returns true if the type is a primitive.
	 */
	public final static boolean isPrimitiveOrVoid(String type) {
		return isPrimitive(type) || isVoid(type);
	}
	/**
	 * Returns true if the type is a primitive.
	 */
	public final static boolean isVoid(String type) {
		return VOID.equals(type);
	}
	/**
	 * Returns true if the two signatures match within the scope of the specified type.
	 */
	public static boolean matchTypeSignatures(IType type, String signature1, String signature2) throws JavaModelException {
		return matchTypeSignatures(type, signature1, signature2, null);
	}
	/**
	 * Returns true if the two signatures match within the scope of the specified type.
	 */
	public static boolean matchTypeSignatures(IType type, String signature1, String signature2, Map resolvedNameCache) throws JavaModelException {
		boolean result = false;
		String sig1 = signature1;
		String sig2 = signature2;
		// First check array count.
		if (Signature.getArrayCount(sig1) == Signature.getArrayCount(sig2)) {
			// We have the same array count, get the element types for consideration.
			sig1 = Signature.getElementType(sig1);
			sig2 = Signature.getElementType(sig2);

			// There are three cases:
			//		1) Both are unqualified (both being primitive will fall into this),
			//		2) Both are qualified, and
			//		3) One is qualified and the other is not (one could be primitive).

			// For the first two cases a compare of the readable names will always do.
			if (!((sig1.indexOf('.') == -1) ^ (sig2.indexOf('.') == -1))) {
				result = Signature.toString(sig1).equals(Signature.toString(sig2));
			} else {
				// This is case 3.
				// First root out one being primitive.
				if (((sig1.charAt(0) == 'Q') || (sig1.charAt(0) == 'L')) && ((sig2.charAt(0) == 'Q') || (sig2.charAt(0) == 'L'))) {
					// Get the readable name of the qualified signature
					// and the simple name of the other.
					String qualifiedName = null;
					String simpleName = null;
					if (sig1.indexOf('.') == -1) {
						qualifiedName = Signature.toString(sig2);
						simpleName = Signature.toString(sig1);
					} else {
						qualifiedName = Signature.toString(sig1);
						simpleName = Signature.toString(sig2);
					}

					// If the simple name resolves to the qualified name, we have a match.
					result = qualifiedName.equals(resolveSimpleTypeName(type, simpleName, resolvedNameCache));
				}
			}
		}

		return result;
	}
	private static boolean needsToResolveName(IType type, String simpleName, boolean isForReflection) {
		return !(type.isBinary() || (!isForReflection && simpleName.indexOf(PERIOD) > -1) || isPrimitiveOrVoid(simpleName));
	}
	/**
	 * Returns the qualified name for the simple name within the scope of the type.
	 * Returns null if the name can not be resolved.
	 */
	public static String resolveSimpleTypeName(IType type, String simpleName) throws JavaModelException {
		return resolveSimpleTypeName(type, simpleName, null);
	}
	/**
	 * Returns the qualified name for the simple name within the scope of the type.
	 * Returns null if the name can not be resolved.
	 */
	public static String resolveSimpleTypeName(IType type, String simpleName, Map resolvedNameCache) throws JavaModelException {
		return resolveSimpleTypeName(type, simpleName, resolvedNameCache, false);
	}

	/**
	 * Returns the qualified name for the simple name within the scope of the type.
	 * Returns null if the name can not be resolved.
	 */
	public static String resolveSimpleTypeName(IType type, String simpleName, Map resolvedNameCache, boolean isForReflection) throws JavaModelException {
		if (!needsToResolveName(type, simpleName, isForReflection))
			return simpleName;
		String key = null, qualifiedName = null;
		if (resolvedNameCache != null) {
			key = type.getFullyQualifiedName() + RESOLVED_NAME + simpleName;
			qualifiedName = (String) resolvedNameCache.get(key);
		}
		if (qualifiedName == null) {
			String[][] result = getTypeNameInfo(type, simpleName, isForReflection);
			if (result != null) {
				String packName = result[0][0];
				if (packName.length() == 0) {
					qualifiedName = result[0][1];
					if (isForReflection)
						qualifiedName = qualifiedName.replace('.', '$');					
				} else {
					StringBuffer b = new StringBuffer();
					b.append(result[0][0]).append(PERIOD);
					String typeName = result[0][1];
					if (isForReflection)
						typeName = typeName.replace('.', '$');
					b.append(typeName);
					qualifiedName = b.toString();
				}
			} else {
				qualifiedName = simpleName;
			}
			if (resolvedNameCache != null)
				resolvedNameCache.put(key, qualifiedName);
		}
		return qualifiedName;
	}
	/**
	 * Searches for a matching method and sets it in the
	 * descriptor if found.
	 */
	public static IMethod searchForMatchingMethod(IType type, String methodName, String[] parmSigs) throws JavaModelException {
		return searchForMatchingMethod(type, methodName, parmSigs, null);
	}
	/**
	 * Searches for a matching method and sets it in the
	 * descriptor if found.
	 */
	public static IMethod searchForMatchingMethod(IType type, String methodName, String[] parmSigs, Map resolvedNameCache) throws JavaModelException {

		// First get all the methods by this name and with this many parms.
		IMethod[] allMethods = type.getMethods();
		List candidateMethods = new ArrayList();
		for (int i = 0; i < allMethods.length; i++) {
			int parmSigsLength = (parmSigs != null ? parmSigs.length : 0);
			if ((allMethods[i].getElementName().equals(methodName)) && (allMethods[i].getNumberOfParameters() == parmSigsLength))
				candidateMethods.add(allMethods[i]);
		}

		// For each candidate consider each parm for a match.
		// Take the first one that matches on all parms.
		IMethod next = null;
		String[] nextParmSigs = null;
		boolean found = false;
		Iterator candidateIter = candidateMethods.iterator();
		while (!found && (candidateIter.hasNext())) {
			next = (IMethod) candidateIter.next();
			nextParmSigs = next.getParameterTypes();
			found = true;
			for (int i = 0;(found && (i < nextParmSigs.length)); i++)
				found &= matchTypeSignatures(type, parmSigs[i], nextParmSigs[i], resolvedNameCache);
		}
		return found ? next : null;
	}

	/**
	* The returned Object[] will contain two entries.  The
	* first will be the IJavaElement that was found and the
	* second will be the qualifiedName used to find it.
	*/
	protected static Object[] findActualJavaElement(String qualifiedName, IJavaProject javaProject, JDOMAdaptor adaptor) {
		Object[] result = new Object[2];
		if (adaptor == null)
			result[1] = qualifiedName;
		else 
			//Ensure the name is qualified
			result[1] = getResolvedTypeName(qualifiedName, adaptor.getType(), adaptor.getTypeResolutionCache());
		
		result[0] = findJavaElement((String) result[1], javaProject, adaptor);
		if (result[0] == null)
			findInnerJavaElement(result, javaProject, adaptor);
		return result;
	}

	/**
	 * The returned Object[] will contain two entries.  The
	 * first will be the IJavaElement that was found and the
	 * second will be the qualifiedName used to find it.
	 */
	protected static void findInnerJavaElement(Object[] info, IJavaProject javaProject, JDOMAdaptor adaptor) {
		String qualifiedName, innerName;
		qualifiedName = (String) info[1];
		int index = qualifiedName.lastIndexOf("."); //$NON-NLS-1$
		if (index > 0) {
			innerName = qualifiedName.substring(0, index);
			innerName += "$"; //$NON-NLS-1$
			innerName += qualifiedName.substring(index + 1, qualifiedName.length());
			if (adaptor != null) {
				//Ensure the name is qualified which it may not be if an inner class
				innerName = getResolvedTypeName(innerName, adaptor.getType(), adaptor.getTypeResolutionCache());
			}
			info[1] = innerName;
			info[0] = findJavaElement(innerName, javaProject, adaptor);
			if (info[0] == null)
				findInnerJavaElement(info, javaProject, adaptor);
		}
	}

	protected static IJavaElement findJavaElement(String qualifiedName, IJavaProject javaProject, JDOMAdaptor adaptor) {
		try {
			if (javaProject != null) {
				return javaProject.findType(qualifiedName);
			}
		} catch (JavaModelException jme) {
			System.out.println(ResourceHandler.getString("Error_Looking_Up_Type_ERROR_", (new Object[] { qualifiedName, jme.getMessage()}))); //$NON-NLS-1$ = "Error looking up type: "
		}
		return null;
	}
	
	protected static IPath getPathFromQualifiedName(String qualifiedName) {
		return new Path(qualifiedName.replace('.', File.separatorChar) + ".java"); //$NON-NLS-1$
	}
	/* 
	* Resolve a type name in the context of a Type.
	* (Borrowed from org.eclipse.jdt.ui.codemanipulation.StubUtility.getResolvedTypeName())
	* The input is a simple or qualified name, NOT a signature
	* The output will be a qualified name, NOT a signature
	*/
	public static String getResolvedTypeName(String typeName, IType declaringType, Map typeCache) {
		String name = typeName;
		try {
			name = JDOMSearchHelper.resolveSimpleTypeName(declaringType, typeName, typeCache, true);
		} catch (JavaModelException e) {
			// ignore
		}
		return name;
	}

	public static IType findType(String qualifiedName, boolean useAdvancedForInners, IJavaProject javaProject, JDOMAdaptor adaptor) {
		try {
			IJavaElement found = null;
			String resolvedName = qualifiedName;
			if (useAdvancedForInners) {
				Object[] result = findActualJavaElement(qualifiedName, javaProject, adaptor);
				found = (IJavaElement) result[0];
				resolvedName = (String) result[1];
			} else
				found = findJavaElement(qualifiedName, javaProject, adaptor);
			if (found != null)
				if (found instanceof IClassFile)
					return ((IClassFile) found).getType();
				else if (found instanceof ICompilationUnit) {
					ICompilationUnit foundCU = (ICompilationUnit) found;
					// strip the ".java", lifted from CompilationUnit.getMainTypeName()
					String cuMainTypeName = foundCU.getElementName();
					cuMainTypeName = cuMainTypeName.substring(0, cuMainTypeName.length() - 5);
					return foundCU.getType(cuMainTypeName);
				} else if (found instanceof IType) {
					IType type = ((IType) found);
					if (!type.getFullyQualifiedName('$').equals(resolvedName)) {
						// I don't know why this is here. Sometime in the past for an inner class, the
						// IType returned was for the outer class, so you would need to search again
						// for the inner class against the outer class. I don't know how this now can
						// happen. The code followed above is extremelly complicated, especially when
						// it is an inner class that isn't fully-qualified that is inside a source file.
						// It goes through some gyrations for that. I don't know what it would
						// return in that case. But just in case, the test is here to be safe.
						int index = resolvedName.lastIndexOf('$'); //$NON-NLS-1$
						if (index > -1)
							return type.getType(resolvedName.substring(index + 1, resolvedName.length()));
						else
							return type;
					} else
						return type;
				}
		} catch (JavaModelException jme) {
			System.out.println(ResourceHandler.getString("Error_Looking_Up_Type_ERROR_", (new Object[] { qualifiedName, jme.getMessage()}))); //$NON-NLS-1$ = "Error looking up type: "
		}
		return null;
	}
	
	public static IType findType(String packageName, String qualifiedTypeName, IJavaProject javaProject) {
		try {
			if (javaProject != null) {
				return javaProject.findType(packageName, qualifiedTypeName.replace('$', '.'));
			}
		} catch (JavaModelException jme) {
			System.out.println(ResourceHandler.getString("Error_Looking_Up_Type_ERROR_", (new Object[] { packageName + "." + qualifiedTypeName, jme.getMessage()}))); //$NON-NLS-1$ //$NON-NLS-2$ = "Error looking up type: "
		}
		return null;
	}

}
