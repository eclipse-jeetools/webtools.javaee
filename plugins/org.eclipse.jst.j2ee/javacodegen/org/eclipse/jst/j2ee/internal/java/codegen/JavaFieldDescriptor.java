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


/**
 * Information about a Java field that is to be generated is gathered into a field descriptor for
 * ease of handling.
 */
public class JavaFieldDescriptor extends JavaMemberDescriptor {
	private String fType = null;
	private String fInitializer = null;

	/**
	 * JavaFieldDescriptor default constructor.
	 */
	public JavaFieldDescriptor() {
		super();
	}

	/**
	 * Each Java field to be generated can have an initializer.
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getInitializer() {
		return fInitializer;
	}

	/**
	 * Each Java field to be generated must have a type.
	 * 
	 * @return The type in Java readable form (not a signature)
	 */
	public java.lang.String getType() {
		return fType;
	}

	/**
	 * Each Java field to be generated can have an initializer.
	 * 
	 * @param newInitializer
	 *            java.lang.String
	 */
	public void setInitializer(java.lang.String newInitializer) {
		fInitializer = newInitializer;
	}

	/**
	 * Each Java field to be generated must have a type.
	 * 
	 * @param newType
	 *            The type in Java readable form (not a signature)
	 */
	public void setType(java.lang.String newType) {
		fType = newType;
	}
}