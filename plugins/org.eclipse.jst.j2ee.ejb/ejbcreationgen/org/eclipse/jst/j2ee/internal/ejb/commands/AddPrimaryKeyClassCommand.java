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
 * Insert the type's description here. Creation date: (11/21/2000 12:30:21 PM)
 * 
 * @author: Administrator
 */
public class AddPrimaryKeyClassCommand extends CreatePrimaryKeyClassCommand {
	/**
	 * AddPrimaryKeyClassCommand constructor comment.
	 * 
	 * @param parent
	 *            org.eclipse.jst.j2ee.internal.internal.commands.IRootCommand
	 * @param aJavaClass
	 *            org.eclipse.jem.internal.java.JavaClass
	 */
	public AddPrimaryKeyClassCommand(IRootCommand parent, JavaClass aJavaClass) {
		super(parent, aJavaClass);
		setGenerateJava(false);
	}

	/**
	 * AddPrimaryKeyClassCommand constructor comment.
	 * 
	 * @param parent
	 *            org.eclipse.jst.j2ee.internal.internal.commands.IRootCommand
	 * @param aJavaClassName
	 *            java.lang.String
	 * @param aPackageName
	 *            java.lang.String
	 */
	public AddPrimaryKeyClassCommand(IRootCommand parent, String aJavaClassName, String aPackageName) {
		super(parent, aJavaClassName, aPackageName);
		setGenerateJava(false);
	}
}