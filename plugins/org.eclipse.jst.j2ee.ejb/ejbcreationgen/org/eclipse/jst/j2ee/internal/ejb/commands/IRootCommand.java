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

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.command.Command;
import org.eclipse.wst.common.frameworks.internal.operations.IOperationHandler;



/**
 * Insert the type's description here. Creation date: (9/11/2000 3:33:06 PM)
 * 
 * @author: Administrator
 */
public interface IRootCommand extends org.eclipse.jst.j2ee.ejb.internal.operations.IEJBProgressCommand, IEJBCommand {
	/**
	 * This adds a command to this compound command's the list of commands.
	 */
	void append(Command command);

	IRootCommand append(IEJBCommand command);

	IRootCommand append(IRootCommand aCommand);

	String getName();

	org.eclipse.emf.ecore.resource.Resource getResource();

	void setName(String aNewName);

	/**
	 * Set the object which will be used to present errors, messages, and continuation prompts to
	 * the user when needed.
	 */
	void setOperationHandler(IOperationHandler newOperationHandler);

	/**
	 * Set this value to true if you do not want to propagate changes to subclasses and related
	 * beans. This should only be used when you know that the related beans are already being fully
	 * updated.
	 * 
	 * @param shouldPropagateAllChanges
	 *            The shouldPropagateAllChanges to set
	 */
	void setShouldPropagateAllChanges(boolean shouldPropagateAllChanges);

	IProject getProject();
}