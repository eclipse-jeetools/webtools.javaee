/*******************************************************************************
 * Copyright (c) 2005 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * rfrost@bea.com - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.refactor.operations;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * Subclass of <code>ProjectRefactorOperation</code> that contains deletion-specific logic.
 */
public class ProjectDeleteOperation extends ProjectRefactorOperation {

	public ProjectDeleteOperation(final IDataModel model) {
		super(model);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.refactor.operations.ProjectRefactorOperation#updateProject(org.eclipse.jst.j2ee.internal.refactor.operations.ProjectRefactorMetadata)
	 */
	protected void updateProject(final ProjectRefactorMetadata refactoredMetadata) 
		throws ExecutionException {
		// update any server instance refs to the refactored project
		super.updateServerRefs(refactoredMetadata, null);
	}

	/**
	 * Updates the metadata for dependent projects
	 * @throws ExecutionException
	 */
	protected void updateDependentProjects(final ProjectRefactorMetadata refactoredMetadata,
			final IProgressMonitor monitor) throws ExecutionException {
		super.updateDependentProjects(refactoredMetadata, monitor);
		// update any server instance refs to the refactored project
		super.updateServerRefs(refactoredMetadata, null);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.refactor.operations.ProjectRefactorOperation#updateDependentEARProject(org.eclipse.jst.j2ee.internal.refactor.operations.ProjectRefactorMetadata, org.eclipse.jst.j2ee.internal.refactor.operations.ProjectRefactorMetadata)
	 */
	protected void updateDependentEARProject(final ProjectRefactorMetadata dependentMetadata, 
			final ProjectRefactorMetadata refactoredMetadata) throws ExecutionException {
		final IDataModel dataModel = createDataModel(dependentMetadata, refactoredMetadata,
				new UpdateDependentEARonDeleteProvider());
		dataModel.getDefaultOperation().execute(null, null);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.refactor.operations.ProjectRefactorOperation#updateDependentModuleProject(org.eclipse.jst.j2ee.internal.refactor.operations.ProjectRefactorMetadata, org.eclipse.jst.j2ee.internal.refactor.operations.ProjectRefactorMetadata)
	 */
	protected void updateDependentModuleProject(final ProjectRefactorMetadata dependentMetadata, 
			final ProjectRefactorMetadata refactoredMetadata) throws ExecutionException {
		final IDataModel dataModel = createDataModel(dependentMetadata, refactoredMetadata,
				new UpdateDependentModuleonDeleteProvider());
		dataModel.getDefaultOperation().execute(null, null);
	}

	private IDataModel createDataModel(final ProjectRefactorMetadata dependentMetadata, 
			final ProjectRefactorMetadata refactoredMetadata,
			final AbstractDataModelProvider provider) {
		final IDataModel dataModel = DataModelFactory.createDataModel(provider);
		dataModel.setProperty(PROJECT_METADATA, refactoredMetadata);
		dataModel.setProperty(DEPENDENT_PROJECT_METADATA, dependentMetadata);
		return dataModel;
	}
}
