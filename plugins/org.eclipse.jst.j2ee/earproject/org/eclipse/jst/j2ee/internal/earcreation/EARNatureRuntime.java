/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.earcreation;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceStatus;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveOptions;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.LoadStrategy;
import org.eclipse.jst.j2ee.internal.J2EEEditModel;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.archive.operations.EARProjectLoadStrategyImpl;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.ModuleMapping;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.UtilityJARMapping;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModel;


public class EARNatureRuntime extends J2EENature {

	private static final String EAR_PROJECT_12_OVERLAY = "1_2_ovr"; //$NON-NLS-1$
	private static final String EAR_PROJECT_13_OVERLAY = "1_3_ovr"; //$NON-NLS-1$
	private static final String EAR_PROJECT_14_OVERLAY = "1_4_ovr"; //$NON-NLS-1$
	protected static final String ALT_ROOT_EDEFAULT = null;

	public static Map EMPTY_MAP = new HashMap(0);


	/**
	 * WebNature constructor comment.
	 */
	public EARNatureRuntime() {
		super();
	}

	/**
	 * Return the key for the sourcePath.
	 */
	public Archive asArchive() throws OpenFailureException {
		return asEARFile();
	}

	/**
	 * Return the key for the sourcePath.
	 */
	public Archive asArchive(boolean shouldExportSource) throws OpenFailureException {
		return null;
	}

