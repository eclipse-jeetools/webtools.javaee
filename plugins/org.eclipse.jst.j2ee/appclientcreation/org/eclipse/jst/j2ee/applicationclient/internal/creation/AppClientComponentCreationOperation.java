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
package org.eclipse.jst.j2ee.applicationclient.internal.creation;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentCreationDataModel;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentCreationOperation;
import org.eclipse.jst.j2ee.applicationclient.componentcore.util.AppClientArtifactEdit;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassDataModel;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.ComponentHandle;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;

public class AppClientComponentCreationOperation extends J2EEComponentCreationOperation {

    public AppClientComponentCreationOperation(AppClientComponentCreationDataModel dataModel) {
        super(dataModel);
    }
    protected void createAndLinkJ2EEComponents() throws CoreException {
		IVirtualComponent component = ComponentCore.createComponent(getProject(), getModuleDeployName());
		component.create(0, null);
		//create and link appClientModule Source Folder
		IVirtualFolder appClientModuleFolder = component.getFolder(new Path("/")); //$NON-NLS-1$		
		appClientModuleFolder.createLink(new Path("/" + getModuleName() + "/appClientModule"), 0, null);
		
		//create and link META-INF folder
    	IVirtualFolder metaInfFolder = appClientModuleFolder.getFolder(J2EEConstants.META_INF);
    	metaInfFolder.create(IResource.FORCE, null);	
    }
    
    protected void createDeploymentDescriptor(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
        
        AppClientArtifactEdit artifactEdit = null;
       	try {
			ComponentHandle handle = ComponentHandle.create(getProject(),operationDataModel.getStringProperty(AppClientComponentCreationDataModel.COMPONENT_DEPLOY_NAME));
       	    artifactEdit = AppClientArtifactEdit.getAppClientArtifactEditForWrite(handle);
       	    Integer version = (Integer)operationDataModel.getProperty(AppClientComponentCreationDataModel.COMPONENT_VERSION);
       	 	artifactEdit.createModelRoot(version.intValue());
       	 	artifactEdit.save(monitor);
            
        	AppClientComponentCreationDataModel dataModel = (AppClientComponentCreationDataModel) operationDataModel;
            if (dataModel.getBooleanProperty(AppClientComponentCreationDataModel.CREATE_DEFAULT_MAIN_CLASS)) {
                NewJavaClassDataModel mainClassDataModel = new NewJavaClassDataModel();
                mainClassDataModel.setProperty(NewJavaClassDataModel.PROJECT_NAME, getProject().getName());
                mainClassDataModel.setProperty(NewJavaClassDataModel.CLASS_NAME, "Main"); //$NON-NLS-1$
                mainClassDataModel.setBooleanProperty(NewJavaClassDataModel.MAIN_METHOD, true);
                //TODO: reimplement
                //mainClassDataModel.getDefaultOperation().run(monitor);
                //dataModel.getUpdateManifestDataModel().setProperty(UpdateManifestDataModel.MAIN_CLASS, mainClassDataModel.getProperty(NewJavaClassDataModel.CLASS_NAME));
            }
        } catch(Exception e){
       		Logger.getLogger().logError(e);
       	} finally {
            if (artifactEdit != null)
                artifactEdit.dispose();
        }
    }
    
	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		
		super.execute( IModuleConstants.JST_APPCLIENT_MODULE, monitor );

	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.application.operations.J2EEComponentCreationOperation#getVersion()
	 */
	protected String getVersion() {
		int version = operationDataModel.getIntProperty(J2EEComponentCreationDataModel.COMPONENT_VERSION);
		return J2EEVersionUtil.getJ2EETextVersion(version);
	}	
  
}
