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
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.core.jdom.IDOMMember;
import org.eclipse.jdt.core.jdom.IDOMMethod;


/**
 * The default method merglet.
 */
public class JavaMethodMerglet extends JavaMemberMerglet {
	private static final String METHOD_COLLISION_INFORMATIONAL = JavaCodeGenResourceHandler.getString("The_method_will_not_gen_INFO_"); //$NON-NLS-1$ = "The method \"{1}\" will not be generated because a non-generated method with the same signature already exists."
	private static final String METHOD_NOT_DELETED_INFORMATIONAL = JavaCodeGenResourceHandler.getString("The_method_will_not_del_INFO_"); //$NON-NLS-1$ = "The method \"{0}\" will not be deleted because it is not marked generated."
	private static final String ABSTRACT_METHOD_BODY = ";"; //$NON-NLS-1$
	private String[] fNoMergeExceptionNames = null;

	/**
	 * JavaMethodMerglet default constructor.
	 */
	public JavaMethodMerglet(IJavaMergeStrategy jms) {
		super(jms);
	}

	/**
	 * Returns the body of the method. The method body includes all code following the method
	 * declaration, including the enclosing braces. For abstract or native methods a string
	 * containing just a semicolon is returned. That is the value returned by JDOM in those cases.
	 * 
	 * @return java.lang.String
	 * @param theMethod
	 *            org.eclipse.jdt.core.IMethod
	 */
	protected String getBody(IMethod theMethod) throws MergeException {
		try {
			// If the method is abstract or native.
			// The semicolon is the body. This is what JDOM returns
			// in this case.
			String body = ABSTRACT_METHOD_BODY;
			String source = theMethod.getSource();
			int sourceIndex = theMethod.getNameRange().getOffset() - theMethod.getSourceRange().getOffset();
			sourceIndex = source.indexOf('{', sourceIndex);
			// If it has a body, return the body
			if (sourceIndex >= 0) {
				sourceIndex--;
				while ((sourceIndex >= 0) && (Character.isWhitespace(source.charAt(sourceIndex))))
					sourceIndex--;
				sourceIndex++;
				body = source.substring(sourceIndex);
			}
			return body;
		} catch (JavaModelException exc) {
			throw new MergeException(exc);
		}
	}

	/**
	 * Returns the typical reason why a method is not generated during a collision.
	 */
	protected Object getMemberCollisionReason() {
		return METHOD_COLLISION_INFORMATIONAL;
	}

	/**
	 * Returns the typical reason why an old method is not deleted during a generation.
	 */
	protected Object getMemberNotDeletedReason() {
		return METHOD_NOT_DELETED_INFORMATIONAL;
	}

	/**
	 * The method generator or merge strategy can specify exceptions that should not be merged from
	 * the old method to the new method via this property.
	 * 
	 * @return java.lang.String[]
	 */
	public java.lang.String[] getNoMergeExceptionNames() {
		return fNoMergeExceptionNames;
	}

	/**
	 * Returns the readable identification of the member.
	 */
	protected String getReadableIdFor(IMember member) throws MergeException {
		try {
			return Signature.toString(((IMethod) member).getSignature(), member.getElementName(), null, false, true);
		} catch (JavaModelException exc) {
			throw new MergeException(exc);
		}
	}

	/**
	 * Returns true if old user code is found and merged.
	 * <p>
	 * Note that the support of VAJ style user code points is here mainly for compatibility and
	 * migration purposes. For new development, the use of specialized merglets is encouraged
	 * instead.
	 * 
	 * @exception java.lang.RuntimeException
	 *                if a user code point format error is found.
	 */
	protected boolean injectUserCode(String name, String oldSource, StringBuffer mergedSource) throws RuntimeException {
		org.eclipse.jst.j2ee.internal.codegen.GenerationBuffer genBuf = new org.eclipse.jst.j2ee.internal.codegen.GenerationBuffer();
		genBuf.format(getJavaMergeStrategy().getUserCodeBeginTemplate(), new String[]{name});
		String codePointTag = genBuf.toString();
		int oldUserCodePos = oldSource.indexOf(codePointTag);
		boolean merged = false;
		if (oldUserCodePos >= 0) {
			oldUserCodePos += codePointTag.length();
			int endOldUserCodePos = oldSource.indexOf(getJavaMergeStrategy().getUserCodeEnd(), oldUserCodePos);
			if (endOldUserCodePos < oldUserCodePos)
				throw new RuntimeException();
			String oldUserCode = oldSource.substring(oldUserCodePos, endOldUserCodePos);
			// If it is all whitespace, skip it.
			for (int i = 0; (!merged && (i < oldUserCode.length())); i++)
				merged = !(Character.isWhitespace(oldUserCode.charAt(i)));
			if (merged)
				mergedSource.append(oldUserCode);
		}
		return merged;
	}

