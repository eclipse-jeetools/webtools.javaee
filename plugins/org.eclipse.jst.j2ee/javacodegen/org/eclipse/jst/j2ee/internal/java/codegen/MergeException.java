/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.java.codegen;



import org.eclipse.jst.j2ee.internal.codegen.GenerationException;

/**
 * A failure occurred during a code merge operation.
 * 
 * @see IJavaMergeStrategy
 * @see IJavaMerglet
 */
public class MergeException extends GenerationException {
	/**
	 * A merge exception can be constructed from another exception. The other exception will be
	 * nested inside the new exception.
	 * 
	 * @param nested
	 *            java.lang.Exception
	 */
	public MergeException(Exception nested) {
		super(nested);
	}

	/**
	 * A merge exception can be constructed from exception text.
	 * 
	 * @param text
	 *            java.lang.String
	 */
	public MergeException(String text) {
		super(text);
	}

	/**
	 * A merge exception can be constructed from both exception text and another exception. The
	 * other exception will be nested inside the new exception.
	 * 
	 * @param text
	 *            java.lang.String
	 * @param nested
	 *            java.lang.Exception
	 */
	public MergeException(String text, Exception nested) {
		super(text, nested);
	}
}