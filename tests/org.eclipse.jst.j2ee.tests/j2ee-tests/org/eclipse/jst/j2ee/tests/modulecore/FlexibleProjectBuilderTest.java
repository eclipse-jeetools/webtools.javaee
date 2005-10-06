/*******************************************************************************
 * Copyright (c) 2003, 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.tests.modulecore;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jst.common.jdt.internal.integration.IJavaProjectCreationProperties;
import org.eclipse.jst.common.jdt.internal.integration.JavaProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentCreationDataModelProvider;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.datamodel.properties.IComponentCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.ComponentResource;
import org.eclipse.wst.common.componentcore.internal.ComponentType;
import org.eclipse.wst.common.componentcore.internal.ComponentcoreFactory;
import org.eclipse.wst.common.componentcore.internal.ModuleStructuralModel;
import org.eclipse.wst.common.componentcore.internal.ProjectComponents;
import org.eclipse.wst.common.componentcore.internal.ReferencedComponent;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.internal.emfworkbench.EMFWorkbenchContext;

public class FlexibleProjectBuilderTest extends TestCase {
	public static final String MODULE__RESOURCE_URI_PROTOCOL = "module:/resource/";
	private IProject project;
	private EMFWorkbenchContext emfContext;

	public interface IModuleTypesConstants {
		String MODULE_TYPE_WEB = IModuleConstants.WST_WEB_MODULE;
	}

    public FlexibleProjectBuilderTest(String name) {
        super(name);
        // TODO Auto-generated constructor stub
    }
    
	public static Test suite() {
		// return new TestSuite(ModuleEditModelTest.class);
		TestSuite suite = new TestSuite();
		suite.addTest(new FlexibleProjectBuilderTest("testFlexibleProjectBuilderOutput"));
		return suite;
	}
	
    /*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	public void setUp() throws Exception {
	    IWorkspaceDescription desc = ResourcesPlugin.getWorkspace().getDescription();
	    desc.setAutoBuilding(false);
	    ResourcesPlugin.getWorkspace().setDescription(desc);
		getProjectForWebModuleAndLocalWebLib().delete(true, null);
		getProjectForRemoteWebLib().delete(true, null);
		super.setUp();
	}
	/**
	 * 
	 */
	public void testFlexibleProjectBuilderOutput() throws Exception {
	    setupContent();
	    buildProjects();
	}

	
    /**
     * 
     */
    private void buildProjects() {
        try {
    	    ResourcesPlugin.getWorkspace().build(IncrementalProjectBuilder.FULL_BUILD, null);           
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

	public void setupContent() throws Exception {
		StructureEdit localModuleCore = null;
		try {
			getProjectForWebModuleAndLocalWebLib();
			
			IProject containingProject = StructureEdit.getContainingProject(getWebModuleURI());
			localModuleCore = StructureEdit.getStructureEditForWrite(containingProject); 

			createLocalModules(localModuleCore);

			// will setup and handle creating the modules model
			getProjectForRemoteWebLib();

			WorkbenchComponent webModule = localModuleCore.getComponent();

			addDependentModule(webModule, new Path("WEB-INF/lib"), getLocalWebLibraryModuleURI());
			addDependentModule(webModule, new Path("WEB-INF/lib"), getRemoteWebLibraryModuleURI());

			localModuleCore.saveIfNecessary(null);

		} finally {
			if (localModuleCore != null)
				localModuleCore.dispose();
		}

	}
	
	public IProject getProjectForWebModuleAndLocalWebLib() throws Exception {
		return getWebProject(getWebModuleAndLocalWebLibModuleProjectName());
	}

	public IProject getProjectForRemoteWebLib() throws Exception {
		return getJavaProject(getRemoteWebLibModuleProjectName());
	}
	public String getWebModuleAndLocalWebLibModuleProjectName() {
		return "MyModulesProject"; //$NON-NLS-1$
	}

	public String getRemoteWebLibModuleProjectName() {
		return "RemoteWebLibProject"; //$NON-NLS-1$
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
			IDataModel dataModel = DataModelFactory.createDataModel(new WebComponentCreationDataModelProvider());
			dataModel.setProperty(IComponentCreationDataModelProperties.PROJECT_NAME, aProjectName);
			dataModel.setProperty(IComponentCreationDataModelProperties.COMPONENT_NAME, aProjectName);
			// dataModel.setProperty(WebComponentCreationDataModel.IS_FLEXIBLE_PROJECT, Boolean.TRUE);
			dataModel.getDefaultOperation().execute(new NullProgressMonitor(), null);
		}
		return ResourcesPlugin.getWorkspace().getRoot().getProject(aProjectName);
	}

	public IProject createJavaProject(String aProjectName) throws Exception {

		project = ResourcesPlugin.getWorkspace().getRoot().getProject(aProjectName);
		if (!project.exists()) {
			IDataModel dataModel = DataModelFactory.createDataModel(new JavaProjectCreationDataModelProvider());
			dataModel.setProperty(IJavaProjectCreationProperties.PROJECT_NAME, aProjectName);
			dataModel.getDefaultOperation().execute(new NullProgressMonitor(),null);
			project = ResourcesPlugin.getWorkspace().getRoot().getProject(aProjectName);
			ModuleCoreNature.addModuleCoreNatureIfNecessary(project, null);

			ModuleStructuralModel structuralModel = null;
			try {
				structuralModel = ModuleCoreNature.getModuleCoreNature(project).getModuleStructuralModelForWrite(this);
				structuralModel.prepareProjectModulesIfNecessary();
				StructureEdit moduleCore = (StructureEdit) structuralModel.getAdapter(StructureEdit.ADAPTER_TYPE);
				String deployedName = aProjectName + ".jar";
				URI moduleURI = URI.createURI(MODULE__RESOURCE_URI_PROTOCOL + aProjectName + IPath.SEPARATOR + deployedName);
				WorkbenchComponent utilityModule = addWorkbenchModule(moduleCore.getComponentModelRoot(), deployedName, moduleURI);
				IResource sourceFolder = project.getFolder("src");
				addResource(utilityModule, sourceFolder, "/"); //$NON-NLS-1$

				ComponentType utilityModuleType = ComponentcoreFactory.eINSTANCE.createComponentType();
				utilityModuleType.setComponentTypeId(IModuleConstants.JST_UTILITY_MODULE);
				utilityModule.setComponentType(utilityModuleType);

				structuralModel.saveIfNecessary(this);
			} finally {
				if (structuralModel != null)
					structuralModel.releaseAccess(this);
			}
		}
		return ResourcesPlugin.getWorkspace().getRoot().getProject(aProjectName);
	}
	
	public void addResource(WorkbenchComponent aModule, IResource aSourceFile, String aDeployPath) {
		ComponentResource resource = ComponentcoreFactory.eINSTANCE.createComponentResource();
		resource.setSourcePath(aSourceFile.getFullPath());
		resource.setRuntimePath(new Path(aDeployPath));
		aModule.getResources().add(resource);
	}

	public WorkbenchComponent addWorkbenchModule(ProjectComponents theModules, String aDeployedName, URI aHandle) {
		WorkbenchComponent module = ComponentcoreFactory.eINSTANCE.createWorkbenchComponent();
		module.setName(aDeployedName); 
		theModules.getComponents().add(module);
		return module;
	}

	public void addDependentModule(WorkbenchComponent aModule, IPath aDeployedPath, URI aHandle) {
		ReferencedComponent aClasspathDependentModule = ComponentcoreFactory.eINSTANCE.createReferencedComponent();
		aClasspathDependentModule.setRuntimePath(aDeployedPath);
		aClasspathDependentModule.setHandle(aHandle);
		aModule.getReferencedComponents().add(aClasspathDependentModule);
	}
	
	public void createLocalModules(StructureEdit moduleCore) throws Exception {

		ProjectComponents projectModules = moduleCore.getComponentModelRoot();

		WorkbenchComponent webLibraryModule = addWorkbenchModule(projectModules, getLocalWebLibraryDeployedName(), getLocalWebLibraryModuleURI());
		IFolder localWebLibrary = getProjectForWebModuleAndLocalWebLib().getFolder(getLocalWebLibraryFolderName());
		if (!localWebLibrary.exists())
			localWebLibrary.create(true, true, null);
		addResource(webLibraryModule, localWebLibrary, "/");

		ComponentType webModuleType = ComponentcoreFactory.eINSTANCE.createComponentType();
		webModuleType.setComponentTypeId(IModuleConstants.JST_UTILITY_MODULE);
		webLibraryModule.setComponentType(webModuleType);
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
