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
import org.eclipse.jst.j2ee.application.operations.FlexibileJ2EEModuleCreationOperation;
import org.eclipse.jst.j2ee.application.operations.FlexibleJ2EEModuleCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.IAnnotationsDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.web.operations.WebPropertiesUtil;
import org.eclipse.jst.j2ee.internal.web.util.WebArtifactEdit;
import org.eclipse.wst.common.modulecore.ModuleCore;
import org.eclipse.wst.common.modulecore.ModuleCoreFactory;
import org.eclipse.wst.common.modulecore.ModuleType;
import org.eclipse.wst.common.modulecore.ProjectModules;
import org.eclipse.wst.common.modulecore.WorkbenchModule;
import org.eclipse.wst.common.modulecore.WorkbenchModuleResource;
import org.eclipse.wst.common.modulecore.internal.operation.ArtifactEditOperation;
import org.eclipse.wst.common.modulecore.internal.operation.ArtifactEditOperationDataModel;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;

public class FlexibileWebModuleCreationOperation extends FlexibileJ2EEModuleCreationOperation {
	public FlexibileWebModuleCreationOperation(FlexibileWebModuleCreationDataModel dataModel) {
		super(dataModel);
	}

	public FlexibileWebModuleCreationOperation() {
		super();
	}

//	protected void createProject(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
//		super.createModule(monitor);
		//TODO add contextRoot and contentName to workbechModule
		//J2EEWebNatureRuntime nature = J2EEWebNatureRuntime.getRuntime(((WebModuleCreationDataModel)operationDataModel).getTargetProject());
		//WebArtifactEdit webArtifactEdit = (WebArtifactEdit)ModuleCore.getFirstArtifactEditForRead(((WebModuleCreationDataModel)operationDataModel).getTargetProject());
		//nature.getWebSettings().setWebContentName(operationDataModel.getStringProperty(WebModuleCreationDataModel.WEB_CONTENT));
		//nature.getWebSettings().setContextRoot(operationDataModel.getStringProperty(webArtifactEdit.getContextRoot()));
		//URIConverter uriConverter = ((ProjectResourceSet) nature.getResourceSet()).getURIConverter();
		//dont need this, keeps nature in synch with websetting file
		//if (uriConverter instanceof J2EEModuleWorkbenchURIConverterImpl)
		//	((J2EEModuleWorkbenchURIConverterImpl) uriConverter).recomputeContainersIfNecessary();
//	}

	protected void createDeploymentDescriptor(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		
		ArtifactEditOperation op = new ArtifactEditOperation( (FlexibleJ2EEModuleCreationDataModel) operationDataModel){
			protected void execute(IProgressMonitor amonitor) throws CoreException, InvocationTargetException, InterruptedException {

				
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
				
				
				/*
				IFolder metainf = moduleFolder.getFolder(J2EEConstants.META_INF);
				if (!metainf.exists()) {
					IFolder parent = metainf.getParent().getFolder(null);
					if (!parent.exists()) {
						parent.create(true, true, null);
					}
					metainf.create(true, true, null);
				}
				
				IFolder webinf = moduleFolder.getFolder(J2EEConstants.WEB_INF);
				if (!webinf.exists()) {
					webinf.create(true, true, null);
				}
				
				IFolder lib = webinf.getFolder("lib"); //$NON-NLS-1$
				if (!lib.exists()) {
					lib.create(true, true, null);
				}
				*/
		
                WorkbenchModule wbModule = getWorkbenchModule();
                WebArtifactEdit webEdit = null;
               	try{

               		webEdit = WebArtifactEdit.getWebArtifactEditForWrite(wbModule );
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
			
		};

		op.doRun(monitor);
	}

	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		
		createModule(monitor);

		super.execute(monitor);
		FlexibleJ2EEModuleCreationDataModel dataModel = (FlexibleJ2EEModuleCreationDataModel) operationDataModel;		
		if (((FlexibileWebModuleCreationDataModel) operationDataModel).getBooleanProperty(IAnnotationsDataModel.USE_ANNOTATIONS))
			addAnnotationsBuilder();		
	}

    /**
     * 
     */
//    private void createInitialWTPModulesFile() {
//    	ModuleCore moduleCore = null;
//		try {
//			IProject containingProject = getProject();
//			moduleCore = ModuleCore.getModuleCoreForWrite(containingProject);
//			moduleCore.prepareProjectModulesIfNecessary(); 
//			ProjectModules projectModules = moduleCore.getModuleModelRoot();
//			addContent(projectModules);
//			moduleCore.saveIfNecessary(null); 
//		} finally {
//			if(moduleCore != null)
//				moduleCore.dispose();
//		}     
//   }
	
//    private void createModule() {
//    	ModuleCore moduleCore = null;
//		try {
//			IProject containingProject = getProject();
//			moduleCore = ModuleCore.getModuleCoreForWrite(containingProject);
//			moduleCore.prepareProjectModulesIfNecessary(); 
//			ProjectModules projectModules = moduleCore.getModuleModelRoot();
//			addContent(projectModules);
//			moduleCore.saveIfNecessary(null); 
//		} finally {
//			if(moduleCore != null)
//				moduleCore.dispose();
//		}     
//   }
    
	/**
	 * @param projectModules
	 */
	private void addContent(ProjectModules projectModules) {
		
	    WorkbenchModule webModule = addWorkbenchModule(projectModules, getModuleName()+".war", createModuleURI()); //$NON-NLS-1$
		
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
		return URI.createURI("module:/resource/"+getProject().getName()+IPath.SEPARATOR+getModuleName()+".war"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public void addResource(WorkbenchModule aModule, IResource aSourceFile, String aDeployPath) {
		WorkbenchModuleResource resource = ModuleCoreFactory.eINSTANCE.createWorkbenchModuleResource();		
		resource.setSourcePath(URI.createURI(aSourceFile.getFullPath().toString()));
		resource.setDeployedPath(URI.createURI(aDeployPath));
		aModule.getResources().add(resource);
	}
	public WorkbenchModule addWorkbenchModule(ProjectModules theModules, String aDeployedName, URI aHandle) {
		WorkbenchModule module = ModuleCoreFactory.eINSTANCE.createWorkbenchModule();
		module.setHandle(aHandle);  
		module.setDeployedName(aDeployedName);  
		ModuleType type = ModuleCoreFactory.eINSTANCE.createModuleType();
		type.setModuleTypeId(IModuleConstants.JST_WEB_MODULE);
		module.setModuleType(type);
		theModules.getWorkbenchModules().add(module);
		return module;
	}

	
	public IProject getProject() {
		FlexibileWebModuleCreationDataModel dataModel = (FlexibileWebModuleCreationDataModel) operationDataModel;
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
		//return getProject().getName();
		return (String)operationDataModel.getProperty(FlexibileWebModuleCreationDataModel.MODULE_NAME);
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
			ProjectModules projectModules = moduleCore.getModuleModelRoot();
			addContent(projectModules);
			moduleCore.saveIfNecessary(null); 
		} finally {
			if(moduleCore != null)
				moduleCore.dispose();
		}     
		
	}
}