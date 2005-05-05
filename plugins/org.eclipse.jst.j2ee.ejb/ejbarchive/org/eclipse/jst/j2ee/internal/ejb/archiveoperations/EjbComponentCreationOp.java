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

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentCreationOp;
import org.eclipse.jst.j2ee.ejb.componentcore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.ejb.datamodel.properties.IEjbComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionUtil;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class EjbComponentCreationOp extends J2EEComponentCreationOp implements IEjbComponentCreationDataModelProperties {

    public EjbComponentCreationOp(IDataModel model) {
        super(model);
        // TODO Auto-generated constructor stub
    }

    protected void createAndLinkJ2EEComponents() throws CoreException {
        IVirtualComponent component = ComponentCore.createComponent(getProject(), getModuleDeployName());
        component.create(0, null);
        //create and link ejbModule Source Folder
        IVirtualFolder ejbModule = component.getFolder(new Path("/")); //$NON-NLS-1$        
        ejbModule.createLink(new Path("/" + getModuleName() + "/ejbModule"), 0, null);
        
        //create and link META-INF folder
        IVirtualFolder metaInfFolder = ejbModule.getFolder(J2EEConstants.META_INF);
        metaInfFolder.create(IResource.FORCE, null);
    }

    protected void createDeploymentDescriptor(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
        //should cache wbmodule when created instead of  searching ?
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

        EJBArtifactEdit ejbEdit = null;
        try{
            ejbEdit = EJBArtifactEdit.getEJBArtifactEditForWrite( wbmodule );
            Integer version = (Integer)model.getProperty(COMPONENT_VERSION);
            ejbEdit.createModelRoot(version.intValue());
            ejbEdit.save(monitor);
        }
        catch(Exception e){
            e.printStackTrace();
        } finally {
            if(ejbEdit != null)
                ejbEdit.dispose();
            ejbEdit = null;
        }   
        
    }


    public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
        try {
            super.execute(IModuleConstants.JST_EJB_MODULE, monitor);
        } catch (CoreException e) {
            Logger.getLogger().log(e.getMessage());
        } catch (InvocationTargetException e) {
            Logger.getLogger().log(e.getMessage());
        } catch (InterruptedException e) {
            Logger.getLogger().log(e.getMessage());
        }
        
        if( model.getBooleanProperty(CREATE_CLIENT) ){
            IDataModel dm = (IDataModel)model.getProperty(NESTED_MODEL_EJB_CLIENT_CREATION);
            dm.getDefaultOperation().execute(monitor, info);
			//To do: after  porting
            //dm.setEarComponentHandle((URI)model.getProperty(EAR_COMPONENT_HANDLE));
            //TODO: update once client port complete
            //runNestedDefaultOperation(dm ,monitor);
        }
        return OK_STATUS;
    }


    /* (non-Javadoc)
     * @see org.eclipse.jst.j2ee.application.operations.J2EEComponentCreationOperation#getVersion()
     */
    protected String getVersion() {
        int version = model.getIntProperty(COMPONENT_VERSION);
        return J2EEVersionUtil.getEJBTextVersion(version);
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
