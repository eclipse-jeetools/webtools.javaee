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
 * Exception thrown if in exception other than java.io.FileNotFoundException is caught while
 * attempting to load a mof resource.
 */
public class ResourceLoadException extends ArchiveRuntimeException {
	/**
	 * ResourceLoadException constructor comment.
	 */
	public ResourceLoadException() {
		super();
	}

	/**
	 * ResourceLoadException constructor comment.
	 * 
	 * @param e
	 *            java.lang.Exception
	 */
	public ResourceLoadException(Exception e) {
		super(e);
	}

	/**
	 * ResourceLoadException constructor comment.
	 * 
	 * @param s
	 *            java.lang.String
	 */
	public ResourceLoadException(String s) {
		super(s);
	}

	/**
	 * ResourceLoadException constructor comment.
	 * 
	 * @param s
	 *            java.lang.String
	 * @param e
	 *            java.lang.Exception
	 */
	public ResourceLoadException(String s, Exception e) {
		super(s, e);
	}
}
