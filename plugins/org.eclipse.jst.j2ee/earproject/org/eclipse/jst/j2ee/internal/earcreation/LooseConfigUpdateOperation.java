/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.earcreation;


import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.internal.core.JavaModel;
import org.eclipse.jst.j2ee.commonarchivecore.internal.looseconfig.LooseApplication;
import org.eclipse.jst.j2ee.commonarchivecore.internal.looseconfig.LooseArchive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.looseconfig.LooseConfiguration;
import org.eclipse.jst.j2ee.commonarchivecore.internal.looseconfig.LooseLibrary;
import org.eclipse.jst.j2ee.commonarchivecore.internal.looseconfig.LooseModule;
import org.eclipse.jst.j2ee.commonarchivecore.internal.looseconfig.LooseconfigFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.looseconfig.LooseconfigPackage;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.ModuleMapping;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.UtilityJARMapping;
import org.eclipse.jst.j2ee.internal.project.J2EEModuleNature;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.wst.common.frameworks.internal.enablement.nonui.WFTWrappedException;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

public class LooseConfigUpdateOperation extends WTPOperation implements ILooseConfigConstants {

	protected Resource resource;
	protected IProgressMonitor monitor;
	protected LooseConfiguration looseConfig;
	protected String uri;
	protected EAREditModel currentEditModel;

	protected static final String UPDATING_MESSAGE = EARCreationResourceHandler.getString("Updating_absolute_paths_UI_"); //$NON-NLS-1$

	public LooseConfigUpdateOperation() {
	}

	protected LooseconfigFactory looseConfigFactory() {
		return ((LooseconfigPackage) EPackage.Registry.INSTANCE.getEPackage(LooseconfigPackage.eNS_URI)).getLooseconfigFactory();
	}

	/*
	 * @see HeadlessJ2EEOperation#execute(IProgressMonitor)
	 */
	protected void execute(IProgressMonitor progressMonitor) throws CoreException, InvocationTargetException, InterruptedException {
		monitor = progressMonitor;
		monitor.beginTask(UPDATING_MESSAGE, IProgressMonitor.UNKNOWN);
		initialize();
		addLooseApplications();
		save();
	}

	protected void initialize() {
		resource = Resource.Factory.Registry.INSTANCE.getFactory(URI.createURI(CONFIG_PATH)).createResource(URI.createURI(CONFIG_PATH));
		resource.getContents().add(createLooseConfig());
	}

	protected LooseConfiguration createLooseConfig() {
		looseConfig = looseConfigFactory().createLooseConfiguration();
		return looseConfig;
	}

	protected void addLooseApplications() throws CoreException {
		List earProjects = EARNatureRuntime.getAllEARProjectsInWorkbench();
		for (int i = 0; i < earProjects.size(); i++) {
			IProject p = (IProject) earProjects.get(i);
			EARNatureRuntime runtime = EARNatureRuntime.getRuntime(p);
			addLooseApplication(runtime);
		}
	}

	protected void addLooseApplication(EARNatureRuntime runtime) throws CoreException {
		monitor.subTask(runtime.getProject().getName());
		currentEditModel = runtime.getEarEditModelForRead(this);
		try {
			LooseApplication looseApp = createLooseApplication(runtime);
			addLooseApplication(looseApp);
		} finally {
			currentEditModel.releaseAccess(this);
			currentEditModel = null;
		}
	}

	protected void addLooseApplication(LooseApplication looseApp) throws CoreException {
		addLooseModules(looseApp, currentEditModel.getModuleMappings());
		addLooseUtilityJARs(looseApp, currentEditModel.getUtilityJARMappings());
		looseConfig.getApplications().add(looseApp);
	}

	protected LooseApplication createLooseApplication(EARNatureRuntime runtime) {
		LooseApplication looseApp = looseConfigFactory().createLooseApplication();
		String absPath = getAbsolutePath(runtime);
		looseApp.setUri(absPath);
		setPaths(looseApp, absPath);
		return looseApp;
	}

	protected void addLooseModules(LooseApplication looseApp, List moduleMaps) {
		List looseModules = looseApp.getLooseArchives();
		for (int i = 0; i < moduleMaps.size(); i++) {
			ModuleMapping map = (ModuleMapping) moduleMaps.get(i);
			LooseModule module = createLooseModule(map);
			looseModules.add(module);
		}
	}

	protected LooseModule createLooseModule(ModuleMapping map) {
		LooseModule loose = looseConfigFactory().createLooseModule();
		loose.setUri(map.getModule().getUri());
		setPaths(loose, getAbsolutePath(map));
		return loose;
	}

	protected void setPaths(LooseArchive archive, String absPath) {
		if (absPath == null)
			return;

		archive.setBinariesPath(absPath);
		archive.setResourcesPath(absPath);
	}

	protected void save() throws InvocationTargetException {
		try {
			ResourceSet set = resource.getResourceSet();
			if (set != null) {
				OutputStream out = set.getURIConverter().createOutputStream(resource.getURI());
				resource.save(out, Collections.EMPTY_MAP);
			}
		} catch (Exception ex) {
			throw new WFTWrappedException(ex);
		}
	}

	protected String getAbsolutePath(ModuleMapping map) {
		if (map.getProjectName() == null)
			return null;
		IProject p = getProject(map.getProjectName());
		J2EEModuleNature nature = (J2EEModuleNature) J2EENature.getRegisteredRuntime(p);

		return getAbsolutePath(nature);
	}

	protected String getAbsolutePath(UtilityJARMapping map) throws CoreException {
		if (map.getProjectName() == null)
			return null;
		IProject p = getProject(map.getProjectName());
		//JavaProject javaProj = JavaCore.get

		return getAbsolutePath(getJavaProject(p));
	}

	protected String getAbsolutePath(IJavaProject javaProj) throws CoreException {
		IPath loc = javaProj.getOutputLocation();
		if (loc == null)
			return null;

		return javaProj.getProject().getFile(loc).getLocation().toOSString();
	}

	protected IProject getProject(String projectName) {
		return getWorkspace().getRoot().getProject(projectName);
	}

	protected JavaModel getJavaModel() {
		return ProjectUtilities.getJavaModel();
	}

	protected IJavaProject getJavaProject(IProject p) {
		return getJavaModel().getJavaProject(p);
	}

	protected String getAbsolutePath(J2EENature nature) {
		if (nature == null)
			return null;
		return nature.getModuleServerRoot().getLocation().toOSString();
	}

	protected void addLooseUtilityJARs(LooseApplication looseApp, List utilMaps) throws CoreException {
		List looseArchives = looseApp.getLooseArchives();
		for (int i = 0; i < utilMaps.size(); i++) {
			UtilityJARMapping map = (UtilityJARMapping) utilMaps.get(i);
			LooseLibrary lib = createLooseLibrary(map);
			looseArchives.add(lib);
		}
	}

	protected LooseLibrary createLooseLibrary(UtilityJARMapping map) throws CoreException {
		LooseLibrary loose = looseConfigFactory().createLooseLibrary();
		loose.setUri(map.getUri());
		setPaths(loose, getAbsolutePath(map));
		return loose;
	}

}