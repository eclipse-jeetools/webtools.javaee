/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.tests.modulecore;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebModuleCreationDataModel;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.wst.common.internal.emfworkbench.EMFWorkbenchContext;
import org.eclipse.wst.common.modulecore.ArtifactEditModel;
import org.eclipse.wst.common.modulecore.DependentModule;
import org.eclipse.wst.common.modulecore.IModuleConstants;
import org.eclipse.wst.common.modulecore.ModuleCoreFactory;
import org.eclipse.wst.common.modulecore.ModuleCoreNature;
import org.eclipse.wst.common.modulecore.ModuleEditModelFactory;
import org.eclipse.wst.common.modulecore.ModuleStructuralModel;
import org.eclipse.wst.common.modulecore.ModuleType;
import org.eclipse.wst.common.modulecore.ProjectModules;
import org.eclipse.wst.common.modulecore.WorkbenchModule;
import org.eclipse.wst.common.modulecore.WorkbenchModuleResource;
import org.eclipse.wst.common.modulecore.impl.PlatformURLModuleConnection;
import org.eclipse.wst.common.modulecore.util.ModuleCore;

/**
 * <p>
 * The following class is experimental until fully documented.
 * </p>
 */
public class ModuleEditModelTest extends TestCase {

	private IProject project;
	private EMFWorkbenchContext emfContext;

	public interface IModuleTypesConstants {
		String MODULE_TYPE_WEB = "org.eclipse.jst.modules.web"; //$NON-NLS-1$
	}


	public ModuleEditModelTest(String name) {
		super(name);
	}

	public static Test suite() {
		// return new TestSuite(ModuleEditModelTest.class);
		TestSuite suite = new TestSuite();
		suite.addTest(new ModuleEditModelTest("testLoadResource"));
		return suite;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		getProject().delete(true, null);
		testCreateWTPModulesResource();
		super.setUp();
	}

	public void testLoadResource() throws Exception {

		ArtifactEditModel artifactModel = null;
		try {
			artifactModel = getNature(getProject()).getModuleEditModelForRead(getModuleURI(), this);
			Resource resource = artifactModel.getPrimaryResource();
			System.out.println(resource.getContents().get(0));
		} finally {
			if (artifactModel != null)
				artifactModel.releaseAccess(this);
		}
	}

	public void testURIAPI() throws Exception {
		URI uri = URI.createURI("module:/resource/" + getProjectName() + IPath.SEPARATOR + getModuleName() + ".war" + IPath.SEPARATOR + getTestResourcePath());
		System.out.println("URI : \"" + uri.toString() + "\"" + " with scheme \"" + uri.scheme() + "\" has " + uri.segmentCount() + " segments. They are ...");
		String[] segments = uri.segments();
		for (int i = 0; i < segments.length; i++)
			System.out.println("[" + i + "]: " + segments[i]);


		/* Determine if the URI is for a resource or binary module */

		if (PlatformURLModuleConnection.RESOURCE_MODULE.equals(segments[0])) {
			ModuleStructuralModel structuralModel = null;
			try {
				/* We need to find the project */
				structuralModel = ModuleCore.INSTANCE.getModuleStructuralModelForRead(uri, this);
				WorkbenchModuleResource resource = ModuleCore.INSTANCE.findWorkbenchModuleResourceByDeployPath(structuralModel, uri);
				System.out.println(resource != null ? resource.getSourcePath().toString() : "NOT FOUND");
			} finally {
				if (structuralModel != null)
					structuralModel.releaseAccess(this);
			}
		} else if (PlatformURLModuleConnection.BINARY_MODULE.equals(segments[0])) {

		}
	}

	public void testCreateWTPModulesResource() throws Exception {
		ModuleStructuralModel structuralModel = null;
		try {
			IProject containingProject = getProject(getProjectName());
			ModuleCoreNature moduleCoreNature = getNature(containingProject);
			structuralModel = moduleCoreNature.getModuleStructuralModelForWrite(this);
			structuralModel.prepareProjectModulesIfNecessary();
			ProjectModules projectModules = (ProjectModules) structuralModel.getPrimaryResource().getContents().get(0);
			addContent(projectModules);
			structuralModel.saveIfNecessary(this);
		} finally {
			if (structuralModel != null)
				structuralModel.releaseAccess(this);
		}

	}

	/**
	 * @return
	 */
	private ProjectModules getProjectModules() {
		ProjectModules projectModules = ModuleCoreFactory.eINSTANCE.createProjectModules();
		addContent(projectModules);
		return projectModules;
	}

	/**
	 * @param projectModules
	 */
	public void addContent(ProjectModules projectModules) {

		WorkbenchModule webModule = addWorkbenchModule(projectModules, getModulesFolder() + ".war", URI.createURI("module:/resource/" + getProjectName() + IPath.SEPARATOR + getModuleName()));
		addResource(webModule, getModuleRelativeFile("WebContent" + IPath.SEPARATOR + getTestResourcePath()), getTestResourcePath());
				
		ModuleType webModuleType = ModuleCoreFactory.eINSTANCE.createModuleType();
		webModuleType.setModuleTypeId(IModuleConstants.WST_WEB_MODULE); 
		webModule.setModuleType(webModuleType);

		WorkbenchModule webLibModule = addWorkbenchModule(projectModules, getModulesFolder() + "Lib.jar", URI.createURI("module:/resource/" + getProjectName() + IPath.SEPARATOR + getModulesFolder() + "Lib"));
		addResource(webLibModule, getProject().getFolder("lib-src"), "/");
		
		ModuleType webLibType = ModuleCoreFactory.eINSTANCE.createModuleType();
		webLibType.setModuleTypeId(IModuleConstants.UTILITY_MODULE); 
		webLibModule.setModuleType(webLibType);
		
		addDependentModule(webModule, URI.createURI("WEB-INF/lib"), URI.createURI(webLibModule.getDeployedName()));
		addDependentModule(webModule, URI.createURI("WEB-INF/lib"), URI.createURI("module:/resource/AnotherProject/" + webLibModule.getDeployedName()));
		addDependentModule(webModule, URI.createURI("WEB-INF/lib"), URI.createURI("module:/classpath/AnotherTeamsUtilities.jar"));

	}

