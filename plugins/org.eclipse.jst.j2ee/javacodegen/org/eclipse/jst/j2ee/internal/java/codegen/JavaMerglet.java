/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.java.codegen;



import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;

/**
 * Abstract Base class for Java merglets that implements some of the {@link IJavaMerglet}api and
 * provides infrastructure for subclass concrete implementations.
 */
public abstract class JavaMerglet implements IJavaMerglet {
	private boolean fPreserveNonCollisionOldMembers = false;
	private boolean fIncrementalPreserveCollision = false;
	private IJavaMergeStrategy fJavaMergeStrategy = null;

	/**
	 * A JavaMerglet is passed it's associated strategy at construction.
	 */
	public JavaMerglet(IJavaMergeStrategy jms) {
		super();
		fJavaMergeStrategy = jms;
		fPreserveNonCollisionOldMembers = jms.isDefaultPreserveNonCollisionOldMembers();
		fIncrementalPreserveCollision = jms.isDefaultIncrementalPreserveCollision();
	}

	/**
	 * Returns the index of the matching type name found in the array of type names to search.
	 * Returns -1 if a match is not found. Assumes typeName1 is not null.
	 * 
	 * @param type
	 *            This type is used to scope resolution of unqualified type names during the search.
	 * @param typeName1
	 *            This is the type name we are trying to match to an entry in the typeName2s array.
	 * @param typeName2s
	 *            The array of type names that we are searching for the match.
	 */
	protected int findTypeNameMatch(IType type, String typeName1, String[] typeName2s) throws MergeException {
		// First a boundary condition.
		if ((typeName2s == null) || (typeName2s.length == 0))
			return -1;

		int index = -1;
		boolean found = false;
		String tn1 = typeName1.trim();
		for (int i = 0; ((!found) && (i < typeName2s.length)); i++) {
			found = matchTypeNames(type, tn1, typeName2s[i]);
			if (found)
				index = i;
		}
		return index;
	}

	/**
	 * Returns the associated merge strategy.
	 * 
	 * @return org.eclipse.jst.j2ee.internal.java.codegen.IJavaMergeStrategy
	 */
	protected IJavaMergeStrategy getJavaMergeStrategy() {
		return fJavaMergeStrategy;
	}

	/**
	 * @see IJavaMerglet
	 */
	public boolean isIncrementalPreserveCollision() {
		return fIncrementalPreserveCollision;
	}

	/**
	 * @see IJavaMerglet
	 */
	public boolean isPreserveNonCollisionOldMembers() {
		return fPreserveNonCollisionOldMembers;
	}

	/**
	 * Converts the array of type signatures to an array of readable type names.
	 */
	protected String[] makeReadable(String[] sigs) {
		if (sigs == null)
			return null;

		String[] names = new String[sigs.length];
		for (int i = 0; i < sigs.length; i++)
			names[i] = makeReadable(sigs[i]);
		return names;
	}

	/**
	 * Converts the type signature to a readable type name.
	 */
	protected String makeReadable(String sig) {
		if (sig == null)
			return null;
		return Signature.toString(sig);
	}

	/**
	 * Returns true if the two lists match. Order is not important. The type names are assumed to
	 * not to be arrays.
	 * 
	 * @param type
	 *            This type is used to scope resolution of unqualified type names during the search.
	 * @param typeName1s
	 *            An array of type names we are trying to match one-for-one (but unordered) to the
	 *            entries in the typeName2s array.
	 * @param typeName2s
	 *            The array of type names that we are searching for the matches.
	 */
	protected boolean matchTypeNames(IType type, String[] typeName1s, String[] typeName2s) throws MergeException {
		// Check some boundaries first.
		if ((typeName1s == null) || (typeName2s == null)) {
			if (typeName1s == typeName2s) {
				return true;
			}
			if (typeName1s == null)
				return (typeName2s.length == 0);

			return (typeName1s.length == 0);
		}

		boolean match = (typeName1s.length == typeName2s.length);
		if (match) {
			String[] typeName2sCopy = new String[typeName2s.length];
			for (int i = 0; i < typeName2s.length; i++)
				typeName2sCopy[i] = typeName2s[i];
			for (int i = 0; (match && (i < typeName1s.length)); i++) {
				int found = findTypeNameMatch(type, typeName1s[i], typeName2sCopy);
				if (found >= 0)
					typeName2sCopy[found] = null;
				else
					match = false;
			}
		}

		return match;
	}

