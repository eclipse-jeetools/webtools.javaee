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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.j2ee.application.internal.operations.AddComponentToEnterpriseApplicationDataModelProvider;
import org.eclipse.jst.j2ee.project.facet.IJavaProjectMigrationDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.JavaProjectMigrationDataModelProvider;
import org.eclipse.jst.j2ee.refactor.RefactorResourceHandler;
import org.eclipse.jst.j2ee.refactor.operations.ProjectRefactorMetadata.RefCachingVirtualComponent;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.operation.CreateReferenceComponentsDataModelProvider;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * Subclass of <code>UpdateDependentProjectOp</code> that updates a dependent
 * EAR project when a referenced project has been renamed.
 */
public class UpdateDependentEARonRenameOp extends UpdateDependentProjectOp {

	public UpdateDependentEARonRenameOp(final IDataModel model) {
		super(model);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.operations.IUndoableOperation#execute(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
	 */
	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		
		final ProjectRefactorMetadata dependentMetadata = (ProjectRefactorMetadata) model.getProperty(DEPENDENT_PROJECT_METADATA);
		final ProjectRefactorMetadata originalMetadata = (ProjectRefactorMetadata) model.getProperty(ORIGINAL_PROJECT_METADATA);
		final ProjectRefactorMetadata refactoredMetadata = (ProjectRefactorMetadata) model.getProperty(PROJECT_METADATA);
		
		// create IVirtualComponents for the EAR and the refactored project
		final RefCachingVirtualComponent earComp = (RefCachingVirtualComponent) dependentMetadata.getVirtualComponent();
		final IVirtualComponent refactoredComp = refactoredMetadata.getVirtualComponent();

		if (refactoredComp == null) {
			return Status.OK_STATUS;
		}
		
		boolean hadReference = hadReference(dependentMetadata, originalMetadata) != null;
		
		// first, remove the dependency on the old project name via the 
		// ProjectDeleteOperation
		UpdateDependentEARonDeleteOp.removeEARDependency(dependentMetadata, originalMetadata);
		
		// change to use not use caching
		earComp.setCaching(false);
		
		// add back a dependency on the renamed project
		if (refactoredMetadata.hasModuleCoreNature()) {
			// the refactored project was a module project
			final IDataModel dm = DataModelFactory.createDataModel(new AddComponentToEnterpriseApplicationDataModelProvider());
			dm.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, earComp); 
			final List depList = new ArrayList();
			depList.add(refactoredComp);
			dm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, depList);
			dm.getDefaultOperation().execute(monitor, null);
		} else if (refactoredMetadata.hasJavaNature()) {
			// the refactored project was a plain java project
			final IDataModel migrationdm = DataModelFactory.createDataModel(new JavaProjectMigrationDataModelProvider());
			migrationdm.setProperty(IJavaProjectMigrationDataModelProperties.PROJECT_NAME, refactoredMetadata.getProjectName());
			migrationdm.getDefaultOperation().execute(new NullProgressMonitor(), null);
			final IDataModel refdm = DataModelFactory.createDataModel(new CreateReferenceComponentsDataModelProvider());
			final List targetCompList = (List) refdm.getProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST);
			targetCompList.add(refactoredComp);
			refdm.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, earComp);
			refdm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, targetCompList);
			CreateOptionalReferenceOp op = new CreateOptionalReferenceOp(refdm, hadReference);
			op.execute(monitor, null);
		} else {
			throw new ExecutionException(RefactorResourceHandler.getString("missing_natures"), null);
		}
		return Status.OK_STATUS;
	}
	
}
