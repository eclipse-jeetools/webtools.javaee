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
/*
 * Created on May 20, 2004
 */
package org.eclipse.jst.j2ee.ejb.internal.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.ejb.commands.IEJBCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.IRootCommand;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperation;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel;


/**
 * @author jlanuti
 */

public abstract class DeleteEnterpriseBeanAbstractOperation extends EditModelOperation {

	protected List beansToBeDeleted;
	protected EnterpriseBean currentEJB;

	/**
	 * @param dataModel
	 */
	public DeleteEnterpriseBeanAbstractOperation(EditModelOperationDataModel dataModel) {
		super(dataModel);
	}

	protected void execute(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException, CoreException {
		preExecute();

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

	protected abstract void preExecute() throws CoreException, InvocationTargetException, InterruptedException;

	protected abstract String errorMessage();

	/**
	 * Return the root command.
	 */
	protected IEJBCommand getCommand() {
		IEJBCommand root = createCommand();
		if (root != null && root.isRootCommand())
			((IRootCommand) root).setOperationHandler(((DeleteEnterpriseBeanDataModel) operationDataModel).getOperationHandler());
		return root;
	}

	/**
	 * @return EnterpriseBean
	 */
	protected EnterpriseBean getCurrentEJB() {
		return currentEJB;
	}

	/**
	 * @param newCurrentEJB
	 *            EnterpriseBean
	 */
	protected void setCurrentEJB(EnterpriseBean newCurrentEJB) {
		currentEJB = newCurrentEJB;
	}

	/**
	 * @return java.util.List
	 */
	protected List getBeansToBeDeleted() {
		if (beansToBeDeleted == null)
			beansToBeDeleted = (List) operationDataModel.getProperty(DeleteEnterpriseBeanDataModel.BEANS_TO_BE_DELETED);
		return beansToBeDeleted;
	}

	protected IEJBCommand createCommand() {
		EnterpriseBean ejb = null;
		IRootCommand root, merged = null;
		for (int i = 0; i < getBeansToBeDeleted().size(); i++) {
			ejb = (EnterpriseBean) getBeansToBeDeleted().get(i);
			setCurrentEJB(ejb);
			root = createRootDeleteCommand(ejb);
			if (root != null) {
				if (merged == null)
					merged = root;
				else
					merged = merged.append(root);
			}
		}
		return merged;
	}

	protected abstract IRootCommand createRootDeleteCommand(EnterpriseBean ejb);
}