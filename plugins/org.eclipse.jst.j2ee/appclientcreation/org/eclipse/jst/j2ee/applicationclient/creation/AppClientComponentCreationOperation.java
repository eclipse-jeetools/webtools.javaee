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

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.application.operations.J2EEComponentCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEComponentCreationOperation;
import org.eclipse.jst.j2ee.application.operations.UpdateManifestDataModel;
import org.eclipse.jst.j2ee.applicationclient.internal.modulecore.util.AppClientArtifactEdit;
import org.eclipse.jst.j2ee.common.operations.NewJavaClassDataModel;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionUtil;
import org.eclipse.wst.common.modulecore.ModuleCore;
import org.eclipse.wst.common.modulecore.WorkbenchComponent;
import org.eclipse.wst.common.modulecore.internal.operation.ArtifactEditOperationDataModel;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;

public class AppClientComponentCreationOperation extends J2EEComponentCreationOperation {

    public AppClientComponentCreationOperation(AppClientComponentCreationDataModel dataModel) {
        super(dataModel);
    }

    protected void createDeploymentDescriptor(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
        
        AppClientArtifactEdit artifactEdit = null;
        try {

            //TODO: create dd
            
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
		IFolder moduleFolder = getProject().getFolder(  getModuleName() );
		if (!moduleFolder.exists()) {
			moduleFolder.create(true, true, null);
		}
		IFolder ejbModuleFolder = moduleFolder.getFolder( "appClientModule" );
		if (!ejbModuleFolder.exists()) {
			ejbModuleFolder.create(true, true, null);
		}
		
		IFolder metainf = ejbModuleFolder.getFolder(J2EEConstants.META_INF);
		if (!metainf.exists()) {
			IFolder parent = metainf.getParent().getFolder(null);
			if (!parent.exists()) {
				parent.create(true, true, null);
			}
			metainf.create(true, true, null);
		}
    }


    /**
     * @return
     */
    private WorkbenchComponent getWorkbenchModule() {
        ModuleCore moduleCore = null;
        WorkbenchComponent module = null;
        try {
            moduleCore = ModuleCore.getModuleCoreForRead(((AppClientComponentCreationDataModel) operationDataModel).getTargetProject());
            module = moduleCore.findWorkbenchModuleByDeployName(((AppClientComponentCreationDataModel) operationDataModel).getStringProperty(ArtifactEditOperationDataModel.MODULE_NAME));
        } finally {
            if (null != moduleCore) {
                moduleCore.dispose();
            }
        }
        return module;
    }

    public AppClientComponentCreationDataModel getDataModel() {
        return (AppClientComponentCreationDataModel) operationDataModel;
    }
    

    /**
     * @return
     */
    public String getJavaSourceSourcePath() {
        return "/" +getModuleName()+ "/appClientModule"; //$NON-NLS-1$
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
        return "/" +getModuleName()+ "/appClientModule/META-INF"; //$NON-NLS-1$
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

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.application.operations.J2EEComponentCreationOperation#getVersion()
	 */
	protected String getVersion() {
		int version = operationDataModel.getIntProperty(J2EEComponentCreationDataModel.COMPONENT_VERSION);
		return J2EEVersionUtil.getJ2EETextVersion(version);
	}
	
  
}
