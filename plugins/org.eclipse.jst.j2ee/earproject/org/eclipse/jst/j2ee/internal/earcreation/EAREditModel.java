/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.earcreation;


import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.application.ApplicationResource;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.application.operations.DefaultModuleProjectCreationOperation;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.EARProjectMap;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.ModuleMapping;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.ModulemapPackage;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.UtilityJARMapping;
import org.eclipse.jst.j2ee.moduleextension.EarModuleManager;
import org.eclipse.wst.common.frameworks.internal.SaveFailedException;
import org.eclipse.wst.common.internal.emfworkbench.EMFWorkbenchContext;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModelEvent;

import org.eclipse.jem.util.emf.workbench.ProjectUtilities;

/**
 * 
 * @deprecated
 * Use
 * <p>
 * 	EARArtifactEdit
 * </p>
 *
 */
public class EAREditModel extends org.eclipse.jst.j2ee.internal.J2EEEditModel {
	private boolean stopListenerNotifications = false;
	//TODO this is a hack until the DOM adaptors are working
	public static final String MODULE_MAP_URI = "META-INF/.modulemaps"; //$NON-NLS-1$
	public static final URI MODULE_MAP_URI_OBJ = URI.createURI(MODULE_MAP_URI);

	/**
	 * EJBSaveModel constructor comment.
	 * 
	 * @param Object
	 *            editModelID - An editModelID to identify the EditModel
	 */
	public EAREditModel(String editModelID, EMFWorkbenchContext context, boolean readOnly) {
		super(editModelID, context, readOnly);
	}// EAREditModel

	/**
	 * EJBSaveModel constructor comment.
	 * 
	 * @param Object
	 *            editModelID - An editModelID to identify the EditModel
	 */
	public EAREditModel(String editModelID, EMFWorkbenchContext context, boolean readOnly, boolean accessUnkownResourcesAsReadOnly) {
		super(editModelID, context, readOnly, accessUnkownResourcesAsReadOnly);
	}// EAREditModel

	/**
	 * Adds the module mapping to a project.
	 * 
	 * @param Module
	 *            module - The module to add
	 * @param IProject
	 *            project - The current project.
	 * @return ModuleMapping
	 */
	public ModuleMapping addModuleMapping(Module module, IProject aProject) {

		ModuleMapping map = ((ModulemapPackage) EPackage.Registry.INSTANCE.getEPackage(ModulemapPackage.eNS_URI)).getModulemapFactory().createModuleMapping();
		map.setModule(module);
		map.setProjectName(aProject.getName());
		getEARProjectMap().getMappings().add(map);

		return map;
	}// ModuleMapping

	/**
	 * Adds a utility jar mapping to a project.
	 * 
	 * @param String
	 *            uri - Utility jar uri.
	 * @param IProject
	 *            project - The current project.
	 * @return UtilityJARMapping
	 */
	public UtilityJARMapping addUtilityJARMapping(String uri, IProject aProject) {

		UtilityJARMapping map = ((ModulemapPackage) EPackage.Registry.INSTANCE.getEPackage(ModulemapPackage.eNS_URI)).getModulemapFactory().createUtilityJARMapping();
		map.setUri(uri);
		map.setProjectName(aProject.getName());
		getEARProjectMap().getUtilityJARMappings().add(map);

		return map;
	}// addUtilityJARMapping

	/**
	 * Return the root object, application, from the application.xml DD.
	 * 
	 * @return Application
	 */
	public Application getApplication() {
		ApplicationResource dd = getApplicationXmiResource();
		return dd == null ? null : dd.getApplication();
	}

	/**
	 * Gets the application resource.
	 * 
	 * @return ApplicationResource
	 */
	public ApplicationResource getApplicationXmiResource() {
		return (ApplicationResource) getResource(ArchiveConstants.APPLICATION_DD_URI_OBJ);
	}// getApplicationXmiResource

	/**
	 * Answer all the projects referenced by the .modulemaps file
	 * 
	 * @return Set
	 */
	public Set getModuleMappedProjects() {
		Set aSet = getModuleMappedModuleProjects();
		aSet.addAll(getModuleMappedUtilityJarProjects());
		return aSet;
	}

	public Map getModuleMappedProjectsAsMap() {
		Map aMap = getModuleMappedModuleProjectsAsMap();
		aMap.putAll(getModuleMappedUtilityJarProjectsAsMap());
		return aMap;
	}


	public Set getModuleMappedUtilityJarProjects() {
		Set aSet = new HashSet();
		aSet.addAll(getModuleMappedUtilityJarProjectsAsMap().values());
		return aSet;
	}

