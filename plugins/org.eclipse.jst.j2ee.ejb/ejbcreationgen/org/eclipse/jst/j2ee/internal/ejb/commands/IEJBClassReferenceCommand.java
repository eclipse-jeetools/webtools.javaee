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
package org.eclipse.jst.j2ee.internal.ejb.commands;

import org.eclipse.jem.java.JavaClass;


/**
 * Insert the type's description here. Creation date: (9/5/2000 10:36:22 PM)
 * 
 * @author: Administrator
 */
public interface IEJBClassReferenceCommand extends IEJBCommand {
	/**
	 * Adds a package import.
	 */
	void addPackageImportName(String packageImportName);

	/**
	 * Adds a super interface.
	 */
	void addSuperInterfaceName(String superInterfaceName);

	/**
	 * Adds a type import.
	 */
	void addTypeImportName(String typeImportName);

	JavaClass getJavaClass();

	String getPackageName();

	String getQualifiedName();

	void setJavaClass(JavaClass newJavaClass);

	void setPackageName(String newPackageName);

	/**
	 * Insert the method's description here. Creation date: (10/5/00 6:06:08 PM)
	 * 
	 * @param newSuperclassName
	 *            java.lang.String
	 */
	void setSuperclassName(java.lang.String newSuperclassName);
}