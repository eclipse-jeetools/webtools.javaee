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



import org.eclipse.jdt.core.jdom.IDOMMember;

/**
 * A Java merglet is used to merge Java Model members. Each {@link IJavaMergeStrategy}
 * implementation has default merglets it uses, but also enable the use of arbitrary merglets via
 * overloads of the merge apis.
 */
public interface IJavaMerglet {
	/**
	 * Returns true if during incremental generation the collision member must be preserved as is.
	 */
	boolean isIncrementalPreserveCollision();

	/**
	 * Returns true if the old member that is not the same as the collision member should be
	 * preserved (not deleted) as a new member is generated.
	 */
	boolean isPreserveNonCollisionOldMembers();

	/**
	 * Merges from the old member to the new member and returns true if we need to generate the new
	 * member. It returns false if there is no need to generate the new member. For example, the new
	 * and collision members may be identical.
	 * 
	 * @return boolean
	 * @param mr
	 *            The merge result the current merge operation
	 * @param newMember
	 *            A JDOM node for the new member
	 */
	boolean merge(MergeResults mr, IDOMMember newMember) throws MergeException;

	/**
	 * Set to true if during incremental generation the collision member must be preserved as is.
	 * This value is initially derived from the default setting on the strategy.
	 */
	void setIncrementalPreserveCollision(boolean newIncrementalPreserveCollision);

	/**
	 * Set to true if old members that do not collide with the member being generated are to be
	 * preserved. This value is initially derived from the default setting on the strategy.
	 */
	void setPreserveNonCollisionOldMembers(boolean newPreserveNonCollisionOldMembers);

	/**
	 * Returns true if it is okay to generate over the collision element. If false is returned, the
	 * MergeResults may have been updated with a AnalysisReport instance.
	 * 
	 * @param mr
	 *            The merge result the current merge operation
	 */
	boolean validateCollision(MergeResults mr) throws MergeException;

	/**
	 * Returns true if it is okay to delete the old member during a generation merge. If false is
	 * returned, the MergeResults may have been updated with a AnalysisReport instance.
	 * 
	 * @param mr
	 *            The merge result the current merge operation
	 */
	boolean validateForDelete(MergeResults mr) throws MergeException;

	/**
	 * Returns true if it is okay to delete the old member during a delete only merge. If false is
	 * returned, the MergeResults may have been updated with a AnalysisReport instance.
	 * 
	 * @param mr
	 *            The merge result the current merge operation
	 */
	boolean validateForDeleteOnly(MergeResults mr) throws MergeException;
}