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



import java.util.Iterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBCodeGenResourceHandler;



/**
 * Insert the type's description here. Creation date: (9/11/2000 4:23:55 PM)
 * 
 * @author: Administrator
 */
public class EJBCompoundRootCommand extends AbstractEJBRootCommand {
	/**
	 * EJBCompoundRootCommand constructor comment.
	 * 
	 * @param desc
	 *            java.lang.String
	 */
	public EJBCompoundRootCommand(String desc) {
		super(desc);
	}

	/**
	 * append method comment.
	 */
	public void append(org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBGenerationHelper aHelper) {
	}

	public IRootCommand append(IRootCommand aCommand) {
		if (aCommand == null)
			return this;
		super.append((Command) aCommand);
		((AbstractEJBRootCommand) aCommand).setParent(this);
		return this;
	}

	/**
	 * appendInverse method comment.
	 */
	public void appendInverse(org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBGenerationHelper aHelper) {
	}

	/**
	 * Execute the command. For a compound command this means executing all of the commands that it
	 * contains.
	 */
	protected void executeCodegenCommand() {
		Iterator commandIter = commandList.iterator();
		IEJBCommand command;
		while (commandIter.hasNext()) {
			command = (IEJBCommand) commandIter.next();
			if (command.isEnterpriseBeanRootCommand())
				((EnterpriseBeanCommand) command).executeCodegenCommand();
		}
	}

	/**
	 * Insert the method's description here. Creation date: (9/5/2000 3:12:32 PM)
	 * 
	 * @return EnterpriseBeanCodegenCommand
	 */
	public EnterpriseBeanCodegenCommand getCodegenCommand() {
		return null;
	}

	/**
	 * getEjb method comment.
	 */
	public EnterpriseBean getEjb() {
		return null;
	}

	/**
	 * Insert the method's description here. Creation date: (12/4/2000 1:00:28 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getTaskName() {
		return EJBCodeGenResourceHandler.getString("Multiple_EnterpriseBean_Up_UI_"); //$NON-NLS-1$ = "Multiple EnterpriseBean Updates"
	}

	/**
	 * Initialize additional commands of child commands.
	 */
	protected void initializeAdditionalCommand() {
		Iterator commandIter = commandList.iterator();
		IEJBCommand command;
		while (commandIter.hasNext()) {
			command = (IEJBCommand) commandIter.next();
			if (command.isEnterpriseBeanRootCommand())
				((EnterpriseBeanCommand) command).initializeAdditionalCommand();
		}
	}

	/**
	 * Undo the command. For a compound command this means undoing all of the commands that it
	 * contains.
	 */
	public void preUndoCodegen() throws CommandExecutionFailure {
		super.preUndoCodegen();
	}

	protected void startProgressMonitor() {
		if (getProgressMonitor() != null)
			getProgressMonitor().beginTask(getTaskName(), org.eclipse.core.runtime.IProgressMonitor.UNKNOWN);
	}

	/**
	 * Undo the command. For a compound command this means undoing all of the commands that it
	 * contains.
	 */
	protected void undoCodegenCommand() {
		Iterator commandIter = commandList.iterator();
		IEJBCommand command;
		while (commandIter.hasNext()) {
			command = (IEJBCommand) commandIter.next();
			if (command.isEnterpriseBeanRootCommand())
				((EnterpriseBeanCommand) commandIter.next()).undoCodegenCommand();
		}
	}
}