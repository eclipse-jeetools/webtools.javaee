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
 * This exception is thrown when a generator name can not be resolved to a generator class.
 */
public class GeneratorNotFoundException extends GenerationException {

	/**
	 * Generator not found exceptions are constructed from the string holding either the logical
	 * generator name or the file name for the Java class that the logical name was mapped to.
	 * 
	 * @param text
	 *            java.lang.String
	 */
	public GeneratorNotFoundException(String text) {
		super(text);
	}
}