	public Map getModuleMappedUtilityJarProjectsAsMap() {
		Map aMap = new HashMap();
		IProject aProject = null;
		List utilityJARMappings = getUtilityJARMappings();
		for (int i = 0; i < utilityJARMappings.size(); i++) {
			aProject = null;
			UtilityJARMapping map = (UtilityJARMapping) utilityJARMappings.get(i);
			if (map.getProjectName() != null && map.getProjectName().length() > 0)
				aProject = getProject(map.getProjectName());
			if (aProject != null && map.getUri() != null && map.getUri().length() > 0)
				aMap.put(map.getUri(), aProject);
		}
		return aMap;
	}

	public Set getModuleMappedModuleProjects() {
		Set aSet = new HashSet();
		aSet.addAll(getModuleMappedModuleProjectsAsMap().values());
		return aSet;
	}

	public Map getModuleMappedModuleProjectsAsMap() {
		Map aMap = new HashMap();
		List moduleMappings = getModuleMappings();
		IProject aProject = null;
		for (int i = 0; i < moduleMappings.size(); i++) {
			aProject = null;
			ModuleMapping map = (ModuleMapping) moduleMappings.get(i);
			if (map.getProjectName() != null && map.getProjectName().length() > 0)
				aProject = getProject(map.getProjectName());
			Module module = map.getModule();
			if (aProject != null && module != null && module.getUri() != null && module.getUri().length() > 0)
				aMap.put(module.getUri(), aProject);
		}
		return aMap;
	}


	/**
	 * Get a project
	 * 
	 * @param String
	 *            name - The project name.
	 * @return IProject
	 */
	protected IProject getProject(String name) {
		if (name == null || name.length() == 0)
			return null;
		return ResourcesPlugin.getWorkspace().getRoot().getProject(name);
	}

	protected IProject getProject(ModuleMapping mapping) {
		if (mapping != null && mapping.getProjectName() != null && mapping.getProjectName().length() > 0)
			return getProject(mapping.getProjectName());
		return null;
	}

	/**
	 * Finds an existing UtilityJARMapping for the project and uri, if one exists
	 *  
	 */
	public UtilityJARMapping getUtilityJARMapping(String uri, IProject aProject) {
		if (aProject == null || uri == null)
			return null;
		List maps = getUtilityJARMappings();
		for (int i = 0; i < maps.size(); i++) {
			UtilityJARMapping map = (UtilityJARMapping) maps.get(i);
			if (aProject.getName().equals(map.getProjectName()) && uri.equals(map.getUri()))
				return map;
		}
		return null;
	}

	/**
	 * Insert the method's description here. Creation date: (4/11/2001 4:14:26 PM)
	 * 
	 * @return EJBNatureRuntime
	 */
	public EARNatureRuntime getEARNature() {
		return EARNatureRuntime.getRuntime(getProject());
	}

	/**
	 * The root object which contains the list of mappings from application modules to workbench
	 * projects
	 */
	public EARProjectMap getEARProjectMap() {
		return getEARProjectMap(true);
	}

	/**
	 * The root object which contains the list of mappings from application modules to workbench
	 * projects
	 */
	protected EARProjectMap getEARProjectMap(boolean createIfEmpty) {
		Resource res = null;
		try {
			res = getOrCreateResource(MODULE_MAP_URI_OBJ);
		} catch (WrappedException e) {
		}
		if (res == null)
			return null;
		if (res.getContents().isEmpty() && createIfEmpty) {
			boolean dirtyFlag = res.isModified();
			res.getContents().add(ModulemapPackage.eINSTANCE.getModulemapFactory().createEARProjectMap());
			res.setModified(dirtyFlag);
		}// if
		if (res.getContents().isEmpty())
			return null;
		return (EARProjectMap) res.getContents().get(0);
	}// getEARProjectMap

	/**
	 * Get the module mappings from module
	 * 
	 * @param Module
	 *            module - The current module.
	 * @return ModuleMapping
	 */
	public ModuleMapping getModuleMapping(Module module) {
		if (module == null)
			return null;
		List maps = getModuleMappings();
		for (int i = 0; i < maps.size(); i++) {
			ModuleMapping map = (ModuleMapping) maps.get(i);
			Module iModule = map.getModule();
			if (module.equals(iModule))
				return map;
		}// for
		return null;
	}// getModuleMapping

