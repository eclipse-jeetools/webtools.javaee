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
 * A parameter descriptor is used to describe a method parameter. It holds the parameter name and
 * type. The type is held in Java readable form (not a signature).
 */
public class JavaParameterDescriptor {
	private String fType = null;
	private String fName = null;

	/**
	 * JavaParameterDescriptor default constructor.
	 */
	public JavaParameterDescriptor() {
		super();
	}

	/**
	 * @return the parameter name.
	 */
	public String getName() {
		return fName;
	}

	/**
	 * Returns the parameter type.
	 * 
	 * @return The parameter type in Java readable form (not a signature)
	 */
	public String getType() {
		return fType;
	}

	/**
	 * Sets the parameter name.
	 */
	public void setName(String newName) {
		fName = (newName == null) ? null : newName.trim();
	}

	/**
	 * Sets the parameter type.
	 * 
	 * @param newType
	 *            The parameter name in Java readable form (not a signature)
	 */
	public void setType(String newType) {
		fType = (newType == null) ? null : newType.trim();
	}

	/**
	 * Returns "type name". The {@link JavaParameterDescriptor#getType()}and
	 * {@link JavaParameterDescriptor#getName()}methods are used to build the result.
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer(100);
		sb.append(getType());
		sb.append(' ');
		sb.append(getName());
		return sb.toString();
	}
}