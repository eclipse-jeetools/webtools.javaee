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

/**
 * Used to describe the existing member that is to be removed and perhaps act as the source for
 * merging.
 */
public class JavaMemberHistoryDescriptor extends JavaElementHistoryDescriptor {
	private boolean fDeleteOnly = false;
	private IMember fOldMember = null;
	private IMember fCollisionMember = null; // Often the same as the old member.

	/**
	 * JavaMemberDescriptor default constructor.
	 */
	public JavaMemberHistoryDescriptor() {
		super();
	}

	/**
	 * Returns the existing member the new member will collide with.
	 * 
	 * @return org.eclipse.jdt.core.IMember
	 */
	public IMember getCollisionMember() {
		return fCollisionMember;
	}

	/**
	 * Returns the old member to merge from.
	 * 
	 * @return org.eclipse.jdt.core.api.IMember
	 */
	public IMember getOldMember() {
		return fOldMember;
	}

	/**
	 * Some member generators set this to true if a helper that is associated with the generator
	 * indicates that this generation run just wants to delete the member.
	 * 
	 * @return boolean
	 */
	public boolean isDeleteOnly() {
		return fDeleteOnly;
	}

	/**
	 * Set this to the same instance as the old member as appropriate.
	 * 
	 * @param newCollisionMember
	 *            org.eclipse.jdt.core.IMember
	 */
	public void setCollisionMember(IMember newCollisionMember) {
		fCollisionMember = newCollisionMember;
	}

	/**
	 * Some member generators set this to true if a helper that is associated with the generator
	 * indicates that this generation run just wants to delete the member.
	 * 
	 * @param newDeleteOnly
	 *            boolean
	 */
	public void setDeleteOnly(boolean newDeleteOnly) {
		fDeleteOnly = newDeleteOnly;
	}

	/**
	 * Set the old member to merge from.
	 * 
	 * @param newOldMember
	 *            org.eclipse.jdt.core.api.IMember
	 */
	public void setOldMember(IMember newOldMember) {
		fOldMember = newOldMember;
	}
}