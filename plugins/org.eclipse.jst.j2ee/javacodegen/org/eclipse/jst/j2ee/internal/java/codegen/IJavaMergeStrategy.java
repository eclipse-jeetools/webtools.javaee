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
import org.eclipse.jdt.core.jdom.IDOMField;
import org.eclipse.jdt.core.jdom.IDOMMember;
import org.eclipse.jdt.core.jdom.IDOMMethod;
import org.eclipse.jdt.core.jdom.IDOMType;

/**
 * The merge strategy is used by generators to avoid losing user changes to generated code. The
 * generators use the merge* methods to do this. The semantics of the merge varies significantly
 * from one strategy to another.
 */
public interface IJavaMergeStrategy {
	/**
	 * Returns a default field merglet for the merge strategy.
	 */
	public IJavaMerglet createDefaultFieldMerglet();

	/**
	 * Returns a default method merglet for the merge strategy.
	 */
	public IJavaMerglet createDefaultMethodMerglet();

	/**
	 * Returns a default type merglet for the merge strategy.
	 */
	public IJavaMerglet createDefaultTypeMerglet();

	/**
	 * Returns the tag that this strategy uses for marking members generated.
	 */
	String getGeneratedMemberTag();

	/**
	 * Returns the comment used to start user code.
	 * <p>
	 * The use of VAJ style user code blocks is supported for compatibility and migration purposes.
	 * More intelligent merging via specialized merglets is encouraged for future work.
	 */
	String getUserCodeBegin();

	/**
	 * Returns the comment used to start user code with a template arg added for the block name.
	 * <p>
	 * The use of VAJ style user code blocks is supported for compatibility and migration purposes.
	 * More intelligent merging via specialized merglets is encouraged for future work.
	 */
	String getUserCodeBeginTemplate();

	/**
	 * Returns the comment used to end user code.
	 * <p>
	 * The use of VAJ style user code blocks is supported for compatibility and migration purposes.
	 * More intelligent merging via specialized merglets is encouraged for future work.
	 */
	public String getUserCodeEnd();

	/**
	 * Returns true if the stategy is for batch generation. The hallmark of batch generation is that
	 * members marked generated are deleted if they are not regenerated each time.
	 */
	boolean isBatchGeneration();

	/**
	 * Merglets created with this strategy will use this value as a default for the merglet's
	 * incremental preserve collision property.
	 * 
	 * @return boolean
	 * @see IJavaMerglet
	 */
	boolean isDefaultIncrementalPreserveCollision();

	/**
	 * Merglets created with this strategy will use this value as a default for the merglet's
	 * preserve non-collision old members property.
	 * 
	 * @return boolean
	 * @see IJavaMerglet
	 */
	boolean isDefaultPreserveNonCollisionOldMembers();

	/**
	 * Returns true if generated members are to be marked generated.
	 */
	boolean isTagGenerated();

	/**
	 * Merges user changes to previously generated field into the descriptor for a new field. The
	 * default field merglet for this merge strategy is used.
	 * 
	 * @exception org.eclipse.jst.j2ee.internal.java.codegen.MergeException
	 * @see IJavaMergeStrategy#createDefaultFieldMerglet()
	 */
	MergeResults merge(JavaMemberHistoryDescriptor oldField, IDOMField newField) throws MergeException;

	/**
	 * Merges user changes to previously generated member into the descriptor for a new member using
	 * a caller specified merglet. The caller is responsible for ensuring that the three parameters
	 * are type compatible.
	 * 
	 * @exception org.eclipse.jst.j2ee.internal.java.codegen.MergeException
	 */
	public MergeResults merge(JavaMemberHistoryDescriptor histDesc, IDOMMember newMember, IJavaMerglet merglet) throws MergeException;

	/**
	 * Merges user changes to previously generated type into the descriptor for a new type. The
	 * default type merglet for this merge strategy is used.
	 * 
	 * @exception org.eclipse.jst.j2ee.internal.java.codegen.MergeException
	 * @see IJavaMergeStrategy#createDefaultTypeMerglet()
	 */
	MergeResults merge(JavaMemberHistoryDescriptor oldType, IDOMType newType) throws MergeException;

	/**
	 * Merges user changes to previously generated method into the descriptor for a new method. The
	 * default method merglet for this merge strategy is used.
	 * 
	 * @exception org.eclipse.jst.j2ee.internal.java.codegen.MergeException
	 * @see IJavaMergeStrategy#createDefaultMethodMerglet()
	 */
	MergeResults merge(JavaMethodHistoryDescriptor oldMethod, IDOMMethod newMethod) throws MergeException;

	/**
	 * Post processes the type. For example, a batch strategy will use this opportunity to delete
	 * generated members that were not regenerated. This method is called at the end of
	 * {@link JavaTypeGenerator#run()}.
	 */
	void postProcess(JavaTypeGenerator typeGen) throws MergeException;

	/**
	 * Merglets created with this strategy will use this value as a default for the merglet's
	 * incremental preserve collision property.
	 * 
	 * @param newDefaultIncrementalPreserveCollision
	 *            boolean
	 * @see IJavaMerglet
	 */
	void setDefaultIncrementalPreserveCollision(boolean newIncrementalPreserveCollision);

	/**
	 * Merglets created with this strategy will use this value as a default for the merglet's
	 * preserve non-collision old members property.
	 * 
	 * @param newDefaultPreserveNonCollisionOldMembers
	 *            boolean
	 * @see IJavaMerglet
	 */
	void setDefaultPreserveNonCollisionOldMembers(boolean newDefaultPreserveNonCollisionOldMembers);

	/**
	 * Returns true if the source is marked generated. Returns true if the null is passed for the
	 * source string.
	 * 
	 * @param source
	 *            The source code to check for the
	 *            {@link IJavaMergeStrategy#getGeneratedMemberTag()}tag.
	 * @param endCheckIndex
	 *            A limit on how far into the source to search. Use -1 to search the entire source
	 *            string.
	 */
	boolean wasGenerated(String source, int endCheckIndex);

	/**
	 * Returns true if the member was generated. Returns true if the member is null or does not
	 * exist. The source code up to the member name is checked. This essentially means checking the
	 * Javadoc comment for the member.
	 * 
	 * @param member
	 *            The Java member to check for the
	 *            {@link IJavaMergeStrategy#getGeneratedMemberTag()}tag.
	 */
	boolean wasGenerated(IMember member) throws MergeException;
}