	/**
	 * Get the utility jar mapping from an uri
	 * 
	 * @param String
	 *            uri - The current uri
	 * @return UtilityJARMapping
	 */
	public UtilityJARMapping getUtilityJARMapping(String uri) {
		if (uri == null)
			return null;
		List maps = getUtilityJARMappings();
		for (int i = 0; i < maps.size(); i++) {
			UtilityJARMapping map = (UtilityJARMapping) maps.get(i);
			if (uri.equals(map.getUri()))
				return map;
		}// for
		return null;
	}// getUtilityJARMapping

	/**
	 * Return a Project for the given <code>uri</code>. This will return a Project for a utility
	 * JAR mapping or a module mapping. A project will be returned if the mapping has a project name
	 * even if the project does not exist or is not accessible.
	 * 
	 * @param uri
	 * @return
	 */
	public IProject getMappedProject(String uri) {
		UtilityJARMapping utilMapping = getUtilityJARMapping(uri);
		if (utilMapping != null) {
			return getProject(utilMapping.getProjectName());
		}
		List moduleMappings = getModuleMappings();
		for (int i = 0; i < moduleMappings.size(); i++) {
			ModuleMapping map = (ModuleMapping) moduleMappings.get(i);
			Module mod = map.getModule();
			if (mod != null && uri.equals(mod.getUri()))
				return getProject(map.getProjectName());
		}
		return null;
	}

	/**
	 * Get the module mapping based on a project.
	 * 
	 * @param IProject
	 *            project - The current project.
	 * @return ModuleMapping
	 */
	public ModuleMapping getModuleMapping(IProject aProject) {
		if (aProject == null)
			return null;
		List maps = getModuleMappings();
		for (int i = 0; i < maps.size(); i++) {
			ModuleMapping map = (ModuleMapping) maps.get(i);
			if (aProject.getName().equals(map.getProjectName()))
				return map;
		}// for
		return null;
	}// getModuleMapping

	/**
	 * Checks if the uri mapping already exists.
	 * 
	 * @param String
	 *            currentName - The current uri of the module.
	 * @return boolean
	 */
	public boolean uriExists(String currentName) {

		if (currentName != null) {
			return (this.moduleMappingExists(currentName) || this.utilJarMappingExists(currentName));
		} // if
		return false;
	} // uriExists

