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
import org.eclipse.jst.j2ee.application.operations.FlexibleJ2EEModuleCreationOperation;
import org.eclipse.jst.j2ee.application.operations.UpdateManifestDataModel;
import org.eclipse.jst.j2ee.applicationclient.internal.modulecore.util.AppClientArtifactEdit;
import org.eclipse.jst.j2ee.common.operations.NewJavaClassDataModel;
import org.eclipse.wst.common.modulecore.ComponentResource;
import org.eclipse.wst.common.modulecore.ComponentType;
import org.eclipse.wst.common.modulecore.ModuleCore;
import org.eclipse.wst.common.modulecore.ModuleCoreFactory;
import org.eclipse.wst.common.modulecore.ProjectComponents;
import org.eclipse.wst.common.modulecore.WorkbenchComponent;
import org.eclipse.wst.common.modulecore.internal.operation.ArtifactEditOperationDataModel;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

public class FlexibleAppClientModuleCreationOperation extends FlexibleJ2EEModuleCreationOperation {

    public FlexibleAppClientModuleCreationOperation(FlexibleAppClientCreationDataModel dataModel) {
        super(dataModel);
    }

    protected void createDeploymentDescriptor(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
        
        AppClientArtifactEdit artifactEdit = null;
        try {
            WorkbenchComponent wbModule = getWorkbenchModule();
            artifactEdit = AppClientArtifactEdit.getAppClientArtifactEditForWrite(wbModule);
            IProject rootProject = getDataModel().getTargetProject();
            URI metainfURI = URI.createURI(rootProject.getName() + IPath.SEPARATOR + getModuleName() + ".jar");
            IPath absMetaRoot = ProjectUtilities.getJavaProjectOutputAbsoluteLocation(rootProject).append(metainfURI.toString());
            createFolder(absMetaRoot);

            artifactEdit.getDeploymentDescriptorRoot();
            AppClientModuleCreationDataModel dataModel = (AppClientModuleCreationDataModel) operationDataModel;
            if (dataModel.getBooleanProperty(AppClientModuleCreationDataModel.CREATE_DEFAULT_MAIN_CLASS)) {
                NewJavaClassDataModel mainClassDataModel = new NewJavaClassDataModel();
                mainClassDataModel.setProperty(NewJavaClassDataModel.PROJECT_NAME, dataModel.getProjectDataModel().getProject().getName());
                mainClassDataModel.setProperty(NewJavaClassDataModel.CLASS_NAME, "Main"); //$NON-NLS-1$
                mainClassDataModel.setBooleanProperty(NewJavaClassDataModel.MAIN_METHOD, true);
                mainClassDataModel.getDefaultOperation().run(monitor);
                dataModel.getUpdateManifestDataModel().setProperty(UpdateManifestDataModel.MAIN_CLASS, mainClassDataModel.getProperty(NewJavaClassDataModel.CLASS_NAME));
            }
        } finally {
            if (artifactEdit != null)
                artifactEdit.dispose();
            artifactEdit = null;
        }
    }

    /**
     * 
     */
    protected void createProjectStructure() throws CoreException {
        IProject rootProject = getDataModel().getTargetProject();
        URI metainfURI = URI.createURI(IPath.SEPARATOR + getModuleName() + ".jar" + IPath.SEPARATOR + "appClientModule" + IPath.SEPARATOR + "META-INF");
        IPath absMetaRoot = rootProject.getLocation().append(metainfURI.toString());
        createFolder(absMetaRoot);
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
            moduleCore = ModuleCore.getModuleCoreForRead(((FlexibleAppClientCreationDataModel) operationDataModel).getTargetProject());
            module = moduleCore.findWorkbenchModuleByDeployName(((FlexibleAppClientCreationDataModel) operationDataModel).getStringProperty(ArtifactEditOperationDataModel.MODULE_NAME));
        } finally {
            if (null != moduleCore) {
                moduleCore.dispose();
            }
        }
        return module;
    }

    public FlexibleAppClientCreationDataModel getDataModel() {
        return (FlexibleAppClientCreationDataModel) operationDataModel;
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
    
    
	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		
		super.execute( IModuleConstants.JST_APPCLIENT_MODULE, monitor );

	}
    
	protected  void addResources( WorkbenchComponent component ){
		addResource(component, getModuleRelativeFile(getContentSourcePath(), getProject()), getContentDeployPath());
		addResource(component, getModuleRelativeFile(getJavaSourceSourcePath(), getProject()), getJavaSourceDeployPath());		
	}
	
  
}
