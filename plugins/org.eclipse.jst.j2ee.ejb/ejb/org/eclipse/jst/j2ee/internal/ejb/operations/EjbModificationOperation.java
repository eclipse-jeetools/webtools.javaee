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

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.impl.EcoreFactoryImpl;
import org.eclipse.jst.j2ee.internal.ejb.commands.IEJBCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.IRootCommand;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;
import org.eclipse.jst.j2ee.plugin.J2EEPlugin;
import org.eclipse.wst.common.framework.operation.IOperationHandler;
import org.eclipse.wst.common.framework.operation.WTPOperation;


/**
 * Insert the type's description here. Creation date: (7/11/2001 10:16:40 AM)
 * 
 * @author: Administrator
 * @deprecated
 */
public abstract class EjbModificationOperation extends WTPOperation {
	private EJBEditModel editModel;
	private IOperationHandler operationHandler;
	private IProgressMonitor progressMonitor;

	/**
	 * Insert the method's description here. Creation date: (8/12/2001 7:10:18 PM)
	 * 
	 * @param anEditModel
	 *            org.eclipse.jst.j2ee.ejb.ejbproject.EJBEditModel
	 * @param anOperationHandler
	 *            org.eclipse.jst.j2ee.operations.IOperationHandler
	 */
	public EjbModificationOperation(EJBEditModel anEditModel, IOperationHandler anOperationHandler) {
		setEditModel(anEditModel);
		setOperationHandler(anOperationHandler);
	}

	protected abstract IEJBCommand createCommand();

	/**
	 * Return a String for error message if the operation fails.
	 */
	protected abstract String errorMessage();

	/**
	 * Performs the steps that are to be treated as a single logical workspace change.
	 * <p>
	 * Subclasses must implement this method.
	 * </p>
	 * 
	 * @param monitor
	 *            the progress monitor to use to display progress and field user requests to cancel
	 * @exception CoreException
	 *                if the operation fails due to a CoreException
	 * @exception InvocationTargetException
	 *                if the operation fails due to an exception other than CoreException
	 * @exception InterruptedException
	 *                if the operation detects a request to cancel, using
	 *                <code>IProgressMonitor.isCanceled()</code>, it should exit by throwing
	 *                <code>InterruptedException</code>
	 */
	protected void execute(org.eclipse.core.runtime.IProgressMonitor monitor) throws java.lang.reflect.InvocationTargetException, InterruptedException, org.eclipse.core.runtime.CoreException {
		setProgressMonitor(monitor);
		IEJBCommand command = getCommand();
		if (command != null) {
			if (command.isRootCommand())
				((IRootCommand) command).setProgressMonitor(monitor);
			try {
				getCommandStack().execute(command);
			} catch (Exception e) {
				throw new CoreException(J2EEPlugin.newErrorStatus(errorMessage(), e));
			} finally {
				monitor.done();
			}
		}
	}

	/**
	 * Return the root command.
	 */
	protected IEJBCommand getCommand() {
		IEJBCommand root = createCommand();
		if (root != null && root.isRootCommand())
			((IRootCommand) root).setOperationHandler(getOperationHandler());
		return root;
	}

	/**
	 * Insert the method's description here. Creation date: (6/18/2001 9:59:45 AM)
	 * 
	 * @return CommandStack
	 */
	protected CommandStack getCommandStack() {
		return getEditModel().getCommandStack();
	}

	protected EcoreFactory getEcoreFactory() {
		return EcoreFactoryImpl.eINSTANCE;
	}

	/**
	 * Insert the method's description here. Creation date: (6/18/2001 11:01:27 AM)
	 * 
	 * @return EjbEditModel
	 */
	protected EJBEditModel getEditModel() {
		return editModel;
	}

	/**
	 * Insert the method's description here. Creation date: (8/12/2001 7:04:08 PM)
	 * 
	 * @return org.eclipse.jst.j2ee.operations.IOperationHandler
	 */
	protected IOperationHandler getOperationHandler() {
		return operationHandler;
	}

	/**
	 * Insert the method's description here. Creation date: (9/6/2001 6:01:39 PM)
	 * 
	 * @return org.eclipse.core.runtime.IProgressMonitor
	 */
	protected org.eclipse.core.runtime.IProgressMonitor getProgressMonitor() {
		return progressMonitor;
	}

	/**
	 * Insert the method's description here. Creation date: (6/18/2001 11:01:27 AM)
	 * 
	 * @param newEditModel
	 *            EjbEditModel
	 */
	public void setEditModel(EJBEditModel newEditModel) {
		editModel = newEditModel;
	}

	/**
	 * Insert the method's description here. Creation date: (8/12/2001 7:04:08 PM)
	 * 
	 * @param newOperationHandler
	 *            org.eclipse.jst.j2ee.operations.IOperationHandler
	 */
	protected void setOperationHandler(IOperationHandler newOperationHandler) {
		operationHandler = newOperationHandler;
	}

	/**
	 * Insert the method's description here. Creation date: (9/6/2001 6:01:39 PM)
	 * 
	 * @param newProgressMonitor
	 *            org.eclipse.core.runtime.IProgressMonitor
	 */
	protected void setProgressMonitor(org.eclipse.core.runtime.IProgressMonitor newProgressMonitor) {
		progressMonitor = newProgressMonitor;
	}
}