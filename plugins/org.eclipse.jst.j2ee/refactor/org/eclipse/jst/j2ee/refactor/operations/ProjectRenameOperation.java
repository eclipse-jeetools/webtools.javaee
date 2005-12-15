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
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.wst.common.componentcore.internal.ComponentcoreFactory;
import org.eclipse.wst.common.componentcore.internal.Property;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class ProjectRenameOperation extends ProjectRefactorOperation {

	public ProjectRenameOperation(final IDataModel model) {
		super(model);
	}
	
	/**
	 * Override to return the pre-rename metadata.
	 */
	protected ProjectRefactorMetadata getProjectMetadata() {
		return (ProjectRefactorMetadata) model.getProperty(ProjectRenameDataModelProvider.ORIGINAL_PROJECT_METADATA);
	}
	
	/**
	 * Updates the project's own metadata.
	 */
	protected void updateProject(final ProjectRefactorMetadata originalMetadata) 
		throws ExecutionException {
		
		// Update the project's .component file
		final ProjectRefactorMetadata refactoredMetadata = super.getProjectMetadata();
		StructureEdit core = null;
		try {
			core = StructureEdit.getStructureEditForWrite(refactoredMetadata.getProject());
			WorkbenchComponent component = core.getComponent();
			// if the deploy-name had been set to the old project name, update it to 
			// the new project name
			if (component.getName().equals(originalMetadata.getProjectName())) {
				component.setName(refactoredMetadata.getProjectName());
			}
			// if there is a context-root property that had been set to the old project name, update it to 
			// the new project name
			List propList = component.getProperties();
            for (int i = 0; i < propList.size(); i++) {
            	final Property prop = (Property) propList.get(i);
            	if (prop.getName().equals("context-root") && prop.getValue().equals(originalMetadata.getProjectName())) {
            		propList.remove(i);
            		final Property newProp = ComponentcoreFactory.eINSTANCE.createProperty();
				    newProp.setName("context-root");
				    newProp.setValue(refactoredMetadata.getProjectName());
				    propList.add(newProp);
				    break;
            	}
            }
		} finally {
			if(core != null) {
				core.saveIfNecessary(null);
				core.dispose();
			}
		}	
	}
	
	/**
	 * Updates the metadata for dependent projects
	 * @throws ExecutionException
	 */
	protected void updateDependentProjects(final ProjectRefactorMetadata originalMetadata,
			final IProgressMonitor monitor) throws ExecutionException {
		super.updateDependentProjects(originalMetadata, monitor);
		// update any server instance refs to the refactored project
		final ProjectRefactorMetadata refactoredMetadata = super.getProjectMetadata();
		super.updateServerRefs(originalMetadata, refactoredMetadata);
	}
	
	/**
	 * Updates the dependent EAR project to account for the renaming of the referenced project.
	 */
	protected void updateDependentEARProject(final ProjectRefactorMetadata dependentMetadata, 
			final ProjectRefactorMetadata originalMetadata) throws ExecutionException {
		final IDataModel dataModel = createDataModel(dependentMetadata, originalMetadata,
				new UpdateDependentEARonRenameProvider());
		dataModel.getDefaultOperation().execute(null, null);
	}
	
	/**
	 * Updates the dependent module project to account for the renaming of the referenced project.
	 */
	protected void updateDependentModuleProject(final ProjectRefactorMetadata dependentMetadata, 
			final ProjectRefactorMetadata originalMetadata) throws ExecutionException {
		final IDataModel dataModel = createDataModel(dependentMetadata, originalMetadata,
				new UpdateDependentModuleonRenameProvider());
		dataModel.getDefaultOperation().execute(null, null);
	}
	
	private IDataModel createDataModel(final ProjectRefactorMetadata dependentMetadata, 
			final ProjectRefactorMetadata originalMetadata,
			final AbstractDataModelProvider provider) {
		final ProjectRefactorMetadata refactoredMetadata = super.getProjectMetadata();
		final IDataModel dataModel = DataModelFactory.createDataModel(provider);
		dataModel.setProperty(PROJECT_METADATA, refactoredMetadata);
		dataModel.setProperty(DEPENDENT_PROJECT_METADATA, dependentMetadata);
		dataModel.setProperty(ORIGINAL_PROJECT_METADATA, originalMetadata);
		return dataModel;
	}
	
}
