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
package org.eclipse.jst.j2ee.applicationclient.creation;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jst.j2ee.application.operations.FlexibleJ2EEModuleCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.FlexibleJ2EEModuleCreationOperation;
import org.eclipse.jst.j2ee.application.operations.UpdateManifestDataModel;
import org.eclipse.jst.j2ee.applicationclient.internal.modulecore.util.AppClientArtifactEdit;
import org.eclipse.jst.j2ee.common.operations.NewJavaClassDataModel;
import org.eclipse.wst.common.modulecore.ModuleCore;
import org.eclipse.wst.common.modulecore.ModuleCoreFactory;
import org.eclipse.wst.common.modulecore.ComponentType;
import org.eclipse.wst.common.modulecore.ProjectComponents;
import org.eclipse.wst.common.modulecore.WorkbenchComponent;
import org.eclipse.wst.common.modulecore.ComponentResource;
import org.eclipse.wst.common.modulecore.internal.operation.ArtifactEditOperation;
import org.eclipse.wst.common.modulecore.internal.operation.ArtifactEditOperationDataModel;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

public class FlexibleAppClientModuleCreationOperation extends FlexibleJ2EEModuleCreationOperation {

    public FlexibleAppClientModuleCreationOperation(FlexibleAppClientCreationDataModel dataModel) {
        super(dataModel);
    }

    protected void createDeploymentDescriptor(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		ArtifactEditOperation op = new ArtifactEditOperation( (FlexibleJ2EEModuleCreationDataModel) operationDataModel){
		    protected void execute(IProgressMonitor localMonitor) throws CoreException, InvocationTargetException, InterruptedException {
		        AppClientArtifactEdit artifactEdit = null;
		        try{
		            WorkbenchComponent wbModule = getWorkbenchModule();
					artifactEdit =  AppClientArtifactEdit.getAppClientArtifactEditForWrite(wbModule);
					IProject rootProject = getDataModel().getTargetProject();
					URI metainfURI = URI.createURI(rootProject.getName()+IPath.SEPARATOR+getModuleName()+".jar");
					IPath absMetaRoot = ProjectUtilities.getJavaProjectOutputAbsoluteLocation(rootProject).append(metainfURI.toString());
					createFolder(absMetaRoot);
	
					artifactEdit.getDeploymentDescriptorRoot();
					AppClientModuleCreationDataModel dataModel = (AppClientModuleCreationDataModel) operationDataModel;
					if (dataModel.getBooleanProperty(AppClientModuleCreationDataModel.CREATE_DEFAULT_MAIN_CLASS)) {
						NewJavaClassDataModel mainClassDataModel = new NewJavaClassDataModel();
						mainClassDataModel.setProperty(NewJavaClassDataModel.PROJECT_NAME, dataModel.getProjectDataModel().getProject().getName());
						mainClassDataModel.setProperty(NewJavaClassDataModel.CLASS_NAME, "Main"); //$NON-NLS-1$
						mainClassDataModel.setBooleanProperty(NewJavaClassDataModel.MAIN_METHOD, true);
						mainClassDataModel.getDefaultOperation().run(localMonitor);
						dataModel.getUpdateManifestDataModel().setProperty(UpdateManifestDataModel.MAIN_CLASS, mainClassDataModel.getProperty(NewJavaClassDataModel.CLASS_NAME));
					}
		        } finally {
		            if(artifactEdit != null)
		                artifactEdit.dispose();
		            artifactEdit = null;
		        }
			}
		};
        op.doRun(monitor);
    }
	/**
	 * Create a folder for given absolute path
	 * 
	 * @exception com.ibm.itp.core.api.resources.CoreException
	 */
	private IFolder createFolder(IPath absolutePath) throws CoreException {
		if (absolutePath == null || absolutePath.isEmpty())
			return null;
		IFolder folder = getWorkspace().getRoot().getFolder(absolutePath);
		// check if the parent is there
		IContainer parent = folder.getParent();
		if (parent != null && !parent.exists() && (parent instanceof IFolder))
			createFolder(parent.getFullPath());
		if (!folder.exists())
			folder.create(true, true, new NullProgressMonitor());
		return folder;
	}
    /**
     * @return
     */
    private WorkbenchComponent getWorkbenchModule() {
        ModuleCore moduleCore = null;
        WorkbenchComponent module = null;
        try {
            moduleCore = ModuleCore.getModuleCoreForRead(((FlexibleAppClientCreationDataModel)operationDataModel).getTargetProject());
            module = moduleCore.findWorkbenchModuleByDeployName(((FlexibleAppClientCreationDataModel)operationDataModel).getStringProperty(ArtifactEditOperationDataModel.MODULE_NAME));
        } finally {
            if (null != moduleCore) {
                moduleCore.dispose();
            }
        }
        return module;
    }
    
