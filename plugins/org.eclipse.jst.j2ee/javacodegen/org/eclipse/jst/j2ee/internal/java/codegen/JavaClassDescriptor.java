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
 * Information about a Java class that is to be generated is gathered into a class descriptor for
 * ease of handling.
 */
public class JavaClassDescriptor extends JavaTypeDescriptor {
	private String fSuperclassName = null;

	/**
	 * JavaClassDescriptor default constructor.
	 */
	public JavaClassDescriptor() {
		super();
	}

	/**
	 * Each Java class to be generated can have a superclass on an extends clause. The default is no
	 * extends clause.
	 * 
	 * @return The superclass name in Java readable form (not a signature)
	 */
	public java.lang.String getSuperclassName() {
		return fSuperclassName;
	}

	/**
	 * Returns true if this is a class descriptor.
	 */
	public boolean isClass() {
		return true;
	}

	/**
	 * Each Java class to be generated can have a superclass on an extends clause. The default is no
	 * extends clause.
	 * 
	 * @param newSuperclassName
	 *            The superclass name in Java readable form (not a signature)
	 */
	public void setSuperclassName(java.lang.String newSuperclassName) {
		fSuperclassName = newSuperclassName;
	}
}