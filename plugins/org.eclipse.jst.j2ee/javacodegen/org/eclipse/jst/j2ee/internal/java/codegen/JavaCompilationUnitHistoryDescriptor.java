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



import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.Signature;

/**
 * Used to describe the existing member that is to be removed and perhaps act as the source for
 * merging.
 */
public class JavaCompilationUnitHistoryDescriptor extends JavaElementHistoryDescriptor {
	private String fPackageName = IJavaGenConstants.EMPTY_STRING;
	private boolean fDeleteOnly = false;
	private ICompilationUnit fOldCompilationUnit = null;

	/**
	 * JavaCompilationUnitHistoryDescriptor default constructor.
	 */
	public JavaCompilationUnitHistoryDescriptor() {
		super();
	}

	/**
	 * If the old compilation unit is found by the compilation unit generator, this property will be
	 * set. In some cases it will be a working copy.
	 * 
	 * @return The old compilation unit (may be a working copy)
	 */
	public org.eclipse.jdt.core.ICompilationUnit getOldCompilationUnit() {
		return fOldCompilationUnit;
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
	 * {@link JavaElementHistoryDescriptor#getName()}and
	 * {@link JavaCompilationUnitHistoryDescriptor#getPackageName()}properties are used to build
	 * the qualified name.
	 * 
	 * @return The qualified name
	 */
	public String getQualifiedName() {
		return Signature.toQualifiedName(new String[]{getPackageName(), getName()});
	}

	/**
	 * Some compilation unit generators set this to true if a helper that is associated with the
	 * generator indicates that this generation run just wants to delete the compilation unit.
	 * 
	 * @return boolean
	 */
	public boolean isDeleteOnly() {
		return fDeleteOnly;
	}

	/**
	 * Some compilation unit generators set this to true if a helper that is associated with the
	 * generator indicates that this generation run just wants to delete the compilation unit.
	 * 
	 * @param newDeleteOnly
	 *            boolean
	 */
	public void setDeleteOnly(boolean newDeleteOnly) {
		fDeleteOnly = newDeleteOnly;
	}

	/**
	 * If the old compilation unit is found by the compilation unit generator, this property will be
	 * set. In some cases it will be a working copy.
	 * 
	 * @param newOldCompilationUnit
	 *            The old compilation unit (may be a working copy)
	 */
	public void setOldCompilationUnit(org.eclipse.jdt.core.ICompilationUnit newOldCompilationUnit) {
		fOldCompilationUnit = newOldCompilationUnit;
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