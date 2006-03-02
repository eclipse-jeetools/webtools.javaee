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

import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * Subclass of <code>UpdateDependentProjectOp</code> that updates a dependent
 * EAR project when one of the referenced projects has been deleted.
 */
public class UpdateDependentEARonDeleteOp extends UpdateDependentProjectOp {

	/**
	 * Creates a new refactoring operation for the specified data model.
	 * @param model The data model.
	 */
	public UpdateDependentEARonDeleteOp(final IDataModel model) {
		super(model);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.operations.IUndoableOperation#execute(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
	 */
	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		final ProjectRefactorMetadata dependentMetadata = (ProjectRefactorMetadata) model.getProperty(DEPENDENT_PROJECT_METADATA);
		final ProjectRefactorMetadata refactoredMetadata = (ProjectRefactorMetadata) model.getProperty(PROJECT_METADATA);
		removeEARDependency(dependentMetadata, refactoredMetadata);
		return Status.OK_STATUS;
	}
	
	/**
	 * Updates the dependent EAR project to account for the deletion of the referenced project.
	 */	
	protected static void removeEARDependency(final ProjectRefactorMetadata dependentMetadata, 
			final ProjectRefactorMetadata refactoredMetadata) throws ExecutionException {

		// create IVirtualComponents for the EAR and the refactored project
		final IVirtualComponent earComp = dependentMetadata.getVirtualComponent();
		final IVirtualComponent refactoredComp = refactoredMetadata.getVirtualComponent();
		final IProgressMonitor monitor = new NullProgressMonitor();
		
		// remove the dependency on the deleted project
		if (refactoredComp != null) {
			final IDataModel model = DataModelFactory.createDataModel(new RemoveDeletedComponentFromEARDataModelProvider(refactoredMetadata));
			model.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, earComp);
			final List modHandlesList = (List) model.getProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST);
			modHandlesList.add(refactoredComp);
			model.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, modHandlesList);
			model.getDefaultOperation().execute(monitor, null);
		}
	}
}
