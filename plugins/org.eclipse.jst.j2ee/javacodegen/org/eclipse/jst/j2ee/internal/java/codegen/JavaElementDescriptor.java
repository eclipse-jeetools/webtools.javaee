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
 * Information about a Java element that is to be generated is gathered into an element descriptor
 * for ease of handling.
 */
public abstract class JavaElementDescriptor {
	private String fName = null;

	/**
	 * JavaMemberDescriptor default constructor.
	 */
	public JavaElementDescriptor() {
		super();
	}

	/**
	 * Each Java element to be generated has a name property.
	 * 
	 * @return The name of the element
	 */
	public java.lang.String getName() {
		return fName;
	}

	/**
	 * Each Java element to be generated has a name property.
	 * 
	 * @param newName
	 *            The name of the element
	 */
	public void setName(java.lang.String newName) {
		fName = newName;
	}
}