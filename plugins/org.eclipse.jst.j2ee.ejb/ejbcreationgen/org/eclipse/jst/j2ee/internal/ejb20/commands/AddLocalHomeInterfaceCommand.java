/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb20.commands;


import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.internal.ejb.commands.IRootCommand;


public class AddLocalHomeInterfaceCommand extends CreateLocalHomeInterfaceCommand {

	/**
	 * Constructor for AddLocalHomeInterfaceCommand.
	 * 
	 * @param parent
	 * @param aJavaClass
	 */
	public AddLocalHomeInterfaceCommand(IRootCommand parent, JavaClass aJavaClass) {
		super(parent, aJavaClass);
		setGenerateJava(false);
	}


	/**
	 * Constructor for AddLocalHomeInterfaceCommand.
	 * 
	 * @param parent
	 * @param aJavaClassName
	 * @param aPackageName
	 */
	public AddLocalHomeInterfaceCommand(IRootCommand parent, String aJavaClassName, String aPackageName) {
		super(parent, aJavaClassName, aPackageName);
		setGenerateJava(false);
	}


}