	/**
	 * Returns true if the two method bodies are equivalent. This implementation just string
	 * compares the bodies after trimming whitespace. The method body includes all code following
	 * the method declaration, including the enclosing braces.
	 * 
	 * @exception org.eclipse.jst.j2ee.internal.java.codegen.MergeException
	 */
	protected boolean matchBody(IMethod collisionMethod, IDOMMethod newMethod) throws MergeException {
		return getBody(collisionMethod).trim().equals(newMethod.getBody().trim());
	}

	/**
	 * Merge the body of the old method and the new method together. Returns false if the new method
	 * code is taken as is. Returns true if merged or the old method code was taken as is. This
	 * implementation just merges user code points.
	 * <p>
	 * Note that the support of VAJ style user code points is here mainly for compatibility and
	 * migration purposes. For new development, the use of specialized merglets is encouraged
	 * instead.
	 * 
	 * @return boolean
	 * @param oldMethod
	 *            org.eclipse.jdt.core.IMethod
	 * @param newMethod
	 *            org.eclipse.jdt.core.jdom.IDOMMethod
	 */
	protected boolean mergeBody(IMethod oldMethod, IDOMMethod newMethod) throws MergeException {
		return mergeUserCode(oldMethod, newMethod);
	}

	/**
	 * If the old method has exceptions that the new one does not have, merge in the missing ones.
	 * Returns true if there were exceptions to merge.
	 * 
	 * @return boolean
	 * @param oldMethod
	 *            org.eclipse.jdt.core.IMethod
	 * @param newMethod
	 *            org.eclipse.jdt.core.jdom.IDOMMethod
	 */
	protected boolean mergeExceptions(IMethod oldMethod, IDOMMethod newMethod) throws MergeException {
		boolean merged = false;
		try {
			String[] oldExceptionNames = makeReadable(oldMethod.getExceptionTypes());
			if (!((oldExceptionNames == null) || (oldExceptionNames.length == 0))) {
				String[] newExceptionNames = newMethod.getExceptions();
				IType oldType = oldMethod.getDeclaringType();
				String[] noMergeExceptionNames = getNoMergeExceptionNames();
				for (int i = 0; i < oldExceptionNames.length; i++) {
					int found = findTypeNameMatch(oldType, oldExceptionNames[i], newExceptionNames);
					if (found < 0) {
						found = findTypeNameMatch(oldType, oldExceptionNames[i], noMergeExceptionNames);
						if (found < 0) {
							newMethod.addException(oldExceptionNames[i]);
							merged = true;
						}
					}
				}
			}
		} catch (JavaModelException exc) {
			throw new MergeException(exc);
		}
		return merged;
	}

	/**
	 * Merges the member. This method is called only if the old member of the merge results is not
	 * null. If the old member is null, there is nothing to merge from. Returns true if merging was
	 * done. Uses {@link JavaMethodMerglet#mergeExceptions(IMethod, IDOMMethod)},
	 * {@link JavaMethodMerglet#mergeBody(IMethod, IDOMMethod)}and
	 * {@link JavaMethodMerglet#mergeReturnTypes(IMethod, IDOMMethod)}.
	 * 
	 * @return boolean
	 * @param oldMember
	 *            org.eclipse.jdt.core.IMember
	 * @param newMember
	 *            org.eclipse.jdt.core.jdom.IDOMMember
	 */
	protected boolean mergeMember(IMember oldMember, IDOMMember newMember) throws MergeException {
		boolean merged = mergeExceptions((IMethod) oldMember, (IDOMMethod) newMember);
		merged |= mergeBody((IMethod) oldMember, (IDOMMethod) newMember);
		merged |= mergeReturnTypes((IMethod) oldMember, (IDOMMethod) newMember);
		return merged;
	}

	/**
	 * Returns true if the oldMethod return type is different from the newMethod return type.
	 * 
	 * @return boolean
	 * @param oldMethod
	 *            org.eclipse.jdt.core.IMethod
	 * @param newMethod
	 *            org.eclipse.jdt.core.jdom.IDOMMethod
	 */
	protected boolean mergeReturnTypes(IMethod oldMethod, IDOMMethod newMethod) throws MergeException {
		try {
			String oldName, newName;
			oldName = makeReadable(oldMethod.getReturnType());
			newName = newMethod.getReturnType();
			return !matchTypeNames(oldMethod.getDeclaringType(), oldName, newName);
		} catch (JavaModelException exc) {
			throw new MergeException(exc);
		}
	}