	/**
	 * Checks if the current uri conflicts with any of the existing module mappings.
	 * 
	 * @param String
	 *            currentUri - The uri to check to see if it exists.
	 * @return boolean
	 */
	public boolean moduleMappingExists(String currentUri) {
		if (currentUri == null)
			return false;
		List maps = getModuleMappings();
		if (maps != null || !maps.isEmpty()) {
			for (int i = 0; i < maps.size(); i++) {
				ModuleMapping map = (ModuleMapping) maps.get(i);
				if (currentUri.equals(map.getModule().getUri())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks if the current uri conflicts with any of the existing utility jar mappings.
	 * 
	 * @param String
	 *            currentUri - The uri to check to see if it exists.
	 * @return boolean
	 */
	public boolean utilJarMappingExists(String currentUri) {
		if (currentUri == null)
			return false;
		List utilMaps = this.getUtilityJARMappings();
		if (utilMaps != null || !utilMaps.isEmpty()) {
			for (int i = 0; i < utilMaps.size(); i++) {
				UtilityJARMapping utilMap = (UtilityJARMapping) utilMaps.get(i);
				if (currentUri.equals(utilMap.getUri())) {
					return true;
				} // if
			} // for
		} // if
		return false;
	} // utilJarMappingExists

	/**
	 * Gets the utilityJARMapping bases on project.
	 * 
	 * @param IProject
	 *            project - The current project.
	 * @return UtilityJARMapping
	 */
	public UtilityJARMapping getUtilityJARMapping(IProject aProject) {
		if (aProject == null)
			return null;
		List maps = getUtilityJARMappings();
		for (int i = 0; i < maps.size(); i++) {
			UtilityJARMapping map = (UtilityJARMapping) maps.get(i);
			if (aProject.getName().equals(map.getProjectName()))
				return map;
		}// for
		return null;
	}// getUtilityJARMapping

	/**
	 * Get a collection of module mappings.
	 * 
	 * @return List
	 */
	public List getModuleMappings() {
		EARProjectMap map = getEARProjectMap();
		if (map == null)
			return java.util.Collections.EMPTY_LIST;
		return map.getMappings();
	}//getModuleMappings

	/**
	 * Get a collection of utility mappings.
	 * 
	 * @return List
	 */
	public List getUtilityJARMappings() {
		EARProjectMap map = getEARProjectMap();
		if (map == null)
			return java.util.Collections.EMPTY_LIST;
		return map.getUtilityJARMappings();
	}// getUtilityJARMappings

	/**
	 * The resource which contains the mappings from application modules to workbench projects
	 * 
	 * @return Resource
	 */
	protected Resource getModuleMapsXmiResource() {
		return getResource(MODULE_MAP_URI_OBJ);
	}// getModuleMapsXmiResource

	//	TODO Removed to make way for the generic getRootObject()
	//	/**
	//	 * Gets the root object.
	//	 *
	//	 * @return Application
	//	 * @deprecated Please use getApplication() for consistency
	//	 */
	//	public org.eclipse.jst.j2ee.internal.internal.application.Application getRootObject() {
	//		return getApplication();
	//	}// getRootObject


	/**
	 * discription goes here
	 * 
	 * @return Resource
	 */
	public Resource makeDeploymentDescriptorWithRoot() {
		XMLResource res = (XMLResource) makeEarXmiResource();
		Application app = ApplicationPackage.eINSTANCE.getApplicationFactory().createApplication();
		res.getContents().add(app);
		res.setID(app, ArchiveConstants.APPL_ID);
		res.setJ2EEVersionID(getEARNature().getJ2EEVersion());
		app.setDisplayName(getProject().getName());
		return res;
	}// Resource

	/**
	 * discription goes here
	 * 
	 * @return Resource
	 */
	public Resource makeEarXmiResource() {
		return createResource(ArchiveConstants.APPLICATION_DD_URI_OBJ);
	}// makeEarXmiResource

	/**
	 * The resource which contains the mappings from application modules to workbench projects
	 * 
	 * @return Resource
	 */
	protected Resource makeModuleMapsXmiResource() {

		if (isReadOnly())
			return null;

		Resource res = createResource(MODULE_MAP_URI_OBJ);
		EARProjectMap root = ModulemapPackage.eINSTANCE.getModulemapFactory().createEARProjectMap();
		res.getContents().add(root);
		res.setModified(false);
		return res;
	}// makeModuleMapsXmiResource

	/**
	 * makeRoot
	 * 
	 * @param Resource
	 *            deploymentDescriptor - The current deployment descriptor
	 */
	public Resource makeRoot(Resource deploymentDescriptor) {

		Application app = ((ApplicationPackage) EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI)).getApplicationFactory().createApplication();
		app.setDisplayName(getProject().getName());
		((XMIResource) app.eResource()).setID(app, ArchiveConstants.APPL_ID);
		deploymentDescriptor.getContents().add(app);
		return deploymentDescriptor;
	}// makeRoot

	//HACK TO BE REMOVED
	protected void notifyListeners(EditModelEvent anEvent) {
		if (!stopListenerNotifications)
			super.notifyListeners(anEvent);
	}

	//HACK TO BE REMOVED
	public void resumeNotifications() {
		stopListenerNotifications = false;
	}

	//HACK TO BE REMOVED
	public void stopNotifications() {
		stopListenerNotifications = true;
	}

	/**
	 * primSave
	 * 
	 * @param IProgressMonitor
	 *            monitor - Current progress monitor.
	 * @see EditModel#primSave(IProgressMonitor)
	 */
	public void primSave(IProgressMonitor monitor) {
		removeInvalidModuleMaps(true);
		super.primSave(monitor);
		try {
			updateProjectReferences(monitor);
		} catch (CoreException ex) {
			throw new SaveFailedException(EARCreationResourceHandler.getString("PROJ_DESC_UPDATE_ERROR_UI_"), ex); //$NON-NLS-1$
		}// try
	} // primSave

	public void removeInvalidModuleMaps(boolean preserveModifiedFlag) {
		if (!this.isReadOnly()) {
			List moduleMaps = this.getExistingModuleMappings();
			if (!moduleMaps.isEmpty()) {
				Resource mmResource = getModuleMapsXmiResource();
				boolean isDirty = mmResource.isModified();
				try {
					Iterator mapIterator = moduleMaps.iterator();
					ModuleMapping mm = null;
					Module m = null;
					while (mapIterator.hasNext()) {
						mm = (ModuleMapping) mapIterator.next();
						m = mm.getModule();
						if (m == null || m.eIsProxy())
							mapIterator.remove();
					}
				} finally {
					if (preserveModifiedFlag)
						mmResource.setModified(isDirty);
				}
			}
		}
	}

	/**
	 * @return
	 */
	private List getExistingModuleMappings() {
		EARProjectMap map = getEARProjectMap(false);
		if (map == null)
			return java.util.Collections.EMPTY_LIST;
		return map.getMappings();
	}



	/**
	 * Updates the project references
	 * 
	 * @param IProgressMonitor
	 *            monitor - Current progress monitor.
	 */
	protected void updateProjectReferences(IProgressMonitor monitor) throws CoreException {
		IProjectDescription description = getProject().getDescription();
		Set newProjects = new HashSet();
		newProjects.addAll(getModuleMappedProjects());
		if (!hasProjectReferenceChanges(description, newProjects))
			return;
		IProject[] newArray = (IProject[]) newProjects.toArray(new IProject[newProjects.size()]);
		description.setReferencedProjects(newArray);
		int flags = IResource.FORCE | IResource.KEEP_HISTORY;
		getProject().setDescription(description, flags, monitor);
	}// updateProjectReferences

	/**
	 * Checks if there are reference changes.
	 * 
	 * @return boolean
	 */
	public boolean hasProjectReferenceChanges() {
		IProjectDescription description;
		try {
			description = getProject().getDescription();
		} catch (CoreException ex) {
			return false;
		}// try

		Set newProjects = new HashSet();
		newProjects.addAll(getModuleMappedProjects());
		return hasProjectReferenceChanges(description, newProjects);
	}// hasProjectReferenceChanges

	/**
	 * Checks if any of the project references have changed.
	 * 
	 * @param IProjectDescription
	 *            description - Current project discription.
	 * @param Set
	 *            newProjects - A set of new projects.
	 * @return boolean
	 */
	protected boolean hasProjectReferenceChanges(IProjectDescription description, Set newProjects) {
		IProject[] projects = description.getReferencedProjects();
		Set oldProjects = new HashSet();
		oldProjects.addAll(Arrays.asList(projects));
		return !oldProjects.equals(newProjects);
	}// hasProjectReferenceChanges

	/**
	 * Gets a list of non resource files.
	 * 
	 * @return List
	 * @see org.eclipse.wst.common.internal.emfworkbench.validateedit.ResourceStateInputProvider#getNonResourceFiles()
	 */
	public List getNonResourceFiles() {
		IFile aFile = getProject().getFile(ProjectUtilities.DOT_PROJECT);
		return Collections.singletonList(aFile);
	}// getNonResourceFiles

	/**
	 * Get the deployment descripto resource
	 * 
	 * @return XMLResource
	 */
	public XMLResource getDeploymentDescriptorResource() {
		return getApplicationXmiResource();
	}// getDeploymentDescriptorResource

	/**
	 * Answer whether there is either a module mapping or a utility JAR mapping to the project.
	 * 
	 * @param IProject
	 *            p - The current project.
	 * @return boolean
	 */
	public boolean hasMappingToProject(IProject p) {
		return getModuleMapping(p) != null || getUtilityJARMapping(p) != null;
	}//  hasMappingToProject

	/**
	 * Returns a project based on a util jar mapping.
	 * 
	 * @param UtilityJARMapping
	 *            utilJarMapping - The current utilJarMapping
	 * @return IProject
	 */
	public IProject getProjectFromUtilJarMapping(UtilityJARMapping utilJarMapping) {
		if (utilJarMapping != null) {
			String projectName = utilJarMapping.getProjectName();
			return getProject(projectName);
		} // if
		return null;
	} // getProjectFromUtilJarMapping

	public boolean isEjbClientJarMapping(UtilityJARMapping utilJarMapping) {
		IProject aProject = getProjectFromUtilJarMapping(utilJarMapping);
		if (aProject != null)
			return isEjbClientJarProject(aProject);
		return false;
	}

	/**
	 * @param project
	 * @return
	 */
	public boolean isEjbClientJarProject(IProject utilProject) {
		if (utilProject != null) {
			List mappings = getModuleMappings();
			ModuleMapping mapping;
			IProject ejbProject;
			for (int i = 0; i < mappings.size(); i++) {
				mapping = (ModuleMapping) mappings.get(i);
				if (mapping.getModule() != null && mapping.getModule().isEjbModule()) {
					ejbProject = getProject(mapping);
					if (utilProject.equals(EarModuleManager.getEJBModuleExtension().getDefinedEJBClientJARProject(ejbProject)))
						return true;
				}
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.workbench.J2EEEditModel#getRootObject()
	 */
	public Object getRootObject() {
		return getApplication();
	}

	public String getDevelopmentAcivityID() {
		return DefaultModuleProjectCreationOperation.ENTERPRISE_JAVA;
	}
}