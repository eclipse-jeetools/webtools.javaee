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
 * Base exception class for non-runtime exceptions occurring with manipulation of archives
 */
public class ArchiveException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4340145465956505570L;

	/**
	 *  
	 */
	public ArchiveException() {
		super();
	}

	/**
	 *  
	 */
	public ArchiveException(String s) {
		super(s);
	}
}
