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
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.jdom.IDOMField;
import org.eclipse.jdt.core.jdom.IDOMMember;
import org.eclipse.jdt.core.jdom.IDOMMethod;
import org.eclipse.jdt.core.jdom.IDOMType;

/**
 * This merge strategy abstract base class implements some of the {@link IJavaMergeStrategy}api and
 * provides infrastructure for subclass concrete implementations.
 * 
 * @see IJavaMergeStrategy
 */
public abstract class JavaMergeStrategy implements IJavaMergeStrategy {
	private boolean fDefaultPreserveNonCollisionOldMembers = false;
	private boolean fDefaultIncrementalPreserveCollision = false;
	protected static final String DEFAULT_GENERATED_MEMBER_FLAG = "@generated";//$NON-NLS-1$
	protected static final String DEFAULT_USER_CODE_BEGIN = "// user code begin";//$NON-NLS-1$
	protected static final String DEFAULT_USER_CODE_BEGIN_TEMPLATE = DEFAULT_USER_CODE_BEGIN + " {%0}";//$NON-NLS-1$
	protected static final String DEFAULT_USER_CODE_END = "// user code end";//$NON-NLS-1$

	/**
	 * JavaMergeStrategy default constructor.
	 */
	public JavaMergeStrategy() {
		super();
	}

	/**
	 * The default default field merglet is {@link JavaFieldMerglet}.
	 * 
	 * @see IJavaMergeStrategy
	 */
	public IJavaMerglet createDefaultFieldMerglet() {
		return new JavaFieldMerglet(this);
	}

	/**
	 * The default default method merglet is {@link JavaMethodMerglet}.
	 * 
	 * @see IJavaMergeStrategy
	 */
	public IJavaMerglet createDefaultMethodMerglet() {
		return new JavaMethodMerglet(this);
	}

	/**
	 * The default default type merglet is {@link JavaTypeMerglet}.
	 * 
	 * @see IJavaMergeStrategy
	 */
	public IJavaMerglet createDefaultTypeMerglet() {
		return new JavaTypeMerglet(this);
	}

	/**
	 * Creates the merge results object from the history object. Subclasses would want to override
	 * if they have a specialization of MergeResults and/or the history descriptor.
	 */
	protected MergeResults createInitialMergeResults(JavaMemberHistoryDescriptor histDesc) {
		MergeResults mr = new MergeResults();
		mr.setDeleteOnly(histDesc.isDeleteOnly());
		mr.setOldMember(histDesc.getOldMember());
		mr.setCollisionMember(histDesc.getCollisionMember());
		return mr;
	}

	/**
	 * @see IJavaMergeStrategy
	 */
	public String getGeneratedMemberTag() {
		return DEFAULT_GENERATED_MEMBER_FLAG;
	}

	/**
	 * @see IJavaMergeStrategy
	 */
	public String getUserCodeBegin() {
		return DEFAULT_USER_CODE_BEGIN;
	}

	/**
	 * @see IJavaMergeStrategy
	 */
	public String getUserCodeBeginTemplate() {
		return DEFAULT_USER_CODE_BEGIN_TEMPLATE;
	}

	/**
	 * @see IJavaMergeStrategy
	 */
	public String getUserCodeEnd() {
		return DEFAULT_USER_CODE_END;
	}

	/**
	 * @see IJavaMergeStrategy
	 */
	public boolean isDefaultIncrementalPreserveCollision() {
		return fDefaultIncrementalPreserveCollision;
	}

	/**
	 * @see IJavaMergeStrategy
	 */
	public boolean isDefaultPreserveNonCollisionOldMembers() {
		return fDefaultPreserveNonCollisionOldMembers;
	}

	/**
	 * @see IJavaMergeStrategy
	 */
	public MergeResults merge(JavaMemberHistoryDescriptor histDesc, IDOMField newField) throws MergeException {
		return merge(histDesc, newField, createDefaultFieldMerglet());
	}

	/**
	 * @see IJavaMergeStrategy
	 */
	public MergeResults merge(JavaMemberHistoryDescriptor histDesc, IDOMMember newMember, IJavaMerglet merglet) throws MergeException {
		// Set up our results and get the old member for tests.
		MergeResults mr = createInitialMergeResults(histDesc);

		// If this is delete only, we only need to check the old member.
		if (mr.isDeleteOnly())
			mergeForDeleteOnly(mr, merglet);
		else
			mergeForGenerate(mr, newMember, merglet);

		return mr;
	}

	/**
	 * @see IJavaMergeStrategy The default strategy only generates the type if it does not exist or
	 *      the old member was marked generated. It doesn't generate if the old and new are exactly
	 *      the same either.
	 */
	public MergeResults merge(JavaMemberHistoryDescriptor histDesc, IDOMType newType) throws MergeException {
		return merge(histDesc, newType, createDefaultTypeMerglet());
	}

	/**
	 * @see IJavaMergeStrategy
	 */
	public MergeResults merge(JavaMethodHistoryDescriptor histDesc, IDOMMethod newMethod) throws MergeException {
		return merge(histDesc, newMethod, createDefaultMethodMerglet());
	}

