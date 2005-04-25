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

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentCreationDataModel;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentCreationOperation;
import org.eclipse.jst.j2ee.applicationclient.internal.creation.AppClientComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.web.classpath.WebAppContainer;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.ComponentcoreFactory;
import org.eclipse.wst.common.componentcore.internal.Property;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;

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
		IVirtualComponent component = ComponentCore.createComponent(getProject(), getModuleDeployName());
    	component.create(0, null);
    	//create and link javaSource Source Folder
    	IVirtualFolder javaSourceFolder = component.getFolder(new Path("/"  + J2EEConstants.WEB_INF + "/classes")); //$NON-NLS-1$		
    	javaSourceFolder.createLink(new Path("/" + getModuleName() + "/JavaSource"), 0, null);
    	
    	//create and link META-INF and WEB-INF folder
    	IVirtualFolder webContent = component.getFolder(new Path("/")); //$NON-NLS-1$		
    	webContent.createLink(new Path("/" + getModuleName() + "/" + "WebContent" ), 0, null);
    	
    	IVirtualFolder webInfFolder = webContent.getFolder(J2EEConstants.WEB_INF);
    	webInfFolder.create(IResource.FORCE, null);		
    	
    	IVirtualFolder metaInfFolder = webContent.getFolder(J2EEConstants.META_INF);
    	metaInfFolder.create(IResource.FORCE,null);

    	IVirtualFolder webLib = webInfFolder.getFolder("lib");
    	webLib.create(IResource.FORCE, null);
    	//webLib.create();
    }
    
	protected void createDeploymentDescriptor(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
	
		//should cache wbmodule when created instead of  searching ?
        StructureEdit moduleCore = null;
        WorkbenchComponent wbmodule = null;
        try {
            moduleCore = StructureEdit.getStructureEditForRead(getProject());
            wbmodule = moduleCore.findComponentByName(operationDataModel.getStringProperty(WebComponentCreationDataModel.COMPONENT_DEPLOY_NAME));
        } finally {
            if (null != moduleCore) {
                moduleCore.dispose();
            }
        }		

        WebArtifactEdit webEdit = null;
       	try{
       		webEdit = WebArtifactEdit.getWebArtifactEditForWrite(wbmodule);
       		Integer version = (Integer)operationDataModel.getProperty(WebComponentCreationDataModel.COMPONENT_VERSION);
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
        
        // Add "Web App Libraries" container to the project classpath.
        
        final IJavaProject jproject = JavaCore.create( getProject() );
        final List cp = new ArrayList();
        final IClasspathEntry[] old = jproject.getRawClasspath();
        
        for( int i = 0; i < old.length; i++ )
        {
            cp.add( old[ i ] );
        }
        
        final String prop = WebComponentCreationDataModel.COMPONENT_DEPLOY_NAME;
        final String name = this.operationDataModel.getStringProperty( prop );
        
        cp.add( WebAppContainer.convert( name ) );

        final IClasspathEntry[] cparray = new IClasspathEntry[ cp.size() ];
        cp.toArray( cparray );
        
        jproject.setRawClasspath( cparray, null );
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
	    Property prop = ComponentcoreFactory.eINSTANCE.createProperty();
	    prop.setName(J2EEConstants.CONTEXTROOT);
	    prop.setValue(operationDataModel.getStringProperty(WebComponentCreationDataModel.CONTEXT_ROOT));
	    newProps.add(prop);
	    return newProps;
	}

}