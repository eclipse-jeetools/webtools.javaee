/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.project.facet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class ConnectorCreateDeploymentFilesFilesOperation extends
		CreateDeploymentFilesDataModelOperation {


	public ConnectorCreateDeploymentFilesFilesOperation(IDataModel model) {
		super(model);
	}
	
	@Override
	protected void createDeploymentFiles(IProject project, IProgressMonitor monitor) {
		final IModelProvider provider = ModelProviderManager.getModelProvider(project);
		provider.modify(new Runnable(){
			public void run() {
			}
		}, IModelProvider.FORCESAVE);
	}

}