	public IFile getModuleRelativeFile(String aModuleRelativePath) {
		return getProject().getFile(new Path(getModulesFolder() + IPath.SEPARATOR + aModuleRelativePath));
	}

	public void addResource(WorkbenchModule aModule, IResource aSourceFile, String aDeployPath) {
		WorkbenchModuleResource resource = ModuleCoreFactory.eINSTANCE.createWorkbenchModuleResource();
		resource.setSourcePath(URI.createURI(aSourceFile.getFullPath().toString()));
		resource.setDeployedPath(URI.createURI(aDeployPath));
		aModule.getResources().add(resource);
	}

	public WorkbenchModule addWorkbenchModule(ProjectModules theModules, String aDeployedName, URI aHandle) {
		WorkbenchModule module = ModuleCoreFactory.eINSTANCE.createWorkbenchModule();
		module.setDeployedName(aDeployedName);
		module.setHandle(aHandle);
		theModules.getWorkbenchModules().add(module);
		return module;
	}

	public void addDependentModule(WorkbenchModule aModule, URI aDeployedPath, URI aHandle) {
		DependentModule aClasspathDependentModule = ModuleCoreFactory.eINSTANCE.createDependentModule();
		aClasspathDependentModule.setDeployedPath(aDeployedPath);
		aClasspathDependentModule.setHandle(aHandle);
		aModule.getModules().add(aClasspathDependentModule);
	}

	/**
	 * @return
	 */
	public String getModulesFolder() {
		return "MyWebModule"; //$NON-NLS-1$
	}

	/**
	 * @return
	 */
	public String getTestResourcePath() {
		return "WEB-INF/web.xml"; //$NON-NLS-1$
	}

	/**
	 * @param containingProject
	 * @return
	 */
	private ModuleCoreNature getNature(IProject aProject) {
		try {
			return (ModuleCoreNature) aProject.getNature(ModuleCoreNature.MODULE_NATURE_ID);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param segments
	 * @return
	 */
	public IProject getProject(String aProjectName) throws Exception {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(aProjectName);

		if (!project.exists()) {
			try {
				WebModuleCreationDataModel dataModel = new WebModuleCreationDataModel();
				dataModel.setProperty(WebModuleCreationDataModel.PROJECT_NAME, getProjectName());
				dataModel.setBooleanProperty(WebModuleCreationDataModel.IS_FLEXIBLE_PROJECT, true);
				dataModel.getDefaultOperation().run(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (!project.hasNature(ModuleCoreNature.MODULE_NATURE_ID)) {
			IProjectDescription description = project.getDescription();
			String[] natureIds = description.getNatureIds();
			String[] newNatureIds = new String[natureIds.length + 1];
			System.arraycopy(natureIds, 0, newNatureIds, 1, natureIds.length);
			newNatureIds[0] = ModuleCoreNature.MODULE_NATURE_ID;
			description.setNatureIds(newNatureIds);
			project.setDescription(description, null);
		}
		return project;
	}

	/**
	 * 
	 */
	public void testLoadingDocument() {
		ModuleEditModelFactory factory = new ModuleEditModelFactory();

		URI moduleURI = URI.createURI(PlatformURLModuleConnection.MODULE_PROTOCOL + IPath.SEPARATOR + getProjectName() + IPath.SEPARATOR + getModuleName());
		URI ddURI = URI.createURI(IPath.SEPARATOR + "WEB-INF" + IPath.SEPARATOR + "web.xml");

		Map params = new HashMap();
		params.put(ModuleEditModelFactory.PARAM_MODULE_URI, moduleURI);
		EMFWorkbenchContext context = createEMFWorkbenchContext();
		ArtifactEditModel editModel = (ArtifactEditModel) factory.createEditModelForWrite(IModuleTypesConstants.MODULE_TYPE_WEB, context, params);
		Resource ddResource = editModel.getResource(ddURI);
		EObject rootObject = (EObject) ddResource.getContents().get(0);
		if (rootObject == null)
			System.out.println("Read failed.");
		else
			System.out.println("Found WebApp: " + ((WebApp) rootObject).getDisplayName());

	}

	public EMFWorkbenchContext createEMFWorkbenchContext() {
		if (emfContext == null)
			emfContext = new EMFWorkbenchContext(getProject());
		return emfContext;
	}

	public IProject getProject() {
		if (project == null) {
			project = ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName());
			if (!project.exists()) {
				try {
					WebModuleCreationDataModel dataModel = new WebModuleCreationDataModel();
					dataModel.setProperty(WebModuleCreationDataModel.PROJECT_NAME, getProjectName());
					dataModel.getDefaultOperation().run(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return project;
	}

	public URI getModuleURI() {
		return URI.createURI("module:/resource/" + getProjectName() + IPath.SEPARATOR + getModuleName() + ".war");
	}

	public String getProjectName() {
		return "MyModulesProject";
	}

	public String getModuleName() {
		return "MyWebModule";
	}



}
