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
/*
 * Created on Nov 6, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.archive.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jst.j2ee.application.operations.FlexibleJ2EEModuleCreationOperation;
import org.eclipse.jst.j2ee.application.operations.FlexibleJ2EEModuleCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.IAnnotationsDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.web.operations.WebPropertiesUtil;
import org.eclipse.jst.j2ee.internal.web.util.WebArtifactEdit;
import org.eclipse.wst.common.modulecore.ModuleCore;
import org.eclipse.wst.common.modulecore.ModuleCoreFactory;
import org.eclipse.wst.common.modulecore.ComponentType;
import org.eclipse.wst.common.modulecore.ProjectComponents;
import org.eclipse.wst.common.modulecore.WorkbenchComponent;
import org.eclipse.wst.common.modulecore.ComponentResource;
import org.eclipse.wst.common.modulecore.internal.operation.ArtifactEditOperation;
import org.eclipse.wst.common.modulecore.internal.operation.ArtifactEditOperationDataModel;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;

public class FlexibleWebModuleCreationOperation extends FlexibleJ2EEModuleCreationOperation {
	public FlexibleWebModuleCreationOperation(FlexibleWebModuleCreationDataModel dataModel) {
		super(dataModel);
	}

	public FlexibleWebModuleCreationOperation() {
		super();
	}


	protected void createDeploymentDescriptor(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		
		
		String moduleName = (String)operationDataModel.getProperty(ArtifactEditOperationDataModel.MODULE_NAME);
		String projName = operationDataModel.getTargetProject().getName();

		IFolder moduleFolder = operationDataModel.getTargetProject().getFolder( moduleName );

		if (!moduleFolder.exists()) {
			moduleFolder.create(true, true, null);
		}

		IFolder javaSourceFolder = moduleFolder.getFolder( "JavaSource" );
		if (!javaSourceFolder.exists()) {
			javaSourceFolder.create(true, true, null);
		}
		
		IFolder webContentFolder = moduleFolder.getFolder( "WebContent" );
		if (!webContentFolder.exists()) {
			webContentFolder.create(true, true, null);
		}
		
		IFolder metainf = webContentFolder.getFolder(J2EEConstants.META_INF);
		if (!metainf.exists()) {
			IFolder parent = metainf.getParent().getFolder(null);
			if (!parent.exists()) {
				parent.create(true, true, null);
			}
			metainf.create(true, true, null);
		}
		
		IFolder webinf = webContentFolder.getFolder(J2EEConstants.WEB_INF);
		if (!webinf.exists()) {
			webinf.create(true, true, null);
		}
		
		IFolder lib = webinf.getFolder("lib"); //$NON-NLS-1$
		if (!lib.exists()) {
			lib.create(true, true, null);
		}
		
        ModuleCore moduleCore = null;
        WorkbenchComponent wbmodule = null;
        try {
            moduleCore = ModuleCore.getModuleCoreForRead(operationDataModel.getTargetProject());
            wbmodule = moduleCore.findWorkbenchModuleByDeployName(operationDataModel.getStringProperty(ArtifactEditOperationDataModel.MODULE_DEPLOY_NAME));
        } finally {
            if (null != moduleCore) {
                moduleCore.dispose();
            }
        }		


        WebArtifactEdit webEdit = null;
       	try{

       		webEdit = WebArtifactEdit.getWebArtifactEditForWrite( wbmodule );
       		if(webEdit != null) {
           		webEdit.createModelRoot();
       		}
       	}
       	catch(Exception e){
            e.printStackTrace();
       	} finally {
       		if(webEdit != null)
       			webEdit.dispose();
       	}					
	
	
	
	}

	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		
		createModule(monitor);

		super.execute(monitor);
		FlexibleJ2EEModuleCreationDataModel dataModel = (FlexibleJ2EEModuleCreationDataModel) operationDataModel;		
		if (((FlexibleWebModuleCreationDataModel) operationDataModel).getBooleanProperty(IAnnotationsDataModel.USE_ANNOTATIONS))
			addAnnotationsBuilder();		
	}
    
	/**
	 * @param projectModules
	 */
	private void addContent(ProjectComponents projectModules) {
		
	    WorkbenchComponent webModule = addWorkbenchModule(projectModules, getModuleDeployName(), createModuleURI()); //$NON-NLS-1$
		
		addResource(webModule, getModuleRelativeFile(getWebContentSourcePath( getModuleName() ), getProject()), getWebContentDeployPath());
		addResource(webModule, getModuleRelativeFile(getJavaSourceSourcePath( getModuleName() ), getProject()), getJavaSourceDeployPath());
	}
	
	
	public IFile getModuleRelativeFile(String aModuleRelativePath, IProject project) {
		return getProject().getFile(new Path(IPath.SEPARATOR + aModuleRelativePath));
	}
	
	/**
	 * @return
	 */
	private URI createModuleURI() {
		return URI.createURI("module:/resource/"+getProject().getName()+IPath.SEPARATOR+ getModuleDeployName()); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public void addResource(WorkbenchComponent aModule, IResource aSourceFile, String aDeployPath) {
		ComponentResource resource = ModuleCoreFactory.eINSTANCE.createComponentResource();		
		resource.setSourcePath(URI.createURI(aSourceFile.getFullPath().toString()));
		resource.setDeployedPath(URI.createURI(aDeployPath));
		aModule.getResources().add(resource);
	}
	public WorkbenchComponent addWorkbenchModule(ProjectComponents theModules, String aDeployedName, URI aHandle) {
		WorkbenchComponent module = ModuleCoreFactory.eINSTANCE.createWorkbenchComponent();
		module.setHandle(aHandle);  
		module.setDeployedName(aDeployedName);  
		ComponentType type = ModuleCoreFactory.eINSTANCE.createComponentType();
		type.setModuleTypeId(IModuleConstants.JST_WEB_MODULE);
		module.setComponentType(type);
		theModules.getComponents().add(module);
		return module;
	}

	
	public IProject getProject() {
		FlexibleWebModuleCreationDataModel dataModel = (FlexibleWebModuleCreationDataModel) operationDataModel;
		return dataModel.getTargetProject();
	}

	/**
	 * @return
	 */
	public String getJavaSourceSourcePath(String moduleName) {
		return "/" + moduleName +"/JavaSource"; //$NON-NLS-1$
	}
	
	/**
	 * @return
	 */
	public String getJavaSourceDeployPath() {
		return "/WEB-INF/classes"; //$NON-NLS-1$
	}
	
	/**
	 * @return
	 */
	public String getWebContentSourcePath(String moduleName) {
		return "/" + moduleName + "/WebContent"; //$NON-NLS-1$
	}
	
	/**
	 * @return
	 */
	public String getWebContentDeployPath() {
		return "/"; //$NON-NLS-1$
	}
	
	public String getModuleName() {
		return (String)operationDataModel.getProperty(FlexibleWebModuleCreationDataModel.MODULE_NAME);
	}
	
	public String getModuleDeployName() {
		return (String)operationDataModel.getProperty(FlexibleWebModuleCreationDataModel.MODULE_DEPLOY_NAME);
	}
	

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.application.operations.FlexibileJ2EECreationOperation#createModule(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void createModule(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
    	ModuleCore moduleCore = null;
		try {
			IProject containingProject = getProject();
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
}