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



import org.eclipse.jdt.core.Signature;

/**
 * Information about a Java compilation unit that is to be generated is gathered into an compilation
 * unit descriptor for ease of handling.
 */
public class JavaCompilationUnitDescriptor extends JavaElementDescriptor {
	private String fPackageName = IJavaGenConstants.EMPTY_STRING;

	/**
	 * JavaMemberDescriptor default constructor.
	 */
	public JavaCompilationUnitDescriptor() {
		super();
	}

	/**
	 * Each compilation unit has a package name property.
	 * 
	 * @return The name of the package
	 */
	public java.lang.String getPackageName() {
		return fPackageName;
	}

	/**
	 * Builds and returns the compilation unit's qualified name. The
	 * {@link JavaElementDescriptor#getName()}and
	 * {@link JavaCompilationUnitDescriptor#getPackageName()}properties are used to build the
	 * qualified name.
	 * 
	 * @return The qualified name
	 */
	public String getQualifiedName() {
		return Signature.toQualifiedName(new String[]{getPackageName(), getName()});
	}

	/**
	 * Each compilation unit has a package name property.
	 * 
	 * @param newPackageName
	 *            The name of the package
	 */
	public void setPackageName(java.lang.String newPackageName) {
		fPackageName = (newPackageName == null) ? IJavaGenConstants.EMPTY_STRING : newPackageName.trim();
	}
}