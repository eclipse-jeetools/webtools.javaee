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
 * Exception which can be thrown as a result of an IO exception which may occur while "re-syncing"
 * an archive after it has been saved. For example, if 10 files are copied from Archive A to Archive
 * B, then archive b is saved, its contents will be loaded from a new source, the newly saved jar
 * file. Therefore, the archive will be reopened after saving, which could result in an IOException.
 */
public class ReopenException extends ArchiveWrappedException {
	/**
	 * ReopenException constructor comment.
	 */
	public ReopenException() {
		super();
	}

	/**
	 * ReopenException constructor comment.
	 * 
	 * @param e
	 *            java.lang.Exception
	 */
	public ReopenException(Exception e) {
		super(e);
	}

	/**
	 * ReopenException constructor comment.
	 * 
	 * @param s
	 *            java.lang.String
	 */
	public ReopenException(String s) {
		super(s);
	}

	/**
	 * ReopenException constructor comment.
	 * 
	 * @param s
	 *            java.lang.String
	 * @param e
	 *            java.lang.Exception
	 */
	public ReopenException(String s, Exception e) {
		super(s, e);
	}
}
