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
import org.eclipse.jdt.core.jdom.IDOMMember;
import org.eclipse.jst.j2ee.internal.codegen.AnalysisResult;


/**
 * An abstract base class that implements more of the {@link IJavaMerglet}api and provides more
 * infrastructure for concrete subclass implementations.
 * <p>
 * If the merglet concept is extended beyond Java members, this class will get a sibling in the
 * inheritance hierarchy.
 */
public abstract class JavaMemberMerglet extends JavaMerglet {
	/**
	 * A JavaMemberMerglet is passed it's associated strategy at construction.
	 */
	public JavaMemberMerglet(IJavaMergeStrategy jms) {
		super(jms);
	}

	/**
	 * This is called when {@link JavaMemberMerglet#validateCollision(MergeResults)}determines that
	 * the collision member can not be overwritten (see {@link MergeResults#getCollisionMember()}).
	 * Subclasses should implement to return the reason.
	 */
	protected abstract Object getMemberCollisionReason();

	/**
	 * This is called when {@link JavaMemberMerglet#validateForDelete(MergeResults)}or
	 * {@link JavaMemberMerglet#validateForDeleteOnly(MergeResults)}determines that the old member
	 * can not be deleted (see {@link MergeResults#getOldMember()}). Subclasses should implement to
	 * return the reason.
	 */
	protected abstract Object getMemberNotDeletedReason();

	/**
	 * Returns the readable identification of the member. The default is the element name.
	 */
	protected String getReadableIdFor(IMember member) throws MergeException {
		return member.getElementName();
	}

	/**
	 * Uses {@link JavaMemberMerglet#mergeMember(IMember, IDOMMember) tomerge the old member (see
	 * {@link MergeResults#getOldMember()}) and the new member. Uses
	 * {@link JavaMemberMerglet#needToGenerate(IMember, IDOMMember)}to make sure we need to
	 * generate the new member over the collision member (see
	 * {@link MergeResults#getCollisionMember()}). For example, the new member could be identical
	 * to the collision member. In that case there is no need to generate the new member.
	 * 
	 * @see IJavaMerglet
	 * @return boolean
	 * @param mr
	 *            org.eclipse.jst.j2ee.internal.java.codegen.MergeResults
	 * @param newMember
	 *            org.eclipse.jdt.core.jdom.IDOMMember
	 */
	public boolean merge(MergeResults mr, IDOMMember newMember) throws MergeException {
		// First merge the member.
		if (mr.getOldMember() != null) {
			if (mergeMember(mr.getOldMember(), newMember)) {
				mr.setMerged(true);
				mr.setSource(newMember.getContents());
			}
		}

		// If no collision member, we need to generate.
		boolean needToGenerate = (mr.getCollisionMember() == null);
		// If there was a collision member, check to see if we need to generate.
		if (!needToGenerate)
			needToGenerate = needToGenerate(mr.getCollisionMember(), newMember);

		return needToGenerate;
	}

	/**
	 * Merges the member. This method is called only if the old member of the merge results is not
	 * null. If the old member is null, there is nothing to merge from. Returns true if merging was
	 * done.
	 * 
	 * @return boolean
	 * @param oldMember
	 *            org.eclipse.jdt.core.IMember
	 * @param newMember
	 *            org.eclipse.jdt.core.jdom.IDOMMember
	 */
	protected abstract boolean mergeMember(IMember oldMember, IDOMMember newMember) throws MergeException;

	/**
	 * Returns true if we need to generate the new member. This method is called only if the
	 * collision member of the merge results is not null. If the collision member is null, we
	 * already know we need to generate.
	 * 
	 * @return boolean
	 * @param collisionMember
	 *            org.eclipse.jdt.core.IMember
	 * @param newMember
	 *            org.eclipse.jdt.core.jdom.IDOMMember
	 */
	protected abstract boolean needToGenerate(IMember collisionMember, IDOMMember newMember) throws MergeException;

	/**
	 * @see IJavaMerglet
	 * @param mr
	 *            org.eclipse.jst.j2ee.internal.java.codegen.MergeResults
	 */
	public boolean validateCollision(MergeResults mr) throws MergeException {
		// If a collision, do not generate and inform the user.
		boolean result = true;
		if (getJavaMergeStrategy().isTagGenerated() && !wasGenerated(mr.getCollisionMember())) {
			result = false;
			AnalysisResult ar = new AnalysisResult();
			ar.setStatus(AnalysisResult.INFORMATIONAL);
			ar.setTo(getReadableIdFor(mr.getCollisionMember()));
			ar.setReason(getMemberCollisionReason());
			ar.setReasonCode(IJavaGenConstants.MEMBER_COLLISION_INFO_CODE);
			mr.setAnalysisResult(ar);
		}
		return result;
	}

	/**
	 * @see IJavaMerglet
	 * @param mr
	 *            org.eclipse.jst.j2ee.internal.java.codegen.MergeResults
	 */
	public boolean validateForDelete(MergeResults mr) throws MergeException {
		return validateForDeleteOnly(mr);
	}

	/**
	 * @see IJavaMerglet
	 * @param mr
	 *            org.eclipse.jst.j2ee.internal.java.codegen.MergeResults
	 */
	public boolean validateForDeleteOnly(MergeResults mr) throws MergeException {
		// If not generated, do not delete. Inform the user.
		boolean result = true;
		if (getJavaMergeStrategy().isTagGenerated() && !wasGenerated(mr.getOldMember())) {
			result = false;
			AnalysisResult ar = new AnalysisResult();
			ar.setStatus(AnalysisResult.INFORMATIONAL);
			ar.setFrom(getReadableIdFor(mr.getOldMember()));
			ar.setReason(getMemberNotDeletedReason());
			ar.setReasonCode(IJavaGenConstants.MEMBER_NO_DELETE_INFO_CODE);
			mr.setAnalysisResult(ar);
		}
		return result;
	}
}