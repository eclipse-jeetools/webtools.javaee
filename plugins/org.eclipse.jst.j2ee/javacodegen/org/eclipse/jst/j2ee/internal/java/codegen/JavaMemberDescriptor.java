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



/**
 * Information about a Java member that is to be generated is gathered into a member descriptor for
 * ease of handling.
 */
public abstract class JavaMemberDescriptor extends JavaElementDescriptor {
	private String fComment = null;
	private int fFlags = 0;

	/**
	 * JavaMemberDescriptor default constructor.
	 */
	public JavaMemberDescriptor() {
		super();
	}

	/**
	 * Each Java element to be generated can have a Javadoc comment.
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getComment() {
		return fComment;
	}

	/**
	 * The flags property defines the Java modifiers for the member.
	 * 
	 * @return int
	 * @see org.eclipse.jdt.core.Flags
	 */
	public int getFlags() {
		return fFlags;
	}

	/**
	 * Each Java element to be generated can have a Javadoc comment.
	 * 
	 * @param newComment
	 *            java.lang.String
	 */
	public void setComment(java.lang.String newComment) {
		fComment = newComment;
	}

	/**
	 * The flags property defines the Java modifiers for the member.
	 * 
	 * @param newFlags
	 *            int
	 * @see org.eclipse.jdt.core.Flags
	 */
	public void setFlags(int newFlags) {
		fFlags = newFlags;
	}
}