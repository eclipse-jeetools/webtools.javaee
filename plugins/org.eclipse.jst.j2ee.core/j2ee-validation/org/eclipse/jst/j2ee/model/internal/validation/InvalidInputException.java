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
package org.eclipse.jst.j2ee.model.internal.validation;


import org.eclipse.jem.java.JavaClass;

/**
 * This exception is thrown when either a type cannot be reflected
 * or when expected input (e.g. the remote interface) is null.
 */
public class InvalidInputException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7811046448684869845L;
	private JavaClass _class = null;
	
	/**
	 * This constructor should be used only when there is no information
	 * available to identify what is invalid.
	 */
	public InvalidInputException() {
		this(null);
	}
	
	/**
	 * This constructor is used when the given type cannot be reflected.
	 */
	public InvalidInputException(JavaClass clazz) {
		_class = clazz;
	}
	
	public JavaClass getJavaClass() {
		return _class;
	}
}
