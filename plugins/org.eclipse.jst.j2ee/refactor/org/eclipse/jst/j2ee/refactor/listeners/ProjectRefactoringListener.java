/*******************************************************************************
 * Copyright (c) 2005 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 * rfrost@bea.com - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.refactor.listeners;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.project.EarUtilities;
import org.eclipse.jst.j2ee.refactor.RefactorResourceHandler;
import org.eclipse.jst.j2ee.refactor.operations.OptionalRefactorHandler;
import org.eclipse.jst.j2ee.refactor.operations.ProjectRefactorMetadata;
import org.eclipse.jst.j2ee.refactor.operations.ProjectRefactoringDataModelProvider;
import org.eclipse.jst.j2ee.refactor.operations.ProjectRenameDataModelProvider;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.internal.builder.IDependencyGraph;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

/**
 * Listens for project rename/delete events and, if the project had the
 * ModuleCore nature, executes the appropriate logic to update
 * project references.
  */
public final class ProjectRefactoringListener implements IResourceChangeListener, IResourceDeltaVisitor {
	
	/**
	 * Name of the Job family in which all project rename/delete refactoring jobs belong.
	 */
	public static final String PROJECT_REFACTORING_JOB_FAMILY =  "org.eclipse.jst.j2ee.refactor.project"; //$NON-NLS-1$
	
	/*
	 * Map from name of deleted project to ProjectRefactorMetadata instances.
	 */
	private final Map deletedProjectMetadata = new HashMap();
	
