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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jst.common.jdt.internal.integration.JavaProjectCreationDataModel;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebModuleCreationDataModel;
import org.eclipse.wst.common.internal.emfworkbench.EMFWorkbenchContext;
import org.eclipse.wst.common.modulecore.ReferencedComponent;
import org.eclipse.wst.common.modulecore.ModuleCore;
import org.eclipse.wst.common.modulecore.ModuleCoreFactory;
import org.eclipse.wst.common.modulecore.ModuleCoreNature;
import org.eclipse.wst.common.modulecore.ModuleStructuralModel;
import org.eclipse.wst.common.modulecore.ComponentType;
import org.eclipse.wst.common.modulecore.ProjectComponents;
import org.eclipse.wst.common.modulecore.WorkbenchComponent;
import org.eclipse.wst.common.modulecore.ComponentResource;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;

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
	    checkForEmptyDeployables();
	    buildProjects();
	    checkForDeployablesOutput();
	}

	/**
     * 
     */
    private void checkForDeployablesOutput() {
        try {
            //check web project
            IProject remoteWeb = getProjectForRemoteWebLib();
            IFolder[] remoteOutputFolders = ModuleCore.getOutputContainersForProject(remoteWeb);
            IFolder tempFolder = remoteWeb.getFolder(remoteOutputFolders[0].getName());
            if(!tempFolder.exists())
                assertFalse(".deployables should exist, a build has been run", true);
            
            if(!tempFolder.getFolder(getRemoteWebLibraryDeployedName()).exists())
                assertFalse(".deployables/RemoteWebLibProject.jar should exist, a build has been run", true);
            //check the remote java util project
            IProject localWeb = getProjectForWebModuleAndLocalWebLib();
            IFolder[] localOutputFolders = ModuleCore.getOutputContainersForProject(localWeb);
            tempFolder = localWeb.getFolder(localOutputFolders[0].getName());
            if(!tempFolder.exists())
                assertFalse(".deployables should exist, a build has been run", true);
            tempFolder = tempFolder.getFolder(getWebModuleDeployedName());
            if(!tempFolder.exists())
                assertFalse(getWebModuleDeployedName() + " should exist, a build has been run", true);
            IFolder metaFolder = tempFolder.getFolder("META-INF");
            if(!metaFolder.exists())
                assertFalse(getWebModuleDeployedName() + "/META-INF should exist, a build has been run", true);
            IFile manifest = metaFolder.getFile("MANIFEST.MF");
            if(!manifest.exists())
                assertFalse(getWebModuleDeployedName() + "/META-INF/MANIFEST.MF should exist, a build has been run", true);
            IFolder webInf = tempFolder.getFolder("WEB-INF");
            if(!webInf.exists())
                assertFalse(getWebModuleDeployedName() + "/WEB-INF should exist, a build has been run", true);
            if(!webInf.getFolder("classes").exists())
                assertFalse(getWebModuleDeployedName() + "/WEB-INF/classes should exist, a build has been run", true);
            if(!webInf.getFile("web.xml").exists())
                assertFalse(getWebModuleDeployedName() + "/WEB-INF/web.xml should exist, a build has been run", true);
            IFolder lib = webInf.getFolder("lib");
            if(!lib.getFile(getLocalWebLibraryDeployedName()).exists())
                assertFalse(getWebModuleDeployedName() + "/WEB-INF/lib/LocalWebLibrary.jar should exist, a build has been run", true);  
            if(!lib.getFile(getRemoteWebLibraryDeployedName()).exists())
                assertFalse(getWebModuleDeployedName() + "/WEB-INF/lib/RemoteWebLibProject.jar should exist, a build has been run", true);
          
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }   
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

    /**
     * 
     */
    private void checkForEmptyDeployables() {
        try {
            IProject remoteWeb = getProjectForRemoteWebLib();
            IFolder[] remoteOutputFolders = ModuleCore.getOutputContainersForProject(remoteWeb);
            IFolder folder = remoteWeb.getFolder(remoteOutputFolders[0].getName());
            if(folder.exists())
                assertFalse(".deployables should only exist if a build has been run", true);
            IProject localWeb = getProjectForWebModuleAndLocalWebLib();
            IFolder[] localOutputFolders = ModuleCore.getOutputContainersForProject(localWeb);
            folder = remoteWeb.getFolder(localOutputFolders[0].getName());
            if(folder.exists())
                assertFalse(".deployables should only exist if a build has been run", true);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }    
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

			WorkbenchComponent webModule = localModuleCore.findWorkbenchModuleByDeployName(getWebModuleDeployedName());

			addDependentModule(webModule, URI.createURI("WEB-INF/lib"), getLocalWebLibraryModuleURI());
			addDependentModule(webModule, URI.createURI("WEB-INF/lib"), getRemoteWebLibraryModuleURI());

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
			WebModuleCreationDataModel dataModel = new WebModuleCreationDataModel();
			dataModel.setProperty(WebModuleCreationDataModel.PROJECT_NAME, aProjectName);
			dataModel.setProperty(WebModuleCreationDataModel.IS_FLEXIBLE_PROJECT, Boolean.TRUE);
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
				WorkbenchComponent utilityModule = addWorkbenchModule(moduleCore.getModuleModelRoot(), deployedName, moduleURI);
				IResource sourceFolder = project.getFolder("src");
				addResource(utilityModule, sourceFolder, "/"); //$NON-NLS-1$

				ComponentType utilityModuleType = ModuleCoreFactory.eINSTANCE.createComponentType();
				utilityModuleType.setModuleTypeId(IModuleConstants.JST_UTILITY_MODULE);
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
		ComponentResource resource = ModuleCoreFactory.eINSTANCE.createComponentResource();
		resource.setSourcePath(URI.createURI(aSourceFile.getFullPath().toString()));
		resource.setDeployedPath(URI.createURI(aDeployPath));
		aModule.getResources().add(resource);
	}

	public WorkbenchComponent addWorkbenchModule(ProjectComponents theModules, String aDeployedName, URI aHandle) {
		WorkbenchComponent module = ModuleCoreFactory.eINSTANCE.createWorkbenchComponent();
		module.setDeployedName(aDeployedName);
		module.setHandle(aHandle);
		theModules.getComponents().add(module);
		return module;
	}

	public void addDependentModule(WorkbenchComponent aModule, URI aDeployedPath, URI aHandle) {
		ReferencedComponent aClasspathDependentModule = ModuleCoreFactory.eINSTANCE.createReferencedComponent();
		aClasspathDependentModule.setDeployedPath(aDeployedPath);
		aClasspathDependentModule.setHandle(aHandle);
		aModule.getReferencedComponents().add(aClasspathDependentModule);
	}
	
	public void createLocalModules(ModuleCore moduleCore) throws Exception {

		ProjectComponents projectModules = moduleCore.getModuleModelRoot();

		WorkbenchComponent webLibraryModule = addWorkbenchModule(projectModules, getLocalWebLibraryDeployedName(), getLocalWebLibraryModuleURI());
		IFolder localWebLibrary = getProjectForWebModuleAndLocalWebLib().getFolder(getLocalWebLibraryFolderName());
		if (!localWebLibrary.exists())
			localWebLibrary.create(true, true, null);
		addResource(webLibraryModule, localWebLibrary, "/");

		ComponentType webModuleType = ModuleCoreFactory.eINSTANCE.createComponentType();
		webModuleType.setModuleTypeId(IModuleConstants.JST_UTILITY_MODULE);
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
