/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb20.commands;


import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.jst.j2ee.ejb.internal.operations.IEJBProgressCommand;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;


public class GenericCommandOperation extends WTPOperation {

	BasicCommandStack fCommandStack;
	Command fCommand;

	/**
	 * Insert the method's description here. Creation date: (10/13/00 4:32:28 PM)
	 */
	public GenericCommandOperation(BasicCommandStack commandStack, Command command) {

		fCommandStack = commandStack;
		fCommand = command;
	}

	/**
	 * execute method comment.
	 */
	protected void execute(IProgressMonitor monitor) throws CoreException {
		((IEJBProgressCommand) fCommand).setProgressMonitor(monitor);
		fCommandStack.execute(fCommand);
	}
}