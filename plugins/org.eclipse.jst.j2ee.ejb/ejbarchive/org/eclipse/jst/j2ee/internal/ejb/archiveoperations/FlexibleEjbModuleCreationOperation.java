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
package org.eclipse.jst.j2ee.internal.ejb.archiveoperations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
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
import org.eclipse.wst.common.frameworks.internal.enablement.nonui.WorkbenchUtil;
import org.eclipse.wst.common.modulecore.ModuleCore;
import org.eclipse.wst.common.modulecore.ModuleCoreFactory;
import org.eclipse.wst.common.modulecore.ComponentType;
import org.eclipse.wst.common.modulecore.ProjectComponents;
import org.eclipse.wst.common.modulecore.WorkbenchComponent;
import org.eclipse.wst.common.modulecore.ComponentResource;
import org.eclipse.wst.common.modulecore.internal.operation.ArtifactEditOperation;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

public class FlexibleEjbModuleCreationOperation extends FlexibleJ2EEModuleCreationOperation {
	public FlexibleEjbModuleCreationOperation(FlexibleEjbModuleCreationDataModel dataModel) {
		super(dataModel);
	}

	public FlexibleEjbModuleCreationOperation() {
		super();
	}

	protected void createProject(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		super.createModule(monitor);
		//TODO add contextRoot and contentName to workbechModule
		//J2EEWebNatureRuntime nature = J2EEWebNatureRuntime.getRuntime(((WebModuleCreationDataModel)operationDataModel).getTargetProject());
		//WebArtifactEdit webArtifactEdit = (WebArtifactEdit)ModuleCore.getFirstArtifactEditForRead(((WebModuleCreationDataModel)operationDataModel).getTargetProject());
		//nature.getWebSettings().setWebContentName(operationDataModel.getStringProperty(WebModuleCreationDataModel.WEB_CONTENT));
		//nature.getWebSettings().setContextRoot(operationDataModel.getStringProperty(webArtifactEdit.getContextRoot()));
		//URIConverter uriConverter = ((ProjectResourceSet) nature.getResourceSet()).getURIConverter();
		//dont need this, keeps nature in synch with websetting file
		//if (uriConverter instanceof J2EEModuleWorkbenchURIConverterImpl)
		//	((J2EEModuleWorkbenchURIConverterImpl) uriConverter).recomputeContainersIfNecessary();
	}

	protected void createDeploymentDescriptor(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		
		ArtifactEditOperation op = new ArtifactEditOperation( (FlexibleJ2EEModuleCreationDataModel) operationDataModel){
			
		};
		
//		EditModelOperation op = new EditModelOperation((J2EEModuleCreationDataModel) operationDataModel) {
//			protected void execute(IProgressMonitor amonitor) throws CoreException, InvocationTargetException, InterruptedException {
//				WebEditModel model = (WebEditModel) editModel;
//				IFolder moduleRoot = WebPropertiesUtil.getModuleServerRoot(model.getProject());
//				IFolder metainf = moduleRoot.getFolder(J2EEConstants.META_INF);
//				if (!metainf.exists()) {
//					IFolder parent = metainf.getParent().getFolder(null);
//					if (!parent.exists()) {
//						parent.create(true, true, null);
//					}
//					metainf.create(true, true, null);
//				}
//				IFolder webinf = moduleRoot.getFolder(J2EEConstants.WEB_INF);
//				if (!webinf.exists()) {
//					webinf.create(true, true, null);
//				}
//				IFolder lib = webinf.getFolder("lib"); //$NON-NLS-1$
//				if (!lib.exists()) {
//					lib.create(true, true, null);
//				}
//				model.makeDeploymentDescriptorWithRoot();
//			}
//		};
		op.doRun(monitor);
	}

	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		super.execute(monitor);
		FlexibleJ2EEModuleCreationDataModel dataModel = (FlexibleJ2EEModuleCreationDataModel) operationDataModel;

		//.wtpmodule should be created when creating a project
		//createInitialWTPModulesFile(); 
		createModule();

		if (((FlexibleEjbModuleCreationDataModel) operationDataModel).getBooleanProperty(IAnnotationsDataModel.USE_ANNOTATIONS))
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
//			ProjectComponents projectModules = moduleCore.getModuleModelRoot();
//			addContent(projectModules);
//			moduleCore.saveIfNecessary(null); 
//		} finally {
//			if(moduleCore != null)
//				moduleCore.dispose();
//		}     
//   }
	
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
	    WorkbenchComponent webModule = addWorkbenchModule(projectModules, getModuleName()+".war", createModuleURI()); //$NON-NLS-1$
		IProject aProject = getDataModel().getTargetProject();
		addResource(webModule, getModuleRelativeFile(getWebContentSourcePath(), aProject), getWebContentDeployPath());
		addResource(webModule, getModuleRelativeFile(getJavaSourceSourcePath(), aProject), getJavaSourceDeployPath());
	}
	
	/**
	 * @return
	 */
	private URI createModuleURI() {
		return URI.createURI("module:/resource/"+getDataModel().getTargetProject().getName()+IPath.SEPARATOR+getModuleName()+".war"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public void addResource(WorkbenchComponent aModule, IResource aSourceFile, String aDeployPath) {
		ComponentResource resource = ModuleCoreFactory.eINSTANCE.createWorkbenchModuleResource();		
		resource.setSourcePath(URI.createURI(aSourceFile.getFullPath().toString()));
		resource.setDeployedPath(URI.createURI(aDeployPath));
		aModule.getResources().add(resource);
	}
	public WorkbenchComponent addWorkbenchModule(ProjectComponents theModules, String aDeployedName, URI aHandle) {
		WorkbenchComponent module = ModuleCoreFactory.eINSTANCE.createWorkbenchModule();
		module.setHandle(aHandle);  
		module.setDeployedName(aDeployedName);  
		ComponentType type = ModuleCoreFactory.eINSTANCE.createModuleType();
		type.setModuleTypeId(IModuleConstants.JST_WEB_MODULE);
		module.setModuleType(type);
		theModules.getWorkbenchModules().add(module);
		return module;
	}
	public IFile getModuleRelativeFile(String aModuleRelativePath, IProject project) {
		return getDataModel().getTargetProject().getFile(new Path(IPath.SEPARATOR + aModuleRelativePath));
	}
	public FlexibleEjbModuleCreationDataModel getDataModel() {
		return (FlexibleEjbModuleCreationDataModel)operationDataModel;
	}
	

	/**
	 * @return
	 */
	public String getJavaSourceSourcePath() {
		return "/JavaSource"; //$NON-NLS-1$
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
	public String getWebContentSourcePath() {
		return "/WebContent"; //$NON-NLS-1$
	}
	
	/**
	 * @return
	 */
	public String getWebContentDeployPath() {
		return "/"; //$NON-NLS-1$
	}
	
	public String getModuleName() {
		return getDataModel().getModuleName();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.application.operations.FlexibileJ2EECreationOperation#createModule(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void createModule(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		// TODO Auto-generated method stub
		
	}
}