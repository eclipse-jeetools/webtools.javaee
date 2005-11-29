/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
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
 * Exception which occurs while opening an archive; could occur for a variety of reasons, eg, io
 * failure, deployment descriptor errors, etc. Check the nested exception for more info.
 */
public class OpenFailureException extends ArchiveWrappedException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1786924156051091340L;

	/**
	 * OpenFailureException constructor comment.
	 */
	public OpenFailureException() {
		super();
	}

	/**
	 * OpenFailureException constructor comment.
	 * 
	 * @param e
	 *            java.lang.Exception
	 */
	public OpenFailureException(Exception e) {
		super(e);
	}

	/**
	 * OpenFailureException constructor comment.
	 * 
	 * @param s
	 *            java.lang.String
	 */
	public OpenFailureException(String s) {
		super(s);
	}

	/**
	 * OpenFailureException constructor comment.
	 * 
	 * @param s
	 *            java.lang.String
	 * @param e
	 *            java.lang.Exception
	 */
	public OpenFailureException(String s, Exception e) {
		super(s, e);
	}
}
