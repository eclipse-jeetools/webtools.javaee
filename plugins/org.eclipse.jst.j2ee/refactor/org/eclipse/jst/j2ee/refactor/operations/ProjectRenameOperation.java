/*******************************************************************************
 * Copyright (c) 2005-2007 BEA Systems, Inc.
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
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup;
import org.eclipse.jst.j2ee.componentcore.EnterpriseArtifactEdit;
import org.eclipse.wst.common.componentcore.ArtifactEdit;
import org.eclipse.wst.common.componentcore.internal.ComponentcoreFactory;
import org.eclipse.wst.common.componentcore.internal.Property;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.internal.util.ComponentUtilities;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.server.core.util.ProjectModuleFactoryDelegate;

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
		final String oldProjectName = originalMetadata.getProjectName();
		final String newProjectName = refactoredMetadata.getProjectName();
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
            	if (prop.getName().equals("context-root") && prop.getValue().equals(oldProjectName)) {
            		propList.remove(i);
            		final Property newProp = ComponentcoreFactory.eINSTANCE.createProperty();
				    newProp.setName("context-root");
				    newProp.setValue(newProjectName);
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
		
		// if the deploy-name equals the old project name, update it in the module-specific deployment descriptor
		ArtifactEdit edit = null;
		try {
			edit = ComponentUtilities.getArtifactEditForWrite(refactoredMetadata.getVirtualComponent());
			if (edit == null || !(edit instanceof EnterpriseArtifactEdit)) {
				return;
			}
			final Resource resource = ((EnterpriseArtifactEdit) edit).getDeploymentDescriptorResource();
			if (resource != null) {
				final EList list = resource.getContents();
				if (list != null && !list.isEmpty()) {
					final EObject root = (EObject) list.get(0);
					if (root instanceof CompatibilityDescriptionGroup) {
						// if current display-name equals old project name, then change to new project name
						CompatibilityDescriptionGroup cdg = (CompatibilityDescriptionGroup) root;
						if (cdg.getDisplayName().equals(oldProjectName)) {
							cdg.setDisplayName(newProjectName);
						}				
					}
				}
			}
		} finally {
			if (edit != null) {
				edit.saveIfNecessary(null);
				edit.dispose();
			}
		}
	}
	
	/**
	 * Updates the metadata for dependent projects
	 * @throws ExecutionException
	 */
	protected void updateDependentProjects(final ProjectRefactorMetadata originalMetadata,
			final IProgressMonitor monitor) throws ExecutionException {
	    // If this is not an EAR, update metadata for dependent projects
        // (not performing any refactoring for projects that depend on EAR's right now)
        if (!originalMetadata.isEAR()) {
            super.updateDependentProjects(originalMetadata, monitor);
        }
		// update any server instance refs to the refactored project
		final ProjectRefactorMetadata refactoredMetadata = super.getProjectMetadata();
        ProjectModuleFactoryDelegate.handleGlobalProjectChange(refactoredMetadata.getProject(),
                (IResourceDelta) model.getProperty(ProjectRenameDataModelProvider.RESOURCE_DELTA));           
		super.updateServerRefs(originalMetadata, refactoredMetadata);
	}
	
	/**
	 * Updates the dependent EAR project to account for the renaming of the referenced project.
	 */
	protected void updateDependentEARProject(final ProjectRefactorMetadata dependentMetadata, 
			final ProjectRefactorMetadata originalMetadata) throws ExecutionException {
        if (OptionalRefactorHandler.getInstance().shouldRefactorDependentProjectOnRename(originalMetadata, dependentMetadata)) {
		    final IDataModel dataModel = createDataModel(dependentMetadata, originalMetadata,
		            new UpdateDependentEARonRenameProvider());
		    dataModel.getDefaultOperation().execute(null, null);
        }
	}
	
	/**
	 * Updates the dependent module project to account for the renaming of the referenced project.
	 */
	protected void updateDependentModuleProject(final ProjectRefactorMetadata dependentMetadata, 
			final ProjectRefactorMetadata originalMetadata) throws ExecutionException {
        if (OptionalRefactorHandler.getInstance().shouldRefactorDependentProjectOnRename(originalMetadata, dependentMetadata)) {
            final IDataModel dataModel = createDataModel(dependentMetadata, originalMetadata,
                    new UpdateDependentModuleonRenameProvider());
            dataModel.getDefaultOperation().execute(null, null);
        }
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