	/**
	 * Maintains a cache of project depencencies;
	 */
	public ProjectRefactoringListener() {
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
	 */
	@Override
	public void resourceChanged(final IResourceChangeEvent event) {
		// need to capture PRE_DELETE events so that metadata about the
		// deleted project can be collected and cached
		try {
			if (event.getType() == IResourceChangeEvent.PRE_DELETE) {
				// for now, only dependencies on ModuleCoreNature projects
				final IProject project = (IProject) event.getResource();
                // ensure project is accessible and has both module core and faceted natures
				if (ModuleCoreNature.isFlexibleProject(project)
                        && ProjectFacetsManager.create(project) != null) {
					cacheDeletedProjectMetadata(project);
				}
			} else {
				event.getDelta().accept(this);
			}
		} catch (CoreException ce) {
			J2EEPlugin.logError(ce);
		}
	}
	
	private synchronized void cacheDeletedProjectMetadata(final IProject project) {
		final ProjectRefactorMetadata metadata = new ProjectRefactorMetadata(project, ProjectRefactorMetadata.REFERER_CACHING);
		// precompute the metadata while the project still exists
		metadata.computeMetadata();
		metadata.computeServers();
		//the list of reference projects that have a .settings/org.eclipse.wst.common.component entry
		//typically these will be EAR projects
		Set<IProject> dotComponentReferences = IDependencyGraph.INSTANCE.getReferencingComponents(project);
		Set<IProject> modulesAlreadyChecked = new HashSet<IProject>();
		modulesAlreadyChecked.add(project);
		modulesAlreadyChecked.addAll(dotComponentReferences);
		Set<IProject> allReferences = new HashSet <IProject>();
		for(IProject earProject: dotComponentReferences){
			allReferences.add(earProject);
			if(EarUtilities.isEARProject(earProject)){
				//for each ear, get the modules, and 
				//for each module see if it has a reference back to the project being deleted
				IVirtualComponent earComponent = ComponentCore.createComponent(earProject);
				IVirtualReference [] earRefs = earComponent.getReferences();
				for(IVirtualReference earRef : earRefs){
					IVirtualComponent moduleComponent = earRef.getReferencedComponent();
					IProject moduleProject = moduleComponent.getProject();
					if(!moduleComponent.isBinary() && !modulesAlreadyChecked.contains(moduleProject)){
						modulesAlreadyChecked.add(moduleProject);
						IVirtualReference [] moduleRefs = moduleComponent.getReferences();
						for (IVirtualReference moduleRef : moduleRefs) {
							IVirtualComponent manifestComponent = moduleRef.getReferencedComponent();
							if(manifestComponent.getProject().equals(project)){
								allReferences.add(moduleProject);
								break;
							}
						}
					}
				}
			}
		}
		
		IProject [] referencingProjects = allReferences.toArray(new IProject[allReferences.size()]);
		metadata.computeDependentMetadata(ProjectRefactorMetadata.REF_CACHING, referencingProjects);
		deletedProjectMetadata.put(project.getName(), metadata);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.resources.IResourceDeltaVisitor#visit(org.eclipse.core.resources.IResourceDelta)
	 */
	@Override
	public boolean visit(final IResourceDelta delta) throws CoreException {
		final IResource resource = delta.getResource();
		if (resource instanceof IWorkspaceRoot) {
			// delta is at the workspace root so keep going
			return true;
		} else if (resource instanceof IProject) {
			processProjectDelta((IProject) resource, delta);
		}
		return false;
	}

	/*
	 * Process the project delta in a sync block.
	 */
	private synchronized void processProjectDelta(final IProject project, final IResourceDelta delta) throws CoreException {
		final int kind = delta.getKind();
		final int flags = delta.getFlags();

		if (kind == IResourceDelta.ADDED && hasRenamedAddedFlags(flags)) { // was renamed
			// get the original name
			final String originalName = delta.getMovedFromPath().lastSegment();
			//Logger.getLogger().logInfo("Added event for " + originalName + " with flags " + flags);
			// we get PRE_DELETE events on rename so retrieve this
			ProjectRefactorMetadata originalMetadata = (ProjectRefactorMetadata) deletedProjectMetadata.remove(originalName);
			// get the metadata for the new project
			final ProjectRefactorMetadata newMetadata = new ProjectRefactorMetadata(project);
			// note: only projects with ModuleCoreNature will have cached metadata
			if (originalMetadata != null && OptionalRefactorHandler.getInstance().shouldRefactorRenamedProject(originalMetadata)) {
				newMetadata.computeMetadata(originalMetadata.getProject());
				processRename(originalMetadata, newMetadata, delta);
			} 
		} 
	}
	
	/*
	 * Determines if the added project was renamed based on the IResourceDelta flags 
	 */
	private boolean hasRenamedAddedFlags(final int flags) {
		if ((flags & IResourceDelta.DESCRIPTION) > 0
			&& (flags & IResourceDelta.MOVED_FROM) > 0) {
			return true;
		}
		return false;
	}
	
	/*
	 * Processes the renaming of a project.
	 */
	private void processRename(final ProjectRefactorMetadata originalMetadata, final ProjectRefactorMetadata newMetadata, final IResourceDelta delta) {
		WorkspaceJob job = new WorkspaceJob(RefactorMessages.ProjectRefactoringListener_J2EE_Project_Rename_) {
			@Override
			public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
				final IDataModel dataModel = DataModelFactory.createDataModel(new ProjectRenameDataModelProvider());
				dataModel.setProperty(ProjectRefactoringDataModelProvider.PROJECT_METADATA, newMetadata);
                dataModel.setProperty(ProjectRenameDataModelProvider.ORIGINAL_PROJECT_METADATA, originalMetadata);
                dataModel.setProperty(ProjectRenameDataModelProvider.RESOURCE_DELTA, delta);                
				try {
					dataModel.getDefaultOperation().execute(monitor, null);
				} catch (Exception e) {
					final String msg = RefactorResourceHandler.getString("error_updating_project_on_rename", new Object[]{originalMetadata.getProjectName()}); //$NON-NLS-1$
					J2EEPlugin.logError(msg);
					J2EEPlugin.logError(e);
					return new Status(Status.ERROR, J2EEPlugin.PLUGIN_ID, 0, msg, e);
				}				
				return Status.OK_STATUS;
			}
			
			@Override
			public boolean belongsTo(final Object family) {
				return PROJECT_REFACTORING_JOB_FAMILY.equals(family);
			}
		};
		// XXX note: might want to consider switching to a MultiRule for optimization
		job.setRule(ResourcesPlugin.getWorkspace().getRoot());
		job.schedule();
	}	
}