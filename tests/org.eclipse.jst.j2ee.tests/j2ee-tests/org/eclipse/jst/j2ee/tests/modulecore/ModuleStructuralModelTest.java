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
import java.util.List;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.common.jdt.internal.integration.JavaProjectCreationDataModel;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebModuleCreationDataModel;
import org.eclipse.jst.j2ee.internal.web.util.WebArtifactEdit;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.wst.common.internal.emfworkbench.EMFWorkbenchContext;
import org.eclipse.wst.common.modulecore.ArtifactEditModel;
import org.eclipse.wst.common.modulecore.DependentModule;
import org.eclipse.wst.common.modulecore.ModuleCore;
import org.eclipse.wst.common.modulecore.ModuleCoreFactory;
import org.eclipse.wst.common.modulecore.ModuleCoreNature;
import org.eclipse.wst.common.modulecore.ModuleStructuralModel;
import org.eclipse.wst.common.modulecore.ModuleType;
import org.eclipse.wst.common.modulecore.ProjectModules;
import org.eclipse.wst.common.modulecore.WorkbenchModule;
import org.eclipse.wst.common.modulecore.WorkbenchModuleResource;
import org.eclipse.wst.common.modulecore.internal.impl.ArtifactEditModelFactory;
import org.eclipse.wst.common.modulecore.internal.impl.PlatformURLModuleConnection;
import org.eclipse.wst.common.modulecore.internal.impl.ResourceTreeRoot;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;
import org.eclipse.wst.common.modulecore.internal.util.SourcePathProvider;

/**
 * <p>
 * The following class is experimental until fully documented.
 * </p>
 */
public class ModuleStructuralModelTest extends TestCase {

	public static final String MODULE__RESOURCE_URI_PROTOCOL = "module:/resource/";
	private IProject project;
	private EMFWorkbenchContext emfContext;

	public interface IModuleTypesConstants {
		String MODULE_TYPE_WEB = "org.eclipse.jst.modules.web"; //$NON-NLS-1$
	}


	public ModuleStructuralModelTest(String name) {
		super(name);
	}

