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
package org.eclipse.jst.j2ee.internal.ejb.archiveoperations;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.application.internal.operations.AddComponentToEnterpriseApplicationDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.FlexibleJavaProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.JavaUtilityComponentCreationOperation;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestDataModel;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestOperation;
import org.eclipse.jst.j2ee.ejb.componentcore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.ejb.datamodel.properties.IEJBClientComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.ejb.datamodel.properties.IEjbComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.common.operations.JARDependencyDataModel;
import org.eclipse.jst.j2ee.internal.common.operations.JARDependencyOperation;
import org.eclipse.jst.j2ee.internal.ejb.impl.EJBJarImpl;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.internal.operation.CreateReferenceComponentsDataModelProvider;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.ComponentHandle;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.properties.IFlexibleProjectCreationDataModelProperties;

public class EJBClientComponentCreationOperation extends JavaUtilityComponentCreationOperation implements IEJBClientComponentCreationDataModelProperties{

    public EJBClientComponentCreationOperation(IDataModel model) {
        super(model);
        // TODO Auto-generated constructor stub
    }
    public static final String metaInfFolderDeployPath = "/"; //$NON-NLS-1$

    public IStatus execute(IProgressMonitor monitor, IAdaptable info) {
        try {
            createProjectIfNecessary(monitor, model.getStringProperty(PROJECT_NAME));

    
            super.execute(IModuleConstants.JST_UTILITY_MODULE, monitor, null);
            
            runAddToEAROperation(monitor);
            runAddToEJBOperation(monitor);
            modifyEJBModuleJarDependency(monitor);
            updateEJBDD(monitor);
        } catch (CoreException e) {
            Logger.getLogger().log(e.getMessage());
        } catch (InvocationTargetException e) {
            Logger.getLogger().log(e.getMessage());
        } catch (InterruptedException e) {
            Logger.getLogger().log(e.getMessage());
        } catch (ExecutionException e){
            Logger.getLogger().log(e.getMessage());
        }
        return OK_STATUS;
    }   
     
    protected void runAddToEAROperation(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
	
        IVirtualComponent component = ComponentCore.createComponent(getProject(), model.getStringProperty(COMPONENT_DEPLOY_NAME));
		ComponentHandle earhandle = (ComponentHandle) model.getProperty(IEjbComponentCreationDataModelProperties.EAR_COMPONENT_HANDLE);
        IDataModel dm = DataModelFactory.createDataModel(new AddComponentToEnterpriseApplicationDataModelProvider());
		dm.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT_HANDLE, earhandle);
		
