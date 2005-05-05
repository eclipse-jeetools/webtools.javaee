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
package org.eclipse.jst.j2ee.application.internal.operations;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.common.UpdateProjectClasspath;
import org.eclipse.jst.j2ee.internal.project.ManifestFileCreationAction;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.UnresolveableURIException;
import org.eclipse.wst.common.componentcore.internal.ComponentType;
import org.eclipse.wst.common.componentcore.internal.ComponentcoreFactory;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.impl.ModuleURIUtil;
import org.eclipse.wst.common.componentcore.internal.operation.ComponentCreationOperationEx;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public abstract class J2EEComponentCreationOp extends ComponentCreationOperationEx implements IJ2EEComponentCreationDataModelProperties, IAnnotationsDataModel{
    /**
     * name of the template emitter to be used to generate the deployment
     * descriptor from the tags
     */
    protected static final String TEMPLATE_EMITTER = "org.eclipse.jst.j2ee.ejb.annotations.emitter.template"; //$NON-NLS-1$
    /**
     * id of the builder used to kick off generation of web metadata based on
     * parsing of annotations
     */
    protected static final String BUILDER_ID = "builderId"; //$NON-NLS-1$
    
    public J2EEComponentCreationOp(IDataModel model) {
        super(model);
        // TODO Auto-generated constructor stub
    }
    protected void execute( String componentType, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
        createAndLinkJ2EEComponents();
        setupComponentType(componentType);
        
        if (model.getBooleanProperty(CREATE_DEFAULT_FILES)) {
            createDeploymentDescriptor(monitor);
            createManifest(monitor);
        }
        
        addSrcFolderToProject();
        if (model.getBooleanProperty(USE_ANNOTATIONS))
            addAnnotationsBuilder();    
        
        linkToEARIfNecessary(monitor);
    }

    protected abstract void createAndLinkJ2EEComponents() throws CoreException;
    

    
    protected abstract void createDeploymentDescriptor(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException;

        public  void linkToEARIfNecessary(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
            if (model.getBooleanProperty(ADD_TO_EAR)) {
                createEARComponentIfNecessary(monitor);
                runAddToEAROperation(monitor);
            }
        }

        /**
         * @param moduleModel
         * @param monitor
         * @throws CoreException
         * @throws InvocationTargetException
         * @throws InterruptedException
         */
        protected void runAddToEAROperation(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
            
//            URI uri = (URI)model.getProperty(EAR_COMPONENT_HANDLE);
//            IProject proj = null;
//            try {
//                proj = StructureEdit.getContainingProject(uri);
//            }
//            catch (UnresolveableURIException e) {
//                Logger.getLogger().log(e);
//            }
//            
            
//            StructureEdit core = null;
//            try {
//                core = StructureEdit.getStructureEditForRead(getProject());
//                WorkbenchComponent wc = core.findComponentByName((String)model.getProperty(COMPONENT_DEPLOY_NAME));
//                AddComponentToEnterpriseApplicationDataModel dm = (AddComponentToEnterpriseApplicationDataModel)model.getProperty(NESTED_MODEL_ADD_TO_EAR);
//                
//                dm.setProperty(AddComponentToEnterpriseApplicationDataModel.EAR_PROJECT_NAME, proj.getName());
//                dm.setProperty(AddComponentToEnterpriseApplicationDataModel.PROJECT_NAME, model.getProperty(PROJECT_NAME));
//                dm.setProperty(AddComponentToEnterpriseApplicationDataModel.MODULE_NAME, wc.getName());
//                dm.setProperty(AddComponentToEnterpriseApplicationDataModel.EAR_MODULE_NAME, model.getProperty(EAR_COMPONENT_DEPLOY_NAME));
//                List modList = (List)dm.getProperty(AddComponentToEnterpriseApplicationDataModel.MODULE_LIST);
//                modList.add(wc);
//                dm.setProperty(AddComponentToEnterpriseApplicationDataModel.MODULE_LIST,modList);
//                AddComponentToEnterpriseApplicationOperation addModuleOp = new AddComponentToEnterpriseApplicationOperation(dm);
//                addModuleOp.doRun(monitor);
//            } finally {
//                if(core != null)
//                    core.dispose();
//            }
        }

        /**
         * @param moduleModel
         * @param monitor
         * @throws CoreException
         * @throws InvocationTargetException
         * @throws InterruptedException
         */
        protected void createEARComponentIfNecessary(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
//            EARComponentCreationDataModel earModel = (EARComponentCreationDataModel)model.getProperty(NESTED_MODEL_EAR_CREATION);
//            
//
//            earModel.setProperty(EARComponentCreationDataModel.COMPONENT_NAME, model.getStringProperty(EAR_COMPONENT_NAME));
//            earModel.setProperty(EARComponentCreationDataModel.COMPONENT_DEPLOY_NAME, model.getStringProperty(EAR_COMPONENT_DEPLOY_NAME));            
//            earModel.setProperty(EARComponentCreationDataModel.PROJECT_NAME, model.getStringProperty(PROJECT_NAME));
//            if (!doesEARComponentExist()) {
//                EARComponentCreationOperation earOp = new EARComponentCreationOperation(earModel);
//                earOp.doRun(monitor);
//                model.setProperty(EAR_COMPONENT_HANDLE, earOp.getComponentHandle());
//            }
        }
    
        public  boolean doesEARComponentExist() {
			//To do: implement after porting
            //URI uri = (URI)model.getProperty(EAR_COMPONENT_HANDLE);
			
			URI uri = null;
            
            boolean isValidURI = false;
            try {
                if( uri != null )
                    isValidURI = ModuleURIUtil.ensureValidFullyQualifiedModuleURI(uri);
            }catch (UnresolveableURIException e) {

            }
            if( isValidURI ){
                IProject proj = null;
                try {
                    proj = StructureEdit.getContainingProject(uri);
                    
                    StructureEdit moduleCore = null;
                    try{
                        moduleCore = StructureEdit.getStructureEditForRead(proj);
                        if((moduleCore.findComponentByURI(uri)) != null ){
                            return true;
                        }
                    } finally {
                        if(moduleCore != null)
                            moduleCore.dispose();
                    }               
                }
                catch (UnresolveableURIException e) {
                    Logger.getLogger().log(e);
                }
            }
            return false;           
        }
        
        public IProject getProject() {
            String projName = model.getStringProperty(PROJECT_NAME );
            return ProjectUtilities.getProject( projName );
        }
        
        public String getModuleName() {
            return (String)model.getProperty(COMPONENT_NAME);
        }
        
        public String getModuleDeployName() {
            return (String)model.getProperty(COMPONENT_DEPLOY_NAME);
        }
        
    protected abstract String getVersion();

    protected void setupComponentType(String typeID) {
        IVirtualComponent component = ComponentCore.createComponent(getProject(), getModuleDeployName());
        ComponentType componentType = ComponentcoreFactory.eINSTANCE.createComponentType();
        componentType.setComponentTypeId(typeID);
        componentType.setVersion(getVersion());
        List newProps = getProperties();
        if (newProps != null && !newProps.isEmpty()) {
            EList existingProps = componentType.getProperties();
            for (int i = 0; i < newProps.size(); i++) {
                existingProps.add(newProps.get(i));
        }
        }
        StructureEdit.setComponentType(component, componentType);
    }

    // Should return null if no additional properties needed
    protected List getProperties() {
        return null;
   }
        

    /**
     * @param monitor
     */
    protected void createManifest(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
        
        String manifestFolder = model.getStringProperty(MANIFEST_FOLDER);
        IContainer container = getProject().getFolder( manifestFolder );

        IFile file = container.getFile( new Path(J2EEConstants.MANIFEST_SHORT_NAME));
        
        try {
            ManifestFileCreationAction.createManifestFile(file, getProject());
        }
        catch (CoreException e) {
            Logger.getLogger().log(e);
        }
        catch (IOException e) {
            Logger.getLogger().log(e);
        }
//      UpdateManifestOperation op = new UpdateManifestOperation(((J2EEModuleCreationDataModel) operationDataModel).getUpdateManifestDataModel());
//      op.doRun(monitor);      

    }
    
    /**
     * This method is intended for internal use only.  This method will add the annotations builder
     * for Xdoclet to the targetted project.  This needs to be removed from the operation and set
     * up to be more extensible throughout the workbench.
     * @see EJBModuleCreationOperation#execute(IProgressMonitor)
     * 
     * @deprecated
     */
    protected final void addAnnotationsBuilder() {
        try {
            // Find the xdoclet builder from the extension registry
            IConfigurationElement[] configurationElements = Platform.getExtensionRegistry().getConfigurationElementsFor(TEMPLATE_EMITTER);
            String builderID = configurationElements[0].getNamespace() + "."+ configurationElements[0].getAttribute(BUILDER_ID); //$NON-NLS-1$
            IProject project = ProjectUtilities.getProject(model.getProperty(PROJECT_NAME)); 
            IProjectDescription description = project.getDescription();
            ICommand[] commands = description.getBuildSpec();
            boolean found = false;
            // Check if the builder is already set on the project
            for (int i = 0; i < commands.length; ++i) {
                if (commands[i].getBuilderName().equals(builderID)) {
                    found = true;
                    break;
                }
            }
            // If the builder is not on the project, add it
            if (!found) {
                ICommand command = description.newCommand();
                command.setBuilderName(builderID);
                ICommand[] newCommands = new ICommand[commands.length + 1];
                System.arraycopy(commands, 0, newCommands, 0, commands.length);
                newCommands[commands.length] = command;
                IProjectDescription desc = project.getDescription();
                desc.setBuildSpec(newCommands);
                project.setDescription(desc, null);
            }
        } catch (Exception e) {
            //Ignore
        }
    }

    /**
     * @return
     */
    public String getJavaSourceSourceFolder() {
        return model.getStringProperty(JAVASOURCE_FOLDER);
    }

    /**
     * @return
     */
    public String getDeploymentDescriptorFolder() {
        return model.getStringProperty(DD_FOLDER);
    }
    
    public String getJavaSourceSourcePath() {
        return model.getStringProperty(JAVASOURCE_FOLDER);
    }
   
    
    private void addSrcFolderToProject() {  
        UpdateProjectClasspath update = new UpdateProjectClasspath(model.getStringProperty(JAVASOURCE_FOLDER), ProjectUtilities.getProject(model.getStringProperty(PROJECT_NAME)));
    }
}
