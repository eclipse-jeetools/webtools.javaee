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



import java.util.Iterator;
import java.util.List;

/**
 * Information about a Java type that is to be generated is gathered into a type descriptor for ease
 * of handling.
 */
public abstract class JavaTypeDescriptor extends JavaMemberDescriptor {
	private String[] fSuperInterfaceNames = null;

	/**
	 * JavaTypeDescriptor default constructor.
	 */
	public JavaTypeDescriptor() {
		super();
	}

	/**
	 * Each Java type to be generated can have super interfaces. The default is no super interfaces.
	 * 
	 * @return The super interface names in Java readable form (not signatures)
	 */
	public String[] getSuperInterfaceNames() {
		return fSuperInterfaceNames;
	}

	/**
	 * Returns true if this is a class descriptor.
	 */
	public boolean isClass() {
		return false;
	}

	/**
	 * Returns true if this is a interface descriptor.
	 */
	public boolean isInterface() {
		return false;
	}

	/**
	 * Each Java type to be generated can have super interfaces. The default is no super interfaces.
	 * 
	 * @param newSuperInterfaceNames
	 *            The super interface names in Java readable form (not signatures)
	 */
	public void setSuperInterfaceNames(String[] newSuperInterfaceNames) {
		fSuperInterfaceNames = newSuperInterfaceNames;
	}

	/**
	 * Each Java type to be generated can have super interfaces. The default is no super interfaces.
	 * 
	 * @param newSuperInterfaceNames
	 *            The super interface names in Java readable form (not signatures) The toString
	 *            method is called on each member of the List.
	 */
	public void setSuperInterfaceNames(List newSuperInterfaceNames) {
		fSuperInterfaceNames = new String[newSuperInterfaceNames.size()];
		Iterator newSuperInterfaceNamesIter = newSuperInterfaceNames.iterator();
		int i = 0;
		while (newSuperInterfaceNamesIter.hasNext()) {
			fSuperInterfaceNames[i] = newSuperInterfaceNamesIter.next().toString();
			i++;
		}
	}
}