	/**
	 * Merges user code from the old method into the new. Returns true if merging happens.
	 * <p>
	 * Note that the support of VAJ style user code points is here mainly for compatibility and
	 * migration purposes. For new development, the use of specialized merglets is encouraged
	 * instead.
	 * 
	 * @exception org.eclipse.jst.j2ee.internal.java.codegen.MergeException
	 */
	protected boolean mergeUserCode(IMethod oldMethod, IDOMMethod newMethod) throws MergeException {
		boolean merged = false;
		try {
			String oldSource = getBody(oldMethod);
			String newSource = newMethod.getBody();
			StringBuffer mergedSource = new StringBuffer(newSource.length() * 2);

			// Get the position of the first user code point in the new source
			// and start looping to merge them all.
			int namePos = 0;
			int nameEnd = 0;
			int doneSourcePos = 0;
			String name = null;
			boolean mergedThisPoint = false;
			String userCodeBegin = getJavaMergeStrategy().getUserCodeBegin();
			int userCodePos = newSource.indexOf(userCodeBegin);
			while (userCodePos >= 0) {
				// Extract the name of the user code point.
				namePos = newSource.indexOf('{', userCodePos) + 1;
				if (namePos < userCodePos)
					throw new RuntimeException();
				nameEnd = newSource.indexOf('}', namePos);
				if (nameEnd < namePos)
					throw new RuntimeException();
				name = newSource.substring(namePos, nameEnd);

				// Stash what we have so far.
				userCodePos = nameEnd + 1;
				mergedSource.append(newSource.substring(doneSourcePos, userCodePos));
				doneSourcePos = userCodePos;

				// Merge user code from the old code into the new code at this point.
				mergedThisPoint = injectUserCode(name, oldSource, mergedSource);
				if (mergedThisPoint) {
					doneSourcePos = newSource.indexOf(getJavaMergeStrategy().getUserCodeEnd(), doneSourcePos);
					if (doneSourcePos == -1)
						throw new RuntimeException();
					merged = true;
				}

				// Get the next user code point in the new source.
				userCodePos = newSource.indexOf(userCodeBegin, doneSourcePos);
			}

			// If we merged, get the last bit of the new source and update the results.
			if (merged) {
				mergedSource.append(newSource.substring(doneSourcePos));
				newMethod.setBody(mergedSource.toString());
			}
		} catch (RuntimeException rtExc) {
			// We get here because of a user code point format error.
			// Do not merge and eat the error.
			merged = false;
		}
		return merged;
	}

	/**
	 * Check the collision method and the new method. 1. same flags 2. same super interfaces - uses
	 * {@link JavaMerglet#matchTypeNames(IType, String[], String[])}3. same body - uses
	 * {@link JavaMethodMerglet#matchBody(IMethod, IDOMMethod)}Do not have to check the name or
	 * types since that defines collision. Returns true if any of the above are not the same.
	 * 
	 * @return boolean
	 * @param collisionMember
	 *            org.eclipse.jdt.core.IMember
	 * @param newMember
	 *            org.eclipse.jdt.core.jdom.IDOMMember
	 */
	protected boolean needToGenerate(IMember collisionMember, IDOMMember newMember) throws MergeException {
		boolean needToGenerate = false;
		IMethod collisionMethod = (IMethod) collisionMember;
		IDOMMethod newMethod = (IDOMMethod) newMember;

		try {
			// First, check the flags.
			needToGenerate = !(collisionMethod.getFlags() == newMember.getFlags());

			//Second, check the return types.
			IType collisionType = null;
			if (!needToGenerate) {
				collisionType = collisionMethod.getDeclaringType();
				needToGenerate = !matchTypeNames(collisionType, makeReadable(collisionMethod.getReturnType()), newMethod.getReturnType());
			}

			// Third, check the exceptions.
			if (!needToGenerate) {
				needToGenerate = !matchTypeNames(collisionType, makeReadable(collisionMethod.getExceptionTypes()), newMethod.getExceptions());
			}

			// Fourth, check the body.
			if (!needToGenerate)
				needToGenerate = !matchBody(collisionMethod, newMethod);
		} catch (JavaModelException exc) {
			throw new MergeException(exc);
		}

		return needToGenerate;
	}

	/**
	 * The method generator or merge strategy can specify exceptions that should not be merged from
	 * the old method to the new method via this property.
	 * 
	 * @param newNoMergeExceptionNames
	 *            java.lang.String[]
	 */
	public void setNoMergeExceptionNames(java.lang.String[] newNoMergeExceptionNames) {
		fNoMergeExceptionNames = newNoMergeExceptionNames;
	}
}