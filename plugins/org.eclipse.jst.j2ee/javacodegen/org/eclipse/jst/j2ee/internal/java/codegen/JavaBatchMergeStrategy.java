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



import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;


/**
 * This strategy and subclass strategies delete members that are not regenerated each time.
 * <p>
 * Note that for an untagged batch strategy all members are assumed to be generated. That is, during
 * a regeneration any member that is not regenerated each time is deleted.
 * 
 * @see IJavaMergeStrategy
 */
public class JavaBatchMergeStrategy extends JavaMergeStrategy {
	/**
	 * JavaBatchMergeStrategy default constructor.
	 */
	public JavaBatchMergeStrategy() {
		super();
	}

	/**
	 * Gathers descendent generators of the generator for the specified member. Only generators that
	 * finished with a target element are included. This is used by
	 * {@link JavaBatchMergeStrategy#postProcess(JavaTypeGenerator)}.
	 * 
	 * @param memberGen
	 *            the root of the tree of generators to be searched
	 * @param typeChildGens
	 *            the collection for the results
	 */
	protected void gatherChildGenerators(JavaMemberGenerator memberGen, List typeChildGens) {
		Iterator childGenIter = memberGen.getChildren().iterator();
		JavaMemberGenerator childGen = null;
		while (childGenIter.hasNext()) {
			childGen = (JavaMemberGenerator) childGenIter.next();
			if (childGen.isGroup())
				gatherChildGenerators(childGen, typeChildGens);
			else if (childGen.getTargetElement() != null)
				typeChildGens.add(childGen);
		}
	}

	/**
	 * @see IJavaMergeStrategy
	 */
	public boolean isBatchGeneration() {
		return true;
	}

	/**
	 * @see IJavaMergeStrategy
	 */
	public boolean isTagGenerated() {
		return false;
	}

	/**
	 * @see IJavaMergeStrategy This batch strategy deletes generated members that were not
	 *      regenerated.
	 * @param typeGen
	 *            The type generator for the type being postprocessed.
	 */
	public void postProcess(JavaTypeGenerator typeGen) throws MergeException {
		// If new type, do not post process.
		if ((typeGen.getMergeResults().getOldMember() == null) && (typeGen.getMergeResults().getCollisionMember() == null))
			return;

		// Gather the generators of children of the type.
		List typeChildGens = new java.util.ArrayList();
		gatherChildGenerators(typeGen, typeChildGens);

		try {
			// Get an array of the child members for efficiency,
			// While doing that, if the type is prepared, reaccess the children as needed.
			boolean isPrepared = typeGen.isPrepared();
			IMember[] children = new IMember[typeChildGens.size()];
			Iterator childGenIter = typeChildGens.iterator();
			JavaMemberGenerator childGen = null;
			for (int i = 0; childGenIter.hasNext(); i++) {
				childGen = (JavaMemberGenerator) childGenIter.next();
				children[i] = (IMember) childGen.getTargetElement();
				if (isPrepared && !children[i].getCompilationUnit().isWorkingCopy())
					children[i] = childGen.reaccess(children[i]);
			}

			// Run the analysis
			boolean needToDelete = false;
			IJavaElement[] dangerousDoNotTouch = ((IType) typeGen.getTargetElement()).getChildren();
			IJavaElement[] deleteCandidates = new IJavaElement[dangerousDoNotTouch.length];
			System.arraycopy(dangerousDoNotTouch, 0, deleteCandidates, 0, dangerousDoNotTouch.length);
			for (int i = 0; i < deleteCandidates.length; i++) {
				if (!isTagGenerated() || (wasGenerated((IMember) deleteCandidates[i]))) {
					boolean found = false;
					for (int j = 0; !found && (j < children.length); j++) {
						if (children[j] != null) {
							found = deleteCandidates[i].equals(children[j]);
							if (found) {
								deleteCandidates[i] = null;
								children[j] = null;
							}
						}
					}
				} else {
					deleteCandidates[i] = null;
				}
				needToDelete |= (deleteCandidates[i] != null);
			}

			// If need to delete, delete the candidates still around.
			if (needToDelete) {
				// If not prepared, prepare. Also will need to reaccess the members to be deleted.
				if (!isPrepared)
					typeGen.prepare();
				IType type = typeGen.getType();
				for (int i = 0; i < deleteCandidates.length; i++) {
					if (deleteCandidates[i] != null) {
						if (!isPrepared)
							deleteCandidates[i] = reaccess((IMember) deleteCandidates[i], type);
						((IMember) deleteCandidates[i]).delete(false, null);
					}
				}
			}
		} catch (GenerationException exc) {
			throw new MergeException(exc);
		} catch (JavaModelException exc) {
			throw new MergeException(exc);
		}
	}

	/**
	 * Reaccess the member from the working copy.
	 * 
	 * @param oldMember
	 *            The old member that needs to be reaccessed.
	 * @param type
	 *            The type to access the member from. Usually a working copy of the orginal
	 *            compilation unit the oldMember was accessed from.
	 */
	protected IMember reaccess(IMember oldMember, IType type) throws GenerationException {
		IMember result = null;
		if (oldMember != null) {
			if (type != null) {
				switch (oldMember.getElementType()) {
					case IJavaElement.FIELD : {
						result = type.getField(oldMember.getElementName());
					}
					case IJavaElement.TYPE : {
						result = type.getType(oldMember.getElementName());
					}
					case IJavaElement.METHOD : {
						result = type.getMethod(oldMember.getElementName(), ((IMethod) oldMember).getParameterTypes());
					}
					default : {
					}
				}
			}
			if ((result != null) && (!result.exists()))
				result = null;
		}
		return result;
	}
}