	public static Test suite() {
		// return new TestSuite(ModuleEditModelTest.class);
		TestSuite suite = new TestSuite();
		suite.addTest(new ModuleStructuralModelTest("testResolveDependentModule"));
		return suite;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	public void setUp() throws Exception {
		getProjectForWebModuleAndLocalWebLib().delete(true, null);
		getProjectForRemoteWebLib().delete(true, null);
		setupContent();
		super.setUp();
	}

	/**
	 * 
	 */
	public void testResourceTree() throws Exception {
		ModuleCore moduleCore = null; 
		try {
			/* We need to find the project */
			moduleCore = ModuleCore.getModuleCoreForRead(ModuleCore.getContainingProject(getWebModuleURI()));
			
			WorkbenchModule module = moduleCore.getWorkbenchModules()[0];
			ResourceTreeRoot sourceRoot = new ResourceTreeRoot(module, SourcePathProvider.INSTANCE); 
			WorkbenchModuleResource[] resources = sourceRoot.findModuleResources(URI.createURI("/"+getWebModuleAndLocalWebLibModuleProjectName()+"/WebContent/WEB-INF/web.xml"));
			System.out.println(resources[0] != null ? resources[0].getSourcePath().toString() : "NOT FOUND");

		} finally {
			if (moduleCore != null)
				moduleCore.dispose();
		}

	}

	public void testResolveDependentModule() throws Exception {

		ModuleCore moduleCore = null;
		try {
			IProject containingProject = ModuleCore.getContainingProject(getWebModuleURI()); 
			moduleCore = ModuleCore.getModuleCoreForRead(containingProject);
			WorkbenchModule[] modules = moduleCore.getWorkbenchModules();
			ProjectModules pm = moduleCore.getModuleModelRoot();
			Class clazz = moduleCore.getClass();
			String name = ModuleCore.getDeployedName(getWebModuleURI());
			List dependentModules = null;
			for(int i=0; i<modules.length; i++) {
				System.out.println("Module: "+modules[i].getDeployedName());
				List list = modules[i].getResources();
				for (int j = 0; j < list.size(); j++) {
					WorkbenchModuleResource wmr = (WorkbenchModuleResource)list.get(j);
					IResource er = ModuleCore.getEclipseResource(wmr);
				}
				// test modulecore API
				dependentModules = modules[i].getModules(); 			
				for(int dependentIndex=0; dependentIndex<dependentModules.size(); dependentIndex++) {
					DependentModule dependentModule = (DependentModule)dependentModules.get(dependentIndex);
					WorkbenchModule resolvedModule = moduleCore.findWorkbenchModuleByModuleURI(dependentModule.getHandle());
					System.out.println("\tDependentModule: "+resolvedModule.getDeployedName()+ " in " + resolvedModule.getHandle());
					boolean b = moduleCore.isLocalDependency(dependentModule);
				}
			}
		} finally {
			if(moduleCore != null)
				moduleCore.dispose();	
		}
	}

	public void testLoadResource() throws Exception {

		ArtifactEditModel artifactModel = null;
		try {
			URI moduleURI = URI.createURI(MODULE__RESOURCE_URI_PROTOCOL + getWebModuleAndLocalWebLibModuleProjectName() + "/" + getWebModuleAndLocalWebLibModuleProjectName() + ".war");
			artifactModel = getNature(getProjectForWebModuleAndLocalWebLib()).getArtifactEditModelForRead(moduleURI, this);
			WebArtifactEdit editUtility = (WebArtifactEdit) artifactModel.getAdapter(WebArtifactEdit.ADAPTER_TYPE);

			WebApp webApp = (WebApp) editUtility.getDeploymentDescriptorRoot();
			if (webApp == null)
				System.out.println("Read failed.");
			else
				System.out.println("Found WebApp: " + webApp.getDisplayName());
		} finally {
			if (artifactModel != null)
				artifactModel.releaseAccess(this);
		}
	}

	public void testURIAPI() throws Exception {
		URI uri = URI.createURI(MODULE__RESOURCE_URI_PROTOCOL + getWebModuleAndLocalWebLibModuleProjectName() + IPath.SEPARATOR + getWebModuleDeployedName() + ".war" + IPath.SEPARATOR + getTestResourcePath());
		System.out.println("URI : \"" + uri.toString() + "\"" + " with scheme \"" + uri.scheme() + "\" has " + uri.segmentCount() + " segments. They are ...");
		String[] segments = uri.segments();
		for (int i = 0; i < segments.length; i++)
			System.out.println("[" + i + "]: " + segments[i]);


		/* Determine if the URI is for a resource or binary module */

		if (PlatformURLModuleConnection.RESOURCE_MODULE.equals(segments[0])) {
			ModuleCore moduleCore = null;
			try {
				moduleCore = ModuleCore.getModuleCoreForRead(ModuleCore.getContainingProject(uri)); 
				WorkbenchModuleResource[] resource = moduleCore.findWorkbenchModuleResourceByDeployPath(uri);
				System.out.println(resource != null ? resource[0].getSourcePath().toString() : "NOT FOUND");
			} finally {
				if (moduleCore != null)
					moduleCore.dispose();
			}
		} else if (PlatformURLModuleConnection.BINARY_MODULE.equals(segments[0])) {

		}
	}

	public void testLoadingDocument() throws Exception {
		ArtifactEditModelFactory factory = new ArtifactEditModelFactory();

		URI moduleURI = URI.createURI(PlatformURLModuleConnection.MODULE_PROTOCOL + IPath.SEPARATOR + getWebModuleAndLocalWebLibModuleProjectName() + IPath.SEPARATOR + getWebModuleDeployedName());
		URI ddURI = URI.createURI(IPath.SEPARATOR + "WEB-INF" + IPath.SEPARATOR + "web.xml");

		Map params = new HashMap();
		params.put(ArtifactEditModelFactory.PARAM_MODULE_URI, moduleURI);
		EMFWorkbenchContext context = createEMFWorkbenchContext();
		ArtifactEditModel editModel = (ArtifactEditModel) factory.createEditModelForWrite(IModuleTypesConstants.MODULE_TYPE_WEB, context, params);
		Resource ddResource = editModel.getResource(ddURI);
		EObject rootObject = (EObject) ddResource.getContents().get(0);
		if (rootObject == null)
			System.out.println("Read failed.");
		else
			System.out.println("Found WebApp: " + ((WebApp) rootObject).getDisplayName());

	}

	public void setupContent() throws Exception {
		ModuleCore localModuleCore = null;
		try {
			getProjectForWebModuleAndLocalWebLib();
			
			IProject containingProject = ModuleCore.getContainingProject(getWebModuleURI());
			localModuleCore = ModuleCore.getModuleCoreForWrite(containingProject); 

			createLocalModules(localModuleCore);

			// will setup and handle creating the modules model
			getProjectForRemoteWebLib();

			WorkbenchModule webModule = localModuleCore.findWorkbenchModuleByDeployName(getWebModuleDeployedName());

			addDependentModule(webModule, URI.createURI("WEB-INF/lib"), getLocalWebLibraryModuleURI());
			addDependentModule(webModule, URI.createURI("WEB-INF/lib"), getRemoteWebLibraryModuleURI());

			localModuleCore.saveIfNecessary(null);

		} finally {
			if (localModuleCore != null)
				localModuleCore.dispose();
		}

	}

	public void createLocalModules(ModuleCore moduleCore) throws Exception {

		ProjectModules projectModules = moduleCore.getModuleModelRoot();

		WorkbenchModule webLibraryModule = addWorkbenchModule(projectModules, getLocalWebLibraryDeployedName(), getLocalWebLibraryModuleURI());
		IFolder localWebLibrary = getProjectForWebModuleAndLocalWebLib().getFolder(getLocalWebLibraryFolderName());
		if (!localWebLibrary.exists())
			localWebLibrary.create(true, true, null);
		addResource(webLibraryModule, localWebLibrary, "/");

		ModuleType webModuleType = ModuleCoreFactory.eINSTANCE.createModuleType();
		webModuleType.setModuleTypeId(IModuleConstants.JST_UTILITY_MODULE);
		webLibraryModule.setModuleType(webModuleType);
	}

	public IFile getModuleRelativeFile(String aModuleRelativePath) throws Exception {
		return getProjectForWebModuleAndLocalWebLib().getFile(new Path(getModulesFolder() + IPath.SEPARATOR + aModuleRelativePath));
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


	public EMFWorkbenchContext createEMFWorkbenchContext() throws Exception {
		if (emfContext == null)
			emfContext = new EMFWorkbenchContext(getProjectForWebModuleAndLocalWebLib());
		return emfContext;
	}

	public IProject getProjectForWebModuleAndLocalWebLib() throws Exception {
		return getWebProject(getWebModuleAndLocalWebLibModuleProjectName());
	}

	public IProject getProjectForRemoteWebLib() throws Exception {
		return getJavaProject(getRemoteWebLibModuleProjectName());
	}

	public IProject getWebProject(String aProjectName) throws Exception {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(aProjectName);
		if (!project.exists())
			project = createWebProject(aProjectName);
		return project;
	}

	public IProject getJavaProject(String aProjectName) throws Exception {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(aProjectName);
		if (!project.exists()) 
			createJavaProject(aProjectName); 
		return project;
	}

	public IProject createWebProject(String aProjectName) throws Exception {

		project = ResourcesPlugin.getWorkspace().getRoot().getProject(aProjectName);
		if (!project.exists()) {
			WebModuleCreationDataModel dataModel = new WebModuleCreationDataModel();
			dataModel.setProperty(WebModuleCreationDataModel.PROJECT_NAME, aProjectName);
			dataModel.setProperty(WebModuleCreationDataModel.IS_FLEXIBLE_PROJECT, Boolean.TRUE);
			dataModel.setProperty(WebModuleCreationDataModel.ADD_TO_EAR, Boolean.FALSE);
			dataModel.setProperty(WebModuleCreationDataModel.ADD_SERVER_TARGET, Boolean.FALSE);
//			dataModel.setProperty(WebModuleCreationDataModel.SERVER_TARGET_ID, AllPluginTests.JONAS_SERVER.getId());
			dataModel.getDefaultOperation().run(null);
		}
		return ResourcesPlugin.getWorkspace().getRoot().getProject(aProjectName);
	}

	public IProject createJavaProject(String aProjectName) throws Exception {

		project = ResourcesPlugin.getWorkspace().getRoot().getProject(aProjectName);
		if (!project.exists()) {
			JavaProjectCreationDataModel dataModel = new JavaProjectCreationDataModel();
			dataModel.setProperty(JavaProjectCreationDataModel.PROJECT_NAME, aProjectName);
			dataModel.getDefaultOperation().run(null);
			project = ResourcesPlugin.getWorkspace().getRoot().getProject(aProjectName);
			ModuleCoreNature.addModuleCoreNatureIfNecessary(project, null);

			ModuleStructuralModel structuralModel = null;
			try {
				structuralModel = ModuleCoreNature.getModuleCoreNature(project).getModuleStructuralModelForWrite(this);
				structuralModel.prepareProjectModulesIfNecessary();
				ModuleCore moduleCore = (ModuleCore) structuralModel.getAdapter(ModuleCore.ADAPTER_TYPE);
				String deployedName = aProjectName + ".jar";
				URI moduleURI = URI.createURI(MODULE__RESOURCE_URI_PROTOCOL + aProjectName + IPath.SEPARATOR + deployedName);
				WorkbenchModule utilityModule = addWorkbenchModule(moduleCore.getModuleModelRoot(), deployedName, moduleURI);
				IResource sourceFolder = project.getFolder("src");
				addResource(utilityModule, sourceFolder, "/"); //$NON-NLS-1$

				ModuleType utilityModuleType = ModuleCoreFactory.eINSTANCE.createModuleType();
				utilityModuleType.setModuleTypeId(IModuleConstants.JST_UTILITY_MODULE);
				utilityModule.setModuleType(utilityModuleType);

				structuralModel.saveIfNecessary(this);
			} finally {
				if (structuralModel != null)
					structuralModel.releaseAccess(this);
			}
		}
		return ResourcesPlugin.getWorkspace().getRoot().getProject(aProjectName);
	}

	public ModuleCoreNature getNature(IProject aProject) {
		try {
			return (ModuleCoreNature) aProject.getNature(ModuleCoreNature.MODULE_NATURE_ID);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return null;
	}

	public URI getWebModuleURI() {
		return URI.createURI(MODULE__RESOURCE_URI_PROTOCOL + getWebModuleAndLocalWebLibModuleProjectName() + IPath.SEPARATOR + getWebModuleDeployedName());
	}

	public URI getLocalWebLibraryModuleURI() {
		return URI.createURI(MODULE__RESOURCE_URI_PROTOCOL + getWebModuleAndLocalWebLibModuleProjectName() + IPath.SEPARATOR + getLocalWebLibraryDeployedName());
	}

	public URI getRemoteWebLibraryModuleURI() {
		return URI.createURI(MODULE__RESOURCE_URI_PROTOCOL + getRemoteWebLibModuleProjectName() + IPath.SEPARATOR + getRemoteWebLibraryDeployedName());
	}

	public String getWebModuleAndLocalWebLibModuleProjectName() {
		return "MyModulesProject"; //$NON-NLS-1$
	}

	public String getRemoteWebLibModuleProjectName() {
		return "RemoteWebLibProject"; //$NON-NLS-1$
	}

	public String getWebModuleDeployedName() {
		return getWebModuleAndLocalWebLibModuleProjectName() + ".war"; //$NON-NLS-1$
	}

	public String getLocalWebLibraryDeployedName() {
		return "LocalWebLibrary.jar"; //$NON-NLS-1$
	}

	public String getRemoteWebLibraryDeployedName() {
		return getRemoteWebLibModuleProjectName() + ".jar"; //$NON-NLS-1$
	}

	public String getModulesFolder() {
		return "MyWebModule"; //$NON-NLS-1$
	}

	public String getTestResourcePath() {
		return "WEB-INF/web.xml"; //$NON-NLS-1$
	}

	public String getLocalWebLibraryFolderName() {
		return "WebLibraryContents";
	}


}
