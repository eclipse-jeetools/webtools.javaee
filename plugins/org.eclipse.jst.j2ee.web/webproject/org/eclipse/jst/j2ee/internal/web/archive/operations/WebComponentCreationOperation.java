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
package org.eclipse.jst.j2ee.internal.web.archive.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentCreationDataModel;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentCreationOperation;
import org.eclipse.jst.j2ee.applicationclient.internal.creation.AppClientComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.web.util.WebArtifactEdit;
import org.eclipse.wst.common.modulecore.ModuleCore;
import org.eclipse.wst.common.modulecore.ModuleCoreFactory;
import org.eclipse.wst.common.modulecore.Property;
import org.eclipse.wst.common.modulecore.WorkbenchComponent;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;
import org.eclipse.wst.common.modulecore.resources.IVirtualContainer;
import org.eclipse.wst.common.modulecore.resources.IVirtualFolder;

public class WebComponentCreationOperation extends J2EEComponentCreationOperation {
	public WebComponentCreationOperation(WebComponentCreationDataModel dataModel) {
		super(dataModel);
	}

	public WebComponentCreationOperation() {
		super();
	}    /* (non-Javadoc)
     * @see org.eclipse.jst.j2ee.application.operations.J2EEComponentCreationOperation#createAndLinkJ2EEComponents()
     */
    protected void createAndLinkJ2EEComponents() throws CoreException {
    	IVirtualContainer component = ModuleCore.create(getProject(), getModuleDeployName());
    	component.commit();
    	//create and link javaSource Source Folder
    	IVirtualFolder javaSourceFolder = component.getFolder(new Path("/"  + J2EEConstants.WEB_INF + "/classes")); //$NON-NLS-1$		
    	javaSourceFolder.createLink(new Path("/" + getModuleName() + "/JavaSource"), 0, null);
    	
    	//create and link META-INF and WEB-INF folder
    	IVirtualFolder webContent = component.getFolder(new Path("/")); //$NON-NLS-1$		
    	webContent.createLink(new Path("/" + getModuleName() + "/" + "WebContent" ), 0, null);
    	
    	IVirtualFolder webInfFolder = webContent.getFolder(J2EEConstants.WEB_INF);
    	webInfFolder.create(true, true, null);		
    	
    	IVirtualFolder metaInfFolder = webContent.getFolder(J2EEConstants.META_INF);
    	metaInfFolder.create(true, true, null);

    	IVirtualFolder webLib = webInfFolder.getFolder("lib");
    	webLib.create(true, true, null);
    	//webLib.create();
    }
    
	protected void createDeploymentDescriptor(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
	
		//should cache wbmodule when created instead of  searching ?
        ModuleCore moduleCore = null;
        WorkbenchComponent wbmodule = null;
        try {
            moduleCore = ModuleCore.getModuleCoreForRead(getProject());
            wbmodule = moduleCore.findWorkbenchModuleByDeployName(operationDataModel.getStringProperty(WebComponentCreationDataModel.COMPONENT_DEPLOY_NAME));
        } finally {
            if (null != moduleCore) {
                moduleCore.dispose();
            }
        }		

        WebArtifactEdit webEdit = null;
       	try{
       		webEdit = WebArtifactEdit.getWebArtifactEditForWrite(wbmodule);
       		Integer version = (Integer)operationDataModel.getProperty(AppClientComponentCreationDataModel.COMPONENT_VERSION);
       		webEdit.createModelRoot(version.intValue());
       		webEdit.save(monitor);
       	}
       	catch(Exception e){
       		Logger.getLogger().logError(e);
       	} finally {
       		if(webEdit != null)
       			webEdit.dispose();
       		webEdit = null;
       	}					
	}

	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		super.execute( IModuleConstants.JST_WEB_MODULE, monitor );
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.application.operations.J2EEComponentCreationOperation#getVersion()
	 */
	protected String getVersion() {
		int version = operationDataModel.getIntProperty(J2EEComponentCreationDataModel.COMPONENT_VERSION);
		return J2EEVersionUtil.getServletTextVersion(version);

	}
	protected List getProperties() {
	    List newProps = new ArrayList();
	    Property prop = ModuleCoreFactory.eINSTANCE.createProperty();
	    prop.setName(J2EEConstants.CONTEXTROOT);
	    prop.setValue(operationDataModel.getStringProperty(WebComponentCreationDataModel.CONTEXT_ROOT));
	    newProps.add(prop);
	    return newProps;
	}

}