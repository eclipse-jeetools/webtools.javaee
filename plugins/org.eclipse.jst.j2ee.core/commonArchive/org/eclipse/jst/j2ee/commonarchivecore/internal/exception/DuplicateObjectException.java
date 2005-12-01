/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal.exception;



/**
 * Exception which can occur when an attemp is made to add to a list an object equaling, or having
 * the same name, id, etc, as another object in the list.
 */
public class DuplicateObjectException extends ArchiveException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7269139518957826130L;
	protected Object duplicate;

	/**
	 * DuplicateObjectException constructor comment.
	 */
	public DuplicateObjectException() {
		super();
	}

	/**
	 * DuplicateObjectException constructor comment.
	 * 
	 * @param s
	 *            java.lang.String
	 */
	public DuplicateObjectException(String s) {
		super(s);
	}

	/**
	 * DuplicateObjectException constructor comment.
	 * 
	 * @param s
	 *            java.lang.String
	 */
	public DuplicateObjectException(String s, Object o) {
		super(s);
		setDuplicate(o);
	}

	/**
	 * Insert the method's description here. Creation date: (04/16/01 9:43:00 AM)
	 * 
	 * @return java.lang.Object
	 */
	public java.lang.Object getDuplicate() {
		return duplicate;
	}

	/**
	 * Insert the method's description here. Creation date: (04/16/01 9:43:00 AM)
	 * 
	 * @param newDuplicate
	 *            java.lang.Object
	 */
	protected void setDuplicate(java.lang.Object newDuplicate) {
		duplicate = newDuplicate;
	}
}