	/**
	 * {@link JavaMergeStrategy#merge(JavaMemberHistoryDescriptor, IDOMMember, IJavaMerglet)}uses
	 * this method for merging if the member history descriptor (see
	 * {@link JavaMemberHistoryDescriptor}) indicated that the only action expected is a delete of
	 * the old member.
	 * <p>
	 * This method uses {@link IJavaMerglet#validateForDeleteOnly(MergeResults)}to decide of the
	 * delete only generation is allowed. If the delete is disallowed,
	 * {@link MergeResults#isGenerate()}is set to false.
	 * 
	 * @exception org.eclipse.jst.j2ee.internal.java.codegen.MergeException
	 */
	protected void mergeForDeleteOnly(MergeResults mr, IJavaMerglet merglet) throws MergeException {
		if (mr.getOldMember() != null) {
			mr.setGenerate(merglet.validateForDeleteOnly(mr));
		} else {
			// No need to delete a member that is not there.
			mr.setGenerate(false);
		}
	}

	/**
	 * {@link JavaMergeStrategy#merge(JavaMemberHistoryDescriptor, IDOMMember, IJavaMerglet)}uses
	 * this method for merging if the member history descriptor (see
	 * {@link JavaMemberHistoryDescriptor}) did <b>not </b> indicate that the only action expected
	 * is a delete of the old member.
	 * <p>
	 * Checks that we need to generate and that we can generate. Also sets up for needed deletions.
	 * Uses {@link IJavaMerglet#validateCollision(MergeResults)},
	 * {@link IJavaMerglet#isPreserveNonCollisionOldMembers()},
	 * {@link IJavaMerglet#validateForDelete(MergeResults)},
	 * {@link IJavaMerglet#isIncrementalPreserveCollision()}and
	 * {@link IJavaMerglet#merge(MergeResults, IDOMMember)}.
	 * 
	 * @exception org.eclipse.jst.j2ee.internal.java.codegen.MergeException
	 */
	protected void mergeForGenerate(MergeResults mr, IDOMMember newMember, IJavaMerglet merglet) throws MergeException {
		// Check for collisions that cause us not to want to generate the new member.
		boolean needToGenerate = merglet.validateCollision(mr);

		// If the old member is not null and is different
		// than the collision member and the merglet does not
		// necessarily want to preserve old non collision members,
		// we need to make sure it is okay to delete it.
		boolean okayToDeleteOldMember = false;
		if ((mr.getOldMember() != null) && (mr.getOldMember() != mr.getCollisionMember()) && (!merglet.isPreserveNonCollisionOldMembers()))
			okayToDeleteOldMember = merglet.validateForDelete(mr);

		// If we collided above, we do not need to generate.
		if (needToGenerate) {
			// At this point we think we are generating a member, however
			// the merglet could decide the generation is not needed because
			// the result is the same as the collision member.
			// Also need to consider incremental collision replacement.
			if (!isBatchGeneration() && merglet.isIncrementalPreserveCollision() && (mr.getCollisionMember() != null))
				needToGenerate = false;
			else
				needToGenerate = merglet.merge(mr, newMember);
		}

		// If the merglet decided to generate the new member...
		if (needToGenerate) {
			// We will generate the new member. If it is not
			// okay to delete the old member, clear the old
			// member out because we want to leave it.
			if (!okayToDeleteOldMember)
				mr.setOldMember(null);
		} else {
			// If there is an old member that is okay to delete, revert to delete only.
			if (okayToDeleteOldMember)
				mr.setDeleteOnly(true);
			else
				mr.setGenerate(false);
		}
	}

	/**
	 * @see IJavaMergeStrategy
	 */
	public void setDefaultIncrementalPreserveCollision(boolean newDefaultIncrementalPreserveCollision) {
		fDefaultIncrementalPreserveCollision = newDefaultIncrementalPreserveCollision;
	}

	/**
	 * @see IJavaMergeStrategy
	 */
	public void setDefaultPreserveNonCollisionOldMembers(boolean newDefaultPreserveNonCollisionOldMembers) {
		fDefaultPreserveNonCollisionOldMembers = newDefaultPreserveNonCollisionOldMembers;
	}

	/**
	 * @see IJavaMergeStrategy
	 */
	public boolean wasGenerated(String source, int endCheckIndex) {
		if (source == null)
			return true;

		boolean foundGeneratedFlag = false;
		if (endCheckIndex <= 0)
			foundGeneratedFlag = (source.indexOf(getGeneratedMemberTag()) >= 0);
		else
			foundGeneratedFlag = (source.lastIndexOf(getGeneratedMemberTag(), endCheckIndex) >= 0);
		return foundGeneratedFlag;
	}

	/**
	 * @see IJavaMergeStrategy
	 */
	public boolean wasGenerated(IMember member) throws MergeException {
		if ((member == null) || (!member.exists()))
			return true;

		boolean foundGeneratedFlag = false;
		try {
			int endCheckIndex = member.getNameRange().getOffset() - member.getSourceRange().getOffset();
			foundGeneratedFlag = wasGenerated(member.getSource(), endCheckIndex);
		} catch (JavaModelException exc) {
			throw new MergeException(exc);
		}
		return foundGeneratedFlag;
	}
}