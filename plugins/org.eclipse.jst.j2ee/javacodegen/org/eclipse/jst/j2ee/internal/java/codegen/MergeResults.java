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
import org.eclipse.jdt.core.jdom.IDOMMethod;
import org.eclipse.jst.j2ee.internal.codegen.AnalysisResult;


/**
 * A description of the merge, including an analysis result that may be returned from the enclosing
 * analysis step.
 */
public class MergeResults {
	private boolean fGenerate = true;
	private boolean fMerged = false;
	private boolean fDeleteOnly = false;
	private IMember fOldMember = null;
	private IMember fCollisionMember = null;
	private String fSource = null;
	private AnalysisResult fAnalysisResult = null;

	/**
	 * MergeResults default constructor.
	 */
	public MergeResults() {
		super();
	}

	/**
	 * Returns the analysis result from the merge. The generator may return this analysis result
	 * from analyze or another result instead of or merged with this result.
	 * 
	 * @return org.eclipse.jst.j2ee.internal.codegen.helpers.AnalysisResult
	 */
	public org.eclipse.jst.j2ee.internal.codegen.AnalysisResult getAnalysisResult() {
		return fAnalysisResult;
	}

	/**
	 * The collision member is a preexisting member that the new member being generated collides
	 * with. Depending on the merge strategy being used, the collision member may be left as is or
	 * may be replaced by the new member. The new member may have been merged with the old member
	 * (see, {@link MergeResults#getOldMember()}). The collision member and the old member are
	 * often the same member, but may not be.
	 * 
	 * @return org.eclipse.jdt.core.IMember
	 */
	public org.eclipse.jdt.core.IMember getCollisionMember() {
		return fCollisionMember;
	}

	/**
	 * The old member is a preexisting member that the new member is logically replacing (perhaps
	 * not physically, i.e. may not be the collision member (
	 * {@link MergeResults#getCollisionMember()})). Depending on the merge strategy being used, the
	 * old member may be merged with the new member. The old member and the collision member are
	 * often the same member, but may not be. If the old member is not the collision member, it may
	 * or may not be deleted.
	 * 
	 * @return org.eclipse.jdt.core.IMember
	 */
	public org.eclipse.jdt.core.IMember getOldMember() {
		return fOldMember;
	}

	/**
	 * After {@link IJavaMergeStrategy#merge(JavaMethodHistoryDescriptor, IDOMMethod)}is returns,
	 * if {@link MergeResults#isMerged()}returns true, this method returns the merged source.
	 * Otherwise, this method returns null.
	 * <p>
	 * For {@link JavaMemberGenerator}instances, by the time {@link JavaMemberGenerator#analyze()}
	 * completes the generator has placed the source for the target member into the merge results
	 * regardless of the result of {@link MergeResults#isMerged()}(see
	 * {@link JavaMemberGenerator#getMergeResults()}).
	 * 
	 * @return java.lang.String
	 */
	public String getSource() {
		return fSource;
	}

	/**
	 * Returns true if the merge has determined that the only action needed is to delete the old
	 * member (see {@link MergeResults#getOldMember()}). This should only be honored if
	 * {@link MergeResults#isGenerate()}returns true. The generator may alter this value after
	 * {@link IJavaMergeStrategy#merge(JavaMethodHistoryDescriptor, IDOMMethod)}returns.
	 * 
	 * @return boolean
	 */
	public boolean isDeleteOnly() {
		return fDeleteOnly;
	}

	/**
	 * Returns true if the merge has determined that some generation action needs to be taken, i.e.
	 * the new member needs to be generated and/or the old member deleted. The generator may alter
	 * this value after {@link IJavaMergeStrategy#merge(JavaMethodHistoryDescriptor, IDOMMethod)}
	 * returns.
	 * 
	 * @return boolean
	 */
	public boolean isGenerate() {
		return fGenerate;
	}

	/**
	 * Returns true if the merge strategy, via a merglet, had to merge the old member (see
	 * {@link MergeResults#getOldMember()}) with the new member.
	 * 
	 * @return boolean
	 */
	public boolean isMerged() {
		return fMerged;
	}

	/**
	 * Sets the analysis result for the merge. Typically called by a merglet.
	 * 
	 * @param newAnalysisResult
	 *            org.eclipse.jst.j2ee.internal.codegen.helpers.AnalysisResult
	 */
	public void setAnalysisResult(org.eclipse.jst.j2ee.internal.codegen.AnalysisResult newAnalysisResult) {
		fAnalysisResult = newAnalysisResult;
	}

	/**
	 * Sets the preexisting member that the new member will collide with. Typically initialized from
	 * the history descriptor passed from the generator, but may later be modified. Often the same
	 * as the old member ({see
	 * 
	 * @link MergeResults#setOldMember(org.eclipse.jdt.core.IMember)}), but may not be.
	 * @param newCollisionMember
	 *            org.eclipse.jdt.core.IMember
	 */
	public void setCollisionMember(org.eclipse.jdt.core.IMember newCollisionMember) {
		fCollisionMember = newCollisionMember;
	}

	/**
	 * See {@link MergeResults#isDeleteOnly()}.
	 * 
	 * @param newDeleteOnly
	 *            boolean
	 */
	public void setDeleteOnly(boolean newDeleteOnly) {
		fDeleteOnly = newDeleteOnly;
	}

	/**
	 * See {@link MergeResults#isGenerate()}.
	 * 
	 * @param newGenerate
	 *            boolean
	 */
	public void setGenerate(boolean newGenerate) {
		fGenerate = newGenerate;
	}

	/**
	 * See {@link MergeResults#isMerged()}.
	 * 
	 * @param newMerged
	 *            boolean
	 */
	public void setMerged(boolean newMerged) {
		fMerged = newMerged;
	}

	/**
	 * Sets the preexisting member that the new member may be merged with. Typically initialized
	 * from the history descriptor passed from the generator, but may later be modified. Often the
	 * same as the collision member ({see
	 * 
	 * @link MergeResults#setCollisionMember(org.eclipse.jdt.core.IMember)}), but may not be.
	 * @param newOldMember
	 *            org.eclipse.jdt.core.IMember
	 */
	public void setOldMember(org.eclipse.jdt.core.IMember newOldMember) {
		fOldMember = newOldMember;
	}

	/**
	 * Set by the merglet when it merges source. The {@link MergeResults#isMerged()}method will
	 * return true in this case. Otherwise, the merge results source is set to null when the merglet
	 * completes.
	 * <p>
	 * For {@link JavaMemberGenerator}instances, by the time {@link JavaMemberGenerator#analyze()}
	 * completes the generator has placed the source for the target member into the merge results
	 * regardless of the result of {@link MergeResults#isMerged()}(see
	 * {@link JavaMemberGenerator#getMergeResults()}).
	 * 
	 * @param newSource
	 *            java.lang.String
	 */
	public void setSource(String newSource) {
		fSource = newSource;
	}

	/**
	 * Return true if a change is required.
	 */
	public boolean isChangeNeeded() {
		return isDeleteOnly() || isGenerate() || isMerged();
	}
}