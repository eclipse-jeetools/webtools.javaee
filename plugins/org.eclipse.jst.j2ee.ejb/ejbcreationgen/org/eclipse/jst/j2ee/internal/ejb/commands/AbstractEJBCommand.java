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

import org.eclipse.emf.common.command.AbstractCommand;



/**
 * Insert the type's description here. Creation date: (11/20/2000 2:11:15 PM)
 * 
 * @author: Administrator
 */
public abstract class AbstractEJBCommand extends AbstractCommand {
	protected IEJBCommand fParent;

	/**
	 * AbstractEJBCommand constructor comment.
	 */
	protected AbstractEJBCommand() {
		super();
	}

	/**
	 * AbstractEJBCommand constructor comment.
	 * 
	 * @param label
	 *            java.lang.String
	 */
	protected AbstractEJBCommand(String label) {
		super(label);
	}

	/**
	 * AbstractEJBCommand constructor comment.
	 * 
	 * @param label
	 *            java.lang.String
	 * @param desc
	 *            java.lang.String
	 */
	protected AbstractEJBCommand(String label, String desc) {
		super(label, desc);
	}

	/**
	 * Currently the EJB commands are not undoable.
	 */
	public boolean canUndo() {
		return false;
	}

	/**
	 * Insert the method's description here. Creation date: (08/18/00 5:00:53 PM)
	 * 
	 * @return org.eclipse.jst.j2ee.internal.internal.commands.ICommand
	 */
	public IEJBCommand getParent() {
		return fParent;
	}

	public boolean hasParent() {
		return getParent() != null;
	}

	public boolean isEnterpriseBeanRootCommand() {
		return false;
	}

	public boolean isRootCommand() {
		return false;
	}

	protected boolean prepare() {
		return true;
	}

	/**
	 * Insert the method's description here. Creation date: (08/18/00 5:00:53 PM)
	 * 
	 * @param newParent
	 *            org.eclipse.jst.j2ee.internal.internal.commands.ICommand
	 */
	protected void setParent(IEJBCommand newParent) {
		fParent = newParent;
	}
}