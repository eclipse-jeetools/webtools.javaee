/*******************************************************************************
 * Copyright (c) 2001, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal.exception;



/**
 * @deprecated No Longer used; check for null instead Exception which occurs if an attempt is made
 *             to access a non-existent module dd element from an ear file
 */
public class NoModuleElementException extends ArchiveException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3781813351160222774L;

	/**
	 * NoModuleElementException constructor comment.
	 */
	public NoModuleElementException() {
		super();
	}

	/**
	 * NoModuleElementException constructor comment.
	 * 
	 * @param s
	 *            java.lang.String
	 */
	public NoModuleElementException(String s) {
		super(s);
	}
}
