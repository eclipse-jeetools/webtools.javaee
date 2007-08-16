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
 * Exception which occurs while saving an archive; could occur for a variety of reasons, eg, io
 * failure, etc. Check the nested exception for more info.
 */
public class SaveFailureException extends ArchiveWrappedException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8593253339847650246L;

	/**
	 * SaveFailureException constructor comment.
	 */
	public SaveFailureException() {
		super();
	}

	/**
	 * SaveFailureException constructor comment.
	 * 
	 * @param s
	 *            java.lang.String
	 */
	public SaveFailureException(Exception e) {
		super(e);
	}

	/**
	 * SaveFailureException constructor comment.
	 * 
	 * @param s
	 *            java.lang.String
	 */
	public SaveFailureException(String s) {
		super(s);
	}

	/**
	 * SaveFailureException constructor comment.
	 * 
	 * @param s
	 *            java.lang.String
	 */
	public SaveFailureException(String s, Exception e) {
		super(s, e);
	}
}
