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


public class NoModuleFileException extends ArchiveRuntimeException {

	/**
	 * Constructor for NoModuleFileException.
	 */
	public NoModuleFileException() {
		super();
	}

	/**
	 * Constructor for NoModuleFileException.
	 * 
	 * @param e
	 */
	public NoModuleFileException(Exception e) {
		super(e);
	}

	/**
	 * Constructor for NoModuleFileException.
	 * 
	 * @param s
	 */
	public NoModuleFileException(String s) {
		super(s);
	}

	/**
	 * Constructor for NoModuleFileException.
	 * 
	 * @param s
	 * @param e
	 */
	public NoModuleFileException(String s, Exception e) {
		super(s, e);
	}

}