	/**
	 * Returns true if the two readable type names (not signatures) match within the scope of the
	 * specified type. The type names are assumed to not to be arrays.
	 * 
	 * @param type
	 *            This type is used to scope resolution of unqualified type names during the search.
	 * @param typeName1
	 *            This is the type name we are trying to match to typeName2.
	 * @param typeName2
	 *            The type name that we are trying to match to typeName1.
	 */
	protected boolean matchTypeNames(IType type, String typeName1, String typeName2) throws MergeException {
		// Check some boundaries first.
		if ((typeName1 == null) || (typeName2 == null)) {
			if (typeName1 == typeName2)
				return true;
			return false;
		}

		boolean result = false;
		String tn1 = typeName1.trim();
		String tn2 = typeName2.trim();
		// There are three cases:
		//		1) Both are unqualified,
		//		2) Both are qualified, and
		//		3) One is qualified and the other is not.

		// For the first two cases a compare of the readable names will always do.
		if (!((tn1.indexOf('.') == -1) ^ (tn2.indexOf('.') == -1))) {
			result = tn1.equals(tn2);
		} else {
			// This is case 3.
			// Get the readable name of the qualified signature
			// and the simple name of the other.
			String qualifiedName = null;
			String simpleName = null;
			if (tn1.indexOf('.') == -1) {
				qualifiedName = tn2;
				simpleName = tn1;
			} else {
				qualifiedName = tn1;
				simpleName = tn2;
			}

			// If the simple name resolves to the qualified name, we have a match.
			result = qualifiedName.equals(resolveSimpleTypeName(type, simpleName));
		}

		return result;
	}

	/**
	 * Returns the qualified name for the simple name within the scope of the type. Returns null if
	 * the name can not be resolved.
	 * 
	 * @param type
	 *            This type is used to scope resolution of the unqualified type name.
	 * @param simpleName
	 *            This is the simple type name we are trying to resolve to a qualified type name.
	 */
	protected String resolveSimpleTypeName(IType type, String simpleName) throws MergeException {
		String[][] result = null;
		try {
			result = type.resolveType(simpleName);
		} catch (JavaModelException exc) {
			throw new MergeException(exc);
		}
		if (result == null)
			return simpleName;
		return result[0][0] + "." + result[0][1];//$NON-NLS-1$
	}

	/**
	 * @see IJavaMerglet
	 */
	public void setIncrementalPreserveCollision(boolean newIncrementalPreserveCollision) {
		fIncrementalPreserveCollision = newIncrementalPreserveCollision;
	}

	/**
	 * @see IJavaMerglet
	 */
	public void setPreserveNonCollisionOldMembers(boolean newPreserveNonCollisionOldMembers) {
		fPreserveNonCollisionOldMembers = newPreserveNonCollisionOldMembers;
	}

	/**
	 * A passthrough to {@link IJavaMergeStrategy#wasGenerated(String, int)}.
	 */
	public boolean wasGenerated(String source, int endCheckIndex) {
		return getJavaMergeStrategy().wasGenerated(source, endCheckIndex);
	}

	/**
	 * A passthrough to {@link IJavaMergeStrategy#wasGenerated(IMember)}.
	 */
	public boolean wasGenerated(IMember member) throws MergeException {
		return getJavaMergeStrategy().wasGenerated(member);
	}
}