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
 * Insert the type's description here. Creation date: (10/12/2000 8:40:37 AM)
 * 
 * @author: Administrator
 */
public class AddBeanClassCommand extends CreateBeanClassCommand {
	/**
	 * This command will create the meta data for the bean class which references
	 * <code>aJavaClass</code>.
	 * 
	 * @param parent
	 *            org.eclipse.jst.j2ee.internal.internal.commands.IEJBCommand
	 * @param aJavaClass
	 *            org.eclipse.jem.internal.java.JavaClass
	 */
	public AddBeanClassCommand(IRootCommand parent, JavaClass aJavaClass) {
		super(parent, aJavaClass);
		setGenerateJava(false);
	}

	/**
	 * This command will create the meta data for a bean class with the name
	 * <code>aJavaClassName</code> in the package <code>aPackageName</code>. It will not
	 * generate any Java code.
	 * 
	 * @param parent
	 *            org.eclipse.jst.j2ee.internal.internal.commands.IEJBCommand
	 * @param aJavaClassName
	 *            java.lang.String
	 * @param aPackageName
	 *            java.lang.String
	 */
	public AddBeanClassCommand(IRootCommand parent, String aJavaClassName, String aPackageName) {
		super(parent, aJavaClassName, aPackageName);
		setGenerateJava(false);
	}
}