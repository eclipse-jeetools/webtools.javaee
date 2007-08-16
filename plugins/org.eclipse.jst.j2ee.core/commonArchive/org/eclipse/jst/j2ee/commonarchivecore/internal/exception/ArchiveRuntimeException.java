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

import org.eclipse.jst.j2ee.internal.IWrappedException;
import org.eclipse.jst.j2ee.internal.WrappedRuntimeException;



/**
 * Base exception class for runtime exceptions occurring with manipulation of archives; there are
 * some situations where we can only throw a runtime exception instead of a subtype of exception,
 * because the signatures of etools generated methods cannot be overridden to throw any exception
 * other than runtime.
 */
public class ArchiveRuntimeException extends WrappedRuntimeException implements IWrappedException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4727603215052186958L;

	/**
	 * Constructor for ArchiveRuntimeException.
	 */
	public ArchiveRuntimeException() {
		super();
	}

	/**
	 * Constructor for ArchiveRuntimeException.
	 * 
	 * @param e
	 */
	public ArchiveRuntimeException(Exception e) {
		super(e);
	}

	/**
	 * Constructor for ArchiveRuntimeException.
	 * 
	 * @param s
	 */
	public ArchiveRuntimeException(String s) {
		super(s);
	}

	/**
	 * Constructor for ArchiveRuntimeException.
	 * 
	 * @param s
	 * @param e
	 */
	public ArchiveRuntimeException(String s, Exception e) {
		super(s, e);
	}

}
