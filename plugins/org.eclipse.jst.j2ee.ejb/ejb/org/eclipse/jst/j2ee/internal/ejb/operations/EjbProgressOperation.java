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
package org.eclipse.jst.j2ee.internal.ejb.operations;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBCodeGenResourceHandler;
import org.eclipse.jst.j2ee.plugin.J2EEPlugin;
import org.eclipse.wst.common.framework.operation.WTPOperation;


/**
 * Insert the type's description here. Creation date: (12/6/2000 9:40:27 AM)
 * 
 * @author: Administrator
 */
public class EjbProgressOperation extends WTPOperation {
	protected CommandStack commandStack;
	protected IEJBProgressCommand command;

	/**
	 * Insert the method's description here. Creation date: (12/6/2000 9:44:03 AM)
	 * 
	 * @param aProject
	 *            com.ibm.itp.core.api.resources.IProject
	 */
	public EjbProgressOperation(CommandStack commandStack, IEJBProgressCommand command) {
		setCommandStack(commandStack);
		setCommand(command);
	}

	/**
	 * Insert the method's description here. Creation date: (12/12/2000 3:44:26 PM)
	 * 
	 * @return IEJBProgressCommand
	 */
	public String errorMessage() {
		return EJBCodeGenResourceHandler.getString("Error_has_occurred_ERROR_"); //$NON-NLS-1$ = "Error has occurred"
	}

	/**
	 * Performs this operation, reporting progress to the given monitor.
	 * <p>
	 * Subclasses implement this method to provide the operation's behavior.
	 * </p>
	 * <p>
	 * Note that the progress monitor must not be <code>null</code>.
	 * </p>
	 * 
	 * @param monitor
	 *            a progress monitor
	 * @exception OperationFailedException
	 *                if the operations fails
	 */
	protected void execute(IProgressMonitor monitor) throws CoreException {
		if (!command.canExecute())
			throw new CoreException(J2EEPlugin.newErrorStatus(errorMessage(), null));
		getCommand().setProgressMonitor(monitor);
		try {
			getCommandStack().execute(command);
		} catch (Exception e) {
			throw new CoreException(J2EEPlugin.newErrorStatus(errorMessage(), e));
		} finally {
			monitor.done();
		}
	}

	/**
	 * Insert the method's description here. Creation date: (12/12/2000 3:44:26 PM)
	 * 
	 * @return IEJBProgressCommand
	 */
	public IEJBProgressCommand getCommand() {
		return command;
	}

	/**
	 * Insert the method's description here. Creation date: (12/12/2000 3:44:26 PM)
	 * 
	 * @return CommandStack
	 */
	public CommandStack getCommandStack() {
		return commandStack;
	}

	/**
	 * Insert the method's description here. Creation date: (12/12/2000 3:44:26 PM)
	 * 
	 * @param newCommand
	 *            IEJBProgressCommand
	 */
	public void setCommand(IEJBProgressCommand newCommand) {
		command = newCommand;
	}

	/**
	 * Insert the method's description here. Creation date: (12/12/2000 3:44:26 PM)
	 * 
	 * @param newCommandStack
	 *            CommandStack
	 */
	public void setCommandStack(CommandStack newCommandStack) {
		commandStack = newCommandStack;
	}
}