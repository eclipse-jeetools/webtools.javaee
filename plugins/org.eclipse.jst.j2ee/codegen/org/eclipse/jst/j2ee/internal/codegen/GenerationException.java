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
package org.eclipse.jst.j2ee.internal.codegen;


/**
 * Base class for generation exceptions.
 */
public class GenerationException extends Exception {
	private Exception fNested = null;

	/**
	 * A generation exception can be constructed from another exception. The other exception will be
	 * nested inside the new exception.
	 * 
	 * @param nested
	 *            java.lang.Exception
	 */
	public GenerationException(Exception nested) {
		super(nested.getMessage());
		fNested = nested;
	}

	/**
	 * A generation exception can be constructed from exception text.
	 * 
	 * @param text
	 *            java.lang.String
	 */
	public GenerationException(String text) {
		super(text);
	}

	/**
	 * A generation exception can be constructed from both exception text and another exception. The
	 * other exception will be nested inside the new exception.
	 * 
	 * @param text
	 *            java.lang.String
	 * @param nested
	 *            java.lang.Exception
	 */
	public GenerationException(String text, Exception nested) {
		super(text);
		fNested = nested;
	}

	/**
	 * Returns the nested exception. It may be null.
	 * 
	 * @return java.lang.Exception
	 */
	public java.lang.Exception getNested() {
		return fNested;
	}
}