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
package org.eclipse.jst.j2ee.internal.jca.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentCreationDataModel;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentCreationOperation;
import org.eclipse.jst.j2ee.applicationclient.internal.creation.AppClientComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionUtil;
import org.eclipse.jst.j2ee.jca.internal.module.util.ConnectorArtifactEdit;
import org.eclipse.wst.common.modulecore.ModuleCore;
import org.eclipse.wst.common.modulecore.WorkbenchComponent;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;
import org.eclipse.wst.common.modulecore.resources.IVirtualContainer;
import org.eclipse.wst.common.modulecore.resources.IVirtualFolder;


public class ConnectorComponentCreationOperation extends J2EEComponentCreationOperation {

    public ConnectorComponentCreationOperation(ConnectorComponentCreationDataModel dataModel) {
        super(dataModel);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.j2ee.application.operations.J2EEComponentCreationOperation#createAndLinkJ2EEComponents()
     */
    protected void createAndLinkJ2EEComponents() throws CoreException {
		IVirtualContainer component = ModuleCore.createContainer(getProject(), getModuleDeployName());
        component.create(0, null);
		//create and link connectorModule Source Folder
		IVirtualFolder connectorModuleFolder = component.getFolder(new Path("/")); //$NON-NLS-1$		
		connectorModuleFolder.createLink(new Path("/" + getModuleName() + "/connectorModule"), 0, null);
		
		//create and link META-INF folder
    	IVirtualFolder metaInfFolder = connectorModuleFolder.getFolder(J2EEConstants.META_INF);
    	metaInfFolder.create(IResource.FORCE, null);	
    }

    protected void createDeploymentDescriptor(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
        ConnectorArtifactEdit artifactEdit = null;
        // should cache wbmodule when created instead of searching ?
        ModuleCore moduleCore = null;
        WorkbenchComponent wbmodule = null;
        try {
            moduleCore = ModuleCore.getModuleCoreForRead(getProject());
            wbmodule = moduleCore.findWorkbenchModuleByDeployName(operationDataModel.getStringProperty(ConnectorComponentCreationDataModel.COMPONENT_DEPLOY_NAME));
        } finally {
            if (null != moduleCore) {
                moduleCore.dispose();
            }
        }

        try {
            artifactEdit = ConnectorArtifactEdit.getConnectorArtifactEditForWrite(wbmodule);
            Integer version = (Integer)operationDataModel.getProperty(AppClientComponentCreationDataModel.COMPONENT_VERSION);
       	 	artifactEdit.createModelRoot(version.intValue());
            artifactEdit.save(monitor);
        } catch (Exception e) {
            Logger.getLogger().logError(e);
        } finally {
            if (artifactEdit != null)
                artifactEdit.dispose();
        }
    }
    
    protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
        super.execute(IModuleConstants.JST_CONNECTOR_MODULE, monitor);
    }
	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.application.operations.J2EEComponentCreationOperation#getVersion()
	 */
	protected String getVersion() {
		int version = operationDataModel.getIntProperty(J2EEComponentCreationDataModel.COMPONENT_VERSION);
		return J2EEVersionUtil.getJCATextVersion(version);
	}



}
