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
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.j2ee.internal.common.operations.JARDependencyDataModelProperties;
import org.eclipse.jst.j2ee.internal.common.operations.JARDependencyDataModelProvider;
import org.eclipse.jst.j2ee.refactor.operations.ProjectRefactorMetadata.RefCachingVirtualComponent;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.operation.CreateReferenceComponentsDataModelProvider;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * Subclass of <code>UpdateDependentProjectOp</code> that updates a dependent
 * module project when a referenced project has been renamed.
 */
public class UpdateDependentModuleonRenameOp extends UpdateDependentProjectOp {

	public UpdateDependentModuleonRenameOp(final IDataModel model) {
		super(model);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.operations.IUndoableOperation#execute(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
	 */
	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		
		final ProjectRefactorMetadata dependentMetadata = (ProjectRefactorMetadata) model.getProperty(DEPENDENT_PROJECT_METADATA);
		final ProjectRefactorMetadata originalMetadata = (ProjectRefactorMetadata) model.getProperty(ORIGINAL_PROJECT_METADATA);
		final ProjectRefactorMetadata refactoredMetadata = (ProjectRefactorMetadata) model.getProperty(PROJECT_METADATA);
		
		// create IVirtualComponents for the dependent project and the refactored project
		final RefCachingVirtualComponent dependentComp = (RefCachingVirtualComponent) dependentMetadata.getVirtualComponent();
		final IVirtualComponent refactoredComp = refactoredMetadata.getVirtualComponent();
		
		// Does the dependent project have a .component reference on the refactored project?
		final IVirtualReference ref = hadReference(dependentMetadata, originalMetadata);
		final boolean webLibDep = hasWebLibDependency(ref);
	
		// first, remove the dependency on the old project name via the 
		// ProjectDeleteOperation
		UpdateDependentModuleonDeleteOp.removeModuleDependency(dependentMetadata, originalMetadata);
		
		// change to use not use caching
		dependentComp.setCaching(false);
		
		// add a reference to the renamed project
		if (refactoredComp != null) {
			final IDataModel refdm = DataModelFactory.createDataModel(new CreateReferenceComponentsDataModelProvider());
			final List targetCompList = (List) refdm.getProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST);
			targetCompList.add(refactoredComp);
			refdm.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, dependentComp);
			refdm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, targetCompList);
			if (webLibDep) {
				refdm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_DEPLOY_PATH,"/WEB-INF/lib"); //$NON-NLS-1$
			}
			CreateOptionalReferenceOp op = new CreateOptionalReferenceOp(refdm, ref != null);
			op.execute(monitor, null);
		}
	
		// update the manifest, unless this was a web library dependency
		if (!webLibDep) {
			UpdateDependentModuleonDeleteOp.updateManifestDependency(refactoredMetadata, dependentMetadata, false);
		}
			
        // update the JAR dependency data
        IDataModel dataModel = DataModelFactory.createDataModel(new JARDependencyDataModelProvider());
        dataModel.setProperty(JARDependencyDataModelProperties.PROJECT_NAME, dependentMetadata.getProjectName());
        dataModel.setProperty(JARDependencyDataModelProperties.REFERENCED_PROJECT_NAME, refactoredMetadata.getProjectName());
        dataModel.setIntProperty(JARDependencyDataModelProperties.JAR_MANIPULATION_TYPE, JARDependencyDataModelProperties.JAR_MANIPULATION_ADD);
        dataModel.getDefaultOperation().execute(monitor, null );
		return Status.OK_STATUS;
	}
	
}
