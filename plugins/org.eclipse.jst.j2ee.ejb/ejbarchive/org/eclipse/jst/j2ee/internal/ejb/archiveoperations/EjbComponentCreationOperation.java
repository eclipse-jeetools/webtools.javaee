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

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.application.operations.J2EEComponentCreationOperation;
import org.eclipse.jst.j2ee.ejb.internal.modulecore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.wst.common.modulecore.ModuleCore;
import org.eclipse.wst.common.modulecore.WorkbenchComponent;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;

public class EjbComponentCreationOperation extends J2EEComponentCreationOperation {
	public EjbComponentCreationOperation(EjbComponentCreationDataModel dataModel) {
		super(dataModel);
	}

	public EjbComponentCreationOperation() {
		super();
	}

	
	protected void createDeploymentDescriptor(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {

		//should cache wbmodule when created instead of  searching ?
        ModuleCore moduleCore = null;
        WorkbenchComponent wbmodule = null;
        try {
            moduleCore = ModuleCore.getModuleCoreForRead(getProject());
            wbmodule = moduleCore.findWorkbenchModuleByDeployName(operationDataModel.getStringProperty(EjbComponentCreationDataModel.MODULE_DEPLOY_NAME));
        } finally {
            if (null != moduleCore) {
                moduleCore.dispose();
            }
        }		


        EJBArtifactEdit ejbEdit = null;
       	try{

       		ejbEdit = EJBArtifactEdit.getEJBArtifactEditForWrite( wbmodule );

       		String projPath = getProject().getLocation().toOSString();
       		
       		projPath += operationDataModel.getProperty( EjbComponentCreationDataModel.DD_FOLDER );
       		projPath +=IPath.SEPARATOR + J2EEConstants.EJBJAR_DD_SHORT_NAME;

       		
       		IPath ejbxmlPath = new Path(projPath);
       		boolean b = ejbxmlPath.isValidPath(ejbxmlPath.toString());
       		if(ejbEdit != null) {
       			int moduleVersion = operationDataModel.getIntProperty(EjbComponentCreationDataModel.J2EE_MODULE_VERSION);
  			
       			ejbEdit.createModelRoot( getProject(), ejbxmlPath, moduleVersion );
       		}
       	}
       	catch(Exception e){
            e.printStackTrace();
       	} finally {
       		if(ejbEdit != null)
       			ejbEdit.dispose();
       	}	
       	
	}

	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		
		super.execute( IModuleConstants.JST_EJB_MODULE, monitor );

	}

	protected  void addResources( WorkbenchComponent component ){
		//addResource(component, getModuleRelativeFile(getMetaInfPath( getModuleName() ), getProject()), getMetaInfPathDeployPath());
		addResource(component, getModuleRelativeFile(getJavaSourceSourcePath( getModuleName() ), getProject()), getJavaSourceDeployPath());		
	}
	
	/**
	 * @return
	 */
	public String getJavaSourceSourcePath(String moduleName) {
		return "/" + moduleName +"/ejbModule"; //$NON-NLS-1$
	}
	
	/**
	 * @return
	 */
	public String getJavaSourceDeployPath() {
		return "/"; //$NON-NLS-1$
	}
	
	/**
	 * @return
	 */
	public String getMetaInfPath(String moduleName) {
		return "/" + moduleName; //$NON-NLS-1$
	}
	
	/**
	 * @return
	 */
	public String getMetaInfPathDeployPath() {
		return "/"; //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.application.operations.FlexibleJ2EEModuleCreationOperation#createProjectStructure()
	 */
	protected void createProjectStructure() throws CoreException {

		IFolder moduleFolder = getProject().getFolder(  getModuleName() );
		if (!moduleFolder.exists()) {
			moduleFolder.create(true, true, null);
		}
		IFolder ejbModuleFolder = moduleFolder.getFolder( "ejbModule" );
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
	
}