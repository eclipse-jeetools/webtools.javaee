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

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentCreationDataModel;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentCreationOperation;
import org.eclipse.jst.j2ee.applicationclient.internal.creation.AppClientComponentCreationDataModel;
import org.eclipse.jst.j2ee.ejb.modulecore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionUtil;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;

public class EjbComponentCreationOperation extends J2EEComponentCreationOperation {
	public EjbComponentCreationOperation(EjbComponentCreationDataModel dataModel) {
		super(dataModel);
	}

	public EjbComponentCreationOperation() {
		super();
	}

    /* (non-Javadoc)
     * @see org.eclipse.jst.j2ee.application.operations.J2EEComponentCreationOperation#createAndLinkJ2EEComponents()
     */
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
            wbmodule = moduleCore.findComponentByName(operationDataModel.getStringProperty(EjbComponentCreationDataModel.COMPONENT_DEPLOY_NAME));
        } finally {
            if (null != moduleCore) {
                moduleCore.dispose();
            }
        }		

        EJBArtifactEdit ejbEdit = null;
       	try{
       		ejbEdit = EJBArtifactEdit.getEJBArtifactEditForWrite( wbmodule );
       		Integer version = (Integer)operationDataModel.getProperty(AppClientComponentCreationDataModel.COMPONENT_VERSION);
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

	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		super.execute( IModuleConstants.JST_EJB_MODULE, monitor );
		
		if( operationDataModel.getBooleanProperty(EjbComponentCreationDataModel.CREATE_CLIENT) ){
			EJBClientComponentDataModel dm = ((EjbComponentCreationDataModel)operationDataModel).getNestedEJBClientComponentDataModel();
			dm.setEarComponentHandle( ((EjbComponentCreationDataModel)operationDataModel).getEarComponentHandle());
			runNestedDefaultOperation(((EjbComponentCreationDataModel)operationDataModel).getNestedEJBClientComponentDataModel() ,monitor);
		}
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.application.operations.J2EEComponentCreationOperation#getVersion()
	 */
	protected String getVersion() {
		int version = operationDataModel.getIntProperty(J2EEComponentCreationDataModel.COMPONENT_VERSION);
		return J2EEVersionUtil.getEJBTextVersion(version);
	}
	
}