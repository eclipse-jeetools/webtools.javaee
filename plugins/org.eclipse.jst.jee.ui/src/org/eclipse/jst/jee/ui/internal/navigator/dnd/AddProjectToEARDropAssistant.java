/***********************************************************************
 * Copyright (c) 2008, 2023 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 *     IBM - bug 281382 clean up
 ***********************************************************************/
package org.eclipse.jst.jee.ui.internal.navigator.dnd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.internal.core.JarPackageFragmentRoot;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.application.internal.operations.AddComponentToEnterpriseApplicationDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.IAddComponentToEnterpriseApplicationDataModelProperties;
import org.eclipse.jst.j2ee.application.internal.operations.RemoveComponentFromEnterpriseApplicationDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestDataModelProperties;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestDataModelProvider;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.componentcore.J2EEModuleVirtualArchiveComponent;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.dialogs.DependencyConflictResolveDialog;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.provider.J2EEItemProvider;
import org.eclipse.jst.j2ee.navigator.internal.plugin.J2EENavigatorPlugin;
import org.eclipse.jst.j2ee.project.EarUtilities;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.j2ee.project.facet.EARFacetUtils;
import org.eclipse.jst.jee.ui.internal.Messages;
import org.eclipse.jst.jee.ui.internal.navigator.ear.AbstractEarNode;
import org.eclipse.jst.jee.ui.internal.navigator.ear.BundledNode;
import org.eclipse.jst.jee.ui.internal.navigator.ear.GroupEARProvider;
import org.eclipse.jst.jee.ui.plugin.JEEUIPlugin;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonDropAdapter;
import org.eclipse.ui.navigator.CommonDropAdapterAssistant;
import org.eclipse.ui.part.PluginTransfer;
import org.eclipse.ui.progress.IProgressService;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.operation.RemoveReferenceComponentsDataModelProvider;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualArchiveComponent;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

/**
 * Handles drop of JEE resources on EAR project thus adding them as modules.
 * 
 * @author Dimitar Giormov
 * 
 */
public class AddProjectToEARDropAssistant extends CommonDropAdapterAssistant {

	private static final Class IPROJECT_CLASS = IProject.class;
	private IVirtualComponent earComponent = null;
	private IProject earProject = null;
	private String libDir = null;
	private boolean resolveConflicts = false;

	public AddProjectToEARDropAssistant() {
		super();
	}

	@Override
	public boolean isSupportedType(TransferData aTransferType) {
		return LocalSelectionTransfer.getTransfer().isSupportedType(aTransferType);
	}