        List modList = (List) dm.getProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_HANDLE_LIST);
        modList.add(component.getComponentHandle());
        dm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_HANDLE_LIST, modList);
		try {
			dm.getDefaultOperation().execute(monitor, null);
		} catch (ExecutionException e) {
			Logger.getLogger().log(e);
		}
    }

    
    protected void runAddToEJBOperation(IProgressMonitor monitor)throws CoreException, InvocationTargetException, InterruptedException{
		
        String ejbProjString = model.getStringProperty( EJB_PROJECT_NAME );
        IProject ejbProj = ProjectUtilities.getProject( ejbProjString );
		
		IVirtualComponent ejbcomponent = ComponentCore.createComponent(ejbProj, model.getStringProperty(EJB_COMPONENT_DEPLOY_NAME));
        IVirtualComponent ejbclientcomponent = ComponentCore.createComponent(getProject(), model.getStringProperty(COMPONENT_DEPLOY_NAME));
		
        IDataModel dm = DataModelFactory.createDataModel(new CreateReferenceComponentsDataModelProvider());
		dm.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT_HANDLE, ejbcomponent.getComponentHandle());
		
        List modList = (List) dm.getProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_HANDLE_LIST);
        modList.add(ejbclientcomponent.getComponentHandle());
        dm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_HANDLE_LIST, modList);
		try {
			dm.getDefaultOperation().execute(monitor, null);
		} catch (ExecutionException e) {
			Logger.getLogger().log(e);
		}
            
    }
    
    private void modifyEJBModuleJarDependency(IProgressMonitor aMonitor) throws InvocationTargetException, InterruptedException {
        String clientProjectName = model.getStringProperty(PROJECT_NAME);
        String ejbComponentDeployName = model.getStringProperty(EJB_COMPONENT_DEPLOY_NAME);
        String ejbprojectName = model.getStringProperty(EJB_PROJECT_NAME);    
        IProject ejbProject = ProjectUtilities.getProject( ejbprojectName );    
        String clientDeployName  = model.getStringProperty(COMPONENT_DEPLOY_NAME);
        
        StructureEdit moduleCore = null;
        IFile manifestmf = null;
        try{
            moduleCore = StructureEdit.getStructureEditForRead(ejbProject);
            WorkbenchComponent ejbwc = moduleCore.findComponentByName(ejbComponentDeployName);
            ejbwc = moduleCore.findComponentByName(ejbComponentDeployName);
            IVirtualComponent component = ComponentCore.createComponent( ejbProject, ejbwc.getName());
            IVirtualFile vf = component.getFile( new Path("/META-INF/MANIFEST.MF")); //$NON-NLS-1$
            manifestmf = vf.getUnderlyingFile();
        }finally {
               if (null != moduleCore) {
                moduleCore.dispose();
           }
        }   
    

        UpdateManifestDataModel updateManifestDataModel = new UpdateManifestDataModel();
        updateManifestDataModel.setProperty(UpdateManifestDataModel.PROJECT_NAME, ejbprojectName);
        updateManifestDataModel.setBooleanProperty(UpdateManifestDataModel.MERGE, false);
        updateManifestDataModel.setProperty(UpdateManifestDataModel.MANIFEST_FILE, manifestmf);
        updateManifestDataModel.setProperty(UpdateManifestDataModel.JAR_LIST, UpdateManifestDataModel.convertClasspathStringToList(clientDeployName) );
        
        UpdateManifestOperation mop = new UpdateManifestOperation(updateManifestDataModel);     
        mop.run(aMonitor);
        
        if( !clientProjectName.equals( ejbprojectName )){
            JARDependencyDataModel dataModel = new JARDependencyDataModel();
            dataModel.setProperty(JARDependencyDataModel.PROJECT_NAME, ejbprojectName);
            dataModel.setProperty(JARDependencyDataModel.REFERENCED_PROJECT_NAME, clientProjectName);
            dataModel.setIntProperty(JARDependencyDataModel.JAR_MANIPULATION_TYPE, JARDependencyDataModel.JAR_MANIPULATION_ADD);
            JARDependencyOperation op = new JARDependencyOperation(dataModel);
            op.run( aMonitor );
        }   
    }
    /**
     * Update the deployment descriptor for the ejb project
     */
    private void updateEJBDD(IProgressMonitor monitor) {        
        String ejbprojectName = model.getStringProperty(EJB_PROJECT_NAME);    
        IProject ejbProject = ProjectUtilities.getProject( ejbprojectName );    
        String ejbComponentDeployName = model.getStringProperty(EJB_COMPONENT_DEPLOY_NAME);
        String clientDeployName  = model.getStringProperty(COMPONENT_DEPLOY_NAME);
        
        EJBArtifactEdit ejbEdit = null;
        try{
			ComponentHandle handle = ComponentHandle.create(ejbProject,ejbComponentDeployName);
            ejbEdit = EJBArtifactEdit.getEJBArtifactEditForWrite(handle);
            if(ejbEdit != null) {
                EJBJarImpl ejbres = (EJBJarImpl)ejbEdit.getDeploymentDescriptorRoot();
                ejbres.setEjbClientJar(clientDeployName);
                ejbEdit.saveIfNecessary(monitor);
            }
        }
        catch(Exception e){
            Logger.getLogger().logError(e);
        } finally {
            if(ejbEdit != null)
                ejbEdit.dispose();
        }  
    }   
     
    private void createProjectIfNecessary(IProgressMonitor monitor, String name) throws CoreException, InvocationTargetException, InterruptedException, ExecutionException {
        if( model.getBooleanProperty(CREATE_PROJECT)){
            //check if project exists
            IProject proj = ProjectUtilities.getProject(name);
            if( !proj.exists() ){
                IDataModel dataModel = DataModelFactory.createDataModel(new FlexibleJavaProjectCreationDataModelProvider());
                dataModel.setProperty(IFlexibleProjectCreationDataModelProperties.PROJECT_NAME, name);
                dataModel.getDefaultOperation().execute(monitor, null);
            }   
        }
    }
}
