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

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentCreationOp;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionUtil;
import org.eclipse.jst.j2ee.jca.modulecore.util.ConnectorArtifactEdit;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class ConnectorComponentCreationOp extends J2EEComponentCreationOp implements IConnectorComponentCreationDataModelProperties{

    public ConnectorComponentCreationOp(IDataModel model) {
        super(model);
    }

    protected void createAndLinkJ2EEComponents() throws CoreException {
        IVirtualComponent component = ComponentCore.createComponent(getProject(), getModuleDeployName());
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
        StructureEdit moduleCore = null;
        WorkbenchComponent wbmodule = null;
        try {
            moduleCore = StructureEdit.getStructureEditForRead(getProject());
            wbmodule = moduleCore.findComponentByName(model.getStringProperty(COMPONENT_DEPLOY_NAME));
        } finally {
            if (null != moduleCore) {
                moduleCore.dispose();
            }
        }
        try {
            artifactEdit = ConnectorArtifactEdit.getConnectorArtifactEditForWrite(wbmodule);
            Integer version = (Integer)model.getProperty(COMPONENT_VERSION);
            artifactEdit.createModelRoot(version.intValue());
            artifactEdit.save(monitor);
        } catch (Exception e) {
            Logger.getLogger().logError(e);
        } finally {
            if (artifactEdit != null)
                artifactEdit.dispose();
        }
    }

    protected String getVersion() {
        int version = model.getIntProperty(COMPONENT_VERSION);
        return J2EEVersionUtil.getJCATextVersion(version);
    }

    public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
        try {
            super.execute(IModuleConstants.JST_CONNECTOR_MODULE, monitor);
        } catch (CoreException e) {
            Logger.getLogger().log(e.getMessage());
        } catch (InvocationTargetException e) {
            Logger.getLogger().log(e.getMessage());
        } catch (InterruptedException e) {
            Logger.getLogger().log(e.getMessage());
        }
        return OK_STATUS;
    }

    public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
        // TODO Auto-generated method stub
        return null;
    }

    public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
        // TODO Auto-generated method stub
        return null;
    }

}
