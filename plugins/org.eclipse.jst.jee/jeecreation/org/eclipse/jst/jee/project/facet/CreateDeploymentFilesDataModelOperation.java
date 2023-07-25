/*******************************************************************************
 * Copyright (c) 2007, 2023 IBM Corporation and others.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.project.facet;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class CreateDeploymentFilesDataModelOperation extends
		AbstractDataModelOperation {

	public CreateDeploymentFilesDataModelOperation(IDataModel model) {
		super(model);
	}

	@Override
	public IStatus execute(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		IProject targetProject = (IProject) model.getProperty(ICreateDeploymentFilesDataModelProperties.TARGET_PROJECT);
		createDeploymentFiles(targetProject, monitor);
		return OK_STATUS;
	}

	protected void createDeploymentFiles(IProject project, IProgressMonitor monitor) {
		// do nothing
	}

}
