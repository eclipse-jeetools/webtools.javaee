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
 * Created on Nov 26, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.application.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.wst.common.internal.emfworkbench.integration.CompoundingCommandStack;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperation;


/**
 * @author DABERG
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class AddArchiveProjectsToEAROperation extends EditModelOperation {
	/**
	 * @param operationDataModel
	 */
	public AddArchiveProjectsToEAROperation(AddArchiveProjectsToEARDataModel operationDataModel) {
		super(operationDataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		AddArchiveProjectsToEARDataModel dataModel = (AddArchiveProjectsToEARDataModel) operationDataModel;
		List models = (List) dataModel.getProperty(AddArchiveProjectsToEARDataModel.ALL_MODULE_MODELS);
		if (!models.isEmpty()) {
			CompoundingCommandStack cmdStack = new CompoundingCommandStack(getCommandStack());
			AddArchiveProjectToEARDataModel model;
			AddArchiveProjectToEAROperation op = null;
			for (int i = 0; i < models.size(); i++) {
				model = (AddArchiveProjectToEARDataModel) models.get(i);
				op = new AddArchiveProjectToEAROperation(model);
				op.setCommandStack(cmdStack);
				op.doRun(monitor);
			}
			cmdStack.performExecution();
		}
	}
}