	public EARFile asEARFile() throws OpenFailureException {
		IProject proj = getProject();
		if (proj == null)
			return null;
		ArchiveOptions options = new ArchiveOptions();
		options.setIsReadOnly(true);
		LoadStrategy loader = new EARProjectLoadStrategyImpl(proj);
		options.setLoadStrategy(loader);
		loader.setResourceSet(getResourceSet());
		return CommonarchiveFactory.eINSTANCE.openEARFile(options, proj.getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.j2eeproject.J2EENature#getJ2EEVersion()
	 */
	public int getJ2EEVersion() {

		return getModuleVersion();
	}

	public EARFile asEARFile(boolean exportSource, boolean mergeDependents) throws OpenFailureException {
		IProject proj = getProject();
		if (proj == null)
			return null;
		ArchiveOptions options = new ArchiveOptions();
		options.setIsReadOnly(true);
		EARProjectLoadStrategyImpl loader = new EARProjectLoadStrategyImpl(proj);
		options.setLoadStrategy(loader);
		loader.setResourceSet(getResourceSet());
		loader.setExportSource(true);
		loader.setMergeDependentJars(false);
		return CommonarchiveFactory.eINSTANCE.openEARFile(options, proj.getName());
	}

	/**
	 * True for all but the EAR Nature
	 */
	protected boolean projectIsJavaProject() {
		return false;
	}

	protected EditModel createCacheEditModel() {
		return getEarEditModelForRead(this);
	}

	/**
	 * Create the folders for the project we have just created.
	 * 
	 * @exception com.ibm.itp.core.api.resources.CoreException
	 *                The exception description.
	 */
	protected void createFolders() throws CoreException {

		//build for metapath
		createFolder(getMetaPath());
	}

	/**
	 * Undefined for an ear project
	 */
	public Module createNewModule() {
		return null;
	}

	/**
	 * Create a new nature runtime from the project info
	 */
	//	public static EARNatureRuntime createRuntime(EARProjectInfo info) throws CoreException {
	//		IProject project = info.getProject();
	//		String natureId = IEARNatureConstants.NATURE_ID;
	//		if (!hasRuntime(project)) {
	//			addNatureToProject(project, natureId);
	//			EARNatureRuntime runtime = getRuntime(project);
	//			runtime.initializeFromInfo(info);
	//			return runtime;
	//		}
	//		return getRuntime(project);
	//	}
	/**
	 * Return a list of all projects that have an ear nature
	 */
	public static List getAllEARProjectsInWorkbench() {
		List allProjects = Arrays.asList(J2EEPlugin.getWorkspace().getRoot().getProjects());
		List earProjects = new ArrayList();
		for (int i = 0; i < allProjects.size(); i++) {
			IProject p = (IProject) allProjects.get(i);
			if (p.exists() && p.isOpen()) {
				if (hasRuntime(p))
					earProjects.add(p);
			}
		}
		return earProjects;
	}

	public Application getApplication() {
		return ((EAREditModel) getCacheEditModel()).getApplication();
	}

	public Resource getApplicationXmiResource() {
		return getResource(URI.createURI(ArchiveConstants.APPLICATION_DD_URI));
	}

	/**
	 * Return the key for the classpath.
	 */
	public java.lang.String getClassPathKey() {
		return null;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.j2eeproject.J2EENature#getEditModelKey()
	 */
	public String getEditModelKey() {
		return IEARNatureConstants.EDIT_MODEL_ID;
	}

	/**
	 * Returns an application xml model that consists of the MOF model and the DOM model.
	 * Important!!! Calling this method increments the use count of this model. When you are done
	 * accessing the model, call releaseAccess()!
	 */
	public EAREditModel getEarEditModelForRead(Object accessorKey) {
		return (EAREditModel) getEditModelForRead(IEARNatureConstants.EDIT_MODEL_ID, accessorKey);
	}

	/**
	 * Returns an application xml model that consists of the MOF model and the DOM model.
	 * Important!!! Calling this method increments the use count of this model. When you are done
	 * accessing the model, call releaseAccess()!
	 */
	public EAREditModel getEarEditModelForWrite(Object accessorKey) {
		return (EAREditModel) getEditModelForWrite(IEARNatureConstants.EDIT_MODEL_ID, accessorKey);
	}

	public Resource getEjbXmiResource() {
		return getResource(URI.createURI(ArchiveConstants.EJBJAR_DD_URI));
	}

	/**
	 * Return the key for the metaPath.
	 */
	public java.lang.String getMetaPathKey() {
		return IEARNatureConstants.DEFAULT_META_PATH;
	}

	public Module getModule(IProject referencedProject) {
		EAREditModel model = (EAREditModel) getCacheEditModel();
		ModuleMapping map = model.getModuleMapping(referencedProject);
		if (map != null) {
			return map.getModule();
		}
		return null;
	}

	/**
	 * Return a Project for the given <code>uri</code>. This will return a Project for a utility
	 * JAR mapping or a module mapping. A project will be returned if the mapping has a project name
	 * even if the project does not exist or is not accessible.
	 * 
	 * @param uri
	 * @return
	 */
	public IProject getMappedProject(String uri) {
		EAREditModel model = (EAREditModel) getCacheEditModel();
		return model.getMappedProject(uri);
	}

	/**
	 * @return J2EENature - the nature for the project that represents
	 * @aModule in the ear; null if no such project exists
	 */
	public J2EENature getModuleProject(Module aModule) {

		IProject aProject = primGetModuleProject(aModule);
		if (aProject != null)
			return J2EENature.getRegisteredRuntime(aProject);
		return null;
	}

	/**
	 * @return J2EENature - the nature for the project that represents the module having
	 * @moduleURI in the ear; null if the application does not contain a module with
	 * @moduleURI or if no such project exists
	 */
	public J2EENature getModuleProject(String moduleURI) {
		Application dd = getApplication();
		//You could have two modules with the same uri - right now we will take the first module it
		// finds
		return dd == null ? null : getModuleProject(dd.getFirstModule(moduleURI));
	}

	/**
	 * If the project is referenced by the EAR, return the URI of the JAR or module
	 */
	public String getJARUri(IProject aProject) {
		EAREditModel model = (EAREditModel) getCacheEditModel();
		ModuleMapping moduleMap = model.getModuleMapping(aProject);
		if (moduleMap != null) {
			Module aModule = moduleMap.getModule();
			return aModule == null ? EARCreationResourceHandler.getString("UNKNOWN_UI_") : aModule.getUri(); //$NON-NLS-1$
		}
		UtilityJARMapping jarMap = model.getUtilityJARMapping(aProject);
		if (jarMap != null)
			return jarMap.getUri();
		return null;
	}

	/**
	 * Return all the projects which represent the modules in this ear; Note that it is possible by
	 * hand editing or otherwise for a module to be declared for which there is no such project. In
	 * this case the value in the map for the uri will be null, so clients should check the values
	 * for null.
	 * 
	 * @return java.util.Map where the keys are the uris of all the modules (String), and the values
	 *         are the natures (J2EENature) of the projects represented by the uris.
	 */
	public java.util.Map getModuleProjects() {
		Application dd = getApplication();
		if (dd == null)
			return EMPTY_MAP;
		//Open an edit model here so we don't keep loading resources for each nested
		// call to getModuleProject()

		List modules = getApplication().getModules();
		Map result = new HashMap();
		for (int i = 0; i < modules.size(); i++) {
			Module m = (Module) modules.get(i);
			J2EENature nature = getModuleProject(m);
			result.put(m.getUri(), nature);
		}
		return result;

	}

	/**
	 * Return all the projects which represent the modules and project utility JARs in this ear;
	 * 
	 * @return java.util.Map where the keys are the uris of all the modules and JARs (String), and
	 *         the values are the projects represented by the uris.
	 */
	public Map getAllMappedProjects() {
		EAREditModel model = (EAREditModel) getCacheEditModel();
		return model.getModuleMappedProjectsAsMap();

	}

	public IContainer getEMFRoot() {
		return getProject();
	}

	/**
	 * Return the nature's ID.
	 */
	public java.lang.String getNatureID() {
		return IEARNatureConstants.NATURE_ID;
	}

	/**
	 * Return the ID of the plugin that this nature is contained within.
	 */
	protected java.lang.String getPluginID() {
		return J2EEPlugin.PLUGIN_ID;
	}

	/**
	 * @deprecated Referenced projects are no longer used to track modules in the ear; if you need
	 *             the referenced projects, obtain them from the project itself
	 */
	public IProject[] getReferencedProjects() {

		try {
			return getProject().getReferencedProjects();
		} catch (CoreException ex) {
			return new IProject[]{};
		}

	}

	/**
	 * Get a WebNatureRuntime that corresponds to the supplied project.
	 * 
	 * @return com.ibm.itp.wt.IWebNature
	 * @param project
	 *            com.ibm.itp.core.api.resources.IProject
	 */
	public static EARNatureRuntime getRuntime(IProject project) {
		return (EARNatureRuntime) getRuntime(project, IEARNatureConstants.NATURE_IDS);
	}

	/**
	 * Return the key for the sourcePath.
	 */
	public java.lang.String getSourcePathKey() {
		return null;
	}

	/**
	 * Return whether or not the project has a runtime created on it.
	 * 
	 * @return boolean
	 * @param project
	 *            com.ibm.itp.core.api.resources.IProject
	 */
	public static boolean hasRuntime(IProject project) {
		return hasRuntime(project, IEARNatureConstants.NATURE_IDS);
	}

	/**
	 * Update the receiver from the supplied info and then configure it.
	 * 
	 * @param info
	 *            EARProjctInfo - the info this was created from
	 * @exception CoreExeption -
	 *                thrown if the project cannot be updated
	 */
	//	public void initializeFromInfo(EARProjectInfo info) throws CoreException {
	//		createFolders();
	//		J2EESettings j2eeSettings = getJ2EESettings();
	//		j2eeSettings.setModuleVersion(info.getModuleVersion());
	//		j2eeSettings.write();
	//	}
	public Resource makeApplicationXmiResource() {
		return createResource(URI.createURI(ArchiveConstants.APPLICATION_DD_URI));
	}

	/**
	 * This returns the project, whether it is open or closed, whereas
	 * {@link #getModuleProject() returns the nature, which is only validif the project is open.
	 * 
	 * @return IProject - the project that represents
	 * @aModule in the ear; null if no such project exists
	 */
	public IProject primGetModuleProject(Module aModule) {

		try {
			if (aModule == null)
				return null;
			EAREditModel model = (EAREditModel) getCacheEditModel();

			ModuleMapping map = model.getModuleMapping(aModule);
			if (map != null) {
				return J2EEPlugin.getWorkspace().getRoot().getProject(map.getProjectName());
			}

			return null;
		} catch (java.lang.IllegalArgumentException ie) {
			return null;
		}
	}

	/**
	 * Because of team support or moving the workspace, the absolute paths of the module extensions
	 * may be out of sync with the actual locations of the files; moreover, modules might contain no
	 * mapping or absolute path, or might be mapped to a non-existent project. If there are unsaved
	 * changes to the application, then a warning is returned and no other validation is executed.
	 * 
	 * @return IStatus - code WARNING if there are unsaved changes or if a module project is closed,
	 *         ERROR if there is a problem with one of the modules, OK otherwise
	 */
	public IStatus validateModuleProjects() {
		//We're not going to write anything, but by using a write model, we can determine
		// if there are pending changes; also, we'll keep it open for the execution of this method
		// to ensure resources remain cached and reduce the performance hit
		EAREditModel model = getEarEditModelForWrite(this);
		try {
			if (model.isShared() && model.isDirty()) {
				String msg = EARCreationResourceHandler.getString("UNSAVED_CHANGES_WARN_", new Object[]{getProject().getName()}); //$NON-NLS-1$
				return J2EEPlugin.newStatus(IStatus.WARNING, 0, msg, (Throwable) null);
			}
			String message = EARCreationResourceHandler.getString("PROJECT_MAP_PROBLEMS_ERROR_", new Object[]{getProject().getName()}); //$NON-NLS-1$
			Application dd = model.getApplication();
			List modules = dd.getModules();
			boolean closedProject = false;
			for (int i = 0; i < modules.size(); i++) {
				Module m = (Module) modules.get(i);
				IProject p = primGetModuleProject(m);
				if (p == null)
					return new Status(IStatus.ERROR, J2EEPlugin.PLUGIN_ID, IResourceStatus.OPERATION_FAILED, message, (Throwable) null);
				if (!p.isOpen())
					closedProject = true;
			}
			if (closedProject) {
				String msg = EARCreationResourceHandler.getString("MODULE_PROJECTS_CLOSED_WARN_", new Object[]{getProject().getName()}); //$NON-NLS-1$
				return J2EEPlugin.newStatus(IStatus.WARNING, 0, msg, (Throwable) null);
			}
			return J2EEPlugin.newStatus(IStatus.OK, 0, "", (Throwable) null); //$NON-NLS-1$
		} finally {
			model.releaseAccess(this);
		}
	}

	public String getOverlayIconName() {
		switch (getJ2EEVersion()) {
			case J2EEVersionConstants.J2EE_1_2_ID :
				return EAR_PROJECT_12_OVERLAY;
			case J2EEVersionConstants.J2EE_1_3_ID :
				return EAR_PROJECT_13_OVERLAY;
			case J2EEVersionConstants.J2EE_1_4_ID :
			default :
				return EAR_PROJECT_14_OVERLAY;
		}
	}

	public int getDeploymentDescriptorType() {
		return XMLResource.APPLICATION_TYPE;
	}

	/**
	 * @see org.eclipse.jem.internal.java.plugin.AbstractJavaMOFNatureRuntime#addJavaReflectionAdapterFactories(ResourceSet)
	 */
	protected void addJavaReflectionAdapterFactories(ResourceSet aContext) {
		//do nothing since this is not a Java project
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.j2eeproject.J2EENature#getDeploymentDescriptorRoot()
	 */
	public EObject getDeploymentDescriptorRoot() {
		return getApplication();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.j2eeproject.J2EENature#getVersionFromModuleFile()
	 */
	protected int getVersionFromModuleFile() {
		Application ddRoot = getApplication();
		if (ddRoot != null) {
			return ddRoot.getVersionID();
		}
		return J2EEVersionConstants.J2EE_1_4_ID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.j2eeproject.J2EENature#getJ2EEEditModelForRead(java.lang.Object)
	 */
	public J2EEEditModel getJ2EEEditModelForRead(Object accessorKey) {
		return getEarEditModelForRead(accessorKey);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.j2eeproject.J2EENature#getJ2EEEditModelForWrite(java.lang.Object)
	 */
	public J2EEEditModel getJ2EEEditModelForWrite(Object accessorKey) {
		return getEarEditModelForWrite(accessorKey);
	}



}