	public FlexibleAppClientCreationDataModel getDataModel() {
		return (FlexibleAppClientCreationDataModel)operationDataModel;
	}
	
    private void createModule() {
    	ModuleCore moduleCore = null;
		try {
			IProject containingProject = getDataModel().getTargetProject();
			moduleCore = ModuleCore.getModuleCoreForWrite(containingProject);
			moduleCore.prepareProjectModulesIfNecessary(); 
			ProjectComponents projectModules = moduleCore.getModuleModelRoot();
			addContent(projectModules);
			moduleCore.saveIfNecessary(null); 
		} finally {
			if(moduleCore != null)
				moduleCore.dispose();
		}     
   }
    
	/**
	 * @param projectModules
	 */
	private void addContent(ProjectComponents projectModules) {
	    WorkbenchComponent webModule = addWorkbenchModule(projectModules, getModuleName()+".jar", createModuleURI()); //$NON-NLS-1$
		IProject aProject = getDataModel().getTargetProject();
		addResource(webModule, getModuleRelativeFile(getContentSourcePath(), aProject), getContentDeployPath());
		addResource(webModule, getModuleRelativeFile(getJavaSourceSourcePath(), aProject), getJavaSourceDeployPath());
	}
	
	/**
	 * @return
	 */
	private URI createModuleURI() {
		return URI.createURI("module:/resource/"+getDataModel().getTargetProject().getName()+IPath.SEPARATOR+getModuleName()+".jar"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public void addResource(WorkbenchComponent aModule, IResource aSourceFile, String aDeployPath) {
		ComponentResource resource = ModuleCoreFactory.eINSTANCE.createComponentResource();		
		resource.setSourcePath(URI.createURI(aSourceFile.getFullPath().toString()));
		resource.setRuntimePath(URI.createURI(aDeployPath));
		aModule.getResources().add(resource);
	}
	public WorkbenchComponent addWorkbenchModule(ProjectComponents theModules, String aDeployedName, URI aHandle) {
		WorkbenchComponent module = ModuleCoreFactory.eINSTANCE.createWorkbenchComponent();
		module.setHandle(aHandle);  
		module.setName(aDeployedName);  
		ComponentType type = ModuleCoreFactory.eINSTANCE.createComponentType();
		type.setModuleTypeId(IModuleConstants.JST_WEB_MODULE);
		module.setComponentType(type);
		theModules.getComponents().add(module);
		return module;
	}
	public IFile getModuleRelativeFile(String aModuleRelativePath, IProject project) {
		return getDataModel().getTargetProject().getFile(new Path(IPath.SEPARATOR + aModuleRelativePath));
	}
	/**
	 * @return
	 */
	public String getJavaSourceSourcePath() {
		return "/appClientModule"; //$NON-NLS-1$
	}
	
	/**
	 * @return
	 */
	public String getJavaSourceDeployPath() {
		return "/appClientModule/"; //$NON-NLS-1$
	}
	
	/**
	 * @return
	 */
	public String getContentSourcePath() {
		return "/appClientModule/META-INF"; //$NON-NLS-1$
	}
	
	/**
	 * @return
	 */
	public String getContentDeployPath() {
		return "/appClientModule/META-INF"; //$NON-NLS-1$
	}
	
	public String getModuleName() {
		return getDataModel().getModuleName();
	}
}
