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

import org.eclipse.jst.j2ee.internal.IWrappedException;
import org.eclipse.jst.j2ee.internal.WrappedException;



/**
 * Base exception class for non-runtime exceptions occurring with manipulation of archives, where a
 * caught exception causes this exception to be thrown
 */
public class ArchiveWrappedException extends WrappedException implements IWrappedException {
	/**
	 * Constructor for ArchiveWrappedException.
	 */
	public ArchiveWrappedException() {
		super();
	}

	/**
	 * Constructor for ArchiveWrappedException.
	 * 
	 * @param e
	 */
	public ArchiveWrappedException(Exception e) {
		super(e);
	}

	/**
	 * Constructor for ArchiveWrappedException.
	 * 
	 * @param s
	 */
	public ArchiveWrappedException(String s) {
		super(s);
	}

	/**
	 * Constructor for ArchiveWrappedException.
	 * 
	 * @param s
	 * @param e
	 */
	public ArchiveWrappedException(String s, Exception e) {
		super(s, e);
	}

}