	@Override
	public IStatus handleDrop(CommonDropAdapter aDropAdapter, DropTargetEvent aDropTargetEvent, final Object aTarget) {
		if (LocalSelectionTransfer.getTransfer().isSupportedType(aDropAdapter.getCurrentTransfer())) {
			final IStructuredSelection selection = (IStructuredSelection) LocalSelectionTransfer.getTransfer().getSelection();
			IProgressService service = PlatformUI.getWorkbench().getProgressService();
			resolveConflicts = false;
			for (Iterator selectionIterator = selection.iterator(); selectionIterator.hasNext();) {
				Object sourceObject = selectionIterator.next();
				if (sourceObject instanceof IVirtualReference) {
					IVirtualReference reference = (IVirtualReference) sourceObject;
					/*
					 * If a reference is moved from the root to the lib
					 * directory check for conflicts with projects referenced by
					 * the ear referencing the same lib
					 */
					IPath libDirPath = null;
					if(libDir != null)
						libDirPath = new Path(libDir).makeRelative();
					if (libDir.length() > 0 && libDirPath != null && !libDirPath.equals(reference.getRuntimePath().makeRelative())) {
						if (hasConflictingProjectInMetaInf(reference)) {
							DependencyConflictResolveDialog dlg = new DependencyConflictResolveDialog(getShell(), DependencyConflictResolveDialog.DLG_TYPE_2);
							if (dlg.open() == DependencyConflictResolveDialog.BTN_ID_CANCEL) {
								return Status.CANCEL_STATUS;
							}
							resolveConflicts = true;
						}
					}
				} else {
					if (libDir.length() > 0){
						IProject projectToAdd = getProject(sourceObject);
						if (hasConflictingProjectInMetaInf(projectToAdd)) {
							DependencyConflictResolveDialog dlg = new DependencyConflictResolveDialog(getShell(), DependencyConflictResolveDialog.DLG_TYPE_2);
							if (dlg.open() == DependencyConflictResolveDialog.BTN_ID_CANCEL) {
								return Status.CANCEL_STATUS;
							}
							resolveConflicts = true;
						}
					}
				}
			}
			Job addProjectToEarJob = new Job(getJobTitle(earComponent)) {
				@Override
				protected IStatus run(IProgressMonitor monitor) {

					IStatus status = null;

					List<IVirtualComponent> componentsToAdd = new ArrayList<IVirtualComponent>();
					List<IVirtualComponent> componentsToRemove = new ArrayList<IVirtualComponent>();

					for (Iterator selectionIterator = selection.iterator(); selectionIterator.hasNext();) {
						Object sourceObject = selectionIterator.next();

						if (sourceObject instanceof JarPackageFragmentRoot) {
							JarPackageFragmentRoot jarPackageFragment = (JarPackageFragmentRoot) sourceObject;

							String type = VirtualArchiveComponent.LIBARCHIVETYPE + IPath.SEPARATOR;
							IVirtualComponent archive = ComponentCore.createArchiveComponent(earComponent.getProject(), type + jarPackageFragment.getPath().toString());
							componentsToAdd.add(archive);
						} else if (sourceObject instanceof IVirtualReference) {
							IVirtualReference reference = (IVirtualReference) sourceObject;
							if (resolveConflicts) {
								status = resolveConflicts(reference.getReferencedComponent());
								if (!status.isOK()) {
									return status;
								}
							}
							IVirtualComponent archive = reference.getReferencedComponent();
							componentsToRemove.add(archive);
							status = removeComponentsFromEar(componentsToRemove, reference.getRuntimePath().toString());
							if (!status.isOK())
								return status;
							componentsToAdd.add(archive);
						} else {
							IProject projectToAdd = getProject(sourceObject);
							if (resolveConflicts) {
								status = resolveConflicts(ComponentCore.createComponent(projectToAdd));
								if (!status.isOK()) {
									return status;
								}
							}
							componentsToAdd.add(ComponentCore.createComponent(projectToAdd));
						}
					}

					if (!componentsToAdd.isEmpty()) {
						status = addComponentsToEar(componentsToAdd, libDir);
						if (!status.isOK())
							return status;
					}

					return status;
				}

			};
			service.showInDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), addProjectToEarJob);
			addProjectToEarJob.setRule(ResourcesPlugin.getWorkspace().getRoot());
			addProjectToEarJob.schedule();
		}
		return Status.OK_STATUS;
	}

	@Override
	public IStatus validateDrop(Object target, int operation, TransferData transferType) {
		libDir = ""; //$NON-NLS-1$
		if (LocalSelectionTransfer.getTransfer().isSupportedType(transferType) || PluginTransfer.getInstance().isSupportedType(transferType)) {
			ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();
			if (selection != null && !selection.isEmpty() && (selection instanceof IStructuredSelection)) {
				IStructuredSelection structuredSelection = (IStructuredSelection) selection;
				earProject = getProject(target);
				if (earProject == null) {
					return Status.CANCEL_STATUS;
				}
				earComponent = ComponentCore.createComponent(earProject);
				int earVersion = getEarVersion(earProject);
				if (target instanceof BundledNode && ((BundledNode) target).getBundledLibsDirectoryNode() == null) {
					libDir = EarUtilities.getEARLibDir(earComponent);
				}

				IStatus status = null;
				for (Iterator iterator = structuredSelection.iterator(); iterator.hasNext();) {
					Object next = iterator.next();
					
					//check if library is physically present in the Ear path
					if (IVirtualReference.class.isInstance(next)){
						IVirtualComponent referencedComponent = ((IVirtualReference)next).getReferencedComponent();
						if (referencedComponent.isBinary() && isPhysicallyAdded(referencedComponent)) {
							return Status.CANCEL_STATUS;
						}
					}

					if (next instanceof IVirtualReference) {
						return Status.OK_STATUS;
					}

					/*
					 * Check if we try to add a JarPackageFragment that is
					 * already referenced
					 */
					if (next instanceof JarPackageFragmentRoot) {
						JarPackageFragmentRoot jar = (JarPackageFragmentRoot) next;
						for (IVirtualReference reference : earComponent.getReferences()) {
							if (reference.getArchiveName().equals(jar.getElementName())) {
								return Status.CANCEL_STATUS;
							}
						}
						return Status.OK_STATUS;
					}

					/*
					 * Check if we try to add a module that is already
					 * referenced by the ear but at the same time allow to
					 * change its location from the root to the lib directory
					 * and the opposite.
					 */
					IProject projectToAdd = getProject(next);
					hasConflictingProjectInMetaInf(ComponentCore.createComponent(projectToAdd));
					try {
						for (IProject referencedProject : earProject.getReferencedProjects()) {
							if (referencedProject == projectToAdd) {
								return Status.CANCEL_STATUS;
							}
						}
					} catch (CoreException e) {
						String msg = e.getMessage() != null ? e.getMessage() : e.toString();
						status = J2EENavigatorPlugin.createErrorStatus(0, msg, e);
					}

					if ((status = validateProjectMayBeAdded(earProject, projectToAdd, earVersion)).isOK())
						return status;
				}
			}

		}
		return Status.CANCEL_STATUS;
	}
	
	private boolean isPhysicallyAdded(IVirtualComponent component) {
		IFile f = component.getAdapter(IFile.class);
		return f == null ? false : true;
	}	

	/**
	 * @param target
	 */
	private IStatus validateProjectMayBeAdded(IProject earProject, IProject projectToAdd, int earVersion) {
		if (earProject == null || projectToAdd == null || earVersion < 0)
			return J2EENavigatorPlugin.createErrorStatus(0, Messages.AddProjectToEARDropAssistant_Could_not_add_module_to_Enterprise_, null);
		else if (!earProject.isAccessible()) {
			return J2EENavigatorPlugin.createErrorStatus(0, NLS.bind(Messages.AddProjectToEARDropAssistant_The_project_0_cannot_be_accesse_, earProject.getName()), null);
		} else if (!projectToAdd.isAccessible()) {
			return J2EENavigatorPlugin.createErrorStatus(0, Messages.AddProjectToEARDropAssistant_The_dragged_project_cannot_be_added_, null);
		}

		IStatus isValid = validateProjectToAdd(projectToAdd, earVersion);
		if (!isValid.isOK()) {
			return isValid;
		}
		
		return Status.OK_STATUS;
	}

	/**
	 * 
	 * @return -1 on error
	 */
	protected final int getEarVersion(IProject earProject) {
		int earVersion = -1;
		IFacetedProject facetedProject = null;
		try {
			facetedProject = ProjectFacetsManager.create(earProject);

			if (facetedProject != null) {
				double double1 = Double.parseDouble(facetedProject.getProjectFacetVersion(EARFacetUtils.EAR_FACET).getVersionString());
				earVersion = (int) (double1 * 10);
			} else {
				JEEUIPlugin.logError("Could not acquire model elements for project \"" + earProject.getName() + "\".", null); //$NON-NLS-1$ //$NON-NLS-2$
			}
		} catch (CoreException e1) {
			String msg = e1.getMessage() != null ? e1.getMessage() : e1.toString();
			JEEUIPlugin.logError(msg, e1);
		}

		return earVersion;
	}

	/**
	 * @param facetedProject
	 * @return
	 */
	protected final boolean hasEarFacet(IProject project) {
		IFacetedProject facetedProject = null;
		try {
			facetedProject = ProjectFacetsManager.create(project);
		} catch (CoreException e1) {
			String msg = e1.getMessage() != null ? e1.getMessage() : e1.toString();
			JEEUIPlugin.logError(msg, e1);
		}
		return facetedProject != null && facetedProject.hasProjectFacet(EARFacetUtils.EAR_FACET);
	}

	protected final String calculateValidProjectName(final String originalName) {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		String validName = originalName;
		int count = 1;
		while (root.getProject(validName).exists()) {
			validName = originalName + count++;
		}
		return validName;
	}

	protected String getJobTitle(IVirtualComponent earComponent) {
		return NLS.bind(Messages.AddModuleToEarDropAssistant_Adding_module_to_ea_, earComponent.getName());
	}

	protected IStatus validateProjectToAdd(IProject projectToAdd, int earVersion) {
		try {
			// check if the project to add is not an EAR itself
			IFacetedProject facetedProject = ProjectFacetsManager.create(projectToAdd);
			if (facetedProject == null) {
				facetedProject = ProjectFacetsManager.create(projectToAdd, true, new NullProgressMonitor());
				IProjectFacetVersion javafacetversion = ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_JAVA).getDefaultVersion();
				facetedProject.installProjectFacet(javafacetversion, null, null);
				IProjectFacetVersion utilityfacetversion = ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_UTILITY_MODULE).getDefaultVersion();
				facetedProject.installProjectFacet(utilityfacetversion, null, null);
			}
			if (facetedProject.hasProjectFacet(EARFacetUtils.EAR_FACET))
				return Status.CANCEL_STATUS;

			// check if the project to add is with Java EE version equal or
			// lesser than that of the EAR
			String verStr = J2EEProjectUtilities.getJ2EEProjectVersion(projectToAdd);
			if (verStr != null) {
				int version;
				if (JavaEEProjectUtilities.isApplicationClientProject(projectToAdd))
					version = J2EEVersionUtil.convertAppClientVersionStringToJ2EEVersionID(verStr);
				else if (JavaEEProjectUtilities.isEJBProject(projectToAdd))
					version = J2EEVersionUtil.convertEJBVersionStringToJ2EEVersionID(verStr);
				else if (JavaEEProjectUtilities.isDynamicWebProject(projectToAdd))
					version = J2EEVersionUtil.convertWebVersionStringToJ2EEVersionID(verStr);
				else if (JavaEEProjectUtilities.isJCAProject(projectToAdd))
					version = J2EEVersionUtil.convertConnectorVersionStringToJ2EEVersionID(verStr);
				else
					version = J2EEVersionUtil.convertVersionStringToInt(verStr);

				if (version > earVersion)
					return Status.CANCEL_STATUS;
			}
		} catch (CoreException e) {
			String msg = e.getMessage() != null ? e.getMessage() : e.toString();
			return J2EENavigatorPlugin.createErrorStatus(0, msg, e);
		}
		return Status.OK_STATUS;

	}

	protected static IProject getProject(Object element) {
		if (element == null)
			return null;
		IProject project = null;
		if (element instanceof IAdaptable)
			project = (IProject) ((IAdaptable) element).getAdapter(IPROJECT_CLASS);
		else
			project = (IProject) Platform.getAdapterManager().getAdapter(element, IPROJECT_CLASS);

		if (element instanceof AbstractEarNode) {
			project = ((AbstractEarNode) element).getEarProject();
		}

		if (element instanceof GroupEARProvider) {
			project = ((GroupEARProvider) element).getProject();
		}

		if (element instanceof IVirtualReference) {
			project = ((IVirtualReference) element).getReferencedComponent().getProject();
		}

		if (project == null) {
			if (element instanceof EObject) {
				project = ProjectUtilities.getProject((EObject) element);
			} else if (element instanceof J2EEItemProvider) {
				IFile associatedFile = ((J2EEItemProvider) element).getAssociatedFile();
				if (associatedFile != null)
					project = associatedFile.getProject();
			}
		}
		return project;
	}

	protected static String getDefaultURI(IVirtualComponent component) {
		IProject project = component.getProject();
		String name = component.getName();

		if (JavaEEProjectUtilities.isDynamicWebProject(project)) {
			name += IJ2EEModuleConstants.WAR_EXT;
		} else if (JavaEEProjectUtilities.isEARProject(project)) {
			name += IJ2EEModuleConstants.EAR_EXT;
		} else if (JavaEEProjectUtilities.isJCAProject(project)) {
			name += IJ2EEModuleConstants.RAR_EXT;
		} else {
			name += IJ2EEModuleConstants.JAR_EXT;
		}
		return name;
	}

	private boolean hasConflictingProjectInMetaInf(Object lib) {
		IProject libProj = null; 
			if (lib instanceof IProject) {
				libProj = (IProject) lib; 
			} else if (lib instanceof IVirtualComponent) {
				libProj = ((IVirtualComponent) lib).getProject(); 
			} else if (lib instanceof IVirtualReference){
				libProj = ((IVirtualReference) lib).getReferencedComponent().getProject();
			}
		if(libProj == null){
			return false;
		}
		IProject earProject = earComponent.getProject();
		try {
			IProject[] earRefProjects = earProject.getReferencedProjects();
			for (int i = 0; i < earRefProjects.length; i++) {
				if (!JavaEEProjectUtilities.isEARProject(earRefProjects[i]) && !earRefProjects[i].equals(libProj)) {
					IVirtualComponent cmp1 = ComponentCore.createComponent(earRefProjects[i]);
					if (cmp1 == null){
						continue;
					}
					IVirtualReference[] refs = cmp1.getReferences();
					for (int j = 0; j < refs.length; j++) {
						if (refs[j].getReferencedComponent().getProject().equals(libProj))
							return true;
					}
				}
			}
			return false;
		} catch (CoreException ce) {
		}
		return false;
	}

	protected IStatus addComponentsToEar(List<IVirtualComponent> components, String libDir) {
		IStatus status = null;
		IDataModel datamodel = DataModelFactory.createDataModel(new AddComponentToEnterpriseApplicationDataModelProvider());

		Map componentToURIMap = new HashMap();
		IVirtualComponent moduleComponent = null;
		for (Iterator itr = components.iterator(); itr.hasNext();) {
			moduleComponent = (IVirtualComponent) itr.next();
			componentToURIMap.put(moduleComponent, getDefaultURI(moduleComponent));
		}
		datamodel.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, earComponent);
		datamodel.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, components);
		datamodel.setProperty(IAddComponentToEnterpriseApplicationDataModelProperties.TARGET_COMPONENTS_TO_URI_MAP, componentToURIMap);
		String libDirPath = libDir; 
		if(libDir != null && libDir.length() > 0)
			libDirPath = new Path(libDir).makeAbsolute().toString();
		datamodel.setProperty(IAddComponentToEnterpriseApplicationDataModelProperties.TARGET_COMPONENTS_DEPLOY_PATH, libDirPath);
		try {
			return datamodel.getDefaultOperation().execute(new NullProgressMonitor(), null);
		} catch (ExecutionException e) {
			String msg = e.getMessage() != null ? e.getMessage() : e.toString();
			JEEUIPlugin.logError(msg, e);
		}
		return status;
	}

	protected IStatus removeComponentsFromEar(List<IVirtualComponent> components, String libDir) {
		IStatus status = null;
		IDataModel datamodel = DataModelFactory.createDataModel(new RemoveComponentFromEnterpriseApplicationDataModelProvider());
		datamodel.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, earComponent);
		datamodel.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, components);
		try {
			status = datamodel.getDefaultOperation().execute(new NullProgressMonitor(), null);
			if (!status.isOK()) {
				return status;
			}
		} catch (ExecutionException e) {
			String msg = e.getMessage() != null ? e.getMessage() : e.toString();
			JEEUIPlugin.logError(msg, e);
		}
		return removeReferences(components, libDir, earComponent);
	}

	protected IStatus removeReferences(List<IVirtualComponent> components, String libDir, IVirtualComponent sourceComponent) {
		IVirtualReference[] refs = sourceComponent.getReferences();
		for (IVirtualReference ref : refs) {
			ref.getRuntimePath();
		}
		Map componentToURIMap = new HashMap();
		for (Iterator itr = components.iterator(); itr.hasNext();) {
			IVirtualComponent moduleComponent = (IVirtualComponent) itr.next();
			componentToURIMap.put(moduleComponent, getDefaultURI(moduleComponent));
		}
		final IDataModel model = DataModelFactory.createDataModel(new RemoveReferenceComponentsDataModelProvider());
		model.setProperty(IAddComponentToEnterpriseApplicationDataModelProperties.TARGET_COMPONENTS_TO_URI_MAP, componentToURIMap);
		model.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, sourceComponent);
		model.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, components);
		if (libDir.length() > 0) {
			model.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_DEPLOY_PATH, new Path(libDir).makeAbsolute().toString());
		}
		try {
			return model.getDefaultOperation().execute(null, null);
		} catch (ExecutionException e) {
			String msg = e.getMessage() != null ? e.getMessage() : e.toString();
			JEEUIPlugin.logError(msg, e);
		}
		return Status.CANCEL_STATUS;
	}

	protected IStatus resolveConflicts(IVirtualComponent component) {
		IProject earProject = earComponent.getProject();
		try {
			IProject[] earRefProjects = earProject.getReferencedProjects();
			for (int i = 0; i < earRefProjects.length; i++) {
				if (!JavaEEProjectUtilities.isEARProject(earRefProjects[i])) {
					IVirtualComponent cmp1 = ComponentCore.createComponent(earRefProjects[i]);
					if(cmp1 == null){
						continue;
					}
					IVirtualReference[] refs = cmp1.getReferences();
					for (int j = 0; j < refs.length; j++) {
						if (refs[j].getReferencedComponent().equals(component)) {
							List<IVirtualComponent> components = new ArrayList<IVirtualComponent>();
							components.add(refs[j].getReferencedComponent());
							IStatus status = removeReferences(components, refs[j].getRuntimePath().toString(), refs[j].getEnclosingComponent());
							if (!status.isOK()) {
								return status;
							}
							try {
								removeManifestDependency(refs[j].getEnclosingComponent(), refs[j].getReferencedComponent());
							} catch (ExecutionException e) {
								String msg = e.getMessage() != null ? e.getMessage() : e.toString();
								JEEUIPlugin.logError(msg, e);
							}
						}
					}
				}
			}
		} catch (CoreException ce) {
		}
		// return false;
		return Status.OK_STATUS;
	}

	private void removeManifestDependency(final IVirtualComponent source, final IVirtualComponent target) throws ExecutionException {
		final String sourceProjName = source.getProject().getName();
		String targetProjName;
		if (target instanceof J2EEModuleVirtualArchiveComponent) {
			targetProjName = ((J2EEModuleVirtualArchiveComponent) target).getName();
			String[] pathSegments = targetProjName.split("" + IPath.SEPARATOR); //$NON-NLS-1$
			targetProjName = pathSegments[pathSegments.length - 1];
		} else {
			targetProjName = target.getProject().getName();
		}
		final IProgressMonitor monitor = new NullProgressMonitor();
		final IFile manifestmf = J2EEProjectUtilities.getManifestFile(source.getProject());
		final ArchiveManifest mf = J2EEProjectUtilities.readManifest(source.getProject());
		if (mf == null)
			return;
		final IDataModel updateManifestDataModel = DataModelFactory.createDataModel(new UpdateManifestDataModelProvider());
		updateManifestDataModel.setProperty(UpdateManifestDataModelProperties.PROJECT_NAME, sourceProjName);
		updateManifestDataModel.setBooleanProperty(UpdateManifestDataModelProperties.MERGE, false);
		updateManifestDataModel.setProperty(UpdateManifestDataModelProperties.MANIFEST_FILE, manifestmf);
		String[] cp = mf.getClassPathTokenized();
		List cpList = new ArrayList();
		String cpToRemove = (targetProjName.endsWith(IJ2EEModuleConstants.JAR_EXT)) ? 
				targetProjName : targetProjName + IJ2EEModuleConstants.JAR_EXT;
		for (int i = 0; i < cp.length; i++) {
			if (!cp[i].equals(cpToRemove)) {
				cpList.add(cp[i]);
			}
		}
		updateManifestDataModel.setProperty(UpdateManifestDataModelProperties.JAR_LIST, cpList);
		updateManifestDataModel.getDefaultOperation().execute(monitor, null);
	}
}
