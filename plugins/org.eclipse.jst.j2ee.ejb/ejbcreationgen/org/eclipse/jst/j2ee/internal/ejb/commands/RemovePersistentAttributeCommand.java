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


/**
 * Insert the type's description here. Creation date: (10/12/2000 9:36:07 AM)
 * 
 * @author: Administrator
 */
public class RemovePersistentAttributeCommand extends DeletePersistentAttributeCommand {
	/**
	 * RemovePersistentAttributeCommand constructor comment.
	 * 
	 * @param parent
	 *            org.eclipse.jst.j2ee.commands.IEJBCommand
	 * @param aName
	 *            java.lang.String
	 */
	public RemovePersistentAttributeCommand(IRootCommand parent, String aName) {
		super(parent, aName);
		setGenerateJava(false);
	}
}